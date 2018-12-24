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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchPublishQueueException;
import org.opencps.dossiermgt.model.PublishQueue;
import org.opencps.dossiermgt.model.impl.PublishQueueImpl;
import org.opencps.dossiermgt.model.impl.PublishQueueModelImpl;
import org.opencps.dossiermgt.service.persistence.PublishQueuePersistence;

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
 * The persistence implementation for the publish queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see PublishQueuePersistence
 * @see org.opencps.dossiermgt.service.persistence.PublishQueueUtil
 * @generated
 */
@ProviderType
public class PublishQueuePersistenceImpl extends BasePersistenceImpl<PublishQueue>
	implements PublishQueuePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PublishQueueUtil} to access the publish queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PublishQueueImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, PublishQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, PublishQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, PublishQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, PublishQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PublishQueueModelImpl.UUID_COLUMN_BITMASK |
			PublishQueueModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the publish queues where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching publish queues
	 */
	@Override
	public List<PublishQueue> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the publish queues where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of publish queues
	 * @param end the upper bound of the range of publish queues (not inclusive)
	 * @return the range of matching publish queues
	 */
	@Override
	public List<PublishQueue> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the publish queues where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of publish queues
	 * @param end the upper bound of the range of publish queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching publish queues
	 */
	@Override
	public List<PublishQueue> findByUuid(String uuid, int start, int end,
		OrderByComparator<PublishQueue> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the publish queues where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of publish queues
	 * @param end the upper bound of the range of publish queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching publish queues
	 */
	@Override
	public List<PublishQueue> findByUuid(String uuid, int start, int end,
		OrderByComparator<PublishQueue> orderByComparator,
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

		List<PublishQueue> list = null;

		if (retrieveFromCache) {
			list = (List<PublishQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PublishQueue publishQueue : list) {
					if (!Objects.equals(uuid, publishQueue.getUuid())) {
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

			query.append(_SQL_SELECT_PUBLISHQUEUE_WHERE);

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
				query.append(PublishQueueModelImpl.ORDER_BY_JPQL);
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
					list = (List<PublishQueue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PublishQueue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first publish queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching publish queue
	 * @throws NoSuchPublishQueueException if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue findByUuid_First(String uuid,
		OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException {
		PublishQueue publishQueue = fetchByUuid_First(uuid, orderByComparator);

		if (publishQueue != null) {
			return publishQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPublishQueueException(msg.toString());
	}

	/**
	 * Returns the first publish queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue fetchByUuid_First(String uuid,
		OrderByComparator<PublishQueue> orderByComparator) {
		List<PublishQueue> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last publish queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching publish queue
	 * @throws NoSuchPublishQueueException if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue findByUuid_Last(String uuid,
		OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException {
		PublishQueue publishQueue = fetchByUuid_Last(uuid, orderByComparator);

		if (publishQueue != null) {
			return publishQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPublishQueueException(msg.toString());
	}

	/**
	 * Returns the last publish queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue fetchByUuid_Last(String uuid,
		OrderByComparator<PublishQueue> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PublishQueue> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the publish queues before and after the current publish queue in the ordered set where uuid = &#63;.
	 *
	 * @param publishQueueId the primary key of the current publish queue
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next publish queue
	 * @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	 */
	@Override
	public PublishQueue[] findByUuid_PrevAndNext(long publishQueueId,
		String uuid, OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException {
		PublishQueue publishQueue = findByPrimaryKey(publishQueueId);

		Session session = null;

		try {
			session = openSession();

			PublishQueue[] array = new PublishQueueImpl[3];

			array[0] = getByUuid_PrevAndNext(session, publishQueue, uuid,
					orderByComparator, true);

			array[1] = publishQueue;

			array[2] = getByUuid_PrevAndNext(session, publishQueue, uuid,
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

	protected PublishQueue getByUuid_PrevAndNext(Session session,
		PublishQueue publishQueue, String uuid,
		OrderByComparator<PublishQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUBLISHQUEUE_WHERE);

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
			query.append(PublishQueueModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(publishQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PublishQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the publish queues where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PublishQueue publishQueue : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(publishQueue);
		}
	}

	/**
	 * Returns the number of publish queues where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching publish queues
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUBLISHQUEUE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "publishQueue.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "publishQueue.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(publishQueue.uuid IS NULL OR publishQueue.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, PublishQueueImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PublishQueueModelImpl.UUID_COLUMN_BITMASK |
			PublishQueueModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the publish queue where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPublishQueueException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching publish queue
	 * @throws NoSuchPublishQueueException if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue findByUUID_G(String uuid, long groupId)
		throws NoSuchPublishQueueException {
		PublishQueue publishQueue = fetchByUUID_G(uuid, groupId);

		if (publishQueue == null) {
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

			throw new NoSuchPublishQueueException(msg.toString());
		}

		return publishQueue;
	}

	/**
	 * Returns the publish queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the publish queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PublishQueue) {
			PublishQueue publishQueue = (PublishQueue)result;

			if (!Objects.equals(uuid, publishQueue.getUuid()) ||
					(groupId != publishQueue.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PUBLISHQUEUE_WHERE);

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

				List<PublishQueue> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PublishQueue publishQueue = list.get(0);

					result = publishQueue;

					cacheResult(publishQueue);
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
			return (PublishQueue)result;
		}
	}

	/**
	 * Removes the publish queue where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the publish queue that was removed
	 */
	@Override
	public PublishQueue removeByUUID_G(String uuid, long groupId)
		throws NoSuchPublishQueueException {
		PublishQueue publishQueue = findByUUID_G(uuid, groupId);

		return remove(publishQueue);
	}

	/**
	 * Returns the number of publish queues where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching publish queues
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUBLISHQUEUE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "publishQueue.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "publishQueue.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(publishQueue.uuid IS NULL OR publishQueue.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "publishQueue.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ST = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, PublishQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByST",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, PublishQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByST",
			new String[] { Integer.class.getName() },
			PublishQueueModelImpl.STATUS_COLUMN_BITMASK |
			PublishQueueModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ST = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByST",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the publish queues where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching publish queues
	 */
	@Override
	public List<PublishQueue> findByST(int status) {
		return findByST(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the publish queues where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of publish queues
	 * @param end the upper bound of the range of publish queues (not inclusive)
	 * @return the range of matching publish queues
	 */
	@Override
	public List<PublishQueue> findByST(int status, int start, int end) {
		return findByST(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the publish queues where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of publish queues
	 * @param end the upper bound of the range of publish queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching publish queues
	 */
	@Override
	public List<PublishQueue> findByST(int status, int start, int end,
		OrderByComparator<PublishQueue> orderByComparator) {
		return findByST(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the publish queues where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of publish queues
	 * @param end the upper bound of the range of publish queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching publish queues
	 */
	@Override
	public List<PublishQueue> findByST(int status, int start, int end,
		OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ST;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<PublishQueue> list = null;

		if (retrieveFromCache) {
			list = (List<PublishQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PublishQueue publishQueue : list) {
					if ((status != publishQueue.getStatus())) {
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

			query.append(_SQL_SELECT_PUBLISHQUEUE_WHERE);

			query.append(_FINDER_COLUMN_ST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PublishQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<PublishQueue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PublishQueue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first publish queue in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching publish queue
	 * @throws NoSuchPublishQueueException if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue findByST_First(int status,
		OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException {
		PublishQueue publishQueue = fetchByST_First(status, orderByComparator);

		if (publishQueue != null) {
			return publishQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchPublishQueueException(msg.toString());
	}

	/**
	 * Returns the first publish queue in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue fetchByST_First(int status,
		OrderByComparator<PublishQueue> orderByComparator) {
		List<PublishQueue> list = findByST(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last publish queue in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching publish queue
	 * @throws NoSuchPublishQueueException if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue findByST_Last(int status,
		OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException {
		PublishQueue publishQueue = fetchByST_Last(status, orderByComparator);

		if (publishQueue != null) {
			return publishQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchPublishQueueException(msg.toString());
	}

	/**
	 * Returns the last publish queue in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue fetchByST_Last(int status,
		OrderByComparator<PublishQueue> orderByComparator) {
		int count = countByST(status);

		if (count == 0) {
			return null;
		}

		List<PublishQueue> list = findByST(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the publish queues before and after the current publish queue in the ordered set where status = &#63;.
	 *
	 * @param publishQueueId the primary key of the current publish queue
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next publish queue
	 * @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	 */
	@Override
	public PublishQueue[] findByST_PrevAndNext(long publishQueueId, int status,
		OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException {
		PublishQueue publishQueue = findByPrimaryKey(publishQueueId);

		Session session = null;

		try {
			session = openSession();

			PublishQueue[] array = new PublishQueueImpl[3];

			array[0] = getByST_PrevAndNext(session, publishQueue, status,
					orderByComparator, true);

			array[1] = publishQueue;

			array[2] = getByST_PrevAndNext(session, publishQueue, status,
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

	protected PublishQueue getByST_PrevAndNext(Session session,
		PublishQueue publishQueue, int status,
		OrderByComparator<PublishQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUBLISHQUEUE_WHERE);

		query.append(_FINDER_COLUMN_ST_STATUS_2);

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
			query.append(PublishQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(publishQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PublishQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the publish queues where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByST(int status) {
		for (PublishQueue publishQueue : findByST(status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(publishQueue);
		}
	}

	/**
	 * Returns the number of publish queues where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching publish queues
	 */
	@Override
	public int countByST(int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ST;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUBLISHQUEUE_WHERE);

			query.append(_FINDER_COLUMN_ST_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_ST_STATUS_2 = "publishQueue.status = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_DID_SN = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, PublishQueueImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_DID_SN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			PublishQueueModelImpl.GROUPID_COLUMN_BITMASK |
			PublishQueueModelImpl.DOSSIERID_COLUMN_BITMASK |
			PublishQueueModelImpl.SERVERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DID_SN = new FinderPath(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DID_SN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; or throws a {@link NoSuchPublishQueueException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param serverNo the server no
	 * @return the matching publish queue
	 * @throws NoSuchPublishQueueException if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue findByG_DID_SN(long groupId, long dossierId,
		String serverNo) throws NoSuchPublishQueueException {
		PublishQueue publishQueue = fetchByG_DID_SN(groupId, dossierId, serverNo);

		if (publishQueue == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dossierId=");
			msg.append(dossierId);

			msg.append(", serverNo=");
			msg.append(serverNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPublishQueueException(msg.toString());
		}

		return publishQueue;
	}

	/**
	 * Returns the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param serverNo the server no
	 * @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue fetchByG_DID_SN(long groupId, long dossierId,
		String serverNo) {
		return fetchByG_DID_SN(groupId, dossierId, serverNo, true);
	}

	/**
	 * Returns the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param serverNo the server no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	 */
	@Override
	public PublishQueue fetchByG_DID_SN(long groupId, long dossierId,
		String serverNo, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, dossierId, serverNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_DID_SN,
					finderArgs, this);
		}

		if (result instanceof PublishQueue) {
			PublishQueue publishQueue = (PublishQueue)result;

			if ((groupId != publishQueue.getGroupId()) ||
					(dossierId != publishQueue.getDossierId()) ||
					!Objects.equals(serverNo, publishQueue.getServerNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_PUBLISHQUEUE_WHERE);

			query.append(_FINDER_COLUMN_G_DID_SN_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_SN_DOSSIERID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_G_DID_SN_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DID_SN_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_G_DID_SN_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (bindServerNo) {
					qPos.add(serverNo);
				}

				List<PublishQueue> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_DID_SN,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PublishQueuePersistenceImpl.fetchByG_DID_SN(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PublishQueue publishQueue = list.get(0);

					result = publishQueue;

					cacheResult(publishQueue);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DID_SN,
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
			return (PublishQueue)result;
		}
	}

	/**
	 * Removes the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param serverNo the server no
	 * @return the publish queue that was removed
	 */
	@Override
	public PublishQueue removeByG_DID_SN(long groupId, long dossierId,
		String serverNo) throws NoSuchPublishQueueException {
		PublishQueue publishQueue = findByG_DID_SN(groupId, dossierId, serverNo);

		return remove(publishQueue);
	}

	/**
	 * Returns the number of publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param serverNo the server no
	 * @return the number of matching publish queues
	 */
	@Override
	public int countByG_DID_SN(long groupId, long dossierId, String serverNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DID_SN;

		Object[] finderArgs = new Object[] { groupId, dossierId, serverNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PUBLISHQUEUE_WHERE);

			query.append(_FINDER_COLUMN_G_DID_SN_GROUPID_2);

			query.append(_FINDER_COLUMN_G_DID_SN_DOSSIERID_2);

			boolean bindServerNo = false;

			if (serverNo == null) {
				query.append(_FINDER_COLUMN_G_DID_SN_SERVERNO_1);
			}
			else if (serverNo.equals("")) {
				query.append(_FINDER_COLUMN_G_DID_SN_SERVERNO_3);
			}
			else {
				bindServerNo = true;

				query.append(_FINDER_COLUMN_G_DID_SN_SERVERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

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

	private static final String _FINDER_COLUMN_G_DID_SN_GROUPID_2 = "publishQueue.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_SN_DOSSIERID_2 = "publishQueue.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_G_DID_SN_SERVERNO_1 = "publishQueue.serverNo IS NULL";
	private static final String _FINDER_COLUMN_G_DID_SN_SERVERNO_2 = "publishQueue.serverNo = ?";
	private static final String _FINDER_COLUMN_G_DID_SN_SERVERNO_3 = "(publishQueue.serverNo IS NULL OR publishQueue.serverNo = '')";

	public PublishQueuePersistenceImpl() {
		setModelClass(PublishQueue.class);

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
	 * Caches the publish queue in the entity cache if it is enabled.
	 *
	 * @param publishQueue the publish queue
	 */
	@Override
	public void cacheResult(PublishQueue publishQueue) {
		entityCache.putResult(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueImpl.class, publishQueue.getPrimaryKey(), publishQueue);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { publishQueue.getUuid(), publishQueue.getGroupId() },
			publishQueue);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DID_SN,
			new Object[] {
				publishQueue.getGroupId(), publishQueue.getDossierId(),
				publishQueue.getServerNo()
			}, publishQueue);

		publishQueue.resetOriginalValues();
	}

	/**
	 * Caches the publish queues in the entity cache if it is enabled.
	 *
	 * @param publishQueues the publish queues
	 */
	@Override
	public void cacheResult(List<PublishQueue> publishQueues) {
		for (PublishQueue publishQueue : publishQueues) {
			if (entityCache.getResult(
						PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
						PublishQueueImpl.class, publishQueue.getPrimaryKey()) == null) {
				cacheResult(publishQueue);
			}
			else {
				publishQueue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all publish queues.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PublishQueueImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the publish queue.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PublishQueue publishQueue) {
		entityCache.removeResult(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueImpl.class, publishQueue.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PublishQueueModelImpl)publishQueue, true);
	}

	@Override
	public void clearCache(List<PublishQueue> publishQueues) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PublishQueue publishQueue : publishQueues) {
			entityCache.removeResult(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
				PublishQueueImpl.class, publishQueue.getPrimaryKey());

			clearUniqueFindersCache((PublishQueueModelImpl)publishQueue, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PublishQueueModelImpl publishQueueModelImpl) {
		Object[] args = new Object[] {
				publishQueueModelImpl.getUuid(),
				publishQueueModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			publishQueueModelImpl, false);

		args = new Object[] {
				publishQueueModelImpl.getGroupId(),
				publishQueueModelImpl.getDossierId(),
				publishQueueModelImpl.getServerNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_DID_SN, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DID_SN, args,
			publishQueueModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PublishQueueModelImpl publishQueueModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					publishQueueModelImpl.getUuid(),
					publishQueueModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((publishQueueModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					publishQueueModelImpl.getOriginalUuid(),
					publishQueueModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					publishQueueModelImpl.getGroupId(),
					publishQueueModelImpl.getDossierId(),
					publishQueueModelImpl.getServerNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_SN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DID_SN, args);
		}

		if ((publishQueueModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_DID_SN.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					publishQueueModelImpl.getOriginalGroupId(),
					publishQueueModelImpl.getOriginalDossierId(),
					publishQueueModelImpl.getOriginalServerNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DID_SN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DID_SN, args);
		}
	}

	/**
	 * Creates a new publish queue with the primary key. Does not add the publish queue to the database.
	 *
	 * @param publishQueueId the primary key for the new publish queue
	 * @return the new publish queue
	 */
	@Override
	public PublishQueue create(long publishQueueId) {
		PublishQueue publishQueue = new PublishQueueImpl();

		publishQueue.setNew(true);
		publishQueue.setPrimaryKey(publishQueueId);

		String uuid = PortalUUIDUtil.generate();

		publishQueue.setUuid(uuid);

		return publishQueue;
	}

	/**
	 * Removes the publish queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param publishQueueId the primary key of the publish queue
	 * @return the publish queue that was removed
	 * @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	 */
	@Override
	public PublishQueue remove(long publishQueueId)
		throws NoSuchPublishQueueException {
		return remove((Serializable)publishQueueId);
	}

	/**
	 * Removes the publish queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the publish queue
	 * @return the publish queue that was removed
	 * @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	 */
	@Override
	public PublishQueue remove(Serializable primaryKey)
		throws NoSuchPublishQueueException {
		Session session = null;

		try {
			session = openSession();

			PublishQueue publishQueue = (PublishQueue)session.get(PublishQueueImpl.class,
					primaryKey);

			if (publishQueue == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPublishQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(publishQueue);
		}
		catch (NoSuchPublishQueueException nsee) {
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
	protected PublishQueue removeImpl(PublishQueue publishQueue) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(publishQueue)) {
				publishQueue = (PublishQueue)session.get(PublishQueueImpl.class,
						publishQueue.getPrimaryKeyObj());
			}

			if (publishQueue != null) {
				session.delete(publishQueue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (publishQueue != null) {
			clearCache(publishQueue);
		}

		return publishQueue;
	}

	@Override
	public PublishQueue updateImpl(PublishQueue publishQueue) {
		boolean isNew = publishQueue.isNew();

		if (!(publishQueue instanceof PublishQueueModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(publishQueue.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(publishQueue);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in publishQueue proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PublishQueue implementation " +
				publishQueue.getClass());
		}

		PublishQueueModelImpl publishQueueModelImpl = (PublishQueueModelImpl)publishQueue;

		if (Validator.isNull(publishQueue.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			publishQueue.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (publishQueue.getCreateDate() == null)) {
			if (serviceContext == null) {
				publishQueue.setCreateDate(now);
			}
			else {
				publishQueue.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!publishQueueModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				publishQueue.setModifiedDate(now);
			}
			else {
				publishQueue.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (publishQueue.isNew()) {
				session.save(publishQueue);

				publishQueue.setNew(false);
			}
			else {
				publishQueue = (PublishQueue)session.merge(publishQueue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PublishQueueModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { publishQueueModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { publishQueueModelImpl.getStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ST, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((publishQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						publishQueueModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { publishQueueModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((publishQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						publishQueueModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST,
					args);

				args = new Object[] { publishQueueModelImpl.getStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ST, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ST,
					args);
			}
		}

		entityCache.putResult(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
			PublishQueueImpl.class, publishQueue.getPrimaryKey(), publishQueue,
			false);

		clearUniqueFindersCache(publishQueueModelImpl, false);
		cacheUniqueFindersCache(publishQueueModelImpl);

		publishQueue.resetOriginalValues();

		return publishQueue;
	}

	/**
	 * Returns the publish queue with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the publish queue
	 * @return the publish queue
	 * @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	 */
	@Override
	public PublishQueue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPublishQueueException {
		PublishQueue publishQueue = fetchByPrimaryKey(primaryKey);

		if (publishQueue == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPublishQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return publishQueue;
	}

	/**
	 * Returns the publish queue with the primary key or throws a {@link NoSuchPublishQueueException} if it could not be found.
	 *
	 * @param publishQueueId the primary key of the publish queue
	 * @return the publish queue
	 * @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	 */
	@Override
	public PublishQueue findByPrimaryKey(long publishQueueId)
		throws NoSuchPublishQueueException {
		return findByPrimaryKey((Serializable)publishQueueId);
	}

	/**
	 * Returns the publish queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the publish queue
	 * @return the publish queue, or <code>null</code> if a publish queue with the primary key could not be found
	 */
	@Override
	public PublishQueue fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
				PublishQueueImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PublishQueue publishQueue = (PublishQueue)serializable;

		if (publishQueue == null) {
			Session session = null;

			try {
				session = openSession();

				publishQueue = (PublishQueue)session.get(PublishQueueImpl.class,
						primaryKey);

				if (publishQueue != null) {
					cacheResult(publishQueue);
				}
				else {
					entityCache.putResult(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
						PublishQueueImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
					PublishQueueImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return publishQueue;
	}

	/**
	 * Returns the publish queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param publishQueueId the primary key of the publish queue
	 * @return the publish queue, or <code>null</code> if a publish queue with the primary key could not be found
	 */
	@Override
	public PublishQueue fetchByPrimaryKey(long publishQueueId) {
		return fetchByPrimaryKey((Serializable)publishQueueId);
	}

	@Override
	public Map<Serializable, PublishQueue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PublishQueue> map = new HashMap<Serializable, PublishQueue>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PublishQueue publishQueue = fetchByPrimaryKey(primaryKey);

			if (publishQueue != null) {
				map.put(primaryKey, publishQueue);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
					PublishQueueImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PublishQueue)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PUBLISHQUEUE_WHERE_PKS_IN);

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

			for (PublishQueue publishQueue : (List<PublishQueue>)q.list()) {
				map.put(publishQueue.getPrimaryKeyObj(), publishQueue);

				cacheResult(publishQueue);

				uncachedPrimaryKeys.remove(publishQueue.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PublishQueueModelImpl.ENTITY_CACHE_ENABLED,
					PublishQueueImpl.class, primaryKey, nullModel);
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
	 * Returns all the publish queues.
	 *
	 * @return the publish queues
	 */
	@Override
	public List<PublishQueue> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the publish queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of publish queues
	 * @param end the upper bound of the range of publish queues (not inclusive)
	 * @return the range of publish queues
	 */
	@Override
	public List<PublishQueue> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the publish queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of publish queues
	 * @param end the upper bound of the range of publish queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of publish queues
	 */
	@Override
	public List<PublishQueue> findAll(int start, int end,
		OrderByComparator<PublishQueue> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the publish queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of publish queues
	 * @param end the upper bound of the range of publish queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of publish queues
	 */
	@Override
	public List<PublishQueue> findAll(int start, int end,
		OrderByComparator<PublishQueue> orderByComparator,
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

		List<PublishQueue> list = null;

		if (retrieveFromCache) {
			list = (List<PublishQueue>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PUBLISHQUEUE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUBLISHQUEUE;

				if (pagination) {
					sql = sql.concat(PublishQueueModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PublishQueue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PublishQueue>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the publish queues from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PublishQueue publishQueue : findAll()) {
			remove(publishQueue);
		}
	}

	/**
	 * Returns the number of publish queues.
	 *
	 * @return the number of publish queues
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PUBLISHQUEUE);

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
		return PublishQueueModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the publish queue persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PublishQueueImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PUBLISHQUEUE = "SELECT publishQueue FROM PublishQueue publishQueue";
	private static final String _SQL_SELECT_PUBLISHQUEUE_WHERE_PKS_IN = "SELECT publishQueue FROM PublishQueue publishQueue WHERE publishQueueId IN (";
	private static final String _SQL_SELECT_PUBLISHQUEUE_WHERE = "SELECT publishQueue FROM PublishQueue publishQueue WHERE ";
	private static final String _SQL_COUNT_PUBLISHQUEUE = "SELECT COUNT(publishQueue) FROM PublishQueue publishQueue";
	private static final String _SQL_COUNT_PUBLISHQUEUE_WHERE = "SELECT COUNT(publishQueue) FROM PublishQueue publishQueue WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "publishQueue.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PublishQueue exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PublishQueue exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PublishQueuePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}