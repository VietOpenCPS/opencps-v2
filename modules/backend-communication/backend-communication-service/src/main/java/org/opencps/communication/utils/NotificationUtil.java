
package org.opencps.communication.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.opencps.communication.constants.MailVariables;
import org.opencps.communication.constants.SendSMSTerm;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.kernel.message.MBMessageEntry;
import org.opencps.kernel.prop.PropValues;
import org.opencps.kernel.template.MessageDataModel;
import org.opencps.kernel.template.freemarker.TemplateProcessor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author trungnt
 */
public class NotificationUtil {

	public static enum NotificationType {
			SHARING_01("SHARING-01"), SHARING_02("SHARING-02"),
			SHARING_03("SHARING-03"), INVITATION_01("INVITATION-01"),
			INVITATION_02("INVITATION-02"), LEADER("LEADER"),
			HOSTING("HOSTING"), MANAGER("MANAGER"), STATE("STATE"),
			OVERDUE("OVERDUE"), FILEATTACH("FILEATTACH"),
			ONLINEFORM("ONLINEFORM"), ALBUMFILE("ALBUMFILE"),
			COMMENT("COMMENT"), VOTING_01("VOTING-01"), VOTING_02("VOTING-02"),
			ACTIVITY("ACTIVITY"), DOCUMENT("DOCUMENT"), ARCHIVE("ARCHIVE"),
			PROJECT("PROJECT"), USER_01("USER-01"), USER_02("USER-02"),
			USER_03("USER-03"), USER_04("USER-04"), NEWSBOARD("NEWSBOARD"),
			BIRTHDATE("BIRTHDATE"), REMINDER_01("REMINDER-01"),
			REMINDER_02("REMINDER-02"), WORKCHECKIN("WORKCHECKIN"),
			TIMESHEET("TIMESHEET"), DUTYPLAN("DUTYPLAN");

		private String code;

		NotificationType(String code) {
			this.setCode(code);
		}

		public void setCode(String code) {

			this.code = code;
		}

		public String getCode() {

			return code;
		}

		@Override
		public String toString() {

			return this.getCode();
		}
	}

