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

import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCachable;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.base.ServiceProcessRoleLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the service process role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ServiceProcessRoleLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ServiceProcessRoleLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil
 */
@ProviderType
public class ServiceProcessRoleLocalServiceImpl extends ServiceProcessRoleLocalServiceBaseImpl {
	private static Log _log = LogFactoryUtil.getLog(ServiceProcessRoleLocalServiceImpl.class);
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil} to access
	 * the service process role local service.
	 */
	
	@ThreadLocalCachable
	public List<ServiceProcessRole> findByS_P_ID(long serviceProcessId) {
		return serviceProcessRolePersistence.findByP_S_ID(serviceProcessId);
	}

	public ServiceProcessRole updateServiceProcessRole(long groupId, long serviceProcessId, long roleId,
			boolean moderator, String condition, String roleCode, String roleName) {

		ServiceProcessRole serviceProcessRole = null;

		ServiceProcessRolePK pk = new ServiceProcessRolePK(serviceProcessId, roleId);

		serviceProcessRole = serviceProcessRolePersistence.fetchByPrimaryKey(pk);

		if (Validator.isNotNull(serviceProcessRole)) {

			serviceProcessRole.setModerator(moderator);
			serviceProcessRole.setCondition(condition);
			serviceProcessRole.setRoleCode(roleCode);
			serviceProcessRole.setRoleName(roleName);
		} else {
			serviceProcessRole = serviceProcessRolePersistence.create(pk);

			serviceProcessRole.setModerator(moderator);
			serviceProcessRole.setCondition(condition);
			
			serviceProcessRole.setRoleCode(roleCode);
			serviceProcessRole.setRoleName(roleName);
		}

		serviceProcessRolePersistence.update(serviceProcessRole);

		// Add to Index

		Indexer<ServiceProcess> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceProcess.class);

		ServiceProcess serviceProcess = serviceProcessPersistence.fetchByPrimaryKey(serviceProcessId);

		try {
			indexer.reindex(serviceProcess);
		} catch (SearchException e) {
			// e.printStackTrace();
			_log.error(e);
		}

		return serviceProcessRole;

	}

	public ServiceProcessRole removeServiceProcessRole(long serviceProcessId, long roleId) {

		ServiceProcessRolePK pk = new ServiceProcessRolePK(serviceProcessId, roleId);

		ServiceProcessRole serviceProcessRole = serviceProcessRolePersistence.fetchByPrimaryKey(pk);

		serviceProcessRolePersistence.remove(serviceProcessRole);

		// Update Index
		Indexer<ServiceProcess> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceProcess.class);

		ServiceProcess serviceProcess = serviceProcessPersistence.fetchByPrimaryKey(serviceProcessId);

		try {
			indexer.reindex(serviceProcess);
		} catch (SearchException e) {
			// e.printStackTrace();
			_log.error(e);
		}

		return serviceProcessRole;

	}

	public void updateServiceProcessRoleDB(long groupId, long serviceProcessId, long roleId, String roleCode,
			String roleName, boolean moderator, String condition, ServiceContext serviceContext) {

		ServiceProcessRolePK pk = new ServiceProcessRolePK(serviceProcessId, roleId);
		ServiceProcessRole serviceProcessRole = serviceProcessRolePersistence.fetchByPrimaryKey(pk);

		if (Validator.isNull(serviceProcessRole)) {
			serviceProcessRole = serviceProcessRolePersistence.create(pk);

			serviceProcessRole.setRoleCode(roleCode);
			serviceProcessRole.setRoleName(roleName);
			serviceProcessRole.setModerator(moderator);
			serviceProcessRole.setCondition(condition);
		} else {
			serviceProcessRole.setRoleName(roleName);
			serviceProcessRole.setModerator(moderator);
			serviceProcessRole.setCondition(condition);
		}

		serviceProcessRolePersistence.update(serviceProcessRole);
	}

	public long getByServiceRoleCode(String roleCode) {
		ServiceProcessRole serviceRole = serviceProcessRolePersistence.fetchByF_CODE(roleCode);
		if (serviceRole != null) {
			return serviceRole.getRoleId();
		}
		return 0;
	}

}