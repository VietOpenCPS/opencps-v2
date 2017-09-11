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

package org.opencps.datamgt.service.persistence.impl;

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

import org.opencps.datamgt.exception.NoSuchWorkspaceUserException;
import org.opencps.datamgt.model.WorkspaceUser;
import org.opencps.datamgt.model.impl.WorkspaceUserImpl;
import org.opencps.datamgt.model.impl.WorkspaceUserModelImpl;
import org.opencps.datamgt.service.persistence.WorkspaceUserPersistence;

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
 * The persistence implementation for the workspace user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see WorkspaceUserPersistence
 * @see org.opencps.datamgt.service.persistence.WorkspaceUserUtil
 * @generated
 */
@ProviderType
public class WorkspaceUserPersistenceImpl extends BasePersistenceImpl<WorkspaceUser>
	implements WorkspaceUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkspaceUserUtil} to access the workspace user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkspaceUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED,
			WorkspaceUserImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED,
			WorkspaceUserImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED,
			WorkspaceUserImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED,
			WorkspaceUserImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			WorkspaceUserModelImpl.UUID_COLUMN_BITMASK |
			WorkspaceUserModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the workspace users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workspace users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @return the range of matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workspace users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workspace users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<WorkspaceUser> orderByComparator,
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

		List<WorkspaceUser> list = null;

		if (retrieveFromCache) {
			list = (List<WorkspaceUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkspaceUser workspaceUser : list) {
					if (!Objects.equals(uuid, workspaceUser.getUuid())) {
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

			query.append(_SQL_SELECT_WORKSPACEUSER_WHERE);

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
				query.append(WorkspaceUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<WorkspaceUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkspaceUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first workspace user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workspace user
	 * @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser findByUuid_First(String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = fetchByUuid_First(uuid, orderByComparator);

		if (workspaceUser != null) {
			return workspaceUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkspaceUserException(msg.toString());
	}

	/**
	 * Returns the first workspace user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workspace user, or <code>null</code> if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser fetchByUuid_First(String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		List<WorkspaceUser> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workspace user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workspace user
	 * @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser findByUuid_Last(String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = fetchByUuid_Last(uuid, orderByComparator);

		if (workspaceUser != null) {
			return workspaceUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkspaceUserException(msg.toString());
	}

	/**
	 * Returns the last workspace user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workspace user, or <code>null</code> if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser fetchByUuid_Last(String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<WorkspaceUser> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workspace users before and after the current workspace user in the ordered set where uuid = &#63;.
	 *
	 * @param workspaceUserId the primary key of the current workspace user
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workspace user
	 * @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	 */
	@Override
	public WorkspaceUser[] findByUuid_PrevAndNext(long workspaceUserId,
		String uuid, OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = findByPrimaryKey(workspaceUserId);

		Session session = null;

		try {
			session = openSession();

			WorkspaceUser[] array = new WorkspaceUserImpl[3];

			array[0] = getByUuid_PrevAndNext(session, workspaceUser, uuid,
					orderByComparator, true);

			array[1] = workspaceUser;

			array[2] = getByUuid_PrevAndNext(session, workspaceUser, uuid,
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

	protected WorkspaceUser getByUuid_PrevAndNext(Session session,
		WorkspaceUser workspaceUser, String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKSPACEUSER_WHERE);

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
			query.append(WorkspaceUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(workspaceUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkspaceUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workspace users where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (WorkspaceUser workspaceUser : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(workspaceUser);
		}
	}

	/**
	 * Returns the number of workspace users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching workspace users
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WORKSPACEUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "workspaceUser.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "workspaceUser.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(workspaceUser.uuid IS NULL OR workspaceUser.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED,
			WorkspaceUserImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			WorkspaceUserModelImpl.UUID_COLUMN_BITMASK |
			WorkspaceUserModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the workspace user where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkspaceUserException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching workspace user
	 * @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser findByUUID_G(String uuid, long groupId)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = fetchByUUID_G(uuid, groupId);

		if (workspaceUser == null) {
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

			throw new NoSuchWorkspaceUserException(msg.toString());
		}

		return workspaceUser;
	}

	/**
	 * Returns the workspace user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching workspace user, or <code>null</code> if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the workspace user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching workspace user, or <code>null</code> if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof WorkspaceUser) {
			WorkspaceUser workspaceUser = (WorkspaceUser)result;

			if (!Objects.equals(uuid, workspaceUser.getUuid()) ||
					(groupId != workspaceUser.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WORKSPACEUSER_WHERE);

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

				List<WorkspaceUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					WorkspaceUser workspaceUser = list.get(0);

					result = workspaceUser;

					cacheResult(workspaceUser);

					if ((workspaceUser.getUuid() == null) ||
							!workspaceUser.getUuid().equals(uuid) ||
							(workspaceUser.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, workspaceUser);
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
			return (WorkspaceUser)result;
		}
	}

	/**
	 * Removes the workspace user where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the workspace user that was removed
	 */
	@Override
	public WorkspaceUser removeByUUID_G(String uuid, long groupId)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = findByUUID_G(uuid, groupId);

		return remove(workspaceUser);
	}

	/**
	 * Returns the number of workspace users where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching workspace users
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKSPACEUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "workspaceUser.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "workspaceUser.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(workspaceUser.uuid IS NULL OR workspaceUser.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "workspaceUser.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED,
			WorkspaceUserImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED,
			WorkspaceUserImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			WorkspaceUserModelImpl.UUID_COLUMN_BITMASK |
			WorkspaceUserModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkspaceUserModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the workspace users where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workspace users where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @return the range of matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workspace users where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WorkspaceUser> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workspace users where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WorkspaceUser> orderByComparator,
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

		List<WorkspaceUser> list = null;

		if (retrieveFromCache) {
			list = (List<WorkspaceUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkspaceUser workspaceUser : list) {
					if (!Objects.equals(uuid, workspaceUser.getUuid()) ||
							(companyId != workspaceUser.getCompanyId())) {
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

			query.append(_SQL_SELECT_WORKSPACEUSER_WHERE);

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
				query.append(WorkspaceUserModelImpl.ORDER_BY_JPQL);
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
					list = (List<WorkspaceUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkspaceUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workspace user
	 * @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (workspaceUser != null) {
			return workspaceUser;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkspaceUserException(msg.toString());
	}

	/**
	 * Returns the first workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workspace user, or <code>null</code> if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		List<WorkspaceUser> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workspace user
	 * @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (workspaceUser != null) {
			return workspaceUser;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkspaceUserException(msg.toString());
	}

	/**
	 * Returns the last workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workspace user, or <code>null</code> if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<WorkspaceUser> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workspace users before and after the current workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param workspaceUserId the primary key of the current workspace user
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workspace user
	 * @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	 */
	@Override
	public WorkspaceUser[] findByUuid_C_PrevAndNext(long workspaceUserId,
		String uuid, long companyId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = findByPrimaryKey(workspaceUserId);

		Session session = null;

		try {
			session = openSession();

			WorkspaceUser[] array = new WorkspaceUserImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, workspaceUser, uuid,
					companyId, orderByComparator, true);

			array[1] = workspaceUser;

			array[2] = getByUuid_C_PrevAndNext(session, workspaceUser, uuid,
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

	protected WorkspaceUser getByUuid_C_PrevAndNext(Session session,
		WorkspaceUser workspaceUser, String uuid, long companyId,
		OrderByComparator<WorkspaceUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_WORKSPACEUSER_WHERE);

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
			query.append(WorkspaceUserModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(workspaceUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkspaceUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workspace users where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (WorkspaceUser workspaceUser : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workspaceUser);
		}
	}

	/**
	 * Returns the number of workspace users where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching workspace users
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WORKSPACEUSER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "workspaceUser.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "workspaceUser.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(workspaceUser.uuid IS NULL OR workspaceUser.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "workspaceUser.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_WORKSPACEID =
		new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED,
			WorkspaceUserImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_workspaceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKSPACEID =
		new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED,
			WorkspaceUserImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_workspaceId", new String[] { Long.class.getName() },
			WorkspaceUserModelImpl.WORKSPACEID_COLUMN_BITMASK |
			WorkspaceUserModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_WORKSPACEID = new FinderPath(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_workspaceId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the workspace users where workspaceId = &#63;.
	 *
	 * @param workspaceId the workspace ID
	 * @return the matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByF_workspaceId(long workspaceId) {
		return findByF_workspaceId(workspaceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workspace users where workspaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workspaceId the workspace ID
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @return the range of matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByF_workspaceId(long workspaceId, int start,
		int end) {
		return findByF_workspaceId(workspaceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workspace users where workspaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workspaceId the workspace ID
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByF_workspaceId(long workspaceId, int start,
		int end, OrderByComparator<WorkspaceUser> orderByComparator) {
		return findByF_workspaceId(workspaceId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the workspace users where workspaceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param workspaceId the workspace ID
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching workspace users
	 */
	@Override
	public List<WorkspaceUser> findByF_workspaceId(long workspaceId, int start,
		int end, OrderByComparator<WorkspaceUser> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKSPACEID;
			finderArgs = new Object[] { workspaceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_WORKSPACEID;
			finderArgs = new Object[] { workspaceId, start, end, orderByComparator };
		}

		List<WorkspaceUser> list = null;

		if (retrieveFromCache) {
			list = (List<WorkspaceUser>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WorkspaceUser workspaceUser : list) {
					if ((workspaceId != workspaceUser.getWorkspaceId())) {
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

			query.append(_SQL_SELECT_WORKSPACEUSER_WHERE);

			query.append(_FINDER_COLUMN_F_WORKSPACEID_WORKSPACEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WorkspaceUserModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workspaceId);

				if (!pagination) {
					list = (List<WorkspaceUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkspaceUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first workspace user in the ordered set where workspaceId = &#63;.
	 *
	 * @param workspaceId the workspace ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workspace user
	 * @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser findByF_workspaceId_First(long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = fetchByF_workspaceId_First(workspaceId,
				orderByComparator);

		if (workspaceUser != null) {
			return workspaceUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("workspaceId=");
		msg.append(workspaceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkspaceUserException(msg.toString());
	}

	/**
	 * Returns the first workspace user in the ordered set where workspaceId = &#63;.
	 *
	 * @param workspaceId the workspace ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workspace user, or <code>null</code> if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser fetchByF_workspaceId_First(long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		List<WorkspaceUser> list = findByF_workspaceId(workspaceId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last workspace user in the ordered set where workspaceId = &#63;.
	 *
	 * @param workspaceId the workspace ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workspace user
	 * @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser findByF_workspaceId_Last(long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = fetchByF_workspaceId_Last(workspaceId,
				orderByComparator);

		if (workspaceUser != null) {
			return workspaceUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("workspaceId=");
		msg.append(workspaceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWorkspaceUserException(msg.toString());
	}

	/**
	 * Returns the last workspace user in the ordered set where workspaceId = &#63;.
	 *
	 * @param workspaceId the workspace ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workspace user, or <code>null</code> if a matching workspace user could not be found
	 */
	@Override
	public WorkspaceUser fetchByF_workspaceId_Last(long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		int count = countByF_workspaceId(workspaceId);

		if (count == 0) {
			return null;
		}

		List<WorkspaceUser> list = findByF_workspaceId(workspaceId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the workspace users before and after the current workspace user in the ordered set where workspaceId = &#63;.
	 *
	 * @param workspaceUserId the primary key of the current workspace user
	 * @param workspaceId the workspace ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workspace user
	 * @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	 */
	@Override
	public WorkspaceUser[] findByF_workspaceId_PrevAndNext(
		long workspaceUserId, long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = findByPrimaryKey(workspaceUserId);

		Session session = null;

		try {
			session = openSession();

			WorkspaceUser[] array = new WorkspaceUserImpl[3];

			array[0] = getByF_workspaceId_PrevAndNext(session, workspaceUser,
					workspaceId, orderByComparator, true);

			array[1] = workspaceUser;

			array[2] = getByF_workspaceId_PrevAndNext(session, workspaceUser,
					workspaceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkspaceUser getByF_workspaceId_PrevAndNext(Session session,
		WorkspaceUser workspaceUser, long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKSPACEUSER_WHERE);

		query.append(_FINDER_COLUMN_F_WORKSPACEID_WORKSPACEID_2);

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
			query.append(WorkspaceUserModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(workspaceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workspaceUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkspaceUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the workspace users where workspaceId = &#63; from the database.
	 *
	 * @param workspaceId the workspace ID
	 */
	@Override
	public void removeByF_workspaceId(long workspaceId) {
		for (WorkspaceUser workspaceUser : findByF_workspaceId(workspaceId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(workspaceUser);
		}
	}

	/**
	 * Returns the number of workspace users where workspaceId = &#63;.
	 *
	 * @param workspaceId the workspace ID
	 * @return the number of matching workspace users
	 */
	@Override
	public int countByF_workspaceId(long workspaceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_WORKSPACEID;

		Object[] finderArgs = new Object[] { workspaceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WORKSPACEUSER_WHERE);

			query.append(_FINDER_COLUMN_F_WORKSPACEID_WORKSPACEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workspaceId);

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

	private static final String _FINDER_COLUMN_F_WORKSPACEID_WORKSPACEID_2 = "workspaceUser.workspaceId = ?";

	public WorkspaceUserPersistenceImpl() {
		setModelClass(WorkspaceUser.class);
	}

	/**
	 * Caches the workspace user in the entity cache if it is enabled.
	 *
	 * @param workspaceUser the workspace user
	 */
	@Override
	public void cacheResult(WorkspaceUser workspaceUser) {
		entityCache.putResult(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserImpl.class, workspaceUser.getPrimaryKey(),
			workspaceUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { workspaceUser.getUuid(), workspaceUser.getGroupId() },
			workspaceUser);

		workspaceUser.resetOriginalValues();
	}

	/**
	 * Caches the workspace users in the entity cache if it is enabled.
	 *
	 * @param workspaceUsers the workspace users
	 */
	@Override
	public void cacheResult(List<WorkspaceUser> workspaceUsers) {
		for (WorkspaceUser workspaceUser : workspaceUsers) {
			if (entityCache.getResult(
						WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
						WorkspaceUserImpl.class, workspaceUser.getPrimaryKey()) == null) {
				cacheResult(workspaceUser);
			}
			else {
				workspaceUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all workspace users.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WorkspaceUserImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the workspace user.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkspaceUser workspaceUser) {
		entityCache.removeResult(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserImpl.class, workspaceUser.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((WorkspaceUserModelImpl)workspaceUser, true);
	}

	@Override
	public void clearCache(List<WorkspaceUser> workspaceUsers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkspaceUser workspaceUser : workspaceUsers) {
			entityCache.removeResult(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
				WorkspaceUserImpl.class, workspaceUser.getPrimaryKey());

			clearUniqueFindersCache((WorkspaceUserModelImpl)workspaceUser, true);
		}
	}

	protected void cacheUniqueFindersCache(
		WorkspaceUserModelImpl workspaceUserModelImpl) {
		Object[] args = new Object[] {
				workspaceUserModelImpl.getUuid(),
				workspaceUserModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			workspaceUserModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		WorkspaceUserModelImpl workspaceUserModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					workspaceUserModelImpl.getUuid(),
					workspaceUserModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((workspaceUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					workspaceUserModelImpl.getOriginalUuid(),
					workspaceUserModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new workspace user with the primary key. Does not add the workspace user to the database.
	 *
	 * @param workspaceUserId the primary key for the new workspace user
	 * @return the new workspace user
	 */
	@Override
	public WorkspaceUser create(long workspaceUserId) {
		WorkspaceUser workspaceUser = new WorkspaceUserImpl();

		workspaceUser.setNew(true);
		workspaceUser.setPrimaryKey(workspaceUserId);

		String uuid = PortalUUIDUtil.generate();

		workspaceUser.setUuid(uuid);

		workspaceUser.setCompanyId(companyProvider.getCompanyId());

		return workspaceUser;
	}

	/**
	 * Removes the workspace user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workspaceUserId the primary key of the workspace user
	 * @return the workspace user that was removed
	 * @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	 */
	@Override
	public WorkspaceUser remove(long workspaceUserId)
		throws NoSuchWorkspaceUserException {
		return remove((Serializable)workspaceUserId);
	}

	/**
	 * Removes the workspace user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the workspace user
	 * @return the workspace user that was removed
	 * @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	 */
	@Override
	public WorkspaceUser remove(Serializable primaryKey)
		throws NoSuchWorkspaceUserException {
		Session session = null;

		try {
			session = openSession();

			WorkspaceUser workspaceUser = (WorkspaceUser)session.get(WorkspaceUserImpl.class,
					primaryKey);

			if (workspaceUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkspaceUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workspaceUser);
		}
		catch (NoSuchWorkspaceUserException nsee) {
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
	protected WorkspaceUser removeImpl(WorkspaceUser workspaceUser) {
		workspaceUser = toUnwrappedModel(workspaceUser);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(workspaceUser)) {
				workspaceUser = (WorkspaceUser)session.get(WorkspaceUserImpl.class,
						workspaceUser.getPrimaryKeyObj());
			}

			if (workspaceUser != null) {
				session.delete(workspaceUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (workspaceUser != null) {
			clearCache(workspaceUser);
		}

		return workspaceUser;
	}

	@Override
	public WorkspaceUser updateImpl(WorkspaceUser workspaceUser) {
		workspaceUser = toUnwrappedModel(workspaceUser);

		boolean isNew = workspaceUser.isNew();

		WorkspaceUserModelImpl workspaceUserModelImpl = (WorkspaceUserModelImpl)workspaceUser;

		if (Validator.isNull(workspaceUser.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			workspaceUser.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (workspaceUser.getCreateDate() == null)) {
			if (serviceContext == null) {
				workspaceUser.setCreateDate(now);
			}
			else {
				workspaceUser.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!workspaceUserModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				workspaceUser.setModifiedDate(now);
			}
			else {
				workspaceUser.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (workspaceUser.isNew()) {
				session.save(workspaceUser);

				workspaceUser.setNew(false);
			}
			else {
				workspaceUser = (WorkspaceUser)session.merge(workspaceUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WorkspaceUserModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((workspaceUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workspaceUserModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { workspaceUserModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((workspaceUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workspaceUserModelImpl.getOriginalUuid(),
						workspaceUserModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						workspaceUserModelImpl.getUuid(),
						workspaceUserModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((workspaceUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKSPACEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						workspaceUserModelImpl.getOriginalWorkspaceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_WORKSPACEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKSPACEID,
					args);

				args = new Object[] { workspaceUserModelImpl.getWorkspaceId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_WORKSPACEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_WORKSPACEID,
					args);
			}
		}

		entityCache.putResult(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
			WorkspaceUserImpl.class, workspaceUser.getPrimaryKey(),
			workspaceUser, false);

		clearUniqueFindersCache(workspaceUserModelImpl, false);
		cacheUniqueFindersCache(workspaceUserModelImpl);

		workspaceUser.resetOriginalValues();

		return workspaceUser;
	}

	protected WorkspaceUser toUnwrappedModel(WorkspaceUser workspaceUser) {
		if (workspaceUser instanceof WorkspaceUserImpl) {
			return workspaceUser;
		}

		WorkspaceUserImpl workspaceUserImpl = new WorkspaceUserImpl();

		workspaceUserImpl.setNew(workspaceUser.isNew());
		workspaceUserImpl.setPrimaryKey(workspaceUser.getPrimaryKey());

		workspaceUserImpl.setUuid(workspaceUser.getUuid());
		workspaceUserImpl.setWorkspaceUserId(workspaceUser.getWorkspaceUserId());
		workspaceUserImpl.setCompanyId(workspaceUser.getCompanyId());
		workspaceUserImpl.setGroupId(workspaceUser.getGroupId());
		workspaceUserImpl.setUserId(workspaceUser.getUserId());
		workspaceUserImpl.setUserName(workspaceUser.getUserName());
		workspaceUserImpl.setCreateDate(workspaceUser.getCreateDate());
		workspaceUserImpl.setModifiedDate(workspaceUser.getModifiedDate());
		workspaceUserImpl.setWorkspaceId(workspaceUser.getWorkspaceId());
		workspaceUserImpl.setAssignUserId(workspaceUser.getAssignUserId());

		return workspaceUserImpl;
	}

	/**
	 * Returns the workspace user with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the workspace user
	 * @return the workspace user
	 * @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	 */
	@Override
	public WorkspaceUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorkspaceUserException {
		WorkspaceUser workspaceUser = fetchByPrimaryKey(primaryKey);

		if (workspaceUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorkspaceUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return workspaceUser;
	}

	/**
	 * Returns the workspace user with the primary key or throws a {@link NoSuchWorkspaceUserException} if it could not be found.
	 *
	 * @param workspaceUserId the primary key of the workspace user
	 * @return the workspace user
	 * @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	 */
	@Override
	public WorkspaceUser findByPrimaryKey(long workspaceUserId)
		throws NoSuchWorkspaceUserException {
		return findByPrimaryKey((Serializable)workspaceUserId);
	}

	/**
	 * Returns the workspace user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the workspace user
	 * @return the workspace user, or <code>null</code> if a workspace user with the primary key could not be found
	 */
	@Override
	public WorkspaceUser fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
				WorkspaceUserImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WorkspaceUser workspaceUser = (WorkspaceUser)serializable;

		if (workspaceUser == null) {
			Session session = null;

			try {
				session = openSession();

				workspaceUser = (WorkspaceUser)session.get(WorkspaceUserImpl.class,
						primaryKey);

				if (workspaceUser != null) {
					cacheResult(workspaceUser);
				}
				else {
					entityCache.putResult(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
						WorkspaceUserImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
					WorkspaceUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return workspaceUser;
	}

	/**
	 * Returns the workspace user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workspaceUserId the primary key of the workspace user
	 * @return the workspace user, or <code>null</code> if a workspace user with the primary key could not be found
	 */
	@Override
	public WorkspaceUser fetchByPrimaryKey(long workspaceUserId) {
		return fetchByPrimaryKey((Serializable)workspaceUserId);
	}

	@Override
	public Map<Serializable, WorkspaceUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WorkspaceUser> map = new HashMap<Serializable, WorkspaceUser>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WorkspaceUser workspaceUser = fetchByPrimaryKey(primaryKey);

			if (workspaceUser != null) {
				map.put(primaryKey, workspaceUser);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
					WorkspaceUserImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WorkspaceUser)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WORKSPACEUSER_WHERE_PKS_IN);

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

			for (WorkspaceUser workspaceUser : (List<WorkspaceUser>)q.list()) {
				map.put(workspaceUser.getPrimaryKeyObj(), workspaceUser);

				cacheResult(workspaceUser);

				uncachedPrimaryKeys.remove(workspaceUser.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WorkspaceUserModelImpl.ENTITY_CACHE_ENABLED,
					WorkspaceUserImpl.class, primaryKey, nullModel);
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
	 * Returns all the workspace users.
	 *
	 * @return the workspace users
	 */
	@Override
	public List<WorkspaceUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workspace users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @return the range of workspace users
	 */
	@Override
	public List<WorkspaceUser> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the workspace users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workspace users
	 */
	@Override
	public List<WorkspaceUser> findAll(int start, int end,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the workspace users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of workspace users
	 * @param end the upper bound of the range of workspace users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of workspace users
	 */
	@Override
	public List<WorkspaceUser> findAll(int start, int end,
		OrderByComparator<WorkspaceUser> orderByComparator,
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

		List<WorkspaceUser> list = null;

		if (retrieveFromCache) {
			list = (List<WorkspaceUser>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WORKSPACEUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKSPACEUSER;

				if (pagination) {
					sql = sql.concat(WorkspaceUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorkspaceUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WorkspaceUser>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the workspace users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WorkspaceUser workspaceUser : findAll()) {
			remove(workspaceUser);
		}
	}

	/**
	 * Returns the number of workspace users.
	 *
	 * @return the number of workspace users
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORKSPACEUSER);

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
		return WorkspaceUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the workspace user persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WorkspaceUserImpl.class.getName());
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
	private static final String _SQL_SELECT_WORKSPACEUSER = "SELECT workspaceUser FROM WorkspaceUser workspaceUser";
	private static final String _SQL_SELECT_WORKSPACEUSER_WHERE_PKS_IN = "SELECT workspaceUser FROM WorkspaceUser workspaceUser WHERE workspaceUserId IN (";
	private static final String _SQL_SELECT_WORKSPACEUSER_WHERE = "SELECT workspaceUser FROM WorkspaceUser workspaceUser WHERE ";
	private static final String _SQL_COUNT_WORKSPACEUSER = "SELECT COUNT(workspaceUser) FROM WorkspaceUser workspaceUser";
	private static final String _SQL_COUNT_WORKSPACEUSER_WHERE = "SELECT COUNT(workspaceUser) FROM WorkspaceUser workspaceUser WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workspaceUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkspaceUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WorkspaceUser exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(WorkspaceUserPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}