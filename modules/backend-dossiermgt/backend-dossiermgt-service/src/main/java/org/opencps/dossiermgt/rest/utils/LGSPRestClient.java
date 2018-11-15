package org.opencps.dossiermgt.rest.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;
import javax.xml.bind.JAXBContext;

import org.opencps.dossiermgt.lgsp.model.MSyncDocument;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.rest.model.DossierPublishModel;
import org.opencps.dossiermgt.scheduler.InvokeREST;

public class LGSPRestClient {
	private Log _log = LogFactoryUtil.getLog(LGSPRestClient.class);
	private String consumerKey;
	private String consumerSecret;
	private String consumerAdapter;
	
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	private String baseUrl;

	public LGSPRestClient(String baseUrl) {
		if (baseUrl.charAt(baseUrl.length() - 1) == '/' && baseUrl.length() >= 2) {
			this.baseUrl = baseUrl.substring(0, baseUrl.length() - 2);
		} else {
			this.baseUrl = baseUrl;
		}
	}

	private static final String DOSSIERS_BASE_PATH = "/Document";
	private static final String TOKEN_BASE_PATH = "/token";
	
	public static LGSPRestClient fromJSONObject(JSONObject configObj) {
		if (configObj.has(SyncServerTerm.CONSUMER_KEY) 
				&& configObj.has(SyncServerTerm.CONSUMER_SECRET)
				&& configObj.has(SyncServerTerm.SERVER_URL)
				&& configObj.has(SyncServerTerm.CONSUMER_ADAPTER)
				) {
			return new LGSPRestClient(
					configObj.getString(SyncServerTerm.CONSUMER_KEY), 
					configObj.getString(SyncServerTerm.CONSUMER_SECRET),
					configObj.getString(SyncServerTerm.SERVER_URL),
					configObj.getString(SyncServerTerm.CONSUMER_ADAPTER));
		}
		else {
			return null;
		}
	}
	
	public LGSPRestClient(String consumerKey, String consumerSecret, String baseUrl, String consumerAdapter) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		if (baseUrl.charAt(baseUrl.length() - 1) == '/' && baseUrl.length() >= 2) {
			this.baseUrl = baseUrl.substring(0, baseUrl.length() - 2);
		}
		else {
			this.baseUrl = baseUrl;
		}
		if (consumerAdapter.charAt(consumerAdapter.length() - 1) == '/' && consumerAdapter.length() >= 2) {
			this.consumerAdapter = consumerAdapter.substring(0, consumerAdapter.length() - 2);
		}
		else {
			this.consumerAdapter = consumerAdapter;
		}		
	}
	
	public Mtoken getToken() {
		Mtoken result = null;
		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<>();
		params.put("grant_type", "client_credentials");
		params.put("client_secret", consumerSecret);
		params.put("client_id", consumerKey);
		
		JSONObject resultObj = callRest.callPostAPI(HttpMethod.POST, "application/json",
				baseUrl, TOKEN_BASE_PATH, properties, params);
		_log.info("====lGSP token====" + resultObj.toJSONString());
		if (resultObj != null && resultObj.has("status")
				&& resultObj.getInt("status") == 200) {
			try {
				result = OpenCPSConverter.convertMtoken(JSONFactoryUtil.createJSONObject(resultObj.getString("message")));
			} catch (JSONException e) {
				_log.debug(e);
			}
		}
		
		return result;
	}	
	
	public MSyncDocument publishDossier(String token, DossierPublishModel model) {
		MSyncDocument result = null;
		InvokeREST callRest = new InvokeREST();

		JSONObject lgspObj = OpenCPSConverter.convertDossierToLGSPJSON(model);
		JSONObject resultObj = callRest.callPostAPIRaw(token, HttpMethod.POST, "application/json",
			consumerAdapter, DOSSIERS_BASE_PATH + "/SyncDocument", lgspObj.toJSONString());
		if (resultObj != null && resultObj.has("status")
				&& resultObj.getInt("status") == 200) {
			try {
				result = OpenCPSConverter.convertLGSPSyncDocument(JSONFactoryUtil.createJSONObject(resultObj.getString("message")));
			} catch (JSONException e) {
				_log.debug(e);
			}
		}
		
		return result;
	}		
}
