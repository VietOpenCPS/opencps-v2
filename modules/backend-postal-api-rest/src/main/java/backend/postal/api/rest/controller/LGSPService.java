package backend.postal.api.rest.controller;

import com.liferay.portal.kernel.json.JSONObject;

public interface LGSPService {
	public JSONObject getToken(String urlToken, String keyToken) throws Exception;

	public String getToken(String tokenURL, String consumer_key, String secret_key) throws Exception;

	public String getVNPOSTPrice(String endpoint, String access_token, JSONObject datas);
}
