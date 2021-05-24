package backend.postal.api.rest.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import backend.postal.api.rest.controller.LGSPService;

public class LGSPServiceImpl implements LGSPService {
	private Log _log = LogFactoryUtil.getLog(LGSPServiceImpl.class);
	private RestTemplate restTemplate = new RestTemplate();;

	@Override
	public JSONObject getToken(String urlToken, String keyToken) throws Exception {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.set("Authorization", "Bearer " + keyToken);

			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("grant_type", "client_credentials");

			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

			_log.info("Calling api lgsp: " + urlToken);
			ResponseEntity<String> response = restTemplate.postForEntity(urlToken, entity, String.class);
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
			_log.info("Response: " + jsonObject);
			return jsonObject;
		} catch (Exception e) {
			_log.error(e);
			throw new Exception(e.getMessage());
		}
	}

//	public String getToken(String tokenURL, String consumer_key, String secret_key) throws Exception {
//		
//		_log.info("+++tokenURL:"+tokenURL);
//		_log.info("+++consumer_key:"+consumer_key);
//		_log.info("+++secret_key:"+secret_key);
//
//		MToken token = IToken.getToken(tokenURL, consumer_key, secret_key);
//
//		return token.getAccessToken();
//	}
//	
	
	public String getToken(String tokenURL, String consumer_key, String secret_key) throws Exception {
		
		HttpsURLConnection  conn = null;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			
			String authString =  consumer_key+StringPool.COLON+secret_key;
			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));
			
			URL url = new URL(tokenURL);
			
			conn = (HttpsURLConnection ) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("Authorization", "Basic "+authStringEnc);
			conn.setInstanceFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);
			
			StringBuilder body = new StringBuilder();
			body.append("grant_type").append(StringPool.EQUAL).append("client_credentials");

			byte[] postData = body.toString().toString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty("Content-Length",
					Integer.toString(postDataLength));
			
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
			
			if(Validator.isNotNull(sb.toString())){
				result = JSONFactoryUtil.createJSONObject(sb.toString());
			}
			
			_log.debug("+++++token return:"+result);

			return result.has("access_token") ? result.getString("access_token") : StringPool.BLANK;

			

		} catch (Exception e) {
			_log.error(e);
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		
	}
	
	public  String getVNPOSTPrice(String endpoint, String access_token, JSONObject datas) {
		
		_log.debug("+++getVNPOSTPrice+++");

		HttpsURLConnection conn = null;

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			
			URL url = new URL(endpoint);

			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("Authorization", "Bearer "+access_token);
			conn.setInstanceFollowRedirects(true);
			conn.setReadTimeout(60 * 1000);
			
			_log.debug("==endpoint:"+endpoint);

			byte[] postData = datas.toString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty("Content-Length",Integer.toString(postDataLength));
			
			TrustManager myTrustManager = new TrustManager();
			conn = myTrustManager.disableSSL(conn);
			
			try (DataOutputStream wr = new DataOutputStream(
					conn.getOutputStream())) {
				wr.write(postData);
			}
			conn.connect();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String output = StringPool.BLANK;

			StringBuilder sb = new StringBuilder();

			while ((output = bufferedReader.readLine()) != null) {
				sb.append(output);
			}
			
			
			String price = StringPool.BLANK;
			
				try {
					result = JSONFactoryUtil.createJSONObject(sb.toString());
					
					price = result.getJSONObject("content").getString("price");
				}catch(JSONException e) {
					_log.error(e);
				}
				
				_log.info("+price:"+price);
		

			return price;

			

		} catch (Exception e) {
			_log.error(e);
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

    @Override
    public JSONObject getTokenTTTT(String urlToken, String authorizationKey) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", "Basic " + authorizationKey);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);

            _log.info("Calling api lgsp: " + urlToken);
            ResponseEntity<String> response = restTemplate.postForEntity(urlToken, entity , String.class);
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response.getBody());
            _log.info("Response: " + jsonObject);
            return jsonObject;
        } catch (Exception e) {
			_log.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public JSONObject postTTTT(String url, String token, Map<String, Object> body, String serviceCode) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);
            headers.set("service-code", serviceCode);
            headers.set("Accept", "*");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity( url, entity , String.class);
            _log.info("Response api: " + response.getBody());

            return JSONFactoryUtil.createJSONObject(response.getBody());
        } catch (Exception e) {
			_log.error(e);
            throw new Exception(e.getMessage());
        }
    }
}
