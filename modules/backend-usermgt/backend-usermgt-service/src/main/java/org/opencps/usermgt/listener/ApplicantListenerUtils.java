package org.opencps.usermgt.listener;

import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

public class ApplicantListenerUtils {
	public static JSONObject getPayload(String notiType, JSONObject object, long groupId) {
		JSONObject payload = JSONFactoryUtil.createJSONObject();
		
	}

	private static String getEmailBody(String notiType, JSONObject object, long groupId) {
		String body = StringPool.BLANK;

		try {
			Notificationtemplate notificationtemplate = NotificationtemplateLocalServiceUtil
					.fetchByF_NotificationtemplateByType(groupId, notiType);
			
			String emailBody = notificationtemplate.getEmailBody();
			
			return StringUtil.replace(emailBody, buildOldSubs(), buildNewSubs(object));
				
		} catch (Exception e) {
			return StringPool.BLANK;
		}

	}

	private static String[] buildOldSubs() {

		StringBuffer sb = new StringBuffer();

		sb.append(ApplicantListenerMessageKeys.ACTIVATION_CODE);
		sb.append(StringPool.COMMA);
		sb.append(ApplicantListenerMessageKeys.ACTIVATION_LINK);
		sb.append(StringPool.COMMA);
		sb.append(ApplicantListenerMessageKeys.USER_NAME);
		sb.append(StringPool.COMMA);
		sb.append(ApplicantListenerMessageKeys.HOME_PAGE_URL);
		sb.append(StringPool.COMMA);

		return StringUtil.split(sb.toString(), StringPool.COMMA);
	}
	
	private static String [] buildNewSubs(JSONObject object) {
		
		StringBuffer sb = new StringBuffer();

		sb.append(object.get(ApplicantListenerMessageKeys.ACTIVATION_CODE));
		sb.append(StringPool.COMMA);
		sb.append(object.get(ApplicantListenerMessageKeys.ACTIVATION_LINK));
		sb.append(StringPool.COMMA);
		sb.append(object.get(ApplicantListenerMessageKeys.USER_NAME));
		sb.append(StringPool.COMMA);
		sb.append(object.get(ApplicantListenerMessageKeys.HOME_PAGE_URL));
		sb.append(StringPool.COMMA);

		return StringUtil.split(sb.toString(), StringPool.COMMA);

	}
}
