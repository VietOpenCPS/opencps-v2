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

package org.opencps.sms.service.persistence.impl;

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

import org.opencps.sms.exception.NoSuchLookUpQueueException;
import org.opencps.sms.model.SMSLookUpQueue;
import org.opencps.sms.model.impl.SMSLookUpQueueImpl;
import org.opencps.sms.model.impl.SMSLookUpQueueModelImpl;
import org.opencps.sms.service.persistence.SMSLookUpQueuePersistence;

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
 * The persistence implementation for the sms look up queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoa
 * @see SMSLookUpQueuePersistence
 * @see org.opencps.sms.service.persistence.SMSLookUpQueueUtil
 * @generated
 */
@ProviderType
public class SMSLookUpQueuePersistenceImpl extends BasePersistenceImpl<SMSLookUpQueue>
	implements SMSLookUpQueuePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SMSLookUpQueueUtil} to access the sms look up queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SMSLookUpQueueImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED,
			SMSLookUpQueueImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED,
			SMSLookUpQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED,
			SMSLookUpQueueImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED,
			SMSLookUpQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SMSLookUpQueueModelImpl.UUID_COLUMN_BITMASK |
			SMSLookUpQueueModelImpl.RECEIVEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the sms look up queues where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sms look up queues where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sms look up queues
	 * @param end the upper bound of the range of sms look up queues (not inclusive)
	 * @return the range of matching sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sms look up queues where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sms look up queues
	 * @param end the upper bound of the range of sms look up queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findByUuid(String uuid, int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sms look up queues where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sms look up queues
	 * @param end the upper bound of the range of sms look up queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findByUuid(String uuid, int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator,
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

		List<SMSLookUpQueue> list = null;

		if (retrieveFromCache) {
			list = (List<SMSLookUpQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SMSLookUpQueue smsLookUpQueue : list) {
					if (!Objects.equals(uuid, smsLookUpQueue.getUuid())) {
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

			query.append(_SQL_SELECT_SMSLOOKUPQUEUE_WHERE);

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
				query.append(SMSLookUpQueueModelImpl.ORDER_BY_JPQL);
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
					list = (List<SMSLookUpQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SMSLookUpQueue>)QueryUtil.list(q,
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
	 * Returns the first sms look up queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sms look up queue
	 * @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue findByUuid_First(String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = fetchByUuid_First(uuid,
				orderByComparator);

		if (smsLookUpQueue != null) {
			return smsLookUpQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLookUpQueueException(msg.toString());
	}

	/**
	 * Returns the first sms look up queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByUuid_First(String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		List<SMSLookUpQueue> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sms look up queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sms look up queue
	 * @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue findByUuid_Last(String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = fetchByUuid_Last(uuid, orderByComparator);

		if (smsLookUpQueue != null) {
			return smsLookUpQueue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLookUpQueueException(msg.toString());
	}

	/**
	 * Returns the last sms look up queue in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByUuid_Last(String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SMSLookUpQueue> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sms look up queues before and after the current sms look up queue in the ordered set where uuid = &#63;.
	 *
	 * @param queueId the primary key of the current sms look up queue
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sms look up queue
	 * @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	 */
	@Override
	public SMSLookUpQueue[] findByUuid_PrevAndNext(long queueId, String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = findByPrimaryKey(queueId);

		Session session = null;

		try {
			session = openSession();

			SMSLookUpQueue[] array = new SMSLookUpQueueImpl[3];

			array[0] = getByUuid_PrevAndNext(session, smsLookUpQueue, uuid,
					orderByComparator, true);

			array[1] = smsLookUpQueue;

			array[2] = getByUuid_PrevAndNext(session, smsLookUpQueue, uuid,
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

	protected SMSLookUpQueue getByUuid_PrevAndNext(Session session,
		SMSLookUpQueue smsLookUpQueue, String uuid,
		OrderByComparator<SMSLookUpQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SMSLOOKUPQUEUE_WHERE);

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
			query.append(SMSLookUpQueueModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(smsLookUpQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SMSLookUpQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sms look up queues where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SMSLookUpQueue smsLookUpQueue : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(smsLookUpQueue);
		}
	}

	/**
	 * Returns the number of sms look up queues where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching sms look up queues
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SMSLOOKUPQUEUE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "smsLookUpQueue.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "smsLookUpQueue.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(smsLookUpQueue.uuid IS NULL OR smsLookUpQueue.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED,
			SMSLookUpQueueImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SMSLookUpQueueModelImpl.UUID_COLUMN_BITMASK |
			SMSLookUpQueueModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the sms look up queue where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLookUpQueueException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sms look up queue
	 * @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue findByUUID_G(String uuid, long groupId)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = fetchByUUID_G(uuid, groupId);

		if (smsLookUpQueue == null) {
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

			throw new NoSuchLookUpQueueException(msg.toString());
		}

		return smsLookUpQueue;
	}

	/**
	 * Returns the sms look up queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the sms look up queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof SMSLookUpQueue) {
			SMSLookUpQueue smsLookUpQueue = (SMSLookUpQueue)result;

			if (!Objects.equals(uuid, smsLookUpQueue.getUuid()) ||
					(groupId != smsLookUpQueue.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SMSLOOKUPQUEUE_WHERE);

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

				List<SMSLookUpQueue> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					SMSLookUpQueue smsLookUpQueue = list.get(0);

					result = smsLookUpQueue;

					cacheResult(smsLookUpQueue);
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
			return (SMSLookUpQueue)result;
		}
	}

	/**
	 * Removes the sms look up queue where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the sms look up queue that was removed
	 */
	@Override
	public SMSLookUpQueue removeByUUID_G(String uuid, long groupId)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = findByUUID_G(uuid, groupId);

		return remove(smsLookUpQueue);
	}

	/**
	 * Returns the number of sms look up queues where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching sms look up queues
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SMSLOOKUPQUEUE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "smsLookUpQueue.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "smsLookUpQueue.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(smsLookUpQueue.uuid IS NULL OR smsLookUpQueue.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "smsLookUpQueue.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED,
			SMSLookUpQueueImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED,
			SMSLookUpQueueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			SMSLookUpQueueModelImpl.UUID_COLUMN_BITMASK |
			SMSLookUpQueueModelImpl.COMPANYID_COLUMN_BITMASK |
			SMSLookUpQueueModelImpl.RECEIVEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sms look up queues where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sms look up queues where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sms look up queues
	 * @param end the upper bound of the range of sms look up queues (not inclusive)
	 * @return the range of matching sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sms look up queues where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sms look up queues
	 * @param end the upper bound of the range of sms look up queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sms look up queues where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sms look up queues
	 * @param end the upper bound of the range of sms look up queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator,
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

		List<SMSLookUpQueue> list = null;

		if (retrieveFromCache) {
			list = (List<SMSLookUpQueue>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SMSLookUpQueue smsLookUpQueue : list) {
					if (!Objects.equals(uuid, smsLookUpQueue.getUuid()) ||
							(companyId != smsLookUpQueue.getCompanyId())) {
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

			query.append(_SQL_SELECT_SMSLOOKUPQUEUE_WHERE);

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
				query.append(SMSLookUpQueueModelImpl.ORDER_BY_JPQL);
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
					list = (List<SMSLookUpQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SMSLookUpQueue>)QueryUtil.list(q,
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
	 * Returns the first sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sms look up queue
	 * @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (smsLookUpQueue != null) {
			return smsLookUpQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLookUpQueueException(msg.toString());
	}

	/**
	 * Returns the first sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		List<SMSLookUpQueue> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sms look up queue
	 * @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (smsLookUpQueue != null) {
			return smsLookUpQueue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLookUpQueueException(msg.toString());
	}

	/**
	 * Returns the last sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SMSLookUpQueue> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sms look up queues before and after the current sms look up queue in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param queueId the primary key of the current sms look up queue
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sms look up queue
	 * @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	 */
	@Override
	public SMSLookUpQueue[] findByUuid_C_PrevAndNext(long queueId, String uuid,
		long companyId, OrderByComparator<SMSLookUpQueue> orderByComparator)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = findByPrimaryKey(queueId);

		Session session = null;

		try {
			session = openSession();

			SMSLookUpQueue[] array = new SMSLookUpQueueImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, smsLookUpQueue, uuid,
					companyId, orderByComparator, true);

			array[1] = smsLookUpQueue;

			array[2] = getByUuid_C_PrevAndNext(session, smsLookUpQueue, uuid,
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

	protected SMSLookUpQueue getByUuid_C_PrevAndNext(Session session,
		SMSLookUpQueue smsLookUpQueue, String uuid, long companyId,
		OrderByComparator<SMSLookUpQueue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SMSLOOKUPQUEUE_WHERE);

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
			query.append(SMSLookUpQueueModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(smsLookUpQueue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SMSLookUpQueue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sms look up queues where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (SMSLookUpQueue smsLookUpQueue : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(smsLookUpQueue);
		}
	}

	/**
	 * Returns the number of sms look up queues where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching sms look up queues
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SMSLOOKUPQUEUE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "smsLookUpQueue.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "smsLookUpQueue.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(smsLookUpQueue.uuid IS NULL OR smsLookUpQueue.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "smsLookUpQueue.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_M_O_I_D = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED,
			SMSLookUpQueueImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByM_O_I_D", new String[] { String.class.getName() },
			SMSLookUpQueueModelImpl.MOID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_O_I_D = new FinderPath(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByM_O_I_D",
			new String[] { String.class.getName() });

	/**
	 * Returns the sms look up queue where moid = &#63; or throws a {@link NoSuchLookUpQueueException} if it could not be found.
	 *
	 * @param moid the moid
	 * @return the matching sms look up queue
	 * @throws NoSuchLookUpQueueException if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue findByM_O_I_D(String moid)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = fetchByM_O_I_D(moid);

		if (smsLookUpQueue == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("moid=");
			msg.append(moid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLookUpQueueException(msg.toString());
		}

		return smsLookUpQueue;
	}

	/**
	 * Returns the sms look up queue where moid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param moid the moid
	 * @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByM_O_I_D(String moid) {
		return fetchByM_O_I_D(moid, true);
	}

	/**
	 * Returns the sms look up queue where moid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param moid the moid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sms look up queue, or <code>null</code> if a matching sms look up queue could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByM_O_I_D(String moid, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { moid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_M_O_I_D,
					finderArgs, this);
		}

		if (result instanceof SMSLookUpQueue) {
			SMSLookUpQueue smsLookUpQueue = (SMSLookUpQueue)result;

			if (!Objects.equals(moid, smsLookUpQueue.getMoid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SMSLOOKUPQUEUE_WHERE);

			boolean bindMoid = false;

			if (moid == null) {
				query.append(_FINDER_COLUMN_M_O_I_D_MOID_1);
			}
			else if (moid.equals("")) {
				query.append(_FINDER_COLUMN_M_O_I_D_MOID_3);
			}
			else {
				bindMoid = true;

				query.append(_FINDER_COLUMN_M_O_I_D_MOID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMoid) {
					qPos.add(moid);
				}

				List<SMSLookUpQueue> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_M_O_I_D,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SMSLookUpQueuePersistenceImpl.fetchByM_O_I_D(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SMSLookUpQueue smsLookUpQueue = list.get(0);

					result = smsLookUpQueue;

					cacheResult(smsLookUpQueue);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_M_O_I_D,
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
			return (SMSLookUpQueue)result;
		}
	}

	/**
	 * Removes the sms look up queue where moid = &#63; from the database.
	 *
	 * @param moid the moid
	 * @return the sms look up queue that was removed
	 */
	@Override
	public SMSLookUpQueue removeByM_O_I_D(String moid)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = findByM_O_I_D(moid);

		return remove(smsLookUpQueue);
	}

	/**
	 * Returns the number of sms look up queues where moid = &#63;.
	 *
	 * @param moid the moid
	 * @return the number of matching sms look up queues
	 */
	@Override
	public int countByM_O_I_D(String moid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_O_I_D;

		Object[] finderArgs = new Object[] { moid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SMSLOOKUPQUEUE_WHERE);

			boolean bindMoid = false;

			if (moid == null) {
				query.append(_FINDER_COLUMN_M_O_I_D_MOID_1);
			}
			else if (moid.equals("")) {
				query.append(_FINDER_COLUMN_M_O_I_D_MOID_3);
			}
			else {
				bindMoid = true;

				query.append(_FINDER_COLUMN_M_O_I_D_MOID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMoid) {
					qPos.add(moid);
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

	private static final String _FINDER_COLUMN_M_O_I_D_MOID_1 = "smsLookUpQueue.moid IS NULL";
	private static final String _FINDER_COLUMN_M_O_I_D_MOID_2 = "smsLookUpQueue.moid = ?";
	private static final String _FINDER_COLUMN_M_O_I_D_MOID_3 = "(smsLookUpQueue.moid IS NULL OR smsLookUpQueue.moid = '')";

	public SMSLookUpQueuePersistenceImpl() {
		setModelClass(SMSLookUpQueue.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("password", "password_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the sms look up queue in the entity cache if it is enabled.
	 *
	 * @param smsLookUpQueue the sms look up queue
	 */
	@Override
	public void cacheResult(SMSLookUpQueue smsLookUpQueue) {
		entityCache.putResult(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueImpl.class, smsLookUpQueue.getPrimaryKey(),
			smsLookUpQueue);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { smsLookUpQueue.getUuid(), smsLookUpQueue.getGroupId() },
			smsLookUpQueue);

		finderCache.putResult(FINDER_PATH_FETCH_BY_M_O_I_D,
			new Object[] { smsLookUpQueue.getMoid() }, smsLookUpQueue);

		smsLookUpQueue.resetOriginalValues();
	}

	/**
	 * Caches the sms look up queues in the entity cache if it is enabled.
	 *
	 * @param smsLookUpQueues the sms look up queues
	 */
	@Override
	public void cacheResult(List<SMSLookUpQueue> smsLookUpQueues) {
		for (SMSLookUpQueue smsLookUpQueue : smsLookUpQueues) {
			if (entityCache.getResult(
						SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
						SMSLookUpQueueImpl.class, smsLookUpQueue.getPrimaryKey()) == null) {
				cacheResult(smsLookUpQueue);
			}
			else {
				smsLookUpQueue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sms look up queues.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SMSLookUpQueueImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sms look up queue.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SMSLookUpQueue smsLookUpQueue) {
		entityCache.removeResult(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueImpl.class, smsLookUpQueue.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SMSLookUpQueueModelImpl)smsLookUpQueue, true);
	}

	@Override
	public void clearCache(List<SMSLookUpQueue> smsLookUpQueues) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SMSLookUpQueue smsLookUpQueue : smsLookUpQueues) {
			entityCache.removeResult(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
				SMSLookUpQueueImpl.class, smsLookUpQueue.getPrimaryKey());

			clearUniqueFindersCache((SMSLookUpQueueModelImpl)smsLookUpQueue,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		SMSLookUpQueueModelImpl smsLookUpQueueModelImpl) {
		Object[] args = new Object[] {
				smsLookUpQueueModelImpl.getUuid(),
				smsLookUpQueueModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			smsLookUpQueueModelImpl, false);

		args = new Object[] { smsLookUpQueueModelImpl.getMoid() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_M_O_I_D, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_M_O_I_D, args,
			smsLookUpQueueModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SMSLookUpQueueModelImpl smsLookUpQueueModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					smsLookUpQueueModelImpl.getUuid(),
					smsLookUpQueueModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((smsLookUpQueueModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					smsLookUpQueueModelImpl.getOriginalUuid(),
					smsLookUpQueueModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { smsLookUpQueueModelImpl.getMoid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_O_I_D, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_O_I_D, args);
		}

		if ((smsLookUpQueueModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_M_O_I_D.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					smsLookUpQueueModelImpl.getOriginalMoid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_O_I_D, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_O_I_D, args);
		}
	}

	/**
	 * Creates a new sms look up queue with the primary key. Does not add the sms look up queue to the database.
	 *
	 * @param queueId the primary key for the new sms look up queue
	 * @return the new sms look up queue
	 */
	@Override
	public SMSLookUpQueue create(long queueId) {
		SMSLookUpQueue smsLookUpQueue = new SMSLookUpQueueImpl();

		smsLookUpQueue.setNew(true);
		smsLookUpQueue.setPrimaryKey(queueId);

		String uuid = PortalUUIDUtil.generate();

		smsLookUpQueue.setUuid(uuid);

		smsLookUpQueue.setCompanyId(companyProvider.getCompanyId());

		return smsLookUpQueue;
	}

	/**
	 * Removes the sms look up queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param queueId the primary key of the sms look up queue
	 * @return the sms look up queue that was removed
	 * @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	 */
	@Override
	public SMSLookUpQueue remove(long queueId)
		throws NoSuchLookUpQueueException {
		return remove((Serializable)queueId);
	}

	/**
	 * Removes the sms look up queue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sms look up queue
	 * @return the sms look up queue that was removed
	 * @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	 */
	@Override
	public SMSLookUpQueue remove(Serializable primaryKey)
		throws NoSuchLookUpQueueException {
		Session session = null;

		try {
			session = openSession();

			SMSLookUpQueue smsLookUpQueue = (SMSLookUpQueue)session.get(SMSLookUpQueueImpl.class,
					primaryKey);

			if (smsLookUpQueue == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLookUpQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(smsLookUpQueue);
		}
		catch (NoSuchLookUpQueueException nsee) {
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
	protected SMSLookUpQueue removeImpl(SMSLookUpQueue smsLookUpQueue) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(smsLookUpQueue)) {
				smsLookUpQueue = (SMSLookUpQueue)session.get(SMSLookUpQueueImpl.class,
						smsLookUpQueue.getPrimaryKeyObj());
			}

			if (smsLookUpQueue != null) {
				session.delete(smsLookUpQueue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (smsLookUpQueue != null) {
			clearCache(smsLookUpQueue);
		}

		return smsLookUpQueue;
	}

	@Override
	public SMSLookUpQueue updateImpl(SMSLookUpQueue smsLookUpQueue) {
		boolean isNew = smsLookUpQueue.isNew();

		if (!(smsLookUpQueue instanceof SMSLookUpQueueModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(smsLookUpQueue.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(smsLookUpQueue);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in smsLookUpQueue proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SMSLookUpQueue implementation " +
				smsLookUpQueue.getClass());
		}

		SMSLookUpQueueModelImpl smsLookUpQueueModelImpl = (SMSLookUpQueueModelImpl)smsLookUpQueue;

		if (Validator.isNull(smsLookUpQueue.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			smsLookUpQueue.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (smsLookUpQueue.getCreateDate() == null)) {
			if (serviceContext == null) {
				smsLookUpQueue.setCreateDate(now);
			}
			else {
				smsLookUpQueue.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!smsLookUpQueueModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				smsLookUpQueue.setModifiedDate(now);
			}
			else {
				smsLookUpQueue.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (smsLookUpQueue.isNew()) {
				session.save(smsLookUpQueue);

				smsLookUpQueue.setNew(false);
			}
			else {
				smsLookUpQueue = (SMSLookUpQueue)session.merge(smsLookUpQueue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SMSLookUpQueueModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { smsLookUpQueueModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					smsLookUpQueueModelImpl.getUuid(),
					smsLookUpQueueModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((smsLookUpQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						smsLookUpQueueModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { smsLookUpQueueModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((smsLookUpQueueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						smsLookUpQueueModelImpl.getOriginalUuid(),
						smsLookUpQueueModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						smsLookUpQueueModelImpl.getUuid(),
						smsLookUpQueueModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
			SMSLookUpQueueImpl.class, smsLookUpQueue.getPrimaryKey(),
			smsLookUpQueue, false);

		clearUniqueFindersCache(smsLookUpQueueModelImpl, false);
		cacheUniqueFindersCache(smsLookUpQueueModelImpl);

		smsLookUpQueue.resetOriginalValues();

		return smsLookUpQueue;
	}

	/**
	 * Returns the sms look up queue with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sms look up queue
	 * @return the sms look up queue
	 * @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	 */
	@Override
	public SMSLookUpQueue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLookUpQueueException {
		SMSLookUpQueue smsLookUpQueue = fetchByPrimaryKey(primaryKey);

		if (smsLookUpQueue == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLookUpQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return smsLookUpQueue;
	}

	/**
	 * Returns the sms look up queue with the primary key or throws a {@link NoSuchLookUpQueueException} if it could not be found.
	 *
	 * @param queueId the primary key of the sms look up queue
	 * @return the sms look up queue
	 * @throws NoSuchLookUpQueueException if a sms look up queue with the primary key could not be found
	 */
	@Override
	public SMSLookUpQueue findByPrimaryKey(long queueId)
		throws NoSuchLookUpQueueException {
		return findByPrimaryKey((Serializable)queueId);
	}

	/**
	 * Returns the sms look up queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sms look up queue
	 * @return the sms look up queue, or <code>null</code> if a sms look up queue with the primary key could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
				SMSLookUpQueueImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SMSLookUpQueue smsLookUpQueue = (SMSLookUpQueue)serializable;

		if (smsLookUpQueue == null) {
			Session session = null;

			try {
				session = openSession();

				smsLookUpQueue = (SMSLookUpQueue)session.get(SMSLookUpQueueImpl.class,
						primaryKey);

				if (smsLookUpQueue != null) {
					cacheResult(smsLookUpQueue);
				}
				else {
					entityCache.putResult(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
						SMSLookUpQueueImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
					SMSLookUpQueueImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return smsLookUpQueue;
	}

	/**
	 * Returns the sms look up queue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param queueId the primary key of the sms look up queue
	 * @return the sms look up queue, or <code>null</code> if a sms look up queue with the primary key could not be found
	 */
	@Override
	public SMSLookUpQueue fetchByPrimaryKey(long queueId) {
		return fetchByPrimaryKey((Serializable)queueId);
	}

	@Override
	public Map<Serializable, SMSLookUpQueue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SMSLookUpQueue> map = new HashMap<Serializable, SMSLookUpQueue>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SMSLookUpQueue smsLookUpQueue = fetchByPrimaryKey(primaryKey);

			if (smsLookUpQueue != null) {
				map.put(primaryKey, smsLookUpQueue);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
					SMSLookUpQueueImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SMSLookUpQueue)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SMSLOOKUPQUEUE_WHERE_PKS_IN);

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

			for (SMSLookUpQueue smsLookUpQueue : (List<SMSLookUpQueue>)q.list()) {
				map.put(smsLookUpQueue.getPrimaryKeyObj(), smsLookUpQueue);

				cacheResult(smsLookUpQueue);

				uncachedPrimaryKeys.remove(smsLookUpQueue.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SMSLookUpQueueModelImpl.ENTITY_CACHE_ENABLED,
					SMSLookUpQueueImpl.class, primaryKey, nullModel);
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
	 * Returns all the sms look up queues.
	 *
	 * @return the sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sms look up queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sms look up queues
	 * @param end the upper bound of the range of sms look up queues (not inclusive)
	 * @return the range of sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sms look up queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sms look up queues
	 * @param end the upper bound of the range of sms look up queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findAll(int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sms look up queues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSLookUpQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sms look up queues
	 * @param end the upper bound of the range of sms look up queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sms look up queues
	 */
	@Override
	public List<SMSLookUpQueue> findAll(int start, int end,
		OrderByComparator<SMSLookUpQueue> orderByComparator,
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

		List<SMSLookUpQueue> list = null;

		if (retrieveFromCache) {
			list = (List<SMSLookUpQueue>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SMSLOOKUPQUEUE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SMSLOOKUPQUEUE;

				if (pagination) {
					sql = sql.concat(SMSLookUpQueueModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SMSLookUpQueue>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SMSLookUpQueue>)QueryUtil.list(q,
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
	 * Removes all the sms look up queues from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SMSLookUpQueue smsLookUpQueue : findAll()) {
			remove(smsLookUpQueue);
		}
	}

	/**
	 * Returns the number of sms look up queues.
	 *
	 * @return the number of sms look up queues
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SMSLOOKUPQUEUE);

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
		return SMSLookUpQueueModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sms look up queue persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SMSLookUpQueueImpl.class.getName());
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
	private static final String _SQL_SELECT_SMSLOOKUPQUEUE = "SELECT smsLookUpQueue FROM SMSLookUpQueue smsLookUpQueue";
	private static final String _SQL_SELECT_SMSLOOKUPQUEUE_WHERE_PKS_IN = "SELECT smsLookUpQueue FROM SMSLookUpQueue smsLookUpQueue WHERE queueId IN (";
	private static final String _SQL_SELECT_SMSLOOKUPQUEUE_WHERE = "SELECT smsLookUpQueue FROM SMSLookUpQueue smsLookUpQueue WHERE ";
	private static final String _SQL_COUNT_SMSLOOKUPQUEUE = "SELECT COUNT(smsLookUpQueue) FROM SMSLookUpQueue smsLookUpQueue";
	private static final String _SQL_COUNT_SMSLOOKUPQUEUE_WHERE = "SELECT COUNT(smsLookUpQueue) FROM SMSLookUpQueue smsLookUpQueue WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "smsLookUpQueue.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SMSLookUpQueue exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SMSLookUpQueue exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SMSLookUpQueuePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "password"
			});
}