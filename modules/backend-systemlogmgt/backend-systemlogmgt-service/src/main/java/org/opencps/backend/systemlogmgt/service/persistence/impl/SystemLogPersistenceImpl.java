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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
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

import java.util.Arrays;
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEGROUPID =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBymultipleGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEGROUPID =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBymultipleGroupId",
			new String[] { Long.class.getName() },
			SystemLogModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MULTIPLEGROUPID = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymultipleGroupId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEGROUPID =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countBymultipleGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the system logs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleGroupId(long groupId) {
		return findBymultipleGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleGroupId(long groupId, int start,
		int end) {
		return findBymultipleGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleGroupId(long groupId, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultipleGroupId(groupId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the system logs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleGroupId(long groupId, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEGROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEGROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if ((groupId != systemLog.getGroupId())) {
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

			query.append(_FINDER_COLUMN_MULTIPLEGROUPID_GROUPID_2);

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

				qPos.add(groupId);

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
	 * Returns the first system log in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultipleGroupId_First(long groupId,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultipleGroupId_First(groupId,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultipleGroupId_First(long groupId,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findBymultipleGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultipleGroupId_Last(long groupId,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultipleGroupId_Last(groupId,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultipleGroupId_Last(long groupId,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countBymultipleGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findBymultipleGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where groupId = &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findBymultipleGroupId_PrevAndNext(long logId,
		long groupId, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getBymultipleGroupId_PrevAndNext(session, systemLog,
					groupId, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getBymultipleGroupId_PrevAndNext(session, systemLog,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SystemLog getBymultipleGroupId_PrevAndNext(Session session,
		SystemLog systemLog, long groupId,
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

		query.append(_FINDER_COLUMN_MULTIPLEGROUPID_GROUPID_2);

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

		qPos.add(groupId);

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
	 * Returns all the system logs where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleGroupId(long[] groupIds) {
		return findBymultipleGroupId(groupIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleGroupId(long[] groupIds, int start,
		int end) {
		return findBymultipleGroupId(groupIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where groupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleGroupId(long[] groupIds, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultipleGroupId(groupIds, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the system logs where groupId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleGroupId(long[] groupIds, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		if (groupIds.length == 1) {
			return findBymultipleGroupId(groupIds[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(groupIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(groupIds),
					
					start, end, orderByComparator
				};
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEGROUPID,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!ArrayUtil.contains(groupIds, systemLog.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

			if (groupIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_MULTIPLEGROUPID_GROUPID_7);

				query.append(StringUtil.merge(groupIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEGROUPID,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEGROUPID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the system logs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBymultipleGroupId(long groupId) {
		for (SystemLog systemLog : findBymultipleGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultipleGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MULTIPLEGROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			query.append(_FINDER_COLUMN_MULTIPLEGROUPID_GROUPID_2);

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

	/**
	 * Returns the number of system logs where groupId = any &#63;.
	 *
	 * @param groupIds the group IDs
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultipleGroupId(long[] groupIds) {
		if (groupIds == null) {
			groupIds = new long[0];
		}
		else if (groupIds.length > 1) {
			groupIds = ArrayUtil.unique(groupIds);

			Arrays.sort(groupIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(groupIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEGROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			if (groupIds.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_MULTIPLEGROUPID_GROUPID_7);

				query.append(StringUtil.merge(groupIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEGROUPID,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEGROUPID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MULTIPLEGROUPID_GROUPID_2 = "systemLog.groupId = ?";
	private static final String _FINDER_COLUMN_MULTIPLEGROUPID_GROUPID_7 = "systemLog.groupId IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETYPE =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBymultipleType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETYPE =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBymultipleType",
			new String[] { String.class.getName() },
			SystemLogModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MULTIPLETYPE = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBymultipleType",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLETYPE =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countBymultipleType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleType(String type) {
		return findBymultipleType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the system logs where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleType(String type, int start, int end) {
		return findBymultipleType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleType(String type, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return findBymultipleType(type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleType(String type, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETYPE;
			finderArgs = new Object[] { type, start, end, orderByComparator };
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!Objects.equals(type, systemLog.getType())) {
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

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_1);
			}
			else if (type.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_2);
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

				if (bindType) {
					qPos.add(type);
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
	 * Returns the first system log in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultipleType_First(String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultipleType_First(type, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultipleType_First(String type,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findBymultipleType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultipleType_Last(String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultipleType_Last(type, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultipleType_Last(String type,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countBymultipleType(type);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findBymultipleType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where type = &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findBymultipleType_PrevAndNext(long logId, String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getBymultipleType_PrevAndNext(session, systemLog, type,
					orderByComparator, true);

			array[1] = systemLog;

			array[2] = getBymultipleType_PrevAndNext(session, systemLog, type,
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

	protected SystemLog getBymultipleType_PrevAndNext(Session session,
		SystemLog systemLog, String type,
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

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_1);
		}
		else if (type.equals("")) {
			query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_2);
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

		if (bindType) {
			qPos.add(type);
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
	 * Returns all the system logs where type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param types the types
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleType(String[] types) {
		return findBymultipleType(types, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the system logs where type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param types the types
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleType(String[] types, int start, int end) {
		return findBymultipleType(types, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param types the types
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleType(String[] types, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultipleType(types, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where type = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleType(String[] types, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		if (types == null) {
			types = new String[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.distinct(types, NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(types, NULL_SAFE_STRING_COMPARATOR);
		}

		if (types.length == 1) {
			return findBymultipleType(types[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(types) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(types),
					
					start, end, orderByComparator
				};
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETYPE,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!ArrayUtil.contains(types, systemLog.getType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

			if (types.length > 0) {
				query.append("(");

				for (int i = 0; i < types.length; i++) {
					String type = types[i];

					if (type == null) {
						query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_1);
					}
					else if (type.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_2);
					}

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

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

				for (String type : types) {
					if ((type != null) && !type.isEmpty()) {
						qPos.add(type);
					}
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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETYPE,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETYPE,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the system logs where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeBymultipleType(String type) {
		for (SystemLog systemLog : findBymultipleType(type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultipleType(String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MULTIPLETYPE;

		Object[] finderArgs = new Object[] { type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_1);
			}
			else if (type.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindType) {
					qPos.add(type);
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

	/**
	 * Returns the number of system logs where type = any &#63;.
	 *
	 * @param types the types
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultipleType(String[] types) {
		if (types == null) {
			types = new String[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.distinct(types, NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(types, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(types) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLETYPE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			if (types.length > 0) {
				query.append("(");

				for (int i = 0; i < types.length; i++) {
					String type = types[i];

					if (type == null) {
						query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_1);
					}
					else if (type.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLETYPE_TYPE_2);
					}

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				for (String type : types) {
					if ((type != null) && !type.isEmpty()) {
						qPos.add(type);
					}
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLETYPE,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLETYPE,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MULTIPLETYPE_TYPE_1 = "systemLog.type IS NULL";
	private static final String _FINDER_COLUMN_MULTIPLETYPE_TYPE_2 = "systemLog.type = ?";
	private static final String _FINDER_COLUMN_MULTIPLETYPE_TYPE_3 = "(systemLog.type IS NULL OR systemLog.type = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMODULENAME =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBymultiplemoduleName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMODULENAME =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBymultiplemoduleName",
			new String[] { String.class.getName() },
			SystemLogModelImpl.MODULENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MULTIPLEMODULENAME = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymultiplemoduleName", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEMODULENAME =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countBymultiplemoduleName", new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplemoduleName(String moduleName) {
		return findBymultiplemoduleName(moduleName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplemoduleName(String moduleName,
		int start, int end) {
		return findBymultiplemoduleName(moduleName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplemoduleName(String moduleName,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultiplemoduleName(moduleName, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where moduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplemoduleName(String moduleName,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMODULENAME;
			finderArgs = new Object[] { moduleName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMODULENAME;
			finderArgs = new Object[] { moduleName, start, end, orderByComparator };
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!Objects.equals(moduleName, systemLog.getModuleName())) {
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

			boolean bindModuleName = false;

			if (moduleName == null) {
				query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_1);
			}
			else if (moduleName.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_3);
			}
			else {
				bindModuleName = true;

				query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_2);
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

				if (bindModuleName) {
					qPos.add(moduleName);
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
	 * Returns the first system log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultiplemoduleName_First(String moduleName,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultiplemoduleName_First(moduleName,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleName=");
		msg.append(moduleName);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultiplemoduleName_First(String moduleName,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findBymultiplemoduleName(moduleName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultiplemoduleName_Last(String moduleName,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultiplemoduleName_Last(moduleName,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleName=");
		msg.append(moduleName);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultiplemoduleName_Last(String moduleName,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countBymultiplemoduleName(moduleName);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findBymultiplemoduleName(moduleName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where moduleName = &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param moduleName the module name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findBymultiplemoduleName_PrevAndNext(long logId,
		String moduleName, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getBymultiplemoduleName_PrevAndNext(session, systemLog,
					moduleName, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getBymultiplemoduleName_PrevAndNext(session, systemLog,
					moduleName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SystemLog getBymultiplemoduleName_PrevAndNext(Session session,
		SystemLog systemLog, String moduleName,
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

		boolean bindModuleName = false;

		if (moduleName == null) {
			query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_1);
		}
		else if (moduleName.equals("")) {
			query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_3);
		}
		else {
			bindModuleName = true;

			query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_2);
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

		if (bindModuleName) {
			qPos.add(moduleName);
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
	 * Returns all the system logs where moduleName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleNames the module names
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplemoduleName(String[] moduleNames) {
		return findBymultiplemoduleName(moduleNames, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where moduleName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleNames the module names
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplemoduleName(String[] moduleNames,
		int start, int end) {
		return findBymultiplemoduleName(moduleNames, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where moduleName = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleNames the module names
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplemoduleName(String[] moduleNames,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultiplemoduleName(moduleNames, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where moduleName = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleName the module name
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplemoduleName(String[] moduleNames,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		if (moduleNames == null) {
			moduleNames = new String[0];
		}
		else if (moduleNames.length > 1) {
			moduleNames = ArrayUtil.distinct(moduleNames,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(moduleNames, NULL_SAFE_STRING_COMPARATOR);
		}

		if (moduleNames.length == 1) {
			return findBymultiplemoduleName(moduleNames[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(moduleNames) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(moduleNames),
					
					start, end, orderByComparator
				};
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMODULENAME,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!ArrayUtil.contains(moduleNames,
								systemLog.getModuleName())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

			if (moduleNames.length > 0) {
				query.append("(");

				for (int i = 0; i < moduleNames.length; i++) {
					String moduleName = moduleNames[i];

					if (moduleName == null) {
						query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_1);
					}
					else if (moduleName.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_2);
					}

					if ((i + 1) < moduleNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

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

				for (String moduleName : moduleNames) {
					if ((moduleName != null) && !moduleName.isEmpty()) {
						qPos.add(moduleName);
					}
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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMODULENAME,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMODULENAME,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the system logs where moduleName = &#63; from the database.
	 *
	 * @param moduleName the module name
	 */
	@Override
	public void removeBymultiplemoduleName(String moduleName) {
		for (SystemLog systemLog : findBymultiplemoduleName(moduleName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultiplemoduleName(String moduleName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MULTIPLEMODULENAME;

		Object[] finderArgs = new Object[] { moduleName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindModuleName = false;

			if (moduleName == null) {
				query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_1);
			}
			else if (moduleName.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_3);
			}
			else {
				bindModuleName = true;

				query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModuleName) {
					qPos.add(moduleName);
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

	/**
	 * Returns the number of system logs where moduleName = any &#63;.
	 *
	 * @param moduleNames the module names
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultiplemoduleName(String[] moduleNames) {
		if (moduleNames == null) {
			moduleNames = new String[0];
		}
		else if (moduleNames.length > 1) {
			moduleNames = ArrayUtil.distinct(moduleNames,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(moduleNames, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(moduleNames) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEMODULENAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			if (moduleNames.length > 0) {
				query.append("(");

				for (int i = 0; i < moduleNames.length; i++) {
					String moduleName = moduleNames[i];

					if (moduleName == null) {
						query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_1);
					}
					else if (moduleName.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_2);
					}

					if ((i + 1) < moduleNames.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				for (String moduleName : moduleNames) {
					if ((moduleName != null) && !moduleName.isEmpty()) {
						qPos.add(moduleName);
					}
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEMODULENAME,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEMODULENAME,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_1 = "systemLog.moduleName IS NULL";
	private static final String _FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_2 = "systemLog.moduleName = ?";
	private static final String _FINDER_COLUMN_MULTIPLEMODULENAME_MODULENAME_3 = "(systemLog.moduleName IS NULL OR systemLog.moduleName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEPREMETHOD =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBymultiplePreMethod",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEPREMETHOD =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBymultiplePreMethod", new String[] { String.class.getName() },
			SystemLogModelImpl.PREMETHOD_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MULTIPLEPREMETHOD = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymultiplePreMethod", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEPREMETHOD =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countBymultiplePreMethod",
			new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where preMethod = &#63;.
	 *
	 * @param preMethod the pre method
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplePreMethod(String preMethod) {
		return findBymultiplePreMethod(preMethod, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where preMethod = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preMethod the pre method
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplePreMethod(String preMethod, int start,
		int end) {
		return findBymultiplePreMethod(preMethod, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where preMethod = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preMethod the pre method
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplePreMethod(String preMethod, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultiplePreMethod(preMethod, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where preMethod = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preMethod the pre method
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplePreMethod(String preMethod, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEPREMETHOD;
			finderArgs = new Object[] { preMethod };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEPREMETHOD;
			finderArgs = new Object[] { preMethod, start, end, orderByComparator };
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!Objects.equals(preMethod, systemLog.getPreMethod())) {
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

			boolean bindPreMethod = false;

			if (preMethod == null) {
				query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_1);
			}
			else if (preMethod.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_3);
			}
			else {
				bindPreMethod = true;

				query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_2);
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

				if (bindPreMethod) {
					qPos.add(preMethod);
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
	 * Returns the first system log in the ordered set where preMethod = &#63;.
	 *
	 * @param preMethod the pre method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultiplePreMethod_First(String preMethod,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultiplePreMethod_First(preMethod,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("preMethod=");
		msg.append(preMethod);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where preMethod = &#63;.
	 *
	 * @param preMethod the pre method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultiplePreMethod_First(String preMethod,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findBymultiplePreMethod(preMethod, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where preMethod = &#63;.
	 *
	 * @param preMethod the pre method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultiplePreMethod_Last(String preMethod,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultiplePreMethod_Last(preMethod,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("preMethod=");
		msg.append(preMethod);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where preMethod = &#63;.
	 *
	 * @param preMethod the pre method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultiplePreMethod_Last(String preMethod,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countBymultiplePreMethod(preMethod);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findBymultiplePreMethod(preMethod, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where preMethod = &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param preMethod the pre method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findBymultiplePreMethod_PrevAndNext(long logId,
		String preMethod, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getBymultiplePreMethod_PrevAndNext(session, systemLog,
					preMethod, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getBymultiplePreMethod_PrevAndNext(session, systemLog,
					preMethod, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SystemLog getBymultiplePreMethod_PrevAndNext(Session session,
		SystemLog systemLog, String preMethod,
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

		boolean bindPreMethod = false;

		if (preMethod == null) {
			query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_1);
		}
		else if (preMethod.equals("")) {
			query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_3);
		}
		else {
			bindPreMethod = true;

			query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_2);
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

		if (bindPreMethod) {
			qPos.add(preMethod);
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
	 * Returns all the system logs where preMethod = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preMethods the pre methods
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplePreMethod(String[] preMethods) {
		return findBymultiplePreMethod(preMethods, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where preMethod = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preMethods the pre methods
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplePreMethod(String[] preMethods,
		int start, int end) {
		return findBymultiplePreMethod(preMethods, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where preMethod = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preMethods the pre methods
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplePreMethod(String[] preMethods,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultiplePreMethod(preMethods, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where preMethod = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param preMethod the pre method
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultiplePreMethod(String[] preMethods,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		if (preMethods == null) {
			preMethods = new String[0];
		}
		else if (preMethods.length > 1) {
			preMethods = ArrayUtil.distinct(preMethods,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(preMethods, NULL_SAFE_STRING_COMPARATOR);
		}

		if (preMethods.length == 1) {
			return findBymultiplePreMethod(preMethods[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(preMethods) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(preMethods),
					
					start, end, orderByComparator
				};
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEPREMETHOD,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!ArrayUtil.contains(preMethods, systemLog.getPreMethod())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

			if (preMethods.length > 0) {
				query.append("(");

				for (int i = 0; i < preMethods.length; i++) {
					String preMethod = preMethods[i];

					if (preMethod == null) {
						query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_1);
					}
					else if (preMethod.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_2);
					}

					if ((i + 1) < preMethods.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

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

				for (String preMethod : preMethods) {
					if ((preMethod != null) && !preMethod.isEmpty()) {
						qPos.add(preMethod);
					}
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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEPREMETHOD,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEPREMETHOD,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the system logs where preMethod = &#63; from the database.
	 *
	 * @param preMethod the pre method
	 */
	@Override
	public void removeBymultiplePreMethod(String preMethod) {
		for (SystemLog systemLog : findBymultiplePreMethod(preMethod,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where preMethod = &#63;.
	 *
	 * @param preMethod the pre method
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultiplePreMethod(String preMethod) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MULTIPLEPREMETHOD;

		Object[] finderArgs = new Object[] { preMethod };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindPreMethod = false;

			if (preMethod == null) {
				query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_1);
			}
			else if (preMethod.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_3);
			}
			else {
				bindPreMethod = true;

				query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPreMethod) {
					qPos.add(preMethod);
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

	/**
	 * Returns the number of system logs where preMethod = any &#63;.
	 *
	 * @param preMethods the pre methods
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultiplePreMethod(String[] preMethods) {
		if (preMethods == null) {
			preMethods = new String[0];
		}
		else if (preMethods.length > 1) {
			preMethods = ArrayUtil.distinct(preMethods,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(preMethods, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(preMethods) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEPREMETHOD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			if (preMethods.length > 0) {
				query.append("(");

				for (int i = 0; i < preMethods.length; i++) {
					String preMethod = preMethods[i];

					if (preMethod == null) {
						query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_1);
					}
					else if (preMethod.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_2);
					}

					if ((i + 1) < preMethods.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				for (String preMethod : preMethods) {
					if ((preMethod != null) && !preMethod.isEmpty()) {
						qPos.add(preMethod);
					}
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEPREMETHOD,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEPREMETHOD,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_1 = "systemLog.preMethod IS NULL";
	private static final String _FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_2 = "systemLog.preMethod = ?";
	private static final String _FINDER_COLUMN_MULTIPLEPREMETHOD_PREMETHOD_3 = "(systemLog.preMethod IS NULL OR systemLog.preMethod = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMETHOD =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBymultipleMethod",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMETHOD =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBymultipleMethod",
			new String[] { String.class.getName() },
			SystemLogModelImpl.METHOD_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MULTIPLEMETHOD = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBymultipleMethod",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEMETHOD =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countBymultipleMethod",
			new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where method = &#63;.
	 *
	 * @param method the method
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleMethod(String method) {
		return findBymultipleMethod(method, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where method = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param method the method
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleMethod(String method, int start,
		int end) {
		return findBymultipleMethod(method, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where method = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param method the method
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleMethod(String method, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultipleMethod(method, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where method = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param method the method
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleMethod(String method, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMETHOD;
			finderArgs = new Object[] { method };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMETHOD;
			finderArgs = new Object[] { method, start, end, orderByComparator };
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!Objects.equals(method, systemLog.getMethod())) {
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

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_1);
			}
			else if (method.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_2);
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

				if (bindMethod) {
					qPos.add(method);
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
	 * Returns the first system log in the ordered set where method = &#63;.
	 *
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultipleMethod_First(String method,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultipleMethod_First(method,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("method=");
		msg.append(method);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where method = &#63;.
	 *
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultipleMethod_First(String method,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findBymultipleMethod(method, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where method = &#63;.
	 *
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultipleMethod_Last(String method,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultipleMethod_Last(method,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("method=");
		msg.append(method);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where method = &#63;.
	 *
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultipleMethod_Last(String method,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countBymultipleMethod(method);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findBymultipleMethod(method, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where method = &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findBymultipleMethod_PrevAndNext(long logId,
		String method, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getBymultipleMethod_PrevAndNext(session, systemLog,
					method, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getBymultipleMethod_PrevAndNext(session, systemLog,
					method, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SystemLog getBymultipleMethod_PrevAndNext(Session session,
		SystemLog systemLog, String method,
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

		boolean bindMethod = false;

		if (method == null) {
			query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_1);
		}
		else if (method.equals("")) {
			query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_3);
		}
		else {
			bindMethod = true;

			query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_2);
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

		if (bindMethod) {
			qPos.add(method);
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
	 * Returns all the system logs where method = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param methods the methods
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleMethod(String[] methods) {
		return findBymultipleMethod(methods, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where method = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param methods the methods
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleMethod(String[] methods, int start,
		int end) {
		return findBymultipleMethod(methods, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where method = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param methods the methods
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleMethod(String[] methods, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultipleMethod(methods, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where method = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param method the method
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleMethod(String[] methods, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		if (methods == null) {
			methods = new String[0];
		}
		else if (methods.length > 1) {
			methods = ArrayUtil.distinct(methods, NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(methods, NULL_SAFE_STRING_COMPARATOR);
		}

		if (methods.length == 1) {
			return findBymultipleMethod(methods[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(methods) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(methods),
					
					start, end, orderByComparator
				};
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMETHOD,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!ArrayUtil.contains(methods, systemLog.getMethod())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

			if (methods.length > 0) {
				query.append("(");

				for (int i = 0; i < methods.length; i++) {
					String method = methods[i];

					if (method == null) {
						query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_1);
					}
					else if (method.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_2);
					}

					if ((i + 1) < methods.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

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

				for (String method : methods) {
					if ((method != null) && !method.isEmpty()) {
						qPos.add(method);
					}
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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMETHOD,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLEMETHOD,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the system logs where method = &#63; from the database.
	 *
	 * @param method the method
	 */
	@Override
	public void removeBymultipleMethod(String method) {
		for (SystemLog systemLog : findBymultipleMethod(method,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where method = &#63;.
	 *
	 * @param method the method
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultipleMethod(String method) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MULTIPLEMETHOD;

		Object[] finderArgs = new Object[] { method };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_1);
			}
			else if (method.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMethod) {
					qPos.add(method);
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

	/**
	 * Returns the number of system logs where method = any &#63;.
	 *
	 * @param methods the methods
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultipleMethod(String[] methods) {
		if (methods == null) {
			methods = new String[0];
		}
		else if (methods.length > 1) {
			methods = ArrayUtil.distinct(methods, NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(methods, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(methods) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEMETHOD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			if (methods.length > 0) {
				query.append("(");

				for (int i = 0; i < methods.length; i++) {
					String method = methods[i];

					if (method == null) {
						query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_1);
					}
					else if (method.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLEMETHOD_METHOD_2);
					}

					if ((i + 1) < methods.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				for (String method : methods) {
					if ((method != null) && !method.isEmpty()) {
						qPos.add(method);
					}
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEMETHOD,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLEMETHOD,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MULTIPLEMETHOD_METHOD_1 = "systemLog.method IS NULL";
	private static final String _FINDER_COLUMN_MULTIPLEMETHOD_METHOD_2 = "systemLog.method = ?";
	private static final String _FINDER_COLUMN_MULTIPLEMETHOD_METHOD_3 = "(systemLog.method IS NULL OR systemLog.method = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETHREADID =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBymultipleThreadId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETHREADID =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBymultipleThreadId", new String[] { String.class.getName() },
			SystemLogModelImpl.THREADID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MULTIPLETHREADID = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBymultipleThreadId", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLETHREADID =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countBymultipleThreadId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleThreadId(String threadId) {
		return findBymultipleThreadId(threadId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where threadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleThreadId(String threadId, int start,
		int end) {
		return findBymultipleThreadId(threadId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where threadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleThreadId(String threadId, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultipleThreadId(threadId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the system logs where threadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleThreadId(String threadId, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETHREADID;
			finderArgs = new Object[] { threadId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETHREADID;
			finderArgs = new Object[] { threadId, start, end, orderByComparator };
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!Objects.equals(threadId, systemLog.getThreadId())) {
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

			boolean bindThreadId = false;

			if (threadId == null) {
				query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_1);
			}
			else if (threadId.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_3);
			}
			else {
				bindThreadId = true;

				query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_2);
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

				if (bindThreadId) {
					qPos.add(threadId);
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
	 * Returns the first system log in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultipleThreadId_First(String threadId,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultipleThreadId_First(threadId,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("threadId=");
		msg.append(threadId);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultipleThreadId_First(String threadId,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findBymultipleThreadId(threadId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBymultipleThreadId_Last(String threadId,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBymultipleThreadId_Last(threadId,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("threadId=");
		msg.append(threadId);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBymultipleThreadId_Last(String threadId,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countBymultipleThreadId(threadId);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findBymultipleThreadId(threadId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where threadId = &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param threadId the thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findBymultipleThreadId_PrevAndNext(long logId,
		String threadId, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getBymultipleThreadId_PrevAndNext(session, systemLog,
					threadId, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getBymultipleThreadId_PrevAndNext(session, systemLog,
					threadId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SystemLog getBymultipleThreadId_PrevAndNext(Session session,
		SystemLog systemLog, String threadId,
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

		boolean bindThreadId = false;

		if (threadId == null) {
			query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_1);
		}
		else if (threadId.equals("")) {
			query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_3);
		}
		else {
			bindThreadId = true;

			query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_2);
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

		if (bindThreadId) {
			qPos.add(threadId);
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
	 * Returns all the system logs where threadId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param threadIds the thread IDs
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleThreadId(String[] threadIds) {
		return findBymultipleThreadId(threadIds, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where threadId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param threadIds the thread IDs
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleThreadId(String[] threadIds,
		int start, int end) {
		return findBymultipleThreadId(threadIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where threadId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param threadIds the thread IDs
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleThreadId(String[] threadIds,
		int start, int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBymultipleThreadId(threadIds, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the system logs where threadId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param threadId the thread ID
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBymultipleThreadId(String[] threadIds,
		int start, int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		if (threadIds == null) {
			threadIds = new String[0];
		}
		else if (threadIds.length > 1) {
			threadIds = ArrayUtil.distinct(threadIds,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(threadIds, NULL_SAFE_STRING_COMPARATOR);
		}

		if (threadIds.length == 1) {
			return findBymultipleThreadId(threadIds[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(threadIds) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(threadIds),
					
					start, end, orderByComparator
				};
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETHREADID,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!ArrayUtil.contains(threadIds, systemLog.getThreadId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

			if (threadIds.length > 0) {
				query.append("(");

				for (int i = 0; i < threadIds.length; i++) {
					String threadId = threadIds[i];

					if (threadId == null) {
						query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_1);
					}
					else if (threadId.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_2);
					}

					if ((i + 1) < threadIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

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

				for (String threadId : threadIds) {
					if ((threadId != null) && !threadId.isEmpty()) {
						qPos.add(threadId);
					}
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

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETHREADID,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_MULTIPLETHREADID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the system logs where threadId = &#63; from the database.
	 *
	 * @param threadId the thread ID
	 */
	@Override
	public void removeBymultipleThreadId(String threadId) {
		for (SystemLog systemLog : findBymultipleThreadId(threadId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where threadId = &#63;.
	 *
	 * @param threadId the thread ID
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultipleThreadId(String threadId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MULTIPLETHREADID;

		Object[] finderArgs = new Object[] { threadId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindThreadId = false;

			if (threadId == null) {
				query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_1);
			}
			else if (threadId.equals("")) {
				query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_3);
			}
			else {
				bindThreadId = true;

				query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindThreadId) {
					qPos.add(threadId);
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

	/**
	 * Returns the number of system logs where threadId = any &#63;.
	 *
	 * @param threadIds the thread IDs
	 * @return the number of matching system logs
	 */
	@Override
	public int countBymultipleThreadId(String[] threadIds) {
		if (threadIds == null) {
			threadIds = new String[0];
		}
		else if (threadIds.length > 1) {
			threadIds = ArrayUtil.distinct(threadIds,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(threadIds, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(threadIds) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLETHREADID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			if (threadIds.length > 0) {
				query.append("(");

				for (int i = 0; i < threadIds.length; i++) {
					String threadId = threadIds[i];

					if (threadId == null) {
						query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_1);
					}
					else if (threadId.equals("")) {
						query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_3);
					}
					else {
						query.append(_FINDER_COLUMN_MULTIPLETHREADID_THREADID_2);
					}

					if ((i + 1) < threadIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				for (String threadId : threadIds) {
					if ((threadId != null) && !threadId.isEmpty()) {
						qPos.add(threadId);
					}
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLETHREADID,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_MULTIPLETHREADID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MULTIPLETHREADID_THREADID_1 = "systemLog.threadId IS NULL";
	private static final String _FINDER_COLUMN_MULTIPLETHREADID_THREADID_2 = "systemLog.threadId = ?";
	private static final String _FINDER_COLUMN_MULTIPLETHREADID_THREADID_3 = "(systemLog.threadId IS NULL OR systemLog.threadId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LIKEMESSAGE =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBylikeMessage",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LIKEMESSAGE =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countBylikeMessage",
			new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where message LIKE &#63;.
	 *
	 * @param message the message
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findBylikeMessage(String message) {
		return findBylikeMessage(message, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the system logs where message LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param message the message
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findBylikeMessage(String message, int start, int end) {
		return findBylikeMessage(message, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where message LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param message the message
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBylikeMessage(String message, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findBylikeMessage(message, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where message LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param message the message
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findBylikeMessage(String message, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LIKEMESSAGE;
		finderArgs = new Object[] { message, start, end, orderByComparator };

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!StringUtil.wildcardMatches(systemLog.getMessage(),
								message, '_', '%', '\\', true)) {
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

			boolean bindMessage = false;

			if (message == null) {
				query.append(_FINDER_COLUMN_LIKEMESSAGE_MESSAGE_1);
			}
			else if (message.equals("")) {
				query.append(_FINDER_COLUMN_LIKEMESSAGE_MESSAGE_3);
			}
			else {
				bindMessage = true;

				query.append(_FINDER_COLUMN_LIKEMESSAGE_MESSAGE_2);
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

				if (bindMessage) {
					qPos.add(message);
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
	 * Returns the first system log in the ordered set where message LIKE &#63;.
	 *
	 * @param message the message
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBylikeMessage_First(String message,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBylikeMessage_First(message,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("message=");
		msg.append(message);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where message LIKE &#63;.
	 *
	 * @param message the message
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBylikeMessage_First(String message,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findBylikeMessage(message, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where message LIKE &#63;.
	 *
	 * @param message the message
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findBylikeMessage_Last(String message,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchBylikeMessage_Last(message, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("message=");
		msg.append(message);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where message LIKE &#63;.
	 *
	 * @param message the message
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchBylikeMessage_Last(String message,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countBylikeMessage(message);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findBylikeMessage(message, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where message LIKE &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param message the message
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findBylikeMessage_PrevAndNext(long logId,
		String message, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getBylikeMessage_PrevAndNext(session, systemLog,
					message, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getBylikeMessage_PrevAndNext(session, systemLog,
					message, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SystemLog getBylikeMessage_PrevAndNext(Session session,
		SystemLog systemLog, String message,
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

		boolean bindMessage = false;

		if (message == null) {
			query.append(_FINDER_COLUMN_LIKEMESSAGE_MESSAGE_1);
		}
		else if (message.equals("")) {
			query.append(_FINDER_COLUMN_LIKEMESSAGE_MESSAGE_3);
		}
		else {
			bindMessage = true;

			query.append(_FINDER_COLUMN_LIKEMESSAGE_MESSAGE_2);
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

		if (bindMessage) {
			qPos.add(message);
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
	 * Removes all the system logs where message LIKE &#63; from the database.
	 *
	 * @param message the message
	 */
	@Override
	public void removeBylikeMessage(String message) {
		for (SystemLog systemLog : findBylikeMessage(message,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where message LIKE &#63;.
	 *
	 * @param message the message
	 * @return the number of matching system logs
	 */
	@Override
	public int countBylikeMessage(String message) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LIKEMESSAGE;

		Object[] finderArgs = new Object[] { message };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindMessage = false;

			if (message == null) {
				query.append(_FINDER_COLUMN_LIKEMESSAGE_MESSAGE_1);
			}
			else if (message.equals("")) {
				query.append(_FINDER_COLUMN_LIKEMESSAGE_MESSAGE_3);
			}
			else {
				bindMessage = true;

				query.append(_FINDER_COLUMN_LIKEMESSAGE_MESSAGE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMessage) {
					qPos.add(message);
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

	private static final String _FINDER_COLUMN_LIKEMESSAGE_MESSAGE_1 = "systemLog.message IS NULL";
	private static final String _FINDER_COLUMN_LIKEMESSAGE_MESSAGE_2 = "systemLog.message LIKE ?";
	private static final String _FINDER_COLUMN_LIKEMESSAGE_MESSAGE_3 = "(systemLog.message IS NULL OR systemLog.message LIKE '')";

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

			args = new Object[] { systemLogModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEGROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEGROUPID,
				args);

			args = new Object[] { systemLogModelImpl.getType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLETYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETYPE,
				args);

			args = new Object[] { systemLogModelImpl.getModuleName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEMODULENAME,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMODULENAME,
				args);

			args = new Object[] { systemLogModelImpl.getPreMethod() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEPREMETHOD,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEPREMETHOD,
				args);

			args = new Object[] { systemLogModelImpl.getMethod() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEMETHOD, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMETHOD,
				args);

			args = new Object[] { systemLogModelImpl.getThreadId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLETHREADID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETHREADID,
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

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEGROUPID,
					args);

				args = new Object[] { systemLogModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEGROUPID,
					args);
			}

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLETYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETYPE,
					args);

				args = new Object[] { systemLogModelImpl.getType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLETYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETYPE,
					args);
			}

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMODULENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalModuleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEMODULENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMODULENAME,
					args);

				args = new Object[] { systemLogModelImpl.getModuleName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEMODULENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMODULENAME,
					args);
			}

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEPREMETHOD.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalPreMethod()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEPREMETHOD,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEPREMETHOD,
					args);

				args = new Object[] { systemLogModelImpl.getPreMethod() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEPREMETHOD,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEPREMETHOD,
					args);
			}

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMETHOD.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalMethod()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEMETHOD,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMETHOD,
					args);

				args = new Object[] { systemLogModelImpl.getMethod() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLEMETHOD,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLEMETHOD,
					args);
			}

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETHREADID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalThreadId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLETHREADID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETHREADID,
					args);

				args = new Object[] { systemLogModelImpl.getThreadId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MULTIPLETHREADID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MULTIPLETHREADID,
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