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
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.constants.DossierTemplateTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.exception.DataConflictException;
import org.opencps.dossiermgt.exception.DuplicateTemplateNameException;
import org.opencps.dossiermgt.exception.DuplicateTemplateNoException;
import org.opencps.dossiermgt.exception.HasChildrenException;
import org.opencps.dossiermgt.exception.RequiredFileTemplateNoException;
import org.opencps.dossiermgt.exception.RequiredTemplateNameException;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.service.base.DossierTemplateLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier template local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierTemplateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see DossierTemplateLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil
 */
@ProviderType
public class DossierTemplateLocalServiceImpl extends DossierTemplateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil} to access the
	 * dossier template local service.
	 */

	public DossierTemplate getByTemplateNo(long groupId, String templateNo) throws PortalException {
		return dossierTemplatePersistence.fetchByG_DT_TPLNO(groupId, templateNo);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierTemplate updateDossierTemplate(long groupId, long dossierTemplateId, String templateName,
			String templateNo, String description, String newFormScript, ServiceContext context) throws PortalException {

		DossierTemplate dossierTemplate = null;

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());
		validateUpdate(groupId, dossierTemplateId, templateName, templateNo, description);

		if (dossierTemplateId == 0) {

			dossierTemplateId = counterLocalService.increment(DossierTemplate.class.getName());

			dossierTemplate = dossierTemplatePersistence.create(dossierTemplateId);

			dossierTemplate.setGroupId(groupId);
			dossierTemplate.setCompanyId(context.getCompanyId());
			dossierTemplate.setCreateDate(now);
			dossierTemplate.setModifiedDate(now);
			dossierTemplate.setUserId(userAction.getUserId());
			dossierTemplate.setUserName(userAction.getFullName());

			dossierTemplate.setTemplateName(templateName);
			dossierTemplate.setTemplateNo(templateNo);
			dossierTemplate.setDescription(description);
			dossierTemplate.setNewFormScript(newFormScript);

		} else {
			dossierTemplate = dossierTemplatePersistence.fetchByPrimaryKey(dossierTemplateId);

			dossierTemplate.setModifiedDate(now);
			dossierTemplate.setUserId(userAction.getUserId());
			dossierTemplate.setUserName(userAction.getFullName());

			if (Validator.isNotNull(templateName))
				dossierTemplate.setTemplateName(templateName);

			List<DossierPart> parts = dossierPartPersistence.findByTP_NO(groupId, dossierTemplate.getTemplateNo());

			for (DossierPart part : parts) {
				part.setTemplateNo(templateNo);
				dossierPartPersistence.update(part);
			}

			if (Validator.isNotNull(templateNo))
				dossierTemplate.setTemplateNo(templateNo);

			if (Validator.isNotNull(description))
				dossierTemplate.setDescription(description);
			if (Validator.isNotNull(newFormScript)) {
				dossierTemplate.setNewFormScript(newFormScript);
			}
		}

		dossierTemplatePersistence.update(dossierTemplate);

		return dossierTemplate;
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierTemplate removeDossierTemplate(long dossierTemplateId) throws PortalException {

		DossierTemplate dossierTemplate = dossierTemplatePersistence.fetchByPrimaryKey(dossierTemplateId);

		validateRemove(dossierTemplate.getGroupId(), dossierTemplateId);

		if (Validator.isNotNull(dossierTemplate)) {
			dossierTemplatePersistence.remove(dossierTemplate);
		}

		return dossierTemplate;
	}

	/**
	 * @author khoavu
	 * @param params
	 * @param sorts
	 * @param start
	 * @param end
	 * @param searchContext
	 * @return
	 * @throws ParseException
	 * @throws SearchException
	 */
	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierTemplate> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierTemplate.class);

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

				query.addFields(DossierTemplateTerm.TEMPLATE_NAME, DossierTemplateTerm.DESCRIPTION,
						DossierTemplateTerm.TEMPLATE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierTemplate> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierTemplate.class);

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

				query.addFields(DossierTemplateTerm.TEMPLATE_NAME, DossierTemplateTerm.DESCRIPTION,
						DossierTemplateTerm.TEMPLATE_NO);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	private void validateUpdate(long groupId, long dossierTemplateId, String templateName, String templateNo,
			String description) throws PortalException {

		if (Validator.isNull(templateName)) {
			throw new RequiredTemplateNameException("RequiredTemplateNameException");
		}

		if (Validator.isNull(templateNo)) {
			throw new RequiredFileTemplateNoException("RequiredFileTemplateNoException");
		}

		DossierTemplate dossierTemplate = null;

		if (dossierTemplateId != 0) {
			dossierTemplate = dossierTemplatePersistence.fetchByG_DT_NAME(groupId, templateName);

			if (Validator.isNotNull(dossierTemplate) && dossierTemplate.getPrimaryKey() != dossierTemplateId) {
				throw new DuplicateTemplateNameException("DuplicateTemplateNameException");
			}

			dossierTemplate = dossierTemplatePersistence.fetchByG_DT_TPLNO(groupId, templateNo);

			if (Validator.isNotNull(dossierTemplate) && dossierTemplate.getPrimaryKey() != dossierTemplateId) {
				throw new DuplicateTemplateNoException("DuplicateTemplateNoException");
			}

		} else {
			dossierTemplate = dossierTemplatePersistence.fetchByG_DT_NAME(groupId, templateName);

			if (Validator.isNotNull(dossierTemplate)) {
				throw new DuplicateTemplateNameException("DuplicateTemplateNameException");
			}

			dossierTemplate = dossierTemplatePersistence.fetchByG_DT_TPLNO(groupId, templateNo);

			if (Validator.isNotNull(dossierTemplate)) {
				throw new DuplicateTemplateNoException("DuplicateTemplateNoException");
			}
		}

	}

	// LamTV_ Process output DossierTemplate to DB
	@Indexable(type = IndexableType.REINDEX)
	public DossierTemplate updateDossierTemplateDB(long userId, long groupId, String templateNo, String templateName,
			String description, String newFormScript, ServiceContext serviceContext) throws PortalException {

		Date now = new Date();
		User userAction = userLocalService.getUser(userId);

		DossierTemplate dossierTemplate = dossierTemplatePersistence.fetchByG_DT_TPLNO(groupId, templateNo);
		if (dossierTemplate == null) {

			long dossierTemplateId = counterLocalService.increment(DossierTemplate.class.getName());
			dossierTemplate = dossierTemplatePersistence.create(dossierTemplateId);

			dossierTemplate.setGroupId(groupId);
			dossierTemplate.setCompanyId(serviceContext.getCompanyId());
			dossierTemplate.setCreateDate(now);
			dossierTemplate.setModifiedDate(now);
			dossierTemplate.setUserId(userAction.getUserId());
			dossierTemplate.setUserName(userAction.getFullName());

			dossierTemplate.setTemplateName(templateName);
			dossierTemplate.setTemplateNo(templateNo);
			dossierTemplate.setDescription(description);
			dossierTemplate.setNewFormScript(newFormScript);
		} else {
			dossierTemplate.setModifiedDate(now);
			dossierTemplate.setUserId(userAction.getUserId());
			dossierTemplate.setUserName(userAction.getFullName());

			if (Validator.isNotNull(templateName))
				dossierTemplate.setTemplateName(templateName);
			if (Validator.isNotNull(templateNo))
				dossierTemplate.setTemplateNo(templateNo);
			if (Validator.isNotNull(description))
				dossierTemplate.setDescription(description);
			if (Validator.isNotNull(newFormScript)) {
				dossierTemplate.setNewFormScript(newFormScript);
			}
		}

		return dossierTemplatePersistence.update(dossierTemplate);
	}

	private void validateRemove(long groupId, long dossierTemplateId) throws PortalException {

		DossierTemplate dossierTemplate = dossierTemplatePersistence.fetchByPrimaryKey(dossierTemplateId);

		List<DossierPart> ls = dossierPartPersistence.findByTP_NO(groupId, dossierTemplate.getTemplateNo());

		if (ls.size() != 0) {
			throw new HasChildrenException("DossierTemplateHasChildrenException");
		}

		// TODO add more logic in here

	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DossierTemplate adminProcessDelete(Long id) throws Exception {

		DossierTemplate object = dossierTemplatePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			int countDossier = dossierLocalService.countByG_NOTS_O_DTN(object.getGroupId(), new String[] { DossierTerm.DOSSIER_STATUS_DONE, DossierTerm.DOSSIER_STATUS_CANCELLED, DossierTerm.DOSSIER_STATUS_DENIED, DossierTerm.DOSSIER_STATUS_UNRESOLVED }, 1, object.getTemplateNo());
			if (countDossier > 0) {
				throw new DataConflictException("Have dossiers use this dossier template");
			}
			dossierTemplatePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierTemplate adminProcessData(JSONObject objectData) {

		DossierTemplate object = null;

		if (objectData.getLong("dossierTemplateId") > 0) {

			object = dossierTemplatePersistence.fetchByPrimaryKey(objectData.getLong("dossierTemplateId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DossierTemplate.class.getName());

			object = dossierTemplatePersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setTemplateName(objectData.getString("templateName"));
		object.setDescription(objectData.getString("description"));
		object.setTemplateNo(objectData.getString("templateNo"));
		object.setNewFormScript(objectData.getString("newFormScript"));
		
		dossierTemplatePersistence.update(object);

		return object;
	}

	public List<DossierTemplate> findByG(long groupId) {
		return dossierTemplatePersistence.findByG(groupId);			
	}
	public static final String CLASS_NAME = DossierTemplate.class.getName();

}