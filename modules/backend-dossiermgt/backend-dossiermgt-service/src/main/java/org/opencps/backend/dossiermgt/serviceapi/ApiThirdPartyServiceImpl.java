package org.opencps.backend.dossiermgt.serviceapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.usermgt.service.util.LGSPRestfulUtils;
import org.apache.http.impl.client.HttpClients;
import org.opencps.dossiermgt.input.model.ResponseListDossier;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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
    private enum ListPaygovUnitLocal {
        DONGTHAP("PAYGOV-DONGTHAP"),
        HAUGIANG("PAYGOV-HAUGIANG");

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
            } else if (paygovConfig.getString("partnerCode").equals(ListPaygovUnitLocal.HAUGIANG.getValue())) {
                return paygovConfig.getString("token");
            } else {
                //other unit
            }

            throw new Exception("Token is empty with paygov config: " + paygovConfig);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String getUrlRedirectToPaygov(String token, Map<String, Object> body, JSONObject paygovConfig) throws Exception {
        try{
            _log.info("Body get url redirect paygov: " + body);
            String url = paygovConfig.getString("urlPaygate");

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            if(paygovConfig.getString("partnerCode").equals(ListPaygovUnitLocal.HAUGIANG.getValue())) {
                headers.add("lgspaccesstoken", paygovConfig.getString("lgspAccessToken"));
            }

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
    public JSONObject callApi(String url, HttpHeaders headers, Map<String, Object> body){
        try {
            _log.info("Calling api: " + url);
            _log.info(body);
            headers.set("Accept", "*");
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            _log.info("Response api: " + response);

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
            System.out.println("Response api: " + jsonObject);
            return jsonObject;
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public JSONObject callApi(String url, HttpHeaders headers, Object body) {
        try {
            _log.info("Calling api: " + url);
            _log.info("Body request: " + objectMapper.writeValueAsString(body));
            headers.set("Accept", "*");
            HttpEntity<Object> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            _log.info("Response api: " + response);

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
            System.out.println(jsonObject);
            return jsonObject;
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
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
    public JSONObject get(String url, HttpHeaders headers) {
        try {
            _log.info("Calling api: " + url);
            headers.set("Accept", "*");
            HttpEntity entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            _log.info("Response api: " + response);
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
