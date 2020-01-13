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

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticManualException;
import org.opencps.statistic.model.OpencpsDossierStatisticManual;
import org.opencps.statistic.model.impl.OpencpsDossierStatisticManualImpl;
import org.opencps.statistic.model.impl.OpencpsDossierStatisticManualModelImpl;
import org.opencps.statistic.service.persistence.OpencpsDossierStatisticManualPersistence;

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
 * The persistence implementation for the opencps dossier statistic manual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OpencpsDossierStatisticManualPersistence
 * @see org.opencps.statistic.service.persistence.OpencpsDossierStatisticManualUtil
 * @generated
 */
@ProviderType
public class OpencpsDossierStatisticManualPersistenceImpl
	extends BasePersistenceImpl<OpencpsDossierStatisticManual>
	implements OpencpsDossierStatisticManualPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OpencpsDossierStatisticManualUtil} to access the opencps dossier statistic manual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OpencpsDossierStatisticManualImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OpencpsDossierStatisticManualModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the opencps dossier statistic manuals where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByUuid(String uuid,
		int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if (!Objects.equals(uuid,
								opencpsDossierStatisticManual.getUuid())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByUuid_First(uuid,
				orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByUuid_First(String uuid,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByUuid_Last(uuid,
				orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByUuid_Last(String uuid,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where uuid = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByUuid_PrevAndNext(
		long dossierStatisticId, String uuid,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					opencpsDossierStatisticManual, uuid, orderByComparator, true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByUuid_PrevAndNext(session,
					opencpsDossierStatisticManual, uuid, orderByComparator,
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

	protected OpencpsDossierStatisticManual getByUuid_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		String uuid,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic manuals where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "opencpsDossierStatisticManual.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "opencpsDossierStatisticManual.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(opencpsDossierStatisticManual.uuid IS NULL OR opencpsDossierStatisticManual.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OpencpsDossierStatisticManualModelImpl.UUID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the opencps dossier statistic manual where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsDossierStatisticManualException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByUUID_G(uuid,
				groupId);

		if (opencpsDossierStatisticManual == null) {
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

			throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
		}

		return opencpsDossierStatisticManual;
	}

	/**
	 * Returns the opencps dossier statistic manual where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the opencps dossier statistic manual where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatisticManual) {
			OpencpsDossierStatisticManual opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)result;

			if (!Objects.equals(uuid, opencpsDossierStatisticManual.getUuid()) ||
					(groupId != opencpsDossierStatisticManual.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

				List<OpencpsDossierStatisticManual> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OpencpsDossierStatisticManual opencpsDossierStatisticManual = list.get(0);

					result = opencpsDossierStatisticManual;

					cacheResult(opencpsDossierStatisticManual);
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
			return (OpencpsDossierStatisticManual)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic manual where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the opencps dossier statistic manual that was removed
	 */
	@Override
	public OpencpsDossierStatisticManual removeByUUID_G(String uuid,
		long groupId) throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByUUID_G(uuid,
				groupId);

		return remove(opencpsDossierStatisticManual);
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "opencpsDossierStatisticManual.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "opencpsDossierStatisticManual.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(opencpsDossierStatisticManual.uuid IS NULL OR opencpsDossierStatisticManual.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OpencpsDossierStatisticManualModelImpl.UUID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the opencps dossier statistic manuals where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if (!Objects.equals(uuid,
								opencpsDossierStatisticManual.getUuid()) ||
							(companyId != opencpsDossierStatisticManual.getCompanyId())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByUuid_C(uuid,
				companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByUuid_C(uuid,
				companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByUuid_C_PrevAndNext(
		long dossierStatisticId, String uuid, long companyId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					opencpsDossierStatisticManual, uuid, companyId,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByUuid_C_PrevAndNext(session,
					opencpsDossierStatisticManual, uuid, companyId,
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

	protected OpencpsDossierStatisticManual getByUuid_C_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		String uuid, long companyId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic manuals where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "opencpsDossierStatisticManual.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "opencpsDossierStatisticManual.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(opencpsDossierStatisticManual.uuid IS NULL OR opencpsDossierStatisticManual.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "opencpsDossierStatisticManual.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_UID_Y = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.USERID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_UID_Y = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_UID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic manuals where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_UID_Y(long groupId,
		long userId, int year) {
		return findByG_UID_Y(groupId, userId, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end) {
		return findByG_UID_Y(groupId, userId, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByG_UID_Y(groupId, userId, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
							(userId != opencpsDossierStatisticManual.getUserId()) ||
							(year != opencpsDossierStatisticManual.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

			query.append(_FINDER_COLUMN_G_UID_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_USERID_2);

			query.append(_FINDER_COLUMN_G_UID_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_UID_Y_First(groupId,
				userId, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByG_UID_Y(groupId,
				userId, year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_UID_Y_Last(groupId,
				userId, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByG_UID_Y(groupId, userId, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByG_UID_Y(groupId,
				userId, year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByG_UID_Y_PrevAndNext(
		long dossierStatisticId, long groupId, long userId, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByG_UID_Y_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, userId, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByG_UID_Y_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, userId, year,
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

	protected OpencpsDossierStatisticManual getByG_UID_Y_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		long groupId, long userId, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic manuals where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 */
	@Override
	public void removeByG_UID_Y(long groupId, long userId, int year) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByG_UID_Y(
				groupId, userId, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and userId = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param year the year
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByG_UID_Y(long groupId, long userId, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_UID_Y;

		Object[] finderArgs = new Object[] { groupId, userId, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_G_UID_Y_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_USERID_2 = "opencpsDossierStatisticManual.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_UID_Y_YEAR_2 = "opencpsDossierStatisticManual.year = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_M_Y_DM_G = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByM_Y_DM_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				String.class.getName(), Boolean.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.DOMAINCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.REPORTING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_Y_DM_G = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByM_Y_DM_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; or throws a {@link NoSuchOpencpsDossierStatisticManualException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		boolean reporting) throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByM_Y_DM_G(groupId,
				govAgencyCode, month, year, domainCode, reporting);

		if (opencpsDossierStatisticManual == null) {
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

			throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
		}

		return opencpsDossierStatisticManual;
	}

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		boolean reporting) {
		return fetchByM_Y_DM_G(groupId, govAgencyCode, month, year, domainCode,
			reporting, true);
	}

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		boolean reporting, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, govAgencyCode, month, year, domainCode, reporting
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatisticManual) {
			OpencpsDossierStatisticManual opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)result;

			if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatisticManual.getGovAgencyCode()) ||
					(month != opencpsDossierStatisticManual.getMonth()) ||
					(year != opencpsDossierStatisticManual.getYear()) ||
					!Objects.equals(domainCode,
						opencpsDossierStatisticManual.getDomainCode()) ||
					(reporting != opencpsDossierStatisticManual.isReporting())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

				List<OpencpsDossierStatisticManual> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticManualPersistenceImpl.fetchByM_Y_DM_G(long, String, int, int, String, boolean, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatisticManual opencpsDossierStatisticManual = list.get(0);

					result = opencpsDossierStatisticManual;

					cacheResult(opencpsDossierStatisticManual);
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
			return (OpencpsDossierStatisticManual)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic manual where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @return the opencps dossier statistic manual that was removed
	 */
	@Override
	public OpencpsDossierStatisticManual removeByM_Y_DM_G(long groupId,
		String govAgencyCode, int month, int year, String domainCode,
		boolean reporting) throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByM_Y_DM_G(groupId,
				govAgencyCode, month, year, domainCode, reporting);

		return remove(opencpsDossierStatisticManual);
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param domainCode the domain code
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByM_Y_DM_G(long groupId, String govAgencyCode, int month,
		int year, String domainCode, boolean reporting) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_Y_DM_G;

		Object[] finderArgs = new Object[] {
				groupId, govAgencyCode, month, year, domainCode, reporting
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_M_Y_DM_G_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_1 = "opencpsDossierStatisticManual.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_2 = "opencpsDossierStatisticManual.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_GOVAGENCYCODE_3 = "(opencpsDossierStatisticManual.govAgencyCode IS NULL OR opencpsDossierStatisticManual.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_YEAR_2 = "opencpsDossierStatisticManual.year = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_1 = "opencpsDossierStatisticManual.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_2 = "opencpsDossierStatisticManual.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_DOMAINCODE_3 = "(opencpsDossierStatisticManual.domainCode IS NULL OR opencpsDossierStatisticManual.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_DM_G_REPORTING_2 = "opencpsDossierStatisticManual.reporting = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_M_Y_G_D = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_M_Y_G_D",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.DOMAINCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y_G_D = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_M_Y_G_D",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsDossierStatisticManualException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_M_Y_G_D(groupId,
				month, year, govAgencyCode, domainCode);

		if (opencpsDossierStatisticManual == null) {
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

			throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
		}

		return opencpsDossierStatisticManual;
	}

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode) {
		return fetchByG_M_Y_G_D(groupId, month, year, govAgencyCode,
			domainCode, true);
	}

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatisticManual) {
			OpencpsDossierStatisticManual opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)result;

			if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
					(month != opencpsDossierStatisticManual.getMonth()) ||
					(year != opencpsDossierStatisticManual.getYear()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatisticManual.getGovAgencyCode()) ||
					!Objects.equals(domainCode,
						opencpsDossierStatisticManual.getDomainCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

				List<OpencpsDossierStatisticManual> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticManualPersistenceImpl.fetchByG_M_Y_G_D(long, int, int, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatisticManual opencpsDossierStatisticManual = list.get(0);

					result = opencpsDossierStatisticManual;

					cacheResult(opencpsDossierStatisticManual);
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
			return (OpencpsDossierStatisticManual)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic manual where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the opencps dossier statistic manual that was removed
	 */
	@Override
	public OpencpsDossierStatisticManual removeByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByG_M_Y_G_D(groupId,
				month, year, govAgencyCode, domainCode);

		return remove(opencpsDossierStatisticManual);
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @return the number of matching opencps dossier statistic manuals
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

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_G_M_Y_G_D_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_YEAR_2 = "opencpsDossierStatisticManual.year = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_1 = "opencpsDossierStatisticManual.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_2 = "opencpsDossierStatisticManual.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_GOVAGENCYCODE_3 = "(opencpsDossierStatisticManual.govAgencyCode IS NULL OR opencpsDossierStatisticManual.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_1 = "opencpsDossierStatisticManual.domainCode IS NULL";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_2 = "opencpsDossierStatisticManual.domainCode = ?";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_DOMAINCODE_3 = "(opencpsDossierStatisticManual.domainCode IS NULL OR opencpsDossierStatisticManual.domainCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_M_Y_G_D_S = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_M_Y_G_D_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.DOMAINCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.SYSTEM_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y_G_D_S = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_M_Y_G_D_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; or throws a {@link NoSuchOpencpsDossierStatisticManualException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @return the matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_M_Y_G_D_S(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String system) throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_M_Y_G_D_S(groupId,
				month, year, govAgencyCode, domainCode, system);

		if (opencpsDossierStatisticManual == null) {
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

			throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
		}

		return opencpsDossierStatisticManual;
	}

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_M_Y_G_D_S(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String system) {
		return fetchByG_M_Y_G_D_S(groupId, month, year, govAgencyCode,
			domainCode, system, true);
	}

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_M_Y_G_D_S(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String system, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				groupId, month, year, govAgencyCode, domainCode, system
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatisticManual) {
			OpencpsDossierStatisticManual opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)result;

			if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
					(month != opencpsDossierStatisticManual.getMonth()) ||
					(year != opencpsDossierStatisticManual.getYear()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatisticManual.getGovAgencyCode()) ||
					!Objects.equals(domainCode,
						opencpsDossierStatisticManual.getDomainCode()) ||
					!Objects.equals(system,
						opencpsDossierStatisticManual.getSystem())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

				List<OpencpsDossierStatisticManual> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticManualPersistenceImpl.fetchByG_M_Y_G_D_S(long, int, int, String, String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatisticManual opencpsDossierStatisticManual = list.get(0);

					result = opencpsDossierStatisticManual;

					cacheResult(opencpsDossierStatisticManual);
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
			return (OpencpsDossierStatisticManual)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic manual where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @return the opencps dossier statistic manual that was removed
	 */
	@Override
	public OpencpsDossierStatisticManual removeByG_M_Y_G_D_S(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		String system) throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByG_M_Y_G_D_S(groupId,
				month, year, govAgencyCode, domainCode, system);

		return remove(opencpsDossierStatisticManual);
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and system = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param govAgencyCode the gov agency code
	 * @param domainCode the domain code
	 * @param system the system
	 * @return the number of matching opencps dossier statistic manuals
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

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_YEAR_2 = "opencpsDossierStatisticManual.year = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_1 = "opencpsDossierStatisticManual.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_2 = "opencpsDossierStatisticManual.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_GOVAGENCYCODE_3 = "(opencpsDossierStatisticManual.govAgencyCode IS NULL OR opencpsDossierStatisticManual.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_1 = "opencpsDossierStatisticManual.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_2 = "opencpsDossierStatisticManual.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_DOMAINCODE_3 = "(opencpsDossierStatisticManual.domainCode IS NULL OR opencpsDossierStatisticManual.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_1 = "opencpsDossierStatisticManual.system IS NULL";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_2 = "opencpsDossierStatisticManual.system = ?";
	private static final String _FINDER_COLUMN_G_M_Y_G_D_S_SYSTEM_3 = "(opencpsDossierStatisticManual.system IS NULL OR opencpsDossierStatisticManual.system = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_M_Y_G = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByM_Y_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.GOVAGENCYCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_Y_G = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByM_Y_G",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; or throws a {@link NoSuchOpencpsDossierStatisticManualException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByM_Y_G(long groupId,
		String govAgencyCode, int month, int year)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByM_Y_G(groupId,
				govAgencyCode, month, year);

		if (opencpsDossierStatisticManual == null) {
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

			throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
		}

		return opencpsDossierStatisticManual;
	}

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByM_Y_G(long groupId,
		String govAgencyCode, int month, int year) {
		return fetchByM_Y_G(groupId, govAgencyCode, month, year, true);
	}

	/**
	 * Returns the opencps dossier statistic manual where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByM_Y_G(long groupId,
		String govAgencyCode, int month, int year, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, govAgencyCode, month, year };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_M_Y_G,
					finderArgs, this);
		}

		if (result instanceof OpencpsDossierStatisticManual) {
			OpencpsDossierStatisticManual opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)result;

			if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
					!Objects.equals(govAgencyCode,
						opencpsDossierStatisticManual.getGovAgencyCode()) ||
					(month != opencpsDossierStatisticManual.getMonth()) ||
					(year != opencpsDossierStatisticManual.getYear())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

				List<OpencpsDossierStatisticManual> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_G,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpencpsDossierStatisticManualPersistenceImpl.fetchByM_Y_G(long, String, int, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpencpsDossierStatisticManual opencpsDossierStatisticManual = list.get(0);

					result = opencpsDossierStatisticManual;

					cacheResult(opencpsDossierStatisticManual);
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
			return (OpencpsDossierStatisticManual)result;
		}
	}

	/**
	 * Removes the opencps dossier statistic manual where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @return the opencps dossier statistic manual that was removed
	 */
	@Override
	public OpencpsDossierStatisticManual removeByM_Y_G(long groupId,
		String govAgencyCode, int month, int year)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByM_Y_G(groupId,
				govAgencyCode, month, year);

		return remove(opencpsDossierStatisticManual);
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and govAgencyCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param govAgencyCode the gov agency code
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByM_Y_G(long groupId, String govAgencyCode, int month,
		int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_Y_G;

		Object[] finderArgs = new Object[] { groupId, govAgencyCode, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_M_Y_G_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_1 = "opencpsDossierStatisticManual.govAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_2 = "opencpsDossierStatisticManual.govAgencyCode = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_G_GOVAGENCYCODE_3 = "(opencpsDossierStatisticManual.govAgencyCode IS NULL OR opencpsDossierStatisticManual.govAgencyCode = '') AND ";
	private static final String _FINDER_COLUMN_M_Y_G_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_M_Y_G_YEAR_2 = "opencpsDossierStatisticManual.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_D_M_Y = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_D_M_Y",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_D_M_Y",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.DOMAINCODE_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_D_M_Y = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_D_M_Y",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic manuals where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year) {
		return findByG_D_M_Y(groupId, domainCode, month, year,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end) {
		return findByG_D_M_Y(groupId, domainCode, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByG_D_M_Y(groupId, domainCode, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
							!Objects.equals(domainCode,
								opencpsDossierStatisticManual.getDomainCode()) ||
							(month != opencpsDossierStatisticManual.getMonth()) ||
							(year != opencpsDossierStatisticManual.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_D_M_Y_First(groupId,
				domainCode, month, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByG_D_M_Y(groupId,
				domainCode, month, year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_D_M_Y_Last(groupId,
				domainCode, month, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByG_D_M_Y(groupId, domainCode, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByG_D_M_Y(groupId,
				domainCode, month, year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByG_D_M_Y_PrevAndNext(
		long dossierStatisticId, long groupId, String domainCode, int month,
		int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByG_D_M_Y_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, domainCode, month,
					year, orderByComparator, true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByG_D_M_Y_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, domainCode, month,
					year, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatisticManual getByG_D_M_Y_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		long groupId, String domainCode, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic manuals where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_D_M_Y(long groupId, String domainCode, int month,
		int year) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByG_D_M_Y(
				groupId, domainCode, month, year, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param domainCode the domain code
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByG_D_M_Y(long groupId, String domainCode, int month,
		int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_D_M_Y;

		Object[] finderArgs = new Object[] { groupId, domainCode, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_G_D_M_Y_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_DOMAINCODE_1 = "opencpsDossierStatisticManual.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_DOMAINCODE_2 = "opencpsDossierStatisticManual.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_DOMAINCODE_3 = "(opencpsDossierStatisticManual.domainCode IS NULL OR opencpsDossierStatisticManual.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_G_D_M_Y_YEAR_2 = "opencpsDossierStatisticManual.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M_Y = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_M_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_M_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_M_Y",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_M_Y(long groupId,
		int month, int year) {
		return findByG_M_Y(groupId, month, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_M_Y(long groupId,
		int month, int year, int start, int end) {
		return findByG_M_Y(groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByG_M_Y(groupId, month, year, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
							(month != opencpsDossierStatisticManual.getMonth()) ||
							(year != opencpsDossierStatisticManual.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_M_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_M_Y_First(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_M_Y_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByG_M_Y(groupId, month,
				year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_M_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_M_Y_Last(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_M_Y_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByG_M_Y(groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByG_M_Y(groupId, month,
				year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByG_M_Y_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByG_M_Y_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, month, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByG_M_Y_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, month, year,
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

	protected OpencpsDossierStatisticManual getByG_M_Y_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_M_Y(long groupId, int month, int year) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByG_M_Y(
				groupId, month, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByG_M_Y(long groupId, int month, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_M_Y;

		Object[] finderArgs = new Object[] { groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_G_M_Y_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_YEAR_2 = "opencpsDossierStatisticManual.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CID_GID_Y =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCID_GID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCID_GID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.COMPANYID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CID_GID_Y = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCID_GID_Y",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic manuals where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByCID_GID_Y(long companyId,
		long groupId, int month, int year) {
		return findByCID_GID_Y(companyId, groupId, month, year,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end) {
		return findByCID_GID_Y(companyId, groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByCID_GID_Y(companyId, groupId, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByCID_GID_Y(long companyId,
		long groupId, int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if ((companyId != opencpsDossierStatisticManual.getCompanyId()) ||
							(groupId != opencpsDossierStatisticManual.getGroupId()) ||
							(month != opencpsDossierStatisticManual.getMonth()) ||
							(year != opencpsDossierStatisticManual.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByCID_GID_Y_First(companyId,
				groupId, month, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByCID_GID_Y_First(
		long companyId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByCID_GID_Y(companyId,
				groupId, month, year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByCID_GID_Y_Last(companyId,
				groupId, month, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByCID_GID_Y(companyId, groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByCID_GID_Y(companyId,
				groupId, month, year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByCID_GID_Y_PrevAndNext(
		long dossierStatisticId, long companyId, long groupId, int month,
		int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByCID_GID_Y_PrevAndNext(session,
					opencpsDossierStatisticManual, companyId, groupId, month,
					year, orderByComparator, true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByCID_GID_Y_PrevAndNext(session,
					opencpsDossierStatisticManual, companyId, groupId, month,
					year, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatisticManual getByCID_GID_Y_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		long companyId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic manuals where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByCID_GID_Y(long companyId, long groupId, int month,
		int year) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByCID_GID_Y(
				companyId, groupId, month, year, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByCID_GID_Y(long companyId, long groupId, int month,
		int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CID_GID_Y;

		Object[] finderArgs = new Object[] { companyId, groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_CID_GID_Y_COMPANYID_2 = "opencpsDossierStatisticManual.companyId = ? AND ";
	private static final String _FINDER_COLUMN_CID_GID_Y_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_CID_GID_Y_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_CID_GID_Y_YEAR_2 = "opencpsDossierStatisticManual.year = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_M_Y_RP =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGID_M_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGID_M_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Boolean.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.REPORTING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_M_Y_RP = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGID_M_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_M_Y_RP(long groupId,
		int month, int year, boolean reporting) {
		return findByGID_M_Y_RP(groupId, month, year, reporting,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_M_Y_RP(long groupId,
		int month, int year, boolean reporting, int start, int end) {
		return findByGID_M_Y_RP(groupId, month, year, reporting, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_M_Y_RP(long groupId,
		int month, int year, boolean reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByGID_M_Y_RP(groupId, month, year, reporting, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_M_Y_RP(long groupId,
		int month, int year, boolean reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
							(month != opencpsDossierStatisticManual.getMonth()) ||
							(year != opencpsDossierStatisticManual.getYear()) ||
							(reporting != opencpsDossierStatisticManual.isReporting())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByGID_M_Y_RP_First(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByGID_M_Y_RP_First(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByGID_M_Y_RP_First(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByGID_M_Y_RP(groupId,
				month, year, reporting, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByGID_M_Y_RP_Last(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByGID_M_Y_RP_Last(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByGID_M_Y_RP_Last(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByGID_M_Y_RP(groupId, month, year, reporting);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByGID_M_Y_RP(groupId,
				month, year, reporting, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByGID_M_Y_RP_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByGID_M_Y_RP_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, month, year,
					reporting, orderByComparator, true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByGID_M_Y_RP_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, month, year,
					reporting, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatisticManual getByGID_M_Y_RP_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		long groupId, int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 */
	@Override
	public void removeByGID_M_Y_RP(long groupId, int month, int year,
		boolean reporting) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByGID_M_Y_RP(
				groupId, month, year, reporting, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByGID_M_Y_RP(long groupId, int month, int year,
		boolean reporting) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_M_Y_RP;

		Object[] finderArgs = new Object[] { groupId, month, year, reporting };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_GID_M_Y_RP_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_M_Y_RP_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_GID_M_Y_RP_YEAR_2 = "opencpsDossierStatisticManual.year = ? AND ";
	private static final String _FINDER_COLUMN_GID_M_Y_RP_REPORTING_2 = "opencpsDossierStatisticManual.reporting = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_MS_Y_RP =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGID_MS_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGID_MS_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Boolean.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.REPORTING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_MS_Y_RP = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGID_MS_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GID_MS_Y_RP =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByGID_MS_Y_RP",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_MS_Y_RP(long groupId,
		int month, int year, boolean reporting) {
		return findByGID_MS_Y_RP(groupId, month, year, reporting,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_MS_Y_RP(long groupId,
		int month, int year, boolean reporting, int start, int end) {
		return findByGID_MS_Y_RP(groupId, month, year, reporting, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_MS_Y_RP(long groupId,
		int month, int year, boolean reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByGID_MS_Y_RP(groupId, month, year, reporting, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_MS_Y_RP(long groupId,
		int month, int year, boolean reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
							(month != opencpsDossierStatisticManual.getMonth()) ||
							(year != opencpsDossierStatisticManual.getYear()) ||
							(reporting != opencpsDossierStatisticManual.isReporting())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByGID_MS_Y_RP_First(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByGID_MS_Y_RP_First(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByGID_MS_Y_RP_First(
		long groupId, int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByGID_MS_Y_RP(groupId,
				month, year, reporting, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByGID_MS_Y_RP_Last(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByGID_MS_Y_RP_Last(groupId,
				month, year, reporting, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByGID_MS_Y_RP_Last(long groupId,
		int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByGID_MS_Y_RP(groupId, month, year, reporting);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByGID_MS_Y_RP(groupId,
				month, year, reporting, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByGID_MS_Y_RP_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByGID_MS_Y_RP_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, month, year,
					reporting, orderByComparator, true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByGID_MS_Y_RP_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, month, year,
					reporting, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpencpsDossierStatisticManual getByGID_MS_Y_RP_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		long groupId, int month, int year, boolean reporting,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the opencps dossier statistic manuals where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param months the months
	 * @param year the year
	 * @param reporting the reporting
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_MS_Y_RP(long groupId,
		int[] months, int year, boolean reporting) {
		return findByGID_MS_Y_RP(groupId, months, year, reporting,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param months the months
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_MS_Y_RP(long groupId,
		int[] months, int year, boolean reporting, int start, int end) {
		return findByGID_MS_Y_RP(groupId, months, year, reporting, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param months the months
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_MS_Y_RP(long groupId,
		int[] months, int year, boolean reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByGID_MS_Y_RP(groupId, months, year, reporting, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByGID_MS_Y_RP(long groupId,
		int[] months, int year, boolean reporting, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_MS_Y_RP,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
							!ArrayUtil.contains(months,
								opencpsDossierStatisticManual.getMonth()) ||
							(year != opencpsDossierStatisticManual.getYear()) ||
							(reporting != opencpsDossierStatisticManual.isReporting())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Removes all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 */
	@Override
	public void removeByGID_MS_Y_RP(long groupId, int month, int year,
		boolean reporting) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByGID_MS_Y_RP(
				groupId, month, year, reporting, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByGID_MS_Y_RP(long groupId, int month, int year,
		boolean reporting) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_MS_Y_RP;

		Object[] finderArgs = new Object[] { groupId, month, year, reporting };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and month = any &#63; and year = &#63; and reporting = &#63;.
	 *
	 * @param groupId the group ID
	 * @param months the months
	 * @param year the year
	 * @param reporting the reporting
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByGID_MS_Y_RP(long groupId, int[] months, int year,
		boolean reporting) {
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

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_GID_MS_Y_RP_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_MS_Y_RP_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_GID_MS_Y_RP_MONTH_7 = "opencpsDossierStatisticManual.month IN (";
	private static final String _FINDER_COLUMN_GID_MS_Y_RP_YEAR_2 = "opencpsDossierStatisticManual.year = ? AND ";
	private static final String _FINDER_COLUMN_GID_MS_Y_RP_REPORTING_2 = "opencpsDossierStatisticManual.reporting = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG",
			new String[] { Long.class.getName() },
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the opencps dossier statistic manuals where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG(long groupId) {
		return findByG(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG(long groupId, int start,
		int end) {
		return findByG(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG(long groupId, int start,
		int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByG(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG(long groupId, int start,
		int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if ((groupId != opencpsDossierStatisticManual.getGroupId())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

			query.append(_FINDER_COLUMN_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_First(long groupId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_First(groupId,
				orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_First(long groupId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByG(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_Last(long groupId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_Last(groupId,
				orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_Last(long groupId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByG(groupId);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByG(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where groupId = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByG_PrevAndNext(
		long dossierStatisticId, long groupId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByG_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, orderByComparator,
					true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByG_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, orderByComparator,
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

	protected OpencpsDossierStatisticManual getByG_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		long groupId,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic manuals where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByG(long groupId) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByG(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByG(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_G_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M_Y_GC_DC =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_M_Y_GC_DC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC =
		new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_M_Y_GC_DC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			},
			OpencpsDossierStatisticManualModelImpl.GROUPID_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.MONTH_COLUMN_BITMASK |
			OpencpsDossierStatisticManualModelImpl.YEAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M_Y_GC_DC = new FinderPath(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_M_Y_GC_DC",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_M_Y_GC_DC(long groupId,
		int month, int year) {
		return findByG_M_Y_GC_DC(groupId, month, year, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_M_Y_GC_DC(long groupId,
		int month, int year, int start, int end) {
		return findByG_M_Y_GC_DC(groupId, month, year, start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_M_Y_GC_DC(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findByG_M_Y_GC_DC(groupId, month, year, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findByG_M_Y_GC_DC(long groupId,
		int month, int year, int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : list) {
					if ((groupId != opencpsDossierStatisticManual.getGroupId()) ||
							(month != opencpsDossierStatisticManual.getMonth()) ||
							(year != opencpsDossierStatisticManual.getYear())) {
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

			query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

			query.append(_FINDER_COLUMN_G_M_Y_GC_DC_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_Y_GC_DC_MONTH_2);

			query.append(_FINDER_COLUMN_G_M_Y_GC_DC_YEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_M_Y_GC_DC_First(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_M_Y_GC_DC_First(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the first opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_M_Y_GC_DC_First(
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		List<OpencpsDossierStatisticManual> list = findByG_M_Y_GC_DC(groupId,
				month, year, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByG_M_Y_GC_DC_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByG_M_Y_GC_DC_Last(groupId,
				month, year, orderByComparator);

		if (opencpsDossierStatisticManual != null) {
			return opencpsDossierStatisticManual;
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

		throw new NoSuchOpencpsDossierStatisticManualException(msg.toString());
	}

	/**
	 * Returns the last opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching opencps dossier statistic manual, or <code>null</code> if a matching opencps dossier statistic manual could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByG_M_Y_GC_DC_Last(long groupId,
		int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		int count = countByG_M_Y_GC_DC(groupId, month, year);

		if (count == 0) {
			return null;
		}

		List<OpencpsDossierStatisticManual> list = findByG_M_Y_GC_DC(groupId,
				month, year, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the opencps dossier statistic manuals before and after the current opencps dossier statistic manual in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param dossierStatisticId the primary key of the current opencps dossier statistic manual
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual[] findByG_M_Y_GC_DC_PrevAndNext(
		long dossierStatisticId, long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = findByPrimaryKey(dossierStatisticId);

		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual[] array = new OpencpsDossierStatisticManualImpl[3];

			array[0] = getByG_M_Y_GC_DC_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, month, year,
					orderByComparator, true);

			array[1] = opencpsDossierStatisticManual;

			array[2] = getByG_M_Y_GC_DC_PrevAndNext(session,
					opencpsDossierStatisticManual, groupId, month, year,
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

	protected OpencpsDossierStatisticManual getByG_M_Y_GC_DC_PrevAndNext(
		Session session,
		OpencpsDossierStatisticManual opencpsDossierStatisticManual,
		long groupId, int month, int year,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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
			query.append(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(opencpsDossierStatisticManual);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpencpsDossierStatisticManual> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 */
	@Override
	public void removeByG_M_Y_GC_DC(long groupId, int month, int year) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findByG_M_Y_GC_DC(
				groupId, month, year, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals where groupId = &#63; and month = &#63; and year = &#63;.
	 *
	 * @param groupId the group ID
	 * @param month the month
	 * @param year the year
	 * @return the number of matching opencps dossier statistic manuals
	 */
	@Override
	public int countByG_M_Y_GC_DC(long groupId, int month, int year) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_M_Y_GC_DC;

		Object[] finderArgs = new Object[] { groupId, month, year };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE);

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

	private static final String _FINDER_COLUMN_G_M_Y_GC_DC_GROUPID_2 = "opencpsDossierStatisticManual.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_GC_DC_MONTH_2 = "opencpsDossierStatisticManual.month = ? AND ";
	private static final String _FINDER_COLUMN_G_M_Y_GC_DC_YEAR_2 = "opencpsDossierStatisticManual.year = ? AND opencpsDossierStatisticManual.govAgencyCode is null and opencpsDossierStatisticManual.domainCode is null";

	public OpencpsDossierStatisticManualPersistenceImpl() {
		setModelClass(OpencpsDossierStatisticManual.class);

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
	 * Caches the opencps dossier statistic manual in the entity cache if it is enabled.
	 *
	 * @param opencpsDossierStatisticManual the opencps dossier statistic manual
	 */
	@Override
	public void cacheResult(
		OpencpsDossierStatisticManual opencpsDossierStatisticManual) {
		entityCache.putResult(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			opencpsDossierStatisticManual.getPrimaryKey(),
			opencpsDossierStatisticManual);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				opencpsDossierStatisticManual.getUuid(),
				opencpsDossierStatisticManual.getGroupId()
			}, opencpsDossierStatisticManual);

		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_DM_G,
			new Object[] {
				opencpsDossierStatisticManual.getGroupId(),
				opencpsDossierStatisticManual.getGovAgencyCode(),
				opencpsDossierStatisticManual.getMonth(),
				opencpsDossierStatisticManual.getYear(),
				opencpsDossierStatisticManual.getDomainCode(),
				opencpsDossierStatisticManual.isReporting()
			}, opencpsDossierStatisticManual);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D,
			new Object[] {
				opencpsDossierStatisticManual.getGroupId(),
				opencpsDossierStatisticManual.getMonth(),
				opencpsDossierStatisticManual.getYear(),
				opencpsDossierStatisticManual.getGovAgencyCode(),
				opencpsDossierStatisticManual.getDomainCode()
			}, opencpsDossierStatisticManual);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S,
			new Object[] {
				opencpsDossierStatisticManual.getGroupId(),
				opencpsDossierStatisticManual.getMonth(),
				opencpsDossierStatisticManual.getYear(),
				opencpsDossierStatisticManual.getGovAgencyCode(),
				opencpsDossierStatisticManual.getDomainCode(),
				opencpsDossierStatisticManual.getSystem()
			}, opencpsDossierStatisticManual);

		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_G,
			new Object[] {
				opencpsDossierStatisticManual.getGroupId(),
				opencpsDossierStatisticManual.getGovAgencyCode(),
				opencpsDossierStatisticManual.getMonth(),
				opencpsDossierStatisticManual.getYear()
			}, opencpsDossierStatisticManual);

		opencpsDossierStatisticManual.resetOriginalValues();
	}

	/**
	 * Caches the opencps dossier statistic manuals in the entity cache if it is enabled.
	 *
	 * @param opencpsDossierStatisticManuals the opencps dossier statistic manuals
	 */
	@Override
	public void cacheResult(
		List<OpencpsDossierStatisticManual> opencpsDossierStatisticManuals) {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : opencpsDossierStatisticManuals) {
			if (entityCache.getResult(
						OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsDossierStatisticManualImpl.class,
						opencpsDossierStatisticManual.getPrimaryKey()) == null) {
				cacheResult(opencpsDossierStatisticManual);
			}
			else {
				opencpsDossierStatisticManual.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all opencps dossier statistic manuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OpencpsDossierStatisticManualImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the opencps dossier statistic manual.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		OpencpsDossierStatisticManual opencpsDossierStatisticManual) {
		entityCache.removeResult(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			opencpsDossierStatisticManual.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OpencpsDossierStatisticManualModelImpl)opencpsDossierStatisticManual,
			true);
	}

	@Override
	public void clearCache(
		List<OpencpsDossierStatisticManual> opencpsDossierStatisticManuals) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : opencpsDossierStatisticManuals) {
			entityCache.removeResult(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsDossierStatisticManualImpl.class,
				opencpsDossierStatisticManual.getPrimaryKey());

			clearUniqueFindersCache((OpencpsDossierStatisticManualModelImpl)opencpsDossierStatisticManual,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OpencpsDossierStatisticManualModelImpl opencpsDossierStatisticManualModelImpl) {
		Object[] args = new Object[] {
				opencpsDossierStatisticManualModelImpl.getUuid(),
				opencpsDossierStatisticManualModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			opencpsDossierStatisticManualModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticManualModelImpl.getGroupId(),
				opencpsDossierStatisticManualModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticManualModelImpl.getMonth(),
				opencpsDossierStatisticManualModelImpl.getYear(),
				opencpsDossierStatisticManualModelImpl.getDomainCode(),
				opencpsDossierStatisticManualModelImpl.isReporting()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_M_Y_DM_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_DM_G, args,
			opencpsDossierStatisticManualModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticManualModelImpl.getGroupId(),
				opencpsDossierStatisticManualModelImpl.getMonth(),
				opencpsDossierStatisticManualModelImpl.getYear(),
				opencpsDossierStatisticManualModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticManualModelImpl.getDomainCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D, args,
			opencpsDossierStatisticManualModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticManualModelImpl.getGroupId(),
				opencpsDossierStatisticManualModelImpl.getMonth(),
				opencpsDossierStatisticManualModelImpl.getYear(),
				opencpsDossierStatisticManualModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticManualModelImpl.getDomainCode(),
				opencpsDossierStatisticManualModelImpl.getSystem()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D_S, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S, args,
			opencpsDossierStatisticManualModelImpl, false);

		args = new Object[] {
				opencpsDossierStatisticManualModelImpl.getGroupId(),
				opencpsDossierStatisticManualModelImpl.getGovAgencyCode(),
				opencpsDossierStatisticManualModelImpl.getMonth(),
				opencpsDossierStatisticManualModelImpl.getYear()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_M_Y_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_M_Y_G, args,
			opencpsDossierStatisticManualModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OpencpsDossierStatisticManualModelImpl opencpsDossierStatisticManualModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getUuid(),
					opencpsDossierStatisticManualModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getOriginalUuid(),
					opencpsDossierStatisticManualModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear(),
					opencpsDossierStatisticManualModelImpl.getDomainCode(),
					opencpsDossierStatisticManualModelImpl.isReporting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_DM_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_DM_G, args);
		}

		if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_M_Y_DM_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticManualModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
					opencpsDossierStatisticManualModelImpl.getOriginalYear(),
					opencpsDossierStatisticManualModelImpl.getOriginalDomainCode(),
					opencpsDossierStatisticManualModelImpl.getOriginalReporting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_DM_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_DM_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear(),
					opencpsDossierStatisticManualModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticManualModelImpl.getDomainCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D, args);
		}

		if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_M_Y_G_D.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
					opencpsDossierStatisticManualModelImpl.getOriginalYear(),
					opencpsDossierStatisticManualModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticManualModelImpl.getOriginalDomainCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear(),
					opencpsDossierStatisticManualModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticManualModelImpl.getDomainCode(),
					opencpsDossierStatisticManualModelImpl.getSystem()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S, args);
		}

		if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_M_Y_G_D_S.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
					opencpsDossierStatisticManualModelImpl.getOriginalYear(),
					opencpsDossierStatisticManualModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticManualModelImpl.getOriginalDomainCode(),
					opencpsDossierStatisticManualModelImpl.getOriginalSystem()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_G_D_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_M_Y_G_D_S, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getGovAgencyCode(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_G, args);
		}

		if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_M_Y_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
					opencpsDossierStatisticManualModelImpl.getOriginalGovAgencyCode(),
					opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
					opencpsDossierStatisticManualModelImpl.getOriginalYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_M_Y_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_M_Y_G, args);
		}
	}

	/**
	 * Creates a new opencps dossier statistic manual with the primary key. Does not add the opencps dossier statistic manual to the database.
	 *
	 * @param dossierStatisticId the primary key for the new opencps dossier statistic manual
	 * @return the new opencps dossier statistic manual
	 */
	@Override
	public OpencpsDossierStatisticManual create(long dossierStatisticId) {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = new OpencpsDossierStatisticManualImpl();

		opencpsDossierStatisticManual.setNew(true);
		opencpsDossierStatisticManual.setPrimaryKey(dossierStatisticId);

		String uuid = PortalUUIDUtil.generate();

		opencpsDossierStatisticManual.setUuid(uuid);

		opencpsDossierStatisticManual.setCompanyId(companyProvider.getCompanyId());

		return opencpsDossierStatisticManual;
	}

	/**
	 * Removes the opencps dossier statistic manual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dossierStatisticId the primary key of the opencps dossier statistic manual
	 * @return the opencps dossier statistic manual that was removed
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual remove(long dossierStatisticId)
		throws NoSuchOpencpsDossierStatisticManualException {
		return remove((Serializable)dossierStatisticId);
	}

	/**
	 * Removes the opencps dossier statistic manual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the opencps dossier statistic manual
	 * @return the opencps dossier statistic manual that was removed
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual remove(Serializable primaryKey)
		throws NoSuchOpencpsDossierStatisticManualException {
		Session session = null;

		try {
			session = openSession();

			OpencpsDossierStatisticManual opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)session.get(OpencpsDossierStatisticManualImpl.class,
					primaryKey);

			if (opencpsDossierStatisticManual == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOpencpsDossierStatisticManualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(opencpsDossierStatisticManual);
		}
		catch (NoSuchOpencpsDossierStatisticManualException nsee) {
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
	protected OpencpsDossierStatisticManual removeImpl(
		OpencpsDossierStatisticManual opencpsDossierStatisticManual) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(opencpsDossierStatisticManual)) {
				opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)session.get(OpencpsDossierStatisticManualImpl.class,
						opencpsDossierStatisticManual.getPrimaryKeyObj());
			}

			if (opencpsDossierStatisticManual != null) {
				session.delete(opencpsDossierStatisticManual);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (opencpsDossierStatisticManual != null) {
			clearCache(opencpsDossierStatisticManual);
		}

		return opencpsDossierStatisticManual;
	}

	@Override
	public OpencpsDossierStatisticManual updateImpl(
		OpencpsDossierStatisticManual opencpsDossierStatisticManual) {
		boolean isNew = opencpsDossierStatisticManual.isNew();

		if (!(opencpsDossierStatisticManual instanceof OpencpsDossierStatisticManualModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(opencpsDossierStatisticManual.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(opencpsDossierStatisticManual);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in opencpsDossierStatisticManual proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OpencpsDossierStatisticManual implementation " +
				opencpsDossierStatisticManual.getClass());
		}

		OpencpsDossierStatisticManualModelImpl opencpsDossierStatisticManualModelImpl =
			(OpencpsDossierStatisticManualModelImpl)opencpsDossierStatisticManual;

		if (Validator.isNull(opencpsDossierStatisticManual.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			opencpsDossierStatisticManual.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (opencpsDossierStatisticManual.getCreateDate() == null)) {
			if (serviceContext == null) {
				opencpsDossierStatisticManual.setCreateDate(now);
			}
			else {
				opencpsDossierStatisticManual.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!opencpsDossierStatisticManualModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				opencpsDossierStatisticManual.setModifiedDate(now);
			}
			else {
				opencpsDossierStatisticManual.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (opencpsDossierStatisticManual.isNew()) {
				session.save(opencpsDossierStatisticManual);

				opencpsDossierStatisticManual.setNew(false);
			}
			else {
				opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)session.merge(opencpsDossierStatisticManual);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OpencpsDossierStatisticManualModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getUuid(),
					opencpsDossierStatisticManualModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getUserId(),
					opencpsDossierStatisticManualModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
				args);

			args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getDomainCode(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_D_M_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y,
				args);

			args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y,
				args);

			args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getCompanyId(),
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CID_GID_Y, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y,
				args);

			args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear(),
					opencpsDossierStatisticManualModelImpl.isReporting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_M_Y_RP, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP,
				args);

			args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear(),
					opencpsDossierStatisticManualModelImpl.isReporting()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_MS_Y_RP, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP,
				args);

			args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
				args);

			args = new Object[] {
					opencpsDossierStatisticManualModelImpl.getGroupId(),
					opencpsDossierStatisticManualModelImpl.getMonth(),
					opencpsDossierStatisticManualModelImpl.getYear()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_GC_DC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalUuid(),
						opencpsDossierStatisticManualModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getUuid(),
						opencpsDossierStatisticManualModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticManualModelImpl.getOriginalUserId(),
						opencpsDossierStatisticManualModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getGroupId(),
						opencpsDossierStatisticManualModelImpl.getUserId(),
						opencpsDossierStatisticManualModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_UID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_UID_Y,
					args);
			}

			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticManualModelImpl.getOriginalDomainCode(),
						opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
						opencpsDossierStatisticManualModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_D_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getGroupId(),
						opencpsDossierStatisticManualModelImpl.getDomainCode(),
						opencpsDossierStatisticManualModelImpl.getMonth(),
						opencpsDossierStatisticManualModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_D_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_D_M_Y,
					args);
			}

			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
						opencpsDossierStatisticManualModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getGroupId(),
						opencpsDossierStatisticManualModelImpl.getMonth(),
						opencpsDossierStatisticManualModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y,
					args);
			}

			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalCompanyId(),
						opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
						opencpsDossierStatisticManualModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CID_GID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getCompanyId(),
						opencpsDossierStatisticManualModelImpl.getGroupId(),
						opencpsDossierStatisticManualModelImpl.getMonth(),
						opencpsDossierStatisticManualModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CID_GID_Y, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CID_GID_Y,
					args);
			}

			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
						opencpsDossierStatisticManualModelImpl.getOriginalYear(),
						opencpsDossierStatisticManualModelImpl.getOriginalReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_M_Y_RP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getGroupId(),
						opencpsDossierStatisticManualModelImpl.getMonth(),
						opencpsDossierStatisticManualModelImpl.getYear(),
						opencpsDossierStatisticManualModelImpl.isReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_M_Y_RP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_M_Y_RP,
					args);
			}

			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
						opencpsDossierStatisticManualModelImpl.getOriginalYear(),
						opencpsDossierStatisticManualModelImpl.getOriginalReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_MS_Y_RP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getGroupId(),
						opencpsDossierStatisticManualModelImpl.getMonth(),
						opencpsDossierStatisticManualModelImpl.getYear(),
						opencpsDossierStatisticManualModelImpl.isReporting()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_MS_Y_RP, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_MS_Y_RP,
					args);
			}

			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G,
					args);
			}

			if ((opencpsDossierStatisticManualModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getOriginalGroupId(),
						opencpsDossierStatisticManualModelImpl.getOriginalMonth(),
						opencpsDossierStatisticManualModelImpl.getOriginalYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_GC_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC,
					args);

				args = new Object[] {
						opencpsDossierStatisticManualModelImpl.getGroupId(),
						opencpsDossierStatisticManualModelImpl.getMonth(),
						opencpsDossierStatisticManualModelImpl.getYear()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_M_Y_GC_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M_Y_GC_DC,
					args);
			}
		}

		entityCache.putResult(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
			OpencpsDossierStatisticManualImpl.class,
			opencpsDossierStatisticManual.getPrimaryKey(),
			opencpsDossierStatisticManual, false);

		clearUniqueFindersCache(opencpsDossierStatisticManualModelImpl, false);
		cacheUniqueFindersCache(opencpsDossierStatisticManualModelImpl);

		opencpsDossierStatisticManual.resetOriginalValues();

		return opencpsDossierStatisticManual;
	}

	/**
	 * Returns the opencps dossier statistic manual with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps dossier statistic manual
	 * @return the opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchOpencpsDossierStatisticManualException {
		OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByPrimaryKey(primaryKey);

		if (opencpsDossierStatisticManual == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOpencpsDossierStatisticManualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return opencpsDossierStatisticManual;
	}

	/**
	 * Returns the opencps dossier statistic manual with the primary key or throws a {@link NoSuchOpencpsDossierStatisticManualException} if it could not be found.
	 *
	 * @param dossierStatisticId the primary key of the opencps dossier statistic manual
	 * @return the opencps dossier statistic manual
	 * @throws NoSuchOpencpsDossierStatisticManualException if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual findByPrimaryKey(
		long dossierStatisticId)
		throws NoSuchOpencpsDossierStatisticManualException {
		return findByPrimaryKey((Serializable)dossierStatisticId);
	}

	/**
	 * Returns the opencps dossier statistic manual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the opencps dossier statistic manual
	 * @return the opencps dossier statistic manual, or <code>null</code> if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
				OpencpsDossierStatisticManualImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OpencpsDossierStatisticManual opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)serializable;

		if (opencpsDossierStatisticManual == null) {
			Session session = null;

			try {
				session = openSession();

				opencpsDossierStatisticManual = (OpencpsDossierStatisticManual)session.get(OpencpsDossierStatisticManualImpl.class,
						primaryKey);

				if (opencpsDossierStatisticManual != null) {
					cacheResult(opencpsDossierStatisticManual);
				}
				else {
					entityCache.putResult(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
						OpencpsDossierStatisticManualImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsDossierStatisticManualImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return opencpsDossierStatisticManual;
	}

	/**
	 * Returns the opencps dossier statistic manual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dossierStatisticId the primary key of the opencps dossier statistic manual
	 * @return the opencps dossier statistic manual, or <code>null</code> if a opencps dossier statistic manual with the primary key could not be found
	 */
	@Override
	public OpencpsDossierStatisticManual fetchByPrimaryKey(
		long dossierStatisticId) {
		return fetchByPrimaryKey((Serializable)dossierStatisticId);
	}

	@Override
	public Map<Serializable, OpencpsDossierStatisticManual> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OpencpsDossierStatisticManual> map = new HashMap<Serializable, OpencpsDossierStatisticManual>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OpencpsDossierStatisticManual opencpsDossierStatisticManual = fetchByPrimaryKey(primaryKey);

			if (opencpsDossierStatisticManual != null) {
				map.put(primaryKey, opencpsDossierStatisticManual);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsDossierStatisticManualImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(OpencpsDossierStatisticManual)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE_PKS_IN);

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

			for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : (List<OpencpsDossierStatisticManual>)q.list()) {
				map.put(opencpsDossierStatisticManual.getPrimaryKeyObj(),
					opencpsDossierStatisticManual);

				cacheResult(opencpsDossierStatisticManual);

				uncachedPrimaryKeys.remove(opencpsDossierStatisticManual.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OpencpsDossierStatisticManualModelImpl.ENTITY_CACHE_ENABLED,
					OpencpsDossierStatisticManualImpl.class, primaryKey,
					nullModel);
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
	 * Returns all the opencps dossier statistic manuals.
	 *
	 * @return the opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the opencps dossier statistic manuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @return the range of opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the opencps dossier statistic manuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticManualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of opencps dossier statistic manuals
	 * @param end the upper bound of the range of opencps dossier statistic manuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of opencps dossier statistic manuals
	 */
	@Override
	public List<OpencpsDossierStatisticManual> findAll(int start, int end,
		OrderByComparator<OpencpsDossierStatisticManual> orderByComparator,
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

		List<OpencpsDossierStatisticManual> list = null;

		if (retrieveFromCache) {
			list = (List<OpencpsDossierStatisticManual>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL;

				if (pagination) {
					sql = sql.concat(OpencpsDossierStatisticManualModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpencpsDossierStatisticManual>)QueryUtil.list(q,
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
	 * Removes all the opencps dossier statistic manuals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OpencpsDossierStatisticManual opencpsDossierStatisticManual : findAll()) {
			remove(opencpsDossierStatisticManual);
		}
	}

	/**
	 * Returns the number of opencps dossier statistic manuals.
	 *
	 * @return the number of opencps dossier statistic manuals
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL);

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
		return OpencpsDossierStatisticManualModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the opencps dossier statistic manual persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OpencpsDossierStatisticManualImpl.class.getName());
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
	private static final String _SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL = "SELECT opencpsDossierStatisticManual FROM OpencpsDossierStatisticManual opencpsDossierStatisticManual";
	private static final String _SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE_PKS_IN =
		"SELECT opencpsDossierStatisticManual FROM OpencpsDossierStatisticManual opencpsDossierStatisticManual WHERE dossierStatisticId IN (";
	private static final String _SQL_SELECT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE = "SELECT opencpsDossierStatisticManual FROM OpencpsDossierStatisticManual opencpsDossierStatisticManual WHERE ";
	private static final String _SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL = "SELECT COUNT(opencpsDossierStatisticManual) FROM OpencpsDossierStatisticManual opencpsDossierStatisticManual";
	private static final String _SQL_COUNT_OPENCPSDOSSIERSTATISTICMANUAL_WHERE = "SELECT COUNT(opencpsDossierStatisticManual) FROM OpencpsDossierStatisticManual opencpsDossierStatisticManual WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "opencpsDossierStatisticManual.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpencpsDossierStatisticManual exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpencpsDossierStatisticManual exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OpencpsDossierStatisticManualPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}