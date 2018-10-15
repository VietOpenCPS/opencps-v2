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

import org.opencps.usermgt.exception.NoSuchResourceUserException;
import org.opencps.usermgt.model.ResourceUser;
import org.opencps.usermgt.model.impl.ResourceUserImpl;
import org.opencps.usermgt.model.impl.ResourceUserModelImpl;
import org.opencps.usermgt.service.persistence.ResourceUserPersistence;

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
 * The persistence implementation for the resource user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see ResourceUserPersistence
 * @see org.opencps.usermgt.service.persistence.ResourceUserUtil
 * @generated
 */
@ProviderType
public class ResourceUserPersistenceImpl extends BasePersistenceImpl<ResourceUser>
	implements ResourceUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResourceUserUtil} to access the resource user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ResourceUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ResourceUserModelImpl.UUID_COLUMN_BITMASK |
			ResourceUserModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the resource users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching resource users
	 */
	@Override
	public List<ResourceUser> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @return the range of matching resource users
	 */
	@Override
	public List<ResourceUser> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource users
	 */
	@Override
	public List<ResourceUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<ResourceUser> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource users
	 */
	@Override
	public List<ResourceUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<ResourceUser> orderByComparator,
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

		List<ResourceUser> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourceUser resourceUser : list) {
					if (!Objects.equals(uuid, resourceUser.getUuid())) {
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

			query.append(_SQL_SELECT_RESOURCEUSER_WHERE);

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
				query.append(ResourceUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<ResourceUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first resource user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource user
	 * @throws NoSuchResourceUserException if a matching resource user could not be found
	 */
	@Override
	public ResourceUser findByUuid_First(String uuid,
		OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = fetchByUuid_First(uuid, orderByComparator);

		if (resourceUser != null) {
			return resourceUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchResourceUserException(msg.toString());
	}

	/**
	 * Returns the first resource user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByUuid_First(String uuid,
		OrderByComparator<ResourceUser> orderByComparator) {
		List<ResourceUser> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource user
	 * @throws NoSuchResourceUserException if a matching resource user could not be found
	 */
	@Override
	public ResourceUser findByUuid_Last(String uuid,
		OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = fetchByUuid_Last(uuid, orderByComparator);

		if (resourceUser != null) {
			return resourceUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchResourceUserException(msg.toString());
	}

	/**
	 * Returns the last resource user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByUuid_Last(String uuid,
		OrderByComparator<ResourceUser> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ResourceUser> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource users before and after the current resource user in the ordered set where uuid = &#63;.
	 *
	 * @param resourceUserId the primary key of the current resource user
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource user
	 * @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	 */
	@Override
	public ResourceUser[] findByUuid_PrevAndNext(long resourceUserId,
		String uuid, OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = findByPrimaryKey(resourceUserId);

		Session session = null;

		try {
			session = openSession();

			ResourceUser[] array = new ResourceUserImpl[3];

			array[0] = getByUuid_PrevAndNext(session, resourceUser, uuid,
					orderByComparator, true);

			array[1] = resourceUser;

			array[2] = getByUuid_PrevAndNext(session, resourceUser, uuid,
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

	protected ResourceUser getByUuid_PrevAndNext(Session session,
		ResourceUser resourceUser, String uuid,
		OrderByComparator<ResourceUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESOURCEUSER_WHERE);

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
			query.append(ResourceUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(resourceUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourceUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource users where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ResourceUser resourceUser : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(resourceUser);
		}
	}

	/**
	 * Returns the number of resource users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching resource users
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESOURCEUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "resourceUser.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "resourceUser.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(resourceUser.uuid IS NULL OR resourceUser.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ResourceUserModelImpl.UUID_COLUMN_BITMASK |
			ResourceUserModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the resource user where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchResourceUserException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching resource user
	 * @throws NoSuchResourceUserException if a matching resource user could not be found
	 */
	@Override
	public ResourceUser findByUUID_G(String uuid, long groupId)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = fetchByUUID_G(uuid, groupId);

		if (resourceUser == null) {
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

			throw new NoSuchResourceUserException(msg.toString());
		}

		return resourceUser;
	}

	/**
	 * Returns the resource user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the resource user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ResourceUser) {
			ResourceUser resourceUser = (ResourceUser)result;

			if (!Objects.equals(uuid, resourceUser.getUuid()) ||
					(groupId != resourceUser.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_RESOURCEUSER_WHERE);

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

				List<ResourceUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ResourceUser resourceUser = list.get(0);

					result = resourceUser;

					cacheResult(resourceUser);
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
			return (ResourceUser)result;
		}
	}

	/**
	 * Removes the resource user where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the resource user that was removed
	 */
	@Override
	public ResourceUser removeByUUID_G(String uuid, long groupId)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = findByUUID_G(uuid, groupId);

		return remove(resourceUser);
	}

	/**
	 * Returns the number of resource users where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching resource users
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESOURCEUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "resourceUser.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "resourceUser.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(resourceUser.uuid IS NULL OR resourceUser.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "resourceUser.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ResourceUserModelImpl.UUID_COLUMN_BITMASK |
			ResourceUserModelImpl.COMPANYID_COLUMN_BITMASK |
			ResourceUserModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the resource users where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching resource users
	 */
	@Override
	public List<ResourceUser> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource users where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @return the range of matching resource users
	 */
	@Override
	public List<ResourceUser> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource users where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource users
	 */
	@Override
	public List<ResourceUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ResourceUser> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource users where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource users
	 */
	@Override
	public List<ResourceUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ResourceUser> orderByComparator,
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

		List<ResourceUser> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourceUser resourceUser : list) {
					if (!Objects.equals(uuid, resourceUser.getUuid()) ||
							(companyId != resourceUser.getCompanyId())) {
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

			query.append(_SQL_SELECT_RESOURCEUSER_WHERE);

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
				query.append(ResourceUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<ResourceUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource user
	 * @throws NoSuchResourceUserException if a matching resource user could not be found
	 */
	@Override
	public ResourceUser findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (resourceUser != null) {
			return resourceUser;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchResourceUserException(msg.toString());
	}

	/**
	 * Returns the first resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator) {
		List<ResourceUser> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource user
	 * @throws NoSuchResourceUserException if a matching resource user could not be found
	 */
	@Override
	public ResourceUser findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (resourceUser != null) {
			return resourceUser;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchResourceUserException(msg.toString());
	}

	/**
	 * Returns the last resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ResourceUser> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource users before and after the current resource user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param resourceUserId the primary key of the current resource user
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource user
	 * @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	 */
	@Override
	public ResourceUser[] findByUuid_C_PrevAndNext(long resourceUserId,
		String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = findByPrimaryKey(resourceUserId);

		Session session = null;

		try {
			session = openSession();

			ResourceUser[] array = new ResourceUserImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, resourceUser, uuid,
					companyId, orderByComparator, true);

			array[1] = resourceUser;

			array[2] = getByUuid_C_PrevAndNext(session, resourceUser, uuid,
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

	protected ResourceUser getByUuid_C_PrevAndNext(Session session,
		ResourceUser resourceUser, String uuid, long companyId,
		OrderByComparator<ResourceUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_RESOURCEUSER_WHERE);

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
			query.append(ResourceUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(resourceUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourceUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource users where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ResourceUser resourceUser : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourceUser);
		}
	}

	/**
	 * Returns the number of resource users where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching resource users
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESOURCEUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "resourceUser.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "resourceUser.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(resourceUser.uuid IS NULL OR resourceUser.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "resourceUser.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_TOUSERID =
		new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_className_classPK_toUserId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName()
			},
			ResourceUserModelImpl.GROUPID_COLUMN_BITMASK |
			ResourceUserModelImpl.CLASSNAME_COLUMN_BITMASK |
			ResourceUserModelImpl.CLASSPK_COLUMN_BITMASK |
			ResourceUserModelImpl.TOUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_TOUSERID =
		new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_className_classPK_toUserId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; or throws a {@link NoSuchResourceUserException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toUserId the to user ID
	 * @return the matching resource user
	 * @throws NoSuchResourceUserException if a matching resource user could not be found
	 */
	@Override
	public ResourceUser findByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = fetchByF_className_classPK_toUserId(groupId,
				className, classPK, toUserId);

		if (resourceUser == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", className=");
			msg.append(className);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", toUserId=");
			msg.append(toUserId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchResourceUserException(msg.toString());
		}

		return resourceUser;
	}

	/**
	 * Returns the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toUserId the to user ID
	 * @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId) {
		return fetchByF_className_classPK_toUserId(groupId, className, classPK,
			toUserId, true);
	}

	/**
	 * Returns the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toUserId the to user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, className, classPK, toUserId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_TOUSERID,
					finderArgs, this);
		}

		if (result instanceof ResourceUser) {
			ResourceUser resourceUser = (ResourceUser)result;

			if ((groupId != resourceUser.getGroupId()) ||
					!Objects.equals(className, resourceUser.getClassName()) ||
					!Objects.equals(classPK, resourceUser.getClassPK()) ||
					(toUserId != resourceUser.getToUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_RESOURCEUSER_WHERE);

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSPK_2);
			}

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_TOUSERID_2);

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

				qPos.add(toUserId);

				List<ResourceUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_TOUSERID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ResourceUserPersistenceImpl.fetchByF_className_classPK_toUserId(long, String, String, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ResourceUser resourceUser = list.get(0);

					result = resourceUser;

					cacheResult(resourceUser);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_TOUSERID,
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
			return (ResourceUser)result;
		}
	}

	/**
	 * Removes the resource user where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toUserId the to user ID
	 * @return the resource user that was removed
	 */
	@Override
	public ResourceUser removeByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = findByF_className_classPK_toUserId(groupId,
				className, classPK, toUserId);

		return remove(resourceUser);
	}

	/**
	 * Returns the number of resource users where groupId = &#63; and className = &#63; and classPK = &#63; and toUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param toUserId the to user ID
	 * @return the number of matching resource users
	 */
	@Override
	public int countByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_TOUSERID;

		Object[] finderArgs = new Object[] { groupId, className, classPK, toUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_RESOURCEUSER_WHERE);

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSNAME_2);
			}

			boolean bindClassPK = false;

			if (classPK == null) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSPK_1);
			}
			else if (classPK.equals("")) {
				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSPK_3);
			}
			else {
				bindClassPK = true;

				query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSPK_2);
			}

			query.append(_FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_TOUSERID_2);

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

				qPos.add(toUserId);

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

	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_GROUPID_2 =
		"resourceUser.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSNAME_1 =
		"resourceUser.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSNAME_2 =
		"resourceUser.className = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSNAME_3 =
		"(resourceUser.className IS NULL OR resourceUser.className = '') AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSPK_1 =
		"resourceUser.classPK IS NULL AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSPK_2 =
		"resourceUser.classPK = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_CLASSPK_3 =
		"(resourceUser.classPK IS NULL OR resourceUser.classPK = '') AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_TOUSERID_TOUSERID_2 =
		"resourceUser.toUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK =
		new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_className_classPK",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK =
		new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, ResourceUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_className_classPK",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			ResourceUserModelImpl.GROUPID_COLUMN_BITMASK |
			ResourceUserModelImpl.CLASSNAME_COLUMN_BITMASK |
			ResourceUserModelImpl.CLASSPK_COLUMN_BITMASK |
			ResourceUserModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK = new FinderPath(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_className_classPK",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @return the matching resource users
	 */
	@Override
	public List<ResourceUser> findByF_className_classPK(long groupId,
		String className, String classPK) {
		return findByF_className_classPK(groupId, className, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @return the range of matching resource users
	 */
	@Override
	public List<ResourceUser> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end) {
		return findByF_className_classPK(groupId, className, classPK, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching resource users
	 */
	@Override
	public List<ResourceUser> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<ResourceUser> orderByComparator) {
		return findByF_className_classPK(groupId, className, classPK, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching resource users
	 */
	@Override
	public List<ResourceUser> findByF_className_classPK(long groupId,
		String className, String classPK, int start, int end,
		OrderByComparator<ResourceUser> orderByComparator,
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

		List<ResourceUser> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ResourceUser resourceUser : list) {
					if ((groupId != resourceUser.getGroupId()) ||
							!Objects.equals(className,
								resourceUser.getClassName()) ||
							!Objects.equals(classPK, resourceUser.getClassPK())) {
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

			query.append(_SQL_SELECT_RESOURCEUSER_WHERE);

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
				query.append(ResourceUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<ResourceUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource user
	 * @throws NoSuchResourceUserException if a matching resource user could not be found
	 */
	@Override
	public ResourceUser findByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = fetchByF_className_classPK_First(groupId,
				className, classPK, orderByComparator);

		if (resourceUser != null) {
			return resourceUser;
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

		throw new NoSuchResourceUserException(msg.toString());
	}

	/**
	 * Returns the first resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByF_className_classPK_First(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator) {
		List<ResourceUser> list = findByF_className_classPK(groupId, className,
				classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource user
	 * @throws NoSuchResourceUserException if a matching resource user could not be found
	 */
	@Override
	public ResourceUser findByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = fetchByF_className_classPK_Last(groupId,
				className, classPK, orderByComparator);

		if (resourceUser != null) {
			return resourceUser;
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

		throw new NoSuchResourceUserException(msg.toString());
	}

	/**
	 * Returns the last resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching resource user, or <code>null</code> if a matching resource user could not be found
	 */
	@Override
	public ResourceUser fetchByF_className_classPK_Last(long groupId,
		String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator) {
		int count = countByF_className_classPK(groupId, className, classPK);

		if (count == 0) {
			return null;
		}

		List<ResourceUser> list = findByF_className_classPK(groupId, className,
				classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the resource users before and after the current resource user in the ordered set where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param resourceUserId the primary key of the current resource user
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next resource user
	 * @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	 */
	@Override
	public ResourceUser[] findByF_className_classPK_PrevAndNext(
		long resourceUserId, long groupId, String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = findByPrimaryKey(resourceUserId);

		Session session = null;

		try {
			session = openSession();

			ResourceUser[] array = new ResourceUserImpl[3];

			array[0] = getByF_className_classPK_PrevAndNext(session,
					resourceUser, groupId, className, classPK,
					orderByComparator, true);

			array[1] = resourceUser;

			array[2] = getByF_className_classPK_PrevAndNext(session,
					resourceUser, groupId, className, classPK,
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

	protected ResourceUser getByF_className_classPK_PrevAndNext(
		Session session, ResourceUser resourceUser, long groupId,
		String className, String classPK,
		OrderByComparator<ResourceUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_RESOURCEUSER_WHERE);

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
			query.append(ResourceUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(resourceUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ResourceUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the resource users where groupId = &#63; and className = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 */
	@Override
	public void removeByF_className_classPK(long groupId, String className,
		String classPK) {
		for (ResourceUser resourceUser : findByF_className_classPK(groupId,
				className, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(resourceUser);
		}
	}

	/**
	 * Returns the number of resource users where groupId = &#63; and className = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param classPK the class pk
	 * @return the number of matching resource users
	 */
	@Override
	public int countByF_className_classPK(long groupId, String className,
		String classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK;

		Object[] finderArgs = new Object[] { groupId, className, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESOURCEUSER_WHERE);

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

	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_GROUPID_2 = "resourceUser.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_1 = "resourceUser.className IS NULL AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_2 = "resourceUser.className = ? AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSNAME_3 = "(resourceUser.className IS NULL OR resourceUser.className = '') AND ";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_1 = "resourceUser.classPK IS NULL";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_2 = "resourceUser.classPK = ?";
	private static final String _FINDER_COLUMN_F_CLASSNAME_CLASSPK_CLASSPK_3 = "(resourceUser.classPK IS NULL OR resourceUser.classPK = '')";

	public ResourceUserPersistenceImpl() {
		setModelClass(ResourceUser.class);

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
	 * Caches the resource user in the entity cache if it is enabled.
	 *
	 * @param resourceUser the resource user
	 */
	@Override
	public void cacheResult(ResourceUser resourceUser) {
		entityCache.putResult(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserImpl.class, resourceUser.getPrimaryKey(), resourceUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { resourceUser.getUuid(), resourceUser.getGroupId() },
			resourceUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_TOUSERID,
			new Object[] {
				resourceUser.getGroupId(), resourceUser.getClassName(),
				resourceUser.getClassPK(), resourceUser.getToUserId()
			}, resourceUser);

		resourceUser.resetOriginalValues();
	}

	/**
	 * Caches the resource users in the entity cache if it is enabled.
	 *
	 * @param resourceUsers the resource users
	 */
	@Override
	public void cacheResult(List<ResourceUser> resourceUsers) {
		for (ResourceUser resourceUser : resourceUsers) {
			if (entityCache.getResult(
						ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
						ResourceUserImpl.class, resourceUser.getPrimaryKey()) == null) {
				cacheResult(resourceUser);
			}
			else {
				resourceUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all resource users.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ResourceUserImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the resource user.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ResourceUser resourceUser) {
		entityCache.removeResult(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserImpl.class, resourceUser.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ResourceUserModelImpl)resourceUser, true);
	}

	@Override
	public void clearCache(List<ResourceUser> resourceUsers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ResourceUser resourceUser : resourceUsers) {
			entityCache.removeResult(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
				ResourceUserImpl.class, resourceUser.getPrimaryKey());

			clearUniqueFindersCache((ResourceUserModelImpl)resourceUser, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ResourceUserModelImpl resourceUserModelImpl) {
		Object[] args = new Object[] {
				resourceUserModelImpl.getUuid(),
				resourceUserModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			resourceUserModelImpl, false);

		args = new Object[] {
				resourceUserModelImpl.getGroupId(),
				resourceUserModelImpl.getClassName(),
				resourceUserModelImpl.getClassPK(),
				resourceUserModelImpl.getToUserId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_TOUSERID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_TOUSERID,
			args, resourceUserModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ResourceUserModelImpl resourceUserModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					resourceUserModelImpl.getUuid(),
					resourceUserModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((resourceUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					resourceUserModelImpl.getOriginalUuid(),
					resourceUserModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					resourceUserModelImpl.getGroupId(),
					resourceUserModelImpl.getClassName(),
					resourceUserModelImpl.getClassPK(),
					resourceUserModelImpl.getToUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_TOUSERID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_TOUSERID,
				args);
		}

		if ((resourceUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_TOUSERID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					resourceUserModelImpl.getOriginalGroupId(),
					resourceUserModelImpl.getOriginalClassName(),
					resourceUserModelImpl.getOriginalClassPK(),
					resourceUserModelImpl.getOriginalToUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK_TOUSERID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_CLASSNAME_CLASSPK_TOUSERID,
				args);
		}
	}

	/**
	 * Creates a new resource user with the primary key. Does not add the resource user to the database.
	 *
	 * @param resourceUserId the primary key for the new resource user
	 * @return the new resource user
	 */
	@Override
	public ResourceUser create(long resourceUserId) {
		ResourceUser resourceUser = new ResourceUserImpl();

		resourceUser.setNew(true);
		resourceUser.setPrimaryKey(resourceUserId);

		String uuid = PortalUUIDUtil.generate();

		resourceUser.setUuid(uuid);

		resourceUser.setCompanyId(companyProvider.getCompanyId());

		return resourceUser;
	}

	/**
	 * Removes the resource user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resourceUserId the primary key of the resource user
	 * @return the resource user that was removed
	 * @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	 */
	@Override
	public ResourceUser remove(long resourceUserId)
		throws NoSuchResourceUserException {
		return remove((Serializable)resourceUserId);
	}

	/**
	 * Removes the resource user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the resource user
	 * @return the resource user that was removed
	 * @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	 */
	@Override
	public ResourceUser remove(Serializable primaryKey)
		throws NoSuchResourceUserException {
		Session session = null;

		try {
			session = openSession();

			ResourceUser resourceUser = (ResourceUser)session.get(ResourceUserImpl.class,
					primaryKey);

			if (resourceUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResourceUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(resourceUser);
		}
		catch (NoSuchResourceUserException nsee) {
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
	protected ResourceUser removeImpl(ResourceUser resourceUser) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(resourceUser)) {
				resourceUser = (ResourceUser)session.get(ResourceUserImpl.class,
						resourceUser.getPrimaryKeyObj());
			}

			if (resourceUser != null) {
				session.delete(resourceUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (resourceUser != null) {
			clearCache(resourceUser);
		}

		return resourceUser;
	}

	@Override
	public ResourceUser updateImpl(ResourceUser resourceUser) {
		boolean isNew = resourceUser.isNew();

		if (!(resourceUser instanceof ResourceUserModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(resourceUser.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(resourceUser);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in resourceUser proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ResourceUser implementation " +
				resourceUser.getClass());
		}

		ResourceUserModelImpl resourceUserModelImpl = (ResourceUserModelImpl)resourceUser;

		if (Validator.isNull(resourceUser.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			resourceUser.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (resourceUser.getCreateDate() == null)) {
			if (serviceContext == null) {
				resourceUser.setCreateDate(now);
			}
			else {
				resourceUser.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!resourceUserModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				resourceUser.setModifiedDate(now);
			}
			else {
				resourceUser.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (resourceUser.isNew()) {
				session.save(resourceUser);

				resourceUser.setNew(false);
			}
			else {
				resourceUser = (ResourceUser)session.merge(resourceUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ResourceUserModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { resourceUserModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					resourceUserModelImpl.getUuid(),
					resourceUserModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					resourceUserModelImpl.getGroupId(),
					resourceUserModelImpl.getClassName(),
					resourceUserModelImpl.getClassPK()
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
			if ((resourceUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourceUserModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { resourceUserModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((resourceUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourceUserModelImpl.getOriginalUuid(),
						resourceUserModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						resourceUserModelImpl.getUuid(),
						resourceUserModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((resourceUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						resourceUserModelImpl.getOriginalGroupId(),
						resourceUserModelImpl.getOriginalClassName(),
						resourceUserModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK,
					args);

				args = new Object[] {
						resourceUserModelImpl.getGroupId(),
						resourceUserModelImpl.getClassName(),
						resourceUserModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_CLASSNAME_CLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_CLASSNAME_CLASSPK,
					args);
			}
		}

		entityCache.putResult(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
			ResourceUserImpl.class, resourceUser.getPrimaryKey(), resourceUser,
			false);

		clearUniqueFindersCache(resourceUserModelImpl, false);
		cacheUniqueFindersCache(resourceUserModelImpl);

		resourceUser.resetOriginalValues();

		return resourceUser;
	}

	/**
	 * Returns the resource user with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the resource user
	 * @return the resource user
	 * @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	 */
	@Override
	public ResourceUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchResourceUserException {
		ResourceUser resourceUser = fetchByPrimaryKey(primaryKey);

		if (resourceUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchResourceUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return resourceUser;
	}

	/**
	 * Returns the resource user with the primary key or throws a {@link NoSuchResourceUserException} if it could not be found.
	 *
	 * @param resourceUserId the primary key of the resource user
	 * @return the resource user
	 * @throws NoSuchResourceUserException if a resource user with the primary key could not be found
	 */
	@Override
	public ResourceUser findByPrimaryKey(long resourceUserId)
		throws NoSuchResourceUserException {
		return findByPrimaryKey((Serializable)resourceUserId);
	}

	/**
	 * Returns the resource user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the resource user
	 * @return the resource user, or <code>null</code> if a resource user with the primary key could not be found
	 */
	@Override
	public ResourceUser fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
				ResourceUserImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ResourceUser resourceUser = (ResourceUser)serializable;

		if (resourceUser == null) {
			Session session = null;

			try {
				session = openSession();

				resourceUser = (ResourceUser)session.get(ResourceUserImpl.class,
						primaryKey);

				if (resourceUser != null) {
					cacheResult(resourceUser);
				}
				else {
					entityCache.putResult(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
						ResourceUserImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
					ResourceUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return resourceUser;
	}

	/**
	 * Returns the resource user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param resourceUserId the primary key of the resource user
	 * @return the resource user, or <code>null</code> if a resource user with the primary key could not be found
	 */
	@Override
	public ResourceUser fetchByPrimaryKey(long resourceUserId) {
		return fetchByPrimaryKey((Serializable)resourceUserId);
	}

	@Override
	public Map<Serializable, ResourceUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ResourceUser> map = new HashMap<Serializable, ResourceUser>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ResourceUser resourceUser = fetchByPrimaryKey(primaryKey);

			if (resourceUser != null) {
				map.put(primaryKey, resourceUser);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
					ResourceUserImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ResourceUser)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RESOURCEUSER_WHERE_PKS_IN);

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

			for (ResourceUser resourceUser : (List<ResourceUser>)q.list()) {
				map.put(resourceUser.getPrimaryKeyObj(), resourceUser);

				cacheResult(resourceUser);

				uncachedPrimaryKeys.remove(resourceUser.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ResourceUserModelImpl.ENTITY_CACHE_ENABLED,
					ResourceUserImpl.class, primaryKey, nullModel);
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
	 * Returns all the resource users.
	 *
	 * @return the resource users
	 */
	@Override
	public List<ResourceUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the resource users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @return the range of resource users
	 */
	@Override
	public List<ResourceUser> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the resource users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of resource users
	 */
	@Override
	public List<ResourceUser> findAll(int start, int end,
		OrderByComparator<ResourceUser> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the resource users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource users
	 * @param end the upper bound of the range of resource users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of resource users
	 */
	@Override
	public List<ResourceUser> findAll(int start, int end,
		OrderByComparator<ResourceUser> orderByComparator,
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

		List<ResourceUser> list = null;

		if (retrieveFromCache) {
			list = (List<ResourceUser>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RESOURCEUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESOURCEUSER;

				if (pagination) {
					sql = sql.concat(ResourceUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ResourceUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ResourceUser>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the resource users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ResourceUser resourceUser : findAll()) {
			remove(resourceUser);
		}
	}

	/**
	 * Returns the number of resource users.
	 *
	 * @return the number of resource users
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RESOURCEUSER);

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
		return ResourceUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the resource user persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ResourceUserImpl.class.getName());
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
	private static final String _SQL_SELECT_RESOURCEUSER = "SELECT resourceUser FROM ResourceUser resourceUser";
	private static final String _SQL_SELECT_RESOURCEUSER_WHERE_PKS_IN = "SELECT resourceUser FROM ResourceUser resourceUser WHERE resourceUserId IN (";
	private static final String _SQL_SELECT_RESOURCEUSER_WHERE = "SELECT resourceUser FROM ResourceUser resourceUser WHERE ";
	private static final String _SQL_COUNT_RESOURCEUSER = "SELECT COUNT(resourceUser) FROM ResourceUser resourceUser";
	private static final String _SQL_COUNT_RESOURCEUSER_WHERE = "SELECT COUNT(resourceUser) FROM ResourceUser resourceUser WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resourceUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResourceUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResourceUser exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ResourceUserPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}