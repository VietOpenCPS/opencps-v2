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

package org.opencps.communication.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.communication.exception.NoSuchNotificationQueueException;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.impl.NotificationQueueImpl;
import org.opencps.communication.model.impl.NotificationQueueModelImpl;
import org.opencps.communication.service.persistence.NotificationQueuePersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

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
 * The persistence implementation for the notification queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see NotificationQueuePersistence
 * @see org.opencps.communication.service.persistence.NotificationQueueUtil
 * @generated
 */
@ProviderType
public class NotificationQueuePersistenceImpl extends BasePersistenceImpl<NotificationQueue>
	implements NotificationQueuePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NotificationQueueUtil} to access the notification queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NotificationQueueImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED,
			NotificationQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED,
			NotificationQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G = new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED,
			NotificationQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G = new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED,
			NotificationQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG",
			new String[] { Long.class.getName() },
			NotificationQueueModelImpl.GROUPID_COLUMN_BITMASK |
			NotificationQueueModelImpl.PRIORITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G = new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the notification queues where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByG(long groupId) {
		return findByG(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification queues where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @return the range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByG(long groupId, int start, int end) {
		return findByG(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification queues where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByG(long groupId, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return findByG(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification queues where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByG(long groupId, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<NotificationQueue> list = null;

		if (retrieveFromCache) {
			list = (List<NotificationQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationQueue notificationQueue : list) {
					if ((groupId != notificationQueue.getGroupId())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<NotificationQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NotificationQueue>)QueryUtil.list(q,
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
	 * Returns the first notification queue in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByG_First(long groupId,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByG_First(groupId,
				orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the first notification queue in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByG_First(long groupId,
		OrderByComparator<NotificationQueue> orderByComparator) {
		List<NotificationQueue> list = findByG(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification queue in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByG_Last(long groupId,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByG_Last(groupId,
				orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the last notification queue in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByG_Last(long groupId,
		OrderByComparator<NotificationQueue> orderByComparator) {
		int count = countByG(groupId);

		if (count == 0) {
			return null;
		}

		List<NotificationQueue> list = findByG(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification queues before and after the current notification queue in the ordered set where groupId = &#63;.
	 *
	 * @param notificationQueueId the primary key of the current notification queue
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification queue
	 * @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue[] findByG_PrevAndNext(long notificationQueueId,
		long groupId, OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = findByPrimaryKey(notificationQueueId);

		Session session = null;

		try {
			session = openSession();

			NotificationQueue[] array = new NotificationQueueImpl[3];

			array[0] = getByG_PrevAndNext(session, notificationQueue, groupId,
					orderByComparator, true);

			array[1] = notificationQueue;

			array[2] = getByG_PrevAndNext(session, notificationQueue, groupId,
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

	protected NotificationQueue getByG_PrevAndNext(Session session,
		NotificationQueue notificationQueue, long groupId,
		OrderByComparator<NotificationQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

		query.append(_FINDER_COLUMN_G_GROUPID_2);

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
			query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NotificationQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification queues where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByG(long groupId) {
		for (NotificationQueue notificationQueue : findByG(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notificationQueue);
		}
	}

	/**
	 * Returns the number of notification queues where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching notification queues
	 */
	@Override
	public int countByG(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NOTIFICATIONQUEUE_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

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

	private static final String _FINDER_COLUMN_G_GROUPID_2 = "notificationQueue.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GREATERTHAN_EXPIREDATE =
		new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED,
			NotificationQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GreaterThan_ExpireDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GREATERTHAN_EXPIREDATE =
		new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_GreaterThan_ExpireDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the notification queues where expireDate &le; &#63;.
	 *
	 * @param expireDate the expire date
	 * @return the matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate) {
		return findByF_GreaterThan_ExpireDate(expireDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification queues where expireDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @return the range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate, int start, int end) {
		return findByF_GreaterThan_ExpireDate(expireDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification queues where expireDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return findByF_GreaterThan_ExpireDate(expireDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification queues where expireDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_GreaterThan_ExpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GREATERTHAN_EXPIREDATE;
		finderArgs = new Object[] {
				_getTime(expireDate),
				
				start, end, orderByComparator
			};

		List<NotificationQueue> list = null;

		if (retrieveFromCache) {
			list = (List<NotificationQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationQueue notificationQueue : list) {
					if ((expireDate.getTime() < notificationQueue.getExpireDate()
																	 .getTime())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

			boolean bindExpireDate = false;

			if (expireDate == null) {
				query.append(_FINDER_COLUMN_F_GREATERTHAN_EXPIREDATE_EXPIREDATE_1);
			}
			else {
				bindExpireDate = true;

				query.append(_FINDER_COLUMN_F_GREATERTHAN_EXPIREDATE_EXPIREDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpireDate) {
					qPos.add(new Timestamp(expireDate.getTime()));
				}

				if (!pagination) {
					list = (List<NotificationQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NotificationQueue>)QueryUtil.list(q,
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
	 * Returns the first notification queue in the ordered set where expireDate &le; &#63;.
	 *
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByF_GreaterThan_ExpireDate_First(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByF_GreaterThan_ExpireDate_First(expireDate,
				orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expireDate=");
		msg.append(expireDate);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the first notification queue in the ordered set where expireDate &le; &#63;.
	 *
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_GreaterThan_ExpireDate_First(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator) {
		List<NotificationQueue> list = findByF_GreaterThan_ExpireDate(expireDate,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification queue in the ordered set where expireDate &le; &#63;.
	 *
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByF_GreaterThan_ExpireDate_Last(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByF_GreaterThan_ExpireDate_Last(expireDate,
				orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expireDate=");
		msg.append(expireDate);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the last notification queue in the ordered set where expireDate &le; &#63;.
	 *
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_GreaterThan_ExpireDate_Last(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator) {
		int count = countByF_GreaterThan_ExpireDate(expireDate);

		if (count == 0) {
			return null;
		}

		List<NotificationQueue> list = findByF_GreaterThan_ExpireDate(expireDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification queues before and after the current notification queue in the ordered set where expireDate &le; &#63;.
	 *
	 * @param notificationQueueId the primary key of the current notification queue
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification queue
	 * @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue[] findByF_GreaterThan_ExpireDate_PrevAndNext(
		long notificationQueueId, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = findByPrimaryKey(notificationQueueId);

		Session session = null;

		try {
			session = openSession();

			NotificationQueue[] array = new NotificationQueueImpl[3];

			array[0] = getByF_GreaterThan_ExpireDate_PrevAndNext(session,
					notificationQueue, expireDate, orderByComparator, true);

			array[1] = notificationQueue;

			array[2] = getByF_GreaterThan_ExpireDate_PrevAndNext(session,
					notificationQueue, expireDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotificationQueue getByF_GreaterThan_ExpireDate_PrevAndNext(
		Session session, NotificationQueue notificationQueue, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

		boolean bindExpireDate = false;

		if (expireDate == null) {
			query.append(_FINDER_COLUMN_F_GREATERTHAN_EXPIREDATE_EXPIREDATE_1);
		}
		else {
			bindExpireDate = true;

			query.append(_FINDER_COLUMN_F_GREATERTHAN_EXPIREDATE_EXPIREDATE_2);
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
			query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindExpireDate) {
			qPos.add(new Timestamp(expireDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NotificationQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification queues where expireDate &le; &#63; from the database.
	 *
	 * @param expireDate the expire date
	 */
	@Override
	public void removeByF_GreaterThan_ExpireDate(Date expireDate) {
		for (NotificationQueue notificationQueue : findByF_GreaterThan_ExpireDate(
				expireDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notificationQueue);
		}
	}

	/**
	 * Returns the number of notification queues where expireDate &le; &#63;.
	 *
	 * @param expireDate the expire date
	 * @return the number of matching notification queues
	 */
	@Override
	public int countByF_GreaterThan_ExpireDate(Date expireDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GREATERTHAN_EXPIREDATE;

		Object[] finderArgs = new Object[] { _getTime(expireDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NOTIFICATIONQUEUE_WHERE);

			boolean bindExpireDate = false;

			if (expireDate == null) {
				query.append(_FINDER_COLUMN_F_GREATERTHAN_EXPIREDATE_EXPIREDATE_1);
			}
			else {
				bindExpireDate = true;

				query.append(_FINDER_COLUMN_F_GREATERTHAN_EXPIREDATE_EXPIREDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpireDate) {
					qPos.add(new Timestamp(expireDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_GREATERTHAN_EXPIREDATE_EXPIREDATE_1 =
		"notificationQueue.expireDate IS NULL";
	private static final String _FINDER_COLUMN_F_GREATERTHAN_EXPIREDATE_EXPIREDATE_2 =
		"notificationQueue.expireDate <= ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE =
		new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED,
			NotificationQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_notificationType_LessThanExpireDate",
			new String[] {
				String.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE =
		new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_notificationType_LessThanExpireDate",
			new String[] { String.class.getName(), Date.class.getName() });

	/**
	 * Returns all the notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @return the matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate) {
		return findByF_notificationType_LessThanExpireDate(notificationType,
			expireDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @return the range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate, int start, int end) {
		return findByF_notificationType_LessThanExpireDate(notificationType,
			expireDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return findByF_notificationType_LessThanExpireDate(notificationType,
			expireDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE;
		finderArgs = new Object[] {
				notificationType, _getTime(expireDate),
				
				start, end, orderByComparator
			};

		List<NotificationQueue> list = null;

		if (retrieveFromCache) {
			list = (List<NotificationQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationQueue notificationQueue : list) {
					if (!Objects.equals(notificationType,
								notificationQueue.getNotificationType()) ||
							(expireDate.getTime() > notificationQueue.getExpireDate()
																		 .getTime())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_2);
			}

			boolean bindExpireDate = false;

			if (expireDate == null) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_EXPIREDATE_1);
			}
			else {
				bindExpireDate = true;

				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_EXPIREDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindExpireDate) {
					qPos.add(new Timestamp(expireDate.getTime()));
				}

				if (!pagination) {
					list = (List<NotificationQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NotificationQueue>)QueryUtil.list(q,
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
	 * Returns the first notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByF_notificationType_LessThanExpireDate_First(
		String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByF_notificationType_LessThanExpireDate_First(notificationType,
				expireDate, orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("notificationType=");
		msg.append(notificationType);

		msg.append(", expireDate=");
		msg.append(expireDate);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the first notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_notificationType_LessThanExpireDate_First(
		String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator) {
		List<NotificationQueue> list = findByF_notificationType_LessThanExpireDate(notificationType,
				expireDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByF_notificationType_LessThanExpireDate_Last(
		String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByF_notificationType_LessThanExpireDate_Last(notificationType,
				expireDate, orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("notificationType=");
		msg.append(notificationType);

		msg.append(", expireDate=");
		msg.append(expireDate);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the last notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_notificationType_LessThanExpireDate_Last(
		String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator) {
		int count = countByF_notificationType_LessThanExpireDate(notificationType,
				expireDate);

		if (count == 0) {
			return null;
		}

		List<NotificationQueue> list = findByF_notificationType_LessThanExpireDate(notificationType,
				expireDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification queues before and after the current notification queue in the ordered set where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationQueueId the primary key of the current notification queue
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification queue
	 * @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue[] findByF_notificationType_LessThanExpireDate_PrevAndNext(
		long notificationQueueId, String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = findByPrimaryKey(notificationQueueId);

		Session session = null;

		try {
			session = openSession();

			NotificationQueue[] array = new NotificationQueueImpl[3];

			array[0] = getByF_notificationType_LessThanExpireDate_PrevAndNext(session,
					notificationQueue, notificationType, expireDate,
					orderByComparator, true);

			array[1] = notificationQueue;

			array[2] = getByF_notificationType_LessThanExpireDate_PrevAndNext(session,
					notificationQueue, notificationType, expireDate,
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

	protected NotificationQueue getByF_notificationType_LessThanExpireDate_PrevAndNext(
		Session session, NotificationQueue notificationQueue,
		String notificationType, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

		boolean bindNotificationType = false;

		if (notificationType == null) {
			query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_1);
		}
		else if (notificationType.equals("")) {
			query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_3);
		}
		else {
			bindNotificationType = true;

			query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_2);
		}

		boolean bindExpireDate = false;

		if (expireDate == null) {
			query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_EXPIREDATE_1);
		}
		else {
			bindExpireDate = true;

			query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_EXPIREDATE_2);
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
			query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindNotificationType) {
			qPos.add(notificationType);
		}

		if (bindExpireDate) {
			qPos.add(new Timestamp(expireDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NotificationQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification queues where notificationType = &#63; and expireDate &ge; &#63; from the database.
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 */
	@Override
	public void removeByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate) {
		for (NotificationQueue notificationQueue : findByF_notificationType_LessThanExpireDate(
				notificationType, expireDate, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(notificationQueue);
		}
	}

	/**
	 * Returns the number of notification queues where notificationType = &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param expireDate the expire date
	 * @return the number of matching notification queues
	 */
	@Override
	public int countByF_notificationType_LessThanExpireDate(
		String notificationType, Date expireDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE;

		Object[] finderArgs = new Object[] {
				notificationType, _getTime(expireDate)
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_NOTIFICATIONQUEUE_WHERE);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_2);
			}

			boolean bindExpireDate = false;

			if (expireDate == null) {
				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_EXPIREDATE_1);
			}
			else {
				bindExpireDate = true;

				query.append(_FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_EXPIREDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindExpireDate) {
					qPos.add(new Timestamp(expireDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_1 =
		"notificationQueue.notificationType IS NULL AND ";
	private static final String _FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_2 =
		"notificationQueue.notificationType = ? AND ";
	private static final String _FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_NOTIFICATIONTYPE_3 =
		"(notificationQueue.notificationType IS NULL OR notificationQueue.notificationType = '') AND ";
	private static final String _FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_EXPIREDATE_1 =
		"notificationQueue.expireDate IS NULL";
	private static final String _FINDER_COLUMN_F_NOTIFICATIONTYPE_LESSTHANEXPIREDATE_EXPIREDATE_2 =
		"notificationQueue.expireDate >= ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_LESSTHAN_EXPIREDATE =
		new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED,
			NotificationQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_LessThan_ExpireDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_LESSTHAN_EXPIREDATE =
		new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_LessThan_ExpireDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the notification queues where expireDate &ge; &#63;.
	 *
	 * @param expireDate the expire date
	 * @return the matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_LessThan_ExpireDate(Date expireDate) {
		return findByF_LessThan_ExpireDate(expireDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification queues where expireDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @return the range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate, int start, int end) {
		return findByF_LessThan_ExpireDate(expireDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification queues where expireDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return findByF_LessThan_ExpireDate(expireDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification queues where expireDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_LessThan_ExpireDate(
		Date expireDate, int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_LESSTHAN_EXPIREDATE;
		finderArgs = new Object[] {
				_getTime(expireDate),
				
				start, end, orderByComparator
			};

		List<NotificationQueue> list = null;

		if (retrieveFromCache) {
			list = (List<NotificationQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationQueue notificationQueue : list) {
					if ((expireDate.getTime() > notificationQueue.getExpireDate()
																	 .getTime())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

			boolean bindExpireDate = false;

			if (expireDate == null) {
				query.append(_FINDER_COLUMN_F_LESSTHAN_EXPIREDATE_EXPIREDATE_1);
			}
			else {
				bindExpireDate = true;

				query.append(_FINDER_COLUMN_F_LESSTHAN_EXPIREDATE_EXPIREDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpireDate) {
					qPos.add(new Timestamp(expireDate.getTime()));
				}

				if (!pagination) {
					list = (List<NotificationQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NotificationQueue>)QueryUtil.list(q,
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
	 * Returns the first notification queue in the ordered set where expireDate &ge; &#63;.
	 *
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByF_LessThan_ExpireDate_First(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByF_LessThan_ExpireDate_First(expireDate,
				orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expireDate=");
		msg.append(expireDate);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the first notification queue in the ordered set where expireDate &ge; &#63;.
	 *
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_LessThan_ExpireDate_First(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator) {
		List<NotificationQueue> list = findByF_LessThan_ExpireDate(expireDate,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification queue in the ordered set where expireDate &ge; &#63;.
	 *
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByF_LessThan_ExpireDate_Last(Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByF_LessThan_ExpireDate_Last(expireDate,
				orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expireDate=");
		msg.append(expireDate);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the last notification queue in the ordered set where expireDate &ge; &#63;.
	 *
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_LessThan_ExpireDate_Last(
		Date expireDate, OrderByComparator<NotificationQueue> orderByComparator) {
		int count = countByF_LessThan_ExpireDate(expireDate);

		if (count == 0) {
			return null;
		}

		List<NotificationQueue> list = findByF_LessThan_ExpireDate(expireDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification queues before and after the current notification queue in the ordered set where expireDate &ge; &#63;.
	 *
	 * @param notificationQueueId the primary key of the current notification queue
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification queue
	 * @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue[] findByF_LessThan_ExpireDate_PrevAndNext(
		long notificationQueueId, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = findByPrimaryKey(notificationQueueId);

		Session session = null;

		try {
			session = openSession();

			NotificationQueue[] array = new NotificationQueueImpl[3];

			array[0] = getByF_LessThan_ExpireDate_PrevAndNext(session,
					notificationQueue, expireDate, orderByComparator, true);

			array[1] = notificationQueue;

			array[2] = getByF_LessThan_ExpireDate_PrevAndNext(session,
					notificationQueue, expireDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotificationQueue getByF_LessThan_ExpireDate_PrevAndNext(
		Session session, NotificationQueue notificationQueue, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

		boolean bindExpireDate = false;

		if (expireDate == null) {
			query.append(_FINDER_COLUMN_F_LESSTHAN_EXPIREDATE_EXPIREDATE_1);
		}
		else {
			bindExpireDate = true;

			query.append(_FINDER_COLUMN_F_LESSTHAN_EXPIREDATE_EXPIREDATE_2);
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
			query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindExpireDate) {
			qPos.add(new Timestamp(expireDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NotificationQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification queues where expireDate &ge; &#63; from the database.
	 *
	 * @param expireDate the expire date
	 */
	@Override
	public void removeByF_LessThan_ExpireDate(Date expireDate) {
		for (NotificationQueue notificationQueue : findByF_LessThan_ExpireDate(
				expireDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notificationQueue);
		}
	}

	/**
	 * Returns the number of notification queues where expireDate &ge; &#63;.
	 *
	 * @param expireDate the expire date
	 * @return the number of matching notification queues
	 */
	@Override
	public int countByF_LessThan_ExpireDate(Date expireDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_LESSTHAN_EXPIREDATE;

		Object[] finderArgs = new Object[] { _getTime(expireDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NOTIFICATIONQUEUE_WHERE);

			boolean bindExpireDate = false;

			if (expireDate == null) {
				query.append(_FINDER_COLUMN_F_LESSTHAN_EXPIREDATE_EXPIREDATE_1);
			}
			else {
				bindExpireDate = true;

				query.append(_FINDER_COLUMN_F_LESSTHAN_EXPIREDATE_EXPIREDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpireDate) {
					qPos.add(new Timestamp(expireDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_LESSTHAN_EXPIREDATE_EXPIREDATE_1 =
		"notificationQueue.expireDate IS NULL";
	private static final String _FINDER_COLUMN_F_LESSTHAN_EXPIREDATE_EXPIREDATE_2 =
		"notificationQueue.expireDate >= ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_NT_CN_CPK_EMAIL = new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED,
			NotificationQueueImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_NT_CN_CPK_EMAIL",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName()
			},
			NotificationQueueModelImpl.GROUPID_COLUMN_BITMASK |
			NotificationQueueModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK |
			NotificationQueueModelImpl.CLASSNAME_COLUMN_BITMASK |
			NotificationQueueModelImpl.CLASSPK_COLUMN_BITMASK |
			NotificationQueueModelImpl.TOEMAIL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_NT_CN_CPK_EMAIL = new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_NT_CN_CPK_EMAIL",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the notification queue where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63; or throws a {@link NoSuchNotificationQueueException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toEmail the to email
	 * @return the matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail) throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByF_NT_CN_CPK_EMAIL(groupId,
				notificationType, className, classPK, toEmail);

		if (notificationQueue == null) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", notificationType=");
			msg.append(notificationType);

			msg.append(", className=");
			msg.append(className);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", toEmail=");
			msg.append(toEmail);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchNotificationQueueException(msg.toString());
		}

		return notificationQueue;
	}

	/**
	 * Returns the notification queue where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toEmail the to email
	 * @return the matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail) {
		return fetchByF_NT_CN_CPK_EMAIL(groupId, notificationType, className,
			classPK, toEmail, true);
	}

	/**
	 * Returns the notification queue where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toEmail the to email
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, notificationType, className, classPK, toEmail
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_NT_CN_CPK_EMAIL,
					finderArgs, this);
		}

		if (result instanceof NotificationQueue) {
			NotificationQueue notificationQueue = (NotificationQueue)result;

			if ((groupId != notificationQueue.getGroupId()) ||
					!Objects.equals(notificationType,
						notificationQueue.getNotificationType()) ||
					!Objects.equals(className, notificationQueue.getClassName()) ||
					!Objects.equals(classPK, notificationQueue.getClassPK()) ||
					!Objects.equals(toEmail, notificationQueue.getToEmail())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

			query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_GROUPID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_NOTIFICATIONTYPE_2);
			}

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSPK_2);
			}

			boolean bindToEmail = false;

			if (toEmail == null) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_TOEMAIL_1);
			}
			else if (toEmail.equals("")) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_TOEMAIL_3);
			}
			else {
				bindToEmail = true;

				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_TOEMAIL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindClassPK) {
					qPos.add(classPK);
				}

				if (bindToEmail) {
					qPos.add(toEmail);
				}

				List<NotificationQueue> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_NT_CN_CPK_EMAIL,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"NotificationQueuePersistenceImpl.fetchByF_NT_CN_CPK_EMAIL(long, String, String, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					NotificationQueue notificationQueue = list.get(0);

					result = notificationQueue;

					cacheResult(notificationQueue);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_NT_CN_CPK_EMAIL,
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
			return (NotificationQueue)result;
		}
	}

	/**
	 * Removes the notification queue where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toEmail the to email
	 * @return the notification queue that was removed
	 */
	@Override
	public NotificationQueue removeByF_NT_CN_CPK_EMAIL(long groupId,
		String notificationType, String className, String classPK,
		String toEmail) throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = findByF_NT_CN_CPK_EMAIL(groupId,
				notificationType, className, classPK, toEmail);

		return remove(notificationQueue);
	}

	/**
	 * Returns the number of notification queues where groupId = &#63; and notificationType = &#63; and className = &#63; and classPK = &#63; and toEmail = &#63;.
	 *
	 * @param groupId the group ID
	 * @param notificationType the notification type
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toEmail the to email
	 * @return the number of matching notification queues
	 */
	@Override
	public int countByF_NT_CN_CPK_EMAIL(long groupId, String notificationType,
		String className, String classPK, String toEmail) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_NT_CN_CPK_EMAIL;

		Object[] finderArgs = new Object[] {
				groupId, notificationType, className, classPK, toEmail
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_NOTIFICATIONQUEUE_WHERE);

			query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_GROUPID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_NOTIFICATIONTYPE_2);
			}

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSPK_2);
			}

			boolean bindToEmail = false;

			if (toEmail == null) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_TOEMAIL_1);
			}
			else if (toEmail.equals("")) {
				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_TOEMAIL_3);
			}
			else {
				bindToEmail = true;

				query.append(_FINDER_COLUMN_F_NT_CN_CPK_EMAIL_TOEMAIL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindClassPK) {
					qPos.add(classPK);
				}

				if (bindToEmail) {
					qPos.add(toEmail);
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

	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_GROUPID_2 = "notificationQueue.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_NOTIFICATIONTYPE_1 =
		"notificationQueue.notificationType IS NULL AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_NOTIFICATIONTYPE_2 =
		"notificationQueue.notificationType = ? AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_NOTIFICATIONTYPE_3 =
		"(notificationQueue.notificationType IS NULL OR notificationQueue.notificationType = '') AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSNAME_1 = "notificationQueue.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSNAME_2 = "notificationQueue.className = ? AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSNAME_3 = "(notificationQueue.className IS NULL OR notificationQueue.className = '') AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSPK_1 = "notificationQueue.classPK IS NULL AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSPK_2 = "notificationQueue.classPK = ? AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_CLASSPK_3 = "(notificationQueue.classPK IS NULL OR notificationQueue.classPK = '') AND ";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_TOEMAIL_1 = "notificationQueue.toEmail IS NULL";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_TOEMAIL_2 = "notificationQueue.toEmail = ?";
	private static final String _FINDER_COLUMN_F_NT_CN_CPK_EMAIL_TOEMAIL_3 = "(notificationQueue.toEmail IS NULL OR notificationQueue.toEmail = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE =
		new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED,
			NotificationQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_Greate_PublicationDate_Less_ExpireDate",
			new String[] {
				String.class.getName(), Date.class.getName(),
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE =
		new FinderPath(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_Greate_PublicationDate_Less_ExpireDate",
			new String[] {
				String.class.getName(), Date.class.getName(),
				Date.class.getName()
			});

	/**
	 * Returns all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @return the matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate) {
		return findByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @return the range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate,
		int start, int end) {
		return findByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate,
		int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return findByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
			publicationDate, expireDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching notification queues
	 */
	@Override
	public List<NotificationQueue> findByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate,
		int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE;
		finderArgs = new Object[] {
				notificationType, _getTime(publicationDate),
				_getTime(expireDate),
				
				start, end, orderByComparator
			};

		List<NotificationQueue> list = null;

		if (retrieveFromCache) {
			list = (List<NotificationQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationQueue notificationQueue : list) {
					if (!Objects.equals(notificationType,
								notificationQueue.getNotificationType()) ||
							(publicationDate.getTime() <= notificationQueue.getPublicationDate()
																			   .getTime()) ||
							(expireDate.getTime() > notificationQueue.getExpireDate()
																		 .getTime())) {
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

			query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_2);
			}

			boolean bindPublicationDate = false;

			if (publicationDate == null) {
				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_PUBLICATIONDATE_1);
			}
			else {
				bindPublicationDate = true;

				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_PUBLICATIONDATE_2);
			}

			boolean bindExpireDate = false;

			if (expireDate == null) {
				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_EXPIREDATE_1);
			}
			else {
				bindExpireDate = true;

				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_EXPIREDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindPublicationDate) {
					qPos.add(new Timestamp(publicationDate.getTime()));
				}

				if (bindExpireDate) {
					qPos.add(new Timestamp(expireDate.getTime()));
				}

				if (!pagination) {
					list = (List<NotificationQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NotificationQueue>)QueryUtil.list(q,
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
	 * Returns the first notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByF_Greate_PublicationDate_Less_ExpireDate_First(
		String notificationType, Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByF_Greate_PublicationDate_Less_ExpireDate_First(notificationType,
				publicationDate, expireDate, orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("notificationType=");
		msg.append(notificationType);

		msg.append(", publicationDate=");
		msg.append(publicationDate);

		msg.append(", expireDate=");
		msg.append(expireDate);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the first notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_Greate_PublicationDate_Less_ExpireDate_First(
		String notificationType, Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator) {
		List<NotificationQueue> list = findByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
				publicationDate, expireDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue
	 * @throws NoSuchNotificationQueueException if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue findByF_Greate_PublicationDate_Less_ExpireDate_Last(
		String notificationType, Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByF_Greate_PublicationDate_Less_ExpireDate_Last(notificationType,
				publicationDate, expireDate, orderByComparator);

		if (notificationQueue != null) {
			return notificationQueue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("notificationType=");
		msg.append(notificationType);

		msg.append(", publicationDate=");
		msg.append(publicationDate);

		msg.append(", expireDate=");
		msg.append(expireDate);

		msg.append("}");

		throw new NoSuchNotificationQueueException(msg.toString());
	}

	/**
	 * Returns the last notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification queue, or <code>null</code> if a matching notification queue could not be found
	 */
	@Override
	public NotificationQueue fetchByF_Greate_PublicationDate_Less_ExpireDate_Last(
		String notificationType, Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator) {
		int count = countByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
				publicationDate, expireDate);

		if (count == 0) {
			return null;
		}

		List<NotificationQueue> list = findByF_Greate_PublicationDate_Less_ExpireDate(notificationType,
				publicationDate, expireDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification queues before and after the current notification queue in the ordered set where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationQueueId the primary key of the current notification queue
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification queue
	 * @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue[] findByF_Greate_PublicationDate_Less_ExpireDate_PrevAndNext(
		long notificationQueueId, String notificationType,
		Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = findByPrimaryKey(notificationQueueId);

		Session session = null;

		try {
			session = openSession();

			NotificationQueue[] array = new NotificationQueueImpl[3];

			array[0] = getByF_Greate_PublicationDate_Less_ExpireDate_PrevAndNext(session,
					notificationQueue, notificationType, publicationDate,
					expireDate, orderByComparator, true);

			array[1] = notificationQueue;

			array[2] = getByF_Greate_PublicationDate_Less_ExpireDate_PrevAndNext(session,
					notificationQueue, notificationType, publicationDate,
					expireDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotificationQueue getByF_Greate_PublicationDate_Less_ExpireDate_PrevAndNext(
		Session session, NotificationQueue notificationQueue,
		String notificationType, Date publicationDate, Date expireDate,
		OrderByComparator<NotificationQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE);

		boolean bindNotificationType = false;

		if (notificationType == null) {
			query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_1);
		}
		else if (notificationType.equals("")) {
			query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_3);
		}
		else {
			bindNotificationType = true;

			query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_2);
		}

		boolean bindPublicationDate = false;

		if (publicationDate == null) {
			query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_PUBLICATIONDATE_1);
		}
		else {
			bindPublicationDate = true;

			query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_PUBLICATIONDATE_2);
		}

		boolean bindExpireDate = false;

		if (expireDate == null) {
			query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_EXPIREDATE_1);
		}
		else {
			bindExpireDate = true;

			query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_EXPIREDATE_2);
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
			query.append(NotificationQueueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindNotificationType) {
			qPos.add(notificationType);
		}

		if (bindPublicationDate) {
			qPos.add(new Timestamp(publicationDate.getTime()));
		}

		if (bindExpireDate) {
			qPos.add(new Timestamp(expireDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(notificationQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NotificationQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63; from the database.
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 */
	@Override
	public void removeByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate) {
		for (NotificationQueue notificationQueue : findByF_Greate_PublicationDate_Less_ExpireDate(
				notificationType, publicationDate, expireDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(notificationQueue);
		}
	}

	/**
	 * Returns the number of notification queues where notificationType = &#63; and publicationDate &lt; &#63; and expireDate &ge; &#63;.
	 *
	 * @param notificationType the notification type
	 * @param publicationDate the publication date
	 * @param expireDate the expire date
	 * @return the number of matching notification queues
	 */
	@Override
	public int countByF_Greate_PublicationDate_Less_ExpireDate(
		String notificationType, Date publicationDate, Date expireDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE;

		Object[] finderArgs = new Object[] {
				notificationType, _getTime(publicationDate),
				_getTime(expireDate)
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_NOTIFICATIONQUEUE_WHERE);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals("")) {
				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_2);
			}

			boolean bindPublicationDate = false;

			if (publicationDate == null) {
				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_PUBLICATIONDATE_1);
			}
			else {
				bindPublicationDate = true;

				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_PUBLICATIONDATE_2);
			}

			boolean bindExpireDate = false;

			if (expireDate == null) {
				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_EXPIREDATE_1);
			}
			else {
				bindExpireDate = true;

				query.append(_FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_EXPIREDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindPublicationDate) {
					qPos.add(new Timestamp(publicationDate.getTime()));
				}

				if (bindExpireDate) {
					qPos.add(new Timestamp(expireDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_1 =
		"notificationQueue.notificationType IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_2 =
		"notificationQueue.notificationType = ? AND ";
	private static final String _FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_NOTIFICATIONTYPE_3 =
		"(notificationQueue.notificationType IS NULL OR notificationQueue.notificationType = '') AND ";
	private static final String _FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_PUBLICATIONDATE_1 =
		"notificationQueue.publicationDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_PUBLICATIONDATE_2 =
		"notificationQueue.publicationDate < ? AND ";
	private static final String _FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_EXPIREDATE_1 =
		"notificationQueue.expireDate IS NULL";
	private static final String _FINDER_COLUMN_F_GREATE_PUBLICATIONDATE_LESS_EXPIREDATE_EXPIREDATE_2 =
		"notificationQueue.expireDate >= ?";

	public NotificationQueuePersistenceImpl() {
		setModelClass(NotificationQueue.class);
	}

	/**
	 * Caches the notification queue in the entity cache if it is enabled.
	 *
	 * @param notificationQueue the notification queue
	 */
	@Override
	public void cacheResult(NotificationQueue notificationQueue) {
		entityCache.putResult(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueImpl.class, notificationQueue.getPrimaryKey(),
			notificationQueue);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_NT_CN_CPK_EMAIL,
			new Object[] {
				notificationQueue.getGroupId(),
				notificationQueue.getNotificationType(),
				notificationQueue.getClassName(), notificationQueue.getClassPK(),
				notificationQueue.getToEmail()
			}, notificationQueue);

		notificationQueue.resetOriginalValues();
	}

	/**
	 * Caches the notification queues in the entity cache if it is enabled.
	 *
	 * @param notificationQueues the notification queues
	 */
	@Override
	public void cacheResult(List<NotificationQueue> notificationQueues) {
		for (NotificationQueue notificationQueue : notificationQueues) {
			if (entityCache.getResult(
						NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
						NotificationQueueImpl.class,
						notificationQueue.getPrimaryKey()) == null) {
				cacheResult(notificationQueue);
			}
			else {
				notificationQueue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all notification queues.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NotificationQueueImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the notification queue.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NotificationQueue notificationQueue) {
		entityCache.removeResult(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueImpl.class, notificationQueue.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((NotificationQueueModelImpl)notificationQueue,
			true);
	}

	@Override
	public void clearCache(List<NotificationQueue> notificationQueues) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NotificationQueue notificationQueue : notificationQueues) {
			entityCache.removeResult(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
				NotificationQueueImpl.class, notificationQueue.getPrimaryKey());

			clearUniqueFindersCache((NotificationQueueModelImpl)notificationQueue,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		NotificationQueueModelImpl notificationQueueModelImpl) {
		Object[] args = new Object[] {
				notificationQueueModelImpl.getGroupId(),
				notificationQueueModelImpl.getNotificationType(),
				notificationQueueModelImpl.getClassName(),
				notificationQueueModelImpl.getClassPK(),
				notificationQueueModelImpl.getToEmail()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_NT_CN_CPK_EMAIL, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_NT_CN_CPK_EMAIL, args,
			notificationQueueModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		NotificationQueueModelImpl notificationQueueModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					notificationQueueModelImpl.getGroupId(),
					notificationQueueModelImpl.getNotificationType(),
					notificationQueueModelImpl.getClassName(),
					notificationQueueModelImpl.getClassPK(),
					notificationQueueModelImpl.getToEmail()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NT_CN_CPK_EMAIL,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_NT_CN_CPK_EMAIL,
				args);
		}

		if ((notificationQueueModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_NT_CN_CPK_EMAIL.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					notificationQueueModelImpl.getOriginalGroupId(),
					notificationQueueModelImpl.getOriginalNotificationType(),
					notificationQueueModelImpl.getOriginalClassName(),
					notificationQueueModelImpl.getOriginalClassPK(),
					notificationQueueModelImpl.getOriginalToEmail()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_NT_CN_CPK_EMAIL,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_NT_CN_CPK_EMAIL,
				args);
		}
	}

	/**
	 * Creates a new notification queue with the primary key. Does not add the notification queue to the database.
	 *
	 * @param notificationQueueId the primary key for the new notification queue
	 * @return the new notification queue
	 */
	@Override
	public NotificationQueue create(long notificationQueueId) {
		NotificationQueue notificationQueue = new NotificationQueueImpl();

		notificationQueue.setNew(true);
		notificationQueue.setPrimaryKey(notificationQueueId);

		notificationQueue.setCompanyId(companyProvider.getCompanyId());

		return notificationQueue;
	}

	/**
	 * Removes the notification queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationQueueId the primary key of the notification queue
	 * @return the notification queue that was removed
	 * @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue remove(long notificationQueueId)
		throws NoSuchNotificationQueueException {
		return remove((Serializable)notificationQueueId);
	}

	/**
	 * Removes the notification queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the notification queue
	 * @return the notification queue that was removed
	 * @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue remove(Serializable primaryKey)
		throws NoSuchNotificationQueueException {
		Session session = null;

		try {
			session = openSession();

			NotificationQueue notificationQueue = (NotificationQueue)session.get(NotificationQueueImpl.class,
					primaryKey);

			if (notificationQueue == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotificationQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(notificationQueue);
		}
		catch (NoSuchNotificationQueueException nsee) {
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
	protected NotificationQueue removeImpl(NotificationQueue notificationQueue) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(notificationQueue)) {
				notificationQueue = (NotificationQueue)session.get(NotificationQueueImpl.class,
						notificationQueue.getPrimaryKeyObj());
			}

			if (notificationQueue != null) {
				session.delete(notificationQueue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (notificationQueue != null) {
			clearCache(notificationQueue);
		}

		return notificationQueue;
	}

	@Override
	public NotificationQueue updateImpl(NotificationQueue notificationQueue) {
		boolean isNew = notificationQueue.isNew();

		if (!(notificationQueue instanceof NotificationQueueModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(notificationQueue.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(notificationQueue);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in notificationQueue proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom NotificationQueue implementation " +
				notificationQueue.getClass());
		}

		NotificationQueueModelImpl notificationQueueModelImpl = (NotificationQueueModelImpl)notificationQueue;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (notificationQueue.getCreateDate() == null)) {
			if (serviceContext == null) {
				notificationQueue.setCreateDate(now);
			}
			else {
				notificationQueue.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!notificationQueueModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				notificationQueue.setModifiedDate(now);
			}
			else {
				notificationQueue.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (notificationQueue.isNew()) {
				session.save(notificationQueue);

				notificationQueue.setNew(false);
			}
			else {
				notificationQueue = (NotificationQueue)session.merge(notificationQueue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!NotificationQueueModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { notificationQueueModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((notificationQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						notificationQueueModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);

				args = new Object[] { notificationQueueModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);
			}
		}

		entityCache.putResult(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
			NotificationQueueImpl.class, notificationQueue.getPrimaryKey(),
			notificationQueue, false);

		clearUniqueFindersCache(notificationQueueModelImpl, false);
		cacheUniqueFindersCache(notificationQueueModelImpl);

		notificationQueue.resetOriginalValues();

		return notificationQueue;
	}

	/**
	 * Returns the notification queue with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the notification queue
	 * @return the notification queue
	 * @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotificationQueueException {
		NotificationQueue notificationQueue = fetchByPrimaryKey(primaryKey);

		if (notificationQueue == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotificationQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return notificationQueue;
	}

	/**
	 * Returns the notification queue with the primary key or throws a {@link NoSuchNotificationQueueException} if it could not be found.
	 *
	 * @param notificationQueueId the primary key of the notification queue
	 * @return the notification queue
	 * @throws NoSuchNotificationQueueException if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue findByPrimaryKey(long notificationQueueId)
		throws NoSuchNotificationQueueException {
		return findByPrimaryKey((Serializable)notificationQueueId);
	}

	/**
	 * Returns the notification queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the notification queue
	 * @return the notification queue, or <code>null</code> if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
				NotificationQueueImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NotificationQueue notificationQueue = (NotificationQueue)serializable;

		if (notificationQueue == null) {
			Session session = null;

			try {
				session = openSession();

				notificationQueue = (NotificationQueue)session.get(NotificationQueueImpl.class,
						primaryKey);

				if (notificationQueue != null) {
					cacheResult(notificationQueue);
				}
				else {
					entityCache.putResult(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
						NotificationQueueImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
					NotificationQueueImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return notificationQueue;
	}

	/**
	 * Returns the notification queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationQueueId the primary key of the notification queue
	 * @return the notification queue, or <code>null</code> if a notification queue with the primary key could not be found
	 */
	@Override
	public NotificationQueue fetchByPrimaryKey(long notificationQueueId) {
		return fetchByPrimaryKey((Serializable)notificationQueueId);
	}

	@Override
	public Map<Serializable, NotificationQueue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NotificationQueue> map = new HashMap<Serializable, NotificationQueue>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			NotificationQueue notificationQueue = fetchByPrimaryKey(primaryKey);

			if (notificationQueue != null) {
				map.put(primaryKey, notificationQueue);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
					NotificationQueueImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (NotificationQueue)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NOTIFICATIONQUEUE_WHERE_PKS_IN);

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

			for (NotificationQueue notificationQueue : (List<NotificationQueue>)q.list()) {
				map.put(notificationQueue.getPrimaryKeyObj(), notificationQueue);

				cacheResult(notificationQueue);

				uncachedPrimaryKeys.remove(notificationQueue.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NotificationQueueModelImpl.ENTITY_CACHE_ENABLED,
					NotificationQueueImpl.class, primaryKey, nullModel);
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
	 * Returns all the notification queues.
	 *
	 * @return the notification queues
	 */
	@Override
	public List<NotificationQueue> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @return the range of notification queues
	 */
	@Override
	public List<NotificationQueue> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notification queues
	 */
	@Override
	public List<NotificationQueue> findAll(int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification queues
	 * @param end the upper bound of the range of notification queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of notification queues
	 */
	@Override
	public List<NotificationQueue> findAll(int start, int end,
		OrderByComparator<NotificationQueue> orderByComparator,
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

		List<NotificationQueue> list = null;

		if (retrieveFromCache) {
			list = (List<NotificationQueue>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NOTIFICATIONQUEUE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NOTIFICATIONQUEUE;

				if (pagination) {
					sql = sql.concat(NotificationQueueModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NotificationQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NotificationQueue>)QueryUtil.list(q,
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
	 * Removes all the notification queues from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NotificationQueue notificationQueue : findAll()) {
			remove(notificationQueue);
		}
	}

	/**
	 * Returns the number of notification queues.
	 *
	 * @return the number of notification queues
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NOTIFICATIONQUEUE);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return NotificationQueueModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the notification queue persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NotificationQueueImpl.class.getName());
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

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_NOTIFICATIONQUEUE = "SELECT notificationQueue FROM NotificationQueue notificationQueue";
	private static final String _SQL_SELECT_NOTIFICATIONQUEUE_WHERE_PKS_IN = "SELECT notificationQueue FROM NotificationQueue notificationQueue WHERE notificationQueueId IN (";
	private static final String _SQL_SELECT_NOTIFICATIONQUEUE_WHERE = "SELECT notificationQueue FROM NotificationQueue notificationQueue WHERE ";
	private static final String _SQL_COUNT_NOTIFICATIONQUEUE = "SELECT COUNT(notificationQueue) FROM NotificationQueue notificationQueue";
	private static final String _SQL_COUNT_NOTIFICATIONQUEUE_WHERE = "SELECT COUNT(notificationQueue) FROM NotificationQueue notificationQueue WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "notificationQueue.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NotificationQueue exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No NotificationQueue exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(NotificationQueuePersistenceImpl.class);
}