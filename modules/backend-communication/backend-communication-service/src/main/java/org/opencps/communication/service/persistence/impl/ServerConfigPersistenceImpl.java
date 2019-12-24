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

package org.opencps.communication.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.communication.exception.NoSuchServerConfigException;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.model.impl.ServerConfigImpl;
import org.opencps.communication.model.impl.ServerConfigModelImpl;
import org.opencps.communication.service.persistence.ServerConfigPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the server config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see ServerConfigPersistence
 * @see org.opencps.communication.service.persistence.ServerConfigUtil
 * @generated
 */
@ProviderType
public class ServerConfigPersistenceImpl extends BasePersistenceImpl<ServerConfig>
	implements ServerConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ServerConfigUtil} to access the server config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ServerConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_G_CF_CD = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_CF_CD",
			new String[] { Long.class.getName(), String.class.getName() },
			ServerConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ServerConfigModelImpl.SERVERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_CF_CD = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_CF_CD",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the server config where groupId = &#63; and serverNo = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the matching server config
	 * @throws NoSuchServerConfigException if a matching server config could not be found
	 */
	@Override
	public ServerConfig findByG_CF_CD(long groupId, String serverNo)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByG_CF_CD(groupId, serverNo);

		if (serverConfig == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", serverNo=");
			msg.append(serverNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServerConfigException(msg.toString());
		}

		return serverConfig;
	}

	/**
	 * Returns the server config where groupId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByG_CF_CD(long groupId, String serverNo) {
		return fetchByG_CF_CD(groupId, serverNo, true);
	}

	/**
	 * Returns the server config where groupId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByG_CF_CD(long groupId, String serverNo,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, serverNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_CF_CD,
					finderArgs, this);
		}

		if (result instanceof ServerConfig) {
			ServerConfig serverConfig = (ServerConfig)result;

			if ((groupId != serverConfig.getGroupId()) ||
					!Objects.equals(serverNo, serverConfig.getServerNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

			query.append(_FINDER_COLUMN_G_CF_CD_GROUPID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_G_CF_CD_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_G_CF_CD_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_G_CF_CD_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				List<ServerConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_CF_CD,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServerConfigPersistenceImpl.fetchByG_CF_CD(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServerConfig serverConfig = list.get(0);

					result = serverConfig;

					cacheResult(serverConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_CF_CD,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ServerConfig)result;
		}
	}

	/**
	 * Removes the server config where groupId = &#63; and serverNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the server config that was removed
	 */
	@Override
	public ServerConfig removeByG_CF_CD(long groupId, String serverNo)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = findByG_CF_CD(groupId, serverNo);

		return remove(serverConfig);
	}

	/**
	 * Returns the number of server configs where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the number of matching server configs
	 */
	@Override
	public int countByG_CF_CD(long groupId, String serverNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_CF_CD;

		Object[] finderArgs = new Object[] { groupId, serverNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVERCONFIG_WHERE);

			query.append(_FINDER_COLUMN_G_CF_CD_GROUPID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_G_CF_CD_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_G_CF_CD_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_G_CF_CD_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_CF_CD_GROUPID_2 = "serverConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_CF_CD_SERVERNO_1 = "serverConfig.serverNo IS NULL";
	private static final String _FINDER_COLUMN_G_CF_CD_SERVERNO_2 = "serverConfig.serverNo = ?";
	private static final String _FINDER_COLUMN_G_CF_CD_SERVERNO_3 = "(serverConfig.serverNo IS NULL OR serverConfig.serverNo = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CF_CD = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCF_CD",
			new String[] { String.class.getName() },
			ServerConfigModelImpl.SERVERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CF_CD = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCF_CD",
			new String[] { String.class.getName() });

	/**
	 * Returns the server config where serverNo = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	 *
	 * @param serverNo the server no
	 * @return the matching server config
	 * @throws NoSuchServerConfigException if a matching server config could not be found
	 */
	@Override
	public ServerConfig findByCF_CD(String serverNo)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByCF_CD(serverNo);

		if (serverConfig == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serverNo=");
			msg.append(serverNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServerConfigException(msg.toString());
		}

		return serverConfig;
	}

	/**
	 * Returns the server config where serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serverNo the server no
	 * @return the matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByCF_CD(String serverNo) {
		return fetchByCF_CD(serverNo, true);
	}

	/**
	 * Returns the server config where serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serverNo the server no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByCF_CD(String serverNo, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { serverNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CF_CD,
					finderArgs, this);
		}

		if (result instanceof ServerConfig) {
			ServerConfig serverConfig = (ServerConfig)result;

			if (!Objects.equals(serverNo, serverConfig.getServerNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_CF_CD_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_CF_CD_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_CF_CD_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				List<ServerConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CF_CD,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServerConfigPersistenceImpl.fetchByCF_CD(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServerConfig serverConfig = list.get(0);

					result = serverConfig;

					cacheResult(serverConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CF_CD, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ServerConfig)result;
		}
	}

	/**
	 * Removes the server config where serverNo = &#63; from the database.
	 *
	 * @param serverNo the server no
	 * @return the server config that was removed
	 */
	@Override
	public ServerConfig removeByCF_CD(String serverNo)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = findByCF_CD(serverNo);

		return remove(serverConfig);
	}

	/**
	 * Returns the number of server configs where serverNo = &#63;.
	 *
	 * @param serverNo the server no
	 * @return the number of matching server configs
	 */
	@Override
	public int countByCF_CD(String serverNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CF_CD;

		Object[] finderArgs = new Object[] { serverNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVERCONFIG_WHERE);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_CF_CD_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_CF_CD_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_CF_CD_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CF_CD_SERVERNO_1 = "serverConfig.serverNo IS NULL";
	private static final String _FINDER_COLUMN_CF_CD_SERVERNO_2 = "serverConfig.serverNo = ?";
	private static final String _FINDER_COLUMN_CF_CD_SERVERNO_3 = "(serverConfig.serverNo IS NULL OR serverConfig.serverNo = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CF_NM = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCF_NM",
			new String[] { String.class.getName() },
			ServerConfigModelImpl.SERVERNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CF_NM = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCF_NM",
			new String[] { String.class.getName() });

	/**
	 * Returns the server config where serverName = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	 *
	 * @param serverName the server name
	 * @return the matching server config
	 * @throws NoSuchServerConfigException if a matching server config could not be found
	 */
	@Override
	public ServerConfig findByCF_NM(String serverName)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByCF_NM(serverName);

		if (serverConfig == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serverName=");
			msg.append(serverName);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServerConfigException(msg.toString());
		}

		return serverConfig;
	}

	/**
	 * Returns the server config where serverName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serverName the server name
	 * @return the matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByCF_NM(String serverName) {
		return fetchByCF_NM(serverName, true);
	}

	/**
	 * Returns the server config where serverName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serverName the server name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByCF_NM(String serverName,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { serverName };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CF_NM,
					finderArgs, this);
		}

		if (result instanceof ServerConfig) {
			ServerConfig serverConfig = (ServerConfig)result;

			if (!Objects.equals(serverName, serverConfig.getServerName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

			boolean bindServerName = false;

			if (serverName == null) {
				query.append(_FINDER_COLUMN_CF_NM_SERVERNAME_1);
			}
			else if (serverName.equals("")) {
				query.append(_FINDER_COLUMN_CF_NM_SERVERNAME_3);
			}
			else {
				bindServerName = true;

				query.append(_FINDER_COLUMN_CF_NM_SERVERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServerName) {
					qPos.add(serverName);
				}

				List<ServerConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CF_NM,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServerConfigPersistenceImpl.fetchByCF_NM(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServerConfig serverConfig = list.get(0);

					result = serverConfig;

					cacheResult(serverConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CF_NM, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ServerConfig)result;
		}
	}

	/**
	 * Removes the server config where serverName = &#63; from the database.
	 *
	 * @param serverName the server name
	 * @return the server config that was removed
	 */
	@Override
	public ServerConfig removeByCF_NM(String serverName)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = findByCF_NM(serverName);

		return remove(serverConfig);
	}

	/**
	 * Returns the number of server configs where serverName = &#63;.
	 *
	 * @param serverName the server name
	 * @return the number of matching server configs
	 */
	@Override
	public int countByCF_NM(String serverName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CF_NM;

		Object[] finderArgs = new Object[] { serverName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVERCONFIG_WHERE);

			boolean bindServerName = false;

			if (serverName == null) {
				query.append(_FINDER_COLUMN_CF_NM_SERVERNAME_1);
			}
			else if (serverName.equals("")) {
				query.append(_FINDER_COLUMN_CF_NM_SERVERNAME_3);
			}
			else {
				bindServerName = true;

				query.append(_FINDER_COLUMN_CF_NM_SERVERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServerName) {
					qPos.add(serverName);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CF_NM_SERVERNAME_1 = "serverConfig.serverName IS NULL";
	private static final String _FINDER_COLUMN_CF_NM_SERVERNAME_2 = "serverConfig.serverName = ?";
	private static final String _FINDER_COLUMN_CF_NM_SERVERNAME_3 = "(serverConfig.serverName IS NULL OR serverConfig.serverName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CF_GID = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCF_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CF_GID =
		new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCF_GID",
			new String[] { Long.class.getName() },
			ServerConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ServerConfigModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CF_GID = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCF_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the server configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching server configs
	 */
	@Override
	public List<ServerConfig> findByCF_GID(long groupId) {
		return findByCF_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ServerConfig> findByCF_GID(long groupId, int start, int end) {
		return findByCF_GID(groupId, start, end, null);
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
	@Override
	public List<ServerConfig> findByCF_GID(long groupId, int start, int end,
		OrderByComparator<ServerConfig> orderByComparator) {
		return findByCF_GID(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<ServerConfig> findByCF_GID(long groupId, int start, int end,
		OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CF_GID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CF_GID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<ServerConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServerConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServerConfig serverConfig : list) {
					if ((groupId != serverConfig.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

			query.append(_FINDER_COLUMN_CF_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServerConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ServerConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServerConfig>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first server config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching server config
	 * @throws NoSuchServerConfigException if a matching server config could not be found
	 */
	@Override
	public ServerConfig findByCF_GID_First(long groupId,
		OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByCF_GID_First(groupId,
				orderByComparator);

		if (serverConfig != null) {
			return serverConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchServerConfigException(msg.toString());
	}

	/**
	 * Returns the first server config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByCF_GID_First(long groupId,
		OrderByComparator<ServerConfig> orderByComparator) {
		List<ServerConfig> list = findByCF_GID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last server config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching server config
	 * @throws NoSuchServerConfigException if a matching server config could not be found
	 */
	@Override
	public ServerConfig findByCF_GID_Last(long groupId,
		OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByCF_GID_Last(groupId,
				orderByComparator);

		if (serverConfig != null) {
			return serverConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchServerConfigException(msg.toString());
	}

	/**
	 * Returns the last server config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByCF_GID_Last(long groupId,
		OrderByComparator<ServerConfig> orderByComparator) {
		int count = countByCF_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<ServerConfig> list = findByCF_GID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ServerConfig[] findByCF_GID_PrevAndNext(long serverConfigId,
		long groupId, OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = findByPrimaryKey(serverConfigId);

		Session session = null;

		try {
			session = openSession();

			ServerConfig[] array = new ServerConfigImpl[3];

			array[0] = getByCF_GID_PrevAndNext(session, serverConfig, groupId,
					orderByComparator, true);

			array[1] = serverConfig;

			array[2] = getByCF_GID_PrevAndNext(session, serverConfig, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServerConfig getByCF_GID_PrevAndNext(Session session,
		ServerConfig serverConfig, long groupId,
		OrderByComparator<ServerConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

		query.append(_FINDER_COLUMN_CF_GID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ServerConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serverConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServerConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the server configs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByCF_GID(long groupId) {
		for (ServerConfig serverConfig : findByCF_GID(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serverConfig);
		}
	}

	/**
	 * Returns the number of server configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching server configs
	 */
	@Override
	public int countByCF_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CF_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVERCONFIG_WHERE);

			query.append(_FINDER_COLUMN_CF_GID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CF_GID_GROUPID_2 = "serverConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P",
			new String[] { Long.class.getName(), String.class.getName() },
			ServerConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ServerConfigModelImpl.PROTOCOL_COLUMN_BITMASK |
			ServerConfigModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the server configs where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @return the matching server configs
	 */
	@Override
	public List<ServerConfig> findByG_P(long groupId, String protocol) {
		return findByG_P(groupId, protocol, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<ServerConfig> findByG_P(long groupId, String protocol,
		int start, int end) {
		return findByG_P(groupId, protocol, start, end, null);
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
	@Override
	public List<ServerConfig> findByG_P(long groupId, String protocol,
		int start, int end, OrderByComparator<ServerConfig> orderByComparator) {
		return findByG_P(groupId, protocol, start, end, orderByComparator, true);
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
	@Override
	public List<ServerConfig> findByG_P(long groupId, String protocol,
		int start, int end, OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] { groupId, protocol };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] {
					groupId, protocol,
					
					start, end, orderByComparator
				};
		}

		List<ServerConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServerConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServerConfig serverConfig : list) {
					if ((groupId != serverConfig.getGroupId()) ||
							!Objects.equals(protocol, serverConfig.getProtocol())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_G_P_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_G_P_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_G_P_PROTOCOL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServerConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProtocol) {
					qPos.add(protocol);
				}

				if (!pagination) {
					list = (List<ServerConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServerConfig>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public ServerConfig findByG_P_First(long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByG_P_First(groupId, protocol,
				orderByComparator);

		if (serverConfig != null) {
			return serverConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", protocol=");
		msg.append(protocol);

		msg.append("}");

		throw new NoSuchServerConfigException(msg.toString());
	}

	/**
	 * Returns the first server config in the ordered set where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByG_P_First(long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator) {
		List<ServerConfig> list = findByG_P(groupId, protocol, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ServerConfig findByG_P_Last(long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByG_P_Last(groupId, protocol,
				orderByComparator);

		if (serverConfig != null) {
			return serverConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", protocol=");
		msg.append(protocol);

		msg.append("}");

		throw new NoSuchServerConfigException(msg.toString());
	}

	/**
	 * Returns the last server config in the ordered set where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByG_P_Last(long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator) {
		int count = countByG_P(groupId, protocol);

		if (count == 0) {
			return null;
		}

		List<ServerConfig> list = findByG_P(groupId, protocol, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ServerConfig[] findByG_P_PrevAndNext(long serverConfigId,
		long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = findByPrimaryKey(serverConfigId);

		Session session = null;

		try {
			session = openSession();

			ServerConfig[] array = new ServerConfigImpl[3];

			array[0] = getByG_P_PrevAndNext(session, serverConfig, groupId,
					protocol, orderByComparator, true);

			array[1] = serverConfig;

			array[2] = getByG_P_PrevAndNext(session, serverConfig, groupId,
					protocol, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServerConfig getByG_P_PrevAndNext(Session session,
		ServerConfig serverConfig, long groupId, String protocol,
		OrderByComparator<ServerConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		boolean bindProtocol = false;

		if (protocol == null) {
			query.append(_FINDER_COLUMN_G_P_PROTOCOL_1);
		}
		else if (protocol.equals("")) {
			query.append(_FINDER_COLUMN_G_P_PROTOCOL_3);
		}
		else {
			bindProtocol = true;

			query.append(_FINDER_COLUMN_G_P_PROTOCOL_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ServerConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindProtocol) {
			qPos.add(protocol);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serverConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServerConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the server configs where groupId = &#63; and protocol = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 */
	@Override
	public void removeByG_P(long groupId, String protocol) {
		for (ServerConfig serverConfig : findByG_P(groupId, protocol,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serverConfig);
		}
	}

	/**
	 * Returns the number of server configs where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @return the number of matching server configs
	 */
	@Override
	public int countByG_P(long groupId, String protocol) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P;

		Object[] finderArgs = new Object[] { groupId, protocol };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVERCONFIG_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_G_P_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_G_P_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_G_P_PROTOCOL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProtocol) {
					qPos.add(protocol);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 = "serverConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_PROTOCOL_1 = "serverConfig.protocol IS NULL";
	private static final String _FINDER_COLUMN_G_P_PROTOCOL_2 = "serverConfig.protocol = ?";
	private static final String _FINDER_COLUMN_G_P_PROTOCOL_3 = "(serverConfig.protocol IS NULL OR serverConfig.protocol = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP",
			new String[] { String.class.getName() },
			ServerConfigModelImpl.PROTOCOL_COLUMN_BITMASK |
			ServerConfigModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP",
			new String[] { String.class.getName() });

	/**
	 * Returns all the server configs where protocol = &#63;.
	 *
	 * @param protocol the protocol
	 * @return the matching server configs
	 */
	@Override
	public List<ServerConfig> findByP(String protocol) {
		return findByP(protocol, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ServerConfig> findByP(String protocol, int start, int end) {
		return findByP(protocol, start, end, null);
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
	@Override
	public List<ServerConfig> findByP(String protocol, int start, int end,
		OrderByComparator<ServerConfig> orderByComparator) {
		return findByP(protocol, start, end, orderByComparator, true);
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
	@Override
	public List<ServerConfig> findByP(String protocol, int start, int end,
		OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P;
			finderArgs = new Object[] { protocol };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P;
			finderArgs = new Object[] { protocol, start, end, orderByComparator };
		}

		List<ServerConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServerConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServerConfig serverConfig : list) {
					if (!Objects.equals(protocol, serverConfig.getProtocol())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_P_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_P_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_P_PROTOCOL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServerConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProtocol) {
					qPos.add(protocol);
				}

				if (!pagination) {
					list = (List<ServerConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServerConfig>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first server config in the ordered set where protocol = &#63;.
	 *
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching server config
	 * @throws NoSuchServerConfigException if a matching server config could not be found
	 */
	@Override
	public ServerConfig findByP_First(String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByP_First(protocol, orderByComparator);

		if (serverConfig != null) {
			return serverConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("protocol=");
		msg.append(protocol);

		msg.append("}");

		throw new NoSuchServerConfigException(msg.toString());
	}

	/**
	 * Returns the first server config in the ordered set where protocol = &#63;.
	 *
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByP_First(String protocol,
		OrderByComparator<ServerConfig> orderByComparator) {
		List<ServerConfig> list = findByP(protocol, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last server config in the ordered set where protocol = &#63;.
	 *
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching server config
	 * @throws NoSuchServerConfigException if a matching server config could not be found
	 */
	@Override
	public ServerConfig findByP_Last(String protocol,
		OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByP_Last(protocol, orderByComparator);

		if (serverConfig != null) {
			return serverConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("protocol=");
		msg.append(protocol);

		msg.append("}");

		throw new NoSuchServerConfigException(msg.toString());
	}

	/**
	 * Returns the last server config in the ordered set where protocol = &#63;.
	 *
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByP_Last(String protocol,
		OrderByComparator<ServerConfig> orderByComparator) {
		int count = countByP(protocol);

		if (count == 0) {
			return null;
		}

		List<ServerConfig> list = findByP(protocol, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public ServerConfig[] findByP_PrevAndNext(long serverConfigId,
		String protocol, OrderByComparator<ServerConfig> orderByComparator)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = findByPrimaryKey(serverConfigId);

		Session session = null;

		try {
			session = openSession();

			ServerConfig[] array = new ServerConfigImpl[3];

			array[0] = getByP_PrevAndNext(session, serverConfig, protocol,
					orderByComparator, true);

			array[1] = serverConfig;

			array[2] = getByP_PrevAndNext(session, serverConfig, protocol,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServerConfig getByP_PrevAndNext(Session session,
		ServerConfig serverConfig, String protocol,
		OrderByComparator<ServerConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

		boolean bindProtocol = false;

		if (protocol == null) {
			query.append(_FINDER_COLUMN_P_PROTOCOL_1);
		}
		else if (protocol.equals("")) {
			query.append(_FINDER_COLUMN_P_PROTOCOL_3);
		}
		else {
			bindProtocol = true;

			query.append(_FINDER_COLUMN_P_PROTOCOL_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ServerConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindProtocol) {
			qPos.add(protocol);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serverConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServerConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the server configs where protocol = &#63; from the database.
	 *
	 * @param protocol the protocol
	 */
	@Override
	public void removeByP(String protocol) {
		for (ServerConfig serverConfig : findByP(protocol, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(serverConfig);
		}
	}

	/**
	 * Returns the number of server configs where protocol = &#63;.
	 *
	 * @param protocol the protocol
	 * @return the number of matching server configs
	 */
	@Override
	public int countByP(String protocol) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P;

		Object[] finderArgs = new Object[] { protocol };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVERCONFIG_WHERE);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_P_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_P_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_P_PROTOCOL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProtocol) {
					qPos.add(protocol);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_PROTOCOL_1 = "serverConfig.protocol IS NULL";
	private static final String _FINDER_COLUMN_P_PROTOCOL_2 = "serverConfig.protocol = ?";
	private static final String _FINDER_COLUMN_P_PROTOCOL_3 = "(serverConfig.protocol IS NULL OR serverConfig.protocol = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_G_S_P = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, ServerConfigImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_G_S_P",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			ServerConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ServerConfigModelImpl.SERVERNO_COLUMN_BITMASK |
			ServerConfigModelImpl.PROTOCOL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_G_S_P = new FinderPath(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_G_S_P",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; or throws a {@link NoSuchServerConfigException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param protocol the protocol
	 * @return the matching server config
	 * @throws NoSuchServerConfigException if a matching server config could not be found
	 */
	@Override
	public ServerConfig findByF_G_S_P(long groupId, String serverNo,
		String protocol) throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByF_G_S_P(groupId, serverNo, protocol);

		if (serverConfig == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", serverNo=");
			msg.append(serverNo);

			msg.append(", protocol=");
			msg.append(protocol);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServerConfigException(msg.toString());
		}

		return serverConfig;
	}

	/**
	 * Returns the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param protocol the protocol
	 * @return the matching server config, or <code>null</code> if a matching server config could not be found
	 */
	@Override
	public ServerConfig fetchByF_G_S_P(long groupId, String serverNo,
		String protocol) {
		return fetchByF_G_S_P(groupId, serverNo, protocol, true);
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
	@Override
	public ServerConfig fetchByF_G_S_P(long groupId, String serverNo,
		String protocol, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, serverNo, protocol };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_G_S_P,
					finderArgs, this);
		}

		if (result instanceof ServerConfig) {
			ServerConfig serverConfig = (ServerConfig)result;

			if ((groupId != serverConfig.getGroupId()) ||
					!Objects.equals(serverNo, serverConfig.getServerNo()) ||
					!Objects.equals(protocol, serverConfig.getProtocol())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_SERVERCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_G_S_P_GROUPID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_F_G_S_P_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_F_G_S_P_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_F_G_S_P_SERVERNO_2);
			}

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_F_G_S_P_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_F_G_S_P_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_F_G_S_P_PROTOCOL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				if (bindProtocol) {
					qPos.add(protocol);
				}

				List<ServerConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_G_S_P,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServerConfigPersistenceImpl.fetchByF_G_S_P(long, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServerConfig serverConfig = list.get(0);

					result = serverConfig;

					cacheResult(serverConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_G_S_P,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ServerConfig)result;
		}
	}

	/**
	 * Removes the server config where groupId = &#63; and serverNo = &#63; and protocol = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param protocol the protocol
	 * @return the server config that was removed
	 */
	@Override
	public ServerConfig removeByF_G_S_P(long groupId, String serverNo,
		String protocol) throws NoSuchServerConfigException {
		ServerConfig serverConfig = findByF_G_S_P(groupId, serverNo, protocol);

		return remove(serverConfig);
	}

	/**
	 * Returns the number of server configs where groupId = &#63; and serverNo = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param protocol the protocol
	 * @return the number of matching server configs
	 */
	@Override
	public int countByF_G_S_P(long groupId, String serverNo, String protocol) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_G_S_P;

		Object[] finderArgs = new Object[] { groupId, serverNo, protocol };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SERVERCONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_G_S_P_GROUPID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_F_G_S_P_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_F_G_S_P_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_F_G_S_P_SERVERNO_2);
			}

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_F_G_S_P_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_F_G_S_P_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_F_G_S_P_PROTOCOL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				if (bindProtocol) {
					qPos.add(protocol);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_G_S_P_GROUPID_2 = "serverConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_G_S_P_SERVERNO_1 = "serverConfig.serverNo IS NULL AND ";
	private static final String _FINDER_COLUMN_F_G_S_P_SERVERNO_2 = "serverConfig.serverNo = ? AND ";
	private static final String _FINDER_COLUMN_F_G_S_P_SERVERNO_3 = "(serverConfig.serverNo IS NULL OR serverConfig.serverNo = '') AND ";
	private static final String _FINDER_COLUMN_F_G_S_P_PROTOCOL_1 = "serverConfig.protocol IS NULL";
	private static final String _FINDER_COLUMN_F_G_S_P_PROTOCOL_2 = "serverConfig.protocol = ?";
	private static final String _FINDER_COLUMN_F_G_S_P_PROTOCOL_3 = "(serverConfig.protocol IS NULL OR serverConfig.protocol = '')";

	public ServerConfigPersistenceImpl() {
		setModelClass(ServerConfig.class);
	}

	/**
	 * Caches the server config in the entity cache if it is enabled.
	 *
	 * @param serverConfig the server config
	 */
	@Override
	public void cacheResult(ServerConfig serverConfig) {
		entityCache.putResult(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigImpl.class, serverConfig.getPrimaryKey(), serverConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_CF_CD,
			new Object[] { serverConfig.getGroupId(), serverConfig.getServerNo() },
			serverConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CF_CD,
			new Object[] { serverConfig.getServerNo() }, serverConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CF_NM,
			new Object[] { serverConfig.getServerName() }, serverConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_G_S_P,
			new Object[] {
				serverConfig.getGroupId(), serverConfig.getServerNo(),
				serverConfig.getProtocol()
			}, serverConfig);

		serverConfig.resetOriginalValues();
	}

	/**
	 * Caches the server configs in the entity cache if it is enabled.
	 *
	 * @param serverConfigs the server configs
	 */
	@Override
	public void cacheResult(List<ServerConfig> serverConfigs) {
		for (ServerConfig serverConfig : serverConfigs) {
			if (entityCache.getResult(
						ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
						ServerConfigImpl.class, serverConfig.getPrimaryKey()) == null) {
				cacheResult(serverConfig);
			}
			else {
				serverConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all server configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServerConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the server config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServerConfig serverConfig) {
		entityCache.removeResult(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigImpl.class, serverConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ServerConfigModelImpl)serverConfig, true);
	}

	@Override
	public void clearCache(List<ServerConfig> serverConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ServerConfig serverConfig : serverConfigs) {
			entityCache.removeResult(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
				ServerConfigImpl.class, serverConfig.getPrimaryKey());

			clearUniqueFindersCache((ServerConfigModelImpl)serverConfig, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ServerConfigModelImpl serverConfigModelImpl) {
		Object[] args = new Object[] {
				serverConfigModelImpl.getGroupId(),
				serverConfigModelImpl.getServerNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_CF_CD, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_CF_CD, args,
			serverConfigModelImpl, false);

		args = new Object[] { serverConfigModelImpl.getServerNo() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_CF_CD, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CF_CD, args,
			serverConfigModelImpl, false);

		args = new Object[] { serverConfigModelImpl.getServerName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_CF_NM, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CF_NM, args,
			serverConfigModelImpl, false);

		args = new Object[] {
				serverConfigModelImpl.getGroupId(),
				serverConfigModelImpl.getServerNo(),
				serverConfigModelImpl.getProtocol()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_G_S_P, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_G_S_P, args,
			serverConfigModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ServerConfigModelImpl serverConfigModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					serverConfigModelImpl.getGroupId(),
					serverConfigModelImpl.getServerNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_CF_CD, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_CF_CD, args);
		}

		if ((serverConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_CF_CD.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serverConfigModelImpl.getOriginalGroupId(),
					serverConfigModelImpl.getOriginalServerNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_CF_CD, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_CF_CD, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { serverConfigModelImpl.getServerNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CF_CD, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CF_CD, args);
		}

		if ((serverConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CF_CD.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serverConfigModelImpl.getOriginalServerNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CF_CD, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CF_CD, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { serverConfigModelImpl.getServerName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CF_NM, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CF_NM, args);
		}

		if ((serverConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CF_NM.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serverConfigModelImpl.getOriginalServerName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CF_NM, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CF_NM, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					serverConfigModelImpl.getGroupId(),
					serverConfigModelImpl.getServerNo(),
					serverConfigModelImpl.getProtocol()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_S_P, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_G_S_P, args);
		}

		if ((serverConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_G_S_P.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serverConfigModelImpl.getOriginalGroupId(),
					serverConfigModelImpl.getOriginalServerNo(),
					serverConfigModelImpl.getOriginalProtocol()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_S_P, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_G_S_P, args);
		}
	}

	/**
	 * Creates a new server config with the primary key. Does not add the server config to the database.
	 *
	 * @param serverConfigId the primary key for the new server config
	 * @return the new server config
	 */
	@Override
	public ServerConfig create(long serverConfigId) {
		ServerConfig serverConfig = new ServerConfigImpl();

		serverConfig.setNew(true);
		serverConfig.setPrimaryKey(serverConfigId);

		serverConfig.setCompanyId(companyProvider.getCompanyId());

		return serverConfig;
	}

	/**
	 * Removes the server config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serverConfigId the primary key of the server config
	 * @return the server config that was removed
	 * @throws NoSuchServerConfigException if a server config with the primary key could not be found
	 */
	@Override
	public ServerConfig remove(long serverConfigId)
		throws NoSuchServerConfigException {
		return remove((Serializable)serverConfigId);
	}

	/**
	 * Removes the server config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the server config
	 * @return the server config that was removed
	 * @throws NoSuchServerConfigException if a server config with the primary key could not be found
	 */
	@Override
	public ServerConfig remove(Serializable primaryKey)
		throws NoSuchServerConfigException {
		Session session = null;

		try {
			session = openSession();

			ServerConfig serverConfig = (ServerConfig)session.get(ServerConfigImpl.class,
					primaryKey);

			if (serverConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServerConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(serverConfig);
		}
		catch (NoSuchServerConfigException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ServerConfig removeImpl(ServerConfig serverConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serverConfig)) {
				serverConfig = (ServerConfig)session.get(ServerConfigImpl.class,
						serverConfig.getPrimaryKeyObj());
			}

			if (serverConfig != null) {
				session.delete(serverConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (serverConfig != null) {
			clearCache(serverConfig);
		}

		return serverConfig;
	}

	@Override
	public ServerConfig updateImpl(ServerConfig serverConfig) {
		boolean isNew = serverConfig.isNew();

		if (!(serverConfig instanceof ServerConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(serverConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(serverConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in serverConfig proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ServerConfig implementation " +
				serverConfig.getClass());
		}

		ServerConfigModelImpl serverConfigModelImpl = (ServerConfigModelImpl)serverConfig;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (serverConfig.getCreateDate() == null)) {
			if (serviceContext == null) {
				serverConfig.setCreateDate(now);
			}
			else {
				serverConfig.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!serverConfigModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				serverConfig.setModifiedDate(now);
			}
			else {
				serverConfig.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (serverConfig.isNew()) {
				session.save(serverConfig);

				serverConfig.setNew(false);
			}
			else {
				serverConfig = (ServerConfig)session.merge(serverConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ServerConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { serverConfigModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CF_GID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CF_GID,
				args);

			args = new Object[] {
					serverConfigModelImpl.getGroupId(),
					serverConfigModelImpl.getProtocol()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
				args);

			args = new Object[] { serverConfigModelImpl.getProtocol() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_P, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((serverConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CF_GID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serverConfigModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CF_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CF_GID,
					args);

				args = new Object[] { serverConfigModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CF_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CF_GID,
					args);
			}

			if ((serverConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serverConfigModelImpl.getOriginalGroupId(),
						serverConfigModelImpl.getOriginalProtocol()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);

				args = new Object[] {
						serverConfigModelImpl.getGroupId(),
						serverConfigModelImpl.getProtocol()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);
			}

			if ((serverConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serverConfigModelImpl.getOriginalProtocol()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P,
					args);

				args = new Object[] { serverConfigModelImpl.getProtocol() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P,
					args);
			}
		}

		entityCache.putResult(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServerConfigImpl.class, serverConfig.getPrimaryKey(), serverConfig,
			false);

		clearUniqueFindersCache(serverConfigModelImpl, false);
		cacheUniqueFindersCache(serverConfigModelImpl);

		serverConfig.resetOriginalValues();

		return serverConfig;
	}

	/**
	 * Returns the server config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the server config
	 * @return the server config
	 * @throws NoSuchServerConfigException if a server config with the primary key could not be found
	 */
	@Override
	public ServerConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServerConfigException {
		ServerConfig serverConfig = fetchByPrimaryKey(primaryKey);

		if (serverConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServerConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return serverConfig;
	}

	/**
	 * Returns the server config with the primary key or throws a {@link NoSuchServerConfigException} if it could not be found.
	 *
	 * @param serverConfigId the primary key of the server config
	 * @return the server config
	 * @throws NoSuchServerConfigException if a server config with the primary key could not be found
	 */
	@Override
	public ServerConfig findByPrimaryKey(long serverConfigId)
		throws NoSuchServerConfigException {
		return findByPrimaryKey((Serializable)serverConfigId);
	}

	/**
	 * Returns the server config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the server config
	 * @return the server config, or <code>null</code> if a server config with the primary key could not be found
	 */
	@Override
	public ServerConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
				ServerConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ServerConfig serverConfig = (ServerConfig)serializable;

		if (serverConfig == null) {
			Session session = null;

			try {
				session = openSession();

				serverConfig = (ServerConfig)session.get(ServerConfigImpl.class,
						primaryKey);

				if (serverConfig != null) {
					cacheResult(serverConfig);
				}
				else {
					entityCache.putResult(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
						ServerConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
					ServerConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return serverConfig;
	}

	/**
	 * Returns the server config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serverConfigId the primary key of the server config
	 * @return the server config, or <code>null</code> if a server config with the primary key could not be found
	 */
	@Override
	public ServerConfig fetchByPrimaryKey(long serverConfigId) {
		return fetchByPrimaryKey((Serializable)serverConfigId);
	}

	@Override
	public Map<Serializable, ServerConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ServerConfig> map = new HashMap<Serializable, ServerConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ServerConfig serverConfig = fetchByPrimaryKey(primaryKey);

			if (serverConfig != null) {
				map.put(primaryKey, serverConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
					ServerConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ServerConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SERVERCONFIG_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ServerConfig serverConfig : (List<ServerConfig>)q.list()) {
				map.put(serverConfig.getPrimaryKeyObj(), serverConfig);

				cacheResult(serverConfig);

				uncachedPrimaryKeys.remove(serverConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ServerConfigModelImpl.ENTITY_CACHE_ENABLED,
					ServerConfigImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the server configs.
	 *
	 * @return the server configs
	 */
	@Override
	public List<ServerConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ServerConfig> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<ServerConfig> findAll(int start, int end,
		OrderByComparator<ServerConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<ServerConfig> findAll(int start, int end,
		OrderByComparator<ServerConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ServerConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServerConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SERVERCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SERVERCONFIG;

				if (pagination) {
					sql = sql.concat(ServerConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ServerConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServerConfig>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the server configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServerConfig serverConfig : findAll()) {
			remove(serverConfig);
		}
	}

	/**
	 * Returns the number of server configs.
	 *
	 * @return the number of server configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SERVERCONFIG);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ServerConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the server config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ServerConfigImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SERVERCONFIG = "SELECT serverConfig FROM ServerConfig serverConfig";
	private static final String _SQL_SELECT_SERVERCONFIG_WHERE_PKS_IN = "SELECT serverConfig FROM ServerConfig serverConfig WHERE serverConfigId IN (";
	private static final String _SQL_SELECT_SERVERCONFIG_WHERE = "SELECT serverConfig FROM ServerConfig serverConfig WHERE ";
	private static final String _SQL_COUNT_SERVERCONFIG = "SELECT COUNT(serverConfig) FROM ServerConfig serverConfig";
	private static final String _SQL_COUNT_SERVERCONFIG_WHERE = "SELECT COUNT(serverConfig) FROM ServerConfig serverConfig WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "serverConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ServerConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ServerConfig exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ServerConfigPersistenceImpl.class);
}