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

package org.opencps.adminconfig.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.adminconfig.model.AdminConfig;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the admin config service. This utility wraps {@link org.opencps.adminconfig.service.persistence.impl.AdminConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see AdminConfigPersistence
 * @see org.opencps.adminconfig.service.persistence.impl.AdminConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class AdminConfigUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AdminConfig adminConfig) {
		getPersistence().clearCache(adminConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AdminConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AdminConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AdminConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AdminConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AdminConfig update(AdminConfig adminConfig) {
		return getPersistence().update(adminConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AdminConfig update(AdminConfig adminConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(adminConfig, serviceContext);
	}

	/**
	* Returns the admin config where code = &#63; or throws a {@link NoSuchAdminConfigException} if it could not be found.
	*
	* @param code the code
	* @return the matching admin config
	* @throws NoSuchAdminConfigException if a matching admin config could not be found
	*/
	public static AdminConfig findByF_Code(String code)
		throws org.opencps.adminconfig.exception.NoSuchAdminConfigException {
		return getPersistence().findByF_Code(code);
	}

	/**
	* Returns the admin config where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param code the code
	* @return the matching admin config, or <code>null</code> if a matching admin config could not be found
	*/
	public static AdminConfig fetchByF_Code(String code) {
		return getPersistence().fetchByF_Code(code);
	}

	/**
	* Returns the admin config where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param code the code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching admin config, or <code>null</code> if a matching admin config could not be found
	*/
	public static AdminConfig fetchByF_Code(String code,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_Code(code, retrieveFromCache);
	}

	/**
	* Removes the admin config where code = &#63; from the database.
	*
	* @param code the code
	* @return the admin config that was removed
	*/
	public static AdminConfig removeByF_Code(String code)
		throws org.opencps.adminconfig.exception.NoSuchAdminConfigException {
		return getPersistence().removeByF_Code(code);
	}

	/**
	* Returns the number of admin configs where code = &#63;.
	*
	* @param code the code
	* @return the number of matching admin configs
	*/
	public static int countByF_Code(String code) {
		return getPersistence().countByF_Code(code);
	}

	/**
	* Caches the admin config in the entity cache if it is enabled.
	*
	* @param adminConfig the admin config
	*/
	public static void cacheResult(AdminConfig adminConfig) {
		getPersistence().cacheResult(adminConfig);
	}

	/**
	* Caches the admin configs in the entity cache if it is enabled.
	*
	* @param adminConfigs the admin configs
	*/
	public static void cacheResult(List<AdminConfig> adminConfigs) {
		getPersistence().cacheResult(adminConfigs);
	}

	/**
	* Creates a new admin config with the primary key. Does not add the admin config to the database.
	*
	* @param id the primary key for the new admin config
	* @return the new admin config
	*/
	public static AdminConfig create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the admin config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the admin config
	* @return the admin config that was removed
	* @throws NoSuchAdminConfigException if a admin config with the primary key could not be found
	*/
	public static AdminConfig remove(long id)
		throws org.opencps.adminconfig.exception.NoSuchAdminConfigException {
		return getPersistence().remove(id);
	}

	public static AdminConfig updateImpl(AdminConfig adminConfig) {
		return getPersistence().updateImpl(adminConfig);
	}

	/**
	* Returns the admin config with the primary key or throws a {@link NoSuchAdminConfigException} if it could not be found.
	*
	* @param id the primary key of the admin config
	* @return the admin config
	* @throws NoSuchAdminConfigException if a admin config with the primary key could not be found
	*/
	public static AdminConfig findByPrimaryKey(long id)
		throws org.opencps.adminconfig.exception.NoSuchAdminConfigException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the admin config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the admin config
	* @return the admin config, or <code>null</code> if a admin config with the primary key could not be found
	*/
	public static AdminConfig fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, AdminConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the admin configs.
	*
	* @return the admin configs
	*/
	public static List<AdminConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the admin configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdminConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of admin configs
	* @param end the upper bound of the range of admin configs (not inclusive)
	* @return the range of admin configs
	*/
	public static List<AdminConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the admin configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdminConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of admin configs
	* @param end the upper bound of the range of admin configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of admin configs
	*/
	public static List<AdminConfig> findAll(int start, int end,
		OrderByComparator<AdminConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the admin configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdminConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of admin configs
	* @param end the upper bound of the range of admin configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of admin configs
	*/
	public static List<AdminConfig> findAll(int start, int end,
		OrderByComparator<AdminConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the admin configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of admin configs.
	*
	* @return the number of admin configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AdminConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AdminConfigPersistence, AdminConfigPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AdminConfigPersistence.class);

		ServiceTracker<AdminConfigPersistence, AdminConfigPersistence> serviceTracker =
			new ServiceTracker<AdminConfigPersistence, AdminConfigPersistence>(bundle.getBundleContext(),
				AdminConfigPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}