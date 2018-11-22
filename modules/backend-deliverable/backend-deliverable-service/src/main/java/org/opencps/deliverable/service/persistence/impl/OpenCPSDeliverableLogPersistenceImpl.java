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

package org.opencps.deliverable.service.persistence.impl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableLogException;
import org.opencps.deliverable.model.OpenCPSDeliverableLog;
import org.opencps.deliverable.model.impl.OpenCPSDeliverableLogImpl;
import org.opencps.deliverable.model.impl.OpenCPSDeliverableLogModelImpl;
import org.opencps.deliverable.service.persistence.OpenCPSDeliverableLogPersistence;

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
 * The persistence implementation for the open cps deliverable log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableLogPersistence
 * @see org.opencps.deliverable.service.persistence.OpenCPSDeliverableLogUtil
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableLogPersistenceImpl extends BasePersistenceImpl<OpenCPSDeliverableLog>
	implements OpenCPSDeliverableLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OpenCPSDeliverableLogUtil} to access the open cps deliverable log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OpenCPSDeliverableLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OpenCPSDeliverableLogModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableLogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the open cps deliverable logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverable logs
	 * @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	 * @return the range of matching open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverable logs
	 * @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverable logs
	 * @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
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

		List<OpenCPSDeliverableLog> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverableLog openCPSDeliverableLog : list) {
					if (!Objects.equals(uuid, openCPSDeliverableLog.getUuid())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLELOG_WHERE);

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
				query.append(OpenCPSDeliverableLogModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpenCPSDeliverableLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableLog>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable log
	 * @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog findByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException {
		OpenCPSDeliverableLog openCPSDeliverableLog = fetchByUuid_First(uuid,
				orderByComparator);

		if (openCPSDeliverableLog != null) {
			return openCPSDeliverableLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableLogException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog fetchByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		List<OpenCPSDeliverableLog> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable log
	 * @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog findByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException {
		OpenCPSDeliverableLog openCPSDeliverableLog = fetchByUuid_Last(uuid,
				orderByComparator);

		if (openCPSDeliverableLog != null) {
			return openCPSDeliverableLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableLogException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog fetchByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverableLog> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverable logs before and after the current open cps deliverable log in the ordered set where uuid = &#63;.
	 *
	 * @param deliverableLogId the primary key of the current open cps deliverable log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable log
	 * @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableLog[] findByUuid_PrevAndNext(
		long deliverableLogId, String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException {
		OpenCPSDeliverableLog openCPSDeliverableLog = findByPrimaryKey(deliverableLogId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableLog[] array = new OpenCPSDeliverableLogImpl[3];

			array[0] = getByUuid_PrevAndNext(session, openCPSDeliverableLog,
					uuid, orderByComparator, true);

			array[1] = openCPSDeliverableLog;

			array[2] = getByUuid_PrevAndNext(session, openCPSDeliverableLog,
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

	protected OpenCPSDeliverableLog getByUuid_PrevAndNext(Session session,
		OpenCPSDeliverableLog openCPSDeliverableLog, String uuid,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDELIVERABLELOG_WHERE);

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
			query.append(OpenCPSDeliverableLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverableLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverableLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverable logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OpenCPSDeliverableLog openCPSDeliverableLog : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverableLog);
		}
	}

	/**
	 * Returns the number of open cps deliverable logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching open cps deliverable logs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLELOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "openCPSDeliverableLog.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "openCPSDeliverableLog.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(openCPSDeliverableLog.uuid IS NULL OR openCPSDeliverableLog.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OpenCPSDeliverableLogModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableLogModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the open cps deliverable log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableLogException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching open cps deliverable log
	 * @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog findByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableLogException {
		OpenCPSDeliverableLog openCPSDeliverableLog = fetchByUUID_G(uuid,
				groupId);

		if (openCPSDeliverableLog == null) {
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

			throw new NoSuchOpenCPSDeliverableLogException(msg.toString());
		}

		return openCPSDeliverableLog;
	}

	/**
	 * Returns the open cps deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the open cps deliverable log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OpenCPSDeliverableLog) {
			OpenCPSDeliverableLog openCPSDeliverableLog = (OpenCPSDeliverableLog)result;

			if (!Objects.equals(uuid, openCPSDeliverableLog.getUuid()) ||
					(groupId != openCPSDeliverableLog.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSDELIVERABLELOG_WHERE);

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

				List<OpenCPSDeliverableLog> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OpenCPSDeliverableLog openCPSDeliverableLog = list.get(0);

					result = openCPSDeliverableLog;

					cacheResult(openCPSDeliverableLog);
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
			return (OpenCPSDeliverableLog)result;
		}
	}

	/**
	 * Removes the open cps deliverable log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the open cps deliverable log that was removed
	 */
	@Override
	public OpenCPSDeliverableLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableLogException {
		OpenCPSDeliverableLog openCPSDeliverableLog = findByUUID_G(uuid, groupId);

		return remove(openCPSDeliverableLog);
	}

	/**
	 * Returns the number of open cps deliverable logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching open cps deliverable logs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLELOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "openCPSDeliverableLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "openCPSDeliverableLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(openCPSDeliverableLog.uuid IS NULL OR openCPSDeliverableLog.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "openCPSDeliverableLog.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OpenCPSDeliverableLogModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableLogModelImpl.COMPANYID_COLUMN_BITMASK |
			OpenCPSDeliverableLogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverable logs
	 * @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	 * @return the range of matching open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverable logs
	 * @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverable logs
	 * @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
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

		List<OpenCPSDeliverableLog> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverableLog openCPSDeliverableLog : list) {
					if (!Objects.equals(uuid, openCPSDeliverableLog.getUuid()) ||
							(companyId != openCPSDeliverableLog.getCompanyId())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLELOG_WHERE);

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
				query.append(OpenCPSDeliverableLogModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpenCPSDeliverableLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableLog>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable log
	 * @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException {
		OpenCPSDeliverableLog openCPSDeliverableLog = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (openCPSDeliverableLog != null) {
			return openCPSDeliverableLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableLogException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		List<OpenCPSDeliverableLog> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable log
	 * @throws NoSuchOpenCPSDeliverableLogException if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException {
		OpenCPSDeliverableLog openCPSDeliverableLog = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (openCPSDeliverableLog != null) {
			return openCPSDeliverableLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableLogException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable log, or <code>null</code> if a matching open cps deliverable log could not be found
	 */
	@Override
	public OpenCPSDeliverableLog fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverableLog> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverable logs before and after the current open cps deliverable log in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deliverableLogId the primary key of the current open cps deliverable log
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable log
	 * @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableLog[] findByUuid_C_PrevAndNext(
		long deliverableLogId, String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator)
		throws NoSuchOpenCPSDeliverableLogException {
		OpenCPSDeliverableLog openCPSDeliverableLog = findByPrimaryKey(deliverableLogId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableLog[] array = new OpenCPSDeliverableLogImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, openCPSDeliverableLog,
					uuid, companyId, orderByComparator, true);

			array[1] = openCPSDeliverableLog;

			array[2] = getByUuid_C_PrevAndNext(session, openCPSDeliverableLog,
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

	protected OpenCPSDeliverableLog getByUuid_C_PrevAndNext(Session session,
		OpenCPSDeliverableLog openCPSDeliverableLog, String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDELIVERABLELOG_WHERE);

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
			query.append(OpenCPSDeliverableLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverableLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverableLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverable logs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OpenCPSDeliverableLog openCPSDeliverableLog : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverableLog);
		}
	}

	/**
	 * Returns the number of open cps deliverable logs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching open cps deliverable logs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLELOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "openCPSDeliverableLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "openCPSDeliverableLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(openCPSDeliverableLog.uuid IS NULL OR openCPSDeliverableLog.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "openCPSDeliverableLog.companyId = ?";

	public OpenCPSDeliverableLogPersistenceImpl() {
		setModelClass(OpenCPSDeliverableLog.class);

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
	 * Caches the open cps deliverable log in the entity cache if it is enabled.
	 *
	 * @param openCPSDeliverableLog the open cps deliverable log
	 */
	@Override
	public void cacheResult(OpenCPSDeliverableLog openCPSDeliverableLog) {
		entityCache.putResult(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class,
			openCPSDeliverableLog.getPrimaryKey(), openCPSDeliverableLog);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				openCPSDeliverableLog.getUuid(),
				openCPSDeliverableLog.getGroupId()
			}, openCPSDeliverableLog);

		openCPSDeliverableLog.resetOriginalValues();
	}

	/**
	 * Caches the open cps deliverable logs in the entity cache if it is enabled.
	 *
	 * @param openCPSDeliverableLogs the open cps deliverable logs
	 */
	@Override
	public void cacheResult(List<OpenCPSDeliverableLog> openCPSDeliverableLogs) {
		for (OpenCPSDeliverableLog openCPSDeliverableLog : openCPSDeliverableLogs) {
			if (entityCache.getResult(
						OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
						OpenCPSDeliverableLogImpl.class,
						openCPSDeliverableLog.getPrimaryKey()) == null) {
				cacheResult(openCPSDeliverableLog);
			}
			else {
				openCPSDeliverableLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all open cps deliverable logs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OpenCPSDeliverableLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the open cps deliverable log.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OpenCPSDeliverableLog openCPSDeliverableLog) {
		entityCache.removeResult(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class,
			openCPSDeliverableLog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OpenCPSDeliverableLogModelImpl)openCPSDeliverableLog,
			true);
	}

	@Override
	public void clearCache(List<OpenCPSDeliverableLog> openCPSDeliverableLogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OpenCPSDeliverableLog openCPSDeliverableLog : openCPSDeliverableLogs) {
			entityCache.removeResult(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
				OpenCPSDeliverableLogImpl.class,
				openCPSDeliverableLog.getPrimaryKey());

			clearUniqueFindersCache((OpenCPSDeliverableLogModelImpl)openCPSDeliverableLog,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OpenCPSDeliverableLogModelImpl openCPSDeliverableLogModelImpl) {
		Object[] args = new Object[] {
				openCPSDeliverableLogModelImpl.getUuid(),
				openCPSDeliverableLogModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			openCPSDeliverableLogModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OpenCPSDeliverableLogModelImpl openCPSDeliverableLogModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					openCPSDeliverableLogModelImpl.getUuid(),
					openCPSDeliverableLogModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((openCPSDeliverableLogModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					openCPSDeliverableLogModelImpl.getOriginalUuid(),
					openCPSDeliverableLogModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new open cps deliverable log with the primary key. Does not add the open cps deliverable log to the database.
	 *
	 * @param deliverableLogId the primary key for the new open cps deliverable log
	 * @return the new open cps deliverable log
	 */
	@Override
	public OpenCPSDeliverableLog create(long deliverableLogId) {
		OpenCPSDeliverableLog openCPSDeliverableLog = new OpenCPSDeliverableLogImpl();

		openCPSDeliverableLog.setNew(true);
		openCPSDeliverableLog.setPrimaryKey(deliverableLogId);

		String uuid = PortalUUIDUtil.generate();

		openCPSDeliverableLog.setUuid(uuid);

		openCPSDeliverableLog.setCompanyId(companyProvider.getCompanyId());

		return openCPSDeliverableLog;
	}

	/**
	 * Removes the open cps deliverable log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deliverableLogId the primary key of the open cps deliverable log
	 * @return the open cps deliverable log that was removed
	 * @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableLog remove(long deliverableLogId)
		throws NoSuchOpenCPSDeliverableLogException {
		return remove((Serializable)deliverableLogId);
	}

	/**
	 * Removes the open cps deliverable log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the open cps deliverable log
	 * @return the open cps deliverable log that was removed
	 * @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableLog remove(Serializable primaryKey)
		throws NoSuchOpenCPSDeliverableLogException {
		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableLog openCPSDeliverableLog = (OpenCPSDeliverableLog)session.get(OpenCPSDeliverableLogImpl.class,
					primaryKey);

			if (openCPSDeliverableLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOpenCPSDeliverableLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(openCPSDeliverableLog);
		}
		catch (NoSuchOpenCPSDeliverableLogException nsee) {
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
	protected OpenCPSDeliverableLog removeImpl(
		OpenCPSDeliverableLog openCPSDeliverableLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(openCPSDeliverableLog)) {
				openCPSDeliverableLog = (OpenCPSDeliverableLog)session.get(OpenCPSDeliverableLogImpl.class,
						openCPSDeliverableLog.getPrimaryKeyObj());
			}

			if (openCPSDeliverableLog != null) {
				session.delete(openCPSDeliverableLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (openCPSDeliverableLog != null) {
			clearCache(openCPSDeliverableLog);
		}

		return openCPSDeliverableLog;
	}

	@Override
	public OpenCPSDeliverableLog updateImpl(
		OpenCPSDeliverableLog openCPSDeliverableLog) {
		boolean isNew = openCPSDeliverableLog.isNew();

		if (!(openCPSDeliverableLog instanceof OpenCPSDeliverableLogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(openCPSDeliverableLog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(openCPSDeliverableLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in openCPSDeliverableLog proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OpenCPSDeliverableLog implementation " +
				openCPSDeliverableLog.getClass());
		}

		OpenCPSDeliverableLogModelImpl openCPSDeliverableLogModelImpl = (OpenCPSDeliverableLogModelImpl)openCPSDeliverableLog;

		if (Validator.isNull(openCPSDeliverableLog.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			openCPSDeliverableLog.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (openCPSDeliverableLog.getCreateDate() == null)) {
			if (serviceContext == null) {
				openCPSDeliverableLog.setCreateDate(now);
			}
			else {
				openCPSDeliverableLog.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!openCPSDeliverableLogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				openCPSDeliverableLog.setModifiedDate(now);
			}
			else {
				openCPSDeliverableLog.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (openCPSDeliverableLog.isNew()) {
				session.save(openCPSDeliverableLog);

				openCPSDeliverableLog.setNew(false);
			}
			else {
				openCPSDeliverableLog = (OpenCPSDeliverableLog)session.merge(openCPSDeliverableLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OpenCPSDeliverableLogModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					openCPSDeliverableLogModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					openCPSDeliverableLogModelImpl.getUuid(),
					openCPSDeliverableLogModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((openCPSDeliverableLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableLogModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { openCPSDeliverableLogModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((openCPSDeliverableLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableLogModelImpl.getOriginalUuid(),
						openCPSDeliverableLogModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						openCPSDeliverableLogModelImpl.getUuid(),
						openCPSDeliverableLogModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableLogImpl.class,
			openCPSDeliverableLog.getPrimaryKey(), openCPSDeliverableLog, false);

		clearUniqueFindersCache(openCPSDeliverableLogModelImpl, false);
		cacheUniqueFindersCache(openCPSDeliverableLogModelImpl);

		openCPSDeliverableLog.resetOriginalValues();

		return openCPSDeliverableLog;
	}

	/**
	 * Returns the open cps deliverable log with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the open cps deliverable log
	 * @return the open cps deliverable log
	 * @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOpenCPSDeliverableLogException {
		OpenCPSDeliverableLog openCPSDeliverableLog = fetchByPrimaryKey(primaryKey);

		if (openCPSDeliverableLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOpenCPSDeliverableLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return openCPSDeliverableLog;
	}

	/**
	 * Returns the open cps deliverable log with the primary key or throws a {@link NoSuchOpenCPSDeliverableLogException} if it could not be found.
	 *
	 * @param deliverableLogId the primary key of the open cps deliverable log
	 * @return the open cps deliverable log
	 * @throws NoSuchOpenCPSDeliverableLogException if a open cps deliverable log with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableLog findByPrimaryKey(long deliverableLogId)
		throws NoSuchOpenCPSDeliverableLogException {
		return findByPrimaryKey((Serializable)deliverableLogId);
	}

	/**
	 * Returns the open cps deliverable log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the open cps deliverable log
	 * @return the open cps deliverable log, or <code>null</code> if a open cps deliverable log with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableLog fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
				OpenCPSDeliverableLogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OpenCPSDeliverableLog openCPSDeliverableLog = (OpenCPSDeliverableLog)serializable;

		if (openCPSDeliverableLog == null) {
			Session session = null;

			try {
				session = openSession();

				openCPSDeliverableLog = (OpenCPSDeliverableLog)session.get(OpenCPSDeliverableLogImpl.class,
						primaryKey);

				if (openCPSDeliverableLog != null) {
					cacheResult(openCPSDeliverableLog);
				}
				else {
					entityCache.putResult(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
						OpenCPSDeliverableLogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableLogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return openCPSDeliverableLog;
	}

	/**
	 * Returns the open cps deliverable log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deliverableLogId the primary key of the open cps deliverable log
	 * @return the open cps deliverable log, or <code>null</code> if a open cps deliverable log with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableLog fetchByPrimaryKey(long deliverableLogId) {
		return fetchByPrimaryKey((Serializable)deliverableLogId);
	}

	@Override
	public Map<Serializable, OpenCPSDeliverableLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OpenCPSDeliverableLog> map = new HashMap<Serializable, OpenCPSDeliverableLog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OpenCPSDeliverableLog openCPSDeliverableLog = fetchByPrimaryKey(primaryKey);

			if (openCPSDeliverableLog != null) {
				map.put(primaryKey, openCPSDeliverableLog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableLogImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OpenCPSDeliverableLog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPENCPSDELIVERABLELOG_WHERE_PKS_IN);

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

			for (OpenCPSDeliverableLog openCPSDeliverableLog : (List<OpenCPSDeliverableLog>)q.list()) {
				map.put(openCPSDeliverableLog.getPrimaryKeyObj(),
					openCPSDeliverableLog);

				cacheResult(openCPSDeliverableLog);

				uncachedPrimaryKeys.remove(openCPSDeliverableLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OpenCPSDeliverableLogModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableLogImpl.class, primaryKey, nullModel);
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
	 * Returns all the open cps deliverable logs.
	 *
	 * @return the open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverable logs
	 * @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	 * @return the range of open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverable logs
	 * @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverable logs
	 * @param end the upper bound of the range of open cps deliverable logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of open cps deliverable logs
	 */
	@Override
	public List<OpenCPSDeliverableLog> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableLog> orderByComparator,
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

		List<OpenCPSDeliverableLog> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableLog>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPENCPSDELIVERABLELOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPENCPSDELIVERABLELOG;

				if (pagination) {
					sql = sql.concat(OpenCPSDeliverableLogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpenCPSDeliverableLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableLog>)QueryUtil.list(q,
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
	 * Removes all the open cps deliverable logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OpenCPSDeliverableLog openCPSDeliverableLog : findAll()) {
			remove(openCPSDeliverableLog);
		}
	}

	/**
	 * Returns the number of open cps deliverable logs.
	 *
	 * @return the number of open cps deliverable logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPENCPSDELIVERABLELOG);

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
		return OpenCPSDeliverableLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the open cps deliverable log persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OpenCPSDeliverableLogImpl.class.getName());
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
	private static final String _SQL_SELECT_OPENCPSDELIVERABLELOG = "SELECT openCPSDeliverableLog FROM OpenCPSDeliverableLog openCPSDeliverableLog";
	private static final String _SQL_SELECT_OPENCPSDELIVERABLELOG_WHERE_PKS_IN = "SELECT openCPSDeliverableLog FROM OpenCPSDeliverableLog openCPSDeliverableLog WHERE deliverableLogId IN (";
	private static final String _SQL_SELECT_OPENCPSDELIVERABLELOG_WHERE = "SELECT openCPSDeliverableLog FROM OpenCPSDeliverableLog openCPSDeliverableLog WHERE ";
	private static final String _SQL_COUNT_OPENCPSDELIVERABLELOG = "SELECT COUNT(openCPSDeliverableLog) FROM OpenCPSDeliverableLog openCPSDeliverableLog";
	private static final String _SQL_COUNT_OPENCPSDELIVERABLELOG_WHERE = "SELECT COUNT(openCPSDeliverableLog) FROM OpenCPSDeliverableLog openCPSDeliverableLog WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "openCPSDeliverableLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpenCPSDeliverableLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpenCPSDeliverableLog exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OpenCPSDeliverableLogPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}