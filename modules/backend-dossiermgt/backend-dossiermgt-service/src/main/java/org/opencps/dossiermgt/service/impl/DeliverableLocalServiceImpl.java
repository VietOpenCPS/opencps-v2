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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableLog;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.base.DeliverableLocalServiceBaseImpl;

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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the deliverable local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DeliverableLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DeliverableLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DeliverableLocalServiceUtil
 */
@ProviderType
public class DeliverableLocalServiceImpl extends DeliverableLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DeliverableLocalServiceUtil} to access the
	 * deliverable local service.
	 */
	
	public Deliverable getByCode(String deliverableCode) {
		return deliverablePersistence.fetchByFB_DCODE(deliverableCode);
	}
	
	public List<Deliverable> getListDeliverable(String deliverableState, String govAgencyCode, String deliverableType,
			String applicant) {
		List<Deliverable> listDeliverable = deliverablePersistence.findByG_ID(deliverableState, govAgencyCode,
				deliverableType, applicant);
		return listDeliverable;
	}

	@Indexable(type = IndexableType.REINDEX)
	public void insert(Deliverable model) {
		model.setDeliverableId(counterLocalService.increment(Deliverable.class.getName()));
		addDeliverable(model);
	}

//	public Deliverable getListDeliverableDetai(Long id) throws NoSuchDeliverableException {
//		Deliverable deliverable = deliverablePersistence.findByDID(id);
//		return deliverable;
//	}

	//12
	public List<Deliverable> getFormDataByTypeCode(long groupId, String registrationId, String typeCode) {
		return null;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable addDeliverable(long groupId, String deliverableType, String deliverableCode,
			String govAgencyCode, String applicationIdNo, String applicationName, String subject,
			String issueDate, String expireDate,String revalidate, String deliverableState,
			ServiceContext serviceContext) {
		// TODO Add RegistrationForm
		long userId = serviceContext.getUserId();

		Date now = new Date();

//		User userAction = userLocalService.getUser(userId);
		
//		referenceUid = UUID.randomUUID().toString();

		long deliverableId = counterLocalService.increment(Deliverable.class.getName());

		Deliverable object = deliverablePersistence.create(deliverableId);

		/// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		object.setDeliverableId(deliverableId);
		object.setDeliverableType(deliverableType);
		object.setDeliverableCode(deliverableCode);
		object.setGovAgencyCode(govAgencyCode);
		object.setApplicantIdNo(applicationIdNo);
		object.setApplicantName(applicationName);
		object.setSubject(subject);
		object.setIssueDate(APIDateTimeUtils.convertStringToDate(issueDate, APIDateTimeUtils._NORMAL_PARTTERN));
		object.setExpireDate(APIDateTimeUtils.convertStringToDate(expireDate, APIDateTimeUtils._NORMAL_PARTTERN));
		object.setRevalidate(APIDateTimeUtils.convertStringToDate(revalidate, APIDateTimeUtils._NORMAL_PARTTERN));
		object.setDeliverableState(String.valueOf(deliverableState));

		return deliverablePersistence.update(object);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<Deliverable> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);

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

				query.addFields(DeliverableTerm.DELIVERABLE_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String state = GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_STATE));
		String agency = GetterUtil.getString(params.get(DeliverableTerm.GOV_AGENCY_CODE));
		String type = GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_TYPE));
		String applicant = GetterUtil.getString(params.get(DeliverableTerm.APPLICANT_ID_NO));
		String deliverableId = String.valueOf(GetterUtil.getLong(params.get(DeliverableTerm.DELIVERABLE_ID)));

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

		if (Validator.isNotNull(deliverableId) && !deliverableId.equalsIgnoreCase("0")) {
			MultiMatchQuery query = new MultiMatchQuery(deliverableId);

			query.addFields(DeliverableTerm.DELIVERABLE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<Deliverable> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);

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

				query.addFields(DeliverableTerm.DELIVERABLE_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String state = GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_STATE));
		String agency = GetterUtil.getString(params.get(DeliverableTerm.GOV_AGENCY_CODE));
		String type = GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_TYPE));
		String applicant = GetterUtil.getString(params.get(DeliverableTerm.APPLICANT_ID_NO));

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

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = Deliverable.class.getName();

	public Deliverable getDeliverableDetail(long id, long groupId) throws NoSuchDeliverableException {
		return deliverablePersistence.fetchByG_DID(groupId, id);
	}

	public Deliverable getDetailById(long id) {
		return deliverablePersistence.fetchByDID(id);
	}

	//4
	@Indexable(type=IndexableType.REINDEX)
	public Deliverable updateDeliverable(long groupId, long id, String subject, String issueDate, String expireDate,
			String revalidate, String deliverableState, String deliverableAction, ServiceContext serviceContext) {
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
			object.setIssueDate(APIDateTimeUtils.convertStringToDate(issueDate, APIDateTimeUtils._NORMAL_PARTTERN));
			object.setExpireDate(APIDateTimeUtils.convertStringToDate(expireDate, APIDateTimeUtils._NORMAL_PARTTERN));
			object.setRevalidate(APIDateTimeUtils.convertStringToDate(revalidate, APIDateTimeUtils._NORMAL_PARTTERN));
			object.setDeliverableState(String.valueOf(deliverableState));
		} catch (Exception e) {
			 //TODO
		}
		//TODO:????
		try {
//			DeliverableLog deliverableLog = deliverableLogPersistence.fi(groupId, id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return deliverablePersistence.update(object);
	}

	//5
	@Indexable(type=IndexableType.DELETE)
	public Deliverable deleteDeliverable(long groupId, long id) throws NoSuchDeliverableException {
		return deliverablePersistence.removeByG_DID(groupId, id);
	}

	//7
	@Indexable(type=IndexableType.REINDEX)
	public Deliverable updateFormData(long groupId, long id, String formData,ServiceContext serviceContext) throws NoSuchDeliverableException {
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

		return deliverablePersistence.update(object);
	}
 }