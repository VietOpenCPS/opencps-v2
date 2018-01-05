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

import aQute.bnd.annotation.ProviderType;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.opencps.dossiermgt.constants.RegistrationFormTerm;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.base.RegistrationFormLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the registration form local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.RegistrationFormLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see RegistrationFormLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil
 */
@ProviderType
public class RegistrationFormLocalServiceImpl extends RegistrationFormLocalServiceBaseImpl {

	public List<RegistrationForm> getFormsbyRegId(long groupId, long registrationId) throws PortalException {

		List<RegistrationForm> lstRegistrationForm = registrationFormPersistence.findByG_REGID(groupId, registrationId);

		return lstRegistrationForm;
	}

	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm addRegistrationForm(long groupId, long companyId, long registrationId, String referenceUid,
			String formNo, String formName, String formData, String formScript, String formReport, long fileEntryId,
			boolean isNew, boolean removed, ServiceContext serviceContext) throws PortalException {
		// TODO Add RegistrationForm
		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		referenceUid = UUID.randomUUID().toString();

		long registrationFormId = counterLocalService.increment(RegistrationForm.class.getName());

		RegistrationForm object = registrationFormPersistence.create(registrationFormId);

		/// Add audit fields
		object.setCompanyId(companyId);
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());

		// Add other fields
		object.setRegistrationId(registrationId);
		object.setReferenceUid(referenceUid);
		object.setFormNo(formNo);
		object.setFormName(formName);
		object.setFormData(formData);
		object.setFormScript(formScript);
		object.setFormReport(formReport);
		object.setFileEntryId(fileEntryId);
		object.setIsNew(true);
		object.setRemoved(removed);

