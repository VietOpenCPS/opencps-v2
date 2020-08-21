package org.opencps.communication.utils;


import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.mail.internet.InternetAddress;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.opencps.kernel.message.MBMessageEntry;


public class LGSPSendMailUtils {
	private static Log _log = LogFactoryUtil.getLog(LGSPSendMailUtils.class);

	public static void sendLGSP(MBMessageEntry messageEntry, String portletId, ServiceContext... serviceContexts) {

		if (messageEntry != null && messageEntry.isSendEmail() && messageEntry.getToAddress().length > 0) {
//			_log.debug("===SEND_MAIL_TO_ADD=======" + messageEntry.getToAddress()[0].getAddress());
			boolean needSendEmail = false;
			for (InternetAddress address : messageEntry.getToAddress()) {
				if (Validator.isNotNull(address.getAddress())) {
					needSendEmail = true;
					break;
				}
			}
			_log.info("Send email: " + needSendEmail + ", " + messageEntry.getToAddress());

			if (needSendEmail) {
				JSONObject jsonToken = LGSPRestfulUtils.createTokenLGSP("Bearer");
				if (jsonToken != null && jsonToken.has("token") && jsonToken.has("refreshToken")
						&& jsonToken.has("expiryDate")) {

					String strUrlSendMail = "http://api.dongthap.gov.vn/api/v1/congdan/SendEmailMulti";
					_log.info("strUrlSendMail: " + strUrlSendMail);
					String authStrEnc = "Bearer" + StringPool.SPACE + jsonToken.getString("token");
					// _log.info("authStrEnc: "+ authStrEnc);

					for (int i = 0; i < 8; i++) {
						StringBuilder sbSendMail = new StringBuilder();
						try {
							URL urlSendMail = new URL(strUrlSendMail);

							JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
							StringBuilder sbToAdd = new StringBuilder();
							if (messageEntry.getToAddress() != null && messageEntry.getToAddress().length > 0) {
								InternetAddress[] addressArr = messageEntry.getToAddress();

								for (int j = 0; j < addressArr.length; j ++) {
									InternetAddress internetAddress = addressArr[j];
									if (i == 0) {
										sbToAdd.append(internetAddress.getAddress().trim());
									}
									else {
										sbToAdd.append(StringPool.SEMICOLON);
										sbToAdd.append(internetAddress.getAddress().trim());

									}
								}
								//sbToAdd.append("dvcmuc34tinhdongthap@gmail.com");
							}
							_log.info("sbToAdd: " + sbToAdd.toString());
							jsonBody.put("mailReceiver", sbToAdd.toString());
							jsonBody.put("receiverName", messageEntry.getToAddress()[0].getAddress());
							jsonBody.put("content", messageEntry.getEmailBody());
							jsonBody.put("subject", messageEntry.getEmailSubject());
							//

							java.net.HttpURLConnection conSendMail = (java.net.HttpURLConnection) urlSendMail
									.openConnection();
							conSendMail.setRequestMethod(HttpMethod.POST);
							conSendMail.setRequestProperty("Accept", MediaType.APPLICATION_JSON);
							conSendMail.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);
							conSendMail.setRequestProperty("Authorization", authStrEnc);
							_log.debug("BASIC AUTHEN: " + authStrEnc);
							conSendMail.setRequestProperty("Content-Length",
									StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));

							conSendMail.setUseCaches(false);
							conSendMail.setDoInput(true);
							conSendMail.setDoOutput(true);
							_log.debug("POST DATA: " + jsonBody.toString());
							OutputStream osLogin = conSendMail.getOutputStream();
							osLogin.write(jsonBody.toString().getBytes());
							osLogin.close();

							BufferedReader brfLogin = new BufferedReader(
									new InputStreamReader(conSendMail.getInputStream()));

							int cpSendMail;
							while ((cpSendMail = brfLogin.read()) != -1) {
								sbSendMail.append((char) cpSendMail);
							}
							_log.info("RESULT PROXY: " + sbSendMail.toString());
							if (Validator.isNotNull(sbSendMail.toString())) {
								JSONObject jsonResult = JSONFactoryUtil.createJSONObject(sbSendMail.toString());
								if (jsonResult != null && jsonResult.has("result")
										&& "SUCCESSFUL".equals(jsonResult.getString("result"))) {
									break;
								}
							}
							try {
								Thread.sleep(500);
							} catch (InterruptedException e1) {
								System.out.println("Time not delay");
							}
							//
						} catch (Exception e) {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e1) {
								System.out.println("Time not delay");
							}
							_log.debug(e);
						}
					}

				}
			}
		}
	}

}
