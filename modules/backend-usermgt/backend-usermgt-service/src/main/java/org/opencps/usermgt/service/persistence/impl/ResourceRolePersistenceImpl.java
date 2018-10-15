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

package org.opencps.usermgt.service.persistence.impl;

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

import org.opencps.usermgt.exception.NoSuchResourceRoleException;
import org.opencps.usermgt.model.ResourceRole;
import org.opencps.usermgt.model.impl.ResourceRoleImpl;
import org.opencps.usermgt.model.impl.ResourceRoleModelImpl;
import org.opencps.usermgt.service.persistence.ResourceRolePersistence;

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
 * The persistence implementation for the resource role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see ResourceRolePersistence
 * @see org.opencps.usermgt.service.persistence.ResourceRoleUtil
 * @generated
 */
@ProviderType
public class ResourceRolePersistenceImpl extends BasePersistenceImpl<ResourceRole>
	implements ResourceRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResourceRoleUtil} to access the resource role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ResourceRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ResourceRoleModelImpl.UUID_COLUMN_BITMASK |
			ResourceRoleModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the resource roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching resource roles
	 */
	@Override
	public List<ResourceRole> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @return the range of matching resource roles
	 */
	@Override
	public List<ResourceRole> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource roles
	 */
	@Override
	public List<ResourceRole> findByUuid(String uuid, int start, int end,
		OrderByComparator<ResourceRole> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource roles
	 */
	@Override
	public List<ResourceRole> findByUuid(String uuid, int start, int end,
		OrderByComparator<ResourceRole> orderByComparator,
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

		List<ResourceRole> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourceRole resourceRole : list) {
					if (!Objects.equals(uuid, resourceRole.getUuid())) {
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

			query.append(_SQL_SELECT_RESOURCEROLE_WHERE);

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
				query.append(ResourceRoleModelImpl.ORDER_BY_JPQL);
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
					list = (List<ResourceRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first resource role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource role
	 * @throws NoSuchResourceRoleException if a matching resource role could not be found
	 */
	@Override
	public ResourceRole findByUuid_First(String uuid,
		OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = fetchByUuid_First(uuid, orderByComparator);

		if (resourceRole != null) {
			return resourceRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchResourceRoleException(msg.toString());
	}

	/**
	 * Returns the first resource role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByUuid_First(String uuid,
		OrderByComparator<ResourceRole> orderByComparator) {
		List<ResourceRole> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource role
	 * @throws NoSuchResourceRoleException if a matching resource role could not be found
	 */
	@Override
	public ResourceRole findByUuid_Last(String uuid,
		OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = fetchByUuid_Last(uuid, orderByComparator);

		if (resourceRole != null) {
			return resourceRole;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchResourceRoleException(msg.toString());
	}

	/**
	 * Returns the last resource role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByUuid_Last(String uuid,
		OrderByComparator<ResourceRole> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ResourceRole> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource roles before and after the current resource role in the ordered set where uuid = &#63;.
	 *
	 * @param resourceRoleId the primary key of the current resource role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource role
	 * @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	 */
	@Override
	public ResourceRole[] findByUuid_PrevAndNext(long resourceRoleId,
		String uuid, OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = findByPrimaryKey(resourceRoleId);

		Session session = null;

		try {
			session = openSession();

			ResourceRole[] array = new ResourceRoleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, resourceRole, uuid,
					orderByComparator, true);

			array[1] = resourceRole;

			array[2] = getByUuid_PrevAndNext(session, resourceRole, uuid,
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

	protected ResourceRole getByUuid_PrevAndNext(Session session,
		ResourceRole resourceRole, String uuid,
		OrderByComparator<ResourceRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESOURCEROLE_WHERE);

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
			query.append(ResourceRoleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(resourceRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourceRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ResourceRole resourceRole : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(resourceRole);
		}
	}

	/**
	 * Returns the number of resource roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching resource roles
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESOURCEROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "resourceRole.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "resourceRole.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(resourceRole.uuid IS NULL OR resourceRole.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ResourceRoleModelImpl.UUID_COLUMN_BITMASK |
			ResourceRoleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the resource role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceRoleException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching resource role
	 * @throws NoSuchResourceRoleException if a matching resource role could not be found
	 */
	@Override
	public ResourceRole findByUUID_G(String uuid, long groupId)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = fetchByUUID_G(uuid, groupId);

		if (resourceRole == null) {
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

			throw new NoSuchResourceRoleException(msg.toString());
		}

		return resourceRole;
	}

	/**
	 * Returns the resource role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the resource role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ResourceRole) {
			ResourceRole resourceRole = (ResourceRole)result;

			if (!Objects.equals(uuid, resourceRole.getUuid()) ||
					(groupId != resourceRole.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_RESOURCEROLE_WHERE);

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

				List<ResourceRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ResourceRole resourceRole = list.get(0);

					result = resourceRole;

					cacheResult(resourceRole);
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
			return (ResourceRole)result;
		}
	}

	/**
	 * Removes the resource role where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the resource role that was removed
	 */
	@Override
	public ResourceRole removeByUUID_G(String uuid, long groupId)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = findByUUID_G(uuid, groupId);

		return remove(resourceRole);
	}

	/**
	 * Returns the number of resource roles where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching resource roles
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESOURCEROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "resourceRole.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "resourceRole.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(resourceRole.uuid IS NULL OR resourceRole.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "resourceRole.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ResourceRoleModelImpl.UUID_COLUMN_BITMASK |
			ResourceRoleModelImpl.COMPANYID_COLUMN_BITMASK |
			ResourceRoleModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the resource roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching resource roles
	 */
	@Override
	public List<ResourceRole> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @return the range of matching resource roles
	 */
	@Override
	public List<ResourceRole> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource roles
	 */
	@Override
	public List<ResourceRole> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ResourceRole> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource roles
	 */
	@Override
	public List<ResourceRole> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ResourceRole> orderByComparator,
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

		List<ResourceRole> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourceRole resourceRole : list) {
					if (!Objects.equals(uuid, resourceRole.getUuid()) ||
							(companyId != resourceRole.getCompanyId())) {
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

			query.append(_SQL_SELECT_RESOURCEROLE_WHERE);

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
				query.append(ResourceRoleModelImpl.ORDER_BY_JPQL);
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
					list = (List<ResourceRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource role
	 * @throws NoSuchResourceRoleException if a matching resource role could not be found
	 */
	@Override
	public ResourceRole findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (resourceRole != null) {
			return resourceRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchResourceRoleException(msg.toString());
	}

	/**
	 * Returns the first resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator) {
		List<ResourceRole> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource role
	 * @throws NoSuchResourceRoleException if a matching resource role could not be found
	 */
	@Override
	public ResourceRole findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (resourceRole != null) {
			return resourceRole;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchResourceRoleException(msg.toString());
	}

	/**
	 * Returns the last resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ResourceRole> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource roles before and after the current resource role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param resourceRoleId the primary key of the current resource role
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource role
	 * @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	 */
	@Override
	public ResourceRole[] findByUuid_C_PrevAndNext(long resourceRoleId,
		String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = findByPrimaryKey(resourceRoleId);

		Session session = null;

		try {
			session = openSession();

			ResourceRole[] array = new ResourceRoleImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, resourceRole, uuid,
					companyId, orderByComparator, true);

			array[1] = resourceRole;

			array[2] = getByUuid_C_PrevAndNext(session, resourceRole, uuid,
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

	protected ResourceRole getByUuid_C_PrevAndNext(Session session,
		ResourceRole resourceRole, String uuid, long companyId,
		OrderByComparator<ResourceRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_RESOURCEROLE_WHERE);

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
			query.append(ResourceRoleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(resourceRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourceRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource roles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ResourceRole resourceRole : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourceRole);
		}
	}

	/**
	 * Returns the number of resource roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching resource roles
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESOURCEROLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "resourceRole.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "resourceRole.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(resourceRole.uuid IS NULL OR resourceRole.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "resourceRole.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_ROLEID =
		new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_className_classPK_roleId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName()
			},
			ResourceRoleModelImpl.GROUPID_COLUMN_BITMASK |
			ResourceRoleModelImpl.CLASSNAME_COLUMN_BITMASK |
			ResourceRoleModelImpl.CLASSPK_COLUMN_BITMASK |
			ResourceRoleModelImpl.ROLEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_ROLEID =
		new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_className_classPK_roleId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; or throws a {@link NoSuchResourceRoleException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param roleId the role ID
	 * @return the matching resource role
	 * @throws NoSuchResourceRoleException if a matching resource role could not be found
	 */
	@Override
	public ResourceRole findByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = fetchByF_className_classPK_roleId(groupId,
				className, classPK, roleId);

		if (resourceRole == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", className=");
			msg.append(className);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", roleId=");
			msg.append(roleId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchResourceRoleException(msg.toString());
		}

		return resourceRole;
	}

	/**
	 * Returns the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param roleId the role ID
	 * @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId) {
		return fetchByF_className_classPK_roleId(groupId, className, classPK,
			roleId, true);
	}

	/**
	 * Returns the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param roleId the role ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, className, classPK, roleId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_ROLEID,
					finderArgs, this);
		}

		if (result instanceof ResourceRole) {
			ResourceRole resourceRole = (ResourceRole)result;

			if ((groupId != resourceRole.getGroupId()) ||
					!Objects.equals(className, resourceRole.getClassName()) ||
					!Objects.equals(classPK, resourceRole.getClassPK()) ||
					(roleId != resourceRole.getRoleId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_RESOURCEROLE_WHERE);

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSPK_2);
			}

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindClassPK) {
					qPos.add(classPK);
				}

				qPos.add(roleId);

				List<ResourceRole> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_ROLEID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ResourceRolePersistenceImpl.fetchByF_className_classPK_roleId(long, String, String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ResourceRole resourceRole = list.get(0);

					result = resourceRole;

					cacheResult(resourceRole);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_ROLEID,
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
			return (ResourceRole)result;
		}
	}

	/**
	 * Removes the resource role where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param roleId the role ID
	 * @return the resource role that was removed
	 */
	@Override
	public ResourceRole removeByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = findByF_className_classPK_roleId(groupId,
				className, classPK, roleId);

		return remove(resourceRole);
	}

	/**
	 * Returns the number of resource roles where groupId = &#63; and className = &#63; and classPK = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param roleId the role ID
	 * @return the number of matching resource roles
	 */
	@Override
	public int countByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_ROLEID;

		Object[] finderArgs = new Object[] { groupId, className, classPK, roleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_RESOURCEROLE_WHERE);

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSPK_2);
			}

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_ROLEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindClassPK) {
					qPos.add(classPK);
				}

				qPos.add(roleId);

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

	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_GROUPID_2 =
		"resourceRole.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSNAME_1 =
		"resourceRole.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSNAME_2 =
		"resourceRole.className = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSNAME_3 =
		"(resourceRole.className IS NULL OR resourceRole.className = '') AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSPK_1 =
		"resourceRole.classPK IS NULL AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSPK_2 =
		"resourceRole.classPK = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_CLASSPK_3 =
		"(resourceRole.classPK IS NULL OR resourceRole.classPK = '') AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_ROLEID_ROLEID_2 =
		"resourceRole.roleId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK =
		new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_className_classPK",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK =
		new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, ResourceRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_className_classPK",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			ResourceRoleModelImpl.GROUPID_COLUMN_BITMASK |
			ResourceRoleModelImpl.CLASSNAME_COLUMN_BITMASK |
			ResourceRoleModelImpl.CLASSPK_COLUMN_BITMASK |
			ResourceRoleModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK = new FinderPath(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_className_classPK",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @return the matching resource roles
	 */
	@Override
	public List<ResourceRole> findByF_className_classPK(long groupId,
		String className, String classPK) {
		return findByF_className_classPK(groupId, className, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @return the range of matching resource roles
	 */
	@Override
	public List<ResourceRole> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end) {
		return findByF_className_classPK(groupId, className, classPK, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource roles
	 */
	@Override
	public List<ResourceRole> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<ResourceRole> orderByComparator) {
		return findByF_className_classPK(groupId, className, classPK, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource roles
	 */
	@Override
	public List<ResourceRole> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<ResourceRole> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK;
			finderArgs = new Object[] { groupId, className, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK;
			finderArgs = new Object[] {
					groupId, className, classPK,
					
					start, end, orderByComparator
				};
		}

		List<ResourceRole> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceRole>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourceRole resourceRole : list) {
					if ((groupId != resourceRole.getGroupId()) ||
							!Objects.equals(className,
								resourceRole.getClassName()) ||
							!Objects.equals(classPK, resourceRole.getClassPK())) {
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

			query.append(_SQL_SELECT_RESOURCEROLE_WHERE);

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ResourceRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindClassPK) {
					qPos.add(classPK);
				}

				if (!pagination) {
					list = (List<ResourceRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceRole>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource role
	 * @throws NoSuchResourceRoleException if a matching resource role could not be found
	 */
	@Override
	public ResourceRole findByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = fetchByF_className_classPK_First(groupId,
				className, classPK, orderByComparator);

		if (resourceRole != null) {
			return resourceRole;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchResourceRoleException(msg.toString());
	}

	/**
	 * Returns the first resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator) {
		List<ResourceRole> list = findByF_className_classPK(groupId, className,
				classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource role
	 * @throws NoSuchResourceRoleException if a matching resource role could not be found
	 */
	@Override
	public ResourceRole findByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = fetchByF_className_classPK_Last(groupId,
				className, classPK, orderByComparator);

		if (resourceRole != null) {
			return resourceRole;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchResourceRoleException(msg.toString());
	}

	/**
	 * Returns the last resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource role, or <code>null</code> if a matching resource role could not be found
	 */
	@Override
	public ResourceRole fetchByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator) {
		int count = countByF_className_classPK(groupId, className, classPK);

		if (count == 0) {
			return null;
		}

		List<ResourceRole> list = findByF_className_classPK(groupId, className,
				classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource roles before and after the current resource role in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param resourceRoleId the primary key of the current resource role
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource role
	 * @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	 */
	@Override
	public ResourceRole[] findByF_className_classPK_PrevAndNext(
		long resourceRoleId, long groupId, String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = findByPrimaryKey(resourceRoleId);

		Session session = null;

		try {
			session = openSession();

			ResourceRole[] array = new ResourceRoleImpl[3];

			array[0] = getByF_className_classPK_PrevAndNext(session,
					resourceRole, groupId, className, classPK,
					orderByComparator, true);

			array[1] = resourceRole;

			array[2] = getByF_className_classPK_PrevAndNext(session,
					resourceRole, groupId, className, classPK,
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

	protected ResourceRole getByF_className_classPK_PrevAndNext(
		Session session, ResourceRole resourceRole, long groupId,
		String className, String classPK,
		OrderByComparator<ResourceRole> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_RESOURCEROLE_WHERE);

		query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_GROUPID_2);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_1);
		}
		else if (className.equals("")) {
			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_2);
		}

		boolean bindClassPK = false;

		if (classPK == null) {
			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_1);
		}
		else if (classPK.equals("")) {
			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_3);
		}
		else {
			bindClassPK = true;

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_2);
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
			query.append(ResourceRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindClassName) {
			qPos.add(className);
		}

		if (bindClassPK) {
			qPos.add(classPK);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resourceRole);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourceRole> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource roles where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 */
	@Override
	public void removeByF_className_classPK(long groupId, String className,
		String classPK) {
		for (ResourceRole resourceRole : findByF_className_classPK(groupId,
				className, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourceRole);
		}
	}

	/**
	 * Returns the number of resource roles where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @return the number of matching resource roles
	 */
	@Override
	public int countByF_className_classPK(long groupId, String className,
		String classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK;

		Object[] finderArgs = new Object[] { groupId, className, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESOURCEROLE_WHERE);

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

				if (bindClassPK) {
					qPos.add(classPK);
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

	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_GROUPID_2 = "resourceRole.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_1 = "resourceRole.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_2 = "resourceRole.className = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_3 = "(resourceRole.className IS NULL OR resourceRole.className = '') AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_1 = "resourceRole.classPK IS NULL";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_2 = "resourceRole.classPK = ?";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_3 = "(resourceRole.classPK IS NULL OR resourceRole.classPK = '')";

	public ResourceRolePersistenceImpl() {
		setModelClass(ResourceRole.class);

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
	 * Caches the resource role in the entity cache if it is enabled.
	 *
	 * @param resourceRole the resource role
	 */
	@Override
	public void cacheResult(ResourceRole resourceRole) {
		entityCache.putResult(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleImpl.class, resourceRole.getPrimaryKey(), resourceRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { resourceRole.getUuid(), resourceRole.getGroupId() },
			resourceRole);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_ROLEID,
			new Object[] {
				resourceRole.getGroupId(), resourceRole.getClassName(),
				resourceRole.getClassPK(), resourceRole.getRoleId()
			}, resourceRole);

		resourceRole.resetOriginalValues();
	}

	/**
	 * Caches the resource roles in the entity cache if it is enabled.
	 *
	 * @param resourceRoles the resource roles
	 */
	@Override
	public void cacheResult(List<ResourceRole> resourceRoles) {
		for (ResourceRole resourceRole : resourceRoles) {
			if (entityCache.getResult(
						ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
						ResourceRoleImpl.class, resourceRole.getPrimaryKey()) == null) {
				cacheResult(resourceRole);
			}
			else {
				resourceRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all resource roles.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ResourceRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the resource role.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ResourceRole resourceRole) {
		entityCache.removeResult(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleImpl.class, resourceRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ResourceRoleModelImpl)resourceRole, true);
	}

	@Override
	public void clearCache(List<ResourceRole> resourceRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ResourceRole resourceRole : resourceRoles) {
			entityCache.removeResult(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
				ResourceRoleImpl.class, resourceRole.getPrimaryKey());

			clearUniqueFindersCache((ResourceRoleModelImpl)resourceRole, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ResourceRoleModelImpl resourceRoleModelImpl) {
		Object[] args = new Object[] {
				resourceRoleModelImpl.getUuid(),
				resourceRoleModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			resourceRoleModelImpl, false);

		args = new Object[] {
				resourceRoleModelImpl.getGroupId(),
				resourceRoleModelImpl.getClassName(),
				resourceRoleModelImpl.getClassPK(),
				resourceRoleModelImpl.getRoleId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_ROLEID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_ROLEID,
			args, resourceRoleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ResourceRoleModelImpl resourceRoleModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					resourceRoleModelImpl.getUuid(),
					resourceRoleModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((resourceRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					resourceRoleModelImpl.getOriginalUuid(),
					resourceRoleModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					resourceRoleModelImpl.getGroupId(),
					resourceRoleModelImpl.getClassName(),
					resourceRoleModelImpl.getClassPK(),
					resourceRoleModelImpl.getRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_ROLEID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_ROLEID,
				args);
		}

		if ((resourceRoleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_ROLEID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					resourceRoleModelImpl.getOriginalGroupId(),
					resourceRoleModelImpl.getOriginalClassName(),
					resourceRoleModelImpl.getOriginalClassPK(),
					resourceRoleModelImpl.getOriginalRoleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_ROLEID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_ROLEID,
				args);
		}
	}

	/**
	 * Creates a new resource role with the primary key. Does not add the resource role to the database.
	 *
	 * @param resourceRoleId the primary key for the new resource role
	 * @return the new resource role
	 */
	@Override
	public ResourceRole create(long resourceRoleId) {
		ResourceRole resourceRole = new ResourceRoleImpl();

		resourceRole.setNew(true);
		resourceRole.setPrimaryKey(resourceRoleId);

		String uuid = PortalUUIDUtil.generate();

		resourceRole.setUuid(uuid);

		resourceRole.setCompanyId(companyProvider.getCompanyId());

		return resourceRole;
	}

	/**
	 * Removes the resource role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resourceRoleId the primary key of the resource role
	 * @return the resource role that was removed
	 * @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	 */
	@Override
	public ResourceRole remove(long resourceRoleId)
		throws NoSuchResourceRoleException {
		return remove((Serializable)resourceRoleId);
	}

	/**
	 * Removes the resource role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the resource role
	 * @return the resource role that was removed
	 * @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	 */
	@Override
	public ResourceRole remove(Serializable primaryKey)
		throws NoSuchResourceRoleException {
		Session session = null;

		try {
			session = openSession();

			ResourceRole resourceRole = (ResourceRole)session.get(ResourceRoleImpl.class,
					primaryKey);

			if (resourceRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResourceRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(resourceRole);
		}
		catch (NoSuchResourceRoleException nsee) {
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
	protected ResourceRole removeImpl(ResourceRole resourceRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(resourceRole)) {
				resourceRole = (ResourceRole)session.get(ResourceRoleImpl.class,
						resourceRole.getPrimaryKeyObj());
			}

			if (resourceRole != null) {
				session.delete(resourceRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (resourceRole != null) {
			clearCache(resourceRole);
		}

		return resourceRole;
	}

	@Override
	public ResourceRole updateImpl(ResourceRole resourceRole) {
		boolean isNew = resourceRole.isNew();

		if (!(resourceRole instanceof ResourceRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(resourceRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(resourceRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in resourceRole proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ResourceRole implementation " +
				resourceRole.getClass());
		}

		ResourceRoleModelImpl resourceRoleModelImpl = (ResourceRoleModelImpl)resourceRole;

		if (Validator.isNull(resourceRole.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			resourceRole.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (resourceRole.getCreateDate() == null)) {
			if (serviceContext == null) {
				resourceRole.setCreateDate(now);
			}
			else {
				resourceRole.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!resourceRoleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				resourceRole.setModifiedDate(now);
			}
			else {
				resourceRole.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (resourceRole.isNew()) {
				session.save(resourceRole);

				resourceRole.setNew(false);
			}
			else {
				resourceRole = (ResourceRole)session.merge(resourceRole);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ResourceRoleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { resourceRoleModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					resourceRoleModelImpl.getUuid(),
					resourceRoleModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					resourceRoleModelImpl.getGroupId(),
					resourceRoleModelImpl.getClassName(),
					resourceRoleModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((resourceRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourceRoleModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { resourceRoleModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((resourceRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourceRoleModelImpl.getOriginalUuid(),
						resourceRoleModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						resourceRoleModelImpl.getUuid(),
						resourceRoleModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((resourceRoleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourceRoleModelImpl.getOriginalGroupId(),
						resourceRoleModelImpl.getOriginalClassName(),
						resourceRoleModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK,
					args);

				args = new Object[] {
						resourceRoleModelImpl.getGroupId(),
						resourceRoleModelImpl.getClassName(),
						resourceRoleModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK,
					args);
			}
		}

		entityCache.putResult(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
			ResourceRoleImpl.class, resourceRole.getPrimaryKey(), resourceRole,
			false);

		clearUniqueFindersCache(resourceRoleModelImpl, false);
		cacheUniqueFindersCache(resourceRoleModelImpl);

		resourceRole.resetOriginalValues();

		return resourceRole;
	}

	/**
	 * Returns the resource role with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the resource role
	 * @return the resource role
	 * @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	 */
	@Override
	public ResourceRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchResourceRoleException {
		ResourceRole resourceRole = fetchByPrimaryKey(primaryKey);

		if (resourceRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchResourceRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return resourceRole;
	}

	/**
	 * Returns the resource role with the primary key or throws a {@link NoSuchResourceRoleException} if it could not be found.
	 *
	 * @param resourceRoleId the primary key of the resource role
	 * @return the resource role
	 * @throws NoSuchResourceRoleException if a resource role with the primary key could not be found
	 */
	@Override
	public ResourceRole findByPrimaryKey(long resourceRoleId)
		throws NoSuchResourceRoleException {
		return findByPrimaryKey((Serializable)resourceRoleId);
	}

	/**
	 * Returns the resource role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the resource role
	 * @return the resource role, or <code>null</code> if a resource role with the primary key could not be found
	 */
	@Override
	public ResourceRole fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
				ResourceRoleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ResourceRole resourceRole = (ResourceRole)serializable;

		if (resourceRole == null) {
			Session session = null;

			try {
				session = openSession();

				resourceRole = (ResourceRole)session.get(ResourceRoleImpl.class,
						primaryKey);

				if (resourceRole != null) {
					cacheResult(resourceRole);
				}
				else {
					entityCache.putResult(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
						ResourceRoleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
					ResourceRoleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return resourceRole;
	}

	/**
	 * Returns the resource role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param resourceRoleId the primary key of the resource role
	 * @return the resource role, or <code>null</code> if a resource role with the primary key could not be found
	 */
	@Override
	public ResourceRole fetchByPrimaryKey(long resourceRoleId) {
		return fetchByPrimaryKey((Serializable)resourceRoleId);
	}

	@Override
	public Map<Serializable, ResourceRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ResourceRole> map = new HashMap<Serializable, ResourceRole>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ResourceRole resourceRole = fetchByPrimaryKey(primaryKey);

			if (resourceRole != null) {
				map.put(primaryKey, resourceRole);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
					ResourceRoleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ResourceRole)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RESOURCEROLE_WHERE_PKS_IN);

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

			for (ResourceRole resourceRole : (List<ResourceRole>)q.list()) {
				map.put(resourceRole.getPrimaryKeyObj(), resourceRole);

				cacheResult(resourceRole);

				uncachedPrimaryKeys.remove(resourceRole.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ResourceRoleModelImpl.ENTITY_CACHE_ENABLED,
					ResourceRoleImpl.class, primaryKey, nullModel);
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
	 * Returns all the resource roles.
	 *
	 * @return the resource roles
	 */
	@Override
	public List<ResourceRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @return the range of resource roles
	 */
	@Override
	public List<ResourceRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of resource roles
	 */
	@Override
	public List<ResourceRole> findAll(int start, int end,
		OrderByComparator<ResourceRole> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource roles
	 * @param end the upper bound of the range of resource roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of resource roles
	 */
	@Override
	public List<ResourceRole> findAll(int start, int end,
		OrderByComparator<ResourceRole> orderByComparator,
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

		List<ResourceRole> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceRole>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RESOURCEROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESOURCEROLE;

				if (pagination) {
					sql = sql.concat(ResourceRoleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ResourceRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceRole>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the resource roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ResourceRole resourceRole : findAll()) {
			remove(resourceRole);
		}
	}

	/**
	 * Returns the number of resource roles.
	 *
	 * @return the number of resource roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RESOURCEROLE);

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
		return ResourceRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the resource role persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ResourceRoleImpl.class.getName());
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
	private static final String _SQL_SELECT_RESOURCEROLE = "SELECT resourceRole FROM ResourceRole resourceRole";
	private static final String _SQL_SELECT_RESOURCEROLE_WHERE_PKS_IN = "SELECT resourceRole FROM ResourceRole resourceRole WHERE resourceRoleId IN (";
	private static final String _SQL_SELECT_RESOURCEROLE_WHERE = "SELECT resourceRole FROM ResourceRole resourceRole WHERE ";
	private static final String _SQL_COUNT_RESOURCEROLE = "SELECT COUNT(resourceRole) FROM ResourceRole resourceRole";
	private static final String _SQL_COUNT_RESOURCEROLE_WHERE = "SELECT COUNT(resourceRole) FROM ResourceRole resourceRole WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resourceRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResourceRole exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResourceRole exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ResourceRolePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}