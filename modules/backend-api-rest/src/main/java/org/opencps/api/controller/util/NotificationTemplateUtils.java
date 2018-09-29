package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.opencps.api.notificationtemplate.model.NotificationQueueShortModel;
import org.opencps.api.notificationtemplate.model.NotificationTypeModel;
import org.opencps.api.notificationtemplate.model.NotificationtemplateModel;
import org.opencps.communication.constants.NotificationMGTConstants;
import org.opencps.communication.constants.NotificationTemplateTerm;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;

import backend.utils.APIDateTimeUtils;

public class NotificationTemplateUtils {

	public static List<NotificationQueueShortModel> mapperNotificationQueueList(List<NotificationQueue> listDocument) {

		List<NotificationQueueShortModel> results = new ArrayList<>();

		try {

			NotificationQueueShortModel ett = null;

			for (NotificationQueue notificationQueue : listDocument) {
				
				ett = new NotificationQueueShortModel();
				ett.setNotificationQueueId(notificationQueue.getNotificationQueueId());
				ett.setNotificationType(notificationQueue.getNotificationType());
				ett.setClassName(notificationQueue.getClassName());
				ett.setClassPK(notificationQueue.getClassPK());
				ett.setPayload(notificationQueue.getPayload());
				ett.setFromUsername(notificationQueue.getFromUsername());
				ett.setToUsername(notificationQueue.getToUsername());
				ett.setToUserId(String.valueOf(notificationQueue.getToUserId()));
				ett.setToEmail(notificationQueue.getToEmail());
				ett.setToTelNo(notificationQueue.getToTelNo());
				ett.setExpireDate(Validator.isNotNull(notificationQueue.getExpireDate()) ? APIDateTimeUtils.convertDateToString(
						notificationQueue.getExpireDate(), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);
				ett.setPublicationDate(Validator.isNotNull(notificationQueue.getCreateDate()) ? APIDateTimeUtils.convertDateToString(
						notificationQueue.getCreateDate(), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);
				
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

			if (!results.isEmpty()) {
				Collections.sort(results, new Comparator<NotificationTypeModel>() {
					@Override
					public int compare(NotificationTypeModel c1, NotificationTypeModel c2) {
						return c1.getTypeCode().compareTo(c2.getTypeCode());
					}
				});
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static List<NotificationtemplateModel> mapperNotificationtemplateList(List<Document> listDocument) {

		List<NotificationtemplateModel> results = new ArrayList<>();
//		Map<String, String> initTemplates = NotificationMGTConstants.NOTIFICATION_TEMPLATE_INIT;

		try {

			NotificationtemplateModel ett = null;

			for (Document document : listDocument) {
				ett = new NotificationtemplateModel();

				ett.setModifiedDate( Validator.isNotNull(document.get("modified")) &&
						Validator.isNotNull(document.getDate("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);
				ett.setNotificationType(document.get(NotificationTemplateTerm.NOTIFICATTION_TYPE));

//				ett.setTypeName(initTemplates.get(document.get(NotificationTemplateTerm.NOTIFICATTION_TYPE)));
				ett.setTypeName(NotificationMGTConstants
						.getNotificationTemp(document.get(NotificationTemplateTerm.NOTIFICATTION_TYPE)));

				ett.setEmailSubject(document.get(NotificationTemplateTerm.NOTIFICATION_EMAIL_SUBJECT));
				ett.setEmailBody(document.get(NotificationTemplateTerm.NOTIFICATION_EMAIL_BODY));
				ett.setTextMessage(document.get(NotificationTemplateTerm.NOTIFICATION_TEXT_MESSAGE));
				ett.setSendEmail(Boolean.valueOf(document.get(NotificationTemplateTerm.SEND_EMAIL)));
				ett.setSendSMS(Boolean.valueOf(document.get(NotificationTemplateTerm.NOTIFICATION_SEND_SMS)));
				ett.setExpireDuration(GetterUtil.get(document.get(NotificationTemplateTerm.EXPIRE_DURATION), 0));
				ett.setUserUrlPattern(document.get(NotificationTemplateTerm.USER_URL_PARTTERN));
				ett.setGuestUrlPattern(document.get(NotificationTemplateTerm.GUEST_URL_PARTTERN));
				ett.setInterval(document.get(NotificationTemplateTerm.INTERVAL));
				ett.setGrouping(Boolean.valueOf(document.get(NotificationTemplateTerm.GROUPING)));
				
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

//			Map<String, String> initTemplates = NotificationMGTConstants.NOTIFICATION_TEMPLATE_INIT;

			ett.setTypeName(NotificationMGTConstants.getNotificationTemp(notificationtemplate.getNotificationType()));
			ett.setEmailSubject(notificationtemplate.getEmailSubject());
			ett.setEmailBody(notificationtemplate.getEmailBody());
			ett.setTextMessage(notificationtemplate.getTextMessage());
			ett.setSendEmail(notificationtemplate.getSendEmail());
			ett.setSendSMS(notificationtemplate.getSendSMS());
			ett.setExpireDuration(notificationtemplate.getExpireDuration());
			ett.setUserUrlPattern(notificationtemplate.getUserUrlPattern());
			ett.setGuestUrlPattern(notificationtemplate.getGuestUrlPattern());
			ett.setInterval(notificationtemplate.getInterval());
			ett.setGrouping(notificationtemplate.getGrouping());
		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}
	
	public static NotificationQueueShortModel mapperNotificationQueueModel(NotificationQueue notificationQueue) {

		NotificationQueueShortModel ett = new NotificationQueueShortModel();

		try {

			ett.setNotificationQueueId(notificationQueue.getNotificationQueueId());
			ett.setNotificationType(notificationQueue.getNotificationType());
			ett.setClassName(notificationQueue.getClassName());
			ett.setClassPK(notificationQueue.getClassPK());
			ett.setPayload(notificationQueue.getPayload());
			ett.setFromUsername(notificationQueue.getFromUsername());
			ett.setToUsername(notificationQueue.getToUsername());
			ett.setToUserId(String.valueOf(notificationQueue.getToUserId()));
			ett.setToEmail(notificationQueue.getToEmail());
			ett.setToTelNo(notificationQueue.getToTelNo());
			ett.setExpireDate(Validator.isNotNull(notificationQueue.getExpireDate()) ? APIDateTimeUtils.convertDateToString(
					notificationQueue.getExpireDate(), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);
			ett.setPublicationDate(Validator.isNotNull(notificationQueue.getCreateDate()) ? APIDateTimeUtils.convertDateToString(
					notificationQueue.getCreateDate(), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);
//			ett.setEmailSubject(notificationQueue.get);
//			ett.setEmailBody(notificationQueue.get);
//			ett.setTextMessage(notificationQueue.get);
//			ett.setUrlLink(notificationQueue.get);
//			ett.setSendEmail(notificationQueue.get);
//			ett.setSendSMS(notificationQueue.get);
			
		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}
	
	
	static Log _log = LogFactoryUtil.getLog(NotificationTemplateUtils.class);
}
