package org.opencps.api.controller.util;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.opencps.dossiermgt.rest.utils.SyncServerTerm;

public class NGSPRestClient {
	private Log _log = LogFactoryUtil.getLog(NGSPRestClient.class);
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

	public NGSPRestClient(String baseUrl) {
		if (baseUrl.charAt(baseUrl.length() - 1) == '/' && baseUrl.length() >= 2) {
			this.baseUrl = baseUrl.substring(0, baseUrl.length() - 2);
		} else {
			this.baseUrl = baseUrl;
		}
	}

	public static NGSPRestClient fromJSONObject(JSONObject configObj) {
		if (configObj.has(SyncServerTerm.CONSUMER_KEY) 
				&& configObj.has(SyncServerTerm.CONSUMER_SECRET)
				&& configObj.has(SyncServerTerm.SERVER_URL)
				&& configObj.has(SyncServerTerm.CONSUMER_ADAPTER)
				) {
			return new NGSPRestClient(
					configObj.getString(SyncServerTerm.CONSUMER_KEY), 
					configObj.getString(SyncServerTerm.CONSUMER_SECRET),
					configObj.getString(SyncServerTerm.SERVER_URL),
					configObj.getString(SyncServerTerm.CONSUMER_ADAPTER));
		}
		else {
			return null;
		}
	}
	
	public NGSPRestClient(String consumerKey, String consumerSecret, String baseUrl, String consumerAdapter) {
		this.setConsumerKey(consumerKey);
		this.setConsumerSecret(consumerSecret);
		if (baseUrl.charAt(baseUrl.length() - 1) == '/' && baseUrl.length() >= 2) {
			this.baseUrl = baseUrl.substring(0, baseUrl.length() - 2);
		}
		else {
			this.baseUrl = baseUrl;
		}
		if (consumerAdapter.charAt(consumerAdapter.length() - 1) == '/' && consumerAdapter.length() >= 2) {
			this.setConsumerAdapter(consumerAdapter.substring(0, consumerAdapter.length() - 2));
		}
		else {
			this.setConsumerAdapter(consumerAdapter);
		}		
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getConsumerAdapter() {
		return consumerAdapter;
	}

	public void setConsumerAdapter(String consumerAdapter) {
		this.consumerAdapter = consumerAdapter;
	}
}
