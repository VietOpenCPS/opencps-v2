package org.opencps.usermgt.listener;

import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class ApplicantListenerUtils {
	public static JSONObject getPayload(String notiType, JSONObject object, long groupId) {
		JSONObject payload = JSONFactoryUtil.createJSONObject();

		String subject = StringPool.BLANK;

		String body = getEmailBody(notiType, object, groupId, subject);

		payload.put("toName", object.get("toName"));
		payload.put("toAddress", object.get("toAddress"));
		payload.put("subject", subject);
		payload.put("body", body);

		return payload;
	}

	private static String getEmailBody(String notiType, JSONObject object, long groupId, String subject) {

		try {
			Notificationtemplate notificationtemplate = NotificationtemplateLocalServiceUtil
					.fetchByF_NotificationtemplateByType(groupId, notiType);

			String emailBody = notificationtemplate.getEmailBody();

			subject = notificationtemplate.getEmailSubject();
			
			String [] oldSubs = buildOldSubs(object);
			
			String [] newSubs = buildNewSubs(object);

			return StringUtil.replace(emailBody, oldSubs, newSubs);

		} catch (Exception e) {
			return StringPool.BLANK;
		}

	}

	private static String[] buildOldSubs(JSONObject object) {

		StringBuffer sb = new StringBuffer();

		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.ACTIVATION_CODE))) {
			sb.append(ApplicantListenerMessageKeys.ACTIVATION_CODE);
			sb.append(StringPool.COMMA);
		}

		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.ACTIVATION_LINK))) {
			sb.append(ApplicantListenerMessageKeys.ACTIVATION_LINK);
			sb.append(StringPool.COMMA);
		}

		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.USER_NAME))) {
			sb.append(ApplicantListenerMessageKeys.USER_NAME);
			sb.append(StringPool.COMMA);
		}

		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.HOME_PAGE_URL))) {
			sb.append(ApplicantListenerMessageKeys.HOME_PAGE_URL);
			sb.append(StringPool.COMMA);
		}
		
		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.PASSWORD))) {
			sb.append(ApplicantListenerMessageKeys.PASSWORD);
			sb.append(StringPool.COMMA);
		}

		return StringUtil.split(sb.toString(), StringPool.COMMA);
	}

	private static String[] buildNewSubs(JSONObject object) {

		StringBuffer sb = new StringBuffer();
		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.ACTIVATION_CODE))) {

			sb.append(object.get(ApplicantListenerMessageKeys.ACTIVATION_CODE));
			sb.append(StringPool.COMMA);
		}
		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.ACTIVATION_LINK))) {
			sb.append(object.get(ApplicantListenerMessageKeys.ACTIVATION_LINK));
			sb.append(StringPool.COMMA);
		}
		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.USER_NAME))) {
			sb.append(object.get(ApplicantListenerMessageKeys.USER_NAME));
			sb.append(StringPool.COMMA);
		}
		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.HOME_PAGE_URL))) {
			sb.append(object.get(ApplicantListenerMessageKeys.HOME_PAGE_URL));
			sb.append(StringPool.COMMA);
		}

		if (Validator.isNotNull(object.get(ApplicantListenerMessageKeys.PASSWORD))) {
			sb.append(object.get(ApplicantListenerMessageKeys.PASSWORD));
			sb.append(StringPool.COMMA);
		}
		
		return StringUtil.split(sb.toString(), StringPool.COMMA);

	}

}
