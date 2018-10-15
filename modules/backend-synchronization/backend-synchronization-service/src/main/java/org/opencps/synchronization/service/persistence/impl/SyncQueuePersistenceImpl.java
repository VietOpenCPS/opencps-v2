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

package org.opencps.synchronization.service.persistence.impl;

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

import org.opencps.synchronization.exception.NoSuchSyncQueueException;
import org.opencps.synchronization.model.SyncQueue;
import org.opencps.synchronization.model.impl.SyncQueueImpl;
import org.opencps.synchronization.model.impl.SyncQueueModelImpl;
import org.opencps.synchronization.service.persistence.SyncQueuePersistence;

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
 * The persistence implementation for the sync queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see SyncQueuePersistence
 * @see org.opencps.synchronization.service.persistence.SyncQueueUtil
 * @generated
 */
@ProviderType
public class SyncQueuePersistenceImpl extends BasePersistenceImpl<SyncQueue>
	implements SyncQueuePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SyncQueueUtil} to access the sync queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SyncQueueImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SyncQueueModelImpl.UUID_COLUMN_BITMASK |
			SyncQueueModelImpl.MODIFIEDDATE_COLUMN_BITMASK |
			SyncQueueModelImpl.PRIORITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the sync queues where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching sync queues
	 */
	@Override
	public List<SyncQueue> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync queues where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @return the range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync queues where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByUuid(String uuid, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync queues where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByUuid(String uuid, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator,
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

		List<SyncQueue> list = null;

		if (retrieveFromCache) {
			list = (List<SyncQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncQueue syncQueue : list) {
					if (!Objects.equals(uuid, syncQueue.getUuid())) {
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

			query.append(_SQL_SELECT_SYNCQUEUE_WHERE);

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
				query.append(SyncQueueModelImpl.ORDER_BY_JPQL);
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
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync queue
	 * @throws NoSuchSyncQueueException if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue findByUuid_First(String uuid,
		OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByUuid_First(uuid, orderByComparator);

		if (syncQueue != null) {
			return syncQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSyncQueueException(msg.toString());
	}

	/**
	 * Returns the first sync queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByUuid_First(String uuid,
		OrderByComparator<SyncQueue> orderByComparator) {
		List<SyncQueue> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync queue
	 * @throws NoSuchSyncQueueException if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue findByUuid_Last(String uuid,
		OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByUuid_Last(uuid, orderByComparator);

		if (syncQueue != null) {
			return syncQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSyncQueueException(msg.toString());
	}

	/**
	 * Returns the last sync queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByUuid_Last(String uuid,
		OrderByComparator<SyncQueue> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SyncQueue> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync queues before and after the current sync queue in the ordered set where uuid = &#63;.
	 *
	 * @param syncQueueId the primary key of the current sync queue
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync queue
	 * @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue[] findByUuid_PrevAndNext(long syncQueueId, String uuid,
		OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = findByPrimaryKey(syncQueueId);

		Session session = null;

		try {
			session = openSession();

			SyncQueue[] array = new SyncQueueImpl[3];

			array[0] = getByUuid_PrevAndNext(session, syncQueue, uuid,
					orderByComparator, true);

			array[1] = syncQueue;

			array[2] = getByUuid_PrevAndNext(session, syncQueue, uuid,
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

	protected SyncQueue getByUuid_PrevAndNext(Session session,
		SyncQueue syncQueue, String uuid,
		OrderByComparator<SyncQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCQUEUE_WHERE);

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
			query.append(SyncQueueModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(syncQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync queues where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SyncQueue syncQueue : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(syncQueue);
		}
	}

	/**
	 * Returns the number of sync queues where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching sync queues
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYNCQUEUE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "syncQueue.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "syncQueue.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(syncQueue.uuid IS NULL OR syncQueue.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SyncQueueModelImpl.UUID_COLUMN_BITMASK |
			SyncQueueModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the sync queue where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSyncQueueException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sync queue
	 * @throws NoSuchSyncQueueException if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue findByUUID_G(String uuid, long groupId)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByUUID_G(uuid, groupId);

		if (syncQueue == null) {
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

			throw new NoSuchSyncQueueException(msg.toString());
		}

		return syncQueue;
	}

	/**
	 * Returns the sync queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the sync queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof SyncQueue) {
			SyncQueue syncQueue = (SyncQueue)result;

			if (!Objects.equals(uuid, syncQueue.getUuid()) ||
					(groupId != syncQueue.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SYNCQUEUE_WHERE);

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

				List<SyncQueue> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					SyncQueue syncQueue = list.get(0);

					result = syncQueue;

					cacheResult(syncQueue);
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
			return (SyncQueue)result;
		}
	}

	/**
	 * Removes the sync queue where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the sync queue that was removed
	 */
	@Override
	public SyncQueue removeByUUID_G(String uuid, long groupId)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = findByUUID_G(uuid, groupId);

		return remove(syncQueue);
	}

	/**
	 * Returns the number of sync queues where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching sync queues
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCQUEUE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "syncQueue.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "syncQueue.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(syncQueue.uuid IS NULL OR syncQueue.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "syncQueue.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			SyncQueueModelImpl.UUID_COLUMN_BITMASK |
			SyncQueueModelImpl.COMPANYID_COLUMN_BITMASK |
			SyncQueueModelImpl.MODIFIEDDATE_COLUMN_BITMASK |
			SyncQueueModelImpl.PRIORITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sync queues where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching sync queues
	 */
	@Override
	public List<SyncQueue> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync queues where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @return the range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync queues where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<SyncQueue> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync queues where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<SyncQueue> orderByComparator,
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

		List<SyncQueue> list = null;

		if (retrieveFromCache) {
			list = (List<SyncQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncQueue syncQueue : list) {
					if (!Objects.equals(uuid, syncQueue.getUuid()) ||
							(companyId != syncQueue.getCompanyId())) {
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

			query.append(_SQL_SELECT_SYNCQUEUE_WHERE);

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
				query.append(SyncQueueModelImpl.ORDER_BY_JPQL);
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
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync queue
	 * @throws NoSuchSyncQueueException if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (syncQueue != null) {
			return syncQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSyncQueueException(msg.toString());
	}

	/**
	 * Returns the first sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator) {
		List<SyncQueue> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync queue
	 * @throws NoSuchSyncQueueException if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (syncQueue != null) {
			return syncQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSyncQueueException(msg.toString());
	}

	/**
	 * Returns the last sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SyncQueue> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync queues before and after the current sync queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param syncQueueId the primary key of the current sync queue
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync queue
	 * @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue[] findByUuid_C_PrevAndNext(long syncQueueId, String uuid,
		long companyId, OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = findByPrimaryKey(syncQueueId);

		Session session = null;

		try {
			session = openSession();

			SyncQueue[] array = new SyncQueueImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, syncQueue, uuid,
					companyId, orderByComparator, true);

			array[1] = syncQueue;

			array[2] = getByUuid_C_PrevAndNext(session, syncQueue, uuid,
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

	protected SyncQueue getByUuid_C_PrevAndNext(Session session,
		SyncQueue syncQueue, String uuid, long companyId,
		OrderByComparator<SyncQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SYNCQUEUE_WHERE);

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
			query.append(SyncQueueModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(syncQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync queues where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (SyncQueue syncQueue : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncQueue);
		}
	}

	/**
	 * Returns the number of sync queues where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching sync queues
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCQUEUE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "syncQueue.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "syncQueue.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(syncQueue.uuid IS NULL OR syncQueue.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "syncQueue.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CLASSNAME_METHOD =
		new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_className_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_METHOD =
		new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_className_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			SyncQueueModelImpl.GROUPID_COLUMN_BITMASK |
			SyncQueueModelImpl.CLASSNAME_COLUMN_BITMASK |
			SyncQueueModelImpl.METHOD_COLUMN_BITMASK |
			SyncQueueModelImpl.MODIFIEDDATE_COLUMN_BITMASK |
			SyncQueueModelImpl.PRIORITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CLASSNAME_METHOD = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_className_Method",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @return the matching sync queues
	 */
	@Override
	public List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method) {
		return findByF_className_Method(groupId, className, method,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @return the range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method, int start, int end) {
		return findByF_className_Method(groupId, className, method, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator) {
		return findByF_className_Method(groupId, className, method, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByF_className_Method(long groupId,
		String className, String method, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_METHOD;
			finderArgs = new Object[] { groupId, className, method };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CLASSNAME_METHOD;
			finderArgs = new Object[] {
					groupId, className, method,
					
					start, end, orderByComparator
				};
		}

		List<SyncQueue> list = null;

		if (retrieveFromCache) {
			list = (List<SyncQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncQueue syncQueue : list) {
					if ((groupId != syncQueue.getGroupId()) ||
							!Objects.equals(className, syncQueue.getClassName()) ||
							!Objects.equals(method, syncQueue.getMethod())) {
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

			query.append(_SQL_SELECT_SYNCQUEUE_WHERE);

			query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_1);
			}
			else if (method.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindMethod) {
					qPos.add(method);
				}

				if (!pagination) {
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync queue
	 * @throws NoSuchSyncQueueException if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue findByF_className_Method_First(long groupId,
		String className, String method,
		OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByF_className_Method_First(groupId,
				className, method, orderByComparator);

		if (syncQueue != null) {
			return syncQueue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append(", method=");
		msg.append(method);

		msg.append("}");

		throw new NoSuchSyncQueueException(msg.toString());
	}

	/**
	 * Returns the first sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByF_className_Method_First(long groupId,
		String className, String method,
		OrderByComparator<SyncQueue> orderByComparator) {
		List<SyncQueue> list = findByF_className_Method(groupId, className,
				method, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync queue
	 * @throws NoSuchSyncQueueException if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue findByF_className_Method_Last(long groupId,
		String className, String method,
		OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByF_className_Method_Last(groupId,
				className, method, orderByComparator);

		if (syncQueue != null) {
			return syncQueue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append(", method=");
		msg.append(method);

		msg.append("}");

		throw new NoSuchSyncQueueException(msg.toString());
	}

	/**
	 * Returns the last sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByF_className_Method_Last(long groupId,
		String className, String method,
		OrderByComparator<SyncQueue> orderByComparator) {
		int count = countByF_className_Method(groupId, className, method);

		if (count == 0) {
			return null;
		}

		List<SyncQueue> list = findByF_className_Method(groupId, className,
				method, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync queues before and after the current sync queue in the ordered set where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * @param syncQueueId the primary key of the current sync queue
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync queue
	 * @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue[] findByF_className_Method_PrevAndNext(long syncQueueId,
		long groupId, String className, String method,
		OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = findByPrimaryKey(syncQueueId);

		Session session = null;

		try {
			session = openSession();

			SyncQueue[] array = new SyncQueueImpl[3];

			array[0] = getByF_className_Method_PrevAndNext(session, syncQueue,
					groupId, className, method, orderByComparator, true);

			array[1] = syncQueue;

			array[2] = getByF_className_Method_PrevAndNext(session, syncQueue,
					groupId, className, method, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncQueue getByF_className_Method_PrevAndNext(Session session,
		SyncQueue syncQueue, long groupId, String className, String method,
		OrderByComparator<SyncQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_SYNCQUEUE_WHERE);

		query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_GROUPID_2);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_1);
		}
		else if (className.equals("")) {
			query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_2);
		}

		boolean bindMethod = false;

		if (method == null) {
			query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_1);
		}
		else if (method.equals("")) {
			query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_3);
		}
		else {
			bindMethod = true;

			query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_2);
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
			query.append(SyncQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindClassName) {
			qPos.add(className);
		}

		if (bindMethod) {
			qPos.add(method);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync queues where groupId = &#63; and className = &#63; and method = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 */
	@Override
	public void removeByF_className_Method(long groupId, String className,
		String method) {
		for (SyncQueue syncQueue : findByF_className_Method(groupId, className,
				method, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncQueue);
		}
	}

	/**
	 * Returns the number of sync queues where groupId = &#63; and className = &#63; and method = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param method the method
	 * @return the number of matching sync queues
	 */
	@Override
	public int countByF_className_Method(long groupId, String className,
		String method) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CLASSNAME_METHOD;

		Object[] finderArgs = new Object[] { groupId, className, method };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SYNCQUEUE_WHERE);

			query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_2);
			}

			boolean bindMethod = false;

			if (method == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_1);
			}
			else if (method.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_3);
			}
			else {
				bindMethod = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindMethod) {
					qPos.add(method);
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

	private static final String _FINDER_COLUMN_F_CLASSNAME_METHOD_GROUPID_2 = "syncQueue.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_1 = "syncQueue.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_2 = "syncQueue.className = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_METHOD_CLASSNAME_3 = "(syncQueue.className IS NULL OR syncQueue.className = '') AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_1 = "syncQueue.method IS NULL";
	private static final String _FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_2 = "syncQueue.method = ?";
	private static final String _FINDER_COLUMN_F_CLASSNAME_METHOD_METHOD_3 = "(syncQueue.method IS NULL OR syncQueue.method = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID_SERVERNO =
		new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_groupId_serverNo",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO =
		new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, SyncQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_groupId_serverNo",
			new String[] { Long.class.getName(), String.class.getName() },
			SyncQueueModelImpl.GROUPID_COLUMN_BITMASK |
			SyncQueueModelImpl.SERVERNO_COLUMN_BITMASK |
			SyncQueueModelImpl.MODIFIEDDATE_COLUMN_BITMASK |
			SyncQueueModelImpl.PRIORITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO = new FinderPath(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_groupId_serverNo",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the sync queues where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the matching sync queues
	 */
	@Override
	public List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo) {
		return findByF_groupId_serverNo(groupId, serverNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync queues where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @return the range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end) {
		return findByF_groupId_serverNo(groupId, serverNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync queues where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator) {
		return findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync queues where groupId = &#63; and serverNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync queues
	 */
	@Override
	public List<SyncQueue> findByF_groupId_serverNo(long groupId,
		String serverNo, int start, int end,
		OrderByComparator<SyncQueue> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO;
			finderArgs = new Object[] { groupId, serverNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID_SERVERNO;
			finderArgs = new Object[] {
					groupId, serverNo,
					
					start, end, orderByComparator
				};
		}

		List<SyncQueue> list = null;

		if (retrieveFromCache) {
			list = (List<SyncQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncQueue syncQueue : list) {
					if ((groupId != syncQueue.getGroupId()) ||
							!Objects.equals(serverNo, syncQueue.getServerNo())) {
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

			query.append(_SQL_SELECT_SYNCQUEUE_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				if (!pagination) {
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync queue
	 * @throws NoSuchSyncQueueException if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue findByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByF_groupId_serverNo_First(groupId,
				serverNo, orderByComparator);

		if (syncQueue != null) {
			return syncQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serverNo=");
		msg.append(serverNo);

		msg.append("}");

		throw new NoSuchSyncQueueException(msg.toString());
	}

	/**
	 * Returns the first sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByF_groupId_serverNo_First(long groupId,
		String serverNo, OrderByComparator<SyncQueue> orderByComparator) {
		List<SyncQueue> list = findByF_groupId_serverNo(groupId, serverNo, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync queue
	 * @throws NoSuchSyncQueueException if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue findByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByF_groupId_serverNo_Last(groupId, serverNo,
				orderByComparator);

		if (syncQueue != null) {
			return syncQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serverNo=");
		msg.append(serverNo);

		msg.append("}");

		throw new NoSuchSyncQueueException(msg.toString());
	}

	/**
	 * Returns the last sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync queue, or <code>null</code> if a matching sync queue could not be found
	 */
	@Override
	public SyncQueue fetchByF_groupId_serverNo_Last(long groupId,
		String serverNo, OrderByComparator<SyncQueue> orderByComparator) {
		int count = countByF_groupId_serverNo(groupId, serverNo);

		if (count == 0) {
			return null;
		}

		List<SyncQueue> list = findByF_groupId_serverNo(groupId, serverNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync queues before and after the current sync queue in the ordered set where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param syncQueueId the primary key of the current sync queue
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync queue
	 * @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue[] findByF_groupId_serverNo_PrevAndNext(long syncQueueId,
		long groupId, String serverNo,
		OrderByComparator<SyncQueue> orderByComparator)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = findByPrimaryKey(syncQueueId);

		Session session = null;

		try {
			session = openSession();

			SyncQueue[] array = new SyncQueueImpl[3];

			array[0] = getByF_groupId_serverNo_PrevAndNext(session, syncQueue,
					groupId, serverNo, orderByComparator, true);

			array[1] = syncQueue;

			array[2] = getByF_groupId_serverNo_PrevAndNext(session, syncQueue,
					groupId, serverNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncQueue getByF_groupId_serverNo_PrevAndNext(Session session,
		SyncQueue syncQueue, long groupId, String serverNo,
		OrderByComparator<SyncQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SYNCQUEUE_WHERE);

		query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2);

		boolean bindServerNo = false;

		if (serverNo == null) {
			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1);
		}
		else if (serverNo.equals("")) {
			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3);
		}
		else {
			bindServerNo = true;

			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2);
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
			query.append(SyncQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindServerNo) {
			qPos.add(serverNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync queues where groupId = &#63; and serverNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 */
	@Override
	public void removeByF_groupId_serverNo(long groupId, String serverNo) {
		for (SyncQueue syncQueue : findByF_groupId_serverNo(groupId, serverNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncQueue);
		}
	}

	/**
	 * Returns the number of sync queues where groupId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serverNo the server no
	 * @return the number of matching sync queues
	 */
	@Override
	public int countByF_groupId_serverNo(long groupId, String serverNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO;

		Object[] finderArgs = new Object[] { groupId, serverNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCQUEUE_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServerNo) {
					qPos.add(serverNo);
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

	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_GROUPID_2 = "syncQueue.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_1 = "syncQueue.serverNo IS NULL";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_2 = "syncQueue.serverNo = ?";
	private static final String _FINDER_COLUMN_F_GROUPID_SERVERNO_SERVERNO_3 = "(syncQueue.serverNo IS NULL OR syncQueue.serverNo = '')";

	public SyncQueuePersistenceImpl() {
		setModelClass(SyncQueue.class);

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
	 * Caches the sync queue in the entity cache if it is enabled.
	 *
	 * @param syncQueue the sync queue
	 */
	@Override
	public void cacheResult(SyncQueue syncQueue) {
		entityCache.putResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueImpl.class, syncQueue.getPrimaryKey(), syncQueue);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { syncQueue.getUuid(), syncQueue.getGroupId() },
			syncQueue);

		syncQueue.resetOriginalValues();
	}

	/**
	 * Caches the sync queues in the entity cache if it is enabled.
	 *
	 * @param syncQueues the sync queues
	 */
	@Override
	public void cacheResult(List<SyncQueue> syncQueues) {
		for (SyncQueue syncQueue : syncQueues) {
			if (entityCache.getResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
						SyncQueueImpl.class, syncQueue.getPrimaryKey()) == null) {
				cacheResult(syncQueue);
			}
			else {
				syncQueue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sync queues.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SyncQueueImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sync queue.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SyncQueue syncQueue) {
		entityCache.removeResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueImpl.class, syncQueue.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SyncQueueModelImpl)syncQueue, true);
	}

	@Override
	public void clearCache(List<SyncQueue> syncQueues) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SyncQueue syncQueue : syncQueues) {
			entityCache.removeResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
				SyncQueueImpl.class, syncQueue.getPrimaryKey());

			clearUniqueFindersCache((SyncQueueModelImpl)syncQueue, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SyncQueueModelImpl syncQueueModelImpl) {
		Object[] args = new Object[] {
				syncQueueModelImpl.getUuid(), syncQueueModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			syncQueueModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SyncQueueModelImpl syncQueueModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					syncQueueModelImpl.getUuid(),
					syncQueueModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((syncQueueModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					syncQueueModelImpl.getOriginalUuid(),
					syncQueueModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new sync queue with the primary key. Does not add the sync queue to the database.
	 *
	 * @param syncQueueId the primary key for the new sync queue
	 * @return the new sync queue
	 */
	@Override
	public SyncQueue create(long syncQueueId) {
		SyncQueue syncQueue = new SyncQueueImpl();

		syncQueue.setNew(true);
		syncQueue.setPrimaryKey(syncQueueId);

		String uuid = PortalUUIDUtil.generate();

		syncQueue.setUuid(uuid);

		syncQueue.setCompanyId(companyProvider.getCompanyId());

		return syncQueue;
	}

	/**
	 * Removes the sync queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncQueueId the primary key of the sync queue
	 * @return the sync queue that was removed
	 * @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue remove(long syncQueueId) throws NoSuchSyncQueueException {
		return remove((Serializable)syncQueueId);
	}

	/**
	 * Removes the sync queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sync queue
	 * @return the sync queue that was removed
	 * @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue remove(Serializable primaryKey)
		throws NoSuchSyncQueueException {
		Session session = null;

		try {
			session = openSession();

			SyncQueue syncQueue = (SyncQueue)session.get(SyncQueueImpl.class,
					primaryKey);

			if (syncQueue == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSyncQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(syncQueue);
		}
		catch (NoSuchSyncQueueException nsee) {
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
	protected SyncQueue removeImpl(SyncQueue syncQueue) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(syncQueue)) {
				syncQueue = (SyncQueue)session.get(SyncQueueImpl.class,
						syncQueue.getPrimaryKeyObj());
			}

			if (syncQueue != null) {
				session.delete(syncQueue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (syncQueue != null) {
			clearCache(syncQueue);
		}

		return syncQueue;
	}

	@Override
	public SyncQueue updateImpl(SyncQueue syncQueue) {
		boolean isNew = syncQueue.isNew();

		if (!(syncQueue instanceof SyncQueueModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(syncQueue.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(syncQueue);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in syncQueue proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SyncQueue implementation " +
				syncQueue.getClass());
		}

		SyncQueueModelImpl syncQueueModelImpl = (SyncQueueModelImpl)syncQueue;

		if (Validator.isNull(syncQueue.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			syncQueue.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (syncQueue.getCreateDate() == null)) {
			if (serviceContext == null) {
				syncQueue.setCreateDate(now);
			}
			else {
				syncQueue.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!syncQueueModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				syncQueue.setModifiedDate(now);
			}
			else {
				syncQueue.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (syncQueue.isNew()) {
				session.save(syncQueue);

				syncQueue.setNew(false);
			}
			else {
				syncQueue = (SyncQueue)session.merge(syncQueue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SyncQueueModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { syncQueueModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					syncQueueModelImpl.getUuid(),
					syncQueueModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					syncQueueModelImpl.getGroupId(),
					syncQueueModelImpl.getClassName(),
					syncQueueModelImpl.getMethod()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_METHOD,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_METHOD,
				args);

			args = new Object[] {
					syncQueueModelImpl.getGroupId(),
					syncQueueModelImpl.getServerNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((syncQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncQueueModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { syncQueueModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((syncQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncQueueModelImpl.getOriginalUuid(),
						syncQueueModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						syncQueueModelImpl.getUuid(),
						syncQueueModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((syncQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_METHOD.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncQueueModelImpl.getOriginalGroupId(),
						syncQueueModelImpl.getOriginalClassName(),
						syncQueueModelImpl.getOriginalMethod()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_METHOD,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_METHOD,
					args);

				args = new Object[] {
						syncQueueModelImpl.getGroupId(),
						syncQueueModelImpl.getClassName(),
						syncQueueModelImpl.getMethod()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_METHOD,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_METHOD,
					args);
			}

			if ((syncQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncQueueModelImpl.getOriginalGroupId(),
						syncQueueModelImpl.getOriginalServerNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO,
					args);

				args = new Object[] {
						syncQueueModelImpl.getGroupId(),
						syncQueueModelImpl.getServerNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID_SERVERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID_SERVERNO,
					args);
			}
		}

		entityCache.putResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
			SyncQueueImpl.class, syncQueue.getPrimaryKey(), syncQueue, false);

		clearUniqueFindersCache(syncQueueModelImpl, false);
		cacheUniqueFindersCache(syncQueueModelImpl);

		syncQueue.resetOriginalValues();

		return syncQueue;
	}

	/**
	 * Returns the sync queue with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync queue
	 * @return the sync queue
	 * @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSyncQueueException {
		SyncQueue syncQueue = fetchByPrimaryKey(primaryKey);

		if (syncQueue == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSyncQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return syncQueue;
	}

	/**
	 * Returns the sync queue with the primary key or throws a {@link NoSuchSyncQueueException} if it could not be found.
	 *
	 * @param syncQueueId the primary key of the sync queue
	 * @return the sync queue
	 * @throws NoSuchSyncQueueException if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue findByPrimaryKey(long syncQueueId)
		throws NoSuchSyncQueueException {
		return findByPrimaryKey((Serializable)syncQueueId);
	}

	/**
	 * Returns the sync queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync queue
	 * @return the sync queue, or <code>null</code> if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
				SyncQueueImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SyncQueue syncQueue = (SyncQueue)serializable;

		if (syncQueue == null) {
			Session session = null;

			try {
				session = openSession();

				syncQueue = (SyncQueue)session.get(SyncQueueImpl.class,
						primaryKey);

				if (syncQueue != null) {
					cacheResult(syncQueue);
				}
				else {
					entityCache.putResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
						SyncQueueImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
					SyncQueueImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return syncQueue;
	}

	/**
	 * Returns the sync queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param syncQueueId the primary key of the sync queue
	 * @return the sync queue, or <code>null</code> if a sync queue with the primary key could not be found
	 */
	@Override
	public SyncQueue fetchByPrimaryKey(long syncQueueId) {
		return fetchByPrimaryKey((Serializable)syncQueueId);
	}

	@Override
	public Map<Serializable, SyncQueue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SyncQueue> map = new HashMap<Serializable, SyncQueue>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SyncQueue syncQueue = fetchByPrimaryKey(primaryKey);

			if (syncQueue != null) {
				map.put(primaryKey, syncQueue);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
					SyncQueueImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SyncQueue)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SYNCQUEUE_WHERE_PKS_IN);

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

			for (SyncQueue syncQueue : (List<SyncQueue>)q.list()) {
				map.put(syncQueue.getPrimaryKeyObj(), syncQueue);

				cacheResult(syncQueue);

				uncachedPrimaryKeys.remove(syncQueue.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SyncQueueModelImpl.ENTITY_CACHE_ENABLED,
					SyncQueueImpl.class, primaryKey, nullModel);
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
	 * Returns all the sync queues.
	 *
	 * @return the sync queues
	 */
	@Override
	public List<SyncQueue> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @return the range of sync queues
	 */
	@Override
	public List<SyncQueue> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sync queues
	 */
	@Override
	public List<SyncQueue> findAll(int start, int end,
		OrderByComparator<SyncQueue> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync queues
	 * @param end the upper bound of the range of sync queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sync queues
	 */
	@Override
	public List<SyncQueue> findAll(int start, int end,
		OrderByComparator<SyncQueue> orderByComparator,
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

		List<SyncQueue> list = null;

		if (retrieveFromCache) {
			list = (List<SyncQueue>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SYNCQUEUE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SYNCQUEUE;

				if (pagination) {
					sql = sql.concat(SyncQueueModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncQueue>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the sync queues from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SyncQueue syncQueue : findAll()) {
			remove(syncQueue);
		}
	}

	/**
	 * Returns the number of sync queues.
	 *
	 * @return the number of sync queues
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SYNCQUEUE);

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
		return SyncQueueModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sync queue persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SyncQueueImpl.class.getName());
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
	private static final String _SQL_SELECT_SYNCQUEUE = "SELECT syncQueue FROM SyncQueue syncQueue";
	private static final String _SQL_SELECT_SYNCQUEUE_WHERE_PKS_IN = "SELECT syncQueue FROM SyncQueue syncQueue WHERE syncQueueId IN (";
	private static final String _SQL_SELECT_SYNCQUEUE_WHERE = "SELECT syncQueue FROM SyncQueue syncQueue WHERE ";
	private static final String _SQL_COUNT_SYNCQUEUE = "SELECT COUNT(syncQueue) FROM SyncQueue syncQueue";
	private static final String _SQL_COUNT_SYNCQUEUE_WHERE = "SELECT COUNT(syncQueue) FROM SyncQueue syncQueue WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "syncQueue.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SyncQueue exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SyncQueue exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SyncQueuePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}