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
import java.util.List;
import java.util.UUID;

import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.base.RegistrationFormLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

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

	public List<RegistrationForm> getFormsbyRegId(long registrationId) throws PortalException {

		List<RegistrationForm> lstRegistrationForm = registrationFormPersistence.findByG_REGID(registrationId);

		return lstRegistrationForm;
	}

	public RegistrationForm addRegistrationForm(long groupId, long registrationId, String referenceUid, String formNo,
			String formName, String formData, String formScript, String formReport, long fileEntryId, boolean isNew,
			boolean removed, ServiceContext serviceContext) throws PortalException {
		// TODO Add RegistrationForm
		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);
		
		referenceUid = UUID.randomUUID().toString();

		long registrationFormId = counterLocalService.increment(RegistrationForm.class.getName());

		RegistrationForm object = registrationFormPersistence.create(registrationFormId);

		/// Add audit fields
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
		object.setIsNew(isNew);
		object.setRemoved(removed);

		return registrationFormPersistence.update(object);
	}
	
	public RegistrationForm updateRegistrationForm(long groupId, long registrationId, String referenceUid, String formNo,
			String formName, String formData, String formScript, String formReport, long fileEntryId, boolean isNew,
			boolean removed, ServiceContext serviceContext)throws PortalException {
		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		RegistrationForm object = registrationFormPersistence.fetchByG_REGID_REFID(registrationId, referenceUid);

		/// Add audit fields
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
		object.setIsNew(isNew);
		object.setRemoved(removed);

		return registrationFormPersistence.update(object);
	}
	
	public RegistrationForm deleteRegistrationForm(long registrationId, String referenceUid){
		
		RegistrationForm registrationForm = findFormbyRegidRefid(registrationId, referenceUid);
		
		return registrationFormPersistence.remove(registrationForm);
	}
	
	public RegistrationForm findFormbyRegidRefid(long registrationId, String referenceUid){
		return registrationFormPersistence.fetchByG_REGID_REFID(registrationId, referenceUid);
	}
}