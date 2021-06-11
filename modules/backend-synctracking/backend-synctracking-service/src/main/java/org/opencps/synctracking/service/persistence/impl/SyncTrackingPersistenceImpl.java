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

package org.opencps.synctracking.service.persistence.impl;

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

import org.opencps.synctracking.exception.NoSuchSyncTrackingException;
import org.opencps.synctracking.model.SyncTracking;
import org.opencps.synctracking.model.impl.SyncTrackingImpl;
import org.opencps.synctracking.model.impl.SyncTrackingModelImpl;
import org.opencps.synctracking.service.persistence.SyncTrackingPersistence;

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
 * The persistence implementation for the sync tracking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncTrackingPersistence
 * @see org.opencps.synctracking.service.persistence.SyncTrackingUtil
 * @generated
 */
@ProviderType
public class SyncTrackingPersistenceImpl extends BasePersistenceImpl<SyncTracking>
	implements SyncTrackingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SyncTrackingUtil} to access the sync tracking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SyncTrackingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SyncTrackingModelImpl.UUID_COLUMN_BITMASK |
			SyncTrackingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the sync trackings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByUuid(String uuid, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByUuid(String uuid, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
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

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if (!Objects.equals(uuid, syncTracking.getUuid())) {
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

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

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
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
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
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByUuid_First(String uuid,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByUuid_First(uuid, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByUuid_First(String uuid,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByUuid_Last(String uuid,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByUuid_Last(uuid, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByUuid_Last(String uuid,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where uuid = &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByUuid_PrevAndNext(long trackingId, String uuid,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByUuid_PrevAndNext(session, syncTracking, uuid,
					orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByUuid_PrevAndNext(session, syncTracking, uuid,
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

	protected SyncTracking getByUuid_PrevAndNext(Session session,
		SyncTracking syncTracking, String uuid,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SyncTracking syncTracking : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "syncTracking.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "syncTracking.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(syncTracking.uuid IS NULL OR syncTracking.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SyncTrackingModelImpl.UUID_COLUMN_BITMASK |
			SyncTrackingModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the sync tracking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByUUID_G(String uuid, long groupId)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByUUID_G(uuid, groupId);

		if (syncTracking == null) {
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

			throw new NoSuchSyncTrackingException(msg.toString());
		}

		return syncTracking;
	}

	/**
	 * Returns the sync tracking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the sync tracking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof SyncTracking) {
			SyncTracking syncTracking = (SyncTracking)result;

			if (!Objects.equals(uuid, syncTracking.getUuid()) ||
					(groupId != syncTracking.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

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

				List<SyncTracking> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					SyncTracking syncTracking = list.get(0);

					result = syncTracking;

					cacheResult(syncTracking);
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
			return (SyncTracking)result;
		}
	}

	/**
	 * Removes the sync tracking where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the sync tracking that was removed
	 */
	@Override
	public SyncTracking removeByUUID_G(String uuid, long groupId)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByUUID_G(uuid, groupId);

		return remove(syncTracking);
	}

	/**
	 * Returns the number of sync trackings where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "syncTracking.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "syncTracking.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(syncTracking.uuid IS NULL OR syncTracking.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "syncTracking.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			SyncTrackingModelImpl.UUID_COLUMN_BITMASK |
			SyncTrackingModelImpl.COMPANYID_COLUMN_BITMASK |
			SyncTrackingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sync trackings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator,
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

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if (!Objects.equals(uuid, syncTracking.getUuid()) ||
							(companyId != syncTracking.getCompanyId())) {
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

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

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
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
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
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByUuid_C_PrevAndNext(long trackingId,
		String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, syncTracking, uuid,
					companyId, orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByUuid_C_PrevAndNext(session, syncTracking, uuid,
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

	protected SyncTracking getByUuid_C_PrevAndNext(Session session,
		SyncTracking syncTracking, String uuid, long companyId,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (SyncTracking syncTracking : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "syncTracking.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "syncTracking.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(syncTracking.uuid IS NULL OR syncTracking.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "syncTracking.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			SyncTrackingModelImpl.GROUPID_COLUMN_BITMASK |
			SyncTrackingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the sync trackings where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByGroupId(long groupId, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByGroupId(long groupId, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId())) {
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

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByGroupId_First(long groupId,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByGroupId_First(groupId,
				orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByGroupId_First(long groupId,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByGroupId_Last(long groupId,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByGroupId_Last(long groupId,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByGroupId_PrevAndNext(long trackingId,
		long groupId, OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, syncTracking, groupId,
					orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByGroupId_PrevAndNext(session, syncTracking, groupId,
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

	protected SyncTracking getByGroupId_PrevAndNext(Session session,
		SyncTracking syncTracking, long groupId,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (SyncTracking syncTracking : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "syncTracking.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_REFERENCEUID = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_GID_ReferenceUid",
			new String[] { Long.class.getName(), String.class.getName() },
			SyncTrackingModelImpl.GROUPID_COLUMN_BITMASK |
			SyncTrackingModelImpl.REFERENCEUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_REFERENCEUID = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_GID_ReferenceUid",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the sync tracking where groupId = &#63; and referenceUid = &#63; or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_ReferenceUid(long groupId,
		String referenceUid) throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_ReferenceUid(groupId,
				referenceUid);

		if (syncTracking == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", referenceUid=");
			msg.append(referenceUid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSyncTrackingException(msg.toString());
		}

		return syncTracking;
	}

	/**
	 * Returns the sync tracking where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_ReferenceUid(long groupId,
		String referenceUid) {
		return fetchByF_GID_ReferenceUid(groupId, referenceUid, true);
	}

	/**
	 * Returns the sync tracking where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_ReferenceUid(long groupId,
		String referenceUid, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, referenceUid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_REFERENCEUID,
					finderArgs, this);
		}

		if (result instanceof SyncTracking) {
			SyncTracking syncTracking = (SyncTracking)result;

			if ((groupId != syncTracking.getGroupId()) ||
					!Objects.equals(referenceUid, syncTracking.getReferenceUid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_REFERENCEUID_GROUPID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCEUID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCEUID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_F_GID_REFERENCEUID_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				List<SyncTracking> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_REFERENCEUID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SyncTrackingPersistenceImpl.fetchByF_GID_ReferenceUid(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SyncTracking syncTracking = list.get(0);

					result = syncTracking;

					cacheResult(syncTracking);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_REFERENCEUID,
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
			return (SyncTracking)result;
		}
	}

	/**
	 * Removes the sync tracking where groupId = &#63; and referenceUid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the sync tracking that was removed
	 */
	@Override
	public SyncTracking removeByF_GID_ReferenceUid(long groupId,
		String referenceUid) throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByF_GID_ReferenceUid(groupId,
				referenceUid);

		return remove(syncTracking);
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and referenceUid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_ReferenceUid(long groupId, String referenceUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_REFERENCEUID;

		Object[] finderArgs = new Object[] { groupId, referenceUid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_REFERENCEUID_GROUPID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCEUID_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCEUID_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_F_GID_REFERENCEUID_REFERENCEUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
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

	private static final String _FINDER_COLUMN_F_GID_REFERENCEUID_GROUPID_2 = "syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_REFERENCEUID_REFERENCEUID_1 =
		"syncTracking.referenceUid IS NULL";
	private static final String _FINDER_COLUMN_F_GID_REFERENCEUID_REFERENCEUID_2 =
		"syncTracking.referenceUid = ?";
	private static final String _FINDER_COLUMN_F_GID_REFERENCEUID_REFERENCEUID_3 =
		"(syncTracking.referenceUid IS NULL OR syncTracking.referenceUid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_GID_DOSSIERNO = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_GID_DossierNo",
			new String[] { Long.class.getName(), String.class.getName() },
			SyncTrackingModelImpl.GROUPID_COLUMN_BITMASK |
			SyncTrackingModelImpl.DOSSIERNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_DOSSIERNO = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_GID_DossierNo",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the sync tracking where groupId = &#63; and dossierNo = &#63; or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @return the matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_DossierNo(long groupId, String dossierNo)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_DossierNo(groupId, dossierNo);

		if (syncTracking == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dossierNo=");
			msg.append(dossierNo);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSyncTrackingException(msg.toString());
		}

		return syncTracking;
	}

	/**
	 * Returns the sync tracking where groupId = &#63; and dossierNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_DossierNo(long groupId, String dossierNo) {
		return fetchByF_GID_DossierNo(groupId, dossierNo, true);
	}

	/**
	 * Returns the sync tracking where groupId = &#63; and dossierNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_DossierNo(long groupId, String dossierNo,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, dossierNo };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_GID_DOSSIERNO,
					finderArgs, this);
		}

		if (result instanceof SyncTracking) {
			SyncTracking syncTracking = (SyncTracking)result;

			if ((groupId != syncTracking.getGroupId()) ||
					!Objects.equals(dossierNo, syncTracking.getDossierNo())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_GROUPID_2);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_DOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				List<SyncTracking> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DOSSIERNO,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SyncTrackingPersistenceImpl.fetchByF_GID_DossierNo(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SyncTracking syncTracking = list.get(0);

					result = syncTracking;

					cacheResult(syncTracking);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DOSSIERNO,
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
			return (SyncTracking)result;
		}
	}

	/**
	 * Removes the sync tracking where groupId = &#63; and dossierNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @return the sync tracking that was removed
	 */
	@Override
	public SyncTracking removeByF_GID_DossierNo(long groupId, String dossierNo)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByF_GID_DossierNo(groupId, dossierNo);

		return remove(syncTracking);
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and dossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_DossierNo(long groupId, String dossierNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_DOSSIERNO;

		Object[] finderArgs = new Object[] { groupId, dossierNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_GROUPID_2);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_DOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierNo) {
					qPos.add(dossierNo);
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

	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_GROUPID_2 = "syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_DOSSIERNO_1 = "syncTracking.dossierNo IS NULL";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_DOSSIERNO_2 = "syncTracking.dossierNo = ?";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_DOSSIERNO_3 = "(syncTracking.dossierNo IS NULL OR syncTracking.dossierNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_API =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID_API",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_API =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID_API",
			new String[] { Long.class.getName(), String.class.getName() },
			SyncTrackingModelImpl.GROUPID_COLUMN_BITMASK |
			SyncTrackingModelImpl.API_COLUMN_BITMASK |
			SyncTrackingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_API = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_API",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the sync trackings where groupId = &#63; and api = &#63;.
	 *
	 * @param groupId the group ID
	 * @param api the api
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_API(long groupId, String api) {
		return findByF_GID_API(groupId, api, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63; and api = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param api the api
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_API(long groupId, String api,
		int start, int end) {
		return findByF_GID_API(groupId, api, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and api = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param api the api
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_API(long groupId, String api,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator) {
		return findByF_GID_API(groupId, api, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and api = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param api the api
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_API(long groupId, String api,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_API;
			finderArgs = new Object[] { groupId, api };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_API;
			finderArgs = new Object[] {
					groupId, api,
					
					start, end, orderByComparator
				};
		}

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId()) ||
							!Objects.equals(api, syncTracking.getApi())) {
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

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_API_GROUPID_2);

			boolean bindApi = false;

			if (api == null) {
				query.append(_FINDER_COLUMN_F_GID_API_API_1);
			}
			else if (api.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_API_API_3);
			}
			else {
				bindApi = true;

				query.append(_FINDER_COLUMN_F_GID_API_API_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApi) {
					qPos.add(api);
				}

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and api = &#63;.
	 *
	 * @param groupId the group ID
	 * @param api the api
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_API_First(long groupId, String api,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_API_First(groupId, api,
				orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", api=");
		msg.append(api);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and api = &#63;.
	 *
	 * @param groupId the group ID
	 * @param api the api
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_API_First(long groupId, String api,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByF_GID_API(groupId, api, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and api = &#63;.
	 *
	 * @param groupId the group ID
	 * @param api the api
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_API_Last(long groupId, String api,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_API_Last(groupId, api,
				orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", api=");
		msg.append(api);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and api = &#63;.
	 *
	 * @param groupId the group ID
	 * @param api the api
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_API_Last(long groupId, String api,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByF_GID_API(groupId, api);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByF_GID_API(groupId, api, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63; and api = &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param api the api
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByF_GID_API_PrevAndNext(long trackingId,
		long groupId, String api,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByF_GID_API_PrevAndNext(session, syncTracking,
					groupId, api, orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByF_GID_API_PrevAndNext(session, syncTracking,
					groupId, api, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncTracking getByF_GID_API_PrevAndNext(Session session,
		SyncTracking syncTracking, long groupId, String api,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_API_GROUPID_2);

		boolean bindApi = false;

		if (api == null) {
			query.append(_FINDER_COLUMN_F_GID_API_API_1);
		}
		else if (api.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_API_API_3);
		}
		else {
			bindApi = true;

			query.append(_FINDER_COLUMN_F_GID_API_API_2);
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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindApi) {
			qPos.add(api);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; and api = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param api the api
	 */
	@Override
	public void removeByF_GID_API(long groupId, String api) {
		for (SyncTracking syncTracking : findByF_GID_API(groupId, api,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and api = &#63;.
	 *
	 * @param groupId the group ID
	 * @param api the api
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_API(long groupId, String api) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_API;

		Object[] finderArgs = new Object[] { groupId, api };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_API_GROUPID_2);

			boolean bindApi = false;

			if (api == null) {
				query.append(_FINDER_COLUMN_F_GID_API_API_1);
			}
			else if (api.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_API_API_3);
			}
			else {
				bindApi = true;

				query.append(_FINDER_COLUMN_F_GID_API_API_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindApi) {
					qPos.add(api);
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

	private static final String _FINDER_COLUMN_F_GID_API_GROUPID_2 = "syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_API_API_1 = "syncTracking.api IS NULL";
	private static final String _FINDER_COLUMN_F_GID_API_API_2 = "syncTracking.api = ?";
	private static final String _FINDER_COLUMN_F_GID_API_API_3 = "(syncTracking.api IS NULL OR syncTracking.api = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_PROTOCOL =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID_Protocol",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID_Protocol",
			new String[] { Long.class.getName(), String.class.getName() },
			SyncTrackingModelImpl.GROUPID_COLUMN_BITMASK |
			SyncTrackingModelImpl.PROTOCOL_COLUMN_BITMASK |
			SyncTrackingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_PROTOCOL = new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_Protocol",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the sync trackings where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol(long groupId, String protocol) {
		return findByF_GID_Protocol(groupId, protocol, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63; and protocol = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol(long groupId,
		String protocol, int start, int end) {
		return findByF_GID_Protocol(groupId, protocol, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and protocol = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol(long groupId,
		String protocol, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return findByF_GID_Protocol(groupId, protocol, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and protocol = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol(long groupId,
		String protocol, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL;
			finderArgs = new Object[] { groupId, protocol };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_PROTOCOL;
			finderArgs = new Object[] {
					groupId, protocol,
					
					start, end, orderByComparator
				};
		}

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId()) ||
							!Objects.equals(protocol, syncTracking.getProtocol())) {
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

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_GROUPID_2);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProtocol) {
					qPos.add(protocol);
				}

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_Protocol_First(long groupId,
		String protocol, OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_Protocol_First(groupId,
				protocol, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", protocol=");
		msg.append(protocol);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_Protocol_First(long groupId,
		String protocol, OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByF_GID_Protocol(groupId, protocol, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_Protocol_Last(long groupId,
		String protocol, OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_Protocol_Last(groupId,
				protocol, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", protocol=");
		msg.append(protocol);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_Protocol_Last(long groupId,
		String protocol, OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByF_GID_Protocol(groupId, protocol);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByF_GID_Protocol(groupId, protocol,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63; and protocol = &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByF_GID_Protocol_PrevAndNext(long trackingId,
		long groupId, String protocol,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByF_GID_Protocol_PrevAndNext(session, syncTracking,
					groupId, protocol, orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByF_GID_Protocol_PrevAndNext(session, syncTracking,
					groupId, protocol, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncTracking getByF_GID_Protocol_PrevAndNext(Session session,
		SyncTracking syncTracking, long groupId, String protocol,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_PROTOCOL_GROUPID_2);

		boolean bindProtocol = false;

		if (protocol == null) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_1);
		}
		else if (protocol.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_3);
		}
		else {
			bindProtocol = true;

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_2);
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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindProtocol) {
			qPos.add(protocol);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; and protocol = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 */
	@Override
	public void removeByF_GID_Protocol(long groupId, String protocol) {
		for (SyncTracking syncTracking : findByF_GID_Protocol(groupId,
				protocol, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and protocol = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_Protocol(long groupId, String protocol) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_PROTOCOL;

		Object[] finderArgs = new Object[] { groupId, protocol };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_GROUPID_2);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProtocol) {
					qPos.add(protocol);
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

	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_GROUPID_2 = "syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_1 = "syncTracking.protocol IS NULL";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_2 = "syncTracking.protocol = ?";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_PROTOCOL_3 = "(syncTracking.protocol IS NULL OR syncTracking.protocol = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_PROTOCOL_DOSSIERNO =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_Protocol_DossierNo",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_DOSSIERNO =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_GID_Protocol_DossierNo",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			SyncTrackingModelImpl.GROUPID_COLUMN_BITMASK |
			SyncTrackingModelImpl.PROTOCOL_COLUMN_BITMASK |
			SyncTrackingModelImpl.DOSSIERNO_COLUMN_BITMASK |
			SyncTrackingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_DOSSIERNO =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_GID_Protocol_DossierNo",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the sync trackings where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol_DossierNo(long groupId,
		String protocol, String dossierNo) {
		return findByF_GID_Protocol_DossierNo(groupId, protocol, dossierNo,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol_DossierNo(long groupId,
		String protocol, String dossierNo, int start, int end) {
		return findByF_GID_Protocol_DossierNo(groupId, protocol, dossierNo,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol_DossierNo(long groupId,
		String protocol, String dossierNo, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return findByF_GID_Protocol_DossierNo(groupId, protocol, dossierNo,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol_DossierNo(long groupId,
		String protocol, String dossierNo, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_DOSSIERNO;
			finderArgs = new Object[] { groupId, protocol, dossierNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_PROTOCOL_DOSSIERNO;
			finderArgs = new Object[] {
					groupId, protocol, dossierNo,
					
					start, end, orderByComparator
				};
		}

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId()) ||
							!Objects.equals(protocol, syncTracking.getProtocol()) ||
							!Objects.equals(dossierNo,
								syncTracking.getDossierNo())) {
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

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_GROUPID_2);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_2);
			}

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProtocol) {
					qPos.add(protocol);
				}

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_Protocol_DossierNo_First(long groupId,
		String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_Protocol_DossierNo_First(groupId,
				protocol, dossierNo, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", protocol=");
		msg.append(protocol);

		msg.append(", dossierNo=");
		msg.append(dossierNo);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_Protocol_DossierNo_First(long groupId,
		String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByF_GID_Protocol_DossierNo(groupId,
				protocol, dossierNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_Protocol_DossierNo_Last(long groupId,
		String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_Protocol_DossierNo_Last(groupId,
				protocol, dossierNo, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", protocol=");
		msg.append(protocol);

		msg.append(", dossierNo=");
		msg.append(dossierNo);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_Protocol_DossierNo_Last(long groupId,
		String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByF_GID_Protocol_DossierNo(groupId, protocol, dossierNo);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByF_GID_Protocol_DossierNo(groupId,
				protocol, dossierNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByF_GID_Protocol_DossierNo_PrevAndNext(
		long trackingId, long groupId, String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByF_GID_Protocol_DossierNo_PrevAndNext(session,
					syncTracking, groupId, protocol, dossierNo,
					orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByF_GID_Protocol_DossierNo_PrevAndNext(session,
					syncTracking, groupId, protocol, dossierNo,
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

	protected SyncTracking getByF_GID_Protocol_DossierNo_PrevAndNext(
		Session session, SyncTracking syncTracking, long groupId,
		String protocol, String dossierNo,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_GROUPID_2);

		boolean bindProtocol = false;

		if (protocol == null) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_1);
		}
		else if (protocol.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_3);
		}
		else {
			bindProtocol = true;

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_2);
		}

		boolean bindDossierNo = false;

		if (dossierNo == null) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_1);
		}
		else if (dossierNo.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_3);
		}
		else {
			bindDossierNo = true;

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_2);
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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindProtocol) {
			qPos.add(protocol);
		}

		if (bindDossierNo) {
			qPos.add(dossierNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; and protocol = &#63; and dossierNo = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 */
	@Override
	public void removeByF_GID_Protocol_DossierNo(long groupId, String protocol,
		String dossierNo) {
		for (SyncTracking syncTracking : findByF_GID_Protocol_DossierNo(
				groupId, protocol, dossierNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and protocol = &#63; and dossierNo = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param dossierNo the dossier no
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_Protocol_DossierNo(long groupId, String protocol,
		String dossierNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_DOSSIERNO;

		Object[] finderArgs = new Object[] { groupId, protocol, dossierNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_GROUPID_2);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_2);
			}

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProtocol) {
					qPos.add(protocol);
				}

				if (bindDossierNo) {
					qPos.add(dossierNo);
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

	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_GROUPID_2 =
		"syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_1 =
		"syncTracking.protocol IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_2 =
		"syncTracking.protocol = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_PROTOCOL_3 =
		"(syncTracking.protocol IS NULL OR syncTracking.protocol = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_1 =
		"syncTracking.dossierNo IS NULL";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_2 =
		"syncTracking.dossierNo = ?";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_DOSSIERNO_DOSSIERNO_3 =
		"(syncTracking.dossierNo IS NULL OR syncTracking.dossierNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_PROTOCOL_SERVICECODE =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_Protocol_ServiceCode",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_SERVICECODE =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_GID_Protocol_ServiceCode",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			SyncTrackingModelImpl.GROUPID_COLUMN_BITMASK |
			SyncTrackingModelImpl.PROTOCOL_COLUMN_BITMASK |
			SyncTrackingModelImpl.SERVICECODE_COLUMN_BITMASK |
			SyncTrackingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_SERVICECODE =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_GID_Protocol_ServiceCode",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the sync trackings where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol_ServiceCode(long groupId,
		String protocol, String serviceCode) {
		return findByF_GID_Protocol_ServiceCode(groupId, protocol, serviceCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol_ServiceCode(long groupId,
		String protocol, String serviceCode, int start, int end) {
		return findByF_GID_Protocol_ServiceCode(groupId, protocol, serviceCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol_ServiceCode(long groupId,
		String protocol, String serviceCode, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return findByF_GID_Protocol_ServiceCode(groupId, protocol, serviceCode,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_Protocol_ServiceCode(long groupId,
		String protocol, String serviceCode, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_SERVICECODE;
			finderArgs = new Object[] { groupId, protocol, serviceCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_PROTOCOL_SERVICECODE;
			finderArgs = new Object[] {
					groupId, protocol, serviceCode,
					
					start, end, orderByComparator
				};
		}

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId()) ||
							!Objects.equals(protocol, syncTracking.getProtocol()) ||
							!Objects.equals(serviceCode,
								syncTracking.getServiceCode())) {
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

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_GROUPID_2);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProtocol) {
					qPos.add(protocol);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_Protocol_ServiceCode_First(long groupId,
		String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_Protocol_ServiceCode_First(groupId,
				protocol, serviceCode, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", protocol=");
		msg.append(protocol);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_Protocol_ServiceCode_First(long groupId,
		String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByF_GID_Protocol_ServiceCode(groupId,
				protocol, serviceCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_Protocol_ServiceCode_Last(long groupId,
		String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_Protocol_ServiceCode_Last(groupId,
				protocol, serviceCode, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", protocol=");
		msg.append(protocol);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_Protocol_ServiceCode_Last(long groupId,
		String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByF_GID_Protocol_ServiceCode(groupId, protocol,
				serviceCode);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByF_GID_Protocol_ServiceCode(groupId,
				protocol, serviceCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByF_GID_Protocol_ServiceCode_PrevAndNext(
		long trackingId, long groupId, String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByF_GID_Protocol_ServiceCode_PrevAndNext(session,
					syncTracking, groupId, protocol, serviceCode,
					orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByF_GID_Protocol_ServiceCode_PrevAndNext(session,
					syncTracking, groupId, protocol, serviceCode,
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

	protected SyncTracking getByF_GID_Protocol_ServiceCode_PrevAndNext(
		Session session, SyncTracking syncTracking, long groupId,
		String protocol, String serviceCode,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_GROUPID_2);

		boolean bindProtocol = false;

		if (protocol == null) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_1);
		}
		else if (protocol.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_3);
		}
		else {
			bindProtocol = true;

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_2);
		}

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_2);
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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindProtocol) {
			qPos.add(protocol);
		}

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; and protocol = &#63; and serviceCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 */
	@Override
	public void removeByF_GID_Protocol_ServiceCode(long groupId,
		String protocol, String serviceCode) {
		for (SyncTracking syncTracking : findByF_GID_Protocol_ServiceCode(
				groupId, protocol, serviceCode, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and protocol = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param protocol the protocol
	 * @param serviceCode the service code
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_Protocol_ServiceCode(long groupId, String protocol,
		String serviceCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_SERVICECODE;

		Object[] finderArgs = new Object[] { groupId, protocol, serviceCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_GROUPID_2);

			boolean bindProtocol = false;

			if (protocol == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_1);
			}
			else if (protocol.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_3);
			}
			else {
				bindProtocol = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindProtocol) {
					qPos.add(protocol);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
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

	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_GROUPID_2 =
		"syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_1 =
		"syncTracking.protocol IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_2 =
		"syncTracking.protocol = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_PROTOCOL_3 =
		"(syncTracking.protocol IS NULL OR syncTracking.protocol = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_1 =
		"syncTracking.serviceCode IS NULL";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_2 =
		"syncTracking.serviceCode = ?";
	private static final String _FINDER_COLUMN_F_GID_PROTOCOL_SERVICECODE_SERVICECODE_3 =
		"(syncTracking.serviceCode IS NULL OR syncTracking.serviceCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), Date.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_GID_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), Date.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the sync trackings where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_CREATED_BETWEEN(long groupId,
		Date createDate, Date modifiedDate) {
		return findByF_GID_CREATED_BETWEEN(groupId, createDate, modifiedDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_CREATED_BETWEEN(long groupId,
		Date createDate, Date modifiedDate, int start, int end) {
		return findByF_GID_CREATED_BETWEEN(groupId, createDate, modifiedDate,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_CREATED_BETWEEN(long groupId,
		Date createDate, Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return findByF_GID_CREATED_BETWEEN(groupId, createDate, modifiedDate,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_CREATED_BETWEEN(long groupId,
		Date createDate, Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_CREATED_BETWEEN;
		finderArgs = new Object[] {
				groupId, _getTime(createDate), _getTime(modifiedDate),
				
				start, end, orderByComparator
			};

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId()) ||
							(createDate.getTime() > syncTracking.getCreateDate()
																	.getTime()) ||
							(modifiedDate.getTime() < syncTracking.getModifiedDate()
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

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_GROUPID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_CREATED_BETWEEN_First(long groupId,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_CREATED_BETWEEN_First(groupId,
				createDate, modifiedDate, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_CREATED_BETWEEN_First(long groupId,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByF_GID_CREATED_BETWEEN(groupId,
				createDate, modifiedDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_CREATED_BETWEEN_Last(long groupId,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_CREATED_BETWEEN_Last(groupId,
				createDate, modifiedDate, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_CREATED_BETWEEN_Last(long groupId,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByF_GID_CREATED_BETWEEN(groupId, createDate,
				modifiedDate);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByF_GID_CREATED_BETWEEN(groupId,
				createDate, modifiedDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByF_GID_CREATED_BETWEEN_PrevAndNext(
		long trackingId, long groupId, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByF_GID_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, createDate, modifiedDate,
					orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByF_GID_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, createDate, modifiedDate,
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

	protected SyncTracking getByF_GID_CREATED_BETWEEN_PrevAndNext(
		Session session, SyncTracking syncTracking, long groupId,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_GROUPID_2);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_CREATEDATE_2);
		}

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_MODIFIEDDATE_2);
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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByF_GID_CREATED_BETWEEN(long groupId, Date createDate,
		Date modifiedDate) {
		for (SyncTracking syncTracking : findByF_GID_CREATED_BETWEEN(groupId,
				createDate, modifiedDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_CREATED_BETWEEN(long groupId, Date createDate,
		Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_CREATED_BETWEEN;

		Object[] finderArgs = new Object[] {
				groupId, _getTime(createDate), _getTime(modifiedDate)
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_GROUPID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_GID_CREATED_BETWEEN_GROUPID_2 = "syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_CREATED_BETWEEN_CREATEDATE_1 =
		"syncTracking.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_CREATED_BETWEEN_CREATEDATE_2 =
		"syncTracking.createDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_GID_CREATED_BETWEEN_MODIFIEDDATE_1 =
		"syncTracking.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_F_GID_CREATED_BETWEEN_MODIFIEDDATE_2 =
		"syncTracking.modifiedDate <= ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_DOSSIERNO_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_DOSSIERNO_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Date.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_DOSSIERNO_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_GID_DOSSIERNO_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Date.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the sync trackings where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String dossierNo, Date createDate, Date modifiedDate) {
		return findByF_GID_DOSSIERNO_CREATED_BETWEEN(groupId, dossierNo,
			createDate, modifiedDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String dossierNo, Date createDate, Date modifiedDate,
		int start, int end) {
		return findByF_GID_DOSSIERNO_CREATED_BETWEEN(groupId, dossierNo,
			createDate, modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String dossierNo, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator) {
		return findByF_GID_DOSSIERNO_CREATED_BETWEEN(groupId, dossierNo,
			createDate, modifiedDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String dossierNo, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_DOSSIERNO_CREATED_BETWEEN;
		finderArgs = new Object[] {
				groupId, dossierNo, _getTime(createDate), _getTime(modifiedDate),
				
				start, end, orderByComparator
			};

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId()) ||
							!Objects.equals(dossierNo,
								syncTracking.getDossierNo()) ||
							(createDate.getTime() > syncTracking.getCreateDate()
																	.getTime()) ||
							(modifiedDate.getTime() < syncTracking.getModifiedDate()
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
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_GROUPID_2);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_DOSSIERNO_CREATED_BETWEEN_First(
		long groupId, String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_DOSSIERNO_CREATED_BETWEEN_First(groupId,
				dossierNo, createDate, modifiedDate, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierNo=");
		msg.append(dossierNo);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_DOSSIERNO_CREATED_BETWEEN_First(
		long groupId, String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByF_GID_DOSSIERNO_CREATED_BETWEEN(groupId,
				dossierNo, createDate, modifiedDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_DOSSIERNO_CREATED_BETWEEN_Last(
		long groupId, String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_DOSSIERNO_CREATED_BETWEEN_Last(groupId,
				dossierNo, createDate, modifiedDate, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierNo=");
		msg.append(dossierNo);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_DOSSIERNO_CREATED_BETWEEN_Last(
		long groupId, String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByF_GID_DOSSIERNO_CREATED_BETWEEN(groupId, dossierNo,
				createDate, modifiedDate);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByF_GID_DOSSIERNO_CREATED_BETWEEN(groupId,
				dossierNo, createDate, modifiedDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByF_GID_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, long groupId, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByF_GID_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, dossierNo, createDate, modifiedDate,
					orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByF_GID_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, dossierNo, createDate, modifiedDate,
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

	protected SyncTracking getByF_GID_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		Session session, SyncTracking syncTracking, long groupId,
		String dossierNo, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_GROUPID_2);

		boolean bindDossierNo = false;

		if (dossierNo == null) {
			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_1);
		}
		else if (dossierNo.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_3);
		}
		else {
			bindDossierNo = true;

			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_2);
		}

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_2);
		}

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_2);
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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindDossierNo) {
			qPos.add(dossierNo);
		}

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByF_GID_DOSSIERNO_CREATED_BETWEEN(long groupId,
		String dossierNo, Date createDate, Date modifiedDate) {
		for (SyncTracking syncTracking : findByF_GID_DOSSIERNO_CREATED_BETWEEN(
				groupId, dossierNo, createDate, modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_DOSSIERNO_CREATED_BETWEEN(long groupId,
		String dossierNo, Date createDate, Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_DOSSIERNO_CREATED_BETWEEN;

		Object[] finderArgs = new Object[] {
				groupId, dossierNo, _getTime(createDate), _getTime(modifiedDate)
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_GROUPID_2);

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_GROUPID_2 =
		"syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_1 =
		"syncTracking.dossierNo IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_2 =
		"syncTracking.dossierNo = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_3 =
		"(syncTracking.dossierNo IS NULL OR syncTracking.dossierNo = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_1 =
		"syncTracking.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_2 =
		"syncTracking.createDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_1 =
		"syncTracking.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_F_GID_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_2 =
		"syncTracking.modifiedDate <= ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_REFERENCE_UID_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_REFERENCE_UID_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Date.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_REFERENCE_UID_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_GID_REFERENCE_UID_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Date.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the sync trackings where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_REFERENCE_UID_CREATED_BETWEEN(
		long groupId, String referenceUid, Date createDate, Date modifiedDate) {
		return findByF_GID_REFERENCE_UID_CREATED_BETWEEN(groupId, referenceUid,
			createDate, modifiedDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_REFERENCE_UID_CREATED_BETWEEN(
		long groupId, String referenceUid, Date createDate, Date modifiedDate,
		int start, int end) {
		return findByF_GID_REFERENCE_UID_CREATED_BETWEEN(groupId, referenceUid,
			createDate, modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_REFERENCE_UID_CREATED_BETWEEN(
		long groupId, String referenceUid, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator) {
		return findByF_GID_REFERENCE_UID_CREATED_BETWEEN(groupId, referenceUid,
			createDate, modifiedDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_REFERENCE_UID_CREATED_BETWEEN(
		long groupId, String referenceUid, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_REFERENCE_UID_CREATED_BETWEEN;
		finderArgs = new Object[] {
				groupId, referenceUid, _getTime(createDate),
				_getTime(modifiedDate),
				
				start, end, orderByComparator
			};

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId()) ||
							!Objects.equals(referenceUid,
								syncTracking.getReferenceUid()) ||
							(createDate.getTime() > syncTracking.getCreateDate()
																	.getTime()) ||
							(modifiedDate.getTime() < syncTracking.getModifiedDate()
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
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_GROUPID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_REFERENCE_UID_CREATED_BETWEEN_First(
		long groupId, String referenceUid, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_REFERENCE_UID_CREATED_BETWEEN_First(groupId,
				referenceUid, createDate, modifiedDate, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceUid=");
		msg.append(referenceUid);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_REFERENCE_UID_CREATED_BETWEEN_First(
		long groupId, String referenceUid, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByF_GID_REFERENCE_UID_CREATED_BETWEEN(groupId,
				referenceUid, createDate, modifiedDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_REFERENCE_UID_CREATED_BETWEEN_Last(
		long groupId, String referenceUid, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_REFERENCE_UID_CREATED_BETWEEN_Last(groupId,
				referenceUid, createDate, modifiedDate, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", referenceUid=");
		msg.append(referenceUid);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_REFERENCE_UID_CREATED_BETWEEN_Last(
		long groupId, String referenceUid, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByF_GID_REFERENCE_UID_CREATED_BETWEEN(groupId,
				referenceUid, createDate, modifiedDate);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByF_GID_REFERENCE_UID_CREATED_BETWEEN(groupId,
				referenceUid, createDate, modifiedDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByF_GID_REFERENCE_UID_CREATED_BETWEEN_PrevAndNext(
		long trackingId, long groupId, String referenceUid, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByF_GID_REFERENCE_UID_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, referenceUid, createDate,
					modifiedDate, orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByF_GID_REFERENCE_UID_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, referenceUid, createDate,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncTracking getByF_GID_REFERENCE_UID_CREATED_BETWEEN_PrevAndNext(
		Session session, SyncTracking syncTracking, long groupId,
		String referenceUid, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_GROUPID_2);

		boolean bindReferenceUid = false;

		if (referenceUid == null) {
			query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_1);
		}
		else if (referenceUid.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_3);
		}
		else {
			bindReferenceUid = true;

			query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_2);
		}

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_CREATEDATE_2);
		}

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_MODIFIEDDATE_2);
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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindReferenceUid) {
			qPos.add(referenceUid);
		}

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByF_GID_REFERENCE_UID_CREATED_BETWEEN(long groupId,
		String referenceUid, Date createDate, Date modifiedDate) {
		for (SyncTracking syncTracking : findByF_GID_REFERENCE_UID_CREATED_BETWEEN(
				groupId, referenceUid, createDate, modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param referenceUid the reference uid
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_REFERENCE_UID_CREATED_BETWEEN(long groupId,
		String referenceUid, Date createDate, Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_REFERENCE_UID_CREATED_BETWEEN;

		Object[] finderArgs = new Object[] {
				groupId, referenceUid, _getTime(createDate),
				_getTime(modifiedDate)
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_GROUPID_2);

			boolean bindReferenceUid = false;

			if (referenceUid == null) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_1);
			}
			else if (referenceUid.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_3);
			}
			else {
				bindReferenceUid = true;

				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindReferenceUid) {
					qPos.add(referenceUid);
				}

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_GROUPID_2 =
		"syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_1 =
		"syncTracking.referenceUid IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_2 =
		"syncTracking.referenceUid = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_REFERENCEUID_3 =
		"(syncTracking.referenceUid IS NULL OR syncTracking.referenceUid = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_CREATEDATE_1 =
		"syncTracking.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_CREATEDATE_2 =
		"syncTracking.createDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_MODIFIEDDATE_1 =
		"syncTracking.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_F_GID_REFERENCE_UID_CREATED_BETWEEN_MODIFIEDDATE_2 =
		"syncTracking.modifiedDate <= ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SERVICECODE_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_SERVICECODE_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Date.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_SERVICECODE_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_GID_SERVICECODE_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Date.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the sync trackings where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_SERVICECODE_CREATED_BETWEEN(
		long groupId, String serviceCode, Date createDate, Date modifiedDate) {
		return findByF_GID_SERVICECODE_CREATED_BETWEEN(groupId, serviceCode,
			createDate, modifiedDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_SERVICECODE_CREATED_BETWEEN(
		long groupId, String serviceCode, Date createDate, Date modifiedDate,
		int start, int end) {
		return findByF_GID_SERVICECODE_CREATED_BETWEEN(groupId, serviceCode,
			createDate, modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_SERVICECODE_CREATED_BETWEEN(
		long groupId, String serviceCode, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator) {
		return findByF_GID_SERVICECODE_CREATED_BETWEEN(groupId, serviceCode,
			createDate, modifiedDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_SERVICECODE_CREATED_BETWEEN(
		long groupId, String serviceCode, Date createDate, Date modifiedDate,
		int start, int end, OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SERVICECODE_CREATED_BETWEEN;
		finderArgs = new Object[] {
				groupId, serviceCode, _getTime(createDate),
				_getTime(modifiedDate),
				
				start, end, orderByComparator
			};

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId()) ||
							!Objects.equals(serviceCode,
								syncTracking.getServiceCode()) ||
							(createDate.getTime() > syncTracking.getCreateDate()
																	.getTime()) ||
							(modifiedDate.getTime() < syncTracking.getModifiedDate()
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
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_SERVICECODE_CREATED_BETWEEN_First(
		long groupId, String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_SERVICECODE_CREATED_BETWEEN_First(groupId,
				serviceCode, createDate, modifiedDate, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_SERVICECODE_CREATED_BETWEEN_First(
		long groupId, String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByF_GID_SERVICECODE_CREATED_BETWEEN(groupId,
				serviceCode, createDate, modifiedDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_SERVICECODE_CREATED_BETWEEN_Last(
		long groupId, String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_SERVICECODE_CREATED_BETWEEN_Last(groupId,
				serviceCode, createDate, modifiedDate, orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_SERVICECODE_CREATED_BETWEEN_Last(
		long groupId, String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByF_GID_SERVICECODE_CREATED_BETWEEN(groupId,
				serviceCode, createDate, modifiedDate);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByF_GID_SERVICECODE_CREATED_BETWEEN(groupId,
				serviceCode, createDate, modifiedDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByF_GID_SERVICECODE_CREATED_BETWEEN_PrevAndNext(
		long trackingId, long groupId, String serviceCode, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByF_GID_SERVICECODE_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, serviceCode, createDate,
					modifiedDate, orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByF_GID_SERVICECODE_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, serviceCode, createDate,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncTracking getByF_GID_SERVICECODE_CREATED_BETWEEN_PrevAndNext(
		Session session, SyncTracking syncTracking, long groupId,
		String serviceCode, Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_GROUPID_2);

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_2);
		}

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_CREATEDATE_2);
		}

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_MODIFIEDDATE_2);
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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByF_GID_SERVICECODE_CREATED_BETWEEN(long groupId,
		String serviceCode, Date createDate, Date modifiedDate) {
		for (SyncTracking syncTracking : findByF_GID_SERVICECODE_CREATED_BETWEEN(
				groupId, serviceCode, createDate, modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_SERVICECODE_CREATED_BETWEEN(long groupId,
		String serviceCode, Date createDate, Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_SERVICECODE_CREATED_BETWEEN;

		Object[] finderArgs = new Object[] {
				groupId, serviceCode, _getTime(createDate),
				_getTime(modifiedDate)
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_GROUPID_2 =
		"syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_1 =
		"syncTracking.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_2 =
		"syncTracking.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_SERVICECODE_3 =
		"(syncTracking.serviceCode IS NULL OR syncTracking.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_CREATEDATE_1 =
		"syncTracking.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_CREATEDATE_2 =
		"syncTracking.createDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_MODIFIEDDATE_1 =
		"syncTracking.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_CREATED_BETWEEN_MODIFIEDDATE_2 =
		"syncTracking.modifiedDate <= ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, SyncTrackingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Date.class.getName(),
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN =
		new FinderPath(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Date.class.getName(),
				Date.class.getName()
			});

	/**
	 * Returns all the sync trackings where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate) {
		return findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(groupId,
			serviceCode, dossierNo, createDate, modifiedDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end) {
		return findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(groupId,
			serviceCode, dossierNo, createDate, modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(groupId,
			serviceCode, dossierNo, createDate, modifiedDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync trackings
	 */
	@Override
	public List<SyncTracking> findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN;
		finderArgs = new Object[] {
				groupId, serviceCode, dossierNo, _getTime(createDate),
				_getTime(modifiedDate),
				
				start, end, orderByComparator
			};

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncTracking syncTracking : list) {
					if ((groupId != syncTracking.getGroupId()) ||
							!Objects.equals(serviceCode,
								syncTracking.getServiceCode()) ||
							!Objects.equals(dossierNo,
								syncTracking.getDossierNo()) ||
							(createDate.getTime() > syncTracking.getCreateDate()
																	.getTime()) ||
							(modifiedDate.getTime() < syncTracking.getModifiedDate()
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
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_2);
			}

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(groupId,
				serviceCode, dossierNo, createDate, modifiedDate,
				orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierNo=");
		msg.append(dossierNo);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the first sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator) {
		List<SyncTracking> list = findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(groupId,
				serviceCode, dossierNo, createDate, modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking
	 * @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(groupId,
				serviceCode, dossierNo, createDate, modifiedDate,
				orderByComparator);

		if (syncTracking != null) {
			return syncTracking;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceCode=");
		msg.append(serviceCode);

		msg.append(", dossierNo=");
		msg.append(dossierNo);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchSyncTrackingException(msg.toString());
	}

	/**
	 * Returns the last sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	 */
	@Override
	public SyncTracking fetchByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator) {
		int count = countByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(groupId,
				serviceCode, dossierNo, createDate, modifiedDate);

		if (count == 0) {
			return null;
		}

		List<SyncTracking> list = findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(groupId,
				serviceCode, dossierNo, createDate, modifiedDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync trackings before and after the current sync tracking in the ordered set where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param trackingId the primary key of the current sync tracking
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking[] findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, long groupId, String serviceCode, String dossierNo,
		Date createDate, Date modifiedDate,
		OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = findByPrimaryKey(trackingId);

		Session session = null;

		try {
			session = openSession();

			SyncTracking[] array = new SyncTrackingImpl[3];

			array[0] = getByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, serviceCode, dossierNo, createDate,
					modifiedDate, orderByComparator, true);

			array[1] = syncTracking;

			array[2] = getByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(session,
					syncTracking, groupId, serviceCode, dossierNo, createDate,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncTracking getByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		Session session, SyncTracking syncTracking, long groupId,
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, OrderByComparator<SyncTracking> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE);

		query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_GROUPID_2);

		boolean bindServiceCode = false;

		if (serviceCode == null) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_1);
		}
		else if (serviceCode.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_3);
		}
		else {
			bindServiceCode = true;

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_2);
		}

		boolean bindDossierNo = false;

		if (dossierNo == null) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_1);
		}
		else if (dossierNo.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_3);
		}
		else {
			bindDossierNo = true;

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_2);
		}

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_2);
		}

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_2);
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
			query.append(SyncTrackingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindServiceCode) {
			qPos.add(serviceCode);
		}

		if (bindDossierNo) {
			qPos.add(dossierNo);
		}

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncTracking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncTracking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync trackings where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate) {
		for (SyncTracking syncTracking : findByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
				groupId, serviceCode, dossierNo, createDate, modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings where groupId = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceCode the service code
	 * @param dossierNo the dossier no
	 * @param createDate the create date
	 * @param modifiedDate the modified date
	 * @return the number of matching sync trackings
	 */
	@Override
	public int countByF_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		long groupId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN;

		Object[] finderArgs = new Object[] {
				groupId, serviceCode, dossierNo, _getTime(createDate),
				_getTime(modifiedDate)
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_SYNCTRACKING_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_GROUPID_2);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_2);
			}

			boolean bindDossierNo = false;

			if (dossierNo == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_1);
			}
			else if (dossierNo.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_3);
			}
			else {
				bindDossierNo = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_2);
			}

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				if (bindDossierNo) {
					qPos.add(dossierNo);
				}

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
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

	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_GROUPID_2 =
		"syncTracking.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_1 =
		"syncTracking.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_2 =
		"syncTracking.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_SERVICECODE_3 =
		"(syncTracking.serviceCode IS NULL OR syncTracking.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_1 =
		"syncTracking.dossierNo IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_2 =
		"syncTracking.dossierNo = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_DOSSIERNO_3 =
		"(syncTracking.dossierNo IS NULL OR syncTracking.dossierNo = '') AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_1 =
		"syncTracking.createDate IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_CREATEDATE_2 =
		"syncTracking.createDate >= ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_1 =
		"syncTracking.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_F_GID_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_MODIFIEDDATE_2 =
		"syncTracking.modifiedDate <= ?";

	public SyncTrackingPersistenceImpl() {
		setModelClass(SyncTracking.class);

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
	 * Caches the sync tracking in the entity cache if it is enabled.
	 *
	 * @param syncTracking the sync tracking
	 */
	@Override
	public void cacheResult(SyncTracking syncTracking) {
		entityCache.putResult(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingImpl.class, syncTracking.getPrimaryKey(), syncTracking);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { syncTracking.getUuid(), syncTracking.getGroupId() },
			syncTracking);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_REFERENCEUID,
			new Object[] {
				syncTracking.getGroupId(), syncTracking.getReferenceUid()
			}, syncTracking);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DOSSIERNO,
			new Object[] { syncTracking.getGroupId(), syncTracking.getDossierNo() },
			syncTracking);

		syncTracking.resetOriginalValues();
	}

	/**
	 * Caches the sync trackings in the entity cache if it is enabled.
	 *
	 * @param syncTrackings the sync trackings
	 */
	@Override
	public void cacheResult(List<SyncTracking> syncTrackings) {
		for (SyncTracking syncTracking : syncTrackings) {
			if (entityCache.getResult(
						SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
						SyncTrackingImpl.class, syncTracking.getPrimaryKey()) == null) {
				cacheResult(syncTracking);
			}
			else {
				syncTracking.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sync trackings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SyncTrackingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sync tracking.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SyncTracking syncTracking) {
		entityCache.removeResult(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingImpl.class, syncTracking.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SyncTrackingModelImpl)syncTracking, true);
	}

	@Override
	public void clearCache(List<SyncTracking> syncTrackings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SyncTracking syncTracking : syncTrackings) {
			entityCache.removeResult(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
				SyncTrackingImpl.class, syncTracking.getPrimaryKey());

			clearUniqueFindersCache((SyncTrackingModelImpl)syncTracking, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SyncTrackingModelImpl syncTrackingModelImpl) {
		Object[] args = new Object[] {
				syncTrackingModelImpl.getUuid(),
				syncTrackingModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			syncTrackingModelImpl, false);

		args = new Object[] {
				syncTrackingModelImpl.getGroupId(),
				syncTrackingModelImpl.getReferenceUid()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_REFERENCEUID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_REFERENCEUID, args,
			syncTrackingModelImpl, false);

		args = new Object[] {
				syncTrackingModelImpl.getGroupId(),
				syncTrackingModelImpl.getDossierNo()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_GID_DOSSIERNO, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_GID_DOSSIERNO, args,
			syncTrackingModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SyncTrackingModelImpl syncTrackingModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					syncTrackingModelImpl.getUuid(),
					syncTrackingModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((syncTrackingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					syncTrackingModelImpl.getOriginalUuid(),
					syncTrackingModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					syncTrackingModelImpl.getGroupId(),
					syncTrackingModelImpl.getReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_REFERENCEUID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_REFERENCEUID,
				args);
		}

		if ((syncTrackingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_REFERENCEUID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					syncTrackingModelImpl.getOriginalGroupId(),
					syncTrackingModelImpl.getOriginalReferenceUid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_REFERENCEUID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_REFERENCEUID,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					syncTrackingModelImpl.getGroupId(),
					syncTrackingModelImpl.getDossierNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_DOSSIERNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DOSSIERNO, args);
		}

		if ((syncTrackingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_GID_DOSSIERNO.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					syncTrackingModelImpl.getOriginalGroupId(),
					syncTrackingModelImpl.getOriginalDossierNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_DOSSIERNO, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_GID_DOSSIERNO, args);
		}
	}

	/**
	 * Creates a new sync tracking with the primary key. Does not add the sync tracking to the database.
	 *
	 * @param trackingId the primary key for the new sync tracking
	 * @return the new sync tracking
	 */
	@Override
	public SyncTracking create(long trackingId) {
		SyncTracking syncTracking = new SyncTrackingImpl();

		syncTracking.setNew(true);
		syncTracking.setPrimaryKey(trackingId);

		String uuid = PortalUUIDUtil.generate();

		syncTracking.setUuid(uuid);

		syncTracking.setCompanyId(companyProvider.getCompanyId());

		return syncTracking;
	}

	/**
	 * Removes the sync tracking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackingId the primary key of the sync tracking
	 * @return the sync tracking that was removed
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking remove(long trackingId)
		throws NoSuchSyncTrackingException {
		return remove((Serializable)trackingId);
	}

	/**
	 * Removes the sync tracking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sync tracking
	 * @return the sync tracking that was removed
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking remove(Serializable primaryKey)
		throws NoSuchSyncTrackingException {
		Session session = null;

		try {
			session = openSession();

			SyncTracking syncTracking = (SyncTracking)session.get(SyncTrackingImpl.class,
					primaryKey);

			if (syncTracking == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSyncTrackingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(syncTracking);
		}
		catch (NoSuchSyncTrackingException nsee) {
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
	protected SyncTracking removeImpl(SyncTracking syncTracking) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(syncTracking)) {
				syncTracking = (SyncTracking)session.get(SyncTrackingImpl.class,
						syncTracking.getPrimaryKeyObj());
			}

			if (syncTracking != null) {
				session.delete(syncTracking);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (syncTracking != null) {
			clearCache(syncTracking);
		}

		return syncTracking;
	}

	@Override
	public SyncTracking updateImpl(SyncTracking syncTracking) {
		boolean isNew = syncTracking.isNew();

		if (!(syncTracking instanceof SyncTrackingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(syncTracking.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(syncTracking);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in syncTracking proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SyncTracking implementation " +
				syncTracking.getClass());
		}

		SyncTrackingModelImpl syncTrackingModelImpl = (SyncTrackingModelImpl)syncTracking;

		if (Validator.isNull(syncTracking.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			syncTracking.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (syncTracking.getCreateDate() == null)) {
			if (serviceContext == null) {
				syncTracking.setCreateDate(now);
			}
			else {
				syncTracking.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!syncTrackingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				syncTracking.setModifiedDate(now);
			}
			else {
				syncTracking.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (syncTracking.isNew()) {
				session.save(syncTracking);

				syncTracking.setNew(false);
			}
			else {
				syncTracking = (SyncTracking)session.merge(syncTracking);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SyncTrackingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { syncTrackingModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					syncTrackingModelImpl.getUuid(),
					syncTrackingModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { syncTrackingModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					syncTrackingModelImpl.getGroupId(),
					syncTrackingModelImpl.getApi()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_API, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_API,
				args);

			args = new Object[] {
					syncTrackingModelImpl.getGroupId(),
					syncTrackingModelImpl.getProtocol()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_PROTOCOL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL,
				args);

			args = new Object[] {
					syncTrackingModelImpl.getGroupId(),
					syncTrackingModelImpl.getProtocol(),
					syncTrackingModelImpl.getDossierNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_DOSSIERNO,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_DOSSIERNO,
				args);

			args = new Object[] {
					syncTrackingModelImpl.getGroupId(),
					syncTrackingModelImpl.getProtocol(),
					syncTrackingModelImpl.getServiceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_SERVICECODE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_SERVICECODE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((syncTrackingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncTrackingModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { syncTrackingModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((syncTrackingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncTrackingModelImpl.getOriginalUuid(),
						syncTrackingModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						syncTrackingModelImpl.getUuid(),
						syncTrackingModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((syncTrackingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncTrackingModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { syncTrackingModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((syncTrackingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_API.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncTrackingModelImpl.getOriginalGroupId(),
						syncTrackingModelImpl.getOriginalApi()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_API, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_API,
					args);

				args = new Object[] {
						syncTrackingModelImpl.getGroupId(),
						syncTrackingModelImpl.getApi()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_API, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_API,
					args);
			}

			if ((syncTrackingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncTrackingModelImpl.getOriginalGroupId(),
						syncTrackingModelImpl.getOriginalProtocol()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_PROTOCOL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL,
					args);

				args = new Object[] {
						syncTrackingModelImpl.getGroupId(),
						syncTrackingModelImpl.getProtocol()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_PROTOCOL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL,
					args);
			}

			if ((syncTrackingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_DOSSIERNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncTrackingModelImpl.getOriginalGroupId(),
						syncTrackingModelImpl.getOriginalProtocol(),
						syncTrackingModelImpl.getOriginalDossierNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_DOSSIERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_DOSSIERNO,
					args);

				args = new Object[] {
						syncTrackingModelImpl.getGroupId(),
						syncTrackingModelImpl.getProtocol(),
						syncTrackingModelImpl.getDossierNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_DOSSIERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_DOSSIERNO,
					args);
			}

			if ((syncTrackingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_SERVICECODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncTrackingModelImpl.getOriginalGroupId(),
						syncTrackingModelImpl.getOriginalProtocol(),
						syncTrackingModelImpl.getOriginalServiceCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_SERVICECODE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_SERVICECODE,
					args);

				args = new Object[] {
						syncTrackingModelImpl.getGroupId(),
						syncTrackingModelImpl.getProtocol(),
						syncTrackingModelImpl.getServiceCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_PROTOCOL_SERVICECODE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_PROTOCOL_SERVICECODE,
					args);
			}
		}

		entityCache.putResult(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
			SyncTrackingImpl.class, syncTracking.getPrimaryKey(), syncTracking,
			false);

		clearUniqueFindersCache(syncTrackingModelImpl, false);
		cacheUniqueFindersCache(syncTrackingModelImpl);

		syncTracking.resetOriginalValues();

		return syncTracking;
	}

	/**
	 * Returns the sync tracking with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync tracking
	 * @return the sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSyncTrackingException {
		SyncTracking syncTracking = fetchByPrimaryKey(primaryKey);

		if (syncTracking == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSyncTrackingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return syncTracking;
	}

	/**
	 * Returns the sync tracking with the primary key or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	 *
	 * @param trackingId the primary key of the sync tracking
	 * @return the sync tracking
	 * @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking findByPrimaryKey(long trackingId)
		throws NoSuchSyncTrackingException {
		return findByPrimaryKey((Serializable)trackingId);
	}

	/**
	 * Returns the sync tracking with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync tracking
	 * @return the sync tracking, or <code>null</code> if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
				SyncTrackingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SyncTracking syncTracking = (SyncTracking)serializable;

		if (syncTracking == null) {
			Session session = null;

			try {
				session = openSession();

				syncTracking = (SyncTracking)session.get(SyncTrackingImpl.class,
						primaryKey);

				if (syncTracking != null) {
					cacheResult(syncTracking);
				}
				else {
					entityCache.putResult(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
						SyncTrackingImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
					SyncTrackingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return syncTracking;
	}

	/**
	 * Returns the sync tracking with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trackingId the primary key of the sync tracking
	 * @return the sync tracking, or <code>null</code> if a sync tracking with the primary key could not be found
	 */
	@Override
	public SyncTracking fetchByPrimaryKey(long trackingId) {
		return fetchByPrimaryKey((Serializable)trackingId);
	}

	@Override
	public Map<Serializable, SyncTracking> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SyncTracking> map = new HashMap<Serializable, SyncTracking>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SyncTracking syncTracking = fetchByPrimaryKey(primaryKey);

			if (syncTracking != null) {
				map.put(primaryKey, syncTracking);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
					SyncTrackingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SyncTracking)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SYNCTRACKING_WHERE_PKS_IN);

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

			for (SyncTracking syncTracking : (List<SyncTracking>)q.list()) {
				map.put(syncTracking.getPrimaryKeyObj(), syncTracking);

				cacheResult(syncTracking);

				uncachedPrimaryKeys.remove(syncTracking.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SyncTrackingModelImpl.ENTITY_CACHE_ENABLED,
					SyncTrackingImpl.class, primaryKey, nullModel);
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
	 * Returns all the sync trackings.
	 *
	 * @return the sync trackings
	 */
	@Override
	public List<SyncTracking> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync trackings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @return the range of sync trackings
	 */
	@Override
	public List<SyncTracking> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync trackings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sync trackings
	 */
	@Override
	public List<SyncTracking> findAll(int start, int end,
		OrderByComparator<SyncTracking> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync trackings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync trackings
	 * @param end the upper bound of the range of sync trackings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sync trackings
	 */
	@Override
	public List<SyncTracking> findAll(int start, int end,
		OrderByComparator<SyncTracking> orderByComparator,
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

		List<SyncTracking> list = null;

		if (retrieveFromCache) {
			list = (List<SyncTracking>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SYNCTRACKING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SYNCTRACKING;

				if (pagination) {
					sql = sql.concat(SyncTrackingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncTracking>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the sync trackings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SyncTracking syncTracking : findAll()) {
			remove(syncTracking);
		}
	}

	/**
	 * Returns the number of sync trackings.
	 *
	 * @return the number of sync trackings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SYNCTRACKING);

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
		return SyncTrackingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sync tracking persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SyncTrackingImpl.class.getName());
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

	private static final String _SQL_SELECT_SYNCTRACKING = "SELECT syncTracking FROM SyncTracking syncTracking";
	private static final String _SQL_SELECT_SYNCTRACKING_WHERE_PKS_IN = "SELECT syncTracking FROM SyncTracking syncTracking WHERE trackingId IN (";
	private static final String _SQL_SELECT_SYNCTRACKING_WHERE = "SELECT syncTracking FROM SyncTracking syncTracking WHERE ";
	private static final String _SQL_COUNT_SYNCTRACKING = "SELECT COUNT(syncTracking) FROM SyncTracking syncTracking";
	private static final String _SQL_COUNT_SYNCTRACKING_WHERE = "SELECT COUNT(syncTracking) FROM SyncTracking syncTracking WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "syncTracking.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SyncTracking exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SyncTracking exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SyncTrackingPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}