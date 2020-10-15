package org.opencps.backend.dossiermgt.logistic;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Validator;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpConnection;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyService;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyServiceImpl;
import org.opencps.communication.model.ServerConfig;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PostConnect;
import org.opencps.dossiermgt.rest.model.ViettelPostUpdateOrder;
import org.opencps.dossiermgt.service.PostConnectLocalServiceUtil;
import org.springframework.http.MediaType;

import javax.ws.rs.core.HttpHeaders;

public class ViettelPostManagementImpl implements ViettelPostManagement {
    private JSONObject configJson;
    private ApiThirdPartyService apiService;
    private Log _log = LogFactoryUtil.getLog(ViettelPostManagementImpl.class);

    private static final String SERVER_CONFIG_NULL = "There is no server config viettle post";
    private static final String PARSE_CONFIG_JSON_FAIL= "Create object json from config error";

    public ViettelPostManagementImpl(ServerConfig serverConfig) throws Exception{
        this.apiService = new ApiThirdPartyServiceImpl();

        if(Validator.isNull(serverConfig)) {
            throw new Exception(SERVER_CONFIG_NULL);
        }

        if(Validator.isNull(serverConfig.getConfigs())
                || serverConfig.getConfigs().isEmpty()) {
            throw new Exception(SERVER_CONFIG_NULL);
        }

        this.configJson = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());;
        if(Validator.isNull(this.configJson)) {
            throw new Exception(PARSE_CONFIG_JSON_FAIL);
        }
    }

    @Override
    public String getToken() throws Exception {
        try {
//            _log.info("configJson" + JSONFactoryUtil.looseSerialize(configJson));
            String apiGetToken = this.configJson.getString(ViettelPostTerm.API_GET_TOKEN);
            _log.info("apiGetToken" + apiGetToken);
            String userName    = this.configJson.getString(ViettelPostTerm.USER);
            String password    = this.configJson.getString(ViettelPostTerm.VT_SECRECT_KEY);

//            HttpHeaders headers = new HttpHeaders();
//            headers.getHeaderString(Collections.singletonList(MediaType.APPLICATION_JSON));
//            headers.setContentType(MediaType.APPLICATION_JSON);

            JSONObject body = JSONFactoryUtil.createJSONObject();

            body.put(ViettelPostTerm.USER_NAME, userName);
            body.put(ViettelPostTerm.SECRET_KEY, password);

            HttpURLConnection conn = null;

//            Map<String, Object> body = new HashMap<>();
//            body.put(ViettelPostTerm.USER_NAME, userName);
//            body.put(ViettelPostTerm.SECRET_KEY, password);
            conn = getConnect(apiGetToken,"" );
            JSONObject response = apiService.callApi(apiGetToken, conn, body);
            if(Validator.isNull(response)) {
               throw new Exception("Response get token null");
            }

            JSONObject data = response.getJSONObject("data");
            if(Validator.isNull(data)){
                throw new Exception("Data get token null");
            }

            String token = data.getString("token");
            if(Validator.isNull(token)){
                throw new Exception("Token is empty");
            }
            _log.info("token" + token);
            return token;
        }catch (Exception e) {
            _log.debug(e);
            return StringPool.BLANK;
        }
    }

    private boolean hasFromViaPostal(JSONObject dossierObj) {
        try {
            if(Validator.isNull(dossierObj.getString(DossierTerm.VIETTEL_POST_KEY))) {
                return false;
            }

            if(!dossierObj.getString(DossierTerm.VIETTEL_POST_KEY).equals(DossierTerm.VIETTEL_POST_VALUE)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void postBill(String token, String orderServiceFromVT, JSONObject dossierObj) throws Exception{
        try {
            if(Validator.isNull(dossierObj)) {
                throw new Exception("dossierObj null");
            }
            boolean hasFromViaPostal = this.hasFromViaPostal(dossierObj);

            String senderWard;
            String senderDistrict;
            String senderProvince;
            String senderFullName;
            String senderAddress;
            String senderPhone;
            String senderEmail;

            String receiverFullName;
            String receiverAddress;
            String receiverPhone;
            String receiveWard;
            String receiveDistrict;
            String receiveProvince;
            String receiverEmail;

            if(!hasFromViaPostal) {
                //case lam tu dau
                senderFullName  = this.configJson.getString(ViettelPostTerm.SENDER_NAME_CONFIG);
                senderAddress   = this.configJson.getString(ViettelPostTerm.SENDER_ADDRESS_CONFIG);
                senderPhone     = this.configJson.getString(ViettelPostTerm.SENDER_PHONE_CONFIG);
                senderWard      = this.configJson.getString(ViettelPostTerm.SENDER_WARD_CONFIG);
                senderDistrict  = this.configJson.getString(ViettelPostTerm.SENDER_DISTRICT_CONFIG);
                senderProvince  = this.configJson.getString(ViettelPostTerm.SENDER_PROVINCE_CONFIG);
                senderEmail     = this.configJson.getString(ViettelPostTerm.SENDER_EMAIL_CONFIG);

                receiverFullName = dossierObj.getString(DossierTerm.APPLICANT_NAME);
                receiverAddress  = dossierObj.getString(DossierTerm.POSTAL_ADDRESS);
                receiverPhone    = dossierObj.getString(DossierTerm.POSTAL_TEL_NO);
                receiveWard      = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_WARD_CODE))
                        ? dossierObj.getString(DossierTerm.POSTAL_WARD_CODE) : "0" ;
                receiveDistrict  = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE))
                        ? dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE) : "0" ;
                receiveProvince  = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_CITY_CODE))
                        ? dossierObj.getString(DossierTerm.POSTAL_CITY_CODE) : "0" ;
                receiverEmail = "";
            } else {
                //case them vao fromViaPostal
                String vnPostalProfile = dossierObj.getString(DossierTerm.VNPOSTAL_PROFILE);
                if (Validator.isNull(vnPostalProfile)) {
                    throw new Exception("No vn postal profile with case from via postal");
                }
                JSONObject profileSenderJson = JSONFactoryUtil.createJSONObject(vnPostalProfile);

                if (Validator.isNull(profileSenderJson)) {
                    throw new Exception("No vn postal profile with case from via postal");
                }

                senderFullName = dossierObj.getString(DossierTerm.APPLICANT_NAME);
                senderAddress  = profileSenderJson.getString(DossierTerm.POSTAL_ADDRESS);
                senderPhone    = profileSenderJson.getString(DossierTerm.POSTAL_TEL_NO);
                senderWard     = Validator.isNotNull(profileSenderJson.getString(DossierTerm.POSTAL_WARD_CODE))?
                        profileSenderJson.getString(DossierTerm.POSTAL_WARD_CODE): "0";
                senderDistrict = Validator.isNotNull(profileSenderJson.getString(DossierTerm.POSTAL_DISTRICT_CODE))?
                    profileSenderJson.getString(DossierTerm.POSTAL_DISTRICT_CODE): "0";
                senderProvince = Validator.isNotNull(profileSenderJson.getString(DossierTerm.POSTAL_CITY_CODE))?
                        profileSenderJson.getString(DossierTerm.POSTAL_CITY_CODE): "0";
                senderEmail    = "";

                receiverFullName = this.configJson.getString(ViettelPostTerm.SENDER_NAME_CONFIG);
                receiverAddress  = this.configJson.getString(ViettelPostTerm.SENDER_ADDRESS_CONFIG);
                receiverPhone    = this.configJson.getString(ViettelPostTerm.SENDER_PHONE_CONFIG);
                receiveWard      = this.configJson.getString(ViettelPostTerm.SENDER_WARD_CONFIG);
                receiveDistrict  = this.configJson.getString(ViettelPostTerm.SENDER_DISTRICT_CONFIG);
                receiveProvince  = this.configJson.getString(ViettelPostTerm.SENDER_PROVINCE_CONFIG);
                receiverEmail    = this.configJson.getString(ViettelPostTerm.SENDER_EMAIL_CONFIG);
            }

            String apiCreateBill   = this.configJson.getString(ViettelPostTerm.API_POST_ORDER);
            String orderPayment    = this.configJson.getString(ViettelPostTerm.ORDER_PAYMENT_CONFIG);

            Integer orderPaymentInt = 3;
            Integer senderWardInt = 0;
            Integer senderDistrictInt = 0;
            Integer senderProvinceInt = 0;

            Integer receiveWardInt = 0;
            Integer receiveDistrictInt = 0;
            Integer receiveProvinceInt = 0;
            try{
                orderPaymentInt    = Validator.isNotNull(orderPayment)? Integer.parseInt(orderPayment) : 3;
                senderWardInt      = Validator.isNotNull(senderWard)? Integer.parseInt(senderWard) : 0;
                senderDistrictInt  = Validator.isNotNull(senderDistrict)? Integer.parseInt(senderDistrict) : 0;
                senderProvinceInt  = Validator.isNotNull(senderProvince)? Integer.parseInt(senderProvince) : 0;
                receiveWardInt = Validator.isNotNull(receiveWard) ? Integer.parseInt(receiveWard) : 0;
                receiveDistrictInt = Validator.isNotNull(receiveDistrict) ? Integer.parseInt(receiveDistrict) : 0;
                receiveProvinceInt = Validator.isNotNull(receiveProvince) ? Integer.parseInt(receiveProvince) : 0;

            } catch (Exception e) {
                _log.debug(e);
            }
            HttpURLConnection conn = null;
