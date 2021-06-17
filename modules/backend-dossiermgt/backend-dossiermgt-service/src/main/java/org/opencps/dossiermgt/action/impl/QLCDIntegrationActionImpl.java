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
import org.opencps.dossiermgt.input.model.FileVBModel;
import org.opencps.dossiermgt.input.model.QLVBModel;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Base64;
import java.util.Iterator;
import java.util.List;

public class QLCDIntegrationActionImpl implements QLCDIntegrationAction {
    private JSONObject configJson;
    private ApiThirdPartyService apiService;
    private Log _log = LogFactoryUtil.getLog(QLCDIntegrationActionImpl.class);
    private static final String SERVER_CONFIG_NULL = "There is no server config frequency";
    private static final String PARSE_CONFIG_JSON_FAIL= "Create object json from config error";
    private ObjectMapper objectMapper;
    private ServerConfig serverConfig;
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

            JSONObject bodyRequest = JSONFactoryUtil.createJSONObject();
            bodyRequest.put("accessToken", "Bearer " + token);
            bodyRequest.put("lgspAccessToken", this.base64LgspToken);
            bodyRequest.put("data", createRequestSoap(body));

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

    @Override
    public String createRequestSoap(JSONObject body) throws Exception {
        try {
            StringBuffer soapRequestData = new StringBuffer("");
            soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:qldc=\"http://dancuquocgia.bca\">");
            soapRequestData.append("<soapenv:Header/>");
            soapRequestData.append("<soapenv:Body>");

            soapRequestData.append("<qldc:").append(body.getString(QLCDConstants.KEY_TYPE_API)).append(">");

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

            soapRequestData.append("</qldc:").append(body.getString(QLCDConstants.KEY_TYPE_API)).append(">");
            soapRequestData.append("</soapenv:Body>");
            soapRequestData.append("</soapenv:Envelope>");
            return soapRequestData.toString();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
