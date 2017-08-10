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

package org.opencps.backend.processmgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.backend.processmgt.model.StepAllowance;
import org.opencps.backend.processmgt.service.persistence.StepAllowancePK;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for StepAllowance. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see StepAllowanceLocalServiceUtil
 * @see org.opencps.backend.processmgt.service.base.StepAllowanceLocalServiceBaseImpl
 * @see org.opencps.backend.processmgt.service.impl.StepAllowanceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface StepAllowanceLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StepAllowanceLocalServiceUtil} to access the step allowance local service. Add custom service methods to {@link org.opencps.backend.processmgt.service.impl.StepAllowanceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of step allowances.
	*
	* @return the number of step allowances
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getStepAllowancesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the step allowances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @return the range of step allowances
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StepAllowance> getStepAllowances(int start, int end);

	/**
	* Returns all the step allowances matching the UUID and company.
	*
	* @param uuid the UUID of the step allowances
	* @param companyId the primary key of the company
	* @return the matching step allowances, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StepAllowance> getStepAllowancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of step allowances matching the UUID and company.
	*
	* @param uuid the UUID of the step allowances
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching step allowances, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StepAllowance> getStepAllowancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<StepAllowance> orderByComparator);

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

	/**
	* Adds the step allowance to the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowance the step allowance
	* @return the step allowance that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public StepAllowance addStepAllowance(StepAllowance stepAllowance);

	/**
	* Creates a new step allowance with the primary key. Does not add the step allowance to the database.
	*
	* @param stepAllowancePK the primary key for the new step allowance
	* @return the new step allowance
	*/
	public StepAllowance createStepAllowance(StepAllowancePK stepAllowancePK);

	/**
	* Deletes the step allowance from the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowance the step allowance
	* @return the step allowance that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public StepAllowance deleteStepAllowance(StepAllowance stepAllowance);

	/**
	* Deletes the step allowance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance that was removed
	* @throws PortalException if a step allowance with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public StepAllowance deleteStepAllowance(StepAllowancePK stepAllowancePK)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StepAllowance fetchStepAllowance(StepAllowancePK stepAllowancePK);

	/**
	* Returns the step allowance matching the UUID and group.
	*
	* @param uuid the step allowance's UUID
	* @param groupId the primary key of the group
	* @return the matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StepAllowance fetchStepAllowanceByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the step allowance with the primary key.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance
	* @throws PortalException if a step allowance with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StepAllowance getStepAllowance(StepAllowancePK stepAllowancePK)
		throws PortalException;

	/**
	* Returns the step allowance matching the UUID and group.
	*
	* @param uuid the step allowance's UUID
	* @param groupId the primary key of the group
	* @return the matching step allowance
	* @throws PortalException if a matching step allowance could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StepAllowance getStepAllowanceByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Updates the step allowance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stepAllowance the step allowance
	* @return the step allowance that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public StepAllowance updateStepAllowance(StepAllowance stepAllowance);
}