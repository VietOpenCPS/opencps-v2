package org.opencps.synctracking.action;

import com.liferay.portal.kernel.json.JSONObject;
import org.springframework.http.HttpHeaders;

public interface IntegrationOutsideApi {
    public String getToken(JSONObject config) throws Exception;
    public JSONObject postWithString(String url, HttpHeaders headers, String body) throws Exception;
}
