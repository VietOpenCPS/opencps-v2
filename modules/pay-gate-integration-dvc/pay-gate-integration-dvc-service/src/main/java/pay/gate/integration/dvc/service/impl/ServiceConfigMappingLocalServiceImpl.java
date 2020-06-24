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

package pay.gate.integration.dvc.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

import aQute.bnd.annotation.ProviderType;
import pay.gate.integration.dvc.model.ApdungDVC;
import pay.gate.integration.dvc.model.ServiceConfigMapping;
import pay.gate.integration.dvc.service.base.ServiceConfigMappingLocalServiceBaseImpl;

/**
 * The implementation of the service config mapping local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link pay.gate.integration.dvc.service.ServiceConfigMappingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceConfigMappingLocalServiceBaseImpl
 * @see pay.gate.integration.dvc.service.ServiceConfigMappingLocalServiceUtil
 */
@ProviderType
public class ServiceConfigMappingLocalServiceImpl
	extends ServiceConfigMappingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link pay.gate.integration.dvc.service.ServiceConfigMappingLocalServiceUtil} to access the service config mapping local service.
	 */
	@Override
	@Transactional(isolation = Isolation.PORTAL, rollbackFor = { PortalException.class, SystemException.class })
	public ServiceConfigMapping initServiceConfigMaping(long groupId, long serviceConfigMappingId, long apdungDVCId, 
			String maDVC, String tenDVC,String maTTHC, String tenTTHC, String tenCQBH, String tenLinhVuc,
			String apdungDVC, String maCQTH, int mucdo, ServiceContext context) throws PortalException {
		ServiceConfigMapping serviceConfigMapping = null;
		ApdungDVC apdungDVC2 = null;
		Date now = new Date();
		long userId = context.getUserId();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);
		if (serviceConfigMappingId == 0 && apdungDVCId == 0) {
			
			serviceConfigMappingId = counterLocalService.increment(ServiceConfigMapping.class.getName());
			serviceConfigMapping = serviceConfigMappingPersistence.create(serviceConfigMappingId);
			
			apdungDVCId = counterLocalService.increment(ApdungDVC.class.getName());
			apdungDVC2 = apdungDVCPersistence.create(apdungDVCId);
			
			// common field service config mapping
			serviceConfigMapping.setCreateDate(now);
			serviceConfigMapping.setModifiedDate(now);
			serviceConfigMapping.setCompanyId(context.getCompanyId());
			serviceConfigMapping.setGroupId(groupId);
			serviceConfigMapping.setUserId(userId);
			serviceConfigMapping.setUserName(auditUser.getFullName());
			
			// extend field service config mapping
			serviceConfigMapping.setMaDVC(maDVC);
			serviceConfigMapping.setTenDVC(tenDVC);
			serviceConfigMapping.setMaTTHC(maTTHC);
			serviceConfigMapping.setTenTTHC(tenTTHC);
			serviceConfigMapping.setTenCQBH(tenCQBH);
			serviceConfigMapping.setTenLinhVuc(tenLinhVuc);
			serviceConfigMapping.setApdungDVC(apdungDVC);
			
			// insert service config mapping instance to db
			serviceConfigMapping = serviceConfigMappingPersistence.update(serviceConfigMapping);
			
			// common field apdungDVC
			JSONArray apdungDVCArray = JSONFactoryUtil.createJSONArray(apdungDVC);
			for (int i =0; i< apdungDVCArray.length(); i++) {
				
				apdungDVC2.setCreateDate(now);
				apdungDVC2.setModifiedDate(now);
				apdungDVC2.setCompanyId(context.getCompanyId());
				apdungDVC2.setGroupId(groupId);
				apdungDVC2.setUserId(userId);
				apdungDVC2.setUserName(auditUser.getFullName());
				
				// extend field
				apdungDVC2.setMaTTHC(maTTHC);
				apdungDVC2.setMaCQTH(maCQTH);
				apdungDVC2.setMucdo(mucdo);
				apdungDVC2.setServiceConfigMappingId(serviceConfigMappingId);
				
				// insert apdungDVC instance to db
				apdungDVC2 = apdungDVCPersistence.update(apdungDVC2);
				i++;
			}
			
		}		
		return serviceConfigMapping;		
	}
	
	public ServiceConfigMapping removeServiceConfigMapping(long groupId, long serviceConfigMappingId) throws PortalException {
		ServiceConfigMapping serviceConfigMapping = null;
		if(serviceConfigMappingId != 0) {
			serviceConfigMapping = serviceConfigMappingPersistence.fetchByPrimaryKey(serviceConfigMappingId);
		}
		return serviceConfigMappingPersistence.remove(serviceConfigMapping);
	}
}