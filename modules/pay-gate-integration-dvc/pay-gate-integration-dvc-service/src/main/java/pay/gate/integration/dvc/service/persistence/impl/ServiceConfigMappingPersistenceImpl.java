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

package pay.gate.integration.dvc.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException;
import pay.gate.integration.dvc.model.ServiceConfigMapping;
import pay.gate.integration.dvc.model.impl.ServiceConfigMappingImpl;
import pay.gate.integration.dvc.model.impl.ServiceConfigMappingModelImpl;
import pay.gate.integration.dvc.service.persistence.ServiceConfigMappingPersistence;

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
 * The persistence implementation for the service config mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceConfigMappingPersistence
 * @see pay.gate.integration.dvc.service.persistence.ServiceConfigMappingUtil
 * @generated
 */
@ProviderType
public class ServiceConfigMappingPersistenceImpl extends BasePersistenceImpl<ServiceConfigMapping>
	implements ServiceConfigMappingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ServiceConfigMappingUtil} to access the service config mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ServiceConfigMappingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ServiceConfigMappingModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the service config mappings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service config mappings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @return the range of matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service config mappings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service config mappings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceConfigMapping> orderByComparator,
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

		List<ServiceConfigMapping> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfigMapping>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceConfigMapping serviceConfigMapping : list) {
					if (!Objects.equals(uuid, serviceConfigMapping.getUuid())) {
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

			query.append(_SQL_SELECT_SERVICECONFIGMAPPING_WHERE);

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
				query.append(ServiceConfigMappingModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceConfigMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfigMapping>)QueryUtil.list(q,
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
	 * Returns the first service config mapping in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config mapping
	 * @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping findByUuid_First(String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = fetchByUuid_First(uuid,
				orderByComparator);

		if (serviceConfigMapping != null) {
			return serviceConfigMapping;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceConfigMappingException(msg.toString());
	}

	/**
	 * Returns the first service config mapping in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByUuid_First(String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		List<ServiceConfigMapping> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service config mapping in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config mapping
	 * @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping findByUuid_Last(String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = fetchByUuid_Last(uuid,
				orderByComparator);

		if (serviceConfigMapping != null) {
			return serviceConfigMapping;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchServiceConfigMappingException(msg.toString());
	}

	/**
	 * Returns the last service config mapping in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ServiceConfigMapping> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service config mappings before and after the current service config mapping in the ordered set where uuid = &#63;.
	 *
	 * @param serviceConfigMappingId the primary key of the current service config mapping
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service config mapping
	 * @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	 */
	@Override
	public ServiceConfigMapping[] findByUuid_PrevAndNext(
		long serviceConfigMappingId, String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = findByPrimaryKey(serviceConfigMappingId);

		Session session = null;

		try {
			session = openSession();

			ServiceConfigMapping[] array = new ServiceConfigMappingImpl[3];

			array[0] = getByUuid_PrevAndNext(session, serviceConfigMapping,
					uuid, orderByComparator, true);

			array[1] = serviceConfigMapping;

			array[2] = getByUuid_PrevAndNext(session, serviceConfigMapping,
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

	protected ServiceConfigMapping getByUuid_PrevAndNext(Session session,
		ServiceConfigMapping serviceConfigMapping, String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator,
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

		query.append(_SQL_SELECT_SERVICECONFIGMAPPING_WHERE);

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
			query.append(ServiceConfigMappingModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceConfigMapping);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceConfigMapping> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service config mappings where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ServiceConfigMapping serviceConfigMapping : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceConfigMapping);
		}
	}

	/**
	 * Returns the number of service config mappings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching service config mappings
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SERVICECONFIGMAPPING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "serviceConfigMapping.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "serviceConfigMapping.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(serviceConfigMapping.uuid IS NULL OR serviceConfigMapping.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigMappingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceConfigMappingModelImpl.UUID_COLUMN_BITMASK |
			ServiceConfigMappingModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the service config mapping where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceConfigMappingException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching service config mapping
	 * @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping findByUUID_G(String uuid, long groupId)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = fetchByUUID_G(uuid, groupId);

		if (serviceConfigMapping == null) {
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

			throw new NoSuchServiceConfigMappingException(msg.toString());
		}

		return serviceConfigMapping;
	}

	/**
	 * Returns the service config mapping where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the service config mapping where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ServiceConfigMapping) {
			ServiceConfigMapping serviceConfigMapping = (ServiceConfigMapping)result;

			if (!Objects.equals(uuid, serviceConfigMapping.getUuid()) ||
					(groupId != serviceConfigMapping.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SERVICECONFIGMAPPING_WHERE);

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

				List<ServiceConfigMapping> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ServiceConfigMapping serviceConfigMapping = list.get(0);

					result = serviceConfigMapping;

					cacheResult(serviceConfigMapping);
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
			return (ServiceConfigMapping)result;
		}
	}

	/**
	 * Removes the service config mapping where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the service config mapping that was removed
	 */
	@Override
	public ServiceConfigMapping removeByUUID_G(String uuid, long groupId)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = findByUUID_G(uuid, groupId);

		return remove(serviceConfigMapping);
	}

	/**
	 * Returns the number of service config mappings where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching service config mappings
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICECONFIGMAPPING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "serviceConfigMapping.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "serviceConfigMapping.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(serviceConfigMapping.uuid IS NULL OR serviceConfigMapping.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "serviceConfigMapping.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ServiceConfigMappingModelImpl.UUID_COLUMN_BITMASK |
			ServiceConfigMappingModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the service config mappings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service config mappings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @return the range of matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service config mappings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service config mappings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator,
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

		List<ServiceConfigMapping> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfigMapping>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceConfigMapping serviceConfigMapping : list) {
					if (!Objects.equals(uuid, serviceConfigMapping.getUuid()) ||
							(companyId != serviceConfigMapping.getCompanyId())) {
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

			query.append(_SQL_SELECT_SERVICECONFIGMAPPING_WHERE);

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
				query.append(ServiceConfigMappingModelImpl.ORDER_BY_JPQL);
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
					list = (List<ServiceConfigMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfigMapping>)QueryUtil.list(q,
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
	 * Returns the first service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config mapping
	 * @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (serviceConfigMapping != null) {
			return serviceConfigMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchServiceConfigMappingException(msg.toString());
	}

	/**
	 * Returns the first service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		List<ServiceConfigMapping> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config mapping
	 * @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (serviceConfigMapping != null) {
			return serviceConfigMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchServiceConfigMappingException(msg.toString());
	}

	/**
	 * Returns the last service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ServiceConfigMapping> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service config mappings before and after the current service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param serviceConfigMappingId the primary key of the current service config mapping
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service config mapping
	 * @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	 */
	@Override
	public ServiceConfigMapping[] findByUuid_C_PrevAndNext(
		long serviceConfigMappingId, String uuid, long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = findByPrimaryKey(serviceConfigMappingId);

		Session session = null;

		try {
			session = openSession();

			ServiceConfigMapping[] array = new ServiceConfigMappingImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, serviceConfigMapping,
					uuid, companyId, orderByComparator, true);

			array[1] = serviceConfigMapping;

			array[2] = getByUuid_C_PrevAndNext(session, serviceConfigMapping,
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

	protected ServiceConfigMapping getByUuid_C_PrevAndNext(Session session,
		ServiceConfigMapping serviceConfigMapping, String uuid, long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator,
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

		query.append(_SQL_SELECT_SERVICECONFIGMAPPING_WHERE);

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
			query.append(ServiceConfigMappingModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(serviceConfigMapping);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceConfigMapping> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service config mappings where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ServiceConfigMapping serviceConfigMapping : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceConfigMapping);
		}
	}

	/**
	 * Returns the number of service config mappings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching service config mappings
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICECONFIGMAPPING_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "serviceConfigMapping.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "serviceConfigMapping.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(serviceConfigMapping.uuid IS NULL OR serviceConfigMapping.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "serviceConfigMapping.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_MATTHC = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_MATTHC",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_MATTHC =
		new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_MATTHC",
			new String[] { Long.class.getName(), String.class.getName() },
			ServiceConfigMappingModelImpl.GROUPID_COLUMN_BITMASK |
			ServiceConfigMappingModelImpl.MATTHC_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_MATTHC = new FinderPath(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_MATTHC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the service config mappings where groupId = &#63; and maTTHC = &#63;.
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @return the matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByG_MATTHC(long groupId, String maTTHC) {
		return findByG_MATTHC(groupId, maTTHC, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service config mappings where groupId = &#63; and maTTHC = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @return the range of matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByG_MATTHC(long groupId,
		String maTTHC, int start, int end) {
		return findByG_MATTHC(groupId, maTTHC, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service config mappings where groupId = &#63; and maTTHC = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByG_MATTHC(long groupId,
		String maTTHC, int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return findByG_MATTHC(groupId, maTTHC, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the service config mappings where groupId = &#63; and maTTHC = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findByG_MATTHC(long groupId,
		String maTTHC, int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_MATTHC;
			finderArgs = new Object[] { groupId, maTTHC };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_MATTHC;
			finderArgs = new Object[] {
					groupId, maTTHC,
					
					start, end, orderByComparator
				};
		}

		List<ServiceConfigMapping> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfigMapping>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceConfigMapping serviceConfigMapping : list) {
					if ((groupId != serviceConfigMapping.getGroupId()) ||
							!Objects.equals(maTTHC,
								serviceConfigMapping.getMaTTHC())) {
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

			query.append(_SQL_SELECT_SERVICECONFIGMAPPING_WHERE);

			query.append(_FINDER_COLUMN_G_MATTHC_GROUPID_2);

			boolean bindMaTTHC = false;

			if (maTTHC == null) {
				query.append(_FINDER_COLUMN_G_MATTHC_MATTHC_1);
			}
			else if (maTTHC.equals("")) {
				query.append(_FINDER_COLUMN_G_MATTHC_MATTHC_3);
			}
			else {
				bindMaTTHC = true;

				query.append(_FINDER_COLUMN_G_MATTHC_MATTHC_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ServiceConfigMappingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindMaTTHC) {
					qPos.add(maTTHC);
				}

				if (!pagination) {
					list = (List<ServiceConfigMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfigMapping>)QueryUtil.list(q,
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
	 * Returns the first service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config mapping
	 * @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping findByG_MATTHC_First(long groupId,
		String maTTHC, OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = fetchByG_MATTHC_First(groupId,
				maTTHC, orderByComparator);

		if (serviceConfigMapping != null) {
			return serviceConfigMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", maTTHC=");
		msg.append(maTTHC);

		msg.append("}");

		throw new NoSuchServiceConfigMappingException(msg.toString());
	}

	/**
	 * Returns the first service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByG_MATTHC_First(long groupId,
		String maTTHC, OrderByComparator<ServiceConfigMapping> orderByComparator) {
		List<ServiceConfigMapping> list = findByG_MATTHC(groupId, maTTHC, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config mapping
	 * @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping findByG_MATTHC_Last(long groupId,
		String maTTHC, OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = fetchByG_MATTHC_Last(groupId,
				maTTHC, orderByComparator);

		if (serviceConfigMapping != null) {
			return serviceConfigMapping;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", maTTHC=");
		msg.append(maTTHC);

		msg.append("}");

		throw new NoSuchServiceConfigMappingException(msg.toString());
	}

	/**
	 * Returns the last service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByG_MATTHC_Last(long groupId,
		String maTTHC, OrderByComparator<ServiceConfigMapping> orderByComparator) {
		int count = countByG_MATTHC(groupId, maTTHC);

		if (count == 0) {
			return null;
		}

		List<ServiceConfigMapping> list = findByG_MATTHC(groupId, maTTHC,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service config mappings before and after the current service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	 *
	 * @param serviceConfigMappingId the primary key of the current service config mapping
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service config mapping
	 * @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	 */
	@Override
	public ServiceConfigMapping[] findByG_MATTHC_PrevAndNext(
		long serviceConfigMappingId, long groupId, String maTTHC,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = findByPrimaryKey(serviceConfigMappingId);

		Session session = null;

		try {
			session = openSession();

			ServiceConfigMapping[] array = new ServiceConfigMappingImpl[3];

			array[0] = getByG_MATTHC_PrevAndNext(session, serviceConfigMapping,
					groupId, maTTHC, orderByComparator, true);

			array[1] = serviceConfigMapping;

			array[2] = getByG_MATTHC_PrevAndNext(session, serviceConfigMapping,
					groupId, maTTHC, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServiceConfigMapping getByG_MATTHC_PrevAndNext(Session session,
		ServiceConfigMapping serviceConfigMapping, long groupId, String maTTHC,
		OrderByComparator<ServiceConfigMapping> orderByComparator,
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

		query.append(_SQL_SELECT_SERVICECONFIGMAPPING_WHERE);

		query.append(_FINDER_COLUMN_G_MATTHC_GROUPID_2);

		boolean bindMaTTHC = false;

		if (maTTHC == null) {
			query.append(_FINDER_COLUMN_G_MATTHC_MATTHC_1);
		}
		else if (maTTHC.equals("")) {
			query.append(_FINDER_COLUMN_G_MATTHC_MATTHC_3);
		}
		else {
			bindMaTTHC = true;

			query.append(_FINDER_COLUMN_G_MATTHC_MATTHC_2);
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
			query.append(ServiceConfigMappingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindMaTTHC) {
			qPos.add(maTTHC);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(serviceConfigMapping);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ServiceConfigMapping> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service config mappings where groupId = &#63; and maTTHC = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 */
	@Override
	public void removeByG_MATTHC(long groupId, String maTTHC) {
		for (ServiceConfigMapping serviceConfigMapping : findByG_MATTHC(
				groupId, maTTHC, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(serviceConfigMapping);
		}
	}

	/**
	 * Returns the number of service config mappings where groupId = &#63; and maTTHC = &#63;.
	 *
	 * @param groupId the group ID
	 * @param maTTHC the ma tthc
	 * @return the number of matching service config mappings
	 */
	@Override
	public int countByG_MATTHC(long groupId, String maTTHC) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_MATTHC;

		Object[] finderArgs = new Object[] { groupId, maTTHC };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SERVICECONFIGMAPPING_WHERE);

			query.append(_FINDER_COLUMN_G_MATTHC_GROUPID_2);

			boolean bindMaTTHC = false;

			if (maTTHC == null) {
				query.append(_FINDER_COLUMN_G_MATTHC_MATTHC_1);
			}
			else if (maTTHC.equals("")) {
				query.append(_FINDER_COLUMN_G_MATTHC_MATTHC_3);
			}
			else {
				bindMaTTHC = true;

				query.append(_FINDER_COLUMN_G_MATTHC_MATTHC_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindMaTTHC) {
					qPos.add(maTTHC);
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

	private static final String _FINDER_COLUMN_G_MATTHC_GROUPID_2 = "serviceConfigMapping.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_MATTHC_MATTHC_1 = "serviceConfigMapping.maTTHC IS NULL";
	private static final String _FINDER_COLUMN_G_MATTHC_MATTHC_2 = "serviceConfigMapping.maTTHC = ?";
	private static final String _FINDER_COLUMN_G_MATTHC_MATTHC_3 = "(serviceConfigMapping.maTTHC IS NULL OR serviceConfigMapping.maTTHC = '')";

	public ServiceConfigMappingPersistenceImpl() {
		setModelClass(ServiceConfigMapping.class);

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
	 * Caches the service config mapping in the entity cache if it is enabled.
	 *
	 * @param serviceConfigMapping the service config mapping
	 */
	@Override
	public void cacheResult(ServiceConfigMapping serviceConfigMapping) {
		entityCache.putResult(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			serviceConfigMapping.getPrimaryKey(), serviceConfigMapping);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				serviceConfigMapping.getUuid(),
				serviceConfigMapping.getGroupId()
			}, serviceConfigMapping);

		serviceConfigMapping.resetOriginalValues();
	}

	/**
	 * Caches the service config mappings in the entity cache if it is enabled.
	 *
	 * @param serviceConfigMappings the service config mappings
	 */
	@Override
	public void cacheResult(List<ServiceConfigMapping> serviceConfigMappings) {
		for (ServiceConfigMapping serviceConfigMapping : serviceConfigMappings) {
			if (entityCache.getResult(
						ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
						ServiceConfigMappingImpl.class,
						serviceConfigMapping.getPrimaryKey()) == null) {
				cacheResult(serviceConfigMapping);
			}
			else {
				serviceConfigMapping.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all service config mappings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServiceConfigMappingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the service config mapping.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServiceConfigMapping serviceConfigMapping) {
		entityCache.removeResult(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingImpl.class, serviceConfigMapping.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ServiceConfigMappingModelImpl)serviceConfigMapping,
			true);
	}

	@Override
	public void clearCache(List<ServiceConfigMapping> serviceConfigMappings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ServiceConfigMapping serviceConfigMapping : serviceConfigMappings) {
			entityCache.removeResult(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
				ServiceConfigMappingImpl.class,
				serviceConfigMapping.getPrimaryKey());

			clearUniqueFindersCache((ServiceConfigMappingModelImpl)serviceConfigMapping,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ServiceConfigMappingModelImpl serviceConfigMappingModelImpl) {
		Object[] args = new Object[] {
				serviceConfigMappingModelImpl.getUuid(),
				serviceConfigMappingModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			serviceConfigMappingModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ServiceConfigMappingModelImpl serviceConfigMappingModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					serviceConfigMappingModelImpl.getUuid(),
					serviceConfigMappingModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((serviceConfigMappingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					serviceConfigMappingModelImpl.getOriginalUuid(),
					serviceConfigMappingModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new service config mapping with the primary key. Does not add the service config mapping to the database.
	 *
	 * @param serviceConfigMappingId the primary key for the new service config mapping
	 * @return the new service config mapping
	 */
	@Override
	public ServiceConfigMapping create(long serviceConfigMappingId) {
		ServiceConfigMapping serviceConfigMapping = new ServiceConfigMappingImpl();

		serviceConfigMapping.setNew(true);
		serviceConfigMapping.setPrimaryKey(serviceConfigMappingId);

		String uuid = PortalUUIDUtil.generate();

		serviceConfigMapping.setUuid(uuid);

		serviceConfigMapping.setCompanyId(companyProvider.getCompanyId());

		return serviceConfigMapping;
	}

	/**
	 * Removes the service config mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceConfigMappingId the primary key of the service config mapping
	 * @return the service config mapping that was removed
	 * @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	 */
	@Override
	public ServiceConfigMapping remove(long serviceConfigMappingId)
		throws NoSuchServiceConfigMappingException {
		return remove((Serializable)serviceConfigMappingId);
	}

	/**
	 * Removes the service config mapping with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the service config mapping
	 * @return the service config mapping that was removed
	 * @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	 */
	@Override
	public ServiceConfigMapping remove(Serializable primaryKey)
		throws NoSuchServiceConfigMappingException {
		Session session = null;

		try {
			session = openSession();

			ServiceConfigMapping serviceConfigMapping = (ServiceConfigMapping)session.get(ServiceConfigMappingImpl.class,
					primaryKey);

			if (serviceConfigMapping == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServiceConfigMappingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(serviceConfigMapping);
		}
		catch (NoSuchServiceConfigMappingException nsee) {
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
	protected ServiceConfigMapping removeImpl(
		ServiceConfigMapping serviceConfigMapping) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serviceConfigMapping)) {
				serviceConfigMapping = (ServiceConfigMapping)session.get(ServiceConfigMappingImpl.class,
						serviceConfigMapping.getPrimaryKeyObj());
			}

			if (serviceConfigMapping != null) {
				session.delete(serviceConfigMapping);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (serviceConfigMapping != null) {
			clearCache(serviceConfigMapping);
		}

		return serviceConfigMapping;
	}

	@Override
	public ServiceConfigMapping updateImpl(
		ServiceConfigMapping serviceConfigMapping) {
		boolean isNew = serviceConfigMapping.isNew();

		if (!(serviceConfigMapping instanceof ServiceConfigMappingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(serviceConfigMapping.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(serviceConfigMapping);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in serviceConfigMapping proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ServiceConfigMapping implementation " +
				serviceConfigMapping.getClass());
		}

		ServiceConfigMappingModelImpl serviceConfigMappingModelImpl = (ServiceConfigMappingModelImpl)serviceConfigMapping;

		if (Validator.isNull(serviceConfigMapping.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			serviceConfigMapping.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (serviceConfigMapping.getCreateDate() == null)) {
			if (serviceContext == null) {
				serviceConfigMapping.setCreateDate(now);
			}
			else {
				serviceConfigMapping.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!serviceConfigMappingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				serviceConfigMapping.setModifiedDate(now);
			}
			else {
				serviceConfigMapping.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (serviceConfigMapping.isNew()) {
				session.save(serviceConfigMapping);

				serviceConfigMapping.setNew(false);
			}
			else {
				serviceConfigMapping = (ServiceConfigMapping)session.merge(serviceConfigMapping);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ServiceConfigMappingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { serviceConfigMappingModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					serviceConfigMappingModelImpl.getUuid(),
					serviceConfigMappingModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					serviceConfigMappingModelImpl.getGroupId(),
					serviceConfigMappingModelImpl.getMaTTHC()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_MATTHC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_MATTHC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((serviceConfigMappingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceConfigMappingModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { serviceConfigMappingModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((serviceConfigMappingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceConfigMappingModelImpl.getOriginalUuid(),
						serviceConfigMappingModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						serviceConfigMappingModelImpl.getUuid(),
						serviceConfigMappingModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((serviceConfigMappingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_MATTHC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						serviceConfigMappingModelImpl.getOriginalGroupId(),
						serviceConfigMappingModelImpl.getOriginalMaTTHC()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_MATTHC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_MATTHC,
					args);

				args = new Object[] {
						serviceConfigMappingModelImpl.getGroupId(),
						serviceConfigMappingModelImpl.getMaTTHC()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_MATTHC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_MATTHC,
					args);
			}
		}

		entityCache.putResult(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
			ServiceConfigMappingImpl.class,
			serviceConfigMapping.getPrimaryKey(), serviceConfigMapping, false);

		clearUniqueFindersCache(serviceConfigMappingModelImpl, false);
		cacheUniqueFindersCache(serviceConfigMappingModelImpl);

		serviceConfigMapping.resetOriginalValues();

		return serviceConfigMapping;
	}

	/**
	 * Returns the service config mapping with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the service config mapping
	 * @return the service config mapping
	 * @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	 */
	@Override
	public ServiceConfigMapping findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServiceConfigMappingException {
		ServiceConfigMapping serviceConfigMapping = fetchByPrimaryKey(primaryKey);

		if (serviceConfigMapping == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServiceConfigMappingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return serviceConfigMapping;
	}

	/**
	 * Returns the service config mapping with the primary key or throws a {@link NoSuchServiceConfigMappingException} if it could not be found.
	 *
	 * @param serviceConfigMappingId the primary key of the service config mapping
	 * @return the service config mapping
	 * @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	 */
	@Override
	public ServiceConfigMapping findByPrimaryKey(long serviceConfigMappingId)
		throws NoSuchServiceConfigMappingException {
		return findByPrimaryKey((Serializable)serviceConfigMappingId);
	}

	/**
	 * Returns the service config mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the service config mapping
	 * @return the service config mapping, or <code>null</code> if a service config mapping with the primary key could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
				ServiceConfigMappingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ServiceConfigMapping serviceConfigMapping = (ServiceConfigMapping)serializable;

		if (serviceConfigMapping == null) {
			Session session = null;

			try {
				session = openSession();

				serviceConfigMapping = (ServiceConfigMapping)session.get(ServiceConfigMappingImpl.class,
						primaryKey);

				if (serviceConfigMapping != null) {
					cacheResult(serviceConfigMapping);
				}
				else {
					entityCache.putResult(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
						ServiceConfigMappingImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
					ServiceConfigMappingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return serviceConfigMapping;
	}

	/**
	 * Returns the service config mapping with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceConfigMappingId the primary key of the service config mapping
	 * @return the service config mapping, or <code>null</code> if a service config mapping with the primary key could not be found
	 */
	@Override
	public ServiceConfigMapping fetchByPrimaryKey(long serviceConfigMappingId) {
		return fetchByPrimaryKey((Serializable)serviceConfigMappingId);
	}

	@Override
	public Map<Serializable, ServiceConfigMapping> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ServiceConfigMapping> map = new HashMap<Serializable, ServiceConfigMapping>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ServiceConfigMapping serviceConfigMapping = fetchByPrimaryKey(primaryKey);

			if (serviceConfigMapping != null) {
				map.put(primaryKey, serviceConfigMapping);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
					ServiceConfigMappingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ServiceConfigMapping)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SERVICECONFIGMAPPING_WHERE_PKS_IN);

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

			for (ServiceConfigMapping serviceConfigMapping : (List<ServiceConfigMapping>)q.list()) {
				map.put(serviceConfigMapping.getPrimaryKeyObj(),
					serviceConfigMapping);

				cacheResult(serviceConfigMapping);

				uncachedPrimaryKeys.remove(serviceConfigMapping.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ServiceConfigMappingModelImpl.ENTITY_CACHE_ENABLED,
					ServiceConfigMappingImpl.class, primaryKey, nullModel);
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
	 * Returns all the service config mappings.
	 *
	 * @return the service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service config mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @return the range of service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the service config mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findAll(int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service config mappings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service config mappings
	 * @param end the upper bound of the range of service config mappings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of service config mappings
	 */
	@Override
	public List<ServiceConfigMapping> findAll(int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator,
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

		List<ServiceConfigMapping> list = null;

		if (retrieveFromCache) {
			list = (List<ServiceConfigMapping>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SERVICECONFIGMAPPING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SERVICECONFIGMAPPING;

				if (pagination) {
					sql = sql.concat(ServiceConfigMappingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ServiceConfigMapping>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ServiceConfigMapping>)QueryUtil.list(q,
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
	 * Removes all the service config mappings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServiceConfigMapping serviceConfigMapping : findAll()) {
			remove(serviceConfigMapping);
		}
	}

	/**
	 * Returns the number of service config mappings.
	 *
	 * @return the number of service config mappings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SERVICECONFIGMAPPING);

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
		return ServiceConfigMappingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the service config mapping persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ServiceConfigMappingImpl.class.getName());
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
	private static final String _SQL_SELECT_SERVICECONFIGMAPPING = "SELECT serviceConfigMapping FROM ServiceConfigMapping serviceConfigMapping";
	private static final String _SQL_SELECT_SERVICECONFIGMAPPING_WHERE_PKS_IN = "SELECT serviceConfigMapping FROM ServiceConfigMapping serviceConfigMapping WHERE serviceConfigMappingId IN (";
	private static final String _SQL_SELECT_SERVICECONFIGMAPPING_WHERE = "SELECT serviceConfigMapping FROM ServiceConfigMapping serviceConfigMapping WHERE ";
	private static final String _SQL_COUNT_SERVICECONFIGMAPPING = "SELECT COUNT(serviceConfigMapping) FROM ServiceConfigMapping serviceConfigMapping";
	private static final String _SQL_COUNT_SERVICECONFIGMAPPING_WHERE = "SELECT COUNT(serviceConfigMapping) FROM ServiceConfigMapping serviceConfigMapping WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "serviceConfigMapping.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ServiceConfigMapping exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ServiceConfigMapping exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ServiceConfigMappingPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}