	@SuppressWarnings("unchecked")
	public static MBMessageEntry createMBMessageEntry(
		NotificationQueue queue, Notificationtemplate template,
		ServiceContext serviceContext) {

		MBMessageEntry messageEntry = null;
		if (queue != null && template != null) {

			String emailSubjectTemplate = template.getEmailSubject();
			String emailBodyTemplate = template.getEmailBody();
			String textMessageTemplate = template.getTextMessage();
			String userUrlPatternTemplate = template.getUserUrlPattern();
			String guestUrlPatternTemplate = template.getGuestUrlPattern();
			// _log.info("emailSubjectTemplate: "+emailSubjectTemplate);
			// _log.info("emailBodyTemplate: "+emailBodyTemplate);
			// _log.info("textMessageTemplate: "+textMessageTemplate);
			// _log.info("userUrlPatternTemplate: "+userUrlPatternTemplate);
			// _log.info("guestUrlPatternTemplate: "+guestUrlPatternTemplate);

			String baseUrl = StringPool.BLANK;
			try {
				Group group = GroupLocalServiceUtil.getGroup(
					serviceContext.getScopeGroupId());

				baseUrl = PropValues.PORTAL_DOMAIN +
					PortalUtil.getPathFriendlyURLPrivateGroup() +
					group.getFriendlyURL();
				// _log.info("baseUrl: "+baseUrl);

			}
			catch (Exception e) {
				_log.error(e);
			}

			String guestBaseUrl = StringPool.BLANK;

			try {
				Group group = GroupLocalServiceUtil.getGroup(
					serviceContext.getScopeGroupId());

				guestBaseUrl = PropValues.PORTAL_DOMAIN +
					PortalUtil.getPathFriendlyURLPublic() +
					group.getFriendlyURL();
				// _log.info("guestBaseUrl: "+guestBaseUrl);

			}
			catch (Exception e) {
				_log.error(e);
			}

			String security = StringPool.BLANK;

			try {

				Object payload =
					JSONFactoryUtil.looseDeserialize(queue.getPayload());

				// JSONObject payloadJSON =
				// JSONFactoryUtil.createJSONObject(queue.getPayload());
				// if (payloadJSON.has("Visibility")) {
				// security =
				// payloadJSON.getJSONObject("Visibility").getString(
				// "security");
				// }

				// String guestConfirmUrl = guestBaseUrl +
				// MailVariables.SUB_URL_ACTIVE +
				// payloadJSON.getJSONObject("Applicant").getString(
				// "applicantId");
				// _log.info("guestConfirmUrl: "+guestConfirmUrl);

				MessageDataModel dataModel = new MessageDataModel();

				dataModel.setPayload(payload);
				dataModel.setClassName(queue.getClassName());
				dataModel.setClassPK(queue.getClassPK());
				dataModel.setCreateDate(queue.getCreateDate());
				dataModel.setExpireDate(queue.getExpireDate());
				if (Validator.isNotNull(PropValues.NAME_ADMIN_SERVER)) {
					dataModel.setFromUsername(PropValues.NAME_ADMIN_SERVER);
				}
				else {
					dataModel.setFromUsername(queue.getFromUsername());
				}
				dataModel.setGroupId(queue.getGroupId());
				dataModel.setModifiedDate(queue.getModifiedDate());
				dataModel.setNotificationType(queue.getNotificationType());
				// dataModel.setPayload(queue.getPayload());
				dataModel.setPublicationDate(queue.getPublicationDate());
				dataModel.setToEmail(queue.getToEmail());
				dataModel.setToTelNo(queue.getToTelNo());
				dataModel.setToUserId(queue.getToUserId());
				dataModel.setToUsername(queue.getToUsername());
				dataModel.setUserId(queue.getUserId());
				dataModel.setBaseUrl(baseUrl);
				dataModel.setGuestBaseUrl(guestBaseUrl);
				dataModel.setSecurity(security);
				dataModel.setSubActiveUrl(MailVariables.SUB_URL_ACTIVE);
				String token = StringPool.BLANK;
				if (Validator.isNotNull(security)) {
					token = Base64.encode(
						(queue.getToEmail() + ":" + security).getBytes());
				}

				dataModel.setToken(token);

				TemplateProcessor emailBodyTemplateProcessor =
					new TemplateProcessor(emailBodyTemplate);

				String emailBody =
					emailBodyTemplateProcessor.process(dataModel);
				// _log.info("emailBody: "+emailBody);

				TemplateProcessor emailSubjectTemplateProcessor =
					new TemplateProcessor(emailSubjectTemplate);

				String emailSubject =
					emailSubjectTemplateProcessor.process(dataModel);

				TemplateProcessor textMessageTemplateProcessor =
					new TemplateProcessor(textMessageTemplate);

				String textMessage =
					textMessageTemplateProcessor.process(dataModel);
				// _log.info("textMessage: "+textMessage);

				String userUrl = StringPool.BLANK;

				if (Validator.isNotNull(userUrlPatternTemplate)) {
					TemplateProcessor userUrlPatternTemplateProcessor =
						new TemplateProcessor(userUrlPatternTemplate);
					userUrl =
						userUrlPatternTemplateProcessor.process(dataModel);
					// _log.info("userUrl: "+userUrl);
				}

				String guestUrl = StringPool.BLANK;

				if (Validator.isNotNull(guestUrlPatternTemplate)) {
					TemplateProcessor guestUrlPatternTemplateProcessor =
						new TemplateProcessor(guestUrlPatternTemplate);

					guestUrl =
						guestUrlPatternTemplateProcessor.process(dataModel);
					// _log.info("guestUrl: "+guestUrl);
				}

				messageEntry = new MBMessageEntry(
					queue.getFromUsername(), queue.getToUserId(),
					queue.getToEmail(), queue.getToUsername(), serviceContext);

				messageEntry.setCreateDate(queue.getCreateDate());
				messageEntry.setEmailBody(emailBody);
				messageEntry.setEmailSubject(emailSubject);
				messageEntry.setTextMessage(textMessage);
				messageEntry.setClassName(queue.getClassName());
				messageEntry.setUserUrl(userUrl);
				messageEntry.setGuestUrl(guestUrl);
				messageEntry.setToTelNo(queue.getToTelNo());
				messageEntry.setNotifyMessage(template.getNotifyMessage());
				messageEntry.setData(queue.getPayload());

				// _log.info(emailBody);

				// _log.info(userUrl);

				// messageEntry.setFromEmail();

				boolean sendEmail = true;
				boolean sendNotify = true;
				boolean sendSMS = false;
				boolean sendMesZalo = false;

//				if (queue.getToUserId() > 0) {
					// Preferences preferences =
					// PreferencesLocalServiceUtil.fetchByF_userId(
					// serviceContext.getScopeGroupId(),
					// queue.getToUserId());
					// if (preferences != null &&
					// Validator.isNotNull(preferences.getPreferences())) {
					// try {
					// JSONObject pref = JSONFactoryUtil.createJSONObject(
					// preferences.getPreferences());
					// if (pref.has(queue.getNotificationType())) {
					// JSONObject object = pref.getJSONObject(
					// queue.getNotificationType());
					// if (object != null &&
					// object.has(queue.getClassName())) {
					// JSONObject conf = object.getJSONObject(
					// queue.getClassName());
					// sendEmail = conf.getBoolean("email");
					// sendNotify = conf.getBoolean("notify");
					// sendSMS = conf.getBoolean("sms");
					// }
					// }
					// }
					// catch (Exception e) {
					// _log.debug(e);
					// //_log.error(e);
					// }
					// }
//					if (template != null) {
//						// sendEmail = template.getSendEmail();
//						sendNotify = template.getSendNotification();
//						// sendSMS = template.getSendSMS();
//						sendMesZalo = template.getSendNotification();
//					}
//				}
//				else {
//					sendNotify = false;
//					sendMesZalo = false;
//				}

				if (template != null) {
					sendEmail = template.getSendEmail();
					sendSMS = template.getSendSMS();
					sendNotify = template.getSendNotification();
					sendMesZalo = template.getSendNotification();
				}

				messageEntry.setSendEmail(sendEmail);
				messageEntry.setSendNotify(sendNotify);
				messageEntry.setSendSMS(sendSMS);

				if (sendMesZalo) {
					JSONObject checkZaloInfo =
						_checkZaloInfo(queue.getToUserId(), queue.getToTelNo());

					if (checkZaloInfo.has(ZALO_UID) &&
						checkZaloInfo.has(ZALO_TOKEN)) {

						messageEntry.setMappingZaloUid(
							(Map<Long, String>) checkZaloInfo.get(ZALO_UID));
						messageEntry.setZaloAccessToken(
							checkZaloInfo.getString(ZALO_TOKEN));
					}
					else {

						sendMesZalo = false;
					}

					messageEntry.setSendZalo(sendMesZalo);
				}

				// _log.info("create mail message: " + messageEntry);

			}
			catch (Exception e) {
				// _log.warn("Can't not create MBMessageEntry " + e);
				//e.printStackTrace();
				_log.debug(e);
			}
		}

		return messageEntry;
	}

