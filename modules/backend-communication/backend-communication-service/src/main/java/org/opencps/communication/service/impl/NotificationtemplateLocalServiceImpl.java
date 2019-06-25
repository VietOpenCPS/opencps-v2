/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.communication.service.impl;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.communication.constants.NotificationTemplateTerm;
import org.opencps.communication.exception.NoSuchNotificationtemplateException;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.base.NotificationtemplateLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;

/**
 * The implementation of the notificationtemplate local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.communication.service.NotificationtemplateLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see NotificationtemplateLocalServiceBaseImpl
 * @see org.opencps.communication.service.NotificationtemplateLocalServiceUtil
 */
@ProviderType
public class NotificationtemplateLocalServiceImpl extends NotificationtemplateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.communication.service. NotificationtemplateLocalServiceUtil} to
	 * access the notificationtemplate local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Notificationtemplate addNotificationTemplate(long userId, long groupId, String notificationType,
			String emailSubject, String emailBody, String textMessage, boolean sendSMS, boolean sendEmail,
			String userUrlPattern, String guestUrlPattern, String interval, boolean grouping,
			ServiceContext serviceContext) throws UnauthenticationException, UnauthorizationException,
			NoSuchUserException, DuplicateCategoryException {

		Notificationtemplate notificationtemplate = notificationtemplatePersistence
				.fetchByF_NotificationtemplateByType(groupId, notificationType);

		if (Validator.isNotNull(notificationtemplate)) {

			throw new DuplicateCategoryException();

		}

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long notificationTemplateId = counterLocalService.increment(Notificationtemplate.class.getName());

		Notificationtemplate notificationTemplate = notificationtemplatePersistence.create(notificationTemplateId);

		// Group instance
		notificationTemplate.setGroupId(groupId);

		// Audit fields
		notificationTemplate.setCompanyId(user.getCompanyId());
		notificationTemplate.setUserId(user.getUserId());
		notificationTemplate.setUserName(user.getFullName());
		notificationTemplate.setCreateDate(serviceContext.getCreateDate(now));
		notificationTemplate.setModifiedDate(serviceContext.getCreateDate(now));

		notificationTemplate.setSendEmail(sendEmail);

		notificationTemplate.setNotificationType(notificationType);
		notificationTemplate.setEmailSubject(emailSubject);
		notificationTemplate.setEmailBody(emailBody);
		notificationTemplate.setTextMessage(textMessage);
		notificationTemplate.setSendSMS(sendSMS);

		notificationTemplate.setExpireDuration(24);

		notificationTemplate.setUserUrlPattern(userUrlPattern);
		notificationTemplate.setGuestUrlPattern(guestUrlPattern);
		notificationTemplate.setInterval(interval);
		notificationTemplate.setGrouping(grouping);

		notificationTemplate.setExpandoBridgeAttributes(serviceContext);

		return notificationtemplatePersistence.update(notificationTemplate);
		// return notificationTemplate;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Notificationtemplate deleteNotificationTemplate(long notificationTemplateId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Notificationtemplate notificationTemplate = null;
		try {
			notificationTemplate = notificationtemplatePersistence.remove(notificationTemplateId);
		} catch (NoSuchNotificationtemplateException e) {
			_log.error(e);
		}

		return notificationTemplate;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Notificationtemplate updateNotificationTemplate(long userId, long notificationTemplateId,
			String notificationType, String emailSubject, String emailBody, String textMessage, boolean sendSMS,
			boolean sendEmail, int expireDuration, String userUrlPattern, String guestUrlPattern, String interval,
			boolean grouping, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, NoSuchUserException {

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Notificationtemplate notificationTemplate = notificationtemplatePersistence
				.fetchByPrimaryKey(notificationTemplateId);

		// Audit fields
		notificationTemplate.setUserId(user.getUserId());
		notificationTemplate.setUserName(user.getFullName());
		notificationTemplate.setModifiedDate(serviceContext.getCreateDate(now));

		notificationTemplate.setSendEmail(sendEmail);
		notificationTemplate.setNotificationType(notificationType);
		notificationTemplate.setEmailSubject(emailSubject);
		notificationTemplate.setEmailBody(emailBody);
		notificationTemplate.setTextMessage(textMessage);
		notificationTemplate.setSendSMS(sendSMS);
		notificationTemplate.setExpireDuration(expireDuration);

		notificationTemplate.setUserUrlPattern(userUrlPattern);
		notificationTemplate.setGuestUrlPattern(guestUrlPattern);
		notificationTemplate.setInterval(interval);
		notificationTemplate.setGrouping(grouping);

		notificationTemplate.setExpandoBridgeAttributes(serviceContext);

		notificationtemplatePersistence.update(notificationTemplate);

		return notificationTemplate;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		Indexer<Notificationtemplate> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Notificationtemplate.class);

		searchContext.addFullQueryEntryClassName(Notificationtemplate.class.getName());
		searchContext.setEntryClassNames(new String[] { Notificationtemplate.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		searchContext.setAttribute("params", params);

		// // LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String sendEMail = (String) params.get(NotificationTemplateTerm.SEND_EMAIL);
		BooleanQuery booleanQuery = null;

		booleanQuery = Validator.isNotNull((String) keywords)
				? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
				: indexer.getFullQuery(searchContext);

		if (Validator.isNotNull(keywords)) {
			String[] keyword = keywords.split(StringPool.SPACE);
			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(NotificationTemplateTerm.NOTIFICATION_EMAIL_SUBJECT);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(NotificationTemplateTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(NotificationTemplateTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}
		if (Validator.isNotNull(sendEMail)) {
			MultiMatchQuery query = new MultiMatchQuery(sendEMail);

			query.addFields(NotificationTemplateTerm.SEND_EMAIL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Notificationtemplate.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		Indexer<Notificationtemplate> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Notificationtemplate.class);

		searchContext.addFullQueryEntryClassName(Notificationtemplate.class.getName());
		searchContext.setEntryClassNames(new String[] { Notificationtemplate.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		searchContext.setAttribute("params", params);

		// // LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String sendEMail = (String) params.get(NotificationTemplateTerm.SEND_EMAIL);

		BooleanQuery booleanQuery = null;

		booleanQuery = Validator.isNotNull((String) keywords)
				? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
				: indexer.getFullQuery(searchContext);

		if (Validator.isNotNull(keywords)) {
			String[] keyword = keywords.split(StringPool.SPACE);
			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(NotificationTemplateTerm.NOTIFICATION_EMAIL_SUBJECT);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(NotificationTemplateTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(NotificationTemplateTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(sendEMail)) {
			MultiMatchQuery query = new MultiMatchQuery(sendEMail);

			query.addFields(NotificationTemplateTerm.SEND_EMAIL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Notificationtemplate.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	public Notificationtemplate fetchByF_NotificationtemplateByType(long groupId, String notificationType) {
		return notificationtemplatePersistence.fetchByF_NotificationtemplateByType(groupId, notificationType);
	}

	public List<Notificationtemplate> findByF_NotificationtemplateByGroup(long groupId) {
		return notificationtemplatePersistence.findByF_NotificationtemplateByGroup(groupId);
	}

	public boolean initTemplate(long groupId) {

		boolean result = false;

		if (notificationtemplatePersistence.findByF_NotificationtemplateByGroup(groupId).size() <= 0) {

			result = true;

		}

		return result;

	}

	// LamTV_Add ouput DB
	@Indexable(type = IndexableType.REINDEX)
	public Notificationtemplate updateNotificationTemplateDB(long userId, long groupId, String notificationType,
			Boolean sendEmail, String emailSubject, String emailBody, String textMessage, Boolean sendSMS,
			Integer expireDuration, String interval, ServiceContext serviceContext) throws NoSuchUserException {

		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		
		Notificationtemplate notificationTemplate = notificationtemplatePersistence
				.fetchByF_NotificationtemplateByType(groupId, notificationType);

		if (notificationTemplate == null) {
			long notificationTemplateId = counterLocalService.increment(Notificationtemplate.class.getName());

			notificationTemplate = notificationtemplatePersistence.create(notificationTemplateId);

			// Group instance
			notificationTemplate.setGroupId(groupId);
			// Audit fields
			notificationTemplate.setCompanyId(user.getCompanyId());
			notificationTemplate.setUserId(user.getUserId());
			notificationTemplate.setUserName(user.getFullName());
			notificationTemplate.setCreateDate(serviceContext.getCreateDate(now));
			notificationTemplate.setModifiedDate(serviceContext.getCreateDate(now));

			notificationTemplate.setSendEmail(sendEmail);
			notificationTemplate.setNotificationType(notificationType);
			notificationTemplate.setEmailSubject(emailSubject);
			notificationTemplate.setEmailBody(emailBody);
			notificationTemplate.setTextMessage(textMessage);
			notificationTemplate.setSendSMS(sendSMS);
			notificationTemplate.setExpireDuration(expireDuration);
			notificationTemplate.setInterval(interval);
		} else {
			notificationTemplate.setModifiedDate(serviceContext.getCreateDate(now));
			if (Validator.isNotNull(sendEmail))
				notificationTemplate.setSendEmail(sendEmail);
			if (Validator.isNotNull(emailSubject))
				notificationTemplate.setEmailSubject(emailSubject);
			if (Validator.isNotNull(emailBody))
				notificationTemplate.setEmailBody(emailBody);
			if (Validator.isNotNull(textMessage))
				notificationTemplate.setTextMessage(textMessage);
			if (Validator.isNotNull(sendSMS))
				notificationTemplate.setSendSMS(sendSMS);
			if (Validator.isNotNull(expireDuration))
				notificationTemplate.setExpireDuration(expireDuration);
			if (Validator.isNotNull(interval))
				notificationTemplate.setInterval(interval);
		}

		return notificationtemplatePersistence.update(notificationTemplate);
	}

	public long countNotificationTemplateByGroupId(long groupId) {
		return notificationtemplatePersistence.countByF_NotificationtemplateByGroup(groupId);
	}

	@Override
	public List<Notificationtemplate> findByInterval(String interval) {
		return notificationtemplatePersistence.findByF_interval(interval);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public Notificationtemplate adminProcessDelete(Long id) {

		Notificationtemplate object = notificationtemplatePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			notificationtemplatePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Notificationtemplate adminProcessData(JSONObject objectData) {

		Notificationtemplate object = null;

		if (objectData.getLong("notificationTemplateId") > 0) {

			object = notificationtemplatePersistence.fetchByPrimaryKey(objectData.getLong("notificationTemplateId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(Notificationtemplate.class.getName());

			object = notificationtemplatePersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setNotificationType(objectData.getString("notificationType"));
		object.setEmailSubject(objectData.getString("emailSubject"));
		object.setEmailBody(objectData.getString("emailBody"));
		object.setTextMessage(objectData.getString("textMessage"));
		object.setSendSMS(objectData.getBoolean("sendSMS"));
		object.setSendEmail(objectData.getBoolean("sendEmail"));
		object.setExpireDuration(objectData.getInt("expireDuration"));
		object.setUserUrlPattern(objectData.getString("userUrlPattern"));
		object.setGuestUrlPattern(objectData.getString("guestUrlPattern"));
		object.setInterval(objectData.getString("interval"));
		object.setGrouping(objectData.getBoolean("grouping"));
		object.setSendNotification(objectData.getBoolean("sendNotification"));

		notificationtemplatePersistence.update(object);

		return object;

	}

	public Notificationtemplate findByF_TYPE_INTER(long groupId, String notificationType, String interval) {
		return notificationtemplatePersistence.fetchByF_GID_TYPE_INTER(groupId, notificationType, interval);
	}

	private static final Log _log = LogFactoryUtil.getLog(NotificationtemplateLocalServiceImpl.class);
}