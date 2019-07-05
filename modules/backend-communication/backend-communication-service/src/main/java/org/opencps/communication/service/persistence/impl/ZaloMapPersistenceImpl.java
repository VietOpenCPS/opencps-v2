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

import org.opencps.communication.exception.NoSuchZaloMapException;
import org.opencps.communication.model.ZaloMap;
import org.opencps.communication.model.impl.ZaloMapImpl;
import org.opencps.communication.model.impl.ZaloMapModelImpl;
import org.opencps.communication.service.persistence.ZaloMapPersistence;

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
 * The persistence implementation for the zalo map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see ZaloMapPersistence
 * @see org.opencps.communication.service.persistence.ZaloMapUtil
 * @generated
 */
@ProviderType
public class ZaloMapPersistenceImpl extends BasePersistenceImpl<ZaloMap>
	implements ZaloMapPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ZaloMapUtil} to access the zalo map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ZaloMapImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, ZaloMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, ZaloMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_F_UID = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, ZaloMapImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_UId",
			new String[] { String.class.getName() },
			ZaloMapModelImpl.UID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_UID = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_UId",
			new String[] { String.class.getName() });

	/**
	 * Returns the zalo map where uId = &#63; or throws a {@link NoSuchZaloMapException} if it could not be found.
	 *
	 * @param uId the u ID
	 * @return the matching zalo map
	 * @throws NoSuchZaloMapException if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap findByF_UId(String uId) throws NoSuchZaloMapException {
		ZaloMap zaloMap = fetchByF_UId(uId);

		if (zaloMap == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uId=");
			msg.append(uId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchZaloMapException(msg.toString());
		}

		return zaloMap;
	}

	/**
	 * Returns the zalo map where uId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uId the u ID
	 * @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap fetchByF_UId(String uId) {
		return fetchByF_UId(uId, true);
	}

	/**
	 * Returns the zalo map where uId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uId the u ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap fetchByF_UId(String uId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_UID,
					finderArgs, this);
		}

		if (result instanceof ZaloMap) {
			ZaloMap zaloMap = (ZaloMap)result;

			if (!Objects.equals(uId, zaloMap.getUId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ZALOMAP_WHERE);

			boolean bindUId = false;

			if (uId == null) {
				query.append(_FINDER_COLUMN_F_UID_UID_1);
			}
			else if (uId.equals("")) {
				query.append(_FINDER_COLUMN_F_UID_UID_3);
			}
			else {
				bindUId = true;

				query.append(_FINDER_COLUMN_F_UID_UID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUId) {
					qPos.add(uId);
				}

				List<ZaloMap> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_UID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ZaloMapPersistenceImpl.fetchByF_UId(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ZaloMap zaloMap = list.get(0);

					result = zaloMap;

					cacheResult(zaloMap);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_UID, finderArgs);

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
			return (ZaloMap)result;
		}
	}

	/**
	 * Removes the zalo map where uId = &#63; from the database.
	 *
	 * @param uId the u ID
	 * @return the zalo map that was removed
	 */
	@Override
	public ZaloMap removeByF_UId(String uId) throws NoSuchZaloMapException {
		ZaloMap zaloMap = findByF_UId(uId);

		return remove(zaloMap);
	}

	/**
	 * Returns the number of zalo maps where uId = &#63;.
	 *
	 * @param uId the u ID
	 * @return the number of matching zalo maps
	 */
	@Override
	public int countByF_UId(String uId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_UID;

		Object[] finderArgs = new Object[] { uId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ZALOMAP_WHERE);

			boolean bindUId = false;

			if (uId == null) {
				query.append(_FINDER_COLUMN_F_UID_UID_1);
			}
			else if (uId.equals("")) {
				query.append(_FINDER_COLUMN_F_UID_UID_3);
			}
			else {
				bindUId = true;

				query.append(_FINDER_COLUMN_F_UID_UID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUId) {
					qPos.add(uId);
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

	private static final String _FINDER_COLUMN_F_UID_UID_1 = "zaloMap.uId IS NULL";
	private static final String _FINDER_COLUMN_F_UID_UID_2 = "zaloMap.uId = ?";
	private static final String _FINDER_COLUMN_F_UID_UID_3 = "(zaloMap.uId IS NULL OR zaloMap.uId = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_TELNO = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, ZaloMapImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_TelNo",
			new String[] { String.class.getName() },
			ZaloMapModelImpl.TELNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_TELNO = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_TelNo",
			new String[] { String.class.getName() });

	/**
	 * Returns the zalo map where telNo = &#63; or throws a {@link NoSuchZaloMapException} if it could not be found.
	 *
	 * @param telNo the tel no
	 * @return the matching zalo map
	 * @throws NoSuchZaloMapException if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap findByF_TelNo(String telNo) throws NoSuchZaloMapException {
		ZaloMap zaloMap = fetchByF_TelNo(telNo);

		if (zaloMap == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("telNo=");
			msg.append(telNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchZaloMapException(msg.toString());
		}

		return zaloMap;
	}

	/**
	 * Returns the zalo map where telNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param telNo the tel no
	 * @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap fetchByF_TelNo(String telNo) {
		return fetchByF_TelNo(telNo, true);
	}

	/**
	 * Returns the zalo map where telNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param telNo the tel no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap fetchByF_TelNo(String telNo, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { telNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_TELNO,
					finderArgs, this);
		}

		if (result instanceof ZaloMap) {
			ZaloMap zaloMap = (ZaloMap)result;

			if (!Objects.equals(telNo, zaloMap.getTelNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ZALOMAP_WHERE);

			boolean bindTelNo = false;

			if (telNo == null) {
				query.append(_FINDER_COLUMN_F_TELNO_TELNO_1);
			}
			else if (telNo.equals("")) {
				query.append(_FINDER_COLUMN_F_TELNO_TELNO_3);
			}
			else {
				bindTelNo = true;

				query.append(_FINDER_COLUMN_F_TELNO_TELNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTelNo) {
					qPos.add(telNo);
				}

				List<ZaloMap> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_TELNO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ZaloMapPersistenceImpl.fetchByF_TelNo(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ZaloMap zaloMap = list.get(0);

					result = zaloMap;

					cacheResult(zaloMap);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TELNO,
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
			return (ZaloMap)result;
		}
	}

	/**
	 * Removes the zalo map where telNo = &#63; from the database.
	 *
	 * @param telNo the tel no
	 * @return the zalo map that was removed
	 */
	@Override
	public ZaloMap removeByF_TelNo(String telNo) throws NoSuchZaloMapException {
		ZaloMap zaloMap = findByF_TelNo(telNo);

		return remove(zaloMap);
	}

	/**
	 * Returns the number of zalo maps where telNo = &#63;.
	 *
	 * @param telNo the tel no
	 * @return the number of matching zalo maps
	 */
	@Override
	public int countByF_TelNo(String telNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_TELNO;

		Object[] finderArgs = new Object[] { telNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ZALOMAP_WHERE);

			boolean bindTelNo = false;

			if (telNo == null) {
				query.append(_FINDER_COLUMN_F_TELNO_TELNO_1);
			}
			else if (telNo.equals("")) {
				query.append(_FINDER_COLUMN_F_TELNO_TELNO_3);
			}
			else {
				bindTelNo = true;

				query.append(_FINDER_COLUMN_F_TELNO_TELNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTelNo) {
					qPos.add(telNo);
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

	private static final String _FINDER_COLUMN_F_TELNO_TELNO_1 = "zaloMap.telNo IS NULL";
	private static final String _FINDER_COLUMN_F_TELNO_TELNO_2 = "zaloMap.telNo = ?";
	private static final String _FINDER_COLUMN_F_TELNO_TELNO_3 = "(zaloMap.telNo IS NULL OR zaloMap.telNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, ZaloMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, ZaloMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID",
			new String[] { Long.class.getName() },
			ZaloMapModelImpl.GROUPID_COLUMN_BITMASK |
			ZaloMapModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the zalo maps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching zalo maps
	 */
	@Override
	public List<ZaloMap> findByF_GID(long groupId) {
		return findByF_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the zalo maps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of zalo maps
	 * @param end the upper bound of the range of zalo maps (not inclusive)
	 * @return the range of matching zalo maps
	 */
	@Override
	public List<ZaloMap> findByF_GID(long groupId, int start, int end) {
		return findByF_GID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the zalo maps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of zalo maps
	 * @param end the upper bound of the range of zalo maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching zalo maps
	 */
	@Override
	public List<ZaloMap> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ZaloMap> orderByComparator) {
		return findByF_GID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the zalo maps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of zalo maps
	 * @param end the upper bound of the range of zalo maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching zalo maps
	 */
	@Override
	public List<ZaloMap> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ZaloMap> orderByComparator, boolean retrieveFromCache) {
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

		List<ZaloMap> list = null;

		if (retrieveFromCache) {
			list = (List<ZaloMap>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (ZaloMap zaloMap : list) {
					if ((groupId != zaloMap.getGroupId())) {
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

			query.append(_SQL_SELECT_ZALOMAP_WHERE);

			query.append(_FINDER_COLUMN_F_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ZaloMapModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ZaloMap>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ZaloMap>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first zalo map in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zalo map
	 * @throws NoSuchZaloMapException if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap findByF_GID_First(long groupId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException {
		ZaloMap zaloMap = fetchByF_GID_First(groupId, orderByComparator);

		if (zaloMap != null) {
			return zaloMap;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchZaloMapException(msg.toString());
	}

	/**
	 * Returns the first zalo map in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zalo map, or <code>null</code> if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap fetchByF_GID_First(long groupId,
		OrderByComparator<ZaloMap> orderByComparator) {
		List<ZaloMap> list = findByF_GID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last zalo map in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zalo map
	 * @throws NoSuchZaloMapException if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap findByF_GID_Last(long groupId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException {
		ZaloMap zaloMap = fetchByF_GID_Last(groupId, orderByComparator);

		if (zaloMap != null) {
			return zaloMap;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchZaloMapException(msg.toString());
	}

	/**
	 * Returns the last zalo map in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zalo map, or <code>null</code> if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap fetchByF_GID_Last(long groupId,
		OrderByComparator<ZaloMap> orderByComparator) {
		int count = countByF_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<ZaloMap> list = findByF_GID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the zalo maps before and after the current zalo map in the ordered set where groupId = &#63;.
	 *
	 * @param zaloMapId the primary key of the current zalo map
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next zalo map
	 * @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	 */
	@Override
	public ZaloMap[] findByF_GID_PrevAndNext(long zaloMapId, long groupId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException {
		ZaloMap zaloMap = findByPrimaryKey(zaloMapId);

		Session session = null;

		try {
			session = openSession();

			ZaloMap[] array = new ZaloMapImpl[3];

			array[0] = getByF_GID_PrevAndNext(session, zaloMap, groupId,
					orderByComparator, true);

			array[1] = zaloMap;

			array[2] = getByF_GID_PrevAndNext(session, zaloMap, groupId,
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

	protected ZaloMap getByF_GID_PrevAndNext(Session session, ZaloMap zaloMap,
		long groupId, OrderByComparator<ZaloMap> orderByComparator,
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

		query.append(_SQL_SELECT_ZALOMAP_WHERE);

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
			query.append(ZaloMapModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(zaloMap);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ZaloMap> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the zalo maps where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_GID(long groupId) {
		for (ZaloMap zaloMap : findByF_GID(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(zaloMap);
		}
	}

	/**
	 * Returns the number of zalo maps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching zalo maps
	 */
	@Override
	public int countByF_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ZALOMAP_WHERE);

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

	private static final String _FINDER_COLUMN_F_GID_GROUPID_2 = "zaloMap.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_OAID = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, ZaloMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_OAId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OAID =
		new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, ZaloMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_OAId",
			new String[] { String.class.getName() },
			ZaloMapModelImpl.OAID_COLUMN_BITMASK |
			ZaloMapModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_OAID = new FinderPath(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_OAId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the zalo maps where oAId = &#63;.
	 *
	 * @param oAId the o a ID
	 * @return the matching zalo maps
	 */
	@Override
	public List<ZaloMap> findByF_OAId(String oAId) {
		return findByF_OAId(oAId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the zalo maps where oAId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param oAId the o a ID
	 * @param start the lower bound of the range of zalo maps
	 * @param end the upper bound of the range of zalo maps (not inclusive)
	 * @return the range of matching zalo maps
	 */
	@Override
	public List<ZaloMap> findByF_OAId(String oAId, int start, int end) {
		return findByF_OAId(oAId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the zalo maps where oAId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param oAId the o a ID
	 * @param start the lower bound of the range of zalo maps
	 * @param end the upper bound of the range of zalo maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching zalo maps
	 */
	@Override
	public List<ZaloMap> findByF_OAId(String oAId, int start, int end,
		OrderByComparator<ZaloMap> orderByComparator) {
		return findByF_OAId(oAId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the zalo maps where oAId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param oAId the o a ID
	 * @param start the lower bound of the range of zalo maps
	 * @param end the upper bound of the range of zalo maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching zalo maps
	 */
	@Override
	public List<ZaloMap> findByF_OAId(String oAId, int start, int end,
		OrderByComparator<ZaloMap> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OAID;
			finderArgs = new Object[] { oAId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_OAID;
			finderArgs = new Object[] { oAId, start, end, orderByComparator };
		}

		List<ZaloMap> list = null;

		if (retrieveFromCache) {
			list = (List<ZaloMap>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (ZaloMap zaloMap : list) {
					if (!Objects.equals(oAId, zaloMap.getOAId())) {
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

			query.append(_SQL_SELECT_ZALOMAP_WHERE);

			boolean bindOAId = false;

			if (oAId == null) {
				query.append(_FINDER_COLUMN_F_OAID_OAID_1);
			}
			else if (oAId.equals("")) {
				query.append(_FINDER_COLUMN_F_OAID_OAID_3);
			}
			else {
				bindOAId = true;

				query.append(_FINDER_COLUMN_F_OAID_OAID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ZaloMapModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOAId) {
					qPos.add(oAId);
				}

				if (!pagination) {
					list = (List<ZaloMap>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ZaloMap>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first zalo map in the ordered set where oAId = &#63;.
	 *
	 * @param oAId the o a ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zalo map
	 * @throws NoSuchZaloMapException if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap findByF_OAId_First(String oAId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException {
		ZaloMap zaloMap = fetchByF_OAId_First(oAId, orderByComparator);

		if (zaloMap != null) {
			return zaloMap;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("oAId=");
		msg.append(oAId);

		msg.append("}");

		throw new NoSuchZaloMapException(msg.toString());
	}

	/**
	 * Returns the first zalo map in the ordered set where oAId = &#63;.
	 *
	 * @param oAId the o a ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zalo map, or <code>null</code> if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap fetchByF_OAId_First(String oAId,
		OrderByComparator<ZaloMap> orderByComparator) {
		List<ZaloMap> list = findByF_OAId(oAId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last zalo map in the ordered set where oAId = &#63;.
	 *
	 * @param oAId the o a ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zalo map
	 * @throws NoSuchZaloMapException if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap findByF_OAId_Last(String oAId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException {
		ZaloMap zaloMap = fetchByF_OAId_Last(oAId, orderByComparator);

		if (zaloMap != null) {
			return zaloMap;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("oAId=");
		msg.append(oAId);

		msg.append("}");

		throw new NoSuchZaloMapException(msg.toString());
	}

	/**
	 * Returns the last zalo map in the ordered set where oAId = &#63;.
	 *
	 * @param oAId the o a ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zalo map, or <code>null</code> if a matching zalo map could not be found
	 */
	@Override
	public ZaloMap fetchByF_OAId_Last(String oAId,
		OrderByComparator<ZaloMap> orderByComparator) {
		int count = countByF_OAId(oAId);

		if (count == 0) {
			return null;
		}

		List<ZaloMap> list = findByF_OAId(oAId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the zalo maps before and after the current zalo map in the ordered set where oAId = &#63;.
	 *
	 * @param zaloMapId the primary key of the current zalo map
	 * @param oAId the o a ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next zalo map
	 * @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	 */
	@Override
	public ZaloMap[] findByF_OAId_PrevAndNext(long zaloMapId, String oAId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException {
		ZaloMap zaloMap = findByPrimaryKey(zaloMapId);

		Session session = null;

		try {
			session = openSession();

			ZaloMap[] array = new ZaloMapImpl[3];

			array[0] = getByF_OAId_PrevAndNext(session, zaloMap, oAId,
					orderByComparator, true);

			array[1] = zaloMap;

			array[2] = getByF_OAId_PrevAndNext(session, zaloMap, oAId,
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

	protected ZaloMap getByF_OAId_PrevAndNext(Session session, ZaloMap zaloMap,
		String oAId, OrderByComparator<ZaloMap> orderByComparator,
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

		query.append(_SQL_SELECT_ZALOMAP_WHERE);

		boolean bindOAId = false;

		if (oAId == null) {
			query.append(_FINDER_COLUMN_F_OAID_OAID_1);
		}
		else if (oAId.equals("")) {
			query.append(_FINDER_COLUMN_F_OAID_OAID_3);
		}
		else {
			bindOAId = true;

			query.append(_FINDER_COLUMN_F_OAID_OAID_2);
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
			query.append(ZaloMapModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindOAId) {
			qPos.add(oAId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(zaloMap);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ZaloMap> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the zalo maps where oAId = &#63; from the database.
	 *
	 * @param oAId the o a ID
	 */
	@Override
	public void removeByF_OAId(String oAId) {
		for (ZaloMap zaloMap : findByF_OAId(oAId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(zaloMap);
		}
	}

	/**
	 * Returns the number of zalo maps where oAId = &#63;.
	 *
	 * @param oAId the o a ID
	 * @return the number of matching zalo maps
	 */
	@Override
	public int countByF_OAId(String oAId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_OAID;

		Object[] finderArgs = new Object[] { oAId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ZALOMAP_WHERE);

			boolean bindOAId = false;

			if (oAId == null) {
				query.append(_FINDER_COLUMN_F_OAID_OAID_1);
			}
			else if (oAId.equals("")) {
				query.append(_FINDER_COLUMN_F_OAID_OAID_3);
			}
			else {
				bindOAId = true;

				query.append(_FINDER_COLUMN_F_OAID_OAID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOAId) {
					qPos.add(oAId);
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

	private static final String _FINDER_COLUMN_F_OAID_OAID_1 = "zaloMap.oAId IS NULL";
	private static final String _FINDER_COLUMN_F_OAID_OAID_2 = "zaloMap.oAId = ?";
	private static final String _FINDER_COLUMN_F_OAID_OAID_3 = "(zaloMap.oAId IS NULL OR zaloMap.oAId = '')";

	public ZaloMapPersistenceImpl() {
		setModelClass(ZaloMap.class);
	}

	/**
	 * Caches the zalo map in the entity cache if it is enabled.
	 *
	 * @param zaloMap the zalo map
	 */
	@Override
	public void cacheResult(ZaloMap zaloMap) {
		entityCache.putResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapImpl.class, zaloMap.getPrimaryKey(), zaloMap);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_UID,
			new Object[] { zaloMap.getUId() }, zaloMap);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_TELNO,
			new Object[] { zaloMap.getTelNo() }, zaloMap);

		zaloMap.resetOriginalValues();
	}

	/**
	 * Caches the zalo maps in the entity cache if it is enabled.
	 *
	 * @param zaloMaps the zalo maps
	 */
	@Override
	public void cacheResult(List<ZaloMap> zaloMaps) {
		for (ZaloMap zaloMap : zaloMaps) {
			if (entityCache.getResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
						ZaloMapImpl.class, zaloMap.getPrimaryKey()) == null) {
				cacheResult(zaloMap);
			}
			else {
				zaloMap.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all zalo maps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ZaloMapImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the zalo map.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ZaloMap zaloMap) {
		entityCache.removeResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapImpl.class, zaloMap.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ZaloMapModelImpl)zaloMap, true);
	}

	@Override
	public void clearCache(List<ZaloMap> zaloMaps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ZaloMap zaloMap : zaloMaps) {
			entityCache.removeResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
				ZaloMapImpl.class, zaloMap.getPrimaryKey());

			clearUniqueFindersCache((ZaloMapModelImpl)zaloMap, true);
		}
	}

	protected void cacheUniqueFindersCache(ZaloMapModelImpl zaloMapModelImpl) {
		Object[] args = new Object[] { zaloMapModelImpl.getUId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_UID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_UID, args,
			zaloMapModelImpl, false);

		args = new Object[] { zaloMapModelImpl.getTelNo() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_TELNO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_TELNO, args,
			zaloMapModelImpl, false);
	}

	protected void clearUniqueFindersCache(ZaloMapModelImpl zaloMapModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { zaloMapModelImpl.getUId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_UID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_UID, args);
		}

		if ((zaloMapModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_UID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { zaloMapModelImpl.getOriginalUId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_UID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_UID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { zaloMapModelImpl.getTelNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TELNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TELNO, args);
		}

		if ((zaloMapModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_TELNO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { zaloMapModelImpl.getOriginalTelNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TELNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TELNO, args);
		}
	}

	/**
	 * Creates a new zalo map with the primary key. Does not add the zalo map to the database.
	 *
	 * @param zaloMapId the primary key for the new zalo map
	 * @return the new zalo map
	 */
	@Override
	public ZaloMap create(long zaloMapId) {
		ZaloMap zaloMap = new ZaloMapImpl();

		zaloMap.setNew(true);
		zaloMap.setPrimaryKey(zaloMapId);

		zaloMap.setCompanyId(companyProvider.getCompanyId());

		return zaloMap;
	}

	/**
	 * Removes the zalo map with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zaloMapId the primary key of the zalo map
	 * @return the zalo map that was removed
	 * @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	 */
	@Override
	public ZaloMap remove(long zaloMapId) throws NoSuchZaloMapException {
		return remove((Serializable)zaloMapId);
	}

	/**
	 * Removes the zalo map with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the zalo map
	 * @return the zalo map that was removed
	 * @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	 */
	@Override
	public ZaloMap remove(Serializable primaryKey)
		throws NoSuchZaloMapException {
		Session session = null;

		try {
			session = openSession();

			ZaloMap zaloMap = (ZaloMap)session.get(ZaloMapImpl.class, primaryKey);

			if (zaloMap == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchZaloMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(zaloMap);
		}
		catch (NoSuchZaloMapException nsee) {
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
	protected ZaloMap removeImpl(ZaloMap zaloMap) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(zaloMap)) {
				zaloMap = (ZaloMap)session.get(ZaloMapImpl.class,
						zaloMap.getPrimaryKeyObj());
			}

			if (zaloMap != null) {
				session.delete(zaloMap);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (zaloMap != null) {
			clearCache(zaloMap);
		}

		return zaloMap;
	}

	@Override
	public ZaloMap updateImpl(ZaloMap zaloMap) {
		boolean isNew = zaloMap.isNew();

		if (!(zaloMap instanceof ZaloMapModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(zaloMap.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(zaloMap);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in zaloMap proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ZaloMap implementation " +
				zaloMap.getClass());
		}

		ZaloMapModelImpl zaloMapModelImpl = (ZaloMapModelImpl)zaloMap;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (zaloMap.getCreateDate() == null)) {
			if (serviceContext == null) {
				zaloMap.setCreateDate(now);
			}
			else {
				zaloMap.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!zaloMapModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				zaloMap.setModifiedDate(now);
			}
			else {
				zaloMap.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (zaloMap.isNew()) {
				session.save(zaloMap);

				zaloMap.setNew(false);
			}
			else {
				zaloMap = (ZaloMap)session.merge(zaloMap);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ZaloMapModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { zaloMapModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
				args);

			args = new Object[] { zaloMapModelImpl.getOAId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_OAID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OAID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((zaloMapModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						zaloMapModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);

				args = new Object[] { zaloMapModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);
			}

			if ((zaloMapModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OAID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { zaloMapModelImpl.getOriginalOAId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_OAID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OAID,
					args);

				args = new Object[] { zaloMapModelImpl.getOAId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_OAID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_OAID,
					args);
			}
		}

		entityCache.putResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
			ZaloMapImpl.class, zaloMap.getPrimaryKey(), zaloMap, false);

		clearUniqueFindersCache(zaloMapModelImpl, false);
		cacheUniqueFindersCache(zaloMapModelImpl);

		zaloMap.resetOriginalValues();

		return zaloMap;
	}

	/**
	 * Returns the zalo map with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the zalo map
	 * @return the zalo map
	 * @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	 */
	@Override
	public ZaloMap findByPrimaryKey(Serializable primaryKey)
		throws NoSuchZaloMapException {
		ZaloMap zaloMap = fetchByPrimaryKey(primaryKey);

		if (zaloMap == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchZaloMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return zaloMap;
	}

	/**
	 * Returns the zalo map with the primary key or throws a {@link NoSuchZaloMapException} if it could not be found.
	 *
	 * @param zaloMapId the primary key of the zalo map
	 * @return the zalo map
	 * @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	 */
	@Override
	public ZaloMap findByPrimaryKey(long zaloMapId)
		throws NoSuchZaloMapException {
		return findByPrimaryKey((Serializable)zaloMapId);
	}

	/**
	 * Returns the zalo map with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the zalo map
	 * @return the zalo map, or <code>null</code> if a zalo map with the primary key could not be found
	 */
	@Override
	public ZaloMap fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
				ZaloMapImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ZaloMap zaloMap = (ZaloMap)serializable;

		if (zaloMap == null) {
			Session session = null;

			try {
				session = openSession();

				zaloMap = (ZaloMap)session.get(ZaloMapImpl.class, primaryKey);

				if (zaloMap != null) {
					cacheResult(zaloMap);
				}
				else {
					entityCache.putResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
						ZaloMapImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
					ZaloMapImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return zaloMap;
	}

	/**
	 * Returns the zalo map with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param zaloMapId the primary key of the zalo map
	 * @return the zalo map, or <code>null</code> if a zalo map with the primary key could not be found
	 */
	@Override
	public ZaloMap fetchByPrimaryKey(long zaloMapId) {
		return fetchByPrimaryKey((Serializable)zaloMapId);
	}

	@Override
	public Map<Serializable, ZaloMap> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ZaloMap> map = new HashMap<Serializable, ZaloMap>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ZaloMap zaloMap = fetchByPrimaryKey(primaryKey);

			if (zaloMap != null) {
				map.put(primaryKey, zaloMap);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
					ZaloMapImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ZaloMap)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ZALOMAP_WHERE_PKS_IN);

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

			for (ZaloMap zaloMap : (List<ZaloMap>)q.list()) {
				map.put(zaloMap.getPrimaryKeyObj(), zaloMap);

				cacheResult(zaloMap);

				uncachedPrimaryKeys.remove(zaloMap.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ZaloMapModelImpl.ENTITY_CACHE_ENABLED,
					ZaloMapImpl.class, primaryKey, nullModel);
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
	 * Returns all the zalo maps.
	 *
	 * @return the zalo maps
	 */
	@Override
	public List<ZaloMap> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the zalo maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zalo maps
	 * @param end the upper bound of the range of zalo maps (not inclusive)
	 * @return the range of zalo maps
	 */
	@Override
	public List<ZaloMap> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the zalo maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zalo maps
	 * @param end the upper bound of the range of zalo maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of zalo maps
	 */
	@Override
	public List<ZaloMap> findAll(int start, int end,
		OrderByComparator<ZaloMap> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the zalo maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zalo maps
	 * @param end the upper bound of the range of zalo maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of zalo maps
	 */
	@Override
	public List<ZaloMap> findAll(int start, int end,
		OrderByComparator<ZaloMap> orderByComparator, boolean retrieveFromCache) {
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

		List<ZaloMap> list = null;

		if (retrieveFromCache) {
			list = (List<ZaloMap>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ZALOMAP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ZALOMAP;

				if (pagination) {
					sql = sql.concat(ZaloMapModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ZaloMap>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ZaloMap>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the zalo maps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ZaloMap zaloMap : findAll()) {
			remove(zaloMap);
		}
	}

	/**
	 * Returns the number of zalo maps.
	 *
	 * @return the number of zalo maps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ZALOMAP);

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
		return ZaloMapModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the zalo map persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ZaloMapImpl.class.getName());
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
	private static final String _SQL_SELECT_ZALOMAP = "SELECT zaloMap FROM ZaloMap zaloMap";
	private static final String _SQL_SELECT_ZALOMAP_WHERE_PKS_IN = "SELECT zaloMap FROM ZaloMap zaloMap WHERE zaloMapId IN (";
	private static final String _SQL_SELECT_ZALOMAP_WHERE = "SELECT zaloMap FROM ZaloMap zaloMap WHERE ";
	private static final String _SQL_COUNT_ZALOMAP = "SELECT COUNT(zaloMap) FROM ZaloMap zaloMap";
	private static final String _SQL_COUNT_ZALOMAP_WHERE = "SELECT COUNT(zaloMap) FROM ZaloMap zaloMap WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "zaloMap.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ZaloMap exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ZaloMap exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ZaloMapPersistenceImpl.class);
}