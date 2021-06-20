package org.opencps.dossiermgt.action.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyService;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyServiceImpl;
import org.opencps.communication.model.ServerConfig;
import org.opencps.dossiermgt.action.QLCDIntegrationAction;
import org.opencps.dossiermgt.constants.QLCDConstants;
import org.opencps.dossiermgt.model.CsdlDcServiceInfo;
import org.opencps.dossiermgt.model.CsdlDcUser;
import org.opencps.dossiermgt.service.CsdlDcServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.CsdlDcUserLocalServiceUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Base64;
import java.util.Iterator;

public class QLCDIntegrationActionImpl implements QLCDIntegrationAction {
    private JSONObject configJson;
    private ApiThirdPartyService apiService;
    private Log _log = LogFactoryUtil.getLog(QLCDIntegrationActionImpl.class);
    private static final String SERVER_CONFIG_NULL = "There is no server config frequency";
    private static final String PARSE_CONFIG_JSON_FAIL= "Create object json from config error";
    private ServerConfig serverConfig;
    private static final Integer STATUS_ACTIVE = 1;
    private String base64LgspToken;

    public QLCDIntegrationActionImpl(ServerConfig serverConfig) throws Exception{
        this.apiService = new ApiThirdPartyServiceImpl();

        if(Validator.isNull(serverConfig.getConfigs())
                || serverConfig.getConfigs().isEmpty()) {
            throw new Exception(SERVER_CONFIG_NULL);
        }
        this.configJson = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
        this.serverConfig = serverConfig;
        if(Validator.isNull(this.configJson)) {
            throw new Exception(PARSE_CONFIG_JSON_FAIL);
        }
        try {
            String  lgspAccessToken = "{\"AccessKey\":\"" + this.configJson.getString(QLCDConstants.CONFIG_ACCESS_KEY)
                    + "\",\"SecretKey\":\"" + this.configJson.getString(QLCDConstants.CONFIG_SECRET_KEY) +
                    "\",\"AppName\":\"" + this.configJson.getString(QLCDConstants.CONFIG_APP_NAME)
                    + "\",\"PartnerCode\":\"" + this.configJson.getString(QLCDConstants.CONFIG_PARTNER_CODE)
                    + "\",\"PartnerCodeCus\":\"" + this.configJson.getString(QLCDConstants.CONFIG_PARTNER_CODE_CUS) + "\"}";
            this.base64LgspToken = Base64.getEncoder().encodeToString(lgspAccessToken.getBytes());
        } catch (Exception e) {
            _log.error(e);
            this.base64LgspToken = "";
        }

    }

