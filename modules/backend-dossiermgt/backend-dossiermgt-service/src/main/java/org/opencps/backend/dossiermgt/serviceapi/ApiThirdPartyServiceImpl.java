package org.opencps.backend.dossiermgt.serviceapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;
import org.opencps.dossiermgt.input.model.SyncTrackingInfo;
import org.opencps.usermgt.service.util.LGSPRestfulUtils;
import org.apache.http.impl.client.HttpClients;
import org.opencps.dossiermgt.input.model.ResponseListDossier;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.DataOutputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiThirdPartyServiceImpl implements ApiThirdPartyService{
    private RestTemplate restTemplate;
    private static final Integer timeout = 10000 ;
    private Log _log = LogFactoryUtil.getLog(ApiThirdPartyServiceImpl.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    public static final Integer SYNC_SUCCESS = 1;
    public static final Integer SYNC_ERROR = 2;

    private enum ListPaygovUnitLocal {
        DONGTHAP("PAYGOV-DONGTHAP"),
        HAUGIANG("PAYGOV-HAUGIANG"),
        BGTVT("PAYGOV-BOGTVT");

        private final String value;

        ListPaygovUnitLocal(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }


    public ApiThirdPartyServiceImpl(){
        this.restTemplate = new RestTemplate(setConfigRestTemplate(timeout));
        this.restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    private ClientHttpRequestFactory setConfigRestTemplate(Integer timeout) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setHttpClient(HttpClients.createDefault());
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }

    @Override
    public String getTokenLGSP(JSONObject paygovConfig) throws Exception{
        try {
            if(paygovConfig.getString("partnerCode").equals(ListPaygovUnitLocal.DONGTHAP.getValue())) {
                JSONObject jsonToken = LGSPRestfulUtils.createTokenLGSP("Bearer");
                if (jsonToken != null && jsonToken.has("token") && jsonToken.has("refreshToken")
                        && jsonToken.has("expiryDate")) {
                    return jsonToken.getString("token");
                }
            }  else {
                //other unit
                return paygovConfig.getString("token");
            }

            throw new Exception("Token is empty with paygov config: " + paygovConfig);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String getUrlRedirectToPaygovBackup(String token, Map<String, Object> body, JSONObject paygovConfig) throws Exception {
        try{
            _log.info("Body get url redirect paygov: " + body);
            String url = paygovConfig.getString("urlPaygate");

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            headers.add("lgspaccesstoken", paygovConfig.getString("lgspAccessToken"));

            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject response = this.callApi(url, headers, body);
            if(Validator.isNull(response)) {
                throw new Exception(response.toString());
            }

            if(Validator.isNull(response.getJSONObject("data"))) {
                throw new Exception(response.toString());
            }

            if(Validator.isNull(response.getJSONObject("data").getString("url"))) {
                throw new Exception(response.toString());
            }

            String urlRedirect = response.getJSONObject("data").getString("url");
            if(urlRedirect.isEmpty()) {
                throw new Exception("Url from paygov is empty");
            }

            return urlRedirect;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String getUrlRedirectToPaygov(String token, Map<String, Object> body, JSONObject paygovConfig) throws Exception {

        HttpURLConnection conn = null;
        JSONObject response = JSONFactoryUtil.createJSONObject();
        String urlRedirect = StringPool.BLANK;
        try {
            _log.debug("token: " + token);
            String endpoint = paygovConfig.getString("urlPaygate");


            JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

            for (Map.Entry<String, Object> entry : body.entrySet()) {
                _log.debug("Key = " + entry.getKey() + ", Value = " + entry.getValue());

                if(Validator.isNumber(String.valueOf(entry.getValue()))){
                    jsonBody.put(entry.getKey(), GetterUtil.getLong(entry.getValue()));
                }else{
                    jsonBody.put(entry.getKey(), GetterUtil.getString(entry.getValue()));
                }

            }
            _log.debug("++++jsonBody:" + jsonBody);

            URL url = new URL(endpoint);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Charset", "utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("lgspaccesstoken", paygovConfig.getString("lgspAccessToken"));
            conn.setInstanceFollowRedirects(true);
            HttpURLConnection.setFollowRedirects(true);
            conn.setReadTimeout(60 * 1000);

            byte[] postData = jsonBody.toJSONString().getBytes("UTF-8");

            int postDataLength = postData.length;
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }

            conn.connect();

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

                String output = StringPool.BLANK;

                StringBuilder sb = new StringBuilder();

                while ((output = bufferedReader.readLine()) != null) {
                    sb.append(output);
                }

                _log.debug("response: " + sb.toString());

                response = JSONFactoryUtil.createJSONObject(sb.toString());

            }

            if (Validator.isNull(response)) {
                throw new Exception(response.toString());
            }

            if (Validator.isNull(response.getJSONObject("data"))) {
                throw new Exception(response.toString());
            }

            if (Validator.isNull(response.getJSONObject("data").getString("url"))) {
                throw new Exception(response.toString());
            }

            urlRedirect = response.getJSONObject("data").getString("url");
            if (urlRedirect.isEmpty()) {
                throw new Exception("Url from paygov is empty");
            }

        } catch ( Exception e) {

            _log.error(e);
            throw new Exception(e.getMessage());
        }
        return urlRedirect;
    }

    @Override
    public boolean checkSum(String token, Map<String, Object> body) {
        try {
            _log.info("Body checksum paygov: " + body);
            String url = "https://api.dongthap.gov.vn/api/v1/PayGov/CheckSumUrlReturn";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject response = this.callApi(url, headers, body);
            if (Validator.isNull(response)) {
                throw new Exception(response.toString());
            }

            if(Validator.isNull(response.getString("error_code"))) {
                throw new Exception(response.toString());
            }

            if(response.getString("error_code").equals("SUCCESSFUL")) {
                return true;
            }
        } catch (Exception e) {
            _log.error("Error when check sum: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void callApiSaveTracking(SyncTrackingInfo body) throws Exception {
        try {
            if(Validator.isNull(body)) {
                throw new Exception("No SyncTrackingInfo was found");
            }

            if(Validator.isNull(body.groupId) || body.groupId == 0) {
                throw new Exception("No groupId was found");
            }

            if(Validator.isNull(body.urlSaveTracking) || body.urlSaveTracking.isEmpty()) {
                throw new Exception("Nor urlSaveTracking found");
            }

            if(Validator.isNull(body.api) || body.api.isEmpty()) {
                throw new Exception("No api was found");
            }

            if(Validator.isNull(body.fromUnit) || body.fromUnit.isEmpty()) {
                throw new Exception("No fromUnit was found");
            }

            if(Validator.isNull(body.toUnit) || body.toUnit.length == 0) {
                throw new Exception("No toUnit was found");
            }

            if(Validator.isNull(body.stateSync)) {
                throw new Exception("No stateSync was found");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "*");
            headers.add("groupId", String.valueOf(body.groupId));
            HttpEntity<SyncTrackingInfo> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(body.urlSaveTracking, entity , String.class);
            _log.info("Response api saving tracking: " + response);
            _log.info("Saved tracking!!!");
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public JSONObject callApi(String url, HttpHeaders headers, Map<String, Object> body){
        try {
            _log.info("Calling api: " + url);
            _log.info(body);
            headers.set("Accept", "*");
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);

            _log.info("Response api: " + response);
            _log.info("Response api: " + response.getBody());

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
            _log.info("Response api: " + jsonObject);
            return jsonObject;
        } catch (Exception e) {
            _log.error(e);
            return null;
        }
    }

    @Override
    public JSONObject callApiAndTrackingWithMapBody(String url, SyncTrackingInfo syncTrackingInfo,
                                                    HttpHeaders headers, Map<String, Object> body) {
        try {
            _log.info("Calling api with map body: " + url);
            headers.set("Accept", "*");
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            _log.info("Response api: " + response);
            Integer statusCode = response.getStatusCode().value();

            //Tracking
            if(Validator.isNotNull(syncTrackingInfo)) {
                try {
                    syncTrackingInfo.bodyRequest  = Validator.isNotNull(body)
                            ? objectMapper.writeValueAsString(body) : "-";
                    syncTrackingInfo.bodyResponse = Validator.isNotNull(response.getBody())
                            ? response.getBody() : "-";

                    if(statusCode == 200) {
                        syncTrackingInfo.stateSync = SYNC_SUCCESS;
                    } else {
                        syncTrackingInfo.stateSync = SYNC_ERROR;
                    }

                    callApiSaveTracking(syncTrackingInfo);
                } catch (Exception e) {
                    _log.error("Save tracking error with message: " + e.getMessage());
                    _log.warn("Still running...");
                }
            }

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
            return jsonObject;
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public JSONObject callAPIPaygovPrintInvoice( Map<String, Object> body, JSONObject paygovConfig) throws Exception {

        HttpURLConnection conn = null;
        JSONObject response = JSONFactoryUtil.createJSONObject();

        try {

            JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

            for (Map.Entry<String, Object> entry : body.entrySet()) {
                _log.debug("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                jsonBody.put(entry.getKey(), GetterUtil.getString(entry.getValue()));

            }
            _log.debug("++++jsonBody:" + jsonBody);

            URL url = new URL(paygovConfig.getString("urlBienLai"));

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Charset", "utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + paygovConfig.getString("token"));
            conn.setRequestProperty("lgspaccesstoken", paygovConfig.getString("lgspAccessToken"));
            conn.setInstanceFollowRedirects(true);
            HttpURLConnection.setFollowRedirects(true);
            conn.setReadTimeout(60 * 1000);

            byte[] postData = jsonBody.toJSONString().getBytes("UTF-8");

            int postDataLength = postData.length;
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }

            conn.connect();

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

                String output = StringPool.BLANK;

                StringBuilder sb = new StringBuilder();

                while ((output = bufferedReader.readLine()) != null) {
                    sb.append(output);
                }

                _log.debug("response: " + sb.toString());

                response = JSONFactoryUtil.createJSONObject(sb.toString());

            }

            if (Validator.isNull(response.getString("transactionReceipt"))) {
                throw new Exception(response.toString());
            }

        } catch ( Exception e) {

            _log.error(e);
            throw new Exception(e.getMessage());
        }
        return response;
    }

    @Override
    public JSONObject callApiAndTracking(String url, SyncTrackingInfo syncTrackingInfo,
                                         HttpHeaders headers, Object body) {
        try {
            _log.info("Calling api: " + url);
            headers.set("Accept", "*");
            HttpEntity<Object> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            _log.info("Response api: " + response);

            //Tracking
            if(Validator.isNotNull(syncTrackingInfo)) {
                try {
                    Integer statusCode = response.getStatusCode().value();
                    syncTrackingInfo.bodyRequest  = Validator.isNotNull(body)
                            ? objectMapper.writeValueAsString(body) : "-";
                    syncTrackingInfo.bodyResponse = Validator.isNotNull(response.getBody())
                            ? response.getBody() : "-";

                    if(statusCode == 200) {
                        syncTrackingInfo.stateSync = SYNC_SUCCESS;
                    } else {
                        syncTrackingInfo.stateSync = SYNC_ERROR;
                    }

                    callApiSaveTracking(syncTrackingInfo);
                } catch (Exception e) {
                    _log.error("Save tracking error with message: " + e.getMessage());
                    _log.warn("Still running...");
                }
            }

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
            return jsonObject;
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public JSONObject callApiEncode(String url, HttpHeaders headers, MultiValueMap<String, String> body) throws Exception{
        try {
            _log.info("Calling api: " + url);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity , String.class);
            _log.info("Response: " + response.getBody());
            return JSONFactoryUtil.createJSONObject(response.getBody());
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public JSONArray callApiWithResponseArray(String url, HttpHeaders headers, Map<String, Object> body) {
        try {
            _log.info("Calling api: " + url);
            headers.set("Accept", "*");
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            _log.info("Response api: " + response);

            JSONArray jsonArray = JSONFactoryUtil.createJSONArray(response.getBody());
            System.out.println(jsonArray);
            return jsonArray;
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public JSONObject get(String url, HttpHeaders headers, SyncTrackingInfo syncTrackingInfo) {
        try {
            _log.info("Calling api: " + url);
            headers.set("Accept", "*");
            HttpEntity entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            _log.info("Response api: " + response);
            //Tracking
            if(Validator.isNotNull(syncTrackingInfo)) {
                try {
                    Integer statusCode = response.getStatusCode().value();
                    syncTrackingInfo.bodyRequest  = "-";
                    syncTrackingInfo.bodyResponse = Validator.isNotNull(response.getBody())
                           ? response.getBody() : "-";

                    JSONObject detailDossier = JSONFactoryUtil.createJSONObject(response.getBody());
                    if(detailDossier.has("profileOutmodel")
                            && detailDossier.getJSONObject("profileOutmodel") != null) {
                        JSONObject profileOutModel = detailDossier.getJSONObject("profileOutmodel");
                        syncTrackingInfo.serviceCode  = profileOutModel.getString("procedures_code");
                        syncTrackingInfo.referenceUid = profileOutModel.getString("ref_code");
                        syncTrackingInfo.dossierNo = profileOutModel.getString("ref_code");
                    }

                    if(statusCode == 200) {
                        syncTrackingInfo.stateSync = SYNC_SUCCESS;
                    } else {
                        syncTrackingInfo.stateSync = SYNC_ERROR;
                    }

                    callApiSaveTracking(syncTrackingInfo);
                } catch (Exception e) {
                    _log.error("Save tracking error with message: " + e.getMessage());
                    _log.warn("Still running...");
                }
            }

            if(Validator.isNull(response) || Validator.isNull(response.getBody()) || response.getStatusCode().value() != 200){
                throw new Exception("Response is null or status code != 200");
            }
            return JSONFactoryUtil.createJSONObject(response.getBody());
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public JSONObject getNew(String url, String token) {
        return getResponseNew(url, token);
    }

    private JSONObject getResponseNew(String endpoint, String token) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + token);
//            conn.setInstanceFollowRedirects(true);
//            HttpURLConnection.setFollowRedirects(true);
            conn.setReadTimeout(60 * 1000);
            conn.connect();



            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output = StringPool.BLANK;

            StringBuilder sb = new StringBuilder();

            while ((output = bufferedReader.readLine()) != null) {
                sb.append(output);
            }

            System.out.println("response: " + sb.toString());

            JSONObject result = JSONFactoryUtil.createJSONObject(sb.toString());
            return result;
        } catch (Exception e) {
            _log.error(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }
}
