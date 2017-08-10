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

package org.opencps.backend.dossiermgt.service.persistence.impl;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.backend.dossiermgt.exception.NoSuchServiceConfigException;
import org.opencps.backend.dossiermgt.model.ServiceConfig;
import org.opencps.backend.dossiermgt.model.impl.ServiceConfigImpl;
import org.opencps.backend.dossiermgt.model.impl.ServiceConfigModelImpl;
import org.opencps.backend.dossiermgt.service.persistence.ServiceConfigPersistence;

import java.io.Serializable;

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
 * @see org.opencps.backend.dossiermgt.service.persistence.ServiceConfigUtil
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
			else if (uuid.equals(StringPool.BLANK)) {
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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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
		else if (uuid.equals(StringPool.BLANK)) {
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
			else if (uuid.equals(StringPool.BLANK)) {
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

			msg.append(StringPool.CLOSE_CURLY_BRACE);

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
			else if (uuid.equals(StringPool.BLANK)) {
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

					if ((serviceConfig.getUuid() == null) ||
							!serviceConfig.getUuid().equals(uuid) ||
							(serviceConfig.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, serviceConfig);
					}
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
			else if (uuid.equals(StringPool.BLANK)) {
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
			else if (uuid.equals(StringPool.BLANK)) {
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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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

		msg.append(StringPool.CLOSE_CURLY_BRACE);

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
		else if (uuid.equals(StringPool.BLANK)) {
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
			else if (uuid.equals(StringPool.BLANK)) {
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

	public ServiceConfigPersistenceImpl() {
		setModelClass(ServiceConfig.class);
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
		serviceConfig = toUnwrappedModel(serviceConfig);

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
		serviceConfig = toUnwrappedModel(serviceConfig);

		boolean isNew = serviceConfig.isNew();

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

		if (isNew || !ServiceConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
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
		}

		entityCache.putResult(ServiceConfigModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigImpl.class, serviceConfig.getPrimaryKey(),
			serviceConfig, false);

		clearUniqueFindersCache(serviceConfigModelImpl, false);
		cacheUniqueFindersCache(serviceConfigModelImpl);

		serviceConfig.resetOriginalValues();

		return serviceConfig;
	}

	protected ServiceConfig toUnwrappedModel(ServiceConfig serviceConfig) {
		if (serviceConfig instanceof ServiceConfigImpl) {
			return serviceConfig;
		}

		ServiceConfigImpl serviceConfigImpl = new ServiceConfigImpl();

		serviceConfigImpl.setNew(serviceConfig.isNew());
		serviceConfigImpl.setPrimaryKey(serviceConfig.getPrimaryKey());

		serviceConfigImpl.setUuid(serviceConfig.getUuid());
		serviceConfigImpl.setServiceConfigId(serviceConfig.getServiceConfigId());
		serviceConfigImpl.setGroupId(serviceConfig.getGroupId());
		serviceConfigImpl.setCompanyId(serviceConfig.getCompanyId());
		serviceConfigImpl.setUserId(serviceConfig.getUserId());
		serviceConfigImpl.setUserName(serviceConfig.getUserName());
		serviceConfigImpl.setCreateDate(serviceConfig.getCreateDate());
		serviceConfigImpl.setModifiedDate(serviceConfig.getModifiedDate());
		serviceConfigImpl.setServiceInfoId(serviceConfig.getServiceInfoId());
		serviceConfigImpl.setGovAgencyCode(serviceConfig.getGovAgencyCode());
		serviceConfigImpl.setGovAgencyName(serviceConfig.getGovAgencyName());
		serviceConfigImpl.setServiceInstruction(serviceConfig.getServiceInstruction());
		serviceConfigImpl.setServiceLevel(serviceConfig.getServiceLevel());
		serviceConfigImpl.setServiceUrl(serviceConfig.getServiceUrl());
		serviceConfigImpl.setAuthentication(serviceConfig.getAuthentication());

		return serviceConfigImpl;
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
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

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