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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.usermgt.exception.NoSuchSyncSchedulerException;
import org.opencps.usermgt.model.SyncScheduler;
import org.opencps.usermgt.model.impl.SyncSchedulerImpl;
import org.opencps.usermgt.model.impl.SyncSchedulerModelImpl;
import org.opencps.usermgt.service.persistence.SyncSchedulerPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
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
 * The persistence implementation for the sync scheduler service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see SyncSchedulerPersistence
 * @see org.opencps.usermgt.service.persistence.SyncSchedulerUtil
 * @generated
 */
@ProviderType
public class SyncSchedulerPersistenceImpl extends BasePersistenceImpl<SyncScheduler>
	implements SyncSchedulerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SyncSchedulerUtil} to access the sync scheduler persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SyncSchedulerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED,
			SyncSchedulerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED,
			SyncSchedulerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED,
			SyncSchedulerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED,
			SyncSchedulerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			SyncSchedulerModelImpl.UUID_COLUMN_BITMASK |
			SyncSchedulerModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the sync schedulers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching sync schedulers
	 */
	@Override
	public List<SyncScheduler> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync schedulers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync schedulers
	 * @param end the upper bound of the range of sync schedulers (not inclusive)
	 * @return the range of matching sync schedulers
	 */
	@Override
	public List<SyncScheduler> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync schedulers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync schedulers
	 * @param end the upper bound of the range of sync schedulers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync schedulers
	 */
	@Override
	public List<SyncScheduler> findByUuid(String uuid, int start, int end,
		OrderByComparator<SyncScheduler> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync schedulers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync schedulers
	 * @param end the upper bound of the range of sync schedulers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync schedulers
	 */
	@Override
	public List<SyncScheduler> findByUuid(String uuid, int start, int end,
		OrderByComparator<SyncScheduler> orderByComparator,
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

		List<SyncScheduler> list = null;

		if (retrieveFromCache) {
			list = (List<SyncScheduler>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncScheduler syncScheduler : list) {
					if (!Objects.equals(uuid, syncScheduler.getUuid())) {
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

			query.append(_SQL_SELECT_SYNCSCHEDULER_WHERE);

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
				query.append(SyncSchedulerModelImpl.ORDER_BY_JPQL);
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
					list = (List<SyncScheduler>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncScheduler>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync scheduler in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync scheduler
	 * @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler findByUuid_First(String uuid,
		OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = fetchByUuid_First(uuid, orderByComparator);

		if (syncScheduler != null) {
			return syncScheduler;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSyncSchedulerException(msg.toString());
	}

	/**
	 * Returns the first sync scheduler in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByUuid_First(String uuid,
		OrderByComparator<SyncScheduler> orderByComparator) {
		List<SyncScheduler> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync scheduler in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync scheduler
	 * @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler findByUuid_Last(String uuid,
		OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = fetchByUuid_Last(uuid, orderByComparator);

		if (syncScheduler != null) {
			return syncScheduler;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSyncSchedulerException(msg.toString());
	}

	/**
	 * Returns the last sync scheduler in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByUuid_Last(String uuid,
		OrderByComparator<SyncScheduler> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SyncScheduler> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync schedulers before and after the current sync scheduler in the ordered set where uuid = &#63;.
	 *
	 * @param syncSchedulerId the primary key of the current sync scheduler
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync scheduler
	 * @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	 */
	@Override
	public SyncScheduler[] findByUuid_PrevAndNext(long syncSchedulerId,
		String uuid, OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = findByPrimaryKey(syncSchedulerId);

		Session session = null;

		try {
			session = openSession();

			SyncScheduler[] array = new SyncSchedulerImpl[3];

			array[0] = getByUuid_PrevAndNext(session, syncScheduler, uuid,
					orderByComparator, true);

			array[1] = syncScheduler;

			array[2] = getByUuid_PrevAndNext(session, syncScheduler, uuid,
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

	protected SyncScheduler getByUuid_PrevAndNext(Session session,
		SyncScheduler syncScheduler, String uuid,
		OrderByComparator<SyncScheduler> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCSCHEDULER_WHERE);

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
			query.append(SyncSchedulerModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(syncScheduler);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncScheduler> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync schedulers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SyncScheduler syncScheduler : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(syncScheduler);
		}
	}

	/**
	 * Returns the number of sync schedulers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching sync schedulers
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYNCSCHEDULER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "syncScheduler.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "syncScheduler.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(syncScheduler.uuid IS NULL OR syncScheduler.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED,
			SyncSchedulerImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SyncSchedulerModelImpl.UUID_COLUMN_BITMASK |
			SyncSchedulerModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the sync scheduler where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sync scheduler
	 * @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler findByUUID_G(String uuid, long groupId)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = fetchByUUID_G(uuid, groupId);

		if (syncScheduler == null) {
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

			throw new NoSuchSyncSchedulerException(msg.toString());
		}

		return syncScheduler;
	}

	/**
	 * Returns the sync scheduler where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the sync scheduler where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof SyncScheduler) {
			SyncScheduler syncScheduler = (SyncScheduler)result;

			if (!Objects.equals(uuid, syncScheduler.getUuid()) ||
					(groupId != syncScheduler.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SYNCSCHEDULER_WHERE);

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

				List<SyncScheduler> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					SyncScheduler syncScheduler = list.get(0);

					result = syncScheduler;

					cacheResult(syncScheduler);
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
			return (SyncScheduler)result;
		}
	}

	/**
	 * Removes the sync scheduler where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the sync scheduler that was removed
	 */
	@Override
	public SyncScheduler removeByUUID_G(String uuid, long groupId)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = findByUUID_G(uuid, groupId);

		return remove(syncScheduler);
	}

	/**
	 * Returns the number of sync schedulers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching sync schedulers
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCSCHEDULER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "syncScheduler.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "syncScheduler.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(syncScheduler.uuid IS NULL OR syncScheduler.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "syncScheduler.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_GID_NAME_SYNC = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED,
			SyncSchedulerImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGID_NAME_SYNC",
			new String[] { String.class.getName(), Date.class.getName() },
			SyncSchedulerModelImpl.CLASSNAME_COLUMN_BITMASK |
			SyncSchedulerModelImpl.SYNCDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_NAME_SYNC = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_NAME_SYNC",
			new String[] { String.class.getName(), Date.class.getName() });

	/**
	 * Returns the sync scheduler where className = &#63; and syncDate = &#63; or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	 *
	 * @param className the class name
	 * @param syncDate the sync date
	 * @return the matching sync scheduler
	 * @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler findByGID_NAME_SYNC(String className, Date syncDate)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = fetchByGID_NAME_SYNC(className, syncDate);

		if (syncScheduler == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("className=");
			msg.append(className);

			msg.append(", syncDate=");
			msg.append(syncDate);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSyncSchedulerException(msg.toString());
		}

		return syncScheduler;
	}

	/**
	 * Returns the sync scheduler where className = &#63; and syncDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param className the class name
	 * @param syncDate the sync date
	 * @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByGID_NAME_SYNC(String className, Date syncDate) {
		return fetchByGID_NAME_SYNC(className, syncDate, true);
	}

	/**
	 * Returns the sync scheduler where className = &#63; and syncDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param className the class name
	 * @param syncDate the sync date
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByGID_NAME_SYNC(String className, Date syncDate,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { className, _getTime(syncDate) };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GID_NAME_SYNC,
					finderArgs, this);
		}

		if (result instanceof SyncScheduler) {
			SyncScheduler syncScheduler = (SyncScheduler)result;

			if (!Objects.equals(className, syncScheduler.getClassName()) ||
					!Objects.equals(syncDate, syncScheduler.getSyncDate())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SYNCSCHEDULER_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_GID_NAME_SYNC_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_GID_NAME_SYNC_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_GID_NAME_SYNC_CLASSNAME_2);
			}

			boolean bindSyncDate = false;

			if (syncDate == null) {
				query.append(_FINDER_COLUMN_GID_NAME_SYNC_SYNCDATE_1);
			}
			else {
				bindSyncDate = true;

				query.append(_FINDER_COLUMN_GID_NAME_SYNC_SYNCDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindSyncDate) {
					qPos.add(new Timestamp(syncDate.getTime()));
				}

				List<SyncScheduler> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GID_NAME_SYNC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SyncSchedulerPersistenceImpl.fetchByGID_NAME_SYNC(String, Date, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SyncScheduler syncScheduler = list.get(0);

					result = syncScheduler;

					cacheResult(syncScheduler);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_NAME_SYNC,
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
			return (SyncScheduler)result;
		}
	}

	/**
	 * Removes the sync scheduler where className = &#63; and syncDate = &#63; from the database.
	 *
	 * @param className the class name
	 * @param syncDate the sync date
	 * @return the sync scheduler that was removed
	 */
	@Override
	public SyncScheduler removeByGID_NAME_SYNC(String className, Date syncDate)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = findByGID_NAME_SYNC(className, syncDate);

		return remove(syncScheduler);
	}

	/**
	 * Returns the number of sync schedulers where className = &#63; and syncDate = &#63;.
	 *
	 * @param className the class name
	 * @param syncDate the sync date
	 * @return the number of matching sync schedulers
	 */
	@Override
	public int countByGID_NAME_SYNC(String className, Date syncDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_NAME_SYNC;

		Object[] finderArgs = new Object[] { className, _getTime(syncDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCSCHEDULER_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_GID_NAME_SYNC_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_GID_NAME_SYNC_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_GID_NAME_SYNC_CLASSNAME_2);
			}

			boolean bindSyncDate = false;

			if (syncDate == null) {
				query.append(_FINDER_COLUMN_GID_NAME_SYNC_SYNCDATE_1);
			}
			else {
				bindSyncDate = true;

				query.append(_FINDER_COLUMN_GID_NAME_SYNC_SYNCDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindSyncDate) {
					qPos.add(new Timestamp(syncDate.getTime()));
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

	private static final String _FINDER_COLUMN_GID_NAME_SYNC_CLASSNAME_1 = "syncScheduler.className IS NULL AND ";
	private static final String _FINDER_COLUMN_GID_NAME_SYNC_CLASSNAME_2 = "syncScheduler.className = ? AND ";
	private static final String _FINDER_COLUMN_GID_NAME_SYNC_CLASSNAME_3 = "(syncScheduler.className IS NULL OR syncScheduler.className = '') AND ";
	private static final String _FINDER_COLUMN_GID_NAME_SYNC_SYNCDATE_1 = "syncScheduler.syncDate IS NULL";
	private static final String _FINDER_COLUMN_GID_NAME_SYNC_SYNCDATE_2 = "syncScheduler.syncDate = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_GID_NAME_TYPE = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED,
			SyncSchedulerImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGID_NAME_TYPE",
			new String[] { String.class.getName(), String.class.getName() },
			SyncSchedulerModelImpl.CLASSNAME_COLUMN_BITMASK |
			SyncSchedulerModelImpl.TYPECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_NAME_TYPE = new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_NAME_TYPE",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the sync scheduler where className = &#63; and typeCode = &#63; or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	 *
	 * @param className the class name
	 * @param typeCode the type code
	 * @return the matching sync scheduler
	 * @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler findByGID_NAME_TYPE(String className, String typeCode)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = fetchByGID_NAME_TYPE(className, typeCode);

		if (syncScheduler == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("className=");
			msg.append(className);

			msg.append(", typeCode=");
			msg.append(typeCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSyncSchedulerException(msg.toString());
		}

		return syncScheduler;
	}

	/**
	 * Returns the sync scheduler where className = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param className the class name
	 * @param typeCode the type code
	 * @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByGID_NAME_TYPE(String className, String typeCode) {
		return fetchByGID_NAME_TYPE(className, typeCode, true);
	}

	/**
	 * Returns the sync scheduler where className = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param className the class name
	 * @param typeCode the type code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByGID_NAME_TYPE(String className,
		String typeCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { className, typeCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GID_NAME_TYPE,
					finderArgs, this);
		}

		if (result instanceof SyncScheduler) {
			SyncScheduler syncScheduler = (SyncScheduler)result;

			if (!Objects.equals(className, syncScheduler.getClassName()) ||
					!Objects.equals(typeCode, syncScheduler.getTypeCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SYNCSCHEDULER_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_GID_NAME_TYPE_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_GID_NAME_TYPE_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_GID_NAME_TYPE_CLASSNAME_2);
			}

			boolean bindTypeCode = false;

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_GID_NAME_TYPE_TYPECODE_1);
			}
			else if (typeCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_NAME_TYPE_TYPECODE_3);
			}
			else {
				bindTypeCode = true;

				query.append(_FINDER_COLUMN_GID_NAME_TYPE_TYPECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindTypeCode) {
					qPos.add(typeCode);
				}

				List<SyncScheduler> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GID_NAME_TYPE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SyncSchedulerPersistenceImpl.fetchByGID_NAME_TYPE(String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SyncScheduler syncScheduler = list.get(0);

					result = syncScheduler;

					cacheResult(syncScheduler);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_NAME_TYPE,
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
			return (SyncScheduler)result;
		}
	}

	/**
	 * Removes the sync scheduler where className = &#63; and typeCode = &#63; from the database.
	 *
	 * @param className the class name
	 * @param typeCode the type code
	 * @return the sync scheduler that was removed
	 */
	@Override
	public SyncScheduler removeByGID_NAME_TYPE(String className, String typeCode)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = findByGID_NAME_TYPE(className, typeCode);

		return remove(syncScheduler);
	}

	/**
	 * Returns the number of sync schedulers where className = &#63; and typeCode = &#63;.
	 *
	 * @param className the class name
	 * @param typeCode the type code
	 * @return the number of matching sync schedulers
	 */
	@Override
	public int countByGID_NAME_TYPE(String className, String typeCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_NAME_TYPE;

		Object[] finderArgs = new Object[] { className, typeCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCSCHEDULER_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_GID_NAME_TYPE_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_GID_NAME_TYPE_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_GID_NAME_TYPE_CLASSNAME_2);
			}

			boolean bindTypeCode = false;

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_GID_NAME_TYPE_TYPECODE_1);
			}
			else if (typeCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_NAME_TYPE_TYPECODE_3);
			}
			else {
				bindTypeCode = true;

				query.append(_FINDER_COLUMN_GID_NAME_TYPE_TYPECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindTypeCode) {
					qPos.add(typeCode);
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

	private static final String _FINDER_COLUMN_GID_NAME_TYPE_CLASSNAME_1 = "syncScheduler.className IS NULL AND ";
	private static final String _FINDER_COLUMN_GID_NAME_TYPE_CLASSNAME_2 = "syncScheduler.className = ? AND ";
	private static final String _FINDER_COLUMN_GID_NAME_TYPE_CLASSNAME_3 = "(syncScheduler.className IS NULL OR syncScheduler.className = '') AND ";
	private static final String _FINDER_COLUMN_GID_NAME_TYPE_TYPECODE_1 = "syncScheduler.typeCode IS NULL";
	private static final String _FINDER_COLUMN_GID_NAME_TYPE_TYPECODE_2 = "syncScheduler.typeCode = ?";
	private static final String _FINDER_COLUMN_GID_NAME_TYPE_TYPECODE_3 = "(syncScheduler.typeCode IS NULL OR syncScheduler.typeCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NAME_RETRY =
		new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED,
			SyncSchedulerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_NAME_RETRY",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_NAME_RETRY =
		new FinderPath(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_NAME_RETRY",
			new String[] { String.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the sync schedulers where className = &#63; and retry &lt; &#63;.
	 *
	 * @param className the class name
	 * @param retry the retry
	 * @return the matching sync schedulers
	 */
	@Override
	public List<SyncScheduler> findByF_NAME_RETRY(String className, int retry) {
		return findByF_NAME_RETRY(className, retry, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync schedulers where className = &#63; and retry &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param retry the retry
	 * @param start the lower bound of the range of sync schedulers
	 * @param end the upper bound of the range of sync schedulers (not inclusive)
	 * @return the range of matching sync schedulers
	 */
	@Override
	public List<SyncScheduler> findByF_NAME_RETRY(String className, int retry,
		int start, int end) {
		return findByF_NAME_RETRY(className, retry, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync schedulers where className = &#63; and retry &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param retry the retry
	 * @param start the lower bound of the range of sync schedulers
	 * @param end the upper bound of the range of sync schedulers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync schedulers
	 */
	@Override
	public List<SyncScheduler> findByF_NAME_RETRY(String className, int retry,
		int start, int end, OrderByComparator<SyncScheduler> orderByComparator) {
		return findByF_NAME_RETRY(className, retry, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync schedulers where className = &#63; and retry &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param retry the retry
	 * @param start the lower bound of the range of sync schedulers
	 * @param end the upper bound of the range of sync schedulers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync schedulers
	 */
	@Override
	public List<SyncScheduler> findByF_NAME_RETRY(String className, int retry,
		int start, int end, OrderByComparator<SyncScheduler> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NAME_RETRY;
		finderArgs = new Object[] {
				className, retry,
				
				start, end, orderByComparator
			};

		List<SyncScheduler> list = null;

		if (retrieveFromCache) {
			list = (List<SyncScheduler>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncScheduler syncScheduler : list) {
					if (!Objects.equals(className, syncScheduler.getClassName()) ||
							(retry <= syncScheduler.getRetry())) {
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

			query.append(_SQL_SELECT_SYNCSCHEDULER_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_F_NAME_RETRY_RETRY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncSchedulerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				qPos.add(retry);

				if (!pagination) {
					list = (List<SyncScheduler>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncScheduler>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	 *
	 * @param className the class name
	 * @param retry the retry
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync scheduler
	 * @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler findByF_NAME_RETRY_First(String className, int retry,
		OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = fetchByF_NAME_RETRY_First(className,
				retry, orderByComparator);

		if (syncScheduler != null) {
			return syncScheduler;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(", retry=");
		msg.append(retry);

		msg.append("}");

		throw new NoSuchSyncSchedulerException(msg.toString());
	}

	/**
	 * Returns the first sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	 *
	 * @param className the class name
	 * @param retry the retry
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByF_NAME_RETRY_First(String className, int retry,
		OrderByComparator<SyncScheduler> orderByComparator) {
		List<SyncScheduler> list = findByF_NAME_RETRY(className, retry, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	 *
	 * @param className the class name
	 * @param retry the retry
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync scheduler
	 * @throws NoSuchSyncSchedulerException if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler findByF_NAME_RETRY_Last(String className, int retry,
		OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = fetchByF_NAME_RETRY_Last(className,
				retry, orderByComparator);

		if (syncScheduler != null) {
			return syncScheduler;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(", retry=");
		msg.append(retry);

		msg.append("}");

		throw new NoSuchSyncSchedulerException(msg.toString());
	}

	/**
	 * Returns the last sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	 *
	 * @param className the class name
	 * @param retry the retry
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync scheduler, or <code>null</code> if a matching sync scheduler could not be found
	 */
	@Override
	public SyncScheduler fetchByF_NAME_RETRY_Last(String className, int retry,
		OrderByComparator<SyncScheduler> orderByComparator) {
		int count = countByF_NAME_RETRY(className, retry);

		if (count == 0) {
			return null;
		}

		List<SyncScheduler> list = findByF_NAME_RETRY(className, retry,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync schedulers before and after the current sync scheduler in the ordered set where className = &#63; and retry &lt; &#63;.
	 *
	 * @param syncSchedulerId the primary key of the current sync scheduler
	 * @param className the class name
	 * @param retry the retry
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync scheduler
	 * @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	 */
	@Override
	public SyncScheduler[] findByF_NAME_RETRY_PrevAndNext(
		long syncSchedulerId, String className, int retry,
		OrderByComparator<SyncScheduler> orderByComparator)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = findByPrimaryKey(syncSchedulerId);

		Session session = null;

		try {
			session = openSession();

			SyncScheduler[] array = new SyncSchedulerImpl[3];

			array[0] = getByF_NAME_RETRY_PrevAndNext(session, syncScheduler,
					className, retry, orderByComparator, true);

			array[1] = syncScheduler;

			array[2] = getByF_NAME_RETRY_PrevAndNext(session, syncScheduler,
					className, retry, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncScheduler getByF_NAME_RETRY_PrevAndNext(Session session,
		SyncScheduler syncScheduler, String className, int retry,
		OrderByComparator<SyncScheduler> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SYNCSCHEDULER_WHERE);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_1);
		}
		else if (className.equals("")) {
			query.append(_FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_F_NAME_RETRY_RETRY_2);

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
			query.append(SyncSchedulerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindClassName) {
			qPos.add(className);
		}

		qPos.add(retry);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncScheduler);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncScheduler> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync schedulers where className = &#63; and retry &lt; &#63; from the database.
	 *
	 * @param className the class name
	 * @param retry the retry
	 */
	@Override
	public void removeByF_NAME_RETRY(String className, int retry) {
		for (SyncScheduler syncScheduler : findByF_NAME_RETRY(className, retry,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncScheduler);
		}
	}

	/**
	 * Returns the number of sync schedulers where className = &#63; and retry &lt; &#63;.
	 *
	 * @param className the class name
	 * @param retry the retry
	 * @return the number of matching sync schedulers
	 */
	@Override
	public int countByF_NAME_RETRY(String className, int retry) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_NAME_RETRY;

		Object[] finderArgs = new Object[] { className, retry };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCSCHEDULER_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_F_NAME_RETRY_RETRY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				qPos.add(retry);

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

	private static final String _FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_1 = "syncScheduler.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_2 = "syncScheduler.className = ? AND ";
	private static final String _FINDER_COLUMN_F_NAME_RETRY_CLASSNAME_3 = "(syncScheduler.className IS NULL OR syncScheduler.className = '') AND ";
	private static final String _FINDER_COLUMN_F_NAME_RETRY_RETRY_2 = "syncScheduler.retry < ?";

	public SyncSchedulerPersistenceImpl() {
		setModelClass(SyncScheduler.class);

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
	 * Caches the sync scheduler in the entity cache if it is enabled.
	 *
	 * @param syncScheduler the sync scheduler
	 */
	@Override
	public void cacheResult(SyncScheduler syncScheduler) {
		entityCache.putResult(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerImpl.class, syncScheduler.getPrimaryKey(),
			syncScheduler);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { syncScheduler.getUuid(), syncScheduler.getGroupId() },
			syncScheduler);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_NAME_SYNC,
			new Object[] {
				syncScheduler.getClassName(), syncScheduler.getSyncDate()
			}, syncScheduler);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_NAME_TYPE,
			new Object[] {
				syncScheduler.getClassName(), syncScheduler.getTypeCode()
			}, syncScheduler);

		syncScheduler.resetOriginalValues();
	}

	/**
	 * Caches the sync schedulers in the entity cache if it is enabled.
	 *
	 * @param syncSchedulers the sync schedulers
	 */
	@Override
	public void cacheResult(List<SyncScheduler> syncSchedulers) {
		for (SyncScheduler syncScheduler : syncSchedulers) {
			if (entityCache.getResult(
						SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
						SyncSchedulerImpl.class, syncScheduler.getPrimaryKey()) == null) {
				cacheResult(syncScheduler);
			}
			else {
				syncScheduler.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sync schedulers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SyncSchedulerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sync scheduler.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SyncScheduler syncScheduler) {
		entityCache.removeResult(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerImpl.class, syncScheduler.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SyncSchedulerModelImpl)syncScheduler, true);
	}

	@Override
	public void clearCache(List<SyncScheduler> syncSchedulers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SyncScheduler syncScheduler : syncSchedulers) {
			entityCache.removeResult(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
				SyncSchedulerImpl.class, syncScheduler.getPrimaryKey());

			clearUniqueFindersCache((SyncSchedulerModelImpl)syncScheduler, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SyncSchedulerModelImpl syncSchedulerModelImpl) {
		Object[] args = new Object[] {
				syncSchedulerModelImpl.getUuid(),
				syncSchedulerModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			syncSchedulerModelImpl, false);

		args = new Object[] {
				syncSchedulerModelImpl.getClassName(),
				_getTime(syncSchedulerModelImpl.getSyncDate())
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GID_NAME_SYNC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_NAME_SYNC, args,
			syncSchedulerModelImpl, false);

		args = new Object[] {
				syncSchedulerModelImpl.getClassName(),
				syncSchedulerModelImpl.getTypeCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GID_NAME_TYPE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_NAME_TYPE, args,
			syncSchedulerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SyncSchedulerModelImpl syncSchedulerModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					syncSchedulerModelImpl.getUuid(),
					syncSchedulerModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((syncSchedulerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					syncSchedulerModelImpl.getOriginalUuid(),
					syncSchedulerModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					syncSchedulerModelImpl.getClassName(),
					_getTime(syncSchedulerModelImpl.getSyncDate())
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_NAME_SYNC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_NAME_SYNC, args);
		}

		if ((syncSchedulerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GID_NAME_SYNC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					syncSchedulerModelImpl.getOriginalClassName(),
					_getTime(syncSchedulerModelImpl.getOriginalSyncDate())
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_NAME_SYNC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_NAME_SYNC, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					syncSchedulerModelImpl.getClassName(),
					syncSchedulerModelImpl.getTypeCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_NAME_TYPE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_NAME_TYPE, args);
		}

		if ((syncSchedulerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GID_NAME_TYPE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					syncSchedulerModelImpl.getOriginalClassName(),
					syncSchedulerModelImpl.getOriginalTypeCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_NAME_TYPE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_NAME_TYPE, args);
		}
	}

	/**
	 * Creates a new sync scheduler with the primary key. Does not add the sync scheduler to the database.
	 *
	 * @param syncSchedulerId the primary key for the new sync scheduler
	 * @return the new sync scheduler
	 */
	@Override
	public SyncScheduler create(long syncSchedulerId) {
		SyncScheduler syncScheduler = new SyncSchedulerImpl();

		syncScheduler.setNew(true);
		syncScheduler.setPrimaryKey(syncSchedulerId);

		String uuid = PortalUUIDUtil.generate();

		syncScheduler.setUuid(uuid);

		return syncScheduler;
	}

	/**
	 * Removes the sync scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncSchedulerId the primary key of the sync scheduler
	 * @return the sync scheduler that was removed
	 * @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	 */
	@Override
	public SyncScheduler remove(long syncSchedulerId)
		throws NoSuchSyncSchedulerException {
		return remove((Serializable)syncSchedulerId);
	}

	/**
	 * Removes the sync scheduler with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sync scheduler
	 * @return the sync scheduler that was removed
	 * @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	 */
	@Override
	public SyncScheduler remove(Serializable primaryKey)
		throws NoSuchSyncSchedulerException {
		Session session = null;

		try {
			session = openSession();

			SyncScheduler syncScheduler = (SyncScheduler)session.get(SyncSchedulerImpl.class,
					primaryKey);

			if (syncScheduler == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSyncSchedulerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(syncScheduler);
		}
		catch (NoSuchSyncSchedulerException nsee) {
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
	protected SyncScheduler removeImpl(SyncScheduler syncScheduler) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(syncScheduler)) {
				syncScheduler = (SyncScheduler)session.get(SyncSchedulerImpl.class,
						syncScheduler.getPrimaryKeyObj());
			}

			if (syncScheduler != null) {
				session.delete(syncScheduler);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (syncScheduler != null) {
			clearCache(syncScheduler);
		}

		return syncScheduler;
	}

	@Override
	public SyncScheduler updateImpl(SyncScheduler syncScheduler) {
		boolean isNew = syncScheduler.isNew();

		if (!(syncScheduler instanceof SyncSchedulerModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(syncScheduler.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(syncScheduler);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in syncScheduler proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SyncScheduler implementation " +
				syncScheduler.getClass());
		}

		SyncSchedulerModelImpl syncSchedulerModelImpl = (SyncSchedulerModelImpl)syncScheduler;

		if (Validator.isNull(syncScheduler.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			syncScheduler.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (syncScheduler.getCreateDate() == null)) {
			if (serviceContext == null) {
				syncScheduler.setCreateDate(now);
			}
			else {
				syncScheduler.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!syncSchedulerModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				syncScheduler.setModifiedDate(now);
			}
			else {
				syncScheduler.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (syncScheduler.isNew()) {
				session.save(syncScheduler);

				syncScheduler.setNew(false);
			}
			else {
				syncScheduler = (SyncScheduler)session.merge(syncScheduler);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SyncSchedulerModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { syncSchedulerModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((syncSchedulerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncSchedulerModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { syncSchedulerModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
			SyncSchedulerImpl.class, syncScheduler.getPrimaryKey(),
			syncScheduler, false);

		clearUniqueFindersCache(syncSchedulerModelImpl, false);
		cacheUniqueFindersCache(syncSchedulerModelImpl);

		syncScheduler.resetOriginalValues();

		return syncScheduler;
	}

	/**
	 * Returns the sync scheduler with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync scheduler
	 * @return the sync scheduler
	 * @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	 */
	@Override
	public SyncScheduler findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSyncSchedulerException {
		SyncScheduler syncScheduler = fetchByPrimaryKey(primaryKey);

		if (syncScheduler == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSyncSchedulerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return syncScheduler;
	}

	/**
	 * Returns the sync scheduler with the primary key or throws a {@link NoSuchSyncSchedulerException} if it could not be found.
	 *
	 * @param syncSchedulerId the primary key of the sync scheduler
	 * @return the sync scheduler
	 * @throws NoSuchSyncSchedulerException if a sync scheduler with the primary key could not be found
	 */
	@Override
	public SyncScheduler findByPrimaryKey(long syncSchedulerId)
		throws NoSuchSyncSchedulerException {
		return findByPrimaryKey((Serializable)syncSchedulerId);
	}

	/**
	 * Returns the sync scheduler with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync scheduler
	 * @return the sync scheduler, or <code>null</code> if a sync scheduler with the primary key could not be found
	 */
	@Override
	public SyncScheduler fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
				SyncSchedulerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SyncScheduler syncScheduler = (SyncScheduler)serializable;

		if (syncScheduler == null) {
			Session session = null;

			try {
				session = openSession();

				syncScheduler = (SyncScheduler)session.get(SyncSchedulerImpl.class,
						primaryKey);

				if (syncScheduler != null) {
					cacheResult(syncScheduler);
				}
				else {
					entityCache.putResult(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
						SyncSchedulerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
					SyncSchedulerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return syncScheduler;
	}

	/**
	 * Returns the sync scheduler with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param syncSchedulerId the primary key of the sync scheduler
	 * @return the sync scheduler, or <code>null</code> if a sync scheduler with the primary key could not be found
	 */
	@Override
	public SyncScheduler fetchByPrimaryKey(long syncSchedulerId) {
		return fetchByPrimaryKey((Serializable)syncSchedulerId);
	}

	@Override
	public Map<Serializable, SyncScheduler> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SyncScheduler> map = new HashMap<Serializable, SyncScheduler>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SyncScheduler syncScheduler = fetchByPrimaryKey(primaryKey);

			if (syncScheduler != null) {
				map.put(primaryKey, syncScheduler);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
					SyncSchedulerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SyncScheduler)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SYNCSCHEDULER_WHERE_PKS_IN);

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

			for (SyncScheduler syncScheduler : (List<SyncScheduler>)q.list()) {
				map.put(syncScheduler.getPrimaryKeyObj(), syncScheduler);

				cacheResult(syncScheduler);

				uncachedPrimaryKeys.remove(syncScheduler.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SyncSchedulerModelImpl.ENTITY_CACHE_ENABLED,
					SyncSchedulerImpl.class, primaryKey, nullModel);
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
	 * Returns all the sync schedulers.
	 *
	 * @return the sync schedulers
	 */
	@Override
	public List<SyncScheduler> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync schedulers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync schedulers
	 * @param end the upper bound of the range of sync schedulers (not inclusive)
	 * @return the range of sync schedulers
	 */
	@Override
	public List<SyncScheduler> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync schedulers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync schedulers
	 * @param end the upper bound of the range of sync schedulers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sync schedulers
	 */
	@Override
	public List<SyncScheduler> findAll(int start, int end,
		OrderByComparator<SyncScheduler> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync schedulers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncSchedulerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync schedulers
	 * @param end the upper bound of the range of sync schedulers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sync schedulers
	 */
	@Override
	public List<SyncScheduler> findAll(int start, int end,
		OrderByComparator<SyncScheduler> orderByComparator,
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

		List<SyncScheduler> list = null;

		if (retrieveFromCache) {
			list = (List<SyncScheduler>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SYNCSCHEDULER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SYNCSCHEDULER;

				if (pagination) {
					sql = sql.concat(SyncSchedulerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SyncScheduler>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncScheduler>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the sync schedulers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SyncScheduler syncScheduler : findAll()) {
			remove(syncScheduler);
		}
	}

	/**
	 * Returns the number of sync schedulers.
	 *
	 * @return the number of sync schedulers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SYNCSCHEDULER);

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
		return SyncSchedulerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sync scheduler persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SyncSchedulerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

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

	private static final String _SQL_SELECT_SYNCSCHEDULER = "SELECT syncScheduler FROM SyncScheduler syncScheduler";
	private static final String _SQL_SELECT_SYNCSCHEDULER_WHERE_PKS_IN = "SELECT syncScheduler FROM SyncScheduler syncScheduler WHERE syncSchedulerId IN (";
	private static final String _SQL_SELECT_SYNCSCHEDULER_WHERE = "SELECT syncScheduler FROM SyncScheduler syncScheduler WHERE ";
	private static final String _SQL_COUNT_SYNCSCHEDULER = "SELECT COUNT(syncScheduler) FROM SyncScheduler syncScheduler";
	private static final String _SQL_COUNT_SYNCSCHEDULER_WHERE = "SELECT COUNT(syncScheduler) FROM SyncScheduler syncScheduler WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "syncScheduler.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SyncScheduler exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SyncScheduler exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SyncSchedulerPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}