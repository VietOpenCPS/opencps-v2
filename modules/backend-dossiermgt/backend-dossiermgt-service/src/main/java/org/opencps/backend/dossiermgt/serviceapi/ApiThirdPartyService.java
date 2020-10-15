package org.opencps.backend.dossiermgt.serviceapi;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import org.springframework.http.HttpHeaders;

import java.net.HttpURLConnection;
import java.util.Map;

public interface ApiThirdPartyService {
    public JSONObject callApi(String url, HttpURLConnection conn, JSONObject body);
    public JSONArray callApiWithResponseArray(String url, HttpURLConnection conn, JSONObject body);
}
