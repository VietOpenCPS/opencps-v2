package backend.postal.api.rest.controller.impl;

import backend.postal.api.rest.controller.LGSPService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class LGSPServiceImpl implements LGSPService {
    private Log _log = LogFactoryUtil.getLog(LGSPServiceImpl.class);
    private RestTemplate restTemplate = new RestTemplate();;

    @Override
    public JSONObject getToken(String urlToken, String keyToken) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", "Bearer " + keyToken);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type","client_credentials");

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

            _log.info("Calling api lgsp: " + urlToken);
            ResponseEntity<String> response = restTemplate.postForEntity(urlToken, entity , String.class);
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
            _log.info("Response: " + jsonObject);
            return jsonObject;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
