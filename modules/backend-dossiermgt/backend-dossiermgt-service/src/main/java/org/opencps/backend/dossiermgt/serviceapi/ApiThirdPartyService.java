package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.portal.kernel.json.JSONObject;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface ApiThirdPartyService {
    public JSONObject callApi(String url, HttpHeaders headers, Map<String, Object> body);
}
