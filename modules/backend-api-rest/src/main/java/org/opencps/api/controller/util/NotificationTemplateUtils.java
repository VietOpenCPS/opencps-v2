package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opencps.api.notificationtemplate.model.NotificationQueueModel;
import org.opencps.api.notificationtemplate.model.NotificationTypeModel;
import org.opencps.api.notificationtemplate.model.NotificationtemplateModel;
import org.opencps.communication.constants.NotificationMGTConstants;
import org.opencps.communication.constants.NotificationTemplateTerm;
import org.opencps.communication.model.Notificationtemplate;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.auth.utils.APIDateTimeUtils;

public class NotificationTemplateUtils {

	public static List<NotificationQueueModel> mapperNotificationQueueList(List<Document> listDocument) {

		List<NotificationQueueModel> results = new ArrayList<>();

		try {

			NotificationQueueModel ett = null;

			for (Document document : listDocument) {
				ett = new NotificationQueueModel();

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}
	
	public static List<NotificationTypeModel> mapperNotificationTypeList(Map<String, String> initTemplates) {

		List<NotificationTypeModel> results = new ArrayList<>();

		try {

			NotificationTypeModel ett = null;

			for (String key : initTemplates.keySet()) {

				ett = new NotificationTypeModel();
				
				ett.setTypeCode(key);
				ett.setTypeName(initTemplates.get(key));
				
				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}
	
	public static List<NotificationtemplateModel> mapperNotificationtemplateList(List<Document> listDocument) {

		List<NotificationtemplateModel> results = new ArrayList<>();
		Map<String, String> initTemplates = NotificationMGTConstants.NOTIFICATION_TEMPLATE_INIT;

		try {

			NotificationtemplateModel ett = null;

			for (Document document : listDocument) {
				ett = new NotificationtemplateModel();

				ett.setModifiedDate(
						Validator.isNotNull(document.getDate("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);
				ett.setNotificationType(document.get(NotificationTemplateTerm.NOTIFICATTION_TYPE));

				ett.setTypeName(initTemplates.get(document.get(NotificationTemplateTerm.NOTIFICATTION_TYPE)));

				ett.setEmailSubject(document.get(NotificationTemplateTerm.NOTIFICATION_EMAIL_SUBJECT));
				ett.setEmailBody(document.get(NotificationTemplateTerm.NOTIFICATION_EMAIL_BODY));
				ett.setTextMessage(document.get(NotificationTemplateTerm.NOTIFICATION_TEXT_MESSAGE));
				ett.setSendEmail(Boolean.valueOf(document.get(NotificationTemplateTerm.SEND_EMAIL)));
				ett.setSendSMS(Boolean.valueOf(document.get(NotificationTemplateTerm.NOTIFICATION_SEND_SMS)));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static NotificationtemplateModel mapperNotificationtemplateModel(Notificationtemplate notificationtemplate) {

		NotificationtemplateModel ett = new NotificationtemplateModel();

		try {

			ett.setModifiedDate(
					Validator.isNotNull(notificationtemplate.getModifiedDate()) ? APIDateTimeUtils.convertDateToString(
							notificationtemplate.getModifiedDate(), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);
			ett.setNotificationType(notificationtemplate.getNotificationType());

			Map<String, String> initTemplates = NotificationMGTConstants.NOTIFICATION_TEMPLATE_INIT;

			ett.setTypeName(initTemplates.get(notificationtemplate.getNotificationType()));
			ett.setEmailSubject(notificationtemplate.getEmailSubject());
			ett.setEmailBody(notificationtemplate.getEmailBody());
			ett.setTextMessage(notificationtemplate.getTextMessage());
			ett.setSendEmail(notificationtemplate.getSendEmail());
			ett.setSendSMS(notificationtemplate.getSendSMS());

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	static Log _log = LogFactoryUtil.getLog(NotificationTemplateUtils.class);
}
