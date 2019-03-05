
package org.opencps.communication.bussiness.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.opencps.communication.bussiness.BaseNotificationTemplateBusiness;
import org.opencps.communication.constants.NotificationMGTConstants;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;

import backend.auth.api.exception.NotFoundException;

/**
 * @author trungnt
 */
public class NotificationTemplateBusinessImpl
	extends BaseNotificationTemplateBusiness {

	@Override
	public Notificationtemplate create(
		long userId, long groupId, String notificationType, String emailBody,
		String emailSubject, String sendEmail, String textMessage,
		String textSMS, String expireDuration, String userUrlPattern,
		String guestUrlPattern, String interval, String grouping,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		return NotificationtemplateLocalServiceUtil.addNotificationTemplate(
			userId, groupId, notificationType, emailSubject, emailBody,
			textMessage, Boolean.valueOf(textSMS), Boolean.valueOf(sendEmail),
			userUrlPattern, guestUrlPattern, interval,
			Boolean.valueOf(grouping), serviceContext);

	}

	@Override
	public void delete(
		long userId, long groupId, String type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Notificationtemplate notificationtemplate =
			NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(
				groupId, type);

		if (notificationtemplate == null) {
			throw new NotFoundException();
		}

		NotificationtemplateLocalServiceUtil.deleteNotificationTemplate(
			notificationtemplate.getNotificationTemplateId(), serviceContext);
	}

	@Override
	public JSONObject getNotificationTemplates(
		long userId, long companyId, long groupId,
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);

		try {
			Hits hits = NotificationtemplateLocalServiceUtil.luceneSearchEngine(
				params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total =
				NotificationtemplateLocalServiceUtil.countLuceneSearchEngine(
					params, searchContext);

			result.put("total", total);

			if (NotificationtemplateLocalServiceUtil.initTemplate(groupId)) {

				// create init Templates
				Map<String, String> initTemplates =
					NotificationMGTConstants.getNotificationTempMap();

				for (String key : initTemplates.keySet()) {

					try {

						NotificationtemplateLocalServiceUtil.addNotificationTemplate(
							userId, groupId, key, initTemplates.get(key),
							initTemplates.get(key), initTemplates.get(key),
							Boolean.TRUE, Boolean.FALSE, StringPool.BLANK,
							StringPool.BLANK, StringPool.BLANK, Boolean.FALSE,
							serviceContext);

					}
					catch (Exception e) {
						_log.error(e);
					}

				}

			}

		}
		catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
		}
		catch (SearchException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getNotificationTypes()
		throws PortalException, SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		Map<String, String> initTemplates =
			NotificationMGTConstants.getNotificationTempMap();

		result.put("data", initTemplates);

		result.put("total", initTemplates.size());

		return result;
	}

	@Override
	public Notificationtemplate read(
		long userId, long groupId, String type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Notificationtemplate notificationtemplate =
			NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(
				groupId, type);

		if (notificationtemplate == null) {
			throw new NotFoundException();
		}

		return notificationtemplate;
	}

	@Override
	public Notificationtemplate update(
		long userId, long groupId, String type, String emailBody,
		String emailSubject, String sendEmail, String textMessage,
		String sendSMS, String expireDuration, String userUrlPattern,
		String guestUrlPattern, String interval, String grouping,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		Notificationtemplate notificationtemplate =
			NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(
				groupId, type);

		if (notificationtemplate == null) {
			throw new NotFoundException();
		}

		if (Validator.isNotNull(emailSubject)) {

			notificationtemplate.setEmailSubject(emailSubject);

		}

		if (Validator.isNotNull(emailBody)) {

			notificationtemplate.setEmailBody(emailBody);

		}

		if (Validator.isNotNull(textMessage)) {

			notificationtemplate.setTextMessage(textMessage);

		}

		if (Validator.isNotNull(sendEmail)) {

			notificationtemplate.setSendEmail(Boolean.valueOf(sendEmail));

		}

		if (Validator.isNotNull(sendSMS)) {

			notificationtemplate.setSendSMS(Boolean.valueOf(sendSMS));

		}

		if (Validator.isNotNull(expireDuration)) {

			notificationtemplate.setExpireDuration(
				Integer.valueOf(expireDuration));

		}

		if (Validator.isNotNull(userUrlPattern)) {

			notificationtemplate.setUserUrlPattern(userUrlPattern);

		}
		if (Validator.isNotNull(guestUrlPattern)) {

			notificationtemplate.setGuestUrlPattern(guestUrlPattern);

		}
		if (Validator.isNotNull(interval)) {

			notificationtemplate.setInterval(interval);

		}
		if (Validator.isNotNull(grouping)) {

			notificationtemplate.setGrouping(Boolean.valueOf(grouping));

		}

		notificationtemplate =
			NotificationtemplateLocalServiceUtil.updateNotificationTemplate(
				userId, notificationtemplate.getNotificationTemplateId(),
				notificationtemplate.getNotificationType(),
				notificationtemplate.getEmailSubject(),
				notificationtemplate.getEmailBody(),
				notificationtemplate.getTextMessage(),
				notificationtemplate.getSendSMS(),
				notificationtemplate.getSendEmail(),
				notificationtemplate.getExpireDuration(),
				notificationtemplate.getUserUrlPattern(),
				notificationtemplate.getGuestUrlPattern(),
				notificationtemplate.getInterval(),
				notificationtemplate.getGrouping(), serviceContext);

		return notificationtemplate;
	}

	public Notificationtemplate fetchByNotificationType(long groupId, String type) {

		Notificationtemplate notificationType =
			NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(
				groupId, type);
		return notificationType;
	}

	public Log _log =
		LogFactoryUtil.getLog(NotificationTemplateBusinessImpl.class);
}
