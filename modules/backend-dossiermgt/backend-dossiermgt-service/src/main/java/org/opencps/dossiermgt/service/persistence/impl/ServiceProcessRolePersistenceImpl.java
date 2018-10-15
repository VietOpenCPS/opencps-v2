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

package org.opencps.dossiermgt.service.persistence.impl;

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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.model.impl.ServiceProcessRoleImpl;
import org.opencps.dossiermgt.model.impl.ServiceProcessRoleModelImpl;
import org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK;
import org.opencps.dossiermgt.service.persistence.ServiceProcessRolePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the service process role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceProcessRolePersistence
 * @see org.opencps.dossiermgt.service.persistence.ServiceProcessRoleUtil
 * @generated
 */
@ProviderType
public class ServiceProcessRolePersistenceImpl extends BasePersistenceImpl<ServiceProcessRole>
	implements ServiceProcessRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ServiceProcessRoleUtil} to access the service process role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ServiceProcessRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ServiceProcessRoleModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the service process roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching service process roles
	 */
	@Override
	public List<ServiceProcessRole> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service process roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service process roles
	 * @param end the upper bound of the range of service process roles (not inclusive)
	 * @return the range of matching service process roles
	 */
	@Override
	public List<ServiceProcessRole> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service process roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service process roles
	 * @param end the upper bound of the range of service process roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service process roles
	 */
	@Override
	public List<ServiceProcessRole> findByUuid(String uuid, int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service process roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service process roles
	 * @param end the upper bound of the range of service process roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service process roles
	 */
	@Override
	public List<ServiceProcessRole> findByUuid(String uuid, int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator,
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

		List<ServiceProcessRole> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceProcessRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceProcessRole serviceProcessRole : list) {
					if (!Objects.equals(uuid, serviceProcessRole.getUuid())) {
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

			query.append(_SQL_SELECT_SERVICEPROCESSROLE_WHERE);

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
				query.append(ServiceProcessRoleModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceProcessRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceProcessRole>)QueryUtil.list(q,
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
	 * Returns the first service process role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process role
	 * @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole findByUuid_First(String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException {
		ServiceProcessRole serviceProcessRole = fetchByUuid_First(uuid,
				orderByComparator);

		if (serviceProcessRole != null) {
			return serviceProcessRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceProcessRoleException(msg.toString());
	}

	/**
	 * Returns the first service process role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process role, or <code>null</code> if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole fetchByUuid_First(String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		List<ServiceProcessRole> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service process role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process role
	 * @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole findByUuid_Last(String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException {
		ServiceProcessRole serviceProcessRole = fetchByUuid_Last(uuid,
				orderByComparator);

		if (serviceProcessRole != null) {
			return serviceProcessRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceProcessRoleException(msg.toString());
	}

	/**
	 * Returns the last service process role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process role, or <code>null</code> if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ServiceProcessRole> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service process roles before and after the current service process role in the ordered set where uuid = &#63;.
	 *
	 * @param serviceProcessRolePK the primary key of the current service process role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service process role
	 * @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	 */
	@Override
	public ServiceProcessRole[] findByUuid_PrevAndNext(
		ServiceProcessRolePK serviceProcessRolePK, String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException {
		ServiceProcessRole serviceProcessRole = findByPrimaryKey(serviceProcessRolePK);

		Session session = null;

		try {
			session = openSession();

			ServiceProcessRole[] array = new ServiceProcessRoleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, serviceProcessRole, uuid,
					orderByComparator, true);

			array[1] = serviceProcessRole;

			array[2] = getByUuid_PrevAndNext(session, serviceProcessRole, uuid,
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

	protected ServiceProcessRole getByUuid_PrevAndNext(Session session,
		ServiceProcessRole serviceProcessRole, String uuid,
		OrderByComparator<ServiceProcessRole> orderByComparator,
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

		query.append(_SQL_SELECT_SERVICEPROCESSROLE_WHERE);

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
			query.append(ServiceProcessRoleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceProcessRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceProcessRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service process roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ServiceProcessRole serviceProcessRole : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceProcessRole);
		}
	}

	/**
	 * Returns the number of service process roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching service process roles
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEPROCESSROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "serviceProcessRole.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "serviceProcessRole.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(serviceProcessRole.uuid IS NULL OR serviceProcessRole.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S_ID = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_S_ID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID =
		new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_S_ID",
			new String[] { Long.class.getName() },
			ServiceProcessRoleModelImpl.SERVICEPROCESSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_S_ID = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_S_ID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the service process roles where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @return the matching service process roles
	 */
	@Override
	public List<ServiceProcessRole> findByP_S_ID(long serviceProcessId) {
		return findByP_S_ID(serviceProcessId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service process roles where serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of service process roles
	 * @param end the upper bound of the range of service process roles (not inclusive)
	 * @return the range of matching service process roles
	 */
	@Override
	public List<ServiceProcessRole> findByP_S_ID(long serviceProcessId,
		int start, int end) {
		return findByP_S_ID(serviceProcessId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service process roles where serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of service process roles
	 * @param end the upper bound of the range of service process roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service process roles
	 */
	@Override
	public List<ServiceProcessRole> findByP_S_ID(long serviceProcessId,
		int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return findByP_S_ID(serviceProcessId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the service process roles where serviceProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceProcessId the service process ID
	 * @param start the lower bound of the range of service process roles
	 * @param end the upper bound of the range of service process roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service process roles
	 */
	@Override
	public List<ServiceProcessRole> findByP_S_ID(long serviceProcessId,
		int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID;
			finderArgs = new Object[] { serviceProcessId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S_ID;
			finderArgs = new Object[] {
					serviceProcessId,
					
					start, end, orderByComparator
				};
		}

		List<ServiceProcessRole> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceProcessRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceProcessRole serviceProcessRole : list) {
					if ((serviceProcessId != serviceProcessRole.getServiceProcessId())) {
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

			query.append(_SQL_SELECT_SERVICEPROCESSROLE_WHERE);

			query.append(_FINDER_COLUMN_P_S_ID_SERVICEPROCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceProcessRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

				if (!pagination) {
					list = (List<ServiceProcessRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceProcessRole>)QueryUtil.list(q,
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
	 * Returns the first service process role in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process role
	 * @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole findByP_S_ID_First(long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException {
		ServiceProcessRole serviceProcessRole = fetchByP_S_ID_First(serviceProcessId,
				orderByComparator);

		if (serviceProcessRole != null) {
			return serviceProcessRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchServiceProcessRoleException(msg.toString());
	}

	/**
	 * Returns the first service process role in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service process role, or <code>null</code> if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole fetchByP_S_ID_First(long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		List<ServiceProcessRole> list = findByP_S_ID(serviceProcessId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service process role in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process role
	 * @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole findByP_S_ID_Last(long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException {
		ServiceProcessRole serviceProcessRole = fetchByP_S_ID_Last(serviceProcessId,
				orderByComparator);

		if (serviceProcessRole != null) {
			return serviceProcessRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("serviceProcessId=");
		msg.append(serviceProcessId);

		msg.append("}");

		throw new NoSuchServiceProcessRoleException(msg.toString());
	}

	/**
	 * Returns the last service process role in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service process role, or <code>null</code> if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole fetchByP_S_ID_Last(long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		int count = countByP_S_ID(serviceProcessId);

		if (count == 0) {
			return null;
		}

		List<ServiceProcessRole> list = findByP_S_ID(serviceProcessId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service process roles before and after the current service process role in the ordered set where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessRolePK the primary key of the current service process role
	 * @param serviceProcessId the service process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service process role
	 * @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	 */
	@Override
	public ServiceProcessRole[] findByP_S_ID_PrevAndNext(
		ServiceProcessRolePK serviceProcessRolePK, long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException {
		ServiceProcessRole serviceProcessRole = findByPrimaryKey(serviceProcessRolePK);

		Session session = null;

		try {
			session = openSession();

			ServiceProcessRole[] array = new ServiceProcessRoleImpl[3];

			array[0] = getByP_S_ID_PrevAndNext(session, serviceProcessRole,
					serviceProcessId, orderByComparator, true);

			array[1] = serviceProcessRole;

			array[2] = getByP_S_ID_PrevAndNext(session, serviceProcessRole,
					serviceProcessId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServiceProcessRole getByP_S_ID_PrevAndNext(Session session,
		ServiceProcessRole serviceProcessRole, long serviceProcessId,
		OrderByComparator<ServiceProcessRole> orderByComparator,
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

		query.append(_SQL_SELECT_SERVICEPROCESSROLE_WHERE);

		query.append(_FINDER_COLUMN_P_S_ID_SERVICEPROCESSID_2);

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
			query.append(ServiceProcessRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(serviceProcessId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceProcessRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceProcessRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service process roles where serviceProcessId = &#63; from the database.
	 *
	 * @param serviceProcessId the service process ID
	 */
	@Override
	public void removeByP_S_ID(long serviceProcessId) {
		for (ServiceProcessRole serviceProcessRole : findByP_S_ID(
				serviceProcessId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceProcessRole);
		}
	}

	/**
	 * Returns the number of service process roles where serviceProcessId = &#63;.
	 *
	 * @param serviceProcessId the service process ID
	 * @return the number of matching service process roles
	 */
	@Override
	public int countByP_S_ID(long serviceProcessId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_S_ID;

		Object[] finderArgs = new Object[] { serviceProcessId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEPROCESSROLE_WHERE);

			query.append(_FINDER_COLUMN_P_S_ID_SERVICEPROCESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(serviceProcessId);

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

	private static final String _FINDER_COLUMN_P_S_ID_SERVICEPROCESSID_2 = "serviceProcessRole.id.serviceProcessId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_CODE = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED,
			ServiceProcessRoleImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_CODE", new String[] { String.class.getName() },
			ServiceProcessRoleModelImpl.ROLECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CODE = new FinderPath(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_CODE",
			new String[] { String.class.getName() });

	/**
	 * Returns the service process role where roleCode = &#63; or throws a {@link NoSuchServiceProcessRoleException} if it could not be found.
	 *
	 * @param roleCode the role code
	 * @return the matching service process role
	 * @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole findByF_CODE(String roleCode)
		throws NoSuchServiceProcessRoleException {
		ServiceProcessRole serviceProcessRole = fetchByF_CODE(roleCode);

		if (serviceProcessRole == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("roleCode=");
			msg.append(roleCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServiceProcessRoleException(msg.toString());
		}

		return serviceProcessRole;
	}

	/**
	 * Returns the service process role where roleCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param roleCode the role code
	 * @return the matching service process role, or <code>null</code> if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole fetchByF_CODE(String roleCode) {
		return fetchByF_CODE(roleCode, true);
	}

	/**
	 * Returns the service process role where roleCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param roleCode the role code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service process role, or <code>null</code> if a matching service process role could not be found
	 */
	@Override
	public ServiceProcessRole fetchByF_CODE(String roleCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { roleCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_CODE,
					finderArgs, this);
		}

		if (result instanceof ServiceProcessRole) {
			ServiceProcessRole serviceProcessRole = (ServiceProcessRole)result;

			if (!Objects.equals(roleCode, serviceProcessRole.getRoleCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SERVICEPROCESSROLE_WHERE);

			boolean bindRoleCode = false;

			if (roleCode == null) {
				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_1);
			}
			else if (roleCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_3);
			}
			else {
				bindRoleCode = true;

				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_2);
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

				List<ServiceProcessRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServiceProcessRolePersistenceImpl.fetchByF_CODE(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceProcessRole serviceProcessRole = list.get(0);

					result = serviceProcessRole;

					cacheResult(serviceProcessRole);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, finderArgs);

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
			return (ServiceProcessRole)result;
		}
	}

	/**
	 * Removes the service process role where roleCode = &#63; from the database.
	 *
	 * @param roleCode the role code
	 * @return the service process role that was removed
	 */
	@Override
	public ServiceProcessRole removeByF_CODE(String roleCode)
		throws NoSuchServiceProcessRoleException {
		ServiceProcessRole serviceProcessRole = findByF_CODE(roleCode);

		return remove(serviceProcessRole);
	}

	/**
	 * Returns the number of service process roles where roleCode = &#63;.
	 *
	 * @param roleCode the role code
	 * @return the number of matching service process roles
	 */
	@Override
	public int countByF_CODE(String roleCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CODE;

		Object[] finderArgs = new Object[] { roleCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEPROCESSROLE_WHERE);

			boolean bindRoleCode = false;

			if (roleCode == null) {
				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_1);
			}
			else if (roleCode.equals("")) {
				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_3);
			}
			else {
				bindRoleCode = true;

				query.append(_FINDER_COLUMN_F_CODE_ROLECODE_2);
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

	private static final String _FINDER_COLUMN_F_CODE_ROLECODE_1 = "serviceProcessRole.roleCode IS NULL";
	private static final String _FINDER_COLUMN_F_CODE_ROLECODE_2 = "serviceProcessRole.roleCode = ?";
	private static final String _FINDER_COLUMN_F_CODE_ROLECODE_3 = "(serviceProcessRole.roleCode IS NULL OR serviceProcessRole.roleCode = '')";

	public ServiceProcessRolePersistenceImpl() {
		setModelClass(ServiceProcessRole.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("condition", "condition_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the service process role in the entity cache if it is enabled.
	 *
	 * @param serviceProcessRole the service process role
	 */
	@Override
	public void cacheResult(ServiceProcessRole serviceProcessRole) {
		entityCache.putResult(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleImpl.class, serviceProcessRole.getPrimaryKey(),
			serviceProcessRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE,
			new Object[] { serviceProcessRole.getRoleCode() },
			serviceProcessRole);

		serviceProcessRole.resetOriginalValues();
	}

	/**
	 * Caches the service process roles in the entity cache if it is enabled.
	 *
	 * @param serviceProcessRoles the service process roles
	 */
	@Override
	public void cacheResult(List<ServiceProcessRole> serviceProcessRoles) {
		for (ServiceProcessRole serviceProcessRole : serviceProcessRoles) {
			if (entityCache.getResult(
						ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
						ServiceProcessRoleImpl.class,
						serviceProcessRole.getPrimaryKey()) == null) {
				cacheResult(serviceProcessRole);
			}
			else {
				serviceProcessRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all service process roles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServiceProcessRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the service process role.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServiceProcessRole serviceProcessRole) {
		entityCache.removeResult(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleImpl.class, serviceProcessRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ServiceProcessRoleModelImpl)serviceProcessRole,
			true);
	}

	@Override
	public void clearCache(List<ServiceProcessRole> serviceProcessRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ServiceProcessRole serviceProcessRole : serviceProcessRoles) {
			entityCache.removeResult(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
				ServiceProcessRoleImpl.class, serviceProcessRole.getPrimaryKey());

			clearUniqueFindersCache((ServiceProcessRoleModelImpl)serviceProcessRole,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ServiceProcessRoleModelImpl serviceProcessRoleModelImpl) {
		Object[] args = new Object[] { serviceProcessRoleModelImpl.getRoleCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_CODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CODE, args,
			serviceProcessRoleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ServiceProcessRoleModelImpl serviceProcessRoleModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceProcessRoleModelImpl.getRoleCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}

		if ((serviceProcessRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_CODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceProcessRoleModelImpl.getOriginalRoleCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CODE, args);
		}
	}

	/**
	 * Creates a new service process role with the primary key. Does not add the service process role to the database.
	 *
	 * @param serviceProcessRolePK the primary key for the new service process role
	 * @return the new service process role
	 */
	@Override
	public ServiceProcessRole create(ServiceProcessRolePK serviceProcessRolePK) {
		ServiceProcessRole serviceProcessRole = new ServiceProcessRoleImpl();

		serviceProcessRole.setNew(true);
		serviceProcessRole.setPrimaryKey(serviceProcessRolePK);

		String uuid = PortalUUIDUtil.generate();

		serviceProcessRole.setUuid(uuid);

		return serviceProcessRole;
	}

	/**
	 * Removes the service process role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceProcessRolePK the primary key of the service process role
	 * @return the service process role that was removed
	 * @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	 */
	@Override
	public ServiceProcessRole remove(ServiceProcessRolePK serviceProcessRolePK)
		throws NoSuchServiceProcessRoleException {
		return remove((Serializable)serviceProcessRolePK);
	}

	/**
	 * Removes the service process role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the service process role
	 * @return the service process role that was removed
	 * @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	 */
	@Override
	public ServiceProcessRole remove(Serializable primaryKey)
		throws NoSuchServiceProcessRoleException {
		Session session = null;

		try {
			session = openSession();

			ServiceProcessRole serviceProcessRole = (ServiceProcessRole)session.get(ServiceProcessRoleImpl.class,
					primaryKey);

			if (serviceProcessRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServiceProcessRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(serviceProcessRole);
		}
		catch (NoSuchServiceProcessRoleException nsee) {
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
	protected ServiceProcessRole removeImpl(
		ServiceProcessRole serviceProcessRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serviceProcessRole)) {
				serviceProcessRole = (ServiceProcessRole)session.get(ServiceProcessRoleImpl.class,
						serviceProcessRole.getPrimaryKeyObj());
			}

			if (serviceProcessRole != null) {
				session.delete(serviceProcessRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (serviceProcessRole != null) {
			clearCache(serviceProcessRole);
		}

		return serviceProcessRole;
	}

	@Override
	public ServiceProcessRole updateImpl(ServiceProcessRole serviceProcessRole) {
		boolean isNew = serviceProcessRole.isNew();

		if (!(serviceProcessRole instanceof ServiceProcessRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(serviceProcessRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(serviceProcessRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in serviceProcessRole proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ServiceProcessRole implementation " +
				serviceProcessRole.getClass());
		}

		ServiceProcessRoleModelImpl serviceProcessRoleModelImpl = (ServiceProcessRoleModelImpl)serviceProcessRole;

		if (Validator.isNull(serviceProcessRole.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			serviceProcessRole.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (serviceProcessRole.isNew()) {
				session.save(serviceProcessRole);

				serviceProcessRole.setNew(false);
			}
			else {
				serviceProcessRole = (ServiceProcessRole)session.merge(serviceProcessRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ServiceProcessRoleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { serviceProcessRoleModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					serviceProcessRoleModelImpl.getServiceProcessId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_P_S_ID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((serviceProcessRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceProcessRoleModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { serviceProcessRoleModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((serviceProcessRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceProcessRoleModelImpl.getOriginalServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID,
					args);

				args = new Object[] {
						serviceProcessRoleModelImpl.getServiceProcessId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P_S_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S_ID,
					args);
			}
		}

		entityCache.putResult(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
			ServiceProcessRoleImpl.class, serviceProcessRole.getPrimaryKey(),
			serviceProcessRole, false);

		clearUniqueFindersCache(serviceProcessRoleModelImpl, false);
		cacheUniqueFindersCache(serviceProcessRoleModelImpl);

		serviceProcessRole.resetOriginalValues();

		return serviceProcessRole;
	}

	/**
	 * Returns the service process role with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the service process role
	 * @return the service process role
	 * @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	 */
	@Override
	public ServiceProcessRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServiceProcessRoleException {
		ServiceProcessRole serviceProcessRole = fetchByPrimaryKey(primaryKey);

		if (serviceProcessRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServiceProcessRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return serviceProcessRole;
	}

	/**
	 * Returns the service process role with the primary key or throws a {@link NoSuchServiceProcessRoleException} if it could not be found.
	 *
	 * @param serviceProcessRolePK the primary key of the service process role
	 * @return the service process role
	 * @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	 */
	@Override
	public ServiceProcessRole findByPrimaryKey(
		ServiceProcessRolePK serviceProcessRolePK)
		throws NoSuchServiceProcessRoleException {
		return findByPrimaryKey((Serializable)serviceProcessRolePK);
	}

	/**
	 * Returns the service process role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the service process role
	 * @return the service process role, or <code>null</code> if a service process role with the primary key could not be found
	 */
	@Override
	public ServiceProcessRole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
				ServiceProcessRoleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ServiceProcessRole serviceProcessRole = (ServiceProcessRole)serializable;

		if (serviceProcessRole == null) {
			Session session = null;

			try {
				session = openSession();

				serviceProcessRole = (ServiceProcessRole)session.get(ServiceProcessRoleImpl.class,
						primaryKey);

				if (serviceProcessRole != null) {
					cacheResult(serviceProcessRole);
				}
				else {
					entityCache.putResult(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
						ServiceProcessRoleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ServiceProcessRoleModelImpl.ENTITY_CACHE_ENABLED,
					ServiceProcessRoleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return serviceProcessRole;
	}

	/**
	 * Returns the service process role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceProcessRolePK the primary key of the service process role
	 * @return the service process role, or <code>null</code> if a service process role with the primary key could not be found
	 */
	@Override
	public ServiceProcessRole fetchByPrimaryKey(
		ServiceProcessRolePK serviceProcessRolePK) {
		return fetchByPrimaryKey((Serializable)serviceProcessRolePK);
	}

	@Override
	public Map<Serializable, ServiceProcessRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ServiceProcessRole> map = new HashMap<Serializable, ServiceProcessRole>();

		for (Serializable primaryKey : primaryKeys) {
			ServiceProcessRole serviceProcessRole = fetchByPrimaryKey(primaryKey);

			if (serviceProcessRole != null) {
				map.put(primaryKey, serviceProcessRole);
			}
		}

		return map;
	}

	/**
	 * Returns all the service process roles.
	 *
	 * @return the service process roles
	 */
	@Override
	public List<ServiceProcessRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service process roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service process roles
	 * @param end the upper bound of the range of service process roles (not inclusive)
	 * @return the range of service process roles
	 */
	@Override
	public List<ServiceProcessRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the service process roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service process roles
	 * @param end the upper bound of the range of service process roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service process roles
	 */
	@Override
	public List<ServiceProcessRole> findAll(int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service process roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service process roles
	 * @param end the upper bound of the range of service process roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of service process roles
	 */
	@Override
	public List<ServiceProcessRole> findAll(int start, int end,
		OrderByComparator<ServiceProcessRole> orderByComparator,
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

		List<ServiceProcessRole> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceProcessRole>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SERVICEPROCESSROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SERVICEPROCESSROLE;

				if (pagination) {
					sql = sql.concat(ServiceProcessRoleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ServiceProcessRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceProcessRole>)QueryUtil.list(q,
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
	 * Removes all the service process roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServiceProcessRole serviceProcessRole : findAll()) {
			remove(serviceProcessRole);
		}
	}

	/**
	 * Returns the number of service process roles.
	 *
	 * @return the number of service process roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SERVICEPROCESSROLE);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ServiceProcessRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the service process role persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ServiceProcessRoleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SERVICEPROCESSROLE = "SELECT serviceProcessRole FROM ServiceProcessRole serviceProcessRole";
	private static final String _SQL_SELECT_SERVICEPROCESSROLE_WHERE = "SELECT serviceProcessRole FROM ServiceProcessRole serviceProcessRole WHERE ";
	private static final String _SQL_COUNT_SERVICEPROCESSROLE = "SELECT COUNT(serviceProcessRole) FROM ServiceProcessRole serviceProcessRole";
	private static final String _SQL_COUNT_SERVICEPROCESSROLE_WHERE = "SELECT COUNT(serviceProcessRole) FROM ServiceProcessRole serviceProcessRole WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "serviceProcessRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ServiceProcessRole exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ServiceProcessRole exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ServiceProcessRolePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "condition"
			});
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(new String[] {
				"serviceProcessId", "roleId"
			});
}