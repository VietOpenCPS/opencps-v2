package org.opencps.communication.sms.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.opencps.communication.constants.SendSMSTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;

public class BCTSMSUtils {

	public static String sendSMS(
		long groupId, String body, String title, String toTelNo)
		throws RemoteException, ServiceException {

		//CcApi_ServiceLocator locator = new CcApi_ServiceLocator();
		//CcApi_PortType portType;
		JSONObject configObj = JSONFactoryUtil.createJSONObject();

		try {

			List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(groupId,
					SendSMSTerm.SERVER_CONFIG_PROTOCOL_BCT_SMS);

			if (!lstScs.isEmpty()) {
				ServerConfig sc = lstScs.get(0);
				configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
			}
			//
			_log.info(configObj);
			if (configObj.has(SendSMSTerm.BCT_CP_CODE) && configObj.has(SendSMSTerm.BCT_ALIAS)
					&& configObj.has(SendSMSTerm.BCT_USER_NAME) && configObj.has(SendSMSTerm.BCT_PASSWORD)
					&& configObj.has(SendSMSTerm.BCT_CONTENT_TYPE) && configObj.has(SendSMSTerm.BCT_PACKAGE_ID)
					&& Validator.isNotNull(toTelNo)) {

				//String cpCode = configObj.getString(SendSMSTerm.BCT_CP_CODE);
				//String alias = configObj.getString(SendSMSTerm.BCT_ALIAS);
				String userName = configObj.getString(SendSMSTerm.BCT_USER_NAME);
				String password = configObj.getString(SendSMSTerm.BCT_PASSWORD);
				//String contentType = configObj.getString(SendSMSTerm.BCT_CONTENT_TYPE);
				//String packageId = configObj.getString(SendSMSTerm.BCT_PACKAGE_ID);
				String serverUrl = configObj.getString("url");

				String toTelNoRpl = toTelNo;
				if ("+84".equals(toTelNo.substring(0, 2))) {
					toTelNoRpl = toTelNo.replace("+84", "0");
				} else if ("+84".equals(toTelNo.substring(0, 1))) {
					toTelNoRpl = toTelNo.replace("84", "0");
				}

				//
				String authStrEnc = Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
				String apiUrl = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();
				try {
					StringBuilder postData = new StringBuilder();
					//JSONObject dataObj = JSONFactoryUtil.createJSONObject(configObj);
					Iterator<?> keys = configObj.keys();
					while (keys.hasNext()) {
						String key = (String) keys.next();
						if (!"".equals(postData.toString()) && key.equals(SendSMSTerm.BCT_USER_NAME)
								&& key.equals(SendSMSTerm.BCT_PASSWORD) && key.equals("url")) {
							postData.append("&");
						}
						postData.append(key);
						postData.append("=");
						postData.append(configObj.get(key));
					}
					//Add new key
					postData.append("msisdn");
					postData.append("=");
					postData.append(toTelNoRpl);
					//
					postData.append("sale_order_id");
					postData.append("=");
					postData.append(100);
					//
					postData.append("content");
					postData.append("=");
					postData.append(body +" : "+ 100);


					apiUrl = serverUrl + "/send-sms";
					URL urlVal = new URL(apiUrl);
					_log.debug("API URL: " + apiUrl);
					java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();
					conn.setRequestProperty("groupId", String.valueOf(groupId));
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("Authorization", "Basic " + authStrEnc);
					_log.debug("BASIC AUTHEN: " + authStrEnc);
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length",
							"" + Integer.toString(postData.toString().getBytes().length));

					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					_log.debug("POST DATA: " + postData.toString());
					OutputStream os = conn.getOutputStream();
					os.write(postData.toString().getBytes());
					os.close();

					BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					int cp;
					while ((cp = brf.read()) != -1) {
						sb.append((char) cp);
					}
					_log.debug("RESULT PROXY: " + sb.toString());
					return sb.toString();
				}
				catch (IOException e) {
					_log.error(e);
					_log.debug("Something went wrong while reading/writing in stream!!");
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return "";
	}

	private static final Log _log =
		LogFactoryUtil.getLog(ViettelSMSUtils.class);
}