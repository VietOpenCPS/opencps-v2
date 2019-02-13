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

package org.opencps.dossiermgt.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.constants.DossierLogTerm;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.service.base.DossierLogLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierLogLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierLogLocalServiceUtil
 */
@ProviderType
public class DossierLogLocalServiceImpl extends DossierLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierLogLocalServiceUtil} to access the
	 * dossier log local service.
	 */

	public List<DossierLog> getByDossierAndType(long dossierId, String type, int start, int end)
			throws PortalException {
		return dossierLogPersistence.findByDID_NTYP(type, dossierId, start, end);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierLog addDossierLog(long groupId, long dossierId, String author, String content,
			String notificationType, String payload, ServiceContext serviceContext)
			throws PortalException, SystemException {

		// long userId = serviceContext.getUserId();

		// validateAddDossierFile(groupId, dossierId, referenceUid,
		// dossierTemplateNo, dossierPartNo, fileTemplateNo);

		// DossierPart dossierPart = dossierPartPersistence.findByTP_NO_PART(
		// groupId, dossierTemplateNo, dossierPartNo);

		Date now = new Date();

		// User userAction = userLocalService.getUser(userId);

		long dossierFileId = counterLocalService.increment(DossierLog.class.getName());

		DossierLog object = dossierLogPersistence.create(dossierFileId);

		/// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setModifiedDate(now);
		// object.setUserId(userAction.getUserId());
		// object.setUserName(userAction.getFullName());
		object.setAuthor(author);

		// Add other fields
		object.setDossierId(dossierId);
		object.setContent(content);
		object.setNotificationType(notificationType);
		object.setPayload(payload);

		return dossierLogPersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierLog updateDossierLog(long groupId, long dossierId, long dossierLogId, String author, String content,
			String notificationType, String payload, ServiceContext serviceContext)
			throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		// TODO: validate

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		DossierLog object = null;
		if (dossierLogId > 0) {
			object = dossierLogPersistence.findByPrimaryKey(dossierLogId);
		} else {
			dossierLogId = counterLocalService.increment(DossierLog.class.getName());

			object = dossierLogPersistence.create(dossierLogId);
			object.setCreateDate(now);
		}

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		// Add other fields
		object.setDossierId(dossierId);
		object.setAuthor(author);
		object.setContent(content);
		object.setNotificationType(notificationType);
		object.setPayload(payload);

		return dossierLogPersistence.update(object);
	}

	public DossierLog deleteDossierLog(long dossierLogId) throws PortalException {
		DossierLog dossierLog = dossierLogPersistence.findByPrimaryKey(dossierLogId);

		return deleteDossierLog(dossierLog);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierLog deleteDossierLog(DossierLog dossierLog) {

		return dossierLogPersistence.remove(dossierLog);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String notiType = String.valueOf(params.get(DossierLogTerm.NOTIFICATION_TYPE));
		notiType = StringUtil.replace(notiType, StringPool.DASH, StringPool.BLANK);
		String groupId = (String) params.get(Field.GROUP_ID);
		String dosssierId = String.valueOf(params.get(DossierLogTerm.DOSSIER_ID));
		String keywords = "";
		if (notiType != "null") {
			keywords = notiType;
		} else {
			keywords = dosssierId;
		}
		Indexer<DossierLog> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierLog.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);
				if (notiType != "null") {
					query.addFields(DossierLogTerm.NOTIFICATION_TYPE);
				} else {
					query.addFields(DossierLogTerm.DOSSIER_ID);
				}
				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		String content = GetterUtil.getString(params.get(DossierLogTerm.CONTENT));
		String dossierLogId = GetterUtil.getString(params.get(DossierLogTerm.DOSSIER_LOG_ID));

		if (Validator.isNotNull(notiType)) {
			MultiMatchQuery query = new MultiMatchQuery(notiType);

			query.addFields(DossierLogTerm.NOTIFICATION_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dosssierId)) {
			MultiMatchQuery query = new MultiMatchQuery(dosssierId);

			query.addFields(DossierLogTerm.DOSSIER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierLogId)) {
			MultiMatchQuery query = new MultiMatchQuery(content);

			query.addFields(DossierLogTerm.DOSSIER_LOG_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String notiType = String.valueOf(params.get(DossierLogTerm.NOTIFICATION_TYPE));
		notiType = StringUtil.replace(notiType, StringPool.DASH, StringPool.BLANK);
		String groupId = (String) params.get(Field.GROUP_ID);
		String dosssierId = String.valueOf(params.get(DossierLogTerm.DOSSIER_ID));
		String keywords = "";
		if (notiType != "null") {
			keywords = notiType;
		} else {
			keywords = dosssierId;
		}

		Indexer<DossierLog> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierLog.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);
				if (notiType != "null") {
					query.addFields(DossierLogTerm.NOTIFICATION_TYPE);
				} else {
					query.addFields(DossierLogTerm.DOSSIER_ID);
				}
				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String dossierLogId = GetterUtil.getString(params.get(DossierLogTerm.DOSSIER_LOG_ID));

		if (Validator.isNotNull(notiType)) {
			MultiMatchQuery query = new MultiMatchQuery(notiType);

			query.addFields(DossierLogTerm.NOTIFICATION_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dosssierId)) {
			MultiMatchQuery query = new MultiMatchQuery(dosssierId);

			query.addFields(DossierLogTerm.DOSSIER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierLogId)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierLogId);

			query.addFields(DossierLogTerm.DOSSIER_LOG_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public List<DossierLog> findByGroup(long groupId) {
		return dossierLogPersistence.findByG(groupId);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DossierLog adminProcessDelete(Long id) {

		DossierLog object = dossierLogPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dossierLogPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierLog adminProcessData(JSONObject objectData) {

		DossierLog object = null;

		if (objectData.getLong("dossierLogId") > 0) {

			object = dossierLogPersistence.fetchByPrimaryKey(objectData.getLong("dossierLogId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DossierLog.class.getName());

			object = dossierLogPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setDossierId(objectData.getLong("dossierId"));
		object.setAuthor(objectData.getString("author"));
		object.setContent(objectData.getString("content"));
		object.setNotificationType(objectData.getString("notificationType"));
		object.setPayload(objectData.getString("payload"));

		dossierLogPersistence.update(object);

		return object;
	}

	public void deleteByDossierAndType(long dossierId, String type)
			throws PortalException {
		dossierLogPersistence.removeByDID_NTYP(type, dossierId);
	}

	public static final String CLASS_NAME = DossierLog.class.getName();
}