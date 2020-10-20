package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.usermgt.service.util.LGSPRestfulUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

public class ApiThirdPartyServiceImpl implements ApiThirdPartyService{
    private RestTemplate restTemplate;
    private static final Integer timeout = 30000 ;
    private Log _log = LogFactoryUtil.getLog(ApiThirdPartyServiceImpl.class);
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
            _log.info("Calling api viettle: " + url);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            _log.info("Response api viettle: " + response);

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
            _log.info("Calling api viettle: " + url);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            _log.info("Response api viettle: " + response);

            JSONArray jsonArray = JSONFactoryUtil.createJSONArray(response.getBody());
            System.out.println(jsonArray);
            return jsonArray;
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

}
