package org.opencps.usermgt.listener;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;

public class ApplicantListenerUtils {
	public static JSONObject getPayload(String notiType, JSONObject object, long groupId) {
		JSONObject payload = JSONFactoryUtil.createJSONObject();

		try {
			
			//_log.info("notiType"+notiType);
			//_log.info("groupId"+groupId);
			
			Notificationtemplate notificationtemplate = NotificationtemplateLocalServiceUtil
					.fetchByF_NotificationtemplateByType(groupId, notiType);
			String body = getEmailBody(notificationtemplate, object);
			String subject = notificationtemplate.getEmailSubject();
			
			payload.put("toName", object.get("toName"));
			payload.put("toAddress", object.get("toAddress"));
			payload.put("subject", subject);
			payload.put("body", body);
			//_log.info("payload"+payload);
		} catch (Exception e) {
			_log.error(e);
		}
		
		return payload;
	}

	private static String getEmailBody(Notificationtemplate notificationtemplate, JSONObject object) {

		try {

			String emailBody = notificationtemplate.getEmailBody();

			object.put(ApplicantListenerMessageKeys.ACTIVATION_LINK, notificationtemplate.getUserUrlPattern() + object.get(ApplicantListenerMessageKeys.ACTIVATION_LINK));
			object.put(ApplicantListenerMessageKeys.HOME_PAGE_URL, notificationtemplate.getGuestUrlPattern());
			
			String [] oldSubs = buildOldSubs(object);
			
			String [] newSubs = buildNewSubs(object);

			return StringUtil.replace(emailBody, oldSubs, newSubs);

		} catch (Exception e) {
			_log.error(e);
			return StringPool.BLANK;
		}

	}

	private static String[] buildOldSubs(JSONObject object) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < object.names().length(); i++) {
			String key = object.names().getString(i);
			//String value = (String) object.get(key);
			sb.append(key);
			//_log.info("APPLICANT notification key =========" + key);
			sb.append(StringPool.COMMA);
		}

		return StringUtil.split(sb.toString(), StringPool.COMMA);
	}

	private static String[] buildNewSubs(JSONObject object) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < object.names().length(); i++) {
			String key = object.names().getString(i);
			String value = (String) object.get(key);
			//_log.info("APPLICANT notification key =========" + key);
			//_log.info("APPLICANT notification value =========" + value);
			sb.append(value);
			sb.append(StringPool.COMMA);
		}

		return StringUtil.split(sb.toString(), StringPool.COMMA);

	}
	
	static Log _log = LogFactoryUtil.getLog(ApplicantListenerUtils.class);

}
