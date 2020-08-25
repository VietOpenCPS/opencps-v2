package org.opencps.backend.dossiermgt.logistic;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.communication.model.ServerConfig;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.model.PostConnect;
import org.opencps.dossiermgt.rest.model.ViettelPostUpdateOrder;
import org.opencps.dossiermgt.service.PostConnectLocalServiceUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyService;
import org.opencps.backend.dossiermgt.serviceapi.ApiThirdPartyServiceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
            String apiGetToken = this.configJson.getString(ViettelPostTerm.API_GET_TOKEN);
            String userName    = this.configJson.getString(ViettelPostTerm.USER);
            String password    = this.configJson.getString(ViettelPostTerm.PASS);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = new HashMap<>();
            body.put(ViettelPostTerm.USER_NAME, userName);
            body.put(ViettelPostTerm.PASSWORD, password);

            JSONObject response = apiService.callApi(apiGetToken, headers, body);
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
            return token;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void postBill(String token, String orderServiceFromVT, JSONObject dossierObj) throws Exception{
        try {
            if(Validator.isNull(dossierObj)) {
                throw new Exception("dossierObj null");
            }

            String apiCreateBill   = this.configJson.getString(ViettelPostTerm.API_POST_ORDER);
            String orderPayment    = this.configJson.getString(ViettelPostTerm.ORDER_PAYMENT_CONFIG);
            String senderWard      = this.configJson.getString(ViettelPostTerm.SENDER_WARD_CONFIG);
            String senderDistrict  = this.configJson.getString(ViettelPostTerm.SENDER_DISTRICT_CONFIG);
            String senderProvince  = this.configJson.getString(ViettelPostTerm.SENDER_PROVINCE_CONFIG);
            String senderLatitude  = this.configJson.getString(ViettelPostTerm.SENDER_LATITUDE_CONFIG);
            String senderLongitude = this.configJson.getString(ViettelPostTerm.SENDER_LONGITUDE_CONFIG);

            String receiveWard      = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_WARD_CODE))
                    ? dossierObj.getString(DossierTerm.POSTAL_WARD_CODE) : "0" ;
            String receiveDistrict  = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE))
                    ? dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE) : "0" ;
            String receiveProvince  = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_CITY_CODE))
                    ? dossierObj.getString(DossierTerm.POSTAL_CITY_CODE) : "0" ;

            Integer orderPaymentInt = 3;
            Integer senderWardInt = 0;
            Integer senderDistrictInt = 0;
            Integer senderProvinceInt = 0;
            Integer senderLatitudeInt = 0;
            Integer senderLongitudeInt = 0;

            Integer receiveWardInt = 0;
            Integer receiveDistrictInt = 0;
            Integer receiveProvinceInt = 0;
            try{
                orderPaymentInt    = Validator.isNotNull(orderPayment)? Integer.parseInt(orderPayment) : 3;
                senderWardInt      = Validator.isNotNull(senderWard)? Integer.parseInt(senderWard) : 0;
                senderDistrictInt  = Validator.isNotNull(senderDistrict)? Integer.parseInt(senderDistrict) : 0;
                senderProvinceInt  = Validator.isNotNull(senderProvince)? Integer.parseInt(senderProvince) : 0;
                senderLatitudeInt  = Validator.isNotNull(senderLatitude)? Integer.parseInt(senderLatitude) : 0;
                senderLongitudeInt = Validator.isNotNull(senderLongitude)? Integer.parseInt(senderLongitude) : 0;

                receiveWardInt = Validator.isNotNull(receiveWard) ? Integer.parseInt(receiveWard) : 0;
                receiveDistrictInt = Validator.isNotNull(receiveDistrict) ? Integer.parseInt(receiveDistrict) : 0;
                receiveProvinceInt = Validator.isNotNull(receiveProvince) ? Integer.parseInt(receiveProvince) : 0;

            } catch (Exception e) {
                _log.error("Parse string to int fail");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set(ViettelPostTerm.TOKEN, token);
            Map<String, Object> body = new HashMap<>();
            body.put(ViettelPostTerm.ORDER_NUMBER, "");
            body.put(ViettelPostTerm.GROUPADDRESS_ID, Integer.valueOf(this.configJson.getString(ViettelPostTerm.GROUP_ADDRESS_ID_CONFIG)));
            body.put(ViettelPostTerm.CUS_ID, Integer.valueOf(this.configJson.getString(ViettelPostTerm.CUS_ID_CONFIG)));
            body.put(ViettelPostTerm.DELIVERY_DATE, "08/08/2020 15:09:52");
            body.put(ViettelPostTerm.SENDER_FULLNAME, this.configJson.getString(ViettelPostTerm.SENDER_NAME_CONFIG));
            body.put(ViettelPostTerm.SENDER_ADDRESS, this.configJson.getString(ViettelPostTerm.SENDER_ADDRESS_CONFIG));
            body.put(ViettelPostTerm.SENDER_PHONE, this.configJson.getString(ViettelPostTerm.SENDER_PHONE_CONFIG));
            body.put(ViettelPostTerm.SENDER_EMAIL, this.configJson.getString(ViettelPostTerm.SENDER_EMAIL_CONFIG));
            body.put(ViettelPostTerm.SENDER_WARD, senderWardInt);
            body.put(ViettelPostTerm.SENDER_DISTRICT, senderDistrictInt);
            body.put(ViettelPostTerm.SENDER_PROVINCE, senderProvinceInt);
            body.put(ViettelPostTerm.SENDER_LATITUDE, senderLatitudeInt);
            body.put(ViettelPostTerm.SENDER_LONGITUDE, senderLongitudeInt);
            body.put(ViettelPostTerm.RECEIVER_FULLNAME, dossierObj.getString(DossierTerm.APPLICANT_NAME));
            body.put(ViettelPostTerm.RECEIVER_ADDRESS, dossierObj.getString(DossierTerm.POSTAL_ADDRESS));
            body.put(ViettelPostTerm.RECEIVER_PHONE, dossierObj.getString(DossierTerm.POSTAL_TEL_NO));
            body.put(ViettelPostTerm.RECEIVER_EMAIL, "");
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
            JSONObject response = apiService.callApi(apiCreateBill, headers, body);
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

            PostConnectLocalServiceUtil.createOrUpdatePostConnect(
                    dossierObj.getLong(Field.GROUP_ID), dossierObj.getLong(DossierTerm.USER_ID),
                    dossierObj.getLong(DossierTerm.DOSSIER_ID),
                    ViettelPostTerm.VIETTEL_POST, ViettelPostTerm.RECEIVE_DOSSIER,
                    orderNumber, ViettelPostTerm.FIRST_TIME_CREATE, "",
                    PublishQueueTerm.STATE_NOT_SYNC, 0
            );
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String getOrderService(String token, JSONObject dossierObj) throws Exception {
        try {
            String senderDistrict  = this.configJson.getString(ViettelPostTerm.SENDER_DISTRICT_CONFIG);
            String senderProvince  = this.configJson.getString(ViettelPostTerm.SENDER_PROVINCE_CONFIG);
            String receiveDistrict  = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE))
                    ? dossierObj.getString(DossierTerm.POSTAL_DISTRICT_CODE) : "0" ;
            String receiveProvince  = Validator.isNotNull(dossierObj.getString(DossierTerm.POSTAL_CITY_CODE))
                    ? dossierObj.getString(DossierTerm.POSTAL_CITY_CODE) : "0" ;


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
                _log.error("GET ORDER SERVICE|Parse string to int fail");
            }

            String apiGetOrderService = this.configJson.getString(ViettelPostTerm.API_GET_PRICE);
            HttpHeaders headers = new HttpHeaders();
            headers.set(ViettelPostTerm.TOKEN, token);
            Map<String, Object> body = new HashMap<>();
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
            JSONArray response = apiService.callApiWithResponseArray(apiGetOrderService, headers, body);

            if(Validator.isNull(response) || response.length() == 0) {
                throw new Exception("Response get price all is invalid data");
            }

//            boolean isError = response.getBoolean("error");
//            if(isError) {
//                String message = response.getString("message") != null ? response.getString("message") : "";
//                if(message.isEmpty()){
//                    message = "Message error null";
//                }
//                throw new Exception(message);
//            }

            JSONObject orderService = Validator.isNotNull(response.get(0)) ? (JSONObject) response.get(0) : null;

            if(Validator.isNull(orderService)) {
                throw new Exception("Response get(0) price all null");
            }

            if(Validator.isNull(orderService.getString(ViettelPostTerm.MA_DV_CHINH))
                || orderService.getString(ViettelPostTerm.MA_DV_CHINH).isEmpty()) {
               throw new Exception("Ma DV CHINH is null");
            }

            return orderService.getString(ViettelPostTerm.MA_DV_CHINH);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean updateBill(ViettelPostUpdateOrder updateInfo) throws Exception{
        try {
            PostConnect result = PostConnectLocalServiceUtil.createOrUpdatePostConnect(0, 0,0, 0, 0,
                    updateInfo.getORDER_NUMBER(), updateInfo.getTYPE(), "",
                    null, 0);
            if(Validator.isNull(result)){
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
