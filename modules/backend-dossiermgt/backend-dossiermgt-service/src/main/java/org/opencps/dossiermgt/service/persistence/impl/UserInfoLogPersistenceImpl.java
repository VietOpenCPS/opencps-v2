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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchUserInfoLogException;
import org.opencps.dossiermgt.model.UserInfoLog;
import org.opencps.dossiermgt.model.impl.UserInfoLogImpl;
import org.opencps.dossiermgt.model.impl.UserInfoLogModelImpl;
import org.opencps.dossiermgt.service.persistence.UserInfoLogPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the user info log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see UserInfoLogPersistence
 * @see org.opencps.dossiermgt.service.persistence.UserInfoLogUtil
 * @generated
 */
@ProviderType
public class UserInfoLogPersistenceImpl extends BasePersistenceImpl<UserInfoLog>
	implements UserInfoLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserInfoLogUtil} to access the user info log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserInfoLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogModelImpl.FINDER_CACHE_ENABLED, UserInfoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogModelImpl.FINDER_CACHE_ENABLED, UserInfoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogModelImpl.FINDER_CACHE_ENABLED, UserInfoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogModelImpl.FINDER_CACHE_ENABLED, UserInfoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			UserInfoLogModelImpl.UUID_COLUMN_BITMASK |
			UserInfoLogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the user info logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user info logs
	 */
	@Override
	public List<UserInfoLog> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user info logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user info logs
	 * @param end the upper bound of the range of user info logs (not inclusive)
	 * @return the range of matching user info logs
	 */
	@Override
	public List<UserInfoLog> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user info logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user info logs
	 * @param end the upper bound of the range of user info logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user info logs
	 */
	@Override
	public List<UserInfoLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user info logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user info logs
	 * @param end the upper bound of the range of user info logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user info logs
	 */
	@Override
	public List<UserInfoLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator,
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

		List<UserInfoLog> list = null;

		if (retrieveFromCache) {
			list = (List<UserInfoLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserInfoLog userInfoLog : list) {
					if (!Objects.equals(uuid, userInfoLog.getUuid())) {
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

			query.append(_SQL_SELECT_USERINFOLOG_WHERE);

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
				query.append(UserInfoLogModelImpl.ORDER_BY_JPQL);
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
					list = (List<UserInfoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserInfoLog>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first user info log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user info log
	 * @throws NoSuchUserInfoLogException if a matching user info log could not be found
	 */
	@Override
	public UserInfoLog findByUuid_First(String uuid,
		OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException {
		UserInfoLog userInfoLog = fetchByUuid_First(uuid, orderByComparator);

		if (userInfoLog != null) {
			return userInfoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchUserInfoLogException(msg.toString());
	}

	/**
	 * Returns the first user info log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user info log, or <code>null</code> if a matching user info log could not be found
	 */
	@Override
	public UserInfoLog fetchByUuid_First(String uuid,
		OrderByComparator<UserInfoLog> orderByComparator) {
		List<UserInfoLog> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user info log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user info log
	 * @throws NoSuchUserInfoLogException if a matching user info log could not be found
	 */
	@Override
	public UserInfoLog findByUuid_Last(String uuid,
		OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException {
		UserInfoLog userInfoLog = fetchByUuid_Last(uuid, orderByComparator);

		if (userInfoLog != null) {
			return userInfoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchUserInfoLogException(msg.toString());
	}

	/**
	 * Returns the last user info log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user info log, or <code>null</code> if a matching user info log could not be found
	 */
	@Override
	public UserInfoLog fetchByUuid_Last(String uuid,
		OrderByComparator<UserInfoLog> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<UserInfoLog> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user info logs before and after the current user info log in the ordered set where uuid = &#63;.
	 *
	 * @param userLogId the primary key of the current user info log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user info log
	 * @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	 */
	@Override
	public UserInfoLog[] findByUuid_PrevAndNext(long userLogId, String uuid,
		OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException {
		UserInfoLog userInfoLog = findByPrimaryKey(userLogId);

		Session session = null;

		try {
			session = openSession();

			UserInfoLog[] array = new UserInfoLogImpl[3];

			array[0] = getByUuid_PrevAndNext(session, userInfoLog, uuid,
					orderByComparator, true);

			array[1] = userInfoLog;

			array[2] = getByUuid_PrevAndNext(session, userInfoLog, uuid,
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

	protected UserInfoLog getByUuid_PrevAndNext(Session session,
		UserInfoLog userInfoLog, String uuid,
		OrderByComparator<UserInfoLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERINFOLOG_WHERE);

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
			query.append(UserInfoLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(userInfoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserInfoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user info logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (UserInfoLog userInfoLog : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(userInfoLog);
		}
	}

	/**
	 * Returns the number of user info logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user info logs
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERINFOLOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "userInfoLog.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "userInfoLog.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(userInfoLog.uuid IS NULL OR userInfoLog.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_USERID = new FinderPath(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogModelImpl.FINDER_CACHE_ENABLED, UserInfoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_USERID",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID =
		new FinderPath(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogModelImpl.FINDER_CACHE_ENABLED, UserInfoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_USERID",
			new String[] { Long.class.getName() },
			UserInfoLogModelImpl.USERID_COLUMN_BITMASK |
			UserInfoLogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_USERID = new FinderPath(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_USERID",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user info logs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching user info logs
	 */
	@Override
	public List<UserInfoLog> findByF_USERID(long userId) {
		return findByF_USERID(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user info logs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user info logs
	 * @param end the upper bound of the range of user info logs (not inclusive)
	 * @return the range of matching user info logs
	 */
	@Override
	public List<UserInfoLog> findByF_USERID(long userId, int start, int end) {
		return findByF_USERID(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user info logs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user info logs
	 * @param end the upper bound of the range of user info logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user info logs
	 */
	@Override
	public List<UserInfoLog> findByF_USERID(long userId, int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return findByF_USERID(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user info logs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user info logs
	 * @param end the upper bound of the range of user info logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching user info logs
	 */
	@Override
	public List<UserInfoLog> findByF_USERID(long userId, int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<UserInfoLog> list = null;

		if (retrieveFromCache) {
			list = (List<UserInfoLog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserInfoLog userInfoLog : list) {
					if ((userId != userInfoLog.getUserId())) {
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

			query.append(_SQL_SELECT_USERINFOLOG_WHERE);

			query.append(_FINDER_COLUMN_F_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserInfoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<UserInfoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserInfoLog>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first user info log in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user info log
	 * @throws NoSuchUserInfoLogException if a matching user info log could not be found
	 */
	@Override
	public UserInfoLog findByF_USERID_First(long userId,
		OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException {
		UserInfoLog userInfoLog = fetchByF_USERID_First(userId,
				orderByComparator);

		if (userInfoLog != null) {
			return userInfoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchUserInfoLogException(msg.toString());
	}

	/**
	 * Returns the first user info log in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user info log, or <code>null</code> if a matching user info log could not be found
	 */
	@Override
	public UserInfoLog fetchByF_USERID_First(long userId,
		OrderByComparator<UserInfoLog> orderByComparator) {
		List<UserInfoLog> list = findByF_USERID(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user info log in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user info log
	 * @throws NoSuchUserInfoLogException if a matching user info log could not be found
	 */
	@Override
	public UserInfoLog findByF_USERID_Last(long userId,
		OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException {
		UserInfoLog userInfoLog = fetchByF_USERID_Last(userId, orderByComparator);

		if (userInfoLog != null) {
			return userInfoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchUserInfoLogException(msg.toString());
	}

	/**
	 * Returns the last user info log in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user info log, or <code>null</code> if a matching user info log could not be found
	 */
	@Override
	public UserInfoLog fetchByF_USERID_Last(long userId,
		OrderByComparator<UserInfoLog> orderByComparator) {
		int count = countByF_USERID(userId);

		if (count == 0) {
			return null;
		}

		List<UserInfoLog> list = findByF_USERID(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user info logs before and after the current user info log in the ordered set where userId = &#63;.
	 *
	 * @param userLogId the primary key of the current user info log
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user info log
	 * @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	 */
	@Override
	public UserInfoLog[] findByF_USERID_PrevAndNext(long userLogId,
		long userId, OrderByComparator<UserInfoLog> orderByComparator)
		throws NoSuchUserInfoLogException {
		UserInfoLog userInfoLog = findByPrimaryKey(userLogId);

		Session session = null;

		try {
			session = openSession();

			UserInfoLog[] array = new UserInfoLogImpl[3];

			array[0] = getByF_USERID_PrevAndNext(session, userInfoLog, userId,
					orderByComparator, true);

			array[1] = userInfoLog;

			array[2] = getByF_USERID_PrevAndNext(session, userInfoLog, userId,
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

	protected UserInfoLog getByF_USERID_PrevAndNext(Session session,
		UserInfoLog userInfoLog, long userId,
		OrderByComparator<UserInfoLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERINFOLOG_WHERE);

		query.append(_FINDER_COLUMN_F_USERID_USERID_2);

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
			query.append(UserInfoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userInfoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserInfoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user info logs where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByF_USERID(long userId) {
		for (UserInfoLog userInfoLog : findByF_USERID(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userInfoLog);
		}
	}

	/**
	 * Returns the number of user info logs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user info logs
	 */
	@Override
	public int countByF_USERID(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERINFOLOG_WHERE);

			query.append(_FINDER_COLUMN_F_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_F_USERID_USERID_2 = "userInfoLog.userId = ?";

	public UserInfoLogPersistenceImpl() {
		setModelClass(UserInfoLog.class);

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
	 * Caches the user info log in the entity cache if it is enabled.
	 *
	 * @param userInfoLog the user info log
	 */
	@Override
	public void cacheResult(UserInfoLog userInfoLog) {
		entityCache.putResult(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogImpl.class, userInfoLog.getPrimaryKey(), userInfoLog);

		userInfoLog.resetOriginalValues();
	}

	/**
	 * Caches the user info logs in the entity cache if it is enabled.
	 *
	 * @param userInfoLogs the user info logs
	 */
	@Override
	public void cacheResult(List<UserInfoLog> userInfoLogs) {
		for (UserInfoLog userInfoLog : userInfoLogs) {
			if (entityCache.getResult(
						UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
						UserInfoLogImpl.class, userInfoLog.getPrimaryKey()) == null) {
				cacheResult(userInfoLog);
			}
			else {
				userInfoLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user info logs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserInfoLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user info log.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserInfoLog userInfoLog) {
		entityCache.removeResult(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogImpl.class, userInfoLog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UserInfoLog> userInfoLogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserInfoLog userInfoLog : userInfoLogs) {
			entityCache.removeResult(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
				UserInfoLogImpl.class, userInfoLog.getPrimaryKey());
		}
	}

	/**
	 * Creates a new user info log with the primary key. Does not add the user info log to the database.
	 *
	 * @param userLogId the primary key for the new user info log
	 * @return the new user info log
	 */
	@Override
	public UserInfoLog create(long userLogId) {
		UserInfoLog userInfoLog = new UserInfoLogImpl();

		userInfoLog.setNew(true);
		userInfoLog.setPrimaryKey(userLogId);

		String uuid = PortalUUIDUtil.generate();

		userInfoLog.setUuid(uuid);

		return userInfoLog;
	}

	/**
	 * Removes the user info log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userLogId the primary key of the user info log
	 * @return the user info log that was removed
	 * @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	 */
	@Override
	public UserInfoLog remove(long userLogId) throws NoSuchUserInfoLogException {
		return remove((Serializable)userLogId);
	}

	/**
	 * Removes the user info log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user info log
	 * @return the user info log that was removed
	 * @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	 */
	@Override
	public UserInfoLog remove(Serializable primaryKey)
		throws NoSuchUserInfoLogException {
		Session session = null;

		try {
			session = openSession();

			UserInfoLog userInfoLog = (UserInfoLog)session.get(UserInfoLogImpl.class,
					primaryKey);

			if (userInfoLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserInfoLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userInfoLog);
		}
		catch (NoSuchUserInfoLogException nsee) {
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
	protected UserInfoLog removeImpl(UserInfoLog userInfoLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userInfoLog)) {
				userInfoLog = (UserInfoLog)session.get(UserInfoLogImpl.class,
						userInfoLog.getPrimaryKeyObj());
			}

			if (userInfoLog != null) {
				session.delete(userInfoLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (userInfoLog != null) {
			clearCache(userInfoLog);
		}

		return userInfoLog;
	}

	@Override
	public UserInfoLog updateImpl(UserInfoLog userInfoLog) {
		boolean isNew = userInfoLog.isNew();

		if (!(userInfoLog instanceof UserInfoLogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userInfoLog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(userInfoLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userInfoLog proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserInfoLog implementation " +
				userInfoLog.getClass());
		}

		UserInfoLogModelImpl userInfoLogModelImpl = (UserInfoLogModelImpl)userInfoLog;

		if (Validator.isNull(userInfoLog.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			userInfoLog.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (userInfoLog.isNew()) {
				session.save(userInfoLog);

				userInfoLog.setNew(false);
			}
			else {
				userInfoLog = (UserInfoLog)session.merge(userInfoLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!UserInfoLogModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { userInfoLogModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { userInfoLogModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((userInfoLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userInfoLogModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { userInfoLogModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((userInfoLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userInfoLogModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID,
					args);

				args = new Object[] { userInfoLogModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_USERID,
					args);
			}
		}

		entityCache.putResult(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
			UserInfoLogImpl.class, userInfoLog.getPrimaryKey(), userInfoLog,
			false);

		userInfoLog.resetOriginalValues();

		return userInfoLog;
	}

	/**
	 * Returns the user info log with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user info log
	 * @return the user info log
	 * @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	 */
	@Override
	public UserInfoLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserInfoLogException {
		UserInfoLog userInfoLog = fetchByPrimaryKey(primaryKey);

		if (userInfoLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserInfoLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return userInfoLog;
	}

	/**
	 * Returns the user info log with the primary key or throws a {@link NoSuchUserInfoLogException} if it could not be found.
	 *
	 * @param userLogId the primary key of the user info log
	 * @return the user info log
	 * @throws NoSuchUserInfoLogException if a user info log with the primary key could not be found
	 */
	@Override
	public UserInfoLog findByPrimaryKey(long userLogId)
		throws NoSuchUserInfoLogException {
		return findByPrimaryKey((Serializable)userLogId);
	}

	/**
	 * Returns the user info log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user info log
	 * @return the user info log, or <code>null</code> if a user info log with the primary key could not be found
	 */
	@Override
	public UserInfoLog fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
				UserInfoLogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UserInfoLog userInfoLog = (UserInfoLog)serializable;

		if (userInfoLog == null) {
			Session session = null;

			try {
				session = openSession();

				userInfoLog = (UserInfoLog)session.get(UserInfoLogImpl.class,
						primaryKey);

				if (userInfoLog != null) {
					cacheResult(userInfoLog);
				}
				else {
					entityCache.putResult(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
						UserInfoLogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
					UserInfoLogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return userInfoLog;
	}

	/**
	 * Returns the user info log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userLogId the primary key of the user info log
	 * @return the user info log, or <code>null</code> if a user info log with the primary key could not be found
	 */
	@Override
	public UserInfoLog fetchByPrimaryKey(long userLogId) {
		return fetchByPrimaryKey((Serializable)userLogId);
	}

	@Override
	public Map<Serializable, UserInfoLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UserInfoLog> map = new HashMap<Serializable, UserInfoLog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			UserInfoLog userInfoLog = fetchByPrimaryKey(primaryKey);

			if (userInfoLog != null) {
				map.put(primaryKey, userInfoLog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
					UserInfoLogImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (UserInfoLog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_USERINFOLOG_WHERE_PKS_IN);

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

			for (UserInfoLog userInfoLog : (List<UserInfoLog>)q.list()) {
				map.put(userInfoLog.getPrimaryKeyObj(), userInfoLog);

				cacheResult(userInfoLog);

				uncachedPrimaryKeys.remove(userInfoLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(UserInfoLogModelImpl.ENTITY_CACHE_ENABLED,
					UserInfoLogImpl.class, primaryKey, nullModel);
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
	 * Returns all the user info logs.
	 *
	 * @return the user info logs
	 */
	@Override
	public List<UserInfoLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user info logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user info logs
	 * @param end the upper bound of the range of user info logs (not inclusive)
	 * @return the range of user info logs
	 */
	@Override
	public List<UserInfoLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user info logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user info logs
	 * @param end the upper bound of the range of user info logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user info logs
	 */
	@Override
	public List<UserInfoLog> findAll(int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the user info logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInfoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user info logs
	 * @param end the upper bound of the range of user info logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of user info logs
	 */
	@Override
	public List<UserInfoLog> findAll(int start, int end,
		OrderByComparator<UserInfoLog> orderByComparator,
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

		List<UserInfoLog> list = null;

		if (retrieveFromCache) {
			list = (List<UserInfoLog>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_USERINFOLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERINFOLOG;

				if (pagination) {
					sql = sql.concat(UserInfoLogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UserInfoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserInfoLog>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the user info logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserInfoLog userInfoLog : findAll()) {
			remove(userInfoLog);
		}
	}

	/**
	 * Returns the number of user info logs.
	 *
	 * @return the number of user info logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERINFOLOG);

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
		return UserInfoLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user info log persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UserInfoLogImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_USERINFOLOG = "SELECT userInfoLog FROM UserInfoLog userInfoLog";
	private static final String _SQL_SELECT_USERINFOLOG_WHERE_PKS_IN = "SELECT userInfoLog FROM UserInfoLog userInfoLog WHERE userLogId IN (";
	private static final String _SQL_SELECT_USERINFOLOG_WHERE = "SELECT userInfoLog FROM UserInfoLog userInfoLog WHERE ";
	private static final String _SQL_COUNT_USERINFOLOG = "SELECT COUNT(userInfoLog) FROM UserInfoLog userInfoLog";
	private static final String _SQL_COUNT_USERINFOLOG_WHERE = "SELECT COUNT(userInfoLog) FROM UserInfoLog userInfoLog WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userInfoLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserInfoLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserInfoLog exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UserInfoLogPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}