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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.usermgt.exception.NoSuchTrackClientException;
import org.opencps.usermgt.model.TrackClient;
import org.opencps.usermgt.model.impl.TrackClientImpl;
import org.opencps.usermgt.model.impl.TrackClientModelImpl;
import org.opencps.usermgt.service.persistence.TrackClientPersistence;

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
 * The persistence implementation for the track client service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see TrackClientPersistence
 * @see org.opencps.usermgt.service.persistence.TrackClientUtil
 * @generated
 */
@ProviderType
public class TrackClientPersistenceImpl extends BasePersistenceImpl<TrackClient>
	implements TrackClientPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrackClientUtil} to access the track client persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrackClientImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, TrackClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, TrackClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, TrackClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, TrackClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			TrackClientModelImpl.UUID_COLUMN_BITMASK |
			TrackClientModelImpl.VISITDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the track clients where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching track clients
	 */
	@Override
	public List<TrackClient> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the track clients where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @return the range of matching track clients
	 */
	@Override
	public List<TrackClient> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the track clients where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching track clients
	 */
	@Override
	public List<TrackClient> findByUuid(String uuid, int start, int end,
		OrderByComparator<TrackClient> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the track clients where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching track clients
	 */
	@Override
	public List<TrackClient> findByUuid(String uuid, int start, int end,
		OrderByComparator<TrackClient> orderByComparator,
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

		List<TrackClient> list = null;

		if (retrieveFromCache) {
			list = (List<TrackClient>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TrackClient trackClient : list) {
					if (!Objects.equals(uuid, trackClient.getUuid())) {
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

			query.append(_SQL_SELECT_TRACKCLIENT_WHERE);

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
				query.append(TrackClientModelImpl.ORDER_BY_JPQL);
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
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first track client in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client
	 * @throws NoSuchTrackClientException if a matching track client could not be found
	 */
	@Override
	public TrackClient findByUuid_First(String uuid,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = fetchByUuid_First(uuid, orderByComparator);

		if (trackClient != null) {
			return trackClient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchTrackClientException(msg.toString());
	}

	/**
	 * Returns the first track client in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client, or <code>null</code> if a matching track client could not be found
	 */
	@Override
	public TrackClient fetchByUuid_First(String uuid,
		OrderByComparator<TrackClient> orderByComparator) {
		List<TrackClient> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last track client in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client
	 * @throws NoSuchTrackClientException if a matching track client could not be found
	 */
	@Override
	public TrackClient findByUuid_Last(String uuid,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = fetchByUuid_Last(uuid, orderByComparator);

		if (trackClient != null) {
			return trackClient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchTrackClientException(msg.toString());
	}

	/**
	 * Returns the last track client in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client, or <code>null</code> if a matching track client could not be found
	 */
	@Override
	public TrackClient fetchByUuid_Last(String uuid,
		OrderByComparator<TrackClient> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<TrackClient> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the track clients before and after the current track client in the ordered set where uuid = &#63;.
	 *
	 * @param trackClientId the primary key of the current track client
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next track client
	 * @throws NoSuchTrackClientException if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient[] findByUuid_PrevAndNext(long trackClientId,
		String uuid, OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = findByPrimaryKey(trackClientId);

		Session session = null;

		try {
			session = openSession();

			TrackClient[] array = new TrackClientImpl[3];

			array[0] = getByUuid_PrevAndNext(session, trackClient, uuid,
					orderByComparator, true);

			array[1] = trackClient;

			array[2] = getByUuid_PrevAndNext(session, trackClient, uuid,
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

	protected TrackClient getByUuid_PrevAndNext(Session session,
		TrackClient trackClient, String uuid,
		OrderByComparator<TrackClient> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRACKCLIENT_WHERE);

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
			query.append(TrackClientModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(trackClient);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrackClient> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the track clients where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (TrackClient trackClient : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(trackClient);
		}
	}

	/**
	 * Returns the number of track clients where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching track clients
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRACKCLIENT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "trackClient.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "trackClient.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(trackClient.uuid IS NULL OR trackClient.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, TrackClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, TrackClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS",
			new String[] { String.class.getName() },
			TrackClientModelImpl.SESSIONID_COLUMN_BITMASK |
			TrackClientModelImpl.VISITDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS",
			new String[] { String.class.getName() });

	/**
	 * Returns all the track clients where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching track clients
	 */
	@Override
	public List<TrackClient> findByS(String sessionId) {
		return findByS(sessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the track clients where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @return the range of matching track clients
	 */
	@Override
	public List<TrackClient> findByS(String sessionId, int start, int end) {
		return findByS(sessionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the track clients where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching track clients
	 */
	@Override
	public List<TrackClient> findByS(String sessionId, int start, int end,
		OrderByComparator<TrackClient> orderByComparator) {
		return findByS(sessionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the track clients where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching track clients
	 */
	@Override
	public List<TrackClient> findByS(String sessionId, int start, int end,
		OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S;
			finderArgs = new Object[] { sessionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S;
			finderArgs = new Object[] { sessionId, start, end, orderByComparator };
		}

		List<TrackClient> list = null;

		if (retrieveFromCache) {
			list = (List<TrackClient>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TrackClient trackClient : list) {
					if (!Objects.equals(sessionId, trackClient.getSessionId())) {
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

			query.append(_SQL_SELECT_TRACKCLIENT_WHERE);

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_S_SESSIONID_1);
			}
			else if (sessionId.equals("")) {
				query.append(_FINDER_COLUMN_S_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_S_SESSIONID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TrackClientModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSessionId) {
					qPos.add(sessionId);
				}

				if (!pagination) {
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first track client in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client
	 * @throws NoSuchTrackClientException if a matching track client could not be found
	 */
	@Override
	public TrackClient findByS_First(String sessionId,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = fetchByS_First(sessionId, orderByComparator);

		if (trackClient != null) {
			return trackClient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sessionId=");
		msg.append(sessionId);

		msg.append("}");

		throw new NoSuchTrackClientException(msg.toString());
	}

	/**
	 * Returns the first track client in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client, or <code>null</code> if a matching track client could not be found
	 */
	@Override
	public TrackClient fetchByS_First(String sessionId,
		OrderByComparator<TrackClient> orderByComparator) {
		List<TrackClient> list = findByS(sessionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last track client in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client
	 * @throws NoSuchTrackClientException if a matching track client could not be found
	 */
	@Override
	public TrackClient findByS_Last(String sessionId,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = fetchByS_Last(sessionId, orderByComparator);

		if (trackClient != null) {
			return trackClient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sessionId=");
		msg.append(sessionId);

		msg.append("}");

		throw new NoSuchTrackClientException(msg.toString());
	}

	/**
	 * Returns the last track client in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client, or <code>null</code> if a matching track client could not be found
	 */
	@Override
	public TrackClient fetchByS_Last(String sessionId,
		OrderByComparator<TrackClient> orderByComparator) {
		int count = countByS(sessionId);

		if (count == 0) {
			return null;
		}

		List<TrackClient> list = findByS(sessionId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the track clients before and after the current track client in the ordered set where sessionId = &#63;.
	 *
	 * @param trackClientId the primary key of the current track client
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next track client
	 * @throws NoSuchTrackClientException if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient[] findByS_PrevAndNext(long trackClientId,
		String sessionId, OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = findByPrimaryKey(trackClientId);

		Session session = null;

		try {
			session = openSession();

			TrackClient[] array = new TrackClientImpl[3];

			array[0] = getByS_PrevAndNext(session, trackClient, sessionId,
					orderByComparator, true);

			array[1] = trackClient;

			array[2] = getByS_PrevAndNext(session, trackClient, sessionId,
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

	protected TrackClient getByS_PrevAndNext(Session session,
		TrackClient trackClient, String sessionId,
		OrderByComparator<TrackClient> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRACKCLIENT_WHERE);

		boolean bindSessionId = false;

		if (sessionId == null) {
			query.append(_FINDER_COLUMN_S_SESSIONID_1);
		}
		else if (sessionId.equals("")) {
			query.append(_FINDER_COLUMN_S_SESSIONID_3);
		}
		else {
			bindSessionId = true;

			query.append(_FINDER_COLUMN_S_SESSIONID_2);
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
			query.append(TrackClientModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSessionId) {
			qPos.add(sessionId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trackClient);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrackClient> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the track clients where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	@Override
	public void removeByS(String sessionId) {
		for (TrackClient trackClient : findByS(sessionId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(trackClient);
		}
	}

	/**
	 * Returns the number of track clients where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching track clients
	 */
	@Override
	public int countByS(String sessionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S;

		Object[] finderArgs = new Object[] { sessionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRACKCLIENT_WHERE);

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_S_SESSIONID_1);
			}
			else if (sessionId.equals("")) {
				query.append(_FINDER_COLUMN_S_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_S_SESSIONID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSessionId) {
					qPos.add(sessionId);
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

	private static final String _FINDER_COLUMN_S_SESSIONID_1 = "trackClient.sessionId IS NULL";
	private static final String _FINDER_COLUMN_S_SESSIONID_2 = "trackClient.sessionId = ?";
	private static final String _FINDER_COLUMN_S_SESSIONID_3 = "(trackClient.sessionId IS NULL OR trackClient.sessionId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_NULL_L = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, TrackClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_NULL_L",
			new String[] {
				String.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_NULL_L =
		new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, TrackClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_NULL_L",
			new String[] { String.class.getName(), Date.class.getName() },
			TrackClientModelImpl.SESSIONID_COLUMN_BITMASK |
			TrackClientModelImpl.LEAVEDATE_COLUMN_BITMASK |
			TrackClientModelImpl.VISITDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_NULL_L = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_NULL_L",
			new String[] { String.class.getName(), Date.class.getName() });

	/**
	 * Returns all the track clients where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @return the matching track clients
	 */
	@Override
	public List<TrackClient> findByS_NULL_L(String sessionId, Date leaveDate) {
		return findByS_NULL_L(sessionId, leaveDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the track clients where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @return the range of matching track clients
	 */
	@Override
	public List<TrackClient> findByS_NULL_L(String sessionId, Date leaveDate,
		int start, int end) {
		return findByS_NULL_L(sessionId, leaveDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the track clients where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching track clients
	 */
	@Override
	public List<TrackClient> findByS_NULL_L(String sessionId, Date leaveDate,
		int start, int end, OrderByComparator<TrackClient> orderByComparator) {
		return findByS_NULL_L(sessionId, leaveDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the track clients where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching track clients
	 */
	@Override
	public List<TrackClient> findByS_NULL_L(String sessionId, Date leaveDate,
		int start, int end, OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_NULL_L;
			finderArgs = new Object[] { sessionId, _getTime(leaveDate) };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S_NULL_L;
			finderArgs = new Object[] {
					sessionId, _getTime(leaveDate),
					
					start, end, orderByComparator
				};
		}

		List<TrackClient> list = null;

		if (retrieveFromCache) {
			list = (List<TrackClient>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TrackClient trackClient : list) {
					if (!Objects.equals(sessionId, trackClient.getSessionId()) ||
							!Objects.equals(leaveDate,
								trackClient.getLeaveDate())) {
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

			query.append(_SQL_SELECT_TRACKCLIENT_WHERE);

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_S_NULL_L_SESSIONID_1);
			}
			else if (sessionId.equals("")) {
				query.append(_FINDER_COLUMN_S_NULL_L_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_S_NULL_L_SESSIONID_2);
			}

			boolean bindLeaveDate = false;

			if (leaveDate == null) {
				query.append(_FINDER_COLUMN_S_NULL_L_LEAVEDATE_1);
			}
			else {
				bindLeaveDate = true;

				query.append(_FINDER_COLUMN_S_NULL_L_LEAVEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TrackClientModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSessionId) {
					qPos.add(sessionId);
				}

				if (bindLeaveDate) {
					qPos.add(new Timestamp(leaveDate.getTime()));
				}

				if (!pagination) {
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client
	 * @throws NoSuchTrackClientException if a matching track client could not be found
	 */
	@Override
	public TrackClient findByS_NULL_L_First(String sessionId, Date leaveDate,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = fetchByS_NULL_L_First(sessionId, leaveDate,
				orderByComparator);

		if (trackClient != null) {
			return trackClient;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sessionId=");
		msg.append(sessionId);

		msg.append(", leaveDate=");
		msg.append(leaveDate);

		msg.append("}");

		throw new NoSuchTrackClientException(msg.toString());
	}

	/**
	 * Returns the first track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client, or <code>null</code> if a matching track client could not be found
	 */
	@Override
	public TrackClient fetchByS_NULL_L_First(String sessionId, Date leaveDate,
		OrderByComparator<TrackClient> orderByComparator) {
		List<TrackClient> list = findByS_NULL_L(sessionId, leaveDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client
	 * @throws NoSuchTrackClientException if a matching track client could not be found
	 */
	@Override
	public TrackClient findByS_NULL_L_Last(String sessionId, Date leaveDate,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = fetchByS_NULL_L_Last(sessionId, leaveDate,
				orderByComparator);

		if (trackClient != null) {
			return trackClient;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sessionId=");
		msg.append(sessionId);

		msg.append(", leaveDate=");
		msg.append(leaveDate);

		msg.append("}");

		throw new NoSuchTrackClientException(msg.toString());
	}

	/**
	 * Returns the last track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client, or <code>null</code> if a matching track client could not be found
	 */
	@Override
	public TrackClient fetchByS_NULL_L_Last(String sessionId, Date leaveDate,
		OrderByComparator<TrackClient> orderByComparator) {
		int count = countByS_NULL_L(sessionId, leaveDate);

		if (count == 0) {
			return null;
		}

		List<TrackClient> list = findByS_NULL_L(sessionId, leaveDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the track clients before and after the current track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * @param trackClientId the primary key of the current track client
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next track client
	 * @throws NoSuchTrackClientException if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient[] findByS_NULL_L_PrevAndNext(long trackClientId,
		String sessionId, Date leaveDate,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = findByPrimaryKey(trackClientId);

		Session session = null;

		try {
			session = openSession();

			TrackClient[] array = new TrackClientImpl[3];

			array[0] = getByS_NULL_L_PrevAndNext(session, trackClient,
					sessionId, leaveDate, orderByComparator, true);

			array[1] = trackClient;

			array[2] = getByS_NULL_L_PrevAndNext(session, trackClient,
					sessionId, leaveDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrackClient getByS_NULL_L_PrevAndNext(Session session,
		TrackClient trackClient, String sessionId, Date leaveDate,
		OrderByComparator<TrackClient> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TRACKCLIENT_WHERE);

		boolean bindSessionId = false;

		if (sessionId == null) {
			query.append(_FINDER_COLUMN_S_NULL_L_SESSIONID_1);
		}
		else if (sessionId.equals("")) {
			query.append(_FINDER_COLUMN_S_NULL_L_SESSIONID_3);
		}
		else {
			bindSessionId = true;

			query.append(_FINDER_COLUMN_S_NULL_L_SESSIONID_2);
		}

		boolean bindLeaveDate = false;

		if (leaveDate == null) {
			query.append(_FINDER_COLUMN_S_NULL_L_LEAVEDATE_1);
		}
		else {
			bindLeaveDate = true;

			query.append(_FINDER_COLUMN_S_NULL_L_LEAVEDATE_2);
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
			query.append(TrackClientModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSessionId) {
			qPos.add(sessionId);
		}

		if (bindLeaveDate) {
			qPos.add(new Timestamp(leaveDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trackClient);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrackClient> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the track clients where sessionId = &#63; and leaveDate = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 */
	@Override
	public void removeByS_NULL_L(String sessionId, Date leaveDate) {
		for (TrackClient trackClient : findByS_NULL_L(sessionId, leaveDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(trackClient);
		}
	}

	/**
	 * Returns the number of track clients where sessionId = &#63; and leaveDate = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param leaveDate the leave date
	 * @return the number of matching track clients
	 */
	@Override
	public int countByS_NULL_L(String sessionId, Date leaveDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_NULL_L;

		Object[] finderArgs = new Object[] { sessionId, _getTime(leaveDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRACKCLIENT_WHERE);

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_S_NULL_L_SESSIONID_1);
			}
			else if (sessionId.equals("")) {
				query.append(_FINDER_COLUMN_S_NULL_L_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_S_NULL_L_SESSIONID_2);
			}

			boolean bindLeaveDate = false;

			if (leaveDate == null) {
				query.append(_FINDER_COLUMN_S_NULL_L_LEAVEDATE_1);
			}
			else {
				bindLeaveDate = true;

				query.append(_FINDER_COLUMN_S_NULL_L_LEAVEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSessionId) {
					qPos.add(sessionId);
				}

				if (bindLeaveDate) {
					qPos.add(new Timestamp(leaveDate.getTime()));
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

	private static final String _FINDER_COLUMN_S_NULL_L_SESSIONID_1 = "trackClient.sessionId IS NULL AND ";
	private static final String _FINDER_COLUMN_S_NULL_L_SESSIONID_2 = "trackClient.sessionId = ? AND ";
	private static final String _FINDER_COLUMN_S_NULL_L_SESSIONID_3 = "(trackClient.sessionId IS NULL OR trackClient.sessionId = '') AND ";
	private static final String _FINDER_COLUMN_S_NULL_L_LEAVEDATE_1 = "trackClient.leaveDate IS NULL AND trackClient.leaveDate IS NULL";
	private static final String _FINDER_COLUMN_S_NULL_L_LEAVEDATE_2 = "trackClient.leaveDate = ? AND trackClient.leaveDate IS NULL";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_LVD = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, TrackClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_LVD",
			new String[] {
				String.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_S_LVD = new FinderPath(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByS_LVD",
			new String[] { String.class.getName(), Date.class.getName() });

	/**
	 * Returns all the track clients where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @return the matching track clients
	 */
	@Override
	public List<TrackClient> findByS_LVD(String sessionId, Date visitDate) {
		return findByS_LVD(sessionId, visitDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the track clients where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @return the range of matching track clients
	 */
	@Override
	public List<TrackClient> findByS_LVD(String sessionId, Date visitDate,
		int start, int end) {
		return findByS_LVD(sessionId, visitDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the track clients where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching track clients
	 */
	@Override
	public List<TrackClient> findByS_LVD(String sessionId, Date visitDate,
		int start, int end, OrderByComparator<TrackClient> orderByComparator) {
		return findByS_LVD(sessionId, visitDate, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the track clients where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching track clients
	 */
	@Override
	public List<TrackClient> findByS_LVD(String sessionId, Date visitDate,
		int start, int end, OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S_LVD;
		finderArgs = new Object[] {
				sessionId, _getTime(visitDate),
				
				start, end, orderByComparator
			};

		List<TrackClient> list = null;

		if (retrieveFromCache) {
			list = (List<TrackClient>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TrackClient trackClient : list) {
					if (!Objects.equals(sessionId, trackClient.getSessionId()) ||
							(visitDate.getTime() <= trackClient.getVisitDate()
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

			query.append(_SQL_SELECT_TRACKCLIENT_WHERE);

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_S_LVD_SESSIONID_1);
			}
			else if (sessionId.equals("")) {
				query.append(_FINDER_COLUMN_S_LVD_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_S_LVD_SESSIONID_2);
			}

			boolean bindVisitDate = false;

			if (visitDate == null) {
				query.append(_FINDER_COLUMN_S_LVD_VISITDATE_1);
			}
			else {
				bindVisitDate = true;

				query.append(_FINDER_COLUMN_S_LVD_VISITDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TrackClientModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSessionId) {
					qPos.add(sessionId);
				}

				if (bindVisitDate) {
					qPos.add(new Timestamp(visitDate.getTime()));
				}

				if (!pagination) {
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client
	 * @throws NoSuchTrackClientException if a matching track client could not be found
	 */
	@Override
	public TrackClient findByS_LVD_First(String sessionId, Date visitDate,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = fetchByS_LVD_First(sessionId, visitDate,
				orderByComparator);

		if (trackClient != null) {
			return trackClient;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sessionId=");
		msg.append(sessionId);

		msg.append(", visitDate=");
		msg.append(visitDate);

		msg.append("}");

		throw new NoSuchTrackClientException(msg.toString());
	}

	/**
	 * Returns the first track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client, or <code>null</code> if a matching track client could not be found
	 */
	@Override
	public TrackClient fetchByS_LVD_First(String sessionId, Date visitDate,
		OrderByComparator<TrackClient> orderByComparator) {
		List<TrackClient> list = findByS_LVD(sessionId, visitDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client
	 * @throws NoSuchTrackClientException if a matching track client could not be found
	 */
	@Override
	public TrackClient findByS_LVD_Last(String sessionId, Date visitDate,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = fetchByS_LVD_Last(sessionId, visitDate,
				orderByComparator);

		if (trackClient != null) {
			return trackClient;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sessionId=");
		msg.append(sessionId);

		msg.append(", visitDate=");
		msg.append(visitDate);

		msg.append("}");

		throw new NoSuchTrackClientException(msg.toString());
	}

	/**
	 * Returns the last track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client, or <code>null</code> if a matching track client could not be found
	 */
	@Override
	public TrackClient fetchByS_LVD_Last(String sessionId, Date visitDate,
		OrderByComparator<TrackClient> orderByComparator) {
		int count = countByS_LVD(sessionId, visitDate);

		if (count == 0) {
			return null;
		}

		List<TrackClient> list = findByS_LVD(sessionId, visitDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the track clients before and after the current track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * @param trackClientId the primary key of the current track client
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next track client
	 * @throws NoSuchTrackClientException if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient[] findByS_LVD_PrevAndNext(long trackClientId,
		String sessionId, Date visitDate,
		OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException {
		TrackClient trackClient = findByPrimaryKey(trackClientId);

		Session session = null;

		try {
			session = openSession();

			TrackClient[] array = new TrackClientImpl[3];

			array[0] = getByS_LVD_PrevAndNext(session, trackClient, sessionId,
					visitDate, orderByComparator, true);

			array[1] = trackClient;

			array[2] = getByS_LVD_PrevAndNext(session, trackClient, sessionId,
					visitDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TrackClient getByS_LVD_PrevAndNext(Session session,
		TrackClient trackClient, String sessionId, Date visitDate,
		OrderByComparator<TrackClient> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TRACKCLIENT_WHERE);

		boolean bindSessionId = false;

		if (sessionId == null) {
			query.append(_FINDER_COLUMN_S_LVD_SESSIONID_1);
		}
		else if (sessionId.equals("")) {
			query.append(_FINDER_COLUMN_S_LVD_SESSIONID_3);
		}
		else {
			bindSessionId = true;

			query.append(_FINDER_COLUMN_S_LVD_SESSIONID_2);
		}

		boolean bindVisitDate = false;

		if (visitDate == null) {
			query.append(_FINDER_COLUMN_S_LVD_VISITDATE_1);
		}
		else {
			bindVisitDate = true;

			query.append(_FINDER_COLUMN_S_LVD_VISITDATE_2);
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
			query.append(TrackClientModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSessionId) {
			qPos.add(sessionId);
		}

		if (bindVisitDate) {
			qPos.add(new Timestamp(visitDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(trackClient);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrackClient> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the track clients where sessionId = &#63; and visitDate &lt; &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 */
	@Override
	public void removeByS_LVD(String sessionId, Date visitDate) {
		for (TrackClient trackClient : findByS_LVD(sessionId, visitDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(trackClient);
		}
	}

	/**
	 * Returns the number of track clients where sessionId = &#63; and visitDate &lt; &#63;.
	 *
	 * @param sessionId the session ID
	 * @param visitDate the visit date
	 * @return the number of matching track clients
	 */
	@Override
	public int countByS_LVD(String sessionId, Date visitDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_S_LVD;

		Object[] finderArgs = new Object[] { sessionId, _getTime(visitDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRACKCLIENT_WHERE);

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_S_LVD_SESSIONID_1);
			}
			else if (sessionId.equals("")) {
				query.append(_FINDER_COLUMN_S_LVD_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_S_LVD_SESSIONID_2);
			}

			boolean bindVisitDate = false;

			if (visitDate == null) {
				query.append(_FINDER_COLUMN_S_LVD_VISITDATE_1);
			}
			else {
				bindVisitDate = true;

				query.append(_FINDER_COLUMN_S_LVD_VISITDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSessionId) {
					qPos.add(sessionId);
				}

				if (bindVisitDate) {
					qPos.add(new Timestamp(visitDate.getTime()));
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

	private static final String _FINDER_COLUMN_S_LVD_SESSIONID_1 = "trackClient.sessionId IS NULL AND ";
	private static final String _FINDER_COLUMN_S_LVD_SESSIONID_2 = "trackClient.sessionId = ? AND ";
	private static final String _FINDER_COLUMN_S_LVD_SESSIONID_3 = "(trackClient.sessionId IS NULL OR trackClient.sessionId = '') AND ";
	private static final String _FINDER_COLUMN_S_LVD_VISITDATE_1 = "trackClient.visitDate IS NULL";
	private static final String _FINDER_COLUMN_S_LVD_VISITDATE_2 = "trackClient.visitDate < ?";

	public TrackClientPersistenceImpl() {
		setModelClass(TrackClient.class);

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
	 * Caches the track client in the entity cache if it is enabled.
	 *
	 * @param trackClient the track client
	 */
	@Override
	public void cacheResult(TrackClient trackClient) {
		entityCache.putResult(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientImpl.class, trackClient.getPrimaryKey(), trackClient);

		trackClient.resetOriginalValues();
	}

	/**
	 * Caches the track clients in the entity cache if it is enabled.
	 *
	 * @param trackClients the track clients
	 */
	@Override
	public void cacheResult(List<TrackClient> trackClients) {
		for (TrackClient trackClient : trackClients) {
			if (entityCache.getResult(
						TrackClientModelImpl.ENTITY_CACHE_ENABLED,
						TrackClientImpl.class, trackClient.getPrimaryKey()) == null) {
				cacheResult(trackClient);
			}
			else {
				trackClient.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all track clients.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TrackClientImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the track client.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrackClient trackClient) {
		entityCache.removeResult(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientImpl.class, trackClient.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TrackClient> trackClients) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrackClient trackClient : trackClients) {
			entityCache.removeResult(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
				TrackClientImpl.class, trackClient.getPrimaryKey());
		}
	}

	/**
	 * Creates a new track client with the primary key. Does not add the track client to the database.
	 *
	 * @param trackClientId the primary key for the new track client
	 * @return the new track client
	 */
	@Override
	public TrackClient create(long trackClientId) {
		TrackClient trackClient = new TrackClientImpl();

		trackClient.setNew(true);
		trackClient.setPrimaryKey(trackClientId);

		String uuid = PortalUUIDUtil.generate();

		trackClient.setUuid(uuid);

		return trackClient;
	}

	/**
	 * Removes the track client with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackClientId the primary key of the track client
	 * @return the track client that was removed
	 * @throws NoSuchTrackClientException if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient remove(long trackClientId)
		throws NoSuchTrackClientException {
		return remove((Serializable)trackClientId);
	}

	/**
	 * Removes the track client with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the track client
	 * @return the track client that was removed
	 * @throws NoSuchTrackClientException if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient remove(Serializable primaryKey)
		throws NoSuchTrackClientException {
		Session session = null;

		try {
			session = openSession();

			TrackClient trackClient = (TrackClient)session.get(TrackClientImpl.class,
					primaryKey);

			if (trackClient == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrackClientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trackClient);
		}
		catch (NoSuchTrackClientException nsee) {
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
	protected TrackClient removeImpl(TrackClient trackClient) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trackClient)) {
				trackClient = (TrackClient)session.get(TrackClientImpl.class,
						trackClient.getPrimaryKeyObj());
			}

			if (trackClient != null) {
				session.delete(trackClient);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (trackClient != null) {
			clearCache(trackClient);
		}

		return trackClient;
	}

	@Override
	public TrackClient updateImpl(TrackClient trackClient) {
		boolean isNew = trackClient.isNew();

		if (!(trackClient instanceof TrackClientModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(trackClient.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(trackClient);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in trackClient proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TrackClient implementation " +
				trackClient.getClass());
		}

		TrackClientModelImpl trackClientModelImpl = (TrackClientModelImpl)trackClient;

		if (Validator.isNull(trackClient.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			trackClient.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (trackClient.getCreateDate() == null)) {
			if (serviceContext == null) {
				trackClient.setCreateDate(now);
			}
			else {
				trackClient.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!trackClientModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				trackClient.setModifiedDate(now);
			}
			else {
				trackClient.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (trackClient.isNew()) {
				session.save(trackClient);

				trackClient.setNew(false);
			}
			else {
				trackClient = (TrackClient)session.merge(trackClient);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TrackClientModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { trackClientModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { trackClientModelImpl.getSessionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_S, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S,
				args);

			args = new Object[] {
					trackClientModelImpl.getSessionId(),
					trackClientModelImpl.getLeaveDate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_S_NULL_L, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_NULL_L,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((trackClientModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						trackClientModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { trackClientModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((trackClientModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						trackClientModelImpl.getOriginalSessionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S,
					args);

				args = new Object[] { trackClientModelImpl.getSessionId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S,
					args);
			}

			if ((trackClientModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_NULL_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						trackClientModelImpl.getOriginalSessionId(),
						trackClientModelImpl.getOriginalLeaveDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_NULL_L, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_NULL_L,
					args);

				args = new Object[] {
						trackClientModelImpl.getSessionId(),
						trackClientModelImpl.getLeaveDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_NULL_L, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_NULL_L,
					args);
			}
		}

		entityCache.putResult(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientImpl.class, trackClient.getPrimaryKey(), trackClient,
			false);

		trackClient.resetOriginalValues();

		return trackClient;
	}

	/**
	 * Returns the track client with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the track client
	 * @return the track client
	 * @throws NoSuchTrackClientException if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrackClientException {
		TrackClient trackClient = fetchByPrimaryKey(primaryKey);

		if (trackClient == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrackClientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return trackClient;
	}

	/**
	 * Returns the track client with the primary key or throws a {@link NoSuchTrackClientException} if it could not be found.
	 *
	 * @param trackClientId the primary key of the track client
	 * @return the track client
	 * @throws NoSuchTrackClientException if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient findByPrimaryKey(long trackClientId)
		throws NoSuchTrackClientException {
		return findByPrimaryKey((Serializable)trackClientId);
	}

	/**
	 * Returns the track client with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the track client
	 * @return the track client, or <code>null</code> if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
				TrackClientImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TrackClient trackClient = (TrackClient)serializable;

		if (trackClient == null) {
			Session session = null;

			try {
				session = openSession();

				trackClient = (TrackClient)session.get(TrackClientImpl.class,
						primaryKey);

				if (trackClient != null) {
					cacheResult(trackClient);
				}
				else {
					entityCache.putResult(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
						TrackClientImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
					TrackClientImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return trackClient;
	}

	/**
	 * Returns the track client with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trackClientId the primary key of the track client
	 * @return the track client, or <code>null</code> if a track client with the primary key could not be found
	 */
	@Override
	public TrackClient fetchByPrimaryKey(long trackClientId) {
		return fetchByPrimaryKey((Serializable)trackClientId);
	}

	@Override
	public Map<Serializable, TrackClient> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TrackClient> map = new HashMap<Serializable, TrackClient>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TrackClient trackClient = fetchByPrimaryKey(primaryKey);

			if (trackClient != null) {
				map.put(primaryKey, trackClient);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
					TrackClientImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TrackClient)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TRACKCLIENT_WHERE_PKS_IN);

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

			for (TrackClient trackClient : (List<TrackClient>)q.list()) {
				map.put(trackClient.getPrimaryKeyObj(), trackClient);

				cacheResult(trackClient);

				uncachedPrimaryKeys.remove(trackClient.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TrackClientModelImpl.ENTITY_CACHE_ENABLED,
					TrackClientImpl.class, primaryKey, nullModel);
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
	 * Returns all the track clients.
	 *
	 * @return the track clients
	 */
	@Override
	public List<TrackClient> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the track clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @return the range of track clients
	 */
	@Override
	public List<TrackClient> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the track clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of track clients
	 */
	@Override
	public List<TrackClient> findAll(int start, int end,
		OrderByComparator<TrackClient> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the track clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of track clients
	 * @param end the upper bound of the range of track clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of track clients
	 */
	@Override
	public List<TrackClient> findAll(int start, int end,
		OrderByComparator<TrackClient> orderByComparator,
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

		List<TrackClient> list = null;

		if (retrieveFromCache) {
			list = (List<TrackClient>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TRACKCLIENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRACKCLIENT;

				if (pagination) {
					sql = sql.concat(TrackClientModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TrackClient>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the track clients from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrackClient trackClient : findAll()) {
			remove(trackClient);
		}
	}

	/**
	 * Returns the number of track clients.
	 *
	 * @return the number of track clients
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRACKCLIENT);

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
		return TrackClientModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the track client persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TrackClientImpl.class.getName());
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

	private static final String _SQL_SELECT_TRACKCLIENT = "SELECT trackClient FROM TrackClient trackClient";
	private static final String _SQL_SELECT_TRACKCLIENT_WHERE_PKS_IN = "SELECT trackClient FROM TrackClient trackClient WHERE trackClientId IN (";
	private static final String _SQL_SELECT_TRACKCLIENT_WHERE = "SELECT trackClient FROM TrackClient trackClient WHERE ";
	private static final String _SQL_COUNT_TRACKCLIENT = "SELECT COUNT(trackClient) FROM TrackClient trackClient";
	private static final String _SQL_COUNT_TRACKCLIENT_WHERE = "SELECT COUNT(trackClient) FROM TrackClient trackClient WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trackClient.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrackClient exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrackClient exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TrackClientPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}