		return registrationFormPersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm updateRegistrationForm(long groupId, long registrationId, String referenceUid,
			String formNo, String formName, String formData, String formScript, String formReport, long fileEntryId,
			boolean isNew, boolean removed, ServiceContext serviceContext) throws PortalException {

		Date now = new Date();

		RegistrationForm object = registrationFormPersistence.fetchByG_REGID_REFID(groupId, registrationId,
				referenceUid);

		/// Add audit fields
		object.setModifiedDate(now);

		// Add other fields
		object.setRegistrationId(registrationId);
		object.setReferenceUid(referenceUid);
		object.setFormNo(formNo);
		object.setFormName(formName);
		object.setFormData(formData);
		object.setFormScript(formScript);
		object.setFormReport(formReport);
		object.setFileEntryId(fileEntryId);
		object.setIsNew(true);
		object.setRemoved(removed);

		return registrationFormPersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm deleteRegistrationForm(long groupId, long registrationId, String referenceUid)
			throws PortalException {

		RegistrationForm object = registrationFormPersistence.findByG_REGID_REFID(groupId, registrationId,
				referenceUid);

		object.setRemoved(true);
		object.setIsNew(true);
		object.setModifiedDate(new Date());

		return registrationFormPersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public List<RegistrationForm> deleteRegistrationForms(long groupId, long registrationId) {

		List<RegistrationForm> lstRegistrationForm = registrationFormPersistence.findByG_REGID(groupId, registrationId);

		for (RegistrationForm registrationForm : lstRegistrationForm) {
			registrationForm.setRemoved(true);
			registrationForm.setIsNew(true);
			registrationFormPersistence.update(registrationForm);
		}

		return lstRegistrationForm;
	}

	public RegistrationForm findFormbyRegidRefid(long groupId, long registrationId, String referenceUid) {
		return registrationFormPersistence.fetchByG_REGID_REFID(groupId, registrationId, referenceUid);
	}

	// binhth
	public List<RegistrationForm> findByG_REGID_ISNEW(long registrationId, boolean isNew) {
		return registrationFormPersistence.findByG_REGID_ISNEW(registrationId, isNew);
	}

	public RegistrationForm registrationFormSync(long groupId, String uuidRegistration, String referenceUid,
			String formNo, String formName, String formData, String formScript, String formReport, Boolean removed,
			ServiceContext serviceContext) throws PortalException, SystemException {

		Date now = new Date();
		long userId = serviceContext.getUserId();
		User userAction = userLocalService.getUser(userId);

		Registration registration = registrationPersistence.fetchByUUID_G(uuidRegistration, groupId);
		RegistrationForm registrationForm = registrationFormPersistence.fetchByG_REGID_REFID(groupId,
				registration.getRegistrationId(), referenceUid);

		if (Validator.isNotNull(registrationForm)) {
			registrationForm.setModifiedDate(now);

			registrationForm.setFormNo(formNo);
			registrationForm.setFormName(formName);
			registrationForm.setFormData(formData);
			registrationForm.setFormScript(formScript);
			registrationForm.setFormReport(formReport);

			if (removed != null) {
				registrationForm.setRemoved(removed.booleanValue());
			}

			if (Validator.isNotNull(formData) && Validator.isNotNull(formReport)) {
				Message message = new Message();

				JSONObject msgData = JSONFactoryUtil.createJSONObject();
				msgData.put("className", RegistrationForm.class.getName());
				msgData.put("classPK", registrationForm.getPrimaryKey());
				msgData.put("jrxmlTemplate", formReport);
				msgData.put("formData", formData);
				msgData.put("userId", serviceContext.getUserId());

				message.put("msgToEngine", msgData);
				MessageBusUtil.sendMessage("jasper/engine/out/destination", message);
			}

			registrationForm = registrationFormPersistence.update(registrationForm);
		} else {

			long registrationFormId = counterLocalService.increment(RegistrationForm.class.getName());

			registrationForm = registrationFormPersistence.create(registrationFormId);

			registrationForm.setGroupId(groupId);
			registrationForm.setCreateDate(now);
			registrationForm.setModifiedDate(now);
			registrationForm.setUserId(userAction.getUserId());

			registrationForm.setRegistrationId(registration.getRegistrationId());
			registrationForm.setReferenceUid(referenceUid);
			registrationForm.setFormNo(formNo);
			registrationForm.setFormName(formName);
			registrationForm.setFormData(formData);
			registrationForm.setFormScript(formScript);
			registrationForm.setFormReport(formReport);

			if (removed != null) {
				registrationForm.setRemoved(removed.booleanValue());
			}

			if (Validator.isNotNull(formData) && Validator.isNotNull(formReport)) {
				Message message = new Message();

				JSONObject msgData = JSONFactoryUtil.createJSONObject();
				msgData.put("className", RegistrationForm.class.getName());
				msgData.put("classPK", registrationForm.getPrimaryKey());
				msgData.put("jrxmlTemplate", formReport);
				msgData.put("formData", formData);
				msgData.put("userId", serviceContext.getUserId());

				message.put("msgToEngine", msgData);
				MessageBusUtil.sendMessage("jasper/engine/out/destination", message);
			}

			registrationForm = registrationFormPersistence.update(registrationForm);

		}

		return registrationForm;
	}

	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm updateFormData(long groupId, long registrationId, String referenceUid, String formData,
			ServiceContext serviceContext) throws PortalException, SystemException {

		RegistrationForm registrationForm = registrationFormPersistence.findByG_REGID_REFID(groupId, registrationId,
				referenceUid);

		String jrxmlTemplate = registrationForm.getFormReport();

		registrationForm.setFormData(formData);
		registrationForm.setIsNew(true);

		Message message = new Message();

		JSONObject msgData = JSONFactoryUtil.createJSONObject();
		msgData.put("className", RegistrationForm.class.getName());
		msgData.put("classPK", registrationForm.getPrimaryKey());
		msgData.put("jrxmlTemplate", jrxmlTemplate);
		msgData.put("formData", formData);
		msgData.put("userId", serviceContext.getUserId());

		message.put("msgToEngine", msgData);
		MessageBusUtil.sendMessage("jasper/engine/out/destination", message);

		return registrationFormPersistence.update(registrationForm);
	}

	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm updateIsNew(long groupId, long registrationId, String referenceUid, boolean isNew,
			ServiceContext serviceContext) throws PortalException, SystemException {

		RegistrationForm registrationForm = registrationFormPersistence.findByG_REGID_REFID(groupId, registrationId,
				referenceUid);

		registrationForm.setIsNew(isNew);

		return registrationFormPersistence.update(registrationForm);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<RegistrationFormTerm> indexer = IndexerRegistryUtil.nullSafeGetIndexer(RegistrationFormTerm.class);

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

				query.addFields(RegistrationFormTerm.REGISTRATION_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		String referenceUid = GetterUtil.getString(params.get(RegistrationFormTerm.REFERENCE_UID));
		String registrationId = GetterUtil.getString(params.get(RegistrationFormTerm.REGISTRATION_ID));

		if (Validator.isNotNull(referenceUid)) {
			MultiMatchQuery query = new MultiMatchQuery(referenceUid);

			query.addFields(RegistrationFormTerm.REFERENCE_UID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(registrationId)) {
			MultiMatchQuery query = new MultiMatchQuery(registrationId);

			query.addFields(RegistrationFormTerm.REGISTRATION_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<RegistrationFormTerm> indexer = IndexerRegistryUtil.nullSafeGetIndexer(RegistrationFormTerm.class);

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

				query.addFields(RegistrationFormTerm.REGISTRATION_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		String referenceUid = GetterUtil.getString(params.get(RegistrationFormTerm.REFERENCE_UID));
		String registrationId = GetterUtil.getString(params.get(RegistrationFormTerm.REGISTRATION_ID));

		if (Validator.isNotNull(referenceUid)) {
			MultiMatchQuery query = new MultiMatchQuery(referenceUid);

			query.addFields(RegistrationFormTerm.REFERENCE_UID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(registrationId)) {
			MultiMatchQuery query = new MultiMatchQuery(registrationId);

			query.addFields(RegistrationFormTerm.REGISTRATION_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = RegistrationForm.class.getName();
}