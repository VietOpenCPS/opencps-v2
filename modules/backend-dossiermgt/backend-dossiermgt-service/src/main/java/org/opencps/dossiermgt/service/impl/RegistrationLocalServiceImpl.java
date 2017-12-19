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

import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationTemplates;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationTemplatesLocalServiceUtil;
import org.opencps.dossiermgt.service.base.RegistrationLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * The implementation of the registration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.RegistrationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see RegistrationLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.RegistrationLocalServiceUtil
 */
@ProviderType
public class RegistrationLocalServiceImpl extends RegistrationLocalServiceBaseImpl {

	public Registration insert(long groupId, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail, String govAgencyCode, String govAgencyName, int registrationState,
			String registrationClass, ServiceContext serviceContext) throws PortalException, SystemException {
		Date now = new Date();
		long userId = serviceContext.getUserId();
		User userAction = userLocalService.getUser(userId);

		long registrationId = counterLocalService.increment(Registration.class.getName());

		Registration model = registrationPersistence.create(registrationId);

		int start = -1, end = -1;

		model.setGroupId(groupId);
		model.setCreateDate(now);
		model.setModifiedDate(now);
		model.setUserId(userAction.getUserId());

		model.setApplicantName(applicantName);
		model.setApplicantIdType(applicantIdType);
		model.setApplicantIdNo(applicantIdNo);
		model.setAddress(address);
		model.setCityCode(cityCode);
		model.setCityName(cityName);
		model.setDistrictCode(districtCode);
		model.setDistrictName(districtName);
		model.setWardCode(wardCode);
		model.setWardName(wardName);
		model.setContactName(contactName);
		model.setContactTelNo(contactTelNo);
		model.setContactEmail(contactEmail);
		model.setGovAgencyCode(govAgencyCode);
		model.setGovAgencyName(govAgencyName);
		model.setRegistrationClass(registrationClass);
		model.setRegistrationState(registrationState);

		String referenceUid = UUID.randomUUID().toString();

		List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
				.getRegistrationTemplateses(start, end);

		for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {
			int fileEntryId = getfileEntryId(registrationTemplates.getSampleData(), registrationTemplates.getFormScript(),
					registrationTemplates.getFormReport());

			RegistrationForm registrationForm = RegistrationFormLocalServiceUtil.addRegistrationForm(groupId,
					registrationId, referenceUid, registrationTemplates.getFormNo(),
					registrationTemplates.getFormName(), registrationTemplates.getSampleData(),
					registrationTemplates.getFormScript(), registrationTemplates.getFormReport(), fileEntryId, false,
					false, serviceContext);
		}
		return registrationPersistence.update(model);
	}
	
	public Registration updateRegistration(long groupId, long registrationId, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail, String govAgencyCode, String govAgencyName, int registrationState,
			String registrationClass, ServiceContext serviceContext)throws PortalException, SystemException{
		
		int start = -1, end = -1;
		
		Date now = new Date();
		long userId = serviceContext.getUserId();
		User userAction = userLocalService.getUser(userId);

		Registration model = registrationPersistence.fetchByPrimaryKey(registrationId);
		
		model.setGroupId(groupId);
		model.setCreateDate(now);
		model.setModifiedDate(now);
		model.setUserId(userAction.getUserId());

		model.setApplicantName(applicantName);
		model.setApplicantIdType(applicantIdType);
		model.setApplicantIdNo(applicantIdNo);
		model.setAddress(address);
		model.setCityCode(cityCode);
		model.setCityName(cityName);
		model.setDistrictCode(districtCode);
		model.setDistrictName(districtName);
		model.setWardCode(wardCode);
		model.setWardName(wardName);
		model.setContactName(contactName);
		model.setContactTelNo(contactTelNo);
		model.setContactEmail(contactEmail);
		model.setGovAgencyCode(govAgencyCode);
		model.setGovAgencyName(govAgencyName);
		model.setRegistrationClass(registrationClass);
		model.setRegistrationState(registrationState);
		
		List<RegistrationForm> lstRegistrationForm = RegistrationFormLocalServiceUtil
				.getRegistrationForms(start, end);
		for (RegistrationForm registrationForm : lstRegistrationForm){
			if(registrationForm.isRemoved() == false){
				registrationForm.setRemoved(true);
			}
			RegistrationFormLocalServiceUtil.updateRegistrationForm(registrationForm);
		}
		
		String referenceUid = UUID.randomUUID().toString();

		List<RegistrationTemplates> lstRegistrationTemplate = RegistrationTemplatesLocalServiceUtil
				.getRegistrationTemplateses(start, end);

		for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {
			int fileEntryId = getfileEntryId(registrationTemplates.getSampleData(), registrationTemplates.getFormScript(),
					registrationTemplates.getFormReport());

			RegistrationForm registrationForm = RegistrationFormLocalServiceUtil.addRegistrationForm(groupId,
					registrationId, referenceUid, registrationTemplates.getFormNo(),
					registrationTemplates.getFormName(), registrationTemplates.getSampleData(),
					registrationTemplates.getFormScript(), registrationTemplates.getFormReport(), fileEntryId, false,
					false, serviceContext);
		}
				return null;
	}

	public int getfileEntryId(String formdata, String formScript, String formReport) {

		int fileEntryId = 0;

		return fileEntryId;
	}

}