	private static JSONObject _checkZaloInfo(long toUserId, String toTelNo) {

		JSONObject sendZaloInfo = JSONFactoryUtil.createJSONObject();

		try {

			JSONObject zaloConfig = _getZaloInfo();
			String zaloAccessToken =
				zaloConfig.getString(SendSMSTerm.OAID_TOKEN_ACCESS);

			if (Validator.isNotNull(toTelNo)) {

				JSONObject resultApi = JSONFactoryUtil.createJSONObject(
					_getZaloUidByTelNo(zaloAccessToken, toTelNo));

				if (resultApi.has("data")) {

					Map<Long, String> mappingZaloUid = new HashMap<>();
					String zOId =
						resultApi.getJSONObject("data").getString("user_id");

					mappingZaloUid.put(
						toUserId > 0 ? toUserId : new Long(0), zOId);
					sendZaloInfo.put(ZALO_UID, mappingZaloUid);
					sendZaloInfo.put(ZALO_TOKEN, zaloAccessToken);
				}
			}
		}
		catch (Exception e) {
			_log.debug(e);
			// TODO: not send zalo
		}
		return sendZaloInfo;
	}

	private static String _getZaloUidByTelNo(String token, String toTelNo) {

		try {

			HashMap<String, String> properties = new HashMap<String, String>();
			JSONObject data = JSONFactoryUtil.createJSONObject();

			data.put("user_id", toTelNo);

			String endPoint = ZALO_ENDPOID_GET_USER_INFO + "?access_token=" +
				token + "&data=" + data.toJSONString();

			JSONObject resPostDossier = _callAPI(
				HttpMethods.GET, "application/json", ZALO_PATH_BASE, endPoint,
				StringPool.BLANK, StringPool.BLANK, properties);

			String uid = resPostDossier.getString("message");

			if (Validator.isNotNull(uid)) {

				return uid;
			}
		}
		catch (Exception e) {

			_log.debug(e);
		}
		return StringPool.BLANK;
	}

