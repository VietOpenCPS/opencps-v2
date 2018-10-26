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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.base.RegistrationTemplatesLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the registration templates local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.RegistrationTemplatesLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see RegistrationTemplatesLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil
 */
@ProviderType
public class RegistrationTemplatesLocalServiceImpl extends RegistrationTemplatesLocalServiceBaseImpl {

	public List<RegistrationTemplates> getRegistrationTemplatesbyGroupId(long groupId) {

		List<RegistrationTemplates> lstRegistrationTemplates = registrationTemplatesPersistence.findByGROUPID(groupId);

		return lstRegistrationTemplates;
	}

	public List<RegistrationTemplates> getRegistrationTemplatesbyFormNo(long groupId, String formNo) {

		List<RegistrationTemplates> lstRegistrationTemplates = registrationTemplatesPersistence.findByFNO(groupId,
				formNo);

		return lstRegistrationTemplates;
	}

	public List<RegistrationTemplates> getRegistrationTemplatesbyGOVCODE(long groupId, String govAgencyCode) {

		List<RegistrationTemplates> lstRegistrationTemplates = registrationTemplatesPersistence.findByGOVCODE(groupId,
				govAgencyCode);

		return lstRegistrationTemplates;
	}

	public RegistrationTemplates addRegistrationTemplates(long groupId, String govAgencyCode, String govAgencyName,
			String formNo, String formName, boolean multiple, String formScript, String formReport, String sampleData,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// TODO Add RegistrationTemplates
		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		long registrationTemplateId = counterLocalService.increment(RegistrationTemplates.class.getName());

		RegistrationTemplates object = registrationTemplatesPersistence.create(registrationTemplateId);

		/// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		// Add other fields
		object.setRegistrationTemplateId(registrationTemplateId);
		object.setGovAgencyCode(govAgencyCode);
		object.setGovAgencyName(govAgencyName);
		object.setFormNo(formNo);
		object.setFormName(formName);
		object.setMultiple(multiple);
		object.setFormScript(formScript);
		object.setFormReport(formReport);
		object.setSampleData(sampleData);

		return registrationTemplatesPersistence.update(object);
	}

	public RegistrationTemplates updateRegistrationTemplates(long groupId, long registrationTemplateId,
			String govAgencyCode, String govAgencyName, String formNo, String formName, boolean multiple,
			String formScript, String formReport, String sampleData, ServiceContext serviceContext)
			throws PortalException {
		// TODO Update RegistrationTemplates
		Date now = new Date();

		long userId = serviceContext.getUserId();

		User userAction = userPersistence.fetchByPrimaryKey(userId);

		RegistrationTemplates registrationTemplates = null;

		if (registrationTemplateId == 0) {
			registrationTemplateId = counterLocalService.increment(RegistrationTemplates.class.getName());

			registrationTemplates = registrationTemplatesPersistence.create(registrationTemplateId);

			/// Add audit fields
			registrationTemplates.setGroupId(groupId);
			registrationTemplates.setCreateDate(now);
			registrationTemplates.setModifiedDate(now);
			registrationTemplates.setUserId(userAction.getUserId());
			registrationTemplates.setUserName(userAction.getFullName());

			// Add other fields
			registrationTemplates.setRegistrationTemplateId(registrationTemplateId);
			registrationTemplates.setGovAgencyCode(govAgencyCode);
			registrationTemplates.setGovAgencyName(govAgencyName);
			registrationTemplates.setFormNo(formNo);
			registrationTemplates.setFormName(formName);
			registrationTemplates.setMultiple(multiple);
			registrationTemplates.setFormScript(formScript);
			registrationTemplates.setFormReport(formReport);
			registrationTemplates.setSampleData(sampleData);

		} else {
			registrationTemplates = registrationTemplatesPersistence.fetchByPrimaryKey(registrationTemplateId);

			registrationTemplates.setModifiedDate(now);

			registrationTemplates.setRegistrationTemplateId(registrationTemplateId);
			registrationTemplates.setGovAgencyCode(govAgencyCode);
			registrationTemplates.setGovAgencyName(govAgencyName);
			registrationTemplates.setFormNo(formNo);
			registrationTemplates.setFormName(formName);
			registrationTemplates.setMultiple(multiple);
			registrationTemplates.setFormScript(formScript);
			registrationTemplates.setFormReport(formReport);
			registrationTemplates.setSampleData(sampleData);
		}

		registrationTemplatesPersistence.update(registrationTemplates);

		return registrationTemplates;
	}

