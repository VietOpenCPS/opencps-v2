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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.ActionConfig;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ActionConfig. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ActionConfigLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ActionConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ActionConfigLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ActionConfigLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActionConfigLocalServiceUtil} to access the action config local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ActionConfigLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the action config to the database. Also notifies the appropriate model listeners.
	*
	* @param actionConfig the action config
	* @return the action config that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig addActionConfig(ActionConfig actionConfig);

	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig addActionConfig(long userId, long groupId,
		String actionCode, String actionName, Boolean extraForm,
		String formScript, String sampleData, Boolean insideProcess,
		Integer userNote, Integer syncType, Boolean pending,
		Boolean rollbackable, String notificationType, String documentType,
		String mappingAction) throws PortalException;

	/**
	* Creates a new action config with the primary key. Does not add the action config to the database.
	*
	* @param actionConfigId the primary key for the new action config
	* @return the new action config
	*/
	@Transactional(enabled = false)
	public ActionConfig createActionConfig(long actionConfigId);

	/**
	* Deletes the action config from the database. Also notifies the appropriate model listeners.
	*
	* @param actionConfig the action config
	* @return the action config that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ActionConfig deleteActionConfig(ActionConfig actionConfig);

	/**
	* Deletes the action config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config that was removed
	* @throws PortalException if a action config with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ActionConfig deleteActionConfig(long actionConfigId)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ActionConfig fetchActionConfig(long actionConfigId);

	/**
	* Returns the action config matching the UUID and group.
	*
	* @param uuid the action config's UUID
	* @param groupId the primary key of the group
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionConfig fetchActionConfigByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the action config with the primary key.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config
	* @throws PortalException if a action config with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionConfig getActionConfig(long actionConfigId)
		throws PortalException;

	/**
	* Returns the action config matching the UUID and group.
	*
	* @param uuid the action config's UUID
	* @param groupId the primary key of the group
	* @return the matching action config
	* @throws PortalException if a matching action config could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionConfig getActionConfigByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the action configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of action configs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActionConfig> getActionConfigs(int start, int end);

	/**
	* Returns all the action configs matching the UUID and company.
	*
	* @param uuid the UUID of the action configs
	* @param companyId the primary key of the company
	* @return the matching action configs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActionConfig> getActionConfigsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of action configs matching the UUID and company.
	*
	* @param uuid the UUID of the action configs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching action configs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActionConfig> getActionConfigsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ActionConfig> orderByComparator);

	/**
	* Returns the number of action configs.
	*
	* @return the number of action configs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getActionConfigsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionConfig getByCode(long groupId, String actionCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActionConfig> getByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getForm(long groupId, String actionCode);

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

	@Indexable(type = IndexableType.DELETE)
	public ActionConfig removeActionConfig(long actionConfigId)
		throws PortalException;

	/**
	* Updates the action config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param actionConfig the action config
	* @return the action config that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig updateActionConfig(ActionConfig actionConfig);

	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig updateActionConfig(long actionConfigId, long userId,
		long groupId, String actionCode, String actionName, Boolean extraForm,
		String formScript, String sampleData, Boolean insideProcess,
		Integer userNote, Integer syncType, Boolean pending,
		Boolean rollbackable, String notificationType, String documentType,
		String mappingAction) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public ActionConfig updateActionConfigDB(long userId, long groupId,
		String actionCode, String actionName, Boolean extraForm,
		String sampleData, Boolean insideProcess, Integer userNote,
		Integer syncType, Integer eventType, Integer infoType,
		Boolean rollbackable, String notificationType, String documentType,
		String formConfig, String mappingAction) throws PortalException;
}