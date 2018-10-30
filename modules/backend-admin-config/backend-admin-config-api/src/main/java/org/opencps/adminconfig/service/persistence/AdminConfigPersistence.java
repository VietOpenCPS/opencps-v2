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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.adminconfig.exception.NoSuchAdminConfigException;
import org.opencps.adminconfig.model.AdminConfig;

/**
 * The persistence interface for the admin config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see org.opencps.adminconfig.service.persistence.impl.AdminConfigPersistenceImpl
 * @see AdminConfigUtil
 * @generated
 */
@ProviderType
public interface AdminConfigPersistence extends BasePersistence<AdminConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AdminConfigUtil} to access the admin config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the admin config where code = &#63; or throws a {@link NoSuchAdminConfigException} if it could not be found.
	*
	* @param code the code
	* @return the matching admin config
	* @throws NoSuchAdminConfigException if a matching admin config could not be found
	*/
	public AdminConfig findByF_Code(String code)
		throws NoSuchAdminConfigException;

	/**
	* Returns the admin config where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param code the code
	* @return the matching admin config, or <code>null</code> if a matching admin config could not be found
	*/
	public AdminConfig fetchByF_Code(String code);

	/**
	* Returns the admin config where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param code the code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching admin config, or <code>null</code> if a matching admin config could not be found
	*/
	public AdminConfig fetchByF_Code(String code, boolean retrieveFromCache);

	/**
	* Removes the admin config where code = &#63; from the database.
	*
	* @param code the code
	* @return the admin config that was removed
	*/
	public AdminConfig removeByF_Code(String code)
		throws NoSuchAdminConfigException;

	/**
	* Returns the number of admin configs where code = &#63;.
	*
	* @param code the code
	* @return the number of matching admin configs
	*/
	public int countByF_Code(String code);

	/**
	* Caches the admin config in the entity cache if it is enabled.
	*
	* @param adminConfig the admin config
	*/
	public void cacheResult(AdminConfig adminConfig);

	/**
	* Caches the admin configs in the entity cache if it is enabled.
	*
	* @param adminConfigs the admin configs
	*/
	public void cacheResult(java.util.List<AdminConfig> adminConfigs);

	/**
	* Creates a new admin config with the primary key. Does not add the admin config to the database.
	*
	* @param id the primary key for the new admin config
	* @return the new admin config
	*/
	public AdminConfig create(long id);

	/**
	* Removes the admin config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the admin config
	* @return the admin config that was removed
	* @throws NoSuchAdminConfigException if a admin config with the primary key could not be found
	*/
	public AdminConfig remove(long id) throws NoSuchAdminConfigException;

	public AdminConfig updateImpl(AdminConfig adminConfig);

	/**
	* Returns the admin config with the primary key or throws a {@link NoSuchAdminConfigException} if it could not be found.
	*
	* @param id the primary key of the admin config
	* @return the admin config
	* @throws NoSuchAdminConfigException if a admin config with the primary key could not be found
	*/
	public AdminConfig findByPrimaryKey(long id)
		throws NoSuchAdminConfigException;

	/**
	* Returns the admin config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the admin config
	* @return the admin config, or <code>null</code> if a admin config with the primary key could not be found
	*/
	public AdminConfig fetchByPrimaryKey(long id);

	@Override
	public java.util.Map<java.io.Serializable, AdminConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the admin configs.
	*
	* @return the admin configs
	*/
	public java.util.List<AdminConfig> findAll();

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
	public java.util.List<AdminConfig> findAll(int start, int end);

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
	public java.util.List<AdminConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AdminConfig> orderByComparator);

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
	public java.util.List<AdminConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AdminConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the admin configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of admin configs.
	*
	* @return the number of admin configs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}