	public RegistrationTemplates getRegistrationTemplatebyId(long groupId, String registrationTemplateId)
			throws PortalException {
		// TODO remove RegistrationTemplates

		RegistrationTemplates registrationTemplates = null;

		long id = Long.valueOf(registrationTemplateId);
		registrationTemplates = registrationTemplatesPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(registrationTemplates)) {
			registrationTemplates = registrationTemplatesPersistence.fetchByG_REGID(groupId, id);
		}

		return registrationTemplates;

	}

	public RegistrationTemplates removeRegistrationTemplate(long groupId, String registrationTemplateId)
			throws PortalException {
		// TODO remove RegistrationTemplates

		validateRemoveRegistrationTemplate(groupId, registrationTemplateId);

		RegistrationTemplates registrationTemplate = null;
		long id = Long.valueOf(registrationTemplateId);
		registrationTemplate = registrationTemplatesPersistence.fetchByPrimaryKey(id);
		if (Validator.isNull(registrationTemplate)) {
			registrationTemplate = registrationTemplatesPersistence.fetchByG_REGID(groupId, id);
		}
		return registrationTemplatesPersistence.remove(registrationTemplate);
	}

	public RegistrationTemplates updateFormScript(long groupId, long registrationTemplateId, String formScript,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// TODO Update FormScript of RegistrationTemplates

		Date now = new Date();

		RegistrationTemplates registrationTemplates = registrationTemplatesPersistence
				.findByPrimaryKey(registrationTemplateId);

		registrationTemplates.setFormScript(formScript);
		registrationTemplates.setModifiedDate(now);

		return registrationTemplatesPersistence.update(registrationTemplates);
	}

	public RegistrationTemplates updateFormReport(long groupId, long registrationTemplatesId, String formReport,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// TODO Update FormReport of RegistrationTemplates

		Date now = new Date();

		RegistrationTemplates registrationTemplates = registrationTemplatesPersistence
				.findByPrimaryKey(registrationTemplatesId);

		registrationTemplates.setFormReport(formReport);
		registrationTemplates.setModifiedDate(now);

		return registrationTemplatesPersistence.update(registrationTemplates);
	}

	public RegistrationTemplates updateSampledata(long groupId, long registrationTemplatesId, String sampleData,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// TODO Update sampleData of RegistrationTemplates

		Date now = new Date();

		RegistrationTemplates registrationTemplates = registrationTemplatesPersistence
				.findByPrimaryKey(registrationTemplatesId);

		registrationTemplates.setSampleData(sampleData);
		registrationTemplates.setModifiedDate(now);

		return registrationTemplatesPersistence.update(registrationTemplates);
	}

	private void validateRemoveRegistrationTemplate(long groupId, String registrationTemplateId) {
		// TODO Auto-generated method stub

	}

	public RegistrationTemplates getRegTempbyFormNoGovCode(long groupId, String formNo, String govAgencyCode) {
		return registrationTemplatesPersistence.fetchByGOVCODE_FORMNO(groupId, formNo, govAgencyCode);
	}

	public RegistrationTemplates getRegTempbyRegId(long groupId, long registrationTemplatesId) {
		return registrationTemplatesPersistence.fetchByG_REGID(groupId, registrationTemplatesId);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public RegistrationTemplates adminProcessDelete(Long id) {

		RegistrationTemplates object = registrationTemplatesPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			registrationTemplatesPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public RegistrationTemplates adminProcessData(JSONObject objectData) {

		RegistrationTemplates object = null;

		if (objectData.getLong("registrationTemplatesId") > 0) {

			object = registrationTemplatesPersistence.fetchByPrimaryKey(objectData.getLong("registrationTemplatesId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(RegistrationTemplates.class.getName());

			object = registrationTemplatesPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setGovAgencyCode(objectData.getString("govAgencyCode"));
		object.setGovAgencyName(objectData.getString("govAgencyName"));
		object.setFormNo(objectData.getString("formNo"));
		object.setFormName(objectData.getString("formName"));
		object.setMultiple(objectData.getBoolean("multiple"));
		object.setFormScript(objectData.getString("formScript"));
		object.setFormReport(objectData.getString("formReport"));
		object.setSampleData(objectData.getString("sampleData"));

		registrationTemplatesPersistence.update(object);

		return object;
	}

}