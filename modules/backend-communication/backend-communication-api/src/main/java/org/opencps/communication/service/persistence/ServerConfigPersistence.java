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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.communication.exception.NoSuchServerConfigException;
import org.opencps.communication.model.ServerConfig;

/**
 * The persistence interface for the server config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see org.opencps.communication.service.persistence.impl.ServerConfigPersistenceImpl
 * @see ServerConfigUtil
 * @generated
 */
@ProviderType
public interface ServerConfigPersistence extends BasePersistence<ServerConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServerConfigUtil} to access the server config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByG_CF_CD(long groupId, String serverNo)
		throws NoSuchServerConfigException;

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByG_CF_CD(long groupId, String serverNo);

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByG_CF_CD(long groupId, String serverNo,
		boolean retrieveFromCache);

	/**
	* Removes the server config where groupId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the server config that was removed
	*/
	public ServerConfig removeByG_CF_CD(long groupId, String serverNo)
		throws NoSuchServerConfigException;

	/**
	* Returns the number of server configs where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the number of matching server configs
	*/
	public int countByG_CF_CD(long groupId, String serverNo);

	/**
	* Returns the server config where serverNo = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param serverNo the server no
	* @return the matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByCF_CD(String serverNo)
		throws NoSuchServerConfigException;

	/**
	* Returns the server config where serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serverNo the server no
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByCF_CD(String serverNo);

	/**
	* Returns the server config where serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serverNo the server no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByCF_CD(String serverNo, boolean retrieveFromCache);

	/**
	* Removes the server config where serverNo = &#63; from the database.
	*
	* @param serverNo the server no
	* @return the server config that was removed
	*/
	public ServerConfig removeByCF_CD(String serverNo)
		throws NoSuchServerConfigException;

	/**
	* Returns the number of server configs where serverNo = &#63;.
	*
	* @param serverNo the server no
	* @return the number of matching server configs
	*/
	public int countByCF_CD(String serverNo);

	/**
	* Returns the server config where serverName = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param serverName the server name
	* @return the matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByCF_NM(String serverName)
		throws NoSuchServerConfigException;

	/**
	* Returns the server config where serverName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serverName the server name
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByCF_NM(String serverName);

	/**
	* Returns the server config where serverName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serverName the server name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByCF_NM(String serverName,
		boolean retrieveFromCache);

	/**
	* Removes the server config where serverName = &#63; from the database.
	*
	* @param serverName the server name
	* @return the server config that was removed
	*/
	public ServerConfig removeByCF_NM(String serverName)
		throws NoSuchServerConfigException;

	/**
	* Returns the number of server configs where serverName = &#63;.
	*
	* @param serverName the server name
	* @return the number of matching server configs
	*/
	public int countByCF_NM(String serverName);

	/**
	* Returns all the server configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching server configs
	*/
	public java.util.List<ServerConfig> findByCF_GID(long groupId);

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
	public java.util.List<ServerConfig> findByCF_GID(long groupId, int start,
		int end);

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
	public java.util.List<ServerConfig> findByCF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

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
	public java.util.List<ServerConfig> findByCF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first server config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByCF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Returns the first server config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByCF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

	/**
	* Returns the last server config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByCF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Returns the last server config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByCF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

	/**
	* Returns the server configs before and after the current server config in the ordered set where groupId = &#63;.
	*
	* @param serverConfigId the primary key of the current server config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next server config
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public ServerConfig[] findByCF_GID_PrevAndNext(long serverConfigId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Removes all the server configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByCF_GID(long groupId);

	/**
	* Returns the number of server configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching server configs
	*/
	public int countByCF_GID(long groupId);

	/**
	* Returns all the server configs where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @return the matching server configs
	*/
	public java.util.List<ServerConfig> findByG_P(long groupId, String protocol);

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
	public java.util.List<ServerConfig> findByG_P(long groupId,
		String protocol, int start, int end);

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
	public java.util.List<ServerConfig> findByG_P(long groupId,
		String protocol, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

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
	public java.util.List<ServerConfig> findByG_P(long groupId,
		String protocol, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first server config in the ordered set where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByG_P_First(long groupId, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Returns the first server config in the ordered set where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByG_P_First(long groupId, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

	/**
	* Returns the last server config in the ordered set where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByG_P_Last(long groupId, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Returns the last server config in the ordered set where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByG_P_Last(long groupId, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

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
	public ServerConfig[] findByG_P_PrevAndNext(long serverConfigId,
		long groupId, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Removes all the server configs where groupId = &#63; and protocol = &#63; from the database.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	*/
	public void removeByG_P(long groupId, String protocol);

	/**
	* Returns the number of server configs where groupId = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param protocol the protocol
	* @return the number of matching server configs
	*/
	public int countByG_P(long groupId, String protocol);

	/**
	* Returns all the server configs where protocol = &#63;.
	*
	* @param protocol the protocol
	* @return the matching server configs
	*/
	public java.util.List<ServerConfig> findByP(String protocol);

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
	public java.util.List<ServerConfig> findByP(String protocol, int start,
		int end);

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
	public java.util.List<ServerConfig> findByP(String protocol, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

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
	public java.util.List<ServerConfig> findByP(String protocol, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first server config in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByP_First(String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Returns the first server config in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByP_First(String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

	/**
	* Returns the last server config in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByP_Last(String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Returns the last server config in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByP_Last(String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

	/**
	* Returns the server configs before and after the current server config in the ordered set where protocol = &#63;.
	*
	* @param serverConfigId the primary key of the current server config
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next server config
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public ServerConfig[] findByP_PrevAndNext(long serverConfigId,
		String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Removes all the server configs where protocol = &#63; from the database.
	*
	* @param protocol the protocol
	*/
	public void removeByP(String protocol);

	/**
	* Returns the number of server configs where protocol = &#63;.
	*
	* @param protocol the protocol
	* @return the number of matching server configs
	*/
	public int countByP(String protocol);

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findByF_G_S_P(long groupId, String serverNo,
		String protocol) throws NoSuchServerConfigException;

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByF_G_S_P(long groupId, String serverNo,
		String protocol);

	/**
	* Returns the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchByF_G_S_P(long groupId, String serverNo,
		String protocol, boolean retrieveFromCache);

	/**
	* Removes the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the server config that was removed
	*/
	public ServerConfig removeByF_G_S_P(long groupId, String serverNo,
		String protocol) throws NoSuchServerConfigException;

	/**
	* Returns the number of server configs where groupId = &#63; and serverNo = &#63; and protocol = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the number of matching server configs
	*/
	public int countByF_G_S_P(long groupId, String serverNo, String protocol);

	/**
	* Returns all the server configs where serverNo = &#63; and protocol = &#63;.
	*
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the matching server configs
	*/
	public java.util.List<ServerConfig> findBySNO_PT(String serverNo,
		String protocol);

	/**
	* Returns a range of all the server configs where serverNo = &#63; and protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serverNo the server no
	* @param protocol the protocol
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @return the range of matching server configs
	*/
	public java.util.List<ServerConfig> findBySNO_PT(String serverNo,
		String protocol, int start, int end);

	/**
	* Returns an ordered range of all the server configs where serverNo = &#63; and protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serverNo the server no
	* @param protocol the protocol
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching server configs
	*/
	public java.util.List<ServerConfig> findBySNO_PT(String serverNo,
		String protocol, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

	/**
	* Returns an ordered range of all the server configs where serverNo = &#63; and protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serverNo the server no
	* @param protocol the protocol
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching server configs
	*/
	public java.util.List<ServerConfig> findBySNO_PT(String serverNo,
		String protocol, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first server config in the ordered set where serverNo = &#63; and protocol = &#63;.
	*
	* @param serverNo the server no
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findBySNO_PT_First(String serverNo, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Returns the first server config in the ordered set where serverNo = &#63; and protocol = &#63;.
	*
	* @param serverNo the server no
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchBySNO_PT_First(String serverNo, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

	/**
	* Returns the last server config in the ordered set where serverNo = &#63; and protocol = &#63;.
	*
	* @param serverNo the server no
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config
	* @throws NoSuchServerConfigException if a matching server config could not be found
	*/
	public ServerConfig findBySNO_PT_Last(String serverNo, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Returns the last server config in the ordered set where serverNo = &#63; and protocol = &#63;.
	*
	* @param serverNo the server no
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching server config, or <code>null</code> if a matching server config could not be found
	*/
	public ServerConfig fetchBySNO_PT_Last(String serverNo, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

	/**
	* Returns the server configs before and after the current server config in the ordered set where serverNo = &#63; and protocol = &#63;.
	*
	* @param serverConfigId the primary key of the current server config
	* @param serverNo the server no
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next server config
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public ServerConfig[] findBySNO_PT_PrevAndNext(long serverConfigId,
		String serverNo, String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException;

	/**
	* Removes all the server configs where serverNo = &#63; and protocol = &#63; from the database.
	*
	* @param serverNo the server no
	* @param protocol the protocol
	*/
	public void removeBySNO_PT(String serverNo, String protocol);

	/**
	* Returns the number of server configs where serverNo = &#63; and protocol = &#63;.
	*
	* @param serverNo the server no
	* @param protocol the protocol
	* @return the number of matching server configs
	*/
	public int countBySNO_PT(String serverNo, String protocol);

	/**
	* Caches the server config in the entity cache if it is enabled.
	*
	* @param serverConfig the server config
	*/
	public void cacheResult(ServerConfig serverConfig);

	/**
	* Caches the server configs in the entity cache if it is enabled.
	*
	* @param serverConfigs the server configs
	*/
	public void cacheResult(java.util.List<ServerConfig> serverConfigs);

	/**
	* Creates a new server config with the primary key. Does not add the server config to the database.
	*
	* @param serverConfigId the primary key for the new server config
	* @return the new server config
	*/
	public ServerConfig create(long serverConfigId);

	/**
	* Removes the server config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config that was removed
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public ServerConfig remove(long serverConfigId)
		throws NoSuchServerConfigException;

	public ServerConfig updateImpl(ServerConfig serverConfig);

	/**
	* Returns the server config with the primary key or throws a {@link NoSuchServerConfigException} if it could not be found.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config
	* @throws NoSuchServerConfigException if a server config with the primary key could not be found
	*/
	public ServerConfig findByPrimaryKey(long serverConfigId)
		throws NoSuchServerConfigException;

	/**
	* Returns the server config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config, or <code>null</code> if a server config with the primary key could not be found
	*/
	public ServerConfig fetchByPrimaryKey(long serverConfigId);

	@Override
	public java.util.Map<java.io.Serializable, ServerConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the server configs.
	*
	* @return the server configs
	*/
	public java.util.List<ServerConfig> findAll();

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
	public java.util.List<ServerConfig> findAll(int start, int end);

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
	public java.util.List<ServerConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator);

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
	public java.util.List<ServerConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the server configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of server configs.
	*
	* @return the number of server configs
	*/
	public int countAll();
}