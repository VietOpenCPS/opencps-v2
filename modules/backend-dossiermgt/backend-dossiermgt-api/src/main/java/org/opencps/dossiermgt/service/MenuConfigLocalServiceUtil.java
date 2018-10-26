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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MenuConfig. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.MenuConfigLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see MenuConfigLocalService
 * @see org.opencps.dossiermgt.service.base.MenuConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.MenuConfigLocalServiceImpl
 * @generated
 */
@ProviderType
public class MenuConfigLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.MenuConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.MenuConfig addMenuConfig(
		long userId, long groupId, String menuGroup, String menuName,
		Integer order, Integer menuType, String queryParams,
		String tableConfig, String buttonConfig)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMenuConfig(userId, groupId, menuGroup, menuName, order,
			menuType, queryParams, tableConfig, buttonConfig);
	}

	/**
	* Adds the menu config to the database. Also notifies the appropriate model listeners.
	*
	* @param menuConfig the menu config
	* @return the menu config that was added
	*/
	public static org.opencps.dossiermgt.model.MenuConfig addMenuConfig(
		org.opencps.dossiermgt.model.MenuConfig menuConfig) {
		return getService().addMenuConfig(menuConfig);
	}

	public static org.opencps.dossiermgt.model.MenuConfig adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.MenuConfig adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new menu config with the primary key. Does not add the menu config to the database.
	*
	* @param menuConfigId the primary key for the new menu config
	* @return the new menu config
	*/
	public static org.opencps.dossiermgt.model.MenuConfig createMenuConfig(
		long menuConfigId) {
		return getService().createMenuConfig(menuConfigId);
	}

	/**
	* Deletes the menu config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config that was removed
	* @throws PortalException if a menu config with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.MenuConfig deleteMenuConfig(
		long menuConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMenuConfig(menuConfigId);
	}

	/**
	* Deletes the menu config from the database. Also notifies the appropriate model listeners.
	*
	* @param menuConfig the menu config
	* @return the menu config that was removed
	*/
	public static org.opencps.dossiermgt.model.MenuConfig deleteMenuConfig(
		org.opencps.dossiermgt.model.MenuConfig menuConfig) {
		return getService().deleteMenuConfig(menuConfig);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.dossiermgt.model.MenuConfig fetchMenuConfig(
		long menuConfigId) {
		return getService().fetchMenuConfig(menuConfigId);
	}

	/**
	* Returns the menu config matching the UUID and group.
	*
	* @param uuid the menu config's UUID
	* @param groupId the primary key of the group
	* @return the matching menu config, or <code>null</code> if a matching menu config could not be found
	*/
	public static org.opencps.dossiermgt.model.MenuConfig fetchMenuConfigByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchMenuConfigByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.MenuConfig getByCode(
		String menuGroup) {
		return getService().getByCode(menuGroup);
	}

	public static java.util.List<org.opencps.dossiermgt.model.MenuConfig> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.MenuConfig> getByMenus(
		long[] menuConfigIds) {
		return getService().getByMenus(menuConfigIds);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the menu config with the primary key.
	*
	* @param menuConfigId the primary key of the menu config
	* @return the menu config
	* @throws PortalException if a menu config with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.MenuConfig getMenuConfig(
		long menuConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMenuConfig(menuConfigId);
	}

	/**
	* Returns the menu config matching the UUID and group.
	*
	* @param uuid the menu config's UUID
	* @param groupId the primary key of the group
	* @return the matching menu config
	* @throws PortalException if a matching menu config could not be found
	*/
	public static org.opencps.dossiermgt.model.MenuConfig getMenuConfigByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMenuConfigByUuidAndGroupId(uuid, groupId);
	}

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
	public static java.util.List<org.opencps.dossiermgt.model.MenuConfig> getMenuConfigs(
		int start, int end) {
		return getService().getMenuConfigs(start, end);
	}

	/**
	* Returns all the menu configs matching the UUID and company.
	*
	* @param uuid the UUID of the menu configs
	* @param companyId the primary key of the company
	* @return the matching menu configs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.MenuConfig> getMenuConfigsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getMenuConfigsByUuidAndCompanyId(uuid, companyId);
	}

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
	public static java.util.List<org.opencps.dossiermgt.model.MenuConfig> getMenuConfigsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.MenuConfig> orderByComparator) {
		return getService()
				   .getMenuConfigsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of menu configs.
	*
	* @return the number of menu configs
	*/
	public static int getMenuConfigsCount() {
		return getService().getMenuConfigsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static org.opencps.dossiermgt.model.MenuConfig removeMenuConfig(
		long menuConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeMenuConfig(menuConfigId);
	}

	public static org.opencps.dossiermgt.model.MenuConfig updateMenuConfig(
		long menuConfigId, long userId, long groupId, String menuGroup,
		String menuName, Integer order, Integer menuType, String queryParams,
		String tableConfig, String buttonConfig)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateMenuConfig(menuConfigId, userId, groupId, menuGroup,
			menuName, order, menuType, queryParams, tableConfig, buttonConfig);
	}

	/**
	* Updates the menu config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param menuConfig the menu config
	* @return the menu config that was updated
	*/
	public static org.opencps.dossiermgt.model.MenuConfig updateMenuConfig(
		org.opencps.dossiermgt.model.MenuConfig menuConfig) {
		return getService().updateMenuConfig(menuConfig);
	}

	public static org.opencps.dossiermgt.model.MenuConfig updateMenuConfigDB(
		long userId, long groupId, String menuGroup, String menuName,
		Integer order, Integer menuType, String queryParams,
		String tableConfig, String buttonConfig)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateMenuConfigDB(userId, groupId, menuGroup, menuName,
			order, menuType, queryParams, tableConfig, buttonConfig);
	}

	public static MenuConfigLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MenuConfigLocalService, MenuConfigLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MenuConfigLocalService.class);

		ServiceTracker<MenuConfigLocalService, MenuConfigLocalService> serviceTracker =
			new ServiceTracker<MenuConfigLocalService, MenuConfigLocalService>(bundle.getBundleContext(),
				MenuConfigLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}