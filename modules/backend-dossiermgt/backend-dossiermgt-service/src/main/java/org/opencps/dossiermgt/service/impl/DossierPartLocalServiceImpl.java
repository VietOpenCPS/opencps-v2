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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.exception.HasExsistException;
import org.opencps.dossiermgt.exception.NoSuchDossierPartException;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.base.DossierPartLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier part local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierPartLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierPartLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierPartLocalServiceUtil
 */
@ProviderType
public class DossierPartLocalServiceImpl extends DossierPartLocalServiceBaseImpl {
	private static Log _log = LogFactoryUtil.getLog(DossierPartLocalServiceImpl.class);
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierPartLocalServiceUtil} to access the
	 * dossier part local service.
	 */

	public DossierPart getByFileTemplateNo(long groupId, String fileTemplateNo) {
		return dossierPartPersistence.fetchByGID_FTN(groupId, fileTemplateNo);
	}

	/**
	 * @param groupId
	 * @param templateNo
	 * @return
	 * @throws PortalException
	 */
	public List<DossierPart> getByTemplateNo(long groupId, String templateNo) throws PortalException {
		return dossierPartPersistence.findByTP_NO(groupId, templateNo);
	}

	/**
	 * @param dossierPartId
	 * @param contentType
	 * @return
	 * @throws PortalException
	 */
	public String getContent(long dossierPartId, int contentType) throws PortalException {

		DossierPart object = dossierPartPersistence.fetchByPrimaryKey(dossierPartId);

		String content = StringPool.BLANK;

		if (contentType == 1) {
			content = object.getFormScript();
		}

		if (contentType == 2) {
			content = object.getFormReport();
		}

		if (contentType == 3) {
			content = object.getSampleData();
		}

		return content;
	}

	/**
	 * @param dossierPartId
	 * @param contentType
	 * @param input
	 * @param context
	 * @return
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.REINDEX)
	public String updateContent(long dossierPartId, int contentType, String input, ServiceContext context)
			throws PortalException {

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		DossierPart object = dossierPartPersistence.fetchByPrimaryKey(dossierPartId);

		// Add audit fields
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		if (contentType == 1) {
			object.setFormScript(input);
		}

		if (contentType == 2) {
			object.setFormReport(input);
		}

		if (contentType == 3) {
			object.setSampleData(input);
		}

		dossierPartPersistence.update(object);

		return input;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierPart updateDossierPart(long groupId, long dossierPartId, String templateNo, String partNo,
			String partName, String partTip, int partType, boolean multiple, String formScript, String formReport,
			String sampleData, boolean required, String fileTemplateNo, boolean eSign, ServiceContext context)
			throws PortalException {

		validateUpdate(groupId, dossierPartId, templateNo, partNo, partName, partTip, partType, templateNo);

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		DossierPart object = null;

		if (dossierPartId == 0) {

			dossierPartId = counterLocalService.increment(DossierPart.class.getName());

			object = dossierPartPersistence.create(dossierPartId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Add other fields

			object.setTemplateNo(templateNo);
			object.setPartNo(partNo);
			object.setPartName(partName);
			object.setPartName(partName);
			object.setPartTip(partTip);
			object.setPartType(partType);
			object.setMultiple(multiple);
			object.setFormScript(formScript);
			object.setFormReport(formReport);
			object.setSampleData(sampleData);
			object.setRequired(required);
			object.setFileTemplateNo(fileTemplateNo);
			object.setESign(eSign);

		} else {
			object = dossierPartPersistence.fetchByPrimaryKey(dossierPartId);

			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Update other fields

			object.setTemplateNo(templateNo);
			object.setPartNo(partNo);
			object.setPartName(partName);
			object.setPartTip(partTip);
			object.setPartType(partType);
			object.setMultiple(multiple);
			object.setFormScript(formScript);
			object.setFormReport(formReport);
			object.setSampleData(sampleData);
			object.setRequired(required);
			object.setFileTemplateNo(fileTemplateNo);
			object.setESign(eSign);
		}

		return dossierPartPersistence.update(object);

	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierPart updateDossierPart(long groupId, long dossierPartId, String templateNo, String partNo,
			String partName, String partTip, int partType, boolean multiple, String formScript, String formReport,
			String sampleData, boolean required, String fileTemplateNo, boolean eSign, String deliverableType,
			int deliverableAction, ServiceContext context) throws PortalException {

		validateUpdate(groupId, dossierPartId, templateNo, partNo, partName, partTip, partType, templateNo);

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		DossierPart object = null;

		if (dossierPartId == 0) {

			dossierPartId = counterLocalService.increment(DossierPart.class.getName());

			object = dossierPartPersistence.create(dossierPartId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Add other fields

			object.setTemplateNo(templateNo);
			object.setPartNo(partNo);
			object.setPartName(partName);
			object.setPartName(partName);
			object.setPartTip(partTip);
			object.setPartType(partType);
			object.setMultiple(multiple);
			object.setFormScript(formScript);
			object.setFormReport(formReport);
			object.setSampleData(sampleData);
			object.setRequired(required);
			object.setFileTemplateNo(fileTemplateNo);
			object.setESign(eSign);
			object.setDeliverableType(deliverableType);
			object.setDeliverableAction(deliverableAction);

		} else {
			object = dossierPartPersistence.fetchByPrimaryKey(dossierPartId);

			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Update other fields

			object.setTemplateNo(templateNo);
			object.setPartNo(partNo);
			object.setPartName(partName);
			object.setPartTip(partTip);
			object.setPartType(partType);
			object.setMultiple(multiple);
			object.setFormScript(formScript);
			object.setFormReport(formReport);
			object.setSampleData(sampleData);
			object.setRequired(required);
			object.setFileTemplateNo(fileTemplateNo);
			object.setESign(eSign);
			object.setDeliverableType(deliverableType);
			object.setDeliverableAction(deliverableAction);

		}

		return dossierPartPersistence.update(object);

	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierPart> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierPart.class);

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

				query.addFields(DossierPartTerm.PART_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		// String templateNo =
		// GetterUtil.getString(params.get(DossierPartTerm.TEMPLATE_NO));
		String partType = GetterUtil.getString(params.get(DossierPartTerm.PART_TYPE));
		String multiple = GetterUtil.getString(params.get(DossierPartTerm.MULTIPLE));
		String required = GetterUtil.getString(params.get(DossierPartTerm.REQUIRED));
		String eSign = GetterUtil.getString(params.get(DossierPartTerm.ESIGN));
		long templateId = GetterUtil.getLong(params.get(DossierPartTerm.TEMPLATE_ID));

		/*
		 * if (Validator.isNotNull(templateNo)) { MultiMatchQuery query = new
		 * MultiMatchQuery(templateNo);
		 * 
		 * query.addFields(DossierPartTerm.TEMPLATE_NO);
		 * 
		 * booleanQuery.add(query, BooleanClauseOccur.MUST); }
		 * 
		 */
		if (Validator.isNotNull(templateId)) {
			MultiMatchQuery query = new MultiMatchQuery(Long.toString(templateId));

			query.addFields(DossierPartTerm.TEMPLATE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(partType)) {
			MultiMatchQuery query = new MultiMatchQuery(partType);

			query.addFields(DossierPartTerm.PART_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(multiple)) {
			MultiMatchQuery query = new MultiMatchQuery(multiple);

			query.addFields(DossierPartTerm.MULTIPLE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(required)) {
			MultiMatchQuery query = new MultiMatchQuery(required);

			query.addFields(DossierPartTerm.REQUIRED);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(eSign)) {
			MultiMatchQuery query = new MultiMatchQuery(eSign);

			query.addFields(DossierPartTerm.ESIGN);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<DossierPart> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierPart.class);

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

				query.addFields(DossierPartTerm.PART_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		// String templateNo =
		// GetterUtil.getString(params.get(DossierPartTerm.TEMPLATE_NO));
		String partType = GetterUtil.getString(params.get(DossierPartTerm.PART_TYPE));
		String multiple = GetterUtil.getString(params.get(DossierPartTerm.MULTIPLE));
		String required = GetterUtil.getString(params.get(DossierPartTerm.REQUIRED));
		String eSign = GetterUtil.getString(params.get(DossierPartTerm.ESIGN));

		long templateId = GetterUtil.getLong(params.get(DossierPartTerm.TEMPLATE_ID));

		/*
		 * if (Validator.isNotNull(templateNo)) { MultiMatchQuery query = new
		 * MultiMatchQuery(templateNo);
		 * 
		 * query.addFields(DossierPartTerm.TEMPLATE_NO);
		 * 
		 * booleanQuery.add(query, BooleanClauseOccur.MUST); }
		 * 
		 */
		if (Validator.isNotNull(templateId)) {
			MultiMatchQuery query = new MultiMatchQuery(Long.toString(templateId));

			query.addFields(DossierPartTerm.TEMPLATE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(partType)) {
			MultiMatchQuery query = new MultiMatchQuery(partType);

			query.addFields(DossierPartTerm.PART_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(multiple)) {
			MultiMatchQuery query = new MultiMatchQuery(multiple);

			query.addFields(DossierPartTerm.MULTIPLE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(required)) {
			MultiMatchQuery query = new MultiMatchQuery(required);

			query.addFields(DossierPartTerm.REQUIRED);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(eSign)) {
			MultiMatchQuery query = new MultiMatchQuery(eSign);

			query.addFields(DossierPartTerm.ESIGN);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierPart removeDossierPart(long dossierPartId) throws PortalException {
		DossierPart dossierPart = dossierPartPersistence.findByPrimaryKey(dossierPartId);

		validateRemove(dossierPartId);

		dossierPartPersistence.remove(dossierPart);

		return dossierPart;
	}

	public DossierPart fetchByTemplatePartNo(long groupId, String templateNo, String partNo) throws PortalException {
		return dossierPartPersistence.fetchByTP_NO_PART(groupId, templateNo, partNo);
	}

	private void validateRemove(long dossierPartId) throws PortalException {
		// TODO add logic here
	}

	private void validateUpdate(long groupId, long dossierPartId, String templateNo, String partNo, String partName,
			String partTip, int partType, String fileTemplateNo) throws PortalException {

		if (dossierPartId == 0) {
			DossierPart dossierPart = dossierPartPersistence.fetchByTP_NO_PART(groupId, templateNo, partNo);

			if (Validator.isNotNull(dossierPart)) {
				throw new HasExsistException("DubplicateTemplatePartNoHasExsistException");
			}
		} else {
			DossierPart dossierPart = dossierPartPersistence.fetchByTP_NO_PART(groupId, templateNo, partNo);

			if (Validator.isNotNull(dossierPart) && dossierPart.getPrimaryKey() != dossierPartId) {
				throw new HasExsistException("DubplicateTemplatePartNoHasExsistException");
			}
		}

		// TODO add more logic here
	}

	public DossierPart getByPartTypeEsign(String templateNo, String partNo, int partType, boolean eSign) {
		try {
			return dossierPartPersistence.findByTP_NO_PART_ESIGN(templateNo, partNo, partType, eSign);
		} catch (NoSuchDossierPartException e) {
			 _log.debug(e);
			return null;
		}
	}

	// LamTV_ Process output DossierPart to DB
	@Indexable(type = IndexableType.REINDEX)
	public DossierPart updateDossierPartDB(long userId, long groupId, String templateNo, String partNo, String partName,
			String partTip, Integer partType, boolean multiple, String formScript, String formReport, boolean required,
			boolean esign, String fileTemplateNo, String deliverableType, Integer deliverableAction, boolean eForm,
			String sampleData, Integer fileMark, ServiceContext serviceContext) throws PortalException {

		Date now = new Date();
		User userAction = userLocalService.getUser(userId);

		long dossierPartId = counterLocalService.increment(CLASS_NAME);
		DossierPart object = dossierPartPersistence.create(dossierPartId);

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		// Add other fields
		object.setTemplateNo(templateNo);
		object.setPartNo(partNo);
		object.setPartName(partName);
		object.setPartName(partName);
		object.setPartTip(partTip);
		object.setPartType(partType);
		object.setMultiple(multiple);
		object.setFormScript(formScript);
		object.setFormReport(formReport);
		object.setSampleData(sampleData);
		object.setRequired(required);
		object.setFileTemplateNo(fileTemplateNo);
		object.setESign(esign);
		object.setDeliverableType(deliverableType);
		object.setDeliverableAction(deliverableAction);
		object.setSampleData(sampleData);
		object.setEForm(eForm);
		object.setFileMark(fileMark);

		return dossierPartPersistence.update(object);
	}

	// LamTV_Get dossierPart by partNo and dossierId
	public DossierPart getByTempAndPartNo(long groupId, String templateNo, String partNo) {
		return dossierPartPersistence.fetchByTP_NO_PART(groupId, templateNo, partNo);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DossierPart adminProcessDelete(Long id) {

		DossierPart object = dossierPartPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dossierPartPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierPart adminProcessData(JSONObject objectData) {

		DossierPart object = null;

		if (objectData.getLong("dossierPartId") > 0) {

			object = dossierPartPersistence.fetchByPrimaryKey(objectData.getLong("dossierPartId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DossierPart.class.getName());

			object = dossierPartPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setTemplateNo(objectData.getString("templateNo"));
		object.setPartNo(objectData.getString("partNo"));
		object.setPartName(objectData.getString("partName"));
		object.setPartTip(objectData.getString("partTip"));
		object.setPartType(objectData.getInt("partType"));
		object.setMultiple(objectData.getBoolean("multiple"));
		object.setFormScript(objectData.getString("formScript"));
		object.setFormReport(objectData.getString("formReport"));
		object.setSampleData(objectData.getString("sampleData"));
		object.setRequired(objectData.getBoolean("required"));
		object.setFileTemplateNo(objectData.getString("fileTemplateNo"));
		object.setESign(objectData.getBoolean("ESign"));
		object.setDeliverableType(objectData.getString("deliverableType"));
		object.setDeliverableAction(objectData.getInt("deliverableAction"));
		object.setEForm(objectData.getBoolean("EForm"));
		object.setFileMark(objectData.getInt("fileMark"));

		dossierPartPersistence.update(object);

		return object;
	}
	public DossierPart getByTempAndFileTempNo(long groupId, String templateNo, String fileTemplateNo) {
		return dossierPartPersistence.fetchByGID_TN_FTN(groupId, templateNo, fileTemplateNo);
	}

	public List<DossierPart> findByG(long groupId) {
		return dossierPartPersistence.findByG(groupId);
	}
	public static final String CLASS_NAME = DossierPart.class.getName();

}