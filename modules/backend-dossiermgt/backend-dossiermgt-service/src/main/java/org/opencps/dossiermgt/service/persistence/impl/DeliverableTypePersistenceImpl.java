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

package org.opencps.dossiermgt.service.persistence.impl;

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

import org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.impl.DeliverableTypeImpl;
import org.opencps.dossiermgt.model.impl.DeliverableTypeModelImpl;
import org.opencps.dossiermgt.service.persistence.DeliverableTypePersistence;

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
 * The persistence implementation for the deliverable type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DeliverableTypePersistence
 * @see org.opencps.dossiermgt.service.persistence.DeliverableTypeUtil
 * @generated
 */
@ProviderType
public class DeliverableTypePersistenceImpl extends BasePersistenceImpl<DeliverableType>
	implements DeliverableTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DeliverableTypeUtil} to access the deliverable type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DeliverableTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			DeliverableTypeModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the deliverable types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching deliverable types
	 */
	@Override
	public List<DeliverableType> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverable types
	 * @param end the upper bound of the range of deliverable types (not inclusive)
	 * @return the range of matching deliverable types
	 */
	@Override
	public List<DeliverableType> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverable types
	 * @param end the upper bound of the range of deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverable types
	 */
	@Override
	public List<DeliverableType> findByUuid(String uuid, int start, int end,
		OrderByComparator<DeliverableType> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of deliverable types
	 * @param end the upper bound of the range of deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverable types
	 */
	@Override
	public List<DeliverableType> findByUuid(String uuid, int start, int end,
		OrderByComparator<DeliverableType> orderByComparator,
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

		List<DeliverableType> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DeliverableType deliverableType : list) {
					if (!Objects.equals(uuid, deliverableType.getUuid())) {
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

			query.append(_SQL_SELECT_DELIVERABLETYPE_WHERE);

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
				query.append(DeliverableTypeModelImpl.ORDER_BY_JPQL);
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
					list = (List<DeliverableType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableType>)QueryUtil.list(q,
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
	 * Returns the first deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type
	 * @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType findByUuid_First(String uuid,
		OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = fetchByUuid_First(uuid,
				orderByComparator);

		if (deliverableType != null) {
			return deliverableType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the first deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType fetchByUuid_First(String uuid,
		OrderByComparator<DeliverableType> orderByComparator) {
		List<DeliverableType> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type
	 * @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType findByUuid_Last(String uuid,
		OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = fetchByUuid_Last(uuid,
				orderByComparator);

		if (deliverableType != null) {
			return deliverableType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the last deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType fetchByUuid_Last(String uuid,
		OrderByComparator<DeliverableType> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DeliverableType> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverable types before and after the current deliverable type in the ordered set where uuid = &#63;.
	 *
	 * @param deliverableTypeId the primary key of the current deliverable type
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable type
	 * @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	 */
	@Override
	public DeliverableType[] findByUuid_PrevAndNext(long deliverableTypeId,
		String uuid, OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = findByPrimaryKey(deliverableTypeId);

		Session session = null;

		try {
			session = openSession();

			DeliverableType[] array = new DeliverableTypeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, deliverableType, uuid,
					orderByComparator, true);

			array[1] = deliverableType;

			array[2] = getByUuid_PrevAndNext(session, deliverableType, uuid,
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

	protected DeliverableType getByUuid_PrevAndNext(Session session,
		DeliverableType deliverableType, String uuid,
		OrderByComparator<DeliverableType> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DELIVERABLETYPE_WHERE);

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
			query.append(DeliverableTypeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(deliverableType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeliverableType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverable types where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DeliverableType deliverableType : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(deliverableType);
		}
	}

	/**
	 * Returns the number of deliverable types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching deliverable types
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DELIVERABLETYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "deliverableType.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "deliverableType.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(deliverableType.uuid IS NULL OR deliverableType.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			DeliverableTypeModelImpl.UUID_COLUMN_BITMASK |
			DeliverableTypeModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the deliverable type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableTypeException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliverable type
	 * @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType findByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = fetchByUUID_G(uuid, groupId);

		if (deliverableType == null) {
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

			throw new NoSuchDeliverableTypeException(msg.toString());
		}

		return deliverableType;
	}

	/**
	 * Returns the deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof DeliverableType) {
			DeliverableType deliverableType = (DeliverableType)result;

			if (!Objects.equals(uuid, deliverableType.getUuid()) ||
					(groupId != deliverableType.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DELIVERABLETYPE_WHERE);

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

				List<DeliverableType> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					DeliverableType deliverableType = list.get(0);

					result = deliverableType;

					cacheResult(deliverableType);
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
			return (DeliverableType)result;
		}
	}

	/**
	 * Removes the deliverable type where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the deliverable type that was removed
	 */
	@Override
	public DeliverableType removeByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = findByUUID_G(uuid, groupId);

		return remove(deliverableType);
	}

	/**
	 * Returns the number of deliverable types where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching deliverable types
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLETYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "deliverableType.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "deliverableType.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(deliverableType.uuid IS NULL OR deliverableType.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "deliverableType.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DeliverableTypeModelImpl.UUID_COLUMN_BITMASK |
			DeliverableTypeModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching deliverable types
	 */
	@Override
	public List<DeliverableType> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverable types
	 * @param end the upper bound of the range of deliverable types (not inclusive)
	 * @return the range of matching deliverable types
	 */
	@Override
	public List<DeliverableType> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverable types
	 * @param end the upper bound of the range of deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching deliverable types
	 */
	@Override
	public List<DeliverableType> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DeliverableType> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of deliverable types
	 * @param end the upper bound of the range of deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching deliverable types
	 */
	@Override
	public List<DeliverableType> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<DeliverableType> orderByComparator,
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

		List<DeliverableType> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableType>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DeliverableType deliverableType : list) {
					if (!Objects.equals(uuid, deliverableType.getUuid()) ||
							(companyId != deliverableType.getCompanyId())) {
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

			query.append(_SQL_SELECT_DELIVERABLETYPE_WHERE);

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
				query.append(DeliverableTypeModelImpl.ORDER_BY_JPQL);
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
					list = (List<DeliverableType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableType>)QueryUtil.list(q,
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
	 * Returns the first deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type
	 * @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (deliverableType != null) {
			return deliverableType;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the first deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DeliverableType> orderByComparator) {
		List<DeliverableType> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type
	 * @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (deliverableType != null) {
			return deliverableType;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDeliverableTypeException(msg.toString());
	}

	/**
	 * Returns the last deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DeliverableType> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DeliverableType> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the deliverable types before and after the current deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param deliverableTypeId the primary key of the current deliverable type
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next deliverable type
	 * @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	 */
	@Override
	public DeliverableType[] findByUuid_C_PrevAndNext(long deliverableTypeId,
		String uuid, long companyId,
		OrderByComparator<DeliverableType> orderByComparator)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = findByPrimaryKey(deliverableTypeId);

		Session session = null;

		try {
			session = openSession();

			DeliverableType[] array = new DeliverableTypeImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, deliverableType, uuid,
					companyId, orderByComparator, true);

			array[1] = deliverableType;

			array[2] = getByUuid_C_PrevAndNext(session, deliverableType, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DeliverableType getByUuid_C_PrevAndNext(Session session,
		DeliverableType deliverableType, String uuid, long companyId,
		OrderByComparator<DeliverableType> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DELIVERABLETYPE_WHERE);

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
			query.append(DeliverableTypeModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(deliverableType);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DeliverableType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the deliverable types where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DeliverableType deliverableType : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(deliverableType);
		}
	}

	/**
	 * Returns the number of deliverable types where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching deliverable types
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLETYPE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "deliverableType.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "deliverableType.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(deliverableType.uuid IS NULL OR deliverableType.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "deliverableType.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_DLT = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED,
			DeliverableTypeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_DLT",
			new String[] { Long.class.getName(), String.class.getName() },
			DeliverableTypeModelImpl.GROUPID_COLUMN_BITMASK |
			DeliverableTypeModelImpl.TYPECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_DLT = new FinderPath(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_DLT",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the deliverable type where groupId = &#63; and typeCode = &#63; or throws a {@link NoSuchDeliverableTypeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @return the matching deliverable type
	 * @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType findByG_DLT(long groupId, String typeCode)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = fetchByG_DLT(groupId, typeCode);

		if (deliverableType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", typeCode=");
			msg.append(typeCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDeliverableTypeException(msg.toString());
		}

		return deliverableType;
	}

	/**
	 * Returns the deliverable type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType fetchByG_DLT(long groupId, String typeCode) {
		return fetchByG_DLT(groupId, typeCode, true);
	}

	/**
	 * Returns the deliverable type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	 */
	@Override
	public DeliverableType fetchByG_DLT(long groupId, String typeCode,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, typeCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_DLT,
					finderArgs, this);
		}

		if (result instanceof DeliverableType) {
			DeliverableType deliverableType = (DeliverableType)result;

			if ((groupId != deliverableType.getGroupId()) ||
					!Objects.equals(typeCode, deliverableType.getTypeCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DELIVERABLETYPE_WHERE);

			query.append(_FINDER_COLUMN_G_DLT_GROUPID_2);

			boolean bindTypeCode = false;

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_G_DLT_TYPECODE_1);
			}
			else if (typeCode.equals("")) {
				query.append(_FINDER_COLUMN_G_DLT_TYPECODE_3);
			}
			else {
				bindTypeCode = true;

				query.append(_FINDER_COLUMN_G_DLT_TYPECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTypeCode) {
					qPos.add(typeCode);
				}

				List<DeliverableType> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_DLT,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"DeliverableTypePersistenceImpl.fetchByG_DLT(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DeliverableType deliverableType = list.get(0);

					result = deliverableType;

					cacheResult(deliverableType);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DLT, finderArgs);

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
			return (DeliverableType)result;
		}
	}

	/**
	 * Removes the deliverable type where groupId = &#63; and typeCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @return the deliverable type that was removed
	 */
	@Override
	public DeliverableType removeByG_DLT(long groupId, String typeCode)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = findByG_DLT(groupId, typeCode);

		return remove(deliverableType);
	}

	/**
	 * Returns the number of deliverable types where groupId = &#63; and typeCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param typeCode the type code
	 * @return the number of matching deliverable types
	 */
	@Override
	public int countByG_DLT(long groupId, String typeCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_DLT;

		Object[] finderArgs = new Object[] { groupId, typeCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DELIVERABLETYPE_WHERE);

			query.append(_FINDER_COLUMN_G_DLT_GROUPID_2);

			boolean bindTypeCode = false;

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_G_DLT_TYPECODE_1);
			}
			else if (typeCode.equals("")) {
				query.append(_FINDER_COLUMN_G_DLT_TYPECODE_3);
			}
			else {
				bindTypeCode = true;

				query.append(_FINDER_COLUMN_G_DLT_TYPECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindTypeCode) {
					qPos.add(typeCode);
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

	private static final String _FINDER_COLUMN_G_DLT_GROUPID_2 = "deliverableType.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_DLT_TYPECODE_1 = "deliverableType.typeCode IS NULL";
	private static final String _FINDER_COLUMN_G_DLT_TYPECODE_2 = "deliverableType.typeCode = ?";
	private static final String _FINDER_COLUMN_G_DLT_TYPECODE_3 = "(deliverableType.typeCode IS NULL OR deliverableType.typeCode = '')";

	public DeliverableTypePersistenceImpl() {
		setModelClass(DeliverableType.class);

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
	 * Caches the deliverable type in the entity cache if it is enabled.
	 *
	 * @param deliverableType the deliverable type
	 */
	@Override
	public void cacheResult(DeliverableType deliverableType) {
		entityCache.putResult(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeImpl.class, deliverableType.getPrimaryKey(),
			deliverableType);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { deliverableType.getUuid(), deliverableType.getGroupId() },
			deliverableType);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DLT,
			new Object[] {
				deliverableType.getGroupId(), deliverableType.getTypeCode()
			}, deliverableType);

		deliverableType.resetOriginalValues();
	}

	/**
	 * Caches the deliverable types in the entity cache if it is enabled.
	 *
	 * @param deliverableTypes the deliverable types
	 */
	@Override
	public void cacheResult(List<DeliverableType> deliverableTypes) {
		for (DeliverableType deliverableType : deliverableTypes) {
			if (entityCache.getResult(
						DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
						DeliverableTypeImpl.class,
						deliverableType.getPrimaryKey()) == null) {
				cacheResult(deliverableType);
			}
			else {
				deliverableType.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all deliverable types.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DeliverableTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the deliverable type.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DeliverableType deliverableType) {
		entityCache.removeResult(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeImpl.class, deliverableType.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DeliverableTypeModelImpl)deliverableType, true);
	}

	@Override
	public void clearCache(List<DeliverableType> deliverableTypes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DeliverableType deliverableType : deliverableTypes) {
			entityCache.removeResult(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
				DeliverableTypeImpl.class, deliverableType.getPrimaryKey());

			clearUniqueFindersCache((DeliverableTypeModelImpl)deliverableType,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		DeliverableTypeModelImpl deliverableTypeModelImpl) {
		Object[] args = new Object[] {
				deliverableTypeModelImpl.getUuid(),
				deliverableTypeModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			deliverableTypeModelImpl, false);

		args = new Object[] {
				deliverableTypeModelImpl.getGroupId(),
				deliverableTypeModelImpl.getTypeCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_DLT, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_DLT, args,
			deliverableTypeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DeliverableTypeModelImpl deliverableTypeModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					deliverableTypeModelImpl.getUuid(),
					deliverableTypeModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((deliverableTypeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableTypeModelImpl.getOriginalUuid(),
					deliverableTypeModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					deliverableTypeModelImpl.getGroupId(),
					deliverableTypeModelImpl.getTypeCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DLT, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DLT, args);
		}

		if ((deliverableTypeModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_DLT.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					deliverableTypeModelImpl.getOriginalGroupId(),
					deliverableTypeModelImpl.getOriginalTypeCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_DLT, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_DLT, args);
		}
	}

	/**
	 * Creates a new deliverable type with the primary key. Does not add the deliverable type to the database.
	 *
	 * @param deliverableTypeId the primary key for the new deliverable type
	 * @return the new deliverable type
	 */
	@Override
	public DeliverableType create(long deliverableTypeId) {
		DeliverableType deliverableType = new DeliverableTypeImpl();

		deliverableType.setNew(true);
		deliverableType.setPrimaryKey(deliverableTypeId);

		String uuid = PortalUUIDUtil.generate();

		deliverableType.setUuid(uuid);

		deliverableType.setCompanyId(companyProvider.getCompanyId());

		return deliverableType;
	}

	/**
	 * Removes the deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deliverableTypeId the primary key of the deliverable type
	 * @return the deliverable type that was removed
	 * @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	 */
	@Override
	public DeliverableType remove(long deliverableTypeId)
		throws NoSuchDeliverableTypeException {
		return remove((Serializable)deliverableTypeId);
	}

	/**
	 * Removes the deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the deliverable type
	 * @return the deliverable type that was removed
	 * @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	 */
	@Override
	public DeliverableType remove(Serializable primaryKey)
		throws NoSuchDeliverableTypeException {
		Session session = null;

		try {
			session = openSession();

			DeliverableType deliverableType = (DeliverableType)session.get(DeliverableTypeImpl.class,
					primaryKey);

			if (deliverableType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeliverableTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(deliverableType);
		}
		catch (NoSuchDeliverableTypeException nsee) {
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
	protected DeliverableType removeImpl(DeliverableType deliverableType) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(deliverableType)) {
				deliverableType = (DeliverableType)session.get(DeliverableTypeImpl.class,
						deliverableType.getPrimaryKeyObj());
			}

			if (deliverableType != null) {
				session.delete(deliverableType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (deliverableType != null) {
			clearCache(deliverableType);
		}

		return deliverableType;
	}

	@Override
	public DeliverableType updateImpl(DeliverableType deliverableType) {
		boolean isNew = deliverableType.isNew();

		if (!(deliverableType instanceof DeliverableTypeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(deliverableType.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(deliverableType);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in deliverableType proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DeliverableType implementation " +
				deliverableType.getClass());
		}

		DeliverableTypeModelImpl deliverableTypeModelImpl = (DeliverableTypeModelImpl)deliverableType;

		if (Validator.isNull(deliverableType.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			deliverableType.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (deliverableType.getCreateDate() == null)) {
			if (serviceContext == null) {
				deliverableType.setCreateDate(now);
			}
			else {
				deliverableType.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!deliverableTypeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				deliverableType.setModifiedDate(now);
			}
			else {
				deliverableType.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (deliverableType.isNew()) {
				session.save(deliverableType);

				deliverableType.setNew(false);
			}
			else {
				deliverableType = (DeliverableType)session.merge(deliverableType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DeliverableTypeModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { deliverableTypeModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					deliverableTypeModelImpl.getUuid(),
					deliverableTypeModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((deliverableTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableTypeModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { deliverableTypeModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((deliverableTypeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						deliverableTypeModelImpl.getOriginalUuid(),
						deliverableTypeModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						deliverableTypeModelImpl.getUuid(),
						deliverableTypeModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
			DeliverableTypeImpl.class, deliverableType.getPrimaryKey(),
			deliverableType, false);

		clearUniqueFindersCache(deliverableTypeModelImpl, false);
		cacheUniqueFindersCache(deliverableTypeModelImpl);

		deliverableType.resetOriginalValues();

		return deliverableType;
	}

	/**
	 * Returns the deliverable type with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliverable type
	 * @return the deliverable type
	 * @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	 */
	@Override
	public DeliverableType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeliverableTypeException {
		DeliverableType deliverableType = fetchByPrimaryKey(primaryKey);

		if (deliverableType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeliverableTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return deliverableType;
	}

	/**
	 * Returns the deliverable type with the primary key or throws a {@link NoSuchDeliverableTypeException} if it could not be found.
	 *
	 * @param deliverableTypeId the primary key of the deliverable type
	 * @return the deliverable type
	 * @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	 */
	@Override
	public DeliverableType findByPrimaryKey(long deliverableTypeId)
		throws NoSuchDeliverableTypeException {
		return findByPrimaryKey((Serializable)deliverableTypeId);
	}

	/**
	 * Returns the deliverable type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deliverable type
	 * @return the deliverable type, or <code>null</code> if a deliverable type with the primary key could not be found
	 */
	@Override
	public DeliverableType fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
				DeliverableTypeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DeliverableType deliverableType = (DeliverableType)serializable;

		if (deliverableType == null) {
			Session session = null;

			try {
				session = openSession();

				deliverableType = (DeliverableType)session.get(DeliverableTypeImpl.class,
						primaryKey);

				if (deliverableType != null) {
					cacheResult(deliverableType);
				}
				else {
					entityCache.putResult(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
						DeliverableTypeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableTypeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return deliverableType;
	}

	/**
	 * Returns the deliverable type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deliverableTypeId the primary key of the deliverable type
	 * @return the deliverable type, or <code>null</code> if a deliverable type with the primary key could not be found
	 */
	@Override
	public DeliverableType fetchByPrimaryKey(long deliverableTypeId) {
		return fetchByPrimaryKey((Serializable)deliverableTypeId);
	}

	@Override
	public Map<Serializable, DeliverableType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DeliverableType> map = new HashMap<Serializable, DeliverableType>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DeliverableType deliverableType = fetchByPrimaryKey(primaryKey);

			if (deliverableType != null) {
				map.put(primaryKey, deliverableType);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableTypeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DeliverableType)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DELIVERABLETYPE_WHERE_PKS_IN);

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

			for (DeliverableType deliverableType : (List<DeliverableType>)q.list()) {
				map.put(deliverableType.getPrimaryKeyObj(), deliverableType);

				cacheResult(deliverableType);

				uncachedPrimaryKeys.remove(deliverableType.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DeliverableTypeModelImpl.ENTITY_CACHE_ENABLED,
					DeliverableTypeImpl.class, primaryKey, nullModel);
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
	 * Returns all the deliverable types.
	 *
	 * @return the deliverable types
	 */
	@Override
	public List<DeliverableType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deliverable types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverable types
	 * @param end the upper bound of the range of deliverable types (not inclusive)
	 * @return the range of deliverable types
	 */
	@Override
	public List<DeliverableType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the deliverable types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverable types
	 * @param end the upper bound of the range of deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of deliverable types
	 */
	@Override
	public List<DeliverableType> findAll(int start, int end,
		OrderByComparator<DeliverableType> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deliverable types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliverable types
	 * @param end the upper bound of the range of deliverable types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of deliverable types
	 */
	@Override
	public List<DeliverableType> findAll(int start, int end,
		OrderByComparator<DeliverableType> orderByComparator,
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

		List<DeliverableType> list = null;

		if (retrieveFromCache) {
			list = (List<DeliverableType>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DELIVERABLETYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DELIVERABLETYPE;

				if (pagination) {
					sql = sql.concat(DeliverableTypeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DeliverableType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeliverableType>)QueryUtil.list(q,
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
	 * Removes all the deliverable types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DeliverableType deliverableType : findAll()) {
			remove(deliverableType);
		}
	}

	/**
	 * Returns the number of deliverable types.
	 *
	 * @return the number of deliverable types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DELIVERABLETYPE);

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
		return DeliverableTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the deliverable type persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DeliverableTypeImpl.class.getName());
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
	private static final String _SQL_SELECT_DELIVERABLETYPE = "SELECT deliverableType FROM DeliverableType deliverableType";
	private static final String _SQL_SELECT_DELIVERABLETYPE_WHERE_PKS_IN = "SELECT deliverableType FROM DeliverableType deliverableType WHERE deliverableTypeId IN (";
	private static final String _SQL_SELECT_DELIVERABLETYPE_WHERE = "SELECT deliverableType FROM DeliverableType deliverableType WHERE ";
	private static final String _SQL_COUNT_DELIVERABLETYPE = "SELECT COUNT(deliverableType) FROM DeliverableType deliverableType";
	private static final String _SQL_COUNT_DELIVERABLETYPE_WHERE = "SELECT COUNT(deliverableType) FROM DeliverableType deliverableType WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "deliverableType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeliverableType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DeliverableType exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(DeliverableTypePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}