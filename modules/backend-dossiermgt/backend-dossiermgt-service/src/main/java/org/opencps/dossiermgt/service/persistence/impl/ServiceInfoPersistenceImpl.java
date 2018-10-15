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

import org.opencps.dossiermgt.exception.NoSuchServiceInfoException;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.impl.ServiceInfoImpl;
import org.opencps.dossiermgt.model.impl.ServiceInfoModelImpl;
import org.opencps.dossiermgt.service.persistence.ServiceInfoPersistence;

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
 * The persistence implementation for the service info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceInfoPersistence
 * @see org.opencps.dossiermgt.service.persistence.ServiceInfoUtil
 * @generated
 */
@ProviderType
public class ServiceInfoPersistenceImpl extends BasePersistenceImpl<ServiceInfo>
	implements ServiceInfoPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ServiceInfoUtil} to access the service info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ServiceInfoImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ServiceInfoModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the service infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching service infos
	 */
	@Override
	public List<ServiceInfo> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @return the range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByUuid(String uuid, int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByUuid(String uuid, int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator,
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

		List<ServiceInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceInfo serviceInfo : list) {
					if (!Objects.equals(uuid, serviceInfo.getUuid())) {
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

			query.append(_SQL_SELECT_SERVICEINFO_WHERE);

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
				query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first service info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByUuid_First(String uuid,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByUuid_First(uuid, orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the first service info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByUuid_First(String uuid,
		OrderByComparator<ServiceInfo> orderByComparator) {
		List<ServiceInfo> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByUuid_Last(String uuid,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByUuid_Last(uuid, orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the last service info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceInfo> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ServiceInfo> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service infos before and after the current service info in the ordered set where uuid = &#63;.
	 *
	 * @param serviceInfoId the primary key of the current service info
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service info
	 * @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo[] findByUuid_PrevAndNext(long serviceInfoId,
		String uuid, OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = findByPrimaryKey(serviceInfoId);

		Session session = null;

		try {
			session = openSession();

			ServiceInfo[] array = new ServiceInfoImpl[3];

			array[0] = getByUuid_PrevAndNext(session, serviceInfo, uuid,
					orderByComparator, true);

			array[1] = serviceInfo;

			array[2] = getByUuid_PrevAndNext(session, serviceInfo, uuid,
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

	protected ServiceInfo getByUuid_PrevAndNext(Session session,
		ServiceInfo serviceInfo, String uuid,
		OrderByComparator<ServiceInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVICEINFO_WHERE);

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
			query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service infos where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ServiceInfo serviceInfo : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(serviceInfo);
		}
	}

	/**
	 * Returns the number of service infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching service infos
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "serviceInfo.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "serviceInfo.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(serviceInfo.uuid IS NULL OR serviceInfo.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceInfoModelImpl.UUID_COLUMN_BITMASK |
			ServiceInfoModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the service info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceInfoException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByUUID_G(String uuid, long groupId)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByUUID_G(uuid, groupId);

		if (serviceInfo == null) {
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

			throw new NoSuchServiceInfoException(msg.toString());
		}

		return serviceInfo;
	}

	/**
	 * Returns the service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ServiceInfo) {
			ServiceInfo serviceInfo = (ServiceInfo)result;

			if (!Objects.equals(uuid, serviceInfo.getUuid()) ||
					(groupId != serviceInfo.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SERVICEINFO_WHERE);

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

				List<ServiceInfo> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ServiceInfo serviceInfo = list.get(0);

					result = serviceInfo;

					cacheResult(serviceInfo);
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
			return (ServiceInfo)result;
		}
	}

	/**
	 * Removes the service info where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the service info that was removed
	 */
	@Override
	public ServiceInfo removeByUUID_G(String uuid, long groupId)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = findByUUID_G(uuid, groupId);

		return remove(serviceInfo);
	}

	/**
	 * Returns the number of service infos where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching service infos
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "serviceInfo.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "serviceInfo.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(serviceInfo.uuid IS NULL OR serviceInfo.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "serviceInfo.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceInfoModelImpl.UUID_COLUMN_BITMASK |
			ServiceInfoModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching service infos
	 */
	@Override
	public List<ServiceInfo> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @return the range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ServiceInfo> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ServiceInfo> orderByComparator,
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

		List<ServiceInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceInfo serviceInfo : list) {
					if (!Objects.equals(uuid, serviceInfo.getUuid()) ||
							(companyId != serviceInfo.getCompanyId())) {
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

			query.append(_SQL_SELECT_SERVICEINFO_WHERE);

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
				query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the first service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		List<ServiceInfo> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the last service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ServiceInfo> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service infos before and after the current service info in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param serviceInfoId the primary key of the current service info
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service info
	 * @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo[] findByUuid_C_PrevAndNext(long serviceInfoId,
		String uuid, long companyId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = findByPrimaryKey(serviceInfoId);

		Session session = null;

		try {
			session = openSession();

			ServiceInfo[] array = new ServiceInfoImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, serviceInfo, uuid,
					companyId, orderByComparator, true);

			array[1] = serviceInfo;

			array[2] = getByUuid_C_PrevAndNext(session, serviceInfo, uuid,
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

	protected ServiceInfo getByUuid_C_PrevAndNext(Session session,
		ServiceInfo serviceInfo, String uuid, long companyId,
		OrderByComparator<ServiceInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SERVICEINFO_WHERE);

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
			query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service infos where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ServiceInfo serviceInfo : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceInfo);
		}
	}

	/**
	 * Returns the number of service infos where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching service infos
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICEINFO_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "serviceInfo.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "serviceInfo.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(serviceInfo.uuid IS NULL OR serviceInfo.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "serviceInfo.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ServiceInfoModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the service infos where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching service infos
	 */
	@Override
	public List<ServiceInfo> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service infos where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @return the range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service infos where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service infos where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<ServiceInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceInfo serviceInfo : list) {
					if ((groupId != serviceInfo.getGroupId())) {
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

			query.append(_SQL_SELECT_SERVICEINFO_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first service info in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByGroupId_First(long groupId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByGroupId_First(groupId,
				orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the first service info in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByGroupId_First(long groupId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		List<ServiceInfo> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service info in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByGroupId_Last(long groupId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByGroupId_Last(groupId, orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the last service info in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByGroupId_Last(long groupId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ServiceInfo> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service infos before and after the current service info in the ordered set where groupId = &#63;.
	 *
	 * @param serviceInfoId the primary key of the current service info
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service info
	 * @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo[] findByGroupId_PrevAndNext(long serviceInfoId,
		long groupId, OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = findByPrimaryKey(serviceInfoId);

		Session session = null;

		try {
			session = openSession();

			ServiceInfo[] array = new ServiceInfoImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, serviceInfo, groupId,
					orderByComparator, true);

			array[1] = serviceInfo;

			array[2] = getByGroupId_PrevAndNext(session, serviceInfo, groupId,
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

	protected ServiceInfo getByGroupId_PrevAndNext(Session session,
		ServiceInfo serviceInfo, long groupId,
		OrderByComparator<ServiceInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVICEINFO_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service infos where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (ServiceInfo serviceInfo : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceInfo);
		}
	}

	/**
	 * Returns the number of service infos where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching service infos
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEINFO_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "serviceInfo.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			ServiceInfoModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the service infos where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching service infos
	 */
	@Override
	public List<ServiceInfo> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the service infos where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @return the range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service infos where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<ServiceInfo> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service infos where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<ServiceInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceInfo serviceInfo : list) {
					if ((companyId != serviceInfo.getCompanyId())) {
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

			query.append(_SQL_SELECT_SERVICEINFO_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first service info in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByCompanyId_First(long companyId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the first service info in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByCompanyId_First(long companyId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		List<ServiceInfo> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service info in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByCompanyId_Last(long companyId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the last service info in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByCompanyId_Last(long companyId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<ServiceInfo> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service infos before and after the current service info in the ordered set where companyId = &#63;.
	 *
	 * @param serviceInfoId the primary key of the current service info
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service info
	 * @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo[] findByCompanyId_PrevAndNext(long serviceInfoId,
		long companyId, OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = findByPrimaryKey(serviceInfoId);

		Session session = null;

		try {
			session = openSession();

			ServiceInfo[] array = new ServiceInfoImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, serviceInfo,
					companyId, orderByComparator, true);

			array[1] = serviceInfo;

			array[2] = getByCompanyId_PrevAndNext(session, serviceInfo,
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

	protected ServiceInfo getByCompanyId_PrevAndNext(Session session,
		ServiceInfo serviceInfo, long companyId,
		OrderByComparator<ServiceInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVICEINFO_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service infos where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (ServiceInfo serviceInfo : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceInfo);
		}
	}

	/**
	 * Returns the number of service infos where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching service infos
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICEINFO_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "serviceInfo.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SC_GI = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBySC_GI",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceInfoModelImpl.SERVICECODE_COLUMN_BITMASK |
			ServiceInfoModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SC_GI = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySC_GI",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the service info where serviceCode = &#63; and groupId = &#63; or throws a {@link NoSuchServiceInfoException} if it could not be found.
	 *
	 * @param serviceCode the service code
	 * @param groupId the group ID
	 * @return the matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findBySC_GI(String serviceCode, long groupId)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchBySC_GI(serviceCode, groupId);

		if (serviceInfo == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("serviceCode=");
			msg.append(serviceCode);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServiceInfoException(msg.toString());
		}

		return serviceInfo;
	}

	/**
	 * Returns the service info where serviceCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param serviceCode the service code
	 * @param groupId the group ID
	 * @return the matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchBySC_GI(String serviceCode, long groupId) {
		return fetchBySC_GI(serviceCode, groupId, true);
	}

	/**
	 * Returns the service info where serviceCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param serviceCode the service code
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchBySC_GI(String serviceCode, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { serviceCode, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SC_GI,
					finderArgs, this);
		}

		if (result instanceof ServiceInfo) {
			ServiceInfo serviceInfo = (ServiceInfo)result;

			if (!Objects.equals(serviceCode, serviceInfo.getServiceCode()) ||
					(groupId != serviceInfo.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SERVICEINFO_WHERE);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_SC_GI_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_SC_GI_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_SC_GI_SERVICECODE_2);
			}

			query.append(_FINDER_COLUMN_SC_GI_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServiceCode) {
					qPos.add(serviceCode);
				}

				qPos.add(groupId);

				List<ServiceInfo> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SC_GI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServiceInfoPersistenceImpl.fetchBySC_GI(String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceInfo serviceInfo = list.get(0);

					result = serviceInfo;

					cacheResult(serviceInfo);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_GI, finderArgs);

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
			return (ServiceInfo)result;
		}
	}

	/**
	 * Removes the service info where serviceCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param serviceCode the service code
	 * @param groupId the group ID
	 * @return the service info that was removed
	 */
	@Override
	public ServiceInfo removeBySC_GI(String serviceCode, long groupId)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = findBySC_GI(serviceCode, groupId);

		return remove(serviceInfo);
	}

	/**
	 * Returns the number of service infos where serviceCode = &#63; and groupId = &#63;.
	 *
	 * @param serviceCode the service code
	 * @param groupId the group ID
	 * @return the number of matching service infos
	 */
	@Override
	public int countBySC_GI(String serviceCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SC_GI;

		Object[] finderArgs = new Object[] { serviceCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICEINFO_WHERE);

			boolean bindServiceCode = false;

			if (serviceCode == null) {
				query.append(_FINDER_COLUMN_SC_GI_SERVICECODE_1);
			}
			else if (serviceCode.equals("")) {
				query.append(_FINDER_COLUMN_SC_GI_SERVICECODE_3);
			}
			else {
				bindServiceCode = true;

				query.append(_FINDER_COLUMN_SC_GI_SERVICECODE_2);
			}

			query.append(_FINDER_COLUMN_SC_GI_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServiceCode) {
					qPos.add(serviceCode);
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

	private static final String _FINDER_COLUMN_SC_GI_SERVICECODE_1 = "serviceInfo.serviceCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SC_GI_SERVICECODE_2 = "serviceInfo.serviceCode = ? AND ";
	private static final String _FINDER_COLUMN_SC_GI_SERVICECODE_3 = "(serviceInfo.serviceCode IS NULL OR serviceInfo.serviceCode = '') AND ";
	private static final String _FINDER_COLUMN_SC_GI_GROUPID_2 = "serviceInfo.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GI_DC = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGI_DC",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_DC = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, ServiceInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGI_DC",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceInfoModelImpl.DOMAINCODE_COLUMN_BITMASK |
			ServiceInfoModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GI_DC = new FinderPath(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGI_DC",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the service infos where domainCode = &#63; and groupId = &#63;.
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @return the matching service infos
	 */
	@Override
	public List<ServiceInfo> findByGI_DC(String domainCode, long groupId) {
		return findByGI_DC(domainCode, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service infos where domainCode = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @return the range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByGI_DC(String domainCode, long groupId,
		int start, int end) {
		return findByGI_DC(domainCode, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service infos where domainCode = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByGI_DC(String domainCode, long groupId,
		int start, int end, OrderByComparator<ServiceInfo> orderByComparator) {
		return findByGI_DC(domainCode, groupId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the service infos where domainCode = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service infos
	 */
	@Override
	public List<ServiceInfo> findByGI_DC(String domainCode, long groupId,
		int start, int end, OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_DC;
			finderArgs = new Object[] { domainCode, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GI_DC;
			finderArgs = new Object[] {
					domainCode, groupId,
					
					start, end, orderByComparator
				};
		}

		List<ServiceInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceInfo>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceInfo serviceInfo : list) {
					if (!Objects.equals(domainCode, serviceInfo.getDomainCode()) ||
							(groupId != serviceInfo.getGroupId())) {
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

			query.append(_SQL_SELECT_SERVICEINFO_WHERE);

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_GI_DC_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_GI_DC_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_GI_DC_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_GI_DC_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDomainCode) {
					qPos.add(domainCode);
				}

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByGI_DC_First(String domainCode, long groupId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByGI_DC_First(domainCode, groupId,
				orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("domainCode=");
		msg.append(domainCode);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the first service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByGI_DC_First(String domainCode, long groupId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		List<ServiceInfo> list = findByGI_DC(domainCode, groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info
	 * @throws NoSuchServiceInfoException if a matching service info could not be found
	 */
	@Override
	public ServiceInfo findByGI_DC_Last(String domainCode, long groupId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByGI_DC_Last(domainCode, groupId,
				orderByComparator);

		if (serviceInfo != null) {
			return serviceInfo;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("domainCode=");
		msg.append(domainCode);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchServiceInfoException(msg.toString());
	}

	/**
	 * Returns the last service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service info, or <code>null</code> if a matching service info could not be found
	 */
	@Override
	public ServiceInfo fetchByGI_DC_Last(String domainCode, long groupId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		int count = countByGI_DC(domainCode, groupId);

		if (count == 0) {
			return null;
		}

		List<ServiceInfo> list = findByGI_DC(domainCode, groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service infos before and after the current service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	 *
	 * @param serviceInfoId the primary key of the current service info
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service info
	 * @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo[] findByGI_DC_PrevAndNext(long serviceInfoId,
		String domainCode, long groupId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = findByPrimaryKey(serviceInfoId);

		Session session = null;

		try {
			session = openSession();

			ServiceInfo[] array = new ServiceInfoImpl[3];

			array[0] = getByGI_DC_PrevAndNext(session, serviceInfo, domainCode,
					groupId, orderByComparator, true);

			array[1] = serviceInfo;

			array[2] = getByGI_DC_PrevAndNext(session, serviceInfo, domainCode,
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

	protected ServiceInfo getByGI_DC_PrevAndNext(Session session,
		ServiceInfo serviceInfo, String domainCode, long groupId,
		OrderByComparator<ServiceInfo> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SERVICEINFO_WHERE);

		boolean bindDomainCode = false;

		if (domainCode == null) {
			query.append(_FINDER_COLUMN_GI_DC_DOMAINCODE_1);
		}
		else if (domainCode.equals("")) {
			query.append(_FINDER_COLUMN_GI_DC_DOMAINCODE_3);
		}
		else {
			bindDomainCode = true;

			query.append(_FINDER_COLUMN_GI_DC_DOMAINCODE_2);
		}

		query.append(_FINDER_COLUMN_GI_DC_GROUPID_2);

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
			query.append(ServiceInfoModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDomainCode) {
			qPos.add(domainCode);
		}

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceInfo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceInfo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service infos where domainCode = &#63; and groupId = &#63; from the database.
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGI_DC(String domainCode, long groupId) {
		for (ServiceInfo serviceInfo : findByGI_DC(domainCode, groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceInfo);
		}
	}

	/**
	 * Returns the number of service infos where domainCode = &#63; and groupId = &#63;.
	 *
	 * @param domainCode the domain code
	 * @param groupId the group ID
	 * @return the number of matching service infos
	 */
	@Override
	public int countByGI_DC(String domainCode, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GI_DC;

		Object[] finderArgs = new Object[] { domainCode, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICEINFO_WHERE);

			boolean bindDomainCode = false;

			if (domainCode == null) {
				query.append(_FINDER_COLUMN_GI_DC_DOMAINCODE_1);
			}
			else if (domainCode.equals("")) {
				query.append(_FINDER_COLUMN_GI_DC_DOMAINCODE_3);
			}
			else {
				bindDomainCode = true;

				query.append(_FINDER_COLUMN_GI_DC_DOMAINCODE_2);
			}

			query.append(_FINDER_COLUMN_GI_DC_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDomainCode) {
					qPos.add(domainCode);
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

	private static final String _FINDER_COLUMN_GI_DC_DOMAINCODE_1 = "serviceInfo.domainCode IS NULL AND ";
	private static final String _FINDER_COLUMN_GI_DC_DOMAINCODE_2 = "serviceInfo.domainCode = ? AND ";
	private static final String _FINDER_COLUMN_GI_DC_DOMAINCODE_3 = "(serviceInfo.domainCode IS NULL OR serviceInfo.domainCode = '') AND ";
	private static final String _FINDER_COLUMN_GI_DC_GROUPID_2 = "serviceInfo.groupId = ?";

	public ServiceInfoPersistenceImpl() {
		setModelClass(ServiceInfo.class);

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
	 * Caches the service info in the entity cache if it is enabled.
	 *
	 * @param serviceInfo the service info
	 */
	@Override
	public void cacheResult(ServiceInfo serviceInfo) {
		entityCache.putResult(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoImpl.class, serviceInfo.getPrimaryKey(), serviceInfo);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { serviceInfo.getUuid(), serviceInfo.getGroupId() },
			serviceInfo);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SC_GI,
			new Object[] { serviceInfo.getServiceCode(), serviceInfo.getGroupId() },
			serviceInfo);

		serviceInfo.resetOriginalValues();
	}

	/**
	 * Caches the service infos in the entity cache if it is enabled.
	 *
	 * @param serviceInfos the service infos
	 */
	@Override
	public void cacheResult(List<ServiceInfo> serviceInfos) {
		for (ServiceInfo serviceInfo : serviceInfos) {
			if (entityCache.getResult(
						ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
						ServiceInfoImpl.class, serviceInfo.getPrimaryKey()) == null) {
				cacheResult(serviceInfo);
			}
			else {
				serviceInfo.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all service infos.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServiceInfoImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the service info.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServiceInfo serviceInfo) {
		entityCache.removeResult(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoImpl.class, serviceInfo.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ServiceInfoModelImpl)serviceInfo, true);
	}

	@Override
	public void clearCache(List<ServiceInfo> serviceInfos) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ServiceInfo serviceInfo : serviceInfos) {
			entityCache.removeResult(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
				ServiceInfoImpl.class, serviceInfo.getPrimaryKey());

			clearUniqueFindersCache((ServiceInfoModelImpl)serviceInfo, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ServiceInfoModelImpl serviceInfoModelImpl) {
		Object[] args = new Object[] {
				serviceInfoModelImpl.getUuid(),
				serviceInfoModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			serviceInfoModelImpl, false);

		args = new Object[] {
				serviceInfoModelImpl.getServiceCode(),
				serviceInfoModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SC_GI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SC_GI, args,
			serviceInfoModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ServiceInfoModelImpl serviceInfoModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceInfoModelImpl.getUuid(),
					serviceInfoModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((serviceInfoModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceInfoModelImpl.getOriginalUuid(),
					serviceInfoModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceInfoModelImpl.getServiceCode(),
					serviceInfoModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_GI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_GI, args);
		}

		if ((serviceInfoModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SC_GI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceInfoModelImpl.getOriginalServiceCode(),
					serviceInfoModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SC_GI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SC_GI, args);
		}
	}

	/**
	 * Creates a new service info with the primary key. Does not add the service info to the database.
	 *
	 * @param serviceInfoId the primary key for the new service info
	 * @return the new service info
	 */
	@Override
	public ServiceInfo create(long serviceInfoId) {
		ServiceInfo serviceInfo = new ServiceInfoImpl();

		serviceInfo.setNew(true);
		serviceInfo.setPrimaryKey(serviceInfoId);

		String uuid = PortalUUIDUtil.generate();

		serviceInfo.setUuid(uuid);

		serviceInfo.setCompanyId(companyProvider.getCompanyId());

		return serviceInfo;
	}

	/**
	 * Removes the service info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceInfoId the primary key of the service info
	 * @return the service info that was removed
	 * @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo remove(long serviceInfoId)
		throws NoSuchServiceInfoException {
		return remove((Serializable)serviceInfoId);
	}

	/**
	 * Removes the service info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the service info
	 * @return the service info that was removed
	 * @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo remove(Serializable primaryKey)
		throws NoSuchServiceInfoException {
		Session session = null;

		try {
			session = openSession();

			ServiceInfo serviceInfo = (ServiceInfo)session.get(ServiceInfoImpl.class,
					primaryKey);

			if (serviceInfo == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServiceInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(serviceInfo);
		}
		catch (NoSuchServiceInfoException nsee) {
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
	protected ServiceInfo removeImpl(ServiceInfo serviceInfo) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serviceInfo)) {
				serviceInfo = (ServiceInfo)session.get(ServiceInfoImpl.class,
						serviceInfo.getPrimaryKeyObj());
			}

			if (serviceInfo != null) {
				session.delete(serviceInfo);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (serviceInfo != null) {
			clearCache(serviceInfo);
		}

		return serviceInfo;
	}

	@Override
	public ServiceInfo updateImpl(ServiceInfo serviceInfo) {
		boolean isNew = serviceInfo.isNew();

		if (!(serviceInfo instanceof ServiceInfoModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(serviceInfo.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(serviceInfo);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in serviceInfo proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ServiceInfo implementation " +
				serviceInfo.getClass());
		}

		ServiceInfoModelImpl serviceInfoModelImpl = (ServiceInfoModelImpl)serviceInfo;

		if (Validator.isNull(serviceInfo.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			serviceInfo.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (serviceInfo.getCreateDate() == null)) {
			if (serviceContext == null) {
				serviceInfo.setCreateDate(now);
			}
			else {
				serviceInfo.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!serviceInfoModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				serviceInfo.setModifiedDate(now);
			}
			else {
				serviceInfo.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (serviceInfo.isNew()) {
				session.save(serviceInfo);

				serviceInfo.setNew(false);
			}
			else {
				serviceInfo = (ServiceInfo)session.merge(serviceInfo);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ServiceInfoModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { serviceInfoModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					serviceInfoModelImpl.getUuid(),
					serviceInfoModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { serviceInfoModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { serviceInfoModelImpl.getCompanyId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			args = new Object[] {
					serviceInfoModelImpl.getDomainCode(),
					serviceInfoModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_DC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_DC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((serviceInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceInfoModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { serviceInfoModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((serviceInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceInfoModelImpl.getOriginalUuid(),
						serviceInfoModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						serviceInfoModelImpl.getUuid(),
						serviceInfoModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((serviceInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceInfoModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { serviceInfoModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((serviceInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceInfoModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { serviceInfoModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((serviceInfoModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_DC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceInfoModelImpl.getOriginalDomainCode(),
						serviceInfoModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_DC,
					args);

				args = new Object[] {
						serviceInfoModelImpl.getDomainCode(),
						serviceInfoModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GI_DC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GI_DC,
					args);
			}
		}

		entityCache.putResult(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
			ServiceInfoImpl.class, serviceInfo.getPrimaryKey(), serviceInfo,
			false);

		clearUniqueFindersCache(serviceInfoModelImpl, false);
		cacheUniqueFindersCache(serviceInfoModelImpl);

		serviceInfo.resetOriginalValues();

		return serviceInfo;
	}

	/**
	 * Returns the service info with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the service info
	 * @return the service info
	 * @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServiceInfoException {
		ServiceInfo serviceInfo = fetchByPrimaryKey(primaryKey);

		if (serviceInfo == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServiceInfoException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return serviceInfo;
	}

	/**
	 * Returns the service info with the primary key or throws a {@link NoSuchServiceInfoException} if it could not be found.
	 *
	 * @param serviceInfoId the primary key of the service info
	 * @return the service info
	 * @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo findByPrimaryKey(long serviceInfoId)
		throws NoSuchServiceInfoException {
		return findByPrimaryKey((Serializable)serviceInfoId);
	}

	/**
	 * Returns the service info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the service info
	 * @return the service info, or <code>null</code> if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
				ServiceInfoImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ServiceInfo serviceInfo = (ServiceInfo)serializable;

		if (serviceInfo == null) {
			Session session = null;

			try {
				session = openSession();

				serviceInfo = (ServiceInfo)session.get(ServiceInfoImpl.class,
						primaryKey);

				if (serviceInfo != null) {
					cacheResult(serviceInfo);
				}
				else {
					entityCache.putResult(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
						ServiceInfoImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
					ServiceInfoImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return serviceInfo;
	}

	/**
	 * Returns the service info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceInfoId the primary key of the service info
	 * @return the service info, or <code>null</code> if a service info with the primary key could not be found
	 */
	@Override
	public ServiceInfo fetchByPrimaryKey(long serviceInfoId) {
		return fetchByPrimaryKey((Serializable)serviceInfoId);
	}

	@Override
	public Map<Serializable, ServiceInfo> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ServiceInfo> map = new HashMap<Serializable, ServiceInfo>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ServiceInfo serviceInfo = fetchByPrimaryKey(primaryKey);

			if (serviceInfo != null) {
				map.put(primaryKey, serviceInfo);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
					ServiceInfoImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ServiceInfo)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SERVICEINFO_WHERE_PKS_IN);

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

			for (ServiceInfo serviceInfo : (List<ServiceInfo>)q.list()) {
				map.put(serviceInfo.getPrimaryKeyObj(), serviceInfo);

				cacheResult(serviceInfo);

				uncachedPrimaryKeys.remove(serviceInfo.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ServiceInfoModelImpl.ENTITY_CACHE_ENABLED,
					ServiceInfoImpl.class, primaryKey, nullModel);
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
	 * Returns all the service infos.
	 *
	 * @return the service infos
	 */
	@Override
	public List<ServiceInfo> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @return the range of service infos
	 */
	@Override
	public List<ServiceInfo> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the service infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service infos
	 */
	@Override
	public List<ServiceInfo> findAll(int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service infos
	 * @param end the upper bound of the range of service infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of service infos
	 */
	@Override
	public List<ServiceInfo> findAll(int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator,
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

		List<ServiceInfo> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceInfo>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SERVICEINFO);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SERVICEINFO;

				if (pagination) {
					sql = sql.concat(ServiceInfoModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceInfo>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the service infos from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServiceInfo serviceInfo : findAll()) {
			remove(serviceInfo);
		}
	}

	/**
	 * Returns the number of service infos.
	 *
	 * @return the number of service infos
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SERVICEINFO);

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
		return ServiceInfoModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the service info persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ServiceInfoImpl.class.getName());
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
	private static final String _SQL_SELECT_SERVICEINFO = "SELECT serviceInfo FROM ServiceInfo serviceInfo";
	private static final String _SQL_SELECT_SERVICEINFO_WHERE_PKS_IN = "SELECT serviceInfo FROM ServiceInfo serviceInfo WHERE serviceInfoId IN (";
	private static final String _SQL_SELECT_SERVICEINFO_WHERE = "SELECT serviceInfo FROM ServiceInfo serviceInfo WHERE ";
	private static final String _SQL_COUNT_SERVICEINFO = "SELECT COUNT(serviceInfo) FROM ServiceInfo serviceInfo";
	private static final String _SQL_COUNT_SERVICEINFO_WHERE = "SELECT COUNT(serviceInfo) FROM ServiceInfo serviceInfo WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "serviceInfo.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ServiceInfo exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ServiceInfo exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ServiceInfoPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}