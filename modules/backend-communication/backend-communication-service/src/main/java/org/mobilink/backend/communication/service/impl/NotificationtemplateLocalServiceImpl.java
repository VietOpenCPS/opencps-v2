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

package org.mobilink.backend.communication.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.mobilink.backend.communication.constants.NotificationTemplateTerm;
import org.mobilink.backend.communication.model.Notificationtemplate;
import org.mobilink.backend.communication.service.base.NotificationtemplateLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the notificationtemplate local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.mobilink.backend.communication.service.NotificationtemplateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see NotificationtemplateLocalServiceBaseImpl
 * @see org.mobilink.backend.communication.service.NotificationtemplateLocalServiceUtil
 */
@ProviderType
public class NotificationtemplateLocalServiceImpl
	extends NotificationtemplateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.mobilink.backend.communication.service.NotificationtemplateLocalServiceUtil} to access the notificationtemplate local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Notificationtemplate addNotificationTemplate(long userId, long groupId, String notificationType,
			String emailSubject, String emailBody, String textMessage, String textSMS, ServiceContext serviceContext)
			throws Exception {

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

		notificationTemplate.setNotificationTemplateId(notificationTemplateId);

		notificationTemplate.setNotificationType(notificationType);
		notificationTemplate.setEmailSubject(emailSubject);
		notificationTemplate.setEmailBody(emailBody);
		notificationTemplate.setTextMessage(textMessage);
		notificationTemplate.setTextSMS(textSMS);

		notificationTemplate.setExpandoBridgeAttributes(serviceContext);

		notificationtemplatePersistence.update(notificationTemplate);

		return notificationTemplate;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Notificationtemplate deleteNotificationTemplate(long notificationTemplateId,
			ServiceContext serviceContext) throws PortalException {

		Notificationtemplate notificationTemplate = notificationtemplatePersistence.remove(notificationTemplateId);

		return notificationTemplate;

	}

	/**
	 * @param userId
	 * @param dictCollectionId
	 * @param fullName
	 * @param companyName
	 * @param telNo
	 * @param email
	 * @param mobilinkId
	 * @param userMappingId
	 * @param outSide
	 * @param isOrg
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Notificationtemplate updateNotificationTemplate(long userId, long notificationTemplateId,
			String notificationType, String emailSubject, String emailBody, String textMessage, String textSMS,
			ServiceContext serviceContext) throws Exception {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Notificationtemplate notificationTemplate = notificationtemplatePersistence
				.fetchByPrimaryKey(notificationTemplateId);

		// Audit fields
		notificationTemplate.setUserId(user.getUserId());
		notificationTemplate.setUserName(user.getFullName());
		notificationTemplate.setModifiedDate(serviceContext.getCreateDate(now));

		notificationTemplate.setNotificationType(notificationType);
		notificationTemplate.setEmailSubject(emailSubject);
		notificationTemplate.setEmailBody(emailBody);
		notificationTemplate.setTextMessage(textMessage);
		notificationTemplate.setTextSMS(textSMS);
		
		notificationTemplate.setExpandoBridgeAttributes(serviceContext);

		notificationtemplatePersistence.update(notificationTemplate);

		return notificationTemplate;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub
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
		BooleanQuery booleanQuery = null;

		booleanQuery = Validator.isNotNull((String) keywords)
				? BooleanQueryFactoryUtil.create((SearchContext) searchContext) : indexer.getFullQuery(searchContext);

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
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Notificationtemplate.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public Notificationtemplate fetchByF_NotificationtemplateByType(long groupId, String notificationType) {
		return notificationtemplatePersistence.fetchByF_NotificationtemplateByType(groupId, notificationType);
	}

	private static final Log _log = LogFactoryUtil.getLog(NotificationtemplateLocalServiceImpl.class);
}