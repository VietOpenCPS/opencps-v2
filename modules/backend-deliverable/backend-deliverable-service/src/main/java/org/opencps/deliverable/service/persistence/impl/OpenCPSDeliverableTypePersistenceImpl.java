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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException;
import org.opencps.deliverable.model.OpenCPSDeliverableType;
import org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeImpl;
import org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeModelImpl;
import org.opencps.deliverable.service.persistence.OpenCPSDeliverableTypePersistence;

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
 * The persistence implementation for the open cps deliverable type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableTypePersistence
 * @see org.opencps.deliverable.service.persistence.OpenCPSDeliverableTypeUtil
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypePersistenceImpl extends BasePersistenceImpl<OpenCPSDeliverableType>
	implements OpenCPSDeliverableTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OpenCPSDeliverableTypeUtil} to access the open cps deliverable type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OpenCPSDeliverableTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OpenCPSDeliverableTypeModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the open cps deliverable types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @return the range of matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByUuid(String uuid, int start,
		int end, OrderByComparator<OpenCPSDeliverableType> orderByComparator,
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

		List<OpenCPSDeliverableType> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverableType openCPSDeliverableType : list) {
					if (!Objects.equals(uuid, openCPSDeliverableType.getUuid())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE);

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
				query.append(OpenCPSDeliverableTypeModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpenCPSDeliverableType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableType>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = fetchByUuid_First(uuid,
				orderByComparator);

		if (openCPSDeliverableType != null) {
			return openCPSDeliverableType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		List<OpenCPSDeliverableType> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = fetchByUuid_Last(uuid,
				orderByComparator);

		if (openCPSDeliverableType != null) {
			return openCPSDeliverableType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverableType> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverable types before and after the current open cps deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param deliverableTypeId the primary key of the current open cps deliverable type
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableType[] findByUuid_PrevAndNext(
		long deliverableTypeId, String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = findByPrimaryKey(deliverableTypeId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableType[] array = new OpenCPSDeliverableTypeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, openCPSDeliverableType,
					uuid, orderByComparator, true);

			array[1] = openCPSDeliverableType;

			array[2] = getByUuid_PrevAndNext(session, openCPSDeliverableType,
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

	protected OpenCPSDeliverableType getByUuid_PrevAndNext(Session session,
		OpenCPSDeliverableType openCPSDeliverableType, String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE);

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
			query.append(OpenCPSDeliverableTypeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverableType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverableType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverable types where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (OpenCPSDeliverableType openCPSDeliverableType : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverableType);
		}
	}

	/**
	 * Returns the number of open cps deliverable types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching open cps deliverable types
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLETYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "openCPSDeliverableType.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "openCPSDeliverableType.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(openCPSDeliverableType.uuid IS NULL OR openCPSDeliverableType.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			OpenCPSDeliverableTypeModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the open cps deliverable type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableTypeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = fetchByUUID_G(uuid,
				groupId);

		if (openCPSDeliverableType == null) {
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

			throw new NoSuchOpenCPSDeliverableTypeException(msg.toString());
		}

		return openCPSDeliverableType;
	}

	/**
	 * Returns the open cps deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the open cps deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof OpenCPSDeliverableType) {
			OpenCPSDeliverableType openCPSDeliverableType = (OpenCPSDeliverableType)result;

			if (!Objects.equals(uuid, openCPSDeliverableType.getUuid()) ||
					(groupId != openCPSDeliverableType.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE);

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

				List<OpenCPSDeliverableType> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					OpenCPSDeliverableType openCPSDeliverableType = list.get(0);

					result = openCPSDeliverableType;

					cacheResult(openCPSDeliverableType);
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
			return (OpenCPSDeliverableType)result;
		}
	}

	/**
	 * Removes the open cps deliverable type where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the open cps deliverable type that was removed
	 */
	@Override
	public OpenCPSDeliverableType removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = findByUUID_G(uuid,
				groupId);

		return remove(openCPSDeliverableType);
	}

	/**
	 * Returns the number of open cps deliverable types where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching open cps deliverable types
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLETYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "openCPSDeliverableType.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "openCPSDeliverableType.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(openCPSDeliverableType.uuid IS NULL OR openCPSDeliverableType.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "openCPSDeliverableType.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			OpenCPSDeliverableTypeModelImpl.UUID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeModelImpl.COMPANYID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @return the range of matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
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

		List<OpenCPSDeliverableType> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverableType openCPSDeliverableType : list) {
					if (!Objects.equals(uuid, openCPSDeliverableType.getUuid()) ||
							(companyId != openCPSDeliverableType.getCompanyId())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE);

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
				query.append(OpenCPSDeliverableTypeModelImpl.ORDER_BY_JPQL);
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
					list = (List<OpenCPSDeliverableType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableType>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (openCPSDeliverableType != null) {
			return openCPSDeliverableType;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		List<OpenCPSDeliverableType> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (openCPSDeliverableType != null) {
			return openCPSDeliverableType;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverableType> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverable types before and after the current open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deliverableTypeId the primary key of the current open cps deliverable type
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableType[] findByUuid_C_PrevAndNext(
		long deliverableTypeId, String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = findByPrimaryKey(deliverableTypeId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableType[] array = new OpenCPSDeliverableTypeImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, openCPSDeliverableType,
					uuid, companyId, orderByComparator, true);

			array[1] = openCPSDeliverableType;

			array[2] = getByUuid_C_PrevAndNext(session, openCPSDeliverableType,
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

	protected OpenCPSDeliverableType getByUuid_C_PrevAndNext(Session session,
		OpenCPSDeliverableType openCPSDeliverableType, String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE);

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
			query.append(OpenCPSDeliverableTypeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverableType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverableType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverable types where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (OpenCPSDeliverableType openCPSDeliverableType : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverableType);
		}
	}

	/**
	 * Returns the number of open cps deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching open cps deliverable types
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLETYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "openCPSDeliverableType.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "openCPSDeliverableType.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(openCPSDeliverableType.uuid IS NULL OR openCPSDeliverableType.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "openCPSDeliverableType.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_TYPECODE = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_typeCode",
			new String[] { String.class.getName(), Long.class.getName() },
			OpenCPSDeliverableTypeModelImpl.TYPECODE_COLUMN_BITMASK |
			OpenCPSDeliverableTypeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_TYPECODE = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_typeCode",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the open cps deliverable type where typeCode = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableTypeException} if it could not be found.
	 *
	 * @param typeCode the type code
	 * @param groupId the group ID
	 * @return the matching open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByF_typeCode(String typeCode, long groupId)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = fetchByF_typeCode(typeCode,
				groupId);

		if (openCPSDeliverableType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("typeCode=");
			msg.append(typeCode);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOpenCPSDeliverableTypeException(msg.toString());
		}

		return openCPSDeliverableType;
	}

	/**
	 * Returns the open cps deliverable type where typeCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param typeCode the type code
	 * @param groupId the group ID
	 * @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByF_typeCode(String typeCode,
		long groupId) {
		return fetchByF_typeCode(typeCode, groupId, true);
	}

	/**
	 * Returns the open cps deliverable type where typeCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param typeCode the type code
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByF_typeCode(String typeCode,
		long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { typeCode, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_TYPECODE,
					finderArgs, this);
		}

		if (result instanceof OpenCPSDeliverableType) {
			OpenCPSDeliverableType openCPSDeliverableType = (OpenCPSDeliverableType)result;

			if (!Objects.equals(typeCode, openCPSDeliverableType.getTypeCode()) ||
					(groupId != openCPSDeliverableType.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE);

			boolean bindTypeCode = false;

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_F_TYPECODE_TYPECODE_1);
			}
			else if (typeCode.equals("")) {
				query.append(_FINDER_COLUMN_F_TYPECODE_TYPECODE_3);
			}
			else {
				bindTypeCode = true;

				query.append(_FINDER_COLUMN_F_TYPECODE_TYPECODE_2);
			}

			query.append(_FINDER_COLUMN_F_TYPECODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTypeCode) {
					qPos.add(typeCode);
				}

				qPos.add(groupId);

				List<OpenCPSDeliverableType> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_TYPECODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OpenCPSDeliverableTypePersistenceImpl.fetchByF_typeCode(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OpenCPSDeliverableType openCPSDeliverableType = list.get(0);

					result = openCPSDeliverableType;

					cacheResult(openCPSDeliverableType);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TYPECODE,
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
			return (OpenCPSDeliverableType)result;
		}
	}

	/**
	 * Removes the open cps deliverable type where typeCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param typeCode the type code
	 * @param groupId the group ID
	 * @return the open cps deliverable type that was removed
	 */
	@Override
	public OpenCPSDeliverableType removeByF_typeCode(String typeCode,
		long groupId) throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = findByF_typeCode(typeCode,
				groupId);

		return remove(openCPSDeliverableType);
	}

	/**
	 * Returns the number of open cps deliverable types where typeCode = &#63; and groupId = &#63;.
	 *
	 * @param typeCode the type code
	 * @param groupId the group ID
	 * @return the number of matching open cps deliverable types
	 */
	@Override
	public int countByF_typeCode(String typeCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_TYPECODE;

		Object[] finderArgs = new Object[] { typeCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLETYPE_WHERE);

			boolean bindTypeCode = false;

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_F_TYPECODE_TYPECODE_1);
			}
			else if (typeCode.equals("")) {
				query.append(_FINDER_COLUMN_F_TYPECODE_TYPECODE_3);
			}
			else {
				bindTypeCode = true;

				query.append(_FINDER_COLUMN_F_TYPECODE_TYPECODE_2);
			}

			query.append(_FINDER_COLUMN_F_TYPECODE_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTypeCode) {
					qPos.add(typeCode);
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

	private static final String _FINDER_COLUMN_F_TYPECODE_TYPECODE_1 = "openCPSDeliverableType.typeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_TYPECODE_TYPECODE_2 = "openCPSDeliverableType.typeCode = ? AND ";
	private static final String _FINDER_COLUMN_F_TYPECODE_TYPECODE_3 = "(openCPSDeliverableType.typeCode IS NULL OR openCPSDeliverableType.typeCode = '') AND ";
	private static final String _FINDER_COLUMN_F_TYPECODE_GROUPID_2 = "openCPSDeliverableType.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID =
		new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_groupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID =
		new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_groupId",
			new String[] { Long.class.getName() },
			OpenCPSDeliverableTypeModelImpl.GROUPID_COLUMN_BITMASK |
			OpenCPSDeliverableTypeModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GROUPID = new FinderPath(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_groupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the open cps deliverable types where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByF_groupId(long groupId) {
		return findByF_groupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the open cps deliverable types where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @return the range of matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByF_groupId(long groupId,
		int start, int end) {
		return findByF_groupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable types where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByF_groupId(long groupId,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return findByF_groupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable types where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findByF_groupId(long groupId,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<OpenCPSDeliverableType> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OpenCPSDeliverableType openCPSDeliverableType : list) {
					if ((groupId != openCPSDeliverableType.getGroupId())) {
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

			query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OpenCPSDeliverableTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<OpenCPSDeliverableType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableType>)QueryUtil.list(q,
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
	 * Returns the first open cps deliverable type in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByF_groupId_First(long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = fetchByF_groupId_First(groupId,
				orderByComparator);

		if (openCPSDeliverableType != null) {
			return openCPSDeliverableType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the first open cps deliverable type in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByF_groupId_First(long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		List<OpenCPSDeliverableType> list = findByF_groupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last open cps deliverable type in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByF_groupId_Last(long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = fetchByF_groupId_Last(groupId,
				orderByComparator);

		if (openCPSDeliverableType != null) {
			return openCPSDeliverableType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOpenCPSDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the last open cps deliverable type in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByF_groupId_Last(long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		int count = countByF_groupId(groupId);

		if (count == 0) {
			return null;
		}

		List<OpenCPSDeliverableType> list = findByF_groupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the open cps deliverable types before and after the current open cps deliverable type in the ordered set where groupId = &#63;.
	 *
	 * @param deliverableTypeId the primary key of the current open cps deliverable type
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableType[] findByF_groupId_PrevAndNext(
		long deliverableTypeId, long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = findByPrimaryKey(deliverableTypeId);

		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableType[] array = new OpenCPSDeliverableTypeImpl[3];

			array[0] = getByF_groupId_PrevAndNext(session,
					openCPSDeliverableType, groupId, orderByComparator, true);

			array[1] = openCPSDeliverableType;

			array[2] = getByF_groupId_PrevAndNext(session,
					openCPSDeliverableType, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OpenCPSDeliverableType getByF_groupId_PrevAndNext(
		Session session, OpenCPSDeliverableType openCPSDeliverableType,
		long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
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

		query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE);

		query.append(_FINDER_COLUMN_F_GROUPID_GROUPID_2);

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
			query.append(OpenCPSDeliverableTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(openCPSDeliverableType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OpenCPSDeliverableType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the open cps deliverable types where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByF_groupId(long groupId) {
		for (OpenCPSDeliverableType openCPSDeliverableType : findByF_groupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(openCPSDeliverableType);
		}
	}

	/**
	 * Returns the number of open cps deliverable types where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching open cps deliverable types
	 */
	@Override
	public int countByF_groupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OPENCPSDELIVERABLETYPE_WHERE);

			query.append(_FINDER_COLUMN_F_GROUPID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_F_GROUPID_GROUPID_2 = "openCPSDeliverableType.groupId = ?";

	public OpenCPSDeliverableTypePersistenceImpl() {
		setModelClass(OpenCPSDeliverableType.class);

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
	 * Caches the open cps deliverable type in the entity cache if it is enabled.
	 *
	 * @param openCPSDeliverableType the open cps deliverable type
	 */
	@Override
	public void cacheResult(OpenCPSDeliverableType openCPSDeliverableType) {
		entityCache.putResult(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			openCPSDeliverableType.getPrimaryKey(), openCPSDeliverableType);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				openCPSDeliverableType.getUuid(),
				openCPSDeliverableType.getGroupId()
			}, openCPSDeliverableType);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_TYPECODE,
			new Object[] {
				openCPSDeliverableType.getTypeCode(),
				openCPSDeliverableType.getGroupId()
			}, openCPSDeliverableType);

		openCPSDeliverableType.resetOriginalValues();
	}

	/**
	 * Caches the open cps deliverable types in the entity cache if it is enabled.
	 *
	 * @param openCPSDeliverableTypes the open cps deliverable types
	 */
	@Override
	public void cacheResult(
		List<OpenCPSDeliverableType> openCPSDeliverableTypes) {
		for (OpenCPSDeliverableType openCPSDeliverableType : openCPSDeliverableTypes) {
			if (entityCache.getResult(
						OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
						OpenCPSDeliverableTypeImpl.class,
						openCPSDeliverableType.getPrimaryKey()) == null) {
				cacheResult(openCPSDeliverableType);
			}
			else {
				openCPSDeliverableType.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all open cps deliverable types.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OpenCPSDeliverableTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the open cps deliverable type.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OpenCPSDeliverableType openCPSDeliverableType) {
		entityCache.removeResult(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			openCPSDeliverableType.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OpenCPSDeliverableTypeModelImpl)openCPSDeliverableType,
			true);
	}

	@Override
	public void clearCache(List<OpenCPSDeliverableType> openCPSDeliverableTypes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OpenCPSDeliverableType openCPSDeliverableType : openCPSDeliverableTypes) {
			entityCache.removeResult(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
				OpenCPSDeliverableTypeImpl.class,
				openCPSDeliverableType.getPrimaryKey());

			clearUniqueFindersCache((OpenCPSDeliverableTypeModelImpl)openCPSDeliverableType,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OpenCPSDeliverableTypeModelImpl openCPSDeliverableTypeModelImpl) {
		Object[] args = new Object[] {
				openCPSDeliverableTypeModelImpl.getUuid(),
				openCPSDeliverableTypeModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			openCPSDeliverableTypeModelImpl, false);

		args = new Object[] {
				openCPSDeliverableTypeModelImpl.getTypeCode(),
				openCPSDeliverableTypeModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_TYPECODE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_TYPECODE, args,
			openCPSDeliverableTypeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OpenCPSDeliverableTypeModelImpl openCPSDeliverableTypeModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					openCPSDeliverableTypeModelImpl.getUuid(),
					openCPSDeliverableTypeModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((openCPSDeliverableTypeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					openCPSDeliverableTypeModelImpl.getOriginalUuid(),
					openCPSDeliverableTypeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					openCPSDeliverableTypeModelImpl.getTypeCode(),
					openCPSDeliverableTypeModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TYPECODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TYPECODE, args);
		}

		if ((openCPSDeliverableTypeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_TYPECODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					openCPSDeliverableTypeModelImpl.getOriginalTypeCode(),
					openCPSDeliverableTypeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_TYPECODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_TYPECODE, args);
		}
	}

	/**
	 * Creates a new open cps deliverable type with the primary key. Does not add the open cps deliverable type to the database.
	 *
	 * @param deliverableTypeId the primary key for the new open cps deliverable type
	 * @return the new open cps deliverable type
	 */
	@Override
	public OpenCPSDeliverableType create(long deliverableTypeId) {
		OpenCPSDeliverableType openCPSDeliverableType = new OpenCPSDeliverableTypeImpl();

		openCPSDeliverableType.setNew(true);
		openCPSDeliverableType.setPrimaryKey(deliverableTypeId);

		String uuid = PortalUUIDUtil.generate();

		openCPSDeliverableType.setUuid(uuid);

		openCPSDeliverableType.setCompanyId(companyProvider.getCompanyId());

		return openCPSDeliverableType;
	}

	/**
	 * Removes the open cps deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deliverableTypeId the primary key of the open cps deliverable type
	 * @return the open cps deliverable type that was removed
	 * @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableType remove(long deliverableTypeId)
		throws NoSuchOpenCPSDeliverableTypeException {
		return remove((Serializable)deliverableTypeId);
	}

	/**
	 * Removes the open cps deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the open cps deliverable type
	 * @return the open cps deliverable type that was removed
	 * @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableType remove(Serializable primaryKey)
		throws NoSuchOpenCPSDeliverableTypeException {
		Session session = null;

		try {
			session = openSession();

			OpenCPSDeliverableType openCPSDeliverableType = (OpenCPSDeliverableType)session.get(OpenCPSDeliverableTypeImpl.class,
					primaryKey);

			if (openCPSDeliverableType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOpenCPSDeliverableTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(openCPSDeliverableType);
		}
		catch (NoSuchOpenCPSDeliverableTypeException nsee) {
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
	protected OpenCPSDeliverableType removeImpl(
		OpenCPSDeliverableType openCPSDeliverableType) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(openCPSDeliverableType)) {
				openCPSDeliverableType = (OpenCPSDeliverableType)session.get(OpenCPSDeliverableTypeImpl.class,
						openCPSDeliverableType.getPrimaryKeyObj());
			}

			if (openCPSDeliverableType != null) {
				session.delete(openCPSDeliverableType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (openCPSDeliverableType != null) {
			clearCache(openCPSDeliverableType);
		}

		return openCPSDeliverableType;
	}

	@Override
	public OpenCPSDeliverableType updateImpl(
		OpenCPSDeliverableType openCPSDeliverableType) {
		boolean isNew = openCPSDeliverableType.isNew();

		if (!(openCPSDeliverableType instanceof OpenCPSDeliverableTypeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(openCPSDeliverableType.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(openCPSDeliverableType);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in openCPSDeliverableType proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OpenCPSDeliverableType implementation " +
				openCPSDeliverableType.getClass());
		}

		OpenCPSDeliverableTypeModelImpl openCPSDeliverableTypeModelImpl = (OpenCPSDeliverableTypeModelImpl)openCPSDeliverableType;

		if (Validator.isNull(openCPSDeliverableType.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			openCPSDeliverableType.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (openCPSDeliverableType.getCreateDate() == null)) {
			if (serviceContext == null) {
				openCPSDeliverableType.setCreateDate(now);
			}
			else {
				openCPSDeliverableType.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!openCPSDeliverableTypeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				openCPSDeliverableType.setModifiedDate(now);
			}
			else {
				openCPSDeliverableType.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (openCPSDeliverableType.isNew()) {
				session.save(openCPSDeliverableType);

				openCPSDeliverableType.setNew(false);
			}
			else {
				openCPSDeliverableType = (OpenCPSDeliverableType)session.merge(openCPSDeliverableType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OpenCPSDeliverableTypeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					openCPSDeliverableTypeModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					openCPSDeliverableTypeModelImpl.getUuid(),
					openCPSDeliverableTypeModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { openCPSDeliverableTypeModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((openCPSDeliverableTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableTypeModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { openCPSDeliverableTypeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((openCPSDeliverableTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableTypeModelImpl.getOriginalUuid(),
						openCPSDeliverableTypeModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						openCPSDeliverableTypeModelImpl.getUuid(),
						openCPSDeliverableTypeModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((openCPSDeliverableTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						openCPSDeliverableTypeModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID,
					args);

				args = new Object[] { openCPSDeliverableTypeModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GROUPID,
					args);
			}
		}

		entityCache.putResult(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			OpenCPSDeliverableTypeImpl.class,
			openCPSDeliverableType.getPrimaryKey(), openCPSDeliverableType,
			false);

		clearUniqueFindersCache(openCPSDeliverableTypeModelImpl, false);
		cacheUniqueFindersCache(openCPSDeliverableTypeModelImpl);

		openCPSDeliverableType.resetOriginalValues();

		return openCPSDeliverableType;
	}

	/**
	 * Returns the open cps deliverable type with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the open cps deliverable type
	 * @return the open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOpenCPSDeliverableTypeException {
		OpenCPSDeliverableType openCPSDeliverableType = fetchByPrimaryKey(primaryKey);

		if (openCPSDeliverableType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOpenCPSDeliverableTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return openCPSDeliverableType;
	}

	/**
	 * Returns the open cps deliverable type with the primary key or throws a {@link NoSuchOpenCPSDeliverableTypeException} if it could not be found.
	 *
	 * @param deliverableTypeId the primary key of the open cps deliverable type
	 * @return the open cps deliverable type
	 * @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableType findByPrimaryKey(long deliverableTypeId)
		throws NoSuchOpenCPSDeliverableTypeException {
		return findByPrimaryKey((Serializable)deliverableTypeId);
	}

	/**
	 * Returns the open cps deliverable type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the open cps deliverable type
	 * @return the open cps deliverable type, or <code>null</code> if a open cps deliverable type with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
				OpenCPSDeliverableTypeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OpenCPSDeliverableType openCPSDeliverableType = (OpenCPSDeliverableType)serializable;

		if (openCPSDeliverableType == null) {
			Session session = null;

			try {
				session = openSession();

				openCPSDeliverableType = (OpenCPSDeliverableType)session.get(OpenCPSDeliverableTypeImpl.class,
						primaryKey);

				if (openCPSDeliverableType != null) {
					cacheResult(openCPSDeliverableType);
				}
				else {
					entityCache.putResult(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
						OpenCPSDeliverableTypeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableTypeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return openCPSDeliverableType;
	}

	/**
	 * Returns the open cps deliverable type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deliverableTypeId the primary key of the open cps deliverable type
	 * @return the open cps deliverable type, or <code>null</code> if a open cps deliverable type with the primary key could not be found
	 */
	@Override
	public OpenCPSDeliverableType fetchByPrimaryKey(long deliverableTypeId) {
		return fetchByPrimaryKey((Serializable)deliverableTypeId);
	}

	@Override
	public Map<Serializable, OpenCPSDeliverableType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OpenCPSDeliverableType> map = new HashMap<Serializable, OpenCPSDeliverableType>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OpenCPSDeliverableType openCPSDeliverableType = fetchByPrimaryKey(primaryKey);

			if (openCPSDeliverableType != null) {
				map.put(primaryKey, openCPSDeliverableType);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableTypeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OpenCPSDeliverableType)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE_PKS_IN);

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

			for (OpenCPSDeliverableType openCPSDeliverableType : (List<OpenCPSDeliverableType>)q.list()) {
				map.put(openCPSDeliverableType.getPrimaryKeyObj(),
					openCPSDeliverableType);

				cacheResult(openCPSDeliverableType);

				uncachedPrimaryKeys.remove(openCPSDeliverableType.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OpenCPSDeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
					OpenCPSDeliverableTypeImpl.class, primaryKey, nullModel);
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
	 * Returns all the open cps deliverable types.
	 *
	 * @return the open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the open cps deliverable types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @return the range of open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the open cps deliverable types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of open cps deliverable types
	 * @param end the upper bound of the range of open cps deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of open cps deliverable types
	 */
	@Override
	public List<OpenCPSDeliverableType> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
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

		List<OpenCPSDeliverableType> list = null;

		if (retrieveFromCache) {
			list = (List<OpenCPSDeliverableType>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPENCPSDELIVERABLETYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPENCPSDELIVERABLETYPE;

				if (pagination) {
					sql = sql.concat(OpenCPSDeliverableTypeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OpenCPSDeliverableType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OpenCPSDeliverableType>)QueryUtil.list(q,
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
	 * Removes all the open cps deliverable types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OpenCPSDeliverableType openCPSDeliverableType : findAll()) {
			remove(openCPSDeliverableType);
		}
	}

	/**
	 * Returns the number of open cps deliverable types.
	 *
	 * @return the number of open cps deliverable types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPENCPSDELIVERABLETYPE);

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
		return OpenCPSDeliverableTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the open cps deliverable type persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OpenCPSDeliverableTypeImpl.class.getName());
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
	private static final String _SQL_SELECT_OPENCPSDELIVERABLETYPE = "SELECT openCPSDeliverableType FROM OpenCPSDeliverableType openCPSDeliverableType";
	private static final String _SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE_PKS_IN = "SELECT openCPSDeliverableType FROM OpenCPSDeliverableType openCPSDeliverableType WHERE deliverableTypeId IN (";
	private static final String _SQL_SELECT_OPENCPSDELIVERABLETYPE_WHERE = "SELECT openCPSDeliverableType FROM OpenCPSDeliverableType openCPSDeliverableType WHERE ";
	private static final String _SQL_COUNT_OPENCPSDELIVERABLETYPE = "SELECT COUNT(openCPSDeliverableType) FROM OpenCPSDeliverableType openCPSDeliverableType";
	private static final String _SQL_COUNT_OPENCPSDELIVERABLETYPE_WHERE = "SELECT COUNT(openCPSDeliverableType) FROM OpenCPSDeliverableType openCPSDeliverableType WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "openCPSDeliverableType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpenCPSDeliverableType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpenCPSDeliverableType exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OpenCPSDeliverableTypePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}