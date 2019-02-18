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

import java.util.List;

import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.service.base.ProcessStepRoleLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.persistence.ProcessStepRolePK;

import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCachable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the process step role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ProcessStepRoleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ProcessStepRoleLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil
 */
@ProviderType
public class ProcessStepRoleLocalServiceImpl extends ProcessStepRoleLocalServiceBaseImpl {
	private static final Log _log = LogFactoryUtil.getLog(ProcessStepRoleLocalServiceBaseImpl.class);
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil} to access
	 * the process step role local service.
	 */

	@ThreadLocalCachable
	public List<ProcessStepRole> findByP_S_ID(long processStepId) {
		return processStepRolePersistence.findByP_S_ID(processStepId);
	}

	public ProcessStepRole updateProcessStepRole(long processStepId, long roleId, boolean moderator, String condition,
			String roleCode, String roleName) {
		ProcessStepRolePK pk = new ProcessStepRolePK(processStepId, roleId);

		ProcessStepRole processStepRole = processStepRolePersistence.fetchByPrimaryKey(pk);

		if (Validator.isNull(processStepRole)) {
			processStepRole = processStepRolePersistence.create(pk);

			processStepRole.setModerator(moderator);
			processStepRole.setCondition(condition);
			processStepRole.setRoleCode(roleCode);
			processStepRole.setRoleName(roleName);
		} else {
			processStepRole = processStepRolePersistence.fetchByPrimaryKey(pk);
			
			processStepRole.setModerator(moderator);
			processStepRole.setCondition(condition);
			processStepRole.setRoleCode(roleCode);
			processStepRole.setRoleName(roleName);
		}

		processStepRolePersistence.update(processStepRole);

		// Update index

		Indexer<ProcessStep> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessStep.class);

		ProcessStep processStep = processStepPersistence.fetchByPrimaryKey(processStepId);

		try {
			indexer.reindex(processStep);
		} catch (SearchException e) {
//			e.printStackTrace();
			_log.error(e);
		}

		return processStepRole;
	}

	public ProcessStepRole removeProcessStepRole(long processStepId, long roleId) {

		ProcessStepRolePK pk = new ProcessStepRolePK(processStepId, roleId);

		ProcessStepRole processStepRole = processStepRolePersistence.fetchByPrimaryKey(pk);

		processStepRolePersistence.remove(processStepRole);

		// Update index

		Indexer<ProcessStep> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessStep.class);

		ProcessStep processStep = processStepPersistence.fetchByPrimaryKey(processStepId);

		try {
			indexer.reindex(processStep);
		} catch (SearchException e) {
//			e.printStackTrace();
			_log.error(e);
		}

		return processStepRole;

	}

	public ProcessStepRole updateProcessStepRoleDB(long userId, long groupId, long processStepId, long roleId,
			String roleCode, String roleName, boolean moderator, String condition, ServiceContext serviceContext) {

		ProcessStepRolePK pk = new ProcessStepRolePK(processStepId, roleId);
		ProcessStepRole processStepRole = processStepRolePersistence.fetchByPrimaryKey(pk);

		if (Validator.isNull(processStepRole)) {
			processStepRole = processStepRolePersistence.create(pk);

			processStepRole.setRoleCode(roleCode);
			processStepRole.setRoleName(roleName);
			processStepRole.setModerator(moderator);
			processStepRole.setCondition(condition);
		} else {
			processStepRole.setRoleName(roleName);
			processStepRole.setModerator(moderator);
			processStepRole.setCondition(condition);
		}

		return processStepRolePersistence.update(processStepRole);
	}

	public ProcessStepRole findByStepAndRole(long processStepId, long roleId) {
		return processStepRolePersistence.fetchByF_STEP_ROLE(processStepId, roleId);
	}

	public long getByRoleCode(String roleCode) {
		ProcessStepRole role = processStepRolePersistence.fetchByF_CODE(roleCode);
		if (role != null) {
			return role.getRoleId();
		}
		return 0;
	}
}