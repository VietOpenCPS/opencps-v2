package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.dossiermgt.input.model.ResponseListDossier;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface ApiThirdPartyService {
    public JSONObject callApi(String url, HttpHeaders headers, Map<String, Object> body);
    public JSONArray callApiWithResponseArray(String url, HttpHeaders headers, Map<String, Object> body);
    public JSONObject get(String url, HttpHeaders headers);
}
