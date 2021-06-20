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

package org.opencps.adminconfig.service.persistence.impl;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.adminconfig.exception.NoSuchApiRoleException;
import org.opencps.adminconfig.model.ApiRole;
import org.opencps.adminconfig.model.impl.ApiRoleImpl;
import org.opencps.adminconfig.model.impl.ApiRoleModelImpl;
import org.opencps.adminconfig.service.persistence.ApiRolePersistence;

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
 * The persistence implementation for the api role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see ApiRolePersistence
 * @see org.opencps.adminconfig.service.persistence.ApiRoleUtil
 * @generated
 */
@ProviderType
public class ApiRolePersistenceImpl extends BasePersistenceImpl<ApiRole>
	implements ApiRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ApiRoleUtil} to access the api role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ApiRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, ApiRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, ApiRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_F_ID = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, ApiRoleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_ID",
			new String[] { Long.class.getName() },
			ApiRoleModelImpl.APIROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_ID = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns the api role where apiRoleId = &#63; or throws a {@link NoSuchApiRoleException} if it could not be found.
	 *
	 * @param apiRoleId the api role ID
	 * @return the matching api role
	 * @throws NoSuchApiRoleException if a matching api role could not be found
	 */
	@Override
	public ApiRole findByF_ID(long apiRoleId) throws NoSuchApiRoleException {
		ApiRole apiRole = fetchByF_ID(apiRoleId);

		if (apiRole == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("apiRoleId=");
			msg.append(apiRoleId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchApiRoleException(msg.toString());
		}

		return apiRole;
	}

	/**
	 * Returns the api role where apiRoleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param apiRoleId the api role ID
	 * @return the matching api role, or <code>null</code> if a matching api role could not be found
	 */
	@Override
	public ApiRole fetchByF_ID(long apiRoleId) {
		return fetchByF_ID(apiRoleId, true);
	}

	/**
	 * Returns the api role where apiRoleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param apiRoleId the api role ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching api role, or <code>null</code> if a matching api role could not be found
	 */
	@Override
	public ApiRole fetchByF_ID(long apiRoleId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { apiRoleId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_ID,
					finderArgs, this);
		}

		if (result instanceof ApiRole) {
			ApiRole apiRole = (ApiRole)result;

			if ((apiRoleId != apiRole.getApiRoleId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_APIROLE_WHERE);

			query.append(_FINDER_COLUMN_F_ID_APIROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(apiRoleId);

				List<ApiRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_ID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ApiRolePersistenceImpl.fetchByF_ID(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ApiRole apiRole = list.get(0);

					result = apiRole;

					cacheResult(apiRole);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ID, finderArgs);

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
			return (ApiRole)result;
		}
	}

	/**
	 * Removes the api role where apiRoleId = &#63; from the database.
	 *
	 * @param apiRoleId the api role ID
	 * @return the api role that was removed
	 */
	@Override
	public ApiRole removeByF_ID(long apiRoleId) throws NoSuchApiRoleException {
		ApiRole apiRole = findByF_ID(apiRoleId);

		return remove(apiRole);
	}

	/**
	 * Returns the number of api roles where apiRoleId = &#63;.
	 *
	 * @param apiRoleId the api role ID
	 * @return the number of matching api roles
	 */
	@Override
	public int countByF_ID(long apiRoleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_ID;

		Object[] finderArgs = new Object[] { apiRoleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APIROLE_WHERE);

			query.append(_FINDER_COLUMN_F_ID_APIROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(apiRoleId);

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

	private static final String _FINDER_COLUMN_F_ID_APIROLEID_2 = "apiRole.apiRoleId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_ROLECODE = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, ApiRoleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_roleCode",
			new String[] { String.class.getName() },
			ApiRoleModelImpl.ROLECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_ROLECODE = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_roleCode",
			new String[] { String.class.getName() });

	/**
	 * Returns the api role where roleCode = &#63; or throws a {@link NoSuchApiRoleException} if it could not be found.
	 *
	 * @param roleCode the role code
	 * @return the matching api role
	 * @throws NoSuchApiRoleException if a matching api role could not be found
	 */
	@Override
	public ApiRole findByF_roleCode(String roleCode)
		throws NoSuchApiRoleException {
		ApiRole apiRole = fetchByF_roleCode(roleCode);

		if (apiRole == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("roleCode=");
			msg.append(roleCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchApiRoleException(msg.toString());
		}

		return apiRole;
	}

	/**
	 * Returns the api role where roleCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param roleCode the role code
	 * @return the matching api role, or <code>null</code> if a matching api role could not be found
	 */
	@Override
	public ApiRole fetchByF_roleCode(String roleCode) {
		return fetchByF_roleCode(roleCode, true);
	}

	/**
	 * Returns the api role where roleCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param roleCode the role code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching api role, or <code>null</code> if a matching api role could not be found
	 */
	@Override
	public ApiRole fetchByF_roleCode(String roleCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { roleCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_ROLECODE,
					finderArgs, this);
		}

		if (result instanceof ApiRole) {
			ApiRole apiRole = (ApiRole)result;

			if (!Objects.equals(roleCode, apiRole.getRoleCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_APIROLE_WHERE);

			boolean bindRoleCode = false;

			if (roleCode == null) {
				query.append(_FINDER_COLUMN_F_ROLECODE_ROLECODE_1);
			}
			else if (roleCode.equals("")) {
				query.append(_FINDER_COLUMN_F_ROLECODE_ROLECODE_3);
			}
			else {
				bindRoleCode = true;

				query.append(_FINDER_COLUMN_F_ROLECODE_ROLECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRoleCode) {
					qPos.add(roleCode);
				}

				List<ApiRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_ROLECODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ApiRolePersistenceImpl.fetchByF_roleCode(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ApiRole apiRole = list.get(0);

					result = apiRole;

					cacheResult(apiRole);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ROLECODE,
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
			return (ApiRole)result;
		}
	}

	/**
	 * Removes the api role where roleCode = &#63; from the database.
	 *
	 * @param roleCode the role code
	 * @return the api role that was removed
	 */
	@Override
	public ApiRole removeByF_roleCode(String roleCode)
		throws NoSuchApiRoleException {
		ApiRole apiRole = findByF_roleCode(roleCode);

		return remove(apiRole);
	}

	/**
	 * Returns the number of api roles where roleCode = &#63;.
	 *
	 * @param roleCode the role code
	 * @return the number of matching api roles
	 */
	@Override
	public int countByF_roleCode(String roleCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_ROLECODE;

		Object[] finderArgs = new Object[] { roleCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APIROLE_WHERE);

			boolean bindRoleCode = false;

			if (roleCode == null) {
				query.append(_FINDER_COLUMN_F_ROLECODE_ROLECODE_1);
			}
			else if (roleCode.equals("")) {
				query.append(_FINDER_COLUMN_F_ROLECODE_ROLECODE_3);
			}
			else {
				bindRoleCode = true;

				query.append(_FINDER_COLUMN_F_ROLECODE_ROLECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRoleCode) {
					qPos.add(roleCode);
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

	private static final String _FINDER_COLUMN_F_ROLECODE_ROLECODE_1 = "apiRole.roleCode IS NULL";
	private static final String _FINDER_COLUMN_F_ROLECODE_ROLECODE_2 = "apiRole.roleCode = ?";
	private static final String _FINDER_COLUMN_F_ROLECODE_ROLECODE_3 = "(apiRole.roleCode IS NULL OR apiRole.roleCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, ApiRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, ApiRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID",
			new String[] { Long.class.getName() },
			ApiRoleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID = new FinderPath(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the api roles where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching api roles
	 */
	@Override
	public List<ApiRole> findByF_GID(long groupId) {
		return findByF_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the api roles where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of api roles
	 * @param end the upper bound of the range of api roles (not inclusive)
	 * @return the range of matching api roles
	 */
	@Override
	public List<ApiRole> findByF_GID(long groupId, int start, int end) {
		return findByF_GID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the api roles where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of api roles
	 * @param end the upper bound of the range of api roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching api roles
	 */
	@Override
	public List<ApiRole> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ApiRole> orderByComparator) {
		return findByF_GID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the api roles where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of api roles
	 * @param end the upper bound of the range of api roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching api roles
	 */
	@Override
	public List<ApiRole> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ApiRole> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<ApiRole> list = null;

		if (retrieveFromCache) {
			list = (List<ApiRole>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (ApiRole apiRole : list) {
					if ((groupId != apiRole.getGroupId())) {
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

			query.append(_SQL_SELECT_APIROLE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ApiRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ApiRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApiRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first api role in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api role
	 * @throws NoSuchApiRoleException if a matching api role could not be found
	 */
	@Override
	public ApiRole findByF_GID_First(long groupId,
		OrderByComparator<ApiRole> orderByComparator)
		throws NoSuchApiRoleException {
		ApiRole apiRole = fetchByF_GID_First(groupId, orderByComparator);

		if (apiRole != null) {
			return apiRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchApiRoleException(msg.toString());
	}

	/**
	 * Returns the first api role in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api role, or <code>null</code> if a matching api role could not be found
	 */
	@Override
	public ApiRole fetchByF_GID_First(long groupId,
		OrderByComparator<ApiRole> orderByComparator) {
		List<ApiRole> list = findByF_GID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last api role in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api role
	 * @throws NoSuchApiRoleException if a matching api role could not be found
	 */
	@Override
	public ApiRole findByF_GID_Last(long groupId,
		OrderByComparator<ApiRole> orderByComparator)
		throws NoSuchApiRoleException {
		ApiRole apiRole = fetchByF_GID_Last(groupId, orderByComparator);

		if (apiRole != null) {
			return apiRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchApiRoleException(msg.toString());
	}

	/**
	 * Returns the last api role in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api role, or <code>null</code> if a matching api role could not be found
	 */
	@Override
	public ApiRole fetchByF_GID_Last(long groupId,
		OrderByComparator<ApiRole> orderByComparator) {
		int count = countByF_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<ApiRole> list = findByF_GID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the api roles before and after the current api role in the ordered set where groupId = &#63;.
	 *
	 * @param apiRoleId the primary key of the current api role
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next api role
	 * @throws NoSuchApiRoleException if a api role with the primary key could not be found
	 */
	@Override
	public ApiRole[] findByF_GID_PrevAndNext(long apiRoleId, long groupId,
		OrderByComparator<ApiRole> orderByComparator)
		throws NoSuchApiRoleException {
		ApiRole apiRole = findByPrimaryKey(apiRoleId);

		Session session = null;

		try {
			session = openSession();

			ApiRole[] array = new ApiRoleImpl[3];

			array[0] = getByF_GID_PrevAndNext(session, apiRole, groupId,
					orderByComparator, true);

			array[1] = apiRole;

			array[2] = getByF_GID_PrevAndNext(session, apiRole, groupId,
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

	protected ApiRole getByF_GID_PrevAndNext(Session session, ApiRole apiRole,
		long groupId, OrderByComparator<ApiRole> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APIROLE_WHERE);

		query.append(_FINDER_COLUMN_F_GID_GROUPID_2);

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
			query.append(ApiRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(apiRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApiRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the api roles where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_GID(long groupId) {
		for (ApiRole apiRole : findByF_GID(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(apiRole);
		}
	}

	/**
	 * Returns the number of api roles where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching api roles
	 */
	@Override
	public int countByF_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APIROLE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_GID_GROUPID_2 = "apiRole.groupId = ?";

	public ApiRolePersistenceImpl() {
		setModelClass(ApiRole.class);
	}

	/**
	 * Caches the api role in the entity cache if it is enabled.
	 *
	 * @param apiRole the api role
	 */
	@Override
	public void cacheResult(ApiRole apiRole) {
		entityCache.putResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleImpl.class, apiRole.getPrimaryKey(), apiRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ID,
			new Object[] { apiRole.getApiRoleId() }, apiRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ROLECODE,
			new Object[] { apiRole.getRoleCode() }, apiRole);

		apiRole.resetOriginalValues();
	}

	/**
	 * Caches the api roles in the entity cache if it is enabled.
	 *
	 * @param apiRoles the api roles
	 */
	@Override
	public void cacheResult(List<ApiRole> apiRoles) {
		for (ApiRole apiRole : apiRoles) {
			if (entityCache.getResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
						ApiRoleImpl.class, apiRole.getPrimaryKey()) == null) {
				cacheResult(apiRole);
			}
			else {
				apiRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all api roles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ApiRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the api role.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApiRole apiRole) {
		entityCache.removeResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleImpl.class, apiRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ApiRoleModelImpl)apiRole, true);
	}

	@Override
	public void clearCache(List<ApiRole> apiRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ApiRole apiRole : apiRoles) {
			entityCache.removeResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
				ApiRoleImpl.class, apiRole.getPrimaryKey());

			clearUniqueFindersCache((ApiRoleModelImpl)apiRole, true);
		}
	}

	protected void cacheUniqueFindersCache(ApiRoleModelImpl apiRoleModelImpl) {
		Object[] args = new Object[] { apiRoleModelImpl.getApiRoleId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_ID, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ID, args,
			apiRoleModelImpl, false);

		args = new Object[] { apiRoleModelImpl.getRoleCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_ROLECODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ROLECODE, args,
			apiRoleModelImpl, false);
	}

	protected void clearUniqueFindersCache(ApiRoleModelImpl apiRoleModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { apiRoleModelImpl.getApiRoleId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ID, args);
		}

		if ((apiRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_ID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { apiRoleModelImpl.getOriginalApiRoleId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { apiRoleModelImpl.getRoleCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ROLECODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ROLECODE, args);
		}

		if ((apiRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_ROLECODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { apiRoleModelImpl.getOriginalRoleCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ROLECODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ROLECODE, args);
		}
	}

	/**
	 * Creates a new api role with the primary key. Does not add the api role to the database.
	 *
	 * @param apiRoleId the primary key for the new api role
	 * @return the new api role
	 */
	@Override
	public ApiRole create(long apiRoleId) {
		ApiRole apiRole = new ApiRoleImpl();

		apiRole.setNew(true);
		apiRole.setPrimaryKey(apiRoleId);

		return apiRole;
	}

	/**
	 * Removes the api role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param apiRoleId the primary key of the api role
	 * @return the api role that was removed
	 * @throws NoSuchApiRoleException if a api role with the primary key could not be found
	 */
	@Override
	public ApiRole remove(long apiRoleId) throws NoSuchApiRoleException {
		return remove((Serializable)apiRoleId);
	}

	/**
	 * Removes the api role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the api role
	 * @return the api role that was removed
	 * @throws NoSuchApiRoleException if a api role with the primary key could not be found
	 */
	@Override
	public ApiRole remove(Serializable primaryKey)
		throws NoSuchApiRoleException {
		Session session = null;

		try {
			session = openSession();

			ApiRole apiRole = (ApiRole)session.get(ApiRoleImpl.class, primaryKey);

			if (apiRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApiRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(apiRole);
		}
		catch (NoSuchApiRoleException nsee) {
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
	protected ApiRole removeImpl(ApiRole apiRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(apiRole)) {
				apiRole = (ApiRole)session.get(ApiRoleImpl.class,
						apiRole.getPrimaryKeyObj());
			}

			if (apiRole != null) {
				session.delete(apiRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (apiRole != null) {
			clearCache(apiRole);
		}

		return apiRole;
	}

	@Override
	public ApiRole updateImpl(ApiRole apiRole) {
		boolean isNew = apiRole.isNew();

		if (!(apiRole instanceof ApiRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(apiRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(apiRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in apiRole proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ApiRole implementation " +
				apiRole.getClass());
		}

		ApiRoleModelImpl apiRoleModelImpl = (ApiRoleModelImpl)apiRole;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (apiRole.getCreateDate() == null)) {
			if (serviceContext == null) {
				apiRole.setCreateDate(now);
			}
			else {
				apiRole.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!apiRoleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				apiRole.setModifiedDate(now);
			}
			else {
				apiRole.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (apiRole.isNew()) {
				session.save(apiRole);

				apiRole.setNew(false);
			}
			else {
				apiRole = (ApiRole)session.merge(apiRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ApiRoleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { apiRoleModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((apiRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						apiRoleModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);

				args = new Object[] { apiRoleModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);
			}
		}

		entityCache.putResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
			ApiRoleImpl.class, apiRole.getPrimaryKey(), apiRole, false);

		clearUniqueFindersCache(apiRoleModelImpl, false);
		cacheUniqueFindersCache(apiRoleModelImpl);

		apiRole.resetOriginalValues();

		return apiRole;
	}

	/**
	 * Returns the api role with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the api role
	 * @return the api role
	 * @throws NoSuchApiRoleException if a api role with the primary key could not be found
	 */
	@Override
	public ApiRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApiRoleException {
		ApiRole apiRole = fetchByPrimaryKey(primaryKey);

		if (apiRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApiRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return apiRole;
	}

	/**
	 * Returns the api role with the primary key or throws a {@link NoSuchApiRoleException} if it could not be found.
	 *
	 * @param apiRoleId the primary key of the api role
	 * @return the api role
	 * @throws NoSuchApiRoleException if a api role with the primary key could not be found
	 */
	@Override
	public ApiRole findByPrimaryKey(long apiRoleId)
		throws NoSuchApiRoleException {
		return findByPrimaryKey((Serializable)apiRoleId);
	}

	/**
	 * Returns the api role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the api role
	 * @return the api role, or <code>null</code> if a api role with the primary key could not be found
	 */
	@Override
	public ApiRole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
				ApiRoleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ApiRole apiRole = (ApiRole)serializable;

		if (apiRole == null) {
			Session session = null;

			try {
				session = openSession();

				apiRole = (ApiRole)session.get(ApiRoleImpl.class, primaryKey);

				if (apiRole != null) {
					cacheResult(apiRole);
				}
				else {
					entityCache.putResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
						ApiRoleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
					ApiRoleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return apiRole;
	}

	/**
	 * Returns the api role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param apiRoleId the primary key of the api role
	 * @return the api role, or <code>null</code> if a api role with the primary key could not be found
	 */
	@Override
	public ApiRole fetchByPrimaryKey(long apiRoleId) {
		return fetchByPrimaryKey((Serializable)apiRoleId);
	}

	@Override
	public Map<Serializable, ApiRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ApiRole> map = new HashMap<Serializable, ApiRole>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ApiRole apiRole = fetchByPrimaryKey(primaryKey);

			if (apiRole != null) {
				map.put(primaryKey, apiRole);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
					ApiRoleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ApiRole)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_APIROLE_WHERE_PKS_IN);

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

			for (ApiRole apiRole : (List<ApiRole>)q.list()) {
				map.put(apiRole.getPrimaryKeyObj(), apiRole);

				cacheResult(apiRole);

				uncachedPrimaryKeys.remove(apiRole.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ApiRoleModelImpl.ENTITY_CACHE_ENABLED,
					ApiRoleImpl.class, primaryKey, nullModel);
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
	 * Returns all the api roles.
	 *
	 * @return the api roles
	 */
	@Override
	public List<ApiRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the api roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of api roles
	 * @param end the upper bound of the range of api roles (not inclusive)
	 * @return the range of api roles
	 */
	@Override
	public List<ApiRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the api roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of api roles
	 * @param end the upper bound of the range of api roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of api roles
	 */
	@Override
	public List<ApiRole> findAll(int start, int end,
		OrderByComparator<ApiRole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the api roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of api roles
	 * @param end the upper bound of the range of api roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of api roles
	 */
	@Override
	public List<ApiRole> findAll(int start, int end,
		OrderByComparator<ApiRole> orderByComparator, boolean retrieveFromCache) {
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

		List<ApiRole> list = null;

		if (retrieveFromCache) {
			list = (List<ApiRole>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_APIROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APIROLE;

				if (pagination) {
					sql = sql.concat(ApiRoleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ApiRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApiRole>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the api roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApiRole apiRole : findAll()) {
			remove(apiRole);
		}
	}

	/**
	 * Returns the number of api roles.
	 *
	 * @return the number of api roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APIROLE);

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
		return ApiRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the api role persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ApiRoleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_APIROLE = "SELECT apiRole FROM ApiRole apiRole";
	private static final String _SQL_SELECT_APIROLE_WHERE_PKS_IN = "SELECT apiRole FROM ApiRole apiRole WHERE apiRoleId IN (";
	private static final String _SQL_SELECT_APIROLE_WHERE = "SELECT apiRole FROM ApiRole apiRole WHERE ";
	private static final String _SQL_COUNT_APIROLE = "SELECT COUNT(apiRole) FROM ApiRole apiRole";
	private static final String _SQL_COUNT_APIROLE_WHERE = "SELECT COUNT(apiRole) FROM ApiRole apiRole WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "apiRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ApiRole exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ApiRole exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ApiRolePersistenceImpl.class);
}