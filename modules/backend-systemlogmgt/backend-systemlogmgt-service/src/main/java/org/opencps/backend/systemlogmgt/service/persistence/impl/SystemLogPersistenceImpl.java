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

package org.opencps.backend.systemlogmgt.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException;
import org.opencps.backend.systemlogmgt.model.SystemLog;
import org.opencps.backend.systemlogmgt.model.impl.SystemLogImpl;
import org.opencps.backend.systemlogmgt.model.impl.SystemLogModelImpl;
import org.opencps.backend.systemlogmgt.service.persistence.SystemLogPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the system log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogPersistence
 * @see org.opencps.backend.systemlogmgt.service.persistence.SystemLogUtil
 * @generated
 */
@ProviderType
public class SystemLogPersistenceImpl extends BasePersistenceImpl<SystemLog>
	implements SystemLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SystemLogUtil} to access the system log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SystemLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SystemLogModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!Objects.equals(uuid, systemLog.getUuid())) {
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

			query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SystemLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<SystemLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SystemLog>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first system log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByUuid_First(String uuid,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByUuid_First(uuid, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByUuid_First(String uuid,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByUuid_Last(String uuid,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByUuid_Last(uuid, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByUuid_Last(String uuid,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where uuid = &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findByUuid_PrevAndNext(long logId, String uuid,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByUuid_PrevAndNext(session, systemLog, uuid,
					orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByUuid_PrevAndNext(session, systemLog, uuid,
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

	protected SystemLog getByUuid_PrevAndNext(Session session,
		SystemLog systemLog, String uuid,
		OrderByComparator<SystemLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(SystemLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(systemLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SystemLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the system logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SystemLog systemLog : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching system logs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "systemLog.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "systemLog.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(systemLog.uuid IS NULL OR systemLog.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SystemLogModelImpl.UUID_COLUMN_BITMASK |
			SystemLogModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the system log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSystemLogException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByUUID_G(String uuid, long groupId)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByUUID_G(uuid, groupId);

		if (systemLog == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSystemLogException(msg.toString());
		}

		return systemLog;
	}

	/**
	 * Returns the system log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the system log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof SystemLog) {
			SystemLog systemLog = (SystemLog)result;

			if (!Objects.equals(uuid, systemLog.getUuid()) ||
					(groupId != systemLog.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<SystemLog> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					SystemLog systemLog = list.get(0);

					result = systemLog;

					cacheResult(systemLog);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

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
			return (SystemLog)result;
		}
	}

	/**
	 * Removes the system log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the system log that was removed
	 */
	@Override
	public SystemLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByUUID_G(uuid, groupId);

		return remove(systemLog);
	}

	/**
	 * Returns the number of system logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching system logs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "systemLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "systemLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(systemLog.uuid IS NULL OR systemLog.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "systemLog.groupId = ?";

	public SystemLogPersistenceImpl() {
		setModelClass(SystemLog.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the system log in the entity cache if it is enabled.
	 *
	 * @param systemLog the system log
	 */
	@Override
	public void cacheResult(SystemLog systemLog) {
		entityCache.putResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogImpl.class, systemLog.getPrimaryKey(), systemLog);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { systemLog.getUuid(), systemLog.getGroupId() },
			systemLog);

		systemLog.resetOriginalValues();
	}

	/**
	 * Caches the system logs in the entity cache if it is enabled.
	 *
	 * @param systemLogs the system logs
	 */
	@Override
	public void cacheResult(List<SystemLog> systemLogs) {
		for (SystemLog systemLog : systemLogs) {
			if (entityCache.getResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
						SystemLogImpl.class, systemLog.getPrimaryKey()) == null) {
				cacheResult(systemLog);
			}
			else {
				systemLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all system logs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SystemLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the system log.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SystemLog systemLog) {
		entityCache.removeResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogImpl.class, systemLog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SystemLogModelImpl)systemLog, true);
	}

	@Override
	public void clearCache(List<SystemLog> systemLogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SystemLog systemLog : systemLogs) {
			entityCache.removeResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
				SystemLogImpl.class, systemLog.getPrimaryKey());

			clearUniqueFindersCache((SystemLogModelImpl)systemLog, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SystemLogModelImpl systemLogModelImpl) {
		Object[] args = new Object[] {
				systemLogModelImpl.getUuid(), systemLogModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			systemLogModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SystemLogModelImpl systemLogModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					systemLogModelImpl.getUuid(),
					systemLogModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((systemLogModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					systemLogModelImpl.getOriginalUuid(),
					systemLogModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new system log with the primary key. Does not add the system log to the database.
	 *
	 * @param logId the primary key for the new system log
	 * @return the new system log
	 */
	@Override
	public SystemLog create(long logId) {
		SystemLog systemLog = new SystemLogImpl();

		systemLog.setNew(true);
		systemLog.setPrimaryKey(logId);

		String uuid = PortalUUIDUtil.generate();

		systemLog.setUuid(uuid);

		return systemLog;
	}

	/**
	 * Removes the system log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param logId the primary key of the system log
	 * @return the system log that was removed
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog remove(long logId) throws NoSuchSystemLogException {
		return remove((Serializable)logId);
	}

	/**
	 * Removes the system log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the system log
	 * @return the system log that was removed
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog remove(Serializable primaryKey)
		throws NoSuchSystemLogException {
		Session session = null;

		try {
			session = openSession();

			SystemLog systemLog = (SystemLog)session.get(SystemLogImpl.class,
					primaryKey);

			if (systemLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSystemLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(systemLog);
		}
		catch (NoSuchSystemLogException nsee) {
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
	protected SystemLog removeImpl(SystemLog systemLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(systemLog)) {
				systemLog = (SystemLog)session.get(SystemLogImpl.class,
						systemLog.getPrimaryKeyObj());
			}

			if (systemLog != null) {
				session.delete(systemLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (systemLog != null) {
			clearCache(systemLog);
		}

		return systemLog;
	}

	@Override
	public SystemLog updateImpl(SystemLog systemLog) {
		boolean isNew = systemLog.isNew();

		if (!(systemLog instanceof SystemLogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(systemLog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(systemLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in systemLog proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SystemLog implementation " +
				systemLog.getClass());
		}

		SystemLogModelImpl systemLogModelImpl = (SystemLogModelImpl)systemLog;

		if (Validator.isNull(systemLog.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			systemLog.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (systemLog.isNew()) {
				session.save(systemLog);

				systemLog.setNew(false);
			}
			else {
				systemLog = (SystemLog)session.merge(systemLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SystemLogModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { systemLogModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { systemLogModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogImpl.class, systemLog.getPrimaryKey(), systemLog, false);

		clearUniqueFindersCache(systemLogModelImpl, false);
		cacheUniqueFindersCache(systemLogModelImpl);

		systemLog.resetOriginalValues();

		return systemLog;
	}

	/**
	 * Returns the system log with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the system log
	 * @return the system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByPrimaryKey(primaryKey);

		if (systemLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSystemLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return systemLog;
	}

	/**
	 * Returns the system log with the primary key or throws a {@link NoSuchSystemLogException} if it could not be found.
	 *
	 * @param logId the primary key of the system log
	 * @return the system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog findByPrimaryKey(long logId)
		throws NoSuchSystemLogException {
		return findByPrimaryKey((Serializable)logId);
	}

	/**
	 * Returns the system log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the system log
	 * @return the system log, or <code>null</code> if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
				SystemLogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SystemLog systemLog = (SystemLog)serializable;

		if (systemLog == null) {
			Session session = null;

			try {
				session = openSession();

				systemLog = (SystemLog)session.get(SystemLogImpl.class,
						primaryKey);

				if (systemLog != null) {
					cacheResult(systemLog);
				}
				else {
					entityCache.putResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
						SystemLogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
					SystemLogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return systemLog;
	}

	/**
	 * Returns the system log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param logId the primary key of the system log
	 * @return the system log, or <code>null</code> if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog fetchByPrimaryKey(long logId) {
		return fetchByPrimaryKey((Serializable)logId);
	}

	@Override
	public Map<Serializable, SystemLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SystemLog> map = new HashMap<Serializable, SystemLog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SystemLog systemLog = fetchByPrimaryKey(primaryKey);

			if (systemLog != null) {
				map.put(primaryKey, systemLog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
					SystemLogImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SystemLog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SYSTEMLOG_WHERE_PKS_IN);

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

			for (SystemLog systemLog : (List<SystemLog>)q.list()) {
				map.put(systemLog.getPrimaryKeyObj(), systemLog);

				cacheResult(systemLog);

				uncachedPrimaryKeys.remove(systemLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
					SystemLogImpl.class, primaryKey, nullModel);
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
	 * Returns all the system logs.
	 *
	 * @return the system logs
	 */
	@Override
	public List<SystemLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of system logs
	 */
	@Override
	public List<SystemLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of system logs
	 */
	@Override
	public List<SystemLog> findAll(int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of system logs
	 */
	@Override
	public List<SystemLog> findAll(int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
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

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SYSTEMLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SYSTEMLOG;

				if (pagination) {
					sql = sql.concat(SystemLogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SystemLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SystemLog>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the system logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SystemLog systemLog : findAll()) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs.
	 *
	 * @return the number of system logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SYSTEMLOG);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SystemLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the system log persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SystemLogImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SYSTEMLOG = "SELECT systemLog FROM SystemLog systemLog";
	private static final String _SQL_SELECT_SYSTEMLOG_WHERE_PKS_IN = "SELECT systemLog FROM SystemLog systemLog WHERE logId IN (";
	private static final String _SQL_SELECT_SYSTEMLOG_WHERE = "SELECT systemLog FROM SystemLog systemLog WHERE ";
	private static final String _SQL_COUNT_SYSTEMLOG = "SELECT COUNT(systemLog) FROM SystemLog systemLog";
	private static final String _SQL_COUNT_SYSTEMLOG_WHERE = "SELECT COUNT(systemLog) FROM SystemLog systemLog WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "systemLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SystemLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SystemLog exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SystemLogPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "type"
			});
}