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

import org.opencps.dossiermgt.model.ApplicableInfo;
import org.opencps.dossiermgt.service.base.ApplicableInfoLocalServiceBaseImpl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;


/**
 * The implementation of the applicable info local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.ApplicableInfoLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ApplicableInfoLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ApplicableInfoLocalServiceUtil
 */
public class ApplicableInfoLocalServiceImpl
	extends ApplicableInfoLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.ApplicableInfoLocalServiceUtil} to access the applicable info local service.
	 */
	public ApplicableInfo addApplicableInfo(long groupId, long serviceConfigMappingId, String govAgencyCode,
			String serviceCode, int serviceLevel, ServiceContext context) {

		long applicableInfoId = counterLocalService.increment(ApplicableInfo.class.getName());

		ApplicableInfo applicableInfo = applicableInfoPersistence.create(applicableInfoId);

		Date now = new Date();

		long userId = context.getUserId();

		User user = userPersistence.fetchByPrimaryKey(userId);

		// common field service config mapping
		applicableInfo.setCreateDate(now);
		applicableInfo.setModifiedDate(now);
		applicableInfo.setCompanyId(context.getCompanyId());
		applicableInfo.setGroupId(groupId);
		applicableInfo.setUserName(user.getFullName());

		applicableInfo.setGovAgencyCode(govAgencyCode);
		applicableInfo.setServiceCode(serviceCode);
		applicableInfo.setServiceLevel(serviceLevel);
		applicableInfo.setServiceConfigMappingId(serviceConfigMappingId);

		return applicableInfoPersistence.update(applicableInfo);
	}
	
	public ApplicableInfo fetchByG_SC_GC_SL(long groupId, String serviceCode, String govAgencyCode, int serviceLevel) {
		return applicableInfoPersistence.fetchByG_SC_GC_SL(groupId, serviceCode, govAgencyCode, serviceLevel);
	}
}