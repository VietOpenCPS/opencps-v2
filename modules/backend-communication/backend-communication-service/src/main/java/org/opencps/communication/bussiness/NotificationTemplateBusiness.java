
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
public interface NotificationTemplateBusiness {

	Notificationtemplate create(long userId, long groupId, String notificationType, String emailBody,
			String emailSubject, String sendEmail, String textMessage, String textSMS, String expireDuration,
			String userUrlPattern, String guestUrlPattern, String interval, String grouping,
			ServiceContext serviceContext) throws PortalException, SystemException;

	void delete(long userId, long groupId, String type, ServiceContext serviceContext)
			throws PortalException, SystemException;

	JSONObject getNotificationTemplates(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) throws PortalException, SystemException;

	JSONObject getNotificationTypes() throws PortalException, SystemException;

	Notificationtemplate read(long userId, long groupId, String type, ServiceContext serviceContext)
			throws PortalException, SystemException;

	Notificationtemplate update(long userId, long groupId, String type, String emailBody, String emailSubject,
			String sendEmail, String textMessage, String textSMS, String expireDuration, String userUrlPattern,
			String guestUrlPattern, String interval, String grouping, ServiceContext serviceContext)
			throws PortalException, SystemException;
}
