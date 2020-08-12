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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.impl.OpencpsDossierStatisticImpl;
import org.opencps.statistic.model.impl.OpencpsDossierStatisticModelImpl;
import org.opencps.statistic.service.persistence.OpencpsDossierStatisticPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
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
 * The persistence implementation for the opencps dossier statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticPersistence
 * @see org.opencps.statistic.service.persistence.OpencpsDossierStatisticUtil
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticPersistenceImpl extends BasePersistenceImpl<OpencpsDossierStatistic>
	implements OpencpsDossierStatisticPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OpencpsDossierStatisticUtil} to access the opencps dossier statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OpencpsDossierStatisticImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OpencpsDossierStatisticModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the opencps dossier statistics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if (!Objects.equals(uuid, opencpsDossierStatistic.getUuid())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByUuid_First(uuid,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByUuid_Last(uuid,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where uuid = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByUuid_PrevAndNext(
		long dossierStatisticId, String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByUuid_PrevAndNext(session, opencpsDossierStatistic,
					uuid, orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByUuid_PrevAndNext(session, opencpsDossierStatistic,
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

	protected OpencpsDossierStatistic getByUuid_PrevAndNext(Session session,
		OpencpsDossierStatistic opencpsDossierStatistic, String uuid,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "opencpsDossierStatistic.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "opencpsDossierStatistic.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(opencpsDossierStatistic.uuid IS NULL OR opencpsDossierStatistic.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OpencpsDossierStatisticModelImpl.UUID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the opencps dossier statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByUUID_G(uuid,
				groupId);

		if (opencpsDossierStatistic == null) {
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

			throw new NoSuchOpencpsDossierStatisticException(msg.toString());
		}

		return opencpsDossierStatistic;
	}

	/**
	 * Returns the opencps dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the opencps dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatistic) {
			OpencpsDossierStatistic opencpsDossierStatistic = (OpencpsDossierStatistic)result;

			if (!Objects.equals(uuid, opencpsDossierStatistic.getUuid()) ||
					(groupId != opencpsDossierStatistic.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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

				List<OpencpsDossierStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OpencpsDossierStatistic opencpsDossierStatistic = list.get(0);

					result = opencpsDossierStatistic;

					cacheResult(opencpsDossierStatistic);
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
			return (OpencpsDossierStatistic)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the opencps dossier statistic that was removed
	 */
	@Override
	public OpencpsDossierStatistic removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByUUID_G(uuid,
				groupId);

		return remove(opencpsDossierStatistic);
	}

	/**
	 * Returns the number of opencps dossier statistics where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "opencpsDossierStatistic.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "opencpsDossierStatistic.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(opencpsDossierStatistic.uuid IS NULL OR opencpsDossierStatistic.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "opencpsDossierStatistic.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OpencpsDossierStatisticModelImpl.UUID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if (!Objects.equals(uuid, opencpsDossierStatistic.getUuid()) ||
							(companyId != opencpsDossierStatistic.getCompanyId())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByUuid_C(uuid, companyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByUuid_C_PrevAndNext(
		long dossierStatisticId, String uuid, long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					opencpsDossierStatistic, uuid, companyId,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByUuid_C_PrevAndNext(session,
					opencpsDossierStatistic, uuid, companyId,
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

	protected OpencpsDossierStatistic getByUuid_C_PrevAndNext(Session session,
		OpencpsDossierStatistic opencpsDossierStatistic, String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "opencpsDossierStatistic.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "opencpsDossierStatistic.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(opencpsDossierStatistic.uuid IS NULL OR opencpsDossierStatistic.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "opencpsDossierStatistic.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.USERID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UID_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year) {
		return findByG_UID_Y(groupId, userId, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return findByG_UID_Y(groupId, userId, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							(userId != opencpsDossierStatistic.getUserId()) ||
							(year != opencpsDossierStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_UID_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_USERID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_UID_Y_First(groupId,
				userId, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByG_UID_Y(groupId, userId,
				year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_UID_Y_Last(groupId,
				userId, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByG_UID_Y(groupId, userId, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByG_UID_Y(groupId, userId,
				year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByG_UID_Y_PrevAndNext(
		long dossierStatisticId, long groupId, long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByG_UID_Y_PrevAndNext(session,
					opencpsDossierStatistic, groupId, userId, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByG_UID_Y_PrevAndNext(session,
					opencpsDossierStatistic, groupId, userId, year,
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

	protected OpencpsDossierStatistic getByG_UID_Y_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long groupId, long userId, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 */
	@Override
	public void removeByG_UID_Y(long groupId, long userId, int year) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByG_UID_Y(
				groupId, userId, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG_UID_Y(long groupId, long userId, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UID_Y;

		Object[] finderArgs = new Object[] { groupId, userId, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_G_UID_Y_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_USERID_2 = "opencpsDossierStatistic.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_YEAR_2 = "opencpsDossierStatistic.year = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_M_Y_DM_G = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByM_Y_DM_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.DOMAINCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.REPORTING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_Y_DM_G = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByM_Y_DM_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				String.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		int reporting) throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByM_Y_DM_G(groupId,
				govAgencyCode, month, year, domainCode, reporting);

		if (opencpsDossierStatistic == null) {
			StringBundler msg = new StringBundler(14);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append(", month=");
			msg.append(month);

			msg.append(", year=");
			msg.append(year);

			msg.append(", domainCode=");
			msg.append(domainCode);

			msg.append(", reporting=");
			msg.append(reporting);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOpencpsDossierStatisticException(msg.toString());
		}

		return opencpsDossierStatistic;
	}

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		int reporting) {
		return fetchByM_Y_DM_G(groupId, govAgencyCode, month, year, domainCode,
			reporting, true);
	}

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		int reporting, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, govAgencyCode, month, year, domainCode, reporting
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatistic) {
			OpencpsDossierStatistic opencpsDossierStatistic = (OpencpsDossierStatistic)result;

			if ((groupId != opencpsDossierStatistic.getGroupId()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatistic.getGovAgencyCode()) ||
					(month != opencpsDossierStatistic.getMonth()) ||
					(year != opencpsDossierStatistic.getYear()) ||
					!Objects.equals(domainCode,
						opencpsDossierStatistic.getDomainCode()) ||
					(reporting != opencpsDossierStatistic.getReporting())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_DM_G_GROUPID_2);

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

			query.append(_FINDER_COLUMN_M_Y_DM_G_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_DM_G_YEAR_2);

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_M_Y_DM_G_REPORTING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				qPos.add(month);

				qPos.add(year);

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				qPos.add(reporting);

				List<OpencpsDossierStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticPersistenceImpl.fetchByM_Y_DM_G(long, String, int, int, String, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatistic opencpsDossierStatistic = list.get(0);

					result = opencpsDossierStatistic;

					cacheResult(opencpsDossierStatistic);
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
			return (OpencpsDossierStatistic)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @return the opencps dossier statistic that was removed
	 */
	@Override
	public OpencpsDossierStatistic removeByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		int reporting) throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByM_Y_DM_G(groupId,
				govAgencyCode, month, year, domainCode, reporting);

		return remove(opencpsDossierStatistic);
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByM_Y_DM_G(long groupId, String govAgencyCode, int month,
		int year, String domainCode, int reporting) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_Y_DM_G;

		Object[] finderArgs = new Object[] {
				groupId, govAgencyCode, month, year, domainCode, reporting
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_DM_G_GROUPID_2);

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

			query.append(_FINDER_COLUMN_M_Y_DM_G_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_DM_G_YEAR_2);

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_M_Y_DM_G_REPORTING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				qPos.add(month);

				qPos.add(year);

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				qPos.add(reporting);

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

	private static final String _FINDER_COLUMN_M_Y_DM_G_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_1 = "opencpsDossierStatistic.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_2 = "opencpsDossierStatistic.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_3 = "(opencpsDossierStatistic.govAgencyCode IS NULL OR opencpsDossierStatistic.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_YEAR_2 = "opencpsDossierStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_1 = "opencpsDossierStatistic.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_2 = "opencpsDossierStatistic.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_3 = "(opencpsDossierStatistic.domainCode IS NULL OR opencpsDossierStatistic.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_REPORTING_2 = "opencpsDossierStatistic.reporting = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_M_Y_G_D = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_M_Y_G_D",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.DOMAINCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y_G_D = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_M_Y_G_D",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_M_Y_G_D(long groupId, int month,
		int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_M_Y_G_D(groupId,
				month, year, govAgencyCode, domainCode);

		if (opencpsDossierStatistic == null) {
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

			msg.append(", domainCode=");
			msg.append(domainCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOpencpsDossierStatisticException(msg.toString());
		}

		return opencpsDossierStatistic;
	}

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_M_Y_G_D(long groupId, int month,
		int year, String govAgencyCode, String domainCode) {
		return fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode, true);
	}

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_M_Y_G_D(long groupId, int month,
		int year, String govAgencyCode, String domainCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatistic) {
			OpencpsDossierStatistic opencpsDossierStatistic = (OpencpsDossierStatistic)result;

			if ((groupId != opencpsDossierStatistic.getGroupId()) ||
					(month != opencpsDossierStatistic.getMonth()) ||
					(year != opencpsDossierStatistic.getYear()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatistic.getGovAgencyCode()) ||
					!Objects.equals(domainCode,
						opencpsDossierStatistic.getDomainCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_2);
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

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				List<OpencpsDossierStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticPersistenceImpl.fetchByG_M_Y_G_D(long, int, int, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatistic opencpsDossierStatistic = list.get(0);

					result = opencpsDossierStatistic;

					cacheResult(opencpsDossierStatistic);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D,
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
			return (OpencpsDossierStatistic)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the opencps dossier statistic that was removed
	 */
	@Override
	public OpencpsDossierStatistic removeByG_M_Y_G_D(long groupId, int month,
		int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByG_M_Y_G_D(groupId,
				month, year, govAgencyCode, domainCode);

		return remove(opencpsDossierStatistic);
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG_M_Y_G_D(long groupId, int month, int year,
		String govAgencyCode, String domainCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_M_Y_G_D;

		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_2);
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

				if (bindDomainCode) {
					qPos.add(domainCode);
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

	private static final String _FINDER_COLUMN_G_M_Y_G_D_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_YEAR_2 = "opencpsDossierStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_1 = "opencpsDossierStatistic.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_2 = "opencpsDossierStatistic.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_3 = "(opencpsDossierStatistic.govAgencyCode IS NULL OR opencpsDossierStatistic.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_1 = "opencpsDossierStatistic.domainCode IS NULL";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_2 = "opencpsDossierStatistic.domainCode = ?";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_3 = "(opencpsDossierStatistic.domainCode IS NULL OR opencpsDossierStatistic.domainCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_M_Y_G_D_S = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_M_Y_G_D_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.DOMAINCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.SYSTEM_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y_G_D_S = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_M_Y_G_D_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @return the matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_M_Y_G_D_S(long groupId, int month,
		int year, String govAgencyCode, String domainCode, String system)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_M_Y_G_D_S(groupId,
				month, year, govAgencyCode, domainCode, system);

		if (opencpsDossierStatistic == null) {
			StringBundler msg = new StringBundler(14);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", month=");
			msg.append(month);

			msg.append(", year=");
			msg.append(year);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append(", domainCode=");
			msg.append(domainCode);

			msg.append(", system=");
			msg.append(system);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOpencpsDossierStatisticException(msg.toString());
		}

		return opencpsDossierStatistic;
	}

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_M_Y_G_D_S(long groupId, int month,
		int year, String govAgencyCode, String domainCode, String system) {
		return fetchByG_M_Y_G_D_S(groupId, month, year, govAgencyCode,
			domainCode, system, true);
	}

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_M_Y_G_D_S(long groupId, int month,
		int year, String govAgencyCode, String domainCode, String system,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode, system
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatistic) {
			OpencpsDossierStatistic opencpsDossierStatistic = (OpencpsDossierStatistic)result;

			if ((groupId != opencpsDossierStatistic.getGroupId()) ||
					(month != opencpsDossierStatistic.getMonth()) ||
					(year != opencpsDossierStatistic.getYear()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatistic.getGovAgencyCode()) ||
					!Objects.equals(domainCode,
						opencpsDossierStatistic.getDomainCode()) ||
					!Objects.equals(system, opencpsDossierStatistic.getSystem())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_S_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_S_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_2);
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

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindSystem) {
					qPos.add(system);
				}

				List<OpencpsDossierStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticPersistenceImpl.fetchByG_M_Y_G_D_S(long, int, int, String, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatistic opencpsDossierStatistic = list.get(0);

					result = opencpsDossierStatistic;

					cacheResult(opencpsDossierStatistic);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S,
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
			return (OpencpsDossierStatistic)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @return the opencps dossier statistic that was removed
	 */
	@Override
	public OpencpsDossierStatistic removeByG_M_Y_G_D_S(long groupId, int month,
		int year, String govAgencyCode, String domainCode, String system)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByG_M_Y_G_D_S(groupId,
				month, year, govAgencyCode, domainCode, system);

		return remove(opencpsDossierStatistic);
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG_M_Y_G_D_S(long groupId, int month, int year,
		String govAgencyCode, String domainCode, String system) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_M_Y_G_D_S;

		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode, system
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_S_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_S_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_2);
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

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindSystem) {
					qPos.add(system);
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

	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_YEAR_2 = "opencpsDossierStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_1 = "opencpsDossierStatistic.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_2 = "opencpsDossierStatistic.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_3 = "(opencpsDossierStatistic.govAgencyCode IS NULL OR opencpsDossierStatistic.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_1 = "opencpsDossierStatistic.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_2 = "opencpsDossierStatistic.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_3 = "(opencpsDossierStatistic.domainCode IS NULL OR opencpsDossierStatistic.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_1 = "opencpsDossierStatistic.system IS NULL";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_2 = "opencpsDossierStatistic.system = ?";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_3 = "(opencpsDossierStatistic.system IS NULL OR opencpsDossierStatistic.system = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_M_Y_G = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByM_Y_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_Y_G = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByM_Y_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByM_Y_G(long groupId,
		String govAgencyCode, int month, int year)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByM_Y_G(groupId,
				govAgencyCode, month, year);

		if (opencpsDossierStatistic == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append(", month=");
			msg.append(month);

			msg.append(", year=");
			msg.append(year);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOpencpsDossierStatisticException(msg.toString());
		}

		return opencpsDossierStatistic;
	}

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByM_Y_G(long groupId,
		String govAgencyCode, int month, int year) {
		return fetchByM_Y_G(groupId, govAgencyCode, month, year, true);
	}

	/**
	 * Returns the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByM_Y_G(long groupId,
		String govAgencyCode, int month, int year, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, govAgencyCode, month, year };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_M_Y_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatistic) {
			OpencpsDossierStatistic opencpsDossierStatistic = (OpencpsDossierStatistic)result;

			if ((groupId != opencpsDossierStatistic.getGroupId()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatistic.getGovAgencyCode()) ||
					(month != opencpsDossierStatistic.getMonth()) ||
					(year != opencpsDossierStatistic.getYear())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_G_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_2);
			}

			query.append(_FINDER_COLUMN_M_Y_G_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_G_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				qPos.add(month);

				qPos.add(year);

				List<OpencpsDossierStatistic> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_G,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticPersistenceImpl.fetchByM_Y_G(long, String, int, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatistic opencpsDossierStatistic = list.get(0);

					result = opencpsDossierStatistic;

					cacheResult(opencpsDossierStatistic);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_G, finderArgs);

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
			return (OpencpsDossierStatistic)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @return the opencps dossier statistic that was removed
	 */
	@Override
	public OpencpsDossierStatistic removeByM_Y_G(long groupId,
		String govAgencyCode, int month, int year)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByM_Y_G(groupId,
				govAgencyCode, month, year);

		return remove(opencpsDossierStatistic);
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByM_Y_G(long groupId, String govAgencyCode, int month,
		int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_Y_G;

		Object[] finderArgs = new Object[] { groupId, govAgencyCode, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_G_GROUPID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_2);
			}

			query.append(_FINDER_COLUMN_M_Y_G_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_G_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

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

	private static final String _FINDER_COLUMN_M_Y_G_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_1 = "opencpsDossierStatistic.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_2 = "opencpsDossierStatistic.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_3 = "(opencpsDossierStatistic.govAgencyCode IS NULL OR opencpsDossierStatistic.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_G_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_G_YEAR_2 = "opencpsDossierStatistic.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_D_M_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_D_M_Y",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_D_M_Y",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.DOMAINCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_D_M_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_D_M_Y",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year) {
		return findByG_D_M_Y(groupId, domainCode, month, year,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end) {
		return findByG_D_M_Y(groupId, domainCode, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByG_D_M_Y(groupId, domainCode, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y;
			finderArgs = new Object[] { groupId, domainCode, month, year };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_D_M_Y;
			finderArgs = new Object[] {
					groupId, domainCode, month, year,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							!Objects.equals(domainCode,
								opencpsDossierStatistic.getDomainCode()) ||
							(month != opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_D_M_Y_GROUPID_2);

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_D_M_Y_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_D_M_Y_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_D_M_Y_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_G_D_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_D_M_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				qPos.add(month);

				qPos.add(year);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_D_M_Y_First(groupId,
				domainCode, month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", domainCode=");
		msg.append(domainCode);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByG_D_M_Y(groupId, domainCode,
				month, year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_D_M_Y_Last(groupId,
				domainCode, month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", domainCode=");
		msg.append(domainCode);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByG_D_M_Y(groupId, domainCode, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByG_D_M_Y(groupId, domainCode,
				month, year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByG_D_M_Y_PrevAndNext(
		long dossierStatisticId, long groupId, String domainCode, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByG_D_M_Y_PrevAndNext(session,
					opencpsDossierStatistic, groupId, domainCode, month, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByG_D_M_Y_PrevAndNext(session,
					opencpsDossierStatistic, groupId, domainCode, month, year,
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

	protected OpencpsDossierStatistic getByG_D_M_Y_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long groupId, String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_G_D_M_Y_GROUPID_2);

		boolean bindDomainCode = false;

		if (domainCode == null) {
			query.append(_FINDER_COLUMN_G_D_M_Y_DOMAINCODE_1);
		}
		else if (domainCode.equals("")) {
			query.append(_FINDER_COLUMN_G_D_M_Y_DOMAINCODE_3);
		}
		else {
			bindDomainCode = true;

			query.append(_FINDER_COLUMN_G_D_M_Y_DOMAINCODE_2);
		}

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindDomainCode) {
			qPos.add(domainCode);
		}

		qPos.add(month);

		qPos.add(year);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_D_M_Y(long groupId, String domainCode, int month,
		int year) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByG_D_M_Y(
				groupId, domainCode, month, year, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG_D_M_Y(long groupId, String domainCode, int month,
		int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_D_M_Y;

		Object[] finderArgs = new Object[] { groupId, domainCode, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_D_M_Y_GROUPID_2);

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_D_M_Y_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_D_M_Y_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_D_M_Y_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_G_D_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_D_M_Y_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

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

	private static final String _FINDER_COLUMN_G_D_M_Y_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_DOMAINCODE_1 = "opencpsDossierStatistic.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_DOMAINCODE_2 = "opencpsDossierStatistic.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_DOMAINCODE_3 = "(opencpsDossierStatistic.domainCode IS NULL OR opencpsDossierStatistic.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_YEAR_2 = "opencpsDossierStatistic.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_M_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_M_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_M_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_M_Y(long groupId, int month,
		int year) {
		return findByG_M_Y(groupId, month, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_M_Y(long groupId, int month,
		int year, int start, int end) {
		return findByG_M_Y(groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_M_Y(long groupId, int month,
		int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByG_M_Y(groupId, month, year, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_M_Y(long groupId, int month,
		int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							(month != opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_M_Y_First(long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_M_Y_First(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_M_Y_First(long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByG_M_Y(groupId, month, year,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_M_Y_Last(long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_M_Y_Last(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_M_Y_Last(long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByG_M_Y(groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByG_M_Y(groupId, month, year,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByG_M_Y_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByG_M_Y_PrevAndNext(session, opencpsDossierStatistic,
					groupId, month, year, orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByG_M_Y_PrevAndNext(session, opencpsDossierStatistic,
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

	protected OpencpsDossierStatistic getByG_M_Y_PrevAndNext(Session session,
		OpencpsDossierStatistic opencpsDossierStatistic, long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_M_Y(long groupId, int month, int year) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByG_M_Y(
				groupId, month, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG_M_Y(long groupId, int month, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_M_Y;

		Object[] finderArgs = new Object[] { groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_G_M_Y_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_YEAR_2 = "opencpsDossierStatistic.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CID_GID_Y =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCID_GID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCID_GID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticModelImpl.COMPANYID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CID_GID_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCID_GID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year) {
		return findByCID_GID_Y(companyId, groupId, month, year,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end) {
		return findByCID_GID_Y(companyId, groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByCID_GID_Y(companyId, groupId, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((companyId != opencpsDossierStatistic.getCompanyId()) ||
							(groupId != opencpsDossierStatistic.getGroupId()) ||
							(month != opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByCID_GID_Y_First(companyId,
				groupId, month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByCID_GID_Y(companyId,
				groupId, month, year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByCID_GID_Y_Last(companyId,
				groupId, month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByCID_GID_Y(companyId, groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByCID_GID_Y(companyId,
				groupId, month, year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByCID_GID_Y_PrevAndNext(
		long dossierStatisticId, long companyId, long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByCID_GID_Y_PrevAndNext(session,
					opencpsDossierStatistic, companyId, groupId, month, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByCID_GID_Y_PrevAndNext(session,
					opencpsDossierStatistic, companyId, groupId, month, year,
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

	protected OpencpsDossierStatistic getByCID_GID_Y_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long companyId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByCID_GID_Y(long companyId, long groupId, int month,
		int year) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByCID_GID_Y(
				companyId, groupId, month, year, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByCID_GID_Y(long companyId, long groupId, int month,
		int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CID_GID_Y;

		Object[] finderArgs = new Object[] { companyId, groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_CID_GID_Y_COMPANYID_2 = "opencpsDossierStatistic.companyId = ? AND ";
	private static final String _FINDER_COLUMN_CID_GID_Y_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_CID_GID_Y_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_CID_GID_Y_YEAR_2 = "opencpsDossierStatistic.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_M_Y_RP =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGID_M_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGID_M_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.REPORTING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_M_Y_RP = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_M_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_M_Y_RP(long groupId,
		int month, int year, int reporting) {
		return findByGID_M_Y_RP(groupId, month, year, reporting,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_M_Y_RP(long groupId,
		int month, int year, int reporting, int start, int end) {
		return findByGID_M_Y_RP(groupId, month, year, reporting, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_M_Y_RP(long groupId,
		int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByGID_M_Y_RP(groupId, month, year, reporting, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_M_Y_RP(long groupId,
		int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP;
			finderArgs = new Object[] { groupId, month, year, reporting };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_M_Y_RP;
			finderArgs = new Object[] {
					groupId, month, year, reporting,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							(month != opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear()) ||
							(reporting != opencpsDossierStatistic.getReporting())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_GID_M_Y_RP_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_M_Y_RP_MONTH_2);

			query.append(_FINDER_COLUMN_GID_M_Y_RP_YEAR_2);

			query.append(_FINDER_COLUMN_GID_M_Y_RP_REPORTING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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

				qPos.add(reporting);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByGID_M_Y_RP_First(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByGID_M_Y_RP_First(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", reporting=");
		msg.append(reporting);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByGID_M_Y_RP_First(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByGID_M_Y_RP(groupId, month,
				year, reporting, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByGID_M_Y_RP_Last(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByGID_M_Y_RP_Last(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", reporting=");
		msg.append(reporting);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByGID_M_Y_RP_Last(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByGID_M_Y_RP(groupId, month, year, reporting);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByGID_M_Y_RP(groupId, month,
				year, reporting, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByGID_M_Y_RP_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByGID_M_Y_RP_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year, reporting,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByGID_M_Y_RP_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year, reporting,
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

	protected OpencpsDossierStatistic getByGID_M_Y_RP_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long groupId, int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_GID_M_Y_RP_GROUPID_2);

		query.append(_FINDER_COLUMN_GID_M_Y_RP_MONTH_2);

		query.append(_FINDER_COLUMN_GID_M_Y_RP_YEAR_2);

		query.append(_FINDER_COLUMN_GID_M_Y_RP_REPORTING_2);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(month);

		qPos.add(year);

		qPos.add(reporting);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 */
	@Override
	public void removeByGID_M_Y_RP(long groupId, int month, int year,
		int reporting) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByGID_M_Y_RP(
				groupId, month, year, reporting, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByGID_M_Y_RP(long groupId, int month, int year,
		int reporting) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_M_Y_RP;

		Object[] finderArgs = new Object[] { groupId, month, year, reporting };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_GID_M_Y_RP_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_M_Y_RP_MONTH_2);

			query.append(_FINDER_COLUMN_GID_M_Y_RP_YEAR_2);

			query.append(_FINDER_COLUMN_GID_M_Y_RP_REPORTING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

				qPos.add(reporting);

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

	private static final String _FINDER_COLUMN_GID_M_Y_RP_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_M_Y_RP_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_GID_M_Y_RP_YEAR_2 = "opencpsDossierStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_GID_M_Y_RP_REPORTING_2 = "opencpsDossierStatistic.reporting = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_MS_Y_RP =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGID_MS_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGID_MS_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.REPORTING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_MS_Y_RP = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_MS_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_MS_Y_RP =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGID_MS_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_MS_Y_RP(long groupId,
		int month, int year, int reporting) {
		return findByGID_MS_Y_RP(groupId, month, year, reporting,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_MS_Y_RP(long groupId,
		int month, int year, int reporting, int start, int end) {
		return findByGID_MS_Y_RP(groupId, month, year, reporting, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_MS_Y_RP(long groupId,
		int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByGID_MS_Y_RP(groupId, month, year, reporting, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_MS_Y_RP(long groupId,
		int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP;
			finderArgs = new Object[] { groupId, month, year, reporting };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_MS_Y_RP;
			finderArgs = new Object[] {
					groupId, month, year, reporting,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							(month != opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear()) ||
							(reporting != opencpsDossierStatistic.getReporting())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_MONTH_2);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_YEAR_2);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_REPORTING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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

				qPos.add(reporting);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByGID_MS_Y_RP_First(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByGID_MS_Y_RP_First(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", reporting=");
		msg.append(reporting);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByGID_MS_Y_RP_First(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByGID_MS_Y_RP(groupId, month,
				year, reporting, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByGID_MS_Y_RP_Last(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByGID_MS_Y_RP_Last(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", reporting=");
		msg.append(reporting);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByGID_MS_Y_RP_Last(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByGID_MS_Y_RP(groupId, month, year, reporting);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByGID_MS_Y_RP(groupId, month,
				year, reporting, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByGID_MS_Y_RP_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByGID_MS_Y_RP_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year, reporting,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByGID_MS_Y_RP_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year, reporting,
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

	protected OpencpsDossierStatistic getByGID_MS_Y_RP_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long groupId, int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_GID_MS_Y_RP_GROUPID_2);

		query.append(_FINDER_COLUMN_GID_MS_Y_RP_MONTH_2);

		query.append(_FINDER_COLUMN_GID_MS_Y_RP_YEAR_2);

		query.append(_FINDER_COLUMN_GID_MS_Y_RP_REPORTING_2);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(month);

		qPos.add(year);

		qPos.add(reporting);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param months the months
	 * @param year the year
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_MS_Y_RP(long groupId,
		int[] months, int year, int reporting) {
		return findByGID_MS_Y_RP(groupId, months, year, reporting,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param months the months
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_MS_Y_RP(long groupId,
		int[] months, int year, int reporting, int start, int end) {
		return findByGID_MS_Y_RP(groupId, months, year, reporting, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param months the months
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_MS_Y_RP(long groupId,
		int[] months, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByGID_MS_Y_RP(groupId, months, year, reporting, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_MS_Y_RP(long groupId,
		int[] months, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		if (months == null) {
			months = new int[0];
		}
		else if (months.length > 1) {
			months = ArrayUtil.unique(months);

			Arrays.sort(months);
		}

		if (months.length == 1) {
			return findByGID_MS_Y_RP(groupId, months[0], year, reporting,
				start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(months), year, reporting
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(months), year, reporting,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_MS_Y_RP,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							!ArrayUtil.contains(months,
								opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear()) ||
							(reporting != opencpsDossierStatistic.getReporting())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_GROUPID_2);

			if (months.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_GID_MS_Y_RP_MONTH_7);

				query.append(StringUtil.merge(months));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_YEAR_2);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_REPORTING_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(year);

				qPos.add(reporting);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_MS_Y_RP,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_MS_Y_RP,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 */
	@Override
	public void removeByGID_MS_Y_RP(long groupId, int month, int year,
		int reporting) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByGID_MS_Y_RP(
				groupId, month, year, reporting, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByGID_MS_Y_RP(long groupId, int month, int year,
		int reporting) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_MS_Y_RP;

		Object[] finderArgs = new Object[] { groupId, month, year, reporting };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_MONTH_2);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_YEAR_2);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_REPORTING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

				qPos.add(reporting);

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

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param months the months
	 * @param year the year
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByGID_MS_Y_RP(long groupId, int[] months, int year,
		int reporting) {
		if (months == null) {
			months = new int[0];
		}
		else if (months.length > 1) {
			months = ArrayUtil.unique(months);

			Arrays.sort(months);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(months), year, reporting
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_MS_Y_RP,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_GROUPID_2);

			if (months.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_GID_MS_Y_RP_MONTH_7);

				query.append(StringUtil.merge(months));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_YEAR_2);

			query.append(_FINDER_COLUMN_GID_MS_Y_RP_REPORTING_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(year);

				qPos.add(reporting);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_MS_Y_RP,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_MS_Y_RP,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GID_MS_Y_RP_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_MS_Y_RP_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_GID_MS_Y_RP_MONTH_7 = "opencpsDossierStatistic.month IN (";
	private static final String _FINDER_COLUMN_GID_MS_Y_RP_YEAR_2 = "opencpsDossierStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_GID_MS_Y_RP_REPORTING_2 = "opencpsDossierStatistic.reporting = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG",
			new String[] { Long.class.getName() },
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG(long groupId) {
		return findByG(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG(long groupId, int start,
		int end) {
		return findByG(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG(long groupId, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByG(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG(long groupId, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_First(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_First(groupId,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_First(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByG(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_Last(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_Last(groupId,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_Last(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByG(groupId);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByG(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByG_PrevAndNext(
		long dossierStatisticId, long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByG_PrevAndNext(session, opencpsDossierStatistic,
					groupId, orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByG_PrevAndNext(session, opencpsDossierStatistic,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatistic getByG_PrevAndNext(Session session,
		OpencpsDossierStatistic opencpsDossierStatistic, long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByG(long groupId) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByG(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

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

	private static final String _FINDER_COLUMN_G_GROUPID_2 = "opencpsDossierStatistic.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M_Y_GC_DC =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_M_Y_GC_DC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_M_Y_GC_DC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y_GC_DC = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_M_Y_GC_DC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_M_Y_GC_DC(long groupId,
		int month, int year) {
		return findByG_M_Y_GC_DC(groupId, month, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_M_Y_GC_DC(long groupId,
		int month, int year, int start, int end) {
		return findByG_M_Y_GC_DC(groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_M_Y_GC_DC(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByG_M_Y_GC_DC(groupId, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_M_Y_GC_DC(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC;
			finderArgs = new Object[] { groupId, month, year };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M_Y_GC_DC;
			finderArgs = new Object[] {
					groupId, month, year,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							(month != opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_GC_DC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_GC_DC_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_GC_DC_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_M_Y_GC_DC_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_M_Y_GC_DC_First(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_M_Y_GC_DC_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByG_M_Y_GC_DC(groupId, month,
				year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_M_Y_GC_DC_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_M_Y_GC_DC_Last(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_M_Y_GC_DC_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByG_M_Y_GC_DC(groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByG_M_Y_GC_DC(groupId, month,
				year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByG_M_Y_GC_DC_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByG_M_Y_GC_DC_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByG_M_Y_GC_DC_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year,
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

	protected OpencpsDossierStatistic getByG_M_Y_GC_DC_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_G_M_Y_GC_DC_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_Y_GC_DC_MONTH_2);

		query.append(_FINDER_COLUMN_G_M_Y_GC_DC_YEAR_2);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_M_Y_GC_DC(long groupId, int month, int year) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByG_M_Y_GC_DC(
				groupId, month, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG_M_Y_GC_DC(long groupId, int month, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_M_Y_GC_DC;

		Object[] finderArgs = new Object[] { groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_GC_DC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_GC_DC_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_GC_DC_YEAR_2);

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

	private static final String _FINDER_COLUMN_G_M_Y_GC_DC_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_GC_DC_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_GC_DC_YEAR_2 = "opencpsDossierStatistic.year = ? AND opencpsDossierStatistic.govAgencyCode is null and opencpsDossierStatistic.domainCode is null";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NM_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_NM_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NM_Y = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_NM_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_NM_Y(long groupId, int month,
		int year) {
		return findByG_NM_Y(groupId, month, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_NM_Y(long groupId, int month,
		int year, int start, int end) {
		return findByG_NM_Y(groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_NM_Y(long groupId, int month,
		int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByG_NM_Y(groupId, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_NM_Y(long groupId, int month,
		int year, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NM_Y;
		finderArgs = new Object[] {
				groupId, month, year,
				
				start, end, orderByComparator
			};

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							(month == opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_NM_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_NM_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_NM_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_NM_Y_First(long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_NM_Y_First(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_NM_Y_First(long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByG_NM_Y(groupId, month, year,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_NM_Y_Last(long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_NM_Y_Last(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
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

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_NM_Y_Last(long groupId, int month,
		int year, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByG_NM_Y(groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByG_NM_Y(groupId, month, year,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByG_NM_Y_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByG_NM_Y_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByG_NM_Y_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year,
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

	protected OpencpsDossierStatistic getByG_NM_Y_PrevAndNext(Session session,
		OpencpsDossierStatistic opencpsDossierStatistic, long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_G_NM_Y_GROUPID_2);

		query.append(_FINDER_COLUMN_G_NM_Y_MONTH_2);

		query.append(_FINDER_COLUMN_G_NM_Y_YEAR_2);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_NM_Y(long groupId, int month, int year) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByG_NM_Y(
				groupId, month, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG_NM_Y(long groupId, int month, int year) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NM_Y;

		Object[] finderArgs = new Object[] { groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_NM_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_NM_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_NM_Y_YEAR_2);

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

	private static final String _FINDER_COLUMN_G_NM_Y_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_MONTH_2 = "opencpsDossierStatistic.month != ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_YEAR_2 = "opencpsDossierStatistic.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_REPO = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_REPO",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPO =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_REPO",
			new String[] { Integer.class.getName() },
			OpencpsDossierStatisticModelImpl.REPORTING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_REPO = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_REPO",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_REPO = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_REPO",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the opencps dossier statistics where reporting = &#63;.
	 *
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_REPO(int reporting) {
		return findByF_REPO(reporting, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_REPO(int reporting, int start,
		int end) {
		return findByF_REPO(reporting, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_REPO(int reporting, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByF_REPO(reporting, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_REPO(int reporting, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPO;
			finderArgs = new Object[] { reporting };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_REPO;
			finderArgs = new Object[] { reporting, start, end, orderByComparator };
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((reporting != opencpsDossierStatistic.getReporting())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_F_REPO_REPORTING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(reporting);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where reporting = &#63;.
	 *
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByF_REPO_First(int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByF_REPO_First(reporting,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("reporting=");
		msg.append(reporting);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where reporting = &#63;.
	 *
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByF_REPO_First(int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByF_REPO(reporting, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where reporting = &#63;.
	 *
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByF_REPO_Last(int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByF_REPO_Last(reporting,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("reporting=");
		msg.append(reporting);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where reporting = &#63;.
	 *
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByF_REPO_Last(int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByF_REPO(reporting);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByF_REPO(reporting, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where reporting = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByF_REPO_PrevAndNext(
		long dossierStatisticId, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByF_REPO_PrevAndNext(session,
					opencpsDossierStatistic, reporting, orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByF_REPO_PrevAndNext(session,
					opencpsDossierStatistic, reporting, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatistic getByF_REPO_PrevAndNext(Session session,
		OpencpsDossierStatistic opencpsDossierStatistic, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_F_REPO_REPORTING_2);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(reporting);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the opencps dossier statistics where reporting = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param reportings the reportings
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_REPO(int[] reportings) {
		return findByF_REPO(reportings, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where reporting = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param reportings the reportings
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_REPO(int[] reportings,
		int start, int end) {
		return findByF_REPO(reportings, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where reporting = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param reportings the reportings
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_REPO(int[] reportings,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByF_REPO(reportings, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where reporting = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_REPO(int[] reportings,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		if (reportings == null) {
			reportings = new int[0];
		}
		else if (reportings.length > 1) {
			reportings = ArrayUtil.unique(reportings);

			Arrays.sort(reportings);
		}

		if (reportings.length == 1) {
			return findByF_REPO(reportings[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(reportings) };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(reportings),
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_REPO,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if (!ArrayUtil.contains(reportings,
								opencpsDossierStatistic.getReporting())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			if (reportings.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_F_REPO_REPORTING_7);

				query.append(StringUtil.merge(reportings));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_REPO,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_F_REPO,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the opencps dossier statistics where reporting = &#63; from the database.
	 *
	 * @param reporting the reporting
	 */
	@Override
	public void removeByF_REPO(int reporting) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByF_REPO(
				reporting, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where reporting = &#63;.
	 *
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByF_REPO(int reporting) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_REPO;

		Object[] finderArgs = new Object[] { reporting };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_F_REPO_REPORTING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(reporting);

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

	/**
	 * Returns the number of opencps dossier statistics where reporting = any &#63;.
	 *
	 * @param reportings the reportings
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByF_REPO(int[] reportings) {
		if (reportings == null) {
			reportings = new int[0];
		}
		else if (reportings.length > 1) {
			reportings = ArrayUtil.unique(reportings);

			Arrays.sort(reportings);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(reportings) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_REPO,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			if (reportings.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_F_REPO_REPORTING_7);

				query.append(StringUtil.merge(reportings));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_REPO,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_REPO,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_REPO_REPORTING_2 = "opencpsDossierStatistic.reporting = ?";
	private static final String _FINDER_COLUMN_F_REPO_REPORTING_7 = "opencpsDossierStatistic.reporting IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NOT_GID =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_NOT_GID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_NOT_GID =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_NOT_GID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the opencps dossier statistics where groupId &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_NOT_GID(long groupId) {
		return findByF_NOT_GID(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_NOT_GID(long groupId,
		int start, int end) {
		return findByF_NOT_GID(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_NOT_GID(long groupId,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByF_NOT_GID(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByF_NOT_GID(long groupId,
		int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_NOT_GID;
		finderArgs = new Object[] { groupId, start, end, orderByComparator };

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId == opencpsDossierStatistic.getGroupId())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_F_NOT_GID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByF_NOT_GID_First(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByF_NOT_GID_First(groupId,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByF_NOT_GID_First(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByF_NOT_GID(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByF_NOT_GID_Last(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByF_NOT_GID_Last(groupId,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByF_NOT_GID_Last(long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByF_NOT_GID(groupId);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByF_NOT_GID(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId &ne; &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByF_NOT_GID_PrevAndNext(
		long dossierStatisticId, long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByF_NOT_GID_PrevAndNext(session,
					opencpsDossierStatistic, groupId, orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByF_NOT_GID_PrevAndNext(session,
					opencpsDossierStatistic, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatistic getByF_NOT_GID_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long groupId,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_F_NOT_GID_GROUPID_2);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId &ne; &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_NOT_GID(long groupId) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByF_NOT_GID(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByF_NOT_GID(long groupId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_F_NOT_GID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_F_NOT_GID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_NOT_GID_GROUPID_2 = "opencpsDossierStatistic.groupId != ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_M_Y_GOV_DOM_GRO_SYS =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByM_Y_GOV_DOM_GRO_SYS",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_Y_GOV_DOM_GRO_SYS =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByM_Y_GOV_DOM_GRO_SYS",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			},
			OpencpsDossierStatisticModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.DOMAINCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GROUPAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.SYSTEM_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_Y_GOV_DOM_GRO_SYS = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByM_Y_GOV_DOM_GRO_SYS",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByM_Y_GOV_DOM_GRO_SYS(int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		return findByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByM_Y_GOV_DOM_GRO_SYS(int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end) {
		return findByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByM_Y_GOV_DOM_GRO_SYS(int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
			domainCode, groupAgencyCode, system, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByM_Y_GOV_DOM_GRO_SYS(int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_Y_GOV_DOM_GRO_SYS;
			finderArgs = new Object[] {
					month, year, govAgencyCode, domainCode, groupAgencyCode,
					system
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_M_Y_GOV_DOM_GRO_SYS;
			finderArgs = new Object[] {
					month, year, govAgencyCode, domainCode, groupAgencyCode,
					system,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((month != opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear()) ||
							!Objects.equals(govAgencyCode,
								opencpsDossierStatistic.getGovAgencyCode()) ||
							!Objects.equals(domainCode,
								opencpsDossierStatistic.getDomainCode()) ||
							!Objects.equals(groupAgencyCode,
								opencpsDossierStatistic.getGroupAgencyCode()) ||
							!Objects.equals(system,
								opencpsDossierStatistic.getSystem())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(8 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(8);
			}

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_2);
			}

			boolean bindGroupAgencyCode = false;

			if (groupAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_1);
			}
			else if (groupAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_3);
			}
			else {
				bindGroupAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(month);

				qPos.add(year);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindGroupAgencyCode) {
					qPos.add(groupAgencyCode);
				}

				if (bindSystem) {
					qPos.add(system);
				}

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByM_Y_GOV_DOM_GRO_SYS_First(int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByM_Y_GOV_DOM_GRO_SYS_First(month,
				year, govAgencyCode, domainCode, groupAgencyCode, system,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", domainCode=");
		msg.append(domainCode);

		msg.append(", groupAgencyCode=");
		msg.append(groupAgencyCode);

		msg.append(", system=");
		msg.append(system);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByM_Y_GOV_DOM_GRO_SYS_First(int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByM_Y_GOV_DOM_GRO_SYS(month,
				year, govAgencyCode, domainCode, groupAgencyCode, system, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByM_Y_GOV_DOM_GRO_SYS_Last(int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByM_Y_GOV_DOM_GRO_SYS_Last(month,
				year, govAgencyCode, domainCode, groupAgencyCode, system,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", domainCode=");
		msg.append(domainCode);

		msg.append(", groupAgencyCode=");
		msg.append(groupAgencyCode);

		msg.append(", system=");
		msg.append(system);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByM_Y_GOV_DOM_GRO_SYS_Last(int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByM_Y_GOV_DOM_GRO_SYS(month, year, govAgencyCode,
				domainCode, groupAgencyCode, system);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByM_Y_GOV_DOM_GRO_SYS(month,
				year, govAgencyCode, domainCode, groupAgencyCode, system,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByM_Y_GOV_DOM_GRO_SYS_PrevAndNext(
		long dossierStatisticId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByM_Y_GOV_DOM_GRO_SYS_PrevAndNext(session,
					opencpsDossierStatistic, month, year, govAgencyCode,
					domainCode, groupAgencyCode, system, orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByM_Y_GOV_DOM_GRO_SYS_PrevAndNext(session,
					opencpsDossierStatistic, month, year, govAgencyCode,
					domainCode, groupAgencyCode, system, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatistic getByM_Y_GOV_DOM_GRO_SYS_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		int month, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(9 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(8);
		}

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_MONTH_2);

		query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_YEAR_2);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_2);
		}

		boolean bindDomainCode = false;

		if (domainCode == null) {
			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_1);
		}
		else if (domainCode.equals("")) {
			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_3);
		}
		else {
			bindDomainCode = true;

			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_2);
		}

		boolean bindGroupAgencyCode = false;

		if (groupAgencyCode == null) {
			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_1);
		}
		else if (groupAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_3);
		}
		else {
			bindGroupAgencyCode = true;

			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_2);
		}

		boolean bindSystem = false;

		if (system == null) {
			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_1);
		}
		else if (system.equals("")) {
			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_3);
		}
		else {
			bindSystem = true;

			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_2);
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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(month);

		qPos.add(year);

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (bindDomainCode) {
			qPos.add(domainCode);
		}

		if (bindGroupAgencyCode) {
			qPos.add(groupAgencyCode);
		}

		if (bindSystem) {
			qPos.add(system);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63; from the database.
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 */
	@Override
	public void removeByM_Y_GOV_DOM_GRO_SYS(int month, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByM_Y_GOV_DOM_GRO_SYS(
				month, year, govAgencyCode, domainCode, groupAgencyCode,
				system, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByM_Y_GOV_DOM_GRO_SYS(int month, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_Y_GOV_DOM_GRO_SYS;

		Object[] finderArgs = new Object[] {
				month, year, govAgencyCode, domainCode, groupAgencyCode, system
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_MONTH_2);

			query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_2);
			}

			boolean bindGroupAgencyCode = false;

			if (groupAgencyCode == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_1);
			}
			else if (groupAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_3);
			}
			else {
				bindGroupAgencyCode = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(month);

				qPos.add(year);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindGroupAgencyCode) {
					qPos.add(groupAgencyCode);
				}

				if (bindSystem) {
					qPos.add(system);
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

	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_YEAR_2 = "opencpsDossierStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_1 =
		"opencpsDossierStatistic.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_2 =
		"opencpsDossierStatistic.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_3 =
		"(opencpsDossierStatistic.govAgencyCode IS NULL OR opencpsDossierStatistic.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_1 = "opencpsDossierStatistic.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_2 = "opencpsDossierStatistic.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_3 = "(opencpsDossierStatistic.domainCode IS NULL OR opencpsDossierStatistic.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_1 =
		"opencpsDossierStatistic.groupAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_2 =
		"opencpsDossierStatistic.groupAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_3 =
		"(opencpsDossierStatistic.groupAgencyCode IS NULL OR opencpsDossierStatistic.groupAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_1 = "opencpsDossierStatistic.system IS NULL";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_2 = "opencpsDossierStatistic.system = ?";
	private static final String _FINDER_COLUMN_M_Y_GOV_DOM_GRO_SYS_SYSTEM_3 = "(opencpsDossierStatistic.system IS NULL OR opencpsDossierStatistic.system = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NOT_G_M_Y_GOV_DOM_GRO_SYS =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByNOT_G_M_Y_GOV_DOM_GRO_SYS",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOT_G_M_Y_GOV_DOM_GRO_SYS =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByNOT_G_M_Y_GOV_DOM_GRO_SYS",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByNOT_G_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system) {
		return findByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByNOT_G_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end) {
		return findByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByNOT_G_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
			govAgencyCode, domainCode, groupAgencyCode, system, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByNOT_G_M_Y_GOV_DOM_GRO_SYS(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system, int start,
		int end, OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NOT_G_M_Y_GOV_DOM_GRO_SYS;
		finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode, groupAgencyCode,
				system,
				
				start, end, orderByComparator
			};

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId == opencpsDossierStatistic.getGroupId()) ||
							(month != opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear()) ||
							!Objects.equals(govAgencyCode,
								opencpsDossierStatistic.getGovAgencyCode()) ||
							!Objects.equals(domainCode,
								opencpsDossierStatistic.getDomainCode()) ||
							!Objects.equals(groupAgencyCode,
								opencpsDossierStatistic.getGroupAgencyCode()) ||
							!Objects.equals(system,
								opencpsDossierStatistic.getSystem())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(9 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(9);
			}

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPID_2);

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_MONTH_2);

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_2);
			}

			boolean bindGroupAgencyCode = false;

			if (groupAgencyCode == null) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_1);
			}
			else if (groupAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_3);
			}
			else {
				bindGroupAgencyCode = true;

				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindGroupAgencyCode) {
					qPos.add(groupAgencyCode);
				}

				if (bindSystem) {
					qPos.add(system);
				}

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByNOT_G_M_Y_GOV_DOM_GRO_SYS_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByNOT_G_M_Y_GOV_DOM_GRO_SYS_First(groupId,
				month, year, govAgencyCode, domainCode, groupAgencyCode,
				system, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(16);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", domainCode=");
		msg.append(domainCode);

		msg.append(", groupAgencyCode=");
		msg.append(groupAgencyCode);

		msg.append(", system=");
		msg.append(system);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByNOT_G_M_Y_GOV_DOM_GRO_SYS_First(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId,
				month, year, govAgencyCode, domainCode, groupAgencyCode,
				system, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByNOT_G_M_Y_GOV_DOM_GRO_SYS_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByNOT_G_M_Y_GOV_DOM_GRO_SYS_Last(groupId,
				month, year, govAgencyCode, domainCode, groupAgencyCode,
				system, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(16);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", domainCode=");
		msg.append(domainCode);

		msg.append(", groupAgencyCode=");
		msg.append(groupAgencyCode);

		msg.append(", system=");
		msg.append(system);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByNOT_G_M_Y_GOV_DOM_GRO_SYS_Last(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, month, year,
				govAgencyCode, domainCode, groupAgencyCode, system);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId,
				month, year, govAgencyCode, domainCode, groupAgencyCode,
				system, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByNOT_G_M_Y_GOV_DOM_GRO_SYS_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByNOT_G_M_Y_GOV_DOM_GRO_SYS_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year,
					govAgencyCode, domainCode, groupAgencyCode, system,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByNOT_G_M_Y_GOV_DOM_GRO_SYS_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year,
					govAgencyCode, domainCode, groupAgencyCode, system,
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

	protected OpencpsDossierStatistic getByNOT_G_M_Y_GOV_DOM_GRO_SYS_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(10 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(9);
		}

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPID_2);

		query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_MONTH_2);

		query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_YEAR_2);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_2);
		}

		boolean bindDomainCode = false;

		if (domainCode == null) {
			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_1);
		}
		else if (domainCode.equals("")) {
			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_3);
		}
		else {
			bindDomainCode = true;

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_2);
		}

		boolean bindGroupAgencyCode = false;

		if (groupAgencyCode == null) {
			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_1);
		}
		else if (groupAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_3);
		}
		else {
			bindGroupAgencyCode = true;

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_2);
		}

		boolean bindSystem = false;

		if (system == null) {
			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_1);
		}
		else if (system.equals("")) {
			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_3);
		}
		else {
			bindSystem = true;

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_2);
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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(month);

		qPos.add(year);

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (bindDomainCode) {
			qPos.add(domainCode);
		}

		if (bindGroupAgencyCode) {
			qPos.add(groupAgencyCode);
		}

		if (bindSystem) {
			qPos.add(system);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 */
	@Override
	public void removeByNOT_G_M_Y_GOV_DOM_GRO_SYS(long groupId, int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByNOT_G_M_Y_GOV_DOM_GRO_SYS(
				groupId, month, year, govAgencyCode, domainCode,
				groupAgencyCode, system, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId &ne; &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByNOT_G_M_Y_GOV_DOM_GRO_SYS(long groupId, int month,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_NOT_G_M_Y_GOV_DOM_GRO_SYS;

		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode, groupAgencyCode,
				system
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPID_2);

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_MONTH_2);

			query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_2);
			}

			boolean bindGroupAgencyCode = false;

			if (groupAgencyCode == null) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_1);
			}
			else if (groupAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_3);
			}
			else {
				bindGroupAgencyCode = true;

				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_2);
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

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindGroupAgencyCode) {
					qPos.add(groupAgencyCode);
				}

				if (bindSystem) {
					qPos.add(system);
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

	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPID_2 =
		"opencpsDossierStatistic.groupId != ? AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_MONTH_2 =
		"opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_YEAR_2 = "opencpsDossierStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_1 =
		"opencpsDossierStatistic.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_2 =
		"opencpsDossierStatistic.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GOVAGENCYCODE_3 =
		"(opencpsDossierStatistic.govAgencyCode IS NULL OR opencpsDossierStatistic.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_1 =
		"opencpsDossierStatistic.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_2 =
		"opencpsDossierStatistic.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_DOMAINCODE_3 =
		"(opencpsDossierStatistic.domainCode IS NULL OR opencpsDossierStatistic.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_1 =
		"opencpsDossierStatistic.groupAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_2 =
		"opencpsDossierStatistic.groupAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_GROUPAGENCYCODE_3 =
		"(opencpsDossierStatistic.groupAgencyCode IS NULL OR opencpsDossierStatistic.groupAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_1 =
		"opencpsDossierStatistic.system IS NULL";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_2 =
		"opencpsDossierStatistic.system = ?";
	private static final String _FINDER_COLUMN_NOT_G_M_Y_GOV_DOM_GRO_SYS_SYSTEM_3 =
		"(opencpsDossierStatistic.system IS NULL OR opencpsDossierStatistic.system = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_M_Y_NOT_RP =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGID_M_Y_NOT_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_M_Y_NOT_RP =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGID_M_Y_NOT_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_M_Y_NOT_RP(long groupId,
		int month, int year, int reporting) {
		return findByGID_M_Y_NOT_RP(groupId, month, year, reporting,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_M_Y_NOT_RP(long groupId,
		int month, int year, int reporting, int start, int end) {
		return findByGID_M_Y_NOT_RP(groupId, month, year, reporting, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_M_Y_NOT_RP(long groupId,
		int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByGID_M_Y_NOT_RP(groupId, month, year, reporting, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByGID_M_Y_NOT_RP(long groupId,
		int month, int year, int reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_M_Y_NOT_RP;
		finderArgs = new Object[] {
				groupId, month, year, reporting,
				
				start, end, orderByComparator
			};

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							(month != opencpsDossierStatistic.getMonth()) ||
							(year != opencpsDossierStatistic.getYear()) ||
							(reporting == opencpsDossierStatistic.getReporting())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_MONTH_2);

			query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_YEAR_2);

			query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_REPORTING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
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

				qPos.add(reporting);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByGID_M_Y_NOT_RP_First(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByGID_M_Y_NOT_RP_First(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", reporting=");
		msg.append(reporting);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByGID_M_Y_NOT_RP_First(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByGID_M_Y_NOT_RP(groupId,
				month, year, reporting, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByGID_M_Y_NOT_RP_Last(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByGID_M_Y_NOT_RP_Last(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", reporting=");
		msg.append(reporting);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByGID_M_Y_NOT_RP_Last(long groupId,
		int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByGID_M_Y_NOT_RP(groupId, month, year, reporting);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByGID_M_Y_NOT_RP(groupId,
				month, year, reporting, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByGID_M_Y_NOT_RP_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByGID_M_Y_NOT_RP_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year, reporting,
					orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByGID_M_Y_NOT_RP_PrevAndNext(session,
					opencpsDossierStatistic, groupId, month, year, reporting,
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

	protected OpencpsDossierStatistic getByGID_M_Y_NOT_RP_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long groupId, int month, int year, int reporting,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_GROUPID_2);

		query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_MONTH_2);

		query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_YEAR_2);

		query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_REPORTING_2);

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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(month);

		qPos.add(year);

		qPos.add(reporting);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 */
	@Override
	public void removeByGID_M_Y_NOT_RP(long groupId, int month, int year,
		int reporting) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByGID_M_Y_NOT_RP(
				groupId, month, year, reporting, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and month = &#63; and year = &#63; and reporting &ne; &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByGID_M_Y_NOT_RP(long groupId, int month, int year,
		int reporting) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_M_Y_NOT_RP;

		Object[] finderArgs = new Object[] { groupId, month, year, reporting };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_MONTH_2);

			query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_YEAR_2);

			query.append(_FINDER_COLUMN_GID_M_Y_NOT_RP_REPORTING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

				qPos.add(reporting);

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

	private static final String _FINDER_COLUMN_GID_M_Y_NOT_RP_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_M_Y_NOT_RP_MONTH_2 = "opencpsDossierStatistic.month = ? AND ";
	private static final String _FINDER_COLUMN_GID_M_Y_NOT_RP_YEAR_2 = "opencpsDossierStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_GID_M_Y_NOT_RP_REPORTING_2 = "opencpsDossierStatistic.reporting != ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_Y_GO_DO_GR_SY",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_Y_GO_DO_GR_SY",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			},
			OpencpsDossierStatisticModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.DOMAINCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.GROUPAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticModelImpl.SYSTEM_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_Y_GO_DO_GR_SY = new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_Y_GO_DO_GR_SY",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_GO_DO_GR_SY =
		new FinderPath(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_Y_GO_DO_GR_SY",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(long groupId,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system) {
		return findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode, domainCode,
			groupAgencyCode, system, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(long groupId,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end) {
		return findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode, domainCode,
			groupAgencyCode, system, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(long groupId,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode, domainCode,
			groupAgencyCode, system, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(long groupId,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY;
			finderArgs = new Object[] {
					groupId, year, govAgencyCode, domainCode, groupAgencyCode,
					system
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY;
			finderArgs = new Object[] {
					groupId, year, govAgencyCode, domainCode, groupAgencyCode,
					system,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							(year != opencpsDossierStatistic.getYear()) ||
							!Objects.equals(govAgencyCode,
								opencpsDossierStatistic.getGovAgencyCode()) ||
							!Objects.equals(domainCode,
								opencpsDossierStatistic.getDomainCode()) ||
							!Objects.equals(groupAgencyCode,
								opencpsDossierStatistic.getGroupAgencyCode()) ||
							!Objects.equals(system,
								opencpsDossierStatistic.getSystem())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(8 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(8);
			}

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_2);
			}

			boolean bindGroupAgencyCode = false;

			if (groupAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_1);
			}
			else if (groupAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_3);
			}
			else {
				bindGroupAgencyCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(year);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindGroupAgencyCode) {
					qPos.add(groupAgencyCode);
				}

				if (bindSystem) {
					qPos.add(system);
				}

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_Y_GO_DO_GR_SY_First(long groupId,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_Y_GO_DO_GR_SY_First(groupId,
				year, govAgencyCode, domainCode, groupAgencyCode, system,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", year=");
		msg.append(year);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", domainCode=");
		msg.append(domainCode);

		msg.append(", groupAgencyCode=");
		msg.append(groupAgencyCode);

		msg.append(", system=");
		msg.append(system);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_Y_GO_DO_GR_SY_First(long groupId,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		List<OpencpsDossierStatistic> list = findByG_Y_GO_DO_GR_SY(groupId,
				year, govAgencyCode, domainCode, groupAgencyCode, system, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByG_Y_GO_DO_GR_SY_Last(long groupId,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByG_Y_GO_DO_GR_SY_Last(groupId,
				year, govAgencyCode, domainCode, groupAgencyCode, system,
				orderByComparator);

		if (opencpsDossierStatistic != null) {
			return opencpsDossierStatistic;
		}

		StringBundler msg = new StringBundler(14);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", year=");
		msg.append(year);

		msg.append(", govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append(", domainCode=");
		msg.append(domainCode);

		msg.append(", groupAgencyCode=");
		msg.append(groupAgencyCode);

		msg.append(", system=");
		msg.append(system);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByG_Y_GO_DO_GR_SY_Last(long groupId,
		int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		int count = countByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCode,
				domainCode, groupAgencyCode, system);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatistic> list = findByG_Y_GO_DO_GR_SY(groupId,
				year, govAgencyCode, domainCode, groupAgencyCode, system,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic[] findByG_Y_GO_DO_GR_SY_PrevAndNext(
		long dossierStatisticId, long groupId, int year, String govAgencyCode,
		String domainCode, String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic[] array = new OpencpsDossierStatisticImpl[3];

			array[0] = getByG_Y_GO_DO_GR_SY_PrevAndNext(session,
					opencpsDossierStatistic, groupId, year, govAgencyCode,
					domainCode, groupAgencyCode, system, orderByComparator, true);

			array[1] = opencpsDossierStatistic;

			array[2] = getByG_Y_GO_DO_GR_SY_PrevAndNext(session,
					opencpsDossierStatistic, groupId, year, govAgencyCode,
					domainCode, groupAgencyCode, system, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatistic getByG_Y_GO_DO_GR_SY_PrevAndNext(
		Session session, OpencpsDossierStatistic opencpsDossierStatistic,
		long groupId, int year, String govAgencyCode, String domainCode,
		String groupAgencyCode, String system,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(9 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(8);
		}

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

		query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPID_2);

		query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_YEAR_2);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_2);
		}

		boolean bindDomainCode = false;

		if (domainCode == null) {
			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_1);
		}
		else if (domainCode.equals("")) {
			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_3);
		}
		else {
			bindDomainCode = true;

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_2);
		}

		boolean bindGroupAgencyCode = false;

		if (groupAgencyCode == null) {
			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_1);
		}
		else if (groupAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_3);
		}
		else {
			bindGroupAgencyCode = true;

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_2);
		}

		boolean bindSystem = false;

		if (system == null) {
			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_1);
		}
		else if (system.equals("")) {
			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_3);
		}
		else {
			bindSystem = true;

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_2);
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
			query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(year);

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (bindDomainCode) {
			qPos.add(domainCode);
		}

		if (bindGroupAgencyCode) {
			qPos.add(groupAgencyCode);
		}

		if (bindSystem) {
			qPos.add(system);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatistic);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatistic> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = any &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCodes the gov agency codes
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @return the matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(long groupId,
		int year, String[] govAgencyCodes, String domainCode,
		String groupAgencyCode, String system) {
		return findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCodes, domainCode,
			groupAgencyCode, system, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = any &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCodes the gov agency codes
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(long groupId,
		int year, String[] govAgencyCodes, String domainCode,
		String groupAgencyCode, String system, int start, int end) {
		return findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCodes, domainCode,
			groupAgencyCode, system, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = any &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCodes the gov agency codes
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(long groupId,
		int year, String[] govAgencyCodes, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCodes, domainCode,
			groupAgencyCode, system, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findByG_Y_GO_DO_GR_SY(long groupId,
		int year, String[] govAgencyCodes, String domainCode,
		String groupAgencyCode, String system, int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache) {
		if (govAgencyCodes == null) {
			govAgencyCodes = new String[0];
		}
		else if (govAgencyCodes.length > 1) {
			govAgencyCodes = ArrayUtil.distinct(govAgencyCodes,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(govAgencyCodes, NULL_SAFE_STRING_COMPARATOR);
		}

		if (govAgencyCodes.length == 1) {
			return findByG_Y_GO_DO_GR_SY(groupId, year, govAgencyCodes[0],
				domainCode, groupAgencyCode, system, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, year, StringUtil.merge(govAgencyCodes), domainCode,
					groupAgencyCode, system
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, year, StringUtil.merge(govAgencyCodes), domainCode,
					groupAgencyCode, system,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatistic opencpsDossierStatistic : list) {
					if ((groupId != opencpsDossierStatistic.getGroupId()) ||
							(year != opencpsDossierStatistic.getYear()) ||
							!ArrayUtil.contains(govAgencyCodes,
								opencpsDossierStatistic.getGovAgencyCode()) ||
							!Objects.equals(domainCode,
								opencpsDossierStatistic.getDomainCode()) ||
							!Objects.equals(groupAgencyCode,
								opencpsDossierStatistic.getGroupAgencyCode()) ||
							!Objects.equals(system,
								opencpsDossierStatistic.getSystem())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_YEAR_2);

			if (govAgencyCodes.length > 0) {
				query.append("(");

				for (int i = 0; i < govAgencyCodes.length; i++) {
					String govAgencyCode = govAgencyCodes[i];

					if (govAgencyCode == null) {
						query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_4);
					}
					else if (govAgencyCode.equals("")) {
						query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_5);
					}

					if ((i + 1) < govAgencyCodes.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_2);
			}

			boolean bindGroupAgencyCode = false;

			if (groupAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_1);
			}
			else if (groupAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_3);
			}
			else {
				bindGroupAgencyCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(year);

				for (String govAgencyCode : govAgencyCodes) {
					if ((govAgencyCode != null) && !govAgencyCode.isEmpty()) {
						qPos.add(govAgencyCode);
					}
				}

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindGroupAgencyCode) {
					qPos.add(groupAgencyCode);
				}

				if (bindSystem) {
					qPos.add(system);
				}

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 */
	@Override
	public void removeByG_Y_GO_DO_GR_SY(long groupId, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findByG_Y_GO_DO_GR_SY(
				groupId, year, govAgencyCode, domainCode, groupAgencyCode,
				system, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG_Y_GO_DO_GR_SY(long groupId, int year,
		String govAgencyCode, String domainCode, String groupAgencyCode,
		String system) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_Y_GO_DO_GR_SY;

		Object[] finderArgs = new Object[] {
				groupId, year, govAgencyCode, domainCode, groupAgencyCode,
				system
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_2);
			}

			boolean bindGroupAgencyCode = false;

			if (groupAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_1);
			}
			else if (groupAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_3);
			}
			else {
				bindGroupAgencyCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(year);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindGroupAgencyCode) {
					qPos.add(groupAgencyCode);
				}

				if (bindSystem) {
					qPos.add(system);
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

	/**
	 * Returns the number of opencps dossier statistics where groupId = &#63; and year = &#63; and govAgencyCode = any &#63; and domainCode = &#63; and groupAgencyCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param govAgencyCodes the gov agency codes
	 * @param domainCode the domain code
	 * @param groupAgencyCode the group agency code
	 * @param system the system
	 * @return the number of matching opencps dossier statistics
	 */
	@Override
	public int countByG_Y_GO_DO_GR_SY(long groupId, int year,
		String[] govAgencyCodes, String domainCode, String groupAgencyCode,
		String system) {
		if (govAgencyCodes == null) {
			govAgencyCodes = new String[0];
		}
		else if (govAgencyCodes.length > 1) {
			govAgencyCodes = ArrayUtil.distinct(govAgencyCodes,
					NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(govAgencyCodes, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				groupId, year, StringUtil.merge(govAgencyCodes), domainCode,
				groupAgencyCode, system
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_GO_DO_GR_SY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE);

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_YEAR_2);

			if (govAgencyCodes.length > 0) {
				query.append("(");

				for (int i = 0; i < govAgencyCodes.length; i++) {
					String govAgencyCode = govAgencyCodes[i];

					if (govAgencyCode == null) {
						query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_4);
					}
					else if (govAgencyCode.equals("")) {
						query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_5);
					}

					if ((i + 1) < govAgencyCodes.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(")");

				query.append(WHERE_AND);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_2);
			}

			boolean bindGroupAgencyCode = false;

			if (groupAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_1);
			}
			else if (groupAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_3);
			}
			else {
				bindGroupAgencyCode = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_2);
			}

			boolean bindSystem = false;

			if (system == null) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_1);
			}
			else if (system.equals("")) {
				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_3);
			}
			else {
				bindSystem = true;

				query.append(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(year);

				for (String govAgencyCode : govAgencyCodes) {
					if ((govAgencyCode != null) && !govAgencyCode.isEmpty()) {
						qPos.add(govAgencyCode);
					}
				}

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				if (bindGroupAgencyCode) {
					qPos.add(groupAgencyCode);
				}

				if (bindSystem) {
					qPos.add(system);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_GO_DO_GR_SY,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_GO_DO_GR_SY,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPID_2 = "opencpsDossierStatistic.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_YEAR_2 = "opencpsDossierStatistic.year = ? AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_1 = "opencpsDossierStatistic.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_2 = "opencpsDossierStatistic.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_3 = "(opencpsDossierStatistic.govAgencyCode IS NULL OR opencpsDossierStatistic.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_1) +
		")";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_2) +
		")";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_Y_GO_DO_GR_SY_GOVAGENCYCODE_3) +
		")";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_1 = "opencpsDossierStatistic.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_2 = "opencpsDossierStatistic.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_DOMAINCODE_3 = "(opencpsDossierStatistic.domainCode IS NULL OR opencpsDossierStatistic.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_1 =
		"opencpsDossierStatistic.groupAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_2 =
		"opencpsDossierStatistic.groupAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_GROUPAGENCYCODE_3 =
		"(opencpsDossierStatistic.groupAgencyCode IS NULL OR opencpsDossierStatistic.groupAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_1 = "opencpsDossierStatistic.system IS NULL";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_2 = "opencpsDossierStatistic.system = ?";
	private static final String _FINDER_COLUMN_G_Y_GO_DO_GR_SY_SYSTEM_3 = "(opencpsDossierStatistic.system IS NULL OR opencpsDossierStatistic.system = '')";

	public OpencpsDossierStatisticPersistenceImpl() {
		setModelClass(OpencpsDossierStatistic.class);

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
	 * Caches the opencps dossier statistic in the entity cache if it is enabled.
	 *
	 * @param opencpsDossierStatistic the opencps dossier statistic
	 */
	@Override
	public void cacheResult(OpencpsDossierStatistic opencpsDossierStatistic) {
		entityCache.putResult(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			opencpsDossierStatistic.getPrimaryKey(), opencpsDossierStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				opencpsDossierStatistic.getUuid(),
				opencpsDossierStatistic.getGroupId()
			}, opencpsDossierStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
			new Object[] {
				opencpsDossierStatistic.getGroupId(),
				opencpsDossierStatistic.getGovAgencyCode(),
				opencpsDossierStatistic.getMonth(),
				opencpsDossierStatistic.getYear(),
				opencpsDossierStatistic.getDomainCode(),
				opencpsDossierStatistic.getReporting()
			}, opencpsDossierStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D,
			new Object[] {
				opencpsDossierStatistic.getGroupId(),
				opencpsDossierStatistic.getMonth(),
				opencpsDossierStatistic.getYear(),
				opencpsDossierStatistic.getGovAgencyCode(),
				opencpsDossierStatistic.getDomainCode()
			}, opencpsDossierStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S,
			new Object[] {
				opencpsDossierStatistic.getGroupId(),
				opencpsDossierStatistic.getMonth(),
				opencpsDossierStatistic.getYear(),
				opencpsDossierStatistic.getGovAgencyCode(),
				opencpsDossierStatistic.getDomainCode(),
				opencpsDossierStatistic.getSystem()
			}, opencpsDossierStatistic);

		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_G,
			new Object[] {
				opencpsDossierStatistic.getGroupId(),
				opencpsDossierStatistic.getGovAgencyCode(),
				opencpsDossierStatistic.getMonth(),
				opencpsDossierStatistic.getYear()
			}, opencpsDossierStatistic);

		opencpsDossierStatistic.resetOriginalValues();
	}

	/**
	 * Caches the opencps dossier statistics in the entity cache if it is enabled.
	 *
	 * @param opencpsDossierStatistics the opencps dossier statistics
	 */
	@Override
	public void cacheResult(
		List<OpencpsDossierStatistic> opencpsDossierStatistics) {
		for (OpencpsDossierStatistic opencpsDossierStatistic : opencpsDossierStatistics) {
			if (entityCache.getResult(
						OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsDossierStatisticImpl.class,
						opencpsDossierStatistic.getPrimaryKey()) == null) {
				cacheResult(opencpsDossierStatistic);
			}
			else {
				opencpsDossierStatistic.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all opencps dossier statistics.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OpencpsDossierStatisticImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the opencps dossier statistic.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OpencpsDossierStatistic opencpsDossierStatistic) {
		entityCache.removeResult(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			opencpsDossierStatistic.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OpencpsDossierStatisticModelImpl)opencpsDossierStatistic,
			true);
	}

	@Override
	public void clearCache(
		List<OpencpsDossierStatistic> opencpsDossierStatistics) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OpencpsDossierStatistic opencpsDossierStatistic : opencpsDossierStatistics) {
			entityCache.removeResult(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsDossierStatisticImpl.class,
				opencpsDossierStatistic.getPrimaryKey());

			clearUniqueFindersCache((OpencpsDossierStatisticModelImpl)opencpsDossierStatistic,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OpencpsDossierStatisticModelImpl opencpsDossierStatisticModelImpl) {
		Object[] args = new Object[] {
				opencpsDossierStatisticModelImpl.getUuid(),
				opencpsDossierStatisticModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			opencpsDossierStatisticModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticModelImpl.getGroupId(),
				opencpsDossierStatisticModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticModelImpl.getMonth(),
				opencpsDossierStatisticModelImpl.getYear(),
				opencpsDossierStatisticModelImpl.getDomainCode(),
				opencpsDossierStatisticModelImpl.getReporting()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_M_Y_DM_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_DM_G, args,
			opencpsDossierStatisticModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticModelImpl.getGroupId(),
				opencpsDossierStatisticModelImpl.getMonth(),
				opencpsDossierStatisticModelImpl.getYear(),
				opencpsDossierStatisticModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticModelImpl.getDomainCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D, args,
			opencpsDossierStatisticModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticModelImpl.getGroupId(),
				opencpsDossierStatisticModelImpl.getMonth(),
				opencpsDossierStatisticModelImpl.getYear(),
				opencpsDossierStatisticModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticModelImpl.getDomainCode(),
				opencpsDossierStatisticModelImpl.getSystem()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D_S, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S, args,
			opencpsDossierStatisticModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticModelImpl.getGroupId(),
				opencpsDossierStatisticModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticModelImpl.getMonth(),
				opencpsDossierStatisticModelImpl.getYear()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_M_Y_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_G, args,
			opencpsDossierStatisticModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OpencpsDossierStatisticModelImpl opencpsDossierStatisticModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getUuid(),
					opencpsDossierStatisticModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getOriginalUuid(),
					opencpsDossierStatisticModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear(),
					opencpsDossierStatisticModelImpl.getDomainCode(),
					opencpsDossierStatisticModelImpl.getReporting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_DM_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_DM_G, args);
		}

		if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_M_Y_DM_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getOriginalMonth(),
					opencpsDossierStatisticModelImpl.getOriginalYear(),
					opencpsDossierStatisticModelImpl.getOriginalDomainCode(),
					opencpsDossierStatisticModelImpl.getOriginalReporting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_DM_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_DM_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear(),
					opencpsDossierStatisticModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getDomainCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D, args);
		}

		if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_M_Y_G_D.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticModelImpl.getOriginalMonth(),
					opencpsDossierStatisticModelImpl.getOriginalYear(),
					opencpsDossierStatisticModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getOriginalDomainCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear(),
					opencpsDossierStatisticModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getDomainCode(),
					opencpsDossierStatisticModelImpl.getSystem()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S, args);
		}

		if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_M_Y_G_D_S.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticModelImpl.getOriginalMonth(),
					opencpsDossierStatisticModelImpl.getOriginalYear(),
					opencpsDossierStatisticModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getOriginalDomainCode(),
					opencpsDossierStatisticModelImpl.getOriginalSystem()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_G, args);
		}

		if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_M_Y_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getOriginalMonth(),
					opencpsDossierStatisticModelImpl.getOriginalYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_G, args);
		}
	}

	/**
	 * Creates a new opencps dossier statistic with the primary key. Does not add the opencps dossier statistic to the database.
	 *
	 * @param dossierStatisticId the primary key for the new opencps dossier statistic
	 * @return the new opencps dossier statistic
	 */
	@Override
	public OpencpsDossierStatistic create(long dossierStatisticId) {
		OpencpsDossierStatistic opencpsDossierStatistic = new OpencpsDossierStatisticImpl();

		opencpsDossierStatistic.setNew(true);
		opencpsDossierStatistic.setPrimaryKey(dossierStatisticId);

		String uuid = PortalUUIDUtil.generate();

		opencpsDossierStatistic.setUuid(uuid);

		opencpsDossierStatistic.setCompanyId(companyProvider.getCompanyId());

		return opencpsDossierStatistic;
	}

	/**
	 * Removes the opencps dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierStatisticId the primary key of the opencps dossier statistic
	 * @return the opencps dossier statistic that was removed
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic remove(long dossierStatisticId)
		throws NoSuchOpencpsDossierStatisticException {
		return remove((Serializable)dossierStatisticId);
	}

	/**
	 * Removes the opencps dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the opencps dossier statistic
	 * @return the opencps dossier statistic that was removed
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic remove(Serializable primaryKey)
		throws NoSuchOpencpsDossierStatisticException {
		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatistic opencpsDossierStatistic = (OpencpsDossierStatistic)session.get(OpencpsDossierStatisticImpl.class,
					primaryKey);

			if (opencpsDossierStatistic == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOpencpsDossierStatisticException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(opencpsDossierStatistic);
		}
		catch (NoSuchOpencpsDossierStatisticException nsee) {
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
	protected OpencpsDossierStatistic removeImpl(
		OpencpsDossierStatistic opencpsDossierStatistic) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(opencpsDossierStatistic)) {
				opencpsDossierStatistic = (OpencpsDossierStatistic)session.get(OpencpsDossierStatisticImpl.class,
						opencpsDossierStatistic.getPrimaryKeyObj());
			}

			if (opencpsDossierStatistic != null) {
				session.delete(opencpsDossierStatistic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (opencpsDossierStatistic != null) {
			clearCache(opencpsDossierStatistic);
		}

		return opencpsDossierStatistic;
	}

	@Override
	public OpencpsDossierStatistic updateImpl(
		OpencpsDossierStatistic opencpsDossierStatistic) {
		boolean isNew = opencpsDossierStatistic.isNew();

		if (!(opencpsDossierStatistic instanceof OpencpsDossierStatisticModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(opencpsDossierStatistic.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(opencpsDossierStatistic);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in opencpsDossierStatistic proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OpencpsDossierStatistic implementation " +
				opencpsDossierStatistic.getClass());
		}

		OpencpsDossierStatisticModelImpl opencpsDossierStatisticModelImpl = (OpencpsDossierStatisticModelImpl)opencpsDossierStatistic;

		if (Validator.isNull(opencpsDossierStatistic.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			opencpsDossierStatistic.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (opencpsDossierStatistic.getCreateDate() == null)) {
			if (serviceContext == null) {
				opencpsDossierStatistic.setCreateDate(now);
			}
			else {
				opencpsDossierStatistic.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!opencpsDossierStatisticModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				opencpsDossierStatistic.setModifiedDate(now);
			}
			else {
				opencpsDossierStatistic.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (opencpsDossierStatistic.isNew()) {
				session.save(opencpsDossierStatistic);

				opencpsDossierStatistic.setNew(false);
			}
			else {
				opencpsDossierStatistic = (OpencpsDossierStatistic)session.merge(opencpsDossierStatistic);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OpencpsDossierStatisticModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					opencpsDossierStatisticModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getUuid(),
					opencpsDossierStatisticModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getUserId(),
					opencpsDossierStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getDomainCode(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_D_M_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getCompanyId(),
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CID_GID_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear(),
					opencpsDossierStatisticModelImpl.getReporting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_M_Y_RP, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear(),
					opencpsDossierStatisticModelImpl.getReporting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_MS_Y_RP, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP,
				args);

			args = new Object[] { opencpsDossierStatisticModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_GC_DC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC,
				args);

			args = new Object[] { opencpsDossierStatisticModelImpl.getReporting() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REPO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPO,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getMonth(),
					opencpsDossierStatisticModelImpl.getYear(),
					opencpsDossierStatisticModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getDomainCode(),
					opencpsDossierStatisticModelImpl.getGroupAgencyCode(),
					opencpsDossierStatisticModelImpl.getSystem()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_GOV_DOM_GRO_SYS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_Y_GOV_DOM_GRO_SYS,
				args);

			args = new Object[] {
					opencpsDossierStatisticModelImpl.getGroupId(),
					opencpsDossierStatisticModelImpl.getYear(),
					opencpsDossierStatisticModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticModelImpl.getDomainCode(),
					opencpsDossierStatisticModelImpl.getGroupAgencyCode(),
					opencpsDossierStatisticModelImpl.getSystem()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Y_GO_DO_GR_SY, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { opencpsDossierStatisticModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalUuid(),
						opencpsDossierStatisticModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getUuid(),
						opencpsDossierStatisticModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticModelImpl.getOriginalUserId(),
						opencpsDossierStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getGroupId(),
						opencpsDossierStatisticModelImpl.getUserId(),
						opencpsDossierStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticModelImpl.getOriginalDomainCode(),
						opencpsDossierStatisticModelImpl.getOriginalMonth(),
						opencpsDossierStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_D_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getGroupId(),
						opencpsDossierStatisticModelImpl.getDomainCode(),
						opencpsDossierStatisticModelImpl.getMonth(),
						opencpsDossierStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_D_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticModelImpl.getOriginalMonth(),
						opencpsDossierStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getGroupId(),
						opencpsDossierStatisticModelImpl.getMonth(),
						opencpsDossierStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalCompanyId(),
						opencpsDossierStatisticModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticModelImpl.getOriginalMonth(),
						opencpsDossierStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CID_GID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getCompanyId(),
						opencpsDossierStatisticModelImpl.getGroupId(),
						opencpsDossierStatisticModelImpl.getMonth(),
						opencpsDossierStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CID_GID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticModelImpl.getOriginalMonth(),
						opencpsDossierStatisticModelImpl.getOriginalYear(),
						opencpsDossierStatisticModelImpl.getOriginalReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_M_Y_RP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getGroupId(),
						opencpsDossierStatisticModelImpl.getMonth(),
						opencpsDossierStatisticModelImpl.getYear(),
						opencpsDossierStatisticModelImpl.getReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_M_Y_RP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticModelImpl.getOriginalMonth(),
						opencpsDossierStatisticModelImpl.getOriginalYear(),
						opencpsDossierStatisticModelImpl.getOriginalReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_MS_Y_RP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getGroupId(),
						opencpsDossierStatisticModelImpl.getMonth(),
						opencpsDossierStatisticModelImpl.getYear(),
						opencpsDossierStatisticModelImpl.getReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_MS_Y_RP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticModelImpl.getOriginalMonth(),
						opencpsDossierStatisticModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_GC_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getGroupId(),
						opencpsDossierStatisticModelImpl.getMonth(),
						opencpsDossierStatisticModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_GC_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REPO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPO,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_REPO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_REPO,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_Y_GOV_DOM_GRO_SYS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalMonth(),
						opencpsDossierStatisticModelImpl.getOriginalYear(),
						opencpsDossierStatisticModelImpl.getOriginalGovAgencyCode(),
						opencpsDossierStatisticModelImpl.getOriginalDomainCode(),
						opencpsDossierStatisticModelImpl.getOriginalGroupAgencyCode(),
						opencpsDossierStatisticModelImpl.getOriginalSystem()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_GOV_DOM_GRO_SYS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_Y_GOV_DOM_GRO_SYS,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getMonth(),
						opencpsDossierStatisticModelImpl.getYear(),
						opencpsDossierStatisticModelImpl.getGovAgencyCode(),
						opencpsDossierStatisticModelImpl.getDomainCode(),
						opencpsDossierStatisticModelImpl.getGroupAgencyCode(),
						opencpsDossierStatisticModelImpl.getSystem()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_GOV_DOM_GRO_SYS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_Y_GOV_DOM_GRO_SYS,
					args);
			}

			if ((opencpsDossierStatisticModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticModelImpl.getOriginalYear(),
						opencpsDossierStatisticModelImpl.getOriginalGovAgencyCode(),
						opencpsDossierStatisticModelImpl.getOriginalDomainCode(),
						opencpsDossierStatisticModelImpl.getOriginalGroupAgencyCode(),
						opencpsDossierStatisticModelImpl.getOriginalSystem()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Y_GO_DO_GR_SY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY,
					args);

				args = new Object[] {
						opencpsDossierStatisticModelImpl.getGroupId(),
						opencpsDossierStatisticModelImpl.getYear(),
						opencpsDossierStatisticModelImpl.getGovAgencyCode(),
						opencpsDossierStatisticModelImpl.getDomainCode(),
						opencpsDossierStatisticModelImpl.getGroupAgencyCode(),
						opencpsDossierStatisticModelImpl.getSystem()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Y_GO_DO_GR_SY,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_GO_DO_GR_SY,
					args);
			}
		}

		entityCache.putResult(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticImpl.class,
			opencpsDossierStatistic.getPrimaryKey(), opencpsDossierStatistic,
			false);

		clearUniqueFindersCache(opencpsDossierStatisticModelImpl, false);
		cacheUniqueFindersCache(opencpsDossierStatisticModelImpl);

		opencpsDossierStatistic.resetOriginalValues();

		return opencpsDossierStatistic;
	}

	/**
	 * Returns the opencps dossier statistic with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps dossier statistic
	 * @return the opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatistic opencpsDossierStatistic = fetchByPrimaryKey(primaryKey);

		if (opencpsDossierStatistic == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOpencpsDossierStatisticException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return opencpsDossierStatistic;
	}

	/**
	 * Returns the opencps dossier statistic with the primary key or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	 *
	 * @param dossierStatisticId the primary key of the opencps dossier statistic
	 * @return the opencps dossier statistic
	 * @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic findByPrimaryKey(long dossierStatisticId)
		throws NoSuchOpencpsDossierStatisticException {
		return findByPrimaryKey((Serializable)dossierStatisticId);
	}

	/**
	 * Returns the opencps dossier statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps dossier statistic
	 * @return the opencps dossier statistic, or <code>null</code> if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsDossierStatisticImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OpencpsDossierStatistic opencpsDossierStatistic = (OpencpsDossierStatistic)serializable;

		if (opencpsDossierStatistic == null) {
			Session session = null;

			try {
				session = openSession();

				opencpsDossierStatistic = (OpencpsDossierStatistic)session.get(OpencpsDossierStatisticImpl.class,
						primaryKey);

				if (opencpsDossierStatistic != null) {
					cacheResult(opencpsDossierStatistic);
				}
				else {
					entityCache.putResult(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsDossierStatisticImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsDossierStatisticImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return opencpsDossierStatistic;
	}

	/**
	 * Returns the opencps dossier statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierStatisticId the primary key of the opencps dossier statistic
	 * @return the opencps dossier statistic, or <code>null</code> if a opencps dossier statistic with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatistic fetchByPrimaryKey(long dossierStatisticId) {
		return fetchByPrimaryKey((Serializable)dossierStatisticId);
	}

	@Override
	public Map<Serializable, OpencpsDossierStatistic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OpencpsDossierStatistic> map = new HashMap<Serializable, OpencpsDossierStatistic>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OpencpsDossierStatistic opencpsDossierStatistic = fetchByPrimaryKey(primaryKey);

			if (opencpsDossierStatistic != null) {
				map.put(primaryKey, opencpsDossierStatistic);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsDossierStatisticImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OpencpsDossierStatistic)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE_PKS_IN);

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

			for (OpencpsDossierStatistic opencpsDossierStatistic : (List<OpencpsDossierStatistic>)q.list()) {
				map.put(opencpsDossierStatistic.getPrimaryKeyObj(),
					opencpsDossierStatistic);

				cacheResult(opencpsDossierStatistic);

				uncachedPrimaryKeys.remove(opencpsDossierStatistic.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OpencpsDossierStatisticModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsDossierStatisticImpl.class, primaryKey, nullModel);
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
	 * Returns all the opencps dossier statistics.
	 *
	 * @return the opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @return the range of opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps dossier statistics
	 * @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of opencps dossier statistics
	 */
	@Override
	public List<OpencpsDossierStatistic> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatistic> orderByComparator,
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

		List<OpencpsDossierStatistic> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatistic>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTIC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPENCPSDOSSIERSTATISTIC;

				if (pagination) {
					sql = sql.concat(OpencpsDossierStatisticModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatistic>)QueryUtil.list(q,
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
	 * Removes all the opencps dossier statistics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OpencpsDossierStatistic opencpsDossierStatistic : findAll()) {
			remove(opencpsDossierStatistic);
		}
	}

	/**
	 * Returns the number of opencps dossier statistics.
	 *
	 * @return the number of opencps dossier statistics
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPENCPSDOSSIERSTATISTIC);

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
		return OpencpsDossierStatisticModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the opencps dossier statistic persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OpencpsDossierStatisticImpl.class.getName());
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
	private static final String _SQL_SELECT_OPENCPSDOSSIERSTATISTIC = "SELECT opencpsDossierStatistic FROM OpencpsDossierStatistic opencpsDossierStatistic";
	private static final String _SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE_PKS_IN =
		"SELECT opencpsDossierStatistic FROM OpencpsDossierStatistic opencpsDossierStatistic WHERE dossierStatisticId IN (";
	private static final String _SQL_SELECT_OPENCPSDOSSIERSTATISTIC_WHERE = "SELECT opencpsDossierStatistic FROM OpencpsDossierStatistic opencpsDossierStatistic WHERE ";
	private static final String _SQL_COUNT_OPENCPSDOSSIERSTATISTIC = "SELECT COUNT(opencpsDossierStatistic) FROM OpencpsDossierStatistic opencpsDossierStatistic";
	private static final String _SQL_COUNT_OPENCPSDOSSIERSTATISTIC_WHERE = "SELECT COUNT(opencpsDossierStatistic) FROM OpencpsDossierStatistic opencpsDossierStatistic WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "opencpsDossierStatistic.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpencpsDossierStatistic exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpencpsDossierStatistic exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OpencpsDossierStatisticPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}