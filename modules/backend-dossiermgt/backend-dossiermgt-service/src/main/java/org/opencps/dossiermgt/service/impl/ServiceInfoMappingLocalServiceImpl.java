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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

import org.opencps.dossiermgt.constants.ServiceInfoMappingTerm;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceInfoMapping;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.base.ServiceInfoMappingLocalServiceBaseImpl;

/**
 * The implementation of the service info mapping local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ServiceInfoMappingLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ServiceInfoMappingLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil
 */
public class ServiceInfoMappingLocalServiceImpl extends ServiceInfoMappingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ServiceInfoMappingLocalServiceUtil} to access
	 * the service info mapping local service.
	 */

	public ServiceInfoMapping addServiceInfoMapping(long groupId, long companyId, long userId, String serviceCode,
			String serviceCodeDVCQG) throws PortalException {
		long serviceInfoMappingId = counterLocalService.increment(ServiceInfoMappingLocalServiceImpl.class.getName());
		ServiceInfoMapping serviceInfoMapping = serviceInfoMappingPersistence.create(serviceInfoMappingId);
		serviceInfoMapping.setGroupId(groupId);
		serviceInfoMapping.setCompanyId(companyId);

		User user = userLocalService.getUser(userId);
		Date now = new Date();
		serviceInfoMapping.setUserId(userId);
		serviceInfoMapping.setUserName(user.getFullName());
		serviceInfoMapping.setCreateDate(now);
		serviceInfoMapping.setModifiedDate(now);
		serviceInfoMapping.setServiceCode(serviceCode);
		serviceInfoMapping.setServiceCodeDVCQG(serviceCodeDVCQG);

		Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceInfo.class.getName());

		ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);

		if (indexer != null && serviceInfo != null) {

			indexer.reindex(ServiceInfo.class.getName(), serviceInfo.getServiceInfoId());
		}

		return serviceInfoMappingPersistence.update(serviceInfoMapping);
	}

	public boolean deleteServiceInfoMapping(long groupId, String serviceCode) {
		ServiceInfoMapping serviceInfoMapping = serviceInfoMappingPersistence.fetchByF_GID_SC(groupId, serviceCode);
		if (serviceInfoMapping != null) {
			try {
				serviceInfoMappingPersistence.remove(serviceInfoMapping.getServiceInfoMappingId());

				Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceInfo.class.getName());

				ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);

				if (indexer != null && serviceInfo != null) {

					indexer.reindex(ServiceInfo.class.getName(), serviceInfo.getServiceInfoId());
				}

				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public ServiceInfoMapping fetchDVCQGServiceCode(long groupId, String serviceCode) {
		return serviceInfoMappingPersistence.fetchByF_GID_SC(groupId, serviceCode);
	}

	// super_admin Generators
	@Override
	public ServiceInfoMapping adminProcessDelete(Long id) {

		ServiceInfoMapping object = serviceInfoMappingPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {

			try {

				Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceInfo.class.getName());

				ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(object.getGroupId(),
						object.getServiceCode());

				if (indexer != null && serviceInfo != null) {

					indexer.reindex(ServiceInfo.class.getName(), serviceInfo.getServiceInfoId());
				}

				serviceInfoMappingPersistence.remove(object);
			} catch (Exception e) {
				_log.error(e);
			}

		}

		return object;
	}

	@Override
	public ServiceInfoMapping adminProcessData(JSONObject objectData) {

		ServiceInfoMapping object = null;

		long serviceInfoMappingId = objectData.getLong(ServiceInfoMappingTerm.SERVICE_INFO_MAPPING_ID);
		long groupId = objectData.getLong(Field.GROUP_ID);

		if (serviceInfoMappingId > 0) {

			object = serviceInfoMappingPersistence.fetchByPrimaryKey(serviceInfoMappingId);
		} else {

			serviceInfoMappingId = CounterLocalServiceUtil.increment(ServiceInfoMapping.class.getName());
			object = serviceInfoMappingPersistence.create(serviceInfoMappingId);

			object.setGroupId(groupId);
			object.setCompanyId(objectData.getLong(Field.COMPANY_ID));
			object.setCreateDate(new Date());
		}

		object.setModifiedDate(new Date());
		object.setUserId(objectData.getLong(Field.USER_ID));
		object.setUserName(objectData.getString(Field.USER_NAME));

		object.setServiceCode(objectData.getString(ServiceInfoMappingTerm.SERVICE_CODE));
		object.setServiceCodeDVCQG(objectData.getString(ServiceInfoMappingTerm.SERVICE_CODE_DVCQG));

		object = serviceInfoMappingPersistence.update(object);

		try {

			Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceInfo.class.getName());

			ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(object.getGroupId(),
					object.getServiceCode());

			if (indexer != null && serviceInfo != null) {

				indexer.reindex(ServiceInfo.class.getName(), serviceInfo.getServiceInfoId());
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return object;
	}

	private Log _log = LogFactoryUtil.getLog(ServiceInfoMappingLocalServiceImpl.class.getName());
}