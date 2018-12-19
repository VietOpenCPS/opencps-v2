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

import org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.model.impl.OpencpsVotingStatisticImpl;
import org.opencps.statistic.model.impl.OpencpsVotingStatisticModelImpl;
import org.opencps.statistic.service.persistence.OpencpsVotingStatisticPersistence;

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
 * The persistence implementation for the opencps voting statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsVotingStatisticPersistence
 * @see org.opencps.statistic.service.persistence.OpencpsVotingStatisticUtil
 * @generated
 */
@ProviderType
public class OpencpsVotingStatisticPersistenceImpl extends BasePersistenceImpl<OpencpsVotingStatistic>
	implements OpencpsVotingStatisticPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OpencpsVotingStatisticUtil} to access the opencps voting statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OpencpsVotingStatisticImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OpencpsVotingStatisticModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the opencps voting statistics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps voting statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @return the range of matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps voting statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps voting statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpencpsVotingStatistic> orderByComparator,
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

		List<OpencpsVotingStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsVotingStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsVotingStatistic opencpsVotingStatistic : list) {
					if (!Objects.equals(uuid, opencpsVotingStatistic.getUuid())) {
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

			query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE);

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
				query.append(OpencpsVotingStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsVotingStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsVotingStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps voting statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByUuid_First(String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = fetchByUuid_First(uuid,
				orderByComparator);

		if (opencpsVotingStatistic != null) {
			return opencpsVotingStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsVotingStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps voting statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByUuid_First(String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		List<OpencpsVotingStatistic> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps voting statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByUuid_Last(String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = fetchByUuid_Last(uuid,
				orderByComparator);

		if (opencpsVotingStatistic != null) {
			return opencpsVotingStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsVotingStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps voting statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByUuid_Last(String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OpencpsVotingStatistic> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where uuid = &#63;.
	 *
	 * @param votingStatisticId the primary key of the current opencps voting statistic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	 */
	@Override
	public OpencpsVotingStatistic[] findByUuid_PrevAndNext(
		long votingStatisticId, String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = findByPrimaryKey(votingStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsVotingStatistic[] array = new OpencpsVotingStatisticImpl[3];

			array[0] = getByUuid_PrevAndNext(session, opencpsVotingStatistic,
					uuid, orderByComparator, true);

			array[1] = opencpsVotingStatistic;

			array[2] = getByUuid_PrevAndNext(session, opencpsVotingStatistic,
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

	protected OpencpsVotingStatistic getByUuid_PrevAndNext(Session session,
		OpencpsVotingStatistic opencpsVotingStatistic, String uuid,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE);

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
			query.append(OpencpsVotingStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsVotingStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsVotingStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps voting statistics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OpencpsVotingStatistic opencpsVotingStatistic : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsVotingStatistic);
		}
	}

	/**
	 * Returns the number of opencps voting statistics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching opencps voting statistics
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSVOTINGSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "opencpsVotingStatistic.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "opencpsVotingStatistic.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(opencpsVotingStatistic.uuid IS NULL OR opencpsVotingStatistic.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OpencpsVotingStatisticModelImpl.UUID_COLUMN_BITMASK |
			OpencpsVotingStatisticModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the opencps voting statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsVotingStatisticException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = fetchByUUID_G(uuid,
				groupId);

		if (opencpsVotingStatistic == null) {
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

			throw new NoSuchOpencpsVotingStatisticException(msg.toString());
		}

		return opencpsVotingStatistic;
	}

	/**
	 * Returns the opencps voting statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the opencps voting statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsVotingStatistic) {
			OpencpsVotingStatistic opencpsVotingStatistic = (OpencpsVotingStatistic)result;

			if (!Objects.equals(uuid, opencpsVotingStatistic.getUuid()) ||
					(groupId != opencpsVotingStatistic.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE);

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

				List<OpencpsVotingStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OpencpsVotingStatistic opencpsVotingStatistic = list.get(0);

					result = opencpsVotingStatistic;

					cacheResult(opencpsVotingStatistic);
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
			return (OpencpsVotingStatistic)result;
		}
	}

	/**
	 * Removes the opencps voting statistic where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the opencps voting statistic that was removed
	 */
	@Override
	public OpencpsVotingStatistic removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = findByUUID_G(uuid,
				groupId);

		return remove(opencpsVotingStatistic);
	}

	/**
	 * Returns the number of opencps voting statistics where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching opencps voting statistics
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSVOTINGSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "opencpsVotingStatistic.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "opencpsVotingStatistic.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(opencpsVotingStatistic.uuid IS NULL OR opencpsVotingStatistic.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "opencpsVotingStatistic.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OpencpsVotingStatisticModelImpl.UUID_COLUMN_BITMASK |
			OpencpsVotingStatisticModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @return the range of matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
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

		List<OpencpsVotingStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsVotingStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsVotingStatistic opencpsVotingStatistic : list) {
					if (!Objects.equals(uuid, opencpsVotingStatistic.getUuid()) ||
							(companyId != opencpsVotingStatistic.getCompanyId())) {
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

			query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE);

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
				query.append(OpencpsVotingStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsVotingStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsVotingStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (opencpsVotingStatistic != null) {
			return opencpsVotingStatistic;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpencpsVotingStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		List<OpencpsVotingStatistic> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (opencpsVotingStatistic != null) {
			return opencpsVotingStatistic;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpencpsVotingStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OpencpsVotingStatistic> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param votingStatisticId the primary key of the current opencps voting statistic
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	 */
	@Override
	public OpencpsVotingStatistic[] findByUuid_C_PrevAndNext(
		long votingStatisticId, String uuid, long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = findByPrimaryKey(votingStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsVotingStatistic[] array = new OpencpsVotingStatisticImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, opencpsVotingStatistic,
					uuid, companyId, orderByComparator, true);

			array[1] = opencpsVotingStatistic;

			array[2] = getByUuid_C_PrevAndNext(session, opencpsVotingStatistic,
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

	protected OpencpsVotingStatistic getByUuid_C_PrevAndNext(Session session,
		OpencpsVotingStatistic opencpsVotingStatistic, String uuid,
		long companyId,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE);

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
			query.append(OpencpsVotingStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsVotingStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsVotingStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps voting statistics where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OpencpsVotingStatistic opencpsVotingStatistic : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsVotingStatistic);
		}
	}

	/**
	 * Returns the number of opencps voting statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching opencps voting statistics
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSVOTINGSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "opencpsVotingStatistic.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "opencpsVotingStatistic.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(opencpsVotingStatistic.uuid IS NULL OR opencpsVotingStatistic.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "opencpsVotingStatistic.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_Y = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y =
		new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			OpencpsVotingStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsVotingStatisticModelImpl.USERID_COLUMN_BITMASK |
			OpencpsVotingStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UID_Y = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year) {
		return findByG_UID_Y(groupId, userId, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @return the range of matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return findByG_UID_Y(groupId, userId, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
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

		List<OpencpsVotingStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsVotingStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsVotingStatistic opencpsVotingStatistic : list) {
					if ((groupId != opencpsVotingStatistic.getGroupId()) ||
							(userId != opencpsVotingStatistic.getUserId()) ||
							(year != opencpsVotingStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_UID_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_USERID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsVotingStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsVotingStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsVotingStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = fetchByG_UID_Y_First(groupId,
				userId, year, orderByComparator);

		if (opencpsVotingStatistic != null) {
			return opencpsVotingStatistic;
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

		throw new NoSuchOpencpsVotingStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		List<OpencpsVotingStatistic> list = findByG_UID_Y(groupId, userId,
				year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByG_UID_Y_Last(long groupId, long userId,
		int year, OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = fetchByG_UID_Y_Last(groupId,
				userId, year, orderByComparator);

		if (opencpsVotingStatistic != null) {
			return opencpsVotingStatistic;
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

		throw new NoSuchOpencpsVotingStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		int count = countByG_UID_Y(groupId, userId, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsVotingStatistic> list = findByG_UID_Y(groupId, userId,
				year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param votingStatisticId the primary key of the current opencps voting statistic
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	 */
	@Override
	public OpencpsVotingStatistic[] findByG_UID_Y_PrevAndNext(
		long votingStatisticId, long groupId, long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = findByPrimaryKey(votingStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsVotingStatistic[] array = new OpencpsVotingStatisticImpl[3];

			array[0] = getByG_UID_Y_PrevAndNext(session,
					opencpsVotingStatistic, groupId, userId, year,
					orderByComparator, true);

			array[1] = opencpsVotingStatistic;

			array[2] = getByG_UID_Y_PrevAndNext(session,
					opencpsVotingStatistic, groupId, userId, year,
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

	protected OpencpsVotingStatistic getByG_UID_Y_PrevAndNext(Session session,
		OpencpsVotingStatistic opencpsVotingStatistic, long groupId,
		long userId, int year,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE);

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
			query.append(OpencpsVotingStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsVotingStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsVotingStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 */
	@Override
	public void removeByG_UID_Y(long groupId, long userId, int year) {
		for (OpencpsVotingStatistic opencpsVotingStatistic : findByG_UID_Y(
				groupId, userId, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(opencpsVotingStatistic);
		}
	}

	/**
	 * Returns the number of opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the number of matching opencps voting statistics
	 */
	@Override
	public int countByG_UID_Y(long groupId, long userId, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UID_Y;

		Object[] finderArgs = new Object[] { groupId, userId, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSVOTINGSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_G_UID_Y_GROUPID_2 = "opencpsVotingStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_USERID_2 = "opencpsVotingStatistic.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_YEAR_2 = "opencpsVotingStatistic.year = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_M_Y_DM_G = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByM_Y_DM_G",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			},
			OpencpsVotingStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsVotingStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsVotingStatisticModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsVotingStatisticModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsVotingStatisticModelImpl.SERVICECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_Y_DM_G = new FinderPath(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByM_Y_DM_G",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and serviceCode = &#63; or throws a {@link NoSuchOpencpsVotingStatisticException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @return the matching opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByM_Y_DM_G(long groupId, int month,
		int year, String govAgencyCode, String serviceCode)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = fetchByM_Y_DM_G(groupId,
				month, year, govAgencyCode, serviceCode);

		if (opencpsVotingStatistic == null) {
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

			msg.append(", serviceCode=");
			msg.append(serviceCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOpencpsVotingStatisticException(msg.toString());
		}

		return opencpsVotingStatistic;
	}

	/**
	 * Returns the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByM_Y_DM_G(long groupId, int month,
		int year, String govAgencyCode, String serviceCode) {
		return fetchByM_Y_DM_G(groupId, month, year, govAgencyCode,
			serviceCode, true);
	}

	/**
	 * Returns the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByM_Y_DM_G(long groupId, int month,
		int year, String govAgencyCode, String serviceCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, serviceCode
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsVotingStatistic) {
			OpencpsVotingStatistic opencpsVotingStatistic = (OpencpsVotingStatistic)result;

			if ((groupId != opencpsVotingStatistic.getGroupId()) ||
					(month != opencpsVotingStatistic.getMonth()) ||
					(year != opencpsVotingStatistic.getYear()) ||
					!Objects.equals(govAgencyCode,
						opencpsVotingStatistic.getGovAgencyCode()) ||
					!Objects.equals(serviceCode,
						opencpsVotingStatistic.getServiceCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_DM_G_GROUPID_2);

			query.append(_FINDER_COLUMN_M_Y_DM_G_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_DM_G_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_M_Y_DM_G_SERVICECODE_2);
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

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				List<OpencpsVotingStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsVotingStatisticPersistenceImpl.fetchByM_Y_DM_G(long, int, int, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsVotingStatistic opencpsVotingStatistic = list.get(0);

					result = opencpsVotingStatistic;

					cacheResult(opencpsVotingStatistic);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
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
			return (OpencpsVotingStatistic)result;
		}
	}

	/**
	 * Removes the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and serviceCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @return the opencps voting statistic that was removed
	 */
	@Override
	public OpencpsVotingStatistic removeByM_Y_DM_G(long groupId, int month,
		int year, String govAgencyCode, String serviceCode)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = findByM_Y_DM_G(groupId,
				month, year, govAgencyCode, serviceCode);

		return remove(opencpsVotingStatistic);
	}

	/**
	 * Returns the number of opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and serviceCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param serviceCode the service code
	 * @return the number of matching opencps voting statistics
	 */
	@Override
	public int countByM_Y_DM_G(long groupId, int month, int year,
		String govAgencyCode, String serviceCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_Y_DM_G;

		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, serviceCode
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_OPENCPSVOTINGSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_DM_G_GROUPID_2);

			query.append(_FINDER_COLUMN_M_Y_DM_G_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_DM_G_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_2);
			}

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_M_Y_DM_G_SERVICECODE_2);
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

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
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

	private static final String _FINDER_COLUMN_M_Y_DM_G_GROUPID_2 = "opencpsVotingStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_MONTH_2 = "opencpsVotingStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_YEAR_2 = "opencpsVotingStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_1 = "opencpsVotingStatistic.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_2 = "opencpsVotingStatistic.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_3 = "(opencpsVotingStatistic.govAgencyCode IS NULL OR opencpsVotingStatistic.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_SERVICECODE_1 = "opencpsVotingStatistic.serviceCode IS NULL";
	private static final String _FINDER_COLUMN_M_Y_DM_G_SERVICECODE_2 = "opencpsVotingStatistic.serviceCode = ?";
	private static final String _FINDER_COLUMN_M_Y_DM_G_SERVICECODE_3 = "(opencpsVotingStatistic.serviceCode IS NULL OR opencpsVotingStatistic.serviceCode = '')";

	public OpencpsVotingStatisticPersistenceImpl() {
		setModelClass(OpencpsVotingStatistic.class);

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
	 * Caches the opencps voting statistic in the entity cache if it is enabled.
	 *
	 * @param opencpsVotingStatistic the opencps voting statistic
	 */
	@Override
	public void cacheResult(OpencpsVotingStatistic opencpsVotingStatistic) {
		entityCache.putResult(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			opencpsVotingStatistic.getPrimaryKey(), opencpsVotingStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				opencpsVotingStatistic.getUuid(),
				opencpsVotingStatistic.getGroupId()
			}, opencpsVotingStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
			new Object[] {
				opencpsVotingStatistic.getGroupId(),
				opencpsVotingStatistic.getMonth(),
				opencpsVotingStatistic.getYear(),
				opencpsVotingStatistic.getGovAgencyCode(),
				opencpsVotingStatistic.getServiceCode()
			}, opencpsVotingStatistic);

		opencpsVotingStatistic.resetOriginalValues();
	}

	/**
	 * Caches the opencps voting statistics in the entity cache if it is enabled.
	 *
	 * @param opencpsVotingStatistics the opencps voting statistics
	 */
	@Override
	public void cacheResult(
		List<OpencpsVotingStatistic> opencpsVotingStatistics) {
		for (OpencpsVotingStatistic opencpsVotingStatistic : opencpsVotingStatistics) {
			if (entityCache.getResult(
						OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsVotingStatisticImpl.class,
						opencpsVotingStatistic.getPrimaryKey()) == null) {
				cacheResult(opencpsVotingStatistic);
			}
			else {
				opencpsVotingStatistic.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all opencps voting statistics.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OpencpsVotingStatisticImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the opencps voting statistic.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OpencpsVotingStatistic opencpsVotingStatistic) {
		entityCache.removeResult(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			opencpsVotingStatistic.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OpencpsVotingStatisticModelImpl)opencpsVotingStatistic,
			true);
	}

	@Override
	public void clearCache(List<OpencpsVotingStatistic> opencpsVotingStatistics) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OpencpsVotingStatistic opencpsVotingStatistic : opencpsVotingStatistics) {
			entityCache.removeResult(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsVotingStatisticImpl.class,
				opencpsVotingStatistic.getPrimaryKey());

			clearUniqueFindersCache((OpencpsVotingStatisticModelImpl)opencpsVotingStatistic,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OpencpsVotingStatisticModelImpl opencpsVotingStatisticModelImpl) {
		Object[] args = new Object[] {
				opencpsVotingStatisticModelImpl.getUuid(),
				opencpsVotingStatisticModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			opencpsVotingStatisticModelImpl, false);

		args = new Object[] {
				opencpsVotingStatisticModelImpl.getGroupId(),
				opencpsVotingStatisticModelImpl.getMonth(),
				opencpsVotingStatisticModelImpl.getYear(),
				opencpsVotingStatisticModelImpl.getGovAgencyCode(),
				opencpsVotingStatisticModelImpl.getServiceCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_M_Y_DM_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_DM_G, args,
			opencpsVotingStatisticModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OpencpsVotingStatisticModelImpl opencpsVotingStatisticModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsVotingStatisticModelImpl.getUuid(),
					opencpsVotingStatisticModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((opencpsVotingStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsVotingStatisticModelImpl.getOriginalUuid(),
					opencpsVotingStatisticModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsVotingStatisticModelImpl.getGroupId(),
					opencpsVotingStatisticModelImpl.getMonth(),
					opencpsVotingStatisticModelImpl.getYear(),
					opencpsVotingStatisticModelImpl.getGovAgencyCode(),
					opencpsVotingStatisticModelImpl.getServiceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_DM_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_DM_G, args);
		}

		if ((opencpsVotingStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_M_Y_DM_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsVotingStatisticModelImpl.getOriginalGroupId(),
					opencpsVotingStatisticModelImpl.getOriginalMonth(),
					opencpsVotingStatisticModelImpl.getOriginalYear(),
					opencpsVotingStatisticModelImpl.getOriginalGovAgencyCode(),
					opencpsVotingStatisticModelImpl.getOriginalServiceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_DM_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_DM_G, args);
		}
	}

	/**
	 * Creates a new opencps voting statistic with the primary key. Does not add the opencps voting statistic to the database.
	 *
	 * @param votingStatisticId the primary key for the new opencps voting statistic
	 * @return the new opencps voting statistic
	 */
	@Override
	public OpencpsVotingStatistic create(long votingStatisticId) {
		OpencpsVotingStatistic opencpsVotingStatistic = new OpencpsVotingStatisticImpl();

		opencpsVotingStatistic.setNew(true);
		opencpsVotingStatistic.setPrimaryKey(votingStatisticId);

		String uuid = PortalUUIDUtil.generate();

		opencpsVotingStatistic.setUuid(uuid);

		opencpsVotingStatistic.setCompanyId(companyProvider.getCompanyId());

		return opencpsVotingStatistic;
	}

	/**
	 * Removes the opencps voting statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param votingStatisticId the primary key of the opencps voting statistic
	 * @return the opencps voting statistic that was removed
	 * @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	 */
	@Override
	public OpencpsVotingStatistic remove(long votingStatisticId)
		throws NoSuchOpencpsVotingStatisticException {
		return remove((Serializable)votingStatisticId);
	}

	/**
	 * Removes the opencps voting statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the opencps voting statistic
	 * @return the opencps voting statistic that was removed
	 * @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	 */
	@Override
	public OpencpsVotingStatistic remove(Serializable primaryKey)
		throws NoSuchOpencpsVotingStatisticException {
		Session session = null;

		try {
			session = openSession();

			OpencpsVotingStatistic opencpsVotingStatistic = (OpencpsVotingStatistic)session.get(OpencpsVotingStatisticImpl.class,
					primaryKey);

			if (opencpsVotingStatistic == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOpencpsVotingStatisticException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(opencpsVotingStatistic);
		}
		catch (NoSuchOpencpsVotingStatisticException nsee) {
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
	protected OpencpsVotingStatistic removeImpl(
		OpencpsVotingStatistic opencpsVotingStatistic) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(opencpsVotingStatistic)) {
				opencpsVotingStatistic = (OpencpsVotingStatistic)session.get(OpencpsVotingStatisticImpl.class,
						opencpsVotingStatistic.getPrimaryKeyObj());
			}

			if (opencpsVotingStatistic != null) {
				session.delete(opencpsVotingStatistic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (opencpsVotingStatistic != null) {
			clearCache(opencpsVotingStatistic);
		}

		return opencpsVotingStatistic;
	}

	@Override
	public OpencpsVotingStatistic updateImpl(
		OpencpsVotingStatistic opencpsVotingStatistic) {
		boolean isNew = opencpsVotingStatistic.isNew();

		if (!(opencpsVotingStatistic instanceof OpencpsVotingStatisticModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(opencpsVotingStatistic.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(opencpsVotingStatistic);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in opencpsVotingStatistic proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OpencpsVotingStatistic implementation " +
				opencpsVotingStatistic.getClass());
		}

		OpencpsVotingStatisticModelImpl opencpsVotingStatisticModelImpl = (OpencpsVotingStatisticModelImpl)opencpsVotingStatistic;

		if (Validator.isNull(opencpsVotingStatistic.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			opencpsVotingStatistic.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (opencpsVotingStatistic.getCreateDate() == null)) {
			if (serviceContext == null) {
				opencpsVotingStatistic.setCreateDate(now);
			}
			else {
				opencpsVotingStatistic.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!opencpsVotingStatisticModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				opencpsVotingStatistic.setModifiedDate(now);
			}
			else {
				opencpsVotingStatistic.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (opencpsVotingStatistic.isNew()) {
				session.save(opencpsVotingStatistic);

				opencpsVotingStatistic.setNew(false);
			}
			else {
				opencpsVotingStatistic = (OpencpsVotingStatistic)session.merge(opencpsVotingStatistic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OpencpsVotingStatisticModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					opencpsVotingStatisticModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					opencpsVotingStatisticModelImpl.getUuid(),
					opencpsVotingStatisticModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					opencpsVotingStatisticModelImpl.getGroupId(),
					opencpsVotingStatisticModelImpl.getUserId(),
					opencpsVotingStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((opencpsVotingStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsVotingStatisticModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { opencpsVotingStatisticModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((opencpsVotingStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsVotingStatisticModelImpl.getOriginalUuid(),
						opencpsVotingStatisticModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						opencpsVotingStatisticModelImpl.getUuid(),
						opencpsVotingStatisticModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((opencpsVotingStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsVotingStatisticModelImpl.getOriginalGroupId(),
						opencpsVotingStatisticModelImpl.getOriginalUserId(),
						opencpsVotingStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);

				args = new Object[] {
						opencpsVotingStatisticModelImpl.getGroupId(),
						opencpsVotingStatisticModelImpl.getUserId(),
						opencpsVotingStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);
			}
		}

		entityCache.putResult(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsVotingStatisticImpl.class,
			opencpsVotingStatistic.getPrimaryKey(), opencpsVotingStatistic,
			false);

		clearUniqueFindersCache(opencpsVotingStatisticModelImpl, false);
		cacheUniqueFindersCache(opencpsVotingStatisticModelImpl);

		opencpsVotingStatistic.resetOriginalValues();

		return opencpsVotingStatistic;
	}

	/**
	 * Returns the opencps voting statistic with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps voting statistic
	 * @return the opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOpencpsVotingStatisticException {
		OpencpsVotingStatistic opencpsVotingStatistic = fetchByPrimaryKey(primaryKey);

		if (opencpsVotingStatistic == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOpencpsVotingStatisticException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return opencpsVotingStatistic;
	}

	/**
	 * Returns the opencps voting statistic with the primary key or throws a {@link NoSuchOpencpsVotingStatisticException} if it could not be found.
	 *
	 * @param votingStatisticId the primary key of the opencps voting statistic
	 * @return the opencps voting statistic
	 * @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	 */
	@Override
	public OpencpsVotingStatistic findByPrimaryKey(long votingStatisticId)
		throws NoSuchOpencpsVotingStatisticException {
		return findByPrimaryKey((Serializable)votingStatisticId);
	}

	/**
	 * Returns the opencps voting statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps voting statistic
	 * @return the opencps voting statistic, or <code>null</code> if a opencps voting statistic with the primary key could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsVotingStatisticImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OpencpsVotingStatistic opencpsVotingStatistic = (OpencpsVotingStatistic)serializable;

		if (opencpsVotingStatistic == null) {
			Session session = null;

			try {
				session = openSession();

				opencpsVotingStatistic = (OpencpsVotingStatistic)session.get(OpencpsVotingStatisticImpl.class,
						primaryKey);

				if (opencpsVotingStatistic != null) {
					cacheResult(opencpsVotingStatistic);
				}
				else {
					entityCache.putResult(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsVotingStatisticImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsVotingStatisticImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return opencpsVotingStatistic;
	}

	/**
	 * Returns the opencps voting statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param votingStatisticId the primary key of the opencps voting statistic
	 * @return the opencps voting statistic, or <code>null</code> if a opencps voting statistic with the primary key could not be found
	 */
	@Override
	public OpencpsVotingStatistic fetchByPrimaryKey(long votingStatisticId) {
		return fetchByPrimaryKey((Serializable)votingStatisticId);
	}

	@Override
	public Map<Serializable, OpencpsVotingStatistic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OpencpsVotingStatistic> map = new HashMap<Serializable, OpencpsVotingStatistic>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OpencpsVotingStatistic opencpsVotingStatistic = fetchByPrimaryKey(primaryKey);

			if (opencpsVotingStatistic != null) {
				map.put(primaryKey, opencpsVotingStatistic);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsVotingStatisticImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OpencpsVotingStatistic)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE_PKS_IN);

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

			for (OpencpsVotingStatistic opencpsVotingStatistic : (List<OpencpsVotingStatistic>)q.list()) {
				map.put(opencpsVotingStatistic.getPrimaryKeyObj(),
					opencpsVotingStatistic);

				cacheResult(opencpsVotingStatistic);

				uncachedPrimaryKeys.remove(opencpsVotingStatistic.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OpencpsVotingStatisticModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsVotingStatisticImpl.class, primaryKey, nullModel);
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
	 * Returns all the opencps voting statistics.
	 *
	 * @return the opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps voting statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @return the range of opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps voting statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps voting statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps voting statistics
	 * @param end the upper bound of the range of opencps voting statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of opencps voting statistics
	 */
	@Override
	public List<OpencpsVotingStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsVotingStatistic> orderByComparator,
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

		List<OpencpsVotingStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsVotingStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPENCPSVOTINGSTATISTIC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPENCPSVOTINGSTATISTIC;

				if (pagination) {
					sql = sql.concat(OpencpsVotingStatisticModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpencpsVotingStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsVotingStatistic>)QueryUtil.list(q,
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
	 * Removes all the opencps voting statistics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OpencpsVotingStatistic opencpsVotingStatistic : findAll()) {
			remove(opencpsVotingStatistic);
		}
	}

	/**
	 * Returns the number of opencps voting statistics.
	 *
	 * @return the number of opencps voting statistics
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPENCPSVOTINGSTATISTIC);

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
		return OpencpsVotingStatisticModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the opencps voting statistic persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OpencpsVotingStatisticImpl.class.getName());
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
	private static final String _SQL_SELECT_OPENCPSVOTINGSTATISTIC = "SELECT opencpsVotingStatistic FROM OpencpsVotingStatistic opencpsVotingStatistic";
	private static final String _SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE_PKS_IN = "SELECT opencpsVotingStatistic FROM OpencpsVotingStatistic opencpsVotingStatistic WHERE votingStatisticId IN (";
	private static final String _SQL_SELECT_OPENCPSVOTINGSTATISTIC_WHERE = "SELECT opencpsVotingStatistic FROM OpencpsVotingStatistic opencpsVotingStatistic WHERE ";
	private static final String _SQL_COUNT_OPENCPSVOTINGSTATISTIC = "SELECT COUNT(opencpsVotingStatistic) FROM OpencpsVotingStatistic opencpsVotingStatistic";
	private static final String _SQL_COUNT_OPENCPSVOTINGSTATISTIC_WHERE = "SELECT COUNT(opencpsVotingStatistic) FROM OpencpsVotingStatistic opencpsVotingStatistic WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "opencpsVotingStatistic.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpencpsVotingStatistic exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpencpsVotingStatistic exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OpencpsVotingStatisticPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}