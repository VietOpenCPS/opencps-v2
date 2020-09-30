package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
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
    public String getTokenLGSP() throws Exception{
        try {
            String url = "http://api.dongthap.gov.vn/api/v1/Authentication/Token";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Auth", "WVdSdGFXND06WVdSdGFXNUFNZz09");
            JSONObject response = this.callApi(url, headers, null);
            if(Validator.isNull(response)) {
                throw new Exception("Response get token null");
            }
            String token = response.getString("token");
            if(token.isEmpty()){
                throw new Exception("Token is empty");
            }
            return token;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String getUrlRedirectToPaygov(String token, Map<String, Object> body) throws Exception {
        try{
            _log.info("Body get url redirect paygov: " + body);
            String url = "https://api.dongthap.gov.vn/api/v1/PayGov/paygate";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
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
