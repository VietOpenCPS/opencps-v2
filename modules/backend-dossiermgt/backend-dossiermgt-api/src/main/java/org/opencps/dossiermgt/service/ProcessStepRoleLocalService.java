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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.service.persistence.ProcessStepRolePK;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ProcessStepRole. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ProcessStepRoleLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ProcessStepRoleLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ProcessStepRoleLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ProcessStepRoleLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessStepRoleLocalServiceUtil} to access the process step role local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ProcessStepRoleLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the process step role to the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRole the process step role
	* @return the process step role that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ProcessStepRole addProcessStepRole(ProcessStepRole processStepRole);

	/**
	* Creates a new process step role with the primary key. Does not add the process step role to the database.
	*
	* @param processStepRolePK the primary key for the new process step role
	* @return the new process step role
	*/
	@Transactional(enabled = false)
	public ProcessStepRole createProcessStepRole(
		ProcessStepRolePK processStepRolePK);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the process step role from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRole the process step role
	* @return the process step role that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ProcessStepRole deleteProcessStepRole(
		ProcessStepRole processStepRole);

	/**
	* Deletes the process step role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role that was removed
	* @throws PortalException if a process step role with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ProcessStepRole deleteProcessStepRole(
		ProcessStepRolePK processStepRolePK) throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessStepRole fetchProcessStepRole(
		ProcessStepRolePK processStepRolePK);

	public List<ProcessStepRole> findByP_S_ID(long processStepId);

	public ProcessStepRole findByStepAndRole(long processStepId, long roleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getByRoleCode(String roleCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the process step role with the primary key.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role
	* @throws PortalException if a process step role with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessStepRole getProcessStepRole(
		ProcessStepRolePK processStepRolePK) throws PortalException;

	/**
	* Returns a range of all the process step roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @return the range of process step roles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessStepRole> getProcessStepRoles(int start, int end);

	/**
	* Returns the number of process step roles.
	*
	* @return the number of process step roles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getProcessStepRolesCount();

	public ProcessStepRole removeProcessStepRole(long processStepId, long roleId);

	public ProcessStepRole updateProcessStepRole(long processStepId,
		long roleId, boolean moderator, String condition);

	/**
	* Updates the process step role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processStepRole the process step role
	* @return the process step role that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ProcessStepRole updateProcessStepRole(
		ProcessStepRole processStepRole);

	public ProcessStepRole updateProcessStepRoleDB(long userId, long groupId,
		long processStepId, long roleId, String roleCode, String roleName,
		boolean moderator, String condition, ServiceContext serviceContext);
}