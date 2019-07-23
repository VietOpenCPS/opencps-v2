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

import org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException;
import org.opencps.dossiermgt.model.DeliverableTypeRole;
import org.opencps.dossiermgt.model.impl.DeliverableTypeRoleImpl;
import org.opencps.dossiermgt.model.impl.DeliverableTypeRoleModelImpl;
import org.opencps.dossiermgt.service.persistence.DeliverableTypeRolePersistence;

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
 * The persistence implementation for the deliverable type role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DeliverableTypeRolePersistence
 * @see org.opencps.dossiermgt.service.persistence.DeliverableTypeRoleUtil
 * @generated
 */
@ProviderType
public class DeliverableTypeRolePersistenceImpl extends BasePersistenceImpl<DeliverableTypeRole>
	implements DeliverableTypeRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DeliverableTypeRoleUtil} to access the deliverable type role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DeliverableTypeRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DeliverableTypeRoleModelImpl.UUID_COLUMN_BITMASK |
			DeliverableTypeRoleModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the deliverable type roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable type roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @return the range of matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable type roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable type roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByUuid(String uuid, int start,
		int end, OrderByComparator<DeliverableTypeRole> orderByComparator,
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

		List<DeliverableTypeRole> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableTypeRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DeliverableTypeRole deliverableTypeRole : list) {
					if (!Objects.equals(uuid, deliverableTypeRole.getUuid())) {
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

			query.append(_SQL_SELECT_DELIVERABLETYPEROLE_WHERE);

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
				query.append(DeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
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
					list = (List<DeliverableTypeRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableTypeRole>)QueryUtil.list(q,
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
	 * Returns the first deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole findByUuid_First(String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = fetchByUuid_First(uuid,
				orderByComparator);

		if (deliverableTypeRole != null) {
			return deliverableTypeRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the first deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByUuid_First(String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		List<DeliverableTypeRole> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole findByUuid_Last(String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = fetchByUuid_Last(uuid,
				orderByComparator);

		if (deliverableTypeRole != null) {
			return deliverableTypeRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the last deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByUuid_Last(String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DeliverableTypeRole> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverable type roles before and after the current deliverable type role in the ordered set where uuid = &#63;.
	 *
	 * @param deliverableTypeRoleId the primary key of the current deliverable type role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	 */
	@Override
	public DeliverableTypeRole[] findByUuid_PrevAndNext(
		long deliverableTypeRoleId, String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = findByPrimaryKey(deliverableTypeRoleId);

		Session session = null;

		try {
			session = openSession();

			DeliverableTypeRole[] array = new DeliverableTypeRoleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, deliverableTypeRole,
					uuid, orderByComparator, true);

			array[1] = deliverableTypeRole;

			array[2] = getByUuid_PrevAndNext(session, deliverableTypeRole,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DeliverableTypeRole getByUuid_PrevAndNext(Session session,
		DeliverableTypeRole deliverableTypeRole, String uuid,
		OrderByComparator<DeliverableTypeRole> orderByComparator,
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

		query.append(_SQL_SELECT_DELIVERABLETYPEROLE_WHERE);

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
			query.append(DeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(deliverableTypeRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeliverableTypeRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverable type roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DeliverableTypeRole deliverableTypeRole : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(deliverableTypeRole);
		}
	}

	/**
	 * Returns the number of deliverable type roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching deliverable type roles
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DELIVERABLETYPEROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "deliverableTypeRole.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "deliverableTypeRole.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(deliverableTypeRole.uuid IS NULL OR deliverableTypeRole.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DeliverableTypeRoleModelImpl.UUID_COLUMN_BITMASK |
			DeliverableTypeRoleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the deliverable type role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableTypeRoleException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole findByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = fetchByUUID_G(uuid, groupId);

		if (deliverableTypeRole == null) {
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

			throw new NoSuchDeliverableTypeRoleException(msg.toString());
		}

		return deliverableTypeRole;
	}

	/**
	 * Returns the deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DeliverableTypeRole) {
			DeliverableTypeRole deliverableTypeRole = (DeliverableTypeRole)result;

			if (!Objects.equals(uuid, deliverableTypeRole.getUuid()) ||
					(groupId != deliverableTypeRole.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DELIVERABLETYPEROLE_WHERE);

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

				List<DeliverableTypeRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DeliverableTypeRole deliverableTypeRole = list.get(0);

					result = deliverableTypeRole;

					cacheResult(deliverableTypeRole);
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
			return (DeliverableTypeRole)result;
		}
	}

	/**
	 * Removes the deliverable type role where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the deliverable type role that was removed
	 */
	@Override
	public DeliverableTypeRole removeByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = findByUUID_G(uuid, groupId);

		return remove(deliverableTypeRole);
	}

	/**
	 * Returns the number of deliverable type roles where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching deliverable type roles
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLETYPEROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "deliverableTypeRole.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "deliverableTypeRole.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(deliverableTypeRole.uuid IS NULL OR deliverableTypeRole.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "deliverableTypeRole.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DeliverableTypeRoleModelImpl.UUID_COLUMN_BITMASK |
			DeliverableTypeRoleModelImpl.COMPANYID_COLUMN_BITMASK |
			DeliverableTypeRoleModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @return the range of matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator,
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

		List<DeliverableTypeRole> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableTypeRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DeliverableTypeRole deliverableTypeRole : list) {
					if (!Objects.equals(uuid, deliverableTypeRole.getUuid()) ||
							(companyId != deliverableTypeRole.getCompanyId())) {
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

			query.append(_SQL_SELECT_DELIVERABLETYPEROLE_WHERE);

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
				query.append(DeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
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
					list = (List<DeliverableTypeRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableTypeRole>)QueryUtil.list(q,
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
	 * Returns the first deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (deliverableTypeRole != null) {
			return deliverableTypeRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the first deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		List<DeliverableTypeRole> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (deliverableTypeRole != null) {
			return deliverableTypeRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the last deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DeliverableTypeRole> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverable type roles before and after the current deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deliverableTypeRoleId the primary key of the current deliverable type role
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	 */
	@Override
	public DeliverableTypeRole[] findByUuid_C_PrevAndNext(
		long deliverableTypeRoleId, String uuid, long companyId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = findByPrimaryKey(deliverableTypeRoleId);

		Session session = null;

		try {
			session = openSession();

			DeliverableTypeRole[] array = new DeliverableTypeRoleImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, deliverableTypeRole,
					uuid, companyId, orderByComparator, true);

			array[1] = deliverableTypeRole;

			array[2] = getByUuid_C_PrevAndNext(session, deliverableTypeRole,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DeliverableTypeRole getByUuid_C_PrevAndNext(Session session,
		DeliverableTypeRole deliverableTypeRole, String uuid, long companyId,
		OrderByComparator<DeliverableTypeRole> orderByComparator,
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

		query.append(_SQL_SELECT_DELIVERABLETYPEROLE_WHERE);

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
			query.append(DeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(deliverableTypeRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeliverableTypeRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverable type roles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DeliverableTypeRole deliverableTypeRole : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(deliverableTypeRole);
		}
	}

	/**
	 * Returns the number of deliverable type roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching deliverable type roles
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLETYPEROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "deliverableTypeRole.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "deliverableTypeRole.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(deliverableTypeRole.uuid IS NULL OR deliverableTypeRole.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "deliverableTypeRole.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_DELIVERABLETYPEID =
		new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_deliverableTypeId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID =
		new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_deliverableTypeId", new String[] { Long.class.getName() },
			DeliverableTypeRoleModelImpl.DELIVERABLETYPEID_COLUMN_BITMASK |
			DeliverableTypeRoleModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_DELIVERABLETYPEID = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_deliverableTypeId", new String[] { Long.class.getName() });

	/**
	 * Returns all the deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @return the matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId) {
		return findByF_deliverableTypeId(deliverableTypeId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @return the range of matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end) {
		return findByF_deliverableTypeId(deliverableTypeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return findByF_deliverableTypeId(deliverableTypeId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator,
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

		List<DeliverableTypeRole> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableTypeRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DeliverableTypeRole deliverableTypeRole : list) {
					if ((deliverableTypeId != deliverableTypeRole.getDeliverableTypeId())) {
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

			query.append(_SQL_SELECT_DELIVERABLETYPEROLE_WHERE);

			query.append(_FINDER_COLUMN_F_DELIVERABLETYPEID_DELIVERABLETYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(deliverableTypeId);

				if (!pagination) {
					list = (List<DeliverableTypeRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableTypeRole>)QueryUtil.list(q,
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
	 * Returns the first deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole findByF_deliverableTypeId_First(
		long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = fetchByF_deliverableTypeId_First(deliverableTypeId,
				orderByComparator);

		if (deliverableTypeRole != null) {
			return deliverableTypeRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("deliverableTypeId=");
		msg.append(deliverableTypeId);

		msg.append("}");

		throw new NoSuchDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the first deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByF_deliverableTypeId_First(
		long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		List<DeliverableTypeRole> list = findByF_deliverableTypeId(deliverableTypeId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole findByF_deliverableTypeId_Last(
		long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = fetchByF_deliverableTypeId_Last(deliverableTypeId,
				orderByComparator);

		if (deliverableTypeRole != null) {
			return deliverableTypeRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("deliverableTypeId=");
		msg.append(deliverableTypeId);

		msg.append("}");

		throw new NoSuchDeliverableTypeRoleException(msg.toString());
	}

	/**
	 * Returns the last deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByF_deliverableTypeId_Last(
		long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		int count = countByF_deliverableTypeId(deliverableTypeId);

		if (count == 0) {
			return null;
		}

		List<DeliverableTypeRole> list = findByF_deliverableTypeId(deliverableTypeId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverable type roles before and after the current deliverable type role in the ordered set where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeRoleId the primary key of the current deliverable type role
	 * @param deliverableTypeId the deliverable type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	 */
	@Override
	public DeliverableTypeRole[] findByF_deliverableTypeId_PrevAndNext(
		long deliverableTypeRoleId, long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = findByPrimaryKey(deliverableTypeRoleId);

		Session session = null;

		try {
			session = openSession();

			DeliverableTypeRole[] array = new DeliverableTypeRoleImpl[3];

			array[0] = getByF_deliverableTypeId_PrevAndNext(session,
					deliverableTypeRole, deliverableTypeId, orderByComparator,
					true);

			array[1] = deliverableTypeRole;

			array[2] = getByF_deliverableTypeId_PrevAndNext(session,
					deliverableTypeRole, deliverableTypeId, orderByComparator,
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

	protected DeliverableTypeRole getByF_deliverableTypeId_PrevAndNext(
		Session session, DeliverableTypeRole deliverableTypeRole,
		long deliverableTypeId,
		OrderByComparator<DeliverableTypeRole> orderByComparator,
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

		query.append(_SQL_SELECT_DELIVERABLETYPEROLE_WHERE);

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
			query.append(DeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(deliverableTypeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(deliverableTypeRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeliverableTypeRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverable type roles where deliverableTypeId = &#63; from the database.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 */
	@Override
	public void removeByF_deliverableTypeId(long deliverableTypeId) {
		for (DeliverableTypeRole deliverableTypeRole : findByF_deliverableTypeId(
				deliverableTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(deliverableTypeRole);
		}
	}

	/**
	 * Returns the number of deliverable type roles where deliverableTypeId = &#63;.
	 *
	 * @param deliverableTypeId the deliverable type ID
	 * @return the number of matching deliverable type roles
	 */
	@Override
	public int countByF_deliverableTypeId(long deliverableTypeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_DELIVERABLETYPEID;

		Object[] finderArgs = new Object[] { deliverableTypeId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DELIVERABLETYPEROLE_WHERE);

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
		"deliverableTypeRole.deliverableTypeId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_DID_RID = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_GID_DID_RID",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			DeliverableTypeRoleModelImpl.GROUPID_COLUMN_BITMASK |
			DeliverableTypeRoleModelImpl.DELIVERABLETYPEID_COLUMN_BITMASK |
			DeliverableTypeRoleModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_DID_RID = new FinderPath(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_DID_RID",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; or throws a {@link NoSuchDeliverableTypeRoleException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param deliverableTypeId the deliverable type ID
	 * @param roleId the role ID
	 * @return the matching deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole findByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = fetchByF_GID_DID_RID(groupId,
				deliverableTypeId, roleId);

		if (deliverableTypeRole == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", deliverableTypeId=");
			msg.append(deliverableTypeId);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDeliverableTypeRoleException(msg.toString());
		}

		return deliverableTypeRole;
	}

	/**
	 * Returns the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param deliverableTypeId the deliverable type ID
	 * @param roleId the role ID
	 * @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId) {
		return fetchByF_GID_DID_RID(groupId, deliverableTypeId, roleId, true);
	}

	/**
	 * Returns the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param deliverableTypeId the deliverable type ID
	 * @param roleId the role ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, deliverableTypeId, roleId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_DID_RID,
					finderArgs, this);
		}

		if (result instanceof DeliverableTypeRole) {
			DeliverableTypeRole deliverableTypeRole = (DeliverableTypeRole)result;

			if ((groupId != deliverableTypeRole.getGroupId()) ||
					(deliverableTypeId != deliverableTypeRole.getDeliverableTypeId()) ||
					(roleId != deliverableTypeRole.getRoleId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DELIVERABLETYPEROLE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DID_RID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_DID_RID_DELIVERABLETYPEID_2);

			query.append(_FINDER_COLUMN_F_GID_DID_RID_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(deliverableTypeId);

				qPos.add(roleId);

				List<DeliverableTypeRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DID_RID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DeliverableTypeRolePersistenceImpl.fetchByF_GID_DID_RID(long, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DeliverableTypeRole deliverableTypeRole = list.get(0);

					result = deliverableTypeRole;

					cacheResult(deliverableTypeRole);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DID_RID,
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
			return (DeliverableTypeRole)result;
		}
	}

	/**
	 * Removes the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param deliverableTypeId the deliverable type ID
	 * @param roleId the role ID
	 * @return the deliverable type role that was removed
	 */
	@Override
	public DeliverableTypeRole removeByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = findByF_GID_DID_RID(groupId,
				deliverableTypeId, roleId);

		return remove(deliverableTypeRole);
	}

	/**
	 * Returns the number of deliverable type roles where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deliverableTypeId the deliverable type ID
	 * @param roleId the role ID
	 * @return the number of matching deliverable type roles
	 */
	@Override
	public int countByF_GID_DID_RID(long groupId, long deliverableTypeId,
		long roleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_DID_RID;

		Object[] finderArgs = new Object[] { groupId, deliverableTypeId, roleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DELIVERABLETYPEROLE_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DID_RID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_DID_RID_DELIVERABLETYPEID_2);

			query.append(_FINDER_COLUMN_F_GID_DID_RID_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(deliverableTypeId);

				qPos.add(roleId);

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

	private static final String _FINDER_COLUMN_F_GID_DID_RID_GROUPID_2 = "deliverableTypeRole.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_DID_RID_DELIVERABLETYPEID_2 =
		"deliverableTypeRole.deliverableTypeId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_DID_RID_ROLEID_2 = "deliverableTypeRole.roleId = ?";

	public DeliverableTypeRolePersistenceImpl() {
		setModelClass(DeliverableTypeRole.class);

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
	 * Caches the deliverable type role in the entity cache if it is enabled.
	 *
	 * @param deliverableTypeRole the deliverable type role
	 */
	@Override
	public void cacheResult(DeliverableTypeRole deliverableTypeRole) {
		entityCache.putResult(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class, deliverableTypeRole.getPrimaryKey(),
			deliverableTypeRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				deliverableTypeRole.getUuid(), deliverableTypeRole.getGroupId()
			}, deliverableTypeRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DID_RID,
			new Object[] {
				deliverableTypeRole.getGroupId(),
				deliverableTypeRole.getDeliverableTypeId(),
				deliverableTypeRole.getRoleId()
			}, deliverableTypeRole);

		deliverableTypeRole.resetOriginalValues();
	}

	/**
	 * Caches the deliverable type roles in the entity cache if it is enabled.
	 *
	 * @param deliverableTypeRoles the deliverable type roles
	 */
	@Override
	public void cacheResult(List<DeliverableTypeRole> deliverableTypeRoles) {
		for (DeliverableTypeRole deliverableTypeRole : deliverableTypeRoles) {
			if (entityCache.getResult(
						DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
						DeliverableTypeRoleImpl.class,
						deliverableTypeRole.getPrimaryKey()) == null) {
				cacheResult(deliverableTypeRole);
			}
			else {
				deliverableTypeRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all deliverable type roles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DeliverableTypeRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the deliverable type role.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DeliverableTypeRole deliverableTypeRole) {
		entityCache.removeResult(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class, deliverableTypeRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DeliverableTypeRoleModelImpl)deliverableTypeRole,
			true);
	}

	@Override
	public void clearCache(List<DeliverableTypeRole> deliverableTypeRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DeliverableTypeRole deliverableTypeRole : deliverableTypeRoles) {
			entityCache.removeResult(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
				DeliverableTypeRoleImpl.class,
				deliverableTypeRole.getPrimaryKey());

			clearUniqueFindersCache((DeliverableTypeRoleModelImpl)deliverableTypeRole,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DeliverableTypeRoleModelImpl deliverableTypeRoleModelImpl) {
		Object[] args = new Object[] {
				deliverableTypeRoleModelImpl.getUuid(),
				deliverableTypeRoleModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			deliverableTypeRoleModelImpl, false);

		args = new Object[] {
				deliverableTypeRoleModelImpl.getGroupId(),
				deliverableTypeRoleModelImpl.getDeliverableTypeId(),
				deliverableTypeRoleModelImpl.getRoleId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_DID_RID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DID_RID, args,
			deliverableTypeRoleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DeliverableTypeRoleModelImpl deliverableTypeRoleModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					deliverableTypeRoleModelImpl.getUuid(),
					deliverableTypeRoleModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((deliverableTypeRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableTypeRoleModelImpl.getOriginalUuid(),
					deliverableTypeRoleModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					deliverableTypeRoleModelImpl.getGroupId(),
					deliverableTypeRoleModelImpl.getDeliverableTypeId(),
					deliverableTypeRoleModelImpl.getRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_DID_RID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DID_RID, args);
		}

		if ((deliverableTypeRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_DID_RID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableTypeRoleModelImpl.getOriginalGroupId(),
					deliverableTypeRoleModelImpl.getOriginalDeliverableTypeId(),
					deliverableTypeRoleModelImpl.getOriginalRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_DID_RID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DID_RID, args);
		}
	}

	/**
	 * Creates a new deliverable type role with the primary key. Does not add the deliverable type role to the database.
	 *
	 * @param deliverableTypeRoleId the primary key for the new deliverable type role
	 * @return the new deliverable type role
	 */
	@Override
	public DeliverableTypeRole create(long deliverableTypeRoleId) {
		DeliverableTypeRole deliverableTypeRole = new DeliverableTypeRoleImpl();

		deliverableTypeRole.setNew(true);
		deliverableTypeRole.setPrimaryKey(deliverableTypeRoleId);

		String uuid = PortalUUIDUtil.generate();

		deliverableTypeRole.setUuid(uuid);

		deliverableTypeRole.setCompanyId(companyProvider.getCompanyId());

		return deliverableTypeRole;
	}

	/**
	 * Removes the deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deliverableTypeRoleId the primary key of the deliverable type role
	 * @return the deliverable type role that was removed
	 * @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	 */
	@Override
	public DeliverableTypeRole remove(long deliverableTypeRoleId)
		throws NoSuchDeliverableTypeRoleException {
		return remove((Serializable)deliverableTypeRoleId);
	}

	/**
	 * Removes the deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the deliverable type role
	 * @return the deliverable type role that was removed
	 * @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	 */
	@Override
	public DeliverableTypeRole remove(Serializable primaryKey)
		throws NoSuchDeliverableTypeRoleException {
		Session session = null;

		try {
			session = openSession();

			DeliverableTypeRole deliverableTypeRole = (DeliverableTypeRole)session.get(DeliverableTypeRoleImpl.class,
					primaryKey);

			if (deliverableTypeRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeliverableTypeRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(deliverableTypeRole);
		}
		catch (NoSuchDeliverableTypeRoleException nsee) {
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
	protected DeliverableTypeRole removeImpl(
		DeliverableTypeRole deliverableTypeRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(deliverableTypeRole)) {
				deliverableTypeRole = (DeliverableTypeRole)session.get(DeliverableTypeRoleImpl.class,
						deliverableTypeRole.getPrimaryKeyObj());
			}

			if (deliverableTypeRole != null) {
				session.delete(deliverableTypeRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (deliverableTypeRole != null) {
			clearCache(deliverableTypeRole);
		}

		return deliverableTypeRole;
	}

	@Override
	public DeliverableTypeRole updateImpl(
		DeliverableTypeRole deliverableTypeRole) {
		boolean isNew = deliverableTypeRole.isNew();

		if (!(deliverableTypeRole instanceof DeliverableTypeRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(deliverableTypeRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(deliverableTypeRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in deliverableTypeRole proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DeliverableTypeRole implementation " +
				deliverableTypeRole.getClass());
		}

		DeliverableTypeRoleModelImpl deliverableTypeRoleModelImpl = (DeliverableTypeRoleModelImpl)deliverableTypeRole;

		if (Validator.isNull(deliverableTypeRole.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			deliverableTypeRole.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (deliverableTypeRole.getCreateDate() == null)) {
			if (serviceContext == null) {
				deliverableTypeRole.setCreateDate(now);
			}
			else {
				deliverableTypeRole.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!deliverableTypeRoleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				deliverableTypeRole.setModifiedDate(now);
			}
			else {
				deliverableTypeRole.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (deliverableTypeRole.isNew()) {
				session.save(deliverableTypeRole);

				deliverableTypeRole.setNew(false);
			}
			else {
				deliverableTypeRole = (DeliverableTypeRole)session.merge(deliverableTypeRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DeliverableTypeRoleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { deliverableTypeRoleModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					deliverableTypeRoleModelImpl.getUuid(),
					deliverableTypeRoleModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					deliverableTypeRoleModelImpl.getDeliverableTypeId()
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
			if ((deliverableTypeRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableTypeRoleModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { deliverableTypeRoleModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((deliverableTypeRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableTypeRoleModelImpl.getOriginalUuid(),
						deliverableTypeRoleModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						deliverableTypeRoleModelImpl.getUuid(),
						deliverableTypeRoleModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((deliverableTypeRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableTypeRoleModelImpl.getOriginalDeliverableTypeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DELIVERABLETYPEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID,
					args);

				args = new Object[] {
						deliverableTypeRoleModelImpl.getDeliverableTypeId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_DELIVERABLETYPEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_DELIVERABLETYPEID,
					args);
			}
		}

		entityCache.putResult(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeRoleImpl.class, deliverableTypeRole.getPrimaryKey(),
			deliverableTypeRole, false);

		clearUniqueFindersCache(deliverableTypeRoleModelImpl, false);
		cacheUniqueFindersCache(deliverableTypeRoleModelImpl);

		deliverableTypeRole.resetOriginalValues();

		return deliverableTypeRole;
	}

	/**
	 * Returns the deliverable type role with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliverable type role
	 * @return the deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	 */
	@Override
	public DeliverableTypeRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeliverableTypeRoleException {
		DeliverableTypeRole deliverableTypeRole = fetchByPrimaryKey(primaryKey);

		if (deliverableTypeRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeliverableTypeRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return deliverableTypeRole;
	}

	/**
	 * Returns the deliverable type role with the primary key or throws a {@link NoSuchDeliverableTypeRoleException} if it could not be found.
	 *
	 * @param deliverableTypeRoleId the primary key of the deliverable type role
	 * @return the deliverable type role
	 * @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	 */
	@Override
	public DeliverableTypeRole findByPrimaryKey(long deliverableTypeRoleId)
		throws NoSuchDeliverableTypeRoleException {
		return findByPrimaryKey((Serializable)deliverableTypeRoleId);
	}

	/**
	 * Returns the deliverable type role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliverable type role
	 * @return the deliverable type role, or <code>null</code> if a deliverable type role with the primary key could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
				DeliverableTypeRoleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DeliverableTypeRole deliverableTypeRole = (DeliverableTypeRole)serializable;

		if (deliverableTypeRole == null) {
			Session session = null;

			try {
				session = openSession();

				deliverableTypeRole = (DeliverableTypeRole)session.get(DeliverableTypeRoleImpl.class,
						primaryKey);

				if (deliverableTypeRole != null) {
					cacheResult(deliverableTypeRole);
				}
				else {
					entityCache.putResult(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
						DeliverableTypeRoleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableTypeRoleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return deliverableTypeRole;
	}

	/**
	 * Returns the deliverable type role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deliverableTypeRoleId the primary key of the deliverable type role
	 * @return the deliverable type role, or <code>null</code> if a deliverable type role with the primary key could not be found
	 */
	@Override
	public DeliverableTypeRole fetchByPrimaryKey(long deliverableTypeRoleId) {
		return fetchByPrimaryKey((Serializable)deliverableTypeRoleId);
	}

	@Override
	public Map<Serializable, DeliverableTypeRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DeliverableTypeRole> map = new HashMap<Serializable, DeliverableTypeRole>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DeliverableTypeRole deliverableTypeRole = fetchByPrimaryKey(primaryKey);

			if (deliverableTypeRole != null) {
				map.put(primaryKey, deliverableTypeRole);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableTypeRoleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DeliverableTypeRole)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DELIVERABLETYPEROLE_WHERE_PKS_IN);

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

			for (DeliverableTypeRole deliverableTypeRole : (List<DeliverableTypeRole>)q.list()) {
				map.put(deliverableTypeRole.getPrimaryKeyObj(),
					deliverableTypeRole);

				cacheResult(deliverableTypeRole);

				uncachedPrimaryKeys.remove(deliverableTypeRole.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DeliverableTypeRoleModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableTypeRoleImpl.class, primaryKey, nullModel);
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
	 * Returns all the deliverable type roles.
	 *
	 * @return the deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable type roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @return the range of deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable type roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findAll(int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable type roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverable type roles
	 * @param end the upper bound of the range of deliverable type roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of deliverable type roles
	 */
	@Override
	public List<DeliverableTypeRole> findAll(int start, int end,
		OrderByComparator<DeliverableTypeRole> orderByComparator,
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

		List<DeliverableTypeRole> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableTypeRole>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DELIVERABLETYPEROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DELIVERABLETYPEROLE;

				if (pagination) {
					sql = sql.concat(DeliverableTypeRoleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DeliverableTypeRole>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableTypeRole>)QueryUtil.list(q,
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
	 * Removes all the deliverable type roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DeliverableTypeRole deliverableTypeRole : findAll()) {
			remove(deliverableTypeRole);
		}
	}

	/**
	 * Returns the number of deliverable type roles.
	 *
	 * @return the number of deliverable type roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DELIVERABLETYPEROLE);

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
		return DeliverableTypeRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the deliverable type role persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DeliverableTypeRoleImpl.class.getName());
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
	private static final String _SQL_SELECT_DELIVERABLETYPEROLE = "SELECT deliverableTypeRole FROM DeliverableTypeRole deliverableTypeRole";
	private static final String _SQL_SELECT_DELIVERABLETYPEROLE_WHERE_PKS_IN = "SELECT deliverableTypeRole FROM DeliverableTypeRole deliverableTypeRole WHERE deliverableTypeRoleId IN (";
	private static final String _SQL_SELECT_DELIVERABLETYPEROLE_WHERE = "SELECT deliverableTypeRole FROM DeliverableTypeRole deliverableTypeRole WHERE ";
	private static final String _SQL_COUNT_DELIVERABLETYPEROLE = "SELECT COUNT(deliverableTypeRole) FROM DeliverableTypeRole deliverableTypeRole";
	private static final String _SQL_COUNT_DELIVERABLETYPEROLE_WHERE = "SELECT COUNT(deliverableTypeRole) FROM DeliverableTypeRole deliverableTypeRole WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "deliverableTypeRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeliverableTypeRole exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DeliverableTypeRole exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DeliverableTypeRolePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}