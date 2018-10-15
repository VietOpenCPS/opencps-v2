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

import org.opencps.dossiermgt.model.StepConfig;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for StepConfig. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see StepConfigLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.StepConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.StepConfigLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface StepConfigLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StepConfigLocalServiceUtil} to access the step config local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.StepConfigLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public StepConfig addStepConfig(long userId, long groupId, String stepCode,
		String stepName, Integer stepType, String dossierStatus,
		String dossierSubStatus, String menuGroup, String menuStepName,
		String buttonConfig) throws PortalException;

	/**
	* Adds the step config to the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfig the step config
	* @return the step config that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public StepConfig addStepConfig(StepConfig stepConfig);

	/**
	* Creates a new step config with the primary key. Does not add the step config to the database.
	*
	* @param stepConfigId the primary key for the new step config
	* @return the new step config
	*/
	@Transactional(enabled = false)
	public StepConfig createStepConfig(long stepConfigId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the step config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config that was removed
	* @throws PortalException if a step config with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public StepConfig deleteStepConfig(long stepConfigId)
		throws PortalException;

	/**
	* Deletes the step config from the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfig the step config
	* @return the step config that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public StepConfig deleteStepConfig(StepConfig stepConfig);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public StepConfig fetchStepConfig(long stepConfigId);

	/**
	* Returns the step config matching the UUID and group.
	*
	* @param uuid the step config's UUID
	* @param groupId the primary key of the group
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StepConfig fetchStepConfigByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StepConfig getByCode(long groupId, String stepCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StepConfig> getByStepType(long groupId, int stepType);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StepConfig> getStepByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StepConfig> getStepByMainStatusAndSubStatus(long groupId,
		String mainStatus, String subStatus);

	/**
	* Returns the step config with the primary key.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config
	* @throws PortalException if a step config with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StepConfig getStepConfig(long stepConfigId)
		throws PortalException;

	/**
	* Returns the step config matching the UUID and group.
	*
	* @param uuid the step config's UUID
	* @param groupId the primary key of the group
	* @return the matching step config
	* @throws PortalException if a matching step config could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StepConfig getStepConfigByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the step configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of step configs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StepConfig> getStepConfigs(int start, int end);

	/**
	* Returns all the step configs matching the UUID and company.
	*
	* @param uuid the UUID of the step configs
	* @param companyId the primary key of the company
	* @return the matching step configs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StepConfig> getStepConfigsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of step configs matching the UUID and company.
	*
	* @param uuid the UUID of the step configs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching step configs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StepConfig> getStepConfigsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<StepConfig> orderByComparator);

	/**
	* Returns the number of step configs.
	*
	* @return the number of step configs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getStepConfigsCount();

	@Indexable(type = IndexableType.DELETE)
	public StepConfig removeStepConfig(long stepConfigId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public StepConfig updateStepConfig(long stepConfigId, long userId,
		long groupId, String stepCode, String stepName, Integer stepType,
		String dossierStatus, String dossierSubStatus, String menuGroup,
		String menuStepName, String buttonConfig) throws PortalException;

	/**
	* Updates the step config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stepConfig the step config
	* @return the step config that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public StepConfig updateStepConfig(StepConfig stepConfig);

	@Indexable(type = IndexableType.REINDEX)
	public StepConfig updateStepConfigDB(long userId, long groupId,
		String stepCode, String stepName, Integer stepType,
		String dossierStatus, String dossierSubStatus, String menuGroup,
		String menuStepName, String buttonConfig) throws PortalException;
}