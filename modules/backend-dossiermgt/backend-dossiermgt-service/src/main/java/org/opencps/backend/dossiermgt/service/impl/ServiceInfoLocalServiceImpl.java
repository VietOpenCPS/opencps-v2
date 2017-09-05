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

package org.opencps.backend.dossiermgt.service.impl;

import aQute.bnd.annotation.ProviderType;

import java.util.Date;
import java.util.List;

import org.opencps.backend.dossiermgt.exception.RequiredAdministrationCodeException;
import org.opencps.backend.dossiermgt.exception.RequiredServiceCodeException;
import org.opencps.backend.dossiermgt.exception.RequiredServiceNameException;
import org.opencps.backend.dossiermgt.model.ServiceInfo;
import org.opencps.backend.dossiermgt.service.base.ServiceInfoLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the service info local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.backend.dossiermgt.service.ServiceInfoLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ServiceInfoLocalServiceBaseImpl
 * @see org.opencps.backend.dossiermgt.service.ServiceInfoLocalServiceUtil
 */
@ProviderType
public class ServiceInfoLocalServiceImpl extends ServiceInfoLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.backend.dossiermgt.service.ServiceInfoLocalServiceUtil} to access the service info local service.
	 */

	public ServiceInfo addServiceInfo(long userId, long groupId, String serviceCode, 
			String serviceName, String processText, String methodText, String dossierText, 
			String conditionText, String durationText, String applicantText, 
			String resultText, String regularText, String feeText, String administrationCode,
			String domainCode, int maxLevel, int activeStatus, ServiceContext serviceContext) 
		throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
		Date now = new Date();
		
		//TODO: validate
		valdiate(serviceCode, serviceName, administrationCode, domainCode);
		
		long serviceInfoId = counterLocalService.increment(ServiceInfo.class.getName());
		
		ServiceInfo serviceInfo = serviceInfoPersistence.create(serviceInfoId);
		
		serviceInfo.setGroupId(groupId);
		serviceInfo.setCompanyId(user.getCompanyId());
		serviceInfo.setUserId(user.getUserId());
		serviceInfo.setUserName(user.getFullName());
		serviceInfo.setCreateDate(now);
		serviceInfo.setModifiedDate(now);
		
		serviceInfo.setServiceCode(serviceCode);
		serviceInfo.setServiceName(serviceName);
		serviceInfo.setProcessText(processText);
		serviceInfo.setMethodText(methodText);
		serviceInfo.setDossierText(dossierText);
		serviceInfo.setConditionText(conditionText);
		serviceInfo.setDurationText(durationText);
		serviceInfo.setApplicantText(applicantText);
		serviceInfo.setResultText(resultText);
		serviceInfo.setRegularText(regularText);
		serviceInfo.setFeeText(feeText);
		serviceInfo.setAdministrationCode(administrationCode);
		serviceInfo.setDomainCode(domainCode);
		serviceInfo.setMaxLevel(maxLevel);
		serviceInfo.setActiveStatus(activeStatus);
		
		
		serviceInfoPersistence.update(serviceInfo);
		
		return serviceInfo;
	}
	
	public int countServiceInfosByGroupId(long groupId) {
		return serviceInfoPersistence.countByGroupId(groupId);
	}
	
	public List<ServiceInfo> getServiceInfosByGroupId(long groupId) {
		return serviceInfoPersistence.findByGroupId(groupId);
	}
	
	public List<ServiceInfo> getServiceInfosByGroupId(long groupId, int start, int end) {
		return serviceInfoPersistence.findByGroupId(groupId, start, end);
	}
	
	public ServiceInfo updateServiceInfo(long groupId, long serviceInfoId, String serviceCode, 
			String serviceName, String processText, String methodText, String dossierText, 
			String conditionText, String durationText, String applicantText, 
			String resultText, String regularText, String feeText, String administrationCode,
			String domainCode, int maxLevel, int activeStatus, ServiceContext serviceContext) 
		throws PortalException {
		
		Date now = new Date();
		
		ServiceInfo serviceInfo = serviceInfoPersistence.findByPrimaryKey(serviceInfoId);
		
		//TODO: validate
		
		serviceInfo.setModifiedDate(now);
		
		serviceInfo.setServiceCode(serviceCode);
		serviceInfo.setServiceName(serviceName);
		serviceInfo.setProcessText(processText);
		serviceInfo.setMethodText(methodText);
		serviceInfo.setDossierText(dossierText);
		serviceInfo.setConditionText(conditionText);
		serviceInfo.setDurationText(durationText);
		serviceInfo.setApplicantText(applicantText);
		serviceInfo.setResultText(resultText);
		serviceInfo.setRegularText(regularText);
		serviceInfo.setFeeText(feeText);
		serviceInfo.setAdministrationCode(administrationCode);
		serviceInfo.setDomainCode(domainCode);
		serviceInfo.setMaxLevel(maxLevel);
		serviceInfo.setActiveStatus(activeStatus);
		
		serviceInfoPersistence.update(serviceInfo);
		
		return serviceInfo;
	}
	
	private void valdiate(String serviceCode, String serviceName, String administrationCode, String domainCode) 
		throws PortalException {
		
		if(Validator.isNull(serviceCode)) {
			throw new RequiredServiceCodeException();
		}
		
		if(Validator.isNull(serviceName)) {
			throw new RequiredServiceNameException();
		}
		
		if(Validator.isNull(administrationCode)) {
			throw new RequiredAdministrationCodeException();
		}
	}
}