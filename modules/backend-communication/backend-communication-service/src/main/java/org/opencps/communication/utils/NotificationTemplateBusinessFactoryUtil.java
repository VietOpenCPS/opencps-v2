
package org.opencps.communication.utils;

import java.util.LinkedHashMap;

import org.opencps.communication.bussiness.impl.NotificationTemplateBusinessImpl;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */
public class NotificationTemplateBusinessFactoryUtil {

	private static NotificationTemplateBusinessImpl _notificationTemplateBusinessImpl;

	public NotificationTemplateBusinessImpl getNotificationTemplateBusinessImpl() {

		return _notificationTemplateBusinessImpl;
	}

	public static void setNotificationTemplateBusinessImpl(
		NotificationTemplateBusinessImpl notificationTemplateBusinessImpl) {

		NotificationTemplateBusinessFactoryUtil._notificationTemplateBusinessImpl =
			notificationTemplateBusinessImpl;
	}

	public static NotificationTemplateBusinessImpl getNotificationTemplateBusiness() {

		if (_notificationTemplateBusinessImpl == null) {
			setNotificationTemplateBusinessImpl(
				new NotificationTemplateBusinessImpl());
		}
		return _notificationTemplateBusinessImpl;
	}

	public static Notificationtemplate create(
		long userId, long groupId, String notificationType, String emailBody,
		String emailSubject, String sendEmail, String textMessage,
		String textSMS, String expireDuration, String userUrlPattern,
		String guestUrlPattern, String interval, String grouping,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getNotificationTemplateBusiness().create(
			userId, groupId, notificationType, emailBody, emailSubject,
			sendEmail, textMessage, textSMS, expireDuration, userUrlPattern,
			guestUrlPattern, interval, grouping, serviceContext);

	}

	public static void delete(
		long userId, long groupId, String type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		getNotificationTemplateBusiness().delete(
			userId, groupId, type, serviceContext);
	}

	public static JSONObject getNotificationTemplates(
		long userId, long companyId, long groupId,
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getNotificationTemplateBusiness().getNotificationTemplates(
			userId, companyId, groupId, params, sorts, start, end,
			serviceContext);
	}

	public static JSONObject getNotificationTypes()
		throws PortalException, SystemException {

		return getNotificationTemplateBusiness().getNotificationTypes();
	}

	public static Notificationtemplate read(
		long userId, long groupId, String type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getNotificationTemplateBusiness().read(
			userId, groupId, type, serviceContext);
	}

	public static Notificationtemplate update(
		long userId, long groupId, String type, String emailBody,
		String emailSubject, String sendEmail, String textMessage,
		String sendSMS, String expireDuration, String userUrlPattern,
		String guestUrlPattern, String interval, String grouping,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getNotificationTemplateBusiness().update(
			userId, groupId, type, emailBody, emailSubject, sendEmail,
			textMessage, sendSMS, expireDuration, userUrlPattern,
			guestUrlPattern, interval, grouping, serviceContext);
	}

	public static Notificationtemplate fetchByNotificationType(
		long groupId, String type) {

		Notificationtemplate notificationType =
			NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(
				groupId, type);
		return notificationType;
	}

}
