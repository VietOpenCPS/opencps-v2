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

import org.opencps.usermgt.exception.NoSuchEmployeeJobPosException;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.impl.EmployeeJobPosImpl;
import org.opencps.usermgt.model.impl.EmployeeJobPosModelImpl;
import org.opencps.usermgt.service.persistence.EmployeeJobPosPersistence;

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
 * The persistence implementation for the employee job pos service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see EmployeeJobPosPersistence
 * @see org.opencps.usermgt.service.persistence.EmployeeJobPosUtil
 * @generated
 */
@ProviderType
public class EmployeeJobPosPersistenceImpl extends BasePersistenceImpl<EmployeeJobPos>
	implements EmployeeJobPosPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EmployeeJobPosUtil} to access the employee job pos persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EmployeeJobPosImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			EmployeeJobPosModelImpl.UUID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the employee job poses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employee job poses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @return the range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employee job poses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByUuid(String uuid, int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employee job poses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByUuid(String uuid, int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator,
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

		List<EmployeeJobPos> list = null;

		if (retrieveFromCache) {
			list = (List<EmployeeJobPos>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EmployeeJobPos employeeJobPos : list) {
					if (!Objects.equals(uuid, employeeJobPos.getUuid())) {
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

			query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

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
				query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
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
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first employee job pos in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByUuid_First(String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByUuid_First(uuid,
				orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the first employee job pos in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByUuid_First(String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		List<EmployeeJobPos> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee job pos in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByUuid_Last(String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByUuid_Last(uuid, orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the last employee job pos in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByUuid_Last(String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<EmployeeJobPos> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employee job poses before and after the current employee job pos in the ordered set where uuid = &#63;.
	 *
	 * @param employeeJobPosId the primary key of the current employee job pos
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee job pos
	 * @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos[] findByUuid_PrevAndNext(long employeeJobPosId,
		String uuid, OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = findByPrimaryKey(employeeJobPosId);

		Session session = null;

		try {
			session = openSession();

			EmployeeJobPos[] array = new EmployeeJobPosImpl[3];

			array[0] = getByUuid_PrevAndNext(session, employeeJobPos, uuid,
					orderByComparator, true);

			array[1] = employeeJobPos;

			array[2] = getByUuid_PrevAndNext(session, employeeJobPos, uuid,
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

	protected EmployeeJobPos getByUuid_PrevAndNext(Session session,
		EmployeeJobPos employeeJobPos, String uuid,
		OrderByComparator<EmployeeJobPos> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

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
			query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(employeeJobPos);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EmployeeJobPos> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employee job poses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (EmployeeJobPos employeeJobPos : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(employeeJobPos);
		}
	}

	/**
	 * Returns the number of employee job poses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching employee job poses
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EMPLOYEEJOBPOS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "employeeJobPos.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "employeeJobPos.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(employeeJobPos.uuid IS NULL OR employeeJobPos.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			EmployeeJobPosModelImpl.UUID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the employee job pos where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEmployeeJobPosException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByUUID_G(String uuid, long groupId)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByUUID_G(uuid, groupId);

		if (employeeJobPos == null) {
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

			throw new NoSuchEmployeeJobPosException(msg.toString());
		}

		return employeeJobPos;
	}

	/**
	 * Returns the employee job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the employee job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof EmployeeJobPos) {
			EmployeeJobPos employeeJobPos = (EmployeeJobPos)result;

			if (!Objects.equals(uuid, employeeJobPos.getUuid()) ||
					(groupId != employeeJobPos.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

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

				List<EmployeeJobPos> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					EmployeeJobPos employeeJobPos = list.get(0);

					result = employeeJobPos;

					cacheResult(employeeJobPos);
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
			return (EmployeeJobPos)result;
		}
	}

	/**
	 * Removes the employee job pos where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the employee job pos that was removed
	 */
	@Override
	public EmployeeJobPos removeByUUID_G(String uuid, long groupId)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = findByUUID_G(uuid, groupId);

		return remove(employeeJobPos);
	}

	/**
	 * Returns the number of employee job poses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching employee job poses
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEEJOBPOS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "employeeJobPos.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "employeeJobPos.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(employeeJobPos.uuid IS NULL OR employeeJobPos.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "employeeJobPos.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			EmployeeJobPosModelImpl.UUID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.COMPANYID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the employee job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employee job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @return the range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employee job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<EmployeeJobPos> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employee job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator,
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

		List<EmployeeJobPos> list = null;

		if (retrieveFromCache) {
			list = (List<EmployeeJobPos>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EmployeeJobPos employeeJobPos : list) {
					if (!Objects.equals(uuid, employeeJobPos.getUuid()) ||
							(companyId != employeeJobPos.getCompanyId())) {
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

			query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

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
				query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
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
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the first employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		List<EmployeeJobPos> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the last employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<EmployeeJobPos> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employee job poses before and after the current employee job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param employeeJobPosId the primary key of the current employee job pos
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee job pos
	 * @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos[] findByUuid_C_PrevAndNext(long employeeJobPosId,
		String uuid, long companyId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = findByPrimaryKey(employeeJobPosId);

		Session session = null;

		try {
			session = openSession();

			EmployeeJobPos[] array = new EmployeeJobPosImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, employeeJobPos, uuid,
					companyId, orderByComparator, true);

			array[1] = employeeJobPos;

			array[2] = getByUuid_C_PrevAndNext(session, employeeJobPos, uuid,
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

	protected EmployeeJobPos getByUuid_C_PrevAndNext(Session session,
		EmployeeJobPos employeeJobPos, String uuid, long companyId,
		OrderByComparator<EmployeeJobPos> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

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
			query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(employeeJobPos);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EmployeeJobPos> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employee job poses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (EmployeeJobPos employeeJobPos : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(employeeJobPos);
		}
	}

	/**
	 * Returns the number of employee job poses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching employee job poses
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEEJOBPOS_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "employeeJobPos.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "employeeJobPos.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(employeeJobPos.uuid IS NULL OR employeeJobPos.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "employeeJobPos.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_EMPLOYEEID =
		new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_EmployeeId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEEID =
		new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_EmployeeId",
			new String[] { Long.class.getName() },
			EmployeeJobPosModelImpl.EMPLOYEEID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_EMPLOYEEID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_EmployeeId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the employee job poses where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_EmployeeId(long employeeId) {
		return findByF_EmployeeId(employeeId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employee job poses where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @return the range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_EmployeeId(long employeeId, int start,
		int end) {
		return findByF_EmployeeId(employeeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employee job poses where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_EmployeeId(long employeeId, int start,
		int end, OrderByComparator<EmployeeJobPos> orderByComparator) {
		return findByF_EmployeeId(employeeId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the employee job poses where employeeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param employeeId the employee ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_EmployeeId(long employeeId, int start,
		int end, OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEEID;
			finderArgs = new Object[] { employeeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_EMPLOYEEID;
			finderArgs = new Object[] { employeeId, start, end, orderByComparator };
		}

		List<EmployeeJobPos> list = null;

		if (retrieveFromCache) {
			list = (List<EmployeeJobPos>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EmployeeJobPos employeeJobPos : list) {
					if ((employeeId != employeeJobPos.getEmployeeId())) {
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

			query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_EMPLOYEEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(employeeId);

				if (!pagination) {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first employee job pos in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByF_EmployeeId_First(long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByF_EmployeeId_First(employeeId,
				orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("employeeId=");
		msg.append(employeeId);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the first employee job pos in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_EmployeeId_First(long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		List<EmployeeJobPos> list = findByF_EmployeeId(employeeId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee job pos in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByF_EmployeeId_Last(long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByF_EmployeeId_Last(employeeId,
				orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("employeeId=");
		msg.append(employeeId);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the last employee job pos in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_EmployeeId_Last(long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		int count = countByF_EmployeeId(employeeId);

		if (count == 0) {
			return null;
		}

		List<EmployeeJobPos> list = findByF_EmployeeId(employeeId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employee job poses before and after the current employee job pos in the ordered set where employeeId = &#63;.
	 *
	 * @param employeeJobPosId the primary key of the current employee job pos
	 * @param employeeId the employee ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee job pos
	 * @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos[] findByF_EmployeeId_PrevAndNext(
		long employeeJobPosId, long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = findByPrimaryKey(employeeJobPosId);

		Session session = null;

		try {
			session = openSession();

			EmployeeJobPos[] array = new EmployeeJobPosImpl[3];

			array[0] = getByF_EmployeeId_PrevAndNext(session, employeeJobPos,
					employeeId, orderByComparator, true);

			array[1] = employeeJobPos;

			array[2] = getByF_EmployeeId_PrevAndNext(session, employeeJobPos,
					employeeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EmployeeJobPos getByF_EmployeeId_PrevAndNext(Session session,
		EmployeeJobPos employeeJobPos, long employeeId,
		OrderByComparator<EmployeeJobPos> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

		query.append(_FINDER_COLUMN_F_EMPLOYEEID_EMPLOYEEID_2);

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
			query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(employeeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(employeeJobPos);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EmployeeJobPos> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employee job poses where employeeId = &#63; from the database.
	 *
	 * @param employeeId the employee ID
	 */
	@Override
	public void removeByF_EmployeeId(long employeeId) {
		for (EmployeeJobPos employeeJobPos : findByF_EmployeeId(employeeId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(employeeJobPos);
		}
	}

	/**
	 * Returns the number of employee job poses where employeeId = &#63;.
	 *
	 * @param employeeId the employee ID
	 * @return the number of matching employee job poses
	 */
	@Override
	public int countByF_EmployeeId(long employeeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_EMPLOYEEID;

		Object[] finderArgs = new Object[] { employeeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_EMPLOYEEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(employeeId);

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

	private static final String _FINDER_COLUMN_F_EMPLOYEEID_EMPLOYEEID_2 = "employeeJobPos.employeeId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_WORKINGUNITID =
		new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_workingUnitId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKINGUNITID =
		new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_workingUnitId",
			new String[] { Long.class.getName() },
			EmployeeJobPosModelImpl.WORKINGUNITID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_WORKINGUNITID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_workingUnitId", new String[] { Long.class.getName() });

	/**
	 * Returns all the employee job poses where workingUnitId = &#63;.
	 *
	 * @param workingUnitId the working unit ID
	 * @return the matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_workingUnitId(long workingUnitId) {
		return findByF_workingUnitId(workingUnitId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employee job poses where workingUnitId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workingUnitId the working unit ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @return the range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_workingUnitId(long workingUnitId,
		int start, int end) {
		return findByF_workingUnitId(workingUnitId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employee job poses where workingUnitId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workingUnitId the working unit ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_workingUnitId(long workingUnitId,
		int start, int end, OrderByComparator<EmployeeJobPos> orderByComparator) {
		return findByF_workingUnitId(workingUnitId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employee job poses where workingUnitId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workingUnitId the working unit ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_workingUnitId(long workingUnitId,
		int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKINGUNITID;
			finderArgs = new Object[] { workingUnitId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_WORKINGUNITID;
			finderArgs = new Object[] {
					workingUnitId,
					
					start, end, orderByComparator
				};
		}

		List<EmployeeJobPos> list = null;

		if (retrieveFromCache) {
			list = (List<EmployeeJobPos>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EmployeeJobPos employeeJobPos : list) {
					if ((workingUnitId != employeeJobPos.getWorkingUnitId())) {
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

			query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_WORKINGUNITID_WORKINGUNITID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workingUnitId);

				if (!pagination) {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first employee job pos in the ordered set where workingUnitId = &#63;.
	 *
	 * @param workingUnitId the working unit ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByF_workingUnitId_First(long workingUnitId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByF_workingUnitId_First(workingUnitId,
				orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("workingUnitId=");
		msg.append(workingUnitId);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the first employee job pos in the ordered set where workingUnitId = &#63;.
	 *
	 * @param workingUnitId the working unit ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_workingUnitId_First(long workingUnitId,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		List<EmployeeJobPos> list = findByF_workingUnitId(workingUnitId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee job pos in the ordered set where workingUnitId = &#63;.
	 *
	 * @param workingUnitId the working unit ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByF_workingUnitId_Last(long workingUnitId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByF_workingUnitId_Last(workingUnitId,
				orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("workingUnitId=");
		msg.append(workingUnitId);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the last employee job pos in the ordered set where workingUnitId = &#63;.
	 *
	 * @param workingUnitId the working unit ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_workingUnitId_Last(long workingUnitId,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		int count = countByF_workingUnitId(workingUnitId);

		if (count == 0) {
			return null;
		}

		List<EmployeeJobPos> list = findByF_workingUnitId(workingUnitId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employee job poses before and after the current employee job pos in the ordered set where workingUnitId = &#63;.
	 *
	 * @param employeeJobPosId the primary key of the current employee job pos
	 * @param workingUnitId the working unit ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee job pos
	 * @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos[] findByF_workingUnitId_PrevAndNext(
		long employeeJobPosId, long workingUnitId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = findByPrimaryKey(employeeJobPosId);

		Session session = null;

		try {
			session = openSession();

			EmployeeJobPos[] array = new EmployeeJobPosImpl[3];

			array[0] = getByF_workingUnitId_PrevAndNext(session,
					employeeJobPos, workingUnitId, orderByComparator, true);

			array[1] = employeeJobPos;

			array[2] = getByF_workingUnitId_PrevAndNext(session,
					employeeJobPos, workingUnitId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EmployeeJobPos getByF_workingUnitId_PrevAndNext(Session session,
		EmployeeJobPos employeeJobPos, long workingUnitId,
		OrderByComparator<EmployeeJobPos> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

		query.append(_FINDER_COLUMN_F_WORKINGUNITID_WORKINGUNITID_2);

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
			query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(workingUnitId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(employeeJobPos);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EmployeeJobPos> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employee job poses where workingUnitId = &#63; from the database.
	 *
	 * @param workingUnitId the working unit ID
	 */
	@Override
	public void removeByF_workingUnitId(long workingUnitId) {
		for (EmployeeJobPos employeeJobPos : findByF_workingUnitId(
				workingUnitId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(employeeJobPos);
		}
	}

	/**
	 * Returns the number of employee job poses where workingUnitId = &#63;.
	 *
	 * @param workingUnitId the working unit ID
	 * @return the number of matching employee job poses
	 */
	@Override
	public int countByF_workingUnitId(long workingUnitId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_WORKINGUNITID;

		Object[] finderArgs = new Object[] { workingUnitId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_WORKINGUNITID_WORKINGUNITID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_F_WORKINGUNITID_WORKINGUNITID_2 = "employeeJobPos.workingUnitId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_EmployeeId_jobPostId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			EmployeeJobPosModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.EMPLOYEEID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.JOBPOSTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_EmployeeId_jobPostId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the employee job pos where groupId = &#63; and employeeId = &#63; and jobPostId = &#63; or throws a {@link NoSuchEmployeeJobPosException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @return the matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByF_EmployeeId_jobPostId(long groupId,
		long employeeId, long jobPostId) throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByF_EmployeeId_jobPostId(groupId,
				employeeId, jobPostId);

		if (employeeJobPos == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", employeeId=");
			msg.append(employeeId);

			msg.append(", jobPostId=");
			msg.append(jobPostId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEmployeeJobPosException(msg.toString());
		}

		return employeeJobPos;
	}

	/**
	 * Returns the employee job pos where groupId = &#63; and employeeId = &#63; and jobPostId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_EmployeeId_jobPostId(long groupId,
		long employeeId, long jobPostId) {
		return fetchByF_EmployeeId_jobPostId(groupId, employeeId, jobPostId,
			true);
	}

	/**
	 * Returns the employee job pos where groupId = &#63; and employeeId = &#63; and jobPostId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_EmployeeId_jobPostId(long groupId,
		long employeeId, long jobPostId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, employeeId, jobPostId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID,
					finderArgs, this);
		}

		if (result instanceof EmployeeJobPos) {
			EmployeeJobPos employeeJobPos = (EmployeeJobPos)result;

			if ((groupId != employeeJobPos.getGroupId()) ||
					(employeeId != employeeJobPos.getEmployeeId()) ||
					(jobPostId != employeeJobPos.getJobPostId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_EMPLOYEEID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_JOBPOSTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(employeeId);

				qPos.add(jobPostId);

				List<EmployeeJobPos> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"EmployeeJobPosPersistenceImpl.fetchByF_EmployeeId_jobPostId(long, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EmployeeJobPos employeeJobPos = list.get(0);

					result = employeeJobPos;

					cacheResult(employeeJobPos);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID,
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
			return (EmployeeJobPos)result;
		}
	}

	/**
	 * Removes the employee job pos where groupId = &#63; and employeeId = &#63; and jobPostId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @return the employee job pos that was removed
	 */
	@Override
	public EmployeeJobPos removeByF_EmployeeId_jobPostId(long groupId,
		long employeeId, long jobPostId) throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = findByF_EmployeeId_jobPostId(groupId,
				employeeId, jobPostId);

		return remove(employeeJobPos);
	}

	/**
	 * Returns the number of employee job poses where groupId = &#63; and employeeId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @return the number of matching employee job poses
	 */
	@Override
	public int countByF_EmployeeId_jobPostId(long groupId, long employeeId,
		long jobPostId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID;

		Object[] finderArgs = new Object[] { groupId, employeeId, jobPostId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_EMPLOYEEID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_JOBPOSTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(employeeId);

				qPos.add(jobPostId);

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

	private static final String _FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_GROUPID_2 = "employeeJobPos.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_EMPLOYEEID_2 =
		"employeeJobPos.employeeId = ? AND ";
	private static final String _FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_JOBPOSTID_2 =
		"employeeJobPos.jobPostId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID =
		new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_EmployeeId_jobPostId_workingUnitId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			EmployeeJobPosModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.EMPLOYEEID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.JOBPOSTID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.WORKINGUNITID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID =
		new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_EmployeeId_jobPostId_workingUnitId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the employee job pos where groupId = &#63; and employeeId = &#63; and jobPostId = &#63; and workingUnitId = &#63; or throws a {@link NoSuchEmployeeJobPosException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @param workingUnitId the working unit ID
	 * @return the matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByF_EmployeeId_jobPostId_workingUnitId(
		long groupId, long employeeId, long jobPostId, long workingUnitId)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByF_EmployeeId_jobPostId_workingUnitId(groupId,
				employeeId, jobPostId, workingUnitId);

		if (employeeJobPos == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", employeeId=");
			msg.append(employeeId);

			msg.append(", jobPostId=");
			msg.append(jobPostId);

			msg.append(", workingUnitId=");
			msg.append(workingUnitId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEmployeeJobPosException(msg.toString());
		}

		return employeeJobPos;
	}

	/**
	 * Returns the employee job pos where groupId = &#63; and employeeId = &#63; and jobPostId = &#63; and workingUnitId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @param workingUnitId the working unit ID
	 * @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_EmployeeId_jobPostId_workingUnitId(
		long groupId, long employeeId, long jobPostId, long workingUnitId) {
		return fetchByF_EmployeeId_jobPostId_workingUnitId(groupId, employeeId,
			jobPostId, workingUnitId, true);
	}

	/**
	 * Returns the employee job pos where groupId = &#63; and employeeId = &#63; and jobPostId = &#63; and workingUnitId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @param workingUnitId the working unit ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_EmployeeId_jobPostId_workingUnitId(
		long groupId, long employeeId, long jobPostId, long workingUnitId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, employeeId, jobPostId, workingUnitId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
					finderArgs, this);
		}

		if (result instanceof EmployeeJobPos) {
			EmployeeJobPos employeeJobPos = (EmployeeJobPos)result;

			if ((groupId != employeeJobPos.getGroupId()) ||
					(employeeId != employeeJobPos.getEmployeeId()) ||
					(jobPostId != employeeJobPos.getJobPostId()) ||
					(workingUnitId != employeeJobPos.getWorkingUnitId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_EMPLOYEEID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_JOBPOSTID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_WORKINGUNITID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(employeeId);

				qPos.add(jobPostId);

				qPos.add(workingUnitId);

				List<EmployeeJobPos> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"EmployeeJobPosPersistenceImpl.fetchByF_EmployeeId_jobPostId_workingUnitId(long, long, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EmployeeJobPos employeeJobPos = list.get(0);

					result = employeeJobPos;

					cacheResult(employeeJobPos);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
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
			return (EmployeeJobPos)result;
		}
	}

	/**
	 * Removes the employee job pos where groupId = &#63; and employeeId = &#63; and jobPostId = &#63; and workingUnitId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @param workingUnitId the working unit ID
	 * @return the employee job pos that was removed
	 */
	@Override
	public EmployeeJobPos removeByF_EmployeeId_jobPostId_workingUnitId(
		long groupId, long employeeId, long jobPostId, long workingUnitId)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = findByF_EmployeeId_jobPostId_workingUnitId(groupId,
				employeeId, jobPostId, workingUnitId);

		return remove(employeeJobPos);
	}

	/**
	 * Returns the number of employee job poses where groupId = &#63; and employeeId = &#63; and jobPostId = &#63; and workingUnitId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param jobPostId the job post ID
	 * @param workingUnitId the working unit ID
	 * @return the number of matching employee job poses
	 */
	@Override
	public int countByF_EmployeeId_jobPostId_workingUnitId(long groupId,
		long employeeId, long jobPostId, long workingUnitId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID;

		Object[] finderArgs = new Object[] {
				groupId, employeeId, jobPostId, workingUnitId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_EMPLOYEEID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_JOBPOSTID_2);

			query.append(_FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_WORKINGUNITID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(employeeId);

				qPos.add(jobPostId);

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

	private static final String _FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_GROUPID_2 =
		"employeeJobPos.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_EMPLOYEEID_2 =
		"employeeJobPos.employeeId = ? AND ";
	private static final String _FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_JOBPOSTID_2 =
		"employeeJobPos.jobPostId = ? AND ";
	private static final String _FINDER_COLUMN_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID_WORKINGUNITID_2 =
		"employeeJobPos.workingUnitId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_EMPLOYEEID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_EmployeeId",
			new String[] { Long.class.getName(), Long.class.getName() },
			EmployeeJobPosModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.EMPLOYEEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_EMPLOYEEID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_EmployeeId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the employee job pos where groupId = &#63; and employeeId = &#63; or throws a {@link NoSuchEmployeeJobPosException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @return the matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByG_EmployeeId(long groupId, long employeeId)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByG_EmployeeId(groupId, employeeId);

		if (employeeJobPos == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", employeeId=");
			msg.append(employeeId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEmployeeJobPosException(msg.toString());
		}

		return employeeJobPos;
	}

	/**
	 * Returns the employee job pos where groupId = &#63; and employeeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByG_EmployeeId(long groupId, long employeeId) {
		return fetchByG_EmployeeId(groupId, employeeId, true);
	}

	/**
	 * Returns the employee job pos where groupId = &#63; and employeeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByG_EmployeeId(long groupId, long employeeId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, employeeId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_EMPLOYEEID,
					finderArgs, this);
		}

		if (result instanceof EmployeeJobPos) {
			EmployeeJobPos employeeJobPos = (EmployeeJobPos)result;

			if ((groupId != employeeJobPos.getGroupId()) ||
					(employeeId != employeeJobPos.getEmployeeId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_G_EMPLOYEEID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_EMPLOYEEID_EMPLOYEEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(employeeId);

				List<EmployeeJobPos> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_EMPLOYEEID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"EmployeeJobPosPersistenceImpl.fetchByG_EmployeeId(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EmployeeJobPos employeeJobPos = list.get(0);

					result = employeeJobPos;

					cacheResult(employeeJobPos);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_EMPLOYEEID,
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
			return (EmployeeJobPos)result;
		}
	}

	/**
	 * Removes the employee job pos where groupId = &#63; and employeeId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @return the employee job pos that was removed
	 */
	@Override
	public EmployeeJobPos removeByG_EmployeeId(long groupId, long employeeId)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = findByG_EmployeeId(groupId, employeeId);

		return remove(employeeJobPos);
	}

	/**
	 * Returns the number of employee job poses where groupId = &#63; and employeeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @return the number of matching employee job poses
	 */
	@Override
	public int countByG_EmployeeId(long groupId, long employeeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_EMPLOYEEID;

		Object[] finderArgs = new Object[] { groupId, employeeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_G_EMPLOYEEID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_EMPLOYEEID_EMPLOYEEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(employeeId);

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

	private static final String _FINDER_COLUMN_G_EMPLOYEEID_GROUPID_2 = "employeeJobPos.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_EMPLOYEEID_EMPLOYEEID_2 = "employeeJobPos.employeeId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_G_JOBPOSTID =
		new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_G_jobPostId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_JOBPOSTID =
		new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED,
			EmployeeJobPosImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_G_jobPostId",
			new String[] { Long.class.getName(), Long.class.getName() },
			EmployeeJobPosModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.JOBPOSTID_COLUMN_BITMASK |
			EmployeeJobPosModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_G_JOBPOSTID = new FinderPath(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_G_jobPostId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the employee job poses where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @return the matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_G_jobPostId(long groupId, long jobPostId) {
		return findByF_G_jobPostId(groupId, jobPostId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employee job poses where groupId = &#63; and jobPostId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @return the range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_G_jobPostId(long groupId,
		long jobPostId, int start, int end) {
		return findByF_G_jobPostId(groupId, jobPostId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employee job poses where groupId = &#63; and jobPostId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_G_jobPostId(long groupId,
		long jobPostId, int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return findByF_G_jobPostId(groupId, jobPostId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employee job poses where groupId = &#63; and jobPostId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findByF_G_jobPostId(long groupId,
		long jobPostId, int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_JOBPOSTID;
			finderArgs = new Object[] { groupId, jobPostId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_G_JOBPOSTID;
			finderArgs = new Object[] {
					groupId, jobPostId,
					
					start, end, orderByComparator
				};
		}

		List<EmployeeJobPos> list = null;

		if (retrieveFromCache) {
			list = (List<EmployeeJobPos>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EmployeeJobPos employeeJobPos : list) {
					if ((groupId != employeeJobPos.getGroupId()) ||
							(jobPostId != employeeJobPos.getJobPostId())) {
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

			query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_G_JOBPOSTID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_G_JOBPOSTID_JOBPOSTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(jobPostId);

				if (!pagination) {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first employee job pos in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByF_G_jobPostId_First(long groupId,
		long jobPostId, OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByF_G_jobPostId_First(groupId,
				jobPostId, orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", jobPostId=");
		msg.append(jobPostId);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the first employee job pos in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_G_jobPostId_First(long groupId,
		long jobPostId, OrderByComparator<EmployeeJobPos> orderByComparator) {
		List<EmployeeJobPos> list = findByF_G_jobPostId(groupId, jobPostId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee job pos in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos
	 * @throws NoSuchEmployeeJobPosException if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos findByF_G_jobPostId_Last(long groupId,
		long jobPostId, OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByF_G_jobPostId_Last(groupId,
				jobPostId, orderByComparator);

		if (employeeJobPos != null) {
			return employeeJobPos;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", jobPostId=");
		msg.append(jobPostId);

		msg.append("}");

		throw new NoSuchEmployeeJobPosException(msg.toString());
	}

	/**
	 * Returns the last employee job pos in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	 */
	@Override
	public EmployeeJobPos fetchByF_G_jobPostId_Last(long groupId,
		long jobPostId, OrderByComparator<EmployeeJobPos> orderByComparator) {
		int count = countByF_G_jobPostId(groupId, jobPostId);

		if (count == 0) {
			return null;
		}

		List<EmployeeJobPos> list = findByF_G_jobPostId(groupId, jobPostId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employee job poses before and after the current employee job pos in the ordered set where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param employeeJobPosId the primary key of the current employee job pos
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee job pos
	 * @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos[] findByF_G_jobPostId_PrevAndNext(
		long employeeJobPosId, long groupId, long jobPostId,
		OrderByComparator<EmployeeJobPos> orderByComparator)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = findByPrimaryKey(employeeJobPosId);

		Session session = null;

		try {
			session = openSession();

			EmployeeJobPos[] array = new EmployeeJobPosImpl[3];

			array[0] = getByF_G_jobPostId_PrevAndNext(session, employeeJobPos,
					groupId, jobPostId, orderByComparator, true);

			array[1] = employeeJobPos;

			array[2] = getByF_G_jobPostId_PrevAndNext(session, employeeJobPos,
					groupId, jobPostId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EmployeeJobPos getByF_G_jobPostId_PrevAndNext(Session session,
		EmployeeJobPos employeeJobPos, long groupId, long jobPostId,
		OrderByComparator<EmployeeJobPos> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE);

		query.append(_FINDER_COLUMN_F_G_JOBPOSTID_GROUPID_2);

		query.append(_FINDER_COLUMN_F_G_JOBPOSTID_JOBPOSTID_2);

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
			query.append(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(jobPostId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(employeeJobPos);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EmployeeJobPos> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employee job poses where groupId = &#63; and jobPostId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 */
	@Override
	public void removeByF_G_jobPostId(long groupId, long jobPostId) {
		for (EmployeeJobPos employeeJobPos : findByF_G_jobPostId(groupId,
				jobPostId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(employeeJobPos);
		}
	}

	/**
	 * Returns the number of employee job poses where groupId = &#63; and jobPostId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param jobPostId the job post ID
	 * @return the number of matching employee job poses
	 */
	@Override
	public int countByF_G_jobPostId(long groupId, long jobPostId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_G_JOBPOSTID;

		Object[] finderArgs = new Object[] { groupId, jobPostId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEEJOBPOS_WHERE);

			query.append(_FINDER_COLUMN_F_G_JOBPOSTID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_G_JOBPOSTID_JOBPOSTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(jobPostId);

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

	private static final String _FINDER_COLUMN_F_G_JOBPOSTID_GROUPID_2 = "employeeJobPos.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_G_JOBPOSTID_JOBPOSTID_2 = "employeeJobPos.jobPostId = ?";

	public EmployeeJobPosPersistenceImpl() {
		setModelClass(EmployeeJobPos.class);

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
	 * Caches the employee job pos in the entity cache if it is enabled.
	 *
	 * @param employeeJobPos the employee job pos
	 */
	@Override
	public void cacheResult(EmployeeJobPos employeeJobPos) {
		entityCache.putResult(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosImpl.class, employeeJobPos.getPrimaryKey(),
			employeeJobPos);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { employeeJobPos.getUuid(), employeeJobPos.getGroupId() },
			employeeJobPos);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID,
			new Object[] {
				employeeJobPos.getGroupId(), employeeJobPos.getEmployeeId(),
				employeeJobPos.getJobPostId()
			}, employeeJobPos);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
			new Object[] {
				employeeJobPos.getGroupId(), employeeJobPos.getEmployeeId(),
				employeeJobPos.getJobPostId(), employeeJobPos.getWorkingUnitId()
			}, employeeJobPos);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_EMPLOYEEID,
			new Object[] {
				employeeJobPos.getGroupId(), employeeJobPos.getEmployeeId()
			}, employeeJobPos);

		employeeJobPos.resetOriginalValues();
	}

	/**
	 * Caches the employee job poses in the entity cache if it is enabled.
	 *
	 * @param employeeJobPoses the employee job poses
	 */
	@Override
	public void cacheResult(List<EmployeeJobPos> employeeJobPoses) {
		for (EmployeeJobPos employeeJobPos : employeeJobPoses) {
			if (entityCache.getResult(
						EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
						EmployeeJobPosImpl.class, employeeJobPos.getPrimaryKey()) == null) {
				cacheResult(employeeJobPos);
			}
			else {
				employeeJobPos.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all employee job poses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EmployeeJobPosImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the employee job pos.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EmployeeJobPos employeeJobPos) {
		entityCache.removeResult(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosImpl.class, employeeJobPos.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((EmployeeJobPosModelImpl)employeeJobPos, true);
	}

	@Override
	public void clearCache(List<EmployeeJobPos> employeeJobPoses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EmployeeJobPos employeeJobPos : employeeJobPoses) {
			entityCache.removeResult(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
				EmployeeJobPosImpl.class, employeeJobPos.getPrimaryKey());

			clearUniqueFindersCache((EmployeeJobPosModelImpl)employeeJobPos,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		EmployeeJobPosModelImpl employeeJobPosModelImpl) {
		Object[] args = new Object[] {
				employeeJobPosModelImpl.getUuid(),
				employeeJobPosModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			employeeJobPosModelImpl, false);

		args = new Object[] {
				employeeJobPosModelImpl.getGroupId(),
				employeeJobPosModelImpl.getEmployeeId(),
				employeeJobPosModelImpl.getJobPostId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID,
			args, employeeJobPosModelImpl, false);

		args = new Object[] {
				employeeJobPosModelImpl.getGroupId(),
				employeeJobPosModelImpl.getEmployeeId(),
				employeeJobPosModelImpl.getJobPostId(),
				employeeJobPosModelImpl.getWorkingUnitId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
			args, employeeJobPosModelImpl, false);

		args = new Object[] {
				employeeJobPosModelImpl.getGroupId(),
				employeeJobPosModelImpl.getEmployeeId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_EMPLOYEEID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_EMPLOYEEID, args,
			employeeJobPosModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		EmployeeJobPosModelImpl employeeJobPosModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					employeeJobPosModelImpl.getUuid(),
					employeeJobPosModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((employeeJobPosModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					employeeJobPosModelImpl.getOriginalUuid(),
					employeeJobPosModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					employeeJobPosModelImpl.getGroupId(),
					employeeJobPosModelImpl.getEmployeeId(),
					employeeJobPosModelImpl.getJobPostId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID,
				args);
		}

		if ((employeeJobPosModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					employeeJobPosModelImpl.getOriginalGroupId(),
					employeeJobPosModelImpl.getOriginalEmployeeId(),
					employeeJobPosModelImpl.getOriginalJobPostId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					employeeJobPosModelImpl.getGroupId(),
					employeeJobPosModelImpl.getEmployeeId(),
					employeeJobPosModelImpl.getJobPostId(),
					employeeJobPosModelImpl.getWorkingUnitId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
				args);
		}

		if ((employeeJobPosModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					employeeJobPosModelImpl.getOriginalGroupId(),
					employeeJobPosModelImpl.getOriginalEmployeeId(),
					employeeJobPosModelImpl.getOriginalJobPostId(),
					employeeJobPosModelImpl.getOriginalWorkingUnitId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_EMPLOYEEID_JOBPOSTID_WORKINGUNITID,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					employeeJobPosModelImpl.getGroupId(),
					employeeJobPosModelImpl.getEmployeeId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_EMPLOYEEID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_EMPLOYEEID, args);
		}

		if ((employeeJobPosModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_EMPLOYEEID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					employeeJobPosModelImpl.getOriginalGroupId(),
					employeeJobPosModelImpl.getOriginalEmployeeId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_EMPLOYEEID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_EMPLOYEEID, args);
		}
	}

	/**
	 * Creates a new employee job pos with the primary key. Does not add the employee job pos to the database.
	 *
	 * @param employeeJobPosId the primary key for the new employee job pos
	 * @return the new employee job pos
	 */
	@Override
	public EmployeeJobPos create(long employeeJobPosId) {
		EmployeeJobPos employeeJobPos = new EmployeeJobPosImpl();

		employeeJobPos.setNew(true);
		employeeJobPos.setPrimaryKey(employeeJobPosId);

		String uuid = PortalUUIDUtil.generate();

		employeeJobPos.setUuid(uuid);

		employeeJobPos.setCompanyId(companyProvider.getCompanyId());

		return employeeJobPos;
	}

	/**
	 * Removes the employee job pos with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param employeeJobPosId the primary key of the employee job pos
	 * @return the employee job pos that was removed
	 * @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos remove(long employeeJobPosId)
		throws NoSuchEmployeeJobPosException {
		return remove((Serializable)employeeJobPosId);
	}

	/**
	 * Removes the employee job pos with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the employee job pos
	 * @return the employee job pos that was removed
	 * @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos remove(Serializable primaryKey)
		throws NoSuchEmployeeJobPosException {
		Session session = null;

		try {
			session = openSession();

			EmployeeJobPos employeeJobPos = (EmployeeJobPos)session.get(EmployeeJobPosImpl.class,
					primaryKey);

			if (employeeJobPos == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEmployeeJobPosException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(employeeJobPos);
		}
		catch (NoSuchEmployeeJobPosException nsee) {
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
	protected EmployeeJobPos removeImpl(EmployeeJobPos employeeJobPos) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(employeeJobPos)) {
				employeeJobPos = (EmployeeJobPos)session.get(EmployeeJobPosImpl.class,
						employeeJobPos.getPrimaryKeyObj());
			}

			if (employeeJobPos != null) {
				session.delete(employeeJobPos);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (employeeJobPos != null) {
			clearCache(employeeJobPos);
		}

		return employeeJobPos;
	}

	@Override
	public EmployeeJobPos updateImpl(EmployeeJobPos employeeJobPos) {
		boolean isNew = employeeJobPos.isNew();

		if (!(employeeJobPos instanceof EmployeeJobPosModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(employeeJobPos.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(employeeJobPos);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in employeeJobPos proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EmployeeJobPos implementation " +
				employeeJobPos.getClass());
		}

		EmployeeJobPosModelImpl employeeJobPosModelImpl = (EmployeeJobPosModelImpl)employeeJobPos;

		if (Validator.isNull(employeeJobPos.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			employeeJobPos.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (employeeJobPos.getCreateDate() == null)) {
			if (serviceContext == null) {
				employeeJobPos.setCreateDate(now);
			}
			else {
				employeeJobPos.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!employeeJobPosModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				employeeJobPos.setModifiedDate(now);
			}
			else {
				employeeJobPos.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (employeeJobPos.isNew()) {
				session.save(employeeJobPos);

				employeeJobPos.setNew(false);
			}
			else {
				employeeJobPos = (EmployeeJobPos)session.merge(employeeJobPos);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!EmployeeJobPosModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { employeeJobPosModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					employeeJobPosModelImpl.getUuid(),
					employeeJobPosModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { employeeJobPosModelImpl.getEmployeeId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEEID,
				args);

			args = new Object[] { employeeJobPosModelImpl.getWorkingUnitId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_WORKINGUNITID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKINGUNITID,
				args);

			args = new Object[] {
					employeeJobPosModelImpl.getGroupId(),
					employeeJobPosModelImpl.getJobPostId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_JOBPOSTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_JOBPOSTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((employeeJobPosModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeJobPosModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { employeeJobPosModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((employeeJobPosModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeJobPosModelImpl.getOriginalUuid(),
						employeeJobPosModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						employeeJobPosModelImpl.getUuid(),
						employeeJobPosModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((employeeJobPosModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeJobPosModelImpl.getOriginalEmployeeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEEID,
					args);

				args = new Object[] { employeeJobPosModelImpl.getEmployeeId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEEID,
					args);
			}

			if ((employeeJobPosModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKINGUNITID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeJobPosModelImpl.getOriginalWorkingUnitId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_WORKINGUNITID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKINGUNITID,
					args);

				args = new Object[] { employeeJobPosModelImpl.getWorkingUnitId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_WORKINGUNITID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKINGUNITID,
					args);
			}

			if ((employeeJobPosModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_JOBPOSTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeJobPosModelImpl.getOriginalGroupId(),
						employeeJobPosModelImpl.getOriginalJobPostId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_JOBPOSTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_JOBPOSTID,
					args);

				args = new Object[] {
						employeeJobPosModelImpl.getGroupId(),
						employeeJobPosModelImpl.getJobPostId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_G_JOBPOSTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_G_JOBPOSTID,
					args);
			}
		}

		entityCache.putResult(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeJobPosImpl.class, employeeJobPos.getPrimaryKey(),
			employeeJobPos, false);

		clearUniqueFindersCache(employeeJobPosModelImpl, false);
		cacheUniqueFindersCache(employeeJobPosModelImpl);

		employeeJobPos.resetOriginalValues();

		return employeeJobPos;
	}

	/**
	 * Returns the employee job pos with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the employee job pos
	 * @return the employee job pos
	 * @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEmployeeJobPosException {
		EmployeeJobPos employeeJobPos = fetchByPrimaryKey(primaryKey);

		if (employeeJobPos == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEmployeeJobPosException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return employeeJobPos;
	}

	/**
	 * Returns the employee job pos with the primary key or throws a {@link NoSuchEmployeeJobPosException} if it could not be found.
	 *
	 * @param employeeJobPosId the primary key of the employee job pos
	 * @return the employee job pos
	 * @throws NoSuchEmployeeJobPosException if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos findByPrimaryKey(long employeeJobPosId)
		throws NoSuchEmployeeJobPosException {
		return findByPrimaryKey((Serializable)employeeJobPosId);
	}

	/**
	 * Returns the employee job pos with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the employee job pos
	 * @return the employee job pos, or <code>null</code> if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
				EmployeeJobPosImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EmployeeJobPos employeeJobPos = (EmployeeJobPos)serializable;

		if (employeeJobPos == null) {
			Session session = null;

			try {
				session = openSession();

				employeeJobPos = (EmployeeJobPos)session.get(EmployeeJobPosImpl.class,
						primaryKey);

				if (employeeJobPos != null) {
					cacheResult(employeeJobPos);
				}
				else {
					entityCache.putResult(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
						EmployeeJobPosImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
					EmployeeJobPosImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return employeeJobPos;
	}

	/**
	 * Returns the employee job pos with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param employeeJobPosId the primary key of the employee job pos
	 * @return the employee job pos, or <code>null</code> if a employee job pos with the primary key could not be found
	 */
	@Override
	public EmployeeJobPos fetchByPrimaryKey(long employeeJobPosId) {
		return fetchByPrimaryKey((Serializable)employeeJobPosId);
	}

	@Override
	public Map<Serializable, EmployeeJobPos> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EmployeeJobPos> map = new HashMap<Serializable, EmployeeJobPos>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EmployeeJobPos employeeJobPos = fetchByPrimaryKey(primaryKey);

			if (employeeJobPos != null) {
				map.put(primaryKey, employeeJobPos);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
					EmployeeJobPosImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EmployeeJobPos)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EMPLOYEEJOBPOS_WHERE_PKS_IN);

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

			for (EmployeeJobPos employeeJobPos : (List<EmployeeJobPos>)q.list()) {
				map.put(employeeJobPos.getPrimaryKeyObj(), employeeJobPos);

				cacheResult(employeeJobPos);

				uncachedPrimaryKeys.remove(employeeJobPos.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EmployeeJobPosModelImpl.ENTITY_CACHE_ENABLED,
					EmployeeJobPosImpl.class, primaryKey, nullModel);
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
	 * Returns all the employee job poses.
	 *
	 * @return the employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employee job poses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @return the range of employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the employee job poses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findAll(int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employee job poses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of employee job poses
	 * @param end the upper bound of the range of employee job poses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of employee job poses
	 */
	@Override
	public List<EmployeeJobPos> findAll(int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator,
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

		List<EmployeeJobPos> list = null;

		if (retrieveFromCache) {
			list = (List<EmployeeJobPos>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EMPLOYEEJOBPOS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EMPLOYEEJOBPOS;

				if (pagination) {
					sql = sql.concat(EmployeeJobPosModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EmployeeJobPos>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the employee job poses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EmployeeJobPos employeeJobPos : findAll()) {
			remove(employeeJobPos);
		}
	}

	/**
	 * Returns the number of employee job poses.
	 *
	 * @return the number of employee job poses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EMPLOYEEJOBPOS);

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
		return EmployeeJobPosModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the employee job pos persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EmployeeJobPosImpl.class.getName());
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
	private static final String _SQL_SELECT_EMPLOYEEJOBPOS = "SELECT employeeJobPos FROM EmployeeJobPos employeeJobPos";
	private static final String _SQL_SELECT_EMPLOYEEJOBPOS_WHERE_PKS_IN = "SELECT employeeJobPos FROM EmployeeJobPos employeeJobPos WHERE employeeJobPosId IN (";
	private static final String _SQL_SELECT_EMPLOYEEJOBPOS_WHERE = "SELECT employeeJobPos FROM EmployeeJobPos employeeJobPos WHERE ";
	private static final String _SQL_COUNT_EMPLOYEEJOBPOS = "SELECT COUNT(employeeJobPos) FROM EmployeeJobPos employeeJobPos";
	private static final String _SQL_COUNT_EMPLOYEEJOBPOS_WHERE = "SELECT COUNT(employeeJobPos) FROM EmployeeJobPos employeeJobPos WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "employeeJobPos.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EmployeeJobPos exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EmployeeJobPos exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EmployeeJobPosPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}