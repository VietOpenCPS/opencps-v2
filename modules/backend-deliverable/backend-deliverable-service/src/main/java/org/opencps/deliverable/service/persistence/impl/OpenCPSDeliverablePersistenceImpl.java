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

import org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableException;
import org.opencps.deliverable.model.OpenCPSDeliverable;
import org.opencps.deliverable.model.impl.OpenCPSDeliverableImpl;
import org.opencps.deliverable.model.impl.OpenCPSDeliverableModelImpl;
import org.opencps.deliverable.service.persistence.OpenCPSDeliverablePersistence;

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
 * The persistence implementation for the open cps deliverable service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverablePersistence
 * @see org.opencps.deliverable.service.persistence.OpenCPSDeliverableUtil
 * @generated
 */
@ProviderType
public class OpenCPSDeliverablePersistenceImpl extends BasePersistenceImpl<OpenCPSDeliverable>
	implements OpenCPSDeliverablePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OpenCPSDeliverableUtil} to access the open cps deliverable persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OpenCPSDeliverableImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OpenCPSDeliverableModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the open cps deliverables where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverables where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverables
	 * @param end the upper bound of the range of open cps deliverables (not inclusive)
	 * @return the range of matching open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverables where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverables
	 * @param end the upper bound of the range of open cps deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findByUuid(String uuid, int start, int end,
		OrderByComparator<OpenCPSDeliverable> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverables where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverables
	 * @param end the upper bound of the range of open cps deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findByUuid(String uuid, int start, int end,
		OrderByComparator<OpenCPSDeliverable> orderByComparator,
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

		List<OpenCPSDeliverable> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverable>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverable openCPSDeliverable : list) {
					if (!Objects.equals(uuid, openCPSDeliverable.getUuid())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLE_WHERE);

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
				query.append(OpenCPSDeliverableModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpenCPSDeliverable>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverable>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable
	 * @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable findByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException {
		OpenCPSDeliverable openCPSDeliverable = fetchByUuid_First(uuid,
				orderByComparator);

		if (openCPSDeliverable != null) {
			return openCPSDeliverable;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable fetchByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverable> orderByComparator) {
		List<OpenCPSDeliverable> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable
	 * @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable findByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException {
		OpenCPSDeliverable openCPSDeliverable = fetchByUuid_Last(uuid,
				orderByComparator);

		if (openCPSDeliverable != null) {
			return openCPSDeliverable;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable fetchByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverable> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverable> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverables before and after the current open cps deliverable in the ordered set where uuid = &#63;.
	 *
	 * @param deliverableId the primary key of the current open cps deliverable
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable
	 * @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverable[] findByUuid_PrevAndNext(long deliverableId,
		String uuid, OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException {
		OpenCPSDeliverable openCPSDeliverable = findByPrimaryKey(deliverableId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverable[] array = new OpenCPSDeliverableImpl[3];

			array[0] = getByUuid_PrevAndNext(session, openCPSDeliverable, uuid,
					orderByComparator, true);

			array[1] = openCPSDeliverable;

			array[2] = getByUuid_PrevAndNext(session, openCPSDeliverable, uuid,
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

	protected OpenCPSDeliverable getByUuid_PrevAndNext(Session session,
		OpenCPSDeliverable openCPSDeliverable, String uuid,
		OrderByComparator<OpenCPSDeliverable> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDELIVERABLE_WHERE);

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
			query.append(OpenCPSDeliverableModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverable);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverable> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverables where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OpenCPSDeliverable openCPSDeliverable : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverable);
		}
	}

	/**
	 * Returns the number of open cps deliverables where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching open cps deliverables
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "openCPSDeliverable.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "openCPSDeliverable.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(openCPSDeliverable.uuid IS NULL OR openCPSDeliverable.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OpenCPSDeliverableModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the open cps deliverable where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching open cps deliverable
	 * @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable findByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableException {
		OpenCPSDeliverable openCPSDeliverable = fetchByUUID_G(uuid, groupId);

		if (openCPSDeliverable == null) {
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

			throw new NoSuchOpenCPSDeliverableException(msg.toString());
		}

		return openCPSDeliverable;
	}

	/**
	 * Returns the open cps deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the open cps deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OpenCPSDeliverable) {
			OpenCPSDeliverable openCPSDeliverable = (OpenCPSDeliverable)result;

			if (!Objects.equals(uuid, openCPSDeliverable.getUuid()) ||
					(groupId != openCPSDeliverable.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSDELIVERABLE_WHERE);

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

				List<OpenCPSDeliverable> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OpenCPSDeliverable openCPSDeliverable = list.get(0);

					result = openCPSDeliverable;

					cacheResult(openCPSDeliverable);
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
			return (OpenCPSDeliverable)result;
		}
	}

	/**
	 * Removes the open cps deliverable where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the open cps deliverable that was removed
	 */
	@Override
	public OpenCPSDeliverable removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableException {
		OpenCPSDeliverable openCPSDeliverable = findByUUID_G(uuid, groupId);

		return remove(openCPSDeliverable);
	}

	/**
	 * Returns the number of open cps deliverables where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching open cps deliverables
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "openCPSDeliverable.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "openCPSDeliverable.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(openCPSDeliverable.uuid IS NULL OR openCPSDeliverable.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "openCPSDeliverable.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OpenCPSDeliverableModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableModelImpl.COMPANYID_COLUMN_BITMASK |
			OpenCPSDeliverableModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the open cps deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverables
	 * @param end the upper bound of the range of open cps deliverables (not inclusive)
	 * @return the range of matching open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverables
	 * @param end the upper bound of the range of open cps deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<OpenCPSDeliverable> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverables
	 * @param end the upper bound of the range of open cps deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<OpenCPSDeliverable> orderByComparator,
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

		List<OpenCPSDeliverable> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverable>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverable openCPSDeliverable : list) {
					if (!Objects.equals(uuid, openCPSDeliverable.getUuid()) ||
							(companyId != openCPSDeliverable.getCompanyId())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLE_WHERE);

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
				query.append(OpenCPSDeliverableModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpenCPSDeliverable>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverable>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable
	 * @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException {
		OpenCPSDeliverable openCPSDeliverable = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (openCPSDeliverable != null) {
			return openCPSDeliverable;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverable> orderByComparator) {
		List<OpenCPSDeliverable> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable
	 * @throws NoSuchOpenCPSDeliverableException if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException {
		OpenCPSDeliverable openCPSDeliverable = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (openCPSDeliverable != null) {
			return openCPSDeliverable;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	 */
	@Override
	public OpenCPSDeliverable fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverable> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverable> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverables before and after the current open cps deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deliverableId the primary key of the current open cps deliverable
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable
	 * @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverable[] findByUuid_C_PrevAndNext(long deliverableId,
		String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverable> orderByComparator)
		throws NoSuchOpenCPSDeliverableException {
		OpenCPSDeliverable openCPSDeliverable = findByPrimaryKey(deliverableId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverable[] array = new OpenCPSDeliverableImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, openCPSDeliverable,
					uuid, companyId, orderByComparator, true);

			array[1] = openCPSDeliverable;

			array[2] = getByUuid_C_PrevAndNext(session, openCPSDeliverable,
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

	protected OpenCPSDeliverable getByUuid_C_PrevAndNext(Session session,
		OpenCPSDeliverable openCPSDeliverable, String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverable> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDELIVERABLE_WHERE);

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
			query.append(OpenCPSDeliverableModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverable);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverable> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverables where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OpenCPSDeliverable openCPSDeliverable : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverable);
		}
	}

	/**
	 * Returns the number of open cps deliverables where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching open cps deliverables
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "openCPSDeliverable.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "openCPSDeliverable.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(openCPSDeliverable.uuid IS NULL OR openCPSDeliverable.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "openCPSDeliverable.companyId = ?";

	public OpenCPSDeliverablePersistenceImpl() {
		setModelClass(OpenCPSDeliverable.class);

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
	 * Caches the open cps deliverable in the entity cache if it is enabled.
	 *
	 * @param openCPSDeliverable the open cps deliverable
	 */
	@Override
	public void cacheResult(OpenCPSDeliverable openCPSDeliverable) {
		entityCache.putResult(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class, openCPSDeliverable.getPrimaryKey(),
			openCPSDeliverable);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				openCPSDeliverable.getUuid(), openCPSDeliverable.getGroupId()
			}, openCPSDeliverable);

		openCPSDeliverable.resetOriginalValues();
	}

	/**
	 * Caches the open cps deliverables in the entity cache if it is enabled.
	 *
	 * @param openCPSDeliverables the open cps deliverables
	 */
	@Override
	public void cacheResult(List<OpenCPSDeliverable> openCPSDeliverables) {
		for (OpenCPSDeliverable openCPSDeliverable : openCPSDeliverables) {
			if (entityCache.getResult(
						OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
						OpenCPSDeliverableImpl.class,
						openCPSDeliverable.getPrimaryKey()) == null) {
				cacheResult(openCPSDeliverable);
			}
			else {
				openCPSDeliverable.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all open cps deliverables.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OpenCPSDeliverableImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the open cps deliverable.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OpenCPSDeliverable openCPSDeliverable) {
		entityCache.removeResult(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class, openCPSDeliverable.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OpenCPSDeliverableModelImpl)openCPSDeliverable,
			true);
	}

	@Override
	public void clearCache(List<OpenCPSDeliverable> openCPSDeliverables) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OpenCPSDeliverable openCPSDeliverable : openCPSDeliverables) {
			entityCache.removeResult(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
				OpenCPSDeliverableImpl.class, openCPSDeliverable.getPrimaryKey());

			clearUniqueFindersCache((OpenCPSDeliverableModelImpl)openCPSDeliverable,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OpenCPSDeliverableModelImpl openCPSDeliverableModelImpl) {
		Object[] args = new Object[] {
				openCPSDeliverableModelImpl.getUuid(),
				openCPSDeliverableModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			openCPSDeliverableModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OpenCPSDeliverableModelImpl openCPSDeliverableModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					openCPSDeliverableModelImpl.getUuid(),
					openCPSDeliverableModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((openCPSDeliverableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					openCPSDeliverableModelImpl.getOriginalUuid(),
					openCPSDeliverableModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new open cps deliverable with the primary key. Does not add the open cps deliverable to the database.
	 *
	 * @param deliverableId the primary key for the new open cps deliverable
	 * @return the new open cps deliverable
	 */
	@Override
	public OpenCPSDeliverable create(long deliverableId) {
		OpenCPSDeliverable openCPSDeliverable = new OpenCPSDeliverableImpl();

		openCPSDeliverable.setNew(true);
		openCPSDeliverable.setPrimaryKey(deliverableId);

		String uuid = PortalUUIDUtil.generate();

		openCPSDeliverable.setUuid(uuid);

		openCPSDeliverable.setCompanyId(companyProvider.getCompanyId());

		return openCPSDeliverable;
	}

	/**
	 * Removes the open cps deliverable with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deliverableId the primary key of the open cps deliverable
	 * @return the open cps deliverable that was removed
	 * @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverable remove(long deliverableId)
		throws NoSuchOpenCPSDeliverableException {
		return remove((Serializable)deliverableId);
	}

	/**
	 * Removes the open cps deliverable with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the open cps deliverable
	 * @return the open cps deliverable that was removed
	 * @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverable remove(Serializable primaryKey)
		throws NoSuchOpenCPSDeliverableException {
		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverable openCPSDeliverable = (OpenCPSDeliverable)session.get(OpenCPSDeliverableImpl.class,
					primaryKey);

			if (openCPSDeliverable == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOpenCPSDeliverableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(openCPSDeliverable);
		}
		catch (NoSuchOpenCPSDeliverableException nsee) {
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
	protected OpenCPSDeliverable removeImpl(
		OpenCPSDeliverable openCPSDeliverable) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(openCPSDeliverable)) {
				openCPSDeliverable = (OpenCPSDeliverable)session.get(OpenCPSDeliverableImpl.class,
						openCPSDeliverable.getPrimaryKeyObj());
			}

			if (openCPSDeliverable != null) {
				session.delete(openCPSDeliverable);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (openCPSDeliverable != null) {
			clearCache(openCPSDeliverable);
		}

		return openCPSDeliverable;
	}

	@Override
	public OpenCPSDeliverable updateImpl(OpenCPSDeliverable openCPSDeliverable) {
		boolean isNew = openCPSDeliverable.isNew();

		if (!(openCPSDeliverable instanceof OpenCPSDeliverableModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(openCPSDeliverable.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(openCPSDeliverable);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in openCPSDeliverable proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OpenCPSDeliverable implementation " +
				openCPSDeliverable.getClass());
		}

		OpenCPSDeliverableModelImpl openCPSDeliverableModelImpl = (OpenCPSDeliverableModelImpl)openCPSDeliverable;

		if (Validator.isNull(openCPSDeliverable.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			openCPSDeliverable.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (openCPSDeliverable.getCreateDate() == null)) {
			if (serviceContext == null) {
				openCPSDeliverable.setCreateDate(now);
			}
			else {
				openCPSDeliverable.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!openCPSDeliverableModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				openCPSDeliverable.setModifiedDate(now);
			}
			else {
				openCPSDeliverable.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (openCPSDeliverable.isNew()) {
				session.save(openCPSDeliverable);

				openCPSDeliverable.setNew(false);
			}
			else {
				openCPSDeliverable = (OpenCPSDeliverable)session.merge(openCPSDeliverable);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OpenCPSDeliverableModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { openCPSDeliverableModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					openCPSDeliverableModelImpl.getUuid(),
					openCPSDeliverableModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((openCPSDeliverableModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { openCPSDeliverableModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((openCPSDeliverableModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableModelImpl.getOriginalUuid(),
						openCPSDeliverableModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						openCPSDeliverableModelImpl.getUuid(),
						openCPSDeliverableModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableImpl.class, openCPSDeliverable.getPrimaryKey(),
			openCPSDeliverable, false);

		clearUniqueFindersCache(openCPSDeliverableModelImpl, false);
		cacheUniqueFindersCache(openCPSDeliverableModelImpl);

		openCPSDeliverable.resetOriginalValues();

		return openCPSDeliverable;
	}

	/**
	 * Returns the open cps deliverable with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the open cps deliverable
	 * @return the open cps deliverable
	 * @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverable findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOpenCPSDeliverableException {
		OpenCPSDeliverable openCPSDeliverable = fetchByPrimaryKey(primaryKey);

		if (openCPSDeliverable == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOpenCPSDeliverableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return openCPSDeliverable;
	}

	/**
	 * Returns the open cps deliverable with the primary key or throws a {@link NoSuchOpenCPSDeliverableException} if it could not be found.
	 *
	 * @param deliverableId the primary key of the open cps deliverable
	 * @return the open cps deliverable
	 * @throws NoSuchOpenCPSDeliverableException if a open cps deliverable with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverable findByPrimaryKey(long deliverableId)
		throws NoSuchOpenCPSDeliverableException {
		return findByPrimaryKey((Serializable)deliverableId);
	}

	/**
	 * Returns the open cps deliverable with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the open cps deliverable
	 * @return the open cps deliverable, or <code>null</code> if a open cps deliverable with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverable fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
				OpenCPSDeliverableImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OpenCPSDeliverable openCPSDeliverable = (OpenCPSDeliverable)serializable;

		if (openCPSDeliverable == null) {
			Session session = null;

			try {
				session = openSession();

				openCPSDeliverable = (OpenCPSDeliverable)session.get(OpenCPSDeliverableImpl.class,
						primaryKey);

				if (openCPSDeliverable != null) {
					cacheResult(openCPSDeliverable);
				}
				else {
					entityCache.putResult(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
						OpenCPSDeliverableImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return openCPSDeliverable;
	}

	/**
	 * Returns the open cps deliverable with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deliverableId the primary key of the open cps deliverable
	 * @return the open cps deliverable, or <code>null</code> if a open cps deliverable with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverable fetchByPrimaryKey(long deliverableId) {
		return fetchByPrimaryKey((Serializable)deliverableId);
	}

	@Override
	public Map<Serializable, OpenCPSDeliverable> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OpenCPSDeliverable> map = new HashMap<Serializable, OpenCPSDeliverable>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OpenCPSDeliverable openCPSDeliverable = fetchByPrimaryKey(primaryKey);

			if (openCPSDeliverable != null) {
				map.put(primaryKey, openCPSDeliverable);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OpenCPSDeliverable)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPENCPSDELIVERABLE_WHERE_PKS_IN);

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

			for (OpenCPSDeliverable openCPSDeliverable : (List<OpenCPSDeliverable>)q.list()) {
				map.put(openCPSDeliverable.getPrimaryKeyObj(),
					openCPSDeliverable);

				cacheResult(openCPSDeliverable);

				uncachedPrimaryKeys.remove(openCPSDeliverable.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OpenCPSDeliverableModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableImpl.class, primaryKey, nullModel);
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
	 * Returns all the open cps deliverables.
	 *
	 * @return the open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverables
	 * @param end the upper bound of the range of open cps deliverables (not inclusive)
	 * @return the range of open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverables
	 * @param end the upper bound of the range of open cps deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverable> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverables
	 * @param end the upper bound of the range of open cps deliverables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of open cps deliverables
	 */
	@Override
	public List<OpenCPSDeliverable> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverable> orderByComparator,
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

		List<OpenCPSDeliverable> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverable>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPENCPSDELIVERABLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPENCPSDELIVERABLE;

				if (pagination) {
					sql = sql.concat(OpenCPSDeliverableModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpenCPSDeliverable>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverable>)QueryUtil.list(q,
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
	 * Removes all the open cps deliverables from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OpenCPSDeliverable openCPSDeliverable : findAll()) {
			remove(openCPSDeliverable);
		}
	}

	/**
	 * Returns the number of open cps deliverables.
	 *
	 * @return the number of open cps deliverables
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPENCPSDELIVERABLE);

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
		return OpenCPSDeliverableModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the open cps deliverable persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OpenCPSDeliverableImpl.class.getName());
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
	private static final String _SQL_SELECT_OPENCPSDELIVERABLE = "SELECT openCPSDeliverable FROM OpenCPSDeliverable openCPSDeliverable";
	private static final String _SQL_SELECT_OPENCPSDELIVERABLE_WHERE_PKS_IN = "SELECT openCPSDeliverable FROM OpenCPSDeliverable openCPSDeliverable WHERE deliverableId IN (";
	private static final String _SQL_SELECT_OPENCPSDELIVERABLE_WHERE = "SELECT openCPSDeliverable FROM OpenCPSDeliverable openCPSDeliverable WHERE ";
	private static final String _SQL_COUNT_OPENCPSDELIVERABLE = "SELECT COUNT(openCPSDeliverable) FROM OpenCPSDeliverable openCPSDeliverable";
	private static final String _SQL_COUNT_OPENCPSDELIVERABLE_WHERE = "SELECT COUNT(openCPSDeliverable) FROM OpenCPSDeliverable openCPSDeliverable WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "openCPSDeliverable.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpenCPSDeliverable exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpenCPSDeliverable exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OpenCPSDeliverablePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}