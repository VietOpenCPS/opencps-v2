package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface ApiThirdPartyService {
    public String getTokenLGSP() throws Exception;
    public String getUrlRedirectToPaygov(String token, Map<String, Object> body) throws Exception;
    public JSONObject callApi(String url, HttpHeaders headers, Map<String, Object> body);
    public JSONArray callApiWithResponseArray(String url, HttpHeaders headers, Map<String, Object> body);
}
