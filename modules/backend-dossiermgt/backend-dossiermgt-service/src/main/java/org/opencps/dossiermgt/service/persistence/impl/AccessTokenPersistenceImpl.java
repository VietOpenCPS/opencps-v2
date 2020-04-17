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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.dossiermgt.exception.NoSuchAccessTokenException;
import org.opencps.dossiermgt.model.AccessToken;
import org.opencps.dossiermgt.model.impl.AccessTokenImpl;
import org.opencps.dossiermgt.model.impl.AccessTokenModelImpl;
import org.opencps.dossiermgt.service.persistence.AccessTokenPersistence;

import java.io.Serializable;

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
 * The persistence implementation for the access token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see AccessTokenPersistence
 * @see org.opencps.dossiermgt.service.persistence.AccessTokenUtil
 * @generated
 */
@ProviderType
public class AccessTokenPersistenceImpl extends BasePersistenceImpl<AccessToken>
	implements AccessTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccessTokenUtil} to access the access token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccessTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
			AccessTokenModelImpl.FINDER_CACHE_ENABLED, AccessTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
			AccessTokenModelImpl.FINDER_CACHE_ENABLED, AccessTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
			AccessTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_CLASSNAME =
		new FinderPath(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
			AccessTokenModelImpl.FINDER_CACHE_ENABLED, AccessTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID_ClassName",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CLASSNAME =
		new FinderPath(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
			AccessTokenModelImpl.FINDER_CACHE_ENABLED, AccessTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_GID_ClassName",
			new String[] { Long.class.getName(), String.class.getName() },
			AccessTokenModelImpl.GROUPID_COLUMN_BITMASK |
			AccessTokenModelImpl.CLASSNAME_COLUMN_BITMASK |
			AccessTokenModelImpl.EXPIREDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_CLASSNAME = new FinderPath(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
			AccessTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_GID_ClassName",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the access tokens where groupId = &#63; and className = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @return the matching access tokens
	 */
	@Override
	public List<AccessToken> findByF_GID_ClassName(long groupId,
		String className) {
		return findByF_GID_ClassName(groupId, className, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the access tokens where groupId = &#63; and className = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param start the lower bound of the range of access tokens
	 * @param end the upper bound of the range of access tokens (not inclusive)
	 * @return the range of matching access tokens
	 */
	@Override
	public List<AccessToken> findByF_GID_ClassName(long groupId,
		String className, int start, int end) {
		return findByF_GID_ClassName(groupId, className, start, end, null);
	}

	/**
	 * Returns an ordered range of all the access tokens where groupId = &#63; and className = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param start the lower bound of the range of access tokens
	 * @param end the upper bound of the range of access tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching access tokens
	 */
	@Override
	public List<AccessToken> findByF_GID_ClassName(long groupId,
		String className, int start, int end,
		OrderByComparator<AccessToken> orderByComparator) {
		return findByF_GID_ClassName(groupId, className, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the access tokens where groupId = &#63; and className = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param start the lower bound of the range of access tokens
	 * @param end the upper bound of the range of access tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching access tokens
	 */
	@Override
	public List<AccessToken> findByF_GID_ClassName(long groupId,
		String className, int start, int end,
		OrderByComparator<AccessToken> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CLASSNAME;
			finderArgs = new Object[] { groupId, className };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_CLASSNAME;
			finderArgs = new Object[] {
					groupId, className,
					
					start, end, orderByComparator
				};
		}

		List<AccessToken> list = null;

		if (retrieveFromCache) {
			list = (List<AccessToken>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccessToken accessToken : list) {
					if ((groupId != accessToken.getGroupId()) ||
							!Objects.equals(className,
								accessToken.getClassName())) {
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

			query.append(_SQL_SELECT_ACCESSTOKEN_WHERE);

			query.append(_FINDER_COLUMN_F_GID_CLASSNAME_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AccessTokenModelImpl.ORDER_BY_JPQL);
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

				if (!pagination) {
					list = (List<AccessToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccessToken>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first access token in the ordered set where groupId = &#63; and className = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access token
	 * @throws NoSuchAccessTokenException if a matching access token could not be found
	 */
	@Override
	public AccessToken findByF_GID_ClassName_First(long groupId,
		String className, OrderByComparator<AccessToken> orderByComparator)
		throws NoSuchAccessTokenException {
		AccessToken accessToken = fetchByF_GID_ClassName_First(groupId,
				className, orderByComparator);

		if (accessToken != null) {
			return accessToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append("}");

		throw new NoSuchAccessTokenException(msg.toString());
	}

	/**
	 * Returns the first access token in the ordered set where groupId = &#63; and className = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access token, or <code>null</code> if a matching access token could not be found
	 */
	@Override
	public AccessToken fetchByF_GID_ClassName_First(long groupId,
		String className, OrderByComparator<AccessToken> orderByComparator) {
		List<AccessToken> list = findByF_GID_ClassName(groupId, className, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last access token in the ordered set where groupId = &#63; and className = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access token
	 * @throws NoSuchAccessTokenException if a matching access token could not be found
	 */
	@Override
	public AccessToken findByF_GID_ClassName_Last(long groupId,
		String className, OrderByComparator<AccessToken> orderByComparator)
		throws NoSuchAccessTokenException {
		AccessToken accessToken = fetchByF_GID_ClassName_Last(groupId,
				className, orderByComparator);

		if (accessToken != null) {
			return accessToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append("}");

		throw new NoSuchAccessTokenException(msg.toString());
	}

	/**
	 * Returns the last access token in the ordered set where groupId = &#63; and className = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access token, or <code>null</code> if a matching access token could not be found
	 */
	@Override
	public AccessToken fetchByF_GID_ClassName_Last(long groupId,
		String className, OrderByComparator<AccessToken> orderByComparator) {
		int count = countByF_GID_ClassName(groupId, className);

		if (count == 0) {
			return null;
		}

		List<AccessToken> list = findByF_GID_ClassName(groupId, className,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the access tokens before and after the current access token in the ordered set where groupId = &#63; and className = &#63;.
	 *
	 * @param accessTokenId the primary key of the current access token
	 * @param groupId the group ID
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access token
	 * @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	 */
	@Override
	public AccessToken[] findByF_GID_ClassName_PrevAndNext(long accessTokenId,
		long groupId, String className,
		OrderByComparator<AccessToken> orderByComparator)
		throws NoSuchAccessTokenException {
		AccessToken accessToken = findByPrimaryKey(accessTokenId);

		Session session = null;

		try {
			session = openSession();

			AccessToken[] array = new AccessTokenImpl[3];

			array[0] = getByF_GID_ClassName_PrevAndNext(session, accessToken,
					groupId, className, orderByComparator, true);

			array[1] = accessToken;

			array[2] = getByF_GID_ClassName_PrevAndNext(session, accessToken,
					groupId, className, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccessToken getByF_GID_ClassName_PrevAndNext(Session session,
		AccessToken accessToken, long groupId, String className,
		OrderByComparator<AccessToken> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ACCESSTOKEN_WHERE);

		query.append(_FINDER_COLUMN_F_GID_CLASSNAME_GROUPID_2);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_1);
		}
		else if (className.equals("")) {
			query.append(_FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_2);
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
			query.append(AccessTokenModelImpl.ORDER_BY_JPQL);
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

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(accessToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AccessToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the access tokens where groupId = &#63; and className = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 */
	@Override
	public void removeByF_GID_ClassName(long groupId, String className) {
		for (AccessToken accessToken : findByF_GID_ClassName(groupId,
				className, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(accessToken);
		}
	}

	/**
	 * Returns the number of access tokens where groupId = &#63; and className = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @return the number of matching access tokens
	 */
	@Override
	public int countByF_GID_ClassName(long groupId, String className) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_CLASSNAME;

		Object[] finderArgs = new Object[] { groupId, className };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCESSTOKEN_WHERE);

			query.append(_FINDER_COLUMN_F_GID_CLASSNAME_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_1);
			}
			else if (className.equals("")) {
				query.append(_FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_2);
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

	private static final String _FINDER_COLUMN_F_GID_CLASSNAME_GROUPID_2 = "accessToken.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_1 = "accessToken.className IS NULL";
	private static final String _FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_2 = "accessToken.className = ?";
	private static final String _FINDER_COLUMN_F_GID_CLASSNAME_CLASSNAME_3 = "(accessToken.className IS NULL OR accessToken.className = '')";

	public AccessTokenPersistenceImpl() {
		setModelClass(AccessToken.class);
	}

	/**
	 * Caches the access token in the entity cache if it is enabled.
	 *
	 * @param accessToken the access token
	 */
	@Override
	public void cacheResult(AccessToken accessToken) {
		entityCache.putResult(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
			AccessTokenImpl.class, accessToken.getPrimaryKey(), accessToken);

		accessToken.resetOriginalValues();
	}

	/**
	 * Caches the access tokens in the entity cache if it is enabled.
	 *
	 * @param accessTokens the access tokens
	 */
	@Override
	public void cacheResult(List<AccessToken> accessTokens) {
		for (AccessToken accessToken : accessTokens) {
			if (entityCache.getResult(
						AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
						AccessTokenImpl.class, accessToken.getPrimaryKey()) == null) {
				cacheResult(accessToken);
			}
			else {
				accessToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all access tokens.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccessTokenImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the access token.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccessToken accessToken) {
		entityCache.removeResult(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
			AccessTokenImpl.class, accessToken.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccessToken> accessTokens) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccessToken accessToken : accessTokens) {
			entityCache.removeResult(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
				AccessTokenImpl.class, accessToken.getPrimaryKey());
		}
	}

	/**
	 * Creates a new access token with the primary key. Does not add the access token to the database.
	 *
	 * @param accessTokenId the primary key for the new access token
	 * @return the new access token
	 */
	@Override
	public AccessToken create(long accessTokenId) {
		AccessToken accessToken = new AccessTokenImpl();

		accessToken.setNew(true);
		accessToken.setPrimaryKey(accessTokenId);

		accessToken.setCompanyId(companyProvider.getCompanyId());

		return accessToken;
	}

	/**
	 * Removes the access token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accessTokenId the primary key of the access token
	 * @return the access token that was removed
	 * @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	 */
	@Override
	public AccessToken remove(long accessTokenId)
		throws NoSuchAccessTokenException {
		return remove((Serializable)accessTokenId);
	}

	/**
	 * Removes the access token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the access token
	 * @return the access token that was removed
	 * @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	 */
	@Override
	public AccessToken remove(Serializable primaryKey)
		throws NoSuchAccessTokenException {
		Session session = null;

		try {
			session = openSession();

			AccessToken accessToken = (AccessToken)session.get(AccessTokenImpl.class,
					primaryKey);

			if (accessToken == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccessTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accessToken);
		}
		catch (NoSuchAccessTokenException nsee) {
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
	protected AccessToken removeImpl(AccessToken accessToken) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accessToken)) {
				accessToken = (AccessToken)session.get(AccessTokenImpl.class,
						accessToken.getPrimaryKeyObj());
			}

			if (accessToken != null) {
				session.delete(accessToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accessToken != null) {
			clearCache(accessToken);
		}

		return accessToken;
	}

	@Override
	public AccessToken updateImpl(AccessToken accessToken) {
		boolean isNew = accessToken.isNew();

		if (!(accessToken instanceof AccessTokenModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(accessToken.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(accessToken);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in accessToken proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AccessToken implementation " +
				accessToken.getClass());
		}

		AccessTokenModelImpl accessTokenModelImpl = (AccessTokenModelImpl)accessToken;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (accessToken.getCreateDate() == null)) {
			if (serviceContext == null) {
				accessToken.setCreateDate(now);
			}
			else {
				accessToken.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!accessTokenModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				accessToken.setModifiedDate(now);
			}
			else {
				accessToken.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (accessToken.isNew()) {
				session.save(accessToken);

				accessToken.setNew(false);
			}
			else {
				accessToken = (AccessToken)session.merge(accessToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AccessTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					accessTokenModelImpl.getGroupId(),
					accessTokenModelImpl.getClassName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_CLASSNAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CLASSNAME,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((accessTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CLASSNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						accessTokenModelImpl.getOriginalGroupId(),
						accessTokenModelImpl.getOriginalClassName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_CLASSNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CLASSNAME,
					args);

				args = new Object[] {
						accessTokenModelImpl.getGroupId(),
						accessTokenModelImpl.getClassName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_CLASSNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_CLASSNAME,
					args);
			}
		}

		entityCache.putResult(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
			AccessTokenImpl.class, accessToken.getPrimaryKey(), accessToken,
			false);

		accessToken.resetOriginalValues();

		return accessToken;
	}

	/**
	 * Returns the access token with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the access token
	 * @return the access token
	 * @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	 */
	@Override
	public AccessToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccessTokenException {
		AccessToken accessToken = fetchByPrimaryKey(primaryKey);

		if (accessToken == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccessTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accessToken;
	}

	/**
	 * Returns the access token with the primary key or throws a {@link NoSuchAccessTokenException} if it could not be found.
	 *
	 * @param accessTokenId the primary key of the access token
	 * @return the access token
	 * @throws NoSuchAccessTokenException if a access token with the primary key could not be found
	 */
	@Override
	public AccessToken findByPrimaryKey(long accessTokenId)
		throws NoSuchAccessTokenException {
		return findByPrimaryKey((Serializable)accessTokenId);
	}

	/**
	 * Returns the access token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the access token
	 * @return the access token, or <code>null</code> if a access token with the primary key could not be found
	 */
	@Override
	public AccessToken fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
				AccessTokenImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccessToken accessToken = (AccessToken)serializable;

		if (accessToken == null) {
			Session session = null;

			try {
				session = openSession();

				accessToken = (AccessToken)session.get(AccessTokenImpl.class,
						primaryKey);

				if (accessToken != null) {
					cacheResult(accessToken);
				}
				else {
					entityCache.putResult(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
						AccessTokenImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
					AccessTokenImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accessToken;
	}

	/**
	 * Returns the access token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accessTokenId the primary key of the access token
	 * @return the access token, or <code>null</code> if a access token with the primary key could not be found
	 */
	@Override
	public AccessToken fetchByPrimaryKey(long accessTokenId) {
		return fetchByPrimaryKey((Serializable)accessTokenId);
	}

	@Override
	public Map<Serializable, AccessToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccessToken> map = new HashMap<Serializable, AccessToken>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccessToken accessToken = fetchByPrimaryKey(primaryKey);

			if (accessToken != null) {
				map.put(primaryKey, accessToken);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
					AccessTokenImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccessToken)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCESSTOKEN_WHERE_PKS_IN);

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

			for (AccessToken accessToken : (List<AccessToken>)q.list()) {
				map.put(accessToken.getPrimaryKeyObj(), accessToken);

				cacheResult(accessToken);

				uncachedPrimaryKeys.remove(accessToken.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccessTokenModelImpl.ENTITY_CACHE_ENABLED,
					AccessTokenImpl.class, primaryKey, nullModel);
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
	 * Returns all the access tokens.
	 *
	 * @return the access tokens
	 */
	@Override
	public List<AccessToken> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the access tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of access tokens
	 * @param end the upper bound of the range of access tokens (not inclusive)
	 * @return the range of access tokens
	 */
	@Override
	public List<AccessToken> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the access tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of access tokens
	 * @param end the upper bound of the range of access tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of access tokens
	 */
	@Override
	public List<AccessToken> findAll(int start, int end,
		OrderByComparator<AccessToken> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the access tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccessTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of access tokens
	 * @param end the upper bound of the range of access tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of access tokens
	 */
	@Override
	public List<AccessToken> findAll(int start, int end,
		OrderByComparator<AccessToken> orderByComparator,
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

		List<AccessToken> list = null;

		if (retrieveFromCache) {
			list = (List<AccessToken>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCESSTOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCESSTOKEN;

				if (pagination) {
					sql = sql.concat(AccessTokenModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccessToken>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccessToken>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the access tokens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccessToken accessToken : findAll()) {
			remove(accessToken);
		}
	}

	/**
	 * Returns the number of access tokens.
	 *
	 * @return the number of access tokens
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCESSTOKEN);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return AccessTokenModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the access token persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccessTokenImpl.class.getName());
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
	private static final String _SQL_SELECT_ACCESSTOKEN = "SELECT accessToken FROM AccessToken accessToken";
	private static final String _SQL_SELECT_ACCESSTOKEN_WHERE_PKS_IN = "SELECT accessToken FROM AccessToken accessToken WHERE accessTokenId IN (";
	private static final String _SQL_SELECT_ACCESSTOKEN_WHERE = "SELECT accessToken FROM AccessToken accessToken WHERE ";
	private static final String _SQL_COUNT_ACCESSTOKEN = "SELECT COUNT(accessToken) FROM AccessToken accessToken";
	private static final String _SQL_COUNT_ACCESSTOKEN_WHERE = "SELECT COUNT(accessToken) FROM AccessToken accessToken WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accessToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccessToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AccessToken exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AccessTokenPersistenceImpl.class);
}