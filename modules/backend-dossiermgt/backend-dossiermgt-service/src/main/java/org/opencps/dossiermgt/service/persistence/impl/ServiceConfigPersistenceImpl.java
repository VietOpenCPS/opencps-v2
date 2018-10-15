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

import org.opencps.dossiermgt.exception.NoSuchServiceConfigException;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.impl.ServiceConfigImpl;
import org.opencps.dossiermgt.model.impl.ServiceConfigModelImpl;
import org.opencps.dossiermgt.service.persistence.ServiceConfigPersistence;

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
 * The persistence implementation for the service config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.ServiceConfigUtil
 * @generated
 */
@ProviderType
public class ServiceConfigPersistenceImpl extends BasePersistenceImpl<ServiceConfig>
	implements ServiceConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ServiceConfigUtil} to access the service config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ServiceConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			ServiceConfigModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the service configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching service configs
	 */
	@Override
	public List<ServiceConfig> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @return the range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator,
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

		List<ServiceConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceConfig serviceConfig : list) {
					if (!Objects.equals(uuid, serviceConfig.getUuid())) {
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

			query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

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
				query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first service config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByUuid_First(String uuid,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByUuid_First(uuid, orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the first service config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByUuid_First(String uuid,
		OrderByComparator<ServiceConfig> orderByComparator) {
		List<ServiceConfig> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByUuid_Last(String uuid,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByUuid_Last(uuid, orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the last service config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceConfig> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ServiceConfig> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service configs before and after the current service config in the ordered set where uuid = &#63;.
	 *
	 * @param serviceConfigId the primary key of the current service config
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service config
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig[] findByUuid_PrevAndNext(long serviceConfigId,
		String uuid, OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = findByPrimaryKey(serviceConfigId);

		Session session = null;

		try {
			session = openSession();

			ServiceConfig[] array = new ServiceConfigImpl[3];

			array[0] = getByUuid_PrevAndNext(session, serviceConfig, uuid,
					orderByComparator, true);

			array[1] = serviceConfig;

			array[2] = getByUuid_PrevAndNext(session, serviceConfig, uuid,
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

	protected ServiceConfig getByUuid_PrevAndNext(Session session,
		ServiceConfig serviceConfig, String uuid,
		OrderByComparator<ServiceConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

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
			query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service configs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ServiceConfig serviceConfig : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(serviceConfig);
		}
	}

	/**
	 * Returns the number of service configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching service configs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICECONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "serviceConfig.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "serviceConfig.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(serviceConfig.uuid IS NULL OR serviceConfig.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceConfigModelImpl.UUID_COLUMN_BITMASK |
			ServiceConfigModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the service config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceConfigException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByUUID_G(uuid, groupId);

		if (serviceConfig == null) {
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

			throw new NoSuchServiceConfigException(msg.toString());
		}

		return serviceConfig;
	}

	/**
	 * Returns the service config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the service config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ServiceConfig) {
			ServiceConfig serviceConfig = (ServiceConfig)result;

			if (!Objects.equals(uuid, serviceConfig.getUuid()) ||
					(groupId != serviceConfig.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

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

				List<ServiceConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ServiceConfig serviceConfig = list.get(0);

					result = serviceConfig;

					cacheResult(serviceConfig);
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
			return (ServiceConfig)result;
		}
	}

	/**
	 * Removes the service config where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the service config that was removed
	 */
	@Override
	public ServiceConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = findByUUID_G(uuid, groupId);

		return remove(serviceConfig);
	}

	/**
	 * Returns the number of service configs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching service configs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICECONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "serviceConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "serviceConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(serviceConfig.uuid IS NULL OR serviceConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "serviceConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceConfigModelImpl.UUID_COLUMN_BITMASK |
			ServiceConfigModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the service configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching service configs
	 */
	@Override
	public List<ServiceConfig> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @return the range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ServiceConfig> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service configs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ServiceConfig> orderByComparator,
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

		List<ServiceConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceConfig serviceConfig : list) {
					if (!Objects.equals(uuid, serviceConfig.getUuid()) ||
							(companyId != serviceConfig.getCompanyId())) {
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

			query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

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
				query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first service config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the first service config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator) {
		List<ServiceConfig> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the last service config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ServiceConfig> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service configs before and after the current service config in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param serviceConfigId the primary key of the current service config
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service config
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig[] findByUuid_C_PrevAndNext(long serviceConfigId,
		String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = findByPrimaryKey(serviceConfigId);

		Session session = null;

		try {
			session = openSession();

			ServiceConfig[] array = new ServiceConfigImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, serviceConfig, uuid,
					companyId, orderByComparator, true);

			array[1] = serviceConfig;

			array[2] = getByUuid_C_PrevAndNext(session, serviceConfig, uuid,
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

	protected ServiceConfig getByUuid_C_PrevAndNext(Session session,
		ServiceConfig serviceConfig, String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

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
			query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service configs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ServiceConfig serviceConfig : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceConfig);
		}
	}

	/**
	 * Returns the number of service configs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching service configs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICECONFIG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "serviceConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "serviceConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(serviceConfig.uuid IS NULL OR serviceConfig.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "serviceConfig.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_GID_SI_GAC = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByGID_SI_GAC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			ServiceConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ServiceConfigModelImpl.SERVICEINFOID_COLUMN_BITMASK |
			ServiceConfigModelImpl.GOVAGENCYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_SI_GAC = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_SI_GAC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchServiceConfigException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param govAgencyCode the gov agency code
	 * @return the matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode) throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByGID_SI_GAC(groupId, serviceInfoId,
				govAgencyCode);

		if (serviceConfig == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", serviceInfoId=");
			msg.append(serviceInfoId);

			msg.append(", govAgencyCode=");
			msg.append(govAgencyCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchServiceConfigException(msg.toString());
		}

		return serviceConfig;
	}

	/**
	 * Returns the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param govAgencyCode the gov agency code
	 * @return the matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode) {
		return fetchByGID_SI_GAC(groupId, serviceInfoId, govAgencyCode, true);
	}

	/**
	 * Returns the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param govAgencyCode the gov agency code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, serviceInfoId, govAgencyCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_GID_SI_GAC,
					finderArgs, this);
		}

		if (result instanceof ServiceConfig) {
			ServiceConfig serviceConfig = (ServiceConfig)result;

			if ((groupId != serviceConfig.getGroupId()) ||
					(serviceInfoId != serviceConfig.getServiceInfoId()) ||
					!Objects.equals(govAgencyCode,
						serviceConfig.getGovAgencyCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_GID_SI_GAC_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_SI_GAC_SERVICEINFOID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GID_SI_GAC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_SI_GAC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GID_SI_GAC_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceInfoId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				List<ServiceConfig> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_GID_SI_GAC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ServiceConfigPersistenceImpl.fetchByGID_SI_GAC(long, long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceConfig serviceConfig = list.get(0);

					result = serviceConfig;

					cacheResult(serviceConfig);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_SI_GAC,
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
			return (ServiceConfig)result;
		}
	}

	/**
	 * Removes the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param govAgencyCode the gov agency code
	 * @return the service config that was removed
	 */
	@Override
	public ServiceConfig removeByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode) throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = findByGID_SI_GAC(groupId, serviceInfoId,
				govAgencyCode);

		return remove(serviceConfig);
	}

	/**
	 * Returns the number of service configs where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param govAgencyCode the gov agency code
	 * @return the number of matching service configs
	 */
	@Override
	public int countByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_SI_GAC;

		Object[] finderArgs = new Object[] { groupId, serviceInfoId, govAgencyCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_GID_SI_GAC_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_SI_GAC_SERVICEINFOID_2);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_GID_SI_GAC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_GID_SI_GAC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_GID_SI_GAC_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceInfoId);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
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

	private static final String _FINDER_COLUMN_GID_SI_GAC_GROUPID_2 = "serviceConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_SI_GAC_SERVICEINFOID_2 = "serviceConfig.serviceInfoId = ? AND ";
	private static final String _FINDER_COLUMN_GID_SI_GAC_GOVAGENCYCODE_1 = "serviceConfig.govAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_GID_SI_GAC_GOVAGENCYCODE_2 = "serviceConfig.govAgencyCode = ?";
	private static final String _FINDER_COLUMN_GID_SI_GAC_GOVAGENCYCODE_3 = "(serviceConfig.govAgencyCode IS NULL OR serviceConfig.govAgencyCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_ = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_ = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByG_", new String[] { Long.class.getName() },
			ServiceConfigModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_ = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the service configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching service configs
	 */
	@Override
	public List<ServiceConfig> findByG_(long groupId) {
		return findByG_(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @return the range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByG_(long groupId, int start, int end) {
		return findByG_(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByG_(long groupId, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return findByG_(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service configs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByG_(long groupId, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<ServiceConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceConfig serviceConfig : list) {
					if ((groupId != serviceConfig.getGroupId())) {
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

			query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_G__GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first service config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByG__First(long groupId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByG__First(groupId, orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the first service config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByG__First(long groupId,
		OrderByComparator<ServiceConfig> orderByComparator) {
		List<ServiceConfig> list = findByG_(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByG__Last(long groupId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByG__Last(groupId, orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the last service config in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByG__Last(long groupId,
		OrderByComparator<ServiceConfig> orderByComparator) {
		int count = countByG_(groupId);

		if (count == 0) {
			return null;
		}

		List<ServiceConfig> list = findByG_(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service configs before and after the current service config in the ordered set where groupId = &#63;.
	 *
	 * @param serviceConfigId the primary key of the current service config
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service config
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig[] findByG__PrevAndNext(long serviceConfigId,
		long groupId, OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = findByPrimaryKey(serviceConfigId);

		Session session = null;

		try {
			session = openSession();

			ServiceConfig[] array = new ServiceConfigImpl[3];

			array[0] = getByG__PrevAndNext(session, serviceConfig, groupId,
					orderByComparator, true);

			array[1] = serviceConfig;

			array[2] = getByG__PrevAndNext(session, serviceConfig, groupId,
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

	protected ServiceConfig getByG__PrevAndNext(Session session,
		ServiceConfig serviceConfig, long groupId,
		OrderByComparator<ServiceConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

		query.append(_FINDER_COLUMN_G__GROUPID_2);

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
			query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service configs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByG_(long groupId) {
		for (ServiceConfig serviceConfig : findByG_(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(serviceConfig);
		}
	}

	/**
	 * Returns the number of service configs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching service configs
	 */
	@Override
	public int countByG_(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_G__GROUPID_2);

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

	private static final String _FINDER_COLUMN_G__GROUPID_2 = "serviceConfig.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SID =
		new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GID_SID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID =
		new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_GID_SID",
			new String[] { Long.class.getName(), Long.class.getName() },
			ServiceConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ServiceConfigModelImpl.SERVICEINFOID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SID = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GID_SID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the service configs where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @return the matching service configs
	 */
	@Override
	public List<ServiceConfig> findByF_GID_SID(long groupId, long serviceInfoId) {
		return findByF_GID_SID(groupId, serviceInfoId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service configs where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @return the range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId, int start, int end) {
		return findByF_GID_SID(groupId, serviceInfoId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service configs where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return findByF_GID_SID(groupId, serviceInfoId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service configs where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID;
			finderArgs = new Object[] { groupId, serviceInfoId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SID;
			finderArgs = new Object[] {
					groupId, serviceInfoId,
					
					start, end, orderByComparator
				};
		}

		List<ServiceConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceConfig serviceConfig : list) {
					if ((groupId != serviceConfig.getGroupId()) ||
							(serviceInfoId != serviceConfig.getServiceInfoId())) {
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

			query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SID_SERVICEINFOID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceInfoId);

				if (!pagination) {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByF_GID_SID_First(long groupId,
		long serviceInfoId, OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByF_GID_SID_First(groupId,
				serviceInfoId, orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceInfoId=");
		msg.append(serviceInfoId);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the first service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByF_GID_SID_First(long groupId,
		long serviceInfoId, OrderByComparator<ServiceConfig> orderByComparator) {
		List<ServiceConfig> list = findByF_GID_SID(groupId, serviceInfoId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByF_GID_SID_Last(long groupId, long serviceInfoId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByF_GID_SID_Last(groupId,
				serviceInfoId, orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceInfoId=");
		msg.append(serviceInfoId);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the last service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByF_GID_SID_Last(long groupId,
		long serviceInfoId, OrderByComparator<ServiceConfig> orderByComparator) {
		int count = countByF_GID_SID(groupId, serviceInfoId);

		if (count == 0) {
			return null;
		}

		List<ServiceConfig> list = findByF_GID_SID(groupId, serviceInfoId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service configs before and after the current service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * @param serviceConfigId the primary key of the current service config
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service config
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig[] findByF_GID_SID_PrevAndNext(long serviceConfigId,
		long groupId, long serviceInfoId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = findByPrimaryKey(serviceConfigId);

		Session session = null;

		try {
			session = openSession();

			ServiceConfig[] array = new ServiceConfigImpl[3];

			array[0] = getByF_GID_SID_PrevAndNext(session, serviceConfig,
					groupId, serviceInfoId, orderByComparator, true);

			array[1] = serviceConfig;

			array[2] = getByF_GID_SID_PrevAndNext(session, serviceConfig,
					groupId, serviceInfoId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServiceConfig getByF_GID_SID_PrevAndNext(Session session,
		ServiceConfig serviceConfig, long groupId, long serviceInfoId,
		OrderByComparator<ServiceConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

		query.append(_FINDER_COLUMN_F_GID_SID_GROUPID_2);

		query.append(_FINDER_COLUMN_F_GID_SID_SERVICEINFOID_2);

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
			query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(serviceInfoId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service configs where groupId = &#63; and serviceInfoId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 */
	@Override
	public void removeByF_GID_SID(long groupId, long serviceInfoId) {
		for (ServiceConfig serviceConfig : findByF_GID_SID(groupId,
				serviceInfoId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceConfig);
		}
	}

	/**
	 * Returns the number of service configs where groupId = &#63; and serviceInfoId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceInfoId the service info ID
	 * @return the number of matching service configs
	 */
	@Override
	public int countByF_GID_SID(long groupId, long serviceInfoId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SID;

		Object[] finderArgs = new Object[] { groupId, serviceInfoId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SID_SERVICEINFOID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceInfoId);

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

	private static final String _FINDER_COLUMN_F_GID_SID_GROUPID_2 = "serviceConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SID_SERVICEINFOID_2 = "serviceConfig.serviceInfoId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GAC = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_GAC",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GAC = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_GAC", new String[] { String.class.getName() },
			ServiceConfigModelImpl.GOVAGENCYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GAC = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_GAC",
			new String[] { String.class.getName() });

	/**
	 * Returns all the service configs where govAgencyCode = &#63;.
	 *
	 * @param govAgencyCode the gov agency code
	 * @return the matching service configs
	 */
	@Override
	public List<ServiceConfig> findByF_GAC(String govAgencyCode) {
		return findByF_GAC(govAgencyCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the service configs where govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @return the range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByF_GAC(String govAgencyCode, int start,
		int end) {
		return findByF_GAC(govAgencyCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service configs where govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByF_GAC(String govAgencyCode, int start,
		int end, OrderByComparator<ServiceConfig> orderByComparator) {
		return findByF_GAC(govAgencyCode, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service configs where govAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param govAgencyCode the gov agency code
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByF_GAC(String govAgencyCode, int start,
		int end, OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GAC;
			finderArgs = new Object[] { govAgencyCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GAC;
			finderArgs = new Object[] {
					govAgencyCode,
					
					start, end, orderByComparator
				};
		}

		List<ServiceConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceConfig serviceConfig : list) {
					if (!Objects.equals(govAgencyCode,
								serviceConfig.getGovAgencyCode())) {
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

			query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_GAC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GAC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_F_GAC_GOVAGENCYCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
				}

				if (!pagination) {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first service config in the ordered set where govAgencyCode = &#63;.
	 *
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByF_GAC_First(String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByF_GAC_First(govAgencyCode,
				orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the first service config in the ordered set where govAgencyCode = &#63;.
	 *
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByF_GAC_First(String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator) {
		List<ServiceConfig> list = findByF_GAC(govAgencyCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service config in the ordered set where govAgencyCode = &#63;.
	 *
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByF_GAC_Last(String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByF_GAC_Last(govAgencyCode,
				orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("govAgencyCode=");
		msg.append(govAgencyCode);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the last service config in the ordered set where govAgencyCode = &#63;.
	 *
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByF_GAC_Last(String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator) {
		int count = countByF_GAC(govAgencyCode);

		if (count == 0) {
			return null;
		}

		List<ServiceConfig> list = findByF_GAC(govAgencyCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service configs before and after the current service config in the ordered set where govAgencyCode = &#63;.
	 *
	 * @param serviceConfigId the primary key of the current service config
	 * @param govAgencyCode the gov agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service config
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig[] findByF_GAC_PrevAndNext(long serviceConfigId,
		String govAgencyCode, OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = findByPrimaryKey(serviceConfigId);

		Session session = null;

		try {
			session = openSession();

			ServiceConfig[] array = new ServiceConfigImpl[3];

			array[0] = getByF_GAC_PrevAndNext(session, serviceConfig,
					govAgencyCode, orderByComparator, true);

			array[1] = serviceConfig;

			array[2] = getByF_GAC_PrevAndNext(session, serviceConfig,
					govAgencyCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServiceConfig getByF_GAC_PrevAndNext(Session session,
		ServiceConfig serviceConfig, String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

		boolean bindGovAgencyCode = false;

		if (govAgencyCode == null) {
			query.append(_FINDER_COLUMN_F_GAC_GOVAGENCYCODE_1);
		}
		else if (govAgencyCode.equals("")) {
			query.append(_FINDER_COLUMN_F_GAC_GOVAGENCYCODE_3);
		}
		else {
			bindGovAgencyCode = true;

			query.append(_FINDER_COLUMN_F_GAC_GOVAGENCYCODE_2);
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
			query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindGovAgencyCode) {
			qPos.add(govAgencyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service configs where govAgencyCode = &#63; from the database.
	 *
	 * @param govAgencyCode the gov agency code
	 */
	@Override
	public void removeByF_GAC(String govAgencyCode) {
		for (ServiceConfig serviceConfig : findByF_GAC(govAgencyCode,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceConfig);
		}
	}

	/**
	 * Returns the number of service configs where govAgencyCode = &#63;.
	 *
	 * @param govAgencyCode the gov agency code
	 * @return the number of matching service configs
	 */
	@Override
	public int countByF_GAC(String govAgencyCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GAC;

		Object[] finderArgs = new Object[] { govAgencyCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICECONFIG_WHERE);

			boolean bindGovAgencyCode = false;

			if (govAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_GAC_GOVAGENCYCODE_1);
			}
			else if (govAgencyCode.equals("")) {
				query.append(_FINDER_COLUMN_F_GAC_GOVAGENCYCODE_3);
			}
			else {
				bindGovAgencyCode = true;

				query.append(_FINDER_COLUMN_F_GAC_GOVAGENCYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindGovAgencyCode) {
					qPos.add(govAgencyCode);
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

	private static final String _FINDER_COLUMN_F_GAC_GOVAGENCYCODE_1 = "serviceConfig.govAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_F_GAC_GOVAGENCYCODE_2 = "serviceConfig.govAgencyCode = ?";
	private static final String _FINDER_COLUMN_F_GAC_GOVAGENCYCODE_3 = "(serviceConfig.govAgencyCode IS NULL OR serviceConfig.govAgencyCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_LEVEL =
		new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGID_LEVEL",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_LEVEL =
		new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGID_LEVEL",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ServiceConfigModelImpl.GROUPID_COLUMN_BITMASK |
			ServiceConfigModelImpl.SERVICELEVEL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GID_LEVEL = new FinderPath(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGID_LEVEL",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the service configs where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @return the matching service configs
	 */
	@Override
	public List<ServiceConfig> findByGID_LEVEL(long groupId, int serviceLevel) {
		return findByGID_LEVEL(groupId, serviceLevel, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service configs where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @return the range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByGID_LEVEL(long groupId, int serviceLevel,
		int start, int end) {
		return findByGID_LEVEL(groupId, serviceLevel, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service configs where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByGID_LEVEL(long groupId, int serviceLevel,
		int start, int end, OrderByComparator<ServiceConfig> orderByComparator) {
		return findByGID_LEVEL(groupId, serviceLevel, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service configs where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service configs
	 */
	@Override
	public List<ServiceConfig> findByGID_LEVEL(long groupId, int serviceLevel,
		int start, int end, OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_LEVEL;
			finderArgs = new Object[] { groupId, serviceLevel };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GID_LEVEL;
			finderArgs = new Object[] {
					groupId, serviceLevel,
					
					start, end, orderByComparator
				};
		}

		List<ServiceConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfig>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceConfig serviceConfig : list) {
					if ((groupId != serviceConfig.getGroupId()) ||
							(serviceLevel != serviceConfig.getServiceLevel())) {
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

			query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_GID_LEVEL_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_LEVEL_SERVICELEVEL_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceLevel);

				if (!pagination) {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByGID_LEVEL_First(long groupId, int serviceLevel,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByGID_LEVEL_First(groupId,
				serviceLevel, orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceLevel=");
		msg.append(serviceLevel);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the first service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByGID_LEVEL_First(long groupId, int serviceLevel,
		OrderByComparator<ServiceConfig> orderByComparator) {
		List<ServiceConfig> list = findByGID_LEVEL(groupId, serviceLevel, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config
	 * @throws NoSuchServiceConfigException if a matching service config could not be found
	 */
	@Override
	public ServiceConfig findByGID_LEVEL_Last(long groupId, int serviceLevel,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByGID_LEVEL_Last(groupId,
				serviceLevel, orderByComparator);

		if (serviceConfig != null) {
			return serviceConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", serviceLevel=");
		msg.append(serviceLevel);

		msg.append("}");

		throw new NoSuchServiceConfigException(msg.toString());
	}

	/**
	 * Returns the last service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config, or <code>null</code> if a matching service config could not be found
	 */
	@Override
	public ServiceConfig fetchByGID_LEVEL_Last(long groupId, int serviceLevel,
		OrderByComparator<ServiceConfig> orderByComparator) {
		int count = countByGID_LEVEL(groupId, serviceLevel);

		if (count == 0) {
			return null;
		}

		List<ServiceConfig> list = findByGID_LEVEL(groupId, serviceLevel,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service configs before and after the current service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * @param serviceConfigId the primary key of the current service config
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service config
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig[] findByGID_LEVEL_PrevAndNext(long serviceConfigId,
		long groupId, int serviceLevel,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = findByPrimaryKey(serviceConfigId);

		Session session = null;

		try {
			session = openSession();

			ServiceConfig[] array = new ServiceConfigImpl[3];

			array[0] = getByGID_LEVEL_PrevAndNext(session, serviceConfig,
					groupId, serviceLevel, orderByComparator, true);

			array[1] = serviceConfig;

			array[2] = getByGID_LEVEL_PrevAndNext(session, serviceConfig,
					groupId, serviceLevel, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServiceConfig getByGID_LEVEL_PrevAndNext(Session session,
		ServiceConfig serviceConfig, long groupId, int serviceLevel,
		OrderByComparator<ServiceConfig> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SERVICECONFIG_WHERE);

		query.append(_FINDER_COLUMN_GID_LEVEL_GROUPID_2);

		query.append(_FINDER_COLUMN_GID_LEVEL_SERVICELEVEL_2);

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
			query.append(ServiceConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(serviceLevel);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service configs where groupId = &#63; and serviceLevel = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 */
	@Override
	public void removeByGID_LEVEL(long groupId, int serviceLevel) {
		for (ServiceConfig serviceConfig : findByGID_LEVEL(groupId,
				serviceLevel, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceConfig);
		}
	}

	/**
	 * Returns the number of service configs where groupId = &#63; and serviceLevel = &#63;.
	 *
	 * @param groupId the group ID
	 * @param serviceLevel the service level
	 * @return the number of matching service configs
	 */
	@Override
	public int countByGID_LEVEL(long groupId, int serviceLevel) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GID_LEVEL;

		Object[] finderArgs = new Object[] { groupId, serviceLevel };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICECONFIG_WHERE);

			query.append(_FINDER_COLUMN_GID_LEVEL_GROUPID_2);

			query.append(_FINDER_COLUMN_GID_LEVEL_SERVICELEVEL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(serviceLevel);

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

	private static final String _FINDER_COLUMN_GID_LEVEL_GROUPID_2 = "serviceConfig.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GID_LEVEL_SERVICELEVEL_2 = "serviceConfig.serviceLevel = ?";

	public ServiceConfigPersistenceImpl() {
		setModelClass(ServiceConfig.class);

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
	 * Caches the service config in the entity cache if it is enabled.
	 *
	 * @param serviceConfig the service config
	 */
	@Override
	public void cacheResult(ServiceConfig serviceConfig) {
		entityCache.putResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigImpl.class, serviceConfig.getPrimaryKey(),
			serviceConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { serviceConfig.getUuid(), serviceConfig.getGroupId() },
			serviceConfig);

		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_SI_GAC,
			new Object[] {
				serviceConfig.getGroupId(), serviceConfig.getServiceInfoId(),
				serviceConfig.getGovAgencyCode()
			}, serviceConfig);

		serviceConfig.resetOriginalValues();
	}

	/**
	 * Caches the service configs in the entity cache if it is enabled.
	 *
	 * @param serviceConfigs the service configs
	 */
	@Override
	public void cacheResult(List<ServiceConfig> serviceConfigs) {
		for (ServiceConfig serviceConfig : serviceConfigs) {
			if (entityCache.getResult(
						ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
						ServiceConfigImpl.class, serviceConfig.getPrimaryKey()) == null) {
				cacheResult(serviceConfig);
			}
			else {
				serviceConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all service configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServiceConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the service config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServiceConfig serviceConfig) {
		entityCache.removeResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigImpl.class, serviceConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ServiceConfigModelImpl)serviceConfig, true);
	}

	@Override
	public void clearCache(List<ServiceConfig> serviceConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ServiceConfig serviceConfig : serviceConfigs) {
			entityCache.removeResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
				ServiceConfigImpl.class, serviceConfig.getPrimaryKey());

			clearUniqueFindersCache((ServiceConfigModelImpl)serviceConfig, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ServiceConfigModelImpl serviceConfigModelImpl) {
		Object[] args = new Object[] {
				serviceConfigModelImpl.getUuid(),
				serviceConfigModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			serviceConfigModelImpl, false);

		args = new Object[] {
				serviceConfigModelImpl.getGroupId(),
				serviceConfigModelImpl.getServiceInfoId(),
				serviceConfigModelImpl.getGovAgencyCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_GID_SI_GAC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_GID_SI_GAC, args,
			serviceConfigModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ServiceConfigModelImpl serviceConfigModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceConfigModelImpl.getUuid(),
					serviceConfigModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((serviceConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceConfigModelImpl.getOriginalUuid(),
					serviceConfigModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceConfigModelImpl.getGroupId(),
					serviceConfigModelImpl.getServiceInfoId(),
					serviceConfigModelImpl.getGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_SI_GAC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_SI_GAC, args);
		}

		if ((serviceConfigModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_GID_SI_GAC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceConfigModelImpl.getOriginalGroupId(),
					serviceConfigModelImpl.getOriginalServiceInfoId(),
					serviceConfigModelImpl.getOriginalGovAgencyCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_SI_GAC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_GID_SI_GAC, args);
		}
	}

	/**
	 * Creates a new service config with the primary key. Does not add the service config to the database.
	 *
	 * @param serviceConfigId the primary key for the new service config
	 * @return the new service config
	 */
	@Override
	public ServiceConfig create(long serviceConfigId) {
		ServiceConfig serviceConfig = new ServiceConfigImpl();

		serviceConfig.setNew(true);
		serviceConfig.setPrimaryKey(serviceConfigId);

		String uuid = PortalUUIDUtil.generate();

		serviceConfig.setUuid(uuid);

		serviceConfig.setCompanyId(companyProvider.getCompanyId());

		return serviceConfig;
	}

	/**
	 * Removes the service config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceConfigId the primary key of the service config
	 * @return the service config that was removed
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig remove(long serviceConfigId)
		throws NoSuchServiceConfigException {
		return remove((Serializable)serviceConfigId);
	}

	/**
	 * Removes the service config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the service config
	 * @return the service config that was removed
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig remove(Serializable primaryKey)
		throws NoSuchServiceConfigException {
		Session session = null;

		try {
			session = openSession();

			ServiceConfig serviceConfig = (ServiceConfig)session.get(ServiceConfigImpl.class,
					primaryKey);

			if (serviceConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServiceConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(serviceConfig);
		}
		catch (NoSuchServiceConfigException nsee) {
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
	protected ServiceConfig removeImpl(ServiceConfig serviceConfig) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serviceConfig)) {
				serviceConfig = (ServiceConfig)session.get(ServiceConfigImpl.class,
						serviceConfig.getPrimaryKeyObj());
			}

			if (serviceConfig != null) {
				session.delete(serviceConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (serviceConfig != null) {
			clearCache(serviceConfig);
		}

		return serviceConfig;
	}

	@Override
	public ServiceConfig updateImpl(ServiceConfig serviceConfig) {
		boolean isNew = serviceConfig.isNew();

		if (!(serviceConfig instanceof ServiceConfigModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(serviceConfig.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(serviceConfig);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in serviceConfig proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ServiceConfig implementation " +
				serviceConfig.getClass());
		}

		ServiceConfigModelImpl serviceConfigModelImpl = (ServiceConfigModelImpl)serviceConfig;

		if (Validator.isNull(serviceConfig.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			serviceConfig.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (serviceConfig.getCreateDate() == null)) {
			if (serviceContext == null) {
				serviceConfig.setCreateDate(now);
			}
			else {
				serviceConfig.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!serviceConfigModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				serviceConfig.setModifiedDate(now);
			}
			else {
				serviceConfig.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (serviceConfig.isNew()) {
				session.save(serviceConfig);

				serviceConfig.setNew(false);
			}
			else {
				serviceConfig = (ServiceConfig)session.merge(serviceConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ServiceConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { serviceConfigModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					serviceConfigModelImpl.getUuid(),
					serviceConfigModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { serviceConfigModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_,
				args);

			args = new Object[] {
					serviceConfigModelImpl.getGroupId(),
					serviceConfigModelImpl.getServiceInfoId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID,
				args);

			args = new Object[] { serviceConfigModelImpl.getGovAgencyCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GAC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GAC,
				args);

			args = new Object[] {
					serviceConfigModelImpl.getGroupId(),
					serviceConfigModelImpl.getServiceLevel()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_LEVEL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_LEVEL,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((serviceConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceConfigModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { serviceConfigModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((serviceConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceConfigModelImpl.getOriginalUuid(),
						serviceConfigModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						serviceConfigModelImpl.getUuid(),
						serviceConfigModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((serviceConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceConfigModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_,
					args);

				args = new Object[] { serviceConfigModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_,
					args);
			}

			if ((serviceConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceConfigModelImpl.getOriginalGroupId(),
						serviceConfigModelImpl.getOriginalServiceInfoId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID,
					args);

				args = new Object[] {
						serviceConfigModelImpl.getGroupId(),
						serviceConfigModelImpl.getServiceInfoId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SID,
					args);
			}

			if ((serviceConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GAC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceConfigModelImpl.getOriginalGovAgencyCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GAC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GAC,
					args);

				args = new Object[] { serviceConfigModelImpl.getGovAgencyCode() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GAC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GAC,
					args);
			}

			if ((serviceConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_LEVEL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceConfigModelImpl.getOriginalGroupId(),
						serviceConfigModelImpl.getOriginalServiceLevel()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_LEVEL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_LEVEL,
					args);

				args = new Object[] {
						serviceConfigModelImpl.getGroupId(),
						serviceConfigModelImpl.getServiceLevel()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GID_LEVEL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GID_LEVEL,
					args);
			}
		}

		entityCache.putResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigImpl.class, serviceConfig.getPrimaryKey(),
			serviceConfig, false);

		clearUniqueFindersCache(serviceConfigModelImpl, false);
		cacheUniqueFindersCache(serviceConfigModelImpl);

		serviceConfig.resetOriginalValues();

		return serviceConfig;
	}

	/**
	 * Returns the service config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the service config
	 * @return the service config
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServiceConfigException {
		ServiceConfig serviceConfig = fetchByPrimaryKey(primaryKey);

		if (serviceConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServiceConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return serviceConfig;
	}

	/**
	 * Returns the service config with the primary key or throws a {@link NoSuchServiceConfigException} if it could not be found.
	 *
	 * @param serviceConfigId the primary key of the service config
	 * @return the service config
	 * @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig findByPrimaryKey(long serviceConfigId)
		throws NoSuchServiceConfigException {
		return findByPrimaryKey((Serializable)serviceConfigId);
	}

	/**
	 * Returns the service config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the service config
	 * @return the service config, or <code>null</code> if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
				ServiceConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ServiceConfig serviceConfig = (ServiceConfig)serializable;

		if (serviceConfig == null) {
			Session session = null;

			try {
				session = openSession();

				serviceConfig = (ServiceConfig)session.get(ServiceConfigImpl.class,
						primaryKey);

				if (serviceConfig != null) {
					cacheResult(serviceConfig);
				}
				else {
					entityCache.putResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
						ServiceConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
					ServiceConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return serviceConfig;
	}

	/**
	 * Returns the service config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceConfigId the primary key of the service config
	 * @return the service config, or <code>null</code> if a service config with the primary key could not be found
	 */
	@Override
	public ServiceConfig fetchByPrimaryKey(long serviceConfigId) {
		return fetchByPrimaryKey((Serializable)serviceConfigId);
	}

	@Override
	public Map<Serializable, ServiceConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ServiceConfig> map = new HashMap<Serializable, ServiceConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ServiceConfig serviceConfig = fetchByPrimaryKey(primaryKey);

			if (serviceConfig != null) {
				map.put(primaryKey, serviceConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
					ServiceConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ServiceConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SERVICECONFIG_WHERE_PKS_IN);

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

			for (ServiceConfig serviceConfig : (List<ServiceConfig>)q.list()) {
				map.put(serviceConfig.getPrimaryKeyObj(), serviceConfig);

				cacheResult(serviceConfig);

				uncachedPrimaryKeys.remove(serviceConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
					ServiceConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the service configs.
	 *
	 * @return the service configs
	 */
	@Override
	public List<ServiceConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @return the range of service configs
	 */
	@Override
	public List<ServiceConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the service configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service configs
	 */
	@Override
	public List<ServiceConfig> findAll(int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service configs
	 * @param end the upper bound of the range of service configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of service configs
	 */
	@Override
	public List<ServiceConfig> findAll(int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator,
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

		List<ServiceConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SERVICECONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SERVICECONFIG;

				if (pagination) {
					sql = sql.concat(ServiceConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfig>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the service configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServiceConfig serviceConfig : findAll()) {
			remove(serviceConfig);
		}
	}

	/**
	 * Returns the number of service configs.
	 *
	 * @return the number of service configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SERVICECONFIG);

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
		return ServiceConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the service config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ServiceConfigImpl.class.getName());
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
	private static final String _SQL_SELECT_SERVICECONFIG = "SELECT serviceConfig FROM ServiceConfig serviceConfig";
	private static final String _SQL_SELECT_SERVICECONFIG_WHERE_PKS_IN = "SELECT serviceConfig FROM ServiceConfig serviceConfig WHERE serviceConfigId IN (";
	private static final String _SQL_SELECT_SERVICECONFIG_WHERE = "SELECT serviceConfig FROM ServiceConfig serviceConfig WHERE ";
	private static final String _SQL_COUNT_SERVICECONFIG = "SELECT COUNT(serviceConfig) FROM ServiceConfig serviceConfig";
	private static final String _SQL_COUNT_SERVICECONFIG_WHERE = "SELECT COUNT(serviceConfig) FROM ServiceConfig serviceConfig WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "serviceConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ServiceConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ServiceConfig exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ServiceConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}