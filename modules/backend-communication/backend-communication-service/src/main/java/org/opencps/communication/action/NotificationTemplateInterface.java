package org.opencps.communication.action;

import java.util.LinkedHashMap;

import org.opencps.communication.model.Notificationtemplate;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public interface NotificationTemplateInterface {

	public JSONObject getNotificationTemplates(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public boolean delete(long userId, long groupId, String type, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;

	public Notificationtemplate read(long userId, long groupId, String type, ServiceContext serviceContext);

	public Notificationtemplate update(long userId, long groupId, String type, String emailBody, String emailSubject,
			String sendEmail, String textMessage, String textSMS, String expireDuration, String userUrlPattern,
			String guestUrlPattern, String interval, String grouping, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException;

	public JSONObject getNotificationTypes();

	public Notificationtemplate create(long userId, long groupId, String notificationType, String emailBody,
			String emailSubject, String sendEmail, String textMessage, String textSMS, String expireDuration,
			String userUrlPattern, String guestUrlPattern, String interval, String grouping,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException;

	public boolean deleteAllNotificationTemplate(long groupId, long userId, ServiceContext serviceContext);

	public void updateNotificationTemplateDB(long userId, long groupId, String notificationType, Boolean sendEmail,
			String emailSubject, String emailBody, String textMessage, Boolean sendSMS, Integer expireDuration,
			String interval, ServiceContext serviceContext) throws NoSuchUserException;
}
