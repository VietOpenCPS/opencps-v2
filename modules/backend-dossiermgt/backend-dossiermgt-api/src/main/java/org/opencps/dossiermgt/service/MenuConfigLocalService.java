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

import org.opencps.dossiermgt.model.MenuConfig;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for MenuConfig. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see MenuConfigLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.MenuConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.MenuConfigLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MenuConfigLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MenuConfigLocalServiceUtil} to access the menu config local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.MenuConfigLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig addMenuConfig(long userId, long groupId,
		String menuGroup, String menuName, Integer order, Integer menuType,
		String queryParams, String tableConfig, String buttonConfig)
		throws PortalException;

	/**
	* Adds the menu config to the database. Also notifies the appropriate model listeners.
	*
	* @param menuConfig the menu config
	* @return the menu config that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig addMenuConfig(MenuConfig menuConfig);

	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public MenuConfig adminProcessDelete(Long id);

	/**
	* Creates a new menu config with the primary key. Does not add the menu config to the database.
	*
	* @param menuConfigId the primary key for the new menu config
	* @return the new menu config
	*/
	@Transactional(enabled = false)
	public MenuConfig createMenuConfig(long menuConfigId);

	/**
	* Deletes the menu config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config that was removed
	* @throws PortalException if a menu config with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public MenuConfig deleteMenuConfig(long menuConfigId)
		throws PortalException;

	/**
	* Deletes the menu config from the database. Also notifies the appropriate model listeners.
	*
	* @param menuConfig the menu config
	* @return the menu config that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public MenuConfig deleteMenuConfig(MenuConfig menuConfig);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public MenuConfig fetchMenuConfig(long menuConfigId);

	/**
	* Returns the menu config matching the UUID and group.
	*
	* @param uuid the menu config's UUID
	* @param groupId the primary key of the group
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MenuConfig fetchMenuConfigByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MenuConfig getByCode(String menuGroup);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MenuConfig> getByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MenuConfig> getByMenus(long[] menuConfigIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the menu config with the primary key.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config
	* @throws PortalException if a menu config with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MenuConfig getMenuConfig(long menuConfigId)
		throws PortalException;

	/**
	* Returns the menu config matching the UUID and group.
	*
	* @param uuid the menu config's UUID
	* @param groupId the primary key of the group
	* @return the matching menu config
	* @throws PortalException if a matching menu config could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MenuConfig getMenuConfigByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the menu configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.MenuConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @return the range of menu configs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MenuConfig> getMenuConfigs(int start, int end);

	/**
	* Returns all the menu configs matching the UUID and company.
	*
	* @param uuid the UUID of the menu configs
	* @param companyId the primary key of the company
	* @return the matching menu configs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MenuConfig> getMenuConfigsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of menu configs matching the UUID and company.
	*
	* @param uuid the UUID of the menu configs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of menu configs
	* @param end the upper bound of the range of menu configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching menu configs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MenuConfig> getMenuConfigsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<MenuConfig> orderByComparator);

	/**
	* Returns the number of menu configs.
	*
	* @return the number of menu configs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMenuConfigsCount();

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
	public MenuConfig removeMenuConfig(long menuConfigId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig updateMenuConfig(long menuConfigId, long userId,
		long groupId, String menuGroup, String menuName, Integer order,
		Integer menuType, String queryParams, String tableConfig,
		String buttonConfig) throws PortalException;

	/**
	* Updates the menu config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param menuConfig the menu config
	* @return the menu config that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig updateMenuConfig(MenuConfig menuConfig);

	@Indexable(type = IndexableType.REINDEX)
	public MenuConfig updateMenuConfigDB(long userId, long groupId,
		String menuGroup, String menuName, Integer order, Integer menuType,
		String queryParams, String tableConfig, String buttonConfig)
		throws PortalException;
}