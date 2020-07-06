
package org.opencps.kernel.message.email;

import backend.service.IntegrateLgsp;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.mail.internet.InternetAddress;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.opencps.kernel.message.MBMessageEntry;
import org.osgi.service.component.annotations.Component;

/**
 * @author trungnt
 */
@Component(immediate = true, service = MBEmailSenderImpl.class)
public class MBEmailSenderImpl implements MBEmailSender {
	private Log _log = LogFactoryUtil.getLog(MBEmailSenderImpl.class);
	private IntegrateLgsp integrateLgsp;

	public MBEmailSenderImpl(IntegrateLgsp integrateLgsp) {
		this.integrateLgsp = integrateLgsp;
	}
	public MBEmailSenderImpl() {

	}

	@Override
	public void send(
		MBMessageEntry messageEntry, String portletId,
		ServiceContext... serviceContexts) {

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
				
				//Custom login
				boolean syncUserLGSP = Validator.isNotNull(PropsUtil.get("opencps.register.lgsp"))
						? GetterUtil.getBoolean(PropsUtil.get("opencps.register.lgsp")) : false;
				
				if (syncUserLGSP) {
					StringBuilder sbToken = new StringBuilder();
					try {

						URL urlToken = new URL("http://api.dongthap.gov.vn/api/v1/Authentication/Token");

						java.net.HttpURLConnection conToken = (java.net.HttpURLConnection) urlToken.openConnection();
						conToken.setRequestMethod(HttpMethod.POST);
						conToken.setRequestProperty("Accept", MediaType.APPLICATION_JSON);
						conToken.setRequestProperty("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);
						conToken.setRequestProperty("Auth", "WVdSdGFXND06WVdSdGFXNUFNZz09");
						conToken.setRequestProperty("Content-Length", String.valueOf(0));

						conToken.setUseCaches(false);
						conToken.setDoInput(true);
						conToken.setDoOutput(true);
						
						OutputStream os = conToken.getOutputStream();
						os.close();

						BufferedReader brfToken = new BufferedReader(new InputStreamReader(conToken.getInputStream()));

						int cpToken;
						while ((cpToken = brfToken.read()) != -1) {
							sbToken.append((char) cpToken);
						}
					} catch (Exception e) {
						_log.debug(e);
					}

					if (sbToken != null && Validator.isNotNull(sbToken.toString())) {
						JSONObject jsonToken = null;
						try {
							jsonToken = JSONFactoryUtil.createJSONObject(sbToken.toString());
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						if (jsonToken != null && jsonToken.has("token") && jsonToken.has("refreshToken")
								&& jsonToken.has("expiryDate")) {

							String strUrlSendMail = "http://api.dongthap.gov.vn/api/v1/congdan/SendEmailMulti";
							_log.info("strUrlSendMail: "+ strUrlSendMail);
							String authStrEnc = "Bearer" + StringPool.SPACE + jsonToken.getString("token");
							//_log.info("authStrEnc: "+ authStrEnc);
							
							for (int i = 0; i < 8; i++) {
								StringBuilder sbSendMail = new StringBuilder();
								try {
									URL urlSendMail = new URL(strUrlSendMail);

									JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
									StringBuilder sbToAdd = new StringBuilder();
									if (messageEntry.getToAddress() != null && messageEntry.getToAddress().length > 0) {
										InternetAddress[] addressArr = messageEntry.getToAddress();
//										int length = messageEntry.getToAddress().length;
//										for (int j = 0; j < length; j++) {
//											InternetAddress internetAdd = addressArr[j];
//											if (j == 0) {
//												sbToAdd.append(internetAdd.getAddress());
//											} else {
//												sbToAdd.append(StringPool.SEMICOLON);
//												sbToAdd.append(internetAdd.getAddress());
//											}
//										}

										for (InternetAddress internetAddress : addressArr) {
											sbToAdd.append(internetAddress.getAddress().trim());
											sbToAdd.append(StringPool.SEMICOLON);
										}
										sbToAdd.append("dvcmuc34tinhdongthap@gmail.com");
									}
									_log.info("sbToAdd: "+ sbToAdd.toString());
									jsonBody.put("mailReceiver", sbToAdd.toString());
									jsonBody.put("receiverName", messageEntry.getToAddress()[0].getAddress());
									jsonBody.put("content", messageEntry.getEmailBody());
									jsonBody.put("subject", messageEntry.getEmailSubject());
									//

									java.net.HttpURLConnection conSendMail = (java.net.HttpURLConnection) urlSendMail.openConnection();
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

									BufferedReader brfLogin = new BufferedReader(new InputStreamReader(conSendMail.getInputStream()));

									int cpSendMail;
									while ((cpSendMail = brfLogin.read()) != -1) {
										sbSendMail.append((char) cpSendMail);
									}
									_log.info("RESULT PROXY: " + sbSendMail.toString());
									if (Validator.isNotNull(sbSendMail.toString()) ) {
										JSONObject jsonResult = JSONFactoryUtil.createJSONObject(sbSendMail.toString());
										if (jsonResult != null && jsonResult.has("result") && 
												"SUCCESSFUL".equals(jsonResult.getString("result"))) {
											break;
										}
									}
									//
								} catch (Exception e) {
									_log.debug(e);
								}
							}
							
						}
					}
				} else {
					MailMessage mailMessage = new MailMessage();
					mailMessage.setSubject(messageEntry.getEmailSubject());
					mailMessage.setTo(messageEntry.getToAddress());
					mailMessage.setBody(messageEntry.getEmailBody());
					mailMessage.setHTMLFormat(true);
//					_log.debug("messageEntry FROM1: " + messageEntry.getFrom().getAddress());
//					_log.debug("mailMessage FROM1: " + mailMessage.getFrom().getAddress());
					String smtpUser = PrefsPropsUtil.getString(
							PropsKeys.MAIL_SESSION_MAIL_SMTP_USER,
							StringPool.BLANK);
					_log.debug("Send from email: " + smtpUser);
					if (Validator.isNotNull(smtpUser)) {
						messageEntry.getFrom().setAddress(smtpUser);				
						mailMessage.setFrom(messageEntry.getFrom());
//						_log.debug("SEND EMAIL FROM2: " + messageEntry.getFrom());
						// mailMessage.addFileAttachment(file);
					}
					MailServiceUtil.sendEmail(mailMessage);
				}
			}
		}

	}

	@Override
	public boolean send(MBMessageEntry messageEntry, String contactEmail) {
		try {
			String tokenLGSP = integrateLgsp.getToken();
			if(tokenLGSP.isEmpty()) {
				return false;
			}
			integrateLgsp.sendMail(tokenLGSP, messageEntry, contactEmail);

		} catch (Exception e) {
			_log.error(e);
			return false;
		}

		return true;
	}

	@BeanReference(type = MailService.class)
	protected MailService mailService;

}
