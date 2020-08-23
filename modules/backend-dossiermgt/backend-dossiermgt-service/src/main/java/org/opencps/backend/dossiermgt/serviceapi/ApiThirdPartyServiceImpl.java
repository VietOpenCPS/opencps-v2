package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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
}
