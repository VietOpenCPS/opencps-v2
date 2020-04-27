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

import org.opencps.usermgt.exception.NoSuchTrackClientStatisticException;
import org.opencps.usermgt.model.TrackClientStatistic;
import org.opencps.usermgt.model.impl.TrackClientStatisticImpl;
import org.opencps.usermgt.model.impl.TrackClientStatisticModelImpl;
import org.opencps.usermgt.service.persistence.TrackClientStatisticPersistence;

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
 * The persistence implementation for the track client statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see TrackClientStatisticPersistence
 * @see org.opencps.usermgt.service.persistence.TrackClientStatisticUtil
 * @generated
 */
@ProviderType
public class TrackClientStatisticPersistenceImpl extends BasePersistenceImpl<TrackClientStatistic>
	implements TrackClientStatisticPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TrackClientStatisticUtil} to access the track client statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TrackClientStatisticImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticModelImpl.FINDER_CACHE_ENABLED,
			TrackClientStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticModelImpl.FINDER_CACHE_ENABLED,
			TrackClientStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticModelImpl.FINDER_CACHE_ENABLED,
			TrackClientStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticModelImpl.FINDER_CACHE_ENABLED,
			TrackClientStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			TrackClientStatisticModelImpl.UUID_COLUMN_BITMASK |
			TrackClientStatisticModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the track client statistics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching track client statistics
	 */
	@Override
	public List<TrackClientStatistic> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the track client statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of track client statistics
	 * @param end the upper bound of the range of track client statistics (not inclusive)
	 * @return the range of matching track client statistics
	 */
	@Override
	public List<TrackClientStatistic> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the track client statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of track client statistics
	 * @param end the upper bound of the range of track client statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching track client statistics
	 */
	@Override
	public List<TrackClientStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<TrackClientStatistic> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the track client statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of track client statistics
	 * @param end the upper bound of the range of track client statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching track client statistics
	 */
	@Override
	public List<TrackClientStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<TrackClientStatistic> orderByComparator,
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

		List<TrackClientStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<TrackClientStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TrackClientStatistic trackClientStatistic : list) {
					if (!Objects.equals(uuid, trackClientStatistic.getUuid())) {
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

			query.append(_SQL_SELECT_TRACKCLIENTSTATISTIC_WHERE);

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
				query.append(TrackClientStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<TrackClientStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TrackClientStatistic>)QueryUtil.list(q,
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
	 * Returns the first track client statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client statistic
	 * @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	 */
	@Override
	public TrackClientStatistic findByUuid_First(String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator)
		throws NoSuchTrackClientStatisticException {
		TrackClientStatistic trackClientStatistic = fetchByUuid_First(uuid,
				orderByComparator);

		if (trackClientStatistic != null) {
			return trackClientStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchTrackClientStatisticException(msg.toString());
	}

	/**
	 * Returns the first track client statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	 */
	@Override
	public TrackClientStatistic fetchByUuid_First(String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator) {
		List<TrackClientStatistic> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last track client statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client statistic
	 * @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	 */
	@Override
	public TrackClientStatistic findByUuid_Last(String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator)
		throws NoSuchTrackClientStatisticException {
		TrackClientStatistic trackClientStatistic = fetchByUuid_Last(uuid,
				orderByComparator);

		if (trackClientStatistic != null) {
			return trackClientStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchTrackClientStatisticException(msg.toString());
	}

	/**
	 * Returns the last track client statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	 */
	@Override
	public TrackClientStatistic fetchByUuid_Last(String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<TrackClientStatistic> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the track client statistics before and after the current track client statistic in the ordered set where uuid = &#63;.
	 *
	 * @param trackClientStatisticId the primary key of the current track client statistic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next track client statistic
	 * @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	 */
	@Override
	public TrackClientStatistic[] findByUuid_PrevAndNext(
		long trackClientStatisticId, String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator)
		throws NoSuchTrackClientStatisticException {
		TrackClientStatistic trackClientStatistic = findByPrimaryKey(trackClientStatisticId);

		Session session = null;

		try {
			session = openSession();

			TrackClientStatistic[] array = new TrackClientStatisticImpl[3];

			array[0] = getByUuid_PrevAndNext(session, trackClientStatistic,
					uuid, orderByComparator, true);

			array[1] = trackClientStatistic;

			array[2] = getByUuid_PrevAndNext(session, trackClientStatistic,
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

	protected TrackClientStatistic getByUuid_PrevAndNext(Session session,
		TrackClientStatistic trackClientStatistic, String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_TRACKCLIENTSTATISTIC_WHERE);

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
			query.append(TrackClientStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(trackClientStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TrackClientStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the track client statistics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (TrackClientStatistic trackClientStatistic : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(trackClientStatistic);
		}
	}

	/**
	 * Returns the number of track client statistics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching track client statistics
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRACKCLIENTSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "trackClientStatistic.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "trackClientStatistic.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(trackClientStatistic.uuid IS NULL OR trackClientStatistic.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_D_M_T = new FinderPath(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticModelImpl.FINDER_CACHE_ENABLED,
			TrackClientStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_D_M_T",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			},
			TrackClientStatisticModelImpl.URL_COLUMN_BITMASK |
			TrackClientStatisticModelImpl.DESKTOP_COLUMN_BITMASK |
			TrackClientStatisticModelImpl.MOBILE_COLUMN_BITMASK |
			TrackClientStatisticModelImpl.TABLET_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_D_M_T = new FinderPath(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_D_M_T",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns the track client statistic where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; or throws a {@link NoSuchTrackClientStatisticException} if it could not be found.
	 *
	 * @param url the url
	 * @param desktop the desktop
	 * @param mobile the mobile
	 * @param tablet the tablet
	 * @return the matching track client statistic
	 * @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	 */
	@Override
	public TrackClientStatistic findByU_D_M_T(String url, boolean desktop,
		boolean mobile, boolean tablet)
		throws NoSuchTrackClientStatisticException {
		TrackClientStatistic trackClientStatistic = fetchByU_D_M_T(url,
				desktop, mobile, tablet);

		if (trackClientStatistic == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("url=");
			msg.append(url);

			msg.append(", desktop=");
			msg.append(desktop);

			msg.append(", mobile=");
			msg.append(mobile);

			msg.append(", tablet=");
			msg.append(tablet);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTrackClientStatisticException(msg.toString());
		}

		return trackClientStatistic;
	}

	/**
	 * Returns the track client statistic where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param url the url
	 * @param desktop the desktop
	 * @param mobile the mobile
	 * @param tablet the tablet
	 * @return the matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	 */
	@Override
	public TrackClientStatistic fetchByU_D_M_T(String url, boolean desktop,
		boolean mobile, boolean tablet) {
		return fetchByU_D_M_T(url, desktop, mobile, tablet, true);
	}

	/**
	 * Returns the track client statistic where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param url the url
	 * @param desktop the desktop
	 * @param mobile the mobile
	 * @param tablet the tablet
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	 */
	@Override
	public TrackClientStatistic fetchByU_D_M_T(String url, boolean desktop,
		boolean mobile, boolean tablet, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { url, desktop, mobile, tablet };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_D_M_T,
					finderArgs, this);
		}

		if (result instanceof TrackClientStatistic) {
			TrackClientStatistic trackClientStatistic = (TrackClientStatistic)result;

			if (!Objects.equals(url, trackClientStatistic.getUrl()) ||
					(desktop != trackClientStatistic.isDesktop()) ||
					(mobile != trackClientStatistic.isMobile()) ||
					(tablet != trackClientStatistic.isTablet())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_TRACKCLIENTSTATISTIC_WHERE);

			boolean bindUrl = false;

			if (url == null) {
				query.append(_FINDER_COLUMN_U_D_M_T_URL_1);
			}
			else if (url.equals("")) {
				query.append(_FINDER_COLUMN_U_D_M_T_URL_3);
			}
			else {
				bindUrl = true;

				query.append(_FINDER_COLUMN_U_D_M_T_URL_2);
			}

			query.append(_FINDER_COLUMN_U_D_M_T_DESKTOP_2);

			query.append(_FINDER_COLUMN_U_D_M_T_MOBILE_2);

			query.append(_FINDER_COLUMN_U_D_M_T_TABLET_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUrl) {
					qPos.add(url);
				}

				qPos.add(desktop);

				qPos.add(mobile);

				qPos.add(tablet);

				List<TrackClientStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_D_M_T,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"TrackClientStatisticPersistenceImpl.fetchByU_D_M_T(String, boolean, boolean, boolean, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					TrackClientStatistic trackClientStatistic = list.get(0);

					result = trackClientStatistic;

					cacheResult(trackClientStatistic);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_D_M_T,
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
			return (TrackClientStatistic)result;
		}
	}

	/**
	 * Removes the track client statistic where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; from the database.
	 *
	 * @param url the url
	 * @param desktop the desktop
	 * @param mobile the mobile
	 * @param tablet the tablet
	 * @return the track client statistic that was removed
	 */
	@Override
	public TrackClientStatistic removeByU_D_M_T(String url, boolean desktop,
		boolean mobile, boolean tablet)
		throws NoSuchTrackClientStatisticException {
		TrackClientStatistic trackClientStatistic = findByU_D_M_T(url, desktop,
				mobile, tablet);

		return remove(trackClientStatistic);
	}

	/**
	 * Returns the number of track client statistics where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63;.
	 *
	 * @param url the url
	 * @param desktop the desktop
	 * @param mobile the mobile
	 * @param tablet the tablet
	 * @return the number of matching track client statistics
	 */
	@Override
	public int countByU_D_M_T(String url, boolean desktop, boolean mobile,
		boolean tablet) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_D_M_T;

		Object[] finderArgs = new Object[] { url, desktop, mobile, tablet };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TRACKCLIENTSTATISTIC_WHERE);

			boolean bindUrl = false;

			if (url == null) {
				query.append(_FINDER_COLUMN_U_D_M_T_URL_1);
			}
			else if (url.equals("")) {
				query.append(_FINDER_COLUMN_U_D_M_T_URL_3);
			}
			else {
				bindUrl = true;

				query.append(_FINDER_COLUMN_U_D_M_T_URL_2);
			}

			query.append(_FINDER_COLUMN_U_D_M_T_DESKTOP_2);

			query.append(_FINDER_COLUMN_U_D_M_T_MOBILE_2);

			query.append(_FINDER_COLUMN_U_D_M_T_TABLET_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUrl) {
					qPos.add(url);
				}

				qPos.add(desktop);

				qPos.add(mobile);

				qPos.add(tablet);

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

	private static final String _FINDER_COLUMN_U_D_M_T_URL_1 = "trackClientStatistic.url IS NULL AND ";
	private static final String _FINDER_COLUMN_U_D_M_T_URL_2 = "trackClientStatistic.url = ? AND ";
	private static final String _FINDER_COLUMN_U_D_M_T_URL_3 = "(trackClientStatistic.url IS NULL OR trackClientStatistic.url = '') AND ";
	private static final String _FINDER_COLUMN_U_D_M_T_DESKTOP_2 = "trackClientStatistic.desktop = ? AND ";
	private static final String _FINDER_COLUMN_U_D_M_T_MOBILE_2 = "trackClientStatistic.mobile = ? AND ";
	private static final String _FINDER_COLUMN_U_D_M_T_TABLET_2 = "trackClientStatistic.tablet = ?";

	public TrackClientStatisticPersistenceImpl() {
		setModelClass(TrackClientStatistic.class);

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
	 * Caches the track client statistic in the entity cache if it is enabled.
	 *
	 * @param trackClientStatistic the track client statistic
	 */
	@Override
	public void cacheResult(TrackClientStatistic trackClientStatistic) {
		entityCache.putResult(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticImpl.class,
			trackClientStatistic.getPrimaryKey(), trackClientStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_D_M_T,
			new Object[] {
				trackClientStatistic.getUrl(), trackClientStatistic.isDesktop(),
				trackClientStatistic.isMobile(), trackClientStatistic.isTablet()
			}, trackClientStatistic);

		trackClientStatistic.resetOriginalValues();
	}

	/**
	 * Caches the track client statistics in the entity cache if it is enabled.
	 *
	 * @param trackClientStatistics the track client statistics
	 */
	@Override
	public void cacheResult(List<TrackClientStatistic> trackClientStatistics) {
		for (TrackClientStatistic trackClientStatistic : trackClientStatistics) {
			if (entityCache.getResult(
						TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
						TrackClientStatisticImpl.class,
						trackClientStatistic.getPrimaryKey()) == null) {
				cacheResult(trackClientStatistic);
			}
			else {
				trackClientStatistic.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all track client statistics.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TrackClientStatisticImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the track client statistic.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TrackClientStatistic trackClientStatistic) {
		entityCache.removeResult(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticImpl.class, trackClientStatistic.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TrackClientStatisticModelImpl)trackClientStatistic,
			true);
	}

	@Override
	public void clearCache(List<TrackClientStatistic> trackClientStatistics) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TrackClientStatistic trackClientStatistic : trackClientStatistics) {
			entityCache.removeResult(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
				TrackClientStatisticImpl.class,
				trackClientStatistic.getPrimaryKey());

			clearUniqueFindersCache((TrackClientStatisticModelImpl)trackClientStatistic,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		TrackClientStatisticModelImpl trackClientStatisticModelImpl) {
		Object[] args = new Object[] {
				trackClientStatisticModelImpl.getUrl(),
				trackClientStatisticModelImpl.isDesktop(),
				trackClientStatisticModelImpl.isMobile(),
				trackClientStatisticModelImpl.isTablet()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_D_M_T, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_D_M_T, args,
			trackClientStatisticModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		TrackClientStatisticModelImpl trackClientStatisticModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					trackClientStatisticModelImpl.getUrl(),
					trackClientStatisticModelImpl.isDesktop(),
					trackClientStatisticModelImpl.isMobile(),
					trackClientStatisticModelImpl.isTablet()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_D_M_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_D_M_T, args);
		}

		if ((trackClientStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_D_M_T.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					trackClientStatisticModelImpl.getOriginalUrl(),
					trackClientStatisticModelImpl.getOriginalDesktop(),
					trackClientStatisticModelImpl.getOriginalMobile(),
					trackClientStatisticModelImpl.getOriginalTablet()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_D_M_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_D_M_T, args);
		}
	}

	/**
	 * Creates a new track client statistic with the primary key. Does not add the track client statistic to the database.
	 *
	 * @param trackClientStatisticId the primary key for the new track client statistic
	 * @return the new track client statistic
	 */
	@Override
	public TrackClientStatistic create(long trackClientStatisticId) {
		TrackClientStatistic trackClientStatistic = new TrackClientStatisticImpl();

		trackClientStatistic.setNew(true);
		trackClientStatistic.setPrimaryKey(trackClientStatisticId);

		String uuid = PortalUUIDUtil.generate();

		trackClientStatistic.setUuid(uuid);

		return trackClientStatistic;
	}

	/**
	 * Removes the track client statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackClientStatisticId the primary key of the track client statistic
	 * @return the track client statistic that was removed
	 * @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	 */
	@Override
	public TrackClientStatistic remove(long trackClientStatisticId)
		throws NoSuchTrackClientStatisticException {
		return remove((Serializable)trackClientStatisticId);
	}

	/**
	 * Removes the track client statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the track client statistic
	 * @return the track client statistic that was removed
	 * @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	 */
	@Override
	public TrackClientStatistic remove(Serializable primaryKey)
		throws NoSuchTrackClientStatisticException {
		Session session = null;

		try {
			session = openSession();

			TrackClientStatistic trackClientStatistic = (TrackClientStatistic)session.get(TrackClientStatisticImpl.class,
					primaryKey);

			if (trackClientStatistic == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTrackClientStatisticException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(trackClientStatistic);
		}
		catch (NoSuchTrackClientStatisticException nsee) {
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
	protected TrackClientStatistic removeImpl(
		TrackClientStatistic trackClientStatistic) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(trackClientStatistic)) {
				trackClientStatistic = (TrackClientStatistic)session.get(TrackClientStatisticImpl.class,
						trackClientStatistic.getPrimaryKeyObj());
			}

			if (trackClientStatistic != null) {
				session.delete(trackClientStatistic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (trackClientStatistic != null) {
			clearCache(trackClientStatistic);
		}

		return trackClientStatistic;
	}

	@Override
	public TrackClientStatistic updateImpl(
		TrackClientStatistic trackClientStatistic) {
		boolean isNew = trackClientStatistic.isNew();

		if (!(trackClientStatistic instanceof TrackClientStatisticModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(trackClientStatistic.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(trackClientStatistic);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in trackClientStatistic proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TrackClientStatistic implementation " +
				trackClientStatistic.getClass());
		}

		TrackClientStatisticModelImpl trackClientStatisticModelImpl = (TrackClientStatisticModelImpl)trackClientStatistic;

		if (Validator.isNull(trackClientStatistic.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			trackClientStatistic.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (trackClientStatistic.getCreateDate() == null)) {
			if (serviceContext == null) {
				trackClientStatistic.setCreateDate(now);
			}
			else {
				trackClientStatistic.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!trackClientStatisticModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				trackClientStatistic.setModifiedDate(now);
			}
			else {
				trackClientStatistic.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (trackClientStatistic.isNew()) {
				session.save(trackClientStatistic);

				trackClientStatistic.setNew(false);
			}
			else {
				trackClientStatistic = (TrackClientStatistic)session.merge(trackClientStatistic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!TrackClientStatisticModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { trackClientStatisticModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((trackClientStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						trackClientStatisticModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { trackClientStatisticModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
			TrackClientStatisticImpl.class,
			trackClientStatistic.getPrimaryKey(), trackClientStatistic, false);

		clearUniqueFindersCache(trackClientStatisticModelImpl, false);
		cacheUniqueFindersCache(trackClientStatisticModelImpl);

		trackClientStatistic.resetOriginalValues();

		return trackClientStatistic;
	}

	/**
	 * Returns the track client statistic with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the track client statistic
	 * @return the track client statistic
	 * @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	 */
	@Override
	public TrackClientStatistic findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTrackClientStatisticException {
		TrackClientStatistic trackClientStatistic = fetchByPrimaryKey(primaryKey);

		if (trackClientStatistic == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTrackClientStatisticException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return trackClientStatistic;
	}

	/**
	 * Returns the track client statistic with the primary key or throws a {@link NoSuchTrackClientStatisticException} if it could not be found.
	 *
	 * @param trackClientStatisticId the primary key of the track client statistic
	 * @return the track client statistic
	 * @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	 */
	@Override
	public TrackClientStatistic findByPrimaryKey(long trackClientStatisticId)
		throws NoSuchTrackClientStatisticException {
		return findByPrimaryKey((Serializable)trackClientStatisticId);
	}

	/**
	 * Returns the track client statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the track client statistic
	 * @return the track client statistic, or <code>null</code> if a track client statistic with the primary key could not be found
	 */
	@Override
	public TrackClientStatistic fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
				TrackClientStatisticImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TrackClientStatistic trackClientStatistic = (TrackClientStatistic)serializable;

		if (trackClientStatistic == null) {
			Session session = null;

			try {
				session = openSession();

				trackClientStatistic = (TrackClientStatistic)session.get(TrackClientStatisticImpl.class,
						primaryKey);

				if (trackClientStatistic != null) {
					cacheResult(trackClientStatistic);
				}
				else {
					entityCache.putResult(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
						TrackClientStatisticImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
					TrackClientStatisticImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return trackClientStatistic;
	}

	/**
	 * Returns the track client statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param trackClientStatisticId the primary key of the track client statistic
	 * @return the track client statistic, or <code>null</code> if a track client statistic with the primary key could not be found
	 */
	@Override
	public TrackClientStatistic fetchByPrimaryKey(long trackClientStatisticId) {
		return fetchByPrimaryKey((Serializable)trackClientStatisticId);
	}

	@Override
	public Map<Serializable, TrackClientStatistic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TrackClientStatistic> map = new HashMap<Serializable, TrackClientStatistic>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TrackClientStatistic trackClientStatistic = fetchByPrimaryKey(primaryKey);

			if (trackClientStatistic != null) {
				map.put(primaryKey, trackClientStatistic);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
					TrackClientStatisticImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TrackClientStatistic)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TRACKCLIENTSTATISTIC_WHERE_PKS_IN);

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

			for (TrackClientStatistic trackClientStatistic : (List<TrackClientStatistic>)q.list()) {
				map.put(trackClientStatistic.getPrimaryKeyObj(),
					trackClientStatistic);

				cacheResult(trackClientStatistic);

				uncachedPrimaryKeys.remove(trackClientStatistic.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TrackClientStatisticModelImpl.ENTITY_CACHE_ENABLED,
					TrackClientStatisticImpl.class, primaryKey, nullModel);
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
	 * Returns all the track client statistics.
	 *
	 * @return the track client statistics
	 */
	@Override
	public List<TrackClientStatistic> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the track client statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of track client statistics
	 * @param end the upper bound of the range of track client statistics (not inclusive)
	 * @return the range of track client statistics
	 */
	@Override
	public List<TrackClientStatistic> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the track client statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of track client statistics
	 * @param end the upper bound of the range of track client statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of track client statistics
	 */
	@Override
	public List<TrackClientStatistic> findAll(int start, int end,
		OrderByComparator<TrackClientStatistic> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the track client statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of track client statistics
	 * @param end the upper bound of the range of track client statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of track client statistics
	 */
	@Override
	public List<TrackClientStatistic> findAll(int start, int end,
		OrderByComparator<TrackClientStatistic> orderByComparator,
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

		List<TrackClientStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<TrackClientStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TRACKCLIENTSTATISTIC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRACKCLIENTSTATISTIC;

				if (pagination) {
					sql = sql.concat(TrackClientStatisticModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TrackClientStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TrackClientStatistic>)QueryUtil.list(q,
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
	 * Removes all the track client statistics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TrackClientStatistic trackClientStatistic : findAll()) {
			remove(trackClientStatistic);
		}
	}

	/**
	 * Returns the number of track client statistics.
	 *
	 * @return the number of track client statistics
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRACKCLIENTSTATISTIC);

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
		return TrackClientStatisticModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the track client statistic persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TrackClientStatisticImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_TRACKCLIENTSTATISTIC = "SELECT trackClientStatistic FROM TrackClientStatistic trackClientStatistic";
	private static final String _SQL_SELECT_TRACKCLIENTSTATISTIC_WHERE_PKS_IN = "SELECT trackClientStatistic FROM TrackClientStatistic trackClientStatistic WHERE trackClientStatisticId IN (";
	private static final String _SQL_SELECT_TRACKCLIENTSTATISTIC_WHERE = "SELECT trackClientStatistic FROM TrackClientStatistic trackClientStatistic WHERE ";
	private static final String _SQL_COUNT_TRACKCLIENTSTATISTIC = "SELECT COUNT(trackClientStatistic) FROM TrackClientStatistic trackClientStatistic";
	private static final String _SQL_COUNT_TRACKCLIENTSTATISTIC_WHERE = "SELECT COUNT(trackClientStatistic) FROM TrackClientStatistic trackClientStatistic WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "trackClientStatistic.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrackClientStatistic exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrackClientStatistic exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TrackClientStatisticPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}