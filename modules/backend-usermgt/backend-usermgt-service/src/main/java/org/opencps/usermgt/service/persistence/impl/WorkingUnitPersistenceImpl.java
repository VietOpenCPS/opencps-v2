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

package org.opencps.usermgt.service.persistence.impl;

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

import org.opencps.usermgt.exception.NoSuchWorkingUnitException;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.model.impl.WorkingUnitImpl;
import org.opencps.usermgt.model.impl.WorkingUnitModelImpl;
import org.opencps.usermgt.service.persistence.WorkingUnitPersistence;

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
 * The persistence implementation for the working unit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see WorkingUnitPersistence
 * @see org.opencps.usermgt.service.persistence.WorkingUnitUtil
 * @generated
 */
@ProviderType
public class WorkingUnitPersistenceImpl extends BasePersistenceImpl<WorkingUnit>
	implements WorkingUnitPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkingUnitUtil} to access the working unit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkingUnitImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			WorkingUnitModelImpl.UUID_COLUMN_BITMASK |
			WorkingUnitModelImpl.TREEINDEX_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the working units where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching working units
	 */
	@Override
	public List<WorkingUnit> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the working units where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @return the range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the working units where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByUuid(String uuid, int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the working units where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByUuid(String uuid, int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator,
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

		List<WorkingUnit> list = null;

		if (retrieveFromCache) {
			list = (List<WorkingUnit>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkingUnit workingUnit : list) {
					if (!Objects.equals(uuid, workingUnit.getUuid())) {
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

			query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

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
				query.append(WorkingUnitModelImpl.ORDER_BY_JPQL);
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
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first working unit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByUuid_First(String uuid,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByUuid_First(uuid, orderByComparator);

		if (workingUnit != null) {
			return workingUnit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchWorkingUnitException(msg.toString());
	}

	/**
	 * Returns the first working unit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByUuid_First(String uuid,
		OrderByComparator<WorkingUnit> orderByComparator) {
		List<WorkingUnit> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last working unit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByUuid_Last(String uuid,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByUuid_Last(uuid, orderByComparator);

		if (workingUnit != null) {
			return workingUnit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchWorkingUnitException(msg.toString());
	}

	/**
	 * Returns the last working unit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByUuid_Last(String uuid,
		OrderByComparator<WorkingUnit> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<WorkingUnit> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the working units before and after the current working unit in the ordered set where uuid = &#63;.
	 *
	 * @param workingUnitId the primary key of the current working unit
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next working unit
	 * @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit[] findByUuid_PrevAndNext(long workingUnitId,
		String uuid, OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = findByPrimaryKey(workingUnitId);

		Session session = null;

		try {
			session = openSession();

			WorkingUnit[] array = new WorkingUnitImpl[3];

			array[0] = getByUuid_PrevAndNext(session, workingUnit, uuid,
					orderByComparator, true);

			array[1] = workingUnit;

			array[2] = getByUuid_PrevAndNext(session, workingUnit, uuid,
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

	protected WorkingUnit getByUuid_PrevAndNext(Session session,
		WorkingUnit workingUnit, String uuid,
		OrderByComparator<WorkingUnit> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

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
			query.append(WorkingUnitModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(workingUnit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkingUnit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the working units where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (WorkingUnit workingUnit : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workingUnit);
		}
	}

	/**
	 * Returns the number of working units where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching working units
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WORKINGUNIT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "workingUnit.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "workingUnit.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(workingUnit.uuid IS NULL OR workingUnit.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			WorkingUnitModelImpl.UUID_COLUMN_BITMASK |
			WorkingUnitModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the working unit where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkingUnitException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByUUID_G(String uuid, long groupId)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByUUID_G(uuid, groupId);

		if (workingUnit == null) {
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

			throw new NoSuchWorkingUnitException(msg.toString());
		}

		return workingUnit;
	}

	/**
	 * Returns the working unit where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the working unit where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof WorkingUnit) {
			WorkingUnit workingUnit = (WorkingUnit)result;

			if (!Objects.equals(uuid, workingUnit.getUuid()) ||
					(groupId != workingUnit.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

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

				List<WorkingUnit> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					WorkingUnit workingUnit = list.get(0);

					result = workingUnit;

					cacheResult(workingUnit);
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
			return (WorkingUnit)result;
		}
	}

	/**
	 * Removes the working unit where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the working unit that was removed
	 */
	@Override
	public WorkingUnit removeByUUID_G(String uuid, long groupId)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = findByUUID_G(uuid, groupId);

		return remove(workingUnit);
	}

	/**
	 * Returns the number of working units where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching working units
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKINGUNIT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "workingUnit.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "workingUnit.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(workingUnit.uuid IS NULL OR workingUnit.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "workingUnit.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			WorkingUnitModelImpl.UUID_COLUMN_BITMASK |
			WorkingUnitModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkingUnitModelImpl.TREEINDEX_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the working units where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching working units
	 */
	@Override
	public List<WorkingUnit> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the working units where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @return the range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the working units where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WorkingUnit> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the working units where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WorkingUnit> orderByComparator,
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

		List<WorkingUnit> list = null;

		if (retrieveFromCache) {
			list = (List<WorkingUnit>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkingUnit workingUnit : list) {
					if (!Objects.equals(uuid, workingUnit.getUuid()) ||
							(companyId != workingUnit.getCompanyId())) {
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

			query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

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
				query.append(WorkingUnitModelImpl.ORDER_BY_JPQL);
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
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (workingUnit != null) {
			return workingUnit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchWorkingUnitException(msg.toString());
	}

	/**
	 * Returns the first working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WorkingUnit> orderByComparator) {
		List<WorkingUnit> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (workingUnit != null) {
			return workingUnit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchWorkingUnitException(msg.toString());
	}

	/**
	 * Returns the last working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WorkingUnit> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<WorkingUnit> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the working units before and after the current working unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param workingUnitId the primary key of the current working unit
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next working unit
	 * @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit[] findByUuid_C_PrevAndNext(long workingUnitId,
		String uuid, long companyId,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = findByPrimaryKey(workingUnitId);

		Session session = null;

		try {
			session = openSession();

			WorkingUnit[] array = new WorkingUnitImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, workingUnit, uuid,
					companyId, orderByComparator, true);

			array[1] = workingUnit;

			array[2] = getByUuid_C_PrevAndNext(session, workingUnit, uuid,
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

	protected WorkingUnit getByUuid_C_PrevAndNext(Session session,
		WorkingUnit workingUnit, String uuid, long companyId,
		OrderByComparator<WorkingUnit> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

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
			query.append(WorkingUnitModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(workingUnit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkingUnit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the working units where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (WorkingUnit workingUnit : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workingUnit);
		}
	}

	/**
	 * Returns the number of working units where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching working units
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKINGUNIT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "workingUnit.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "workingUnit.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(workingUnit.uuid IS NULL OR workingUnit.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "workingUnit.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GOVAGENCYCODE = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_govAgencyCode",
			new String[] { Long.class.getName(), String.class.getName() },
			WorkingUnitModelImpl.GROUPID_COLUMN_BITMASK |
			WorkingUnitModelImpl.GOVAGENCYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GOVAGENCYCODE = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_govAgencyCode",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the working unit where groupId = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchWorkingUnitException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByF_govAgencyCode(long groupId, String govAgencyCode)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByF_govAgencyCode(groupId, govAgencyCode);

		if (workingUnit == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchWorkingUnitException(msg.toString());
		}

		return workingUnit;
	}

	/**
	 * Returns the working unit where groupId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByF_govAgencyCode(long groupId, String govAgencyCode) {
		return fetchByF_govAgencyCode(groupId, govAgencyCode, true);
	}

	/**
	 * Returns the working unit where groupId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByF_govAgencyCode(long groupId,
		String govAgencyCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, govAgencyCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GOVAGENCYCODE,
					finderArgs, this);
		}

		if (result instanceof WorkingUnit) {
			WorkingUnit workingUnit = (WorkingUnit)result;

			if ((groupId != workingUnit.getGroupId()) ||
					!Objects.equals(govAgencyCode,
						workingUnit.getGovAgencyCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

			query.append(_FINDER_COLUMN_F_GOVAGENCYCODE_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_GOVAGENCYCODE_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GOVAGENCYCODE_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_F_GOVAGENCYCODE_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				List<WorkingUnit> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GOVAGENCYCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"WorkingUnitPersistenceImpl.fetchByF_govAgencyCode(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					WorkingUnit workingUnit = list.get(0);

					result = workingUnit;

					cacheResult(workingUnit);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GOVAGENCYCODE,
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
			return (WorkingUnit)result;
		}
	}

	/**
	 * Removes the working unit where groupId = &#63; and govAgencyCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the working unit that was removed
	 */
	@Override
	public WorkingUnit removeByF_govAgencyCode(long groupId,
		String govAgencyCode) throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = findByF_govAgencyCode(groupId, govAgencyCode);

		return remove(workingUnit);
	}

	/**
	 * Returns the number of working units where groupId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @return the number of matching working units
	 */
	@Override
	public int countByF_govAgencyCode(long groupId, String govAgencyCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GOVAGENCYCODE;

		Object[] finderArgs = new Object[] { groupId, govAgencyCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKINGUNIT_WHERE);

			query.append(_FINDER_COLUMN_F_GOVAGENCYCODE_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_GOVAGENCYCODE_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GOVAGENCYCODE_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_F_GOVAGENCYCODE_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
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

	private static final String _FINDER_COLUMN_F_GOVAGENCYCODE_GROUPID_2 = "workingUnit.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GOVAGENCYCODE_GOVAGENCYCODE_1 = "workingUnit.govAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_F_GOVAGENCYCODE_GOVAGENCYCODE_2 = "workingUnit.govAgencyCode = ?";
	private static final String _FINDER_COLUMN_F_GOVAGENCYCODE_GOVAGENCYCODE_3 = "(workingUnit.govAgencyCode IS NULL OR workingUnit.govAgencyCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_PARENTID_LEVEL =
		new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_parentId_level",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTID_LEVEL =
		new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_parentId_level",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			WorkingUnitModelImpl.GROUPID_COLUMN_BITMASK |
			WorkingUnitModelImpl.PARENTWORKINGUNITID_COLUMN_BITMASK |
			WorkingUnitModelImpl.LEVEL_COLUMN_BITMASK |
			WorkingUnitModelImpl.TREEINDEX_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_PARENTID_LEVEL = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_parentId_level",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the working units where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @return the matching working units
	 */
	@Override
	public List<WorkingUnit> findByF_parentId_level(long groupId,
		long parentWorkingUnitId, int level) {
		return findByF_parentId_level(groupId, parentWorkingUnitId, level,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the working units where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @return the range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByF_parentId_level(long groupId,
		long parentWorkingUnitId, int level, int start, int end) {
		return findByF_parentId_level(groupId, parentWorkingUnitId, level,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the working units where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByF_parentId_level(long groupId,
		long parentWorkingUnitId, int level, int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator) {
		return findByF_parentId_level(groupId, parentWorkingUnitId, level,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the working units where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByF_parentId_level(long groupId,
		long parentWorkingUnitId, int level, int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTID_LEVEL;
			finderArgs = new Object[] { groupId, parentWorkingUnitId, level };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_PARENTID_LEVEL;
			finderArgs = new Object[] {
					groupId, parentWorkingUnitId, level,
					
					start, end, orderByComparator
				};
		}

		List<WorkingUnit> list = null;

		if (retrieveFromCache) {
			list = (List<WorkingUnit>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkingUnit workingUnit : list) {
					if ((groupId != workingUnit.getGroupId()) ||
							(parentWorkingUnitId != workingUnit.getParentWorkingUnitId()) ||
							(level != workingUnit.getLevel())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

			query.append(_FINDER_COLUMN_F_PARENTID_LEVEL_GROUPID_2);

			query.append(_FINDER_COLUMN_F_PARENTID_LEVEL_PARENTWORKINGUNITID_2);

			query.append(_FINDER_COLUMN_F_PARENTID_LEVEL_LEVEL_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkingUnitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentWorkingUnitId);

				qPos.add(level);

				if (!pagination) {
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first working unit in the ordered set where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByF_parentId_level_First(long groupId,
		long parentWorkingUnitId, int level,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByF_parentId_level_First(groupId,
				parentWorkingUnitId, level, orderByComparator);

		if (workingUnit != null) {
			return workingUnit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentWorkingUnitId=");
		msg.append(parentWorkingUnitId);

		msg.append(", level=");
		msg.append(level);

		msg.append("}");

		throw new NoSuchWorkingUnitException(msg.toString());
	}

	/**
	 * Returns the first working unit in the ordered set where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByF_parentId_level_First(long groupId,
		long parentWorkingUnitId, int level,
		OrderByComparator<WorkingUnit> orderByComparator) {
		List<WorkingUnit> list = findByF_parentId_level(groupId,
				parentWorkingUnitId, level, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last working unit in the ordered set where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByF_parentId_level_Last(long groupId,
		long parentWorkingUnitId, int level,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByF_parentId_level_Last(groupId,
				parentWorkingUnitId, level, orderByComparator);

		if (workingUnit != null) {
			return workingUnit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentWorkingUnitId=");
		msg.append(parentWorkingUnitId);

		msg.append(", level=");
		msg.append(level);

		msg.append("}");

		throw new NoSuchWorkingUnitException(msg.toString());
	}

	/**
	 * Returns the last working unit in the ordered set where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByF_parentId_level_Last(long groupId,
		long parentWorkingUnitId, int level,
		OrderByComparator<WorkingUnit> orderByComparator) {
		int count = countByF_parentId_level(groupId, parentWorkingUnitId, level);

		if (count == 0) {
			return null;
		}

		List<WorkingUnit> list = findByF_parentId_level(groupId,
				parentWorkingUnitId, level, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the working units before and after the current working unit in the ordered set where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * @param workingUnitId the primary key of the current working unit
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next working unit
	 * @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit[] findByF_parentId_level_PrevAndNext(
		long workingUnitId, long groupId, long parentWorkingUnitId, int level,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = findByPrimaryKey(workingUnitId);

		Session session = null;

		try {
			session = openSession();

			WorkingUnit[] array = new WorkingUnitImpl[3];

			array[0] = getByF_parentId_level_PrevAndNext(session, workingUnit,
					groupId, parentWorkingUnitId, level, orderByComparator, true);

			array[1] = workingUnit;

			array[2] = getByF_parentId_level_PrevAndNext(session, workingUnit,
					groupId, parentWorkingUnitId, level, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkingUnit getByF_parentId_level_PrevAndNext(Session session,
		WorkingUnit workingUnit, long groupId, long parentWorkingUnitId,
		int level, OrderByComparator<WorkingUnit> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

		query.append(_FINDER_COLUMN_F_PARENTID_LEVEL_GROUPID_2);

		query.append(_FINDER_COLUMN_F_PARENTID_LEVEL_PARENTWORKINGUNITID_2);

		query.append(_FINDER_COLUMN_F_PARENTID_LEVEL_LEVEL_2);

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
			query.append(WorkingUnitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentWorkingUnitId);

		qPos.add(level);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workingUnit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkingUnit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the working units where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 */
	@Override
	public void removeByF_parentId_level(long groupId,
		long parentWorkingUnitId, int level) {
		for (WorkingUnit workingUnit : findByF_parentId_level(groupId,
				parentWorkingUnitId, level, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workingUnit);
		}
	}

	/**
	 * Returns the number of working units where groupId = &#63; and parentWorkingUnitId = &#63; and level = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentWorkingUnitId the parent working unit ID
	 * @param level the level
	 * @return the number of matching working units
	 */
	@Override
	public int countByF_parentId_level(long groupId, long parentWorkingUnitId,
		int level) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_PARENTID_LEVEL;

		Object[] finderArgs = new Object[] { groupId, parentWorkingUnitId, level };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_WORKINGUNIT_WHERE);

			query.append(_FINDER_COLUMN_F_PARENTID_LEVEL_GROUPID_2);

			query.append(_FINDER_COLUMN_F_PARENTID_LEVEL_PARENTWORKINGUNITID_2);

			query.append(_FINDER_COLUMN_F_PARENTID_LEVEL_LEVEL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentWorkingUnitId);

				qPos.add(level);

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

	private static final String _FINDER_COLUMN_F_PARENTID_LEVEL_GROUPID_2 = "workingUnit.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_PARENTID_LEVEL_PARENTWORKINGUNITID_2 =
		"workingUnit.parentWorkingUnitId = ? AND ";
	private static final String _FINDER_COLUMN_F_PARENTID_LEVEL_LEVEL_2 = "workingUnit.level = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CHILDS_UNIT =
		new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_childs_unit",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_CHILDS_UNIT =
		new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_childs_unit",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the working units where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @return the matching working units
	 */
	@Override
	public List<WorkingUnit> findByF_childs_unit(long groupId, String treeIndex) {
		return findByF_childs_unit(groupId, treeIndex, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the working units where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @return the range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByF_childs_unit(long groupId,
		String treeIndex, int start, int end) {
		return findByF_childs_unit(groupId, treeIndex, start, end, null);
	}

	/**
	 * Returns an ordered range of all the working units where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByF_childs_unit(long groupId,
		String treeIndex, int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator) {
		return findByF_childs_unit(groupId, treeIndex, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the working units where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching working units
	 */
	@Override
	public List<WorkingUnit> findByF_childs_unit(long groupId,
		String treeIndex, int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CHILDS_UNIT;
		finderArgs = new Object[] {
				groupId, treeIndex,
				
				start, end, orderByComparator
			};

		List<WorkingUnit> list = null;

		if (retrieveFromCache) {
			list = (List<WorkingUnit>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkingUnit workingUnit : list) {
					if ((groupId != workingUnit.getGroupId()) ||
							!StringUtil.wildcardMatches(
								workingUnit.getTreeIndex(), treeIndex, '_',
								'%', '\\', true)) {
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

			query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

			query.append(_FINDER_COLUMN_F_CHILDS_UNIT_GROUPID_2);

			boolean bindTreeIndex = false;

			if (treeIndex == null) {
				query.append(_FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_1);
			}
			else if (treeIndex.equals("")) {
				query.append(_FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_3);
			}
			else {
				bindTreeIndex = true;

				query.append(_FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkingUnitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTreeIndex) {
					qPos.add(treeIndex);
				}

				if (!pagination) {
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first working unit in the ordered set where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByF_childs_unit_First(long groupId,
		String treeIndex, OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByF_childs_unit_First(groupId,
				treeIndex, orderByComparator);

		if (workingUnit != null) {
			return workingUnit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", treeIndex=");
		msg.append(treeIndex);

		msg.append("}");

		throw new NoSuchWorkingUnitException(msg.toString());
	}

	/**
	 * Returns the first working unit in the ordered set where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByF_childs_unit_First(long groupId,
		String treeIndex, OrderByComparator<WorkingUnit> orderByComparator) {
		List<WorkingUnit> list = findByF_childs_unit(groupId, treeIndex, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last working unit in the ordered set where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByF_childs_unit_Last(long groupId, String treeIndex,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByF_childs_unit_Last(groupId, treeIndex,
				orderByComparator);

		if (workingUnit != null) {
			return workingUnit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", treeIndex=");
		msg.append(treeIndex);

		msg.append("}");

		throw new NoSuchWorkingUnitException(msg.toString());
	}

	/**
	 * Returns the last working unit in the ordered set where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByF_childs_unit_Last(long groupId,
		String treeIndex, OrderByComparator<WorkingUnit> orderByComparator) {
		int count = countByF_childs_unit(groupId, treeIndex);

		if (count == 0) {
			return null;
		}

		List<WorkingUnit> list = findByF_childs_unit(groupId, treeIndex,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the working units before and after the current working unit in the ordered set where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param workingUnitId the primary key of the current working unit
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next working unit
	 * @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit[] findByF_childs_unit_PrevAndNext(long workingUnitId,
		long groupId, String treeIndex,
		OrderByComparator<WorkingUnit> orderByComparator)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = findByPrimaryKey(workingUnitId);

		Session session = null;

		try {
			session = openSession();

			WorkingUnit[] array = new WorkingUnitImpl[3];

			array[0] = getByF_childs_unit_PrevAndNext(session, workingUnit,
					groupId, treeIndex, orderByComparator, true);

			array[1] = workingUnit;

			array[2] = getByF_childs_unit_PrevAndNext(session, workingUnit,
					groupId, treeIndex, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkingUnit getByF_childs_unit_PrevAndNext(Session session,
		WorkingUnit workingUnit, long groupId, String treeIndex,
		OrderByComparator<WorkingUnit> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

		query.append(_FINDER_COLUMN_F_CHILDS_UNIT_GROUPID_2);

		boolean bindTreeIndex = false;

		if (treeIndex == null) {
			query.append(_FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_1);
		}
		else if (treeIndex.equals("")) {
			query.append(_FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_3);
		}
		else {
			bindTreeIndex = true;

			query.append(_FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_2);
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
			query.append(WorkingUnitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindTreeIndex) {
			qPos.add(treeIndex);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workingUnit);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkingUnit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the working units where groupId = &#63; and treeIndex LIKE &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 */
	@Override
	public void removeByF_childs_unit(long groupId, String treeIndex) {
		for (WorkingUnit workingUnit : findByF_childs_unit(groupId, treeIndex,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workingUnit);
		}
	}

	/**
	 * Returns the number of working units where groupId = &#63; and treeIndex LIKE &#63;.
	 *
	 * @param groupId the group ID
	 * @param treeIndex the tree index
	 * @return the number of matching working units
	 */
	@Override
	public int countByF_childs_unit(long groupId, String treeIndex) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_CHILDS_UNIT;

		Object[] finderArgs = new Object[] { groupId, treeIndex };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKINGUNIT_WHERE);

			query.append(_FINDER_COLUMN_F_CHILDS_UNIT_GROUPID_2);

			boolean bindTreeIndex = false;

			if (treeIndex == null) {
				query.append(_FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_1);
			}
			else if (treeIndex.equals("")) {
				query.append(_FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_3);
			}
			else {
				bindTreeIndex = true;

				query.append(_FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTreeIndex) {
					qPos.add(treeIndex);
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

	private static final String _FINDER_COLUMN_F_CHILDS_UNIT_GROUPID_2 = "workingUnit.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_1 = "workingUnit.treeIndex IS NULL";
	private static final String _FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_2 = "workingUnit.treeIndex LIKE ?";
	private static final String _FINDER_COLUMN_F_CHILDS_UNIT_TREEINDEX_3 = "(workingUnit.treeIndex IS NULL OR workingUnit.treeIndex LIKE '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_WID = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, WorkingUnitImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_WID",
			new String[] { Long.class.getName(), Long.class.getName() },
			WorkingUnitModelImpl.GROUPID_COLUMN_BITMASK |
			WorkingUnitModelImpl.WORKINGUNITID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_WID = new FinderPath(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_WID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the working unit where groupId = &#63; and workingUnitId = &#63; or throws a {@link NoSuchWorkingUnitException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param workingUnitId the working unit ID
	 * @return the matching working unit
	 * @throws NoSuchWorkingUnitException if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit findByF_WID(long groupId, long workingUnitId)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByF_WID(groupId, workingUnitId);

		if (workingUnit == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", workingUnitId=");
			msg.append(workingUnitId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchWorkingUnitException(msg.toString());
		}

		return workingUnit;
	}

	/**
	 * Returns the working unit where groupId = &#63; and workingUnitId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param workingUnitId the working unit ID
	 * @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByF_WID(long groupId, long workingUnitId) {
		return fetchByF_WID(groupId, workingUnitId, true);
	}

	/**
	 * Returns the working unit where groupId = &#63; and workingUnitId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param workingUnitId the working unit ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	 */
	@Override
	public WorkingUnit fetchByF_WID(long groupId, long workingUnitId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, workingUnitId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_WID,
					finderArgs, this);
		}

		if (result instanceof WorkingUnit) {
			WorkingUnit workingUnit = (WorkingUnit)result;

			if ((groupId != workingUnit.getGroupId()) ||
					(workingUnitId != workingUnit.getWorkingUnitId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WORKINGUNIT_WHERE);

			query.append(_FINDER_COLUMN_F_WID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_WID_WORKINGUNITID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(workingUnitId);

				List<WorkingUnit> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_WID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"WorkingUnitPersistenceImpl.fetchByF_WID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					WorkingUnit workingUnit = list.get(0);

					result = workingUnit;

					cacheResult(workingUnit);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_WID, finderArgs);

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
			return (WorkingUnit)result;
		}
	}

	/**
	 * Removes the working unit where groupId = &#63; and workingUnitId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param workingUnitId the working unit ID
	 * @return the working unit that was removed
	 */
	@Override
	public WorkingUnit removeByF_WID(long groupId, long workingUnitId)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = findByF_WID(groupId, workingUnitId);

		return remove(workingUnit);
	}

	/**
	 * Returns the number of working units where groupId = &#63; and workingUnitId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param workingUnitId the working unit ID
	 * @return the number of matching working units
	 */
	@Override
	public int countByF_WID(long groupId, long workingUnitId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_WID;

		Object[] finderArgs = new Object[] { groupId, workingUnitId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKINGUNIT_WHERE);

			query.append(_FINDER_COLUMN_F_WID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_WID_WORKINGUNITID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(workingUnitId);

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

	private static final String _FINDER_COLUMN_F_WID_GROUPID_2 = "workingUnit.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_WID_WORKINGUNITID_2 = "workingUnit.workingUnitId = ?";

	public WorkingUnitPersistenceImpl() {
		setModelClass(WorkingUnit.class);

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
	 * Caches the working unit in the entity cache if it is enabled.
	 *
	 * @param workingUnit the working unit
	 */
	@Override
	public void cacheResult(WorkingUnit workingUnit) {
		entityCache.putResult(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitImpl.class, workingUnit.getPrimaryKey(), workingUnit);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { workingUnit.getUuid(), workingUnit.getGroupId() },
			workingUnit);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GOVAGENCYCODE,
			new Object[] {
				workingUnit.getGroupId(), workingUnit.getGovAgencyCode()
			}, workingUnit);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_WID,
			new Object[] {
				workingUnit.getGroupId(), workingUnit.getWorkingUnitId()
			}, workingUnit);

		workingUnit.resetOriginalValues();
	}

	/**
	 * Caches the working units in the entity cache if it is enabled.
	 *
	 * @param workingUnits the working units
	 */
	@Override
	public void cacheResult(List<WorkingUnit> workingUnits) {
		for (WorkingUnit workingUnit : workingUnits) {
			if (entityCache.getResult(
						WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
						WorkingUnitImpl.class, workingUnit.getPrimaryKey()) == null) {
				cacheResult(workingUnit);
			}
			else {
				workingUnit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all working units.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WorkingUnitImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the working unit.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkingUnit workingUnit) {
		entityCache.removeResult(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitImpl.class, workingUnit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((WorkingUnitModelImpl)workingUnit, true);
	}

	@Override
	public void clearCache(List<WorkingUnit> workingUnits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkingUnit workingUnit : workingUnits) {
			entityCache.removeResult(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
				WorkingUnitImpl.class, workingUnit.getPrimaryKey());

			clearUniqueFindersCache((WorkingUnitModelImpl)workingUnit, true);
		}
	}

	protected void cacheUniqueFindersCache(
		WorkingUnitModelImpl workingUnitModelImpl) {
		Object[] args = new Object[] {
				workingUnitModelImpl.getUuid(),
				workingUnitModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			workingUnitModelImpl, false);

		args = new Object[] {
				workingUnitModelImpl.getGroupId(),
				workingUnitModelImpl.getGovAgencyCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GOVAGENCYCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GOVAGENCYCODE, args,
			workingUnitModelImpl, false);

		args = new Object[] {
				workingUnitModelImpl.getGroupId(),
				workingUnitModelImpl.getWorkingUnitId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_WID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_WID, args,
			workingUnitModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		WorkingUnitModelImpl workingUnitModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					workingUnitModelImpl.getUuid(),
					workingUnitModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((workingUnitModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					workingUnitModelImpl.getOriginalUuid(),
					workingUnitModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					workingUnitModelImpl.getGroupId(),
					workingUnitModelImpl.getGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GOVAGENCYCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GOVAGENCYCODE, args);
		}

		if ((workingUnitModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GOVAGENCYCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					workingUnitModelImpl.getOriginalGroupId(),
					workingUnitModelImpl.getOriginalGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GOVAGENCYCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GOVAGENCYCODE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					workingUnitModelImpl.getGroupId(),
					workingUnitModelImpl.getWorkingUnitId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_WID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_WID, args);
		}

		if ((workingUnitModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_WID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					workingUnitModelImpl.getOriginalGroupId(),
					workingUnitModelImpl.getOriginalWorkingUnitId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_WID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_WID, args);
		}
	}

	/**
	 * Creates a new working unit with the primary key. Does not add the working unit to the database.
	 *
	 * @param workingUnitId the primary key for the new working unit
	 * @return the new working unit
	 */
	@Override
	public WorkingUnit create(long workingUnitId) {
		WorkingUnit workingUnit = new WorkingUnitImpl();

		workingUnit.setNew(true);
		workingUnit.setPrimaryKey(workingUnitId);

		String uuid = PortalUUIDUtil.generate();

		workingUnit.setUuid(uuid);

		workingUnit.setCompanyId(companyProvider.getCompanyId());

		return workingUnit;
	}

	/**
	 * Removes the working unit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workingUnitId the primary key of the working unit
	 * @return the working unit that was removed
	 * @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit remove(long workingUnitId)
		throws NoSuchWorkingUnitException {
		return remove((Serializable)workingUnitId);
	}

	/**
	 * Removes the working unit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the working unit
	 * @return the working unit that was removed
	 * @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit remove(Serializable primaryKey)
		throws NoSuchWorkingUnitException {
		Session session = null;

		try {
			session = openSession();

			WorkingUnit workingUnit = (WorkingUnit)session.get(WorkingUnitImpl.class,
					primaryKey);

			if (workingUnit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkingUnitException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workingUnit);
		}
		catch (NoSuchWorkingUnitException nsee) {
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
	protected WorkingUnit removeImpl(WorkingUnit workingUnit) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workingUnit)) {
				workingUnit = (WorkingUnit)session.get(WorkingUnitImpl.class,
						workingUnit.getPrimaryKeyObj());
			}

			if (workingUnit != null) {
				session.delete(workingUnit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workingUnit != null) {
			clearCache(workingUnit);
		}

		return workingUnit;
	}

	@Override
	public WorkingUnit updateImpl(WorkingUnit workingUnit) {
		boolean isNew = workingUnit.isNew();

		if (!(workingUnit instanceof WorkingUnitModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(workingUnit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(workingUnit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in workingUnit proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WorkingUnit implementation " +
				workingUnit.getClass());
		}

		WorkingUnitModelImpl workingUnitModelImpl = (WorkingUnitModelImpl)workingUnit;

		if (Validator.isNull(workingUnit.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			workingUnit.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (workingUnit.getCreateDate() == null)) {
			if (serviceContext == null) {
				workingUnit.setCreateDate(now);
			}
			else {
				workingUnit.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!workingUnitModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				workingUnit.setModifiedDate(now);
			}
			else {
				workingUnit.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (workingUnit.isNew()) {
				session.save(workingUnit);

				workingUnit.setNew(false);
			}
			else {
				workingUnit = (WorkingUnit)session.merge(workingUnit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!WorkingUnitModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { workingUnitModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					workingUnitModelImpl.getUuid(),
					workingUnitModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					workingUnitModelImpl.getGroupId(),
					workingUnitModelImpl.getParentWorkingUnitId(),
					workingUnitModelImpl.getLevel()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTID_LEVEL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTID_LEVEL,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((workingUnitModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workingUnitModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { workingUnitModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((workingUnitModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workingUnitModelImpl.getOriginalUuid(),
						workingUnitModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						workingUnitModelImpl.getUuid(),
						workingUnitModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((workingUnitModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTID_LEVEL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workingUnitModelImpl.getOriginalGroupId(),
						workingUnitModelImpl.getOriginalParentWorkingUnitId(),
						workingUnitModelImpl.getOriginalLevel()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTID_LEVEL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTID_LEVEL,
					args);

				args = new Object[] {
						workingUnitModelImpl.getGroupId(),
						workingUnitModelImpl.getParentWorkingUnitId(),
						workingUnitModelImpl.getLevel()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_PARENTID_LEVEL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_PARENTID_LEVEL,
					args);
			}
		}

		entityCache.putResult(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
			WorkingUnitImpl.class, workingUnit.getPrimaryKey(), workingUnit,
			false);

		clearUniqueFindersCache(workingUnitModelImpl, false);
		cacheUniqueFindersCache(workingUnitModelImpl);

		workingUnit.resetOriginalValues();

		return workingUnit;
	}

	/**
	 * Returns the working unit with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the working unit
	 * @return the working unit
	 * @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkingUnitException {
		WorkingUnit workingUnit = fetchByPrimaryKey(primaryKey);

		if (workingUnit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkingUnitException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workingUnit;
	}

	/**
	 * Returns the working unit with the primary key or throws a {@link NoSuchWorkingUnitException} if it could not be found.
	 *
	 * @param workingUnitId the primary key of the working unit
	 * @return the working unit
	 * @throws NoSuchWorkingUnitException if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit findByPrimaryKey(long workingUnitId)
		throws NoSuchWorkingUnitException {
		return findByPrimaryKey((Serializable)workingUnitId);
	}

	/**
	 * Returns the working unit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the working unit
	 * @return the working unit, or <code>null</code> if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
				WorkingUnitImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WorkingUnit workingUnit = (WorkingUnit)serializable;

		if (workingUnit == null) {
			Session session = null;

			try {
				session = openSession();

				workingUnit = (WorkingUnit)session.get(WorkingUnitImpl.class,
						primaryKey);

				if (workingUnit != null) {
					cacheResult(workingUnit);
				}
				else {
					entityCache.putResult(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
						WorkingUnitImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
					WorkingUnitImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workingUnit;
	}

	/**
	 * Returns the working unit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workingUnitId the primary key of the working unit
	 * @return the working unit, or <code>null</code> if a working unit with the primary key could not be found
	 */
	@Override
	public WorkingUnit fetchByPrimaryKey(long workingUnitId) {
		return fetchByPrimaryKey((Serializable)workingUnitId);
	}

	@Override
	public Map<Serializable, WorkingUnit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WorkingUnit> map = new HashMap<Serializable, WorkingUnit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WorkingUnit workingUnit = fetchByPrimaryKey(primaryKey);

			if (workingUnit != null) {
				map.put(primaryKey, workingUnit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
					WorkingUnitImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WorkingUnit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WORKINGUNIT_WHERE_PKS_IN);

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

			for (WorkingUnit workingUnit : (List<WorkingUnit>)q.list()) {
				map.put(workingUnit.getPrimaryKeyObj(), workingUnit);

				cacheResult(workingUnit);

				uncachedPrimaryKeys.remove(workingUnit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WorkingUnitModelImpl.ENTITY_CACHE_ENABLED,
					WorkingUnitImpl.class, primaryKey, nullModel);
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
	 * Returns all the working units.
	 *
	 * @return the working units
	 */
	@Override
	public List<WorkingUnit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the working units.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @return the range of working units
	 */
	@Override
	public List<WorkingUnit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the working units.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of working units
	 */
	@Override
	public List<WorkingUnit> findAll(int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the working units.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of working units
	 * @param end the upper bound of the range of working units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of working units
	 */
	@Override
	public List<WorkingUnit> findAll(int start, int end,
		OrderByComparator<WorkingUnit> orderByComparator,
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

		List<WorkingUnit> list = null;

		if (retrieveFromCache) {
			list = (List<WorkingUnit>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WORKINGUNIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKINGUNIT;

				if (pagination) {
					sql = sql.concat(WorkingUnitModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkingUnit>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the working units from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WorkingUnit workingUnit : findAll()) {
			remove(workingUnit);
		}
	}

	/**
	 * Returns the number of working units.
	 *
	 * @return the number of working units
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORKINGUNIT);

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
		return WorkingUnitModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the working unit persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WorkingUnitImpl.class.getName());
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
	private static final String _SQL_SELECT_WORKINGUNIT = "SELECT workingUnit FROM WorkingUnit workingUnit";
	private static final String _SQL_SELECT_WORKINGUNIT_WHERE_PKS_IN = "SELECT workingUnit FROM WorkingUnit workingUnit WHERE workingUnitId IN (";
	private static final String _SQL_SELECT_WORKINGUNIT_WHERE = "SELECT workingUnit FROM WorkingUnit workingUnit WHERE ";
	private static final String _SQL_COUNT_WORKINGUNIT = "SELECT COUNT(workingUnit) FROM WorkingUnit workingUnit";
	private static final String _SQL_COUNT_WORKINGUNIT_WHERE = "SELECT COUNT(workingUnit) FROM WorkingUnit workingUnit WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workingUnit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkingUnit exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WorkingUnit exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(WorkingUnitPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}