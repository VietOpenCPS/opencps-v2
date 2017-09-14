package org.opencps.communication.action;

import java.util.LinkedHashMap;

import org.opencps.communication.model.Notificationtemplate;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

public interface NotificationTemplateInterface {

	public JSONObject getNotificationTemplates(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public boolean delete(long userId, long groupId, String type, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;

	public Notificationtemplate read(long userId, long groupId, String type, ServiceContext serviceContext);

	public Notificationtemplate update(long userId, long groupId, String type, String emailBody, String emailSubject,
			String sendEmail, String textMessage, String textSMS, String expireDuration, ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public JSONObject getNotificationTypes();
}
