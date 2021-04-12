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

package backend.systemlogmgt.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import backend.systemlogmgt.exception.NoSuchSystemLogException;

import backend.systemlogmgt.model.SystemLog;
import backend.systemlogmgt.model.impl.SystemLogImpl;
import backend.systemlogmgt.model.impl.SystemLogModelImpl;

import backend.systemlogmgt.service.persistence.SystemLogPersistence;

import com.liferay.petra.string.StringBundler;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

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
 * The persistence implementation for the system log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogPersistence
 * @see backend.systemlogmgt.service.persistence.SystemLogUtil
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATEGREATER =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCreateDateGreater",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_CREATEDATEGREATER =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByCreateDateGreater",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the system logs where createDate &ge; &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByCreateDateGreater(Date createDate) {
		return findByCreateDateGreater(createDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where createDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findByCreateDateGreater(Date createDate, int start,
		int end) {
		return findByCreateDateGreater(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where createDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByCreateDateGreater(Date createDate, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findByCreateDateGreater(createDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where createDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByCreateDateGreater(Date createDate, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATEGREATER;
		finderArgs = new Object[] {
				_getTime(createDate),
				
				start, end, orderByComparator
			};

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if ((createDate.getTime() > systemLog.getCreateDate()
															 .getTime())) {
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

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CREATEDATEGREATER_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_CREATEDATEGREATER_CREATEDATE_2);
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

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
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
	 * Returns the first system log in the ordered set where createDate &ge; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByCreateDateGreater_First(Date createDate,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByCreateDateGreater_First(createDate,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where createDate &ge; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByCreateDateGreater_First(Date createDate,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByCreateDateGreater(createDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where createDate &ge; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByCreateDateGreater_Last(Date createDate,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByCreateDateGreater_Last(createDate,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where createDate &ge; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByCreateDateGreater_Last(Date createDate,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByCreateDateGreater(createDate);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByCreateDateGreater(createDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where createDate &ge; &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findByCreateDateGreater_PrevAndNext(long logId,
		Date createDate, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByCreateDateGreater_PrevAndNext(session, systemLog,
					createDate, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByCreateDateGreater_PrevAndNext(session, systemLog,
					createDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SystemLog getByCreateDateGreater_PrevAndNext(Session session,
		SystemLog systemLog, Date createDate,
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

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_CREATEDATEGREATER_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_CREATEDATEGREATER_CREATEDATE_2);
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

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
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
	 * Removes all the system logs where createDate &ge; &#63; from the database.
	 *
	 * @param createDate the create date
	 */
	@Override
	public void removeByCreateDateGreater(Date createDate) {
		for (SystemLog systemLog : findByCreateDateGreater(createDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where createDate &ge; &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching system logs
	 */
	@Override
	public int countByCreateDateGreater(Date createDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_CREATEDATEGREATER;

		Object[] finderArgs = new Object[] { _getTime(createDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CREATEDATEGREATER_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_CREATEDATEGREATER_CREATEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
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

	private static final String _FINDER_COLUMN_CREATEDATEGREATER_CREATEDATE_1 = "systemLog.createDate IS NULL";
	private static final String _FINDER_COLUMN_CREATEDATEGREATER_CREATEDATE_2 = "systemLog.createDate >= ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATELESSER =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCreateDateLesser",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_CREATEDATELESSER =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByCreateDateLesser",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the system logs where createDate &le; &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByCreateDateLesser(Date createDate) {
		return findByCreateDateLesser(createDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where createDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findByCreateDateLesser(Date createDate, int start,
		int end) {
		return findByCreateDateLesser(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where createDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByCreateDateLesser(Date createDate, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findByCreateDateLesser(createDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where createDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByCreateDateLesser(Date createDate, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATELESSER;
		finderArgs = new Object[] {
				_getTime(createDate),
				
				start, end, orderByComparator
			};

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if ((createDate.getTime() < systemLog.getCreateDate()
															 .getTime())) {
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

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CREATEDATELESSER_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_CREATEDATELESSER_CREATEDATE_2);
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

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
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
	 * Returns the first system log in the ordered set where createDate &le; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByCreateDateLesser_First(Date createDate,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByCreateDateLesser_First(createDate,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where createDate &le; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByCreateDateLesser_First(Date createDate,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByCreateDateLesser(createDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where createDate &le; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByCreateDateLesser_Last(Date createDate,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByCreateDateLesser_Last(createDate,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where createDate &le; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByCreateDateLesser_Last(Date createDate,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByCreateDateLesser(createDate);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByCreateDateLesser(createDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where createDate &le; &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findByCreateDateLesser_PrevAndNext(long logId,
		Date createDate, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByCreateDateLesser_PrevAndNext(session, systemLog,
					createDate, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByCreateDateLesser_PrevAndNext(session, systemLog,
					createDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SystemLog getByCreateDateLesser_PrevAndNext(Session session,
		SystemLog systemLog, Date createDate,
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

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_CREATEDATELESSER_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_CREATEDATELESSER_CREATEDATE_2);
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

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
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
	 * Removes all the system logs where createDate &le; &#63; from the database.
	 *
	 * @param createDate the create date
	 */
	@Override
	public void removeByCreateDateLesser(Date createDate) {
		for (SystemLog systemLog : findByCreateDateLesser(createDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where createDate &le; &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching system logs
	 */
	@Override
	public int countByCreateDateLesser(Date createDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_CREATEDATELESSER;

		Object[] finderArgs = new Object[] { _getTime(createDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CREATEDATELESSER_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_CREATEDATELESSER_CREATEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
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

	private static final String _FINDER_COLUMN_CREATEDATELESSER_CREATEDATE_1 = "systemLog.createDate IS NULL";
	private static final String _FINDER_COLUMN_CREATEDATELESSER_CREATEDATE_2 = "systemLog.createDate <= ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_LOGID = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByLogId",
			new String[] { Long.class.getName() },
			SystemLogModelImpl.LOGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LOGID = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLogId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the system log where logId = &#63; or throws a {@link NoSuchSystemLogException} if it could not be found.
	 *
	 * @param logId the log ID
	 * @return the matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByLogId(long logId) throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByLogId(logId);

		if (systemLog == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("logId=");
			msg.append(logId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSystemLogException(msg.toString());
		}

		return systemLog;
	}

	/**
	 * Returns the system log where logId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param logId the log ID
	 * @return the matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByLogId(long logId) {
		return fetchByLogId(logId, true);
	}

	/**
	 * Returns the system log where logId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param logId the log ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByLogId(long logId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { logId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_LOGID,
					finderArgs, this);
		}

		if (result instanceof SystemLog) {
			SystemLog systemLog = (SystemLog)result;

			if ((logId != systemLog.getLogId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SYSTEMLOG_WHERE);

			query.append(_FINDER_COLUMN_LOGID_LOGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(logId);

				List<SystemLog> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_LOGID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SystemLogPersistenceImpl.fetchByLogId(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SystemLog systemLog = list.get(0);

					result = systemLog;

					cacheResult(systemLog);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_LOGID, finderArgs);

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
	 * Removes the system log where logId = &#63; from the database.
	 *
	 * @param logId the log ID
	 * @return the system log that was removed
	 */
	@Override
	public SystemLog removeByLogId(long logId) throws NoSuchSystemLogException {
		SystemLog systemLog = findByLogId(logId);

		return remove(systemLog);
	}

	/**
	 * Returns the number of system logs where logId = &#63;.
	 *
	 * @param logId the log ID
	 * @return the number of matching system logs
	 */
	@Override
	public int countByLogId(long logId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LOGID;

		Object[] finderArgs = new Object[] { logId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			query.append(_FINDER_COLUMN_LOGID_LOGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(logId);

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

	private static final String _FINDER_COLUMN_LOGID_LOGID_2 = "systemLog.logId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAME =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByClassName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByClassName",
			new String[] { String.class.getName() },
			SystemLogModelImpl.CLASSNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAME = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByClassName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where className = &#63;.
	 *
	 * @param className the class name
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByClassName(String className) {
		return findByClassName(className, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the system logs where className = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findByClassName(String className, int start, int end) {
		return findByClassName(className, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where className = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByClassName(String className, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findByClassName(className, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where className = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByClassName(String className, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME;
			finderArgs = new Object[] { className };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAME;
			finderArgs = new Object[] { className, start, end, orderByComparator };
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if (!Objects.equals(className, systemLog.getClassName())) {
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

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_2);
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

				if (bindClassName) {
					qPos.add(className);
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
	 * Returns the first system log in the ordered set where className = &#63;.
	 *
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByClassName_First(String className,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByClassName_First(className,
				orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where className = &#63;.
	 *
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByClassName_First(String className,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByClassName(className, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where className = &#63;.
	 *
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByClassName_Last(String className,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByClassName_Last(className, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where className = &#63;.
	 *
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByClassName_Last(String className,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByClassName(className);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByClassName(className, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where className = &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findByClassName_PrevAndNext(long logId,
		String className, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByClassName_PrevAndNext(session, systemLog,
					className, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByClassName_PrevAndNext(session, systemLog,
					className, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SystemLog getByClassName_PrevAndNext(Session session,
		SystemLog systemLog, String className,
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

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_1);
		}
		else if (className.equals("")) {
			query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_2);
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

		if (bindClassName) {
			qPos.add(className);
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
	 * Removes all the system logs where className = &#63; from the database.
	 *
	 * @param className the class name
	 */
	@Override
	public void removeByClassName(String className) {
		for (SystemLog systemLog : findByClassName(className,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where className = &#63;.
	 *
	 * @param className the class name
	 * @return the number of matching system logs
	 */
	@Override
	public int countByClassName(String className) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAME;

		Object[] finderArgs = new Object[] { className };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
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

	private static final String _FINDER_COLUMN_CLASSNAME_CLASSNAME_1 = "systemLog.className IS NULL";
	private static final String _FINDER_COLUMN_CLASSNAME_CLASSNAME_2 = "systemLog.className = ?";
	private static final String _FINDER_COLUMN_CLASSNAME_CLASSNAME_3 = "(systemLog.className IS NULL OR systemLog.className = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODULENAME =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByModuleName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModuleName",
			new String[] { String.class.getName() },
			SystemLogModelImpl.MODULENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODULENAME = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModuleName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where moduleName = &#63;.
	 *
	 * @param moduleName the module name
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByModuleName(String moduleName) {
		return findByModuleName(moduleName, QueryUtil.ALL_POS,
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
	public List<SystemLog> findByModuleName(String moduleName, int start,
		int end) {
		return findByModuleName(moduleName, start, end, null);
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
	public List<SystemLog> findByModuleName(String moduleName, int start,
		int end, OrderByComparator<SystemLog> orderByComparator) {
		return findByModuleName(moduleName, start, end, orderByComparator, true);
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
	public List<SystemLog> findByModuleName(String moduleName, int start,
		int end, OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME;
			finderArgs = new Object[] { moduleName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODULENAME;
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
				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_1);
			}
			else if (moduleName.equals("")) {
				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_3);
			}
			else {
				bindModuleName = true;

				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_2);
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
	public SystemLog findByModuleName_First(String moduleName,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByModuleName_First(moduleName,
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
	public SystemLog fetchByModuleName_First(String moduleName,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByModuleName(moduleName, 0, 1,
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
	public SystemLog findByModuleName_Last(String moduleName,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByModuleName_Last(moduleName,
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
	public SystemLog fetchByModuleName_Last(String moduleName,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByModuleName(moduleName);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByModuleName(moduleName, count - 1, count,
				orderByComparator);

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
	public SystemLog[] findByModuleName_PrevAndNext(long logId,
		String moduleName, OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByModuleName_PrevAndNext(session, systemLog,
					moduleName, orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByModuleName_PrevAndNext(session, systemLog,
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

	protected SystemLog getByModuleName_PrevAndNext(Session session,
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
			query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_1);
		}
		else if (moduleName.equals("")) {
			query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_3);
		}
		else {
			bindModuleName = true;

			query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_2);
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
	 * Removes all the system logs where moduleName = &#63; from the database.
	 *
	 * @param moduleName the module name
	 */
	@Override
	public void removeByModuleName(String moduleName) {
		for (SystemLog systemLog : findByModuleName(moduleName,
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
	public int countByModuleName(String moduleName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MODULENAME;

		Object[] finderArgs = new Object[] { moduleName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindModuleName = false;

			if (moduleName == null) {
				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_1);
			}
			else if (moduleName.equals("")) {
				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_3);
			}
			else {
				bindModuleName = true;

				query.append(_FINDER_COLUMN_MODULENAME_MODULENAME_2);
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

	private static final String _FINDER_COLUMN_MODULENAME_MODULENAME_1 = "systemLog.moduleName IS NULL";
	private static final String _FINDER_COLUMN_MODULENAME_MODULENAME_2 = "systemLog.moduleName = ?";
	private static final String _FINDER_COLUMN_MODULENAME_MODULENAME_3 = "(systemLog.moduleName IS NULL OR systemLog.moduleName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] { String.class.getName() },
			SystemLogModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the system logs where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByType(String type) {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<SystemLog> findByType(String type, int start, int end) {
		return findByType(type, start, end, null);
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
	public List<SystemLog> findByType(String type, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return findByType(type, start, end, orderByComparator, true);
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
	public List<SystemLog> findByType(String type, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
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
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else if (type.equals("")) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_TYPE_TYPE_2);
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
	public SystemLog findByType_First(String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByType_First(type, orderByComparator);

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
	public SystemLog fetchByType_First(String type,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByType(type, 0, 1, orderByComparator);

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
	public SystemLog findByType_Last(String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByType_Last(type, orderByComparator);

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
	public SystemLog fetchByType_Last(String type,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByType(type);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByType(type, count - 1, count,
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
	public SystemLog[] findByType_PrevAndNext(long logId, String type,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByType_PrevAndNext(session, systemLog, type,
					orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByType_PrevAndNext(session, systemLog, type,
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

	protected SystemLog getByType_PrevAndNext(Session session,
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
			query.append(_FINDER_COLUMN_TYPE_TYPE_1);
		}
		else if (type.equals("")) {
			query.append(_FINDER_COLUMN_TYPE_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);
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
	 * Removes all the system logs where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeByType(String type) {
		for (SystemLog systemLog : findByType(type, QueryUtil.ALL_POS,
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
	public int countByType(String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPE;

		Object[] finderArgs = new Object[] { type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else if (type.equals("")) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_TYPE_TYPE_2);
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

	private static final String _FINDER_COLUMN_TYPE_TYPE_1 = "systemLog.type IS NULL";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "systemLog.type = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_3 = "(systemLog.type IS NULL OR systemLog.type = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LINE = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLine",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LINE = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLine",
			new String[] { Integer.class.getName() },
			SystemLogModelImpl.LINE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LINE = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLine",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the system logs where line = &#63;.
	 *
	 * @param line the line
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByLine(int line) {
		return findByLine(line, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where line = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param line the line
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findByLine(int line, int start, int end) {
		return findByLine(line, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where line = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param line the line
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByLine(int line, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return findByLine(line, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where line = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param line the line
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByLine(int line, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LINE;
			finderArgs = new Object[] { line };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LINE;
			finderArgs = new Object[] { line, start, end, orderByComparator };
		}

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if ((line != systemLog.getLine())) {
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

			query.append(_FINDER_COLUMN_LINE_LINE_2);

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

				qPos.add(line);

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
	 * Returns the first system log in the ordered set where line = &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByLine_First(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByLine_First(line, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("line=");
		msg.append(line);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where line = &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByLine_First(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByLine(line, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where line = &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByLine_Last(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByLine_Last(line, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("line=");
		msg.append(line);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where line = &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByLine_Last(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByLine(line);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByLine(line, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where line = &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findByLine_PrevAndNext(long logId, int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByLine_PrevAndNext(session, systemLog, line,
					orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByLine_PrevAndNext(session, systemLog, line,
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

	protected SystemLog getByLine_PrevAndNext(Session session,
		SystemLog systemLog, int line,
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

		query.append(_FINDER_COLUMN_LINE_LINE_2);

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

		qPos.add(line);

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
	 * Removes all the system logs where line = &#63; from the database.
	 *
	 * @param line the line
	 */
	@Override
	public void removeByLine(int line) {
		for (SystemLog systemLog : findByLine(line, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where line = &#63;.
	 *
	 * @param line the line
	 * @return the number of matching system logs
	 */
	@Override
	public int countByLine(int line) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LINE;

		Object[] finderArgs = new Object[] { line };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			query.append(_FINDER_COLUMN_LINE_LINE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(line);

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

	private static final String _FINDER_COLUMN_LINE_LINE_2 = "systemLog.line = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LINEGREATER =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLineGreater",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LINEGREATER =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLineGreater",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the system logs where line &gt; &#63;.
	 *
	 * @param line the line
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByLineGreater(int line) {
		return findByLineGreater(line, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the system logs where line &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param line the line
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findByLineGreater(int line, int start, int end) {
		return findByLineGreater(line, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where line &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param line the line
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByLineGreater(int line, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return findByLineGreater(line, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where line &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param line the line
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByLineGreater(int line, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LINEGREATER;
		finderArgs = new Object[] { line, start, end, orderByComparator };

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if ((line >= systemLog.getLine())) {
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

			query.append(_FINDER_COLUMN_LINEGREATER_LINE_2);

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

				qPos.add(line);

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
	 * Returns the first system log in the ordered set where line &gt; &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByLineGreater_First(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByLineGreater_First(line, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("line=");
		msg.append(line);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where line &gt; &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByLineGreater_First(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByLineGreater(line, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where line &gt; &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByLineGreater_Last(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByLineGreater_Last(line, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("line=");
		msg.append(line);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where line &gt; &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByLineGreater_Last(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByLineGreater(line);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByLineGreater(line, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where line &gt; &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findByLineGreater_PrevAndNext(long logId, int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByLineGreater_PrevAndNext(session, systemLog, line,
					orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByLineGreater_PrevAndNext(session, systemLog, line,
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

	protected SystemLog getByLineGreater_PrevAndNext(Session session,
		SystemLog systemLog, int line,
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

		query.append(_FINDER_COLUMN_LINEGREATER_LINE_2);

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

		qPos.add(line);

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
	 * Removes all the system logs where line &gt; &#63; from the database.
	 *
	 * @param line the line
	 */
	@Override
	public void removeByLineGreater(int line) {
		for (SystemLog systemLog : findByLineGreater(line, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where line &gt; &#63;.
	 *
	 * @param line the line
	 * @return the number of matching system logs
	 */
	@Override
	public int countByLineGreater(int line) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LINEGREATER;

		Object[] finderArgs = new Object[] { line };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			query.append(_FINDER_COLUMN_LINEGREATER_LINE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(line);

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

	private static final String _FINDER_COLUMN_LINEGREATER_LINE_2 = "systemLog.line > ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LINELESSER =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLineLesser",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LINELESSER =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLineLesser",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the system logs where line &lt; &#63;.
	 *
	 * @param line the line
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByLineLesser(int line) {
		return findByLineLesser(line, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the system logs where line &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param line the line
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @return the range of matching system logs
	 */
	@Override
	public List<SystemLog> findByLineLesser(int line, int start, int end) {
		return findByLineLesser(line, start, end, null);
	}

	/**
	 * Returns an ordered range of all the system logs where line &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param line the line
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByLineLesser(int line, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return findByLineLesser(line, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the system logs where line &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param line the line
	 * @param start the lower bound of the range of system logs
	 * @param end the upper bound of the range of system logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching system logs
	 */
	@Override
	public List<SystemLog> findByLineLesser(int line, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LINELESSER;
		finderArgs = new Object[] { line, start, end, orderByComparator };

		List<SystemLog> list = null;

		if (retrieveFromCache) {
			list = (List<SystemLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SystemLog systemLog : list) {
					if ((line <= systemLog.getLine())) {
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

			query.append(_FINDER_COLUMN_LINELESSER_LINE_2);

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

				qPos.add(line);

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
	 * Returns the first system log in the ordered set where line &lt; &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByLineLesser_First(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByLineLesser_First(line, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("line=");
		msg.append(line);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the first system log in the ordered set where line &lt; &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByLineLesser_First(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByLineLesser(line, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last system log in the ordered set where line &lt; &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log
	 * @throws NoSuchSystemLogException if a matching system log could not be found
	 */
	@Override
	public SystemLog findByLineLesser_Last(int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByLineLesser_Last(line, orderByComparator);

		if (systemLog != null) {
			return systemLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("line=");
		msg.append(line);

		msg.append("}");

		throw new NoSuchSystemLogException(msg.toString());
	}

	/**
	 * Returns the last system log in the ordered set where line &lt; &#63;.
	 *
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching system log, or <code>null</code> if a matching system log could not be found
	 */
	@Override
	public SystemLog fetchByLineLesser_Last(int line,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByLineLesser(line);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByLineLesser(line, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the system logs before and after the current system log in the ordered set where line &lt; &#63;.
	 *
	 * @param logId the primary key of the current system log
	 * @param line the line
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next system log
	 * @throws NoSuchSystemLogException if a system log with the primary key could not be found
	 */
	@Override
	public SystemLog[] findByLineLesser_PrevAndNext(long logId, int line,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByLineLesser_PrevAndNext(session, systemLog, line,
					orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByLineLesser_PrevAndNext(session, systemLog, line,
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

	protected SystemLog getByLineLesser_PrevAndNext(Session session,
		SystemLog systemLog, int line,
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

		query.append(_FINDER_COLUMN_LINELESSER_LINE_2);

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

		qPos.add(line);

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
	 * Removes all the system logs where line &lt; &#63; from the database.
	 *
	 * @param line the line
	 */
	@Override
	public void removeByLineLesser(int line) {
		for (SystemLog systemLog : findByLineLesser(line, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(systemLog);
		}
	}

	/**
	 * Returns the number of system logs where line &lt; &#63;.
	 *
	 * @param line the line
	 * @return the number of matching system logs
	 */
	@Override
	public int countByLineLesser(int line) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LINELESSER;

		Object[] finderArgs = new Object[] { line };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			query.append(_FINDER_COLUMN_LINELESSER_LINE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(line);

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

	private static final String _FINDER_COLUMN_LINELESSER_LINE_2 = "systemLog.line < ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, SystemLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			SystemLogModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SystemLogModelImpl.ENTITY_CACHE_ENABLED,
			SystemLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the system logs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching system logs
	 */
	@Override
	public List<SystemLog> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<SystemLog> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
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
	public List<SystemLog> findByGroupId(long groupId, int start, int end,
		OrderByComparator<SystemLog> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	public List<SystemLog> findByGroupId(long groupId, int start, int end,
		OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
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

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
	public SystemLog findByGroupId_First(long groupId,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByGroupId_First(groupId, orderByComparator);

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
	public SystemLog fetchByGroupId_First(long groupId,
		OrderByComparator<SystemLog> orderByComparator) {
		List<SystemLog> list = findByGroupId(groupId, 0, 1, orderByComparator);

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
	public SystemLog findByGroupId_Last(long groupId,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = fetchByGroupId_Last(groupId, orderByComparator);

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
	public SystemLog fetchByGroupId_Last(long groupId,
		OrderByComparator<SystemLog> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SystemLog> list = findByGroupId(groupId, count - 1, count,
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
	public SystemLog[] findByGroupId_PrevAndNext(long logId, long groupId,
		OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException {
		SystemLog systemLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			SystemLog[] array = new SystemLogImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, systemLog, groupId,
					orderByComparator, true);

			array[1] = systemLog;

			array[2] = getByGroupId_PrevAndNext(session, systemLog, groupId,
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

	protected SystemLog getByGroupId_PrevAndNext(Session session,
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

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
	 * Removes all the system logs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (SystemLog systemLog : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
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
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYSTEMLOG_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "systemLog.groupId = ?";

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

		finderCache.putResult(FINDER_PATH_FETCH_BY_LOGID,
			new Object[] { systemLog.getLogId() }, systemLog);

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

		args = new Object[] { systemLogModelImpl.getLogId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_LOGID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_LOGID, args,
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

		if (clearCurrent) {
			Object[] args = new Object[] { systemLogModelImpl.getLogId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LOGID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LOGID, args);
		}

		if ((systemLogModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_LOGID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { systemLogModelImpl.getOriginalLogId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LOGID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LOGID, args);
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

			args = new Object[] { systemLogModelImpl.getClassName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME,
				args);

			args = new Object[] { systemLogModelImpl.getModuleName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULENAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME,
				args);

			args = new Object[] { systemLogModelImpl.getType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
				args);

			args = new Object[] { systemLogModelImpl.getLine() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LINE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LINE,
				args);

			args = new Object[] { systemLogModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
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
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalClassName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME,
					args);

				args = new Object[] { systemLogModelImpl.getClassName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME,
					args);
			}

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalModuleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULENAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME,
					args);

				args = new Object[] { systemLogModelImpl.getModuleName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULENAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULENAME,
					args);
			}

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] { systemLogModelImpl.getType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LINE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalLine()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LINE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LINE,
					args);

				args = new Object[] { systemLogModelImpl.getLine() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LINE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LINE,
					args);
			}

			if ((systemLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						systemLogModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { systemLogModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
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

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

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