//            HttpHeaders headers = new HttpHeaders();
//            headers.set(ViettelPostTerm.TOKEN, token);
            JSONObject body = JSONFactoryUtil.createJSONObject();
//            Map<String, Object> body = new HashMap<>();
            body.put(ViettelPostTerm.ORDER_NUMBER, "");
            body.put(ViettelPostTerm.GROUPADDRESS_ID, Integer.valueOf(this.configJson.getString(ViettelPostTerm.GROUP_ADDRESS_ID_CONFIG)));
            body.put(ViettelPostTerm.CUS_ID, Integer.valueOf(this.configJson.getString(ViettelPostTerm.CUS_ID_CONFIG)));
            body.put(ViettelPostTerm.DELIVERY_DATE, "08/08/2020 15:09:52");
            body.put(ViettelPostTerm.SENDER_FULLNAME, senderFullName);
            body.put(ViettelPostTerm.SENDER_ADDRESS, senderAddress);
            body.put(ViettelPostTerm.SENDER_PHONE, senderPhone);
            body.put(ViettelPostTerm.SENDER_EMAIL, senderEmail);
            body.put(ViettelPostTerm.SENDER_WARD, senderWardInt);
            body.put(ViettelPostTerm.SENDER_DISTRICT, senderDistrictInt);
            body.put(ViettelPostTerm.SENDER_PROVINCE, senderProvinceInt);
            body.put(ViettelPostTerm.SENDER_LATITUDE, 0);
            body.put(ViettelPostTerm.SENDER_LONGITUDE, 0);
            body.put(ViettelPostTerm.RECEIVER_FULLNAME, receiverFullName);
            body.put(ViettelPostTerm.RECEIVER_ADDRESS, receiverAddress);
            body.put(ViettelPostTerm.RECEIVER_PHONE, receiverPhone);
            body.put(ViettelPostTerm.RECEIVER_EMAIL, receiverEmail);
            body.put(ViettelPostTerm.RECEIVER_WARD, receiveWardInt);
            body.put(ViettelPostTerm.RECEIVER_DISTRICT, receiveDistrictInt);
            body.put(ViettelPostTerm.RECEIVER_PROVINCE, receiveProvinceInt);
            body.put(ViettelPostTerm.RECEIVER_LATITUDE, 0);
            body.put(ViettelPostTerm.RECEIVER_LONGITUDE, 0);
            body.put(ViettelPostTerm.PRODUCT_NAME, dossierObj.getString(DossierTerm.DOSSIER_NAME));
            body.put(ViettelPostTerm.PRODUCT_DESCRIPTION, "");
            body.put(ViettelPostTerm.PRODUCT_QUANTITY, 0);
            body.put(ViettelPostTerm.PRODUCT_PRICE, 0);
            body.put(ViettelPostTerm.PRODUCT_WEIGHT, 0);
            body.put(ViettelPostTerm.PRODUCT_LENGTH, 0);
            body.put(ViettelPostTerm.PRODUCT_WIDTH, 0);
            body.put(ViettelPostTerm.PRODUCT_HEIGHT, 0);
            body.put(ViettelPostTerm.PRODUCT_TYPE, "HH");
            body.put(ViettelPostTerm.ORDER_PAYMENT, orderPaymentInt);
            body.put(ViettelPostTerm.ORDER_SERVICE, orderServiceFromVT);
            body.put(ViettelPostTerm.ORDER_SERVICE_ADD, "");
            body.put(ViettelPostTerm.ORDER_VOUCHER, "");
            body.put(ViettelPostTerm.ORDER_NOTE, "");
            body.put(ViettelPostTerm.MONEY_COLLECTION, 0);
            body.put(ViettelPostTerm.MONEY_TOTALFEE, 0);
            body.put(ViettelPostTerm.MONEY_FEECOD, 0);
            body.put(ViettelPostTerm.MONEY_FEEVAS, 0);
            body.put(ViettelPostTerm.MONEY_FEEINSURRANCE, 0);
            body.put(ViettelPostTerm.MONEY_FEE, 0);
            body.put(ViettelPostTerm.MONEY_FEEOTHER, 0);
            body.put(ViettelPostTerm.MONEY_TOTALVAT, 0);
            body.put(ViettelPostTerm.MONEY_TOTAL, 0);
            _log.info("Body json post bill: " + body);

            conn = getConnect(apiCreateBill,token);
            JSONObject response = apiService.callApi(apiCreateBill, conn, body);
            if(Validator.isNull(response)) {
                throw new Exception("Response create bill null");
            }

            boolean isError = response.getBoolean("error");
            if(isError) {
                String message = response.getString("message") != null ? response.getString("message") : "";
                if(message.isEmpty()){
                    message = "Message error null";
                }
                throw new Exception(message);
            }

            JSONObject data = response.getJSONObject("data");
            if(Validator.isNull(data)){
                throw new Exception("Data create bill null");
            }

            String orderNumber = data.getString(ViettelPostTerm.ORDER_NUMBER);
            _log.info("vnPostalStatus :" + dossierObj.getLong(DossierTerm.VNPOSTAL_STATUS));
            String vnPostalStatus = String.valueOf(dossierObj.getLong(DossierTerm.VNPOSTAL_STATUS));

            PostConnectLocalServiceUtil.createOrUpdatePostConnect(
                    dossierObj.getLong(Field.GROUP_ID), dossierObj.getLong(DossierTerm.USER_ID),
                    dossierObj.getLong(DossierTerm.DOSSIER_ID),
                    ViettelPostTerm.VIETTEL_POST, "1".equals(vnPostalStatus) ? ViettelPostTerm.SEND_DOSSIER :ViettelPostTerm.RECEIVE_DOSSIER,
                    orderNumber, ViettelPostTerm.FIRST_TIME_CREATE, "",
                    PublishQueueTerm.STATE_NOT_SYNC, 0
            );
        }catch (Exception e) {
            _log.debug(e);
        }
    }

    @Override
    public String getOrderService(String token, JSONObject dossierObj) throws Exception {
        try {
            String senderDistrict  = this.configJson.getString(ViettelPostTerm.SENDER_DISTRICT_CONFIG);
            String senderProvince  = this.configJson.getString(ViettelPostTerm.SENDER_PROVINCE_CONFIG);
//            String receiveDistrict  = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE))
//                    ? dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE) : "0" ;
//            String receiveProvince  = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_CITY_CODE))
//                    ? dossierObj.getString(DossierTerm.POSTAL_CITY_CODE) : "0" ;
            String vnPostalProfile = dossierObj.getString(DossierTerm.VNPOSTAL_PROFILE);
            JSONObject profileSenderJson = JSONFactoryUtil.createJSONObject(vnPostalProfile);
            String receiveDistrict =  Validator.isNotNull(profileSenderJson.getString(DossierTerm.POSTAL_DISTRICT_CODE))?
                    profileSenderJson.getString(DossierTerm.POSTAL_DISTRICT_CODE): "0";
            String receiveProvince = Validator.isNotNull(profileSenderJson.getString(DossierTerm.POSTAL_CITY_CODE))?
                    profileSenderJson.getString(DossierTerm.POSTAL_CITY_CODE): "0";


            Integer receiveDistrictInt = 0;
            Integer receiveProvinceInt = 0;
            Integer senderDistrictInt = 0;
            Integer senderProvinceInt = 0;

            try{
                senderDistrictInt  = Validator.isNotNull(senderDistrict)? Integer.parseInt(senderDistrict) : 0;
                senderProvinceInt  = Validator.isNotNull(senderProvince)? Integer.parseInt(senderProvince) : 0;

                receiveDistrictInt = Validator.isNotNull(receiveDistrict) ? Integer.parseInt(receiveDistrict) : 0;
                receiveProvinceInt = Validator.isNotNull(receiveProvince) ? Integer.parseInt(receiveProvince) : 0;

            } catch (Exception e) {
                _log.error(e);
            }

            String apiGetOrderService = this.configJson.getString(ViettelPostTerm.API_GET_PRICE);
            HttpURLConnection conn = null;
//            HttpHeaders headers = new HttpHeaders();
//            headers.set(ViettelPostTerm.TOKEN, token);

//            Map<String, Object> body = new HashMap<>();
            JSONObject body = JSONFactoryUtil.createJSONObject();
            body.put(ViettelPostTerm.SENDER_PROVINCE, senderProvinceInt);
            body.put(ViettelPostTerm.SENDER_DISTRICT, senderDistrictInt);
            body.put(ViettelPostTerm.RECEIVER_PROVINCE, receiveProvinceInt);
            body.put(ViettelPostTerm.RECEIVER_DISTRICT, receiveDistrictInt);
            body.put(ViettelPostTerm.PRODUCT_TYPE, "HH");
            body.put(ViettelPostTerm.PRODUCT_WEIGHT, 0);
            body.put(ViettelPostTerm.PRODUCT_PRICE, 0);
            body.put(ViettelPostTerm.MONEY_COLLECTION, "0");
            body.put(ViettelPostTerm.TYPE, 1);
            _log.info("Body json get order service: " + body);
            conn = getConnect(apiGetOrderService,token);
//            _log.info("TOKEN :" +token);
            _log.info("apiGetOrderService :" +apiGetOrderService);
            JSONArray response = apiService.callApiWithResponseArray(apiGetOrderService, conn, body);

            if(Validator.isNull(response) || response.length() == 0) {
                throw new Exception("Response get price all is invalid data");
            }

            JSONObject orderService = Validator.isNotNull(response.get(0)) ? (JSONObject) response.get(0) : null;

            if(orderService == null) {
                throw new Exception("Response get(0) price all null");
            }

            if(orderService != null && Validator.isNull(orderService.getString(ViettelPostTerm.MA_DV_CHINH))
                || orderService.getString(ViettelPostTerm.MA_DV_CHINH).isEmpty()) {
               throw new Exception("Ma DV CHINH is null");
            }

            return orderService.getString(ViettelPostTerm.MA_DV_CHINH);
        } catch (Exception e) {
           _log.debug(e);
           return StringPool.BLANK;
        }
    }

    @Override
    public boolean updateBill(ViettelPostUpdateOrder updateInfo) throws Exception{
        try {
            PostConnect result = PostConnectLocalServiceUtil.createOrUpdatePostConnect(0, 0,0, 0, 0,
                    updateInfo.getDATA().getORDER_NUMBER(), updateInfo.getDATA().getORDER_STATUS(), "",
                    null, 0);
            if(Validator.isNull(result)){
                return false;
            }
            return true;
        } catch (Exception e) {
        	_log.debug(e);
        	return false;
        }
    }
    private HttpURLConnection getConnect(String enpoint, String token){
        HttpURLConnection conn = null;
        try {
            URL url = new URL(enpoint);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Charset", "utf-8");
            conn.setRequestProperty("token", token);
//            _log.info("TOKEN : " + token);
            conn.setInstanceFollowRedirects(true);
            HttpURLConnection.setFollowRedirects(true);
            conn.setReadTimeout(60 * 1000);

//            byte[] postData = body.toJSONString().getBytes("UTF-8");
//            int postDataLength = postData.length;
//            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
//            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
//                wr.write(postData);
//            }

            conn.connect();
        }catch (Exception e){
            e.getMessage();
        }
        return conn;
    }
}
