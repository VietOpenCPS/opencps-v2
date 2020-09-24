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

import org.opencps.dossiermgt.exception.NoSuchPostConnectException;
import org.opencps.dossiermgt.model.PostConnect;
import org.opencps.dossiermgt.model.impl.PostConnectImpl;
import org.opencps.dossiermgt.model.impl.PostConnectModelImpl;
import org.opencps.dossiermgt.service.persistence.PostConnectPersistence;

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
 * The persistence implementation for the post connect service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see PostConnectPersistence
 * @see org.opencps.dossiermgt.service.persistence.PostConnectUtil
 * @generated
 */
@ProviderType
public class PostConnectPersistenceImpl extends BasePersistenceImpl<PostConnect>
	implements PostConnectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PostConnectUtil} to access the post connect persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PostConnectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PostConnectModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the post connects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching post connects
	 */
	@Override
	public List<PostConnect> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the post connects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @return the range of matching post connects
	 */
	@Override
	public List<PostConnect> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the post connects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByUuid(String uuid, int start, int end,
		OrderByComparator<PostConnect> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the post connects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByUuid(String uuid, int start, int end,
		OrderByComparator<PostConnect> orderByComparator,
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

		List<PostConnect> list = null;

		if (retrieveFromCache) {
			list = (List<PostConnect>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PostConnect postConnect : list) {
					if (!Objects.equals(uuid, postConnect.getUuid())) {
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

			query.append(_SQL_SELECT_POSTCONNECT_WHERE);

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
				query.append(PostConnectModelImpl.ORDER_BY_JPQL);
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
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first post connect in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByUuid_First(String uuid,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByUuid_First(uuid, orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the first post connect in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByUuid_First(String uuid,
		OrderByComparator<PostConnect> orderByComparator) {
		List<PostConnect> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last post connect in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByUuid_Last(String uuid,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByUuid_Last(uuid, orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the last post connect in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByUuid_Last(String uuid,
		OrderByComparator<PostConnect> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PostConnect> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the post connects before and after the current post connect in the ordered set where uuid = &#63;.
	 *
	 * @param postConnectId the primary key of the current post connect
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next post connect
	 * @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect[] findByUuid_PrevAndNext(long postConnectId,
		String uuid, OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = findByPrimaryKey(postConnectId);

		Session session = null;

		try {
			session = openSession();

			PostConnect[] array = new PostConnectImpl[3];

			array[0] = getByUuid_PrevAndNext(session, postConnect, uuid,
					orderByComparator, true);

			array[1] = postConnect;

			array[2] = getByUuid_PrevAndNext(session, postConnect, uuid,
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

	protected PostConnect getByUuid_PrevAndNext(Session session,
		PostConnect postConnect, String uuid,
		OrderByComparator<PostConnect> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POSTCONNECT_WHERE);

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
			query.append(PostConnectModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(postConnect);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PostConnect> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the post connects where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PostConnect postConnect : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(postConnect);
		}
	}

	/**
	 * Returns the number of post connects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching post connects
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POSTCONNECT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "postConnect.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "postConnect.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(postConnect.uuid IS NULL OR postConnect.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PostConnectModelImpl.UUID_COLUMN_BITMASK |
			PostConnectModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the post connect where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByUUID_G(String uuid, long groupId)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByUUID_G(uuid, groupId);

		if (postConnect == null) {
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

			throw new NoSuchPostConnectException(msg.toString());
		}

		return postConnect;
	}

	/**
	 * Returns the post connect where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the post connect where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PostConnect) {
			PostConnect postConnect = (PostConnect)result;

			if (!Objects.equals(uuid, postConnect.getUuid()) ||
					(groupId != postConnect.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_POSTCONNECT_WHERE);

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

				List<PostConnect> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					PostConnect postConnect = list.get(0);

					result = postConnect;

					cacheResult(postConnect);
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
			return (PostConnect)result;
		}
	}

	/**
	 * Removes the post connect where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the post connect that was removed
	 */
	@Override
	public PostConnect removeByUUID_G(String uuid, long groupId)
		throws NoSuchPostConnectException {
		PostConnect postConnect = findByUUID_G(uuid, groupId);

		return remove(postConnect);
	}

	/**
	 * Returns the number of post connects where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching post connects
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSTCONNECT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "postConnect.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "postConnect.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(postConnect.uuid IS NULL OR postConnect.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "postConnect.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			PostConnectModelImpl.UUID_COLUMN_BITMASK |
			PostConnectModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the post connects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching post connects
	 */
	@Override
	public List<PostConnect> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the post connects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @return the range of matching post connects
	 */
	@Override
	public List<PostConnect> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the post connects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PostConnect> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the post connects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PostConnect> orderByComparator,
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

		List<PostConnect> list = null;

		if (retrieveFromCache) {
			list = (List<PostConnect>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PostConnect postConnect : list) {
					if (!Objects.equals(uuid, postConnect.getUuid()) ||
							(companyId != postConnect.getCompanyId())) {
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

			query.append(_SQL_SELECT_POSTCONNECT_WHERE);

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
				query.append(PostConnectModelImpl.ORDER_BY_JPQL);
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
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the first post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator) {
		List<PostConnect> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the last post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<PostConnect> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the post connects before and after the current post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param postConnectId the primary key of the current post connect
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next post connect
	 * @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect[] findByUuid_C_PrevAndNext(long postConnectId,
		String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = findByPrimaryKey(postConnectId);

		Session session = null;

		try {
			session = openSession();

			PostConnect[] array = new PostConnectImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, postConnect, uuid,
					companyId, orderByComparator, true);

			array[1] = postConnect;

			array[2] = getByUuid_C_PrevAndNext(session, postConnect, uuid,
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

	protected PostConnect getByUuid_C_PrevAndNext(Session session,
		PostConnect postConnect, String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_POSTCONNECT_WHERE);

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
			query.append(PostConnectModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(postConnect);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PostConnect> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the post connects where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (PostConnect postConnect : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(postConnect);
		}
	}

	/**
	 * Returns the number of post connects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching post connects
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSTCONNECT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "postConnect.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "postConnect.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(postConnect.uuid IS NULL OR postConnect.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "postConnect.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SYNC_STATE =
		new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_GID_SYNC_STATE",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SYNC_STATE =
		new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_GID_SYNC_STATE",
			new String[] { Long.class.getName(), Integer.class.getName() },
			PostConnectModelImpl.GROUPID_COLUMN_BITMASK |
			PostConnectModelImpl.SYNCSTATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_GID_SYNC_STATE = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_GID_SYNC_STATE",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the post connects where groupId = &#63; and syncState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @return the matching post connects
	 */
	@Override
	public List<PostConnect> findByF_GID_SYNC_STATE(long groupId, int syncState) {
		return findByF_GID_SYNC_STATE(groupId, syncState, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the post connects where groupId = &#63; and syncState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @return the range of matching post connects
	 */
	@Override
	public List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState, int start, int end) {
		return findByF_GID_SYNC_STATE(groupId, syncState, start, end, null);
	}

	/**
	 * Returns an ordered range of all the post connects where groupId = &#63; and syncState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState, int start, int end,
		OrderByComparator<PostConnect> orderByComparator) {
		return findByF_GID_SYNC_STATE(groupId, syncState, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the post connects where groupId = &#63; and syncState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState, int start, int end,
		OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SYNC_STATE;
			finderArgs = new Object[] { groupId, syncState };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_GID_SYNC_STATE;
			finderArgs = new Object[] {
					groupId, syncState,
					
					start, end, orderByComparator
				};
		}

		List<PostConnect> list = null;

		if (retrieveFromCache) {
			list = (List<PostConnect>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PostConnect postConnect : list) {
					if ((groupId != postConnect.getGroupId()) ||
							(syncState != postConnect.getSyncState())) {
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

			query.append(_SQL_SELECT_POSTCONNECT_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SYNC_STATE_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SYNC_STATE_SYNCSTATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PostConnectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(syncState);

				if (!pagination) {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByF_GID_SYNC_STATE_First(long groupId,
		int syncState, OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByF_GID_SYNC_STATE_First(groupId,
				syncState, orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", syncState=");
		msg.append(syncState);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the first post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_GID_SYNC_STATE_First(long groupId,
		int syncState, OrderByComparator<PostConnect> orderByComparator) {
		List<PostConnect> list = findByF_GID_SYNC_STATE(groupId, syncState, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByF_GID_SYNC_STATE_Last(long groupId, int syncState,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByF_GID_SYNC_STATE_Last(groupId,
				syncState, orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", syncState=");
		msg.append(syncState);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the last post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_GID_SYNC_STATE_Last(long groupId,
		int syncState, OrderByComparator<PostConnect> orderByComparator) {
		int count = countByF_GID_SYNC_STATE(groupId, syncState);

		if (count == 0) {
			return null;
		}

		List<PostConnect> list = findByF_GID_SYNC_STATE(groupId, syncState,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the post connects before and after the current post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	 *
	 * @param postConnectId the primary key of the current post connect
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next post connect
	 * @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect[] findByF_GID_SYNC_STATE_PrevAndNext(
		long postConnectId, long groupId, int syncState,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = findByPrimaryKey(postConnectId);

		Session session = null;

		try {
			session = openSession();

			PostConnect[] array = new PostConnectImpl[3];

			array[0] = getByF_GID_SYNC_STATE_PrevAndNext(session, postConnect,
					groupId, syncState, orderByComparator, true);

			array[1] = postConnect;

			array[2] = getByF_GID_SYNC_STATE_PrevAndNext(session, postConnect,
					groupId, syncState, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PostConnect getByF_GID_SYNC_STATE_PrevAndNext(Session session,
		PostConnect postConnect, long groupId, int syncState,
		OrderByComparator<PostConnect> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_POSTCONNECT_WHERE);

		query.append(_FINDER_COLUMN_F_GID_SYNC_STATE_GROUPID_2);

		query.append(_FINDER_COLUMN_F_GID_SYNC_STATE_SYNCSTATE_2);

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
			query.append(PostConnectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(syncState);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(postConnect);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PostConnect> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the post connects where groupId = &#63; and syncState = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 */
	@Override
	public void removeByF_GID_SYNC_STATE(long groupId, int syncState) {
		for (PostConnect postConnect : findByF_GID_SYNC_STATE(groupId,
				syncState, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(postConnect);
		}
	}

	/**
	 * Returns the number of post connects where groupId = &#63; and syncState = &#63;.
	 *
	 * @param groupId the group ID
	 * @param syncState the sync state
	 * @return the number of matching post connects
	 */
	@Override
	public int countByF_GID_SYNC_STATE(long groupId, int syncState) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_GID_SYNC_STATE;

		Object[] finderArgs = new Object[] { groupId, syncState };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSTCONNECT_WHERE);

			query.append(_FINDER_COLUMN_F_GID_SYNC_STATE_GROUPID_2);

			query.append(_FINDER_COLUMN_F_GID_SYNC_STATE_SYNCSTATE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(syncState);

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

	private static final String _FINDER_COLUMN_F_GID_SYNC_STATE_GROUPID_2 = "postConnect.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_GID_SYNC_STATE_SYNCSTATE_2 = "postConnect.syncState = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_SYNC_STATE =
		new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_SYNC_STATE",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SYNC_STATE =
		new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_SYNC_STATE",
			new String[] { Integer.class.getName() },
			PostConnectModelImpl.SYNCSTATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_SYNC_STATE = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_SYNC_STATE",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the post connects where syncState = &#63;.
	 *
	 * @param syncState the sync state
	 * @return the matching post connects
	 */
	@Override
	public List<PostConnect> findByF_SYNC_STATE(int syncState) {
		return findByF_SYNC_STATE(syncState, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the post connects where syncState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param syncState the sync state
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @return the range of matching post connects
	 */
	@Override
	public List<PostConnect> findByF_SYNC_STATE(int syncState, int start,
		int end) {
		return findByF_SYNC_STATE(syncState, start, end, null);
	}

	/**
	 * Returns an ordered range of all the post connects where syncState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param syncState the sync state
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByF_SYNC_STATE(int syncState, int start,
		int end, OrderByComparator<PostConnect> orderByComparator) {
		return findByF_SYNC_STATE(syncState, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the post connects where syncState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param syncState the sync state
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByF_SYNC_STATE(int syncState, int start,
		int end, OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SYNC_STATE;
			finderArgs = new Object[] { syncState };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_SYNC_STATE;
			finderArgs = new Object[] { syncState, start, end, orderByComparator };
		}

		List<PostConnect> list = null;

		if (retrieveFromCache) {
			list = (List<PostConnect>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PostConnect postConnect : list) {
					if ((syncState != postConnect.getSyncState())) {
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

			query.append(_SQL_SELECT_POSTCONNECT_WHERE);

			query.append(_FINDER_COLUMN_F_SYNC_STATE_SYNCSTATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PostConnectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(syncState);

				if (!pagination) {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first post connect in the ordered set where syncState = &#63;.
	 *
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByF_SYNC_STATE_First(int syncState,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByF_SYNC_STATE_First(syncState,
				orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("syncState=");
		msg.append(syncState);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the first post connect in the ordered set where syncState = &#63;.
	 *
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_SYNC_STATE_First(int syncState,
		OrderByComparator<PostConnect> orderByComparator) {
		List<PostConnect> list = findByF_SYNC_STATE(syncState, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last post connect in the ordered set where syncState = &#63;.
	 *
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByF_SYNC_STATE_Last(int syncState,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByF_SYNC_STATE_Last(syncState,
				orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("syncState=");
		msg.append(syncState);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the last post connect in the ordered set where syncState = &#63;.
	 *
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_SYNC_STATE_Last(int syncState,
		OrderByComparator<PostConnect> orderByComparator) {
		int count = countByF_SYNC_STATE(syncState);

		if (count == 0) {
			return null;
		}

		List<PostConnect> list = findByF_SYNC_STATE(syncState, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the post connects before and after the current post connect in the ordered set where syncState = &#63;.
	 *
	 * @param postConnectId the primary key of the current post connect
	 * @param syncState the sync state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next post connect
	 * @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect[] findByF_SYNC_STATE_PrevAndNext(long postConnectId,
		int syncState, OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = findByPrimaryKey(postConnectId);

		Session session = null;

		try {
			session = openSession();

			PostConnect[] array = new PostConnectImpl[3];

			array[0] = getByF_SYNC_STATE_PrevAndNext(session, postConnect,
					syncState, orderByComparator, true);

			array[1] = postConnect;

			array[2] = getByF_SYNC_STATE_PrevAndNext(session, postConnect,
					syncState, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PostConnect getByF_SYNC_STATE_PrevAndNext(Session session,
		PostConnect postConnect, int syncState,
		OrderByComparator<PostConnect> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POSTCONNECT_WHERE);

		query.append(_FINDER_COLUMN_F_SYNC_STATE_SYNCSTATE_2);

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
			query.append(PostConnectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(syncState);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(postConnect);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PostConnect> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the post connects where syncState = &#63; from the database.
	 *
	 * @param syncState the sync state
	 */
	@Override
	public void removeByF_SYNC_STATE(int syncState) {
		for (PostConnect postConnect : findByF_SYNC_STATE(syncState,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(postConnect);
		}
	}

	/**
	 * Returns the number of post connects where syncState = &#63;.
	 *
	 * @param syncState the sync state
	 * @return the number of matching post connects
	 */
	@Override
	public int countByF_SYNC_STATE(int syncState) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_SYNC_STATE;

		Object[] finderArgs = new Object[] { syncState };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POSTCONNECT_WHERE);

			query.append(_FINDER_COLUMN_F_SYNC_STATE_SYNCSTATE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(syncState);

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

	private static final String _FINDER_COLUMN_F_SYNC_STATE_SYNCSTATE_2 = "postConnect.syncState = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_ORDER_NUMBER = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_ORDER_NUMBER",
			new String[] { String.class.getName() },
			PostConnectModelImpl.ORDERNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_ORDER_NUMBER = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_ORDER_NUMBER",
			new String[] { String.class.getName() });

	/**
	 * Returns the post connect where orderNumber = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	 *
	 * @param orderNumber the order number
	 * @return the matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByF_ORDER_NUMBER(String orderNumber)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByF_ORDER_NUMBER(orderNumber);

		if (postConnect == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("orderNumber=");
			msg.append(orderNumber);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPostConnectException(msg.toString());
		}

		return postConnect;
	}

	/**
	 * Returns the post connect where orderNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param orderNumber the order number
	 * @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_ORDER_NUMBER(String orderNumber) {
		return fetchByF_ORDER_NUMBER(orderNumber, true);
	}

	/**
	 * Returns the post connect where orderNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param orderNumber the order number
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_ORDER_NUMBER(String orderNumber,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { orderNumber };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER,
					finderArgs, this);
		}

		if (result instanceof PostConnect) {
			PostConnect postConnect = (PostConnect)result;

			if (!Objects.equals(orderNumber, postConnect.getOrderNumber())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_POSTCONNECT_WHERE);

			boolean bindOrderNumber = false;

			if (orderNumber == null) {
				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_ORDERNUMBER_1);
			}
			else if (orderNumber.equals("")) {
				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_ORDERNUMBER_3);
			}
			else {
				bindOrderNumber = true;

				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_ORDERNUMBER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOrderNumber) {
					qPos.add(orderNumber);
				}

				List<PostConnect> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PostConnectPersistenceImpl.fetchByF_ORDER_NUMBER(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PostConnect postConnect = list.get(0);

					result = postConnect;

					cacheResult(postConnect);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER,
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
			return (PostConnect)result;
		}
	}

	/**
	 * Removes the post connect where orderNumber = &#63; from the database.
	 *
	 * @param orderNumber the order number
	 * @return the post connect that was removed
	 */
	@Override
	public PostConnect removeByF_ORDER_NUMBER(String orderNumber)
		throws NoSuchPostConnectException {
		PostConnect postConnect = findByF_ORDER_NUMBER(orderNumber);

		return remove(postConnect);
	}

	/**
	 * Returns the number of post connects where orderNumber = &#63;.
	 *
	 * @param orderNumber the order number
	 * @return the number of matching post connects
	 */
	@Override
	public int countByF_ORDER_NUMBER(String orderNumber) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_ORDER_NUMBER;

		Object[] finderArgs = new Object[] { orderNumber };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POSTCONNECT_WHERE);

			boolean bindOrderNumber = false;

			if (orderNumber == null) {
				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_ORDERNUMBER_1);
			}
			else if (orderNumber.equals("")) {
				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_ORDERNUMBER_3);
			}
			else {
				bindOrderNumber = true;

				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_ORDERNUMBER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOrderNumber) {
					qPos.add(orderNumber);
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

	private static final String _FINDER_COLUMN_F_ORDER_NUMBER_ORDERNUMBER_1 = "postConnect.orderNumber IS NULL";
	private static final String _FINDER_COLUMN_F_ORDER_NUMBER_ORDERNUMBER_2 = "postConnect.orderNumber = ?";
	private static final String _FINDER_COLUMN_F_ORDER_NUMBER_ORDERNUMBER_3 = "(postConnect.orderNumber IS NULL OR postConnect.orderNumber = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_ORDER_NUMBER_POST_STATUS =
		new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_ORDER_NUMBER_POST_STATUS",
			new String[] { String.class.getName(), Integer.class.getName() },
			PostConnectModelImpl.ORDERNUMBER_COLUMN_BITMASK |
			PostConnectModelImpl.POSTSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_ORDER_NUMBER_POST_STATUS =
		new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_ORDER_NUMBER_POST_STATUS",
			new String[] { String.class.getName(), Integer.class.getName() });

	/**
	 * Returns the post connect where orderNumber = &#63; and postStatus = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	 *
	 * @param orderNumber the order number
	 * @param postStatus the post status
	 * @return the matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus) throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByF_ORDER_NUMBER_POST_STATUS(orderNumber,
				postStatus);

		if (postConnect == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("orderNumber=");
			msg.append(orderNumber);

			msg.append(", postStatus=");
			msg.append(postStatus);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPostConnectException(msg.toString());
		}

		return postConnect;
	}

	/**
	 * Returns the post connect where orderNumber = &#63; and postStatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param orderNumber the order number
	 * @param postStatus the post status
	 * @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus) {
		return fetchByF_ORDER_NUMBER_POST_STATUS(orderNumber, postStatus, true);
	}

	/**
	 * Returns the post connect where orderNumber = &#63; and postStatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param orderNumber the order number
	 * @param postStatus the post status
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { orderNumber, postStatus };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER_POST_STATUS,
					finderArgs, this);
		}

		if (result instanceof PostConnect) {
			PostConnect postConnect = (PostConnect)result;

			if (!Objects.equals(orderNumber, postConnect.getOrderNumber()) ||
					(postStatus != postConnect.getPostStatus())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_POSTCONNECT_WHERE);

			boolean bindOrderNumber = false;

			if (orderNumber == null) {
				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_ORDERNUMBER_1);
			}
			else if (orderNumber.equals("")) {
				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_ORDERNUMBER_3);
			}
			else {
				bindOrderNumber = true;

				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_ORDERNUMBER_2);
			}

			query.append(_FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_POSTSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOrderNumber) {
					qPos.add(orderNumber);
				}

				qPos.add(postStatus);

				List<PostConnect> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER_POST_STATUS,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PostConnectPersistenceImpl.fetchByF_ORDER_NUMBER_POST_STATUS(String, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PostConnect postConnect = list.get(0);

					result = postConnect;

					cacheResult(postConnect);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER_POST_STATUS,
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
			return (PostConnect)result;
		}
	}

	/**
	 * Removes the post connect where orderNumber = &#63; and postStatus = &#63; from the database.
	 *
	 * @param orderNumber the order number
	 * @param postStatus the post status
	 * @return the post connect that was removed
	 */
	@Override
	public PostConnect removeByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus) throws NoSuchPostConnectException {
		PostConnect postConnect = findByF_ORDER_NUMBER_POST_STATUS(orderNumber,
				postStatus);

		return remove(postConnect);
	}

	/**
	 * Returns the number of post connects where orderNumber = &#63; and postStatus = &#63;.
	 *
	 * @param orderNumber the order number
	 * @param postStatus the post status
	 * @return the number of matching post connects
	 */
	@Override
	public int countByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_ORDER_NUMBER_POST_STATUS;

		Object[] finderArgs = new Object[] { orderNumber, postStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSTCONNECT_WHERE);

			boolean bindOrderNumber = false;

			if (orderNumber == null) {
				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_ORDERNUMBER_1);
			}
			else if (orderNumber.equals("")) {
				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_ORDERNUMBER_3);
			}
			else {
				bindOrderNumber = true;

				query.append(_FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_ORDERNUMBER_2);
			}

			query.append(_FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_POSTSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOrderNumber) {
					qPos.add(orderNumber);
				}

				qPos.add(postStatus);

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

	private static final String _FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_ORDERNUMBER_1 =
		"postConnect.orderNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_ORDERNUMBER_2 =
		"postConnect.orderNumber = ? AND ";
	private static final String _FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_ORDERNUMBER_3 =
		"(postConnect.orderNumber IS NULL OR postConnect.orderNumber = '') AND ";
	private static final String _FINDER_COLUMN_F_ORDER_NUMBER_POST_STATUS_POSTSTATUS_2 =
		"postConnect.postStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_POST_BY_DOSSIER_ID =
		new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_POST_BY_DOSSIER_ID",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_POST_BY_DOSSIER_ID =
		new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByF_POST_BY_DOSSIER_ID",
			new String[] { Long.class.getName(), Long.class.getName() },
			PostConnectModelImpl.GROUPID_COLUMN_BITMASK |
			PostConnectModelImpl.DOSSIERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_POST_BY_DOSSIER_ID = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_POST_BY_DOSSIER_ID",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the post connects where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the matching post connects
	 */
	@Override
	public List<PostConnect> findByF_POST_BY_DOSSIER_ID(long groupId,
		long dossierId) {
		return findByF_POST_BY_DOSSIER_ID(groupId, dossierId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the post connects where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @return the range of matching post connects
	 */
	@Override
	public List<PostConnect> findByF_POST_BY_DOSSIER_ID(long groupId,
		long dossierId, int start, int end) {
		return findByF_POST_BY_DOSSIER_ID(groupId, dossierId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the post connects where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByF_POST_BY_DOSSIER_ID(long groupId,
		long dossierId, int start, int end,
		OrderByComparator<PostConnect> orderByComparator) {
		return findByF_POST_BY_DOSSIER_ID(groupId, dossierId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the post connects where groupId = &#63; and dossierId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching post connects
	 */
	@Override
	public List<PostConnect> findByF_POST_BY_DOSSIER_ID(long groupId,
		long dossierId, int start, int end,
		OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_POST_BY_DOSSIER_ID;
			finderArgs = new Object[] { groupId, dossierId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_POST_BY_DOSSIER_ID;
			finderArgs = new Object[] {
					groupId, dossierId,
					
					start, end, orderByComparator
				};
		}

		List<PostConnect> list = null;

		if (retrieveFromCache) {
			list = (List<PostConnect>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PostConnect postConnect : list) {
					if ((groupId != postConnect.getGroupId()) ||
							(dossierId != postConnect.getDossierId())) {
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

			query.append(_SQL_SELECT_POSTCONNECT_WHERE);

			query.append(_FINDER_COLUMN_F_POST_BY_DOSSIER_ID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_POST_BY_DOSSIER_ID_DOSSIERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PostConnectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				if (!pagination) {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByF_POST_BY_DOSSIER_ID_First(long groupId,
		long dossierId, OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByF_POST_BY_DOSSIER_ID_First(groupId,
				dossierId, orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the first post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_POST_BY_DOSSIER_ID_First(long groupId,
		long dossierId, OrderByComparator<PostConnect> orderByComparator) {
		List<PostConnect> list = findByF_POST_BY_DOSSIER_ID(groupId, dossierId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByF_POST_BY_DOSSIER_ID_Last(long groupId,
		long dossierId, OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByF_POST_BY_DOSSIER_ID_Last(groupId,
				dossierId, orderByComparator);

		if (postConnect != null) {
			return postConnect;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", dossierId=");
		msg.append(dossierId);

		msg.append("}");

		throw new NoSuchPostConnectException(msg.toString());
	}

	/**
	 * Returns the last post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_POST_BY_DOSSIER_ID_Last(long groupId,
		long dossierId, OrderByComparator<PostConnect> orderByComparator) {
		int count = countByF_POST_BY_DOSSIER_ID(groupId, dossierId);

		if (count == 0) {
			return null;
		}

		List<PostConnect> list = findByF_POST_BY_DOSSIER_ID(groupId, dossierId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the post connects before and after the current post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param postConnectId the primary key of the current post connect
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next post connect
	 * @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect[] findByF_POST_BY_DOSSIER_ID_PrevAndNext(
		long postConnectId, long groupId, long dossierId,
		OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException {
		PostConnect postConnect = findByPrimaryKey(postConnectId);

		Session session = null;

		try {
			session = openSession();

			PostConnect[] array = new PostConnectImpl[3];

			array[0] = getByF_POST_BY_DOSSIER_ID_PrevAndNext(session,
					postConnect, groupId, dossierId, orderByComparator, true);

			array[1] = postConnect;

			array[2] = getByF_POST_BY_DOSSIER_ID_PrevAndNext(session,
					postConnect, groupId, dossierId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PostConnect getByF_POST_BY_DOSSIER_ID_PrevAndNext(
		Session session, PostConnect postConnect, long groupId, long dossierId,
		OrderByComparator<PostConnect> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_POSTCONNECT_WHERE);

		query.append(_FINDER_COLUMN_F_POST_BY_DOSSIER_ID_GROUPID_2);

		query.append(_FINDER_COLUMN_F_POST_BY_DOSSIER_ID_DOSSIERID_2);

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
			query.append(PostConnectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(dossierId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(postConnect);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PostConnect> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the post connects where groupId = &#63; and dossierId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 */
	@Override
	public void removeByF_POST_BY_DOSSIER_ID(long groupId, long dossierId) {
		for (PostConnect postConnect : findByF_POST_BY_DOSSIER_ID(groupId,
				dossierId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(postConnect);
		}
	}

	/**
	 * Returns the number of post connects where groupId = &#63; and dossierId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @return the number of matching post connects
	 */
	@Override
	public int countByF_POST_BY_DOSSIER_ID(long groupId, long dossierId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_POST_BY_DOSSIER_ID;

		Object[] finderArgs = new Object[] { groupId, dossierId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSTCONNECT_WHERE);

			query.append(_FINDER_COLUMN_F_POST_BY_DOSSIER_ID_GROUPID_2);

			query.append(_FINDER_COLUMN_F_POST_BY_DOSSIER_ID_DOSSIERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

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

	private static final String _FINDER_COLUMN_F_POST_BY_DOSSIER_ID_GROUPID_2 = "postConnect.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_POST_BY_DOSSIER_ID_DOSSIERID_2 = "postConnect.dossierId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_POST_BY_D_TYPE = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, PostConnectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByF_POST_BY_D_TYPE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			PostConnectModelImpl.GROUPID_COLUMN_BITMASK |
			PostConnectModelImpl.DOSSIERID_COLUMN_BITMASK |
			PostConnectModelImpl.POSTTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_POST_BY_D_TYPE = new FinderPath(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByF_POST_BY_D_TYPE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param postType the post type
	 * @return the matching post connect
	 * @throws NoSuchPostConnectException if a matching post connect could not be found
	 */
	@Override
	public PostConnect findByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType) throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByF_POST_BY_D_TYPE(groupId, dossierId,
				postType);

		if (postConnect == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", dossierId=");
			msg.append(dossierId);

			msg.append(", postType=");
			msg.append(postType);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPostConnectException(msg.toString());
		}

		return postConnect;
	}

	/**
	 * Returns the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param postType the post type
	 * @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType) {
		return fetchByF_POST_BY_D_TYPE(groupId, dossierId, postType, true);
	}

	/**
	 * Returns the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param postType the post type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	 */
	@Override
	public PostConnect fetchByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, dossierId, postType };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_F_POST_BY_D_TYPE,
					finderArgs, this);
		}

		if (result instanceof PostConnect) {
			PostConnect postConnect = (PostConnect)result;

			if ((groupId != postConnect.getGroupId()) ||
					(dossierId != postConnect.getDossierId()) ||
					(postType != postConnect.getPostType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_POSTCONNECT_WHERE);

			query.append(_FINDER_COLUMN_F_POST_BY_D_TYPE_GROUPID_2);

			query.append(_FINDER_COLUMN_F_POST_BY_D_TYPE_DOSSIERID_2);

			query.append(_FINDER_COLUMN_F_POST_BY_D_TYPE_POSTTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				qPos.add(postType);

				List<PostConnect> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_F_POST_BY_D_TYPE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PostConnectPersistenceImpl.fetchByF_POST_BY_D_TYPE(long, long, int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PostConnect postConnect = list.get(0);

					result = postConnect;

					cacheResult(postConnect);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_F_POST_BY_D_TYPE,
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
			return (PostConnect)result;
		}
	}

	/**
	 * Removes the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param postType the post type
	 * @return the post connect that was removed
	 */
	@Override
	public PostConnect removeByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType) throws NoSuchPostConnectException {
		PostConnect postConnect = findByF_POST_BY_D_TYPE(groupId, dossierId,
				postType);

		return remove(postConnect);
	}

	/**
	 * Returns the number of post connects where groupId = &#63; and dossierId = &#63; and postType = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dossierId the dossier ID
	 * @param postType the post type
	 * @return the number of matching post connects
	 */
	@Override
	public int countByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_POST_BY_D_TYPE;

		Object[] finderArgs = new Object[] { groupId, dossierId, postType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_POSTCONNECT_WHERE);

			query.append(_FINDER_COLUMN_F_POST_BY_D_TYPE_GROUPID_2);

			query.append(_FINDER_COLUMN_F_POST_BY_D_TYPE_DOSSIERID_2);

			query.append(_FINDER_COLUMN_F_POST_BY_D_TYPE_POSTTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(dossierId);

				qPos.add(postType);

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

	private static final String _FINDER_COLUMN_F_POST_BY_D_TYPE_GROUPID_2 = "postConnect.groupId = ? AND ";
	private static final String _FINDER_COLUMN_F_POST_BY_D_TYPE_DOSSIERID_2 = "postConnect.dossierId = ? AND ";
	private static final String _FINDER_COLUMN_F_POST_BY_D_TYPE_POSTTYPE_2 = "postConnect.postType = ?";

	public PostConnectPersistenceImpl() {
		setModelClass(PostConnect.class);

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
	 * Caches the post connect in the entity cache if it is enabled.
	 *
	 * @param postConnect the post connect
	 */
	@Override
	public void cacheResult(PostConnect postConnect) {
		entityCache.putResult(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectImpl.class, postConnect.getPrimaryKey(), postConnect);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { postConnect.getUuid(), postConnect.getGroupId() },
			postConnect);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER,
			new Object[] { postConnect.getOrderNumber() }, postConnect);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER_POST_STATUS,
			new Object[] {
				postConnect.getOrderNumber(), postConnect.getPostStatus()
			}, postConnect);

		finderCache.putResult(FINDER_PATH_FETCH_BY_F_POST_BY_D_TYPE,
			new Object[] {
				postConnect.getGroupId(), postConnect.getDossierId(),
				postConnect.getPostType()
			}, postConnect);

		postConnect.resetOriginalValues();
	}

	/**
	 * Caches the post connects in the entity cache if it is enabled.
	 *
	 * @param postConnects the post connects
	 */
	@Override
	public void cacheResult(List<PostConnect> postConnects) {
		for (PostConnect postConnect : postConnects) {
			if (entityCache.getResult(
						PostConnectModelImpl.ENTITY_CACHE_ENABLED,
						PostConnectImpl.class, postConnect.getPrimaryKey()) == null) {
				cacheResult(postConnect);
			}
			else {
				postConnect.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all post connects.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PostConnectImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the post connect.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PostConnect postConnect) {
		entityCache.removeResult(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectImpl.class, postConnect.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PostConnectModelImpl)postConnect, true);
	}

	@Override
	public void clearCache(List<PostConnect> postConnects) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PostConnect postConnect : postConnects) {
			entityCache.removeResult(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
				PostConnectImpl.class, postConnect.getPrimaryKey());

			clearUniqueFindersCache((PostConnectModelImpl)postConnect, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PostConnectModelImpl postConnectModelImpl) {
		Object[] args = new Object[] {
				postConnectModelImpl.getUuid(),
				postConnectModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			postConnectModelImpl, false);

		args = new Object[] { postConnectModelImpl.getOrderNumber() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_ORDER_NUMBER, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER, args,
			postConnectModelImpl, false);

		args = new Object[] {
				postConnectModelImpl.getOrderNumber(),
				postConnectModelImpl.getPostStatus()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_ORDER_NUMBER_POST_STATUS,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER_POST_STATUS,
			args, postConnectModelImpl, false);

		args = new Object[] {
				postConnectModelImpl.getGroupId(),
				postConnectModelImpl.getDossierId(),
				postConnectModelImpl.getPostType()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_F_POST_BY_D_TYPE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_F_POST_BY_D_TYPE, args,
			postConnectModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PostConnectModelImpl postConnectModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					postConnectModelImpl.getUuid(),
					postConnectModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((postConnectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					postConnectModelImpl.getOriginalUuid(),
					postConnectModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { postConnectModelImpl.getOrderNumber() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ORDER_NUMBER, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER, args);
		}

		if ((postConnectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_ORDER_NUMBER.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					postConnectModelImpl.getOriginalOrderNumber()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ORDER_NUMBER, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					postConnectModelImpl.getOrderNumber(),
					postConnectModelImpl.getPostStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ORDER_NUMBER_POST_STATUS,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER_POST_STATUS,
				args);
		}

		if ((postConnectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_ORDER_NUMBER_POST_STATUS.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					postConnectModelImpl.getOriginalOrderNumber(),
					postConnectModelImpl.getOriginalPostStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_ORDER_NUMBER_POST_STATUS,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_ORDER_NUMBER_POST_STATUS,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					postConnectModelImpl.getGroupId(),
					postConnectModelImpl.getDossierId(),
					postConnectModelImpl.getPostType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_POST_BY_D_TYPE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_POST_BY_D_TYPE, args);
		}

		if ((postConnectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_POST_BY_D_TYPE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					postConnectModelImpl.getOriginalGroupId(),
					postConnectModelImpl.getOriginalDossierId(),
					postConnectModelImpl.getOriginalPostType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_POST_BY_D_TYPE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_F_POST_BY_D_TYPE, args);
		}
	}

	/**
	 * Creates a new post connect with the primary key. Does not add the post connect to the database.
	 *
	 * @param postConnectId the primary key for the new post connect
	 * @return the new post connect
	 */
	@Override
	public PostConnect create(long postConnectId) {
		PostConnect postConnect = new PostConnectImpl();

		postConnect.setNew(true);
		postConnect.setPrimaryKey(postConnectId);

		String uuid = PortalUUIDUtil.generate();

		postConnect.setUuid(uuid);

		postConnect.setCompanyId(companyProvider.getCompanyId());

		return postConnect;
	}

	/**
	 * Removes the post connect with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param postConnectId the primary key of the post connect
	 * @return the post connect that was removed
	 * @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect remove(long postConnectId)
		throws NoSuchPostConnectException {
		return remove((Serializable)postConnectId);
	}

	/**
	 * Removes the post connect with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the post connect
	 * @return the post connect that was removed
	 * @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect remove(Serializable primaryKey)
		throws NoSuchPostConnectException {
		Session session = null;

		try {
			session = openSession();

			PostConnect postConnect = (PostConnect)session.get(PostConnectImpl.class,
					primaryKey);

			if (postConnect == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPostConnectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(postConnect);
		}
		catch (NoSuchPostConnectException nsee) {
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
	protected PostConnect removeImpl(PostConnect postConnect) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(postConnect)) {
				postConnect = (PostConnect)session.get(PostConnectImpl.class,
						postConnect.getPrimaryKeyObj());
			}

			if (postConnect != null) {
				session.delete(postConnect);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (postConnect != null) {
			clearCache(postConnect);
		}

		return postConnect;
	}

	@Override
	public PostConnect updateImpl(PostConnect postConnect) {
		boolean isNew = postConnect.isNew();

		if (!(postConnect instanceof PostConnectModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(postConnect.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(postConnect);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in postConnect proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PostConnect implementation " +
				postConnect.getClass());
		}

		PostConnectModelImpl postConnectModelImpl = (PostConnectModelImpl)postConnect;

		if (Validator.isNull(postConnect.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			postConnect.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (postConnect.getCreateDate() == null)) {
			if (serviceContext == null) {
				postConnect.setCreateDate(now);
			}
			else {
				postConnect.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!postConnectModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				postConnect.setModifiedDate(now);
			}
			else {
				postConnect.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (postConnect.isNew()) {
				session.save(postConnect);

				postConnect.setNew(false);
			}
			else {
				postConnect = (PostConnect)session.merge(postConnect);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PostConnectModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { postConnectModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					postConnectModelImpl.getUuid(),
					postConnectModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					postConnectModelImpl.getGroupId(),
					postConnectModelImpl.getSyncState()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SYNC_STATE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SYNC_STATE,
				args);

			args = new Object[] { postConnectModelImpl.getSyncState() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SYNC_STATE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SYNC_STATE,
				args);

			args = new Object[] {
					postConnectModelImpl.getGroupId(),
					postConnectModelImpl.getDossierId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_F_POST_BY_DOSSIER_ID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_POST_BY_DOSSIER_ID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((postConnectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						postConnectModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { postConnectModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((postConnectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						postConnectModelImpl.getOriginalUuid(),
						postConnectModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						postConnectModelImpl.getUuid(),
						postConnectModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((postConnectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SYNC_STATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						postConnectModelImpl.getOriginalGroupId(),
						postConnectModelImpl.getOriginalSyncState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SYNC_STATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SYNC_STATE,
					args);

				args = new Object[] {
						postConnectModelImpl.getGroupId(),
						postConnectModelImpl.getSyncState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_GID_SYNC_STATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_GID_SYNC_STATE,
					args);
			}

			if ((postConnectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SYNC_STATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						postConnectModelImpl.getOriginalSyncState()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SYNC_STATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SYNC_STATE,
					args);

				args = new Object[] { postConnectModelImpl.getSyncState() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_SYNC_STATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_SYNC_STATE,
					args);
			}

			if ((postConnectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_POST_BY_DOSSIER_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						postConnectModelImpl.getOriginalGroupId(),
						postConnectModelImpl.getOriginalDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_POST_BY_DOSSIER_ID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_POST_BY_DOSSIER_ID,
					args);

				args = new Object[] {
						postConnectModelImpl.getGroupId(),
						postConnectModelImpl.getDossierId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_F_POST_BY_DOSSIER_ID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_POST_BY_DOSSIER_ID,
					args);
			}
		}

		entityCache.putResult(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
			PostConnectImpl.class, postConnect.getPrimaryKey(), postConnect,
			false);

		clearUniqueFindersCache(postConnectModelImpl, false);
		cacheUniqueFindersCache(postConnectModelImpl);

		postConnect.resetOriginalValues();

		return postConnect;
	}

	/**
	 * Returns the post connect with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the post connect
	 * @return the post connect
	 * @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPostConnectException {
		PostConnect postConnect = fetchByPrimaryKey(primaryKey);

		if (postConnect == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPostConnectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return postConnect;
	}

	/**
	 * Returns the post connect with the primary key or throws a {@link NoSuchPostConnectException} if it could not be found.
	 *
	 * @param postConnectId the primary key of the post connect
	 * @return the post connect
	 * @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect findByPrimaryKey(long postConnectId)
		throws NoSuchPostConnectException {
		return findByPrimaryKey((Serializable)postConnectId);
	}

	/**
	 * Returns the post connect with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the post connect
	 * @return the post connect, or <code>null</code> if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
				PostConnectImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PostConnect postConnect = (PostConnect)serializable;

		if (postConnect == null) {
			Session session = null;

			try {
				session = openSession();

				postConnect = (PostConnect)session.get(PostConnectImpl.class,
						primaryKey);

				if (postConnect != null) {
					cacheResult(postConnect);
				}
				else {
					entityCache.putResult(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
						PostConnectImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
					PostConnectImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return postConnect;
	}

	/**
	 * Returns the post connect with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param postConnectId the primary key of the post connect
	 * @return the post connect, or <code>null</code> if a post connect with the primary key could not be found
	 */
	@Override
	public PostConnect fetchByPrimaryKey(long postConnectId) {
		return fetchByPrimaryKey((Serializable)postConnectId);
	}

	@Override
	public Map<Serializable, PostConnect> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PostConnect> map = new HashMap<Serializable, PostConnect>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PostConnect postConnect = fetchByPrimaryKey(primaryKey);

			if (postConnect != null) {
				map.put(primaryKey, postConnect);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
					PostConnectImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PostConnect)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_POSTCONNECT_WHERE_PKS_IN);

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

			for (PostConnect postConnect : (List<PostConnect>)q.list()) {
				map.put(postConnect.getPrimaryKeyObj(), postConnect);

				cacheResult(postConnect);

				uncachedPrimaryKeys.remove(postConnect.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PostConnectModelImpl.ENTITY_CACHE_ENABLED,
					PostConnectImpl.class, primaryKey, nullModel);
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
	 * Returns all the post connects.
	 *
	 * @return the post connects
	 */
	@Override
	public List<PostConnect> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the post connects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @return the range of post connects
	 */
	@Override
	public List<PostConnect> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the post connects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of post connects
	 */
	@Override
	public List<PostConnect> findAll(int start, int end,
		OrderByComparator<PostConnect> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the post connects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of post connects
	 * @param end the upper bound of the range of post connects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of post connects
	 */
	@Override
	public List<PostConnect> findAll(int start, int end,
		OrderByComparator<PostConnect> orderByComparator,
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

		List<PostConnect> list = null;

		if (retrieveFromCache) {
			list = (List<PostConnect>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_POSTCONNECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_POSTCONNECT;

				if (pagination) {
					sql = sql.concat(PostConnectModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostConnect>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the post connects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PostConnect postConnect : findAll()) {
			remove(postConnect);
		}
	}

	/**
	 * Returns the number of post connects.
	 *
	 * @return the number of post connects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_POSTCONNECT);

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
		return PostConnectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the post connect persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PostConnectImpl.class.getName());
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
	private static final String _SQL_SELECT_POSTCONNECT = "SELECT postConnect FROM PostConnect postConnect";
	private static final String _SQL_SELECT_POSTCONNECT_WHERE_PKS_IN = "SELECT postConnect FROM PostConnect postConnect WHERE postConnectId IN (";
	private static final String _SQL_SELECT_POSTCONNECT_WHERE = "SELECT postConnect FROM PostConnect postConnect WHERE ";
	private static final String _SQL_COUNT_POSTCONNECT = "SELECT COUNT(postConnect) FROM PostConnect postConnect";
	private static final String _SQL_COUNT_POSTCONNECT_WHERE = "SELECT COUNT(postConnect) FROM PostConnect postConnect WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "postConnect.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PostConnect exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PostConnect exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PostConnectPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}