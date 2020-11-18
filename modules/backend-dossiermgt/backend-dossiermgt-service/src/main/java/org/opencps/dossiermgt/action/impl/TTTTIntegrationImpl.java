package org.opencps.dossiermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.TTTTIntegrationAction;
import org.opencps.dossiermgt.action.util.TrustManager;
import org.opencps.dossiermgt.constants.IntegrateTTTTConstants;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class TTTTIntegrationImpl implements TTTTIntegrationAction {
	
	private Log _log = LogFactoryUtil.getLog(TTTTIntegrationImpl.class);

	public JSONObject syncDataToEMCTracking(String endpoint, JSONObject datas) {

		_log.debug("+++syncDataToEMCTracking+++");

		HttpsURLConnection conn = null;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {

			StringBuilder params = new StringBuilder();
//			params.append(StringPool.QUESTION);

			Iterator<String> keys = datas.keys();
			
			while (keys.hasNext()) {
				
				String key = keys.next();

				params.append(key).append(StringPool.EQUAL).append(datas.get(key)).append(StringPool.AMPERSAND);

			}
			
			String body = params.toString();
			
			int index = StringUtil.lastIndexOfAny(body, new String[]{StringPool.AMPERSAND});
			
			body = StringUtil.replace(body, StringPool.AMPERSAND, StringPool.BLANK, index);

			_log.debug("+++endpoint:" + endpoint);
			_log.debug("+++body:" + body);

			URL url = new URL(endpoint);

			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethods.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);
//			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setInstanceFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);

			byte[] postData = body.toString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));

			TrustManager myTrustManager = new TrustManager();
			conn = myTrustManager.disableSSL(conn);

			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
			}
			conn.connect();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String output = StringPool.BLANK;

			StringBuilder sb = new StringBuilder();

			while ((output = bufferedReader.readLine()) != null) {
				sb.append(output);
			}
			
			try {
				
				if (conn.getResponseCode() < 200 || conn.getResponseCode() > 300) {
					
					_log.debug("+sb.toString():" + sb.toString());
					throw new Exception("Something happened on TTTT server with response: " + conn.getResponseCode());
					
				}
				
				
				result = JSONFactoryUtil.createJSONObject();
				result.put("response", conn.getResponseCode());

			} catch (JSONException e) {

			}

			return result;

		} catch (Exception e) {
			_log.error(e);
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
	private JSONObject creatJsonParamForCheckActionDossier(Dossier dossier, JSONObject serverConfig) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String codeProfile = dossier.getDocumentNo() != null ? dossier.getDocumentNo() : "";
		// Integer siteId = IntegrateTTTTConstants.SITE_ID;
		Integer siteId = serverConfig.has("siteId") ? serverConfig.getInt("siteId") : IntegrateTTTTConstants.SITE_ID;
		String codeTTHC = dossier.getServiceCode() != null ? dossier.getServiceCode() : "";
		String nameTTHC = dossier.getServiceName() != null ? dossier.getServiceName() : "";
		;
		Integer status;
		Integer formsReception;
		Integer formsPayments = IntegrateTTTTConstants.FormsPaymentDirect;
		Integer level = IntegrateTTTTConstants.LEVEL;
		Integer isFromDVCQG = IntegrateTTTTConstants.IS_FROM_DVCQG;
		Integer isDVCBC;
		String data = "";

		String statusDossier = dossier.getDossierStatus() != null ? dossier.getDossierStatus() : "";
		_log.debug("====dossier.getDossierId():"+dossier.getDossierId());
		_log.debug("====dossier.getDossierStatus():"+dossier.getDossierStatus());
		if (statusDossier.equals("processing")) {
			status = IntegrateTTTTConstants.STATUS_RECEIVED;
		} else if (statusDossier.equals("interoperating") || statusDossier.equals("releasing")
				|| statusDossier.equals("posting")) {
			status = IntegrateTTTTConstants.STATUS_PROCESSING;
		} else if (statusDossier.equals("done")) {
			status = IntegrateTTTTConstants.STATUS_DONE;
		} else {
			status = IntegrateTTTTConstants.STATUS_ANOTHER;
		}

		if (dossier.getOnline()) {
			formsReception = IntegrateTTTTConstants.FormsReceptionOnline;
		} else {
			formsReception = IntegrateTTTTConstants.FormsReceptionDirect;
		}

		if (dossier.getViaPostal() == 2) {
			isDVCBC = IntegrateTTTTConstants.DVBC_VNPOST;
		} else {
			isDVCBC = IntegrateTTTTConstants.DVBC_DIRECT;
		}

		jsonObject.put("codeProfile", codeProfile);
		jsonObject.put("siteId", String.valueOf(siteId));
		jsonObject.put("codeTTHC", codeTTHC);
		jsonObject.put("nameTTHC", nameTTHC);
		jsonObject.put("status", String.valueOf(status));
		jsonObject.put("formsReception", String.valueOf(formsReception));
		jsonObject.put("formsPayments", String.valueOf(formsPayments));
		jsonObject.put("level", String.valueOf(level));
		jsonObject.put("isFromDVCQG", String.valueOf(isFromDVCQG));
		jsonObject.put("isDVCBC", String.valueOf(isDVCBC));
		jsonObject.put("data", "");

		return jsonObject;
	}
	
	 @Override
	public boolean syncDoActionDossierUsingHttpConnection(Dossier dossier) throws Exception {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil
				.getByProtocol(ServerConfigTerm.TTTT_INTEGRATION);
		if (serverConfigs == null || serverConfigs.isEmpty()) {
			throw new Exception("No server config");
		}

		ServerConfig serverConfig = serverConfigs.get(0);
		JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

		try {

			JSONObject datas = creatJsonParamForCheckActionDossier(dossier, config);
			JSONObject result = syncDataToEMCTracking(config.getString("url"), datas);
			
			if(result.has("response") && (result.getInt("response") == 204)){
				
				_log.debug("Sync data to TTTT successfully");
				return true;
			}else {
				return false;
			}
			
			
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		
	}
	
    @Override
    public boolean syncDoActionDossier(Dossier dossier) throws Exception{
        List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(ServerConfigTerm.TTTT_INTEGRATION);
        if (serverConfigs == null || serverConfigs.isEmpty()) {
            throw new Exception("No server config");
        }

        ServerConfig serverConfig = serverConfigs.get(0);
        JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= this.createBodyForCheckActionDossier(dossier, config);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity( config.getString("url"), request , String.class );

            if(response == null || response.getStatusCode() == null) {
                throw new Exception("Response from TTTT is null");
            }

            if(response.getStatusCode().value() < 200 || response.getStatusCode().value() > 300) {
                throw new Exception("Something happened on TTTT server with response: " + response.getStatusCode().value());
            }
            _log.debug("Sync data to TTTT successfully");
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private MultiValueMap<String, String> createBodyForCheckActionDossier(Dossier dossier, JSONObject serverConfig) {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();

        String codeProfile = dossier.getDocumentNo()!= null ? dossier.getDocumentNo() : "";
        //Integer siteId  = IntegrateTTTTConstants.SITE_ID;
        Integer siteId  = serverConfig.has("siteId") ? serverConfig.getInt("siteId"):IntegrateTTTTConstants.SITE_ID;
        String codeTTHC = dossier.getServiceCode()!= null ? dossier.getServiceCode() : "";
        String nameTTHC = dossier.getServiceName()!= null ? dossier.getServiceName() : "";;
        Integer status;
        Integer formsReception ;
        Integer formsPayments = IntegrateTTTTConstants.FormsPaymentDirect;
        Integer level = IntegrateTTTTConstants.LEVEL;
        Integer isFromDVCQG = IntegrateTTTTConstants.IS_FROM_DVCQG;
        Integer isDVCBC;
        String data = "";

        String statusDossier = dossier.getDossierStatus() != null ? dossier.getDossierStatus() : "";

        if (statusDossier.equals("processing")) {
            status = IntegrateTTTTConstants.STATUS_RECEIVED;
        } else if (statusDossier.equals("interoperating") ||
                statusDossier.equals("releasing") ||
                statusDossier.equals("posting")) {
            status = IntegrateTTTTConstants.STATUS_PROCESSING;
        } else if (statusDossier.equals("done")) {
            status = IntegrateTTTTConstants.STATUS_DONE;
        } else {
            status = IntegrateTTTTConstants.STATUS_ANOTHER;
        }

        if(dossier.getOnline()) {
            formsReception = IntegrateTTTTConstants.FormsReceptionOnline;
        } else {
            formsReception = IntegrateTTTTConstants.FormsReceptionDirect;
        }

        if(dossier.getViaPostal() == 2) {
            isDVCBC = IntegrateTTTTConstants.DVBC_VNPOST;
        } else {
            isDVCBC = IntegrateTTTTConstants.DVBC_DIRECT;
        }

        map.add("codeProfile", codeProfile);
        map.add("siteId", String.valueOf(siteId));
        map.add("codeTTHC", codeTTHC);
        map.add("nameTTHC", nameTTHC);
        map.add("status", String.valueOf(status));
        map.add("formsReception", String.valueOf(formsReception));
        map.add("formsPayments", String.valueOf(formsPayments));
        map.add("level", String.valueOf(level));
        map.add("isFromDVCQG", String.valueOf(isFromDVCQG));
        map.add("isDVCBC", String.valueOf(isDVCBC));
        map.add("data", "");

        return map;
    }
}
