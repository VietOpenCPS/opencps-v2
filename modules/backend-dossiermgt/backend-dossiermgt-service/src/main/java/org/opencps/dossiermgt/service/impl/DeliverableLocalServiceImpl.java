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
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
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
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.base.DeliverableLocalServiceBaseImpl;
import org.opencps.usermgt.constants.ApplicantTerm;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the deliverable local service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DeliverableLocalService} interface. <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM. </p>
 *
 * @author huymq
 * @see DeliverableLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DeliverableLocalServiceUtil
 */
@ProviderType
public class DeliverableLocalServiceImpl
	extends DeliverableLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
	 * {@link org.opencps.dossiermgt.service.DeliverableLocalServiceUtil} to
	 * access the deliverable local service.
	 */

	public Deliverable getByCode(String deliverableCode) {

		return deliverablePersistence.fetchByFB_DCODE(deliverableCode);
	}

	public Deliverable fetchByGID_DID(long groupId, long dossierId) {

		return deliverablePersistence.fetchByF_GID_DID(groupId, dossierId);
	}

	public List<Deliverable> getListDeliverable(
		int deliverableState, String govAgencyCode, String deliverableType,
		String applicant) {

		List<Deliverable> listDeliverable = deliverablePersistence.findByG_ID(
			deliverableState, govAgencyCode, deliverableType, applicant);
		return listDeliverable;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable addDeliverable(
		long groupId, String deliverableType, String deliverableCode,
		String govAgencyCode, String govAgencyName, String applicationIdNo,
		String applicationName, String subject, String issueDate,
		String expireDate, String revalidate, String deliverableState,
		ServiceContext serviceContext) {

		long userId = serviceContext.getUserId();

		Date now = new Date();

		long deliverableId =
			counterLocalService.increment(Deliverable.class.getName());

		Deliverable object = deliverablePersistence.create(deliverableId);

		DeliverableType dlvType =
			deliverableTypePersistence.fetchByG_DLT(groupId, deliverableType);

		/// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		if (Validator.isNotNull(dlvType)) {
			object.setDeliverableName(dlvType.getTypeName());
		}
		object.setDeliverableId(deliverableId);
		object.setDeliverableType(deliverableType);
		object.setDeliverableCode(deliverableCode);
		object.setGovAgencyCode(govAgencyCode);
		object.setGovAgencyName(govAgencyName);
		object.setApplicantIdNo(applicationIdNo);
		object.setApplicantName(applicationName);
		object.setSubject(subject);
		if (Validator.isNotNull(issueDate)) {
			object.setIssueDate(
				APIDateTimeUtils.convertStringToDate(
					issueDate, APIDateTimeUtils._NORMAL_DATE));
		}
		else {
			object.setIssueDate(now);
		}
		object.setExpireDate(
			APIDateTimeUtils.convertStringToDate(
				expireDate, APIDateTimeUtils._NORMAL_DATE));
		object.setRevalidate(
			APIDateTimeUtils.convertStringToDate(
				revalidate, APIDateTimeUtils._NORMAL_DATE));
		object.setDeliverableState(Integer.valueOf(deliverableState));

		return deliverablePersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable addDeliverable(
		long groupId, String deliverableType, String deliverableCode,
		String govAgencyCode, String govAgencyName, String applicationIdNo,
		String applicationName, String subject, String issueDate,
		String expireDate, String revalidate, String deliverableState,
		long dossierId, long fileEntryId, ServiceContext serviceContext) {

		long userId = serviceContext.getUserId();

		Date now = new Date();

		long deliverableId =
			counterLocalService.increment(Deliverable.class.getName());

		Deliverable object = deliverablePersistence.create(deliverableId);

		DeliverableType dlvType =
			deliverableTypePersistence.fetchByG_DLT(groupId, deliverableType);

		/// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		if (Validator.isNotNull(dlvType)) {
			object.setDeliverableName(dlvType.getTypeName());
		}
		object.setDeliverableId(deliverableId);
		object.setDeliverableType(deliverableType);
		object.setDeliverableCode(deliverableCode);
		object.setGovAgencyCode(govAgencyCode);
		object.setGovAgencyName(govAgencyName);
		object.setApplicantIdNo(applicationIdNo);
		object.setApplicantName(applicationName);
		object.setSubject(subject);
		object.setDossierId(dossierId);
		object.setFileEntryId(fileEntryId);
		if (Validator.isNotNull(issueDate)) {
			object.setIssueDate(
				APIDateTimeUtils.convertStringToDate(
					issueDate, APIDateTimeUtils._NORMAL_DATE));
		}
		else {
			object.setIssueDate(now);
		}
		object.setExpireDate(
			APIDateTimeUtils.convertStringToDate(
				expireDate, APIDateTimeUtils._NORMAL_DATE));
		object.setRevalidate(
			APIDateTimeUtils.convertStringToDate(
				revalidate, APIDateTimeUtils._NORMAL_DATE));
		object.setDeliverableState(
			Validator.isNotNull(deliverableState)
				? Integer.valueOf(deliverableState) : 0);

		return deliverablePersistence.update(object);
	}

	//
	@Indexable(type = IndexableType.REINDEX)
	public Deliverable addDeliverableSign(
		long groupId, String deliverableType, String deliverableName,
		String deliverableCode, String govAgencyCode, String govAgencyName,
		String applicationIdNo, String applicationName, String subject,
		String issueDate, String expireDate, String revalidate,
		String deliverableState, long dossierId, long fileEntryId,
		long formScriptFileId, long formReportFileId, String formData,
		String fileAttachs, ServiceContext serviceContext) {

		long userId = serviceContext.getUserId();

		Date now = new Date();

		long deliverableId =
			counterLocalService.increment(Deliverable.class.getName());

		Deliverable object = deliverablePersistence.create(deliverableId);

		/// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		object.setDeliverableName(deliverableName);
		// object.setDeliverableId(deliverableId);
		object.setDeliverableType(deliverableType);
		object.setDeliverableCode(deliverableCode);
		object.setGovAgencyCode(govAgencyCode);
		object.setGovAgencyName(govAgencyName);
		object.setApplicantIdNo(applicationIdNo);
		object.setApplicantName(applicationName);
		object.setSubject(subject);
		object.setDossierId(dossierId);
		object.setFileEntryId(fileEntryId);
		if (Validator.isNotNull(issueDate)) {
			object.setIssueDate(
				APIDateTimeUtils.convertStringToDate(
					issueDate, APIDateTimeUtils._NORMAL_DATE));
		}
		else {
			object.setIssueDate(now);
		}
		object.setExpireDate(
			APIDateTimeUtils.convertStringToDate(
				expireDate, APIDateTimeUtils._NORMAL_DATE));
		object.setRevalidate(
			APIDateTimeUtils.convertStringToDate(
				revalidate, APIDateTimeUtils._NORMAL_DATE));
		object.setFormScriptFileId(formScriptFileId);
		object.setFormReportFileId(formReportFileId);
		object.setDeliverableState(
			Validator.isNotNull(deliverableState)
				? Integer.valueOf(deliverableState) : 0);
		object.setFormData(formData);
		object.setFileAttachs(fileAttachs);

		return deliverablePersistence.update(object);
	}

	public Hits searchLucene(
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		SearchContext searchContext)
		throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);

		Indexer<Deliverable> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);

		// Set value header
		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute(DeliverableTerm.PAGINATION_TYPE, DeliverableTerm.REGULAR);
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		}
		else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		// LamTV: Process search LIKE
		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] {
				DeliverableTerm.DELIVERABLE_TYPE,
				DeliverableTerm.DELIVERABLE_NAME,
				DeliverableTerm.GOV_AGENCY_NAME, DeliverableTerm.APPLICANT_NAME,
				DeliverableTerm.DELIVERABLE_CODE_SEARCH
			};

			// query.addTerms(subQuerieArr, keywords.toLowerCase(), true);
			// booleanQuery.add(query, BooleanClauseOccur.MUST);
			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(
						fieldSearch,
						StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}

			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String state =
			GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_STATE));
		String agency =
			GetterUtil.getString(params.get(DeliverableTerm.GOV_AGENCY_CODE));
		String type =
			GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_TYPE));
		String applicant =
			GetterUtil.getString(params.get(DeliverableTerm.APPLICANT_ID_NO));
		String deliverableId = String.valueOf(
			GetterUtil.getLong(params.get(DeliverableTerm.DELIVERABLE_ID)));
		String owner = GetterUtil.getString(params.get(DossierTerm.OWNER));
		long userId = GetterUtil.getLong(params.get(DossierTerm.USER_ID));
		_log.info("owner:" + owner);
		_log.info("userId:" + userId);

		if (Validator.isNotNull(state)) {
			MultiMatchQuery query = new MultiMatchQuery(state);

			query.addFields(DeliverableTerm.DELIVERABLE_STATE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(DeliverableTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(type)) {
			MultiMatchQuery query = new MultiMatchQuery(type);

			query.addFields(DeliverableTerm.DELIVERABLE_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(applicant)) {
			MultiMatchQuery query = new MultiMatchQuery(applicant);

			query.addFields(DeliverableTerm.APPLICANT_ID_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(deliverableId) &&
			!String.valueOf(0).equalsIgnoreCase(deliverableId)) {
			MultiMatchQuery query = new MultiMatchQuery(deliverableId);

			query.addFields(DeliverableTerm.DELIVERABLE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(owner) &&
			Boolean.parseBoolean(owner.toLowerCase()) && userId > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));

			query.addField(DossierTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(
		LinkedHashMap<String, Object> params, SearchContext searchContext)
		throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);

		Indexer<Deliverable> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);

		// Set value header
		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute(DeliverableTerm.PAGINATION_TYPE, DeliverableTerm.REGULAR);
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		}
		else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		// LamTV: Process search LIKE
		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] {
				DeliverableTerm.DELIVERABLE_TYPE,
				DeliverableTerm.DELIVERABLE_NAME,
				DeliverableTerm.GOV_AGENCY_NAME, DeliverableTerm.APPLICANT_NAME
			};

			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(
						fieldSearch,
						StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}

			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String state =
			GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_STATE));
		String agency =
			GetterUtil.getString(params.get(DeliverableTerm.GOV_AGENCY_CODE));
		String type =
			GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_TYPE));
		String applicant =
			GetterUtil.getString(params.get(DeliverableTerm.APPLICANT_ID_NO));
		String owner = GetterUtil.getString(params.get(DossierTerm.OWNER));
		long userId = GetterUtil.getLong(params.get(DossierTerm.USER_ID));
		_log.info("owner:" + owner);
		_log.info("userId:" + userId);

		if (Validator.isNotNull(state)) {
			MultiMatchQuery query = new MultiMatchQuery(state);

			query.addFields(DeliverableTerm.DELIVERABLE_STATE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(DeliverableTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(type)) {
			MultiMatchQuery query = new MultiMatchQuery(type);

			query.addFields(DeliverableTerm.DELIVERABLE_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(applicant)) {
			MultiMatchQuery query = new MultiMatchQuery(applicant);

			query.addFields(DeliverableTerm.APPLICANT_ID_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(owner) &&
			Boolean.parseBoolean(owner.toLowerCase()) && userId > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));

			query.addField(DossierTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = Deliverable.class.getName();

	public Deliverable getDeliverableDetail(long id)
		throws NoSuchDeliverableException {

		return deliverablePersistence.fetchByDID(id);
	}

	public Deliverable getDetailById(long id) {

		return deliverablePersistence.fetchByDID(id);
	}

	// 4
	@Indexable(type = IndexableType.REINDEX)
	public Deliverable updateDeliverable(
		long groupId, long id, String subject, String issueDate,
		String expireDate, String revalidate, String deliverableState,
		String deliverableAction, ServiceContext serviceContext) {

		long userId = serviceContext.getUserId();

		Date now = new Date();

		Deliverable object = null;
		try {
			object = deliverablePersistence.findByG_DID(groupId, id);

			/// Add audit fields
			object.setGroupId(groupId);
			object.setModifiedDate(now);
			object.setUserId(userId);

			// Add other fields
			object.setSubject(subject);
			object.setIssueDate(
				APIDateTimeUtils.convertStringToDate(
					issueDate, APIDateTimeUtils._NORMAL_PARTTERN));
			object.setExpireDate(
				APIDateTimeUtils.convertStringToDate(
					expireDate, APIDateTimeUtils._NORMAL_PARTTERN));
			object.setRevalidate(
				APIDateTimeUtils.convertStringToDate(
					revalidate, APIDateTimeUtils._NORMAL_PARTTERN));
			object.setDeliverableState(Integer.valueOf(deliverableState));
		}
		catch (Exception e) {
			_log.error(e);
		}
		return deliverablePersistence.update(object);
	}

	// 5
	@Indexable(type = IndexableType.DELETE)
	public Deliverable deleteDeliverable(long id)
		throws PortalException {

		Deliverable deliverable = deliverablePersistence.findByPrimaryKey(id);
		if (deliverable != null) {
			return deliverablePersistence.remove(deliverable);
		}

		return null;
	}

	// 7
	@Indexable(type = IndexableType.REINDEX)
	public Deliverable updateFormData(
		long groupId, long id, String formData, ServiceContext serviceContext)
		throws NoSuchDeliverableException {

		long userId = serviceContext.getUserId();

		Date now = new Date();

		Deliverable object = null;
		object = deliverablePersistence.findByG_DID(groupId, id);

		/// Add audit fields
		object.setGroupId(groupId);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		object.setFormData(formData);
		//
		String result = StringPool.BLANK;
		if (object.getFormReportFileId() > 0) {
			InputStream is = null;

			try {
				DLFileEntry dlFileEntry =
					DLFileEntryLocalServiceUtil.getFileEntry(
						object.getFormReportFileId());

				is = dlFileEntry.getContentStream();

				result = IOUtils.toString(is, StandardCharsets.UTF_8);

			}
			catch (Exception e) {
				_log.debug(e);
				result = StringPool.BLANK;
			}
			finally {
				if (is != null) {
					try {
						is.close();
					}
					catch (IOException e) {
						_log.debug(e);
					}
				}
			}
		}
		// Process update deliverable file Id
		Message message = new Message();
		// _log.info("Document script: " + dt.getDocumentScript());
		JSONObject msgData = JSONFactoryUtil.createJSONObject();
		msgData.put(ConstantUtils.CLASS_NAME, Deliverable.class.getName());
		msgData.put(Field.CLASS_PK, object.getDeliverableId());
		msgData.put(ConstantUtils.JRMX_TEMPLATE, result);
		msgData.put(ConstantUtils.FORM_DATA, formData);
		msgData.put(Field.USER_ID, userId);

		message.put(ConstantUtils.MSG_ENG, msgData);
		MessageBusUtil.sendMessage(ConstantUtils.JASPER_DESTINATION, message);

		return deliverablePersistence.update(object);
	}

	public Deliverable getByCodeAndState(String deliverableCode, int state) {

		return deliverablePersistence.fetchByFB_DCODE_STATE(
			deliverableCode, state);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public Deliverable adminProcessDelete(Long id) {

		Deliverable object = deliverablePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		}
		else {
			deliverablePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable adminProcessData(JSONObject objectData) {

		System.out.println("=========objectData==========" + objectData);

		Deliverable object = null;

		long deliverableId = objectData.getLong(DeliverableTerm.DELIVERABLE_ID);
		long groupId = objectData.getLong(Field.GROUP_ID);
		boolean flagAttach = false;
		if (deliverableId > 0) {

			object = deliverablePersistence.fetchByPrimaryKey(deliverableId);
			//
			try {
				JSONObject jsonDeli =
					JSONFactoryUtil.createJSONObject(object.getFormData());
				if (jsonDeli != null && jsonDeli.has(DeliverableTerm.FILE_ATTACH)) {
					flagAttach = jsonDeli.getBoolean(DeliverableTerm.FILE_ATTACH);
				}
			}
			catch (JSONException e) {
				_log.debug(e);
			}

		}
		else {

			deliverableId =
				CounterLocalServiceUtil.increment(Deliverable.class.getName());
			object = deliverablePersistence.create(deliverableId);

			object.setGroupId(groupId);
			object.setCompanyId(objectData.getLong(Field.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setModifiedDate(new Date());
		object.setUserId(objectData.getLong(Field.USER_ID));
		object.setUserName(objectData.getString(Field.USER_NAME));

		//
		String deliverableType = objectData.getString(DeliverableTerm.DELIVERABLE_TYPE);
		object.setDeliverableType(deliverableType);
		object.setGovAgencyCode(objectData.getString(DossierTerm.GOV_AGENCY_CODE));
		//
		String govAgencyName = objectData.getString(DossierTerm.GOV_AGENCY_NAME);
		if (Validator.isNull(govAgencyName)) {
			govAgencyName = getDictItemName(
				groupId, ReadFilePropertiesUtils.get(ConstantUtils.GOVERNMENT_AGENCY),
				objectData.getString(DossierTerm.GOV_AGENCY_CODE));
		}

		object.setGovAgencyName(govAgencyName);
		// applicant
		JSONObject jsonData = null;
		try {
			jsonData = JSONFactoryUtil.createJSONObject(
				objectData.getString(ConstantUtils.FORM_DATA));

			String deliverableCode = jsonData.getString(DeliverableTerm.DELIVERABLE_CODE);
			if (Validator.isNotNull(deliverableCode)) {
				object.setDeliverableCode(deliverableCode);
			}
			//
			String deliverableName = jsonData.getString(DeliverableTerm.DELIVERABLE_NAME);
			if (Validator.isNotNull(deliverableName)) {
				object.setDeliverableName(deliverableName);
			}
			else {
				DeliverableType deliType =
					deliverableTypePersistence.fetchByG_DLT(
						groupId, deliverableType);
				if (deliType != null) {
					object.setDeliverableName(deliType.getTypeName());
				}
			}
			//
			String applicantName = jsonData.getString(ApplicantTerm.APPLICANTNAME);
			if (Validator.isNotNull(applicantName)) {
				object.setApplicantName(applicantName);
			}
			//
			String applicantIdNo = jsonData.getString(ApplicantTerm.APPLICANTIDNO);
			if (Validator.isNotNull(applicantIdNo)) {
				object.setApplicantIdNo(applicantIdNo);
			}
			//
			String subject = jsonData.getString(DeliverableTerm.SUBJECT);
			if (Validator.isNotNull(subject)) {
				object.setSubject(subject);
			}
			//
			String deliverableState = jsonData.getString(DeliverableTerm.DELIVERABLE_STATE);
			if (Validator.isNotNull(deliverableState)) {
				object.setDeliverableState(Integer.valueOf(deliverableState));
			}
			else {
				object.setDeliverableState(1);
			}
			//
			SimpleDateFormat sdf =
				new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE);
			String strExpireDate = jsonData.getString(DeliverableTerm.EXPIRE_DATE);
			String strIssueDate = jsonData.getString(DeliverableTerm.ISSUE_DATE);
			String strRevalidate = jsonData.getString(DeliverableTerm.REVALIDATE);
			if (Validator.isNotNull(strExpireDate)) {
				Date expireDate = sdf.parse(strExpireDate);
				if (expireDate != null) {
					object.setExpireDate(expireDate);
				}
			}
			//
			if (Validator.isNotNull(strIssueDate)) {
				Date issueDate = sdf.parse(strIssueDate);
				if (issueDate != null) {
					object.setIssueDate(issueDate);
				}
			}
			//
			if (Validator.isNotNull(strRevalidate)) {
				Date revaliDate = sdf.parse(strRevalidate);
				if (revaliDate != null) {
					object.setRevalidate(revaliDate);
				}
			}

		}
		catch (JSONException e1) {
			_log.debug(e1);
		}
		catch (java.text.ParseException e2) {
			_log.debug(e2);
		}

		if (jsonData != null) {
			jsonData.put(DeliverableTerm.FILE_ATTACH, flagAttach);
			object.setFormData(jsonData.toJSONString());
		}
		else {
			object.setFormData(StringPool.BLANK);
		}

		//
		long formScriptFileId = 0;
		long formReportFileId = 0;
		if (Validator.isNotNull(deliverableType)) {
			DeliverableType deliType =
				DeliverableTypeLocalServiceUtil.getByCode(
					groupId, deliverableType);
			if (deliType != null) {
				formScriptFileId = deliType.getFormScriptFileId();
				formReportFileId = deliType.getFormReportFileId();
			}
		}
		object.setFormScriptFileId(formScriptFileId);
		object.setFormReportFileId(formReportFileId);

		object.setFormScript(objectData.getString(DeliverableTerm.FORM_SCRIPT));
		object.setFormReport(objectData.getString(DeliverableTerm.FORM_REPORT));

		// new field to save QD
		object.setFormReport(objectData.getString(DeliverableTerm.FILE_ATTACHS));

		object = deliverablePersistence.update(object);

		if (!flagAttach) {
			String result = StringPool.BLANK;
			if (formReportFileId > 0) {
				InputStream is = null;

				try {
					DLFileEntry dlFileEntry =
						DLFileEntryLocalServiceUtil.getFileEntry(
							formReportFileId);

					is = dlFileEntry.getContentStream();

					result = IOUtils.toString(is, StandardCharsets.UTF_8);

				}
				catch (Exception e) {
					_log.debug(e);
					result = StringPool.BLANK;
				}
				finally {
					if (is != null) {
						try {
							is.close();
						}
						catch (IOException e) {
							_log.debug(e);
						}
					}
				}
			}
			// Process update deliverable file Id
			Message message = new Message();
			// _log.info("Document script: " + dt.getDocumentScript());
			JSONObject msgData = JSONFactoryUtil.createJSONObject();
			msgData.put(ConstantUtils.CLASS_NAME, Deliverable.class.getName());
			msgData.put(Field.CLASS_PK, object.getDeliverableId());
			msgData.put(ConstantUtils.JRMX_TEMPLATE, result);
			msgData.put(
					ConstantUtils.FORM_DATA,
				jsonData != null ? jsonData.toJSONString() : StringPool.BLANK);
			msgData.put(Field.USER_ID, objectData.getLong(Field.USER_ID));

			message.put(ConstantUtils.MSG_ENG, msgData);
			MessageBusUtil.sendMessage(
					ConstantUtils.JASPER_DESTINATION, message);
		}

		return object;
	}

	public Deliverable getByF_GID_DCODE(long groupId, String deliverableCode) {

		return deliverablePersistence.fetchByF_GID_DCODE(
			groupId, deliverableCode);
	}

	protected String getDictItemName(
		long groupId, String collectionCode, String itemCode) {

		DictCollection dc =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				collectionCode, groupId);

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(
				itemCode, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				return it.getItemName();
			}
			else {
				return StringPool.BLANK;
			}
		}
		else {
			return StringPool.BLANK;
		}

	}

	private static Log _log =
		LogFactoryUtil.getLog(DeliverableLocalServiceImpl.class);

}
