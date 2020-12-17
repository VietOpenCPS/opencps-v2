package org.opencps.synctracking.action.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.synctracking.action.IntegrationOutsideApi;
import org.opencps.synctracking.service.util.CommonConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collections;

public class IntegrationOutsideApiImpl implements IntegrationOutsideApi {
    private RestTemplate restTemplate;
    private static final Integer timeout = 10000 ;
    private Log _log = LogFactoryUtil.getLog(IntegrationOutsideApiImpl.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    public IntegrationOutsideApiImpl(){
        this.restTemplate = new RestTemplate(setConfigRestTemplate(timeout));
        this.restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    private ClientHttpRequestFactory setConfigRestTemplate(Integer timeout) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }

    @Override
    public String getToken(JSONObject configJson) throws Exception {
        try {
            String urlGetToken = configJson.getString(CommonConstant.CONFIG_URL)
                    + configJson.getString(CommonConstant.CONFIG_GET_TOKEN);
            String userName = configJson.getString(CommonConstant.CONFIG_USER_LGSP);
            String password = configJson.getString(CommonConstant.CONFIG_PASS_LGSP);
            String authStr = userName + ":" + password;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Basic " + base64Creds);

            JSONObject response = this.postWithString(urlGetToken, headers, null);
            if(Validator.isNull(response)) {
                throw new Exception("Response get token null");
            }

            String token = response.getString("access_token");
            if(Validator.isNull(token)){
                throw new Exception("Token is empty");
            }
            return token;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public JSONObject postWithString(String url, HttpHeaders headers, String body) throws Exception {
        try {
            _log.info("Calling api: " + url);
            _log.info(body);
            headers.set("Accept", "*");
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            _log.info("Response api: " + response);

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
            System.out.println("Response api: " + jsonObject);
            return jsonObject;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
