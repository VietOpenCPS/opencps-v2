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

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException;
import org.opencps.statistic.model.OpencpsDossierStatisticMgt;
import org.opencps.statistic.model.impl.OpencpsDossierStatisticMgtImpl;
import org.opencps.statistic.model.impl.OpencpsDossierStatisticMgtModelImpl;
import org.opencps.statistic.service.persistence.OpencpsDossierStatisticMgtPersistence;

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
 * The persistence implementation for the opencps dossier statistic mgt service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticMgtPersistence
 * @see org.opencps.statistic.service.persistence.OpencpsDossierStatisticMgtUtil
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticMgtPersistenceImpl
	extends BasePersistenceImpl<OpencpsDossierStatisticMgt>
	implements OpencpsDossierStatisticMgtPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OpencpsDossierStatisticMgtUtil} to access the opencps dossier statistic mgt persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OpencpsDossierStatisticMgtImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OpencpsDossierStatisticMgtModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the opencps dossier statistic mgts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByUuid(String uuid, int start,
		int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if (!Objects.equals(uuid,
								opencpsDossierStatisticMgt.getUuid())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByUuid_First(uuid,
				orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		List<OpencpsDossierStatisticMgt> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByUuid_Last(uuid,
				orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticMgt> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	 *
	 * @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt[] findByUuid_PrevAndNext(
		long dossierStatisticMgtId, String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByPrimaryKey(dossierStatisticMgtId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticMgt[] array = new OpencpsDossierStatisticMgtImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					opencpsDossierStatisticMgt, uuid, orderByComparator, true);

			array[1] = opencpsDossierStatisticMgt;

			array[2] = getByUuid_PrevAndNext(session,
					opencpsDossierStatisticMgt, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatisticMgt getByUuid_PrevAndNext(
		Session session, OpencpsDossierStatisticMgt opencpsDossierStatisticMgt,
		String uuid,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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
			query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticMgt);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticMgt> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic mgts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticMgt);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "opencpsDossierStatisticMgt.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "opencpsDossierStatisticMgt.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(opencpsDossierStatisticMgt.uuid IS NULL OR opencpsDossierStatisticMgt.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OpencpsDossierStatisticMgtModelImpl.UUID_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByUUID_G(uuid,
				groupId);

		if (opencpsDossierStatisticMgt == null) {
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

			throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
		}

		return opencpsDossierStatisticMgt;
	}

	/**
	 * Returns the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatisticMgt) {
			OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = (OpencpsDossierStatisticMgt)result;

			if (!Objects.equals(uuid, opencpsDossierStatisticMgt.getUuid()) ||
					(groupId != opencpsDossierStatisticMgt.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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

				List<OpencpsDossierStatisticMgt> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = list.get(0);

					result = opencpsDossierStatisticMgt;

					cacheResult(opencpsDossierStatisticMgt);
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
			return (OpencpsDossierStatisticMgt)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the opencps dossier statistic mgt that was removed
	 */
	@Override
	public OpencpsDossierStatisticMgt removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByUUID_G(uuid,
				groupId);

		return remove(opencpsDossierStatisticMgt);
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "opencpsDossierStatisticMgt.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "opencpsDossierStatisticMgt.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(opencpsDossierStatisticMgt.uuid IS NULL OR opencpsDossierStatisticMgt.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_Y = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			OpencpsDossierStatisticMgtModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.USERID_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UID_Y = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_UID_Y(long groupId,
		long userId, int year) {
		return findByG_UID_Y(groupId, userId, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return findByG_UID_Y(groupId, userId, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
							(userId != opencpsDossierStatisticMgt.getUserId()) ||
							(year != opencpsDossierStatisticMgt.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_UID_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_USERID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_UID_Y_First(groupId,
				userId, year, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
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

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		List<OpencpsDossierStatisticMgt> list = findByG_UID_Y(groupId, userId,
				year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_UID_Y_Last(groupId,
				userId, year, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
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

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		int count = countByG_UID_Y(groupId, userId, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticMgt> list = findByG_UID_Y(groupId, userId,
				year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt[] findByG_UID_Y_PrevAndNext(
		long dossierStatisticMgtId, long groupId, long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByPrimaryKey(dossierStatisticMgtId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticMgt[] array = new OpencpsDossierStatisticMgtImpl[3];

			array[0] = getByG_UID_Y_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, userId, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticMgt;

			array[2] = getByG_UID_Y_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, userId, year,
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

	protected OpencpsDossierStatisticMgt getByG_UID_Y_PrevAndNext(
		Session session, OpencpsDossierStatisticMgt opencpsDossierStatisticMgt,
		long groupId, long userId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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
			query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticMgt);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticMgt> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 */
	@Override
	public void removeByG_UID_Y(long groupId, long userId, int year) {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : findByG_UID_Y(
				groupId, userId, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(opencpsDossierStatisticMgt);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_UID_Y(long groupId, long userId, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UID_Y;

		Object[] finderArgs = new Object[] { groupId, userId, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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

	private static final String _FINDER_COLUMN_G_UID_Y_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_USERID_2 = "opencpsDossierStatisticMgt.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_YEAR_2 = "opencpsDossierStatisticMgt.year = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_M_Y_G_D = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_M_Y_G_D",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			},
			OpencpsDossierStatisticMgtModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.DOMAINCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y_G_D = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_M_Y_G_D",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_M_Y_G_D(long groupId, int month,
		int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_M_Y_G_D(groupId,
				month, year, govAgencyCode, domainCode);

		if (opencpsDossierStatisticMgt == null) {
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

			throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
		}

		return opencpsDossierStatisticMgt;
	}

	/**
	 * Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_M_Y_G_D(long groupId, int month,
		int year, String govAgencyCode, String domainCode) {
		return fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode, true);
	}

	/**
	 * Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_M_Y_G_D(long groupId, int month,
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

		if (result instanceof OpencpsDossierStatisticMgt) {
			OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = (OpencpsDossierStatisticMgt)result;

			if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
					(month != opencpsDossierStatisticMgt.getMonth()) ||
					(year != opencpsDossierStatisticMgt.getYear()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatisticMgt.getGovAgencyCode()) ||
					!Objects.equals(domainCode,
						opencpsDossierStatisticMgt.getDomainCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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

				List<OpencpsDossierStatisticMgt> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticMgtPersistenceImpl.fetchByG_M_Y_G_D(long, int, int, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = list.get(0);

					result = opencpsDossierStatisticMgt;

					cacheResult(opencpsDossierStatisticMgt);
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
			return (OpencpsDossierStatisticMgt)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the opencps dossier statistic mgt that was removed
	 */
	@Override
	public OpencpsDossierStatisticMgt removeByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByG_M_Y_G_D(groupId,
				month, year, govAgencyCode, domainCode);

		return remove(opencpsDossierStatisticMgt);
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the number of matching opencps dossier statistic mgts
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

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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

	private static final String _FINDER_COLUMN_G_M_Y_G_D_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_MONTH_2 = "opencpsDossierStatisticMgt.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_YEAR_2 = "opencpsDossierStatisticMgt.year = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_1 = "opencpsDossierStatisticMgt.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_2 = "opencpsDossierStatisticMgt.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_3 = "(opencpsDossierStatisticMgt.govAgencyCode IS NULL OR opencpsDossierStatisticMgt.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_1 = "opencpsDossierStatisticMgt.domainCode IS NULL";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_2 = "opencpsDossierStatisticMgt.domainCode = ?";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_3 = "(opencpsDossierStatisticMgt.domainCode IS NULL OR opencpsDossierStatisticMgt.domainCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NM_Y = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_NM_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NM_Y = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByG_NM_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y(long groupId,
		int month, int year) {
		return findByG_NM_Y(groupId, month, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y(long groupId,
		int month, int year, int start, int end) {
		return findByG_NM_Y(groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByG_NM_Y(groupId, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NM_Y;
		finderArgs = new Object[] {
				groupId, month, year,
				
				start, end, orderByComparator
			};

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
							(month == opencpsDossierStatisticMgt.getMonth()) ||
							(year != opencpsDossierStatisticMgt.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_NM_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_NM_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_NM_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_NM_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_NM_Y_First(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
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

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		List<OpencpsDossierStatisticMgt> list = findByG_NM_Y(groupId, month,
				year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_NM_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_NM_Y_Last(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
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

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		int count = countByG_NM_Y(groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticMgt> list = findByG_NM_Y(groupId, month,
				year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt[] findByG_NM_Y_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByPrimaryKey(dossierStatisticMgtId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticMgt[] array = new OpencpsDossierStatisticMgtImpl[3];

			array[0] = getByG_NM_Y_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, month, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticMgt;

			array[2] = getByG_NM_Y_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, month, year,
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

	protected OpencpsDossierStatisticMgt getByG_NM_Y_PrevAndNext(
		Session session, OpencpsDossierStatisticMgt opencpsDossierStatisticMgt,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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
			query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticMgt);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticMgt> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_NM_Y(long groupId, int month, int year) {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : findByG_NM_Y(
				groupId, month, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticMgt);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_NM_Y(long groupId, int month, int year) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NM_Y;

		Object[] finderArgs = new Object[] { groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

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

	private static final String _FINDER_COLUMN_G_NM_Y_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_MONTH_2 = "opencpsDossierStatisticMgt.month != ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_YEAR_2 = "opencpsDossierStatisticMgt.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_Y_REPO",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_Y_REPO",
			new String[] { Long.class.getName(), Integer.class.getName() },
			OpencpsDossierStatisticMgtModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_Y_REPO = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_Y_REPO",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_REPO =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByG_Y_REPO",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO(long groupId,
		int year) {
		return findByG_Y_REPO(groupId, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO(long groupId,
		int year, int start, int end) {
		return findByG_Y_REPO(groupId, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO(long groupId,
		int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByG_Y_REPO(groupId, year, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO(long groupId,
		int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO;
			finderArgs = new Object[] { groupId, year };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO;
			finderArgs = new Object[] {
					groupId, year,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
							(year != opencpsDossierStatisticMgt.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_Y_REPO_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Y_REPO_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(year);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_Y_REPO_First(long groupId,
		int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_Y_REPO_First(groupId,
				year, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_Y_REPO_First(long groupId,
		int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		List<OpencpsDossierStatisticMgt> list = findByG_Y_REPO(groupId, year,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_Y_REPO_Last(long groupId,
		int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_Y_REPO_Last(groupId,
				year, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", year=");
		msg.append(year);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_Y_REPO_Last(long groupId,
		int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		int count = countByG_Y_REPO(groupId, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticMgt> list = findByG_Y_REPO(groupId, year,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	 * @param groupId the group ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt[] findByG_Y_REPO_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByPrimaryKey(dossierStatisticMgtId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticMgt[] array = new OpencpsDossierStatisticMgtImpl[3];

			array[0] = getByG_Y_REPO_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticMgt;

			array[2] = getByG_Y_REPO_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, year,
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

	protected OpencpsDossierStatisticMgt getByG_Y_REPO_PrevAndNext(
		Session session, OpencpsDossierStatisticMgt opencpsDossierStatisticMgt,
		long groupId, int year,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

		query.append(_FINDER_COLUMN_G_Y_REPO_GROUPID_2);

		query.append(_FINDER_COLUMN_G_Y_REPO_YEAR_2);

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
			query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(year);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticMgt);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticMgt> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param years the years
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO(long groupId,
		int[] years) {
		return findByG_Y_REPO(groupId, years, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param years the years
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO(long groupId,
		int[] years, int start, int end) {
		return findByG_Y_REPO(groupId, years, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param years the years
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO(long groupId,
		int[] years, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByG_Y_REPO(groupId, years, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO(long groupId,
		int[] years, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		if (years == null) {
			years = new int[0];
		}
		else if (years.length > 1) {
			years = ArrayUtil.unique(years);

			Arrays.sort(years);
		}

		if (years.length == 1) {
			return findByG_Y_REPO(groupId, years[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { groupId, StringUtil.merge(years) };
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(years),
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
							!ArrayUtil.contains(years,
								opencpsDossierStatisticMgt.getYear())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_Y_REPO_GROUPID_2);

			if (years.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_Y_REPO_YEAR_7);

				query.append(StringUtil.merge(years));

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
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO,
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
	 * Removes all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 */
	@Override
	public void removeByG_Y_REPO(long groupId, int year) {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : findByG_Y_REPO(
				groupId, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticMgt);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_Y_REPO(long groupId, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_Y_REPO;

		Object[] finderArgs = new Object[] { groupId, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_Y_REPO_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Y_REPO_YEAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param years the years
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_Y_REPO(long groupId, int[] years) {
		if (years == null) {
			years = new int[0];
		}
		else if (years.length > 1) {
			years = ArrayUtil.unique(years);

			Arrays.sort(years);
		}

		Object[] finderArgs = new Object[] { groupId, StringUtil.merge(years) };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_REPO,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_Y_REPO_GROUPID_2);

			if (years.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_Y_REPO_YEAR_7);

				query.append(StringUtil.merge(years));

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

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_REPO,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_REPO,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_Y_REPO_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_Y_REPO_YEAR_2 = "opencpsDossierStatisticMgt.year = ?";
	private static final String _FINDER_COLUMN_G_Y_REPO_YEAR_7 = "opencpsDossierStatisticMgt.year IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_Y_GB =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UID_Y_GB",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y_GB =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UID_Y_GB",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticMgtModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.USERID_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.GROUPBY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UID_Y_GB = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_UID_Y_GB",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(long groupId,
		long userId, int year, int groupBy) {
		return findByG_UID_Y_GB(groupId, userId, year, groupBy,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(long groupId,
		long userId, int year, int groupBy, int start, int end) {
		return findByG_UID_Y_GB(groupId, userId, year, groupBy, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(long groupId,
		long userId, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByG_UID_Y_GB(groupId, userId, year, groupBy, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(long groupId,
		long userId, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y_GB;
			finderArgs = new Object[] { groupId, userId, year, groupBy };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_Y_GB;
			finderArgs = new Object[] {
					groupId, userId, year, groupBy,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
							(userId != opencpsDossierStatisticMgt.getUserId()) ||
							(year != opencpsDossierStatisticMgt.getYear()) ||
							(groupBy != opencpsDossierStatisticMgt.getGroupBy())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_UID_Y_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_GB_USERID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_GB_YEAR_2);

			query.append(_FINDER_COLUMN_G_UID_Y_GB_GROUPBY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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

				qPos.add(groupBy);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_UID_Y_GB_First(long groupId,
		long userId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_UID_Y_GB_First(groupId,
				userId, year, groupBy, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", year=");
		msg.append(year);

		msg.append(", groupBy=");
		msg.append(groupBy);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_UID_Y_GB_First(long groupId,
		long userId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		List<OpencpsDossierStatisticMgt> list = findByG_UID_Y_GB(groupId,
				userId, year, groupBy, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_UID_Y_GB_Last(long groupId,
		long userId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_UID_Y_GB_Last(groupId,
				userId, year, groupBy, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", year=");
		msg.append(year);

		msg.append(", groupBy=");
		msg.append(groupBy);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_UID_Y_GB_Last(long groupId,
		long userId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		int count = countByG_UID_Y_GB(groupId, userId, year, groupBy);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticMgt> list = findByG_UID_Y_GB(groupId,
				userId, year, groupBy, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt[] findByG_UID_Y_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, long userId, int year,
		int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByPrimaryKey(dossierStatisticMgtId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticMgt[] array = new OpencpsDossierStatisticMgtImpl[3];

			array[0] = getByG_UID_Y_GB_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, userId, year, groupBy,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticMgt;

			array[2] = getByG_UID_Y_GB_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, userId, year, groupBy,
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

	protected OpencpsDossierStatisticMgt getByG_UID_Y_GB_PrevAndNext(
		Session session, OpencpsDossierStatisticMgt opencpsDossierStatisticMgt,
		long groupId, long userId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

		query.append(_FINDER_COLUMN_G_UID_Y_GB_GROUPID_2);

		query.append(_FINDER_COLUMN_G_UID_Y_GB_USERID_2);

		query.append(_FINDER_COLUMN_G_UID_Y_GB_YEAR_2);

		query.append(_FINDER_COLUMN_G_UID_Y_GB_GROUPBY_2);

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
			query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(year);

		qPos.add(groupBy);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticMgt);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticMgt> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 */
	@Override
	public void removeByG_UID_Y_GB(long groupId, long userId, int year,
		int groupBy) {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : findByG_UID_Y_GB(
				groupId, userId, year, groupBy, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticMgt);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param groupBy the group by
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_UID_Y_GB(long groupId, long userId, int year,
		int groupBy) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UID_Y_GB;

		Object[] finderArgs = new Object[] { groupId, userId, year, groupBy };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_UID_Y_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_GB_USERID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_GB_YEAR_2);

			query.append(_FINDER_COLUMN_G_UID_Y_GB_GROUPBY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(year);

				qPos.add(groupBy);

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

	private static final String _FINDER_COLUMN_G_UID_Y_GB_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_GB_USERID_2 = "opencpsDossierStatisticMgt.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_GB_YEAR_2 = "opencpsDossierStatisticMgt.year = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_GB_GROUPBY_2 = "opencpsDossierStatisticMgt.groupBy = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_M_Y_G_D_GB = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_M_Y_G_D_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticMgtModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.DOMAINCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.GROUPBY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y_G_D_GB = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_M_Y_G_D_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @return the matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy) throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_M_Y_G_D_GB(groupId,
				month, year, govAgencyCode, domainCode, groupBy);

		if (opencpsDossierStatisticMgt == null) {
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

			msg.append(", groupBy=");
			msg.append(groupBy);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
		}

		return opencpsDossierStatisticMgt;
	}

	/**
	 * Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy) {
		return fetchByG_M_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy, true);
	}

	/**
	 * Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode, groupBy
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_GB,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatisticMgt) {
			OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = (OpencpsDossierStatisticMgt)result;

			if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
					(month != opencpsDossierStatisticMgt.getMonth()) ||
					(year != opencpsDossierStatisticMgt.getYear()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatisticMgt.getGovAgencyCode()) ||
					!Objects.equals(domainCode,
						opencpsDossierStatisticMgt.getDomainCode()) ||
					(groupBy != opencpsDossierStatisticMgt.getGroupBy())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GROUPBY_2);

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

				qPos.add(groupBy);

				List<OpencpsDossierStatisticMgt> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_GB,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticMgtPersistenceImpl.fetchByG_M_Y_G_D_GB(long, int, int, String, String, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = list.get(0);

					result = opencpsDossierStatisticMgt;

					cacheResult(opencpsDossierStatisticMgt);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_GB,
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
			return (OpencpsDossierStatisticMgt)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @return the opencps dossier statistic mgt that was removed
	 */
	@Override
	public OpencpsDossierStatisticMgt removeByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy) throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByG_M_Y_G_D_GB(groupId,
				month, year, govAgencyCode, domainCode, groupBy);

		return remove(opencpsDossierStatisticMgt);
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_M_Y_G_D_GB(long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_M_Y_G_D_GB;

		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode, groupBy
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_G_M_Y_G_D_GB_GROUPBY_2);

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

				qPos.add(groupBy);

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

	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_MONTH_2 = "opencpsDossierStatisticMgt.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_YEAR_2 = "opencpsDossierStatisticMgt.year = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_GOVAGENCYCODE_1 = "opencpsDossierStatisticMgt.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_GOVAGENCYCODE_2 = "opencpsDossierStatisticMgt.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_GOVAGENCYCODE_3 = "(opencpsDossierStatisticMgt.govAgencyCode IS NULL OR opencpsDossierStatisticMgt.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_DOMAINCODE_1 = "opencpsDossierStatisticMgt.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_DOMAINCODE_2 = "opencpsDossierStatisticMgt.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_DOMAINCODE_3 = "(opencpsDossierStatisticMgt.domainCode IS NULL OR opencpsDossierStatisticMgt.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GB_GROUPBY_2 = "opencpsDossierStatisticMgt.groupBy = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NM_Y_GB =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_NM_Y_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NM_Y_GB =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByG_NM_Y_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(long groupId,
		int month, int year, int groupBy) {
		return findByG_NM_Y_GB(groupId, month, year, groupBy,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(long groupId,
		int month, int year, int groupBy, int start, int end) {
		return findByG_NM_Y_GB(groupId, month, year, groupBy, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(long groupId,
		int month, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByG_NM_Y_GB(groupId, month, year, groupBy, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(long groupId,
		int month, int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NM_Y_GB;
		finderArgs = new Object[] {
				groupId, month, year, groupBy,
				
				start, end, orderByComparator
			};

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
							(month == opencpsDossierStatisticMgt.getMonth()) ||
							(year != opencpsDossierStatisticMgt.getYear()) ||
							(groupBy != opencpsDossierStatisticMgt.getGroupBy())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_NM_Y_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_NM_Y_GB_MONTH_2);

			query.append(_FINDER_COLUMN_G_NM_Y_GB_YEAR_2);

			query.append(_FINDER_COLUMN_G_NM_Y_GB_GROUPBY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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

				qPos.add(groupBy);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_NM_Y_GB_First(long groupId,
		int month, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_NM_Y_GB_First(groupId,
				month, year, groupBy, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", groupBy=");
		msg.append(groupBy);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_GB_First(long groupId,
		int month, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		List<OpencpsDossierStatisticMgt> list = findByG_NM_Y_GB(groupId, month,
				year, groupBy, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_NM_Y_GB_Last(long groupId,
		int month, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_NM_Y_GB_Last(groupId,
				month, year, groupBy, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", month=");
		msg.append(month);

		msg.append(", year=");
		msg.append(year);

		msg.append(", groupBy=");
		msg.append(groupBy);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_GB_Last(long groupId,
		int month, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		int count = countByG_NM_Y_GB(groupId, month, year, groupBy);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticMgt> list = findByG_NM_Y_GB(groupId, month,
				year, groupBy, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt[] findByG_NM_Y_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int month, int year,
		int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByPrimaryKey(dossierStatisticMgtId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticMgt[] array = new OpencpsDossierStatisticMgtImpl[3];

			array[0] = getByG_NM_Y_GB_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, month, year, groupBy,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticMgt;

			array[2] = getByG_NM_Y_GB_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, month, year, groupBy,
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

	protected OpencpsDossierStatisticMgt getByG_NM_Y_GB_PrevAndNext(
		Session session, OpencpsDossierStatisticMgt opencpsDossierStatisticMgt,
		long groupId, int month, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

		query.append(_FINDER_COLUMN_G_NM_Y_GB_GROUPID_2);

		query.append(_FINDER_COLUMN_G_NM_Y_GB_MONTH_2);

		query.append(_FINDER_COLUMN_G_NM_Y_GB_YEAR_2);

		query.append(_FINDER_COLUMN_G_NM_Y_GB_GROUPBY_2);

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
			query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(month);

		qPos.add(year);

		qPos.add(groupBy);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticMgt);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticMgt> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 */
	@Override
	public void removeByG_NM_Y_GB(long groupId, int month, int year, int groupBy) {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : findByG_NM_Y_GB(
				groupId, month, year, groupBy, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticMgt);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param groupBy the group by
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_NM_Y_GB(long groupId, int month, int year, int groupBy) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NM_Y_GB;

		Object[] finderArgs = new Object[] { groupId, month, year, groupBy };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_NM_Y_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_NM_Y_GB_MONTH_2);

			query.append(_FINDER_COLUMN_G_NM_Y_GB_YEAR_2);

			query.append(_FINDER_COLUMN_G_NM_Y_GB_GROUPBY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(month);

				qPos.add(year);

				qPos.add(groupBy);

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

	private static final String _FINDER_COLUMN_G_NM_Y_GB_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_GB_MONTH_2 = "opencpsDossierStatisticMgt.month != ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_GB_YEAR_2 = "opencpsDossierStatisticMgt.year = ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_GB_GROUPBY_2 = "opencpsDossierStatisticMgt.groupBy = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO_GB =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_Y_REPO_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO_GB =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_Y_REPO_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			OpencpsDossierStatisticMgtModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticMgtModelImpl.GROUPBY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_Y_REPO_GB = new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_Y_REPO_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_REPO_GB =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByG_Y_REPO_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(long groupId,
		int year, int groupBy) {
		return findByG_Y_REPO_GB(groupId, year, groupBy, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(long groupId,
		int year, int groupBy, int start, int end) {
		return findByG_Y_REPO_GB(groupId, year, groupBy, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(long groupId,
		int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByG_Y_REPO_GB(groupId, year, groupBy, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(long groupId,
		int year, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO_GB;
			finderArgs = new Object[] { groupId, year, groupBy };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO_GB;
			finderArgs = new Object[] {
					groupId, year, groupBy,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
							(year != opencpsDossierStatisticMgt.getYear()) ||
							(groupBy != opencpsDossierStatisticMgt.getGroupBy())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_YEAR_2);

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPBY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(year);

				qPos.add(groupBy);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_Y_REPO_GB_First(long groupId,
		int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_Y_REPO_GB_First(groupId,
				year, groupBy, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", year=");
		msg.append(year);

		msg.append(", groupBy=");
		msg.append(groupBy);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_Y_REPO_GB_First(long groupId,
		int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		List<OpencpsDossierStatisticMgt> list = findByG_Y_REPO_GB(groupId,
				year, groupBy, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_Y_REPO_GB_Last(long groupId,
		int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_Y_REPO_GB_Last(groupId,
				year, groupBy, orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", year=");
		msg.append(year);

		msg.append(", groupBy=");
		msg.append(groupBy);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_Y_REPO_GB_Last(long groupId,
		int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		int count = countByG_Y_REPO_GB(groupId, year, groupBy);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticMgt> list = findByG_Y_REPO_GB(groupId,
				year, groupBy, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt[] findByG_Y_REPO_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByPrimaryKey(dossierStatisticMgtId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticMgt[] array = new OpencpsDossierStatisticMgtImpl[3];

			array[0] = getByG_Y_REPO_GB_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, year, groupBy,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticMgt;

			array[2] = getByG_Y_REPO_GB_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, year, groupBy,
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

	protected OpencpsDossierStatisticMgt getByG_Y_REPO_GB_PrevAndNext(
		Session session, OpencpsDossierStatisticMgt opencpsDossierStatisticMgt,
		long groupId, int year, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

		query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPID_2);

		query.append(_FINDER_COLUMN_G_Y_REPO_GB_YEAR_2);

		query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPBY_2);

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
			query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(year);

		qPos.add(groupBy);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticMgt);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticMgt> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param years the years
	 * @param groupBy the group by
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(long groupId,
		int[] years, int groupBy) {
		return findByG_Y_REPO_GB(groupId, years, groupBy, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param years the years
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(long groupId,
		int[] years, int groupBy, int start, int end) {
		return findByG_Y_REPO_GB(groupId, years, groupBy, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param years the years
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(long groupId,
		int[] years, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByG_Y_REPO_GB(groupId, years, groupBy, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(long groupId,
		int[] years, int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		if (years == null) {
			years = new int[0];
		}
		else if (years.length > 1) {
			years = ArrayUtil.unique(years);

			Arrays.sort(years);
		}

		if (years.length == 1) {
			return findByG_Y_REPO_GB(groupId, years[0], groupBy, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { groupId, StringUtil.merge(years), groupBy };
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(years), groupBy,
					
					start, end, orderByComparator
				};
		}

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO_GB,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
							!ArrayUtil.contains(years,
								opencpsDossierStatisticMgt.getYear()) ||
							(groupBy != opencpsDossierStatisticMgt.getGroupBy())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPID_2);

			if (years.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_Y_REPO_GB_YEAR_7);

				query.append(StringUtil.merge(years));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPBY_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(groupBy);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO_GB,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_Y_REPO_GB,
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
	 * Removes all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 */
	@Override
	public void removeByG_Y_REPO_GB(long groupId, int year, int groupBy) {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : findByG_Y_REPO_GB(
				groupId, year, groupBy, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(opencpsDossierStatisticMgt);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param year the year
	 * @param groupBy the group by
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_Y_REPO_GB(long groupId, int year, int groupBy) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_Y_REPO_GB;

		Object[] finderArgs = new Object[] { groupId, year, groupBy };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_YEAR_2);

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPBY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(year);

				qPos.add(groupBy);

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
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param years the years
	 * @param groupBy the group by
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_Y_REPO_GB(long groupId, int[] years, int groupBy) {
		if (years == null) {
			years = new int[0];
		}
		else if (years.length > 1) {
			years = ArrayUtil.unique(years);

			Arrays.sort(years);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(years), groupBy
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_REPO_GB,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPID_2);

			if (years.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_G_Y_REPO_GB_YEAR_7);

				query.append(StringUtil.merge(years));

				query.append(")");

				query.append(")");

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_Y_REPO_GB_GROUPBY_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(groupBy);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_REPO_GB,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_Y_REPO_GB,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_Y_REPO_GB_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_Y_REPO_GB_YEAR_2 = "opencpsDossierStatisticMgt.year = ? AND ";
	private static final String _FINDER_COLUMN_G_Y_REPO_GB_YEAR_7 = "opencpsDossierStatisticMgt.year IN (";
	private static final String _FINDER_COLUMN_G_Y_REPO_GB_GROUPBY_2 = "opencpsDossierStatisticMgt.groupBy = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NM_Y_G_D_GB =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_NM_Y_G_D_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NM_Y_G_D_GB =
		new FinderPath(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByG_NM_Y_G_D_GB",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @return the matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy) {
		return findByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy, int start, int end) {
		return findByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
			domainCode, groupBy, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy, int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_NM_Y_G_D_GB;
		finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode, groupBy,
				
				start, end, orderByComparator
			};

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : list) {
					if ((groupId != opencpsDossierStatisticMgt.getGroupId()) ||
							(month == opencpsDossierStatisticMgt.getMonth()) ||
							(year != opencpsDossierStatisticMgt.getYear()) ||
							!Objects.equals(govAgencyCode,
								opencpsDossierStatisticMgt.getGovAgencyCode()) ||
							!Objects.equals(domainCode,
								opencpsDossierStatisticMgt.getDomainCode()) ||
							(groupBy != opencpsDossierStatisticMgt.getGroupBy())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_MONTH_2);

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GROUPBY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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

				qPos.add(groupBy);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_NM_Y_G_D_GB_First(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_NM_Y_G_D_GB_First(groupId,
				month, year, govAgencyCode, domainCode, groupBy,
				orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

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

		msg.append(", groupBy=");
		msg.append(groupBy);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_G_D_GB_First(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		List<OpencpsDossierStatisticMgt> list = findByG_NM_Y_G_D_GB(groupId,
				month, year, govAgencyCode, domainCode, groupBy, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByG_NM_Y_G_D_GB_Last(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByG_NM_Y_G_D_GB_Last(groupId,
				month, year, govAgencyCode, domainCode, groupBy,
				orderByComparator);

		if (opencpsDossierStatisticMgt != null) {
			return opencpsDossierStatisticMgt;
		}

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

		msg.append(", groupBy=");
		msg.append(groupBy);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticMgtException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_G_D_GB_Last(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		int count = countByG_NM_Y_G_D_GB(groupId, month, year, govAgencyCode,
				domainCode, groupBy);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticMgt> list = findByG_NM_Y_G_D_GB(groupId,
				month, year, govAgencyCode, domainCode, groupBy, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt[] findByG_NM_Y_G_D_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = findByPrimaryKey(dossierStatisticMgtId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticMgt[] array = new OpencpsDossierStatisticMgtImpl[3];

			array[0] = getByG_NM_Y_G_D_GB_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, month, year,
					govAgencyCode, domainCode, groupBy, orderByComparator, true);

			array[1] = opencpsDossierStatisticMgt;

			array[2] = getByG_NM_Y_G_D_GB_PrevAndNext(session,
					opencpsDossierStatisticMgt, groupId, month, year,
					govAgencyCode, domainCode, groupBy, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatisticMgt getByG_NM_Y_G_D_GB_PrevAndNext(
		Session session, OpencpsDossierStatisticMgt opencpsDossierStatisticMgt,
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

		query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GROUPID_2);

		query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_MONTH_2);

		query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_YEAR_2);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_2);
		}

		boolean bindDomainCode = false;

		if (domainCode == null) {
			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_1);
		}
		else if (domainCode.equals("")) {
			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_3);
		}
		else {
			bindDomainCode = true;

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_2);
		}

		query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GROUPBY_2);

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
			query.append(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
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

		qPos.add(groupBy);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticMgt);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticMgt> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 */
	@Override
	public void removeByG_NM_Y_G_D_GB(long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy) {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : findByG_NM_Y_G_D_GB(
				groupId, month, year, govAgencyCode, domainCode, groupBy,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticMgt);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param groupBy the group by
	 * @return the number of matching opencps dossier statistic mgts
	 */
	@Override
	public int countByG_NM_Y_G_D_GB(long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_NM_Y_G_D_GB;

		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode, groupBy
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE);

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GROUPID_2);

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_MONTH_2);

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_YEAR_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_2);
			}

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_G_NM_Y_G_D_GB_GROUPBY_2);

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

				qPos.add(groupBy);

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

	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_GROUPID_2 = "opencpsDossierStatisticMgt.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_MONTH_2 = "opencpsDossierStatisticMgt.month != ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_YEAR_2 = "opencpsDossierStatisticMgt.year = ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_1 = "opencpsDossierStatisticMgt.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_2 = "opencpsDossierStatisticMgt.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_GOVAGENCYCODE_3 = "(opencpsDossierStatisticMgt.govAgencyCode IS NULL OR opencpsDossierStatisticMgt.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_1 = "opencpsDossierStatisticMgt.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_2 = "opencpsDossierStatisticMgt.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_DOMAINCODE_3 = "(opencpsDossierStatisticMgt.domainCode IS NULL OR opencpsDossierStatisticMgt.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_G_NM_Y_G_D_GB_GROUPBY_2 = "opencpsDossierStatisticMgt.groupBy = ?";

	public OpencpsDossierStatisticMgtPersistenceImpl() {
		setModelClass(OpencpsDossierStatisticMgt.class);

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
	 * Caches the opencps dossier statistic mgt in the entity cache if it is enabled.
	 *
	 * @param opencpsDossierStatisticMgt the opencps dossier statistic mgt
	 */
	@Override
	public void cacheResult(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		entityCache.putResult(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			opencpsDossierStatisticMgt.getPrimaryKey(),
			opencpsDossierStatisticMgt);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				opencpsDossierStatisticMgt.getUuid(),
				opencpsDossierStatisticMgt.getGroupId()
			}, opencpsDossierStatisticMgt);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D,
			new Object[] {
				opencpsDossierStatisticMgt.getGroupId(),
				opencpsDossierStatisticMgt.getMonth(),
				opencpsDossierStatisticMgt.getYear(),
				opencpsDossierStatisticMgt.getGovAgencyCode(),
				opencpsDossierStatisticMgt.getDomainCode()
			}, opencpsDossierStatisticMgt);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_GB,
			new Object[] {
				opencpsDossierStatisticMgt.getGroupId(),
				opencpsDossierStatisticMgt.getMonth(),
				opencpsDossierStatisticMgt.getYear(),
				opencpsDossierStatisticMgt.getGovAgencyCode(),
				opencpsDossierStatisticMgt.getDomainCode(),
				opencpsDossierStatisticMgt.getGroupBy()
			}, opencpsDossierStatisticMgt);

		opencpsDossierStatisticMgt.resetOriginalValues();
	}

	/**
	 * Caches the opencps dossier statistic mgts in the entity cache if it is enabled.
	 *
	 * @param opencpsDossierStatisticMgts the opencps dossier statistic mgts
	 */
	@Override
	public void cacheResult(
		List<OpencpsDossierStatisticMgt> opencpsDossierStatisticMgts) {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : opencpsDossierStatisticMgts) {
			if (entityCache.getResult(
						OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsDossierStatisticMgtImpl.class,
						opencpsDossierStatisticMgt.getPrimaryKey()) == null) {
				cacheResult(opencpsDossierStatisticMgt);
			}
			else {
				opencpsDossierStatisticMgt.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all opencps dossier statistic mgts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OpencpsDossierStatisticMgtImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the opencps dossier statistic mgt.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		entityCache.removeResult(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			opencpsDossierStatisticMgt.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OpencpsDossierStatisticMgtModelImpl)opencpsDossierStatisticMgt,
			true);
	}

	@Override
	public void clearCache(
		List<OpencpsDossierStatisticMgt> opencpsDossierStatisticMgts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : opencpsDossierStatisticMgts) {
			entityCache.removeResult(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsDossierStatisticMgtImpl.class,
				opencpsDossierStatisticMgt.getPrimaryKey());

			clearUniqueFindersCache((OpencpsDossierStatisticMgtModelImpl)opencpsDossierStatisticMgt,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OpencpsDossierStatisticMgtModelImpl opencpsDossierStatisticMgtModelImpl) {
		Object[] args = new Object[] {
				opencpsDossierStatisticMgtModelImpl.getUuid(),
				opencpsDossierStatisticMgtModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			opencpsDossierStatisticMgtModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticMgtModelImpl.getGroupId(),
				opencpsDossierStatisticMgtModelImpl.getMonth(),
				opencpsDossierStatisticMgtModelImpl.getYear(),
				opencpsDossierStatisticMgtModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticMgtModelImpl.getDomainCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D, args,
			opencpsDossierStatisticMgtModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticMgtModelImpl.getGroupId(),
				opencpsDossierStatisticMgtModelImpl.getMonth(),
				opencpsDossierStatisticMgtModelImpl.getYear(),
				opencpsDossierStatisticMgtModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticMgtModelImpl.getDomainCode(),
				opencpsDossierStatisticMgtModelImpl.getGroupBy()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D_GB, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_GB, args,
			opencpsDossierStatisticMgtModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OpencpsDossierStatisticMgtModelImpl opencpsDossierStatisticMgtModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getUuid(),
					opencpsDossierStatisticMgtModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((opencpsDossierStatisticMgtModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getOriginalUuid(),
					opencpsDossierStatisticMgtModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getGroupId(),
					opencpsDossierStatisticMgtModelImpl.getMonth(),
					opencpsDossierStatisticMgtModelImpl.getYear(),
					opencpsDossierStatisticMgtModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticMgtModelImpl.getDomainCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D, args);
		}

		if ((opencpsDossierStatisticMgtModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_M_Y_G_D.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticMgtModelImpl.getOriginalMonth(),
					opencpsDossierStatisticMgtModelImpl.getOriginalYear(),
					opencpsDossierStatisticMgtModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticMgtModelImpl.getOriginalDomainCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getGroupId(),
					opencpsDossierStatisticMgtModelImpl.getMonth(),
					opencpsDossierStatisticMgtModelImpl.getYear(),
					opencpsDossierStatisticMgtModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticMgtModelImpl.getDomainCode(),
					opencpsDossierStatisticMgtModelImpl.getGroupBy()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D_GB, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_GB, args);
		}

		if ((opencpsDossierStatisticMgtModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_M_Y_G_D_GB.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticMgtModelImpl.getOriginalMonth(),
					opencpsDossierStatisticMgtModelImpl.getOriginalYear(),
					opencpsDossierStatisticMgtModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticMgtModelImpl.getOriginalDomainCode(),
					opencpsDossierStatisticMgtModelImpl.getOriginalGroupBy()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D_GB, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_GB, args);
		}
	}

	/**
	 * Creates a new opencps dossier statistic mgt with the primary key. Does not add the opencps dossier statistic mgt to the database.
	 *
	 * @param dossierStatisticMgtId the primary key for the new opencps dossier statistic mgt
	 * @return the new opencps dossier statistic mgt
	 */
	@Override
	public OpencpsDossierStatisticMgt create(long dossierStatisticMgtId) {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = new OpencpsDossierStatisticMgtImpl();

		opencpsDossierStatisticMgt.setNew(true);
		opencpsDossierStatisticMgt.setPrimaryKey(dossierStatisticMgtId);

		String uuid = PortalUUIDUtil.generate();

		opencpsDossierStatisticMgt.setUuid(uuid);

		return opencpsDossierStatisticMgt;
	}

	/**
	 * Removes the opencps dossier statistic mgt with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	 * @return the opencps dossier statistic mgt that was removed
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt remove(long dossierStatisticMgtId)
		throws NoSuchOpencpsDossierStatisticMgtException {
		return remove((Serializable)dossierStatisticMgtId);
	}

	/**
	 * Removes the opencps dossier statistic mgt with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the opencps dossier statistic mgt
	 * @return the opencps dossier statistic mgt that was removed
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt remove(Serializable primaryKey)
		throws NoSuchOpencpsDossierStatisticMgtException {
		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = (OpencpsDossierStatisticMgt)session.get(OpencpsDossierStatisticMgtImpl.class,
					primaryKey);

			if (opencpsDossierStatisticMgt == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOpencpsDossierStatisticMgtException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(opencpsDossierStatisticMgt);
		}
		catch (NoSuchOpencpsDossierStatisticMgtException nsee) {
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
	protected OpencpsDossierStatisticMgt removeImpl(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(opencpsDossierStatisticMgt)) {
				opencpsDossierStatisticMgt = (OpencpsDossierStatisticMgt)session.get(OpencpsDossierStatisticMgtImpl.class,
						opencpsDossierStatisticMgt.getPrimaryKeyObj());
			}

			if (opencpsDossierStatisticMgt != null) {
				session.delete(opencpsDossierStatisticMgt);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (opencpsDossierStatisticMgt != null) {
			clearCache(opencpsDossierStatisticMgt);
		}

		return opencpsDossierStatisticMgt;
	}

	@Override
	public OpencpsDossierStatisticMgt updateImpl(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt) {
		boolean isNew = opencpsDossierStatisticMgt.isNew();

		if (!(opencpsDossierStatisticMgt instanceof OpencpsDossierStatisticMgtModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(opencpsDossierStatisticMgt.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(opencpsDossierStatisticMgt);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in opencpsDossierStatisticMgt proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OpencpsDossierStatisticMgt implementation " +
				opencpsDossierStatisticMgt.getClass());
		}

		OpencpsDossierStatisticMgtModelImpl opencpsDossierStatisticMgtModelImpl = (OpencpsDossierStatisticMgtModelImpl)opencpsDossierStatisticMgt;

		if (Validator.isNull(opencpsDossierStatisticMgt.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			opencpsDossierStatisticMgt.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (opencpsDossierStatisticMgt.getCreateDate() == null)) {
			if (serviceContext == null) {
				opencpsDossierStatisticMgt.setCreateDate(now);
			}
			else {
				opencpsDossierStatisticMgt.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!opencpsDossierStatisticMgtModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				opencpsDossierStatisticMgt.setModifiedDate(now);
			}
			else {
				opencpsDossierStatisticMgt.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (opencpsDossierStatisticMgt.isNew()) {
				session.save(opencpsDossierStatisticMgt);

				opencpsDossierStatisticMgt.setNew(false);
			}
			else {
				opencpsDossierStatisticMgt = (OpencpsDossierStatisticMgt)session.merge(opencpsDossierStatisticMgt);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OpencpsDossierStatisticMgtModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getGroupId(),
					opencpsDossierStatisticMgtModelImpl.getUserId(),
					opencpsDossierStatisticMgtModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
				args);

			args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getGroupId(),
					opencpsDossierStatisticMgtModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Y_REPO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO,
				args);

			args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getGroupId(),
					opencpsDossierStatisticMgtModelImpl.getUserId(),
					opencpsDossierStatisticMgtModelImpl.getYear(),
					opencpsDossierStatisticMgtModelImpl.getGroupBy()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y_GB, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y_GB,
				args);

			args = new Object[] {
					opencpsDossierStatisticMgtModelImpl.getGroupId(),
					opencpsDossierStatisticMgtModelImpl.getYear(),
					opencpsDossierStatisticMgtModelImpl.getGroupBy()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Y_REPO_GB, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO_GB,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((opencpsDossierStatisticMgtModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((opencpsDossierStatisticMgtModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticMgtModelImpl.getOriginalUserId(),
						opencpsDossierStatisticMgtModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);

				args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getGroupId(),
						opencpsDossierStatisticMgtModelImpl.getUserId(),
						opencpsDossierStatisticMgtModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);
			}

			if ((opencpsDossierStatisticMgtModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticMgtModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Y_REPO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO,
					args);

				args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getGroupId(),
						opencpsDossierStatisticMgtModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Y_REPO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO,
					args);
			}

			if ((opencpsDossierStatisticMgtModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y_GB.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticMgtModelImpl.getOriginalUserId(),
						opencpsDossierStatisticMgtModelImpl.getOriginalYear(),
						opencpsDossierStatisticMgtModelImpl.getOriginalGroupBy()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y_GB, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y_GB,
					args);

				args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getGroupId(),
						opencpsDossierStatisticMgtModelImpl.getUserId(),
						opencpsDossierStatisticMgtModelImpl.getYear(),
						opencpsDossierStatisticMgtModelImpl.getGroupBy()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y_GB, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y_GB,
					args);
			}

			if ((opencpsDossierStatisticMgtModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO_GB.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticMgtModelImpl.getOriginalYear(),
						opencpsDossierStatisticMgtModelImpl.getOriginalGroupBy()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Y_REPO_GB, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO_GB,
					args);

				args = new Object[] {
						opencpsDossierStatisticMgtModelImpl.getGroupId(),
						opencpsDossierStatisticMgtModelImpl.getYear(),
						opencpsDossierStatisticMgtModelImpl.getGroupBy()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_Y_REPO_GB, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_Y_REPO_GB,
					args);
			}
		}

		entityCache.putResult(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticMgtImpl.class,
			opencpsDossierStatisticMgt.getPrimaryKey(),
			opencpsDossierStatisticMgt, false);

		clearUniqueFindersCache(opencpsDossierStatisticMgtModelImpl, false);
		cacheUniqueFindersCache(opencpsDossierStatisticMgtModelImpl);

		opencpsDossierStatisticMgt.resetOriginalValues();

		return opencpsDossierStatisticMgt;
	}

	/**
	 * Returns the opencps dossier statistic mgt with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps dossier statistic mgt
	 * @return the opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOpencpsDossierStatisticMgtException {
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByPrimaryKey(primaryKey);

		if (opencpsDossierStatisticMgt == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOpencpsDossierStatisticMgtException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return opencpsDossierStatisticMgt;
	}

	/**
	 * Returns the opencps dossier statistic mgt with the primary key or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	 *
	 * @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	 * @return the opencps dossier statistic mgt
	 * @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt findByPrimaryKey(
		long dossierStatisticMgtId)
		throws NoSuchOpencpsDossierStatisticMgtException {
		return findByPrimaryKey((Serializable)dossierStatisticMgtId);
	}

	/**
	 * Returns the opencps dossier statistic mgt with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps dossier statistic mgt
	 * @return the opencps dossier statistic mgt, or <code>null</code> if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsDossierStatisticMgtImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = (OpencpsDossierStatisticMgt)serializable;

		if (opencpsDossierStatisticMgt == null) {
			Session session = null;

			try {
				session = openSession();

				opencpsDossierStatisticMgt = (OpencpsDossierStatisticMgt)session.get(OpencpsDossierStatisticMgtImpl.class,
						primaryKey);

				if (opencpsDossierStatisticMgt != null) {
					cacheResult(opencpsDossierStatisticMgt);
				}
				else {
					entityCache.putResult(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsDossierStatisticMgtImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsDossierStatisticMgtImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return opencpsDossierStatisticMgt;
	}

	/**
	 * Returns the opencps dossier statistic mgt with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	 * @return the opencps dossier statistic mgt, or <code>null</code> if a opencps dossier statistic mgt with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticMgt fetchByPrimaryKey(
		long dossierStatisticMgtId) {
		return fetchByPrimaryKey((Serializable)dossierStatisticMgtId);
	}

	@Override
	public Map<Serializable, OpencpsDossierStatisticMgt> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OpencpsDossierStatisticMgt> map = new HashMap<Serializable, OpencpsDossierStatisticMgt>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OpencpsDossierStatisticMgt opencpsDossierStatisticMgt = fetchByPrimaryKey(primaryKey);

			if (opencpsDossierStatisticMgt != null) {
				map.put(primaryKey, opencpsDossierStatisticMgt);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsDossierStatisticMgtImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OpencpsDossierStatisticMgt)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE_PKS_IN);

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

			for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : (List<OpencpsDossierStatisticMgt>)q.list()) {
				map.put(opencpsDossierStatisticMgt.getPrimaryKeyObj(),
					opencpsDossierStatisticMgt);

				cacheResult(opencpsDossierStatisticMgt);

				uncachedPrimaryKeys.remove(opencpsDossierStatisticMgt.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OpencpsDossierStatisticMgtModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsDossierStatisticMgtImpl.class, primaryKey, nullModel);
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
	 * Returns all the opencps dossier statistic mgts.
	 *
	 * @return the opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic mgts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @return the range of opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic mgts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps dossier statistic mgts
	 * @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of opencps dossier statistic mgts
	 */
	@Override
	public List<OpencpsDossierStatisticMgt> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
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

		List<OpencpsDossierStatisticMgt> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticMgt>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT;

				if (pagination) {
					sql = sql.concat(OpencpsDossierStatisticMgtModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticMgt>)QueryUtil.list(q,
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
	 * Removes all the opencps dossier statistic mgts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OpencpsDossierStatisticMgt opencpsDossierStatisticMgt : findAll()) {
			remove(opencpsDossierStatisticMgt);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic mgts.
	 *
	 * @return the number of opencps dossier statistic mgts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT);

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
		return OpencpsDossierStatisticMgtModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the opencps dossier statistic mgt persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OpencpsDossierStatisticMgtImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT = "SELECT opencpsDossierStatisticMgt FROM OpencpsDossierStatisticMgt opencpsDossierStatisticMgt";
	private static final String _SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE_PKS_IN =
		"SELECT opencpsDossierStatisticMgt FROM OpencpsDossierStatisticMgt opencpsDossierStatisticMgt WHERE dossierStatisticMgtId IN (";
	private static final String _SQL_SELECT_OPENCPSDOSSIERSTATISTICMGT_WHERE = "SELECT opencpsDossierStatisticMgt FROM OpencpsDossierStatisticMgt opencpsDossierStatisticMgt WHERE ";
	private static final String _SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT = "SELECT COUNT(opencpsDossierStatisticMgt) FROM OpencpsDossierStatisticMgt opencpsDossierStatisticMgt";
	private static final String _SQL_COUNT_OPENCPSDOSSIERSTATISTICMGT_WHERE = "SELECT COUNT(opencpsDossierStatisticMgt) FROM OpencpsDossierStatisticMgt opencpsDossierStatisticMgt WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "opencpsDossierStatisticMgt.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpencpsDossierStatisticMgt exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpencpsDossierStatisticMgt exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OpencpsDossierStatisticMgtPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}