package backend.postal.api.rest.controller;

import com.liferay.portal.kernel.json.JSONObject;

public interface LGSPService {
    public JSONObject getToken(String urlToken, String keyToken) throws Exception;
}