	private static JSONObject _getZaloInfo() {

		JSONObject zaloInfoConfig = JSONFactoryUtil.createJSONObject();

		try {

			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(
				SendSMSTerm.SERVER_CONFIG_SERVERNO_ZALO);

			zaloInfoConfig = JSONFactoryUtil.createJSONObject(sc.getConfigs());
		}
		catch (Exception e) {
			_log.debug(e);
		}

		return zaloInfoConfig;
	}

	private static JSONObject _callAPI(
		String httpMethod, String accept, String pathBase, String endPoint,
		String username, String password, HashMap<String, String> properties) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		try {
			String urlPath;
			if (pathBase.endsWith("/") && endPoint.startsWith("/")) {
				String endPoint2 = endPoint.substring(1);
				urlPath = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith("/") && endPoint.startsWith("/")) ||
				(pathBase.endsWith("/") && !endPoint.startsWith("/"))) {
				urlPath = pathBase + endPoint;
			}
			else {
				urlPath = pathBase + "/" + endPoint;
			}
			URL url = new URL(urlPath);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(RESTFulConfiguration.TIME_OUT);

			conn.setRequestMethod(httpMethod);
			conn.setRequestProperty("Accept", accept);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("groupId", StringPool.BLANK);

			if (Validator.isNotNull(username) &&
				Validator.isNotNull(password)) {

				String authString = username + ":" + password;

				String authStringEnc = new String(
					java.util.Base64.getEncoder().encodeToString(
						authString.getBytes()));
				conn.setRequestProperty(
					"Authorization", "Basic " + authStringEnc);
			}

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(
						m.getKey().toString(), m.getValue().toString());
				}
			}

			BufferedReader br = new BufferedReader(
				new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			response.put(RESTFulConfiguration.STATUS, conn.getResponseCode());
			response.put(RESTFulConfiguration.MESSAGE, sb.toString());

			conn.disconnect();

		}
		catch (MalformedURLException e) {
			// e.printStackTrace();
			_log.error(e);
		}
		catch (IOException e) {
			_log.error(e);
			// e.printStackTrace();

		}

		return response;
	}

	private static final String ZALO_PATH_BASE = "https://openapi.zalo.me";
	private static final String ZALO_ENDPOID_GET_USER_INFO = "/v2.0/oa/getprofile";
	private static final String ZALO_UID = "zaloUid";
	private static final String ZALO_TOKEN = "zaloToken";

	private static Log _log = LogFactoryUtil.getLog(NotificationUtil.class);

}

class RESTFulConfiguration {

	public static final String STATUS = "status";
	public static final String MESSAGE = "message";

	public static final String SUBMIT = "submit";
	public static final String TIMER = "timer";

	public static final int TIME_OUT = 3000;

}
