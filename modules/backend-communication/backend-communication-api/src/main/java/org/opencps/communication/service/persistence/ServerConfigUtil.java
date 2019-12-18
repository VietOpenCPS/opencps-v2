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

package org.opencps.communication.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.communication.model.ServerConfig;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the server config service. This utility wraps {@link org.opencps.communication.service.persistence.impl.ServerConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see ServerConfigPersistence
 * @see org.opencps.communication.service.persistence.impl.ServerConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class ServerConfigUtil {
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
	public static void clearCache(ServerConfig serverConfig) {
		getPersistence().clearCache(serverConfig);
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
	public static List<ServerConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServerConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServerConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServerConfig update(ServerConfig serverConfig) {
		return getPersistence().update(serverConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServerConfig update(ServerConfig serverConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(serverConfig, serviceContext);
	}

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByG_CF_CD(long groupId, String serverNo)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().findByG_CF_CD(groupId, serverNo);
	}

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByG_CF_CD(long groupId, String serverNo) {
		return getPersistence().fetchByG_CF_CD(groupId, serverNo);
	}

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByG_CF_CD(long groupId, String serverNo,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_CF_CD(groupId, serverNo, retrieveFromCache);
	}

	/**
	* Removes the server config where groupId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the server config that was removed
	*/
	public static ServerConfig removeByG_CF_CD(long groupId, String serverNo)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().removeByG_CF_CD(groupId, serverNo);
	}

	/**
	* Returns the number of server configs where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the number of matching server configs
	*/
	public static int countByG_CF_CD(long groupId, String serverNo) {
		return getPersistence().countByG_CF_CD(groupId, serverNo);
	}

	/**
	* Returns the server config where serverNo = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param serverNo the server no
	* @return the matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByCF_CD(String serverNo)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().findByCF_CD(serverNo);
	}

	/**
	* Returns the server config where serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serverNo the server no
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByCF_CD(String serverNo) {
		return getPersistence().fetchByCF_CD(serverNo);
	}

	/**
	* Returns the server config where serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serverNo the server no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByCF_CD(String serverNo,
		boolean retrieveFromCache) {
		return getPersistence().fetchByCF_CD(serverNo, retrieveFromCache);
	}

	/**
	* Removes the server config where serverNo = &#63; from the database.
	*
	* @param serverNo the server no
	* @return the server config that was removed
	*/
	public static ServerConfig removeByCF_CD(String serverNo)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().removeByCF_CD(serverNo);
	}

	/**
	* Returns the number of server configs where serverNo = &#63;.
	*
	* @param serverNo the server no
	* @return the number of matching server configs
	*/
	public static int countByCF_CD(String serverNo) {
		return getPersistence().countByCF_CD(serverNo);
	}

	/**
	* Returns the server config where serverName = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param serverName the server name
	* @return the matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByCF_NM(String serverName)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().findByCF_NM(serverName);
	}

	/**
	* Returns the server config where serverName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serverName the server name
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByCF_NM(String serverName) {
		return getPersistence().fetchByCF_NM(serverName);
	}

	/**
	* Returns the server config where serverName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serverName the server name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByCF_NM(String serverName,
		boolean retrieveFromCache) {
		return getPersistence().fetchByCF_NM(serverName, retrieveFromCache);
	}

	/**
	* Removes the server config where serverName = &#63; from the database.
	*
	* @param serverName the server name
	* @return the server config that was removed
	*/
	public static ServerConfig removeByCF_NM(String serverName)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().removeByCF_NM(serverName);
	}

	/**
	* Returns the number of server configs where serverName = &#63;.
	*
	* @param serverName the server name
	* @return the number of matching server configs
	*/
	public static int countByCF_NM(String serverName) {
		return getPersistence().countByCF_NM(serverName);
	}

	/**
	* Returns all the server configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching server configs
	*/
	public static List<ServerConfig> findByCF_GID(long groupId) {
		return getPersistence().findByCF_GID(groupId);
	}

	/**
	* Returns a range of all the server configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @return the range of matching server configs
	*/
	public static List<ServerConfig> findByCF_GID(long groupId, int start,
		int end) {
		return getPersistence().findByCF_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the server configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching server configs
	*/
	public static List<ServerConfig> findByCF_GID(long groupId, int start,
		int end, OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence()
				   .findByCF_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the server configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching server configs
	*/
	public static List<ServerConfig> findByCF_GID(long groupId, int start,
		int end, OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCF_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first server config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByCF_GID_First(long groupId,
		OrderByComparator<ServerConfig> orderByComparator)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().findByCF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first server config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByCF_GID_First(long groupId,
		OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence().fetchByCF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last server config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByCF_GID_Last(long groupId,
		OrderByComparator<ServerConfig> orderByComparator)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().findByCF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last server config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByCF_GID_Last(long groupId,
		OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence().fetchByCF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the server configs before and after the current server config in the ordered set where groupId = &#63;.
	*
	* @param serverConfigId the primary key of the current server config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next server config
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public static ServerConfig[] findByCF_GID_PrevAndNext(long serverConfigId,
		long groupId, OrderByComparator<ServerConfig> orderByComparator)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence()
				   .findByCF_GID_PrevAndNext(serverConfigId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the server configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByCF_GID(long groupId) {
		getPersistence().removeByCF_GID(groupId);
	}

	/**
	* Returns the number of server configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching server configs
	*/
	public static int countByCF_GID(long groupId) {
		return getPersistence().countByCF_GID(groupId);
	}

	/**
	* Returns all the server configs where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @return the matching server configs
	*/
	public static List<ServerConfig> findByG_P(long groupId, String protocol) {
		return getPersistence().findByG_P(groupId, protocol);
	}

	/**
	* Returns a range of all the server configs where groupId = &#63; and protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @return the range of matching server configs
	*/
	public static List<ServerConfig> findByG_P(long groupId, String protocol,
		int start, int end) {
		return getPersistence().findByG_P(groupId, protocol, start, end);
	}

	/**
	* Returns an ordered range of all the server configs where groupId = &#63; and protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching server configs
	*/
	public static List<ServerConfig> findByG_P(long groupId, String protocol,
		int start, int end, OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence()
				   .findByG_P(groupId, protocol, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the server configs where groupId = &#63; and protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching server configs
	*/
	public static List<ServerConfig> findByG_P(long groupId, String protocol,
		int start, int end, OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_P(groupId, protocol, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first server config in the ordered set where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByG_P_First(long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence()
				   .findByG_P_First(groupId, protocol, orderByComparator);
	}

	/**
	* Returns the first server config in the ordered set where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByG_P_First(long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_First(groupId, protocol, orderByComparator);
	}

	/**
	* Returns the last server config in the ordered set where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByG_P_Last(long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence()
				   .findByG_P_Last(groupId, protocol, orderByComparator);
	}

	/**
	* Returns the last server config in the ordered set where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByG_P_Last(long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence()
				   .fetchByG_P_Last(groupId, protocol, orderByComparator);
	}

	/**
	* Returns the server configs before and after the current server config in the ordered set where groupId = &#63; and protocol = &#63;.
	*
	* @param serverConfigId the primary key of the current server config
	* @param groupId the group ID
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next server config
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public static ServerConfig[] findByG_P_PrevAndNext(long serverConfigId,
		long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence()
				   .findByG_P_PrevAndNext(serverConfigId, groupId, protocol,
			orderByComparator);
	}

	/**
	* Removes all the server configs where groupId = &#63; and protocol = &#63; from the database.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	*/
	public static void removeByG_P(long groupId, String protocol) {
		getPersistence().removeByG_P(groupId, protocol);
	}

	/**
	* Returns the number of server configs where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @return the number of matching server configs
	*/
	public static int countByG_P(long groupId, String protocol) {
		return getPersistence().countByG_P(groupId, protocol);
	}

	/**
	* Returns all the server configs where protocol = &#63;.
	*
	* @param protocol the protocol
	* @return the matching server configs
	*/
	public static List<ServerConfig> findByP(String protocol) {
		return getPersistence().findByP(protocol);
	}

	/**
	* Returns a range of all the server configs where protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @return the range of matching server configs
	*/
	public static List<ServerConfig> findByP(String protocol, int start, int end) {
		return getPersistence().findByP(protocol, start, end);
	}

	/**
	* Returns an ordered range of all the server configs where protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching server configs
	*/
	public static List<ServerConfig> findByP(String protocol, int start,
		int end, OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence().findByP(protocol, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the server configs where protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching server configs
	*/
	public static List<ServerConfig> findByP(String protocol, int start,
		int end, OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByP(protocol, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first server config in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByP_First(String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().findByP_First(protocol, orderByComparator);
	}

	/**
	* Returns the first server config in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByP_First(String protocol,
		OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence().fetchByP_First(protocol, orderByComparator);
	}

	/**
	* Returns the last server config in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByP_Last(String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().findByP_Last(protocol, orderByComparator);
	}

	/**
	* Returns the last server config in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByP_Last(String protocol,
		OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence().fetchByP_Last(protocol, orderByComparator);
	}

	/**
	* Returns the server configs before and after the current server config in the ordered set where protocol = &#63;.
	*
	* @param serverConfigId the primary key of the current server config
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next server config
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public static ServerConfig[] findByP_PrevAndNext(long serverConfigId,
		String protocol, OrderByComparator<ServerConfig> orderByComparator)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence()
				   .findByP_PrevAndNext(serverConfigId, protocol,
			orderByComparator);
	}

	/**
	* Removes all the server configs where protocol = &#63; from the database.
	*
	* @param protocol the protocol
	*/
	public static void removeByP(String protocol) {
		getPersistence().removeByP(protocol);
	}

	/**
	* Returns the number of server configs where protocol = &#63;.
	*
	* @param protocol the protocol
	* @return the number of matching server configs
	*/
	public static int countByP(String protocol) {
		return getPersistence().countByP(protocol);
	}

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public static ServerConfig findByF_G_S_P(long groupId, String serverNo,
		String protocol)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().findByF_G_S_P(groupId, serverNo, protocol);
	}

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByF_G_S_P(long groupId, String serverNo,
		String protocol) {
		return getPersistence().fetchByF_G_S_P(groupId, serverNo, protocol);
	}

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public static ServerConfig fetchByF_G_S_P(long groupId, String serverNo,
		String protocol, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_G_S_P(groupId, serverNo, protocol,
			retrieveFromCache);
	}

	/**
	* Removes the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the server config that was removed
	*/
	public static ServerConfig removeByF_G_S_P(long groupId, String serverNo,
		String protocol)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().removeByF_G_S_P(groupId, serverNo, protocol);
	}

	/**
	* Returns the number of server configs where groupId = &#63; and serverNo = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the number of matching server configs
	*/
	public static int countByF_G_S_P(long groupId, String serverNo,
		String protocol) {
		return getPersistence().countByF_G_S_P(groupId, serverNo, protocol);
	}

	/**
	* Caches the server config in the entity cache if it is enabled.
	*
	* @param serverConfig the server config
	*/
	public static void cacheResult(ServerConfig serverConfig) {
		getPersistence().cacheResult(serverConfig);
	}

	/**
	* Caches the server configs in the entity cache if it is enabled.
	*
	* @param serverConfigs the server configs
	*/
	public static void cacheResult(List<ServerConfig> serverConfigs) {
		getPersistence().cacheResult(serverConfigs);
	}

	/**
	* Creates a new server config with the primary key. Does not add the server config to the database.
	*
	* @param serverConfigId the primary key for the new server config
	* @return the new server config
	*/
	public static ServerConfig create(long serverConfigId) {
		return getPersistence().create(serverConfigId);
	}

	/**
	* Removes the server config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config that was removed
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public static ServerConfig remove(long serverConfigId)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().remove(serverConfigId);
	}

	public static ServerConfig updateImpl(ServerConfig serverConfig) {
		return getPersistence().updateImpl(serverConfig);
	}

	/**
	* Returns the server config with the primary key or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public static ServerConfig findByPrimaryKey(long serverConfigId)
		throws org.opencps.communication.exception.NoSuchServerConfigException {
		return getPersistence().findByPrimaryKey(serverConfigId);
	}

	/**
	* Returns the server config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config, or <code>null</code> if a server config with the primary key could not be found
	*/
	public static ServerConfig fetchByPrimaryKey(long serverConfigId) {
		return getPersistence().fetchByPrimaryKey(serverConfigId);
	}

	public static java.util.Map<java.io.Serializable, ServerConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the server configs.
	*
	* @return the server configs
	*/
	public static List<ServerConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the server configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @return the range of server configs
	*/
	public static List<ServerConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the server configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of server configs
	*/
	public static List<ServerConfig> findAll(int start, int end,
		OrderByComparator<ServerConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the server configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of server configs
	*/
	public static List<ServerConfig> findAll(int start, int end,
		OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the server configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of server configs.
	*
	* @return the number of server configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ServerConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServerConfigPersistence, ServerConfigPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServerConfigPersistence.class);

		ServiceTracker<ServerConfigPersistence, ServerConfigPersistence> serviceTracker =
			new ServiceTracker<ServerConfigPersistence, ServerConfigPersistence>(bundle.getBundleContext(),
				ServerConfigPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}