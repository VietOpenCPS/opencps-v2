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

package org.opencps.deliverable.service.persistence.impl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException;
import org.opencps.deliverable.model.OpenCPSDeliverableTypeRole;
import org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeRoleImpl;
import org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeRoleModelImpl;
import org.opencps.deliverable.service.persistence.OpenCPSDeliverableTypeRolePersistence;

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
 * The persistence implementation for the open cps deliverable type role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeRolePersistence
 * @see org.opencps.deliverable.service.persistence.OpenCPSDeliverableTypeRoleUtil
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeRolePersistenceImpl
	extends BasePersistenceImpl<OpenCPSDeliverableTypeRole>
	implements OpenCPSDeliverableTypeRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OpenCPSDeliverableTypeRoleUtil} to access the open cps deliverable type role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OpenCPSDeliverableTypeRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OpenCPSDeliverableTypeRoleModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeRoleModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the open cps deliverable type roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable type roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @return the range of matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable type roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable type roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByUuid(String uuid, int start,
		int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
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

		List<OpenCPSDeliverableTypeRole> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableTypeRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : list) {
					if (!Objects.equals(uuid,
								openCPSDeliverableTypeRole.getUuid())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE);

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
				query.append(OpenCPSDeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpenCPSDeliverableTypeRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableTypeRole>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole findByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = fetchByUuid_First(uuid,
				orderByComparator);

		if (openCPSDeliverableTypeRole != null) {
			return openCPSDeliverableTypeRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		List<OpenCPSDeliverableTypeRole> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole findByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = fetchByUuid_Last(uuid,
				orderByComparator);

		if (openCPSDeliverableTypeRole != null) {
			return openCPSDeliverableTypeRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverableTypeRole> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverable type roles before and after the current open cps deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param deliverableTypeRoleId the primary key of the current open cps deliverable type role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole[] findByUuid_PrevAndNext(
		long deliverableTypeRoleId, String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = findByPrimaryKey(deliverableTypeRoleId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableTypeRole[] array = new OpenCPSDeliverableTypeRoleImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					openCPSDeliverableTypeRole, uuid, orderByComparator, true);

			array[1] = openCPSDeliverableTypeRole;

			array[2] = getByUuid_PrevAndNext(session,
					openCPSDeliverableTypeRole, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpenCPSDeliverableTypeRole getByUuid_PrevAndNext(
		Session session, OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole,
		String uuid,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE);

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
			query.append(OpenCPSDeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverableTypeRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverableTypeRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverable type roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverableTypeRole);
		}
	}

	/**
	 * Returns the number of open cps deliverable type roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching open cps deliverable type roles
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLETYPEROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "openCPSDeliverableTypeRole.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "openCPSDeliverableTypeRole.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(openCPSDeliverableTypeRole.uuid IS NULL OR openCPSDeliverableTypeRole.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OpenCPSDeliverableTypeRoleModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeRoleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the open cps deliverable type role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableTypeRoleException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole findByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = fetchByUUID_G(uuid,
				groupId);

		if (openCPSDeliverableTypeRole == null) {
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

			throw new NoSuchOpenCPSDeliverableTypeRoleException(msg.toString());
		}

		return openCPSDeliverableTypeRole;
	}

	/**
	 * Returns the open cps deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the open cps deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OpenCPSDeliverableTypeRole) {
			OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = (OpenCPSDeliverableTypeRole)result;

			if (!Objects.equals(uuid, openCPSDeliverableTypeRole.getUuid()) ||
					(groupId != openCPSDeliverableTypeRole.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE);

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

				List<OpenCPSDeliverableTypeRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = list.get(0);

					result = openCPSDeliverableTypeRole;

					cacheResult(openCPSDeliverableTypeRole);
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
			return (OpenCPSDeliverableTypeRole)result;
		}
	}

	/**
	 * Removes the open cps deliverable type role where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the open cps deliverable type role that was removed
	 */
	@Override
	public OpenCPSDeliverableTypeRole removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = findByUUID_G(uuid,
				groupId);

		return remove(openCPSDeliverableTypeRole);
	}

	/**
	 * Returns the number of open cps deliverable type roles where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching open cps deliverable type roles
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLETYPEROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "openCPSDeliverableTypeRole.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "openCPSDeliverableTypeRole.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(openCPSDeliverableTypeRole.uuid IS NULL OR openCPSDeliverableTypeRole.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "openCPSDeliverableTypeRole.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OpenCPSDeliverableTypeRoleModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeRoleModelImpl.COMPANYID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeRoleModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @return the range of matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
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

		List<OpenCPSDeliverableTypeRole> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableTypeRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : list) {
					if (!Objects.equals(uuid,
								openCPSDeliverableTypeRole.getUuid()) ||
							(companyId != openCPSDeliverableTypeRole.getCompanyId())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE);

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
				query.append(OpenCPSDeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpenCPSDeliverableTypeRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableTypeRole>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (openCPSDeliverableTypeRole != null) {
			return openCPSDeliverableTypeRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		List<OpenCPSDeliverableTypeRole> list = findByUuid_C(uuid, companyId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (openCPSDeliverableTypeRole != null) {
			return openCPSDeliverableTypeRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverableTypeRole> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverable type roles before and after the current open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deliverableTypeRoleId the primary key of the current open cps deliverable type role
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole[] findByUuid_C_PrevAndNext(
		long deliverableTypeRoleId, String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = findByPrimaryKey(deliverableTypeRoleId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableTypeRole[] array = new OpenCPSDeliverableTypeRoleImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					openCPSDeliverableTypeRole, uuid, companyId,
					orderByComparator, true);

			array[1] = openCPSDeliverableTypeRole;

			array[2] = getByUuid_C_PrevAndNext(session,
					openCPSDeliverableTypeRole, uuid, companyId,
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

	protected OpenCPSDeliverableTypeRole getByUuid_C_PrevAndNext(
		Session session, OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole,
		String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE);

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
			query.append(OpenCPSDeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverableTypeRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverableTypeRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverable type roles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverableTypeRole);
		}
	}

	/**
	 * Returns the number of open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching open cps deliverable type roles
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLETYPEROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "openCPSDeliverableTypeRole.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "openCPSDeliverableTypeRole.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(openCPSDeliverableTypeRole.uuid IS NULL OR openCPSDeliverableTypeRole.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "openCPSDeliverableTypeRole.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DELIVERABLETYPEID =
		new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_deliverableTypeId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID =
		new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_deliverableTypeId", new String[] { Long.class.getName() },
			OpenCPSDeliverableTypeRoleModelImpl.DELIVERABLETYPEID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeRoleModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DELIVERABLETYPEID = new FinderPath(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_deliverableTypeId", new String[] { Long.class.getName() });

	/**
	 * Returns all the open cps deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @return the matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId) {
		return findByF_deliverableTypeId(deliverableTypeId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @return the range of matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end) {
		return findByF_deliverableTypeId(deliverableTypeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return findByF_deliverableTypeId(deliverableTypeId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID;
			finderArgs = new Object[] { deliverableTypeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DELIVERABLETYPEID;
			finderArgs = new Object[] {
					deliverableTypeId,
					
					start, end, orderByComparator
				};
		}

		List<OpenCPSDeliverableTypeRole> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableTypeRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : list) {
					if ((deliverableTypeId != openCPSDeliverableTypeRole.getDeliverableTypeId())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE);

			query.append(_FINDER_COLUMN_F_DELIVERABLETYPEID_DELIVERABLETYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpenCPSDeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(deliverableTypeId);

				if (!pagination) {
					list = (List<OpenCPSDeliverableTypeRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableTypeRole>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole findByF_deliverableTypeId_First(
		long deliverableTypeId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = fetchByF_deliverableTypeId_First(deliverableTypeId,
				orderByComparator);

		if (openCPSDeliverableTypeRole != null) {
			return openCPSDeliverableTypeRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("deliverableTypeId=");
		msg.append(deliverableTypeId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByF_deliverableTypeId_First(
		long deliverableTypeId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		List<OpenCPSDeliverableTypeRole> list = findByF_deliverableTypeId(deliverableTypeId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole findByF_deliverableTypeId_Last(
		long deliverableTypeId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = fetchByF_deliverableTypeId_Last(deliverableTypeId,
				orderByComparator);

		if (openCPSDeliverableTypeRole != null) {
			return openCPSDeliverableTypeRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("deliverableTypeId=");
		msg.append(deliverableTypeId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByF_deliverableTypeId_Last(
		long deliverableTypeId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		int count = countByF_deliverableTypeId(deliverableTypeId);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverableTypeRole> list = findByF_deliverableTypeId(deliverableTypeId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverable type roles before and after the current open cps deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeRoleId the primary key of the current open cps deliverable type role
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole[] findByF_deliverableTypeId_PrevAndNext(
		long deliverableTypeRoleId, long deliverableTypeId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = findByPrimaryKey(deliverableTypeRoleId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableTypeRole[] array = new OpenCPSDeliverableTypeRoleImpl[3];

			array[0] = getByF_deliverableTypeId_PrevAndNext(session,
					openCPSDeliverableTypeRole, deliverableTypeId,
					orderByComparator, true);

			array[1] = openCPSDeliverableTypeRole;

			array[2] = getByF_deliverableTypeId_PrevAndNext(session,
					openCPSDeliverableTypeRole, deliverableTypeId,
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

	protected OpenCPSDeliverableTypeRole getByF_deliverableTypeId_PrevAndNext(
		Session session, OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole,
		long deliverableTypeId,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE);

		query.append(_FINDER_COLUMN_F_DELIVERABLETYPEID_DELIVERABLETYPEID_2);

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
			query.append(OpenCPSDeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(deliverableTypeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverableTypeRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverableTypeRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverable type roles where deliverableTypeId = &#63; from the database.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 */
	@Override
	public void removeByF_deliverableTypeId(long deliverableTypeId) {
		for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : findByF_deliverableTypeId(
				deliverableTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverableTypeRole);
		}
	}

	/**
	 * Returns the number of open cps deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @return the number of matching open cps deliverable type roles
	 */
	@Override
	public int countByF_deliverableTypeId(long deliverableTypeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DELIVERABLETYPEID;

		Object[] finderArgs = new Object[] { deliverableTypeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLETYPEROLE_WHERE);

			query.append(_FINDER_COLUMN_F_DELIVERABLETYPEID_DELIVERABLETYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(deliverableTypeId);

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

	private static final String _FINDER_COLUMN_F_DELIVERABLETYPEID_DELIVERABLETYPEID_2 =
		"openCPSDeliverableTypeRole.deliverableTypeId = ?";

	public OpenCPSDeliverableTypeRolePersistenceImpl() {
		setModelClass(OpenCPSDeliverableTypeRole.class);

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
	 * Caches the open cps deliverable type role in the entity cache if it is enabled.
	 *
	 * @param openCPSDeliverableTypeRole the open cps deliverable type role
	 */
	@Override
	public void cacheResult(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		entityCache.putResult(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			openCPSDeliverableTypeRole.getPrimaryKey(),
			openCPSDeliverableTypeRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				openCPSDeliverableTypeRole.getUuid(),
				openCPSDeliverableTypeRole.getGroupId()
			}, openCPSDeliverableTypeRole);

		openCPSDeliverableTypeRole.resetOriginalValues();
	}

	/**
	 * Caches the open cps deliverable type roles in the entity cache if it is enabled.
	 *
	 * @param openCPSDeliverableTypeRoles the open cps deliverable type roles
	 */
	@Override
	public void cacheResult(
		List<OpenCPSDeliverableTypeRole> openCPSDeliverableTypeRoles) {
		for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : openCPSDeliverableTypeRoles) {
			if (entityCache.getResult(
						OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
						OpenCPSDeliverableTypeRoleImpl.class,
						openCPSDeliverableTypeRole.getPrimaryKey()) == null) {
				cacheResult(openCPSDeliverableTypeRole);
			}
			else {
				openCPSDeliverableTypeRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all open cps deliverable type roles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OpenCPSDeliverableTypeRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the open cps deliverable type role.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		entityCache.removeResult(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			openCPSDeliverableTypeRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OpenCPSDeliverableTypeRoleModelImpl)openCPSDeliverableTypeRole,
			true);
	}

	@Override
	public void clearCache(
		List<OpenCPSDeliverableTypeRole> openCPSDeliverableTypeRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : openCPSDeliverableTypeRoles) {
			entityCache.removeResult(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
				OpenCPSDeliverableTypeRoleImpl.class,
				openCPSDeliverableTypeRole.getPrimaryKey());

			clearUniqueFindersCache((OpenCPSDeliverableTypeRoleModelImpl)openCPSDeliverableTypeRole,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OpenCPSDeliverableTypeRoleModelImpl openCPSDeliverableTypeRoleModelImpl) {
		Object[] args = new Object[] {
				openCPSDeliverableTypeRoleModelImpl.getUuid(),
				openCPSDeliverableTypeRoleModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			openCPSDeliverableTypeRoleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OpenCPSDeliverableTypeRoleModelImpl openCPSDeliverableTypeRoleModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					openCPSDeliverableTypeRoleModelImpl.getUuid(),
					openCPSDeliverableTypeRoleModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((openCPSDeliverableTypeRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					openCPSDeliverableTypeRoleModelImpl.getOriginalUuid(),
					openCPSDeliverableTypeRoleModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new open cps deliverable type role with the primary key. Does not add the open cps deliverable type role to the database.
	 *
	 * @param deliverableTypeRoleId the primary key for the new open cps deliverable type role
	 * @return the new open cps deliverable type role
	 */
	@Override
	public OpenCPSDeliverableTypeRole create(long deliverableTypeRoleId) {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = new OpenCPSDeliverableTypeRoleImpl();

		openCPSDeliverableTypeRole.setNew(true);
		openCPSDeliverableTypeRole.setPrimaryKey(deliverableTypeRoleId);

		String uuid = PortalUUIDUtil.generate();

		openCPSDeliverableTypeRole.setUuid(uuid);

		openCPSDeliverableTypeRole.setCompanyId(companyProvider.getCompanyId());

		return openCPSDeliverableTypeRole;
	}

	/**
	 * Removes the open cps deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	 * @return the open cps deliverable type role that was removed
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole remove(long deliverableTypeRoleId)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		return remove((Serializable)deliverableTypeRoleId);
	}

	/**
	 * Removes the open cps deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the open cps deliverable type role
	 * @return the open cps deliverable type role that was removed
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole remove(Serializable primaryKey)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = (OpenCPSDeliverableTypeRole)session.get(OpenCPSDeliverableTypeRoleImpl.class,
					primaryKey);

			if (openCPSDeliverableTypeRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOpenCPSDeliverableTypeRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(openCPSDeliverableTypeRole);
		}
		catch (NoSuchOpenCPSDeliverableTypeRoleException nsee) {
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
	protected OpenCPSDeliverableTypeRole removeImpl(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(openCPSDeliverableTypeRole)) {
				openCPSDeliverableTypeRole = (OpenCPSDeliverableTypeRole)session.get(OpenCPSDeliverableTypeRoleImpl.class,
						openCPSDeliverableTypeRole.getPrimaryKeyObj());
			}

			if (openCPSDeliverableTypeRole != null) {
				session.delete(openCPSDeliverableTypeRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (openCPSDeliverableTypeRole != null) {
			clearCache(openCPSDeliverableTypeRole);
		}

		return openCPSDeliverableTypeRole;
	}

	@Override
	public OpenCPSDeliverableTypeRole updateImpl(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		boolean isNew = openCPSDeliverableTypeRole.isNew();

		if (!(openCPSDeliverableTypeRole instanceof OpenCPSDeliverableTypeRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(openCPSDeliverableTypeRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(openCPSDeliverableTypeRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in openCPSDeliverableTypeRole proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OpenCPSDeliverableTypeRole implementation " +
				openCPSDeliverableTypeRole.getClass());
		}

		OpenCPSDeliverableTypeRoleModelImpl openCPSDeliverableTypeRoleModelImpl = (OpenCPSDeliverableTypeRoleModelImpl)openCPSDeliverableTypeRole;

		if (Validator.isNull(openCPSDeliverableTypeRole.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			openCPSDeliverableTypeRole.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (openCPSDeliverableTypeRole.getCreateDate() == null)) {
			if (serviceContext == null) {
				openCPSDeliverableTypeRole.setCreateDate(now);
			}
			else {
				openCPSDeliverableTypeRole.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!openCPSDeliverableTypeRoleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				openCPSDeliverableTypeRole.setModifiedDate(now);
			}
			else {
				openCPSDeliverableTypeRole.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (openCPSDeliverableTypeRole.isNew()) {
				session.save(openCPSDeliverableTypeRole);

				openCPSDeliverableTypeRole.setNew(false);
			}
			else {
				openCPSDeliverableTypeRole = (OpenCPSDeliverableTypeRole)session.merge(openCPSDeliverableTypeRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OpenCPSDeliverableTypeRoleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					openCPSDeliverableTypeRoleModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					openCPSDeliverableTypeRoleModelImpl.getUuid(),
					openCPSDeliverableTypeRoleModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					openCPSDeliverableTypeRoleModelImpl.getDeliverableTypeId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DELIVERABLETYPEID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((openCPSDeliverableTypeRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableTypeRoleModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] {
						openCPSDeliverableTypeRoleModelImpl.getUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((openCPSDeliverableTypeRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableTypeRoleModelImpl.getOriginalUuid(),
						openCPSDeliverableTypeRoleModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						openCPSDeliverableTypeRoleModelImpl.getUuid(),
						openCPSDeliverableTypeRoleModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((openCPSDeliverableTypeRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableTypeRoleModelImpl.getOriginalDeliverableTypeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DELIVERABLETYPEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID,
					args);

				args = new Object[] {
						openCPSDeliverableTypeRoleModelImpl.getDeliverableTypeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DELIVERABLETYPEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID,
					args);
			}
		}

		entityCache.putResult(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeRoleImpl.class,
			openCPSDeliverableTypeRole.getPrimaryKey(),
			openCPSDeliverableTypeRole, false);

		clearUniqueFindersCache(openCPSDeliverableTypeRoleModelImpl, false);
		cacheUniqueFindersCache(openCPSDeliverableTypeRoleModelImpl);

		openCPSDeliverableTypeRole.resetOriginalValues();

		return openCPSDeliverableTypeRole;
	}

	/**
	 * Returns the open cps deliverable type role with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the open cps deliverable type role
	 * @return the open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = fetchByPrimaryKey(primaryKey);

		if (openCPSDeliverableTypeRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOpenCPSDeliverableTypeRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return openCPSDeliverableTypeRole;
	}

	/**
	 * Returns the open cps deliverable type role with the primary key or throws a {@link NoSuchOpenCPSDeliverableTypeRoleException} if it could not be found.
	 *
	 * @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	 * @return the open cps deliverable type role
	 * @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole findByPrimaryKey(
		long deliverableTypeRoleId)
		throws NoSuchOpenCPSDeliverableTypeRoleException {
		return findByPrimaryKey((Serializable)deliverableTypeRoleId);
	}

	/**
	 * Returns the open cps deliverable type role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the open cps deliverable type role
	 * @return the open cps deliverable type role, or <code>null</code> if a open cps deliverable type role with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
				OpenCPSDeliverableTypeRoleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = (OpenCPSDeliverableTypeRole)serializable;

		if (openCPSDeliverableTypeRole == null) {
			Session session = null;

			try {
				session = openSession();

				openCPSDeliverableTypeRole = (OpenCPSDeliverableTypeRole)session.get(OpenCPSDeliverableTypeRoleImpl.class,
						primaryKey);

				if (openCPSDeliverableTypeRole != null) {
					cacheResult(openCPSDeliverableTypeRole);
				}
				else {
					entityCache.putResult(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
						OpenCPSDeliverableTypeRoleImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableTypeRoleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return openCPSDeliverableTypeRole;
	}

	/**
	 * Returns the open cps deliverable type role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	 * @return the open cps deliverable type role, or <code>null</code> if a open cps deliverable type role with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableTypeRole fetchByPrimaryKey(
		long deliverableTypeRoleId) {
		return fetchByPrimaryKey((Serializable)deliverableTypeRoleId);
	}

	@Override
	public Map<Serializable, OpenCPSDeliverableTypeRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OpenCPSDeliverableTypeRole> map = new HashMap<Serializable, OpenCPSDeliverableTypeRole>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole = fetchByPrimaryKey(primaryKey);

			if (openCPSDeliverableTypeRole != null) {
				map.put(primaryKey, openCPSDeliverableTypeRole);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableTypeRoleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OpenCPSDeliverableTypeRole)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE_PKS_IN);

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

			for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : (List<OpenCPSDeliverableTypeRole>)q.list()) {
				map.put(openCPSDeliverableTypeRole.getPrimaryKeyObj(),
					openCPSDeliverableTypeRole);

				cacheResult(openCPSDeliverableTypeRole);

				uncachedPrimaryKeys.remove(openCPSDeliverableTypeRole.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OpenCPSDeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableTypeRoleImpl.class, primaryKey, nullModel);
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
	 * Returns all the open cps deliverable type roles.
	 *
	 * @return the open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable type roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @return the range of open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable type roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable type roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverable type roles
	 * @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of open cps deliverable type roles
	 */
	@Override
	public List<OpenCPSDeliverableTypeRole> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
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

		List<OpenCPSDeliverableTypeRole> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableTypeRole>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPEROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPENCPSDELIVERABLETYPEROLE;

				if (pagination) {
					sql = sql.concat(OpenCPSDeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpenCPSDeliverableTypeRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableTypeRole>)QueryUtil.list(q,
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
	 * Removes all the open cps deliverable type roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole : findAll()) {
			remove(openCPSDeliverableTypeRole);
		}
	}

	/**
	 * Returns the number of open cps deliverable type roles.
	 *
	 * @return the number of open cps deliverable type roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPENCPSDELIVERABLETYPEROLE);

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
		return OpenCPSDeliverableTypeRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the open cps deliverable type role persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OpenCPSDeliverableTypeRoleImpl.class.getName());
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
	private static final String _SQL_SELECT_OPENCPSDELIVERABLETYPEROLE = "SELECT openCPSDeliverableTypeRole FROM OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole";
	private static final String _SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE_PKS_IN =
		"SELECT openCPSDeliverableTypeRole FROM OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole WHERE deliverableTypeRoleId IN (";
	private static final String _SQL_SELECT_OPENCPSDELIVERABLETYPEROLE_WHERE = "SELECT openCPSDeliverableTypeRole FROM OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole WHERE ";
	private static final String _SQL_COUNT_OPENCPSDELIVERABLETYPEROLE = "SELECT COUNT(openCPSDeliverableTypeRole) FROM OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole";
	private static final String _SQL_COUNT_OPENCPSDELIVERABLETYPEROLE_WHERE = "SELECT COUNT(openCPSDeliverableTypeRole) FROM OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "openCPSDeliverableTypeRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpenCPSDeliverableTypeRole exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpenCPSDeliverableTypeRole exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OpenCPSDeliverableTypeRolePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}