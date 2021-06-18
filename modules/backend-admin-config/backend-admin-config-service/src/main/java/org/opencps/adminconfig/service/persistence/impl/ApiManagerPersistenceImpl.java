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

import org.opencps.adminconfig.exception.NoSuchApiManagerException;
import org.opencps.adminconfig.model.ApiManager;
import org.opencps.adminconfig.model.impl.ApiManagerImpl;
import org.opencps.adminconfig.model.impl.ApiManagerModelImpl;
import org.opencps.adminconfig.service.persistence.ApiManagerPersistence;

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
 * The persistence implementation for the api manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see ApiManagerPersistence
 * @see org.opencps.adminconfig.service.persistence.ApiManagerUtil
 * @generated
 */
@ProviderType
public class ApiManagerPersistenceImpl extends BasePersistenceImpl<ApiManager>
	implements ApiManagerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ApiManagerUtil} to access the api manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ApiManagerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, ApiManagerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, ApiManagerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_F_ID = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, ApiManagerImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_ID",
			new String[] { Long.class.getName() },
			ApiManagerModelImpl.APIMANAGERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_ID = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns the api manager where apiManagerId = &#63; or throws a {@link NoSuchApiManagerException} if it could not be found.
	 *
	 * @param apiManagerId the api manager ID
	 * @return the matching api manager
	 * @throws NoSuchApiManagerException if a matching api manager could not be found
	 */
	@Override
	public ApiManager findByF_ID(long apiManagerId)
		throws NoSuchApiManagerException {
		ApiManager apiManager = fetchByF_ID(apiManagerId);

		if (apiManager == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("apiManagerId=");
			msg.append(apiManagerId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchApiManagerException(msg.toString());
		}

		return apiManager;
	}

	/**
	 * Returns the api manager where apiManagerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param apiManagerId the api manager ID
	 * @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	 */
	@Override
	public ApiManager fetchByF_ID(long apiManagerId) {
		return fetchByF_ID(apiManagerId, true);
	}

	/**
	 * Returns the api manager where apiManagerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param apiManagerId the api manager ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	 */
	@Override
	public ApiManager fetchByF_ID(long apiManagerId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { apiManagerId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_ID,
					finderArgs, this);
		}

		if (result instanceof ApiManager) {
			ApiManager apiManager = (ApiManager)result;

			if ((apiManagerId != apiManager.getApiManagerId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_APIMANAGER_WHERE);

			query.append(_FINDER_COLUMN_F_ID_APIMANAGERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(apiManagerId);

				List<ApiManager> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_ID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ApiManagerPersistenceImpl.fetchByF_ID(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ApiManager apiManager = list.get(0);

					result = apiManager;

					cacheResult(apiManager);
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
			return (ApiManager)result;
		}
	}

	/**
	 * Removes the api manager where apiManagerId = &#63; from the database.
	 *
	 * @param apiManagerId the api manager ID
	 * @return the api manager that was removed
	 */
	@Override
	public ApiManager removeByF_ID(long apiManagerId)
		throws NoSuchApiManagerException {
		ApiManager apiManager = findByF_ID(apiManagerId);

		return remove(apiManager);
	}

	/**
	 * Returns the number of api managers where apiManagerId = &#63;.
	 *
	 * @param apiManagerId the api manager ID
	 * @return the number of matching api managers
	 */
	@Override
	public int countByF_ID(long apiManagerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_ID;

		Object[] finderArgs = new Object[] { apiManagerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APIMANAGER_WHERE);

			query.append(_FINDER_COLUMN_F_ID_APIMANAGERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(apiManagerId);

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

	private static final String _FINDER_COLUMN_F_ID_APIMANAGERID_2 = "apiManager.apiManagerId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_APICODE = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, ApiManagerImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_apiCode",
			new String[] { String.class.getName() },
			ApiManagerModelImpl.APICODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_APICODE = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_apiCode",
			new String[] { String.class.getName() });

	/**
	 * Returns the api manager where apiCode = &#63; or throws a {@link NoSuchApiManagerException} if it could not be found.
	 *
	 * @param apiCode the api code
	 * @return the matching api manager
	 * @throws NoSuchApiManagerException if a matching api manager could not be found
	 */
	@Override
	public ApiManager findByF_apiCode(String apiCode)
		throws NoSuchApiManagerException {
		ApiManager apiManager = fetchByF_apiCode(apiCode);

		if (apiManager == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("apiCode=");
			msg.append(apiCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchApiManagerException(msg.toString());
		}

		return apiManager;
	}

	/**
	 * Returns the api manager where apiCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param apiCode the api code
	 * @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	 */
	@Override
	public ApiManager fetchByF_apiCode(String apiCode) {
		return fetchByF_apiCode(apiCode, true);
	}

	/**
	 * Returns the api manager where apiCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param apiCode the api code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching api manager, or <code>null</code> if a matching api manager could not be found
	 */
	@Override
	public ApiManager fetchByF_apiCode(String apiCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { apiCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_APICODE,
					finderArgs, this);
		}

		if (result instanceof ApiManager) {
			ApiManager apiManager = (ApiManager)result;

			if (!Objects.equals(apiCode, apiManager.getApiCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_APIMANAGER_WHERE);

			boolean bindApiCode = false;

			if (apiCode == null) {
				query.append(_FINDER_COLUMN_F_APICODE_APICODE_1);
			}
			else if (apiCode.equals("")) {
				query.append(_FINDER_COLUMN_F_APICODE_APICODE_3);
			}
			else {
				bindApiCode = true;

				query.append(_FINDER_COLUMN_F_APICODE_APICODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindApiCode) {
					qPos.add(apiCode);
				}

				List<ApiManager> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_APICODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ApiManagerPersistenceImpl.fetchByF_apiCode(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ApiManager apiManager = list.get(0);

					result = apiManager;

					cacheResult(apiManager);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_APICODE,
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
			return (ApiManager)result;
		}
	}

	/**
	 * Removes the api manager where apiCode = &#63; from the database.
	 *
	 * @param apiCode the api code
	 * @return the api manager that was removed
	 */
	@Override
	public ApiManager removeByF_apiCode(String apiCode)
		throws NoSuchApiManagerException {
		ApiManager apiManager = findByF_apiCode(apiCode);

		return remove(apiManager);
	}

	/**
	 * Returns the number of api managers where apiCode = &#63;.
	 *
	 * @param apiCode the api code
	 * @return the number of matching api managers
	 */
	@Override
	public int countByF_apiCode(String apiCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_APICODE;

		Object[] finderArgs = new Object[] { apiCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APIMANAGER_WHERE);

			boolean bindApiCode = false;

			if (apiCode == null) {
				query.append(_FINDER_COLUMN_F_APICODE_APICODE_1);
			}
			else if (apiCode.equals("")) {
				query.append(_FINDER_COLUMN_F_APICODE_APICODE_3);
			}
			else {
				bindApiCode = true;

				query.append(_FINDER_COLUMN_F_APICODE_APICODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindApiCode) {
					qPos.add(apiCode);
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

	private static final String _FINDER_COLUMN_F_APICODE_APICODE_1 = "apiManager.apiCode IS NULL";
	private static final String _FINDER_COLUMN_F_APICODE_APICODE_2 = "apiManager.apiCode = ?";
	private static final String _FINDER_COLUMN_F_APICODE_APICODE_3 = "(apiManager.apiCode IS NULL OR apiManager.apiCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, ApiManagerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, ApiManagerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID",
			new String[] { Long.class.getName() },
			ApiManagerModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID = new FinderPath(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the api managers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching api managers
	 */
	@Override
	public List<ApiManager> findByF_GID(long groupId) {
		return findByF_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the api managers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of api managers
	 * @param end the upper bound of the range of api managers (not inclusive)
	 * @return the range of matching api managers
	 */
	@Override
	public List<ApiManager> findByF_GID(long groupId, int start, int end) {
		return findByF_GID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the api managers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of api managers
	 * @param end the upper bound of the range of api managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching api managers
	 */
	@Override
	public List<ApiManager> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ApiManager> orderByComparator) {
		return findByF_GID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the api managers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of api managers
	 * @param end the upper bound of the range of api managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching api managers
	 */
	@Override
	public List<ApiManager> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ApiManager> orderByComparator,
		boolean retrieveFromCache) {
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

		List<ApiManager> list = null;

		if (retrieveFromCache) {
			list = (List<ApiManager>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ApiManager apiManager : list) {
					if ((groupId != apiManager.getGroupId())) {
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

			query.append(_SQL_SELECT_APIMANAGER_WHERE);

			query.append(_FINDER_COLUMN_F_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ApiManagerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ApiManager>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApiManager>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first api manager in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api manager
	 * @throws NoSuchApiManagerException if a matching api manager could not be found
	 */
	@Override
	public ApiManager findByF_GID_First(long groupId,
		OrderByComparator<ApiManager> orderByComparator)
		throws NoSuchApiManagerException {
		ApiManager apiManager = fetchByF_GID_First(groupId, orderByComparator);

		if (apiManager != null) {
			return apiManager;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchApiManagerException(msg.toString());
	}

	/**
	 * Returns the first api manager in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching api manager, or <code>null</code> if a matching api manager could not be found
	 */
	@Override
	public ApiManager fetchByF_GID_First(long groupId,
		OrderByComparator<ApiManager> orderByComparator) {
		List<ApiManager> list = findByF_GID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last api manager in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api manager
	 * @throws NoSuchApiManagerException if a matching api manager could not be found
	 */
	@Override
	public ApiManager findByF_GID_Last(long groupId,
		OrderByComparator<ApiManager> orderByComparator)
		throws NoSuchApiManagerException {
		ApiManager apiManager = fetchByF_GID_Last(groupId, orderByComparator);

		if (apiManager != null) {
			return apiManager;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchApiManagerException(msg.toString());
	}

	/**
	 * Returns the last api manager in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching api manager, or <code>null</code> if a matching api manager could not be found
	 */
	@Override
	public ApiManager fetchByF_GID_Last(long groupId,
		OrderByComparator<ApiManager> orderByComparator) {
		int count = countByF_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<ApiManager> list = findByF_GID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the api managers before and after the current api manager in the ordered set where groupId = &#63;.
	 *
	 * @param apiManagerId the primary key of the current api manager
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next api manager
	 * @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	 */
	@Override
	public ApiManager[] findByF_GID_PrevAndNext(long apiManagerId,
		long groupId, OrderByComparator<ApiManager> orderByComparator)
		throws NoSuchApiManagerException {
		ApiManager apiManager = findByPrimaryKey(apiManagerId);

		Session session = null;

		try {
			session = openSession();

			ApiManager[] array = new ApiManagerImpl[3];

			array[0] = getByF_GID_PrevAndNext(session, apiManager, groupId,
					orderByComparator, true);

			array[1] = apiManager;

			array[2] = getByF_GID_PrevAndNext(session, apiManager, groupId,
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

	protected ApiManager getByF_GID_PrevAndNext(Session session,
		ApiManager apiManager, long groupId,
		OrderByComparator<ApiManager> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APIMANAGER_WHERE);

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
			query.append(ApiManagerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(apiManager);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApiManager> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the api managers where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_GID(long groupId) {
		for (ApiManager apiManager : findByF_GID(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(apiManager);
		}
	}

	/**
	 * Returns the number of api managers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching api managers
	 */
	@Override
	public int countByF_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APIMANAGER_WHERE);

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

	private static final String _FINDER_COLUMN_F_GID_GROUPID_2 = "apiManager.groupId = ?";

	public ApiManagerPersistenceImpl() {
		setModelClass(ApiManager.class);
	}

	/**
	 * Caches the api manager in the entity cache if it is enabled.
	 *
	 * @param apiManager the api manager
	 */
	@Override
	public void cacheResult(ApiManager apiManager) {
		entityCache.putResult(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerImpl.class, apiManager.getPrimaryKey(), apiManager);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ID,
			new Object[] { apiManager.getApiManagerId() }, apiManager);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_APICODE,
			new Object[] { apiManager.getApiCode() }, apiManager);

		apiManager.resetOriginalValues();
	}

	/**
	 * Caches the api managers in the entity cache if it is enabled.
	 *
	 * @param apiManagers the api managers
	 */
	@Override
	public void cacheResult(List<ApiManager> apiManagers) {
		for (ApiManager apiManager : apiManagers) {
			if (entityCache.getResult(
						ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
						ApiManagerImpl.class, apiManager.getPrimaryKey()) == null) {
				cacheResult(apiManager);
			}
			else {
				apiManager.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all api managers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ApiManagerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the api manager.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApiManager apiManager) {
		entityCache.removeResult(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerImpl.class, apiManager.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ApiManagerModelImpl)apiManager, true);
	}

	@Override
	public void clearCache(List<ApiManager> apiManagers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ApiManager apiManager : apiManagers) {
			entityCache.removeResult(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
				ApiManagerImpl.class, apiManager.getPrimaryKey());

			clearUniqueFindersCache((ApiManagerModelImpl)apiManager, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ApiManagerModelImpl apiManagerModelImpl) {
		Object[] args = new Object[] { apiManagerModelImpl.getApiManagerId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_ID, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ID, args,
			apiManagerModelImpl, false);

		args = new Object[] { apiManagerModelImpl.getApiCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_APICODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_APICODE, args,
			apiManagerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ApiManagerModelImpl apiManagerModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { apiManagerModelImpl.getApiManagerId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ID, args);
		}

		if ((apiManagerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_ID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					apiManagerModelImpl.getOriginalApiManagerId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { apiManagerModelImpl.getApiCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_APICODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_APICODE, args);
		}

		if ((apiManagerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_APICODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					apiManagerModelImpl.getOriginalApiCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_APICODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_APICODE, args);
		}
	}

	/**
	 * Creates a new api manager with the primary key. Does not add the api manager to the database.
	 *
	 * @param apiManagerId the primary key for the new api manager
	 * @return the new api manager
	 */
	@Override
	public ApiManager create(long apiManagerId) {
		ApiManager apiManager = new ApiManagerImpl();

		apiManager.setNew(true);
		apiManager.setPrimaryKey(apiManagerId);

		return apiManager;
	}

	/**
	 * Removes the api manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param apiManagerId the primary key of the api manager
	 * @return the api manager that was removed
	 * @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	 */
	@Override
	public ApiManager remove(long apiManagerId)
		throws NoSuchApiManagerException {
		return remove((Serializable)apiManagerId);
	}

	/**
	 * Removes the api manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the api manager
	 * @return the api manager that was removed
	 * @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	 */
	@Override
	public ApiManager remove(Serializable primaryKey)
		throws NoSuchApiManagerException {
		Session session = null;

		try {
			session = openSession();

			ApiManager apiManager = (ApiManager)session.get(ApiManagerImpl.class,
					primaryKey);

			if (apiManager == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApiManagerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(apiManager);
		}
		catch (NoSuchApiManagerException nsee) {
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
	protected ApiManager removeImpl(ApiManager apiManager) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(apiManager)) {
				apiManager = (ApiManager)session.get(ApiManagerImpl.class,
						apiManager.getPrimaryKeyObj());
			}

			if (apiManager != null) {
				session.delete(apiManager);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (apiManager != null) {
			clearCache(apiManager);
		}

		return apiManager;
	}

	@Override
	public ApiManager updateImpl(ApiManager apiManager) {
		boolean isNew = apiManager.isNew();

		if (!(apiManager instanceof ApiManagerModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(apiManager.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(apiManager);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in apiManager proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ApiManager implementation " +
				apiManager.getClass());
		}

		ApiManagerModelImpl apiManagerModelImpl = (ApiManagerModelImpl)apiManager;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (apiManager.getCreateDate() == null)) {
			if (serviceContext == null) {
				apiManager.setCreateDate(now);
			}
			else {
				apiManager.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!apiManagerModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				apiManager.setModifiedDate(now);
			}
			else {
				apiManager.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (apiManager.isNew()) {
				session.save(apiManager);

				apiManager.setNew(false);
			}
			else {
				apiManager = (ApiManager)session.merge(apiManager);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ApiManagerModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { apiManagerModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((apiManagerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						apiManagerModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);

				args = new Object[] { apiManagerModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);
			}
		}

		entityCache.putResult(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
			ApiManagerImpl.class, apiManager.getPrimaryKey(), apiManager, false);

		clearUniqueFindersCache(apiManagerModelImpl, false);
		cacheUniqueFindersCache(apiManagerModelImpl);

		apiManager.resetOriginalValues();

		return apiManager;
	}

	/**
	 * Returns the api manager with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the api manager
	 * @return the api manager
	 * @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	 */
	@Override
	public ApiManager findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApiManagerException {
		ApiManager apiManager = fetchByPrimaryKey(primaryKey);

		if (apiManager == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApiManagerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return apiManager;
	}

	/**
	 * Returns the api manager with the primary key or throws a {@link NoSuchApiManagerException} if it could not be found.
	 *
	 * @param apiManagerId the primary key of the api manager
	 * @return the api manager
	 * @throws NoSuchApiManagerException if a api manager with the primary key could not be found
	 */
	@Override
	public ApiManager findByPrimaryKey(long apiManagerId)
		throws NoSuchApiManagerException {
		return findByPrimaryKey((Serializable)apiManagerId);
	}

	/**
	 * Returns the api manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the api manager
	 * @return the api manager, or <code>null</code> if a api manager with the primary key could not be found
	 */
	@Override
	public ApiManager fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
				ApiManagerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ApiManager apiManager = (ApiManager)serializable;

		if (apiManager == null) {
			Session session = null;

			try {
				session = openSession();

				apiManager = (ApiManager)session.get(ApiManagerImpl.class,
						primaryKey);

				if (apiManager != null) {
					cacheResult(apiManager);
				}
				else {
					entityCache.putResult(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
						ApiManagerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
					ApiManagerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return apiManager;
	}

	/**
	 * Returns the api manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param apiManagerId the primary key of the api manager
	 * @return the api manager, or <code>null</code> if a api manager with the primary key could not be found
	 */
	@Override
	public ApiManager fetchByPrimaryKey(long apiManagerId) {
		return fetchByPrimaryKey((Serializable)apiManagerId);
	}

	@Override
	public Map<Serializable, ApiManager> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ApiManager> map = new HashMap<Serializable, ApiManager>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ApiManager apiManager = fetchByPrimaryKey(primaryKey);

			if (apiManager != null) {
				map.put(primaryKey, apiManager);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
					ApiManagerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ApiManager)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_APIMANAGER_WHERE_PKS_IN);

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

			for (ApiManager apiManager : (List<ApiManager>)q.list()) {
				map.put(apiManager.getPrimaryKeyObj(), apiManager);

				cacheResult(apiManager);

				uncachedPrimaryKeys.remove(apiManager.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ApiManagerModelImpl.ENTITY_CACHE_ENABLED,
					ApiManagerImpl.class, primaryKey, nullModel);
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
	 * Returns all the api managers.
	 *
	 * @return the api managers
	 */
	@Override
	public List<ApiManager> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the api managers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of api managers
	 * @param end the upper bound of the range of api managers (not inclusive)
	 * @return the range of api managers
	 */
	@Override
	public List<ApiManager> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the api managers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of api managers
	 * @param end the upper bound of the range of api managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of api managers
	 */
	@Override
	public List<ApiManager> findAll(int start, int end,
		OrderByComparator<ApiManager> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the api managers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApiManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of api managers
	 * @param end the upper bound of the range of api managers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of api managers
	 */
	@Override
	public List<ApiManager> findAll(int start, int end,
		OrderByComparator<ApiManager> orderByComparator,
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

		List<ApiManager> list = null;

		if (retrieveFromCache) {
			list = (List<ApiManager>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_APIMANAGER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APIMANAGER;

				if (pagination) {
					sql = sql.concat(ApiManagerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ApiManager>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ApiManager>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the api managers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ApiManager apiManager : findAll()) {
			remove(apiManager);
		}
	}

	/**
	 * Returns the number of api managers.
	 *
	 * @return the number of api managers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APIMANAGER);

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
		return ApiManagerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the api manager persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ApiManagerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_APIMANAGER = "SELECT apiManager FROM ApiManager apiManager";
	private static final String _SQL_SELECT_APIMANAGER_WHERE_PKS_IN = "SELECT apiManager FROM ApiManager apiManager WHERE apiManagerId IN (";
	private static final String _SQL_SELECT_APIMANAGER_WHERE = "SELECT apiManager FROM ApiManager apiManager WHERE ";
	private static final String _SQL_COUNT_APIMANAGER = "SELECT COUNT(apiManager) FROM ApiManager apiManager";
	private static final String _SQL_COUNT_APIMANAGER_WHERE = "SELECT COUNT(apiManager) FROM ApiManager apiManager WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "apiManager.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ApiManager exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ApiManager exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ApiManagerPersistenceImpl.class);
}