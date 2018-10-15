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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchDeliverableLogException;
import org.opencps.dossiermgt.model.DeliverableLog;
import org.opencps.dossiermgt.model.impl.DeliverableLogImpl;
import org.opencps.dossiermgt.model.impl.DeliverableLogModelImpl;
import org.opencps.dossiermgt.service.persistence.DeliverableLogPersistence;

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
 * The persistence implementation for the deliverable log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DeliverableLogPersistence
 * @see org.opencps.dossiermgt.service.persistence.DeliverableLogUtil
 * @generated
 */
@ProviderType
public class DeliverableLogPersistenceImpl extends BasePersistenceImpl<DeliverableLog>
	implements DeliverableLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DeliverableLogUtil} to access the deliverable log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DeliverableLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			DeliverableLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			DeliverableLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			DeliverableLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			DeliverableLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DeliverableLogModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the deliverable logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching deliverable logs
	 */
	@Override
	public List<DeliverableLog> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverable logs
	 * @param end the upper bound of the range of deliverable logs (not inclusive)
	 * @return the range of matching deliverable logs
	 */
	@Override
	public List<DeliverableLog> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverable logs
	 * @param end the upper bound of the range of deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverable logs
	 */
	@Override
	public List<DeliverableLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverable logs
	 * @param end the upper bound of the range of deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverable logs
	 */
	@Override
	public List<DeliverableLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator,
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

		List<DeliverableLog> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DeliverableLog deliverableLog : list) {
					if (!Objects.equals(uuid, deliverableLog.getUuid())) {
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

			query.append(_SQL_SELECT_DELIVERABLELOG_WHERE);

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
				query.append(DeliverableLogModelImpl.ORDER_BY_JPQL);
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
					list = (List<DeliverableLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableLog>)QueryUtil.list(q,
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
	 * Returns the first deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable log
	 * @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog findByUuid_First(String uuid,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException {
		DeliverableLog deliverableLog = fetchByUuid_First(uuid,
				orderByComparator);

		if (deliverableLog != null) {
			return deliverableLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliverableLogException(msg.toString());
	}

	/**
	 * Returns the first deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog fetchByUuid_First(String uuid,
		OrderByComparator<DeliverableLog> orderByComparator) {
		List<DeliverableLog> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable log
	 * @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog findByUuid_Last(String uuid,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException {
		DeliverableLog deliverableLog = fetchByUuid_Last(uuid, orderByComparator);

		if (deliverableLog != null) {
			return deliverableLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliverableLogException(msg.toString());
	}

	/**
	 * Returns the last deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog fetchByUuid_Last(String uuid,
		OrderByComparator<DeliverableLog> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DeliverableLog> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverable logs before and after the current deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param deliverableLogId the primary key of the current deliverable log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable log
	 * @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	 */
	@Override
	public DeliverableLog[] findByUuid_PrevAndNext(long deliverableLogId,
		String uuid, OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException {
		DeliverableLog deliverableLog = findByPrimaryKey(deliverableLogId);

		Session session = null;

		try {
			session = openSession();

			DeliverableLog[] array = new DeliverableLogImpl[3];

			array[0] = getByUuid_PrevAndNext(session, deliverableLog, uuid,
					orderByComparator, true);

			array[1] = deliverableLog;

			array[2] = getByUuid_PrevAndNext(session, deliverableLog, uuid,
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

	protected DeliverableLog getByUuid_PrevAndNext(Session session,
		DeliverableLog deliverableLog, String uuid,
		OrderByComparator<DeliverableLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DELIVERABLELOG_WHERE);

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
			query.append(DeliverableLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(deliverableLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeliverableLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverable logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DeliverableLog deliverableLog : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(deliverableLog);
		}
	}

	/**
	 * Returns the number of deliverable logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching deliverable logs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DELIVERABLELOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "deliverableLog.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "deliverableLog.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(deliverableLog.uuid IS NULL OR deliverableLog.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			DeliverableLogImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DeliverableLogModelImpl.UUID_COLUMN_BITMASK |
			DeliverableLogModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the deliverable log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableLogException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliverable log
	 * @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog findByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableLogException {
		DeliverableLog deliverableLog = fetchByUUID_G(uuid, groupId);

		if (deliverableLog == null) {
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

			throw new NoSuchDeliverableLogException(msg.toString());
		}

		return deliverableLog;
	}

	/**
	 * Returns the deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DeliverableLog) {
			DeliverableLog deliverableLog = (DeliverableLog)result;

			if (!Objects.equals(uuid, deliverableLog.getUuid()) ||
					(groupId != deliverableLog.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DELIVERABLELOG_WHERE);

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

				List<DeliverableLog> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DeliverableLog deliverableLog = list.get(0);

					result = deliverableLog;

					cacheResult(deliverableLog);
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
			return (DeliverableLog)result;
		}
	}

	/**
	 * Removes the deliverable log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the deliverable log that was removed
	 */
	@Override
	public DeliverableLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableLogException {
		DeliverableLog deliverableLog = findByUUID_G(uuid, groupId);

		return remove(deliverableLog);
	}

	/**
	 * Returns the number of deliverable logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching deliverable logs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLELOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "deliverableLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "deliverableLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(deliverableLog.uuid IS NULL OR deliverableLog.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "deliverableLog.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			DeliverableLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			DeliverableLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DeliverableLogModelImpl.UUID_COLUMN_BITMASK |
			DeliverableLogModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching deliverable logs
	 */
	@Override
	public List<DeliverableLog> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverable logs
	 * @param end the upper bound of the range of deliverable logs (not inclusive)
	 * @return the range of matching deliverable logs
	 */
	@Override
	public List<DeliverableLog> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverable logs
	 * @param end the upper bound of the range of deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverable logs
	 */
	@Override
	public List<DeliverableLog> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DeliverableLog> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverable logs
	 * @param end the upper bound of the range of deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverable logs
	 */
	@Override
	public List<DeliverableLog> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator,
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

		List<DeliverableLog> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DeliverableLog deliverableLog : list) {
					if (!Objects.equals(uuid, deliverableLog.getUuid()) ||
							(companyId != deliverableLog.getCompanyId())) {
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

			query.append(_SQL_SELECT_DELIVERABLELOG_WHERE);

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
				query.append(DeliverableLogModelImpl.ORDER_BY_JPQL);
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
					list = (List<DeliverableLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableLog>)QueryUtil.list(q,
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
	 * Returns the first deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable log
	 * @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException {
		DeliverableLog deliverableLog = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (deliverableLog != null) {
			return deliverableLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliverableLogException(msg.toString());
	}

	/**
	 * Returns the first deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DeliverableLog> orderByComparator) {
		List<DeliverableLog> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable log
	 * @throws NoSuchDeliverableLogException if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException {
		DeliverableLog deliverableLog = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (deliverableLog != null) {
			return deliverableLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliverableLogException(msg.toString());
	}

	/**
	 * Returns the last deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable log, or <code>null</code> if a matching deliverable log could not be found
	 */
	@Override
	public DeliverableLog fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DeliverableLog> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DeliverableLog> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverable logs before and after the current deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deliverableLogId the primary key of the current deliverable log
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable log
	 * @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	 */
	@Override
	public DeliverableLog[] findByUuid_C_PrevAndNext(long deliverableLogId,
		String uuid, long companyId,
		OrderByComparator<DeliverableLog> orderByComparator)
		throws NoSuchDeliverableLogException {
		DeliverableLog deliverableLog = findByPrimaryKey(deliverableLogId);

		Session session = null;

		try {
			session = openSession();

			DeliverableLog[] array = new DeliverableLogImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, deliverableLog, uuid,
					companyId, orderByComparator, true);

			array[1] = deliverableLog;

			array[2] = getByUuid_C_PrevAndNext(session, deliverableLog, uuid,
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

	protected DeliverableLog getByUuid_C_PrevAndNext(Session session,
		DeliverableLog deliverableLog, String uuid, long companyId,
		OrderByComparator<DeliverableLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DELIVERABLELOG_WHERE);

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
			query.append(DeliverableLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(deliverableLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeliverableLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverable logs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DeliverableLog deliverableLog : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(deliverableLog);
		}
	}

	/**
	 * Returns the number of deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching deliverable logs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLELOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "deliverableLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "deliverableLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(deliverableLog.uuid IS NULL OR deliverableLog.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "deliverableLog.companyId = ?";

	public DeliverableLogPersistenceImpl() {
		setModelClass(DeliverableLog.class);

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
	 * Caches the deliverable log in the entity cache if it is enabled.
	 *
	 * @param deliverableLog the deliverable log
	 */
	@Override
	public void cacheResult(DeliverableLog deliverableLog) {
		entityCache.putResult(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogImpl.class, deliverableLog.getPrimaryKey(),
			deliverableLog);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { deliverableLog.getUuid(), deliverableLog.getGroupId() },
			deliverableLog);

		deliverableLog.resetOriginalValues();
	}

	/**
	 * Caches the deliverable logs in the entity cache if it is enabled.
	 *
	 * @param deliverableLogs the deliverable logs
	 */
	@Override
	public void cacheResult(List<DeliverableLog> deliverableLogs) {
		for (DeliverableLog deliverableLog : deliverableLogs) {
			if (entityCache.getResult(
						DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
						DeliverableLogImpl.class, deliverableLog.getPrimaryKey()) == null) {
				cacheResult(deliverableLog);
			}
			else {
				deliverableLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all deliverable logs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DeliverableLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the deliverable log.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DeliverableLog deliverableLog) {
		entityCache.removeResult(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogImpl.class, deliverableLog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DeliverableLogModelImpl)deliverableLog, true);
	}

	@Override
	public void clearCache(List<DeliverableLog> deliverableLogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DeliverableLog deliverableLog : deliverableLogs) {
			entityCache.removeResult(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
				DeliverableLogImpl.class, deliverableLog.getPrimaryKey());

			clearUniqueFindersCache((DeliverableLogModelImpl)deliverableLog,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DeliverableLogModelImpl deliverableLogModelImpl) {
		Object[] args = new Object[] {
				deliverableLogModelImpl.getUuid(),
				deliverableLogModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			deliverableLogModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DeliverableLogModelImpl deliverableLogModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					deliverableLogModelImpl.getUuid(),
					deliverableLogModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((deliverableLogModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableLogModelImpl.getOriginalUuid(),
					deliverableLogModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new deliverable log with the primary key. Does not add the deliverable log to the database.
	 *
	 * @param deliverableLogId the primary key for the new deliverable log
	 * @return the new deliverable log
	 */
	@Override
	public DeliverableLog create(long deliverableLogId) {
		DeliverableLog deliverableLog = new DeliverableLogImpl();

		deliverableLog.setNew(true);
		deliverableLog.setPrimaryKey(deliverableLogId);

		String uuid = PortalUUIDUtil.generate();

		deliverableLog.setUuid(uuid);

		deliverableLog.setCompanyId(companyProvider.getCompanyId());

		return deliverableLog;
	}

	/**
	 * Removes the deliverable log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deliverableLogId the primary key of the deliverable log
	 * @return the deliverable log that was removed
	 * @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	 */
	@Override
	public DeliverableLog remove(long deliverableLogId)
		throws NoSuchDeliverableLogException {
		return remove((Serializable)deliverableLogId);
	}

	/**
	 * Removes the deliverable log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the deliverable log
	 * @return the deliverable log that was removed
	 * @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	 */
	@Override
	public DeliverableLog remove(Serializable primaryKey)
		throws NoSuchDeliverableLogException {
		Session session = null;

		try {
			session = openSession();

			DeliverableLog deliverableLog = (DeliverableLog)session.get(DeliverableLogImpl.class,
					primaryKey);

			if (deliverableLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeliverableLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(deliverableLog);
		}
		catch (NoSuchDeliverableLogException nsee) {
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
	protected DeliverableLog removeImpl(DeliverableLog deliverableLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(deliverableLog)) {
				deliverableLog = (DeliverableLog)session.get(DeliverableLogImpl.class,
						deliverableLog.getPrimaryKeyObj());
			}

			if (deliverableLog != null) {
				session.delete(deliverableLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (deliverableLog != null) {
			clearCache(deliverableLog);
		}

		return deliverableLog;
	}

	@Override
	public DeliverableLog updateImpl(DeliverableLog deliverableLog) {
		boolean isNew = deliverableLog.isNew();

		if (!(deliverableLog instanceof DeliverableLogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(deliverableLog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(deliverableLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in deliverableLog proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DeliverableLog implementation " +
				deliverableLog.getClass());
		}

		DeliverableLogModelImpl deliverableLogModelImpl = (DeliverableLogModelImpl)deliverableLog;

		if (Validator.isNull(deliverableLog.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			deliverableLog.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (deliverableLog.getCreateDate() == null)) {
			if (serviceContext == null) {
				deliverableLog.setCreateDate(now);
			}
			else {
				deliverableLog.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!deliverableLogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				deliverableLog.setModifiedDate(now);
			}
			else {
				deliverableLog.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (deliverableLog.isNew()) {
				session.save(deliverableLog);

				deliverableLog.setNew(false);
			}
			else {
				deliverableLog = (DeliverableLog)session.merge(deliverableLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DeliverableLogModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { deliverableLogModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					deliverableLogModelImpl.getUuid(),
					deliverableLogModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((deliverableLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableLogModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { deliverableLogModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((deliverableLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableLogModelImpl.getOriginalUuid(),
						deliverableLogModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						deliverableLogModelImpl.getUuid(),
						deliverableLogModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableLogImpl.class, deliverableLog.getPrimaryKey(),
			deliverableLog, false);

		clearUniqueFindersCache(deliverableLogModelImpl, false);
		cacheUniqueFindersCache(deliverableLogModelImpl);

		deliverableLog.resetOriginalValues();

		return deliverableLog;
	}

	/**
	 * Returns the deliverable log with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliverable log
	 * @return the deliverable log
	 * @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	 */
	@Override
	public DeliverableLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeliverableLogException {
		DeliverableLog deliverableLog = fetchByPrimaryKey(primaryKey);

		if (deliverableLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeliverableLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return deliverableLog;
	}

	/**
	 * Returns the deliverable log with the primary key or throws a {@link NoSuchDeliverableLogException} if it could not be found.
	 *
	 * @param deliverableLogId the primary key of the deliverable log
	 * @return the deliverable log
	 * @throws NoSuchDeliverableLogException if a deliverable log with the primary key could not be found
	 */
	@Override
	public DeliverableLog findByPrimaryKey(long deliverableLogId)
		throws NoSuchDeliverableLogException {
		return findByPrimaryKey((Serializable)deliverableLogId);
	}

	/**
	 * Returns the deliverable log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliverable log
	 * @return the deliverable log, or <code>null</code> if a deliverable log with the primary key could not be found
	 */
	@Override
	public DeliverableLog fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
				DeliverableLogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DeliverableLog deliverableLog = (DeliverableLog)serializable;

		if (deliverableLog == null) {
			Session session = null;

			try {
				session = openSession();

				deliverableLog = (DeliverableLog)session.get(DeliverableLogImpl.class,
						primaryKey);

				if (deliverableLog != null) {
					cacheResult(deliverableLog);
				}
				else {
					entityCache.putResult(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
						DeliverableLogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableLogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return deliverableLog;
	}

	/**
	 * Returns the deliverable log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deliverableLogId the primary key of the deliverable log
	 * @return the deliverable log, or <code>null</code> if a deliverable log with the primary key could not be found
	 */
	@Override
	public DeliverableLog fetchByPrimaryKey(long deliverableLogId) {
		return fetchByPrimaryKey((Serializable)deliverableLogId);
	}

	@Override
	public Map<Serializable, DeliverableLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DeliverableLog> map = new HashMap<Serializable, DeliverableLog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DeliverableLog deliverableLog = fetchByPrimaryKey(primaryKey);

			if (deliverableLog != null) {
				map.put(primaryKey, deliverableLog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableLogImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DeliverableLog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DELIVERABLELOG_WHERE_PKS_IN);

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

			for (DeliverableLog deliverableLog : (List<DeliverableLog>)q.list()) {
				map.put(deliverableLog.getPrimaryKeyObj(), deliverableLog);

				cacheResult(deliverableLog);

				uncachedPrimaryKeys.remove(deliverableLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableLogImpl.class, primaryKey, nullModel);
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
	 * Returns all the deliverable logs.
	 *
	 * @return the deliverable logs
	 */
	@Override
	public List<DeliverableLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverable logs
	 * @param end the upper bound of the range of deliverable logs (not inclusive)
	 * @return the range of deliverable logs
	 */
	@Override
	public List<DeliverableLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverable logs
	 * @param end the upper bound of the range of deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of deliverable logs
	 */
	@Override
	public List<DeliverableLog> findAll(int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverable logs
	 * @param end the upper bound of the range of deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of deliverable logs
	 */
	@Override
	public List<DeliverableLog> findAll(int start, int end,
		OrderByComparator<DeliverableLog> orderByComparator,
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

		List<DeliverableLog> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableLog>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DELIVERABLELOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DELIVERABLELOG;

				if (pagination) {
					sql = sql.concat(DeliverableLogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DeliverableLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableLog>)QueryUtil.list(q,
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
	 * Removes all the deliverable logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DeliverableLog deliverableLog : findAll()) {
			remove(deliverableLog);
		}
	}

	/**
	 * Returns the number of deliverable logs.
	 *
	 * @return the number of deliverable logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DELIVERABLELOG);

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
		return DeliverableLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the deliverable log persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DeliverableLogImpl.class.getName());
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
	private static final String _SQL_SELECT_DELIVERABLELOG = "SELECT deliverableLog FROM DeliverableLog deliverableLog";
	private static final String _SQL_SELECT_DELIVERABLELOG_WHERE_PKS_IN = "SELECT deliverableLog FROM DeliverableLog deliverableLog WHERE deliverableLogId IN (";
	private static final String _SQL_SELECT_DELIVERABLELOG_WHERE = "SELECT deliverableLog FROM DeliverableLog deliverableLog WHERE ";
	private static final String _SQL_COUNT_DELIVERABLELOG = "SELECT COUNT(deliverableLog) FROM DeliverableLog deliverableLog";
	private static final String _SQL_COUNT_DELIVERABLELOG_WHERE = "SELECT COUNT(deliverableLog) FROM DeliverableLog deliverableLog WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "deliverableLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeliverableLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DeliverableLog exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DeliverableLogPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}