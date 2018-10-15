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

package org.opencps.datamgt.service.persistence.impl;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.datamgt.exception.NoSuchWorkTimeException;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.model.impl.WorkTimeImpl;
import org.opencps.datamgt.model.impl.WorkTimeModelImpl;
import org.opencps.datamgt.service.persistence.WorkTimePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
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
 * The persistence implementation for the work time service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see WorkTimePersistence
 * @see org.opencps.datamgt.service.persistence.WorkTimeUtil
 * @generated
 */
@ProviderType
public class WorkTimePersistenceImpl extends BasePersistenceImpl<WorkTime>
	implements WorkTimePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkTimeUtil} to access the work time persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkTimeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			WorkTimeModelImpl.UUID_COLUMN_BITMASK |
			WorkTimeModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the work times where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching work times
	 */
	@Override
	public List<WorkTime> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the work times where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @return the range of matching work times
	 */
	@Override
	public List<WorkTime> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the work times where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching work times
	 */
	@Override
	public List<WorkTime> findByUuid(String uuid, int start, int end,
		OrderByComparator<WorkTime> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the work times where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching work times
	 */
	@Override
	public List<WorkTime> findByUuid(String uuid, int start, int end,
		OrderByComparator<WorkTime> orderByComparator, boolean retrieveFromCache) {
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

		List<WorkTime> list = null;

		if (retrieveFromCache) {
			list = (List<WorkTime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkTime workTime : list) {
					if (!Objects.equals(uuid, workTime.getUuid())) {
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

			query.append(_SQL_SELECT_WORKTIME_WHERE);

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
				query.append(WorkTimeModelImpl.ORDER_BY_JPQL);
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
					list = (List<WorkTime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkTime>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first work time in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work time
	 * @throws NoSuchWorkTimeException if a matching work time could not be found
	 */
	@Override
	public WorkTime findByUuid_First(String uuid,
		OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException {
		WorkTime workTime = fetchByUuid_First(uuid, orderByComparator);

		if (workTime != null) {
			return workTime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchWorkTimeException(msg.toString());
	}

	/**
	 * Returns the first work time in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByUuid_First(String uuid,
		OrderByComparator<WorkTime> orderByComparator) {
		List<WorkTime> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last work time in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work time
	 * @throws NoSuchWorkTimeException if a matching work time could not be found
	 */
	@Override
	public WorkTime findByUuid_Last(String uuid,
		OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException {
		WorkTime workTime = fetchByUuid_Last(uuid, orderByComparator);

		if (workTime != null) {
			return workTime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchWorkTimeException(msg.toString());
	}

	/**
	 * Returns the last work time in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByUuid_Last(String uuid,
		OrderByComparator<WorkTime> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<WorkTime> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the work times before and after the current work time in the ordered set where uuid = &#63;.
	 *
	 * @param workTimeId the primary key of the current work time
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next work time
	 * @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	 */
	@Override
	public WorkTime[] findByUuid_PrevAndNext(long workTimeId, String uuid,
		OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException {
		WorkTime workTime = findByPrimaryKey(workTimeId);

		Session session = null;

		try {
			session = openSession();

			WorkTime[] array = new WorkTimeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, workTime, uuid,
					orderByComparator, true);

			array[1] = workTime;

			array[2] = getByUuid_PrevAndNext(session, workTime, uuid,
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

	protected WorkTime getByUuid_PrevAndNext(Session session,
		WorkTime workTime, String uuid,
		OrderByComparator<WorkTime> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKTIME_WHERE);

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
			query.append(WorkTimeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(workTime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkTime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the work times where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (WorkTime workTime : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workTime);
		}
	}

	/**
	 * Returns the number of work times where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching work times
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WORKTIME_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "workTime.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "workTime.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(workTime.uuid IS NULL OR workTime.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			WorkTimeModelImpl.UUID_COLUMN_BITMASK |
			WorkTimeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the work time where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkTimeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching work time
	 * @throws NoSuchWorkTimeException if a matching work time could not be found
	 */
	@Override
	public WorkTime findByUUID_G(String uuid, long groupId)
		throws NoSuchWorkTimeException {
		WorkTime workTime = fetchByUUID_G(uuid, groupId);

		if (workTime == null) {
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

			throw new NoSuchWorkTimeException(msg.toString());
		}

		return workTime;
	}

	/**
	 * Returns the work time where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the work time where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof WorkTime) {
			WorkTime workTime = (WorkTime)result;

			if (!Objects.equals(uuid, workTime.getUuid()) ||
					(groupId != workTime.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WORKTIME_WHERE);

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

				List<WorkTime> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					WorkTime workTime = list.get(0);

					result = workTime;

					cacheResult(workTime);
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
			return (WorkTime)result;
		}
	}

	/**
	 * Removes the work time where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the work time that was removed
	 */
	@Override
	public WorkTime removeByUUID_G(String uuid, long groupId)
		throws NoSuchWorkTimeException {
		WorkTime workTime = findByUUID_G(uuid, groupId);

		return remove(workTime);
	}

	/**
	 * Returns the number of work times where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching work times
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKTIME_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "workTime.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "workTime.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(workTime.uuid IS NULL OR workTime.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "workTime.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			WorkTimeModelImpl.UUID_COLUMN_BITMASK |
			WorkTimeModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkTimeModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the work times where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching work times
	 */
	@Override
	public List<WorkTime> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the work times where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @return the range of matching work times
	 */
	@Override
	public List<WorkTime> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the work times where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching work times
	 */
	@Override
	public List<WorkTime> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<WorkTime> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the work times where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching work times
	 */
	@Override
	public List<WorkTime> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<WorkTime> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<WorkTime> list = null;

		if (retrieveFromCache) {
			list = (List<WorkTime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkTime workTime : list) {
					if (!Objects.equals(uuid, workTime.getUuid()) ||
							(companyId != workTime.getCompanyId())) {
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

			query.append(_SQL_SELECT_WORKTIME_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkTimeModelImpl.ORDER_BY_JPQL);
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

				qPos.add(companyId);

				if (!pagination) {
					list = (List<WorkTime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkTime>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first work time in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work time
	 * @throws NoSuchWorkTimeException if a matching work time could not be found
	 */
	@Override
	public WorkTime findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException {
		WorkTime workTime = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (workTime != null) {
			return workTime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchWorkTimeException(msg.toString());
	}

	/**
	 * Returns the first work time in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator) {
		List<WorkTime> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last work time in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work time
	 * @throws NoSuchWorkTimeException if a matching work time could not be found
	 */
	@Override
	public WorkTime findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException {
		WorkTime workTime = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (workTime != null) {
			return workTime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchWorkTimeException(msg.toString());
	}

	/**
	 * Returns the last work time in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<WorkTime> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the work times before and after the current work time in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param workTimeId the primary key of the current work time
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next work time
	 * @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	 */
	@Override
	public WorkTime[] findByUuid_C_PrevAndNext(long workTimeId, String uuid,
		long companyId, OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException {
		WorkTime workTime = findByPrimaryKey(workTimeId);

		Session session = null;

		try {
			session = openSession();

			WorkTime[] array = new WorkTimeImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, workTime, uuid,
					companyId, orderByComparator, true);

			array[1] = workTime;

			array[2] = getByUuid_C_PrevAndNext(session, workTime, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkTime getByUuid_C_PrevAndNext(Session session,
		WorkTime workTime, String uuid, long companyId,
		OrderByComparator<WorkTime> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_WORKTIME_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(WorkTimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workTime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkTime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the work times where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (WorkTime workTime : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workTime);
		}
	}

	/**
	 * Returns the number of work times where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching work times
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKTIME_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "workTime.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "workTime.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(workTime.uuid IS NULL OR workTime.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "workTime.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_DAY = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_day",
			new String[] { Long.class.getName(), Integer.class.getName() },
			WorkTimeModelImpl.GROUPID_COLUMN_BITMASK |
			WorkTimeModelImpl.DAY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DAY = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_day",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns the work time where groupId = &#63; and day = &#63; or throws a {@link NoSuchWorkTimeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param day the day
	 * @return the matching work time
	 * @throws NoSuchWorkTimeException if a matching work time could not be found
	 */
	@Override
	public WorkTime findByF_day(long groupId, int day)
		throws NoSuchWorkTimeException {
		WorkTime workTime = fetchByF_day(groupId, day);

		if (workTime == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", day=");
			msg.append(day);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchWorkTimeException(msg.toString());
		}

		return workTime;
	}

	/**
	 * Returns the work time where groupId = &#63; and day = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param day the day
	 * @return the matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByF_day(long groupId, int day) {
		return fetchByF_day(groupId, day, true);
	}

	/**
	 * Returns the work time where groupId = &#63; and day = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param day the day
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByF_day(long groupId, int day,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, day };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_DAY,
					finderArgs, this);
		}

		if (result instanceof WorkTime) {
			WorkTime workTime = (WorkTime)result;

			if ((groupId != workTime.getGroupId()) ||
					(day != workTime.getDay())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WORKTIME_WHERE);

			query.append(_FINDER_COLUMN_F_DAY_GROUPID_2);

			query.append(_FINDER_COLUMN_F_DAY_DAY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(day);

				List<WorkTime> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_DAY,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"WorkTimePersistenceImpl.fetchByF_day(long, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					WorkTime workTime = list.get(0);

					result = workTime;

					cacheResult(workTime);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DAY, finderArgs);

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
			return (WorkTime)result;
		}
	}

	/**
	 * Removes the work time where groupId = &#63; and day = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param day the day
	 * @return the work time that was removed
	 */
	@Override
	public WorkTime removeByF_day(long groupId, int day)
		throws NoSuchWorkTimeException {
		WorkTime workTime = findByF_day(groupId, day);

		return remove(workTime);
	}

	/**
	 * Returns the number of work times where groupId = &#63; and day = &#63;.
	 *
	 * @param groupId the group ID
	 * @param day the day
	 * @return the number of matching work times
	 */
	@Override
	public int countByF_day(long groupId, int day) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DAY;

		Object[] finderArgs = new Object[] { groupId, day };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKTIME_WHERE);

			query.append(_FINDER_COLUMN_F_DAY_GROUPID_2);

			query.append(_FINDER_COLUMN_F_DAY_DAY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(day);

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

	private static final String _FINDER_COLUMN_F_DAY_GROUPID_2 = "workTime.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_DAY_DAY_2 = "workTime.day = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, WorkTimeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID",
			new String[] { Long.class.getName() },
			WorkTimeModelImpl.GROUPID_COLUMN_BITMASK |
			WorkTimeModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID = new FinderPath(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the work times where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching work times
	 */
	@Override
	public List<WorkTime> findByF_GID(long groupId) {
		return findByF_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the work times where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @return the range of matching work times
	 */
	@Override
	public List<WorkTime> findByF_GID(long groupId, int start, int end) {
		return findByF_GID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the work times where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching work times
	 */
	@Override
	public List<WorkTime> findByF_GID(long groupId, int start, int end,
		OrderByComparator<WorkTime> orderByComparator) {
		return findByF_GID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the work times where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching work times
	 */
	@Override
	public List<WorkTime> findByF_GID(long groupId, int start, int end,
		OrderByComparator<WorkTime> orderByComparator, boolean retrieveFromCache) {
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

		List<WorkTime> list = null;

		if (retrieveFromCache) {
			list = (List<WorkTime>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkTime workTime : list) {
					if ((groupId != workTime.getGroupId())) {
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

			query.append(_SQL_SELECT_WORKTIME_WHERE);

			query.append(_FINDER_COLUMN_F_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkTimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<WorkTime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkTime>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first work time in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work time
	 * @throws NoSuchWorkTimeException if a matching work time could not be found
	 */
	@Override
	public WorkTime findByF_GID_First(long groupId,
		OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException {
		WorkTime workTime = fetchByF_GID_First(groupId, orderByComparator);

		if (workTime != null) {
			return workTime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchWorkTimeException(msg.toString());
	}

	/**
	 * Returns the first work time in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByF_GID_First(long groupId,
		OrderByComparator<WorkTime> orderByComparator) {
		List<WorkTime> list = findByF_GID(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last work time in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work time
	 * @throws NoSuchWorkTimeException if a matching work time could not be found
	 */
	@Override
	public WorkTime findByF_GID_Last(long groupId,
		OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException {
		WorkTime workTime = fetchByF_GID_Last(groupId, orderByComparator);

		if (workTime != null) {
			return workTime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchWorkTimeException(msg.toString());
	}

	/**
	 * Returns the last work time in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching work time, or <code>null</code> if a matching work time could not be found
	 */
	@Override
	public WorkTime fetchByF_GID_Last(long groupId,
		OrderByComparator<WorkTime> orderByComparator) {
		int count = countByF_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<WorkTime> list = findByF_GID(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the work times before and after the current work time in the ordered set where groupId = &#63;.
	 *
	 * @param workTimeId the primary key of the current work time
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next work time
	 * @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	 */
	@Override
	public WorkTime[] findByF_GID_PrevAndNext(long workTimeId, long groupId,
		OrderByComparator<WorkTime> orderByComparator)
		throws NoSuchWorkTimeException {
		WorkTime workTime = findByPrimaryKey(workTimeId);

		Session session = null;

		try {
			session = openSession();

			WorkTime[] array = new WorkTimeImpl[3];

			array[0] = getByF_GID_PrevAndNext(session, workTime, groupId,
					orderByComparator, true);

			array[1] = workTime;

			array[2] = getByF_GID_PrevAndNext(session, workTime, groupId,
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

	protected WorkTime getByF_GID_PrevAndNext(Session session,
		WorkTime workTime, long groupId,
		OrderByComparator<WorkTime> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKTIME_WHERE);

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
			query.append(WorkTimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workTime);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkTime> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the work times where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_GID(long groupId) {
		for (WorkTime workTime : findByF_GID(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workTime);
		}
	}

	/**
	 * Returns the number of work times where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching work times
	 */
	@Override
	public int countByF_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WORKTIME_WHERE);

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

	private static final String _FINDER_COLUMN_F_GID_GROUPID_2 = "workTime.groupId = ?";

	public WorkTimePersistenceImpl() {
		setModelClass(WorkTime.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the work time in the entity cache if it is enabled.
	 *
	 * @param workTime the work time
	 */
	@Override
	public void cacheResult(WorkTime workTime) {
		entityCache.putResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeImpl.class, workTime.getPrimaryKey(), workTime);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { workTime.getUuid(), workTime.getGroupId() }, workTime);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DAY,
			new Object[] { workTime.getGroupId(), workTime.getDay() }, workTime);

		workTime.resetOriginalValues();
	}

	/**
	 * Caches the work times in the entity cache if it is enabled.
	 *
	 * @param workTimes the work times
	 */
	@Override
	public void cacheResult(List<WorkTime> workTimes) {
		for (WorkTime workTime : workTimes) {
			if (entityCache.getResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
						WorkTimeImpl.class, workTime.getPrimaryKey()) == null) {
				cacheResult(workTime);
			}
			else {
				workTime.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all work times.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WorkTimeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the work time.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkTime workTime) {
		entityCache.removeResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeImpl.class, workTime.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((WorkTimeModelImpl)workTime, true);
	}

	@Override
	public void clearCache(List<WorkTime> workTimes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkTime workTime : workTimes) {
			entityCache.removeResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
				WorkTimeImpl.class, workTime.getPrimaryKey());

			clearUniqueFindersCache((WorkTimeModelImpl)workTime, true);
		}
	}

	protected void cacheUniqueFindersCache(WorkTimeModelImpl workTimeModelImpl) {
		Object[] args = new Object[] {
				workTimeModelImpl.getUuid(), workTimeModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			workTimeModelImpl, false);

		args = new Object[] {
				workTimeModelImpl.getGroupId(), workTimeModelImpl.getDay()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_DAY, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_DAY, args,
			workTimeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		WorkTimeModelImpl workTimeModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					workTimeModelImpl.getUuid(), workTimeModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((workTimeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					workTimeModelImpl.getOriginalUuid(),
					workTimeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					workTimeModelImpl.getGroupId(), workTimeModelImpl.getDay()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DAY, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DAY, args);
		}

		if ((workTimeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_DAY.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					workTimeModelImpl.getOriginalGroupId(),
					workTimeModelImpl.getOriginalDay()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DAY, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_DAY, args);
		}
	}

	/**
	 * Creates a new work time with the primary key. Does not add the work time to the database.
	 *
	 * @param workTimeId the primary key for the new work time
	 * @return the new work time
	 */
	@Override
	public WorkTime create(long workTimeId) {
		WorkTime workTime = new WorkTimeImpl();

		workTime.setNew(true);
		workTime.setPrimaryKey(workTimeId);

		String uuid = PortalUUIDUtil.generate();

		workTime.setUuid(uuid);

		workTime.setCompanyId(companyProvider.getCompanyId());

		return workTime;
	}

	/**
	 * Removes the work time with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workTimeId the primary key of the work time
	 * @return the work time that was removed
	 * @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	 */
	@Override
	public WorkTime remove(long workTimeId) throws NoSuchWorkTimeException {
		return remove((Serializable)workTimeId);
	}

	/**
	 * Removes the work time with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the work time
	 * @return the work time that was removed
	 * @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	 */
	@Override
	public WorkTime remove(Serializable primaryKey)
		throws NoSuchWorkTimeException {
		Session session = null;

		try {
			session = openSession();

			WorkTime workTime = (WorkTime)session.get(WorkTimeImpl.class,
					primaryKey);

			if (workTime == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkTimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workTime);
		}
		catch (NoSuchWorkTimeException nsee) {
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
	protected WorkTime removeImpl(WorkTime workTime) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workTime)) {
				workTime = (WorkTime)session.get(WorkTimeImpl.class,
						workTime.getPrimaryKeyObj());
			}

			if (workTime != null) {
				session.delete(workTime);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workTime != null) {
			clearCache(workTime);
		}

		return workTime;
	}

	@Override
	public WorkTime updateImpl(WorkTime workTime) {
		boolean isNew = workTime.isNew();

		if (!(workTime instanceof WorkTimeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(workTime.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(workTime);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in workTime proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WorkTime implementation " +
				workTime.getClass());
		}

		WorkTimeModelImpl workTimeModelImpl = (WorkTimeModelImpl)workTime;

		if (Validator.isNull(workTime.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			workTime.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (workTime.getCreateDate() == null)) {
			if (serviceContext == null) {
				workTime.setCreateDate(now);
			}
			else {
				workTime.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!workTimeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				workTime.setModifiedDate(now);
			}
			else {
				workTime.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (workTime.isNew()) {
				session.save(workTime);

				workTime.setNew(false);
			}
			else {
				workTime = (WorkTime)session.merge(workTime);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!WorkTimeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { workTimeModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					workTimeModelImpl.getUuid(),
					workTimeModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { workTimeModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((workTimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { workTimeModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { workTimeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((workTimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workTimeModelImpl.getOriginalUuid(),
						workTimeModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						workTimeModelImpl.getUuid(),
						workTimeModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((workTimeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workTimeModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);

				args = new Object[] { workTimeModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID,
					args);
			}
		}

		entityCache.putResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
			WorkTimeImpl.class, workTime.getPrimaryKey(), workTime, false);

		clearUniqueFindersCache(workTimeModelImpl, false);
		cacheUniqueFindersCache(workTimeModelImpl);

		workTime.resetOriginalValues();

		return workTime;
	}

	/**
	 * Returns the work time with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the work time
	 * @return the work time
	 * @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	 */
	@Override
	public WorkTime findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkTimeException {
		WorkTime workTime = fetchByPrimaryKey(primaryKey);

		if (workTime == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkTimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workTime;
	}

	/**
	 * Returns the work time with the primary key or throws a {@link NoSuchWorkTimeException} if it could not be found.
	 *
	 * @param workTimeId the primary key of the work time
	 * @return the work time
	 * @throws NoSuchWorkTimeException if a work time with the primary key could not be found
	 */
	@Override
	public WorkTime findByPrimaryKey(long workTimeId)
		throws NoSuchWorkTimeException {
		return findByPrimaryKey((Serializable)workTimeId);
	}

	/**
	 * Returns the work time with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the work time
	 * @return the work time, or <code>null</code> if a work time with the primary key could not be found
	 */
	@Override
	public WorkTime fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
				WorkTimeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WorkTime workTime = (WorkTime)serializable;

		if (workTime == null) {
			Session session = null;

			try {
				session = openSession();

				workTime = (WorkTime)session.get(WorkTimeImpl.class, primaryKey);

				if (workTime != null) {
					cacheResult(workTime);
				}
				else {
					entityCache.putResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
						WorkTimeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
					WorkTimeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workTime;
	}

	/**
	 * Returns the work time with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workTimeId the primary key of the work time
	 * @return the work time, or <code>null</code> if a work time with the primary key could not be found
	 */
	@Override
	public WorkTime fetchByPrimaryKey(long workTimeId) {
		return fetchByPrimaryKey((Serializable)workTimeId);
	}

	@Override
	public Map<Serializable, WorkTime> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WorkTime> map = new HashMap<Serializable, WorkTime>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WorkTime workTime = fetchByPrimaryKey(primaryKey);

			if (workTime != null) {
				map.put(primaryKey, workTime);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
					WorkTimeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WorkTime)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WORKTIME_WHERE_PKS_IN);

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

			for (WorkTime workTime : (List<WorkTime>)q.list()) {
				map.put(workTime.getPrimaryKeyObj(), workTime);

				cacheResult(workTime);

				uncachedPrimaryKeys.remove(workTime.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WorkTimeModelImpl.ENTITY_CACHE_ENABLED,
					WorkTimeImpl.class, primaryKey, nullModel);
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
	 * Returns all the work times.
	 *
	 * @return the work times
	 */
	@Override
	public List<WorkTime> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the work times.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @return the range of work times
	 */
	@Override
	public List<WorkTime> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the work times.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of work times
	 */
	@Override
	public List<WorkTime> findAll(int start, int end,
		OrderByComparator<WorkTime> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the work times.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of work times
	 * @param end the upper bound of the range of work times (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of work times
	 */
	@Override
	public List<WorkTime> findAll(int start, int end,
		OrderByComparator<WorkTime> orderByComparator, boolean retrieveFromCache) {
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

		List<WorkTime> list = null;

		if (retrieveFromCache) {
			list = (List<WorkTime>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WORKTIME);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKTIME;

				if (pagination) {
					sql = sql.concat(WorkTimeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkTime>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkTime>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the work times from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WorkTime workTime : findAll()) {
			remove(workTime);
		}
	}

	/**
	 * Returns the number of work times.
	 *
	 * @return the number of work times
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORKTIME);

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
		return WorkTimeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the work time persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WorkTimeImpl.class.getName());
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
	private static final String _SQL_SELECT_WORKTIME = "SELECT workTime FROM WorkTime workTime";
	private static final String _SQL_SELECT_WORKTIME_WHERE_PKS_IN = "SELECT workTime FROM WorkTime workTime WHERE workTimeId IN (";
	private static final String _SQL_SELECT_WORKTIME_WHERE = "SELECT workTime FROM WorkTime workTime WHERE ";
	private static final String _SQL_COUNT_WORKTIME = "SELECT COUNT(workTime) FROM WorkTime workTime";
	private static final String _SQL_COUNT_WORKTIME_WHERE = "SELECT COUNT(workTime) FROM WorkTime workTime WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workTime.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkTime exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WorkTime exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(WorkTimePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}