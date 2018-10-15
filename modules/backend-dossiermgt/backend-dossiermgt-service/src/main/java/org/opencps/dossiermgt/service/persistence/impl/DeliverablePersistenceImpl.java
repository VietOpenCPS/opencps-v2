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

import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.impl.DeliverableImpl;
import org.opencps.dossiermgt.model.impl.DeliverableModelImpl;
import org.opencps.dossiermgt.service.persistence.DeliverablePersistence;

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
 * The persistence implementation for the deliverable service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DeliverablePersistence
 * @see org.opencps.dossiermgt.service.persistence.DeliverableUtil
 * @generated
 */
@ProviderType
public class DeliverablePersistenceImpl extends BasePersistenceImpl<Deliverable>
	implements DeliverablePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DeliverableUtil} to access the deliverable persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DeliverableImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DeliverableModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the deliverables where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching deliverables
	 */
	@Override
	public List<Deliverable> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverables where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @return the range of matching deliverables
	 */
	@Override
	public List<Deliverable> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverables where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverables
	 */
	@Override
	public List<Deliverable> findByUuid(String uuid, int start, int end,
		OrderByComparator<Deliverable> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverables where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverables
	 */
	@Override
	public List<Deliverable> findByUuid(String uuid, int start, int end,
		OrderByComparator<Deliverable> orderByComparator,
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

		List<Deliverable> list = null;

		if (retrieveFromCache) {
			list = (List<Deliverable>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Deliverable deliverable : list) {
					if (!Objects.equals(uuid, deliverable.getUuid())) {
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

			query.append(_SQL_SELECT_DELIVERABLE_WHERE);

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
				query.append(DeliverableModelImpl.ORDER_BY_JPQL);
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
					list = (List<Deliverable>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Deliverable>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByUuid_First(String uuid,
		OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByUuid_First(uuid, orderByComparator);

		if (deliverable != null) {
			return deliverable;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliverableException(msg.toString());
	}

	/**
	 * Returns the first deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByUuid_First(String uuid,
		OrderByComparator<Deliverable> orderByComparator) {
		List<Deliverable> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByUuid_Last(String uuid,
		OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByUuid_Last(uuid, orderByComparator);

		if (deliverable != null) {
			return deliverable;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliverableException(msg.toString());
	}

	/**
	 * Returns the last deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByUuid_Last(String uuid,
		OrderByComparator<Deliverable> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Deliverable> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverables before and after the current deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param deliverableId the primary key of the current deliverable
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable
	 * @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	 */
	@Override
	public Deliverable[] findByUuid_PrevAndNext(long deliverableId,
		String uuid, OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException {
		Deliverable deliverable = findByPrimaryKey(deliverableId);

		Session session = null;

		try {
			session = openSession();

			Deliverable[] array = new DeliverableImpl[3];

			array[0] = getByUuid_PrevAndNext(session, deliverable, uuid,
					orderByComparator, true);

			array[1] = deliverable;

			array[2] = getByUuid_PrevAndNext(session, deliverable, uuid,
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

	protected Deliverable getByUuid_PrevAndNext(Session session,
		Deliverable deliverable, String uuid,
		OrderByComparator<Deliverable> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DELIVERABLE_WHERE);

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
			query.append(DeliverableModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(deliverable);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Deliverable> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverables where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Deliverable deliverable : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(deliverable);
		}
	}

	/**
	 * Returns the number of deliverables where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching deliverables
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DELIVERABLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "deliverable.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "deliverable.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(deliverable.uuid IS NULL OR deliverable.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DeliverableModelImpl.UUID_COLUMN_BITMASK |
			DeliverableModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the deliverable where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByUUID_G(uuid, groupId);

		if (deliverable == null) {
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

			throw new NoSuchDeliverableException(msg.toString());
		}

		return deliverable;
	}

	/**
	 * Returns the deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Deliverable) {
			Deliverable deliverable = (Deliverable)result;

			if (!Objects.equals(uuid, deliverable.getUuid()) ||
					(groupId != deliverable.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DELIVERABLE_WHERE);

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

				List<Deliverable> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Deliverable deliverable = list.get(0);

					result = deliverable;

					cacheResult(deliverable);
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
			return (Deliverable)result;
		}
	}

	/**
	 * Removes the deliverable where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the deliverable that was removed
	 */
	@Override
	public Deliverable removeByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableException {
		Deliverable deliverable = findByUUID_G(uuid, groupId);

		return remove(deliverable);
	}

	/**
	 * Returns the number of deliverables where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching deliverables
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "deliverable.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "deliverable.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(deliverable.uuid IS NULL OR deliverable.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "deliverable.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DeliverableModelImpl.UUID_COLUMN_BITMASK |
			DeliverableModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching deliverables
	 */
	@Override
	public List<Deliverable> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @return the range of matching deliverables
	 */
	@Override
	public List<Deliverable> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverables
	 */
	@Override
	public List<Deliverable> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Deliverable> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverables
	 */
	@Override
	public List<Deliverable> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Deliverable> orderByComparator,
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

		List<Deliverable> list = null;

		if (retrieveFromCache) {
			list = (List<Deliverable>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Deliverable deliverable : list) {
					if (!Objects.equals(uuid, deliverable.getUuid()) ||
							(companyId != deliverable.getCompanyId())) {
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

			query.append(_SQL_SELECT_DELIVERABLE_WHERE);

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
				query.append(DeliverableModelImpl.ORDER_BY_JPQL);
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
					list = (List<Deliverable>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Deliverable>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (deliverable != null) {
			return deliverable;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliverableException(msg.toString());
	}

	/**
	 * Returns the first deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator) {
		List<Deliverable> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (deliverable != null) {
			return deliverable;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliverableException(msg.toString());
	}

	/**
	 * Returns the last deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Deliverable> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverables before and after the current deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deliverableId the primary key of the current deliverable
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable
	 * @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	 */
	@Override
	public Deliverable[] findByUuid_C_PrevAndNext(long deliverableId,
		String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException {
		Deliverable deliverable = findByPrimaryKey(deliverableId);

		Session session = null;

		try {
			session = openSession();

			Deliverable[] array = new DeliverableImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, deliverable, uuid,
					companyId, orderByComparator, true);

			array[1] = deliverable;

			array[2] = getByUuid_C_PrevAndNext(session, deliverable, uuid,
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

	protected Deliverable getByUuid_C_PrevAndNext(Session session,
		Deliverable deliverable, String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DELIVERABLE_WHERE);

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
			query.append(DeliverableModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(deliverable);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Deliverable> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverables where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Deliverable deliverable : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(deliverable);
		}
	}

	/**
	 * Returns the number of deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching deliverables
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "deliverable.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "deliverable.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(deliverable.uuid IS NULL OR deliverable.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "deliverable.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDID",
			new String[] { Long.class.getName() },
			DeliverableModelImpl.DELIVERABLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDID",
			new String[] { Long.class.getName() });

	/**
	 * Returns the deliverable where deliverableId = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	 *
	 * @param deliverableId the deliverable ID
	 * @return the matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByDID(long deliverableId)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByDID(deliverableId);

		if (deliverable == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("deliverableId=");
			msg.append(deliverableId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDeliverableException(msg.toString());
		}

		return deliverable;
	}

	/**
	 * Returns the deliverable where deliverableId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param deliverableId the deliverable ID
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByDID(long deliverableId) {
		return fetchByDID(deliverableId, true);
	}

	/**
	 * Returns the deliverable where deliverableId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param deliverableId the deliverable ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByDID(long deliverableId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { deliverableId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DID,
					finderArgs, this);
		}

		if (result instanceof Deliverable) {
			Deliverable deliverable = (Deliverable)result;

			if ((deliverableId != deliverable.getDeliverableId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DELIVERABLE_WHERE);

			query.append(_FINDER_COLUMN_DID_DELIVERABLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(deliverableId);

				List<Deliverable> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DID, finderArgs,
						list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DeliverablePersistenceImpl.fetchByDID(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Deliverable deliverable = list.get(0);

					result = deliverable;

					cacheResult(deliverable);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DID, finderArgs);

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
			return (Deliverable)result;
		}
	}

	/**
	 * Removes the deliverable where deliverableId = &#63; from the database.
	 *
	 * @param deliverableId the deliverable ID
	 * @return the deliverable that was removed
	 */
	@Override
	public Deliverable removeByDID(long deliverableId)
		throws NoSuchDeliverableException {
		Deliverable deliverable = findByDID(deliverableId);

		return remove(deliverable);
	}

	/**
	 * Returns the number of deliverables where deliverableId = &#63;.
	 *
	 * @param deliverableId the deliverable ID
	 * @return the number of matching deliverables
	 */
	@Override
	public int countByDID(long deliverableId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DID;

		Object[] finderArgs = new Object[] { deliverableId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DELIVERABLE_WHERE);

			query.append(_FINDER_COLUMN_DID_DELIVERABLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(deliverableId);

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

	private static final String _FINDER_COLUMN_DID_DELIVERABLEID_2 = "deliverable.deliverableId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_DID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_DID",
			new String[] { Long.class.getName(), Long.class.getName() },
			DeliverableModelImpl.GROUPID_COLUMN_BITMASK |
			DeliverableModelImpl.DELIVERABLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the deliverable where groupId = &#63; and deliverableId = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param deliverableId the deliverable ID
	 * @return the matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByG_DID(long groupId, long deliverableId)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByG_DID(groupId, deliverableId);

		if (deliverable == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", deliverableId=");
			msg.append(deliverableId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDeliverableException(msg.toString());
		}

		return deliverable;
	}

	/**
	 * Returns the deliverable where groupId = &#63; and deliverableId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param deliverableId the deliverable ID
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByG_DID(long groupId, long deliverableId) {
		return fetchByG_DID(groupId, deliverableId, true);
	}

	/**
	 * Returns the deliverable where groupId = &#63; and deliverableId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param deliverableId the deliverable ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByG_DID(long groupId, long deliverableId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, deliverableId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_DID,
					finderArgs, this);
		}

		if (result instanceof Deliverable) {
			Deliverable deliverable = (Deliverable)result;

			if ((groupId != deliverable.getGroupId()) ||
					(deliverableId != deliverable.getDeliverableId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DELIVERABLE_WHERE);

			query.append(_FINDER_COLUMN_G_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_DELIVERABLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(deliverableId);

				List<Deliverable> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_DID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DeliverablePersistenceImpl.fetchByG_DID(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Deliverable deliverable = list.get(0);

					result = deliverable;

					cacheResult(deliverable);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DID, finderArgs);

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
			return (Deliverable)result;
		}
	}

	/**
	 * Removes the deliverable where groupId = &#63; and deliverableId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param deliverableId the deliverable ID
	 * @return the deliverable that was removed
	 */
	@Override
	public Deliverable removeByG_DID(long groupId, long deliverableId)
		throws NoSuchDeliverableException {
		Deliverable deliverable = findByG_DID(groupId, deliverableId);

		return remove(deliverable);
	}

	/**
	 * Returns the number of deliverables where groupId = &#63; and deliverableId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param deliverableId the deliverable ID
	 * @return the number of matching deliverables
	 */
	@Override
	public int countByG_DID(long groupId, long deliverableId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DID;

		Object[] finderArgs = new Object[] { groupId, deliverableId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLE_WHERE);

			query.append(_FINDER_COLUMN_G_DID_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_DELIVERABLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(deliverableId);

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

	private static final String _FINDER_COLUMN_G_DID_GROUPID_2 = "deliverable.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_DELIVERABLEID_2 = "deliverable.deliverableId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_ID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_ID",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_ID",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			},
			DeliverableModelImpl.DELIVERABLESTATE_COLUMN_BITMASK |
			DeliverableModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			DeliverableModelImpl.DELIVERABLETYPE_COLUMN_BITMASK |
			DeliverableModelImpl.APPLICANTIDNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_ID = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_ID",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @return the matching deliverables
	 */
	@Override
	public List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo) {
		return findByG_ID(deliverableState, govAgencyCode, deliverableType,
			applicantIdNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @return the range of matching deliverables
	 */
	@Override
	public List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		int start, int end) {
		return findByG_ID(deliverableState, govAgencyCode, deliverableType,
			applicantIdNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverables
	 */
	@Override
	public List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		int start, int end, OrderByComparator<Deliverable> orderByComparator) {
		return findByG_ID(deliverableState, govAgencyCode, deliverableType,
			applicantIdNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverables
	 */
	@Override
	public List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		int start, int end, OrderByComparator<Deliverable> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID;
			finderArgs = new Object[] {
					deliverableState, govAgencyCode, deliverableType,
					applicantIdNo
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_ID;
			finderArgs = new Object[] {
					deliverableState, govAgencyCode, deliverableType,
					applicantIdNo,
					
					start, end, orderByComparator
				};
		}

		List<Deliverable> list = null;

		if (retrieveFromCache) {
			list = (List<Deliverable>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Deliverable deliverable : list) {
					if (!Objects.equals(deliverableState,
								deliverable.getDeliverableState()) ||
							!Objects.equals(govAgencyCode,
								deliverable.getGovAgencyCode()) ||
							!Objects.equals(deliverableType,
								deliverable.getDeliverableType()) ||
							!Objects.equals(applicantIdNo,
								deliverable.getApplicantIdNo())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_DELIVERABLE_WHERE);

			boolean bindDeliverableState = false;

			if (deliverableState == null) {
				query.append(_FINDER_COLUMN_G_ID_DELIVERABLESTATE_1);
			}
			else if (deliverableState.equals("")) {
				query.append(_FINDER_COLUMN_G_ID_DELIVERABLESTATE_3);
			}
			else {
				bindDeliverableState = true;

				query.append(_FINDER_COLUMN_G_ID_DELIVERABLESTATE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_ID_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_ID_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_ID_GOVAGENCYCODE_2);
			}

			boolean bindDeliverableType = false;

			if (deliverableType == null) {
				query.append(_FINDER_COLUMN_G_ID_DELIVERABLETYPE_1);
			}
			else if (deliverableType.equals("")) {
				query.append(_FINDER_COLUMN_G_ID_DELIVERABLETYPE_3);
			}
			else {
				bindDeliverableType = true;

				query.append(_FINDER_COLUMN_G_ID_DELIVERABLETYPE_2);
			}

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_ID_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_ID_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_ID_APPLICANTIDNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DeliverableModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeliverableState) {
					qPos.add(deliverableState);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDeliverableType) {
					qPos.add(deliverableType);
				}

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
				}

				if (!pagination) {
					list = (List<Deliverable>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Deliverable>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByG_ID_First(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByG_ID_First(deliverableState,
				govAgencyCode, deliverableType, applicantIdNo, orderByComparator);

		if (deliverable != null) {
			return deliverable;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("deliverableState=");
		msg.append(deliverableState);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", deliverableType=");
		msg.append(deliverableType);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append("}");

		throw new NoSuchDeliverableException(msg.toString());
	}

	/**
	 * Returns the first deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByG_ID_First(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		OrderByComparator<Deliverable> orderByComparator) {
		List<Deliverable> list = findByG_ID(deliverableState, govAgencyCode,
				deliverableType, applicantIdNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByG_ID_Last(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByG_ID_Last(deliverableState,
				govAgencyCode, deliverableType, applicantIdNo, orderByComparator);

		if (deliverable != null) {
			return deliverable;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("deliverableState=");
		msg.append(deliverableState);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", deliverableType=");
		msg.append(deliverableType);

		msg.append(", applicantIdNo=");
		msg.append(applicantIdNo);

		msg.append("}");

		throw new NoSuchDeliverableException(msg.toString());
	}

	/**
	 * Returns the last deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByG_ID_Last(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		OrderByComparator<Deliverable> orderByComparator) {
		int count = countByG_ID(deliverableState, govAgencyCode,
				deliverableType, applicantIdNo);

		if (count == 0) {
			return null;
		}

		List<Deliverable> list = findByG_ID(deliverableState, govAgencyCode,
				deliverableType, applicantIdNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverables before and after the current deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * @param deliverableId the primary key of the current deliverable
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable
	 * @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	 */
	@Override
	public Deliverable[] findByG_ID_PrevAndNext(long deliverableId,
		String deliverableState, String govAgencyCode, String deliverableType,
		String applicantIdNo, OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException {
		Deliverable deliverable = findByPrimaryKey(deliverableId);

		Session session = null;

		try {
			session = openSession();

			Deliverable[] array = new DeliverableImpl[3];

			array[0] = getByG_ID_PrevAndNext(session, deliverable,
					deliverableState, govAgencyCode, deliverableType,
					applicantIdNo, orderByComparator, true);

			array[1] = deliverable;

			array[2] = getByG_ID_PrevAndNext(session, deliverable,
					deliverableState, govAgencyCode, deliverableType,
					applicantIdNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Deliverable getByG_ID_PrevAndNext(Session session,
		Deliverable deliverable, String deliverableState, String govAgencyCode,
		String deliverableType, String applicantIdNo,
		OrderByComparator<Deliverable> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_DELIVERABLE_WHERE);

		boolean bindDeliverableState = false;

		if (deliverableState == null) {
			query.append(_FINDER_COLUMN_G_ID_DELIVERABLESTATE_1);
		}
		else if (deliverableState.equals("")) {
			query.append(_FINDER_COLUMN_G_ID_DELIVERABLESTATE_3);
		}
		else {
			bindDeliverableState = true;

			query.append(_FINDER_COLUMN_G_ID_DELIVERABLESTATE_2);
		}

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_G_ID_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_G_ID_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_G_ID_GOVAGENCYCODE_2);
		}

		boolean bindDeliverableType = false;

		if (deliverableType == null) {
			query.append(_FINDER_COLUMN_G_ID_DELIVERABLETYPE_1);
		}
		else if (deliverableType.equals("")) {
			query.append(_FINDER_COLUMN_G_ID_DELIVERABLETYPE_3);
		}
		else {
			bindDeliverableType = true;

			query.append(_FINDER_COLUMN_G_ID_DELIVERABLETYPE_2);
		}

		boolean bindApplicantIdNo = false;

		if (applicantIdNo == null) {
			query.append(_FINDER_COLUMN_G_ID_APPLICANTIDNO_1);
		}
		else if (applicantIdNo.equals("")) {
			query.append(_FINDER_COLUMN_G_ID_APPLICANTIDNO_3);
		}
		else {
			bindApplicantIdNo = true;

			query.append(_FINDER_COLUMN_G_ID_APPLICANTIDNO_2);
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
			query.append(DeliverableModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDeliverableState) {
			qPos.add(deliverableState);
		}

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (bindDeliverableType) {
			qPos.add(deliverableType);
		}

		if (bindApplicantIdNo) {
			qPos.add(applicantIdNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(deliverable);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Deliverable> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63; from the database.
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 */
	@Override
	public void removeByG_ID(String deliverableState, String govAgencyCode,
		String deliverableType, String applicantIdNo) {
		for (Deliverable deliverable : findByG_ID(deliverableState,
				govAgencyCode, deliverableType, applicantIdNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(deliverable);
		}
	}

	/**
	 * Returns the number of deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	 *
	 * @param deliverableState the deliverable state
	 * @param govAgencyCode the gov agency code
	 * @param deliverableType the deliverable type
	 * @param applicantIdNo the applicant ID no
	 * @return the number of matching deliverables
	 */
	@Override
	public int countByG_ID(String deliverableState, String govAgencyCode,
		String deliverableType, String applicantIdNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_ID;

		Object[] finderArgs = new Object[] {
				deliverableState, govAgencyCode, deliverableType, applicantIdNo
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DELIVERABLE_WHERE);

			boolean bindDeliverableState = false;

			if (deliverableState == null) {
				query.append(_FINDER_COLUMN_G_ID_DELIVERABLESTATE_1);
			}
			else if (deliverableState.equals("")) {
				query.append(_FINDER_COLUMN_G_ID_DELIVERABLESTATE_3);
			}
			else {
				bindDeliverableState = true;

				query.append(_FINDER_COLUMN_G_ID_DELIVERABLESTATE_2);
			}

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_ID_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_ID_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_ID_GOVAGENCYCODE_2);
			}

			boolean bindDeliverableType = false;

			if (deliverableType == null) {
				query.append(_FINDER_COLUMN_G_ID_DELIVERABLETYPE_1);
			}
			else if (deliverableType.equals("")) {
				query.append(_FINDER_COLUMN_G_ID_DELIVERABLETYPE_3);
			}
			else {
				bindDeliverableType = true;

				query.append(_FINDER_COLUMN_G_ID_DELIVERABLETYPE_2);
			}

			boolean bindApplicantIdNo = false;

			if (applicantIdNo == null) {
				query.append(_FINDER_COLUMN_G_ID_APPLICANTIDNO_1);
			}
			else if (applicantIdNo.equals("")) {
				query.append(_FINDER_COLUMN_G_ID_APPLICANTIDNO_3);
			}
			else {
				bindApplicantIdNo = true;

				query.append(_FINDER_COLUMN_G_ID_APPLICANTIDNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeliverableState) {
					qPos.add(deliverableState);
				}

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDeliverableType) {
					qPos.add(deliverableType);
				}

				if (bindApplicantIdNo) {
					qPos.add(applicantIdNo);
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

	private static final String _FINDER_COLUMN_G_ID_DELIVERABLESTATE_1 = "deliverable.deliverableState IS NULL AND ";
	private static final String _FINDER_COLUMN_G_ID_DELIVERABLESTATE_2 = "deliverable.deliverableState = ? AND ";
	private static final String _FINDER_COLUMN_G_ID_DELIVERABLESTATE_3 = "(deliverable.deliverableState IS NULL OR deliverable.deliverableState = '') AND ";
	private static final String _FINDER_COLUMN_G_ID_GOVAGENCYCODE_1 = "deliverable.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_ID_GOVAGENCYCODE_2 = "deliverable.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_ID_GOVAGENCYCODE_3 = "(deliverable.govAgencyCode IS NULL OR deliverable.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_ID_DELIVERABLETYPE_1 = "deliverable.deliverableType IS NULL AND ";
	private static final String _FINDER_COLUMN_G_ID_DELIVERABLETYPE_2 = "deliverable.deliverableType = ? AND ";
	private static final String _FINDER_COLUMN_G_ID_DELIVERABLETYPE_3 = "(deliverable.deliverableType IS NULL OR deliverable.deliverableType = '') AND ";
	private static final String _FINDER_COLUMN_G_ID_APPLICANTIDNO_1 = "deliverable.applicantIdNo IS NULL";
	private static final String _FINDER_COLUMN_G_ID_APPLICANTIDNO_2 = "deliverable.applicantIdNo = ?";
	private static final String _FINDER_COLUMN_G_ID_APPLICANTIDNO_3 = "(deliverable.applicantIdNo IS NULL OR deliverable.applicantIdNo = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_FB_DCODE = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByFB_DCODE",
			new String[] { String.class.getName() },
			DeliverableModelImpl.DELIVERABLECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FB_DCODE = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFB_DCODE",
			new String[] { String.class.getName() });

	/**
	 * Returns the deliverable where deliverableCode = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	 *
	 * @param deliverableCode the deliverable code
	 * @return the matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByFB_DCODE(String deliverableCode)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByFB_DCODE(deliverableCode);

		if (deliverable == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("deliverableCode=");
			msg.append(deliverableCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDeliverableException(msg.toString());
		}

		return deliverable;
	}

	/**
	 * Returns the deliverable where deliverableCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param deliverableCode the deliverable code
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByFB_DCODE(String deliverableCode) {
		return fetchByFB_DCODE(deliverableCode, true);
	}

	/**
	 * Returns the deliverable where deliverableCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param deliverableCode the deliverable code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByFB_DCODE(String deliverableCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { deliverableCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_FB_DCODE,
					finderArgs, this);
		}

		if (result instanceof Deliverable) {
			Deliverable deliverable = (Deliverable)result;

			if (!Objects.equals(deliverableCode,
						deliverable.getDeliverableCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DELIVERABLE_WHERE);

			boolean bindDeliverableCode = false;

			if (deliverableCode == null) {
				query.append(_FINDER_COLUMN_FB_DCODE_DELIVERABLECODE_1);
			}
			else if (deliverableCode.equals("")) {
				query.append(_FINDER_COLUMN_FB_DCODE_DELIVERABLECODE_3);
			}
			else {
				bindDeliverableCode = true;

				query.append(_FINDER_COLUMN_FB_DCODE_DELIVERABLECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeliverableCode) {
					qPos.add(deliverableCode);
				}

				List<Deliverable> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_FB_DCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DeliverablePersistenceImpl.fetchByFB_DCODE(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Deliverable deliverable = list.get(0);

					result = deliverable;

					cacheResult(deliverable);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_DCODE,
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
			return (Deliverable)result;
		}
	}

	/**
	 * Removes the deliverable where deliverableCode = &#63; from the database.
	 *
	 * @param deliverableCode the deliverable code
	 * @return the deliverable that was removed
	 */
	@Override
	public Deliverable removeByFB_DCODE(String deliverableCode)
		throws NoSuchDeliverableException {
		Deliverable deliverable = findByFB_DCODE(deliverableCode);

		return remove(deliverable);
	}

	/**
	 * Returns the number of deliverables where deliverableCode = &#63;.
	 *
	 * @param deliverableCode the deliverable code
	 * @return the number of matching deliverables
	 */
	@Override
	public int countByFB_DCODE(String deliverableCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FB_DCODE;

		Object[] finderArgs = new Object[] { deliverableCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DELIVERABLE_WHERE);

			boolean bindDeliverableCode = false;

			if (deliverableCode == null) {
				query.append(_FINDER_COLUMN_FB_DCODE_DELIVERABLECODE_1);
			}
			else if (deliverableCode.equals("")) {
				query.append(_FINDER_COLUMN_FB_DCODE_DELIVERABLECODE_3);
			}
			else {
				bindDeliverableCode = true;

				query.append(_FINDER_COLUMN_FB_DCODE_DELIVERABLECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeliverableCode) {
					qPos.add(deliverableCode);
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

	private static final String _FINDER_COLUMN_FB_DCODE_DELIVERABLECODE_1 = "deliverable.deliverableCode IS NULL";
	private static final String _FINDER_COLUMN_FB_DCODE_DELIVERABLECODE_2 = "deliverable.deliverableCode = ?";
	private static final String _FINDER_COLUMN_FB_DCODE_DELIVERABLECODE_3 = "(deliverable.deliverableCode IS NULL OR deliverable.deliverableCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_FB_DCODE_STATE = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, DeliverableImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByFB_DCODE_STATE",
			new String[] { String.class.getName(), String.class.getName() },
			DeliverableModelImpl.DELIVERABLECODE_COLUMN_BITMASK |
			DeliverableModelImpl.DELIVERABLESTATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FB_DCODE_STATE = new FinderPath(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFB_DCODE_STATE",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the deliverable where deliverableCode = &#63; and deliverableState = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	 *
	 * @param deliverableCode the deliverable code
	 * @param deliverableState the deliverable state
	 * @return the matching deliverable
	 * @throws NoSuchDeliverableException if a matching deliverable could not be found
	 */
	@Override
	public Deliverable findByFB_DCODE_STATE(String deliverableCode,
		String deliverableState) throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByFB_DCODE_STATE(deliverableCode,
				deliverableState);

		if (deliverable == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("deliverableCode=");
			msg.append(deliverableCode);

			msg.append(", deliverableState=");
			msg.append(deliverableState);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDeliverableException(msg.toString());
		}

		return deliverable;
	}

	/**
	 * Returns the deliverable where deliverableCode = &#63; and deliverableState = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param deliverableCode the deliverable code
	 * @param deliverableState the deliverable state
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByFB_DCODE_STATE(String deliverableCode,
		String deliverableState) {
		return fetchByFB_DCODE_STATE(deliverableCode, deliverableState, true);
	}

	/**
	 * Returns the deliverable where deliverableCode = &#63; and deliverableState = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param deliverableCode the deliverable code
	 * @param deliverableState the deliverable state
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	 */
	@Override
	public Deliverable fetchByFB_DCODE_STATE(String deliverableCode,
		String deliverableState, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { deliverableCode, deliverableState };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_FB_DCODE_STATE,
					finderArgs, this);
		}

		if (result instanceof Deliverable) {
			Deliverable deliverable = (Deliverable)result;

			if (!Objects.equals(deliverableCode,
						deliverable.getDeliverableCode()) ||
					!Objects.equals(deliverableState,
						deliverable.getDeliverableState())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DELIVERABLE_WHERE);

			boolean bindDeliverableCode = false;

			if (deliverableCode == null) {
				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLECODE_1);
			}
			else if (deliverableCode.equals("")) {
				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLECODE_3);
			}
			else {
				bindDeliverableCode = true;

				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLECODE_2);
			}

			boolean bindDeliverableState = false;

			if (deliverableState == null) {
				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLESTATE_1);
			}
			else if (deliverableState.equals("")) {
				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLESTATE_3);
			}
			else {
				bindDeliverableState = true;

				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLESTATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeliverableCode) {
					qPos.add(deliverableCode);
				}

				if (bindDeliverableState) {
					qPos.add(deliverableState);
				}

				List<Deliverable> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_FB_DCODE_STATE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DeliverablePersistenceImpl.fetchByFB_DCODE_STATE(String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Deliverable deliverable = list.get(0);

					result = deliverable;

					cacheResult(deliverable);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_DCODE_STATE,
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
			return (Deliverable)result;
		}
	}

	/**
	 * Removes the deliverable where deliverableCode = &#63; and deliverableState = &#63; from the database.
	 *
	 * @param deliverableCode the deliverable code
	 * @param deliverableState the deliverable state
	 * @return the deliverable that was removed
	 */
	@Override
	public Deliverable removeByFB_DCODE_STATE(String deliverableCode,
		String deliverableState) throws NoSuchDeliverableException {
		Deliverable deliverable = findByFB_DCODE_STATE(deliverableCode,
				deliverableState);

		return remove(deliverable);
	}

	/**
	 * Returns the number of deliverables where deliverableCode = &#63; and deliverableState = &#63;.
	 *
	 * @param deliverableCode the deliverable code
	 * @param deliverableState the deliverable state
	 * @return the number of matching deliverables
	 */
	@Override
	public int countByFB_DCODE_STATE(String deliverableCode,
		String deliverableState) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FB_DCODE_STATE;

		Object[] finderArgs = new Object[] { deliverableCode, deliverableState };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLE_WHERE);

			boolean bindDeliverableCode = false;

			if (deliverableCode == null) {
				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLECODE_1);
			}
			else if (deliverableCode.equals("")) {
				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLECODE_3);
			}
			else {
				bindDeliverableCode = true;

				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLECODE_2);
			}

			boolean bindDeliverableState = false;

			if (deliverableState == null) {
				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLESTATE_1);
			}
			else if (deliverableState.equals("")) {
				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLESTATE_3);
			}
			else {
				bindDeliverableState = true;

				query.append(_FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLESTATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDeliverableCode) {
					qPos.add(deliverableCode);
				}

				if (bindDeliverableState) {
					qPos.add(deliverableState);
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

	private static final String _FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLECODE_1 = "deliverable.deliverableCode IS NULL AND ";
	private static final String _FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLECODE_2 = "deliverable.deliverableCode = ? AND ";
	private static final String _FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLECODE_3 = "(deliverable.deliverableCode IS NULL OR deliverable.deliverableCode = '') AND ";
	private static final String _FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLESTATE_1 =
		"deliverable.deliverableState IS NULL";
	private static final String _FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLESTATE_2 =
		"deliverable.deliverableState = ?";
	private static final String _FINDER_COLUMN_FB_DCODE_STATE_DELIVERABLESTATE_3 =
		"(deliverable.deliverableState IS NULL OR deliverable.deliverableState = '')";

	public DeliverablePersistenceImpl() {
		setModelClass(Deliverable.class);

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
	 * Caches the deliverable in the entity cache if it is enabled.
	 *
	 * @param deliverable the deliverable
	 */
	@Override
	public void cacheResult(Deliverable deliverable) {
		entityCache.putResult(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableImpl.class, deliverable.getPrimaryKey(), deliverable);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { deliverable.getUuid(), deliverable.getGroupId() },
			deliverable);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DID,
			new Object[] { deliverable.getDeliverableId() }, deliverable);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DID,
			new Object[] {
				deliverable.getGroupId(), deliverable.getDeliverableId()
			}, deliverable);

		finderCache.putResult(FINDER_PATH_FETCH_BY_FB_DCODE,
			new Object[] { deliverable.getDeliverableCode() }, deliverable);

		finderCache.putResult(FINDER_PATH_FETCH_BY_FB_DCODE_STATE,
			new Object[] {
				deliverable.getDeliverableCode(),
				deliverable.getDeliverableState()
			}, deliverable);

		deliverable.resetOriginalValues();
	}

	/**
	 * Caches the deliverables in the entity cache if it is enabled.
	 *
	 * @param deliverables the deliverables
	 */
	@Override
	public void cacheResult(List<Deliverable> deliverables) {
		for (Deliverable deliverable : deliverables) {
			if (entityCache.getResult(
						DeliverableModelImpl.ENTITY_CACHE_ENABLED,
						DeliverableImpl.class, deliverable.getPrimaryKey()) == null) {
				cacheResult(deliverable);
			}
			else {
				deliverable.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all deliverables.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DeliverableImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the deliverable.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Deliverable deliverable) {
		entityCache.removeResult(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableImpl.class, deliverable.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DeliverableModelImpl)deliverable, true);
	}

	@Override
	public void clearCache(List<Deliverable> deliverables) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Deliverable deliverable : deliverables) {
			entityCache.removeResult(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
				DeliverableImpl.class, deliverable.getPrimaryKey());

			clearUniqueFindersCache((DeliverableModelImpl)deliverable, true);
		}
	}

	protected void cacheUniqueFindersCache(
		DeliverableModelImpl deliverableModelImpl) {
		Object[] args = new Object[] {
				deliverableModelImpl.getUuid(),
				deliverableModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			deliverableModelImpl, false);

		args = new Object[] { deliverableModelImpl.getDeliverableId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_DID, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DID, args,
			deliverableModelImpl, false);

		args = new Object[] {
				deliverableModelImpl.getGroupId(),
				deliverableModelImpl.getDeliverableId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_DID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DID, args,
			deliverableModelImpl, false);

		args = new Object[] { deliverableModelImpl.getDeliverableCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_FB_DCODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_FB_DCODE, args,
			deliverableModelImpl, false);

		args = new Object[] {
				deliverableModelImpl.getDeliverableCode(),
				deliverableModelImpl.getDeliverableState()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_FB_DCODE_STATE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_FB_DCODE_STATE, args,
			deliverableModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DeliverableModelImpl deliverableModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					deliverableModelImpl.getUuid(),
					deliverableModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((deliverableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableModelImpl.getOriginalUuid(),
					deliverableModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { deliverableModelImpl.getDeliverableId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID, args);
		}

		if ((deliverableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableModelImpl.getOriginalDeliverableId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					deliverableModelImpl.getGroupId(),
					deliverableModelImpl.getDeliverableId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DID, args);
		}

		if ((deliverableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_DID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableModelImpl.getOriginalGroupId(),
					deliverableModelImpl.getOriginalDeliverableId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					deliverableModelImpl.getDeliverableCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_DCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_DCODE, args);
		}

		if ((deliverableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_FB_DCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableModelImpl.getOriginalDeliverableCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_DCODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_DCODE, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					deliverableModelImpl.getDeliverableCode(),
					deliverableModelImpl.getDeliverableState()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_DCODE_STATE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_DCODE_STATE, args);
		}

		if ((deliverableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_FB_DCODE_STATE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableModelImpl.getOriginalDeliverableCode(),
					deliverableModelImpl.getOriginalDeliverableState()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FB_DCODE_STATE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_FB_DCODE_STATE, args);
		}
	}

	/**
	 * Creates a new deliverable with the primary key. Does not add the deliverable to the database.
	 *
	 * @param deliverableId the primary key for the new deliverable
	 * @return the new deliverable
	 */
	@Override
	public Deliverable create(long deliverableId) {
		Deliverable deliverable = new DeliverableImpl();

		deliverable.setNew(true);
		deliverable.setPrimaryKey(deliverableId);

		String uuid = PortalUUIDUtil.generate();

		deliverable.setUuid(uuid);

		deliverable.setCompanyId(companyProvider.getCompanyId());

		return deliverable;
	}

	/**
	 * Removes the deliverable with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deliverableId the primary key of the deliverable
	 * @return the deliverable that was removed
	 * @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	 */
	@Override
	public Deliverable remove(long deliverableId)
		throws NoSuchDeliverableException {
		return remove((Serializable)deliverableId);
	}

	/**
	 * Removes the deliverable with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the deliverable
	 * @return the deliverable that was removed
	 * @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	 */
	@Override
	public Deliverable remove(Serializable primaryKey)
		throws NoSuchDeliverableException {
		Session session = null;

		try {
			session = openSession();

			Deliverable deliverable = (Deliverable)session.get(DeliverableImpl.class,
					primaryKey);

			if (deliverable == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeliverableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(deliverable);
		}
		catch (NoSuchDeliverableException nsee) {
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
	protected Deliverable removeImpl(Deliverable deliverable) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(deliverable)) {
				deliverable = (Deliverable)session.get(DeliverableImpl.class,
						deliverable.getPrimaryKeyObj());
			}

			if (deliverable != null) {
				session.delete(deliverable);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (deliverable != null) {
			clearCache(deliverable);
		}

		return deliverable;
	}

	@Override
	public Deliverable updateImpl(Deliverable deliverable) {
		boolean isNew = deliverable.isNew();

		if (!(deliverable instanceof DeliverableModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(deliverable.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(deliverable);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in deliverable proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Deliverable implementation " +
				deliverable.getClass());
		}

		DeliverableModelImpl deliverableModelImpl = (DeliverableModelImpl)deliverable;

		if (Validator.isNull(deliverable.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			deliverable.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (deliverable.getCreateDate() == null)) {
			if (serviceContext == null) {
				deliverable.setCreateDate(now);
			}
			else {
				deliverable.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!deliverableModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				deliverable.setModifiedDate(now);
			}
			else {
				deliverable.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (deliverable.isNew()) {
				session.save(deliverable);

				deliverable.setNew(false);
			}
			else {
				deliverable = (Deliverable)session.merge(deliverable);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DeliverableModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { deliverableModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					deliverableModelImpl.getUuid(),
					deliverableModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					deliverableModelImpl.getDeliverableState(),
					deliverableModelImpl.getGovAgencyCode(),
					deliverableModelImpl.getDeliverableType(),
					deliverableModelImpl.getApplicantIdNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((deliverableModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { deliverableModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((deliverableModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableModelImpl.getOriginalUuid(),
						deliverableModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						deliverableModelImpl.getUuid(),
						deliverableModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((deliverableModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableModelImpl.getOriginalDeliverableState(),
						deliverableModelImpl.getOriginalGovAgencyCode(),
						deliverableModelImpl.getOriginalDeliverableType(),
						deliverableModelImpl.getOriginalApplicantIdNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID,
					args);

				args = new Object[] {
						deliverableModelImpl.getDeliverableState(),
						deliverableModelImpl.getGovAgencyCode(),
						deliverableModelImpl.getDeliverableType(),
						deliverableModelImpl.getApplicantIdNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_ID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ID,
					args);
			}
		}

		entityCache.putResult(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableImpl.class, deliverable.getPrimaryKey(), deliverable,
			false);

		clearUniqueFindersCache(deliverableModelImpl, false);
		cacheUniqueFindersCache(deliverableModelImpl);

		deliverable.resetOriginalValues();

		return deliverable;
	}

	/**
	 * Returns the deliverable with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliverable
	 * @return the deliverable
	 * @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	 */
	@Override
	public Deliverable findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeliverableException {
		Deliverable deliverable = fetchByPrimaryKey(primaryKey);

		if (deliverable == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeliverableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return deliverable;
	}

	/**
	 * Returns the deliverable with the primary key or throws a {@link NoSuchDeliverableException} if it could not be found.
	 *
	 * @param deliverableId the primary key of the deliverable
	 * @return the deliverable
	 * @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	 */
	@Override
	public Deliverable findByPrimaryKey(long deliverableId)
		throws NoSuchDeliverableException {
		return findByPrimaryKey((Serializable)deliverableId);
	}

	/**
	 * Returns the deliverable with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliverable
	 * @return the deliverable, or <code>null</code> if a deliverable with the primary key could not be found
	 */
	@Override
	public Deliverable fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
				DeliverableImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Deliverable deliverable = (Deliverable)serializable;

		if (deliverable == null) {
			Session session = null;

			try {
				session = openSession();

				deliverable = (Deliverable)session.get(DeliverableImpl.class,
						primaryKey);

				if (deliverable != null) {
					cacheResult(deliverable);
				}
				else {
					entityCache.putResult(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
						DeliverableImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return deliverable;
	}

	/**
	 * Returns the deliverable with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deliverableId the primary key of the deliverable
	 * @return the deliverable, or <code>null</code> if a deliverable with the primary key could not be found
	 */
	@Override
	public Deliverable fetchByPrimaryKey(long deliverableId) {
		return fetchByPrimaryKey((Serializable)deliverableId);
	}

	@Override
	public Map<Serializable, Deliverable> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Deliverable> map = new HashMap<Serializable, Deliverable>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Deliverable deliverable = fetchByPrimaryKey(primaryKey);

			if (deliverable != null) {
				map.put(primaryKey, deliverable);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Deliverable)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DELIVERABLE_WHERE_PKS_IN);

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

			for (Deliverable deliverable : (List<Deliverable>)q.list()) {
				map.put(deliverable.getPrimaryKeyObj(), deliverable);

				cacheResult(deliverable);

				uncachedPrimaryKeys.remove(deliverable.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DeliverableModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableImpl.class, primaryKey, nullModel);
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
	 * Returns all the deliverables.
	 *
	 * @return the deliverables
	 */
	@Override
	public List<Deliverable> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @return the range of deliverables
	 */
	@Override
	public List<Deliverable> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of deliverables
	 */
	@Override
	public List<Deliverable> findAll(int start, int end,
		OrderByComparator<Deliverable> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverables
	 * @param end the upper bound of the range of deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of deliverables
	 */
	@Override
	public List<Deliverable> findAll(int start, int end,
		OrderByComparator<Deliverable> orderByComparator,
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

		List<Deliverable> list = null;

		if (retrieveFromCache) {
			list = (List<Deliverable>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DELIVERABLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DELIVERABLE;

				if (pagination) {
					sql = sql.concat(DeliverableModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Deliverable>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Deliverable>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the deliverables from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Deliverable deliverable : findAll()) {
			remove(deliverable);
		}
	}

	/**
	 * Returns the number of deliverables.
	 *
	 * @return the number of deliverables
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DELIVERABLE);

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
		return DeliverableModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the deliverable persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DeliverableImpl.class.getName());
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
	private static final String _SQL_SELECT_DELIVERABLE = "SELECT deliverable FROM Deliverable deliverable";
	private static final String _SQL_SELECT_DELIVERABLE_WHERE_PKS_IN = "SELECT deliverable FROM Deliverable deliverable WHERE deliverableId IN (";
	private static final String _SQL_SELECT_DELIVERABLE_WHERE = "SELECT deliverable FROM Deliverable deliverable WHERE ";
	private static final String _SQL_COUNT_DELIVERABLE = "SELECT COUNT(deliverable) FROM Deliverable deliverable";
	private static final String _SQL_COUNT_DELIVERABLE_WHERE = "SELECT COUNT(deliverable) FROM Deliverable deliverable WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "deliverable.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Deliverable exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Deliverable exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DeliverablePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}