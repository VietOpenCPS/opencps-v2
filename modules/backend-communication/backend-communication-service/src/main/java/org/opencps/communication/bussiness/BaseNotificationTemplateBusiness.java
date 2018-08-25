package org.opencps.communication.bussiness;

import java.util.LinkedHashMap;

import org.opencps.communication.model.Notificationtemplate;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */
public abstract class BaseNotificationTemplateBusiness
	implements NotificationTemplateBusiness {

	@Override
	public Notificationtemplate create(
		long userId, long groupId, String notificationType, String emailBody,
		String emailSubject, String sendEmail, String textMessage,
		String textSMS, String expireDuration, String userUrlPattern,
		String guestUrlPattern, String interval, String grouping,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		return create(
			userId, groupId, notificationType, emailBody, emailSubject,
			sendEmail, textMessage, textSMS, expireDuration, userUrlPattern,
			guestUrlPattern, interval, grouping, serviceContext);
	}

	@Override
	public void delete(
		long userId, long groupId, String type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		delete(userId, groupId, type, serviceContext);
	}

	@Override
	public JSONObject getNotificationTemplates(
		long userId, long companyId, long groupId,
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getNotificationTemplates(
			userId, companyId, groupId, params, sorts, start, end,
			serviceContext);
	}

	@Override
	public JSONObject getNotificationTypes()
		throws PortalException, SystemException {

		return getNotificationTypes();
	}

	@Override
	public Notificationtemplate read(
		long userId, long groupId, String type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return read(userId, groupId, type, serviceContext);
	}

	@Override
	public Notificationtemplate update(
		long userId, long groupId, String type, String emailBody,
		String emailSubject, String sendEmail, String textMessage,
		String textSMS, String expireDuration, String userUrlPattern,
		String guestUrlPattern, String interval, String grouping,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		return update(
			userId, groupId, type, emailBody, emailSubject, sendEmail,
			textMessage, textSMS, expireDuration, userUrlPattern,
			guestUrlPattern, interval, grouping, serviceContext);
	}
}