    @Override
    public String getToken() throws Exception {
        try {
            String urlGetToken = this.configJson.getString(QLCDConstants.CONFIG_URL)
                    + this.configJson.getString(QLCDConstants.CONFIG_GET_TOKEN);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            _log.info("lgspaccesstoken: " + headers.get("lgspaccesstoken"));
            JSONObject result = this.apiService.callApiEncode(urlGetToken, headers, null,
                    true, this.base64LgspToken);

            if(Validator.isNotNull(result) && result.has("access_token")) {
                return result.getString("access_token");
            }

            throw new Exception("No token found");
        } catch (Exception e) {
            _log.error(e);
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public String sendData(String token, JSONObject body) throws Exception {
        try {
            String url = this.configJson.getString(QLCDConstants.CONFIG_URL)
                    + this.configJson.getString(body.getString("type"));
            JSONObject bodyValid = validateBodyJson(body);
            JSONObject bodyRequest = JSONFactoryUtil.createJSONObject();

            bodyRequest.put("accessToken", "Bearer " + token);
            bodyRequest.put("lgspAccessToken", this.base64LgspToken);
            bodyRequest.put("AuthHash", bodyValid.getString(QLCDConstants.KEY_AuthHash));

            //remove unnecessary key to forward to LGSP
            bodyValid.remove(QLCDConstants.KEY_AuthHash);
            bodyValid.remove(QLCDConstants.KEY_StaffEmail);
            bodyValid.remove(QLCDConstants.KEY_GovAgencyCode);

            bodyRequest.put("data", createRequestSoap(bodyValid));

            //Call api DLDC
            String soapResponse = this.apiService.callApiWithRawBody(url, bodyRequest);

            //Parse xml result to json
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode node = xmlMapper.readTree(soapResponse);
            ObjectMapper jsonMapper = new ObjectMapper();

            return jsonMapper.writeValueAsString(node);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private JSONObject validateBodyJson(JSONObject body) throws Exception {
        try {
            if(!body.has(QLCDConstants.KEY_MaDVC) || Validator.isNull(body.getString(QLCDConstants.KEY_MaDVC))) {
                throw new Exception("No key MaDVC was found");
            }

            if(!body.has(QLCDConstants.KEY_GovAgencyCode) || Validator.isNull(body.getString(QLCDConstants.KEY_GovAgencyCode))) {
                throw new Exception("No key GovAgencyCode was found");
            }

            if(!body.has(QLCDConstants.KEY_StaffEmail) || Validator.isNull(body.getString(QLCDConstants.KEY_StaffEmail))) {
                throw new Exception("No key StaffEmail was found");
            }

            CsdlDcServiceInfo serviceInfoMapping = CsdlDcServiceInfoLocalServiceUtil.findByServiceCodeAndStatus(
                            body.getString(QLCDConstants.KEY_MaDVC), STATUS_ACTIVE);

            if(Validator.isNull(serviceInfoMapping)) {
                throw new Exception("No mapping service code was found with key MaDVC: " + body.getString(QLCDConstants.KEY_MaDVC));
            }

            CsdlDcUser staffInfoMapping = CsdlDcUserLocalServiceUtil.findByGovAndEmailAndStatus(
                    body.getString(QLCDConstants.KEY_GovAgencyCode), body.getString(QLCDConstants.KEY_StaffEmail), STATUS_ACTIVE
            );

            if(Validator.isNull(staffInfoMapping)) {
                throw new Exception("No mapping staff was found with key GovAgencyCode: "
                        + body.getString(QLCDConstants.KEY_GovAgencyCode) + ", Staff: "
                        + body.getString(QLCDConstants.KEY_StaffEmail));
            }
            String keyStringAuthHash = "";
            try {
                keyStringAuthHash = staffInfoMapping.getKeyName() + ":" + staffInfoMapping.getKeyPass();
            } catch (Exception e) {
                e.printStackTrace();
            }

            body.put(QLCDConstants.KEY_AuthHash, Base64.getEncoder().encodeToString(keyStringAuthHash.getBytes()));
            body.put(QLCDConstants.KEY_MaCanBo, staffInfoMapping.getUserName());

            return body;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public String createRequestSoap(JSONObject body) throws Exception {
        try {
            StringBuffer soapRequestData = new StringBuffer("");
            soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:qldc=\"http://dancuquocgia.bca\">");
            soapRequestData.append("<soapenv:Header/>");
            soapRequestData.append("<soapenv:Body>");

            String type = body.getString(QLCDConstants.KEY_TYPE_API);
            body.remove(QLCDConstants.KEY_TYPE_API);
            soapRequestData.append("<qldc:").append(type).append(">");

            //Some key must order by this
            if(body.has(QLCDConstants.KEY_MaYeuCau)) {
                soapRequestData.append("<qldc:").append(QLCDConstants.KEY_MaYeuCau).append(">");
                soapRequestData.append(body.getString(QLCDConstants.KEY_MaYeuCau));
                soapRequestData.append("</qldc:").append(QLCDConstants.KEY_MaYeuCau).append(">");
                body.remove(QLCDConstants.KEY_MaYeuCau);
            }

            if(body.has(QLCDConstants.KEY_MaDVC)) {
                soapRequestData.append("<qldc:").append(QLCDConstants.KEY_MaDVC).append(">");
                soapRequestData.append(body.getString(QLCDConstants.KEY_MaDVC));
                soapRequestData.append("</qldc:").append(QLCDConstants.KEY_MaDVC).append(">");
                body.remove(QLCDConstants.KEY_MaDVC);
            }

            if(body.has(QLCDConstants.KEY_MaTichHop)) {
                soapRequestData.append("<qldc:").append(QLCDConstants.KEY_MaTichHop).append(">");
                soapRequestData.append(body.getString(QLCDConstants.KEY_MaTichHop));
                soapRequestData.append("</qldc:").append(QLCDConstants.KEY_MaTichHop).append(">");
                body.remove(QLCDConstants.KEY_MaTichHop);
            }

            if(body.has(QLCDConstants.KEY_MaCanBo)) {
                soapRequestData.append("<qldc:").append(QLCDConstants.KEY_MaCanBo).append(">");
                soapRequestData.append(body.getString(QLCDConstants.KEY_MaCanBo));
                soapRequestData.append("</qldc:").append(QLCDConstants.KEY_MaCanBo).append(">");
                body.remove(QLCDConstants.KEY_MaCanBo);
            }

            if(body.has(QLCDConstants.KEY_SoDinhDanh)) {
                soapRequestData.append("<qldc:").append(QLCDConstants.KEY_SoDinhDanh).append(">");
                soapRequestData.append(body.getString(QLCDConstants.KEY_SoDinhDanh));
                soapRequestData.append("</qldc:").append(QLCDConstants.KEY_SoDinhDanh).append(">");
                body.remove(QLCDConstants.KEY_SoDinhDanh);
            }

            Iterator<String> keys = body.keys();

            String key;
            while(keys.hasNext()) {
                key = keys.next();

                soapRequestData.append("<qldc:").append(key).append(">");

                if(key.equals(QLCDConstants.KEY_NgayThangNamSinh)) {
                    soapRequestData.append("<qldc:").append(QLCDConstants.KEY_NgayThangNam).append(">");
                    soapRequestData.append(body.getString(key));
                    soapRequestData.append("</qldc:").append(QLCDConstants.KEY_NgayThangNam).append(">");
                } else if (key.equals(QLCDConstants.KEY_Paging)) {
                    JSONObject pagingObj = body.getJSONObject(key) ;
                    if(Validator.isNotNull(pagingObj)) {
                        Iterator<String> keyPages = pagingObj.keys();
                        String keyPage;
                        while (keyPages.hasNext()) {
                            keyPage = keyPages.next();
                            soapRequestData.append("<qldc:").append(keyPage).append(">");
                            soapRequestData.append(pagingObj.getString(keyPage));
                            soapRequestData.append("</qldc:").append(keyPage).append(">");
                        }
                    }
                } else {
                    soapRequestData.append(body.getString(key));
                }

                soapRequestData.append("</qldc:").append(key).append(">");
            }

            soapRequestData.append("</qldc:").append(type).append(">");
            soapRequestData.append("</soapenv:Body>");
            soapRequestData.append("</soapenv:Envelope>");
            return soapRequestData.toString();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
