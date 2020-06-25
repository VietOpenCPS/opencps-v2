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

package opencps.dvcqg.extend.sync.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;

import opencps.dvcqg.extend.sync.model.ServiceConfigMapping;
import opencps.dvcqg.extend.sync.service.base.ServiceConfigMappingLocalServiceBaseImpl;


/**
 * The implementation of the service config mapping local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link opencps.dvcqg.extend.sync.service.ServiceConfigMappingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceConfigMappingLocalServiceBaseImpl
 * @see opencps.dvcqg.extend.sync.service.ServiceConfigMappingLocalServiceUtil
 */
public class ServiceConfigMappingLocalServiceImpl
	extends ServiceConfigMappingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link opencps.dvcqg.extend.sync.service.ServiceConfigMappingLocalServiceUtil} to access the service config mapping local service.
	 */
	
	@Override
	@Transactional(isolation = Isolation.PORTAL, rollbackFor = { PortalException.class, SystemException.class })
	public ServiceConfigMapping addServiceConfigMaping(long groupId, String serviceConfigCode, String serviceConfigName,
			String serviceCode, String serviceName, String govAgencyName, String domainName,
			JSONArray applicableInfoObj, JSONArray paymentFeeInfoObj, ServiceContext context) throws PortalException {

		Date now = new Date();

		long userId = context.getUserId();

		User user = userPersistence.fetchByPrimaryKey(userId);

		long serviceConfigMappingId = counterLocalService.increment(ServiceConfigMapping.class.getName());

		ServiceConfigMapping serviceConfigMapping = serviceConfigMappingPersistence.create(serviceConfigMappingId);

		// common field service config mapping
		serviceConfigMapping.setCreateDate(now);
		serviceConfigMapping.setModifiedDate(now);
		serviceConfigMapping.setCompanyId(context.getCompanyId());
		serviceConfigMapping.setGroupId(groupId);
		serviceConfigMapping.setUserId(userId);
		serviceConfigMapping.setUserName(user.getFullName());

		// extend field service config mapping
		serviceConfigMapping.setServiceConfigCode(serviceConfigCode);
		serviceConfigMapping.setServiceConfigName(serviceConfigName);
		serviceConfigMapping.setServiceCode(serviceCode);
		serviceConfigMapping.setServiceName(serviceName);
		serviceConfigMapping.setGovAgencyName(govAgencyName);
		serviceConfigMapping.setDomainName(domainName);

		serviceConfigMapping = serviceConfigMappingPersistence.update(serviceConfigMapping);

		if (applicableInfoObj != null) {
			for (int i = 0; i < applicableInfoObj.length(); i++) {
				JSONObject obj = applicableInfoObj.getJSONObject(i);

				String govAgencyCode = obj.getString("MACOQUANTHUCHIEN");

				int serviceLevel = obj.getInt("MUCDO");

				applicableInfoLocalService.addApplicableInfo(groupId, serviceConfigMappingId, govAgencyCode,
						serviceCode, serviceLevel, context);

			}
		}
		
		if(paymentFeeInfoObj != null) {
			for (int i = 0; i < paymentFeeInfoObj.length(); i++) {
				JSONObject obj = paymentFeeInfoObj.getJSONObject(i);

				String paymentFeeCode = obj.getString("MAPHILEPHI");

				String paymentFeeName = obj.getString("MOTA");
				
				String type = obj.getString("MAPHILEPHI");// chua xac dinh
				
				String amount = obj.getString("SOTIEN");

				paymentFeeInfoLocalService.addPaymentFeeInfo(groupId, serviceConfigMappingId, paymentFeeCode, paymentFeeName, type, amount, context);

			}
		}

		return serviceConfigMapping;
	}

	public ServiceConfigMapping removeServiceConfigMapping(long groupId, long serviceConfigMappingId)
			throws PortalException {
		ServiceConfigMapping serviceConfigMapping = null;
		if (serviceConfigMappingId != 0) {
			serviceConfigMapping = serviceConfigMappingPersistence.fetchByPrimaryKey(serviceConfigMappingId);
		}
		return serviceConfigMappingPersistence.remove(serviceConfigMapping);
	}
}