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

import org.opencps.usermgt.exception.NoSuchEmployeeException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.impl.EmployeeImpl;
import org.opencps.usermgt.model.impl.EmployeeModelImpl;
import org.opencps.usermgt.service.persistence.EmployeePersistence;

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
 * The persistence implementation for the employee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see EmployeePersistence
 * @see org.opencps.usermgt.service.persistence.EmployeeUtil
 * @generated
 */
@ProviderType
public class EmployeePersistenceImpl extends BasePersistenceImpl<Employee>
	implements EmployeePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EmployeeUtil} to access the employee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EmployeeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			EmployeeModelImpl.UUID_COLUMN_BITMASK |
			EmployeeModelImpl.EMPLOYEENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the employees where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching employees
	 */
	@Override
	public List<Employee> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employees where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @return the range of matching employees
	 */
	@Override
	public List<Employee> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employees where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByUuid(String uuid, int start, int end,
		OrderByComparator<Employee> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employees where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByUuid(String uuid, int start, int end,
		OrderByComparator<Employee> orderByComparator, boolean retrieveFromCache) {
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

		List<Employee> list = null;

		if (retrieveFromCache) {
			list = (List<Employee>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employee employee : list) {
					if (!Objects.equals(uuid, employee.getUuid())) {
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

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

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
				query.append(EmployeeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first employee in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByUuid_First(String uuid,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByUuid_First(uuid, orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the first employee in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByUuid_First(String uuid,
		OrderByComparator<Employee> orderByComparator) {
		List<Employee> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByUuid_Last(String uuid,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByUuid_Last(uuid, orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the last employee in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByUuid_Last(String uuid,
		OrderByComparator<Employee> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Employee> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employees before and after the current employee in the ordered set where uuid = &#63;.
	 *
	 * @param employeeId the primary key of the current employee
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee[] findByUuid_PrevAndNext(long employeeId, String uuid,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = findByPrimaryKey(employeeId);

		Session session = null;

		try {
			session = openSession();

			Employee[] array = new EmployeeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, employee, uuid,
					orderByComparator, true);

			array[1] = employee;

			array[2] = getByUuid_PrevAndNext(session, employee, uuid,
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

	protected Employee getByUuid_PrevAndNext(Session session,
		Employee employee, String uuid,
		OrderByComparator<Employee> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EMPLOYEE_WHERE);

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
			query.append(EmployeeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(employee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Employee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employees where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Employee employee : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(employee);
		}
	}

	/**
	 * Returns the number of employees where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching employees
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "employee.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "employee.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(employee.uuid IS NULL OR employee.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			EmployeeModelImpl.UUID_COLUMN_BITMASK |
			EmployeeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the employee where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByUUID_G(String uuid, long groupId)
		throws NoSuchEmployeeException {
		Employee employee = fetchByUUID_G(uuid, groupId);

		if (employee == null) {
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

			throw new NoSuchEmployeeException(msg.toString());
		}

		return employee;
	}

	/**
	 * Returns the employee where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the employee where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Employee) {
			Employee employee = (Employee)result;

			if (!Objects.equals(uuid, employee.getUuid()) ||
					(groupId != employee.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

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

				List<Employee> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Employee employee = list.get(0);

					result = employee;

					cacheResult(employee);
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
			return (Employee)result;
		}
	}

	/**
	 * Removes the employee where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the employee that was removed
	 */
	@Override
	public Employee removeByUUID_G(String uuid, long groupId)
		throws NoSuchEmployeeException {
		Employee employee = findByUUID_G(uuid, groupId);

		return remove(employee);
	}

	/**
	 * Returns the number of employees where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching employees
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "employee.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "employee.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(employee.uuid IS NULL OR employee.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "employee.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			EmployeeModelImpl.UUID_COLUMN_BITMASK |
			EmployeeModelImpl.COMPANYID_COLUMN_BITMASK |
			EmployeeModelImpl.EMPLOYEENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the employees where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching employees
	 */
	@Override
	public List<Employee> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employees where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @return the range of matching employees
	 */
	@Override
	public List<Employee> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employees where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Employee> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employees where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Employee> orderByComparator,
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

		List<Employee> list = null;

		if (retrieveFromCache) {
			list = (List<Employee>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employee employee : list) {
					if (!Objects.equals(uuid, employee.getUuid()) ||
							(companyId != employee.getCompanyId())) {
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

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

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
				query.append(EmployeeModelImpl.ORDER_BY_JPQL);
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
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first employee in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the first employee in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator) {
		List<Employee> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the last employee in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Employee> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employees before and after the current employee in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param employeeId the primary key of the current employee
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee[] findByUuid_C_PrevAndNext(long employeeId, String uuid,
		long companyId, OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = findByPrimaryKey(employeeId);

		Session session = null;

		try {
			session = openSession();

			Employee[] array = new EmployeeImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, employee, uuid,
					companyId, orderByComparator, true);

			array[1] = employee;

			array[2] = getByUuid_C_PrevAndNext(session, employee, uuid,
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

	protected Employee getByUuid_C_PrevAndNext(Session session,
		Employee employee, String uuid, long companyId,
		OrderByComparator<Employee> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EMPLOYEE_WHERE);

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
			query.append(EmployeeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(employee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Employee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employees where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Employee employee : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(employee);
		}
	}

	/**
	 * Returns the number of employees where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching employees
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "employee.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "employee.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(employee.uuid IS NULL OR employee.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "employee.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_MAPPINGUSERID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_mappingUserId",
			new String[] { Long.class.getName(), Long.class.getName() },
			EmployeeModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeModelImpl.MAPPINGUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_MAPPINGUSERID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_mappingUserId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the employee where groupId = &#63; and mappingUserId = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param mappingUserId the mapping user ID
	 * @return the matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByF_mappingUserId(long groupId, long mappingUserId)
		throws NoSuchEmployeeException {
		Employee employee = fetchByF_mappingUserId(groupId, mappingUserId);

		if (employee == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", mappingUserId=");
			msg.append(mappingUserId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEmployeeException(msg.toString());
		}

		return employee;
	}

	/**
	 * Returns the employee where groupId = &#63; and mappingUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param mappingUserId the mapping user ID
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_mappingUserId(long groupId, long mappingUserId) {
		return fetchByF_mappingUserId(groupId, mappingUserId, true);
	}

	/**
	 * Returns the employee where groupId = &#63; and mappingUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param mappingUserId the mapping user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_mappingUserId(long groupId, long mappingUserId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, mappingUserId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_MAPPINGUSERID,
					finderArgs, this);
		}

		if (result instanceof Employee) {
			Employee employee = (Employee)result;

			if ((groupId != employee.getGroupId()) ||
					(mappingUserId != employee.getMappingUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_MAPPINGUSERID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_MAPPINGUSERID_MAPPINGUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(mappingUserId);

				List<Employee> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_MAPPINGUSERID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"EmployeePersistenceImpl.fetchByF_mappingUserId(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Employee employee = list.get(0);

					result = employee;

					cacheResult(employee);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_MAPPINGUSERID,
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
			return (Employee)result;
		}
	}

	/**
	 * Removes the employee where groupId = &#63; and mappingUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param mappingUserId the mapping user ID
	 * @return the employee that was removed
	 */
	@Override
	public Employee removeByF_mappingUserId(long groupId, long mappingUserId)
		throws NoSuchEmployeeException {
		Employee employee = findByF_mappingUserId(groupId, mappingUserId);

		return remove(employee);
	}

	/**
	 * Returns the number of employees where groupId = &#63; and mappingUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param mappingUserId the mapping user ID
	 * @return the number of matching employees
	 */
	@Override
	public int countByF_mappingUserId(long groupId, long mappingUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_MAPPINGUSERID;

		Object[] finderArgs = new Object[] { groupId, mappingUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_MAPPINGUSERID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_MAPPINGUSERID_MAPPINGUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(mappingUserId);

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

	private static final String _FINDER_COLUMN_F_MAPPINGUSERID_GROUPID_2 = "employee.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_MAPPINGUSERID_MAPPINGUSERID_2 = "employee.mappingUserId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_FB_MUID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByFB_MUID",
			new String[] { Long.class.getName() },
			EmployeeModelImpl.MAPPINGUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FB_MUID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFB_MUID",
			new String[] { Long.class.getName() });

	/**
	 * Returns the employee where mappingUserId = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	 *
	 * @param mappingUserId the mapping user ID
	 * @return the matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByFB_MUID(long mappingUserId)
		throws NoSuchEmployeeException {
		Employee employee = fetchByFB_MUID(mappingUserId);

		if (employee == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("mappingUserId=");
			msg.append(mappingUserId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEmployeeException(msg.toString());
		}

		return employee;
	}

	/**
	 * Returns the employee where mappingUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mappingUserId the mapping user ID
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByFB_MUID(long mappingUserId) {
		return fetchByFB_MUID(mappingUserId, true);
	}

	/**
	 * Returns the employee where mappingUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mappingUserId the mapping user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByFB_MUID(long mappingUserId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { mappingUserId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_FB_MUID,
					finderArgs, this);
		}

		if (result instanceof Employee) {
			Employee employee = (Employee)result;

			if ((mappingUserId != employee.getMappingUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_FB_MUID_MAPPINGUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(mappingUserId);

				List<Employee> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_FB_MUID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"EmployeePersistenceImpl.fetchByFB_MUID(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Employee employee = list.get(0);

					result = employee;

					cacheResult(employee);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_MUID,
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
			return (Employee)result;
		}
	}

	/**
	 * Removes the employee where mappingUserId = &#63; from the database.
	 *
	 * @param mappingUserId the mapping user ID
	 * @return the employee that was removed
	 */
	@Override
	public Employee removeByFB_MUID(long mappingUserId)
		throws NoSuchEmployeeException {
		Employee employee = findByFB_MUID(mappingUserId);

		return remove(employee);
	}

	/**
	 * Returns the number of employees where mappingUserId = &#63;.
	 *
	 * @param mappingUserId the mapping user ID
	 * @return the number of matching employees
	 */
	@Override
	public int countByFB_MUID(long mappingUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FB_MUID;

		Object[] finderArgs = new Object[] { mappingUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_FB_MUID_MAPPINGUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(mappingUserId);

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

	private static final String _FINDER_COLUMN_FB_MUID_MAPPINGUSERID_2 = "employee.mappingUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID =
		new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_groupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID =
		new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_groupId",
			new String[] { Long.class.getName() },
			EmployeeModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeModelImpl.EMPLOYEENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_groupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the employees where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching employees
	 */
	@Override
	public List<Employee> findByF_groupId(long groupId) {
		return findByF_groupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the employees where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @return the range of matching employees
	 */
	@Override
	public List<Employee> findByF_groupId(long groupId, int start, int end) {
		return findByF_groupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employees where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByF_groupId(long groupId, int start, int end,
		OrderByComparator<Employee> orderByComparator) {
		return findByF_groupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employees where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByF_groupId(long groupId, int start, int end,
		OrderByComparator<Employee> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<Employee> list = null;

		if (retrieveFromCache) {
			list = (List<Employee>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employee employee : list) {
					if ((groupId != employee.getGroupId())) {
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

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EmployeeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first employee in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByF_groupId_First(long groupId,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByF_groupId_First(groupId, orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the first employee in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_groupId_First(long groupId,
		OrderByComparator<Employee> orderByComparator) {
		List<Employee> list = findByF_groupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByF_groupId_Last(long groupId,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByF_groupId_Last(groupId, orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the last employee in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_groupId_Last(long groupId,
		OrderByComparator<Employee> orderByComparator) {
		int count = countByF_groupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Employee> list = findByF_groupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employees before and after the current employee in the ordered set where groupId = &#63;.
	 *
	 * @param employeeId the primary key of the current employee
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee[] findByF_groupId_PrevAndNext(long employeeId,
		long groupId, OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = findByPrimaryKey(employeeId);

		Session session = null;

		try {
			session = openSession();

			Employee[] array = new EmployeeImpl[3];

			array[0] = getByF_groupId_PrevAndNext(session, employee, groupId,
					orderByComparator, true);

			array[1] = employee;

			array[2] = getByF_groupId_PrevAndNext(session, employee, groupId,
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

	protected Employee getByF_groupId_PrevAndNext(Session session,
		Employee employee, long groupId,
		OrderByComparator<Employee> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EMPLOYEE_WHERE);

		query.append(_FINDER_COLUMN_F_GROUPID_GROUPID_2);

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
			query.append(EmployeeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(employee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Employee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employees where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_groupId(long groupId) {
		for (Employee employee : findByF_groupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(employee);
		}
	}

	/**
	 * Returns the number of employees where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching employees
	 */
	@Override
	public int countByF_groupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_GROUPID_GROUPID_2 = "employee.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_EMPLOYEENO =
		new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_employeeNo",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEENO =
		new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_employeeNo",
			new String[] { Long.class.getName(), String.class.getName() },
			EmployeeModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeModelImpl.EMPLOYEENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_EMPLOYEENO = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_employeeNo",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the employees where groupId = &#63; and employeeNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @return the matching employees
	 */
	@Override
	public List<Employee> findByF_employeeNo(long groupId, String employeeNo) {
		return findByF_employeeNo(groupId, employeeNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employees where groupId = &#63; and employeeNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @return the range of matching employees
	 */
	@Override
	public List<Employee> findByF_employeeNo(long groupId, String employeeNo,
		int start, int end) {
		return findByF_employeeNo(groupId, employeeNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employees where groupId = &#63; and employeeNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByF_employeeNo(long groupId, String employeeNo,
		int start, int end, OrderByComparator<Employee> orderByComparator) {
		return findByF_employeeNo(groupId, employeeNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employees where groupId = &#63; and employeeNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByF_employeeNo(long groupId, String employeeNo,
		int start, int end, OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEENO;
			finderArgs = new Object[] { groupId, employeeNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_EMPLOYEENO;
			finderArgs = new Object[] {
					groupId, employeeNo,
					
					start, end, orderByComparator
				};
		}

		List<Employee> list = null;

		if (retrieveFromCache) {
			list = (List<Employee>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employee employee : list) {
					if ((groupId != employee.getGroupId()) ||
							!Objects.equals(employeeNo, employee.getEmployeeNo())) {
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

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_EMPLOYEENO_GROUPID_2);

			boolean bindEmployeeNo = false;

			if (employeeNo == null) {
				query.append(_FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_1);
			}
			else if (employeeNo.equals("")) {
				query.append(_FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_3);
			}
			else {
				bindEmployeeNo = true;

				query.append(_FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EmployeeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEmployeeNo) {
					qPos.add(employeeNo);
				}

				if (!pagination) {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByF_employeeNo_First(long groupId, String employeeNo,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByF_employeeNo_First(groupId, employeeNo,
				orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", employeeNo=");
		msg.append(employeeNo);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the first employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_employeeNo_First(long groupId, String employeeNo,
		OrderByComparator<Employee> orderByComparator) {
		List<Employee> list = findByF_employeeNo(groupId, employeeNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByF_employeeNo_Last(long groupId, String employeeNo,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByF_employeeNo_Last(groupId, employeeNo,
				orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", employeeNo=");
		msg.append(employeeNo);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the last employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_employeeNo_Last(long groupId, String employeeNo,
		OrderByComparator<Employee> orderByComparator) {
		int count = countByF_employeeNo(groupId, employeeNo);

		if (count == 0) {
			return null;
		}

		List<Employee> list = findByF_employeeNo(groupId, employeeNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employees before and after the current employee in the ordered set where groupId = &#63; and employeeNo = &#63;.
	 *
	 * @param employeeId the primary key of the current employee
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee[] findByF_employeeNo_PrevAndNext(long employeeId,
		long groupId, String employeeNo,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = findByPrimaryKey(employeeId);

		Session session = null;

		try {
			session = openSession();

			Employee[] array = new EmployeeImpl[3];

			array[0] = getByF_employeeNo_PrevAndNext(session, employee,
					groupId, employeeNo, orderByComparator, true);

			array[1] = employee;

			array[2] = getByF_employeeNo_PrevAndNext(session, employee,
					groupId, employeeNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Employee getByF_employeeNo_PrevAndNext(Session session,
		Employee employee, long groupId, String employeeNo,
		OrderByComparator<Employee> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EMPLOYEE_WHERE);

		query.append(_FINDER_COLUMN_F_EMPLOYEENO_GROUPID_2);

		boolean bindEmployeeNo = false;

		if (employeeNo == null) {
			query.append(_FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_1);
		}
		else if (employeeNo.equals("")) {
			query.append(_FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_3);
		}
		else {
			bindEmployeeNo = true;

			query.append(_FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_2);
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
			query.append(EmployeeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindEmployeeNo) {
			qPos.add(employeeNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(employee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Employee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employees where groupId = &#63; and employeeNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 */
	@Override
	public void removeByF_employeeNo(long groupId, String employeeNo) {
		for (Employee employee : findByF_employeeNo(groupId, employeeNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(employee);
		}
	}

	/**
	 * Returns the number of employees where groupId = &#63; and employeeNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @return the number of matching employees
	 */
	@Override
	public int countByF_employeeNo(long groupId, String employeeNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_EMPLOYEENO;

		Object[] finderArgs = new Object[] { groupId, employeeNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_EMPLOYEENO_GROUPID_2);

			boolean bindEmployeeNo = false;

			if (employeeNo == null) {
				query.append(_FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_1);
			}
			else if (employeeNo.equals("")) {
				query.append(_FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_3);
			}
			else {
				bindEmployeeNo = true;

				query.append(_FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEmployeeNo) {
					qPos.add(employeeNo);
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

	private static final String _FINDER_COLUMN_F_EMPLOYEENO_GROUPID_2 = "employee.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_1 = "employee.employeeNo IS NULL";
	private static final String _FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_2 = "employee.employeeNo = ?";
	private static final String _FINDER_COLUMN_F_EMPLOYEENO_EMPLOYEENO_3 = "(employee.employeeNo IS NULL OR employee.employeeNo = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_EMPNO = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_GID_EMPNO",
			new String[] { Long.class.getName(), String.class.getName() },
			EmployeeModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeModelImpl.EMPLOYEENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_EMPNO = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_EMPNO",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the employee where groupId = &#63; and employeeNo = &#63; or throws a {@link NoSuchEmployeeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @return the matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByF_GID_EMPNO(long groupId, String employeeNo)
		throws NoSuchEmployeeException {
		Employee employee = fetchByF_GID_EMPNO(groupId, employeeNo);

		if (employee == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", employeeNo=");
			msg.append(employeeNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEmployeeException(msg.toString());
		}

		return employee;
	}

	/**
	 * Returns the employee where groupId = &#63; and employeeNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_GID_EMPNO(long groupId, String employeeNo) {
		return fetchByF_GID_EMPNO(groupId, employeeNo, true);
	}

	/**
	 * Returns the employee where groupId = &#63; and employeeNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_GID_EMPNO(long groupId, String employeeNo,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, employeeNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_EMPNO,
					finderArgs, this);
		}

		if (result instanceof Employee) {
			Employee employee = (Employee)result;

			if ((groupId != employee.getGroupId()) ||
					!Objects.equals(employeeNo, employee.getEmployeeNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_EMPNO_GROUPID_2);

			boolean bindEmployeeNo = false;

			if (employeeNo == null) {
				query.append(_FINDER_COLUMN_F_GID_EMPNO_EMPLOYEENO_1);
			}
			else if (employeeNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_EMPNO_EMPLOYEENO_3);
			}
			else {
				bindEmployeeNo = true;

				query.append(_FINDER_COLUMN_F_GID_EMPNO_EMPLOYEENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEmployeeNo) {
					qPos.add(employeeNo);
				}

				List<Employee> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_EMPNO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"EmployeePersistenceImpl.fetchByF_GID_EMPNO(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Employee employee = list.get(0);

					result = employee;

					cacheResult(employee);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_EMPNO,
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
			return (Employee)result;
		}
	}

	/**
	 * Removes the employee where groupId = &#63; and employeeNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @return the employee that was removed
	 */
	@Override
	public Employee removeByF_GID_EMPNO(long groupId, String employeeNo)
		throws NoSuchEmployeeException {
		Employee employee = findByF_GID_EMPNO(groupId, employeeNo);

		return remove(employee);
	}

	/**
	 * Returns the number of employees where groupId = &#63; and employeeNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeNo the employee no
	 * @return the number of matching employees
	 */
	@Override
	public int countByF_GID_EMPNO(long groupId, String employeeNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_EMPNO;

		Object[] finderArgs = new Object[] { groupId, employeeNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_EMPNO_GROUPID_2);

			boolean bindEmployeeNo = false;

			if (employeeNo == null) {
				query.append(_FINDER_COLUMN_F_GID_EMPNO_EMPLOYEENO_1);
			}
			else if (employeeNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_EMPNO_EMPLOYEENO_3);
			}
			else {
				bindEmployeeNo = true;

				query.append(_FINDER_COLUMN_F_GID_EMPNO_EMPLOYEENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEmployeeNo) {
					qPos.add(employeeNo);
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

	private static final String _FINDER_COLUMN_F_GID_EMPNO_GROUPID_2 = "employee.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_EMPNO_EMPLOYEENO_1 = "employee.employeeNo IS NULL";
	private static final String _FINDER_COLUMN_F_GID_EMPNO_EMPLOYEENO_2 = "employee.employeeNo = ?";
	private static final String _FINDER_COLUMN_F_GID_EMPNO_EMPLOYEENO_3 = "(employee.employeeNo IS NULL OR employee.employeeNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_EMAIL = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_email",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMAIL =
		new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_email",
			new String[] { Long.class.getName(), String.class.getName() },
			EmployeeModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeModelImpl.EMAIL_COLUMN_BITMASK |
			EmployeeModelImpl.EMPLOYEENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_EMAIL = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_email",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the employees where groupId = &#63; and email = &#63;.
	 *
	 * @param groupId the group ID
	 * @param email the email
	 * @return the matching employees
	 */
	@Override
	public List<Employee> findByF_email(long groupId, String email) {
		return findByF_email(groupId, email, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employees where groupId = &#63; and email = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param email the email
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @return the range of matching employees
	 */
	@Override
	public List<Employee> findByF_email(long groupId, String email, int start,
		int end) {
		return findByF_email(groupId, email, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employees where groupId = &#63; and email = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param email the email
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByF_email(long groupId, String email, int start,
		int end, OrderByComparator<Employee> orderByComparator) {
		return findByF_email(groupId, email, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employees where groupId = &#63; and email = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param email the email
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByF_email(long groupId, String email, int start,
		int end, OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMAIL;
			finderArgs = new Object[] { groupId, email };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_EMAIL;
			finderArgs = new Object[] {
					groupId, email,
					
					start, end, orderByComparator
				};
		}

		List<Employee> list = null;

		if (retrieveFromCache) {
			list = (List<Employee>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employee employee : list) {
					if ((groupId != employee.getGroupId()) ||
							!Objects.equals(email, employee.getEmail())) {
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

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_EMAIL_GROUPID_2);

			boolean bindEmail = false;

			if (email == null) {
				query.append(_FINDER_COLUMN_F_EMAIL_EMAIL_1);
			}
			else if (email.equals("")) {
				query.append(_FINDER_COLUMN_F_EMAIL_EMAIL_3);
			}
			else {
				bindEmail = true;

				query.append(_FINDER_COLUMN_F_EMAIL_EMAIL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EmployeeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEmail) {
					qPos.add(email);
				}

				if (!pagination) {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first employee in the ordered set where groupId = &#63; and email = &#63;.
	 *
	 * @param groupId the group ID
	 * @param email the email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByF_email_First(long groupId, String email,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByF_email_First(groupId, email,
				orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", email=");
		msg.append(email);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the first employee in the ordered set where groupId = &#63; and email = &#63;.
	 *
	 * @param groupId the group ID
	 * @param email the email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_email_First(long groupId, String email,
		OrderByComparator<Employee> orderByComparator) {
		List<Employee> list = findByF_email(groupId, email, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee in the ordered set where groupId = &#63; and email = &#63;.
	 *
	 * @param groupId the group ID
	 * @param email the email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByF_email_Last(long groupId, String email,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByF_email_Last(groupId, email,
				orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", email=");
		msg.append(email);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the last employee in the ordered set where groupId = &#63; and email = &#63;.
	 *
	 * @param groupId the group ID
	 * @param email the email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByF_email_Last(long groupId, String email,
		OrderByComparator<Employee> orderByComparator) {
		int count = countByF_email(groupId, email);

		if (count == 0) {
			return null;
		}

		List<Employee> list = findByF_email(groupId, email, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employees before and after the current employee in the ordered set where groupId = &#63; and email = &#63;.
	 *
	 * @param employeeId the primary key of the current employee
	 * @param groupId the group ID
	 * @param email the email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee[] findByF_email_PrevAndNext(long employeeId, long groupId,
		String email, OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = findByPrimaryKey(employeeId);

		Session session = null;

		try {
			session = openSession();

			Employee[] array = new EmployeeImpl[3];

			array[0] = getByF_email_PrevAndNext(session, employee, groupId,
					email, orderByComparator, true);

			array[1] = employee;

			array[2] = getByF_email_PrevAndNext(session, employee, groupId,
					email, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Employee getByF_email_PrevAndNext(Session session,
		Employee employee, long groupId, String email,
		OrderByComparator<Employee> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EMPLOYEE_WHERE);

		query.append(_FINDER_COLUMN_F_EMAIL_GROUPID_2);

		boolean bindEmail = false;

		if (email == null) {
			query.append(_FINDER_COLUMN_F_EMAIL_EMAIL_1);
		}
		else if (email.equals("")) {
			query.append(_FINDER_COLUMN_F_EMAIL_EMAIL_3);
		}
		else {
			bindEmail = true;

			query.append(_FINDER_COLUMN_F_EMAIL_EMAIL_2);
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
			query.append(EmployeeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindEmail) {
			qPos.add(email);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(employee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Employee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employees where groupId = &#63; and email = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param email the email
	 */
	@Override
	public void removeByF_email(long groupId, String email) {
		for (Employee employee : findByF_email(groupId, email,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(employee);
		}
	}

	/**
	 * Returns the number of employees where groupId = &#63; and email = &#63;.
	 *
	 * @param groupId the group ID
	 * @param email the email
	 * @return the number of matching employees
	 */
	@Override
	public int countByF_email(long groupId, String email) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_EMAIL;

		Object[] finderArgs = new Object[] { groupId, email };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_F_EMAIL_GROUPID_2);

			boolean bindEmail = false;

			if (email == null) {
				query.append(_FINDER_COLUMN_F_EMAIL_EMAIL_1);
			}
			else if (email.equals("")) {
				query.append(_FINDER_COLUMN_F_EMAIL_EMAIL_3);
			}
			else {
				bindEmail = true;

				query.append(_FINDER_COLUMN_F_EMAIL_EMAIL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEmail) {
					qPos.add(email);
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

	private static final String _FINDER_COLUMN_F_EMAIL_GROUPID_2 = "employee.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_EMAIL_EMAIL_1 = "employee.email IS NULL";
	private static final String _FINDER_COLUMN_F_EMAIL_EMAIL_2 = "employee.email = ?";
	private static final String _FINDER_COLUMN_F_EMAIL_EMAIL_3 = "(employee.email IS NULL OR employee.email = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, EmployeeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UID",
			new String[] { Long.class.getName(), Long.class.getName() },
			EmployeeModelImpl.GROUPID_COLUMN_BITMASK |
			EmployeeModelImpl.USERID_COLUMN_BITMASK |
			EmployeeModelImpl.EMPLOYEENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UID = new FinderPath(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_UID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the employees where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching employees
	 */
	@Override
	public List<Employee> findByG_UID(long groupId, long userId) {
		return findByG_UID(groupId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employees where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @return the range of matching employees
	 */
	@Override
	public List<Employee> findByG_UID(long groupId, long userId, int start,
		int end) {
		return findByG_UID(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the employees where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByG_UID(long groupId, long userId, int start,
		int end, OrderByComparator<Employee> orderByComparator) {
		return findByG_UID(groupId, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employees where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching employees
	 */
	@Override
	public List<Employee> findByG_UID(long groupId, long userId, int start,
		int end, OrderByComparator<Employee> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Employee> list = null;

		if (retrieveFromCache) {
			list = (List<Employee>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Employee employee : list) {
					if ((groupId != employee.getGroupId()) ||
							(userId != employee.getUserId())) {
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

			query.append(_SQL_SELECT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_G_UID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EmployeeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first employee in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByG_UID_First(long groupId, long userId,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByG_UID_First(groupId, userId,
				orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the first employee in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByG_UID_First(long groupId, long userId,
		OrderByComparator<Employee> orderByComparator) {
		List<Employee> list = findByG_UID(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last employee in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee
	 * @throws NoSuchEmployeeException if a matching employee could not be found
	 */
	@Override
	public Employee findByG_UID_Last(long groupId, long userId,
		OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = fetchByG_UID_Last(groupId, userId, orderByComparator);

		if (employee != null) {
			return employee;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchEmployeeException(msg.toString());
	}

	/**
	 * Returns the last employee in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching employee, or <code>null</code> if a matching employee could not be found
	 */
	@Override
	public Employee fetchByG_UID_Last(long groupId, long userId,
		OrderByComparator<Employee> orderByComparator) {
		int count = countByG_UID(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<Employee> list = findByG_UID(groupId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the employees before and after the current employee in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param employeeId the primary key of the current employee
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next employee
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee[] findByG_UID_PrevAndNext(long employeeId, long groupId,
		long userId, OrderByComparator<Employee> orderByComparator)
		throws NoSuchEmployeeException {
		Employee employee = findByPrimaryKey(employeeId);

		Session session = null;

		try {
			session = openSession();

			Employee[] array = new EmployeeImpl[3];

			array[0] = getByG_UID_PrevAndNext(session, employee, groupId,
					userId, orderByComparator, true);

			array[1] = employee;

			array[2] = getByG_UID_PrevAndNext(session, employee, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Employee getByG_UID_PrevAndNext(Session session,
		Employee employee, long groupId, long userId,
		OrderByComparator<Employee> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EMPLOYEE_WHERE);

		query.append(_FINDER_COLUMN_G_UID_GROUPID_2);

		query.append(_FINDER_COLUMN_G_UID_USERID_2);

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
			query.append(EmployeeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(employee);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Employee> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the employees where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByG_UID(long groupId, long userId) {
		for (Employee employee : findByG_UID(groupId, userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(employee);
		}
	}

	/**
	 * Returns the number of employees where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching employees
	 */
	@Override
	public int countByG_UID(long groupId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UID;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EMPLOYEE_WHERE);

			query.append(_FINDER_COLUMN_G_UID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_G_UID_GROUPID_2 = "employee.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_USERID_2 = "employee.userId = ?";

	public EmployeePersistenceImpl() {
		setModelClass(Employee.class);

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
	 * Caches the employee in the entity cache if it is enabled.
	 *
	 * @param employee the employee
	 */
	@Override
	public void cacheResult(Employee employee) {
		entityCache.putResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeImpl.class, employee.getPrimaryKey(), employee);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { employee.getUuid(), employee.getGroupId() }, employee);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_MAPPINGUSERID,
			new Object[] { employee.getGroupId(), employee.getMappingUserId() },
			employee);

		finderCache.putResult(FINDER_PATH_FETCH_BY_FB_MUID,
			new Object[] { employee.getMappingUserId() }, employee);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_EMPNO,
			new Object[] { employee.getGroupId(), employee.getEmployeeNo() },
			employee);

		employee.resetOriginalValues();
	}

	/**
	 * Caches the employees in the entity cache if it is enabled.
	 *
	 * @param employees the employees
	 */
	@Override
	public void cacheResult(List<Employee> employees) {
		for (Employee employee : employees) {
			if (entityCache.getResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
						EmployeeImpl.class, employee.getPrimaryKey()) == null) {
				cacheResult(employee);
			}
			else {
				employee.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all employees.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EmployeeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the employee.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Employee employee) {
		entityCache.removeResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeImpl.class, employee.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((EmployeeModelImpl)employee, true);
	}

	@Override
	public void clearCache(List<Employee> employees) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Employee employee : employees) {
			entityCache.removeResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
				EmployeeImpl.class, employee.getPrimaryKey());

			clearUniqueFindersCache((EmployeeModelImpl)employee, true);
		}
	}

	protected void cacheUniqueFindersCache(EmployeeModelImpl employeeModelImpl) {
		Object[] args = new Object[] {
				employeeModelImpl.getUuid(), employeeModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			employeeModelImpl, false);

		args = new Object[] {
				employeeModelImpl.getGroupId(),
				employeeModelImpl.getMappingUserId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_MAPPINGUSERID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_MAPPINGUSERID, args,
			employeeModelImpl, false);

		args = new Object[] { employeeModelImpl.getMappingUserId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_FB_MUID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_FB_MUID, args,
			employeeModelImpl, false);

		args = new Object[] {
				employeeModelImpl.getGroupId(),
				employeeModelImpl.getEmployeeNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_EMPNO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_EMPNO, args,
			employeeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		EmployeeModelImpl employeeModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					employeeModelImpl.getUuid(), employeeModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((employeeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					employeeModelImpl.getOriginalUuid(),
					employeeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					employeeModelImpl.getGroupId(),
					employeeModelImpl.getMappingUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_MAPPINGUSERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_MAPPINGUSERID, args);
		}

		if ((employeeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_MAPPINGUSERID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					employeeModelImpl.getOriginalGroupId(),
					employeeModelImpl.getOriginalMappingUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_MAPPINGUSERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_MAPPINGUSERID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { employeeModelImpl.getMappingUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_MUID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_MUID, args);
		}

		if ((employeeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_FB_MUID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					employeeModelImpl.getOriginalMappingUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_MUID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_MUID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					employeeModelImpl.getGroupId(),
					employeeModelImpl.getEmployeeNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_EMPNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_EMPNO, args);
		}

		if ((employeeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_EMPNO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					employeeModelImpl.getOriginalGroupId(),
					employeeModelImpl.getOriginalEmployeeNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_EMPNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_EMPNO, args);
		}
	}

	/**
	 * Creates a new employee with the primary key. Does not add the employee to the database.
	 *
	 * @param employeeId the primary key for the new employee
	 * @return the new employee
	 */
	@Override
	public Employee create(long employeeId) {
		Employee employee = new EmployeeImpl();

		employee.setNew(true);
		employee.setPrimaryKey(employeeId);

		String uuid = PortalUUIDUtil.generate();

		employee.setUuid(uuid);

		employee.setCompanyId(companyProvider.getCompanyId());

		return employee;
	}

	/**
	 * Removes the employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param employeeId the primary key of the employee
	 * @return the employee that was removed
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee remove(long employeeId) throws NoSuchEmployeeException {
		return remove((Serializable)employeeId);
	}

	/**
	 * Removes the employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the employee
	 * @return the employee that was removed
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee remove(Serializable primaryKey)
		throws NoSuchEmployeeException {
		Session session = null;

		try {
			session = openSession();

			Employee employee = (Employee)session.get(EmployeeImpl.class,
					primaryKey);

			if (employee == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEmployeeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(employee);
		}
		catch (NoSuchEmployeeException nsee) {
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
	protected Employee removeImpl(Employee employee) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(employee)) {
				employee = (Employee)session.get(EmployeeImpl.class,
						employee.getPrimaryKeyObj());
			}

			if (employee != null) {
				session.delete(employee);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (employee != null) {
			clearCache(employee);
		}

		return employee;
	}

	@Override
	public Employee updateImpl(Employee employee) {
		boolean isNew = employee.isNew();

		if (!(employee instanceof EmployeeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(employee.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(employee);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in employee proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Employee implementation " +
				employee.getClass());
		}

		EmployeeModelImpl employeeModelImpl = (EmployeeModelImpl)employee;

		if (Validator.isNull(employee.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			employee.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (employee.getCreateDate() == null)) {
			if (serviceContext == null) {
				employee.setCreateDate(now);
			}
			else {
				employee.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!employeeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				employee.setModifiedDate(now);
			}
			else {
				employee.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (employee.isNew()) {
				session.save(employee);

				employee.setNew(false);
			}
			else {
				employee = (Employee)session.merge(employee);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!EmployeeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { employeeModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					employeeModelImpl.getUuid(),
					employeeModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { employeeModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID,
				args);

			args = new Object[] {
					employeeModelImpl.getGroupId(),
					employeeModelImpl.getEmployeeNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEENO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEENO,
				args);

			args = new Object[] {
					employeeModelImpl.getGroupId(), employeeModelImpl.getEmail()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMAIL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMAIL,
				args);

			args = new Object[] {
					employeeModelImpl.getGroupId(),
					employeeModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((employeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { employeeModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { employeeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((employeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeModelImpl.getOriginalUuid(),
						employeeModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						employeeModelImpl.getUuid(),
						employeeModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((employeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID,
					args);

				args = new Object[] { employeeModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID,
					args);
			}

			if ((employeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEENO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeModelImpl.getOriginalGroupId(),
						employeeModelImpl.getOriginalEmployeeNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEENO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEENO,
					args);

				args = new Object[] {
						employeeModelImpl.getGroupId(),
						employeeModelImpl.getEmployeeNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMPLOYEENO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMPLOYEENO,
					args);
			}

			if ((employeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMAIL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeModelImpl.getOriginalGroupId(),
						employeeModelImpl.getOriginalEmail()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMAIL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMAIL,
					args);

				args = new Object[] {
						employeeModelImpl.getGroupId(),
						employeeModelImpl.getEmail()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_EMAIL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_EMAIL,
					args);
			}

			if ((employeeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						employeeModelImpl.getOriginalGroupId(),
						employeeModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID,
					args);

				args = new Object[] {
						employeeModelImpl.getGroupId(),
						employeeModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID,
					args);
			}
		}

		entityCache.putResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
			EmployeeImpl.class, employee.getPrimaryKey(), employee, false);

		clearUniqueFindersCache(employeeModelImpl, false);
		cacheUniqueFindersCache(employeeModelImpl);

		employee.resetOriginalValues();

		return employee;
	}

	/**
	 * Returns the employee with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the employee
	 * @return the employee
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEmployeeException {
		Employee employee = fetchByPrimaryKey(primaryKey);

		if (employee == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEmployeeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return employee;
	}

	/**
	 * Returns the employee with the primary key or throws a {@link NoSuchEmployeeException} if it could not be found.
	 *
	 * @param employeeId the primary key of the employee
	 * @return the employee
	 * @throws NoSuchEmployeeException if a employee with the primary key could not be found
	 */
	@Override
	public Employee findByPrimaryKey(long employeeId)
		throws NoSuchEmployeeException {
		return findByPrimaryKey((Serializable)employeeId);
	}

	/**
	 * Returns the employee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the employee
	 * @return the employee, or <code>null</code> if a employee with the primary key could not be found
	 */
	@Override
	public Employee fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
				EmployeeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Employee employee = (Employee)serializable;

		if (employee == null) {
			Session session = null;

			try {
				session = openSession();

				employee = (Employee)session.get(EmployeeImpl.class, primaryKey);

				if (employee != null) {
					cacheResult(employee);
				}
				else {
					entityCache.putResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
						EmployeeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
					EmployeeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return employee;
	}

	/**
	 * Returns the employee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param employeeId the primary key of the employee
	 * @return the employee, or <code>null</code> if a employee with the primary key could not be found
	 */
	@Override
	public Employee fetchByPrimaryKey(long employeeId) {
		return fetchByPrimaryKey((Serializable)employeeId);
	}

	@Override
	public Map<Serializable, Employee> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Employee> map = new HashMap<Serializable, Employee>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Employee employee = fetchByPrimaryKey(primaryKey);

			if (employee != null) {
				map.put(primaryKey, employee);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
					EmployeeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Employee)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EMPLOYEE_WHERE_PKS_IN);

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

			for (Employee employee : (List<Employee>)q.list()) {
				map.put(employee.getPrimaryKeyObj(), employee);

				cacheResult(employee);

				uncachedPrimaryKeys.remove(employee.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EmployeeModelImpl.ENTITY_CACHE_ENABLED,
					EmployeeImpl.class, primaryKey, nullModel);
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
	 * Returns all the employees.
	 *
	 * @return the employees
	 */
	@Override
	public List<Employee> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @return the range of employees
	 */
	@Override
	public List<Employee> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of employees
	 */
	@Override
	public List<Employee> findAll(int start, int end,
		OrderByComparator<Employee> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of employees
	 * @param end the upper bound of the range of employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of employees
	 */
	@Override
	public List<Employee> findAll(int start, int end,
		OrderByComparator<Employee> orderByComparator, boolean retrieveFromCache) {
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

		List<Employee> list = null;

		if (retrieveFromCache) {
			list = (List<Employee>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EMPLOYEE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EMPLOYEE;

				if (pagination) {
					sql = sql.concat(EmployeeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Employee>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the employees from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Employee employee : findAll()) {
			remove(employee);
		}
	}

	/**
	 * Returns the number of employees.
	 *
	 * @return the number of employees
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EMPLOYEE);

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
		return EmployeeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the employee persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EmployeeImpl.class.getName());
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
	private static final String _SQL_SELECT_EMPLOYEE = "SELECT employee FROM Employee employee";
	private static final String _SQL_SELECT_EMPLOYEE_WHERE_PKS_IN = "SELECT employee FROM Employee employee WHERE employeeId IN (";
	private static final String _SQL_SELECT_EMPLOYEE_WHERE = "SELECT employee FROM Employee employee WHERE ";
	private static final String _SQL_COUNT_EMPLOYEE = "SELECT COUNT(employee) FROM Employee employee";
	private static final String _SQL_COUNT_EMPLOYEE_WHERE = "SELECT COUNT(employee) FROM Employee employee WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "employee.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Employee exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Employee exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EmployeePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}