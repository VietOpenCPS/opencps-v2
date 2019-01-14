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

package org.opencps.statistic.service.persistence.impl;

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

import org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException;
import org.opencps.statistic.model.OpencpsPersonStatistic;
import org.opencps.statistic.model.impl.OpencpsPersonStatisticImpl;
import org.opencps.statistic.model.impl.OpencpsPersonStatisticModelImpl;
import org.opencps.statistic.service.persistence.OpencpsPersonStatisticPersistence;

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
 * The persistence implementation for the opencps person statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsPersonStatisticPersistence
 * @see org.opencps.statistic.service.persistence.OpencpsPersonStatisticUtil
 * @generated
 */
@ProviderType
public class OpencpsPersonStatisticPersistenceImpl extends BasePersistenceImpl<OpencpsPersonStatistic>
	implements OpencpsPersonStatisticPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OpencpsPersonStatisticUtil} to access the opencps person statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OpencpsPersonStatisticImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OpencpsPersonStatisticModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the opencps person statistics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps person statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @return the range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpencpsPersonStatistic> orderByComparator,
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

		List<OpencpsPersonStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsPersonStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsPersonStatistic opencpsPersonStatistic : list) {
					if (!Objects.equals(uuid, opencpsPersonStatistic.getUuid())) {
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

			query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

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
				query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps person statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByUuid_First(String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByUuid_First(uuid,
				orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps person statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByUuid_First(String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		List<OpencpsPersonStatistic> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByUuid_Last(String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByUuid_Last(uuid,
				orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByUuid_Last(String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OpencpsPersonStatistic> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where uuid = &#63;.
	 *
	 * @param personStatisticId the primary key of the current opencps person statistic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic[] findByUuid_PrevAndNext(
		long personStatisticId, String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = findByPrimaryKey(personStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsPersonStatistic[] array = new OpencpsPersonStatisticImpl[3];

			array[0] = getByUuid_PrevAndNext(session, opencpsPersonStatistic,
					uuid, orderByComparator, true);

			array[1] = opencpsPersonStatistic;

			array[2] = getByUuid_PrevAndNext(session, opencpsPersonStatistic,
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

	protected OpencpsPersonStatistic getByUuid_PrevAndNext(Session session,
		OpencpsPersonStatistic opencpsPersonStatistic, String uuid,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

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
			query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsPersonStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsPersonStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps person statistics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OpencpsPersonStatistic opencpsPersonStatistic : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsPersonStatistic);
		}
	}

	/**
	 * Returns the number of opencps person statistics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching opencps person statistics
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSPERSONSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "opencpsPersonStatistic.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "opencpsPersonStatistic.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(opencpsPersonStatistic.uuid IS NULL OR opencpsPersonStatistic.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OpencpsPersonStatisticModelImpl.UUID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the opencps person statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsPersonStatisticException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByUUID_G(uuid,
				groupId);

		if (opencpsPersonStatistic == null) {
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

			throw new NoSuchOpencpsPersonStatisticException(msg.toString());
		}

		return opencpsPersonStatistic;
	}

	/**
	 * Returns the opencps person statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the opencps person statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsPersonStatistic) {
			OpencpsPersonStatistic opencpsPersonStatistic = (OpencpsPersonStatistic)result;

			if (!Objects.equals(uuid, opencpsPersonStatistic.getUuid()) ||
					(groupId != opencpsPersonStatistic.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

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

				List<OpencpsPersonStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OpencpsPersonStatistic opencpsPersonStatistic = list.get(0);

					result = opencpsPersonStatistic;

					cacheResult(opencpsPersonStatistic);
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
			return (OpencpsPersonStatistic)result;
		}
	}

	/**
	 * Removes the opencps person statistic where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the opencps person statistic that was removed
	 */
	@Override
	public OpencpsPersonStatistic removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = findByUUID_G(uuid,
				groupId);

		return remove(opencpsPersonStatistic);
	}

	/**
	 * Returns the number of opencps person statistics where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching opencps person statistics
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSPERSONSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "opencpsPersonStatistic.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "opencpsPersonStatistic.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(opencpsPersonStatistic.uuid IS NULL OR opencpsPersonStatistic.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "opencpsPersonStatistic.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OpencpsPersonStatisticModelImpl.UUID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @return the range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
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

		List<OpencpsPersonStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsPersonStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsPersonStatistic opencpsPersonStatistic : list) {
					if (!Objects.equals(uuid, opencpsPersonStatistic.getUuid()) ||
							(companyId != opencpsPersonStatistic.getCompanyId())) {
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

			query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

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
				query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		List<OpencpsPersonStatistic> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OpencpsPersonStatistic> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param personStatisticId the primary key of the current opencps person statistic
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic[] findByUuid_C_PrevAndNext(
		long personStatisticId, String uuid, long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = findByPrimaryKey(personStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsPersonStatistic[] array = new OpencpsPersonStatisticImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, opencpsPersonStatistic,
					uuid, companyId, orderByComparator, true);

			array[1] = opencpsPersonStatistic;

			array[2] = getByUuid_C_PrevAndNext(session, opencpsPersonStatistic,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsPersonStatistic getByUuid_C_PrevAndNext(Session session,
		OpencpsPersonStatistic opencpsPersonStatistic, String uuid,
		long companyId,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

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
			query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsPersonStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsPersonStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps person statistics where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OpencpsPersonStatistic opencpsPersonStatistic : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsPersonStatistic);
		}
	}

	/**
	 * Returns the number of opencps person statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching opencps person statistics
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSPERSONSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "opencpsPersonStatistic.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "opencpsPersonStatistic.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(opencpsPersonStatistic.uuid IS NULL OR opencpsPersonStatistic.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "opencpsPersonStatistic.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_Y = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y =
		new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			OpencpsPersonStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.USERID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UID_Y = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year) {
		return findByG_UID_Y(groupId, userId, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @return the range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return findByG_UID_Y(groupId, userId, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y;
			finderArgs = new Object[] { groupId, userId, year };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_Y;
			finderArgs = new Object[] {
					groupId, userId, year,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsPersonStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsPersonStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsPersonStatistic opencpsPersonStatistic : list) {
					if ((groupId != opencpsPersonStatistic.getGroupId()) ||
							(userId != opencpsPersonStatistic.getUserId()) ||
							(year != opencpsPersonStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_UID_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_USERID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(year);

				if (!pagination) {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByG_UID_Y_First(groupId,
				userId, year, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		List<OpencpsPersonStatistic> list = findByG_UID_Y(groupId, userId,
				year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByG_UID_Y_Last(long groupId, long userId,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByG_UID_Y_Last(groupId,
				userId, year, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		int count = countByG_UID_Y(groupId, userId, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsPersonStatistic> list = findByG_UID_Y(groupId, userId,
				year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param personStatisticId the primary key of the current opencps person statistic
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic[] findByG_UID_Y_PrevAndNext(
		long personStatisticId, long groupId, long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = findByPrimaryKey(personStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsPersonStatistic[] array = new OpencpsPersonStatisticImpl[3];

			array[0] = getByG_UID_Y_PrevAndNext(session,
					opencpsPersonStatistic, groupId, userId, year,
					orderByComparator, true);

			array[1] = opencpsPersonStatistic;

			array[2] = getByG_UID_Y_PrevAndNext(session,
					opencpsPersonStatistic, groupId, userId, year,
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

	protected OpencpsPersonStatistic getByG_UID_Y_PrevAndNext(Session session,
		OpencpsPersonStatistic opencpsPersonStatistic, long groupId,
		long userId, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_G_UID_Y_GROUPID_2);

		query.append(_FINDER_COLUMN_G_UID_Y_USERID_2);

		query.append(_FINDER_COLUMN_G_UID_Y_YEAR_2);

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
			query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(year);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsPersonStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsPersonStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 */
	@Override
	public void removeByG_UID_Y(long groupId, long userId, int year) {
		for (OpencpsPersonStatistic opencpsPersonStatistic : findByG_UID_Y(
				groupId, userId, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(opencpsPersonStatistic);
		}
	}

	/**
	 * Returns the number of opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the number of matching opencps person statistics
	 */
	@Override
	public int countByG_UID_Y(long groupId, long userId, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UID_Y;

		Object[] finderArgs = new Object[] { groupId, userId, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_UID_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_USERID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(year);

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

	private static final String _FINDER_COLUMN_G_UID_Y_GROUPID_2 = "opencpsPersonStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_USERID_2 = "opencpsPersonStatistic.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_YEAR_2 = "opencpsPersonStatistic.year = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_M_Y_GOV_EMP = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByM_Y_GOV_EMP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				Long.class.getName()
			},
			OpencpsPersonStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.EMPLOYEEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_Y_GOV_EMP = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByM_Y_GOV_EMP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; or throws a {@link NoSuchOpencpsPersonStatisticException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param employeeId the employee ID
	 * @return the matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByM_Y_GOV_EMP(long groupId, int month,
		int year, String govAgencyCode, long employeeId)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByM_Y_GOV_EMP(groupId,
				month, year, govAgencyCode, employeeId);

		if (opencpsPersonStatistic == null) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", month=");
			msg.append(month);

			msg.append(", year=");
			msg.append(year);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append(", employeeId=");
			msg.append(employeeId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOpencpsPersonStatisticException(msg.toString());
		}

		return opencpsPersonStatistic;
	}

	/**
	 * Returns the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param employeeId the employee ID
	 * @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByM_Y_GOV_EMP(long groupId, int month,
		int year, String govAgencyCode, long employeeId) {
		return fetchByM_Y_GOV_EMP(groupId, month, year, govAgencyCode,
			employeeId, true);
	}

	/**
	 * Returns the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param employeeId the employee ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByM_Y_GOV_EMP(long groupId, int month,
		int year, String govAgencyCode, long employeeId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, employeeId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_M_Y_GOV_EMP,
					finderArgs, this);
		}

		if (result instanceof OpencpsPersonStatistic) {
			OpencpsPersonStatistic opencpsPersonStatistic = (OpencpsPersonStatistic)result;

			if ((groupId != opencpsPersonStatistic.getGroupId()) ||
					(month != opencpsPersonStatistic.getMonth()) ||
					(year != opencpsPersonStatistic.getYear()) ||
					!Objects.equals(govAgencyCode,
						opencpsPersonStatistic.getGovAgencyCode()) ||
					(employeeId != opencpsPersonStatistic.getEmployeeId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_GOV_EMP_GROUPID_2);

			query.append(_FINDER_COLUMN_M_Y_GOV_EMP_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_GOV_EMP_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_EMP_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_EMP_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_EMP_GOVAGENCYCODE_2);
			}

			query.append(_FINDER_COLUMN_M_Y_GOV_EMP_EMPLOYEEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				qPos.add(employeeId);

				List<OpencpsPersonStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_GOV_EMP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsPersonStatisticPersistenceImpl.fetchByM_Y_GOV_EMP(long, int, int, String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsPersonStatistic opencpsPersonStatistic = list.get(0);

					result = opencpsPersonStatistic;

					cacheResult(opencpsPersonStatistic);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_GOV_EMP,
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
			return (OpencpsPersonStatistic)result;
		}
	}

	/**
	 * Removes the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param employeeId the employee ID
	 * @return the opencps person statistic that was removed
	 */
	@Override
	public OpencpsPersonStatistic removeByM_Y_GOV_EMP(long groupId, int month,
		int year, String govAgencyCode, long employeeId)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = findByM_Y_GOV_EMP(groupId,
				month, year, govAgencyCode, employeeId);

		return remove(opencpsPersonStatistic);
	}

	/**
	 * Returns the number of opencps person statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param employeeId the employee ID
	 * @return the number of matching opencps person statistics
	 */
	@Override
	public int countByM_Y_GOV_EMP(long groupId, int month, int year,
		String govAgencyCode, long employeeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_Y_GOV_EMP;

		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, employeeId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_GOV_EMP_GROUPID_2);

			query.append(_FINDER_COLUMN_M_Y_GOV_EMP_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_GOV_EMP_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_EMP_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_EMP_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_EMP_GOVAGENCYCODE_2);
			}

			query.append(_FINDER_COLUMN_M_Y_GOV_EMP_EMPLOYEEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				qPos.add(employeeId);

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

	private static final String _FINDER_COLUMN_M_Y_GOV_EMP_GROUPID_2 = "opencpsPersonStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_EMP_MONTH_2 = "opencpsPersonStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_EMP_YEAR_2 = "opencpsPersonStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_EMP_GOVAGENCYCODE_1 = "opencpsPersonStatistic.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_EMP_GOVAGENCYCODE_2 = "opencpsPersonStatistic.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_EMP_GOVAGENCYCODE_3 = "(opencpsPersonStatistic.govAgencyCode IS NULL OR opencpsPersonStatistic.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_EMP_EMPLOYEEID_2 = "opencpsPersonStatistic.employeeId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_D_M_Y = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_D_M_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y =
		new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_D_M_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsPersonStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.EMPLOYEEID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_D_M_Y = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_D_M_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year) {
		return findByG_D_M_Y(groupId, employeeId, month, year,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @return the range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year, int start, int end) {
		return findByG_D_M_Y(groupId, employeeId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return findByG_D_M_Y(groupId, employeeId, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y;
			finderArgs = new Object[] { groupId, employeeId, month, year };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_D_M_Y;
			finderArgs = new Object[] {
					groupId, employeeId, month, year,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsPersonStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsPersonStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsPersonStatistic opencpsPersonStatistic : list) {
					if ((groupId != opencpsPersonStatistic.getGroupId()) ||
							(employeeId != opencpsPersonStatistic.getEmployeeId()) ||
							(month != opencpsPersonStatistic.getMonth()) ||
							(year != opencpsPersonStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_D_M_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_D_M_Y_EMPLOYEEID_2);

			query.append(_FINDER_COLUMN_G_D_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_D_M_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(employeeId);

				qPos.add(month);

				qPos.add(year);

				if (!pagination) {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByG_D_M_Y_First(long groupId,
		long employeeId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByG_D_M_Y_First(groupId,
				employeeId, month, year, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", employeeId=");
		msg.append(employeeId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByG_D_M_Y_First(long groupId,
		long employeeId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		List<OpencpsPersonStatistic> list = findByG_D_M_Y(groupId, employeeId,
				month, year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByG_D_M_Y_Last(long groupId,
		long employeeId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByG_D_M_Y_Last(groupId,
				employeeId, month, year, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", employeeId=");
		msg.append(employeeId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByG_D_M_Y_Last(long groupId,
		long employeeId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		int count = countByG_D_M_Y(groupId, employeeId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsPersonStatistic> list = findByG_D_M_Y(groupId, employeeId,
				month, year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param personStatisticId the primary key of the current opencps person statistic
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic[] findByG_D_M_Y_PrevAndNext(
		long personStatisticId, long groupId, long employeeId, int month,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = findByPrimaryKey(personStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsPersonStatistic[] array = new OpencpsPersonStatisticImpl[3];

			array[0] = getByG_D_M_Y_PrevAndNext(session,
					opencpsPersonStatistic, groupId, employeeId, month, year,
					orderByComparator, true);

			array[1] = opencpsPersonStatistic;

			array[2] = getByG_D_M_Y_PrevAndNext(session,
					opencpsPersonStatistic, groupId, employeeId, month, year,
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

	protected OpencpsPersonStatistic getByG_D_M_Y_PrevAndNext(Session session,
		OpencpsPersonStatistic opencpsPersonStatistic, long groupId,
		long employeeId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_G_D_M_Y_GROUPID_2);

		query.append(_FINDER_COLUMN_G_D_M_Y_EMPLOYEEID_2);

		query.append(_FINDER_COLUMN_G_D_M_Y_MONTH_2);

		query.append(_FINDER_COLUMN_G_D_M_Y_YEAR_2);

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
			query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(employeeId);

		qPos.add(month);

		qPos.add(year);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsPersonStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsPersonStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_D_M_Y(long groupId, long employeeId, int month,
		int year) {
		for (OpencpsPersonStatistic opencpsPersonStatistic : findByG_D_M_Y(
				groupId, employeeId, month, year, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsPersonStatistic);
		}
	}

	/**
	 * Returns the number of opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param employeeId the employee ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps person statistics
	 */
	@Override
	public int countByG_D_M_Y(long groupId, long employeeId, int month, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_D_M_Y;

		Object[] finderArgs = new Object[] { groupId, employeeId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_D_M_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_D_M_Y_EMPLOYEEID_2);

			query.append(_FINDER_COLUMN_G_D_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_D_M_Y_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(employeeId);

				qPos.add(month);

				qPos.add(year);

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

	private static final String _FINDER_COLUMN_G_D_M_Y_GROUPID_2 = "opencpsPersonStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_EMPLOYEEID_2 = "opencpsPersonStatistic.employeeId = ? AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_MONTH_2 = "opencpsPersonStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_YEAR_2 = "opencpsPersonStatistic.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M_Y = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_M_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_M_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			OpencpsPersonStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_M_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_M_Y(long groupId, int month,
		int year) {
		return findByG_M_Y(groupId, month, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @return the range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_M_Y(long groupId, int month,
		int year, int start, int end) {
		return findByG_M_Y(groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_M_Y(long groupId, int month,
		int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return findByG_M_Y(groupId, month, year, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByG_M_Y(long groupId, int month,
		int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y;
			finderArgs = new Object[] { groupId, month, year };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M_Y;
			finderArgs = new Object[] {
					groupId, month, year,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsPersonStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsPersonStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsPersonStatistic opencpsPersonStatistic : list) {
					if ((groupId != opencpsPersonStatistic.getGroupId()) ||
							(month != opencpsPersonStatistic.getMonth()) ||
							(year != opencpsPersonStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

				if (!pagination) {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByG_M_Y_First(long groupId, int month,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByG_M_Y_First(groupId,
				month, year, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByG_M_Y_First(long groupId, int month,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		List<OpencpsPersonStatistic> list = findByG_M_Y(groupId, month, year,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByG_M_Y_Last(long groupId, int month,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByG_M_Y_Last(groupId,
				month, year, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByG_M_Y_Last(long groupId, int month,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		int count = countByG_M_Y(groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsPersonStatistic> list = findByG_M_Y(groupId, month, year,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param personStatisticId the primary key of the current opencps person statistic
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic[] findByG_M_Y_PrevAndNext(
		long personStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = findByPrimaryKey(personStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsPersonStatistic[] array = new OpencpsPersonStatisticImpl[3];

			array[0] = getByG_M_Y_PrevAndNext(session, opencpsPersonStatistic,
					groupId, month, year, orderByComparator, true);

			array[1] = opencpsPersonStatistic;

			array[2] = getByG_M_Y_PrevAndNext(session, opencpsPersonStatistic,
					groupId, month, year, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsPersonStatistic getByG_M_Y_PrevAndNext(Session session,
		OpencpsPersonStatistic opencpsPersonStatistic, long groupId, int month,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_G_M_Y_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_Y_MONTH_2);

		query.append(_FINDER_COLUMN_G_M_Y_YEAR_2);

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
			query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(month);

		qPos.add(year);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsPersonStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsPersonStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_M_Y(long groupId, int month, int year) {
		for (OpencpsPersonStatistic opencpsPersonStatistic : findByG_M_Y(
				groupId, month, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsPersonStatistic);
		}
	}

	/**
	 * Returns the number of opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps person statistics
	 */
	@Override
	public int countByG_M_Y(long groupId, int month, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_M_Y;

		Object[] finderArgs = new Object[] { groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

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

	private static final String _FINDER_COLUMN_G_M_Y_GROUPID_2 = "opencpsPersonStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_MONTH_2 = "opencpsPersonStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_YEAR_2 = "opencpsPersonStatistic.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CID_GID_Y =
		new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCID_GID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y =
		new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCID_GID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsPersonStatisticModelImpl.COMPANYID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsPersonStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CID_GID_Y = new FinderPath(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCID_GID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year) {
		return findByCID_GID_Y(companyId, groupId, month, year,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @return the range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end) {
		return findByCID_GID_Y(companyId, groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return findByCID_GID_Y(companyId, groupId, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y;
			finderArgs = new Object[] { companyId, groupId, month, year };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CID_GID_Y;
			finderArgs = new Object[] {
					companyId, groupId, month, year,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsPersonStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsPersonStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsPersonStatistic opencpsPersonStatistic : list) {
					if ((companyId != opencpsPersonStatistic.getCompanyId()) ||
							(groupId != opencpsPersonStatistic.getGroupId()) ||
							(month != opencpsPersonStatistic.getMonth()) ||
							(year != opencpsPersonStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_CID_GID_Y_COMPANYID_2);

			query.append(_FINDER_COLUMN_CID_GID_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_CID_GID_Y_MONTH_2);

			query.append(_FINDER_COLUMN_CID_GID_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

				if (!pagination) {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByCID_GID_Y_First(companyId,
				groupId, month, year, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		List<OpencpsPersonStatistic> list = findByCID_GID_Y(companyId, groupId,
				month, year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByCID_GID_Y_Last(companyId,
				groupId, month, year, orderByComparator);

		if (opencpsPersonStatistic != null) {
			return opencpsPersonStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsPersonStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		int count = countByCID_GID_Y(companyId, groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsPersonStatistic> list = findByCID_GID_Y(companyId, groupId,
				month, year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param personStatisticId the primary key of the current opencps person statistic
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic[] findByCID_GID_Y_PrevAndNext(
		long personStatisticId, long companyId, long groupId, int month,
		int year, OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = findByPrimaryKey(personStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsPersonStatistic[] array = new OpencpsPersonStatisticImpl[3];

			array[0] = getByCID_GID_Y_PrevAndNext(session,
					opencpsPersonStatistic, companyId, groupId, month, year,
					orderByComparator, true);

			array[1] = opencpsPersonStatistic;

			array[2] = getByCID_GID_Y_PrevAndNext(session,
					opencpsPersonStatistic, companyId, groupId, month, year,
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

	protected OpencpsPersonStatistic getByCID_GID_Y_PrevAndNext(
		Session session, OpencpsPersonStatistic opencpsPersonStatistic,
		long companyId, long groupId, int month, int year,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_CID_GID_Y_COMPANYID_2);

		query.append(_FINDER_COLUMN_CID_GID_Y_GROUPID_2);

		query.append(_FINDER_COLUMN_CID_GID_Y_MONTH_2);

		query.append(_FINDER_COLUMN_CID_GID_Y_YEAR_2);

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
			query.append(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		qPos.add(month);

		qPos.add(year);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsPersonStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsPersonStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByCID_GID_Y(long companyId, long groupId, int month,
		int year) {
		for (OpencpsPersonStatistic opencpsPersonStatistic : findByCID_GID_Y(
				companyId, groupId, month, year, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsPersonStatistic);
		}
	}

	/**
	 * Returns the number of opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps person statistics
	 */
	@Override
	public int countByCID_GID_Y(long companyId, long groupId, int month,
		int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CID_GID_Y;

		Object[] finderArgs = new Object[] { companyId, groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSPERSONSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_CID_GID_Y_COMPANYID_2);

			query.append(_FINDER_COLUMN_CID_GID_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_CID_GID_Y_MONTH_2);

			query.append(_FINDER_COLUMN_CID_GID_Y_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

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

	private static final String _FINDER_COLUMN_CID_GID_Y_COMPANYID_2 = "opencpsPersonStatistic.companyId = ? AND ";
	private static final String _FINDER_COLUMN_CID_GID_Y_GROUPID_2 = "opencpsPersonStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_CID_GID_Y_MONTH_2 = "opencpsPersonStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_CID_GID_Y_YEAR_2 = "opencpsPersonStatistic.year = ?";

	public OpencpsPersonStatisticPersistenceImpl() {
		setModelClass(OpencpsPersonStatistic.class);

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
	 * Caches the opencps person statistic in the entity cache if it is enabled.
	 *
	 * @param opencpsPersonStatistic the opencps person statistic
	 */
	@Override
	public void cacheResult(OpencpsPersonStatistic opencpsPersonStatistic) {
		entityCache.putResult(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			opencpsPersonStatistic.getPrimaryKey(), opencpsPersonStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				opencpsPersonStatistic.getUuid(),
				opencpsPersonStatistic.getGroupId()
			}, opencpsPersonStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_GOV_EMP,
			new Object[] {
				opencpsPersonStatistic.getGroupId(),
				opencpsPersonStatistic.getMonth(),
				opencpsPersonStatistic.getYear(),
				opencpsPersonStatistic.getGovAgencyCode(),
				opencpsPersonStatistic.getEmployeeId()
			}, opencpsPersonStatistic);

		opencpsPersonStatistic.resetOriginalValues();
	}

	/**
	 * Caches the opencps person statistics in the entity cache if it is enabled.
	 *
	 * @param opencpsPersonStatistics the opencps person statistics
	 */
	@Override
	public void cacheResult(
		List<OpencpsPersonStatistic> opencpsPersonStatistics) {
		for (OpencpsPersonStatistic opencpsPersonStatistic : opencpsPersonStatistics) {
			if (entityCache.getResult(
						OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsPersonStatisticImpl.class,
						opencpsPersonStatistic.getPrimaryKey()) == null) {
				cacheResult(opencpsPersonStatistic);
			}
			else {
				opencpsPersonStatistic.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all opencps person statistics.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OpencpsPersonStatisticImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the opencps person statistic.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OpencpsPersonStatistic opencpsPersonStatistic) {
		entityCache.removeResult(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			opencpsPersonStatistic.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OpencpsPersonStatisticModelImpl)opencpsPersonStatistic,
			true);
	}

	@Override
	public void clearCache(List<OpencpsPersonStatistic> opencpsPersonStatistics) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OpencpsPersonStatistic opencpsPersonStatistic : opencpsPersonStatistics) {
			entityCache.removeResult(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsPersonStatisticImpl.class,
				opencpsPersonStatistic.getPrimaryKey());

			clearUniqueFindersCache((OpencpsPersonStatisticModelImpl)opencpsPersonStatistic,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OpencpsPersonStatisticModelImpl opencpsPersonStatisticModelImpl) {
		Object[] args = new Object[] {
				opencpsPersonStatisticModelImpl.getUuid(),
				opencpsPersonStatisticModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			opencpsPersonStatisticModelImpl, false);

		args = new Object[] {
				opencpsPersonStatisticModelImpl.getGroupId(),
				opencpsPersonStatisticModelImpl.getMonth(),
				opencpsPersonStatisticModelImpl.getYear(),
				opencpsPersonStatisticModelImpl.getGovAgencyCode(),
				opencpsPersonStatisticModelImpl.getEmployeeId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_M_Y_GOV_EMP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_GOV_EMP, args,
			opencpsPersonStatisticModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OpencpsPersonStatisticModelImpl opencpsPersonStatisticModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsPersonStatisticModelImpl.getUuid(),
					opencpsPersonStatisticModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((opencpsPersonStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsPersonStatisticModelImpl.getOriginalUuid(),
					opencpsPersonStatisticModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsPersonStatisticModelImpl.getGroupId(),
					opencpsPersonStatisticModelImpl.getMonth(),
					opencpsPersonStatisticModelImpl.getYear(),
					opencpsPersonStatisticModelImpl.getGovAgencyCode(),
					opencpsPersonStatisticModelImpl.getEmployeeId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_GOV_EMP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_GOV_EMP, args);
		}

		if ((opencpsPersonStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_M_Y_GOV_EMP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsPersonStatisticModelImpl.getOriginalGroupId(),
					opencpsPersonStatisticModelImpl.getOriginalMonth(),
					opencpsPersonStatisticModelImpl.getOriginalYear(),
					opencpsPersonStatisticModelImpl.getOriginalGovAgencyCode(),
					opencpsPersonStatisticModelImpl.getOriginalEmployeeId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_GOV_EMP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_GOV_EMP, args);
		}
	}

	/**
	 * Creates a new opencps person statistic with the primary key. Does not add the opencps person statistic to the database.
	 *
	 * @param personStatisticId the primary key for the new opencps person statistic
	 * @return the new opencps person statistic
	 */
	@Override
	public OpencpsPersonStatistic create(long personStatisticId) {
		OpencpsPersonStatistic opencpsPersonStatistic = new OpencpsPersonStatisticImpl();

		opencpsPersonStatistic.setNew(true);
		opencpsPersonStatistic.setPrimaryKey(personStatisticId);

		String uuid = PortalUUIDUtil.generate();

		opencpsPersonStatistic.setUuid(uuid);

		opencpsPersonStatistic.setCompanyId(companyProvider.getCompanyId());

		return opencpsPersonStatistic;
	}

	/**
	 * Removes the opencps person statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param personStatisticId the primary key of the opencps person statistic
	 * @return the opencps person statistic that was removed
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic remove(long personStatisticId)
		throws NoSuchOpencpsPersonStatisticException {
		return remove((Serializable)personStatisticId);
	}

	/**
	 * Removes the opencps person statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the opencps person statistic
	 * @return the opencps person statistic that was removed
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic remove(Serializable primaryKey)
		throws NoSuchOpencpsPersonStatisticException {
		Session session = null;

		try {
			session = openSession();

			OpencpsPersonStatistic opencpsPersonStatistic = (OpencpsPersonStatistic)session.get(OpencpsPersonStatisticImpl.class,
					primaryKey);

			if (opencpsPersonStatistic == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOpencpsPersonStatisticException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(opencpsPersonStatistic);
		}
		catch (NoSuchOpencpsPersonStatisticException nsee) {
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
	protected OpencpsPersonStatistic removeImpl(
		OpencpsPersonStatistic opencpsPersonStatistic) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(opencpsPersonStatistic)) {
				opencpsPersonStatistic = (OpencpsPersonStatistic)session.get(OpencpsPersonStatisticImpl.class,
						opencpsPersonStatistic.getPrimaryKeyObj());
			}

			if (opencpsPersonStatistic != null) {
				session.delete(opencpsPersonStatistic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (opencpsPersonStatistic != null) {
			clearCache(opencpsPersonStatistic);
		}

		return opencpsPersonStatistic;
	}

	@Override
	public OpencpsPersonStatistic updateImpl(
		OpencpsPersonStatistic opencpsPersonStatistic) {
		boolean isNew = opencpsPersonStatistic.isNew();

		if (!(opencpsPersonStatistic instanceof OpencpsPersonStatisticModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(opencpsPersonStatistic.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(opencpsPersonStatistic);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in opencpsPersonStatistic proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OpencpsPersonStatistic implementation " +
				opencpsPersonStatistic.getClass());
		}

		OpencpsPersonStatisticModelImpl opencpsPersonStatisticModelImpl = (OpencpsPersonStatisticModelImpl)opencpsPersonStatistic;

		if (Validator.isNull(opencpsPersonStatistic.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			opencpsPersonStatistic.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (opencpsPersonStatistic.getCreateDate() == null)) {
			if (serviceContext == null) {
				opencpsPersonStatistic.setCreateDate(now);
			}
			else {
				opencpsPersonStatistic.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!opencpsPersonStatisticModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				opencpsPersonStatistic.setModifiedDate(now);
			}
			else {
				opencpsPersonStatistic.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (opencpsPersonStatistic.isNew()) {
				session.save(opencpsPersonStatistic);

				opencpsPersonStatistic.setNew(false);
			}
			else {
				opencpsPersonStatistic = (OpencpsPersonStatistic)session.merge(opencpsPersonStatistic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OpencpsPersonStatisticModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					opencpsPersonStatisticModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					opencpsPersonStatisticModelImpl.getUuid(),
					opencpsPersonStatisticModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					opencpsPersonStatisticModelImpl.getGroupId(),
					opencpsPersonStatisticModelImpl.getUserId(),
					opencpsPersonStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
				args);

			args = new Object[] {
					opencpsPersonStatisticModelImpl.getGroupId(),
					opencpsPersonStatisticModelImpl.getEmployeeId(),
					opencpsPersonStatisticModelImpl.getMonth(),
					opencpsPersonStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_D_M_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y,
				args);

			args = new Object[] {
					opencpsPersonStatisticModelImpl.getGroupId(),
					opencpsPersonStatisticModelImpl.getMonth(),
					opencpsPersonStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y,
				args);

			args = new Object[] {
					opencpsPersonStatisticModelImpl.getCompanyId(),
					opencpsPersonStatisticModelImpl.getGroupId(),
					opencpsPersonStatisticModelImpl.getMonth(),
					opencpsPersonStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CID_GID_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((opencpsPersonStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsPersonStatisticModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { opencpsPersonStatisticModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((opencpsPersonStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsPersonStatisticModelImpl.getOriginalUuid(),
						opencpsPersonStatisticModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						opencpsPersonStatisticModelImpl.getUuid(),
						opencpsPersonStatisticModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((opencpsPersonStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsPersonStatisticModelImpl.getOriginalGroupId(),
						opencpsPersonStatisticModelImpl.getOriginalUserId(),
						opencpsPersonStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);

				args = new Object[] {
						opencpsPersonStatisticModelImpl.getGroupId(),
						opencpsPersonStatisticModelImpl.getUserId(),
						opencpsPersonStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);
			}

			if ((opencpsPersonStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsPersonStatisticModelImpl.getOriginalGroupId(),
						opencpsPersonStatisticModelImpl.getOriginalEmployeeId(),
						opencpsPersonStatisticModelImpl.getOriginalMonth(),
						opencpsPersonStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_D_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y,
					args);

				args = new Object[] {
						opencpsPersonStatisticModelImpl.getGroupId(),
						opencpsPersonStatisticModelImpl.getEmployeeId(),
						opencpsPersonStatisticModelImpl.getMonth(),
						opencpsPersonStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_D_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y,
					args);
			}

			if ((opencpsPersonStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsPersonStatisticModelImpl.getOriginalGroupId(),
						opencpsPersonStatisticModelImpl.getOriginalMonth(),
						opencpsPersonStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y,
					args);

				args = new Object[] {
						opencpsPersonStatisticModelImpl.getGroupId(),
						opencpsPersonStatisticModelImpl.getMonth(),
						opencpsPersonStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y,
					args);
			}

			if ((opencpsPersonStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsPersonStatisticModelImpl.getOriginalCompanyId(),
						opencpsPersonStatisticModelImpl.getOriginalGroupId(),
						opencpsPersonStatisticModelImpl.getOriginalMonth(),
						opencpsPersonStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CID_GID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y,
					args);

				args = new Object[] {
						opencpsPersonStatisticModelImpl.getCompanyId(),
						opencpsPersonStatisticModelImpl.getGroupId(),
						opencpsPersonStatisticModelImpl.getMonth(),
						opencpsPersonStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CID_GID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y,
					args);
			}
		}

		entityCache.putResult(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsPersonStatisticImpl.class,
			opencpsPersonStatistic.getPrimaryKey(), opencpsPersonStatistic,
			false);

		clearUniqueFindersCache(opencpsPersonStatisticModelImpl, false);
		cacheUniqueFindersCache(opencpsPersonStatisticModelImpl);

		opencpsPersonStatistic.resetOriginalValues();

		return opencpsPersonStatistic;
	}

	/**
	 * Returns the opencps person statistic with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps person statistic
	 * @return the opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOpencpsPersonStatisticException {
		OpencpsPersonStatistic opencpsPersonStatistic = fetchByPrimaryKey(primaryKey);

		if (opencpsPersonStatistic == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOpencpsPersonStatisticException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return opencpsPersonStatistic;
	}

	/**
	 * Returns the opencps person statistic with the primary key or throws a {@link NoSuchOpencpsPersonStatisticException} if it could not be found.
	 *
	 * @param personStatisticId the primary key of the opencps person statistic
	 * @return the opencps person statistic
	 * @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic findByPrimaryKey(long personStatisticId)
		throws NoSuchOpencpsPersonStatisticException {
		return findByPrimaryKey((Serializable)personStatisticId);
	}

	/**
	 * Returns the opencps person statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps person statistic
	 * @return the opencps person statistic, or <code>null</code> if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsPersonStatisticImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OpencpsPersonStatistic opencpsPersonStatistic = (OpencpsPersonStatistic)serializable;

		if (opencpsPersonStatistic == null) {
			Session session = null;

			try {
				session = openSession();

				opencpsPersonStatistic = (OpencpsPersonStatistic)session.get(OpencpsPersonStatisticImpl.class,
						primaryKey);

				if (opencpsPersonStatistic != null) {
					cacheResult(opencpsPersonStatistic);
				}
				else {
					entityCache.putResult(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsPersonStatisticImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsPersonStatisticImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return opencpsPersonStatistic;
	}

	/**
	 * Returns the opencps person statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param personStatisticId the primary key of the opencps person statistic
	 * @return the opencps person statistic, or <code>null</code> if a opencps person statistic with the primary key could not be found
	 */
	@Override
	public OpencpsPersonStatistic fetchByPrimaryKey(long personStatisticId) {
		return fetchByPrimaryKey((Serializable)personStatisticId);
	}

	@Override
	public Map<Serializable, OpencpsPersonStatistic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OpencpsPersonStatistic> map = new HashMap<Serializable, OpencpsPersonStatistic>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OpencpsPersonStatistic opencpsPersonStatistic = fetchByPrimaryKey(primaryKey);

			if (opencpsPersonStatistic != null) {
				map.put(primaryKey, opencpsPersonStatistic);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsPersonStatisticImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OpencpsPersonStatistic)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE_PKS_IN);

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

			for (OpencpsPersonStatistic opencpsPersonStatistic : (List<OpencpsPersonStatistic>)q.list()) {
				map.put(opencpsPersonStatistic.getPrimaryKeyObj(),
					opencpsPersonStatistic);

				cacheResult(opencpsPersonStatistic);

				uncachedPrimaryKeys.remove(opencpsPersonStatistic.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OpencpsPersonStatisticModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsPersonStatisticImpl.class, primaryKey, nullModel);
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
	 * Returns all the opencps person statistics.
	 *
	 * @return the opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps person statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @return the range of opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps person statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps person statistics
	 * @param end the upper bound of the range of opencps person statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of opencps person statistics
	 */
	@Override
	public List<OpencpsPersonStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsPersonStatistic> orderByComparator,
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

		List<OpencpsPersonStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsPersonStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPENCPSPERSONSTATISTIC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPENCPSPERSONSTATISTIC;

				if (pagination) {
					sql = sql.concat(OpencpsPersonStatisticModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsPersonStatistic>)QueryUtil.list(q,
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
	 * Removes all the opencps person statistics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OpencpsPersonStatistic opencpsPersonStatistic : findAll()) {
			remove(opencpsPersonStatistic);
		}
	}

	/**
	 * Returns the number of opencps person statistics.
	 *
	 * @return the number of opencps person statistics
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPENCPSPERSONSTATISTIC);

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
		return OpencpsPersonStatisticModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the opencps person statistic persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OpencpsPersonStatisticImpl.class.getName());
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
	private static final String _SQL_SELECT_OPENCPSPERSONSTATISTIC = "SELECT opencpsPersonStatistic FROM OpencpsPersonStatistic opencpsPersonStatistic";
	private static final String _SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE_PKS_IN = "SELECT opencpsPersonStatistic FROM OpencpsPersonStatistic opencpsPersonStatistic WHERE personStatisticId IN (";
	private static final String _SQL_SELECT_OPENCPSPERSONSTATISTIC_WHERE = "SELECT opencpsPersonStatistic FROM OpencpsPersonStatistic opencpsPersonStatistic WHERE ";
	private static final String _SQL_COUNT_OPENCPSPERSONSTATISTIC = "SELECT COUNT(opencpsPersonStatistic) FROM OpencpsPersonStatistic opencpsPersonStatistic";
	private static final String _SQL_COUNT_OPENCPSPERSONSTATISTIC_WHERE = "SELECT COUNT(opencpsPersonStatistic) FROM OpencpsPersonStatistic opencpsPersonStatistic WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "opencpsPersonStatistic.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpencpsPersonStatistic exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpencpsPersonStatistic exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OpencpsPersonStatisticPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}