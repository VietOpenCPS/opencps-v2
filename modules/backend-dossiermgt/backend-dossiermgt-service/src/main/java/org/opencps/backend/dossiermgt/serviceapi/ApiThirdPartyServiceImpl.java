package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.dossiermgt.input.model.ResponseListDossier;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public JSONObject callApi(String url, HttpHeaders headers, Map<String, Object> body){
        try {
            _log.info("Calling api: " + url);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
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
    public JSONObject callApi(String url, HttpHeaders headers, Object body) {
        try {
            _log.info("Calling api: " + url);
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
            HttpEntity entity = new HttpEntity<>(headers);
//            Object test = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
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
}
